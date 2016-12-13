package com.fengtaiguang.ddd.framwork.domain.entity;

import java.io.Serializable;

public interface IEntity<T extends Serializable> extends Serializable {
	String getId();
}
