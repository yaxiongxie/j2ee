package com.xyx.common;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.xyx.core.bean.CoreAttachment;
import com.xyx.core.bean.CorePerson;

public class BaseControl {
	
	private static Logger log = Logger.getLogger(BaseControl.class);  
	
	public JSONObject getJSONData(HttpServletRequest request){
		Map<String, String[]>jsonData =request.getParameterMap();
		JSONObject jsonObject=new JSONObject();
		Set<String> keySet=jsonData.keySet();
		try {
			for(String string:keySet){
				jsonObject.put(string, jsonData.get(string)[0]);
			}
			log.debug("request json==="+jsonObject.toString());
			return jsonObject;
		}catch (Exception e) {
			log.error("json", e);
		}
		return null;
	}
	
	public CorePerson getLoginPerson(HttpServletRequest request){
		Object object=request.getSession().getAttribute("user");
		return (CorePerson)object;
	}
	
	public void returnJson(HttpServletResponse response,String body){
		response.setContentType("application/json; charset=UTF-8");
		try {
			response.getWriter().print(body);
			System.out.println("response json===="+body);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void returnSuccess(HttpServletResponse response){
		response.setContentType("application/json; charset=UTF-8");
		try {
			JSONObject jObject=new JSONObject();
			jObject.put("status", "success");
			response.getWriter().print(jObject.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void returnFail(HttpServletResponse response){
		response.setContentType("application/json; charset=UTF-8");
		try {
			JSONObject jObject=new JSONObject();
			jObject.put("status", "fail");
			response.getWriter().print(jObject.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void downloadFile(HttpServletResponse response,CoreAttachment coreAttachment) throws UnsupportedEncodingException{
		if(coreAttachment==null){
			return ;
		}
		response.setContentType("multipart/form-data"); 
		response.setContentLength(coreAttachment.getFilesize());
		response.setHeader("Content-Disposition", "attachment;fileName="+URLEncoder.encode(coreAttachment.getFilename(), "UTF-8"));  
		try {
			response.getOutputStream().write(coreAttachment.getFiledata());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
