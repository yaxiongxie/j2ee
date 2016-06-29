package com.xyx.common;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

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
			return jsonObject;
		}catch (Exception e) {
			log.error("json", e);
		}
		return null;
	}
	
	public void returnJson(HttpServletResponse response,String body){
		response.setContentType("application/json; charset=UTF-8");
		try {
			response.getWriter().print(body);
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

}
