package com.eveq;

import java.util.EventListener;

public interface OnEnqueueListener extends EventListener{
	void onEnqueue(enqueueEvent event);
}