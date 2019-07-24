package read_write_lock;



import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyHashMap{
    private volatile Map<Object,Object> map = new HashMap<>();
    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    public void set(Object key,Object value){
        try{
            rwLock.writeLock().lock();
            System.out.println(Thread.currentThread().getName()+"正在写入");
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"写入完成");
        }
        finally {
            rwLock.writeLock().unlock();
        }
    }

    public void get(Object key){
        try{
            rwLock.readLock().lock();
            System.out.println(Thread.currentThread().getName()+"正在读取");
            map.get(key);
            System.out.println(Thread.currentThread().getName()+"读取完成");
        }
        finally {
            rwLock.readLock().unlock();
        }
    }
}
public class Test {

    public static void main(String[] args) {
        MyHashMap myHashMap = new MyHashMap();
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                myHashMap.set(new Object(),new Object());
            },String.valueOf(i)).start();
        }

        for (int j = 0; j < 5; j++) {
            new Thread(()->{
                myHashMap.get(new Object());
            },String.valueOf(j)).start();
        }
    }
}
