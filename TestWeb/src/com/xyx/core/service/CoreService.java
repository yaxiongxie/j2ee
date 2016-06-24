package com.xyx.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xyx.core.dao.CoreDao;

@Component
public class CoreService {
	
	@Autowired
	public CoreDao coreDao;
	
	public void addRole(){
		coreDao.addRole();
	}

}
