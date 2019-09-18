package lecture13.defaultmethod;

/**
 * * @Author: cuixin
 * * @Date: 2019/9/18 9:37
 */
public interface DefaultMethod {
    default String hello(){
        return "Hello world";
    }
}
