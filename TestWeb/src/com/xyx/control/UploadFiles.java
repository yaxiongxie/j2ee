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
                System.out.println("�ļ�δ�ϴ�");  
            }else{  
                System.out.println("�ļ�����: " + myfile.getSize());  
                System.out.println("�ļ�����: " + myfile.getContentType());  
                System.out.println("�ļ�����: " + myfile.getName());  
                System.out.println("�ļ�ԭ��: " + myfile.getOriginalFilename());  

                String filePath = request.getSession().getServletContext().getRealPath("/") + "upload/"  
                        + myfile.getOriginalFilename();  
                // ת���ļ�  
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
                //����õ���Tomcat�����������ļ����ϴ���\\%TOMCAT_HOME%\\webapps\\YourWebProject\\WEB-INF\\upload\\�ļ�����  
//                String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");  
//                //���ﲻ�ش���IO���رյ����⣬��ΪFileUtils.copyInputStreamToFile()�����ڲ����Զ����õ���IO���ص������ǿ�����Դ���֪����  
//                FileUtils.copyInputStreamToFile(myfile.getInputStream(), new File(realPath, myfile.getOriginalFilename()));  
            }  
        }
		return "success";
	}

}
