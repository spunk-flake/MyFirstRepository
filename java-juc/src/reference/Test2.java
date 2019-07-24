package reference;

import java.lang.ref.SoftReference;

public class Test2 {
    /**
     * JVM配置，故意产生大对象并配置小的内存，让它内存不够用了导致OOM，看软引用的回收情况
     * 配置 -Xms5m -Xmx5m
     */
    public static void memory_notEnough(){
        Object o1 = new Object();
        SoftReference<Object> reference = new SoftReference<>(new Object());
        System.out.println(o1);
        System.out.println(reference.get());
        try{
            Byte[] b = new Byte[30*1024*1024];
        }
        finally {
            System.out.println(o1);
            System.out.println(reference.get());
        }
    }
    public static void main(String[] args) {
        memory_notEnough();
    }
}
