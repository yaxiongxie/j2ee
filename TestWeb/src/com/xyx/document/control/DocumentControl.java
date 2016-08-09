package com.xyx.document.control;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import com.xyx.common.StringUtil;
import com.xyx.common.lucene.SolrjTool;
import com.xyx.common.processfile.FileTool;
import com.xyx.core.bean.CoreAttachment;
import com.xyx.core.service.CommonService;
import com.xyx.document.bean.Document;
import com.xyx.document.service.DocumentService;

@Controller("DocumentControl")
public class DocumentControl extends BaseControl{
	
	public DocumentService documentService;
	public CommonService commonService;
	
	DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	Logger logger=Logger.getLogger(DocumentControl.class);
	
	@RequestMapping("document/saveCategory.do")
	public void saveCategory(HttpServletRequest request,HttpServletResponse response){
		try{
			System.out.println(request.getParameterMap());
			JSONObject jsonObject=getJSONData(request);
			jsonObject.put("personid", getLoginPerson(request).getId());
			System.out.println("jsonObject===="+jsonObject);
			documentService.saveCategory(jsonObject);
			returnSuccess(response);
		}catch (Exception e) {
			logger.error("document", e);
		}
	}
	
	@RequestMapping("document/deleteCategory.do")
	public void deleteCategory(HttpServletRequest request,HttpServletResponse response){
		try{
			JSONObject jsonObject=getJSONData(request);
			documentService.deleteCategory(jsonObject);
			returnSuccess(response);
		}catch (Exception e) {
			logger.error("document", e);
		}
	}
	
	@RequestMapping("document/loadCategory.do")
	public void loadCategory(HttpServletRequest request,HttpServletResponse response){
		try{
			String result=documentService.loadCategoryAll(getLoginPerson(request).getId());
			returnJson(response, result);
		}catch (Exception e) {
			logger.error("document", e);
		}
	}
	
	@RequestMapping("document/addDocument.do")
	public void uploadFiles(HttpServletRequest request,HttpServletResponse response){
		String categoryString=request.getParameter("categoryid");
		int categoryid=Integer.parseInt(categoryString);
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
        if(multipartResolver.isMultipart(request)){  
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
            Iterator<String> iter = multiRequest.getFileNames();  
            while(iter.hasNext()){  
                MultipartFile file = multiRequest.getFile(iter.next());  
                if(file != null){ 
                	 byte[] filebytes=null;
 					try {
 						filebytes = file.getBytes();
 					} catch (IOException e) {
 						e.printStackTrace();
 					}
 					String filename=file.getOriginalFilename();
 					String content="";
 					try {
						content=FileTool.getFileContent(filename, filebytes);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
                	Document document=new Document();
                	document.setCategoryid(categoryid);
                	document.setCreatetime(dateFormat.format(new Date()));
                	String docContent=content.length()>100?content.substring(0,100):content;
                	docContent=StringUtil.replaceBlank(docContent);
                	document.setDoccontent(docContent);
                	document.setDocsize(filebytes.length);
                	document.setDoctitle(filename);
                	document.setDoctype(filename.substring(filename.lastIndexOf(".")+1));
                	document.setIsopen(1);
                	document.setPersonid(getLoginPerson(request).getId());
                	documentService.saveOrUpdate(document);
                	
                    CoreAttachment attachment=new CoreAttachment();
                    attachment.setCreatetime(dateFormat.format(new Date()));
                   
                    attachment.setFiledata(filebytes);
                    attachment.setFilename(file.getOriginalFilename());
                    attachment.setFilesize(filebytes.length);
                    attachment.setFiletype(file.getContentType());
                    attachment.setRelationid(document.getId());
                    attachment.setTablename("document");
                    commonService.saveOrUpdate(attachment);
                    
                    document.setDoccontent(content);
                    SolrjTool.AddDoc(document);
                }  
            }  
        }  
		returnSuccess(response);
	}
	
	
	@RequestMapping("document/loadDocumentPage.do")
	public void loadPersonPage(HttpServletRequest request,HttpServletResponse response){
		try{
			JSONObject jsonObject=getJSONData(request);
			jsonObject.put("personid", getLoginPerson(request).getId());
//			jsonObject.put("pageNo", 1);
//			jsonObject.put("pageSize", 8);
			String result=documentService.loadDocumentPage(jsonObject);
			returnJson(response, result);
		}catch (Exception e) {
			logger.error("role", e);
		}
	}
	
	@RequestMapping("document/deleteDocument.do")
	public void deleteDocument(HttpServletRequest request,HttpServletResponse response){
		try{
			JSONObject jsonObject=getJSONData(request);
			int id = jsonObject.getInt("id");
			SolrjTool.delDocsById("document"+id);
			documentService.deleteById(Document.class, id);
			List<CoreAttachment> attachments=commonService.getAttachments(id, "document");
			for(CoreAttachment attachment:attachments){
				commonService.delete(attachment);
			}
			returnSuccess(response);
		}catch (Exception e) {
			logger.error("role", e);
		}
	}
	
	@RequestMapping("document/downloadDocument.do")
	public void downloadDocument(HttpServletRequest request,HttpServletResponse response){
		try{
			JSONObject jsonObject=getJSONData(request);
			int id = jsonObject.getInt("id");
			List<CoreAttachment> attachments=commonService.getAttachments(id, "document");
			if(attachments.size()>0){
				downloadFile(response, attachments.get(0));
			}
		}catch (Exception e) {
			logger.error("role", e);
		}
	}
	
	@RequestMapping("document/deleteDocumentByIds.do")
	public void deleteDocumentByIds(HttpServletRequest request,HttpServletResponse response){
		try{
			JSONObject jsonObject=getJSONData(request);
			String ids=jsonObject.getString("ids");
			for(String id:ids.split(",")){
				if(id.equals("")){
					continue;
				}
				SolrjTool.delDocsById("document"+id);
				documentService.deleteById(Document.class, Integer.parseInt(id));
				List<CoreAttachment> attachments=commonService.getAttachments(Integer.parseInt(id), "document");
				for(CoreAttachment attachment:attachments){
					commonService.delete(attachment);
				}
			}
			returnSuccess(response);
		}catch (Exception e) {
			logger.error("role", e);
		}
	}

	@Autowired
	public void setDocumentService(DocumentService documentService) {
		this.documentService = documentService;
	}
	@Autowired
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}

	
	
}
