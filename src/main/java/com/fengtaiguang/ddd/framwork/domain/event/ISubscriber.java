package com.fengtaiguang.ddd.framwork.domain.event;

public interface ISubscriber {
	/**
     * 事件类型
     * @return
     */
    <T extends IDomainEvent> Class<?> suscribedToEventType();
}
