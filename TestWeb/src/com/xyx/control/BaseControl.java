package com.xyx.control;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

public class BaseControl {
	
	private static Logger log = Logger.getLogger(BaseControl.class);  
	
	public JSONObject getJSONData(HttpServletRequest request){
		String jsonData=request.getParameter("jsonData");
		try {
			return new JSONObject(jsonData);
		} catch (JSONException e) {
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

}
