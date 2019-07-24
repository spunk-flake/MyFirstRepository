package reference;

import java.lang.ref.Reference;

public class Test {
    public static void main(String[] args) {
        Object o1 = new Object();//这样定义的默认就是强引用
        Object o2 = o1;
        o1 = null;
        System.gc();
        System.out.println(o2);

    }
}
