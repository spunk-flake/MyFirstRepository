package producer_consumer;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

class MyResource{
    private volatile boolean FLAG = true;
    private AtomicInteger atomicInteger = new AtomicInteger();
    BlockingQueue<String> blockingQueue = null;
    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void myProd() throws Exception{
        String data = null;
        boolean retValue;
        while (FLAG){
            data = atomicInteger.incrementAndGet()+"";;
            retValue = blockingQueue.offer(data,2L,TimeUnit.SECONDS);
            if (retValue){
                System.out.println(Thread.currentThread().getName()+"\t插入队列"+data+"成功");
            }else{
                System.out.println(Thread.currentThread().getName()+"\t插入队列"+data+"失败");
            }
            TimeUnit.SECONDS.sleep(2);
        }
        System.out.println(Thread.currentThread().getName()+"\tFLAG==FALSE,生产动作结束");
    }

    public void myConsumer() throws Exception{
        String result = null;
        while (FLAG){
           result = blockingQueue.poll(2L,TimeUnit.SECONDS);
           if (null == result || result.equalsIgnoreCase("")){
               FLAG = false;
               System.out.println(Thread.currentThread().getName()+"\t超过2秒没有取到蛋糕，消费退出");
               System.out.println();
               System.out.println();
               return;
           }
            System.out.println(Thread.currentThread().getName()+"\t 消费队列"+result+"成功");
        }
    }

    public void stop(){
        FLAG = false;
    }

}
public class test2 {

    public static void main(String[] args) throws InterruptedException {
        MyResource resource = new MyResource(new ArrayBlockingQueue<String>(10));
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t 线程消费启动");
            try {
                resource.myConsumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"Consumer").start();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t 线程生产启动");
            try {
                resource.myProd();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"Prod").start();

        TimeUnit.SECONDS.sleep(4);
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(Thread.currentThread().getName()+"\t 叫停，活动结束");
        resource.stop();
    }
}
