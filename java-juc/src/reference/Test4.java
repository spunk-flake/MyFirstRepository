package reference;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public class Test4 {
    public static void main(String[] args) {
        HashMap<Integer,String> map = new HashMap<>();
        Integer key = new Integer(1);
        String  value = new String("HashMap");
        map.put(key,value);
        System.out.println(map);
        key = null;
        System.gc();
        System.out.println(map);
        System.out.println("**************************");
        WeakHashMap<Integer,String> map1 = new WeakHashMap<>();
        Integer key1 = new Integer(1);
        String  value1 = new String("WeakHashMap");
        map1.put(key1,value1);
        System.out.println(map);
        key1 = null;
        System.gc();
        System.out.println(map1);
    }
}
