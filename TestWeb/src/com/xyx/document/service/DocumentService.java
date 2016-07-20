package com.xyx.document.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Component;

import com.xyx.common.BaseService;
import com.xyx.common.tree.TreeUtil;
import com.xyx.core.bean.CoreDepartment;
import com.xyx.document.bean.DocumentCategory;

@Component
public class DocumentService extends BaseService {

	public void saveCategory(JSONObject jsonObject) throws Exception {
		DocumentCategory documentCategory=new DocumentCategory();
		int id=jsonObject.getInt("id");
		String name=jsonObject.getString("name");
		int pid=jsonObject.getInt("pid");
		int personid=jsonObject.getInt("personid");
		documentCategory.setCode("");
		documentCategory.setName(name);
		documentCategory.setOrdernum(0);
		documentCategory.setParentId(new Integer(pid));
		documentCategory.setPersonid(personid);
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		documentCategory.setCreatetime(sdf.format(new Date()));
		if(id!=0){
			documentCategory.setId(new Integer(id));
		}
		saveOrUpdate(documentCategory);
	}

	public DocumentCategory loadCategory(JSONObject jsonObject) throws Exception {
		int id = jsonObject.getInt("id");
		DocumentCategory documentCategory = load(DocumentCategory.class, id);
		return documentCategory;
	}

	public void deleteCategory(JSONObject jsonObject) throws Exception {
		int id = jsonObject.getInt("id");
		deleteById(DocumentCategory.class, id);
	}
	
	@SuppressWarnings("rawtypes")
	public String loadCategoryAll(int personid) throws Exception{
		List list=getListByHQL("from DocumentCategory where personid=?",personid);
		String result=new TreeUtil().getJson(list, -1).toString();
		return result;
	}
}
