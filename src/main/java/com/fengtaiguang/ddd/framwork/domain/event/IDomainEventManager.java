package com.fengtaiguang.ddd.framwork.domain.event;

import java.util.List;

public interface IDomainEventManager {
	void registerDomainEvent(List<Class<?>> domainEventTypes);

	void registerSubscriber(List<ISubscriber> items);

	<T extends IDomainEvent> void publishEvent(T obj);
}
