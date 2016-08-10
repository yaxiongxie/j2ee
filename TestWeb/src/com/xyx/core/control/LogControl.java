package com.xyx.core.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xyx.common.BaseControl;
import com.xyx.core.service.LogService;

@Controller("LogControl")
public class LogControl extends BaseControl{
	
	public LogService logService;
	
	Logger logger=Logger.getLogger(LogControl.class);
	
	@RequestMapping("core/deleteLog.do")
	public void deleteLog(HttpServletRequest request,HttpServletResponse response){
		try{
			JSONObject jsonObject=getJSONData(request);
			logService.deleteLog(jsonObject);
			returnSuccess(response);
		}catch (Exception e) {
			logger.error("role", e);
		}
	}
	
	@RequestMapping("core/loadLogPage.do")
	public void loadLogPage(HttpServletRequest request,HttpServletResponse response){
		try{
			JSONObject jsonObject=getJSONData(request);
			String result=logService.loadLogPage(jsonObject);
			returnJson(response, result);
		}catch (Exception e) {
			logger.error("role", e);
		}
	}

	@Autowired
	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	
	
}
