package reentrant_lock;

import java.sql.Time;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁（递归锁）
 * 指的是同一线程外层函数获得锁之后，内层函数仍然能自动获取该锁的代码
 * 在同一线程，外层函数获得锁的时候，在进入内层函数时会自动获取锁
 */

class Phone{

    Lock lock = new ReentrantLock();

    //***********************************************************************************分割线

    public synchronized void send(){
        System.out.println(Thread.currentThread().getName()+"  send message!");
        call();
    }
    public synchronized void call(){
        System.out.println(Thread.currentThread().getName()+"  call me!");
    }

    //***********************************************************************************分割线
    public void send2(){
        try{
            lock.lock();
            System.out.println(Thread.currentThread().getName()+"  send message!");
            call2();
        }finally {
            lock.unlock();
        }
    }
    public void call2(){
        try{
            lock.lock();
            System.out.println(Thread.currentThread().getName()+"  call me!");
        }finally {
            lock.unlock();
        }
    }
}

public class Test {
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(()->{
            phone.send();
        },"t1").start();

        new Thread(()->{
            phone.send();
        },"t2").start();

        //***********************************************************************************分割线

        try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println();
        System.out.println();

        //***********************************************************************************分割线

        new Thread(()->{
            phone.send2();
        },"t3").start();

        new Thread(()->{
            phone.send2();
        },"t4").start();
    }
}
