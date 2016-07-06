package com.xyx.core.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xyx.common.BaseControl;
import com.xyx.core.service.CommonService;

@Controller
public class CommonControl extends BaseControl{
	@Autowired
	public CommonService commonService;
	
	Logger logger=Logger.getLogger(CommonControl.class);
	
	
	@RequestMapping("core/getDepartmentAndPersonTree.do")
	public void getDepartmentAndPersonTree(HttpServletRequest request,HttpServletResponse response){
		try{
			String result=commonService.getDepartmentAndPersonTree();
			returnJson(response, result);
		}catch (Exception e) {
			logger.error("role", e);
		}
	}

}
