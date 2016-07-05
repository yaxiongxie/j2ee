package com.xyx.core.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Component;

import com.xyx.common.BaseService;
import com.xyx.common.Page;
import com.xyx.core.bean.CoreRole;
import com.xyx.core.bean.CoreRoleAuth;

@Component
public class RoleService extends BaseService {

	public void saveRole(JSONObject jsonObject) throws Exception {
		CoreRole coreRole = new CoreRole();
		int id = jsonObject.getInt("id");
		if(id!=0){
			coreRole=load(CoreRole.class, id);
		}
		String name = jsonObject.getString("name");
		coreRole.setName(name);
		coreRole.setId(id);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		coreRole.setCreatetime(sdf.format(new Date()));
		saveOrUpdate(coreRole);
		queryHql("delete CoreRoleAuth where roleId=?", coreRole.getId());
		String auths=jsonObject.getString("auths");
		for(String string:auths.split(",")){
			if(string.equals("")){
				continue;
			}
			CoreRoleAuth coreRoleAuth=new CoreRoleAuth();
			coreRoleAuth.setAuthId(new Integer(string));
			coreRoleAuth.setRoleId(coreRole.getId());
			saveOrUpdate(coreRoleAuth);
		}
	}

	public String loadRole(JSONObject jsonObject) throws Exception {
		int id = jsonObject.getInt("id");
		CoreRole role = load(CoreRole.class, id);
		JSONObject rJsonObject=JSONObject.fromObject(role);
		List list=getListByHQL("from CoreRoleAuth where roleId=?", id);
		JSONArray array=new JSONArray();
		for(Object object:list){
			CoreRoleAuth coreRoleAuth=(CoreRoleAuth)object;
			array.add(coreRoleAuth.getAuthId());
		}
		rJsonObject.put("auths", array);
		return rJsonObject.toString();
	}

	public void deleteRole(JSONObject jsonObject) throws Exception {
		int id = jsonObject.getInt("id");
		queryHql("delete CoreRoleAuth where roleId=?", id);
		deleteById(CoreRole.class, id);
	}
	
	public String getAuthAll() throws Exception {
		List list=getListByHQL("from CoreAuth", null);
		return JSONArray.fromObject(list).toString();
	}
	
	public String loadRolePage(JSONObject jsonObject) throws Exception{
		int pageNo=jsonObject.getInt("currentPage");
		int pageSize=jsonObject.getInt("pageSize");
		Page page=findPageByFetchedHql("from CoreRole", null, pageNo, pageSize, new Object[]{});
		System.out.println("page:"+page.getCurrentPage()+":"+page.getPageCount()+":"+page.getPageSize()+":"+page.getTotalCount());
		return net.sf.json.JSONObject.fromObject(page).toString();
	}
}
