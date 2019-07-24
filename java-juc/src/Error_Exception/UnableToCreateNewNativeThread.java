package Error_Exception;

public class UnableToCreateNewNativeThread {
    public static void main(String[] args) {
        // 别运行，上次卡死了
        for (int i = 0;  ; i++) {
            new Thread(()->{
                try {
                    Thread.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
