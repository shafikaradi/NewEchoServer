/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package echoserver;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bsc
 */
public class ThreadPool{
    
  
    private PoolWorker [] thread;
    private LinkedBlockingQueue queue;
    private int numberOfWorkers;

    public ThreadPool(int numberOfWorkers){

        queue = new LinkedBlockingQueue();
        thread = new PoolWorker[numberOfWorkers];
        this.numberOfWorkers = numberOfWorkers;
        this.CreateThreadPool();
    }

    private void CreateThreadPool(){
        for (int i = 0 ; i < numberOfWorkers; i++){

            thread[i] = new PoolWorker();
            thread[i].setName(String.format("Thread %d",i));
            thread[i].start();

        }
    }


    public  void execute(Runnable task){

        synchronized(queue) {
            queue.add(task);
            queue.notify();
        }
    }


    private class PoolWorker extends Thread{

        @Override
        public void run(){

            Runnable task;

            while (true){

                synchronized (queue){
                    while (queue.isEmpty()){

                        try{
                            queue.wait();
                        }catch (Exception e){

                        }

                    }
                    task = (Runnable) queue.poll();
                }

                task.run();


            }

        }

    }

    
}
