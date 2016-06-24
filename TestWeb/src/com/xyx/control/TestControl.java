package com.xyx.control;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xyx.dao.impl.TestDaoImpl;



@Controller
public class TestControl {
	
	Logger logger=Logger.getLogger(TestControl.class);
	
	@Autowired
	private TestDaoImpl testDao;
	
	
	@RequestMapping("xx.do")
	@ResponseBody
	public String xx(String xx){
		testDao.test();
		return "success";
	}

}
