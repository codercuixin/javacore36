package lecture16;

/**
 * * @Author: cuixin
 * * @Date: 2019/9/18 14:24
 */
public class HelloWorld {
    public static void main(String[] args){
        System.out.println("Hello World");
        //找到根Thread group
        ThreadGroup curGroup =  Thread.currentThread().getThreadGroup();
        while(  curGroup.getParent() != null){
            curGroup = curGroup.getParent();
        }
        System.out.println(curGroup.activeCount());

        Thread[] threads = new Thread[curGroup.activeCount()];
        //Copies into the specified array every active thread in this thread group and its subgroups.
        curGroup.enumerate(threads);
        for(int i=0; i<threads.length; i++){
            System.out.println(threads[i].getThreadGroup()+ "-"+threads[i].getName() );
        }
    }
}
