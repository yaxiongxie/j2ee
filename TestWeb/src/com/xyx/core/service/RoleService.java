package com.xyx.core.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.xyx.common.BaseService;
import com.xyx.common.Page;
import com.xyx.core.bean.CoreRole;

@Component
public class RoleService extends BaseService {

	public void saveRole(JSONObject jsonObject) throws Exception {
		CoreRole coreRole = new CoreRole();
		int id = jsonObject.getInt("id");
		String name = jsonObject.getString("name");
		coreRole.setName(name);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		coreRole.setCreatetime(sdf.format(new Date()));
		if (id != 0) {
			coreRole.setId(id);
		}
		saveOrUpdate(coreRole);
	}

	public String loadRole(JSONObject jsonObject) throws Exception {
		int id = jsonObject.getInt("id");
		CoreRole role = load(CoreRole.class, id);
		return net.sf.json.JSONObject.fromObject(role).toString();
	}

	public void deleteRole(JSONObject jsonObject) throws Exception {
		int id = jsonObject.getInt("id");
		deleteById(CoreRole.class, id);
	}
	
	public String loadRolePage(JSONObject jsonObject) throws Exception{
		int pageNo=jsonObject.getInt("pageNo");
		int pageSize=jsonObject.getInt("pageSize");
		Page page=findPageByFetchedHql("from CoreRole", null, pageNo, pageSize, new Object[]{});
		System.out.println("page:"+page.getCurrentPage()+":"+page.getPageCount()+":"+page.getPageSize()+":"+page.getTotalCount());
		return net.sf.json.JSONObject.fromObject(page).toString();
	}
}
