package com.fengtaiguang.ddd.framwork.domain.event;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 事件发布器
 * 
 *
 */
public class DomainEventPublisher {
	private final List<ISubscriber> subscribers = new ArrayList<ISubscriber>();
	private static final ExecutorService pool;

	static {
		pool = Executors.newCachedThreadPool();
	}
	/**
	 * 订阅事件
	 * 
	 * @param subscriber
	 */
	public <T extends IDomainEvent> void subscribe(IDomainEventSubscriber<T> subscriber) {
		subscribers.add(subscriber);
	}

	/**
	 * 发布事件
	 * 
	 * @param aDomainEvent
	 */
	@SuppressWarnings("unchecked")
	public <T extends IDomainEvent> void publish(final T aDomainEvent) {
		for (ISubscriber sub : this.subscribers) {
			IDomainEventSubscriber<T> subscribedTo = (IDomainEventSubscriber<T>) sub;

			if (subscribedTo != null && subscribedTo.suscribedToEventType() == aDomainEvent.getClass()) {
				Task<T> task = new Task<T>(subscribedTo, aDomainEvent);
				pool.execute(task);
			}
		}
	}
}
