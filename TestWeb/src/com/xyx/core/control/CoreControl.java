package com.xyx.core.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xyx.control.BaseControl;
import com.xyx.control.TestControl;
import com.xyx.core.service.CoreService;

@Controller
public class CoreControl extends BaseControl{
	@Autowired
	public CoreService coreService;
	
	Logger logger=Logger.getLogger(TestControl.class);
	
	@RequestMapping("core/addrole.do")
	public void addRole(HttpServletRequest request,HttpServletResponse response){
		coreService.addRole();
		JSONObject jObject=new JSONObject();
		try {
			jObject.put("status", "success");
		} catch (JSONException e) {
			logger.error("error", e);
		}
		returnJson(response, jObject.toString());
	}

}
