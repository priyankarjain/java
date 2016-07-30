package com.eveq;

public class OnDequeueAction implements OnDequeueListener{
	public void onDequeue(dequeueEvent event){
		System.out.println(event.getSource()+" is the source that produced dequeue event");
		System.out.println("Item count: "+event.getCount());
		System.out.println("X===============================================================X");
	}
}