package com.eveq;

import java.util.EventListener;

public interface OnDequeueListener extends EventListener{
	void onDequeue(dequeueEvent event);
}