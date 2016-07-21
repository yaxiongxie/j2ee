package com.xyx.common.processfile.xml;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.jsoup.Jsoup;
import org.jsoup.examples.HtmlToPlainText;
import org.jsoup.nodes.Document;

public class HttpUtil {
	
	public String getXmlContent(String filename,String encodeString) {
		FileInputStream fileInputStream = null;
		BufferedReader bufferedReader = null;

		try {
			fileInputStream=new FileInputStream(filename);
			bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream, encodeString));
			StringBuffer stringBuffer = new StringBuffer();
			String tempString = null;
			while ((tempString = bufferedReader.readLine()) != null) {
				stringBuffer.append(tempString+"\r\n");
			}
			return stringBuffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bufferedReader.close();
				fileInputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "";
	}
	
	public String getXmlContent(InputStream is) {
		BufferedReader bufferedReader = null;

		try {
			bufferedReader = new BufferedReader(new InputStreamReader(is, "GBK"));
			StringBuffer stringBuffer = new StringBuffer();
			String tempString = null;
			while ((tempString = bufferedReader.readLine()) != null) {
				stringBuffer.append(tempString+"\r\n");
			}
			return stringBuffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bufferedReader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "";
	}
	
	public String getTxtContent(String filename){
		String xmlContent=getXmlContent(filename, "utf-8");
		Document document=Jsoup.parse(xmlContent);
		String result=new HtmlToPlainText().getPlainText(document.body());
		return result;
	}
	
	public static void main(String[] args) {
		String resultString=new HttpUtil().getTxtContent("C:\\Users\\Administrator\\Desktop\\main.fxml");
		System.out.println(resultString);
	}
	

}
