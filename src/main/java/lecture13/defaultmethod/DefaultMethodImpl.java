package lecture13.defaultmethod;

/**
 * * @Author: cuixin
 * * @Date: 2019/9/18 9:39
 * todo java8 新特性
 */
public class DefaultMethodImpl implements DefaultMethod {
    @Override
    public String hello() {
        return "How you doing";
    }

    public static void main(String[] args){
        DefaultMethod d = new DefaultMethodImpl();
        System.out.println(d.hello());
        d = new DefaultMethodImpl2();
        System.out.println(d.hello());
    }
}
