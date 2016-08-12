package com.xyx.core.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.xyx.common.BaseService;
import com.xyx.common.Page;
import com.xyx.common.encrypt.MD5;
import com.xyx.common.tree.TreeUtil;
import com.xyx.core.bean.CoreDepartment;
import com.xyx.core.bean.CorePerson;
import com.xyx.core.bean.CoreWordbook;
import com.xyx.core.bean.CoreWordbookCategory;

@Component("WordbookService")
public class WordbookService extends BaseService {

	public void saveWordbookCategory(JSONObject jsonObject) throws Exception {
		CoreWordbookCategory category=new CoreWordbookCategory();
		int id=jsonObject.getInt("id");
		String name=jsonObject.getString("name");
		int pid=jsonObject.getInt("pid");
		category.setCode("");
		category.setName(name);
		category.setOrdernum(0);
		category.setParentId(new Integer(pid));
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		category.setCreatetime(sdf.format(new Date()));
		if(id!=0){
			category.setId(new Integer(id));
		}
		saveOrUpdate(category);
	}

	public CoreWordbookCategory loadWordbookCategory(JSONObject jsonObject) throws Exception {
		int id = jsonObject.getInt("id");
		CoreWordbookCategory category = load(CoreWordbookCategory.class, id);
		return category;
	}

	public void deleteWordbookCategory(JSONObject jsonObject) throws Exception {
		int id = jsonObject.getInt("id");
		deleteById(CoreWordbookCategory.class, id);
	}
	
	@SuppressWarnings("rawtypes")
	public String loadWordbookCategoryAll() throws Exception{
		List list=getListByHQL("from CoreWordbookCategory",null);
		String result=new TreeUtil().getJson(list, -1).toString();
		System.out.println("result==="+result);
		return result;
//		return net.sf.json.JSONObject.fromObject(page).toString();
	}
	
	
	public String saveWordBook(JSONObject jsonObject) throws Exception {
		CoreWordbook coreWordbook=(CoreWordbook) net.sf.json.JSONObject.toBean(jsonObject,CoreWordbook.class);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		coreWordbook.setCreatetime(sdf.format(new Date()));
		saveOrUpdate(coreWordbook);
		JSONObject returnObject=JSONObject.fromObject(coreWordbook);
		return returnObject.toString();
	}

	public String loadWordbook(JSONObject jsonObject) throws Exception {
		int id = jsonObject.getInt("id");
		CoreWordbook coreWordbook = load(CoreWordbook.class, id);
		CoreWordbook newWordbook=new CoreWordbook();
		BeanUtils.copyProperties(newWordbook, coreWordbook);
		return net.sf.json.JSONObject.fromObject(newWordbook).toString();
	}

	public void deleteWordbook(JSONObject jsonObject) throws Exception {
		int id = jsonObject.getInt("id");
		deleteById(CoreWordbook.class, id);
	}
	
	public void deleteWordbookByIds(JSONObject jsonObject) throws Exception {
		String ids=jsonObject.getString("ids");
		for(String id:ids.split(",")){
			if(id.equals("")){
				continue;
			}
			deleteById(CoreWordbook.class, Integer.parseInt(id));
		}
	}
	
	public String loadWordbookPage(JSONObject jsonObject) throws Exception{
		System.out.println("jsonObject===="+jsonObject);
		int pageNo=jsonObject.getInt("currentPage");
		int pageSize=jsonObject.getInt("pageSize");
		String hqlString="from CoreWordbook ";
		String whereString="";
		String name=(String)jsonObject.get("name");
		if(!StringUtils.isEmpty(name)){
			whereString=" name like '%"+name+"%' ";
		}
		Object categoryid=jsonObject.get("categoryid");
		if(categoryid!=null){
			if(StringUtils.isEmpty(whereString)){
				whereString=whereString+" categoryId="+categoryid;
			}else{
				whereString=whereString+" and categoryId="+categoryid;
			}
		}
		if(!StringUtils.isEmpty(whereString)){
			whereString="where "+whereString;
		}
		
		Page page=findPageByFetchedHql(hqlString+whereString+" order by ordernum", null, pageNo, pageSize, new Object[]{});
		System.out.println("page:"+page.getCurrentPage()+":"+page.getPageCount()+":"+page.getPageSize()+":"+page.getTotalCount());
		List<CoreWordbook> wordbooks=page.getList();
		for(CoreWordbook wordbook:wordbooks){
			wordbook.setPersonname(get(CorePerson.class, wordbook.getPid()).getRealname());
		}
		return net.sf.json.JSONObject.fromObject(page).toString();
	}
	
	
}
