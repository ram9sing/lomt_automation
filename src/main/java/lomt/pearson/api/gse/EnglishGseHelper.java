package lomt.pearson.api.gse;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import junit.framework.Assert;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import lomt.pearson.api.externalframework.ReadExternalFrameworkFile;
import lomt.pearson.constant.EnglishTestData;
import lomt.pearson.constant.LOMTConstant;

public class EnglishGseHelper {
	
	public ReadExternalFrameworkFile readExFFile = new ReadExternalFrameworkFile();
	
	public void copyFileForReingestion(File source, File dest, int counter){
		try {
			if(readExFFile.copyExportFileIntoNewFile(source, dest))
				updateFileData(dest, counter); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateFileData(File file, int counter) throws Exception {
		FileInputStream isFile = null;
		FileOutputStream osFile = null;
		XSSFWorkbook workbook = null;
		XSSFSheet worksheet = null;
		Cell cell = null;

		switch (counter) {
		case 0:
			isFile =  new FileInputStream(file);
			workbook = new XSSFWorkbook(isFile);
			worksheet = workbook.getSheetAt(1);
			
			// ######### FIRST ROW ##########
			//Updated Descriptor value
			cell = worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.SEVEN);
			String value = cell.getStringCellValue();
			cell.setCellValue(value+EnglishTestData.DESCRIPTOR_VALUE_1);
			
			// UPDATED ATTRIBUTION
			cell = worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.EIGHT);
			cell.setCellValue("(C)");
			
			// UPDATED GSE
			cell = worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.NINE);
			cell.setCellValue("10");
			
			// UPDATED CEREF LEVEL
			cell = worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.TEN);
			cell.setCellValue("A2 (30-35)");
			
			//updated COMMUNICATIVE CATEGORY VALUE
			cell = worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.ELEVENTH);
			cell.setCellValue(EnglishTestData.C_CATEGORY);
			
			// UPDATED BUSINESS SKILLS VALUE
			cell = worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.TWELEVE);
			cell.setCellValue(EnglishTestData.B_SKILLS);
			
			// UPDATED ACADEMIC SKILLS VALUE
			cell = worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.THIRTEEN);
			cell.setCellValue(EnglishTestData.ACADEMIC_SKILLS);
			
			//####### 2ND ROW UDPATE ###############
			//DESCRITPOR
			cell = worksheet.getRow(LOMTConstant.TWO).getCell(LOMTConstant.SEVEN);
			String value1 = cell.getStringCellValue();
			cell.setCellValue(value1+EnglishTestData.DESCRIPTOR_VALUE_1);
			
			//updated COMMUNICATIVE CATEGORY VALUE
			cell = worksheet.getRow(LOMTConstant.TWO).getCell(LOMTConstant.ELEVENTH);
			cell.setCellValue(EnglishTestData.C_CATEGORY);
			
			// UPDATED BUSINESS SKILLS VALUE
			cell = worksheet.getRow(LOMTConstant.TWO).getCell(LOMTConstant.TWELEVE);
			cell.setCellValue(EnglishTestData.B_SKILLS);
			
			// UPDATED ACADEMIC SKILLS VALUE
			cell = worksheet.getRow(LOMTConstant.TWO).getCell(LOMTConstant.THIRTEEN);
			cell.setCellValue(EnglishTestData.ACADEMIC_SKILLS);
			
			//##### 3RD ROW UDPATE ###############
			
			cell = worksheet.getRow(LOMTConstant.TWO).getCell(LOMTConstant.SEVEN);
			String value2 = cell.getStringCellValue();
			cell.setCellValue(value2+EnglishTestData.DESCRIPTOR_VALUE_1);
			
			//updated COMMUNICATIVE CATEGORY VALUE
			cell = worksheet.getRow(LOMTConstant.TWO).getCell(LOMTConstant.ELEVENTH);
			cell.setCellValue("");
			
			// UPDATED BUSINESS SKILLS VALUE
			cell = worksheet.getRow(LOMTConstant.TWO).getCell(LOMTConstant.TWELEVE);
			cell.setCellValue("");
			
			// UPDATED ACADEMIC SKILLS VALUE
			cell = worksheet.getRow(LOMTConstant.TWO).getCell(LOMTConstant.THIRTEEN);
			cell.setCellValue("");
			
			// ############# ADDED NEW ROW AT 14TH ROW ####################
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
			cell.setCellValue(EnglishTestData.B_SKILLS);
			
			// UPDATED ACADEMIC SKILLS VALUE
			cell = worksheet.getRow(13).createCell(LOMTConstant.THIRTEEN);
			cell.setCellValue(EnglishTestData.ACADEMIC_SKILLS);
			
			isFile.close();
			//update is done
			
			osFile =new FileOutputStream(file);
			workbook.write(osFile); //writing changes
			osFile.close();
			System.out.println("couner 0 is done");
			break;
		case 1:
			//Re-ingestion with URN
			isFile =  new FileInputStream(file);
			workbook = new XSSFWorkbook(isFile);
			worksheet = workbook.getSheetAt(1);
			cell = worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.TWO);
			cell.setCellValue(EnglishTestData.DRAFT_ID);
			
			cell = worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.FOUR);
			cell.setCellValue(EnglishTestData.BATCH_ID);
			
			cell = worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.FIVE);
			cell.setCellValue(EnglishTestData.SKILL);
			
			cell = worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.SIX);
			cell.setCellValue(EnglishTestData.STATUS);
			
			cell = worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.SEVEN);
			cell.setCellValue(EnglishTestData.DESCRIPTOR);
			
			cell = worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.EIGHT);
			cell.setCellValue(EnglishTestData.ATTRIBUTION);
			
			cell = worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.NINE);
			cell.setCellValue(EnglishTestData.GSE);
			
			cell = worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.TEN);
			cell.setCellValue(EnglishTestData.CEFR_LEVEL);
			
			cell = worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.ELEVENTH);
			cell.setCellValue(EnglishTestData.C_CATEGORY);
			
			cell = worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.TWELEVE);
			cell.setCellValue(EnglishTestData.B_SKILLS);
			
			cell = worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.THIRTEEN);
			cell.setCellValue(EnglishTestData.ACADEMIC_SKILLS_NEW);
			
			cell = worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.TWENTY);
			cell.setCellValue(EnglishTestData.FUN_NOTION);
			
			cell = worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.TWENTY_ONE);
			cell.setCellValue(EnglishTestData.ANCHAR);
			
			cell = worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.TWENTY_TWO);
			cell.setCellValue(EnglishTestData.SOURCE_DES);
			
			cell = worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.TWENTY_THREE);
			cell.setCellValue(EnglishTestData.SOURCE);
			
			cell = worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.TWENTY_FOUR);
			cell.setCellValue(EnglishTestData.ESTIMATED_LEVEL);
			
			cell = worksheet.getRow(LOMTConstant.ONE).getCell(25);
			cell.setCellValue(EnglishTestData.NOTES);
			
			isFile.close();
			//update is done
			
			osFile =new FileOutputStream(file);
			workbook.write(osFile); //writing changes
			osFile.close();
			System.out.println("couner 1 is done");
			break;
		default:
		}
	}
	
	public void compareSpreadFiles(File sourceFile, File destFile) {
		FileInputStream isFile = null;
		XSSFWorkbook workbook = null;
		XSSFSheet worksheet = null;
		
		FileInputStream isFileDest = null;
		XSSFWorkbook workbookDest = null;
		XSSFSheet worksheetDest = null;
		
		try {
			isFile =  new FileInputStream(sourceFile);
			workbook = new XSSFWorkbook(isFile);
			worksheet = workbook.getSheetAt(1);
			
			isFileDest =  new FileInputStream(destFile);
			workbookDest = new XSSFWorkbook(isFileDest);
			worksheetDest = workbookDest.getSheetAt(1);
			
			//int num = worksheet.getRow(1).compareTo(worksheetDest.getRow(1));, it works in only same file
			
			//URN 
			assertEquals(worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.ZERO).getStringCellValue(),
					worksheetDest.getRow(LOMTConstant.ONE).getCell(LOMTConstant.ZERO).getStringCellValue());  
			
			//Descriptive id
			assertEquals(worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.ONE).getStringCellValue(),
					worksheetDest.getRow(LOMTConstant.ONE).getCell(LOMTConstant.ONE).getStringCellValue()); 
			
			//Draft id
			assertEquals(worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.TWO).getStringCellValue(),
					worksheetDest.getRow(LOMTConstant.ONE).getCell(LOMTConstant.TWO).getStringCellValue()); 
			
			//Syllabus
			assertEquals(worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.THREE).getStringCellValue(),
					worksheetDest.getRow(LOMTConstant.ONE).getCell(LOMTConstant.THREE).getStringCellValue());
			
			//Batch
			assertEquals(worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.FOUR).getStringCellValue(),
					worksheetDest.getRow(LOMTConstant.ONE).getCell(LOMTConstant.FOUR).getStringCellValue());
			
			//Skill
			assertEquals(worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.FIVE).getStringCellValue(),
					worksheetDest.getRow(LOMTConstant.ONE).getCell(LOMTConstant.FIVE).getStringCellValue());
			
			//Status
			assertEquals(worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.SIX).getStringCellValue(),
					worksheetDest.getRow(LOMTConstant.ONE).getCell(LOMTConstant.SIX).getStringCellValue());
			
			//Descriptor
			assertEquals(worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.SEVEN).getStringCellValue(),
					worksheetDest.getRow(LOMTConstant.ONE).getCell(LOMTConstant.SEVEN).getStringCellValue());
			
			//Attribution
			assertEquals(worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.EIGHT).getStringCellValue(),
					worksheetDest.getRow(LOMTConstant.ONE).getCell(LOMTConstant.EIGHT).getStringCellValue());
			//GSE
			assertEquals(worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.NINE).getStringCellValue(),
					worksheetDest.getRow(LOMTConstant.ONE).getCell(LOMTConstant.NINE).getStringCellValue());
			
			//CEFR Level
			assertEquals(worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.TEN).getStringCellValue(),
					worksheetDest.getRow(LOMTConstant.ONE).getCell(LOMTConstant.TEN).getStringCellValue());
			
			//Communicative Categories
			/*assertEquals(worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.ELEVENTH).getStringCellValue(),
					worksheetDest.getRow(LOMTConstant.ONE).getCell(LOMTConstant.ELEVENTH).getStringCellValue());*/
			
			//Business Skills
			/*assertEquals(worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.TWELEVE).getStringCellValue(),
					worksheetDest.getRow(LOMTConstant.ONE).getCell(LOMTConstant.TWELEVE).getStringCellValue());*/
			
			//Academic Skills
			/*assertEquals(worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.THIRTEEN).getStringCellValue(),
					worksheetDest.getRow(LOMTConstant.ONE).getCell(LOMTConstant.THIRTEEN).getStringCellValue());*/
			
			//Function/Notion
			assertEquals(worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.TWENTY).getStringCellValue().replaceAll(" ", ""),
					worksheetDest.getRow(LOMTConstant.ONE).getCell(LOMTConstant.TWENTY).getStringCellValue().replaceAll(" ", ""));
			
			//Anchor
			assertEquals(worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.TWENTY_ONE).getStringCellValue(),
					worksheetDest.getRow(LOMTConstant.ONE).getCell(LOMTConstant.TWENTY_ONE).getStringCellValue());
			
			//Source Descriptor
			assertEquals(worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.TWENTY_TWO).getStringCellValue(),
					worksheetDest.getRow(LOMTConstant.ONE).getCell(LOMTConstant.TWENTY_TWO).getStringCellValue());
			
			//Source
			assertEquals(worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.TWENTY_THREE).getStringCellValue(),
					worksheetDest.getRow(LOMTConstant.ONE).getCell(LOMTConstant.TWENTY_THREE).getStringCellValue());
			
			//Estimated Level
			assertEquals(worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.TWENTY_FOUR).getStringCellValue(),
					worksheetDest.getRow(LOMTConstant.ONE).getCell(LOMTConstant.TWENTY_FOUR).getStringCellValue());

			//Notes
			assertEquals(worksheet.getRow(LOMTConstant.ONE).getCell(25).getStringCellValue(),
					worksheetDest.getRow(LOMTConstant.ONE).getCell(25).getStringCellValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void removeExistingFile() throws IOException {
		if (new File(LOMTConstant.EXPORTED_FILE_PATH).exists()) 
			FileUtils.cleanDirectory(new File(LOMTConstant.EXPORTED_FILE_PATH));
	}
	
	public boolean findDuplicateRecords(File file) {
		boolean flag = false;
		FileInputStream isFile = null;
		XSSFWorkbook workbook = null;
		XSSFSheet worksheet = null;
		try {
			isFile =  new FileInputStream(file);
			workbook = new XSSFWorkbook(isFile);
			worksheet = workbook.getSheetAt(1);
			
			Iterator<Row> rowItr = worksheet.iterator();
			List<String> list = new LinkedList<String>();
			while (rowItr.hasNext()) {
				Row row = rowItr.next();
				if (!(row.getRowNum() == 0)) {
					if (row.getCell(LOMTConstant.ONE)!=null) {
						String descId = row.getCell(LOMTConstant.ONE).getStringCellValue();
						if(list.contains(descId)) {
							flag = false;
							break;
						} else {
							list.add(descId);
							flag = true;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

}
