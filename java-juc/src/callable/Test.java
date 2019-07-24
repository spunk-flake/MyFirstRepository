package callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class MyCallable implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("into callable");
        return 2019;
    }
}

public class Test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask(new MyCallable());
        new Thread(futureTask,"a").start();
//        while (!futureTask.isDone()){
//            System.out.println("not isDone");
//        }
        int result = futureTask.get();
        System.out.println(result);
        System.out.println(Thread.currentThread().getName()+"*****************");
    }
}
