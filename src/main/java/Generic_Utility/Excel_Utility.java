package Generic_Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * Description: This method is used to fetch the string data from excel file
 * @author: Prashanth K
  * @parameters: sheet name, row number and cell number
  * @returns: String
 * @Date: 6th Feb 2025 
 **/
public class Excel_Utility {
	public String getDataFromExcel(String sheetName, int rowNum, int cellNum) throws Throwable {
		FileInputStream file = new FileInputStream("E:\\SeleniumAutomation\\VTiger_Automation\\TestData.xlsx");

		// step2:- keep excel in read mode
		Workbook book = WorkbookFactory.create(file);

		// step3:- navigate into excel sheet
		Sheet sheet = book.getSheet(sheetName);

		// step4:- navigate into row
		Row row = sheet.getRow(rowNum);
		sheet.getLastRowNum();
	
		// step5:-navigate into cell
		Cell cell = row.getCell(cellNum);
		String value = cell.getStringCellValue();
		return value;
	}
	
	public String getDataFromExcelUsingDataFormatter(String sheetName, int rowNum, int cellNum) throws Throwable {
		FileInputStream file = new FileInputStream("E:\\SeleniumAutomation\\VTiger_Automation\\TestData.xlsx");

		// step2:- keep excel in read mode
		Workbook book = WorkbookFactory.create(file);

		// step3:- navigate into excel sheet
		Sheet sheet = book.getSheet(sheetName);

		// step4:- navigate into row
		Row row = sheet.getRow(rowNum);

		// step5:-navigate into cell
		Cell cell = row.getCell(cellNum);
		
		//format the cell data using DataFormatter class
		DataFormatter df = new DataFormatter();
		String excelDataFromFormatter = df.formatCellValue(cell);
		return excelDataFromFormatter;
	}
	
	public HashMap<String, Integer> getLastRowAndLastColumn(String sheetName) throws Throwable {
		FileInputStream file = new FileInputStream("E:\\SeleniumAutomation\\VTiger_Automation\\TestData.xlsx");

		// step2:- keep excel in read mode
		Workbook book = WorkbookFactory.create(file);

		// step3:- navigate into excel sheet
		Sheet sheet = book.getSheet(sheetName);

		// get last used row
		int lastRowNumber = sheet.getLastRowNum();
		
		// get last used column
		int lastColNumber = sheet.getRow(0).getLastCellNum();
		
		System.out.println(lastRowNumber +" and " +lastColNumber);

		HashMap<String, Integer>lastRowColHashMap=new HashMap<String, Integer>();
		lastRowColHashMap.put("lastRowNum", lastRowNumber);
		lastRowColHashMap.put("lastColNum", lastColNumber);
		
		return lastRowColHashMap;
	}
	
	public Object[][] readExcelDataForDataProvider(String sheetName) throws Throwable, IOException {
		FileInputStream file = new FileInputStream("E:\\\\SeleniumAutomation\\\\VTiger_Automation\\\\TestData.xlsx");

		// step2:- keep excel in read mode
		Workbook book = WorkbookFactory.create(file);

		// step3:- navigate into excel sheet
		Sheet sheet = book.getSheet(sheetName);

		int lastRow = sheet.getLastRowNum()+1;
		short lastCell = sheet.getRow(0).getLastCellNum();

		Object[][] objArr = new Object[lastRow][lastCell];
		for (int i = 0; i < lastRow; i++) {
			for (int j = 0; j < lastCell; j++) {
				objArr[i][j] = sheet.getRow(i).getCell(j).toString();
			}
		}
		return objArr;

	}
}
