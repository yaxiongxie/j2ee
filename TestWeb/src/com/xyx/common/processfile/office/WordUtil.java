package com.xyx.common.processfile.office;

import java.io.File;
import java.io.InputStream;

import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class WordUtil {

	public String getContent(String s) throws Exception {
		if(s.endsWith(".doc")){
			return getContent2003(new java.io.FileInputStream(s));
		}else{
			return getContent2007(new java.io.FileInputStream(s));
		}
	}

	public String getContent(File f) throws Exception {
		if(f.getName().endsWith(".doc")){
			return getContent2003(new java.io.FileInputStream(f));
		}else{
			return getContent2007(new java.io.FileInputStream(f));
		}
	}

	public String getContent2003(InputStream is) throws Exception {
		String bodyText = null;
		WordExtractor ex = new WordExtractor(is);
		bodyText = ex.getText();
		return bodyText;
	}
	
	public String getContent2007(InputStream is) throws Exception {
		XWPFDocument doc = new XWPFDocument(is);  
	      XWPFWordExtractor extractor = new XWPFWordExtractor(doc);  
	      return extractor.getText();     
	  
	}
	
	
	
	public static void main(String[] args) throws Exception {
		try{
			String resultString=new WordUtil().getContent(new File("C:\\Users\\Administrator\\Desktop\\新建 Microsoft Word 文档.docx"));
			System.out.println(resultString);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    }

}
