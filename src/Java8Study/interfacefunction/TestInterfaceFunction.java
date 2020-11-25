package Java8Study.interfacefunction;

import org.junit.Test;

public class TestInterfaceFunction {
    @Test
    public void test1(){
        String res = new SubClass().getName();
        //调用发现，使用的是class类的getName方法
        //即 类优先原则
        System.out.println(res);
    }
}
