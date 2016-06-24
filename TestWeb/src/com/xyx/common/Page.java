package com.xyx.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Page implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int pageCount=0;
	private int currentPage=0;
	private int pageSize=1;
	private int totalCount=0;
	
	@SuppressWarnings("rawtypes")
	private List list=new ArrayList();
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	@SuppressWarnings("rawtypes")
	public List getList() {
		return list;
	}
	@SuppressWarnings("rawtypes")
	public void setList(List list) {
		this.list = list;
	}
	
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public void resetPageNo(){
		if(totalCount%pageSize==0){
			pageCount= totalCount/pageSize;
		}else{
			pageCount=totalCount/pageSize+1;
		}
	}
	

}
