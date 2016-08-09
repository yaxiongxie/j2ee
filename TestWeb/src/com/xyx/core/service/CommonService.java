package com.xyx.core.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.xyx.common.BaseService;
import com.xyx.common.encrypt.MD5;
import com.xyx.common.tree.TreeUtil;
import com.xyx.core.bean.CoreAttachment;
import com.xyx.core.bean.CorePerson;

@Component("CommonService")
public class CommonService extends BaseService {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getDepartmentAndPersonTree() throws Exception {
		List list=getListByHQL("from CoreDepartment",null);
		List<CorePerson> persons=getListByHQL("from CorePerson", null);
		String result=new TreeUtil().getDepartmentAndPersonTree(list,persons,"", -1).toString();
		return result;
	}
	
	public Object login(String username,String password){
		Object object=getByHQL("from CorePerson where username=? and password=?", username,MD5.GetMD5Code(password));
		return object;
	}
	
	public List<CoreAttachment> getAttachments(int id,String tablename){
		List<CoreAttachment> list=getListByHQL("from CoreAttachment where relationid=? and tablename=?", id,tablename);
		return list;
	}
	
}
