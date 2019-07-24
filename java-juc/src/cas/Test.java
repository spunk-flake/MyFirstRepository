package cas;

import java.util.concurrent.atomic.AtomicInteger;

public class Test {
    /**
     * CSA compareAndSet 比较并交换
     *
     */
    public static void main(String[] args) {
        AtomicInteger integer = new AtomicInteger(0);
        integer.compareAndSet(0,1);
        System.out.println(integer.compareAndSet(0, 1)+"   current value: "+integer.get());
        System.out.println(integer.compareAndSet(2, 3)+"   current value: "+integer.get());
    }
}
