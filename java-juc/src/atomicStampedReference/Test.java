package atomicStampedReference;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

public class Test {
    public static void main(String[] args) {
        AtomicStampedReference<Integer> stampedReference = new AtomicStampedReference<>(100,1);
        new Thread(()->{
            Integer stamp = stampedReference.getStamp();
            //休眠一秒，让线程t2拿到初始的stamp
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(stampedReference.compareAndSet(100, 101, stamp, stamp + 1));
            Integer stamp1 = stampedReference.getStamp();
            System.out.println(stampedReference.compareAndSet(101, 100, stamp1, stamp + 1));
            System.out.println("t1线程两次修改后的stamp值： "+stampedReference.getStamp());
        },"t1").start();

        new Thread(()->{
            Integer stamp = stampedReference.getStamp();
            try { TimeUnit.SECONDS.sleep(4); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println("t2线程获取到的初始stamp: "+stamp);
            System.out.println("t2使用初始stamp修改结果： "+stampedReference.compareAndSet(100, 2019, stamp, stamp + 1));
        },"t2").start();
    }
}
