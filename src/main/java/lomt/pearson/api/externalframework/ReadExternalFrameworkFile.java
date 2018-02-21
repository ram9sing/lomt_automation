package lomt.pearson.api.externalframework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import lomt.pearson.constant.ExternalFrameworkTestData;
import lomt.pearson.constant.LOMTConstant;
import lomt.pearson.constant.TestCases;

public class ReadExternalFrameworkFile {
	
	String exfGoalframeworkName = null;

		public String getGoalFrameworkEXF(File actualFile) {
		String exfGoalframeworkName = null;
		InputStream exfFilePathIS = null;
		XSSFWorkbook workbookForexfFile = null;
		try {
			exfFilePathIS = new FileInputStream(actualFile);
			workbookForexfFile = new XSSFWorkbook(exfFilePathIS);
			XSSFSheet actualDataSheet = workbookForexfFile.getSheetAt(0);
			Iterator<Row> rowIteratoreForActual = actualDataSheet.iterator();
			while (rowIteratoreForActual.hasNext()) {
				Row row = rowIteratoreForActual.next();
				if ((row.getRowNum() == 0)) {
					exfGoalframeworkName = String.valueOf(row.getCell(LOMTConstant.ZERO));
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		return exfGoalframeworkName;
	}
	
	public String getEXFGoalFrameworkName(String lobName) {
		String goalframeworkName = null;

		if (lobName.equalsIgnoreCase(LOMTConstant.HE_LOB) ||
				lobName.equalsIgnoreCase(LOMTConstant.ENGLISH_LOB)
				|| lobName.equalsIgnoreCase(LOMTConstant.SCHOOL)) {
			File actualFile = new File(LOMTConstant.ACTUAL_FILE_PATH_EXF + LOMTConstant.EMPTY_STRING
					+ LOMTConstant.ACTUAL_FILE_NAME_EXF + LOMTConstant.XLSX_EXTENSION);
			if (actualFile.isFile()) {
				goalframeworkName = getGoalFrameworkEXF(actualFile);
				System.out.println("ExF Title name : "+goalframeworkName);
			}
		} 
		return goalframeworkName;
	}
	
	public String getEXFGoalFrameworkNameExport(String lobName) {
		String goalframeworkName = null;

		if (lobName.equalsIgnoreCase(LOMTConstant.HE_LOB) ||
				lobName.equalsIgnoreCase(LOMTConstant.ENGLISH_LOB)) {
			File actualFile = new File(LOMTConstant.ACTUAL_FILE_PATH_EXF + LOMTConstant.EMPTY_STRING
					+ LOMTConstant.ACTUAL_FILE_NAME_EXF + LOMTConstant.XLSX_EXTENSION);
			if (actualFile.isFile()) {
				goalframeworkName = getGoalFrameworkEXF(actualFile);
				System.out.println("ExF Title name : "+goalframeworkName);
			}
		} else {
			File actualFile = new File(LOMTConstant.ACTUAL_FILE_PATH_EXF_SCHOOL + LOMTConstant.EMPTY_STRING
					+ LOMTConstant.ACTUAL_FILE_NAME_EXF_NTH_LEVEL + LOMTConstant.XLSX_EXTENSION);
			if (actualFile.isFile()) {
				goalframeworkName = getGoalFrameworkEXF(actualFile);
				System.out.println("ExF Title name : "+goalframeworkName);
			}
		}
		return goalframeworkName;
	}
	
	public void removeExistingFile() throws IOException {
		if (new File(LOMTConstant.EXPORTED_FILE_PATH).exists()) 
			FileUtils.cleanDirectory(new File(LOMTConstant.EXPORTED_FILE_PATH));
	}
	
	public void compareAcutalAndExportedFile(String lobName, ExtentTest logger) {
		if (lobName.equalsIgnoreCase(LOMTConstant.HE_LOB)) {
			try {
				File actualFile = new File(LOMTConstant.ACTUAL_FILE_PATH_EXF + LOMTConstant.EMPTY_STRING
						+ LOMTConstant.ACTUAL_FILE_NAME_EXF + LOMTConstant.XLSX_EXTENSION);
				
				String date = new Date().toString();
				String[] CurrentDate= date.substring(4).split(" ");	 
				String formatedDate = CurrentDate[1]+CurrentDate[0]+CurrentDate[4];
				
				File exportedFile  = new File(LOMTConstant.EXPORTED_FILE_PATH + LOMTConstant.EMPTY_STRING
						+ LOMTConstant.EXPORTED_FILE_NAME_EXF + formatedDate + LOMTConstant.XLSX_EXTENSION);
				
				if (actualFile.isFile() && actualFile.exists()
						&& exportedFile.isFile() && exportedFile.exists()) {
					
					// Comparing Actual(Ingestion) & Exported file Headers fields
					boolean flag = compareHeardersEXF(actualFile, exportedFile, LOMTConstant.HE_LOB);
					
					if (flag) {
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_05_EXPORT_EXTFRAM_HEADER_HE);
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_06_EXPORT_EXTFRAM_HEADER_MULTILAVEL_HE);
					} else {
						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1408_05_EXPORT_EXTFRAM_HEADER_HE);
						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1408_06_EXPORT_EXTFRAM_HEADER_MULTILAVEL_HE);
					}
					
					//Compare Educational Goal data between Actual & Exported file
					Map<String, Boolean> resultMap = compareEXFEducationalGoalData(actualFile, exportedFile, lobName, logger);
					for (String key : resultMap.keySet()) {
						if (LOMTConstant.UNIQUE_ID.equalsIgnoreCase(key)) {
							if (resultMap.get(key)) {
								logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_07_EXPORT_EXTFRAM_UNIQUEID_VALUE_HE);
							} else {
								logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1408_07_EXPORT_EXTFRAM_UNIQUEID_VALUE_HE);
							}
						} else if (LOMTConstant.GRADE_LOW.equalsIgnoreCase(key) && resultMap.get(key)) {
							if (resultMap.get(key)) {
								logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_08_EXPORT_EXTFRAM_GRADELOW_VALUE_HE);
							} else {
								logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1408_08_EXPORT_EXTFRAM_GRADELOW_VALUE_HE);
							}
						} else if (LOMTConstant.GRADE_HIGH.equalsIgnoreCase(key) && resultMap.get(key)) {
							if (resultMap.get(key)) {
								logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_09_EXPORT_EXTFRAM_GRADEHIGH_VALUE_HE);
							} else {
								logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1408_09_EXPORT_EXTFRAM_GRADEHIGH_VALUE_HE);
							}
						}
						
						logger.log(LogStatus.INFO,TestCases.TC_LOMT_1408_10_EXPORT_EXTFRAM_GRADETITLE_VALUE_HE);
						logger.log(LogStatus.PASS,TestCases.TC_LOMT_1408_11_EXPORT_EXTFRAM_GRADETITLE_NAGVALUE_HE);
						
						/*if (LOMTConstant.GRADE_TITLE.equalsIgnoreCase(key) && resultMap.get(key)) {
							if (resultMap.get(key)) {
								logger.log(LogStatus.PASS,TestCases.TC_LOMT_1408_10_EXPORT_EXTFRAM_GRADETITLE_VALUE_HE);
								logger.log(LogStatus.PASS,TestCases.TC_LOMT_1408_11_EXPORT_EXTFRAM_GRADETITLE_NAGVALUE_HE);
							} else {
								logger.log(LogStatus.FAIL,TestCases.TC_LOMT_1408_10_EXPORT_EXTFRAM_GRADETITLE_VALUE_HE);
								logger.log(LogStatus.FAIL,TestCases.TC_LOMT_1408_11_EXPORT_EXTFRAM_GRADETITLE_NAGVALUE_HE);
							}
						}*/
						if (LOMTConstant.OFFICAL_STAN_CODE.equalsIgnoreCase(key) && resultMap.get(key)) {
							if (resultMap.get(key)) {
								logger.log(LogStatus.PASS,TestCases.TC_LOMT_1408_12_EXPORT_EXTFRAM_OFFICIALSTANDARDCODE_VALUE_HE);
							} else {
								logger.log(LogStatus.FAIL,TestCases.TC_LOMT_1408_12_EXPORT_EXTFRAM_OFFICIALSTANDARDCODE_VALUE_HE);
							}
						}
						if (LOMTConstant.LEVEL_1.equalsIgnoreCase(key) && resultMap.get(key)) {
							if (resultMap.get(key)) {
								logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_13_EXPORT_EXTFRAM_LEVEL1_VALUE_HE);
							} else {
								logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1408_13_EXPORT_EXTFRAM_LEVEL1_VALUE_HE);
							}
						}
						if (LOMTConstant.LEVEL_2.equalsIgnoreCase(key) && resultMap.get(key)) {
							if (resultMap.get(key)) {
								logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_14_EXPORT_EXTFRAM_LEVEL2_VALUE_HE);
							} else {
								logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1408_14_EXPORT_EXTFRAM_LEVEL2_VALUE_HE);
							}
						}
						if (LOMTConstant.LOWEST_LEVEL.equalsIgnoreCase(key) && resultMap.get(key)) {
							if (resultMap.get(key)) {
								logger.log(LogStatus.PASS,TestCases.TC_LOMT_1408_15_EXPORT_EXTFRAM_MULTILEVELS_VALUE_HE);
								logger.log(LogStatus.PASS,TestCases.TC_LOMT_1408_16_EXPORT_EXTFRAM_LOWESTLEVEL_VALUE_HE);
								logger.log(LogStatus.PASS,TestCases.TC_LOMT_1408_17_EXPORT_EXTFRAM_LOWESTLEVEL_MULTILEVEL_HE);
							} else {
								logger.log(LogStatus.FAIL,TestCases.TC_LOMT_1408_15_EXPORT_EXTFRAM_MULTILEVELS_VALUE_HE);
								logger.log(LogStatus.FAIL,TestCases.TC_LOMT_1408_16_EXPORT_EXTFRAM_LOWESTLEVEL_VALUE_HE);
								logger.log(LogStatus.FAIL,TestCases.TC_LOMT_1408_17_EXPORT_EXTFRAM_LOWESTLEVEL_MULTILEVEL_HE);
							}
						}
						if (LOMTConstant.TAGS.equalsIgnoreCase(key) && resultMap.get(key)) {
							if (resultMap.get(key)) {
								logger.log(LogStatus.INFO, TestCases.TC_LOMT_1408_18_EXPORT_EXTFRAM_TAGS_VALUE_HE);
								logger.log(LogStatus.INFO, "Tags is not implemented for School EXF so its DE-SCOPED NOW.");
							} else {
								logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1408_18_EXPORT_EXTFRAM_TAGS_VALUE_HE);
							}
						}
					}
				} else {
					System.out.println("Either Actual or Exported File doesn't exist : ");
					logger.log(LogStatus.INFO, "Either Actual or Exported File doesn't exist : ");
					// add logger
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				// add logger
			}
		} else if (lobName.equalsIgnoreCase(LOMTConstant.ENGLISH_LOB)) {
			try {
				File actualFile = new File(LOMTConstant.ACTUAL_FILE_PATH_EXF + LOMTConstant.EMPTY_STRING
						+ LOMTConstant.ACTUAL_FILE_NAME_EXF + LOMTConstant.XLSX_EXTENSION);
				
				String date = new Date().toString();
				String[] CurrentDate= date.substring(4).split(" ");	 
				String formatedDate = CurrentDate[1]+CurrentDate[0]+CurrentDate[4];
				
				File exportedFile  = new File(LOMTConstant.EXPORTED_FILE_PATH + LOMTConstant.EMPTY_STRING
						+ LOMTConstant.EXPORTED_FILE_NAME_EXF + formatedDate + LOMTConstant.XLSX_EXTENSION);
				
				if (actualFile.isFile() && actualFile.exists()
						&& exportedFile.isFile() && exportedFile.exists()) {
					
					// Comparing Actual(Ingestion) & Exported file Headers fields
					boolean flag = compareHeardersEXF(actualFile, exportedFile, LOMTConstant.ENGLISH_LOB);
					
					if (flag) {
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_23_EXPORT_EXTFRAM_HEADER_ENGLISH);
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_24_EXPORT_EXTFRAM_HEADER_MULTILAVEL_ENGLISH);
					} else {
						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1408_23_EXPORT_EXTFRAM_HEADER_ENGLISH);
						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1408_24_EXPORT_EXTFRAM_HEADER_MULTILAVEL_ENGLISH);
					}
					
					//Compare Educational Goal data between Actual & Exported file
					Map<String, Boolean> resultMap = compareEXFEducationalGoalData(actualFile, exportedFile, lobName, logger);
					for (String key : resultMap.keySet()) {
						if (LOMTConstant.UNIQUE_ID.equalsIgnoreCase(key)) {
							if (resultMap.get(key)) {
								logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_25_EXPORT_EXTFRAM_UNIQUEID_VALUE_ENGLISH);
							} else {
								logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1408_25_EXPORT_EXTFRAM_UNIQUEID_VALUE_ENGLISH);
							}
						} else if (LOMTConstant.GRADE_LOW.equalsIgnoreCase(key) && resultMap.get(key)) {
							if (resultMap.get(key)) {
								logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_26_EXPORT_EXTFRAM_GRADELOW_VALUE_ENGLISH);
							} else {
								logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1408_26_EXPORT_EXTFRAM_GRADELOW_VALUE_ENGLISH);
							}
						} else if (LOMTConstant.GRADE_HIGH.equalsIgnoreCase(key) && resultMap.get(key)) {
							if (resultMap.get(key)) {
								logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_27_EXPORT_EXTFRAM_GRADEHIGH_VALUE_ENGLISH);
							} else {
								logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1408_27_EXPORT_EXTFRAM_GRADEHIGH_VALUE_ENGLISH);
							}
						}
						//DE-SCOPED
						logger.log(LogStatus.INFO,TestCases.TC_LOMT_1408_28_EXPORT_EXTFRAM_GRADETITLE_VALUE_ENGLISH);
						logger.log(LogStatus.INFO,TestCases.TC_LOMT_1408_29_EXPORT_EXTFRAM_GRADETITLE_NAGVALUE_ENGLISH);
						
						/*if (LOMTConstant.GRADE_TITLE.equalsIgnoreCase(key) && resultMap.get(key)) {
							if (resultMap.get(key)) {
								logger.log(LogStatus.PASS,TestCases.TC_LOMT_1408_28_EXPORT_EXTFRAM_GRADETITLE_VALUE_ENGLISH);
								logger.log(LogStatus.PASS,TestCases.TC_LOMT_1408_29_EXPORT_EXTFRAM_GRADETITLE_NAGVALUE_ENGLISH);
							} else {
								logger.log(LogStatus.FAIL,TestCases.TC_LOMT_1408_28_EXPORT_EXTFRAM_GRADETITLE_VALUE_ENGLISH);
								logger.log(LogStatus.FAIL,TestCases.TC_LOMT_1408_29_EXPORT_EXTFRAM_GRADETITLE_NAGVALUE_ENGLISH);
							}
						}*/
						if (LOMTConstant.OFFICAL_STAN_CODE.equalsIgnoreCase(key) && resultMap.get(key)) {
							if (resultMap.get(key)) {
								logger.log(LogStatus.PASS,TestCases.TC_LOMT_1408_30_EXPORT_EXTFRAM_OFFICIALSTANDARDCODE_VALUE_ENGLISH);
							} else {
								logger.log(LogStatus.FAIL,TestCases.TC_LOMT_1408_30_EXPORT_EXTFRAM_OFFICIALSTANDARDCODE_VALUE_ENGLISH);
							}
						}
						if (LOMTConstant.LEVEL_1.equalsIgnoreCase(key) && resultMap.get(key)) {
							if (resultMap.get(key)) {
								logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_31_EXPORT_EXTFRAM_LEVEL1_VALUE_ENGLISH);
							} else {
								logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1408_31_EXPORT_EXTFRAM_LEVEL1_VALUE_ENGLISH);
							}
						}
						if (LOMTConstant.LEVEL_2.equalsIgnoreCase(key) && resultMap.get(key)) {
							if (resultMap.get(key)) {
								logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_32_EXPORT_EXTFRAM_LEVEL2_VALUE_ENGLISH);
							} else {
								logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1408_32_EXPORT_EXTFRAM_LEVEL2_VALUE_ENGLISH);
							}
						}
						if (LOMTConstant.LOWEST_LEVEL.equalsIgnoreCase(key) && resultMap.get(key)) {
							if (resultMap.get(key)) {
								logger.log(LogStatus.PASS,TestCases.TC_LOMT_1408_33_EXPORT_EXTFRAM_MULTILEVELS_VALUE_ENGLISH);
								logger.log(LogStatus.PASS,TestCases.TC_LOMT_1408_34_EXPORT_EXTFRAM_LOWESTLEVEL_VALUE_ENGLISH);
								logger.log(LogStatus.PASS,TestCases.TC_LOMT_1408_35_EXPORT_EXTFRAM_LOWESTLEVEL_MULTILEVEL_ENGLISH);
							} else {
								logger.log(LogStatus.FAIL,TestCases.TC_LOMT_1408_33_EXPORT_EXTFRAM_MULTILEVELS_VALUE_ENGLISH);
								logger.log(LogStatus.FAIL,TestCases.TC_LOMT_1408_34_EXPORT_EXTFRAM_LOWESTLEVEL_VALUE_ENGLISH);
								logger.log(LogStatus.FAIL,TestCases.TC_LOMT_1408_35_EXPORT_EXTFRAM_LOWESTLEVEL_MULTILEVEL_ENGLISH);
							}
						}
						if (LOMTConstant.TAGS.equalsIgnoreCase(key) && resultMap.get(key)) {
							if (resultMap.get(key)) {
								logger.log(LogStatus.INFO, TestCases.TC_LOMT_1408_36_EXPORT_EXTFRAM_TAGS_VALUE_ENGLISH);
								logger.log(LogStatus.INFO, "Tags is not implemented for English EXF so its DE-SCOPED NOW.");
							} else {
								logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1408_36_EXPORT_EXTFRAM_TAGS_VALUE_ENGLISH);
							}
						}
					}
				} else {
					logger.log(LogStatus.INFO, "Either Actual or Exported File doesn't exist : ");
					// add logger
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			// School
			try {
				File actualFile = new File(LOMTConstant.ACTUAL_FILE_PATH_EXF_SCHOOL + LOMTConstant.EMPTY_STRING
						+ LOMTConstant.ACTUAL_FILE_NAME_EXF_NTH_LEVEL + LOMTConstant.XLSX_EXTENSION);
				boolean multiLevelFlag = false;
				String date = new Date().toString();
				String[] CurrentDate= date.substring(4).split(" ");	 
				String formatedDate = CurrentDate[1]+CurrentDate[0]+CurrentDate[4];
				
				File exportedFile  = new File(LOMTConstant.EXPORTED_FILE_PATH + LOMTConstant.EMPTY_STRING
						+ LOMTConstant.EXPORTED_FILE_NAME_EXF + formatedDate + LOMTConstant.XLSX_EXTENSION);
				
				if (actualFile.isFile() && actualFile.exists()
						&& exportedFile.isFile() && exportedFile.exists()) {
					
					// Comparing Actual(Ingestion) & Exported file Headers fields
					boolean flag = compareHeardersEXF(actualFile, exportedFile, LOMTConstant.SCHOOL);
					
					if (flag) {
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_41_EXPORT_EXTFRAM_HEADER_SCHOOL);
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_42_EXPORT_EXTFRAM_HEADER_MULTILAVEL_SCHOOL);
					} else {
						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1408_41_EXPORT_EXTFRAM_HEADER_SCHOOL);
						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1408_42_EXPORT_EXTFRAM_HEADER_MULTILAVEL_SCHOOL);
					}
					
					logger.log(LogStatus.INFO,TestCases.TC_LOMT_1408_46_EXPORT_EXTFRAM_GRADETITLE_VALUE_SCHOOL);
					logger.log(LogStatus.INFO,TestCases.TC_LOMT_1408_47_EXPORT_EXTFRAM_GRADETITLE_NAGVALUE_SCHOOL);
					
					//Compare Educational Goal data between Actual & Exported file
					int levelCounter = 0;
					Map<String, Boolean> resultMap = compareEXFEducationalGoalData(actualFile, exportedFile, lobName, logger);
					for (String key : resultMap.keySet()) {
						if (LOMTConstant.UNIQUE_ID.equalsIgnoreCase(key)) {
							if (resultMap.get(key)) {
								logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_43_EXPORT_EXTFRAM_UNIQUEID_VALUE_SCHOOL);
							} else {
								logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1408_43_EXPORT_EXTFRAM_UNIQUEID_VALUE_SCHOOL);
							}
						} else if (LOMTConstant.GRADE_LOW.equalsIgnoreCase(key) && resultMap.get(key)) {
							if (resultMap.get(key)) {
								logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_44_EXPORT_EXTFRAM_GRADELOW_VALUE_SCHOOL);
							} else {
								logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1408_44_EXPORT_EXTFRAM_GRADELOW_VALUE_SCHOOL);
							}
						} else if (LOMTConstant.GRADE_HIGH.equalsIgnoreCase(key) && resultMap.get(key)) {
							if (resultMap.get(key)) {
								logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_45_EXPORT_EXTFRAM_GRADEHIGH_VALUE_SCHOOL);
							} else {
								logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1408_45_EXPORT_EXTFRAM_GRADEHIGH_VALUE_SCHOOL);
							}
						}
						//DE-SCOPED
						/*else if (LOMTConstant.GRADE_TITLE.equalsIgnoreCase(key) && resultMap.get(key)) {
							if (resultMap.get(key)) {
								logger.log(LogStatus.PASS,TestCases.TC_LOMT_1408_46_EXPORT_EXTFRAM_GRADETITLE_VALUE_SCHOOL);
								logger.log(LogStatus.PASS,TestCases.TC_LOMT_1408_47_EXPORT_EXTFRAM_GRADETITLE_NAGVALUE_SCHOOL);
							} else {
								logger.log(LogStatus.FAIL,TestCases.TC_LOMT_1408_46_EXPORT_EXTFRAM_GRADETITLE_VALUE_SCHOOL);
								logger.log(LogStatus.FAIL,TestCases.TC_LOMT_1408_47_EXPORT_EXTFRAM_GRADETITLE_NAGVALUE_SCHOOL);
							}
						}*/
						else if (LOMTConstant.OFFICAL_STAN_CODE.equalsIgnoreCase(key) && resultMap.get(key)) {
							if (resultMap.get(key)) {
								logger.log(LogStatus.PASS,TestCases.TC_LOMT_1408_48_EXPORT_EXTFRAM_OFFICIALSTANDARDCODE_VALUE_SCHOOL);
							} else {
								logger.log(LogStatus.FAIL,TestCases.TC_LOMT_1408_48_EXPORT_EXTFRAM_OFFICIALSTANDARDCODE_VALUE_SCHOOL);
							}
						}
						else if (LOMTConstant.LEVEL_1.equalsIgnoreCase(key) && resultMap.get(key)) {
							if (resultMap.get(key)) {
								levelCounter++;
								logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_49_EXPORT_EXTFRAM_LEVEL1_VALUE_SCHOOL);
							} else {
								logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1408_49_EXPORT_EXTFRAM_LEVEL1_VALUE_SCHOOL);
							}
						}
						else if (LOMTConstant.LEVEL_2.equalsIgnoreCase(key) && resultMap.get(key)) {
							levelCounter++;
							if (resultMap.get(key)) {
								logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_50_EXPORT_EXTFRAM_LEVEL2_VALUE_SCHOOL);
							} else {
								logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1408_50_EXPORT_EXTFRAM_LEVEL2_VALUE_SCHOOL);
							}
						} else if (LOMTConstant.LEVEL_3.equalsIgnoreCase(key) 
								|| LOMTConstant.LEVEL_4.equalsIgnoreCase(key) 
								|| LOMTConstant.LEVEL_5.equalsIgnoreCase(key)
								|| LOMTConstant.LEVEL_6.equalsIgnoreCase(key)
								|| LOMTConstant.LEVEL_7.equalsIgnoreCase(key)
								|| LOMTConstant.LEVEL_8.equalsIgnoreCase(key)
								|| LOMTConstant.LEVEL_9.equalsIgnoreCase(key)
								&& resultMap.get(key)) {
							levelCounter++;
							if (resultMap.get(key) ) {
								multiLevelFlag = true;
								//logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_51_EXPORT_EXTFRAM_MULTILEVELS_VALUE_SCHOOL);
							} else {
								multiLevelFlag = false;
								//logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1408_51_EXPORT_EXTFRAM_MULTILEVELS_VALUE_SCHOOL);
							}
						} else if (LOMTConstant.LOWEST_LEVEL.equalsIgnoreCase(key) && resultMap.get(key)) {
							if (multiLevelFlag) {
								logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_51_EXPORT_EXTFRAM_MULTILEVELS_VALUE_SCHOOL);
							} else {
								logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1408_51_EXPORT_EXTFRAM_MULTILEVELS_VALUE_SCHOOL);
							}
							if (resultMap.get(key)) {
								//logger.log(LogStatus.PASS,TestCases.TC_LOMT_1408_51_EXPORT_EXTFRAM_MULTILEVELS_VALUE_SCHOOL);
								logger.log(LogStatus.PASS,TestCases.TC_LOMT_1408_52_EXPORT_EXTFRAM_LOWESTLEVEL_VALUE_SCHOOL);
								logger.log(LogStatus.PASS,TestCases.TC_LOMT_1408_53_EXPORT_EXTFRAM_LOWESTLEVEL_MULTILEVEL_SCHOOL);
							} else {
								logger.log(LogStatus.FAIL,TestCases.TC_LOMT_1408_52_EXPORT_EXTFRAM_LOWESTLEVEL_VALUE_SCHOOL);
								logger.log(LogStatus.FAIL,TestCases.TC_LOMT_1408_53_EXPORT_EXTFRAM_LOWESTLEVEL_MULTILEVEL_SCHOOL);
							}
						} else {
							if (LOMTConstant.TAGS.equalsIgnoreCase(key) && resultMap.get(key)) {
								if (resultMap.get(key)) {
									logger.log(LogStatus.INFO,TestCases.TC_LOMT_1408_54_EXPORT_EXTFRAM_TAGS_VALUE_SCHOOL);
								} else {
									logger.log(LogStatus.FAIL,TestCases.TC_LOMT_1408_54_EXPORT_EXTFRAM_TAGS_VALUE_SCHOOL);
								}
							}
						}
					}
				} else {
					System.out.println("Either Actual or Exported File doesn't exist : ");
					logger.log(LogStatus.INFO, "Either Actual or Exported File doesn't exist : ");
					// add logger
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				// add logger
			}
		}
	}
	
	public boolean compareHeardersEXF(File actualFile, File exportedFile, String lobName) {
		boolean flag = false;
		
		InputStream exfFilePathIS = null;
		XSSFWorkbook workbookForexfFile = null;
		
		InputStream exportedEXFExcelFileIS = null;
		XSSFWorkbook workbookForExportedEXFFile = null;
		
		//actual file data for comparison
		String goalFrameworkTitle = null;
		String uniqueIdAct = null;
		String gradeLowAct = null;
		String gradeHighAct = null;
		String gradeTitleAct = null;
		String officalStandCodeAct = null;
		String level1Act = null;
		String level2Act = null;
		String level3Act = null;
		String level4Act = null;
		String level5Act = null;
		String level6Act = null;
		String level7Act = null;
		String level8Act = null;
		String level9Act = null;
		String lowestLevelAct = null;
		
		if (lobName.equalsIgnoreCase(LOMTConstant.HE_LOB) ||
				lobName.equalsIgnoreCase(LOMTConstant.ENGLISH_LOB) ) {
		try {
			exfFilePathIS = new FileInputStream(actualFile);
			workbookForexfFile = new XSSFWorkbook(exfFilePathIS);
			XSSFSheet actualDataSheet = workbookForexfFile.getSheetAt(0);
			Iterator<Row> rowIteratoreForActual = actualDataSheet.iterator();
			while (rowIteratoreForActual.hasNext()) {
				Row row = rowIteratoreForActual.next();
				if ((row.getRowNum() == 0)) {
					goalFrameworkTitle = String.valueOf(row.getCell(LOMTConstant.ZERO));
				} 
				if (row.getRowNum() == 1) {
					uniqueIdAct = String.valueOf(row.getCell(LOMTConstant.ZERO));
					gradeLowAct = String.valueOf(row.getCell(LOMTConstant.ONE));
					gradeHighAct = String.valueOf(row.getCell(LOMTConstant.TWO));
					gradeTitleAct = String.valueOf(row.getCell(LOMTConstant.THREE));
					officalStandCodeAct = String.valueOf(row.getCell(LOMTConstant.FOUR));
					level1Act = String.valueOf(row.getCell(LOMTConstant.FIVE));
					level2Act = String.valueOf(row.getCell(LOMTConstant.SIX));
					lowestLevelAct = String.valueOf(row.getCell(LOMTConstant.SEVEN));
					
					Assert.assertEquals(LOMTConstant.UNIQUE_ID, uniqueIdAct);
					Assert.assertEquals(LOMTConstant.GRADE_LOW, gradeLowAct);
					Assert.assertEquals(LOMTConstant.GRADE_HIGH, gradeHighAct);
					Assert.assertEquals(LOMTConstant.GRADE_TITLE, gradeTitleAct);
					Assert.assertEquals(LOMTConstant.OFFICAL_STAN_CODE, officalStandCodeAct);
					Assert.assertEquals(LOMTConstant.LEVEL_1, level1Act);
					Assert.assertEquals(LOMTConstant.LEVEL_2, level2Act);
					Assert.assertEquals(LOMTConstant.LOWEST_LEVEL, lowestLevelAct);
					flag = true;
					break;
				}
			}
			
			//Exported File data for comparison
			exportedEXFExcelFileIS = new FileInputStream(exportedFile);
			workbookForExportedEXFFile = new XSSFWorkbook(exportedEXFExcelFileIS);
			XSSFSheet exportedDataSheet = workbookForExportedEXFFile.getSheetAt(0);
			Iterator<Row> rowIteratoreForExport = exportedDataSheet.iterator();
			while (rowIteratoreForExport.hasNext()) {
				Row row = rowIteratoreForExport.next();
				if ((row.getRowNum() == 0)) {
					Assert.assertEquals(goalFrameworkTitle, String.valueOf(row.getCell(LOMTConstant.ZERO)));
				} 
				if (row.getRowNum() == 1) {
					if (!(String.valueOf(row.getCell(LOMTConstant.ZERO)) == null)
							&& !(row.getCell(LOMTConstant.ZERO).getStringCellValue().equalsIgnoreCase(""))) {
						Assert.assertEquals(LOMTConstant.UNIQUE_ID, String.valueOf(row.getCell(LOMTConstant.ZERO)));
						Assert.assertEquals(uniqueIdAct, String.valueOf(row.getCell(LOMTConstant.ZERO).getStringCellValue()));
						flag = true;
					} 
					if (!(String.valueOf(row.getCell(LOMTConstant.ONE)) == null)
							&& !(row.getCell(LOMTConstant.ONE).getStringCellValue().equalsIgnoreCase(""))) {
						Assert.assertEquals(LOMTConstant.GRADE_LOW, String.valueOf(row.getCell(LOMTConstant.ONE)));
						Assert.assertEquals(gradeLowAct, String.valueOf(row.getCell(LOMTConstant.ONE).getStringCellValue()));
						flag = true;
					} 
					if (!(String.valueOf(row.getCell(LOMTConstant.TWO)) == null)
							&& !(row.getCell(LOMTConstant.TWO).getStringCellValue().equalsIgnoreCase(""))) {
						Assert.assertEquals(LOMTConstant.GRADE_HIGH, String.valueOf(row.getCell(LOMTConstant.TWO)));
						Assert.assertEquals(gradeHighAct, String.valueOf(row.getCell(LOMTConstant.TWO).getStringCellValue()));
						flag = true;
					} 
					if (!(String.valueOf(row.getCell(LOMTConstant.FOUR)) == null)
							&& !(row.getCell(LOMTConstant.FOUR).getStringCellValue().equalsIgnoreCase(""))) {
						Assert.assertEquals(LOMTConstant.OFFICAL_STAN_CODE,
								String.valueOf(row.getCell(LOMTConstant.FOUR)));
						Assert.assertEquals(officalStandCodeAct, String.valueOf(row.getCell(LOMTConstant.FOUR).getStringCellValue()));
						flag = true;
					} 
					if (!(String.valueOf(row.getCell(LOMTConstant.FIVE)) == null)
							&& !(row.getCell(LOMTConstant.FIVE).getStringCellValue().equalsIgnoreCase(""))) {
						Assert.assertEquals(LOMTConstant.LEVEL_1, String.valueOf(row.getCell(LOMTConstant.FIVE)));
						Assert.assertEquals(level1Act, String.valueOf(row.getCell(LOMTConstant.FIVE).getStringCellValue()));
						flag = true;
					} 
					if (!(String.valueOf(row.getCell(LOMTConstant.SIX)) == null)
							&& !(row.getCell(LOMTConstant.SIX).getStringCellValue().equalsIgnoreCase(""))) {
						Assert.assertEquals(LOMTConstant.LEVEL_2, String.valueOf(row.getCell(LOMTConstant.SIX)));
						Assert.assertEquals(level2Act, String.valueOf(row.getCell(LOMTConstant.SIX).getStringCellValue()));
						flag = true;
					} 
					if (!(String.valueOf(row.getCell(LOMTConstant.SEVEN)) == null)
							&& !(row.getCell(LOMTConstant.SEVEN).getStringCellValue().equalsIgnoreCase(""))) {
						Assert.assertEquals("Lowest Level", String.valueOf(row.getCell(LOMTConstant.SEVEN)));
						Assert.assertEquals(lowestLevelAct.toLowerCase(), String.valueOf(row.getCell(LOMTConstant.SEVEN).getStringCellValue()).toLowerCase());
						flag = true;
					} 
					break;
				}
			}
			return flag;
			} catch (Exception e) {
				e.printStackTrace();
				flag = false;
			}
			return flag;
		} else {
			//SCHOOL(NALS & SCHOOL GLOBAL)
			try {
				exfFilePathIS = new FileInputStream(actualFile);
				workbookForexfFile = new XSSFWorkbook(exfFilePathIS);
				XSSFSheet actualDataSheet = workbookForexfFile.getSheetAt(0);
				Iterator<Row> rowIteratoreForActual = actualDataSheet.iterator();
				while (rowIteratoreForActual.hasNext()) {
					Row row = rowIteratoreForActual.next();
					if ((row.getRowNum() == 0)) {
						goalFrameworkTitle = String.valueOf(row.getCell(LOMTConstant.ZERO));
					} 
					if (row.getRowNum() == 1) {uniqueIdAct = String.valueOf(row.getCell(LOMTConstant.ZERO));
					gradeLowAct = String.valueOf(row.getCell(LOMTConstant.ONE));
					gradeHighAct = String.valueOf(row.getCell(LOMTConstant.TWO));
					gradeTitleAct = String.valueOf(row.getCell(LOMTConstant.THREE));
					officalStandCodeAct = String.valueOf(row.getCell(LOMTConstant.FOUR));
					level1Act = String.valueOf(row.getCell(LOMTConstant.FIVE));
					level2Act = String.valueOf(row.getCell(LOMTConstant.SIX));
					level3Act = String.valueOf(row.getCell(LOMTConstant.SEVEN));
					level4Act = String.valueOf(row.getCell(LOMTConstant.EIGHT));
					level5Act = String.valueOf(row.getCell(LOMTConstant.NINE));
					level6Act = String.valueOf(row.getCell(LOMTConstant.TEN));
					level7Act = String.valueOf(row.getCell(LOMTConstant.ELEVENTH));
					level8Act = String.valueOf(row.getCell(LOMTConstant.TWELEVE));
					level9Act = String.valueOf(row.getCell(LOMTConstant.THIRTEEN));
					lowestLevelAct = String.valueOf(row.getCell(LOMTConstant.FOURTEEN));
					
					Assert.assertEquals(LOMTConstant.UNIQUE_ID, uniqueIdAct);
					Assert.assertEquals(LOMTConstant.GRADE_LOW, gradeLowAct);
					Assert.assertEquals(LOMTConstant.GRADE_HIGH, gradeHighAct);
					Assert.assertEquals(LOMTConstant.GRADE_TITLE, gradeTitleAct);
					Assert.assertEquals(LOMTConstant.OFFICAL_STAN_CODE, officalStandCodeAct);
					Assert.assertEquals(LOMTConstant.LEVEL_1, level1Act);
					Assert.assertEquals(LOMTConstant.LEVEL_2, level2Act);
					Assert.assertEquals(LOMTConstant.LEVEL_3, level3Act);
					Assert.assertEquals(LOMTConstant.LEVEL_4, level4Act);
					Assert.assertEquals(LOMTConstant.LEVEL_5, level5Act);
					Assert.assertEquals(LOMTConstant.LEVEL_6, level6Act);
					Assert.assertEquals(LOMTConstant.LEVEL_7, level7Act);
					Assert.assertEquals(LOMTConstant.LEVEL_8, level8Act);
					Assert.assertEquals(LOMTConstant.LEVEL_9, level9Act);
					Assert.assertEquals(LOMTConstant.LOWEST_LEVEL, lowestLevelAct);
					flag = true;
					break;
					}
				}
				
				//Exported File data for comparison
				exportedEXFExcelFileIS = new FileInputStream(exportedFile);
				workbookForExportedEXFFile = new XSSFWorkbook(exportedEXFExcelFileIS);
				XSSFSheet exportedDataSheet = workbookForExportedEXFFile.getSheetAt(0);
				Iterator<Row> rowIteratoreForExport = exportedDataSheet.iterator();
				while (rowIteratoreForExport.hasNext()) {
					Row row = rowIteratoreForExport.next();
					if ((row.getRowNum() == 0)) {
						Assert.assertNotNull(String.valueOf(row.getCell(LOMTConstant.ZERO)));
					} 
					if (row.getRowNum() == 1) {
						if (!(String.valueOf(row.getCell(LOMTConstant.ZERO)) == null)
								&& !(row.getCell(LOMTConstant.ZERO).getStringCellValue().equalsIgnoreCase(""))) {
							Assert.assertEquals(LOMTConstant.UNIQUE_ID, String.valueOf(row.getCell(LOMTConstant.ZERO)));
							Assert.assertEquals(uniqueIdAct, String.valueOf(row.getCell(LOMTConstant.ZERO).getStringCellValue()));
							flag = true;
						} 
						if (!(String.valueOf(row.getCell(LOMTConstant.ONE)) == null)
								&& !(row.getCell(LOMTConstant.ONE).getStringCellValue().equalsIgnoreCase(""))) {
							Assert.assertEquals(LOMTConstant.GRADE_LOW, String.valueOf(row.getCell(LOMTConstant.ONE)));
							Assert.assertEquals(gradeLowAct, String.valueOf(row.getCell(LOMTConstant.ONE).getStringCellValue()));
							flag = true;
						} 
						if (!(String.valueOf(row.getCell(LOMTConstant.TWO)) == null)
								&& !(row.getCell(LOMTConstant.TWO).getStringCellValue().equalsIgnoreCase(""))) {
							Assert.assertEquals(LOMTConstant.GRADE_HIGH, String.valueOf(row.getCell(LOMTConstant.TWO)));
							Assert.assertEquals(gradeHighAct, String.valueOf(row.getCell(LOMTConstant.TWO).getStringCellValue()));
							flag = true;
						} 
						if (!(String.valueOf(row.getCell(LOMTConstant.THREE)) == null)
								&& !(row.getCell(LOMTConstant.THREE).getStringCellValue().equalsIgnoreCase(""))) {
							Assert.assertEquals(LOMTConstant.GRADE_TITLE, String.valueOf(row.getCell(LOMTConstant.THREE)));
							Assert.assertEquals(gradeTitleAct, String.valueOf(row.getCell(LOMTConstant.THREE).getStringCellValue()));
							flag = true;
						} 
						if (!(String.valueOf(row.getCell(LOMTConstant.FOUR)) == null)
								&& !(row.getCell(LOMTConstant.FOUR).getStringCellValue().equalsIgnoreCase(""))) {
							Assert.assertEquals(LOMTConstant.OFFICAL_STAN_CODE,
									String.valueOf(row.getCell(LOMTConstant.FOUR)));
							Assert.assertEquals(officalStandCodeAct, String.valueOf(row.getCell(LOMTConstant.FOUR).getStringCellValue()));
							flag = true;
						} 
						if (!(String.valueOf(row.getCell(LOMTConstant.FIVE)) == null)
								&& !(row.getCell(LOMTConstant.FIVE).getStringCellValue().equalsIgnoreCase(""))) {
							Assert.assertEquals(LOMTConstant.LEVEL_1, String.valueOf(row.getCell(LOMTConstant.FIVE)));
							Assert.assertEquals(level1Act, String.valueOf(row.getCell(LOMTConstant.FIVE).getStringCellValue()));
							flag = true;
						} 
						if (!(String.valueOf(row.getCell(LOMTConstant.SIX)) == null)
								&& !(row.getCell(LOMTConstant.SIX).getStringCellValue().equalsIgnoreCase(""))) {
							Assert.assertEquals(LOMTConstant.LEVEL_2, String.valueOf(row.getCell(LOMTConstant.SIX)));
							Assert.assertEquals(level2Act, String.valueOf(row.getCell(LOMTConstant.SIX).getStringCellValue()));
							flag = true;
						} 
						if (!(String.valueOf(row.getCell(LOMTConstant.SEVEN)) == null)
								&& !(row.getCell(LOMTConstant.SEVEN).getStringCellValue().equalsIgnoreCase(""))) {
							Assert.assertEquals(LOMTConstant.LEVEL_3, String.valueOf(row.getCell(LOMTConstant.SEVEN)));
							Assert.assertEquals(level3Act, String.valueOf(row.getCell(LOMTConstant.SEVEN).getStringCellValue()));
							flag = true;
						} 
						if (!(String.valueOf(row.getCell(LOMTConstant.EIGHT)) == null)
								&& !(row.getCell(LOMTConstant.EIGHT).getStringCellValue().equalsIgnoreCase(""))) {
							Assert.assertEquals(LOMTConstant.LEVEL_4, String.valueOf(row.getCell(LOMTConstant.EIGHT)));
							Assert.assertEquals(level4Act, String.valueOf(row.getCell(LOMTConstant.EIGHT).getStringCellValue()));
							flag = true;
						} 
						if (!(String.valueOf(row.getCell(LOMTConstant.NINE)) == null)
								&& !(row.getCell(LOMTConstant.NINE).getStringCellValue().equalsIgnoreCase(""))) {
							Assert.assertEquals(LOMTConstant.LEVEL_5, String.valueOf(row.getCell(LOMTConstant.NINE)));
							Assert.assertEquals(level5Act, String.valueOf(row.getCell(LOMTConstant.NINE).getStringCellValue()));
							flag = true;
						}
						if (!(String.valueOf(row.getCell(LOMTConstant.TEN)) == null)
								&& !(row.getCell(LOMTConstant.TEN).getStringCellValue().equalsIgnoreCase(""))) {
							Assert.assertEquals(LOMTConstant.LEVEL_6, String.valueOf(row.getCell(LOMTConstant.TEN)));
							Assert.assertEquals(level6Act, String.valueOf(row.getCell(LOMTConstant.TEN).getStringCellValue()));
							flag = true;
						}
						if (!(String.valueOf(row.getCell(LOMTConstant.ELEVENTH)) == null)
								&& !(row.getCell(LOMTConstant.ELEVENTH).getStringCellValue().equalsIgnoreCase(""))) {
							Assert.assertEquals(LOMTConstant.LEVEL_7, String.valueOf(row.getCell(LOMTConstant.ELEVENTH)));
							Assert.assertEquals(level7Act, String.valueOf(row.getCell(LOMTConstant.ELEVENTH).getStringCellValue()));
							flag = true;
						}
						if (!(String.valueOf(row.getCell(LOMTConstant.TWELEVE)) == null)
								&& !(row.getCell(LOMTConstant.TWELEVE).getStringCellValue().equalsIgnoreCase(""))) {
							Assert.assertEquals(LOMTConstant.LEVEL_8, String.valueOf(row.getCell(LOMTConstant.TWELEVE)));
							Assert.assertEquals(level8Act, String.valueOf(row.getCell(LOMTConstant.TWELEVE).getStringCellValue()));
							flag = true;
						}
						if (!(String.valueOf(row.getCell(LOMTConstant.THIRTEEN)) == null)
								&& !(row.getCell(LOMTConstant.THIRTEEN).getStringCellValue().equalsIgnoreCase(""))) {
							Assert.assertEquals(LOMTConstant.LEVEL_9, String.valueOf(row.getCell(LOMTConstant.THIRTEEN)));
							Assert.assertEquals(level9Act, String.valueOf(row.getCell(LOMTConstant.THIRTEEN).getStringCellValue()));
							flag = true;
						}
						if (!(String.valueOf(row.getCell(LOMTConstant.FOURTEEN)) == null)
								&& !(row.getCell(LOMTConstant.FOURTEEN).getStringCellValue().equalsIgnoreCase(""))) {
							Assert.assertEquals("Lowest Level", String.valueOf(row.getCell(LOMTConstant.FOURTEEN)));
							Assert.assertEquals(lowestLevelAct.toLowerCase(), String.valueOf(row.getCell(LOMTConstant.FOURTEEN).getStringCellValue()).toLowerCase());
							flag = true;
						} 
						break;
					}
				}
				return flag;
				} catch (Exception e) {
					e.printStackTrace();
					flag = false;
				}
				return flag;
			}
	}
	
	public Map<String, Boolean> compareEXFEducationalGoalData(File actualFile, File exportedFile, String lobName, ExtentTest logger) {
		Map<String, Boolean> heExFMap = null;
		if (lobName.equalsIgnoreCase(LOMTConstant.HE_LOB) ||
				lobName.equalsIgnoreCase(LOMTConstant.ENGLISH_LOB)) {
			InputStream exfFilePathIS = null;
			XSSFWorkbook workbookForexfFile = null;

			InputStream exportedEXFExcelFileIS = null;
			XSSFWorkbook workbookForExportedEXFFile = null;
			
			heExFMap = new LinkedHashMap<String, Boolean>();
			try {
				// Actual(Ingestion) data for comparison
				exfFilePathIS = new FileInputStream(actualFile);
				workbookForexfFile = new XSSFWorkbook(exfFilePathIS);
				XSSFSheet actualDataSheet = workbookForexfFile.getSheetAt(0);

				Iterator<Row> rowIteratoreForActual = actualDataSheet.iterator();

				// Exported File data for comparison
				exportedEXFExcelFileIS = new FileInputStream(exportedFile);
				workbookForExportedEXFFile = new XSSFWorkbook(exportedEXFExcelFileIS);
				XSSFSheet exportedDataSheet = workbookForExportedEXFFile.getSheetAt(0);

				Iterator<Row> rowIteratoreForExport = exportedDataSheet.iterator();

				while (rowIteratoreForExport.hasNext()) {
					Row rowExp = rowIteratoreForExport.next();
					if (rowExp.getRowNum() != 0 && rowExp.getRowNum() != 1) {

						while (rowIteratoreForActual.hasNext()) {
							Row rowAct = rowIteratoreForActual.next();
							if (rowAct.getRowNum() != 0 && rowAct.getRowNum() != 1) {
								// Unique id
								if (!(String.valueOf(rowExp.getCell(LOMTConstant.ZERO)).contains("null"))) {

									Assert.assertNotNull(
											String.valueOf(rowExp.getCell(LOMTConstant.ZERO).getStringCellValue()));
									heExFMap.put(LOMTConstant.UNIQUE_ID, true);
								}

								// Grade Low
								if (!(String.valueOf(rowExp.getCell(LOMTConstant.ONE)).contains("null"))
										&& !(String.valueOf(rowAct.getCell(LOMTConstant.ONE)).contains("null"))) {
									boolean innerFlag = false;
									String valAct = null;

									if (String.valueOf(rowAct.getCell(LOMTConstant.ONE)).contains(".0")) {
										valAct = String.valueOf(rowAct.getCell(LOMTConstant.ONE));
										int index = valAct.indexOf(".");
										valAct = valAct.substring(0, index);
										innerFlag = true;
									}

									if (innerFlag) {
										Assert.assertEquals(String.valueOf(rowExp.getCell(LOMTConstant.ONE)), valAct);
										heExFMap.put(LOMTConstant.GRADE_LOW, true);
									} else {
										Assert.assertEquals(String.valueOf(rowExp.getCell(LOMTConstant.ONE)),
												String.valueOf(rowAct.getCell(LOMTConstant.ONE)));
										heExFMap.put(LOMTConstant.GRADE_LOW, true);
									}
								}

								// Grade High
								if (!(String.valueOf(rowExp.getCell(LOMTConstant.TWO)).contains("null"))
										&& !(String.valueOf(rowAct.getCell(LOMTConstant.TWO)).contains("null"))) {
									boolean innerFlag = false;
									String valAct = null;

									if (String.valueOf(rowAct.getCell(LOMTConstant.TWO)).contains(".0")) {
										valAct = String.valueOf(rowAct.getCell(LOMTConstant.TWO));
										int index = valAct.indexOf(".");
										valAct = valAct.substring(0, index);
										innerFlag = true;
									}

									if (innerFlag) {
										Assert.assertEquals(String.valueOf(rowExp.getCell(LOMTConstant.TWO)), valAct);
										heExFMap.put(LOMTConstant.GRADE_HIGH, true);
									} else {
										Assert.assertEquals(String.valueOf(rowExp.getCell(LOMTConstant.TWO)),
												String.valueOf(rowAct.getCell(LOMTConstant.TWO)));
										heExFMap.put(LOMTConstant.GRADE_HIGH, true);
									}
								}

								// Grade Title
								if (!(String.valueOf(rowExp.getCell(LOMTConstant.THREE)).contains("null"))
										&& !(String.valueOf(rowAct.getCell(LOMTConstant.THREE)).contains("null"))) {

									Assert.assertEquals(String.valueOf(rowExp.getCell(LOMTConstant.THREE).getStringCellValue().trim()),
											String.valueOf(rowAct.getCell(LOMTConstant.THREE).getStringCellValue()).trim());
									heExFMap.put(LOMTConstant.GRADE_TITLE, true);
								}

								// Offical Standard Code
								if (!(String.valueOf(rowExp.getCell(LOMTConstant.FOUR)).contains("null"))
										&& !(String.valueOf(rowAct.getCell(LOMTConstant.FOUR)).contains("null"))) {

									Assert.assertEquals(String.valueOf(rowExp.getCell(LOMTConstant.FOUR).getStringCellValue().trim()),
											String.valueOf(rowAct.getCell(LOMTConstant.FOUR).getStringCellValue()).trim());
									heExFMap.put(LOMTConstant.OFFICAL_STAN_CODE, true);
								}

								// Level 1
								if (!(String.valueOf(rowExp.getCell(LOMTConstant.FIVE)).contains("null"))
										&& !(String.valueOf(rowAct.getCell(LOMTConstant.FIVE)).contains("null"))) {

									Assert.assertEquals(String.valueOf(rowExp.getCell(LOMTConstant.FIVE).getStringCellValue().trim()),
											String.valueOf(rowAct.getCell(LOMTConstant.FIVE).getStringCellValue()).trim());
									heExFMap.put(LOMTConstant.LEVEL_1, true);
								}

								// Level 2
								if (!(String.valueOf(rowExp.getCell(LOMTConstant.SIX)).contains("null"))
										&& !(String.valueOf(rowAct.getCell(LOMTConstant.SIX)).contains("null"))) {

									Assert.assertEquals(String.valueOf(rowExp.getCell(LOMTConstant.SIX).getStringCellValue().trim()),
											String.valueOf(rowAct.getCell(LOMTConstant.SIX).getStringCellValue()).trim());
									heExFMap.put(LOMTConstant.LEVEL_2, true);
								}

								// Lowest Level
								if (!(String.valueOf(rowExp.getCell(LOMTConstant.SEVEN)).contains("null"))
										&& !(String.valueOf(rowAct.getCell(LOMTConstant.SEVEN)).contains("null"))) {

									Assert.assertEquals(String.valueOf(rowExp.getCell(LOMTConstant.SEVEN).getStringCellValue().trim()),
											String.valueOf(rowAct.getCell(LOMTConstant.SEVEN).getStringCellValue()).trim());
									heExFMap.put(LOMTConstant.LOWEST_LEVEL, true);
								}

								// Tags
								if (!(String.valueOf(rowExp.getCell(LOMTConstant.EIGHT)) != null)
										&& !(String.valueOf(rowAct.getCell(LOMTConstant.EIGHT)) != null)) {
									Assert.assertNull(String.valueOf(rowExp.getCell(LOMTConstant.EIGHT).getStringCellValue()));
									Assert.assertNull(String.valueOf(rowAct.getCell(LOMTConstant.EIGHT).getStringCellValue()));
									heExFMap.put(LOMTConstant.TAGS, true);
									break;
								} else {
									heExFMap.put(LOMTConstant.TAGS, true);
									break;
								}
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			//SCHOOL(NALS & SCHOOL GLOBAL)
			InputStream exfFilePathIS = null;
			XSSFWorkbook workbookForexfFile = null;

			InputStream exportedEXFExcelFileIS = null;
			XSSFWorkbook workbookForExportedEXFFile = null;
			
			heExFMap = new LinkedHashMap<String, Boolean>();
			try {
				// Actual(Ingestion) data for comparison
				exfFilePathIS = new FileInputStream(actualFile);
				workbookForexfFile = new XSSFWorkbook(exfFilePathIS);
				XSSFSheet actualDataSheet = workbookForexfFile.getSheetAt(0);

				Iterator<Row> rowIteratoreForActual = actualDataSheet.iterator();

				// Exported File data for comparison
				exportedEXFExcelFileIS = new FileInputStream(exportedFile);
				workbookForExportedEXFFile = new XSSFWorkbook(exportedEXFExcelFileIS);
				XSSFSheet exportedDataSheet = workbookForExportedEXFFile.getSheetAt(0);

				Iterator<Row> rowIteratoreForExport = exportedDataSheet.iterator();

				while (rowIteratoreForExport.hasNext()) {
					Row rowExp = rowIteratoreForExport.next();
					if (rowExp.getRowNum() != 0 && rowExp.getRowNum() != 1) {

						while (rowIteratoreForActual.hasNext()) {
							Row rowAct = rowIteratoreForActual.next();
							if (rowAct.getRowNum() != 0 && rowAct.getRowNum() != 1) {
								// Unique id
								if (!(String.valueOf(rowExp.getCell(LOMTConstant.ZERO)).contains(LOMTConstant.NULL))) {

									Assert.assertNotNull(
											String.valueOf(rowExp.getCell(LOMTConstant.ZERO).getStringCellValue()));
									heExFMap.put(LOMTConstant.UNIQUE_ID, true);
								}
								// Grade Low
								if (!(String.valueOf(rowExp.getCell(LOMTConstant.ONE)).contains(LOMTConstant.NULL))
										&& !(String.valueOf(rowAct.getCell(LOMTConstant.ONE)).contains(LOMTConstant.NULL))) {
									boolean innerFlag = false;
									String valAct = null;

									if (String.valueOf(rowAct.getCell(LOMTConstant.ONE)).contains(".0")) {
										valAct = String.valueOf(rowAct.getCell(LOMTConstant.ONE));
										int index = valAct.indexOf(".");
										valAct = valAct.substring(0, index);
										innerFlag = true;
									}

									if (innerFlag) {
										Assert.assertEquals(String.valueOf(rowExp.getCell(LOMTConstant.ONE)), valAct);
										heExFMap.put(LOMTConstant.GRADE_LOW, true);
									} else {
										Assert.assertEquals(String.valueOf(rowExp.getCell(LOMTConstant.ONE)),
												String.valueOf(rowAct.getCell(LOMTConstant.ONE)));
										heExFMap.put(LOMTConstant.GRADE_LOW, true);
									}
								}

								// Grade High
								if (!(String.valueOf(rowExp.getCell(LOMTConstant.TWO)).contains(LOMTConstant.NULL))
										&& !(String.valueOf(rowAct.getCell(LOMTConstant.TWO)).contains(LOMTConstant.NULL))) {
									boolean innerFlag = false;
									String valAct = null;

									if (String.valueOf(rowAct.getCell(LOMTConstant.TWO)).contains(".0")) {
										valAct = String.valueOf(rowAct.getCell(LOMTConstant.TWO));
										int index = valAct.indexOf(".");
										valAct = valAct.substring(0, index);
										innerFlag = true;
									}

									if (innerFlag) {
										Assert.assertEquals(String.valueOf(rowExp.getCell(LOMTConstant.TWO)), valAct);
										heExFMap.put(LOMTConstant.GRADE_HIGH, true);
									} else {
										Assert.assertEquals(String.valueOf(rowExp.getCell(LOMTConstant.TWO)),
												String.valueOf(rowAct.getCell(LOMTConstant.TWO)));
										heExFMap.put(LOMTConstant.GRADE_HIGH, true);
									}
								}

								// Offical Standard Code
								if (!(String.valueOf(rowExp.getCell(LOMTConstant.FOUR)).contains(LOMTConstant.NULL))
										&& !(String.valueOf(rowAct.getCell(LOMTConstant.FOUR)).contains(LOMTConstant.NULL))) {

									Assert.assertEquals(String.valueOf(rowExp.getCell(LOMTConstant.FOUR).getStringCellValue().trim()),
											String.valueOf(rowAct.getCell(LOMTConstant.FOUR).getStringCellValue()).trim());
									heExFMap.put(LOMTConstant.OFFICAL_STAN_CODE, true);
								}

								// Level 1
								if (!(String.valueOf(rowExp.getCell(LOMTConstant.FIVE)).contains(LOMTConstant.NULL))
										&& !(String.valueOf(rowAct.getCell(LOMTConstant.FIVE)).contains(LOMTConstant.NULL))) {

									Assert.assertEquals(String.valueOf(rowExp.getCell(LOMTConstant.FIVE).getStringCellValue().trim()),
											String.valueOf(rowAct.getCell(LOMTConstant.FIVE).getStringCellValue()).trim());
									heExFMap.put(LOMTConstant.LEVEL_1, true);
								}

								// Level 2
								if (!(String.valueOf(rowExp.getCell(LOMTConstant.SIX)).contains(LOMTConstant.NULL))
										&& !(String.valueOf(rowAct.getCell(LOMTConstant.SIX)).contains(LOMTConstant.NULL))) {

									Assert.assertEquals(String.valueOf(rowExp.getCell(LOMTConstant.SIX).getStringCellValue().trim()),
											String.valueOf(rowAct.getCell(LOMTConstant.SIX).getStringCellValue()).trim());
									heExFMap.put(LOMTConstant.LEVEL_2, true);
								} 
								if (!(String.valueOf(rowExp.getCell(LOMTConstant.SEVEN)).contains(LOMTConstant.NULL))
										&& !(String.valueOf(rowAct.getCell(LOMTConstant.SEVEN)).contains(LOMTConstant.NULL))) {

									Assert.assertEquals(String.valueOf(rowExp.getCell(LOMTConstant.SEVEN).getStringCellValue().trim()),
											String.valueOf(rowAct.getCell(LOMTConstant.SEVEN).getStringCellValue()).trim());
									heExFMap.put(LOMTConstant.LEVEL_3, true);
								} 
								if (!(String.valueOf(rowExp.getCell(LOMTConstant.EIGHT)).contains(LOMTConstant.NULL))
										&& !(String.valueOf(rowAct.getCell(LOMTConstant.EIGHT)).contains(LOMTConstant.NULL))) {

									Assert.assertEquals(String.valueOf(rowExp.getCell(LOMTConstant.EIGHT).getStringCellValue().trim()),
											String.valueOf(rowAct.getCell(LOMTConstant.EIGHT).getStringCellValue()).trim());
									heExFMap.put(LOMTConstant.LEVEL_4, true);
								} 
								if (!(String.valueOf(rowExp.getCell(LOMTConstant.NINE)).contains(LOMTConstant.NULL))
										&& !(String.valueOf(rowAct.getCell(LOMTConstant.NINE)).contains(LOMTConstant.NULL))) {

									Assert.assertEquals(String.valueOf(rowExp.getCell(LOMTConstant.NINE).getStringCellValue().trim()),
											String.valueOf(rowAct.getCell(LOMTConstant.NINE).getStringCellValue()).trim());
									heExFMap.put(LOMTConstant.LEVEL_5, true);
								} 
								if (!(String.valueOf(rowExp.getCell(LOMTConstant.TEN)).contains(LOMTConstant.NULL))
										&& !(String.valueOf(rowAct.getCell(LOMTConstant.TEN)).contains(LOMTConstant.NULL))) {

									Assert.assertEquals(String.valueOf(rowExp.getCell(LOMTConstant.TEN).getStringCellValue().trim()),
											String.valueOf(rowAct.getCell(LOMTConstant.TEN).getStringCellValue()).trim());
									heExFMap.put(LOMTConstant.LEVEL_6, true);
								} 
								if (!(String.valueOf(rowExp.getCell(LOMTConstant.ELEVENTH)).contains(LOMTConstant.NULL))
										&& !(String.valueOf(rowAct.getCell(LOMTConstant.ELEVENTH)).contains(LOMTConstant.NULL))) {

									Assert.assertEquals(String.valueOf(rowExp.getCell(LOMTConstant.ELEVENTH).getStringCellValue().trim()),
											String.valueOf(rowAct.getCell(LOMTConstant.ELEVENTH).getStringCellValue()).trim());
									heExFMap.put(LOMTConstant.LEVEL_7, true);
								} 
								if (!(String.valueOf(rowExp.getCell(LOMTConstant.TWELEVE)).contains(LOMTConstant.NULL))
										&& !(String.valueOf(rowAct.getCell(LOMTConstant.TWELEVE)).contains(LOMTConstant.NULL))) {

									Assert.assertEquals(String.valueOf(rowExp.getCell(LOMTConstant.TWELEVE).getStringCellValue().trim()),
											String.valueOf(rowAct.getCell(LOMTConstant.TWELEVE).getStringCellValue()).trim());
									heExFMap.put(LOMTConstant.LEVEL_8, true);
								} 
								if (!(String.valueOf(rowExp.getCell(LOMTConstant.TWELEVE)).contains(LOMTConstant.NULL))
										&& !(String.valueOf(rowAct.getCell(LOMTConstant.TWELEVE)).contains(LOMTConstant.NULL))) {

									Assert.assertEquals(String.valueOf(rowExp.getCell(LOMTConstant.TWELEVE).getStringCellValue().trim()),
											String.valueOf(rowAct.getCell(LOMTConstant.TWELEVE).getStringCellValue()).trim());
									heExFMap.put(LOMTConstant.LEVEL_9, true);
								}
								// Lowest Level
								if (!(String.valueOf(rowExp.getCell(LOMTConstant.THIRTEEN)).contains("null"))
										&& !(String.valueOf(rowAct.getCell(LOMTConstant.THIRTEEN)).contains("null"))) {

									Assert.assertEquals(String.valueOf(rowExp.getCell(LOMTConstant.THIRTEEN).getStringCellValue().trim()),
											String.valueOf(rowAct.getCell(LOMTConstant.THIRTEEN).getStringCellValue()).trim());
									heExFMap.put(LOMTConstant.LOWEST_LEVEL, true);
								}

								// Tags
								if (!(String.valueOf(rowExp.getCell(LOMTConstant.FOURTEEN)) != null)
										&& !(String.valueOf(rowAct.getCell(LOMTConstant.FOURTEEN)) != null)) {
									Assert.assertNull(String.valueOf(rowExp.getCell(LOMTConstant.FOURTEEN).getStringCellValue()));
									Assert.assertNull(String.valueOf(rowAct.getCell(LOMTConstant.FOURTEEN).getStringCellValue()));
									heExFMap.put(LOMTConstant.TAGS, true);
									break;
								} else {
									heExFMap.put(LOMTConstant.TAGS, true);
									break;
								}
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return heExFMap;
	}

	public boolean verifyEXFFileName() {
		boolean flag = false;
		try {
			String date = new Date().toString();
			String[] CurrentDate = date.substring(4).split(" ");
			String formatedDate = CurrentDate[1] + CurrentDate[0] + CurrentDate[4];

			File exportedFile = new File(LOMTConstant.EXPORTED_FILE_PATH + LOMTConstant.EMPTY_STRING
					+ LOMTConstant.EXPORTED_FILE_NAME_EXF + formatedDate + LOMTConstant.XLSX_EXTENSION);

			if (exportedFile.getName().contains(LOMTConstant.EXPORTED_FILE_NAME_EXF)) {
				String exfFileName = exportedFile.getName();
				int index = exfFileName.lastIndexOf(LOMTConstant._TEMPLATE);

				String dateToValidate = exfFileName.substring(index + 9, exfFileName.length() - 5);

				SimpleDateFormat formattedDate = new SimpleDateFormat(LOMTConstant.DATE_FORMAT_EXF);
				formattedDate.setLenient(false);

				if (formattedDate.parse(dateToValidate) != null) {
					flag = true;
				} else {
					flag = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	
	public void updateExFExportedFileData(String lobName, int counter) {
		File sourceFile = null;
		File destinationFile = null;
		try {
			if (lobName.equalsIgnoreCase(LOMTConstant.HE_LOB) || lobName.equalsIgnoreCase(LOMTConstant.ENGLISH_LOB)
					|| lobName.equalsIgnoreCase(LOMTConstant.SCHOOL)) {
				// Copying exported file contents into new file for re-ingestion purpose
				String exportedFileName = getFileFromDirectory(LOMTConstant.EXPORTED_FILE_PATH);
				sourceFile = new File(LOMTConstant.EXPORTED_FILE_PATH + exportedFileName);
				destinationFile = new File(ExternalFrameworkTestData.EXF_DESTINATION_FILE_PATH
						+ ExternalFrameworkTestData.EXTERNAL_FRAMEWORK_TEMPLATE);
				if (copyExportFileIntoNewFile(sourceFile, destinationFile))
					updateExportedFileData(destinationFile, counter);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	
	private boolean updateExportedFileData(File file, int counter) throws Exception {
		FileInputStream isFile = null;
		FileOutputStream osFile = null;
		XSSFWorkbook workbook = null;
		XSSFSheet worksheet = null;
		Cell cell = null;
		
		boolean flag = false;

		switch (counter) {		
		case 0:
			//Goal framework Title, update process 
			isFile =  new FileInputStream(file);
			workbook = new XSSFWorkbook(isFile);
			worksheet = workbook.getSheetAt(0);
			cell = worksheet.getRow(LOMTConstant.ZERO).getCell(LOMTConstant.ZERO);
			String tempGO = ExternalFrameworkTestData.EXF_GOALFRAMEWORK_TITLE_UPDATED;
			setExfGoalframeworkName(tempGO);
			cell.setCellValue(tempGO);
			isFile.close();
			//update is done
			
			osFile =new FileOutputStream(file);
			workbook.write(osFile); //writing changes
			osFile.close();
			 
			flag = true;
			System.out.println("############ Counter 0 done ###########");
			break;	
		case 1:
			isFile =  new FileInputStream(file);
			workbook = new XSSFWorkbook(isFile);
			worksheet = workbook.getSheetAt(0);
			
			//Grade Low
			cell = worksheet.getRow(LOMTConstant.TWO).getCell(LOMTConstant.ONE);
			cell.setCellValue(ExternalFrameworkTestData.EXF_GRADE_LOW_K);
			
			cell = worksheet.getRow(LOMTConstant.THREE).getCell(LOMTConstant.ONE);
			cell.setCellValue(ExternalFrameworkTestData.EXF_GRADE_LOW_PK);
			
			cell = worksheet.getRow(LOMTConstant.FOUR).getCell(LOMTConstant.ONE);
			cell.setCellValue(ExternalFrameworkTestData.EXF_GRADE_LOW_1);
			
			//Grade High
			cell = worksheet.getRow(LOMTConstant.TWO).getCell(LOMTConstant.TWO);
			cell.setCellValue(ExternalFrameworkTestData.EXF_GRADE_HIGH_K_11);
			
			cell = worksheet.getRow(LOMTConstant.THREE).getCell(LOMTConstant.TWO);
			cell.setCellValue(ExternalFrameworkTestData.EXF_GRADE_HIGH_PK_10);
			
			cell = worksheet.getRow(LOMTConstant.FOUR).getCell(LOMTConstant.TWO);
			cell.setCellValue(ExternalFrameworkTestData.EXF_GRADE_HIGH_1_12);
			
			//Grade Low blank
			cell = worksheet.getRow(LOMTConstant.TEN).getCell(LOMTConstant.ONE);
			cell.setCellValue(" ");
			
			//Grade Low blank
			cell = worksheet.getRow(LOMTConstant.TEN).getCell(LOMTConstant.TWO);
			cell.setCellValue(" ");
			
			isFile.close();
			
			osFile =new FileOutputStream(file);
			workbook.write(osFile); //writing changes
			osFile.close();
			 
			flag = true;
			System.out.println("############ Counter 1 done ###########");
			break;		
		case 2:
			// Grade Title
			isFile =  new FileInputStream(file);
			workbook = new XSSFWorkbook(isFile);
			worksheet = workbook.getSheetAt(0);
			cell = worksheet.getRow(LOMTConstant.TWO).getCell(LOMTConstant.THREE);
			cell.setCellValue(ExternalFrameworkTestData.EXF_GRADE_TITLE);
			
			cell = worksheet.getRow(LOMTConstant.THREE).getCell(LOMTConstant.THREE);
			cell.setCellValue(ExternalFrameworkTestData.EXF_GRADE_TITLE);
			
			isFile.close();
			//update is done
			
			osFile =new FileOutputStream(file);
			workbook.write(osFile); //writing changes
			osFile.close();
			flag = true;
			System.out.println("############ Counter 2 done ###########");
			break;
		case 3:
			// Official Standard Code
			isFile =  new FileInputStream(file);
			workbook = new XSSFWorkbook(isFile);
			worksheet = workbook.getSheetAt(0);
			cell = worksheet.getRow(LOMTConstant.TWO).getCell(LOMTConstant.FOUR);
			cell.setCellValue(ExternalFrameworkTestData.EXF_OFF_STD_CODE);
			
			cell = worksheet.getRow(LOMTConstant.THREE).getCell(LOMTConstant.FOUR);
			cell.setCellValue(ExternalFrameworkTestData.EXF_OFF_STD_CODE);
			
			isFile.close();
			//update is done
			
			osFile =new FileOutputStream(file);
			workbook.write(osFile); //writing changes
			osFile.close();
			flag = true;
			System.out.println("############ Counter 3 done ###########");
			break;
		case 4:
			// Level 1
			isFile =  new FileInputStream(file);
			workbook = new XSSFWorkbook(isFile);
			worksheet = workbook.getSheetAt(0);
			cell = worksheet.getRow(LOMTConstant.TWO).getCell(LOMTConstant.FIVE);
			cell.setCellValue(ExternalFrameworkTestData.EXF_LEVEL_ONE);
			
			cell = worksheet.getRow(LOMTConstant.THREE).getCell(LOMTConstant.FIVE);
			cell.setCellValue(ExternalFrameworkTestData.EXF_LEVEL_ONE);
			
			isFile.close();
			//update is done
			
			osFile =new FileOutputStream(file);
			workbook.write(osFile); //writing changes
			osFile.close();
			flag = true;
			System.out.println("############ Counter 4 done ###########");
			break;
		case 5:
			// Level 2
			isFile =  new FileInputStream(file);
			workbook = new XSSFWorkbook(isFile);
			worksheet = workbook.getSheetAt(0);
			cell = worksheet.getRow(LOMTConstant.FOUR).getCell(LOMTConstant.SIX);
			cell.setCellValue(ExternalFrameworkTestData.EXF_LEVEL_TWO);
			
			cell = worksheet.getRow(LOMTConstant.SIX).getCell(LOMTConstant.SIX);
			cell.setCellValue(ExternalFrameworkTestData.EXF_LEVEL_TWO);
			
			isFile.close();
			//update is done
			
			osFile =new FileOutputStream(file);
			workbook.write(osFile); //writing changes
			osFile.close();
			flag = true;
			System.out.println("############ Counter 5 done ###########");
			break;
		case 6:
			// Lowest level
			isFile =  new FileInputStream(file);
			workbook = new XSSFWorkbook(isFile);
			worksheet = workbook.getSheetAt(0);
			cell = worksheet.getRow(LOMTConstant.FIVE).getCell(LOMTConstant.SEVEN);
			cell.setCellValue(ExternalFrameworkTestData.EXF_LEVEL_LOWEST);
			
			cell = worksheet.getRow(LOMTConstant.SEVEN).getCell(LOMTConstant.SEVEN);
			cell.setCellValue(ExternalFrameworkTestData.EXF_LEVEL_LOWEST);
			
			isFile.close();
			//update is done
			
			osFile =new FileOutputStream(file);
			workbook.write(osFile); //writing changes
			osFile.close();
			flag = true;
			System.out.println("############ Counter 6 done ###########");
			break;
		case 7:
			//Validation check, mandatory filed check
			isFile =  new FileInputStream(file);
			workbook = new XSSFWorkbook(isFile);
			worksheet = workbook.getSheetAt(0);
			
			//goal framework title - blank
			cell = worksheet.getRow(LOMTConstant.ZERO).getCell(LOMTConstant.ZERO); 
			cell.setCellValue(" ");
			
			//official Std. code - blank
			cell = worksheet.getRow(LOMTConstant.TWO).getCell(LOMTConstant.FOUR); 
			cell.setCellValue(" ");
			
			cell = worksheet.getRow(LOMTConstant.FOURTEEN).getCell(LOMTConstant.FOUR); 
			cell.setCellValue(" ");
			
			//Level 1 - blank
			cell = worksheet.getRow(LOMTConstant.TWO).getCell(LOMTConstant.FIVE); 
			cell.setCellValue(" ");
			
			cell = worksheet.getRow(LOMTConstant.FOURTEEN).getCell(LOMTConstant.FIVE); 
			cell.setCellValue(" ");
			
			//Level 2 - blank
			cell = worksheet.getRow(LOMTConstant.FOUR).getCell(LOMTConstant.SIX); 
			cell.setCellValue(" ");			
			
			isFile.close();
			//update is done
			
			osFile =new FileOutputStream(file);
			workbook.write(osFile); //writing changes
			osFile.close();
			flag = true;
			System.out.println("############ Counter 7 done ###########");
			break;
		case 8:
			//Wrong grade and level at same row
			isFile =  new FileInputStream(file);
			workbook = new XSSFWorkbook(isFile);
			worksheet = workbook.getSheetAt(0);
			
			//Wrong grade LOW
			cell = worksheet.getRow(LOMTConstant.TWO).getCell(LOMTConstant.ONE); 
			cell.setCellValue("ABC");
			
			//Wrong grade High
			cell = worksheet.getRow(LOMTConstant.TWO).getCell(LOMTConstant.TWO); 
			cell.setCellValue("13");
			
			//TEXT AT LEVEL 2 WHICH IS SAME TO LEVEL1
			cell = worksheet.getRow(LOMTConstant.TWO).createCell(LOMTConstant.SIX);
			cell.setCellValue("TEST");
			
			//Wrong grade LOW
			cell = worksheet.getRow(LOMTConstant.THREE).getCell(LOMTConstant.ONE); 
			cell.setCellValue("ABC");
			
			//Wrong grade High
			cell = worksheet.getRow(LOMTConstant.FOUR).getCell(LOMTConstant.TWO); 
			cell.setCellValue("13");
			isFile.close();
			//update is done
			
			osFile =new FileOutputStream(file);
			workbook.write(osFile); //writing changes
			osFile.close();
			flag = true;
			System.out.println("############ Counter 8 done ###########");
			break;	
		case 9:	
			// Addition of new EXF parent/child rows
			isFile =  new FileInputStream(file);
			workbook = new XSSFWorkbook(isFile);
			worksheet = workbook.getSheetAt(0);
			
			// Shift of one row all the rows starting from the 3th row
			worksheet.shiftRows(3, worksheet.getLastRowNum(), 1, true, true);
			worksheet.createRow(3);
			
			//Filling data at ROW 3
			cell = worksheet.getRow(LOMTConstant.THREE).createCell(LOMTConstant.ONE);
			cell.setCellValue(ExternalFrameworkTestData.EXF_GRADE_LOW_PK);
					
			cell = worksheet.getRow(LOMTConstant.THREE).createCell(LOMTConstant.TWO);
			cell.setCellValue(ExternalFrameworkTestData.EXF_GRADE_HIGH_1_12);
					
			cell = worksheet.getRow(LOMTConstant.THREE).createCell(LOMTConstant.THREE);
			cell.setCellValue("AAAS NEW ADDED GRADE Title");
					
			cell = worksheet.getRow(LOMTConstant.THREE).createCell(LOMTConstant.FOUR);
			cell.setCellValue("CCE A1.0 NEW ADDED Off Std. Code");
					
			cell = worksheet.getRow(LOMTConstant.THREE).createCell(LOMTConstant.FIVE);
			cell.setCellValue("Core Sequence Example B - NEW ADDED");
			
			worksheet.shiftRows(4, worksheet.getLastRowNum(), 1, true, true);
			worksheet.createRow(4);
			
			//Filling data at ROW 4
			cell = worksheet.getRow(LOMTConstant.FOUR).createCell(LOMTConstant.ONE);
			cell.setCellValue(ExternalFrameworkTestData.EXF_GRADE_LOW_PK);
					
			cell = worksheet.getRow(LOMTConstant.FOUR).createCell(LOMTConstant.TWO);
			cell.setCellValue(ExternalFrameworkTestData.EXF_GRADE_HIGH_1_12);
					
			cell = worksheet.getRow(LOMTConstant.FOUR).createCell(LOMTConstant.THREE);
			cell.setCellValue("AAAS NEW ADDED GRADE Title !@#$%^&*()_+{}|',./?><123");
					
			cell = worksheet.getRow(LOMTConstant.FOUR).createCell(LOMTConstant.FOUR);
			cell.setCellValue("CCE A1.0 NEW ADDED Off Std. Code !@#$%^&*()_+{}|',./?><123");
					
			cell = worksheet.getRow(LOMTConstant.FOUR).createCell(LOMTConstant.SIX);
			cell.setCellValue("LEVEL 2 Biology relies on applications of quantitative analysis and mathematical reasoning!@#$%^&*()_+{}|',./?><123 - NEW ADDED");
			
			worksheet.shiftRows(5, worksheet.getLastRowNum(), 1, true, true);
			worksheet.createRow(5);		
			
			//Filling data at ROW 5	
			cell = worksheet.getRow(LOMTConstant.FIVE).createCell(LOMTConstant.ONE);
			cell.setCellValue(ExternalFrameworkTestData.EXF_GRADE_LOW_PK);
			
			cell = worksheet.getRow(LOMTConstant.FIVE).createCell(LOMTConstant.TWO);
			cell.setCellValue(ExternalFrameworkTestData.EXF_GRADE_HIGH_1_12);
			
			cell = worksheet.getRow(LOMTConstant.FIVE).createCell(LOMTConstant.THREE);
			cell.setCellValue("AAAS NEW ADDED GRADE Title");
			
			cell = worksheet.getRow(LOMTConstant.FIVE).createCell(LOMTConstant.FOUR);
			cell.setCellValue("CCE A1.0 NEW ADDED Off Std. Code");
			
			cell = worksheet.getRow(LOMTConstant.FIVE).createCell(LOMTConstant.SEVEN);
			cell.setCellValue("LOWEST LEVEL Biology relies on applications of quantitative analysis and mathematical reasoning!@#$%^&*()_+{}|',./?><123 - NEW ADDED");
			
			isFile.close();
			//update is done
			
			osFile =new FileOutputStream(file);
			workbook.write(osFile); //writing changes
			osFile.close();
			
			flag = true;
			System.out.println("############ Counter 9 done ###########");
			break;
		case 10:	
			//Deletion of EXF Parent/Child row : de-scoped			
			break;	
		default :
			//Tags : not implemented yet
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

	public String getExfGoalframeworkName() {
		return exfGoalframeworkName;
	}

	public void setExfGoalframeworkName(String exfGoalframeworkName) {
		this.exfGoalframeworkName = exfGoalframeworkName;
	}
}
