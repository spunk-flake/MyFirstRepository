package count_down_latch;

import java.util.concurrent.CountDownLatch;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 1; i <= 5 ; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"离开教室");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        /**
         * 执行到main线程中的await方法时，如果count不为零，则该线程应当阻塞，只有等到coun减为0（即五位学生全部离开教室），由于count不为零而阻塞的线程被唤醒继续执行（锁门操作）
         */

        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"最后离开教室，锁门");
    }
}
