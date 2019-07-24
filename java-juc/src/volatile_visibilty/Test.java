package volatile_visibilty;

import java.util.concurrent.TimeUnit;

class Mydata{
    volatile int number = 0;
    public void change(){
        this.number=1;
    }
}
public class Test {
    public static void main(String[] args) {
        Mydata mydata = new Mydata();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"  come in !");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) { }
            mydata.change();
            System.out.println(Thread.currentThread().getName()+ "  change over !");
            System.out.println(Thread.currentThread().getName()+ "  number value :"+mydata.number);
        },"aaa").start();

        while (mydata.number == 0){

        }
        System.out.println(Thread.currentThread().getName()+ "  number value :"+mydata.number);

    }
}
