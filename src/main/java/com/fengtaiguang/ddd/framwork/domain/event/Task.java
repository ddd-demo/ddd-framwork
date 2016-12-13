package com.fengtaiguang.ddd.framwork.domain.event;

class Task<T extends IDomainEvent> implements Runnable {

	private final IDomainEventSubscriber<T> subscriber;
	private final T aDomainEvent;

	public Task(IDomainEventSubscriber<T> subscriber, T aDomainEvent) {
		this.subscriber = subscriber;
		this.aDomainEvent = aDomainEvent;
	}

	@Override
	public void run() {
		this.subscriber.handleEvent(this.aDomainEvent);
	}
}
