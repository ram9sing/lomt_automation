package lomt.pearson.api.externalframework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Random;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import lomt.pearson.api.product_toc.ReadProductTOCFile;
import lomt.pearson.constant.EnglishTestData;
import lomt.pearson.constant.ExternalFrameworkTestData;
import lomt.pearson.constant.LOMTConstant;

public class TestPTOC {

	public static void main(String[] args) throws Exception {
		
		ReadProductTOCFile readProductTOCFile = new ReadProductTOCFile();
		
		//String exportedFileName = readProductTOCFile.getFileFromDirectory(LOMTConstant.EXPORTED_FILE_PATH);
		//File sourceFile = new File(LOMTConstant.EXPORTED_FILE_PATH + exportedFileName);
		File sourceFile = new File(LOMTConstant.ENGLISH_GSE_REIN_XLS_FILE_PATH);
		
		FileInputStream isFile = null;
		FileOutputStream osFile = null;
		XSSFWorkbook workbook = null;
		XSSFSheet worksheet = null;
		Cell cell = null;
		
		isFile = new FileInputStream(sourceFile);
		workbook = new XSSFWorkbook(isFile);
		worksheet = workbook.getSheetAt(1);
		
		//Program Title update
		worksheet.shiftRows(13, worksheet.getLastRowNum(), 1, true, true);
		worksheet.createRow(13);
		
		cell = worksheet.getRow(13).createCell(LOMTConstant.ONE);
		cell.setCellValue(EnglishTestData.DES_ID_NEW);
		
		cell = worksheet.getRow(13).createCell(LOMTConstant.TWO);
		cell.setCellValue("DB1-SI-5|125643");
		
		cell = worksheet.getRow(13).createCell(LOMTConstant.THREE);
		cell.setCellValue(EnglishTestData.SYLLABUS_NEW);
		
		cell = worksheet.getRow(13).createCell(LOMTConstant.FOUR);
		cell.setCellValue("GL_B3");
		
		cell = worksheet.getRow(13).createCell(LOMTConstant.FIVE);
		cell.setCellValue("Speaking");
		
		cell = worksheet.getRow(13).createCell(LOMTConstant.SIX);
		cell.setCellValue("Published");
		
		cell = worksheet.getRow(13).createCell(LOMTConstant.SEVEN);
		cell.setCellValue("Can express likes and dislikes about things they have or do in a very limited way "+new Random().nextInt(1000));
		
		cell = worksheet.getRow(13).createCell(LOMTConstant.EIGHT);
		cell.setCellValue("(P)");
		
		cell = worksheet.getRow(13).createCell(LOMTConstant.NINE);
		cell.setCellValue("27");
		
		cell = worksheet.getRow(13).createCell(LOMTConstant.TEN);
		cell.setCellValue("A1 (22-29)");
		
		cell = worksheet.getRow(13).createCell(LOMTConstant.ELEVENTH);
		cell.setCellValue(EnglishTestData.C_CATEGORY);
		
		cell = worksheet.getRow(13).createCell(LOMTConstant.TWELEVE);
		cell.setCellValue(EnglishTestData.C_CATEGORY);
		
		// UPDATED ACADEMIC SKILLS VALUE
		cell = worksheet.getRow(13).createCell(LOMTConstant.THIRTEEN);
		cell.setCellValue(EnglishTestData.ACADEMIC_SKILLS);
		
		/*//Level for Hierarchy
		cell = worksheet.getRow(LOMTConstant.THIRTEEN).createCell(LOMTConstant.TWO);
		cell.setCellValue("2");
		
		//Level Title
		cell = worksheet.getRow(LOMTConstant.THIRTEEN).createCell(LOMTConstant.THREE);
		cell.setCellValue("New Level Title added !@#$%^&*()_+{}|:?><'',Test12345");
		
		//start & end page
		cell = worksheet.getRow(LOMTConstant.THIRTEEN).createCell(LOMTConstant.FOUR);
		cell.setCellValue("2");
		
		cell = worksheet.getRow(LOMTConstant.THIRTEEN).createCell(LOMTConstant.FIVE);
		cell.setCellValue("4");
		
		//CONTENT TITLE
		cell = worksheet.getRow(LOMTConstant.THIRTEEN).createCell(LOMTConstant.SIX);
		cell.setCellValue("New Content Title added : re-ingetsion !@#$%^&*()_+{}|:?><'',Test12345");*/
		
		isFile.close();
		
		osFile = new FileOutputStream(sourceFile);
		workbook.write(osFile); //writing changes
		osFile.close();
		
		System.out.println("done");

	}

}
