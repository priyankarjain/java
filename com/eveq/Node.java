package com.eveq;

public class Node{
	private int data;
	Node link;

	Node(){
		data=0;
		link=null;
	}

	public void setData(int data){
		this.data=data;
	}
	public int getData(){
		return data;
	}
}