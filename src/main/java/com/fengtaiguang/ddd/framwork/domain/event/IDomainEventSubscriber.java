package com.fengtaiguang.ddd.framwork.domain.event;

/**
 * 事件订阅接口
 *
 * @param <T>
 */
public interface IDomainEventSubscriber<T extends IDomainEvent> extends ISubscriber {
    /**
     * 事件处理
     */
	void handleEvent(T aDomainEvent);
}
