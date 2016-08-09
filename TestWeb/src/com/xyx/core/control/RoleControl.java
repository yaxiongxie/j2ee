package com.xyx.core.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xyx.common.BaseControl;
import com.xyx.core.service.RoleService;

@Controller("RoleControl")
public class RoleControl extends BaseControl{
	
	public RoleService roleService;
	
	Logger logger=Logger.getLogger(RoleControl.class);
	
	@RequestMapping("core/saveRole.do")
	public void saveRole(HttpServletRequest request,HttpServletResponse response){
		try{
			JSONObject jsonObject=getJSONData(request);
			roleService.saveRole(jsonObject);
			returnSuccess(response);
		}catch (Exception e) {
			logger.error("role", e);
		}
	}
	
	@RequestMapping("core/loadRole.do")
	public void loadRole(HttpServletRequest request,HttpServletResponse response){
		try{
			JSONObject jsonObject=getJSONData(request);
			String result=roleService.loadRole(jsonObject);
			returnJson(response, result);
		}catch (Exception e) {
			logger.error("role", e);
		}
	}
	
	@RequestMapping("core/deleteRole.do")
	public void deleteRole(HttpServletRequest request,HttpServletResponse response){
		try{
			JSONObject jsonObject=getJSONData(request);
			roleService.deleteRole(jsonObject);
			returnSuccess(response);
		}catch (Exception e) {
			logger.error("role", e);
		}
	}
	
	@RequestMapping("core/getAuthAll.do")
	public void getAuthAll(HttpServletRequest request,HttpServletResponse response){
		try{
			String result=JSONArray.fromObject(roleService.getAuthAll()).toString();
			returnJson(response, result);
		}catch (Exception e) {
			logger.error("role", e);
		}
	}
	
	@RequestMapping("core/role/getDepartmentTree.do")
	public void getDepartmentTree(HttpServletRequest request,HttpServletResponse response){
		try{
			JSONObject jsonObject=getJSONData(request);
			String result=roleService.getDepartmentTree(jsonObject);
			returnJson(response, result);
		}catch (Exception e) {
			logger.error("role", e);
		}
	}
	
	@RequestMapping("core/addAuth.do")
	public void addAuth(HttpServletRequest request,HttpServletResponse response){
		try{
			JSONObject jsonObject=getJSONData(request);
			roleService.addAuth(jsonObject);
			returnSuccess(response);
		}catch (Exception e) {
			logger.error("role", e);
		}
	}
	
	@RequestMapping("core/loadRolePage.do")
	public void loadRolePage(HttpServletRequest request,HttpServletResponse response){
		try{
			JSONObject jsonObject=getJSONData(request);
			String result=roleService.loadRolePage(jsonObject);
			returnJson(response, result);
		}catch (Exception e) {
			logger.error("role", e);
		}
	}

	@Autowired
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	
}
