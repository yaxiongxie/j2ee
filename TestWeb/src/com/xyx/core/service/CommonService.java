package com.xyx.core.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.xyx.common.BaseService;
import com.xyx.common.tree.TreeUtil;
import com.xyx.core.bean.CorePerson;

@Component
public class CommonService extends BaseService {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getDepartmentAndPersonTree() throws Exception {
		List list=getListByHQL("from CoreDepartment",null);
		List<CorePerson> persons=getListByHQL("from CorePerson", null);
		String result=new TreeUtil().getDepartmentAndPersonTree(list,persons,"", -1).toString();
		return result;
	}
	
	public Object login(String username,String password){
		Object object=getByHQL("from CorePerson where username=? and password=?", username,password);
		return object;
	}
	
	
	

	
}
