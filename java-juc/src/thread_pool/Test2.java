package thread_pool;

import java.util.concurrent.*;

public class Test2 {
    public static void main(String[] args) {
        ExecutorService service = new ThreadPoolExecutor(
                2,                                //corePoolSize
                5,                           //maximumPoolSize
                1L,                             //keepAliveTime
                TimeUnit.SECONDS,
                new LinkedBlockingQueue(3),           //阻塞队列
                Executors.defaultThreadFactory(),               //线程池工程
                new ThreadPoolExecutor.DiscardOldestPolicy()); //拒绝策略
        for (int i = 1; i <=10 ; i++) {
            final  int a = i;
            service.execute(()->{
                System.out.println(Thread.currentThread().getName()+"正在执行业务"+a);
            });
        }
    }
}
