package producer_consumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 生产者，消费者传统版
 */
class MyData{
    private int number;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();


    public void increase(){
        try {
           lock.lock();
           while (number!=0){  //防止虚假唤醒->使用循环判断
               condition.await();
           }
            number++;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void decrease(){
        try {
            lock.lock();
            while (number==0){
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
public class test {
    public static void main(String[] args) {
        MyData data =  new MyData();
        new Thread(()->{
            for (int i = 0; i <5 ; i++) {
                data.increase();
            }
        },"A").start();

        new Thread(()->{
            for (int i = 0; i <5 ; i++) {
                data.decrease();
            }
        },"B").start();
    }
}
