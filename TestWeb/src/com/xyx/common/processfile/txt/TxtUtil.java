package com.xyx.common.processfile.txt;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TxtUtil {

	public String getContent(String filename,String encodeString) {
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
	
	public String getContent(InputStream is) {
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
	
	public static void main(String[] args) {
		String string=new TxtUtil().getContent("C:\\Users\\Administrator\\Desktop\\新建文本文档.txt","GBK");
		System.out.println(string);
	}

}
