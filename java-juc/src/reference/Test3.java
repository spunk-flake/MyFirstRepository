package reference;

import java.lang.ref.WeakReference;

public class Test3 {
    public static void main(String[] args) {
        Object o1 = new Object();
        WeakReference<Object> reference = new WeakReference<>(new Object());
        System.out.println(o1);
        System.out.println(reference.get());
        System.gc();
        System.out.println(o1);
        System.out.println(reference.get());
    }
}
