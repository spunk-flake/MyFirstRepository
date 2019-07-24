package intern;

public class Test {
    public static void main(String[] args) {
        /**
         * 　s3与s4有所不同的是s3.intern(); 先在字符串常量池中查找是否存在"bc"，再从堆中查找，
         * 　然后将堆中s3的引用存储到字符串常量池中。
         * 　String s4 = "11"; 创建的时候发现字符串常量池中有了“11”（s3），然后指向s3引用的对象。
         * 　因此s3与s4的引用相同，返回 true。
         */
        String s3 = new String("b")+new String("c");
        s3.intern();
        String s4 = "bc";
        System.out.println(s3==s4);//true
    }
}
