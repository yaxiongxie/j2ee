package com.xyx.core.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.xyx.common.BaseService;
import com.xyx.common.Page;
import com.xyx.common.tree.TreeUtil;
import com.xyx.core.bean.CoreDepartment;
import com.xyx.core.bean.CoreRole;

@Component
public class DepartmentService extends BaseService {

	public void saveDept(JSONObject jsonObject) throws Exception {
		CoreDepartment department=new CoreDepartment();
		int id=jsonObject.getInt("id");
		String name=jsonObject.getString("name");
		int pid=jsonObject.getInt("pid");
		department.setCode("");
		department.setName(name);
		department.setOrdernum(0);
		department.setParentId(new Integer(pid));
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		department.setCreatetime(sdf.format(new Date()));
		if(id!=0){
			department.setId(new Integer(id));
		}
		saveOrUpdate(department);
	}

	public String loadDept(JSONObject jsonObject) throws Exception {
		int id = jsonObject.getInt("id");
		CoreDepartment department = load(CoreDepartment.class, id);
		return net.sf.json.JSONObject.fromObject(department).toString();
	}

	public void deleteDept(JSONObject jsonObject) throws Exception {
		int id = jsonObject.getInt("id");
		deleteById(CoreDepartment.class, id);
	}
	
	public String loadDeptAll() throws Exception{
		List list=getListByHQL("from CoreDepartment",null);
		String result=new TreeUtil().getJson(list, -1).toString();
		System.out.println("result==="+result);
		return result;
//		return net.sf.json.JSONObject.fromObject(page).toString();
	}
}
