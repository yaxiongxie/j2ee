package com.xyx.core.service;

import java.sql.Timestamp;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.xyx.common.orm.BaseService;
import com.xyx.core.bean.CoreRole;

@Component
public class CoreService extends BaseService{
	
	public void saveRole(JSONObject jsonObject){
		CoreRole coreRole=new CoreRole();
		try {
			int id=jsonObject.getInt("id");
			String name=jsonObject.getString("name");
			coreRole.setName(name);
			coreRole.setCreatetime(new Timestamp(new Date().getTime()));
			if(id!=0){
				coreRole.setId(id);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		saveOrUpdate(coreRole);
	}

}
