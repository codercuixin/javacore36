package lecture19;

import java.util.concurrent.Semaphore;

/**
 * * @Author: cuixin
 * * @Date: 2019/9/18 16:15
 * Semaphore实现原理 AQS state中保存permits，如果还有剩余就减1，并继续当前线程；否则没有剩余，则阻塞当前线程并将当前线程添加到等待队列
 */
public class UsualSemaphoreSample {

    public static void main(String[] args){
        System.out.println("Action...Go");
        Semaphore semaphore = new Semaphore(5);
        for(int i=0; i<10; i++){
            Thread t = new Thread(new SemaphoreWorker(null, semaphore));
            t.start();
        }
    }
}

class SemaphoreWorker implements Runnable{
    private String name;
    private Semaphore semaphore;
    public SemaphoreWorker(String name, Semaphore semaphore){
        this.name = name;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            log("is waiting for a permit");
            semaphore.acquire();
            log("acquired a permit");
            log("executed");
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            log("released a permit");
            semaphore.release();
        }

    }

    private void log(String msg){
        if(name == null){
            name = Thread.currentThread().getName();
        }
        System.out.println(name+ " "+msg);
    }
}
