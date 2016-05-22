package com.xyx.processfile.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

public class PdfUtil {

	public String readFileContent(String fileName) {
		try {
			PDDocument doc = PDDocument.load(fileName);
			PDFTextStripper stripper = new PDFTextStripper();
			return stripper.getText(doc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static void main(String[] args) {
		String resultString=new PdfUtil().readFileContent("D:\\Downloads\\The C programming Language Second Edition.pdf");
		System.out.println(resultString);
	}

}
