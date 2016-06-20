package com.xyx.util;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import com.xyx.bean.Test;

public class TestJSON {
	
	public static void main(String[] args) {
		List<Test> list=new ArrayList<Test>();
		for(int i=0;i<5;i++){
			Test test=new Test();
			test.setId(i);
			test.setName("xie"+i);
			list.add(test);
		}
		JSONObject object=JSONObject.fromObject(list.get(2));
		System.out.println(object.toString());
		Test test=(Test)JSONObject.toBean(object,Test.class);
		System.out.println(test.getId());
	}

}
