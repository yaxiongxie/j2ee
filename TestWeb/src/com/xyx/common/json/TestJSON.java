package com.xyx.common.json;

import com.xyx.core.bean.CorePerson;

import net.sf.json.JSONObject;


public class TestJSON {
	
	public static void main(String[] args) {
//		List<Test> list=new ArrayList<Test>();
//		for(int i=0;i<5;i++){
//			Test test=new Test();
//			test.setId(i);
//			test.setName("xie"+i);
//			list.add(test);
//		}
//		JSONObject object=JSONObject.fromObject(list.get(2));
//		System.out.println(object.toString());
//		Test test=(Test)JSONObject.toBean(object,Test.class);
//		System.out.println(test.getId());
//		JSONObject jsonObject=new JSONObject();
//		String ssString=(String)jsonObject.get("xx");
//		System.out.println(ssString);
		JSONObject jsonObject=JSONObject.fromObject(new CorePerson());
		System.out.println(jsonObject);
	}

}
