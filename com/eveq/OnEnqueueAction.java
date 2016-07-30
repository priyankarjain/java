package com.eveq;

public class OnEnqueueAction implements OnEnqueueListener{
	public void onEnqueue(enqueueEvent event){
		System.out.println(event.getSource()+" is the source that produced enqueue event");
		System.out.println("Item count: "+event.getCount());
		System.out.println("X===============================================================X");
	}
}
