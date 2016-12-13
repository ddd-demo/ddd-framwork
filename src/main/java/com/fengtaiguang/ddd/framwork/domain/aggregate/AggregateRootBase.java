package com.fengtaiguang.ddd.framwork.domain.aggregate;

import javax.annotation.Resource;

import com.fengtaiguang.ddd.framwork.domain.repository.IRepository;

public abstract class AggregateRootBase<T> {

	@Resource
	private IRepository daoTemplate;

	public void save() {
		daoTemplate.save(this);
	}

	public void delete() {
		daoTemplate.delete(this);
	}

	public void update() {
		daoTemplate.update(this);
	}

	public void load(String id) {
		daoTemplate.get(this.getClass(), id);
		// 拷贝
	}

	public T get(String id) {
		return (T) daoTemplate.get(this.getClass(), id);
	}

	public abstract String getId();

	public IRepository getDaoTemplate() {
		return daoTemplate;
	}

	public void setDaoTemplate(IRepository daoTemplate) {
		this.daoTemplate = daoTemplate;
	}

}
