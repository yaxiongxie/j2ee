package com.xyx.core.control;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.xyx.common.BaseControl;
import com.xyx.core.bean.CommonData;
import com.xyx.core.bean.CoreAuth;
import com.xyx.core.bean.CorePerson;
import com.xyx.core.bean.CorePersonRole;
import com.xyx.core.bean.CoreRoleAuth;
import com.xyx.core.service.CommonService;
import com.xyx.core.service.PersonService;
import com.xyx.core.service.RoleService;

@Controller
public class CommonControl extends BaseControl{
	@Autowired
	public CommonService commonService;
	@Autowired
	public PersonService personService;
	@Autowired
	public RoleService roleService;
	
	Logger logger=Logger.getLogger(CommonControl.class);
	
	
	@RequestMapping("core/getDepartmentAndPersonTree.do")
	public void getDepartmentAndPersonTree(HttpServletRequest request,HttpServletResponse response){
		try{
			String result=commonService.getDepartmentAndPersonTree();
			returnJson(response, result);
		}catch (Exception e) {
			logger.error("role", e);
		}
	}
	
	@RequestMapping("core/login.do")
	public String login(HttpServletRequest request,HttpServletResponse response){
		try{
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			Object object=commonService.login(username, password);
			if(object!=null){
				request.getSession().setAttribute("isLogin", true);
				request.getSession().setAttribute("user", object);
				return "redirect:/index.html";
			}
		}catch (Exception e) {
			logger.error("core", e);
		}
		return "redirect:/login.html?msg=loginfail";
		
	}
	
	@RequestMapping("core/initSystem.do")
	public void initSystem(HttpServletRequest request,HttpServletResponse response){
		CommonData.personList.clear();
		CommonData.personRoleList.clear();
		CommonData.roleAuthList.clear();
		CommonData.authList.clear();
		CommonData.authMap.clear();
    	
    	CommonData.personList=personService.loadPersonAll();
    	CommonData.personRoleList=roleService.getPersonRoleAll();
    	CommonData.roleAuthList=roleService.getRoleAuthAll();
    	CommonData.authList=roleService.getAuthAll();
    	for(CorePerson corePerson:CommonData.personList){
    		String username=corePerson.getUsername();
    		List<CoreAuth> auths=new ArrayList<CoreAuth>();
    		for(CorePersonRole personRole:CommonData.personRoleList){
    			if(personRole.getPersonId()==corePerson.getId()){
    				for(CoreRoleAuth roleAuth:CommonData.roleAuthList){
    					if(roleAuth.getRoleId()==personRole.getRoleId()){
    						for(CoreAuth auth:CommonData.authList){
    							if(auth.getId()==roleAuth.getAuthId()){
    								if(!auths.contains(auth)){
    									auths.add(auth);
    								}
    							}
    						}
    					}
    				}
    			}
    		}
    		CommonData.authMap.put(username, auths);
    	}
    	System.out.println(CommonData.authMap);
    	returnSuccess(response);
	}

}
