package instruction_rearrangement;

class  MyData{
    int a = 0;
    boolean f = false;

    public void change_status(){
        a = 1;      //指令1
        f = true;   //指令2
        //如果存在指令重排，可能导致指令 2 早于指令 1 执行，存在一种情况： f->true , a仍然等于 0 的情况下，另一线程调用 check() 得到的结果为 5 而不是 6 ；
    }
    public void check(){
        if (f){
            a = a+5;
            System.out.println("result a: "+ a);
        }
    }
}
public class Test {
    public static void main(String[] args) {
        MyData data = new MyData();
            new Thread(() -> {
                data.change_status();
            }, "a").start();

            new Thread(() -> {
                data.check();
            }, "b").start();
        }
}
