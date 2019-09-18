package lecture13.oop;

import java.util.List;

/**
 * * @Author: cuixin
 * * @Date: 2019/9/18 9:47
 */
public class OverloadDemo {
    public int doSomething(){
        return 0;
    }
    //输入参数不同，方法签名不同，重载。
    public int doSomething(List<String> strs){
        return 0;
    }
    //只是return类型不一样，编译不能通过。
//    public short doSomething(){
//        return 0;
//    }
}
