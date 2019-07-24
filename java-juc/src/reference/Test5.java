package reference;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.HashMap;

public class Test5 {
    public static void main(String[] args) {
        HashMap<Reference,String> map = new HashMap<>();
        ReferenceQueue<SoftReference<Byte[]>> referenceQueue = new ReferenceQueue();

        new Thread(()->{
            for (int i = 0; i < 10000; i++) {
                SoftReference reference = new SoftReference(new Byte[1*1024*1024],referenceQueue);
                map.put(reference,String.valueOf(i));
                System.out.println("put "+i);
            }
        },"A").start();

        new Thread(()->{
            SoftReference k;
            while ((k = (SoftReference) referenceQueue.poll())!=null)
            System.out.println("---pull"+k);
        },"B").start();


    }

}
