package lomt.pearson.api.nals_sg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import lomt.pearson.constant.LOMTConstant;
import lomt.pearson.constant.SchoolConstant;

public class IntermediaryHelper {
	
	public void updateIntermediaryExportedFileData(int counter) {
		File sourceFile = null;
		File destinationFile = null;
		try {
			String exportedFileName = getFileFromDirectory(LOMTConstant.EXPORTED_FILE_PATH);
			sourceFile = new File(LOMTConstant.EXPORTED_FILE_PATH + exportedFileName);
			destinationFile = new File(SchoolConstant.INT_DESTINATION_FILE_PATH + SchoolConstant.INT_REINGESTION_TEMPLATE);
			if (copyExportFileIntoNewFile(sourceFile, destinationFile))
				updateExportedFileData(destinationFile, counter);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getFileFromDirectory(String filePath) {
		String exportedFileName = null;
		File folder = new File(filePath);
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
		      if (listOfFiles[i].isFile()) {
		    	  exportedFileName = listOfFiles[i].getName();
		      } 
		    }
		return exportedFileName;
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
	
	public void updateExportedFileData(File file, int counter) throws Exception {
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
		
		switch (counter) {	
		
		case 0:			
			isFile =  new FileInputStream(file);
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
			
			isFile.close();
			
			osFile = new FileOutputStream(file);
			workbook.write(osFile); 
			osFile.close();
			
			//addition of new row
			isFile1 =  new FileInputStream(file);
			workbook1 = new XSSFWorkbook(isFile1);
			worksheet1 = workbook1.getSheetAt(0);
			
			worksheet1.shiftRows(2, worksheet1.getLastRowNum(), 2, true, true);
			worksheet1.createRow(2);
			
			cell1 = worksheet1.getRow(2).createCell(0);
			cell1.setCellValue(SchoolConstant.INTERMEDIARY_STMT_NEW_1);
			
			cell1 = worksheet1.getRow(2).createCell(1);
			cell1.setCellValue(SchoolConstant.TAG_NEW);
			
			cell1 = worksheet1.getRow(2).createCell(2);
			cell1.setCellValue(SchoolConstant.CATEGORY_NEW);
			
			cell1 = worksheet1.getRow(2).createCell(3);
			cell1.setCellValue(SchoolConstant.IS_CODE_NEW);
			
			cell1 = worksheet1.createRow(3).createCell(0);
			cell1.setCellValue(SchoolConstant.INTERMEDIARY_STMT_NEW_2);
			
			cell1 = worksheet1.getRow(3).createCell(1);
			cell1.setCellValue(SchoolConstant.TAG_NEW_2);
			
			cell1 = worksheet1.getRow(3).createCell(2);
			cell1.setCellValue(SchoolConstant.CATEGORY_NEW_2);
			
			cell1 = worksheet1.getRow(3).createCell(3);
			cell1.setCellValue(SchoolConstant.IS_CODE_NEW_2);	
			
			isFile1.close();
			
			osFile1 = new FileOutputStream(file);
			workbook1.write(osFile1); 
			osFile1.close();
			break;
		case 1:			
			//deletion rows
			isFile =  new FileInputStream(file);
			workbook = new XSSFWorkbook(isFile);
			worksheet = workbook.getSheetAt(0);
			
			worksheet.removeRow(worksheet.getRow(3));
			
			isFile.close();
			
			osFile = new FileOutputStream(file);
			workbook.write(osFile); 
			osFile.close();
			break;
		case 2:			
			isFile =  new FileInputStream(file);
			workbook = new XSSFWorkbook(isFile);
			worksheet = workbook.getSheetAt(0);
			
			cell = worksheet.getRow(1).getCell(0);
			cell.setCellValue("");
			
			cell = worksheet.getRow(1).getCell(1);
			cell.setCellValue("");
			
			cell = worksheet.getRow(2).getCell(1);
			cell.setCellValue("");
			
			//Wrong header
			/*cell = worksheet.getRow(0).getCell(0);
			cell.setCellValue("test header");*/
			
			isFile.close();
			
			osFile = new FileOutputStream(file);
			workbook.write(osFile); 
			osFile.close();
			break;
			
			default:
		}
	}

}
