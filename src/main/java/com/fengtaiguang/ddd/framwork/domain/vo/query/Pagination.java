package com.fengtaiguang.ddd.framwork.domain.vo.query;

import java.io.Serializable;
/**
 * 
 * @author pc
 *
 */
public class Pagination implements Serializable {
	//public static final String MYSQL = "mysql";
	//public static final String ORACLE = "oracle";
	/**当前要显示的页数*/
	private Long currentPage = 1L;
	/**符合条件的总页数*/
	private Long totalPage = 0L;
	/**每页显示的最多纪录数*/
	private int maxResult = 10;
	/**符合条件的信息总条数*/
	private Long totalItems = 0L;
	/**查询的起始位置*/
	private Long startPosition=0L;
	//private String sql;
	//private String dbType = MYSQL;
	/**计算起始位置*/
	public Long calculateStartPosition(long totalItems) {
		this.totalItems=totalItems;
		return calculateStartPosition();
		
	}
	/**计算起始位置*/
	public Long calculateStartPosition() {
		//totalItems = getItemCount(sql);
		if (totalItems == 0) {
			currentPage = 0L;
			totalPage = 0L;
			return startPosition;
		}
		Long y = totalItems % maxResult;
		Long totalPage = totalItems / maxResult;
		if (y > 0) {
			totalPage = totalPage + 1;
		}
		if(currentPage>totalPage){
			currentPage=totalPage;
		}
		if(currentPage<1){
			currentPage=1L;
		}
		startPosition=(currentPage-1)*maxResult;
		return startPosition;
	}

	public Long getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Long currentPage) {
		this.currentPage = currentPage;
	}

	public Long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Long totalPage) {
		this.totalPage = totalPage;
	}

	public int getMaxResult() {
		return maxResult;
	}

	public void setMaxResult(int maxResult) {
		this.maxResult = maxResult;
	}

	public Long getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(Long totalItems) {
		this.totalItems = totalItems;
	}

	public Long getStartPosition() {
		return startPosition;
	}

	public void setStartPosition(Long startPosition) {
		this.startPosition = startPosition;
	}
	


}
