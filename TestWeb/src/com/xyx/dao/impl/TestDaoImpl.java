package com.xyx.dao.impl;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Component;

import com.xyx.bean.Test;
import com.xyx.common.orm.BaseDao;
import com.xyx.common.orm.Page;
import com.xyx.dao.TestDao;

@Component
public class TestDaoImpl extends BaseDao implements TestDao{
	
	@SuppressWarnings("unchecked")
	@Override
	public void test() {
		Page page=findPageByFetchedHql("from Test", null, 1, 5, new Object[]{});
		System.out.println("page:"+page.getCurrentPage()+":"+page.getPageNo()+":"+page.getPageSize()+":"+page.getTotalCount());
		List<Test> list=(List<Test>)page.getList();
		for(Test test:list){
			System.out.println(test.getId()+"========"+test.getName());
		}
		JSONObject jsonObject=JSONObject.fromObject(page);
		System.out.println(jsonObject);
	}

}
