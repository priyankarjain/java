package com.eveq;

public class eventQueues{
	public static void main(String[] args){
		Queue q=new Queue();
		OnEnqueueAction listener=new OnEnqueueAction();
		OnDequeueAction dlistener=new OnDequeueAction();
		q.addOnEnqueueListener(listener);
		q.addOnDequeueListener(dlistener);

		Producer p=new Producer(q);
		q.addOnDequeueListener(p);
		Consumer c=new Consumer(q);
		q.addOnEnqueueListener(c);

		try{
			Thread.sleep(4000);
			c.stop();
			p.stop();

			c.thread.join();
			p.thread.join();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}