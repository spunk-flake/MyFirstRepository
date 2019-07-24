package spinLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

class MySpinLock{
    AtomicReference<Thread> reference = new AtomicReference<>();
    public void lock(){
        System.out.println(Thread.currentThread().getName()+"  want to get lock");
        while (!reference.compareAndSet(null,Thread.currentThread())){
            System.out.println(Thread.currentThread().getName()+"  try to get lock");
        }
        System.out.println(Thread.currentThread().getName()+"  got the lock");
    }
    public void unLock(){
        System.out.println(Thread.currentThread().getName()+"  want to  Unlock");
        reference.compareAndSet(Thread.currentThread(),null);
        System.out.println(Thread.currentThread().getName()+"  Unlock ");
    }
}
public class Test {
    public static void main(String[] args) {
        MySpinLock mySpinLock = new MySpinLock();
        new Thread(()->{
            mySpinLock.lock();
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            mySpinLock.unLock();
        },"A").start();

        new Thread(()->{
            mySpinLock.lock();
            mySpinLock.unLock();
        },"B").start();
    }
}
