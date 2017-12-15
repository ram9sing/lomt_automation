package lomt.pearson.api.product_toc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.util.Iterator;
import java.util.Random;

import lomt.pearson.constant.ExternalFrameworkTestData;
import lomt.pearson.constant.LOMTConstant;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ReadProductTOCFile {
	
	// copy the code and URN into Code & Goal URN column
	public boolean copyCodeAndURNFromExportIntermediaryFile(File exportedfile, File aligCodeFile, File aignGoalURNFile, String subjectName) {
		boolean flag = false;
		
		FileInputStream isFile1 = null;
		XSSFWorkbook workbook1 = null;
		XSSFSheet expWorksheet = null;
		Cell cell1 = null;
		
		FileInputStream isFile2 = null;
		FileOutputStream osFile2 = null;
		XSSFWorkbook workbook2 = null;
		XSSFSheet alignCodeWorksheet = null;
		Cell cell2 = null;
		
		FileInputStream isFile3 = null;
		FileOutputStream osFile3 = null;
		XSSFWorkbook workbook3 = null;
		XSSFSheet goalURNworksheet = null;
		
		if (exportedfile !=null && exportedfile.isFile() || aligCodeFile !=null && aligCodeFile.isFile()
				|| aligCodeFile !=null && aligCodeFile.isFile() ) {
			try {
				isFile1 =  new FileInputStream(exportedfile);
				workbook1 = new XSSFWorkbook(isFile1);
				expWorksheet = workbook1.getSheetAt(0);
				
				isFile2 =  new FileInputStream(aligCodeFile);
				workbook2 = new XSSFWorkbook(isFile2);
				alignCodeWorksheet = workbook2.getSheetAt(0);
				
				isFile3 =  new FileInputStream(aignGoalURNFile);
				workbook3 = new XSSFWorkbook(isFile3);
				goalURNworksheet = workbook3.getSheetAt(0);
				
				//Start copying Alignment Code 
				cell1 = expWorksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.THREE);				
				cell2 = alignCodeWorksheet.getRow(LOMTConstant.TWELEVE).getCell(LOMTConstant.TEN);
				cell2.setCellValue(cell1.getStringCellValue());
				
				cell2 = alignCodeWorksheet.getRow(LOMTConstant.TWELEVE).getCell(LOMTConstant.NINE); //Discipline
				cell2.setCellValue(subjectName);
				
				cell1 = expWorksheet.getRow(LOMTConstant.TWO).getCell(LOMTConstant.THREE);
				cell2 = alignCodeWorksheet.getRow(LOMTConstant.THIRTEEN).getCell(LOMTConstant.TEN);
				cell2.setCellValue(cell1.getStringCellValue());
				
				cell2 = alignCodeWorksheet.getRow(LOMTConstant.THIRTEEN).getCell(LOMTConstant.NINE); //Discipline
				cell2.setCellValue(subjectName);
				
				cell1 = expWorksheet.getRow(LOMTConstant.THREE).getCell(LOMTConstant.THREE);
				cell2 = alignCodeWorksheet.getRow(LOMTConstant.FOURTEEN).getCell(LOMTConstant.TEN);
				cell2.setCellValue(cell1.getStringCellValue());
				
				cell2 = alignCodeWorksheet.getRow(LOMTConstant.FOURTEEN).getCell(LOMTConstant.NINE); //Discipline
				cell2.setCellValue(subjectName);
				
				cell1 = expWorksheet.getRow(LOMTConstant.FOUR).getCell(LOMTConstant.THREE);
				cell2 = alignCodeWorksheet.getRow(LOMTConstant.FIFTEEN).getCell(LOMTConstant.TEN);
				cell2.setCellValue(cell1.getStringCellValue());
				
				cell2 = alignCodeWorksheet.getRow(LOMTConstant.FIFTEEN).getCell(LOMTConstant.NINE); //Discipline
				cell2.setCellValue(subjectName);
				
				cell1 = expWorksheet.getRow(LOMTConstant.FIVE).getCell(LOMTConstant.THREE);
				cell2 = alignCodeWorksheet.getRow(LOMTConstant.SIXTEEN).getCell(LOMTConstant.TEN);
				cell2.setCellValue(cell1.getStringCellValue());
				
				cell2 = alignCodeWorksheet.getRow(LOMTConstant.SIXTEEN).getCell(LOMTConstant.NINE); //Discipline
				cell2.setCellValue(subjectName);
				
				cell1 = expWorksheet.getRow(LOMTConstant.SIX).getCell(LOMTConstant.THREE);
				cell2 = alignCodeWorksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.TEN);
				cell2.setCellValue(cell1.getStringCellValue());		
				
				cell2 = alignCodeWorksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.NINE); //Discipline
				cell2.setCellValue(subjectName);
				
				cell1 = expWorksheet.getRow(LOMTConstant.SEVEN).getCell(LOMTConstant.THREE);
				cell2 = alignCodeWorksheet.getRow(LOMTConstant.TWENTY).getCell(LOMTConstant.TEN);
				cell2.setCellValue(cell1.getStringCellValue());
				
				cell2 = alignCodeWorksheet.getRow(LOMTConstant.TWENTY).getCell(LOMTConstant.NINE); //Discipline
				cell2.setCellValue(subjectName);
				
				cell1 = expWorksheet.getRow(LOMTConstant.EIGHT).getCell(LOMTConstant.THREE);
				cell2 = alignCodeWorksheet.getRow(LOMTConstant.TWENTY_ONE).getCell(LOMTConstant.TEN);
				cell2.setCellValue(cell1.getStringCellValue());	
				
				cell2 = alignCodeWorksheet.getRow(LOMTConstant.TWENTY_ONE).getCell(LOMTConstant.NINE); //Discipline
				cell2.setCellValue(subjectName);
				
				cell1 = expWorksheet.getRow(LOMTConstant.EIGHT).getCell(LOMTConstant.THREE);
				cell2 = alignCodeWorksheet.getRow(35).getCell(LOMTConstant.TEN);
				cell2.setCellValue(cell1.getStringCellValue());	
				
				cell2 = alignCodeWorksheet.getRow(35).getCell(LOMTConstant.NINE); //Discipline
				cell2.setCellValue(subjectName);
				
				cell1 = expWorksheet.getRow(LOMTConstant.FOUR).getCell(LOMTConstant.THREE);
				cell2 = alignCodeWorksheet.getRow(51).getCell(LOMTConstant.TEN);
				cell2.setCellValue(cell1.getStringCellValue());	
				
				cell2 = alignCodeWorksheet.getRow(51).getCell(LOMTConstant.NINE); //Discipline
				cell2.setCellValue(subjectName);
				
				cell1 = expWorksheet.getRow(LOMTConstant.EIGHT).getCell(LOMTConstant.THREE);
				cell2 = alignCodeWorksheet.getRow(67).getCell(LOMTConstant.TEN);
				cell2.setCellValue(cell1.getStringCellValue());	
				
				cell2 = alignCodeWorksheet.getRow(67).getCell(LOMTConstant.NINE); //Discipline
				cell2.setCellValue(subjectName);
				
				cell1 = expWorksheet.getRow(LOMTConstant.SIX).getCell(LOMTConstant.THREE);
				cell2 = alignCodeWorksheet.getRow(95).getCell(LOMTConstant.TEN);
				cell2.setCellValue(cell1.getStringCellValue());	
				
				cell2 = alignCodeWorksheet.getRow(95).getCell(LOMTConstant.NINE); //Discipline
				cell2.setCellValue(subjectName);
				
				cell1 = expWorksheet.getRow(LOMTConstant.NINE).getCell(LOMTConstant.THREE);
				cell2 = alignCodeWorksheet.getRow(96).getCell(LOMTConstant.TEN);
				cell2.setCellValue(cell1.getStringCellValue());		
				
				cell2 = alignCodeWorksheet.getRow(96).getCell(LOMTConstant.NINE); //Discipline
				cell2.setCellValue(subjectName);
				
				cell1 = expWorksheet.getRow(LOMTConstant.TEN).getCell(LOMTConstant.THREE);
				cell2 = alignCodeWorksheet.getRow(97).getCell(LOMTConstant.TEN);
				cell2.setCellValue(cell1.getStringCellValue());	
				
				cell2 = alignCodeWorksheet.getRow(97).getCell(LOMTConstant.NINE); //Discipline
				cell2.setCellValue(subjectName);
				
				isFile2.close();				
				
				osFile2 = new FileOutputStream(aligCodeFile);
				workbook2.write(osFile2); //writing changes
				osFile2.close();
				
				flag = true;
				//End of Align code mapping
				
				//copying Intermediary URN, into Goal URN column				
				/*cell1 = expWorksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.THREE);				
				cell2 = goalURNworksheet.getRow(LOMTConstant.TWELEVE).getCell(LOMTConstant.TEN);
				cell2.setCellValue(cell1.getStringCellValue());
				
				cell1 = expWorksheet.getRow(LOMTConstant.TWO).getCell(LOMTConstant.THREE);
				cell2 = goalURNworksheet.getRow(LOMTConstant.THIRTEEN).getCell(LOMTConstant.TEN);
				cell2.setCellValue(cell1.getStringCellValue());
				
				cell1 = expWorksheet.getRow(LOMTConstant.THREE).getCell(LOMTConstant.THREE);
				cell2 = goalURNworksheet.getRow(LOMTConstant.FOURTEEN).getCell(LOMTConstant.TEN);
				cell2.setCellValue(cell1.getStringCellValue());
				
				cell1 = expWorksheet.getRow(LOMTConstant.FOUR).getCell(LOMTConstant.THREE);
				cell2 = goalURNworksheet.getRow(LOMTConstant.FIFTEEN).getCell(LOMTConstant.TEN);
				cell2.setCellValue(cell1.getStringCellValue());
				
				cell1 = expWorksheet.getRow(LOMTConstant.FIVE).getCell(LOMTConstant.THREE);
				cell2 = goalURNworksheet.getRow(LOMTConstant.SIXTEEN).getCell(LOMTConstant.TEN);
				cell2.setCellValue(cell1.getStringCellValue());
				
				cell1 = expWorksheet.getRow(LOMTConstant.SIX).getCell(LOMTConstant.THREE);
				cell2 = goalURNworksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.TEN);
				cell2.setCellValue(cell1.getStringCellValue());	
				
				isFile3.close();				
				isFile1.close();
				
				osFile3 = new FileOutputStream(aignGoalURNFile);
				workbook3.write(osFile3); //writing changes
				osFile3.close();*/
				//End GOAL URN
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return flag;
	}
	
	public String readGoalframeworkName(File file) {
		String goalFrameworkName = null;
		try {
			InputStream inputStream = new FileInputStream(file);
			XSSFWorkbook xssFWorkbook = new XSSFWorkbook(inputStream);
			XSSFSheet actualDataSheet = xssFWorkbook.getSheetAt(0);
			//Product Title
			goalFrameworkName = actualDataSheet.getRow(LOMTConstant.TWO).getCell(LOMTConstant.ONE).getStringCellValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return goalFrameworkName;
	}
	
	public void removeExistingFile() throws IOException {
		if (new File(LOMTConstant.EXPORTED_FILE_PATH).exists()) 
			FileUtils.cleanDirectory(new File(LOMTConstant.EXPORTED_FILE_PATH));
	}
	
	public boolean copyExportFileIntoNewFile(File source, File dest) throws IOException {
	    FileChannel sourceChannel = null;
	    FileChannel destChannel = null;
	    
	    boolean flag = false;
		try {
			sourceChannel = new FileInputStream(source).getChannel();
			destChannel = new FileOutputStream(dest).getChannel();
			destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
	           sourceChannel.close();
	           destChannel.close();
	   }
	    return flag;
	}
	
	public String getFileFromDirectory(String filePath) {
		String exportedFileName = null;
		File folder = new File(filePath);
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
		      if (listOfFiles[i].isFile()) {
		    	  exportedFileName = listOfFiles[i].getName();
		        System.out.println("File " + listOfFiles[i].getName());
		      } 
		    }
		return exportedFileName;
	}
	
	public boolean updateFileData(File reIngestionFile, int counter, ExtentTest logger) throws Exception {
		
		boolean flag = true;
		
		FileInputStream isFile = null;
		FileOutputStream osFile = null;
		XSSFWorkbook workbook = null;
		XSSFSheet worksheet = null;
		Cell cell = null;
		
		switch (counter) {
		case 0:
			//Updated all fileds except alignment(Goal URN and Code)
			isFile = new FileInputStream(reIngestionFile);
			workbook = new XSSFWorkbook(isFile);
			worksheet = workbook.getSheetAt(0);
			
			//Program Title update
			cell = worksheet.getRow(LOMTConstant.ZERO).getCell(LOMTConstant.ONE);
			cell.setCellValue(cell.getStringCellValue()+"_updated"+new Random().nextInt(500));
			
			//Course Title
			cell = worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.ONE);
			cell.setCellValue(cell.getStringCellValue()+"_updated");
			
			//Product Title
			cell = worksheet.getRow(LOMTConstant.TWO).getCell(LOMTConstant.ONE);
			cell.setCellValue(cell.getStringCellValue()+"_updated");
			
			//State or Region
			cell = worksheet.getRow(LOMTConstant.FOUR).getCell(LOMTConstant.ONE);
			cell.setCellValue("Hawaii");
			
			//Start Grade
			cell = worksheet.getRow(LOMTConstant.SIX).getCell(LOMTConstant.ONE);
			cell.setCellValue("1");

			//End Grade
			cell = worksheet.getRow(LOMTConstant.SEVEN).getCell(LOMTConstant.ONE);
			cell.setCellValue("10");
			
			//ISBN 10
			cell = worksheet.getRow(LOMTConstant.EIGHT).getCell(LOMTConstant.ONE);
			cell.setCellValue("isbn 10");

			//ISBN 13
			cell = worksheet.getRow(LOMTConstant.NINE).getCell(LOMTConstant.ONE);
			cell.setCellValue("isbn 13");
			
			//Print Type, if it is Digital then Screen Location field should have value
			cell = worksheet.getRow(LOMTConstant.TEN).getCell(LOMTConstant.ONE);
			cell.setCellValue("Digital");
			
			//Removing Print Location value
			cell = worksheet.getRow(LOMTConstant.TWELEVE).getCell(LOMTConstant.EIGHT);
			cell.setCellValue("");
			
			//Adding Screen Location value
			cell = worksheet.getRow(LOMTConstant.TWELEVE).getCell(LOMTConstant.SEVEN);
			cell.setCellValue("Screen Location");
			
			//Level for Hierarchy
			cell = worksheet.getRow(LOMTConstant.FOURTEEN).getCell(LOMTConstant.TWO);
			cell.setCellValue("2");
			
			//Level Title
			cell = worksheet.getRow(LOMTConstant.TWELEVE).getCell(LOMTConstant.THREE);
			cell.setCellValue(cell.getStringCellValue()+" UPDATED!@#$%^&*()_+{}|:'<>Test123 test test test test test test test test test test test test test test test test test test test test test test test 123");
			
			//Starting Page No
			cell = worksheet.getRow(LOMTConstant.TWELEVE).getCell(LOMTConstant.FOUR);
			cell.setCellValue("4");
			
			//End Page No
			cell = worksheet.getRow(LOMTConstant.TWELEVE).getCell(LOMTConstant.FIVE);
			cell.setCellValue("9");
			
			//Content Title
			cell = worksheet.getRow(LOMTConstant.TWELEVE).getCell(LOMTConstant.SIX);
			cell.setCellValue(cell.getStringCellValue()+" UPDATED!@#$%^&*()_+{}|:'<>Test123 test test test test test test test test test test test test test test test test test test test test test test test 123");
			isFile.close();
			
			osFile = new FileOutputStream(reIngestionFile);
			workbook.write(osFile); //writing changes
			osFile.close();
			break;
		case 1:
			//Add new row
			isFile = new FileInputStream(reIngestionFile);
			workbook = new XSSFWorkbook(isFile);
			worksheet = workbook.getSheetAt(0);
			
			// Shift of one row all the rows starting from the 3th row
			worksheet.shiftRows(13, worksheet.getLastRowNum(), 1, true, true);
			worksheet.createRow(13);
			
			//Filling data at ROW 13
			
			//Level for Hierarchy
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
			cell.setCellValue("New Content Title added : re-ingetsion !@#$%^&*()_+{}|:?><'',Test12345");
			
			isFile.close();
			
			osFile = new FileOutputStream(reIngestionFile);
			workbook.write(osFile); //writing changes
			osFile.close();
			break;	
		case 2:
			//Validation check
			isFile = new FileInputStream(reIngestionFile);
			workbook = new XSSFWorkbook(isFile);
			worksheet = workbook.getSheetAt(0);
			
			//Product Title blank
			cell = worksheet.getRow(LOMTConstant.TWO).getCell(LOMTConstant.ONE);
			cell.setCellValue("");
			
			//Level for Hierarchy
			cell = worksheet.getRow(LOMTConstant.TWELEVE).getCell(LOMTConstant.TWO);
			cell.setCellValue("");
			
			//Level Title
			cell = worksheet.getRow(LOMTConstant.FIFTEEN).getCell(LOMTConstant.THREE);
			cell.setCellValue("");
			
			isFile.close();
			
			osFile = new FileOutputStream(reIngestionFile);
			workbook.write(osFile); //writing changes
			osFile.close();			
			break;
		case 3:
			// Update Goal URN values			
			isFile = new FileInputStream(reIngestionFile);
			workbook = new XSSFWorkbook(isFile);
			worksheet = workbook.getSheetAt(0);
			
			//Getting goal urn value
			String goalURNVal = worksheet.getRow(LOMTConstant.FOURTEEN).getCell(LOMTConstant.TWELEVE).getStringCellValue();
			if (goalURNVal.isEmpty()) {
				logger.log(LogStatus.FAIL, "Alignment : Code & Goal URN is empty in product toc exported file while it should has value");
				flag = false;
				return flag;
			}
			
			//setting goal urn value
			cell = worksheet.getRow(LOMTConstant.TWELEVE).getCell(LOMTConstant.TWELEVE);
			cell.setCellValue(goalURNVal);
			
			//AlignmentType blank
			cell = worksheet.getRow(LOMTConstant.THIRTEEN).getCell(LOMTConstant.THIRTEEN);
			cell.setCellValue("");
			
			//Key Alignment blank
			cell = worksheet.getRow(LOMTConstant.THIRTEEN).getCell(LOMTConstant.FOURTEEN);
			cell.setCellValue("");
			
			isFile.close();
			
			osFile = new FileOutputStream(reIngestionFile);
			workbook.write(osFile); //writing changes
			osFile.close();	
			break;	
		default :
			
		}
		return flag;
	}
	
}
