package com.xyx.control;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class TestControl {
	
	Logger logger=Logger.getLogger(TestControl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	@RequestMapping("xx.do")
	public void xx(String xx){
		System.out.println("save");
		jdbcTemplate.update("insert into test(name) values('xx') ");
		try{
			throw new NullPointerException();
		}catch (Exception e) {
			// TODO: handle exception
			logger.error(e);
		}
		//sd/
	}
	
	@RequestMapping("yy.do")
	public void yy(String yyString){
		
	}
	

}
