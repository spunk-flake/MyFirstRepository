package Error_Exception;

import sun.misc.VM;

import java.nio.ByteBuffer;

public class Test4 {
    public static void main(String[] args) {
        //配置 -XX:MaxDirectMemorySize=5m
        System.out.println("配置的maxDirectMemory:  "+ VM.maxDirectMemory()/1024/1024+"MB");
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(6*1024*1024);
    }
}
