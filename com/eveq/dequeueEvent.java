package com.eveq;

import java.awt.AWTEvent;

public class dequeueEvent extends AWTEvent{
	private static final int id=AWTEvent.RESERVED_ID_MAX+500;
	private int count;

	public dequeueEvent(Queue q){
		super(q,id);
	}

	public void setCount(int c){
		count=c;
	}
	public int getCount(){
		return count;
	}
}