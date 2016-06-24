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
	public void saveRole(HttpServletRequest request,HttpServletResponse response){
		JSONObject jsonObject=getJSONData(request);
		try {
			jsonObject.put("name", "xieyaxiong");
			jsonObject.put("id", 0);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		coreService.saveRole(jsonObject);
		
		returnSuccess(response);
	}

}
