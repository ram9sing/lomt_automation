package lomt.pearson.api.he;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import lomt.pearson.constant.HEConstant;
import lomt.pearson.constant.LOMTConstant;

public class ReadHEFile {
	
	public void updateHEFExportedFileData(int counter) {
		File sourceFile = null;
		File destinationFile = null;
		try {
			String exportedFileName = getFileFromDirectory(LOMTConstant.EXPORTED_FILE_PATH);
			sourceFile = new File(LOMTConstant.EXPORTED_FILE_PATH + exportedFileName);
			destinationFile = new File(HEConstant.HE_DESTINATION_FILE_PATH + HEConstant.HE_REINGESTION_TEMPLATE);
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
		        System.out.println("File " + listOfFiles[i].getName());
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
		
		//QA and pairs
		FileInputStream isFile1 = null;
		FileOutputStream osFile1 = null;
		XSSFWorkbook workbook1 = null;
		XSSFSheet worksheet1 = null;
		Cell cell1 = null;
		
		switch (counter) {	
			
		case 0:
			//update Domian all values
			isFile =  new FileInputStream(file);
			workbook = new XSSFWorkbook(isFile);
			worksheet = workbook.getSheetAt(0);
			
			cell = worksheet.getRow(12).getCell(6);
			cell.setCellValue(HEConstant.LO_1_DESC);
			
			cell = worksheet.getRow(13).getCell(8);
			cell.setCellValue(HEConstant.EO_1_DESC);
			
			cell = worksheet.getRow(16).getCell(9);
			cell.setCellValue(HEConstant.LO_EO_PREREQUISITES);
			
			cell = worksheet.getRow(16).getCell(11);
			cell.setCellValue(HEConstant.IDENTIFIED_AS_A_MOST_DIFFICULT_CONCEPT_Y_N);
			
			cell = worksheet.getRow(16).getCell(13);
			cell.setCellValue(HEConstant.MISCONCEPTION_DESCRIPTIVE_STATEMENT_1);
			
			cell = worksheet.getRow(16).getCell(22);
			cell.setCellValue(HEConstant.DOMAIN);
			
			cell = worksheet.getRow(16).getCell(23);
			cell.setCellValue(HEConstant.BLOOMS_COGNITIVE_PROCESS_DIMENSIONS);
			
			cell = worksheet.getRow(16).getCell(24);
			cell.setCellValue(HEConstant.BLOOMS_KNOWLEDGE_DIMENSIONS);
			
			cell = worksheet.getRow(16).getCell(25);
			cell.setCellValue(HEConstant.WEBBS_DEPTH_OF_KNOWLEDGE_COGNITIVE_COMPLEXITY_DIMENSION);
			
			cell = worksheet.getRow(16).getCell(26);
			cell.setCellValue(HEConstant.PROFICIENCY);
			
			isFile.close();
			//update is done
			
			osFile =new FileOutputStream(file);
			workbook.write(osFile); //writing changes
			osFile.close();
			
			//QA and Pairs data update
			isFile1 =  new FileInputStream(file);
			workbook1 = new XSSFWorkbook(isFile1);
			worksheet1 = workbook1.getSheetAt(1);
			
			cell1 = worksheet1.getRow(4).getCell(5);
			cell1.setCellValue(HEConstant.QUESTION);
			
			cell1 = worksheet1.getRow(4).getCell(6);
			cell1.setCellValue(HEConstant.ANSWER);
			
			cell1 = worksheet1.getRow(4).getCell(7);
			cell1.setCellValue(HEConstant.ASSERTION1);
			
			cell1 = worksheet1.getRow(4).getCell(8);
			cell1.setCellValue(HEConstant.HINT1);
			
			isFile1.close();
			//update is done
			
			osFile1 = new FileOutputStream(file);
			workbook1.write(osFile1); //writing changes
			osFile1.close();
			break;
		case 1:	
			isFile =  new FileInputStream(file);
			workbook = new XSSFWorkbook(isFile);
			worksheet = workbook.getSheetAt(0);
			
			//Add new LO/EO rows
			//New LO/EO added
			cell = worksheet.getRow(38).createCell(5);
			cell.setCellValue(HEConstant.NEW_LO_NUM);
			
			cell = worksheet.getRow(38).createCell(6);
			cell.setCellValue(HEConstant.NEW_LO_NUM_DESC);
			
			cell = worksheet.getRow(38).createCell(9);
			cell.setCellValue(HEConstant.PRE_NEW_LO);
			
			cell = worksheet.getRow(38).createCell(10);
			cell.setCellValue(HEConstant.FLAG_N);
			
			cell = worksheet.getRow(38).createCell(11);
			cell.setCellValue(HEConstant.FLAG_N);
			
			cell = worksheet.getRow(38).createCell(12);
			cell.setCellValue(HEConstant.FLAG_Y);
			
			cell = worksheet.getRow(38).createCell(13);
			cell.setCellValue(HEConstant.LO_MIS);
			
			cell = worksheet.getRow(38).createCell(22);
			cell.setCellValue(HEConstant.DOMAIN);
			
			cell = worksheet.getRow(38).createCell(23);
			cell.setCellValue(HEConstant.BLOOMS_COGNITIVE_PROCESS_DIMENSIONS);
			
			cell = worksheet.getRow(38).createCell(24);
			cell.setCellValue(HEConstant.BLOOMS_KNOWLEDGE_DIMENSIONS); 
			
			cell = worksheet.getRow(38).createCell(25);
			cell.setCellValue(HEConstant.WEBBS_DEPTH_OF_KNOWLEDGE_COGNITIVE_COMPLEXITY_DIMENSION);
			
			cell = worksheet.getRow(38).createCell(26);
			cell.setCellValue(HEConstant.PROFICIENCY);
			//LO update end
			
			//EO 1 Update	
			cell = worksheet.getRow(39).createCell(7);
			cell.setCellValue(HEConstant.NEW_EO_NUM_1);
			
			cell = worksheet.getRow(39).createCell(8);
			cell.setCellValue(HEConstant.NEW_EO_NUM_1_DESC);
			
			cell = worksheet.getRow(39).createCell(9);
			cell.setCellValue(""); // no pre-request 
			
			cell = worksheet.getRow(39).createCell(10);
			cell.setCellValue(HEConstant.FLAG_N);
			
			cell = worksheet.getRow(39).createCell(11);
			cell.setCellValue(HEConstant.FLAG_N);
			
			cell = worksheet.getRow(39).createCell(12);
			cell.setCellValue(HEConstant.FLAG_Y);
			
			cell = worksheet.getRow(39).createCell(13);
			cell.setCellValue(HEConstant.LO_MIS);
			
			cell = worksheet.getRow(39).createCell(22);
			cell.setCellValue(HEConstant.DOMAIN);
			
			cell = worksheet.getRow(39).createCell(23);
			cell.setCellValue(HEConstant.BLOOMS_COGNITIVE_PROCESS_DIMENSIONS);
			
			cell = worksheet.getRow(39).createCell(24);
			cell.setCellValue(HEConstant.BLOOMS_KNOWLEDGE_DIMENSIONS); 
			
			cell = worksheet.getRow(39).createCell(25);
			cell.setCellValue(HEConstant.WEBBS_DEPTH_OF_KNOWLEDGE_COGNITIVE_COMPLEXITY_DIMENSION);
			
			cell = worksheet.getRow(39).createCell(26);
			cell.setCellValue(HEConstant.PROFICIENCY);
			
			//End EO 1 	
			
			cell = worksheet.getRow(40).createCell(7);
			cell.setCellValue(HEConstant.NEW_EO_NUM_2);
			
			cell = worksheet.getRow(40).createCell(8);
			cell.setCellValue(HEConstant.NEW_EO_NUM_2_DESC);
			
			cell = worksheet.getRow(40).createCell(9);
			cell.setCellValue(HEConstant.PRE_NEW_EO_1); 
			
			cell = worksheet.getRow(40).createCell(10);
			cell.setCellValue(HEConstant.FLAG_N);
			
			cell = worksheet.getRow(40).createCell(11);
			cell.setCellValue(HEConstant.FLAG_N);
			
			cell = worksheet.getRow(40).createCell(12);
			cell.setCellValue(HEConstant.FLAG_N);
			
			//cell = worksheet.getRow(40).createCell(13);
			//cell.setCellValue(HEConstant.LO_MIS);
			
			cell = worksheet.getRow(40).createCell(22);
			cell.setCellValue(HEConstant.DOMAIN);
			
			cell = worksheet.getRow(40).createCell(23);
			cell.setCellValue(HEConstant.BLOOMS_COGNITIVE_PROCESS_DIMENSIONS);
			
			cell = worksheet.getRow(40).createCell(24);
			cell.setCellValue(HEConstant.BLOOMS_KNOWLEDGE_DIMENSIONS); 
			
			cell = worksheet.getRow(40).createCell(25);
			cell.setCellValue(HEConstant.WEBBS_DEPTH_OF_KNOWLEDGE_COGNITIVE_COMPLEXITY_DIMENSION);
			
			cell = worksheet.getRow(40).createCell(26);
			cell.setCellValue(HEConstant.PROFICIENCY);
			
			//End EO 2 	
			
			cell = worksheet.getRow(41).createCell(7);
			cell.setCellValue(HEConstant.NEW_EO_NUM_3);
			
			cell = worksheet.getRow(41).createCell(8);
			cell.setCellValue(HEConstant.NEW_EO_NUM_3_DESC);
			
			cell = worksheet.getRow(41).createCell(9);
			cell.setCellValue(HEConstant.PRE_NEW_EO_2); 
			
			cell = worksheet.getRow(41).createCell(10);
			cell.setCellValue(HEConstant.FLAG_N);
			
			cell = worksheet.getRow(41).createCell(11);
			cell.setCellValue(HEConstant.FLAG_N);
			
			cell = worksheet.getRow(41).createCell(12);
			cell.setCellValue(HEConstant.FLAG_Y);
			
			cell = worksheet.getRow(41).createCell(13);
			cell.setCellValue(HEConstant.LO_MIS);
			
			cell = worksheet.getRow(41).createCell(22);
			cell.setCellValue(HEConstant.DOMAIN);
			
			cell = worksheet.getRow(41).createCell(23);
			cell.setCellValue(HEConstant.BLOOMS_COGNITIVE_PROCESS_DIMENSIONS);
			
			cell = worksheet.getRow(41).createCell(24);
			cell.setCellValue(HEConstant.BLOOMS_KNOWLEDGE_DIMENSIONS); 
			
			cell = worksheet.getRow(41).createCell(25);
			cell.setCellValue(HEConstant.WEBBS_DEPTH_OF_KNOWLEDGE_COGNITIVE_COMPLEXITY_DIMENSION);
			
			cell = worksheet.getRow(41).createCell(26);
			cell.setCellValue(HEConstant.PROFICIENCY);
			
			//End EO 3 	
			
			isFile.close();
			
			osFile =new FileOutputStream(file);
			workbook.write(osFile); 
			osFile.close();
			break;
		case 2:	
			//delete LO/EO			
			isFile =  new FileInputStream(file);
			workbook = new XSSFWorkbook(isFile);
			worksheet = workbook.getSheetAt(0);
			
			worksheet.removeRow(worksheet.getRow(41));
			
			isFile.close();
			
			osFile =new FileOutputStream(file);
			workbook.write(osFile); 
			osFile.close();
			
			//QA & Pairs
			isFile1 =  new FileInputStream(file);
			workbook1 = new XSSFWorkbook(isFile1);
			worksheet1 = workbook1.getSheetAt(1);
			
			// udpate question, answer, assertions, hint
			cell1 = worksheet1.getRow(31).createCell(5);
			cell1.setCellValue(HEConstant.NEW_QUESTION_ADDED);
			
			cell1 = worksheet1.getRow(31).createCell(6);
			cell1.setCellValue(HEConstant.NEW_ANSWER_ADDED);
			
			cell1 = worksheet1.getRow(31).createCell(7);
			cell1.setCellValue(HEConstant.NEW_ASSERTION_ADDED);
			
			cell1 = worksheet1.getRow(31).createCell(8);
			cell1.setCellValue(HEConstant.NEW_HINT_ADDED);
			
			//row deleted
			worksheet1.removeRow(worksheet1.getRow(33));
			
			isFile1.close();
			
			osFile1 =new FileOutputStream(file);
			workbook1.write(osFile1); 
			osFile1.close();
			break;
			
		default:
		}
	}

}
