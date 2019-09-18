package lecture18;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * * @Author: cuixin
 * * @Date: 2019/9/18 15:00
 * 锁定顺序导致的死锁
 */
public class DeadLockSample extends Thread {
    private final String first;
    private final String second;

    public DeadLockSample(String name, String first, String second) {
        super(name);
        this.first = first;
        this.second = second;
    }

    @Override
    public void run() {
        synchronized (first) {
            System.out.println(this.getName() + " obtained: " + first);
            try {
                Thread.sleep(1000);
                synchronized (second) {
                    System.out.println(this.getName() + "obtained: " + second);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        doDeadLockCheck();

        String lockA = "lockA";
        String lockB = "lockB";
        DeadLockSample t1 = new DeadLockSample("Thread1", lockA, lockB);
        DeadLockSample t2 = new DeadLockSample("Thread2", lockB, lockA);
        t1.start();
        t2.start();
        t1.join();
        t1.join();

    }

    private static void doDeadLockCheck(){
        ThreadMXBean mxBean = ManagementFactory.getThreadMXBean();
        Runnable deadLockCheck = new Runnable() {
            @Override
            public void run() {
                long[] threadIds = mxBean.findDeadlockedThreads();
                if(threadIds != null){
                    ThreadInfo[] threadInfos = mxBean.getThreadInfo(threadIds);
                    System.out.println("Detected deadlock threads");
                    for(ThreadInfo threadInfo: threadInfos){
                        System.out.println(threadInfo.getThreadName());
                    }
                }

            }
        };
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(deadLockCheck, 5L, 10L, TimeUnit.SECONDS);
    }
}
