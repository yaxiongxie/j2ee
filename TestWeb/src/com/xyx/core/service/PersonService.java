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
import com.xyx.core.bean.CorePerson;

@Component("PersonService")
public class PersonService extends BaseService {

	public String savePerson(JSONObject jsonObject) throws Exception {
		CorePerson corePerson=(CorePerson) net.sf.json.JSONObject.toBean(jsonObject,CorePerson.class);
		corePerson.setPassword(MD5.GetMD5Code(corePerson.getPassword()));
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		corePerson.setCreatetime(sdf.format(new Date()));
		saveOrUpdate(corePerson);
		JSONObject returnObject=JSONObject.fromObject(corePerson);
		return returnObject.toString();
	}

	public String loadPerson(JSONObject jsonObject) throws Exception {
		int id = jsonObject.getInt("id");
		CorePerson corePerson = load(CorePerson.class, id);
		CorePerson newPerson=new CorePerson();
		BeanUtils.copyProperties(newPerson, corePerson);
		return net.sf.json.JSONObject.fromObject(newPerson).toString();
	}

	public void deletePerson(JSONObject jsonObject) throws Exception {
		int id = jsonObject.getInt("id");
		deleteById(CorePerson.class, id);
	}
	
	public void deletePersonByIds(JSONObject jsonObject) throws Exception {
		String ids=jsonObject.getString("ids");
		for(String id:ids.split(",")){
			if(id.equals("")){
				continue;
			}
			deleteById(CorePerson.class, Integer.parseInt(id));
		}
	}
	
	public String loadPersonPage(JSONObject jsonObject) throws Exception{
		System.out.println("jsonObject===="+jsonObject);
		int pageNo=jsonObject.getInt("currentPage");
		int pageSize=jsonObject.getInt("pageSize");
		String hqlString="from CorePerson ";
		String whereString="";
		String realname=(String)jsonObject.get("realname");
		if(!StringUtils.isEmpty(realname)){
			whereString=" realname like '%"+realname+"%' ";
		}
		if(!StringUtils.isEmpty(whereString)){
			whereString="where "+whereString;
		}
		Page page=findPageByFetchedHql(hqlString+whereString, null, pageNo, pageSize, new Object[]{});
		System.out.println("page:"+page.getCurrentPage()+":"+page.getPageCount()+":"+page.getPageSize()+":"+page.getTotalCount());
		return net.sf.json.JSONObject.fromObject(page).toString();
	}
	
	public List loadPersonAll(){
		String hqlString="from CorePerson ";
		return getListByHQL(hqlString, null);
	}
}
