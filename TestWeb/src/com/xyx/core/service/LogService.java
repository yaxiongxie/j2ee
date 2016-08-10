package com.xyx.core.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.xyx.common.BaseService;
import com.xyx.common.Page;
import com.xyx.common.StringUtil;
import com.xyx.core.bean.CoreLog;
import com.xyx.core.bean.CorePerson;

@Component("LogService")
public class LogService extends BaseService {

	public void deleteLog(JSONObject jsonObject) throws Exception {
		int id = jsonObject.getInt("id");
		deleteById(CoreLog.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public String loadLogPage(JSONObject jsonObject) throws Exception{
		int pageNo=jsonObject.getInt("currentPage");
		int pageSize=jsonObject.getInt("pageSize");
		String queryString=(String)jsonObject.get("queryString");
		String hqlString="from CoreLog ";
		if(!StringUtils.isEmpty(queryString)){
			List<CorePerson> personList=getListByHQL("from CorePerson where realname like ?", "%"+queryString+"%");
			String idx="";
			for(CorePerson corePerson:personList){
				idx=idx+corePerson.getId()+",";
			}
			if(!StringUtils.isEmpty(idx)){
				idx=idx.substring(0,idx.length()-1);
				hqlString=hqlString+" where userid in("+idx+") ";
			}
		}
		hqlString=hqlString+" order by id desc";
		Page page=findPageByFetchedHql(hqlString, null, pageNo, pageSize, new Object[]{});
		System.out.println("page:"+page.getCurrentPage()+":"+page.getPageCount()+":"+page.getPageSize()+":"+page.getTotalCount());
		List<CoreLog> logList=(List<CoreLog>)page.getList();
		for(CoreLog coreLog:logList){
			CorePerson corePerson=get(CorePerson.class, coreLog.getUserid());
			coreLog.setUsername(corePerson.getRealname());
		}
		return net.sf.json.JSONObject.fromObject(page).toString();
	}
}
