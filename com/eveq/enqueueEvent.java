package com.eveq;

import java.awt.AWTEvent;

public class enqueueEvent extends AWTEvent{
	private static final int id=AWTEvent.RESERVED_ID_MAX+22;
	private int count;

	public enqueueEvent(Queue q){
		super(q,id);
	}
	public void setCount(int c){
		count=c;
	}
	public int getCount(){
		return count;
	}
}