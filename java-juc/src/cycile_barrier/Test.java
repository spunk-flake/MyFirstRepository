package cycile_barrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Test {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{ System.out.println("<<<召唤神龙>>>"+Thread.currentThread().getName()); });

        for (int i = 1; i <= 7 ; i++) {
            final int a=i;
            new Thread(()->{
                System.out.println("找到第"+a+"颗龙珠");
                try {
                    cyclicBarrier.await();
                    System.out.println("归还第"+a+"颗龙珠");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
