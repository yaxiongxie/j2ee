package com.xyx.common.tree;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class TreeUtil {
	
	public JSONArray getJson(List<TreeBean> list,int parentid){
		List<TreeBean> tempList=new ArrayList<TreeBean>();
		for(TreeBean bean:list){
			if(bean.getParentid()==parentid){
				tempList.add(bean);
			}
		}
		if(tempList.size()==0){
			return null;
		}
		JSONArray array=new JSONArray();
		for(TreeBean bean:tempList){
			JSONObject object=new JSONObject();
			try{
				object.put("id", bean.getId());
				object.put("parentid",bean.getParentid());
				object.put("name", bean.getNodename());
				object.put("childs", getJson(list, bean.getId()));
			}catch (Exception e) {
				e.printStackTrace();
			}
			array.put(object);
		}
		return array;
	}

	public static void main(String[] args) {
		List<TreeBean> list=new ArrayList<TreeBean>();
		TreeBean bean1=new TreeBean(1,0,"1");
		TreeBean bean2=new TreeBean(2,0,"1");
		TreeBean bean3=new TreeBean(3,2,"1");
		list.add(bean1);
		list.add(bean2);
		list.add(bean3);
		JSONObject root=new JSONObject();
		try{
			root.put("id", 0);
			root.put("parentid", -1);
			root.put("nodename", "root");
			root.put("childs", new TreeUtil().getJson(list, 0));
			System.out.println(root.toString());
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
