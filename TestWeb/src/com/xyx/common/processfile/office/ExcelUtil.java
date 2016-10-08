package com.xyx.common.processfile.office;

/**
 * 
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;

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
	
	public List<List<String>> readExcel(String filename,InputStream is) throws IOException {

		String postfix = getPostfix(filename);
		if (OFFICE_EXCEL_2003_POSTFIX.equals(postfix)) {
			return readXls(is);
		} else if (OFFICE_EXCEL_2010_POSTFIX.equals(postfix)) {
			return readXlsx(is);
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
	 * Read the Excel 2010
	 * 
	 * @param path
	 *            the path of the excel file
	 * @return
	 * @throws IOException
	 */
	public List<List<String>> readXlsx(InputStream is) throws IOException {
		System.out.println("read excel 2010");
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
	
	public List<List<String>> readXls(InputStream is) throws IOException {
		System.out.println("read excel 2003-2007");
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
	
	public void writeXls(Map<String, String> map, String path) throws Exception {
        if (map == null) {
            return;
        }
        int countColumnNum = map.size();
        HSSFWorkbook book = new HSSFWorkbook();
        HSSFSheet sheet = book.createSheet("Sheet");
        // option at first row.
        HSSFRow firstRow = sheet.createRow(0);
        HSSFCell[] firstCells = new HSSFCell[countColumnNum];
        String[] options = { "设备数", "人数"};
        for (int j = 0; j < options.length; j++) {
            firstCells[j] = firstRow.createCell(j);
            firstCells[j].setCellValue(options[j]);
        }
        //
        Set<String> keySet=map.keySet();
        List<String> keyList=new ArrayList<String>();
        for(String string:keySet){
        	keyList.add(string);
        }
        for (int i = 0; i < countColumnNum; i++) {
            HSSFRow row = sheet.createRow(i + 1);
                HSSFCell no = row.createCell(0);
                HSSFCell name = row.createCell(1);
                no.setCellValue(keyList.get(i));
                name.setCellValue(map.get(keyList.get(i)));
        }
        File file = new File(path);
        OutputStream os = new FileOutputStream(file);
        System.out.println(path);
        book.write(os);
        os.close();
    }
    
    public void writeXlsx(Map<Integer, String> map, String path) throws Exception {
        if (map == null) {
            return;
        }
        //XSSFWorkbook
        int countColumnNum = map.size();
        XSSFWorkbook book = new XSSFWorkbook();
        XSSFSheet sheet = book.createSheet("Sheet");
        // option at first row.
        XSSFRow firstRow = sheet.createRow(0);
        XSSFCell[] firstCells = new XSSFCell[countColumnNum];
        String[] options = { "设备数", "人数"};
        for (int j = 0; j < options.length; j++) {
            firstCells[j] = firstRow.createCell(j);
            firstCells[j].setCellValue(new XSSFRichTextString(options[j]));
        }
        //
        Set<Integer> keySet=map.keySet();
        List<String> keyList=new ArrayList<String>();
        for(Integer string:keySet){
        	keyList.add(string+"");
        }
        for (int i = 0; i < countColumnNum; i++) {
            XSSFRow row = sheet.createRow(i + 1);
                XSSFCell no = row.createCell(0);
                XSSFCell name = row.createCell(1);
                no.setCellValue(keyList.get(i));
                name.setCellValue(map.get(Integer.parseInt(keyList.get(i))));
        }
        File file = new File(path);
        OutputStream os = new FileOutputStream(file);
        System.out.println(path);
        book.write(os);
        os.close();
    }
	
	public static void main(String[] args) {
		try{
//			List<List<String>> list=new ExcelUtil().readExcel("D:\\statistic.xlsx");
//			for(List<String> l:list){
//				System.out.println(l);
//			}
			int count=0;
			String string=new String("{0:187652,1:112868,2:26679,3:6612,4:3290,415:1,5:1531,6:803,7:511,8:366,9:220,10:155,11:81,12:77,286:1,13:90,14:33,15:27,17:46,16:20,19:14,18:15,21:11,20:12,23:17,22:7,25:2,24:1,27:4,26:1,29:3,28:3,31:4,30:5,34:2,35:1,32:2,33:1,38:1,39:1,36:16,42:1,43:1,41:3,46:1,44:1,51:1,54:1,52:2,59:1,57:2,56:1,60:1,70:4,64:1,72:1,209:1,103:1}");
			JSONObject jsonObject=new JSONObject(string);
			System.out.println(jsonObject);
			Map<Integer, String> map=new TreeMap<Integer, String>();
			Iterator<String> iterable=jsonObject.keys();
			while(iterable.hasNext()){
				String keyString=iterable.next();
				map.put(Integer.parseInt(keyString), jsonObject.getString(keyString));
				count=count+(Integer.parseInt(keyString)*Integer.parseInt(jsonObject.getString(keyString)));
			}
			System.out.println(map);
			System.out.println(count);
			new ExcelUtil().writeXlsx(map, "D:\\statistic.xlsx");
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
}