package com.eveq;

import java.awt.AWTEvent;
import java.util.EventListener;
import java.awt.Toolkit;
import java.awt.EventQueue;
import java.awt.Component;
import javax.swing.event.EventListenerList;

public class Queue extends Component{
	private Node head;
	private Node tail;
	private volatile int count;
	private EventListenerList listenerList;
	private static EventQueue sysq=Toolkit.getDefaultToolkit().getSystemEventQueue();

	Queue(){
		enableEvents(0);
		head=tail=null;
		count=0;
		listenerList=new EventListenerList();
	}

	public void addOnEnqueueListener(OnEnqueueListener listener){
		listenerList.add(OnEnqueueListener.class,listener);
	}

	public void removeOnEnqueueListener(OnEnqueueListener listener){
		listenerList.remove(OnEnqueueListener.class,listener);
	}
	public void addOnDequeueListener(OnDequeueListener listener){
		listenerList.add(OnDequeueListener.class,listener);
	}

	public void removeOnDequeueListener(OnDequeueListener listener){
		listenerList.remove(OnDequeueListener.class,listener);
	}

	public void processEvent(AWTEvent event){

		if(event instanceof enqueueEvent){
			EventListener[] listeners=listenerList.getListeners(OnEnqueueListener.class);
			for(int i=0;i<listeners.length;i++){
				((OnEnqueueListener)listeners[i]).onEnqueue((enqueueEvent)event);
			}
		}else if(event instanceof dequeueEvent){
			EventListener[] listeners=listenerList.getListeners(OnDequeueListener.class);
			for(int i=0;i<listeners.length;i++){
				((OnDequeueListener)listeners[i]).onDequeue((dequeueEvent)event);
			}
		}else{
			super.processEvent(event);
		}
	}

	public synchronized void enqueue(int data) throws InterruptedException{		
		while(count>=100)
			wait();

		Node node=new Node();
		node.setData(data);

		if(tail==null){
			head=tail=node;
		}else{
			tail.link=node;
			tail=node;
		}
		count++;
		enqueueEvent e=new enqueueEvent(this);
		e.setCount(count); 
		sysq.postEvent(e);
		notifyAll();
	}

	public synchronized void dequeue() throws InterruptedException{
		while(head==null){
			wait();
		}

		if(head==tail){
			head=tail=null;
		}else{
			head=head.link;
		}
		count--;
		dequeueEvent e=new dequeueEvent(this);
		e.setCount(count);
		sysq.postEvent(e);
		notifyAll();
	}

	public int getCount(){
		return count;
	}

	public void display(){
		Node temp=head;

		while(temp!=null){
			System.out.print(temp.getData()+",");
			temp=temp.link;
		}
		System.out.println();
	}
}