package com.xyx.common.tree;

import java.util.ArrayList;
import java.util.List;

import com.xyx.core.bean.CorePerson;

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
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public JSONArray getDepartmentAndPersonTree(List list,List<CorePerson> corePersons,String checkids,int parentid){
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
			object.put("flag", "department");
			object.put("icon", "fa fa-cube");
			JSONObject stateJsonObject=new JSONObject();
			if(parentid==-1){
				stateJsonObject.put("selected", true);
			}else{
				stateJsonObject.put("selected", false);
			}
			object.put("state", stateJsonObject);
			JSONArray childArray=new JSONArray();
			for(CorePerson corePerson:corePersons){
				if(corePerson.getDepartmentId()==bean.getId()){
					JSONObject pObject=new JSONObject();
					pObject.put("id", corePerson.getId());
					pObject.put("text", corePerson.getRealname());
					pObject.put("pid", bean.getId());
					if(corePerson.getSex()==1){
						pObject.put("icon", "fa fa-male");
					}else{
						pObject.put("icon", "fa fa-female");
					}
					pObject.put("flag", "person");
					JSONObject psateJsonObject=new JSONObject();
					if(isContain(checkids, corePerson.getId()+"")){
						psateJsonObject.put("checked", true);
					}else{
						psateJsonObject.put("checked", false);
					}
					pObject.put("state", psateJsonObject);
					childArray.add(pObject);
				}
			}
			childArray.addAll(getDepartmentAndPersonTree(list,corePersons,checkids, bean.getId()));
			object.put("nodes", childArray);
			array.add(object);
		}
		return array;
	}
	
	public boolean isContain(String ids,String id){
		String[] sArr=ids.split(",");
		for(String string:sArr){
			if(string.equals(id)){
				return true;
			}
		}
		return false;
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
