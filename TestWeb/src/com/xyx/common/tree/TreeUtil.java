package com.xyx.common.tree;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class TreeUtil {
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public JSONArray getJson(List list,int parentid){
		List tempList=new ArrayList();
		for(Object bean:list){
			TreeBean treeBean=(TreeBean)bean;
			if(treeBean.getParentId()==parentid){
				tempList.add(bean);
			}
		}
		if(tempList.size()==0){
			return null;
		}
		JSONArray array=new JSONArray();
		for(Object tObject:tempList){
			TreeBean bean=(TreeBean)tObject;
			net.sf.json.JSONObject object=net.sf.json.JSONObject.fromObject(tObject);
			object.put("text", object.get("name").toString());
			object.remove("name");
			object.put("pid", object.getInt("parentId"));
			object.remove("parentId");
			JSONObject stateJsonObject=new JSONObject();
			if(parentid==-1){
				stateJsonObject.put("selected", true);
			}else{
				stateJsonObject.put("selected", false);
			}
			object.put("state", stateJsonObject);
			object.put("nodes", getJson(list, bean.getId()));
			array.add(object);
		}
		return array;
	}

	public static void main(String[] args) {
		List<TreeBean> list=new ArrayList<TreeBean>();
		TreeBean bean1=new TreeBean(1,4,"1");
		TreeBean bean2=new TreeBean(2,4,"1");
		TreeBean bean3=new TreeBean(3,2,"second");
		TreeBean root=new TreeBean(4,-1,"root");
		list.add(bean1);
		list.add(bean2);
		list.add(bean3);
		list.add(root);
		try{
//			root.put("id", 0);
//			root.put("parentid", -1);
//			root.put("nodename", "root");
//			root.put("childs", new TreeUtil().getJson(list, 0));
			System.out.println(new TreeUtil().getJson(list, -1).get(0).toString());
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
