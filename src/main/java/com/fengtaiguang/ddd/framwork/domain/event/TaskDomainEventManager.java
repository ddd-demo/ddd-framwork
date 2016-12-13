package com.fengtaiguang.ddd.framwork.domain.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;

public class TaskDomainEventManager implements IDomainEventManager {

	private final HashMap<String, List<ISubscriber>> DOMAIN_EVENTS = new HashMap<>();

	private List<ISubscriber> domainEvents(String name) {
		return ObjectUtils.defaultIfNull(this.DOMAIN_EVENTS.get(name),
				new ArrayList<ISubscriber>(0));
	}

	@Override
	public void registerDomainEvent(List<Class<?>> domainEventTypes) {
	}

	@Override
	public void registerSubscriber(List<ISubscriber> item) {

		if (item.size() > 0) {
			String eventName = item.get(0).suscribedToEventType().getName();
			this.DOMAIN_EVENTS.put(eventName, item);
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends IDomainEvent> void publishEvent(T obj) {

		List<ISubscriber> subscribers = this.domainEvents(obj.getClass()
				.getName());
		DomainEventPublisher publisher = new DomainEventPublisher();
		for (ISubscriber sub : subscribers) {
			publisher.subscribe((IDomainEventSubscriber<T>) sub);
		}

		publisher.publish(obj);
	}
}
