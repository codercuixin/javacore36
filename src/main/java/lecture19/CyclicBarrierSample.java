package lecture19;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * * @Author: cuixin
 * * @Date: 2019/9/18 17:00
 */
public class CyclicBarrierSample {
    public static void main(String[] args){
        CyclicBarrier barrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("Action...Go Again");
            }
        });
        for(int i=0; i<5; i++){
            Thread t = new Thread(new CyclicWorker(barrier));
            t.start();
        }
    }
    static class CyclicWorker implements Runnable{
        private CyclicBarrier barrier;
        public CyclicWorker(CyclicBarrier barrier){
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 3; i++) {
                    System.out.println("Executed");
                    barrier.await();
                }
            }catch (BrokenBarrierException e){
                e.printStackTrace();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
