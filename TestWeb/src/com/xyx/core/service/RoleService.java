package com.xyx.core.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Component;

import com.xyx.common.BaseService;
import com.xyx.common.Page;
import com.xyx.common.tree.TreeUtil;
import com.xyx.core.bean.CorePerson;
import com.xyx.core.bean.CorePersonRole;
import com.xyx.core.bean.CoreRole;
import com.xyx.core.bean.CoreRoleAuth;

@Component("RoleService")
public class RoleService extends BaseService {

	public void saveRole(JSONObject jsonObject) throws Exception {
		CoreRole coreRole = new CoreRole();
		int id = jsonObject.getInt("id");
		if(id!=0){
			coreRole=get(CoreRole.class, id);
		}
		String name = jsonObject.getString("name");
		coreRole.setName(name);
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

	@SuppressWarnings("rawtypes")
	public String loadRole(JSONObject jsonObject) throws Exception {
		int id = jsonObject.getInt("id");
		CoreRole role = load(CoreRole.class, id);
		CoreRole coreRole=new CoreRole();
		BeanUtils.copyProperties(coreRole, role);
		JSONObject rJsonObject=JSONObject.fromObject(coreRole);
		List list=getListByHQL("from CoreRoleAuth where roleId=?", id);
		JSONArray array=new JSONArray();
		for(Object object:list){
			CoreRoleAuth coreRoleAuth=(CoreRoleAuth)object;
			array.add(coreRoleAuth.getAuthId());
		}
		rJsonObject.put("auths", array);
		List authlist=getListByHQL("from CoreAuth", null);
		rJsonObject.put("authAll", authlist);
		return rJsonObject.toString();
	}

	public void deleteRole(JSONObject jsonObject) throws Exception {
		int id = jsonObject.getInt("id");
		queryHql("delete CoreRoleAuth where roleId=?", id);
		deleteById(CoreRole.class, id);
	}
	
	@SuppressWarnings("rawtypes")
	public List getAuthAll(){
		List list=getListByHQL("from CoreAuth", null);
		return list;
	}
	
	public List getRoleAuthAll(){
		List list=getListByHQL("from CoreRoleAuth", null);
		return list;
	}
	public List getPersonRoleAll(){
		List list=getListByHQL("from CorePersonRole", null);
		return list;
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getDepartmentTree(JSONObject jsonObject) throws Exception {
		int id=jsonObject.getInt("id");
		List personlist=getListByHQL("from CorePersonRole where roleId=? ", new Integer(id));
		String ids="";
		for(Object object:personlist){
			CorePersonRole corePersonRole=(CorePersonRole)object;
			ids=ids+corePersonRole.getPersonId()+",";
		}
		List list=getListByHQL("from CoreDepartment",null);
		List<CorePerson> persons=getListByHQL("from CorePerson", null);
		String result=new TreeUtil().getDepartmentAndPersonTree(list,persons,ids, -1).toString();
		return result;
	}
	
	public void addAuth(JSONObject jsonObject) throws Exception {
		int id=jsonObject.getInt("id");
		String ids=jsonObject.getString("ps");
		queryHql("delete CorePersonRole where roleId=?", new Integer(id));
		String[] sArr=ids.split(",");
		String pnames="";
		for(String string:sArr){
			if(string.equals("")){
				continue;
			}
			CorePersonRole corePersonRole=new CorePersonRole();
			corePersonRole.setPersonId(new Integer(string));
			corePersonRole.setRoleId(id);
			saveOrUpdate(corePersonRole);
			CorePerson corePerson=load(CorePerson.class, new Integer(string));
			pnames=pnames+corePerson.getRealname()+",";
		}
		if(!pnames.equals("")){
			pnames=pnames.substring(0,pnames.length()-1);
		}
		CoreRole coreRole=load(CoreRole.class, id);
		coreRole.setNames(pnames);
		coreRole.setPcount(sArr.length);
		saveOrUpdate(coreRole);
	}
	
	public String loadRolePage(JSONObject jsonObject) throws Exception{
		int pageNo=jsonObject.getInt("currentPage");
		int pageSize=jsonObject.getInt("pageSize");
		Page page=findPageByFetchedHql("from CoreRole", null, pageNo, pageSize, new Object[]{});
		System.out.println("page:"+page.getCurrentPage()+":"+page.getPageCount()+":"+page.getPageSize()+":"+page.getTotalCount());
		return net.sf.json.JSONObject.fromObject(page).toString();
	}
}
