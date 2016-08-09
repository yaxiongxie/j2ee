package com.xyx.core.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xyx.common.BaseControl;
import com.xyx.core.service.DepartmentService;

@Controller("DepartmentControl")
public class DepartmentControl extends BaseControl{
	
	public DepartmentService deptService;
	
	Logger logger=Logger.getLogger(DepartmentControl.class);
	
	@RequestMapping("core/saveDept.do")
	public void saveDept(HttpServletRequest request,HttpServletResponse response){
		try{
			System.out.println(request.getParameterMap());
			JSONObject jsonObject=getJSONData(request);
			System.out.println("jsonObject===="+jsonObject);
			deptService.saveDept(jsonObject);
			returnSuccess(response);
		}catch (Exception e) {
			logger.error("dept", e);
		}
	}
	
	@RequestMapping("core/deleteDept.do")
	public void deleteDept(HttpServletRequest request,HttpServletResponse response){
		try{
			JSONObject jsonObject=getJSONData(request);
			deptService.deleteDept(jsonObject);
			returnSuccess(response);
		}catch (Exception e) {
			logger.error("role", e);
		}
	}
	
	@RequestMapping("core/loadDeptAll.do")
	public void loadDeptAll(HttpServletRequest request,HttpServletResponse response){
		try{
			String result=deptService.loadDeptAll();
			returnJson(response, result);
		}catch (Exception e) {
			logger.error("role", e);
		}
	}

	@Autowired
	public void setDeptService(DepartmentService deptService) {
		this.deptService = deptService;
	}

	
}
