package volatile_noAtomicity;


class MyData{
    volatile int number;
    public void increaseOne(){
        this.number++;
    }
}
public class Test {
    public static void main(String[] args) {
        MyData data = new MyData();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 1000; j++) {
                new Thread(()->{
                    data.increaseOne();
                },String.valueOf(i)).start();
            }
        }
        //当其他线程执行完毕之后，再打印number的值
        //java默认的后台线程为main线程和线程，所以当activeCount>2的时候，说明还有其他的线程没有执行完毕
        while (Thread.activeCount() > 2){
            Thread.yield();
        }

        System.out.println(data.number);
    }


}
