package com.fengtaiguang.ddd.framwork.domain.repository;

import java.util.List;

public class Page<T> {
	private int totalRows;
	private List<T> rows;

	public Page(int totalRows, List<T> rows) {
		this.totalRows = totalRows;
		this.rows = rows;
	}

	public int getTotalRows() {
		return this.totalRows;
	}

	public List<T> getRows() {
		return this.rows;
	}
}
