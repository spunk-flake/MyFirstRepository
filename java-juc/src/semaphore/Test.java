package semaphore;

import sun.util.locale.provider.TimeZoneNameUtility;

import java.sql.Time;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);//模拟3个停车位
        for (int i = 1; i <= 6; i++) { //模拟6辆车
            new Thread(()-> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "号车抢到了车位");
                    TimeUnit.SECONDS.sleep(3);//模拟停车三秒
                    semaphore.release();
                    System.out.println(Thread.currentThread().getName() + "号车离开了车位");
                } catch (Exception e) { e.printStackTrace(); }

            },String.valueOf(i)).start();
        }
    }
}
