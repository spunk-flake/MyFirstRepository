package GC;

import java.util.Collections;
import java.util.concurrent.Callable;

class Test3 extends Thread{
    public void run(){
        System.out.println("s");
    }
}
interface Inter{
    void f();
    default void de(){
        System.out.println("default");
    };
    static void sta(){
        System.out.println("static");
    }
}
public class Test2 {
    public static void main(String[] args) {

         //new Test3().start();
        Inter inter = ()->{
            System.out.println("lambo");
        };
        inter.f();
        inter.de();
        Inter.sta();
    }
}
