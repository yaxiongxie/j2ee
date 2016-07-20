package com.xyx.common.processfile.office;

/**
 * 
 */

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author xyx
 */
public class ExcelUtil {

	public static final String OFFICE_EXCEL_2003_POSTFIX = "xls";
	public static final String OFFICE_EXCEL_2010_POSTFIX = "xlsx";

	public static String getPostfix(String path) {
		if (path.contains(".")) {
			return path.substring(path.lastIndexOf(".") + 1, path.length());
		}
		return "";
	}

	/**
	 * read the Excel file
	 * 
	 * @param path
	 *            the path of the Excel file
	 * @return
	 * @throws IOException
	 */
	public List<List<String>> readExcel(String path) throws IOException {

		String postfix = getPostfix(path);
		if (OFFICE_EXCEL_2003_POSTFIX.equals(postfix)) {
			return readXls(path);
		} else if (OFFICE_EXCEL_2010_POSTFIX.equals(postfix)) {
			return readXlsx(path);
		}
		return null;

	}

	/**
	 * Read the Excel 2010
	 * 
	 * @param path
	 *            the path of the excel file
	 * @return
	 * @throws IOException
	 */
	public List<List<String>> readXlsx(String path) throws IOException {
		System.out.println("read excel 2010");
		InputStream is = new FileInputStream(path);
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
		List<List<String>> list = new ArrayList<List<String>>();
		// Read the Sheet
		for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
			XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
			if (xssfSheet == null) {
				continue;
			}
			// Read the Row
			for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
				XSSFRow xssfRow = xssfSheet.getRow(rowNum);
				if (xssfRow != null) {
					List<String> rowList = new ArrayList<String>();
					for(int cellNum=0;cellNum<xssfRow.getLastCellNum();cellNum++){
						rowList.add(getValue(xssfRow.getCell(cellNum)));
					}
					
					list.add(rowList);
				}
			}
		}
		return list;
	}

	/**
	 * Read the Excel 2003-2007
	 * 
	 * @param path
	 *            the path of the Excel
	 * @return
	 * @throws IOException
	 */
	public List<List<String>> readXls(String path) throws IOException {
		System.out.println("read excel 2003-2007");
		InputStream is = new FileInputStream(path);
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
		List<List<String>> list = new ArrayList<List<String>>();
		// Read the Sheet
		for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
			if (hssfSheet == null) {
				continue;
			}
			// Read the Row
			for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
				HSSFRow hssfRow = hssfSheet.getRow(rowNum);
				if (hssfRow != null) {
					List<String> rowList=new ArrayList<String>();
					for(int cellNum=0;cellNum<hssfRow.getLastCellNum();cellNum++){
						rowList.add(getValue(hssfRow.getCell(cellNum)));
					}
					
					list.add(rowList);
				}
			}
		}
		return list;
	}

	@SuppressWarnings("static-access")
	private String getValue(XSSFCell xssfRow) {
		if(xssfRow==null){
			return "";
		}
		if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
			return String.valueOf(xssfRow.getBooleanCellValue());
		} else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
			return String.valueOf(xssfRow.getNumericCellValue());
		} else {
			return String.valueOf(xssfRow.getStringCellValue());
		}
	}

	@SuppressWarnings("static-access")
	private String getValue(HSSFCell hssfCell) {
		if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(hssfCell.getBooleanCellValue());
		} else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
			return String.valueOf(hssfCell.getNumericCellValue());
		} else {
			return String.valueOf(hssfCell.getStringCellValue());
		}
	}
	
	public static void main(String[] args) {
		try{
			List<List<String>> list=new ExcelUtil().readExcel("C:\\Users\\Administrator\\Desktop\\新建 Microsoft Excel 工作表.xlsx");
			for(List<String> l:list){
				System.out.println(l);
			}
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
}