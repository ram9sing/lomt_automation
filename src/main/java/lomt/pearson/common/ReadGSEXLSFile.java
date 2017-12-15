package lomt.pearson.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;

import lomt.pearson.constant.LOMTConstant;

public class ReadGSEXLSFile {
	
	/**
	 * Export at GSE goalframework level
	 * @param actualFile
	 * @param exportedFile
	 * @throws IOException
	 */
	public void readGSEExportedFile(File actualFile, File exportedFile) throws IOException {
		InputStream exportedGSEExcelFileIS = null;
		XSSFWorkbook workbookForExportedGSEFile = null;
			exportedGSEExcelFileIS = new FileInputStream(exportedFile);
			workbookForExportedGSEFile = new XSSFWorkbook(exportedGSEExcelFileIS);
			XSSFSheet instructionsSheet = workbookForExportedGSEFile.getSheetAt(0);
			XSSFSheet dataSheet = workbookForExportedGSEFile.getSheetAt(1);

			Assert.assertEquals(LOMTConstant.INSTRUCTIONS, instructionsSheet.getSheetName());
			System.out.println(LOMTConstant.INSTRUCTIONS.equalsIgnoreCase(instructionsSheet.getSheetName()));

			Assert.assertEquals(LOMTConstant.DATA, dataSheet.getSheetName());
			System.out.println(LOMTConstant.DATA.equalsIgnoreCase(dataSheet.getSheetName()));
			
			Assert.assertNotNull(String.valueOf(dataSheet.getPhysicalNumberOfRows()));
			System.out.println("Total rows : " +String.valueOf(dataSheet.getPhysicalNumberOfRows()));

			Iterator<Row> rowIteratoreForExport = dataSheet.iterator();
			while (rowIteratoreForExport.hasNext()) {
				Row row = rowIteratoreForExport.next();
				if (row.getRowNum() == 0) {
					Assert.assertEquals(LOMTConstant.HEADER_URN, String.valueOf(row.getCell(LOMTConstant.ZERO)));

					Assert.assertEquals(LOMTConstant.HEADER_DESCRIPTIVE_ID, String.valueOf(row.getCell(LOMTConstant.ONE)));
					Assert.assertEquals(LOMTConstant.HEADER_DRAFT_IDS, String.valueOf(row.getCell(LOMTConstant.TWO)));
					Assert.assertEquals(LOMTConstant.HEADER_SYLLABUS, String.valueOf(row.getCell(LOMTConstant.THREE)));
					Assert.assertEquals(LOMTConstant.HEADER_BATCH, String.valueOf(row.getCell(LOMTConstant.FOUR)));
					Assert.assertEquals(LOMTConstant.HEADER_SKILL, String.valueOf(row.getCell(LOMTConstant.FIVE)));
					Assert.assertEquals(LOMTConstant.HEADER_STATUS, String.valueOf(row.getCell(LOMTConstant.SIX)));
					Assert.assertEquals(LOMTConstant.HEADER_DESCRIPTOR, String.valueOf(row.getCell(LOMTConstant.SEVEN)));
					Assert.assertEquals(LOMTConstant.HEADER_ATTRIBUTION, String.valueOf(row.getCell(LOMTConstant.EIGHT)));
					Assert.assertEquals(LOMTConstant.HEADER_GSE, String.valueOf(row.getCell(LOMTConstant.NINE)));
					Assert.assertEquals(LOMTConstant.HEADER_CERF_LEVEL, String.valueOf(row.getCell(LOMTConstant.TEN)));
					Assert.assertEquals(LOMTConstant.HEADER_COMMUNICATIVE_CATEGGORIES, String.valueOf(row.getCell(LOMTConstant.ELEVENTH)));
					Assert.assertEquals(LOMTConstant.HEADER_BUSINESS_SKILLS, String.valueOf(row.getCell(LOMTConstant.TWELEVE)));
					Assert.assertEquals(LOMTConstant.HEADER_TOPIC_L1, String.valueOf(row.getCell(LOMTConstant.THIRTEEN)));
					Assert.assertEquals(LOMTConstant.HEADER_YL_SIMPLIFIED, String.valueOf(row.getCell(LOMTConstant.FOURTEEN)));
					Assert.assertEquals(LOMTConstant.HEADER_STRUCTURE, String.valueOf(row.getCell(LOMTConstant.FIFTEEN)));
					Assert.assertEquals(LOMTConstant.HEADER_GRAMMATICAL_CATEGORIES, String.valueOf(row.getCell(LOMTConstant.SIXTEEN)));
					Assert.assertEquals(LOMTConstant.HEADER_EXAMPLE, String.valueOf(row.getCell(LOMTConstant.SEVENTEEN)));
					Assert.assertEquals(LOMTConstant.HEADER_VARIANT_TERMS, String.valueOf(row.getCell(LOMTConstant.EIGHTEEN)));
					Assert.assertEquals(LOMTConstant.HEADER_FUNCTION_NOTION, String.valueOf(row.getCell(LOMTConstant.NINETEEN)));
					Assert.assertEquals(LOMTConstant.HEADER_ANCHOR, String.valueOf(row.getCell(LOMTConstant.TWENTY)));
					Assert.assertEquals(LOMTConstant.HEADER_SOURCE_DESCRIPTOR, String.valueOf(row.getCell(LOMTConstant.TWENTY_ONE)));
					Assert.assertEquals(LOMTConstant.HEADER_SOURCE, String.valueOf(row.getCell(LOMTConstant.TWENTY_TWO)));
					Assert.assertEquals(LOMTConstant.HEADER_ESTIMATED_LEVEL, String.valueOf(row.getCell(LOMTConstant.TWENTY_THREE)));
					Assert.assertEquals(LOMTConstant.HEADER_NOTES, String.valueOf(row.getCell(LOMTConstant.TWENTY_FOUR)));
				} else {
					// Mandatory GSE fields start 
					if (!(row.getCell(0) == null) && !(row.getCell(0).getStringCellValue().equalsIgnoreCase(""))) {
						Assert.assertNotNull(LOMTConstant.HEADER_URN+LOMTConstant.NOT_NULL,row.getCell(0).getStringCellValue());
					}
					if (!(row.getCell(1) == null)) {
						Assert.assertNotNull(LOMTConstant.HEADER_DESCRIPTIVE_ID+LOMTConstant.NOT_NULL, row.getCell(1).getStringCellValue());
					}
					if (!(row.getCell(3) == null)) {
						Assert.assertNotNull(LOMTConstant.HEADER_SYLLABUS+LOMTConstant.NOT_NULL, row.getCell(3).getStringCellValue());
					}
					if (!(row.getCell(6) == null)) {
						Assert.assertNotNull(LOMTConstant.HEADER_STATUS+LOMTConstant.NOT_NULL, row.getCell(6).getStringCellValue());
					}
					if (!(row.getCell(7) == null)) {
						Assert.assertNotNull(LOMTConstant.HEADER_DESCRIPTOR+LOMTConstant.NOT_NULL, row.getCell(7).getStringCellValue());
						System.out.println("Descriptor : "+ String.valueOf(row.getCell(7).getStringCellValue()));
					}
					// Mandatory GSE fields end
					
					// Non-mandatory gse fields start
					if (!(row.getCell(LOMTConstant.TWO) == null)) {
						Assert.assertNotNull(row.getCell(LOMTConstant.TWO).getStringCellValue());
					} else {
						Assert.assertNull(row.getCell(LOMTConstant.TWO));
					}
					if (!(row.getCell(LOMTConstant.FOUR) == null)) {
						Assert.assertNotNull(row.getCell(LOMTConstant.FOUR).getStringCellValue());
					} else {
						Assert.assertNull(row.getCell(LOMTConstant.FOUR));
					}
					if (!(row.getCell(LOMTConstant.FIVE) == null)) {
						Assert.assertNotNull(row.getCell(LOMTConstant.FIVE).getStringCellValue());
					} else {
						Assert.assertNull(row.getCell(LOMTConstant.FIVE));
					}
					if (!(row.getCell(LOMTConstant.EIGHT) == null)) {
						Assert.assertNotNull(row.getCell(LOMTConstant.EIGHT).getStringCellValue());
					} else {
						Assert.assertNull(row.getCell(LOMTConstant.EIGHT));
					}
					if (!(row.getCell(LOMTConstant.NINE) == null)) {
						Assert.assertNotNull(row.getCell(LOMTConstant.NINE).getStringCellValue());
					} else {
						Assert.assertNull(row.getCell(LOMTConstant.NINE));
					}
					if (!(row.getCell(LOMTConstant.TEN) == null)) {
						Assert.assertNotNull(row.getCell(LOMTConstant.TEN).getStringCellValue());
					} else {
						Assert.assertNull(row.getCell(LOMTConstant.TEN));
					}
					if (!(row.getCell(LOMTConstant.ELEVENTH) == null)) {
						Assert.assertNotNull(row.getCell(LOMTConstant.ELEVENTH).getStringCellValue());
					} else {
						Assert.assertNull(row.getCell(LOMTConstant.ELEVENTH));
					}
					if (!(row.getCell(LOMTConstant.TWELEVE) == null)) {
						Assert.assertNotNull(row.getCell(LOMTConstant.TWELEVE).getStringCellValue());
					} else {
						Assert.assertNull(row.getCell(LOMTConstant.TWELEVE));
					}
					if (!(row.getCell(LOMTConstant.THIRTEEN) == null)) {
						Assert.assertNotNull(row.getCell(LOMTConstant.THIRTEEN).getStringCellValue());
					} else {
						Assert.assertNull(row.getCell(LOMTConstant.THIRTEEN));
					}
					if (!(row.getCell(LOMTConstant.FOURTEEN) == null)) {
						Assert.assertNotNull(row.getCell(LOMTConstant.FOURTEEN).getStringCellValue());
					} else {
						Assert.assertNull(row.getCell(LOMTConstant.FOURTEEN));
					}
					if (!(row.getCell(LOMTConstant.FIFTEEN) == null)) {
						Assert.assertNotNull(row.getCell(LOMTConstant.FIFTEEN).getStringCellValue());
					} else {
						Assert.assertNull(row.getCell(LOMTConstant.FIFTEEN));
					}
					if (!(row.getCell(LOMTConstant.SIXTEEN) == null)) {
						Assert.assertNotNull(row.getCell(LOMTConstant.SIXTEEN).getStringCellValue());
					} else {
						Assert.assertNull(row.getCell(LOMTConstant.SIXTEEN));
					}
					if (!(row.getCell(LOMTConstant.SEVENTEEN) == null)) {
						Assert.assertNotNull(row.getCell(LOMTConstant.SEVENTEEN).getStringCellValue());
					} else {
						Assert.assertNull(row.getCell(LOMTConstant.SEVENTEEN));
					}
					if (!(row.getCell(LOMTConstant.EIGHTEEN) == null)) {
						Assert.assertNotNull(row.getCell(LOMTConstant.EIGHTEEN).getStringCellValue());
					} else {
						Assert.assertNull(row.getCell(LOMTConstant.EIGHTEEN));
					}
					if (!(row.getCell(LOMTConstant.NINETEEN) == null)) {
						Assert.assertNotNull(row.getCell(LOMTConstant.NINETEEN).getStringCellValue());
					} else {
						Assert.assertNull(row.getCell(LOMTConstant.NINETEEN));
					}
					if (!(row.getCell(LOMTConstant.TWENTY) == null)) {
						Assert.assertNotNull(row.getCell(LOMTConstant.TWENTY).getStringCellValue());
					} else {
						Assert.assertNull(row.getCell(LOMTConstant.TWENTY));
					}
					if (!(row.getCell(LOMTConstant.TWENTY_ONE) == null)) {
						Assert.assertNotNull(row.getCell(LOMTConstant.TWENTY_ONE).getStringCellValue());
					} else {
						Assert.assertNull(row.getCell(LOMTConstant.TWENTY_ONE));
					}
					if (!(row.getCell(LOMTConstant.TWENTY_TWO) == null)) {
						Assert.assertNotNull(row.getCell(LOMTConstant.TWENTY_TWO).getStringCellValue());
					} else {
						Assert.assertNull(row.getCell(LOMTConstant.TWENTY_TWO));
					}
					if (!(row.getCell(LOMTConstant.TWENTY_THREE) == null)) {
						Assert.assertNotNull(row.getCell(LOMTConstant.TWENTY_THREE).getStringCellValue());
					} else {
						Assert.assertNull(row.getCell(LOMTConstant.TWENTY_THREE));
					}
					if (!(row.getCell(LOMTConstant.TWENTY_FOUR) == null)) {
						Assert.assertNotNull(row.getCell(LOMTConstant.TWENTY_FOUR).getStringCellValue());
					} else {
						Assert.assertNull(row.getCell(LOMTConstant.TWENTY_FOUR));
					}
					// Non-mandatory GSE fields end
					
					
					//Negative Scenario start
					/*if (row.getCell(3).getStringCellValue().equalsIgnoreCase(GL)) { 
						Assert.assertNotNull(HEADER_COMMUNICATIVE_CATEGGORIES+NOT_NULL, row.getCell(11).getStringCellValue());
					}
					if (row.getCell(3).getStringCellValue().equalsIgnoreCase(PL)) { 
						Assert.assertNotNull(HEADER_BUSINESS_SKILLS+NOT_NULL, row.getCell(12).getStringCellValue());
					}
					if (row.getCell(3).getStringCellValue().equalsIgnoreCase(GL+COMMA+PL)) {  // need to apply OR condition bcz it can be like PL,GL
						Assert.assertNotNull(HEADER_BUSINESS_SKILLS+NOT_NULL, row.getCell(12).getStringCellValue());
						
						Assert.assertNotNull(HEADER_YL_SIMPLIFIED+NOT_NULL, row.getCell(14).getStringCellValue());
					}
					if (row.getCell(3).getStringCellValue().equalsIgnoreCase(YL)) { 
						Assert.assertNotNull(HEADER_YL_SIMPLIFIED+NOT_NULL, row.getCell(14).getStringCellValue());
					}
					if (row.getCell(3).getStringCellValue().equalsIgnoreCase(GR+COMMA+GL)) { 
						Assert.assertNotNull(HEADER_COMMUNICATIVE_CATEGGORIES+NOT_NULL, row.getCell(11).getStringCellValue());
						
						Assert.assertNotNull(HEADER_GRAMMATICAL_CATEGORIES+NOT_NULL, row.getCell(16).getStringCellValue());
					}
					if (row.getCell(3).getStringCellValue().equalsIgnoreCase(VC+COMMA+GL)) { 
						Assert.assertNotNull(HEADER_COMMUNICATIVE_CATEGGORIES+NOT_NULL, row.getCell(11).getStringCellValue());
						
						Assert.assertNotNull(HEADER_TOPIC_L1+NOT_NULL, row.getCell(13).getStringCellValue());
					}*/
					//Negative Scenario end
			}
		}
	}
	
	/**
	 * GSE export at Educational goal framework level
	 * @param actualFile
	 * @param exportedFile
	 * @throws IOException
	 */
	public void readGSEExportedFileEducationGoalFramework(File actualFile, File exportedFile) throws IOException {
		InputStream exportedGSEExcelFileIS = null;
		XSSFWorkbook workbookForExportedGSEFile = null;
		
		InputStream actualGSEExcelFileIS = null;
		XSSFWorkbook actualGSEFile = null;
		
		actualGSEExcelFileIS = new FileInputStream(actualFile);
		actualGSEFile = new XSSFWorkbook(actualGSEExcelFileIS);
		XSSFSheet actualDataSheet = actualGSEFile.getSheetAt(1);
		
		exportedGSEExcelFileIS = new FileInputStream(exportedFile);
		workbookForExportedGSEFile = new XSSFWorkbook(exportedGSEExcelFileIS);
		
		XSSFSheet instructionsSheet = workbookForExportedGSEFile.getSheetAt(0);
		XSSFSheet dataSheet = workbookForExportedGSEFile.getSheetAt(1);

		Assert.assertEquals(LOMTConstant.INSTRUCTIONS, instructionsSheet.getSheetName());
		Assert.assertEquals(LOMTConstant.DATA, dataSheet.getSheetName());		
		Assert.assertNotNull(String.valueOf(dataSheet.getPhysicalNumberOfRows()));
		
		// for data comparision, actual sheet
		String urn = null;
		String descriptiveId = null;
		String draftId = null;
		String syllabus = null;
		String batch = null;
		String skill = null;
		String status = null;
		String descriptor = null;
		String attribution = null;
		String gse = null;
		String crefLevel = null;
		String communicativeCategories = null;
		String businessSkills = null;
		String sourceDescriptor = null;
		String source = null;
		String notes = null;
		
		Iterator<Row> rowIteratoreForActual = actualDataSheet.iterator();
		while (rowIteratoreForActual.hasNext()) {
			Row row = rowIteratoreForActual.next();
			if (!(row.getRowNum() == 0)) {
					urn = String.valueOf(row.getCell(LOMTConstant.ZERO));
					descriptiveId = String.valueOf(row.getCell(LOMTConstant.ONE));
					draftId = String.valueOf(row.getCell(LOMTConstant.TWO));
					syllabus = String.valueOf(row.getCell(LOMTConstant.THREE));
					batch = String.valueOf(row.getCell(LOMTConstant.FOUR));
					skill = String.valueOf(row.getCell(LOMTConstant.FIVE));
					status = String.valueOf(row.getCell(LOMTConstant.SIX));
					descriptor = String.valueOf(row.getCell(LOMTConstant.SEVEN));
					attribution = String.valueOf(row.getCell(LOMTConstant.EIGHT));
					gse = String.valueOf(row.getCell(LOMTConstant.NINE));
					crefLevel = String.valueOf(row.getCell(LOMTConstant.TEN));
					communicativeCategories = String.valueOf(row.getCell(LOMTConstant.ELEVENTH));
					businessSkills = String.valueOf(row.getCell(LOMTConstant.TWELEVE));
					sourceDescriptor = String.valueOf(row.getCell(LOMTConstant.TWENTY_ONE));
					source  = String.valueOf(row.getCell(LOMTConstant.TWENTY_TWO));
					notes = String.valueOf(row.getCell(LOMTConstant.TWENTY_FOUR));
			}
		}
		
		// exported sheet
		Iterator<Row> rowIteratoreForExport = dataSheet.iterator();
		while (rowIteratoreForExport.hasNext()) {
			Row row = rowIteratoreForExport.next();
			if (row.getRowNum() == 0) {
				//CHECKING HEADER VALUES
				Assert.assertEquals(LOMTConstant.HEADER_URN, String.valueOf(row.getCell(LOMTConstant.ZERO)));
				Assert.assertEquals(LOMTConstant.HEADER_DESCRIPTIVE_ID, String.valueOf(row.getCell(LOMTConstant.ONE)));
				Assert.assertEquals(LOMTConstant.HEADER_DRAFT_IDS, String.valueOf(row.getCell(LOMTConstant.TWO)));
				Assert.assertEquals(LOMTConstant.HEADER_SYLLABUS, String.valueOf(row.getCell(LOMTConstant.THREE)));
				Assert.assertEquals(LOMTConstant.HEADER_BATCH, String.valueOf(row.getCell(LOMTConstant.FOUR)));
				Assert.assertEquals(LOMTConstant.HEADER_SKILL, String.valueOf(row.getCell(LOMTConstant.FIVE)));
				Assert.assertEquals(LOMTConstant.HEADER_STATUS, String.valueOf(row.getCell(LOMTConstant.SIX)));
				Assert.assertEquals(LOMTConstant.HEADER_DESCRIPTOR, String.valueOf(row.getCell(LOMTConstant.SEVEN)));
				Assert.assertEquals(LOMTConstant.HEADER_ATTRIBUTION, String.valueOf(row.getCell(LOMTConstant.EIGHT)));
				Assert.assertEquals(LOMTConstant.HEADER_GSE, String.valueOf(row.getCell(LOMTConstant.NINE)));
				Assert.assertEquals(LOMTConstant.HEADER_CERF_LEVEL, String.valueOf(row.getCell(LOMTConstant.TEN)));
				Assert.assertEquals(LOMTConstant.HEADER_COMMUNICATIVE_CATEGGORIES, String.valueOf(row.getCell(LOMTConstant.ELEVENTH)));
				Assert.assertEquals(LOMTConstant.HEADER_BUSINESS_SKILLS, String.valueOf(row.getCell(LOMTConstant.TWELEVE)));
				Assert.assertEquals(LOMTConstant.HEADER_TOPIC_L1, String.valueOf(row.getCell(LOMTConstant.THIRTEEN)));
				Assert.assertEquals(LOMTConstant.HEADER_YL_SIMPLIFIED, String.valueOf(row.getCell(LOMTConstant.FOURTEEN)));
				Assert.assertEquals(LOMTConstant.HEADER_STRUCTURE, String.valueOf(row.getCell(LOMTConstant.FIFTEEN)));
				Assert.assertEquals(LOMTConstant.HEADER_GRAMMATICAL_CATEGORIES, String.valueOf(row.getCell(LOMTConstant.SIXTEEN)));
				Assert.assertEquals(LOMTConstant.HEADER_EXAMPLE, String.valueOf(row.getCell(LOMTConstant.SEVENTEEN)));
				Assert.assertEquals(LOMTConstant.HEADER_VARIANT_TERMS, String.valueOf(row.getCell(LOMTConstant.EIGHTEEN)));
				Assert.assertEquals(LOMTConstant.HEADER_FUNCTION_NOTION, String.valueOf(row.getCell(LOMTConstant.NINETEEN)));
				Assert.assertEquals(LOMTConstant.HEADER_ANCHOR, String.valueOf(row.getCell(LOMTConstant.TWENTY)));
				Assert.assertEquals(LOMTConstant.HEADER_SOURCE_DESCRIPTOR, String.valueOf(row.getCell(LOMTConstant.TWENTY_ONE)));
				Assert.assertEquals(LOMTConstant.HEADER_SOURCE, String.valueOf(row.getCell(LOMTConstant.TWENTY_TWO)));
				Assert.assertEquals(LOMTConstant.HEADER_ESTIMATED_LEVEL, String.valueOf(row.getCell(LOMTConstant.TWENTY_THREE)));
				Assert.assertEquals(LOMTConstant.HEADER_NOTES, String.valueOf(row.getCell(LOMTConstant.TWENTY_FOUR)));
			} else {
				// Mandatory GSE fields START 
				if (!(row.getCell(0) == null) && !(row.getCell(0).getStringCellValue().equalsIgnoreCase(""))) {
					Assert.assertNotNull(LOMTConstant.HEADER_URN+LOMTConstant.NOT_NULL,row.getCell(0).getStringCellValue());
				}
				if (!(row.getCell(1) == null)) {
					Assert.assertNotNull(LOMTConstant.HEADER_DESCRIPTIVE_ID+LOMTConstant.NOT_NULL, row.getCell(1).getStringCellValue());
				}
				if (!(row.getCell(3) == null)) {
					Assert.assertNotNull(LOMTConstant.HEADER_SYLLABUS+LOMTConstant.NOT_NULL, row.getCell(3).getStringCellValue());
				}
				if (!(row.getCell(6) == null)) {
					Assert.assertNotNull(LOMTConstant.HEADER_STATUS+LOMTConstant.NOT_NULL, row.getCell(6).getStringCellValue());
				}
				if (!(row.getCell(7) == null)) {
					Assert.assertNotNull(LOMTConstant.HEADER_DESCRIPTOR+LOMTConstant.NOT_NULL, row.getCell(7).getStringCellValue());
				}
				// Mandatory GSE fields END
				
				// Data comparison between actual and export file START
				if (!(row.getCell(0) == null) && !urn.isEmpty()) {
					Assert.assertEquals(urn, row.getCell(0).getStringCellValue());
				}
				if (!(row.getCell(1) == null) && !descriptiveId.isEmpty()) {
					Assert.assertEquals(descriptiveId, row.getCell(1).getStringCellValue());
				}
				if (!(row.getCell(2) == null) && !draftId.isEmpty()) {
					Assert.assertEquals(draftId, row.getCell(2).getStringCellValue());
				}
				if (!(row.getCell(3) == null) && !syllabus.isEmpty()) {
					Assert.assertEquals(syllabus, row.getCell(3).getStringCellValue());
				}
				if (!(row.getCell(4) == null) && !batch.isEmpty()) {
					Assert.assertEquals(batch, row.getCell(4).getStringCellValue());
				}
				if (!(row.getCell(5) == null) && !skill.isEmpty()) {
					Assert.assertEquals(skill, row.getCell(5).getStringCellValue());
				}
				if (!(row.getCell(6) == null) && !status.isEmpty()) {
					Assert.assertEquals(status, row.getCell(6).getStringCellValue());
				}
				if (!(row.getCell(7) == null) && !descriptor.isEmpty()) {
					Assert.assertEquals(descriptor, row.getCell(7).getStringCellValue());
				}
				if (!(row.getCell(8) == null) && !attribution.isEmpty()) {
					Assert.assertEquals(attribution, row.getCell(8).getStringCellValue());
				}
				if (!(row.getCell(9) == null) && !gse.isEmpty()) {
					Assert.assertEquals(gse, row.getCell(9).getStringCellValue());
				}
				if (!(row.getCell(10) == null) && !crefLevel.isEmpty()) {
					Assert.assertEquals(crefLevel, row.getCell(10).getStringCellValue());
				}
				if (!(row.getCell(11) == null) && !communicativeCategories.isEmpty()) {
					Assert.assertEquals(communicativeCategories, row.getCell(11).getStringCellValue());
				}
				if (!(row.getCell(12) == null) && !businessSkills.isEmpty()) {
					Assert.assertEquals(businessSkills, row.getCell(12).getStringCellValue());
				}
				if (!(row.getCell(21) == null) && !sourceDescriptor.isEmpty()) {
					Assert.assertEquals(sourceDescriptor, row.getCell(21).getStringCellValue());
				}
				if (!(row.getCell(22) == null) && !source.isEmpty()) {
					Assert.assertEquals(source, row.getCell(22).getStringCellValue());
				}
				if (!(row.getCell(23) == null) && !notes.isEmpty()) {
					Assert.assertEquals(notes, row.getCell(24).getStringCellValue());
				}
				// Data comparison between actual and export file END
			}
		}
	}
	
	/**
	 * It returns descriptive id from Actual sheet
	 * @return
	 * @throws IOException 
	 */
	public /*List<String>*/String getDescriptor(File actualFile) throws IOException {
		InputStream inputStream = new FileInputStream(actualFile);
		XSSFWorkbook xssFWorkbook = new XSSFWorkbook(inputStream);
		XSSFSheet actualDataSheet = xssFWorkbook.getSheetAt(1);
		String descriptor = null;
		int counter = 0;
		//List<String> list = new LinkedList<String>();
		
		Iterator<Row> rowIteratoreForActual = actualDataSheet.iterator();
		while (rowIteratoreForActual.hasNext()) {
			Row row = rowIteratoreForActual.next();
			if (!(row.getRowNum() == 0) /*&& counter <5*/) {
				if (String.valueOf(row.getCell(LOMTConstant.ONE)) != null) {
					descriptor = String.valueOf(row.getCell(LOMTConstant.ONE));
					break;
					//list.add(descriptor);
					//counter++;
				}
			}
		}
		return descriptor;
	}
}
