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
import com.xyx.core.service.WordbookService;

@Controller("WordbookControl")
public class WordbookControl extends BaseControl{
	
	public WordbookService wordbookService;
	
	Logger logger=Logger.getLogger(WordbookControl.class);
	
	@RequestMapping("core/saveWordbookCategory.do")
	public void saveWordbookCategory(HttpServletRequest request,HttpServletResponse response){
		try{
			System.out.println(request.getParameterMap());
			JSONObject jsonObject=getJSONData(request);
			wordbookService.saveWordbookCategory(jsonObject);
			returnSuccess(response);
		}catch (Exception e) {
			logger.error("dept", e);
		}
	}
	
	@RequestMapping("core/deleteWordbookCategory.do")
	public void deleteWordbookCategory(HttpServletRequest request,HttpServletResponse response){
		try{
			JSONObject jsonObject=getJSONData(request);
			wordbookService.deleteWordbookCategory(jsonObject);
			returnSuccess(response);
		}catch (Exception e) {
			logger.error("role", e);
		}
	}
	
	@RequestMapping("core/loadWordbookCategoryAll.do")
	public void loadWordbookCategoryAll(HttpServletRequest request,HttpServletResponse response){
		try{
			String result=wordbookService.loadWordbookCategoryAll();
			returnJson(response, result);
		}catch (Exception e) {
			logger.error("role", e);
		}
	}
	
	@RequestMapping("core/saveWordbook.do")
	public void saveWordbook(HttpServletRequest request,HttpServletResponse response){
		try{
			System.out.println(request.getParameterMap());
			JSONObject jsonObject=getJSONData(request);
			jsonObject.put("pid", getLoginPerson(request).getId());
			String result=wordbookService.saveWordBook(jsonObject);
			returnJson(response, result);
		}catch (Exception e) {
			logger.error("person", e);
		}
	}
	
	@RequestMapping("core/deleteWordbook.do")
	public void deleteWordbook(HttpServletRequest request,HttpServletResponse response){
		try{
			JSONObject jsonObject=getJSONData(request);
			wordbookService.deleteWordbook(jsonObject);
			returnSuccess(response);
		}catch (Exception e) {
			logger.error("role", e);
		}
	}
	
	@RequestMapping("core/deleteWordbookByIds.do")
	public void deleteWordbookByIds(HttpServletRequest request,HttpServletResponse response){
		try{
			JSONObject jsonObject=getJSONData(request);
			wordbookService.deleteWordbookByIds(jsonObject);
			returnSuccess(response);
		}catch (Exception e) {
			logger.error("role", e);
		}
	}
	
	@RequestMapping("core/loadWordbook.do")
	public void loadWordbook(HttpServletRequest request,HttpServletResponse response){
		try{
			JSONObject jsonObject=getJSONData(request);
			String result=wordbookService.loadWordbook(jsonObject);
			returnJson(response, result);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("role", e);
		}
	}
	
	@RequestMapping("core/loadWordbookPage.do")
	public void loadWordbookPage(HttpServletRequest request,HttpServletResponse response){
		try{
			JSONObject jsonObject=getJSONData(request);
//			jsonObject.put("pageNo", 1);
//			jsonObject.put("pageSize", 8);
			String result=wordbookService.loadWordbookPage(jsonObject);
			returnJson(response, result);
		}catch (Exception e) {
			logger.error("role", e);
		}
	}

	@Autowired
	public void setWordbookService(WordbookService wordbookService) {
		this.wordbookService = wordbookService;
	}

	
	
}
