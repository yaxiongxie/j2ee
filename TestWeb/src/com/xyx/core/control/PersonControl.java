package com.xyx.core.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xyx.common.BaseControl;
import com.xyx.core.service.PersonService;

@Controller("PersonControl")
public class PersonControl extends BaseControl{
	
	public PersonService personService;
	
	Logger logger=Logger.getLogger(PersonControl.class);
	
	@RequestMapping("core/savePerson.do")
	public void savePerson(HttpServletRequest request,HttpServletResponse response){
		try{
			System.out.println(request.getParameterMap());
			JSONObject jsonObject=getJSONData(request);
			String result=personService.savePerson(jsonObject);
			returnJson(response, result);
		}catch (Exception e) {
			logger.error("person", e);
		}
	}
	
	@RequestMapping("core/deletePerson.do")
	public void deletePerson(HttpServletRequest request,HttpServletResponse response){
		try{
			JSONObject jsonObject=getJSONData(request);
			personService.deletePerson(jsonObject);
			returnSuccess(response);
		}catch (Exception e) {
			logger.error("role", e);
		}
	}
	
	@RequestMapping("core/deletePersonByIds.do")
	public void deletePersonByIds(HttpServletRequest request,HttpServletResponse response){
		try{
			JSONObject jsonObject=getJSONData(request);
			personService.deletePersonByIds(jsonObject);
			returnSuccess(response);
		}catch (Exception e) {
			logger.error("role", e);
		}
	}
	
	@RequestMapping("core/loadPerson.do")
	public void loadPerson(HttpServletRequest request,HttpServletResponse response){
		try{
			JSONObject jsonObject=getJSONData(request);
			String result=personService.loadPerson(jsonObject);
			returnJson(response, result);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("role", e);
		}
	}
	
	@RequestMapping("core/loadPersonPage.do")
	public void loadPersonPage(HttpServletRequest request,HttpServletResponse response){
		try{
			JSONObject jsonObject=getJSONData(request);
//			jsonObject.put("pageNo", 1);
//			jsonObject.put("pageSize", 8);
			String result=personService.loadPersonPage(jsonObject);
			returnJson(response, result);
		}catch (Exception e) {
			logger.error("role", e);
		}
	}

	@Autowired
	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

}
