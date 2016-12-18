package com.fengtaiguang.ddd.framwork.domain.vo.query;

import java.util.List;

import com.fengtaiguang.ddd.framwork.domain.vo.query.Pagination;

public class QueryAndResult  implements IQueryAndResult{

	private Object vo;
	private List list;
	private Pagination pagination=new Pagination();
	
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public Pagination getPagination() {
		return pagination;
	}
	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
	public Object getVo() {
		return vo;
	}
	public void setVo(Object vo) {
		this.vo = vo;
	}
}
