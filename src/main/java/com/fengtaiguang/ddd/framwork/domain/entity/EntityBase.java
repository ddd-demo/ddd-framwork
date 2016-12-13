package com.fengtaiguang.ddd.framwork.domain.entity;

import java.io.Serializable;

import com.fengtaiguang.ddd.framwork.domain.rules.BrokenRuleMessage;
import com.fengtaiguang.ddd.framwork.domain.rules.BrokenRuleObject;

public abstract class EntityBase<T extends Serializable> extends BrokenRuleObject implements IEntity<T> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3851243975849363906L;
	private String id;

	@Override
	protected abstract BrokenRuleMessage getBrokenRuleMessages();

	@Override
	public String getId() {
		return this.id;
	}

	protected void setId(String id) {
		this.id = id;
	}
}
