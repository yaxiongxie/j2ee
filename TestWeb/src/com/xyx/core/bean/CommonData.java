package com.xyx.core.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonData {
	
	public static List<CorePerson> personList=new ArrayList<CorePerson>();
	public static List<CorePersonRole> personRoleList=new ArrayList<CorePersonRole>();
	public static List<CoreRoleAuth> roleAuthList=new ArrayList<CoreRoleAuth>();
	public static List<CoreAuth> authList=new ArrayList<CoreAuth>();
	public static Map<String, List<CoreAuth>> authMap=new HashMap<String, List<CoreAuth>>();
}
