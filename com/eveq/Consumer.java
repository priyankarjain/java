package com.eveq;
import java.util.Random;

public class Consumer implements Runnable,OnEnqueueListener{

	private Queue queue;
	private Random random;
	private volatile boolean isRunning=true;
	public Thread thread;

	Consumer(Queue q){
		queue=q;
		isRunning=true;
		random=new Random();
		thread=new Thread(this,"consumer");
		thread.start();
	}

	public void run(){
		while(isRunning){
			try{
				int ctr=random.nextInt(queue.getCount()+1);
				for(int i=1;i<ctr;i++)
					queue.dequeue();
				Thread.sleep(100);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}

	public void stop(){
		isRunning=false;
		queue.display();
	}

	public void onEnqueue(enqueueEvent event){
		System.out.println(getClass().getName()+" is also listening to this event");
	}
}