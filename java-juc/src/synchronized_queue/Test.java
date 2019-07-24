package synchronized_queue;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) {
        SynchronousQueue<String> synchronousQueue = new SynchronousQueue();
        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+" 插入元素: 1");
                synchronousQueue.put("1");
                System.out.println(Thread.currentThread().getName()+" 插入元素: 2");
                synchronousQueue.put("2");
                System.out.println(Thread.currentThread().getName()+" 插入元素: 3");
                synchronousQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName()+" 取出元素："+synchronousQueue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName()+" 取出元素："+synchronousQueue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName()+" 取出元素："+synchronousQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"B").start();
    }
}
