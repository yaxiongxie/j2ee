package com.xyx.common.test;

import java.io.File;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.xyx.common.BaseControl;



@Controller
public class UploadFiles extends BaseControl{
	
	Logger logger=Logger.getLogger(UploadFiles.class);
	
	//947752974
	@ResponseBody
	@RequestMapping(value="updateFiles.do", method=RequestMethod.POST)
	public String updateFiles(HttpServletRequest request){
		try{
			Thread.sleep(1000);
		}catch (Exception e) {
			// TODO: handle exception
		}
//		JSONObject jsonObject=getJSONData(request);
//		System.out.println(jsonObject.toString());
		//创建一个通用的多部分解析器  
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
        //判断 request 是否有文件上传,即多部分请求  
        if(multipartResolver.isMultipart(request)){  
            //转换成多部分request    
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
            //取得request中的所有文件名  
            Iterator<String> iter = multiRequest.getFileNames();  
            while(iter.hasNext()){  
                //记录上传过程起始时的时间，用来计算上传时间  
                int pre = (int) System.currentTimeMillis();  
                //取得上传文件  
                MultipartFile file = multiRequest.getFile(iter.next());  
                if(file != null){  
                    //取得当前上传文件的文件名称  
                    String myFileName = file.getOriginalFilename();  
                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在  
                    if(myFileName.trim() !=""){  
                    	String filePath = request.getSession().getServletContext().getRealPath("/") + "upload/"  
                                + myFileName; 
                        System.out.println(myFileName);  
                        File localFile = new File(filePath);  
                        try {
							file.transferTo(localFile);
						} catch (Exception e) {
                    }  
                }  
                //记录上传该文件后的时间  
                int finaltime = (int) System.currentTimeMillis();  
                System.out.println(finaltime - pre);  
            }  
            }
              
        }  
        return "";
//        try {
//			jsonObject.put("status", "success");
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return jsonObject.toString();
	}

}
