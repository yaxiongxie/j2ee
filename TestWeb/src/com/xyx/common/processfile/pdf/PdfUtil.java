package com.xyx.common.processfile.pdf;

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
		String resultString=new PdfUtil().readFileContent("E:\\ebook\\apache-solr-ref-guide-6.0.pdf");
		System.out.println(resultString);
	}

}
