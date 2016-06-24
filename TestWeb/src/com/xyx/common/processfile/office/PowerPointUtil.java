package com.xyx.common.processfile.office;

import java.io.File;
import java.io.InputStream;

import org.apache.poi.hslf.HSLFSlideShow;
import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.model.TextRun;
import org.apache.poi.hslf.usermodel.SlideShow;

public class PowerPointUtil {

	public String getContent(String s) throws Exception {
		return getContent(new java.io.FileInputStream(s));
	}

	public String getContent(File f) throws Exception {
		return getContent(new java.io.FileInputStream(f));
	}

	public String getContent(InputStream is) throws Exception {
		StringBuffer content = new StringBuffer("");
		SlideShow ss = new SlideShow(new HSLFSlideShow(is));
		Slide[] slides = ss.getSlides();
		for (int i = 0; i < slides.length; i++) {
			TextRun[] t = slides[i].getTextRuns();
			for (int j = 0; j < t.length; j++) {
				content.append(t[j].getText());
			}
			content.append(slides[i].getTitle());
		}
		return content.toString();
	}
	
	public static void main(String[] args) {
		try{
			String resultString=new PowerPointUtil().getContent("C:\\Users\\Administrator\\Desktop\\New Microsoft PowerPoint �õ�Ƭ.ppt");
			System.out.println(resultString);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
