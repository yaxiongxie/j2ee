package com.xyx.document.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xyx.common.BaseControl;
import com.xyx.core.service.DepartmentService;
import com.xyx.document.service.DocumentService;

@Controller
public class DocumentControl extends BaseControl{
	@Autowired
	public DocumentService documentService;
	
	Logger logger=Logger.getLogger(DocumentControl.class);
	
	@RequestMapping("document/saveCategory.do")
	public void saveCategory(HttpServletRequest request,HttpServletResponse response){
		try{
			System.out.println(request.getParameterMap());
			JSONObject jsonObject=getJSONData(request);
			jsonObject.put("personid", getLoginPerson(request).getId());
			System.out.println("jsonObject===="+jsonObject);
			documentService.saveCategory(jsonObject);
			returnSuccess(response);
		}catch (Exception e) {
			logger.error("document", e);
		}
	}
	
	@RequestMapping("document/deleteCategory.do")
	public void deleteCategory(HttpServletRequest request,HttpServletResponse response){
		try{
			JSONObject jsonObject=getJSONData(request);
			documentService.deleteCategory(jsonObject);
			returnSuccess(response);
		}catch (Exception e) {
			logger.error("document", e);
		}
	}
	
	@RequestMapping("document/loadCategory.do")
	public void loadCategory(HttpServletRequest request,HttpServletResponse response){
		try{
			String result=documentService.loadCategoryAll(getLoginPerson(request).getId());
			returnJson(response, result);
		}catch (Exception e) {
			logger.error("document", e);
		}
	}

}
