package single_volatile;

class Single{
    /**
     * 加volatile的必要性
     * 原因在于指令重排的存在，加入volatile可以禁止指令重排
     *
     * 当某一个线程执行到第一次检测，读取到的instance不为null时，singleStance的引用对象可能没有完成初始化
     * singleStance = new Single();分为以下三步完成（伪代码）
     * memory = allocate();1、初始化对象内存空间
     * instance(memory);2、初始化对象
     * singleStance = memory;3、设置singleStance指向实例好的对象，此时singleStance!=null
     *
     * 由于步骤2 3 不存在数据依赖关系，如果发生指令重排
     * memory = allocate();1、初始化对象内存空间
     * singleStance = memory;3、设置singleStance指向实例好的对象，此时singleStance!=null,***但是对象还没有初始化完成***
     * inStance(memory);2、初始化对象
     *
     * 所以当一条线程访问instance不为null时，由于singleStance实例未必已初始化完成，也就造成了线程安全问题
     */
    volatile private  static Single singleStance = null;
    private Single(){
        System.out.println("Single 的构造方法被执行了！！！");
    }
    public static Single getSingleStance() {
        if (singleStance == null) {
            synchronized (Single.class) {
                if (singleStance == null) {
                    singleStance = new Single();
                }
            }
        }
        return singleStance;
    }
}

public class test {

}
