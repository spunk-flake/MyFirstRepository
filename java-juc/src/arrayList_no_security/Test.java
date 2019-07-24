package arrayList_no_security;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Test {
    /**
     *
     * 集合类并发修改异常案例展示： ArrayList
     */
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        List<String> list2 = Collections.synchronizedList(new ArrayList<>());
        List<String> list3 = new Vector <>();


        for (int i = 0; i <5 ; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,3));
                list.add(UUID.randomUUID().toString().substring(0,3));
                list.add(UUID.randomUUID().toString().substring(0,3));
                System.out.println(list.toString());
            },String.valueOf(i)).start();
        }
    }
}
/**
 * 1、故障现象：
 *     java.util.ConcurrentModificationException
 * 2、导致原因：
 *
 * 3、解决方案：
 *    3.1： new Vector<>();
 *    3.2:  Collections.synchronizedList(new ArrayList<>());
 *    3.3:  new CopyOnWriteArrayList();
 */
