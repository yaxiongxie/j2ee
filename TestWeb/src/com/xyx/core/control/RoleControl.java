package com.xyx.core.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xyx.common.BaseControl;
import com.xyx.core.service.RoleService;

@Controller
public class RoleControl extends BaseControl{
	@Autowired
	public RoleService coreService;
	
	Logger logger=Logger.getLogger(RoleControl.class);
	
	@RequestMapping("core/saveRole.do")
	public void saveRole(HttpServletRequest request,HttpServletResponse response){
		try{
			JSONObject jsonObject=getJSONData(request);
			jsonObject.put("name", "xieyaxiong");
			jsonObject.put("id", 0);
			coreService.saveRole(jsonObject);
			returnSuccess(response);
		}catch (Exception e) {
			logger.error("role", e);
		}
	}
	
	@RequestMapping("core/loadRole.do")
	public void loadRole(HttpServletRequest request,HttpServletResponse response){
		try{
			JSONObject jsonObject=getJSONData(request);
			jsonObject.put("id", 10);
			String result=coreService.loadRole(jsonObject);
			returnJson(response, result);
		}catch (Exception e) {
			logger.error("role", e);
		}
	}
	
	@RequestMapping("core/deleteRole.do")
	public void deleteRole(HttpServletRequest request,HttpServletResponse response){
		try{
			JSONObject jsonObject=getJSONData(request);
			jsonObject.put("id", 10);
			coreService.deleteRole(jsonObject);
			returnSuccess(response);
		}catch (Exception e) {
			logger.error("role", e);
		}
	}
	
	@RequestMapping("core/loadRolePage.do")
	public void loadRolePage(HttpServletRequest request,HttpServletResponse response){
		try{
			JSONObject jsonObject=getJSONData(request);
			jsonObject.put("pageNo", 1);
			jsonObject.put("pageSize", 8);
			String result=coreService.loadRolePage(jsonObject);
			returnJson(response, result);
		}catch (Exception e) {
			logger.error("role", e);
		}
	}

}
