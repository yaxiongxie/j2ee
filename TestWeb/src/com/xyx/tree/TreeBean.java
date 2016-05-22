package com.xyx.tree;

public class TreeBean {
	
	private int id;
	private int parentid;
	
	private String nodename;
	
	public TreeBean(){
		
	}
	
	public TreeBean(int id,int parentid,String nodename){
		this.id=id;
		this.parentid=parentid;
		this.nodename=nodename;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getParentid() {
		return parentid;
	}

	public void setParentid(int parentid) {
		this.parentid = parentid;
	}

	public String getNodename() {
		return nodename;
	}

	public void setNodename(String nodename) {
		this.nodename = nodename;
	}
	
	
}
