package com.fengtaiguang.ddd.framwork.domain.repository;

import com.fengtaiguang.ddd.framwork.domain.aggregate.IAggregateRoot;

public interface IUnitOfWorkRepository {
	void persistNewItem(IAggregateRoot item);
    void persistUpdatedItem(IAggregateRoot item);
    void persistDeletedItem(IAggregateRoot item);
}
