package com.xyx.control;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;



@Controller
public class UploadFiles {
	
	@Autowired  
    private HttpServletRequest request; 
	
	Logger logger=Logger.getLogger(UploadFiles.class);
	
	//947752974
	@ResponseBody
	@RequestMapping(value="updateFiles.do", method=RequestMethod.POST)
	public String updateFiles(@RequestParam MultipartFile[] Filedata){
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
		return "success";
	}

}
