package genericLibrary;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelLibrary {
	Workbook wb;

	public void excelInitialization() {

		FileInputStream fis = null;

		try {
			fis = new FileInputStream(IconstantPath.EXCEL_FILE_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			wb = WorkbookFactory.create(fis);
		} catch (EncryptedDocumentException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public String fetchSingleDataFromExcelSheet(String sheetName, int rowNum, int cellNum) {
		DataFormatter d = new DataFormatter();
		Sheet sh = wb.getSheet(sheetName);
		String value = d.formatCellValue(sh.getRow(rowNum).getCell(cellNum));
		return value;
	}
	
	public Map<String, String> fetchMultipleDataFromExcelSheet(String sheetName) {
		
		Map<String,String> map = new HashMap<>();
		Sheet sh = wb.getSheet(sheetName);
		int row = sh.getLastRowNum();
	    for(int i=0;i<=row;i++) {
	    	
	    	String key = sh.getRow(i).getCell(0).getStringCellValue();
	    	String value = sh.getRow(i).getCell(1).getStringCellValue();
	    	map.put(key, value);
	    }
		return map;
	}
	
	public void closeExcel() {
		try {
			wb.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}
