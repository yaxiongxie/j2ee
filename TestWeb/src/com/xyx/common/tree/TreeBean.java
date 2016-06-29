package com.xyx.common.tree;

public class TreeBean {
	
	private int id;
	private int parentId;
	
	private String name;
	
	public TreeBean(){
		
	}
	
	public TreeBean(int id,int parentId,String name){
		this.id=id;
		this.parentId=parentId;
		this.name=name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
}
