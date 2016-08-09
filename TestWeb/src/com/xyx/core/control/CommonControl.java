package com.xyx.core.control;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.xyx.common.BaseControl;
import com.xyx.common.io.ByteToInputStream;
import com.xyx.core.bean.CommonData;
import com.xyx.core.bean.CoreAttachment;
import com.xyx.core.bean.CoreAuth;
import com.xyx.core.bean.CorePerson;
import com.xyx.core.bean.CorePersonRole;
import com.xyx.core.bean.CoreRoleAuth;
import com.xyx.core.service.CommonService;
import com.xyx.core.service.PersonService;
import com.xyx.core.service.RoleService;

@Controller("CommonControl")
public class CommonControl extends BaseControl{
	
	public CommonService commonService;
	public PersonService personService;
	public RoleService roleService;
	
	
	Logger logger=Logger.getLogger(CommonControl.class);
	DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	
	@RequestMapping("core/uploadFiles.do")
	public void uploadFiles(HttpServletRequest request,HttpServletResponse response){
		JSONObject jsonObject=getJSONData(request).getJSONObject("jsonData");
		System.out.println(jsonObject.toString());
		int relateId=jsonObject.getInt("relateId");
		String tableName=jsonObject.getString("tableName");
		List<CoreAttachment> attachments=commonService.getAttachments(relateId,tableName);
		for(CoreAttachment attachment:attachments){
			commonService.delete(attachment);
		}
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
        if(multipartResolver.isMultipart(request)){  
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
            Iterator<String> iter = multiRequest.getFileNames();  
            while(iter.hasNext()){  
                MultipartFile file = multiRequest.getFile(iter.next());  
                if(file != null){  
                    CoreAttachment attachment=new CoreAttachment();
                    attachment.setCreatetime(dateFormat.format(new Date()));
                    byte[] filebytes=null;
					try {
						filebytes = file.getBytes();
					} catch (IOException e) {
						e.printStackTrace();
					}
                    attachment.setFiledata(filebytes);
                    attachment.setFilename(file.getOriginalFilename());
                    attachment.setFilesize(filebytes.length);
                    attachment.setFiletype(file.getContentType());
                    attachment.setRelationid(relateId);
                    attachment.setTablename(tableName);
                    commonService.saveOrUpdate(attachment);
                
//                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在  
//                    if(myFileName.trim() !=""){  
//                    	String filePath = request.getSession().getServletContext().getRealPath("/") + "upload/"  
//                                + myFileName; 
//                        System.out.println(myFileName);  
//                        System.out.println(filePath);
//                        File localFile = new File(filePath);  
//                        try {
//							file.transferTo(localFile);
//						} catch (Exception e) {
//							
//						}
//                    }  
                }  
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
	
	@RequestMapping("core/exit.do")
	public void exit(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().removeAttribute("isLogin");
		request.getSession().removeAttribute("user");
	}
	
	@RequestMapping("core/downloadAttachment.do")
	public void downloadAttachment(HttpServletRequest request,HttpServletResponse response){
		try{
			String id=request.getParameter("id");
			CoreAttachment coreAttachment=commonService.get(CoreAttachment.class, Integer.parseInt(id));
			downloadFile(response, coreAttachment);
		}catch (Exception e) {
			logger.error("core", e);
		}
	}
	
	@RequestMapping("core/loadImage.do")
	public void loadImage(HttpServletRequest request,HttpServletResponse response){
		try{
			CorePerson corePerson=getLoginPerson(request);
			List<CoreAttachment> attachments=commonService.getAttachments(corePerson.getId(), "coreperson");
			System.out.println("image==="+attachments.size());
			if(attachments!=null && attachments.size()>0){
				response.setContentType(attachments.get(0).getFiletype()); 
				response.setContentLength(attachments.get(0).getFilesize());
				response.setHeader("Content-Disposition", "attachment;fileName="+URLEncoder.encode(attachments.get(0).getFilename(), "UTF-8"));  
				try {
					response.getOutputStream().write(attachments.get(0).getFiledata());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else{
				FileInputStream fileInputStream=new FileInputStream(request.getRealPath("/img/test.jpg"));
				try {
					response.getOutputStream().write(ByteToInputStream.input2byte(fileInputStream));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}catch (Exception e) {
			logger.error("core", e);
		}
	}
	
	@SuppressWarnings("unchecked")
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


	@Autowired
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}
	@Autowired
	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}
	@Autowired
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

}
