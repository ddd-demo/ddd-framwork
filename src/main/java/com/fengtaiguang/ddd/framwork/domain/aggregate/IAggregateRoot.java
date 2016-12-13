package com.fengtaiguang.ddd.framwork.domain.aggregate;

import java.io.Serializable;

import com.fengtaiguang.ddd.framwork.domain.entity.IEntity;

public interface IAggregateRoot<T extends Serializable> extends IEntity<T> {

	
}
