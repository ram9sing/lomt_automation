package lomt.pearson.api.spike_reingestion;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import lomt.pearson.constant.ExternalFrameworkTestData;
import lomt.pearson.constant.HEConstant;
import lomt.pearson.constant.LOMTConstant;
import lomt.pearson.constant.SchoolConstant;

public class AddNewRow {
	
	//public static String EXPORTED_FILE_PATH = ExternalFrameworkTestData.EXF_DESTINATION_FILE_PATH+ExternalFrameworkTestData.EXTERNAL_FRAMEWORK_TEMPLATE;
	

	public static void main(String[] args) throws Exception {
		FileInputStream isFile = null;
		FileOutputStream osFile = null;
		XSSFWorkbook workbook = null;
		XSSFSheet worksheet = null;
		Cell cell = null;
		
		FileInputStream isFile1 = null;
		FileOutputStream osFile1 = null;
		XSSFWorkbook workbook1 = null;
		XSSFSheet worksheet1 = null;
		Cell cell1 = null;
		
		//File file =  new File(EXPORTED_FILE_PATH);
		String exportedFileName = "Business21Nov2017.xlsx";
		File sourceFile = new File(LOMTConstant.EXPORTED_FILE_PATH + exportedFileName);
		
		isFile =  new FileInputStream(sourceFile);
		workbook = new XSSFWorkbook(isFile);
		worksheet = workbook.getSheetAt(0);
		
		//update existing row
		cell = worksheet.getRow(1).getCell(0);
		cell.setCellValue(SchoolConstant.INTERMEDIARY_STMT_UPDATE);
		
		cell = worksheet.getRow(1).getCell(1);
		cell.setCellValue(SchoolConstant.TAG_UPDATE);
		
		cell = worksheet.getRow(1).getCell(2);
		cell.setCellValue(SchoolConstant.CATEGORY_UPDATE);
		
		cell = worksheet.getRow(1).getCell(3);
		cell.setCellValue(SchoolConstant.IS_CODE_UPDATE);
		
		//addition of new row
		worksheet.shiftRows(2, worksheet.getLastRowNum(), 2, true, true);
		worksheet.createRow(2);
		
		cell = worksheet.getRow(2).createCell(0);
		cell.setCellValue(SchoolConstant.INTERMEDIARY_STMT_NEW_1);
		
		cell = worksheet.getRow(2).createCell(1);
		cell.setCellValue(SchoolConstant.TAG_NEW);
		
		cell = worksheet.getRow(2).createCell(2);
		cell.setCellValue(SchoolConstant.CATEGORY_NEW);
		
		cell = worksheet.getRow(2).createCell(3);
		cell.setCellValue(SchoolConstant.IS_CODE_NEW);
		
		cell = worksheet.createRow(3).createCell(0);
		cell.setCellValue(SchoolConstant.INTERMEDIARY_STMT_NEW_2);
		
		cell = worksheet.getRow(3).createCell(1);
		cell.setCellValue(SchoolConstant.TAG_NEW_2);
		
		cell = worksheet.getRow(3).createCell(2);
		cell.setCellValue(SchoolConstant.CATEGORY_NEW_2);
		
		cell = worksheet.getRow(3).createCell(3);
		cell.setCellValue(SchoolConstant.IS_CODE_NEW_2);
		
		isFile.close();
		
		osFile =new FileOutputStream(sourceFile);
		workbook.write(osFile); 
		osFile.close();
		
		
		//Shift of one row all the rows starting from the 3th row
		//worksheet.shiftRows(3, worksheet.getLastRowNum(), 1, true, true);
		//worksheet.createRow(3);
		
		/*cell = worksheet.getRow(38).createCell(5);
		cell.setCellValue(HEConstant.NEW_LO_NUM);*/
		
		/*worksheet.removeRow(worksheet.getRow(41));				
		
		isFile.close();
		
		osFile =new FileOutputStream(sourceFile);
		workbook.write(osFile); 
		osFile.close();*/
		
		//QA Pairs
		/*isFile1 =  new FileInputStream(sourceFile);
		workbook1 = new XSSFWorkbook(isFile1);
		worksheet1 = workbook1.getSheetAt(1);
		
		worksheet1.removeRow(worksheet1.getRow(33));				
		
		isFile1.close();
		
		osFile1 =new FileOutputStream(sourceFile);
		workbook1.write(osFile1); 
		osFile1.close();		
		System.out.println("Done");*/
	}

}
