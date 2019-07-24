package arrayList_no_security;

import java.util.*;

public class Test2 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        int[] a = new int[5];
        int b = 2;
        a[b++]=5;
        for (int i = 0; i <a.length ; i++) {
            System.out.println(i+" "+a[i]);
        }

    }
}
