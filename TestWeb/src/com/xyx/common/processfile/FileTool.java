package com.xyx.common.processfile;

import java.io.InputStream;

import com.xyx.common.io.ByteToInputStream;
import com.xyx.common.processfile.office.ExcelUtil;
import com.xyx.common.processfile.office.PowerPointUtil;
import com.xyx.common.processfile.office.WordUtil;
import com.xyx.common.processfile.pdf.PdfUtil;
import com.xyx.common.processfile.txt.TxtUtil;
import com.xyx.common.processfile.xml.HttpUtil;

public class FileTool {
	
	public static final String OFFICE_EXCEL_2003_POSTFIX = "xls";
	public static final String OFFICE_EXCEL_2010_POSTFIX = "xlsx";
	public static final String OFFICE_DOC_2003_POSTFIX = "doc";
	public static final String OFFICE_DOC_2010_POSTFIX = "docx";
	public static final String OFFICE_PPT_POSTFIX = "ppt";
	public static final String OFFICE_PDF_POSTFIX = "pdf";
	public static final String OFFICE_XML_POSTFIX = "xml";
	public static final String OFFICE_TXT_POSTFIX = "txt";
	
	public static String getPostfix(String path) {
		if (path.contains(".")) {
			return path.substring(path.lastIndexOf(".") + 1, path.length());
		}
		return "";
	}
	
	public static String getFileContent(String filename,byte[] bytes) throws Exception{
		InputStream is=ByteToInputStream.byte2Input(bytes);
		String postfix=getPostfix(filename);
		if(postfix.contains(OFFICE_EXCEL_2003_POSTFIX) || postfix.contains(OFFICE_EXCEL_2010_POSTFIX)){
			return new ExcelUtil().readExcel(filename, is).toString();
		}else if(postfix.contains(OFFICE_DOC_2010_POSTFIX)){
			return new WordUtil().getContent2007(is);
		}else if(postfix.contains(OFFICE_DOC_2003_POSTFIX)){
			return new WordUtil().getContent2003(is);
		}else if(postfix.contains(OFFICE_PPT_POSTFIX)){
			return new PowerPointUtil().getContent(is);
		}else if(postfix.contains(OFFICE_PDF_POSTFIX)){
			return new PdfUtil().readFileContent(is);
		}else if(postfix.contains(OFFICE_XML_POSTFIX)){
			return new HttpUtil().getXmlContent(is);
		}else if(postfix.contains(OFFICE_TXT_POSTFIX)){
			return new TxtUtil().getContent(is);
		}else{
			return "";
		}
	}
	

}
