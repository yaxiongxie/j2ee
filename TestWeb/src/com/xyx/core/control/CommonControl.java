package com.xyx.core.control;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.xyx.common.BaseControl;
import com.xyx.core.bean.CommonData;
import com.xyx.core.bean.CoreAuth;
import com.xyx.core.bean.CorePerson;
import com.xyx.core.bean.CorePersonRole;
import com.xyx.core.bean.CoreRoleAuth;
import com.xyx.core.service.CommonService;
import com.xyx.core.service.PersonService;
import com.xyx.core.service.RoleService;

@Controller
public class CommonControl extends BaseControl{
	@Autowired
	public CommonService commonService;
	@Autowired
	public PersonService personService;
	@Autowired
	public RoleService roleService;
	
	Logger logger=Logger.getLogger(CommonControl.class);
	
	
	@RequestMapping("core/uploadFiles.do")
	public void uploadFiles(HttpServletRequest request,HttpServletResponse response,@RequestParam MultipartFile[] Filedata){
		for(MultipartFile myfile : Filedata){  
            if(myfile.isEmpty()){  
                System.out.println("文件未上传");  
            }else{  
                System.out.println("文件长度: " + myfile.getSize());  
                System.out.println("文件类型: " + myfile.getContentType());  
                System.out.println("文件名称: " + myfile.getName());  
                System.out.println("文件原名: " + myfile.getOriginalFilename());  

                String filePath = request.getSession().getServletContext().getRealPath("/") + "upload/"  
                        + myfile.getOriginalFilename();  
                // 转存文件  
                try {
					myfile.transferTo(new File(filePath));
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                System.out.println("========================================");  
                //如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\WEB-INF\\upload\\文件夹中  
//                String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");  
//                //这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的  
//                FileUtils.copyInputStreamToFile(myfile.getInputStream(), new File(realPath, myfile.getOriginalFilename()));  
            }  
        }
		returnSuccess(response);
	}
	
	
	
	@RequestMapping("core/getDepartmentAndPersonTree.do")
	public void getDepartmentAndPersonTree(HttpServletRequest request,HttpServletResponse response){
		try{
			String result=commonService.getDepartmentAndPersonTree();
			returnJson(response, result);
		}catch (Exception e) {
			logger.error("role", e);
		}
	}
	
	@RequestMapping("core/login.do")
	public String login(HttpServletRequest request,HttpServletResponse response){
		try{
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			Object object=commonService.login(username, password);
			if(object!=null){
				request.getSession().setAttribute("isLogin", true);
				request.getSession().setAttribute("user", object);
				return "redirect:/index.html";
			}
		}catch (Exception e) {
			logger.error("core", e);
		}
		return "redirect:/login.html?msg=loginfail";
		
	}
	
	@RequestMapping("core/initSystem.do")
	public void initSystem(HttpServletRequest request,HttpServletResponse response){
		CommonData.personList.clear();
		CommonData.personRoleList.clear();
		CommonData.roleAuthList.clear();
		CommonData.authList.clear();
		CommonData.authMap.clear();
    	
    	CommonData.personList=personService.loadPersonAll();
    	CommonData.personRoleList=roleService.getPersonRoleAll();
    	CommonData.roleAuthList=roleService.getRoleAuthAll();
    	CommonData.authList=roleService.getAuthAll();
    	for(CorePerson corePerson:CommonData.personList){
    		String username=corePerson.getUsername();
    		List<CoreAuth> auths=new ArrayList<CoreAuth>();
    		for(CorePersonRole personRole:CommonData.personRoleList){
    			if(personRole.getPersonId()==corePerson.getId()){
    				for(CoreRoleAuth roleAuth:CommonData.roleAuthList){
    					if(roleAuth.getRoleId()==personRole.getRoleId()){
    						for(CoreAuth auth:CommonData.authList){
    							if(auth.getId()==roleAuth.getAuthId()){
    								if(!auths.contains(auth)){
    									auths.add(auth);
    								}
    							}
    						}
    					}
    				}
    			}
    		}
    		CommonData.authMap.put(username, auths);
    	}
    	System.out.println(CommonData.authMap);
    	returnSuccess(response);
	}

}
