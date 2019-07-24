package death_lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

class Resource{
    String re_a = "re_a";
    String re_b = "re_b";
}

public class Test {
    public static void main(String[] args) {
        Resource s = new Resource();
        new Thread(()->{
            synchronized (s.re_a){
                System.out.println(Thread.currentThread().getName()+"得到"+s.re_a);
                synchronized (s.re_b){
                    System.out.println(Thread.currentThread().getName()+"得到"+s.re_b);
                }
            }
        },"A").start();

        new Thread(()->{
            synchronized (s.re_b){
                System.out.println(Thread.currentThread().getName()+"得到"+s.re_b);
                synchronized (s.re_a){
                    System.out.println(Thread.currentThread().getName()+"得到"+s.re_a);
                }
            }
        },"B").start();
    }
}
