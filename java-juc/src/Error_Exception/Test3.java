package Error_Exception;

import sun.misc.VM;

import java.util.ArrayList;
import java.util.List;

public class Test3 {
    public static void main(String[] args) {
        System.out.println(VM.maxDirectMemory()/1024/1024);
        //-Xms10m -Xmx10m -XX:MaxDirectMemorySize=5m
        int i = 1;
        List list = new ArrayList();
        try {
            while (true) {
                list.add(String.valueOf(++i).intern());
            }
        }catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
