package com.eveq;
import java.util.Random;

class Producer implements Runnable,OnDequeueListener{

	private Queue queue;
	private Random random;
	private volatile boolean isRunning=true;
	public Thread thread;
	
	Producer(Queue q){
		queue=q;
		isRunning=true;
		random=new Random();
		thread=new Thread(this,"producer");
		thread.start();
	}

	public void run(){
		while(isRunning){
			try{
				int ctr=random.nextInt(10);
				for(int i=0;i<ctr;i++)
					queue.enqueue(random.nextInt(1000));

				Thread.sleep(50);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}

	public void stop(){
		isRunning=false;
		queue.display();
	}

	public void onDequeue(dequeueEvent event){
		System.out.println(getClass().getName()+" is also listening to this event");
	}

}