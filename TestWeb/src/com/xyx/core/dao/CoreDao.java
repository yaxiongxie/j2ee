package com.xyx.core.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Component;

import com.xyx.bean.Test;
import com.xyx.common.orm.BaseDao;
import com.xyx.common.orm.Page;
import com.xyx.core.bean.CoreRole;

@Component
public class CoreDao extends BaseDao{
	
	@SuppressWarnings("unchecked")
	public void addRole() {
		Page page=findPageByFetchedHql("from CoreRole", null, 1, 5, new Object[]{});
		System.out.println("page:"+page.getCurrentPage()+":"+page.getPageNo()+":"+page.getPageSize()+":"+page.getTotalCount());
		List<CoreRole> list=(List<CoreRole>)page.getList();
		for(CoreRole test:list){
			System.out.println(test.getId()+"========"+test.getName());
		}
		JSONObject jsonObject=JSONObject.fromObject(page);
		System.out.println(jsonObject);
	}

}