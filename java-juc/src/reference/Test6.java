package reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.HashMap;

public class Test6 {
    public static void main(String[] args) {
        HashMap map = new HashMap();
        ReferenceQueue referenceQueue = new ReferenceQueue();
        new Thread(() -> {//每次往map里添加1M大小的元素
            while (true){
                SoftReference k;
                if ((k = (SoftReference) referenceQueue.poll()) != null)
                    System.out.println("---pull" + k);
            }
        }, "B").start();

        new Thread(() -> { //查看回收的对象
            for (int i = 1; i <= 1000; i++) {
                System.out.println("put\t" + i);
                SoftReference reference = new SoftReference(new Byte[1024 * 1024], referenceQueue);
                map.put(reference, Integer.valueOf(i));
                // map.put(new Byte[1024*1024],Integer.valueOf(i)); //OOM 错误
            }
        }, "A").start();
    }
}
