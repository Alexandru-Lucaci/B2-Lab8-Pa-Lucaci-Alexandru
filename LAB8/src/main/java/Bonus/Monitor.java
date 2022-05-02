package Bonus;

import java.util.concurrent.ThreadPoolExecutor;

public class Monitor implements Runnable{
    private boolean run=true;
    private int delayInSeconds;
    private ThreadPoolExecutor poolExecutor;

    public Monitor(int delayInSeconds, ThreadPoolExecutor poolExecutor) {
        this.delayInSeconds = delayInSeconds;
        this.poolExecutor = poolExecutor;
    }

    @Override
    public void run() {
        while(run)
        {
            System.out.println("thread monitor");
            System.out.println("active count: "+ this.poolExecutor.getActiveCount());
            System.out.println("Maximim pool size: "+ this.poolExecutor.getMaximumPoolSize());
            System.out.println("TaskCount: "+ this.poolExecutor.getTaskCount());
            System.out.println("Core Pool size "+ this.poolExecutor.getCorePoolSize());
            System.out.println("isShotdown: "+ this.poolExecutor.isShutdown());
            System.out.println("isTerminated: "+ this.poolExecutor.isTerminated());
            try{
                Thread.sleep(delayInSeconds *1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    public void shutdown(){
        this.run=false;
    }
}
