package com.xyx.processfile.office;

import java.io.File;
import java.io.InputStream;

import org.apache.poi.hwpf.extractor.WordExtractor;

public class WordUtil {

	public String getContent(String s) throws Exception {
		return getContent(new java.io.FileInputStream(s));
	}

	public String getContent(File f) throws Exception {
		return getContent(new java.io.FileInputStream(f));
	}

	public String getContent(InputStream is) throws Exception {
		String bodyText = null;
		WordExtractor ex = new WordExtractor(is);
		bodyText = ex.getText();
		return bodyText;
	}
	
	public static void main(String[] args) throws Exception {
		try{
			String resultString=new WordUtil().getContent("D:\\Documents\\Tencent Files\\1413122249\\FileRecv\\西藏自由行 .doc");
			System.out.println(resultString);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    }

}
