package lomt.pearson.test_script.nals_sg;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import lomt.pearson.api.nals_sg.School;
import lomt.pearson.constant.LOMTConstant;
import lomt.pearson.constant.SchoolConstant;
import lomt.pearson.constant.TestCases;

public class SchoolTestScript {
	
	ExtentTest logger;
	ExtentReports reports = new ExtentReports(LOMTConstant.REPORT_SCHOOL_FILE_PATH, true);
	
	School school = new School();
	
	private static int year = 0;
	private static int yearReingestion = 0;
	private static int startYear = 1901;
	private static int endYear = 2000;

	@Test(priority = 0)
	public void setup() {
		school.openBrowser();
		school.login();
	}
	
	@Test(priority = 1)
	public void ingestionSchoolCurriculum() {
		logger = reports.startTest(SchoolConstant.SCHOOL_CURRICULUM_INGESTION_LOMT_09 + LOMTConstant.COMMA
				+ LOMTConstant.EMPTY_SPACE + "LOMT-09" + LOMTConstant.COMMA + LOMTConstant.EMPTY_SPACE + SchoolConstant.LOMT_458
				+ LOMTConstant.COMMA + LOMTConstant.EMPTY_SPACE + SchoolConstant.LOMT_338
				+ LOMTConstant.COMMA + LOMTConstant.EMPTY_SPACE + SchoolConstant.LOMT_1548);
		
		boolean browseFlag = school.schoolGlobalBrowsePage();
		if (browseFlag) {
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_09_01_VALID_ADMIN_USER);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_09_02_INGESTIONPAGE_ADMINUSER); 
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_09_03_INGESTIONPAGE_NONADMIN);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_09_04_INGESTIONPAGE_UI);
			
			logger.log(LogStatus.PASS, "TC-LOMT-338-01_Admin_Access_ Ingestion page.");
		} else {
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_09_02_INGESTIONPAGE_ADMINUSER);
			logger.log(LogStatus.FAIL, "TC-LOMT-338-01_Admin_Access_ Ingestion page.");
			System.exit(0);
		}
		
		boolean lobStructureFlag = school.getLOBAndStructure();
		if (lobStructureFlag) {
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_09_05_SCHOOL_GLOBAL_RADIO_BUTTON);
			logger.log(LogStatus.INFO, TestCases.TC_LOMT_09_06_SCHOOL_NORTH_AMERICA_RADIO_BUTTON);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_09_07_CLICKNEXTBUTTON_LINEOFBUSINESS_SELECTED);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_09_08_CLICKNEXTBUTTON_PRODUCTSELECTED);
			logger.log(LogStatus.INFO, TestCases.TC_LOMT_09_09_TRY_TO_SELECT_TWO_NOT_LINE_OF_BUSINESS_RADIOBUTTON);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_09_10_NEXTBTN_CURRICULUM_STANDARD);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_09_11_NEXTBTN_NO_CHECKBOXSELECTED);
			
		} else {
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_09_01_VALID_ADMIN_USER);
			
			logger.log(LogStatus.INFO, TestCases.TC_LOMT_09_06_SCHOOL_NORTH_AMERICA_RADIO_BUTTON);
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_09_07_CLICKNEXTBUTTON_LINEOFBUSINESS_SELECTED);
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_09_08_CLICKNEXTBUTTON_PRODUCTSELECTED);
			logger.log(LogStatus.INFO, TestCases.TC_LOMT_09_09_TRY_TO_SELECT_TWO_NOT_LINE_OF_BUSINESS_RADIOBUTTON);
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_09_10_NEXTBTN_CURRICULUM_STANDARD);
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_09_11_NEXTBTN_NO_CHECKBOXSELECTED);
			System.exit(0);
		}
		 year = startYear + (int)Math.round(Math.random() * (endYear - startYear));
		 System.out.println("year : "+year);
		 
		boolean metaFlag = school.getMetaDataFields(year);
		if (metaFlag) {
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_09_12_ALL_METADATA_FIELDS);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_09_13_MANDATORY_METADATA_FIELDS);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_09_14_SUBJECTDROPDOWN_VALUES);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_09_15_COUNTRYDROPDOWN_VALUES);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_09_16_ENTERVALUE_ALLFIELDS);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_09_17_NEXTBTN_VALUEENTERD_ALLFIELDS);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_09_18_NEXTBTN_VALUEIN_MANDETORYFIELDS);
		} else {
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_09_12_ALL_METADATA_FIELDS);
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_09_13_MANDATORY_METADATA_FIELDS);
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_09_14_SUBJECTDROPDOWN_VALUES);
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_09_15_COUNTRYDROPDOWN_VALUES);
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_09_16_ENTERVALUE_ALLFIELDS);
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_09_17_NEXTBTN_VALUEENTERD_ALLFIELDS);
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_09_18_NEXTBTN_VALUEIN_MANDETORYFIELDS);
			System.exit(0);
		}
		
		boolean ingestionFlag = school.schoolCurriculumIngestion();
		if (ingestionFlag) {
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_09_19_VALIDATION_NONMANDATORY);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_09_20_DRAGANDDROP_FILE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_09_21_INGEST_DRAGANDDROP);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_09_24_UPLOADFILE_SELECTBTN);
			
			logger.log(LogStatus.PASS, "TC-LOMT-458-05_Ingest AB xml");
			
			logger.log(LogStatus.PASS, "TC-LOMT-338-04_Ingest_DragAndDrop_SchoolGlobal");
			logger.log(LogStatus.PASS, "TC-LOMT-338-06_UploadFile_SelectBTN_SchoolGlobal");
		} else {
			logger.log(LogStatus.FAIL, "TC-LOMT-458-05_Ingest AB xml");
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_09_24_UPLOADFILE_SELECTBTN);
			
			logger.log(LogStatus.FAIL, "TC-LOMT-338-04_Ingest_DragAndDrop_SchoolGlobal");
			logger.log(LogStatus.FAIL, "TC-LOMT-338-06_UploadFile_SelectBTN_SchoolGlobal");
			return;
		}
		
		logger.log(LogStatus.INFO, "TC-LOMT-338-03_Ingest_DragAndDrop_SchoolNA");
		logger.log(LogStatus.INFO, "TC-LOMT-338-05_UploadFile_SelectBTN_SchoolNA");
		logger.log(LogStatus.INFO, "TC-LOMT-338-07_VerifyDataOnUI_SchoolNA");
		logger.log(LogStatus.INFO, "TC-LOMT-338-09_VerifyState_UI_SchoolNA");
		
		//Upload incorrect file
		school.getLOBAndStructure();
		school.getMetaDataFields(year);
		boolean ingestionWrongFlag = school.ingestWrongFile();
		if(ingestionWrongFlag) {
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_09_22_ERROR_DRAGANDDROP_WRONGFILE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_09_23_ERROR_DRAGANDDROP_WRONGFORMATFILE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_09_25_ERROR_WRONGFILE_SELECTBTN);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_09_26_ERROR_WRONGFORMATFILE_SELECTBTN);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_09_27_ERRORLOGS_DRAGANDDROP);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_09_28_ERRORLOGS_SELECTBTN);
		} else {
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_09_22_ERROR_DRAGANDDROP_WRONGFILE);
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_09_23_ERROR_DRAGANDDROP_WRONGFORMATFILE);
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_09_25_ERROR_WRONGFILE_SELECTBTN);
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_09_26_ERROR_WRONGFORMATFILE_SELECTBTN);
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_09_27_ERRORLOGS_DRAGANDDROP);
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_09_28_ERRORLOGS_SELECTBTN);
		}
		
		//verify ingested data
		boolean verifyFlag =  school.verifyingestedDataUI(true, year, logger);
		if (verifyFlag) {
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_09_30_VERIFYUI_AFTERINGESTION);
			
			logger.log(LogStatus.PASS, "TC-LOMT-338-08_VerifyDataOnUI_SchoolGlobal");
			logger.log(LogStatus.PASS, "TC-LOMT-338-10_VerifyState_UI_SchoolGLobal");
		} else {
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_09_30_VERIFYUI_AFTERINGESTION);
			
			logger.log(LogStatus.FAIL, "TC-LOMT-338-08_VerifyDataOnUI_SchoolGlobal");
			logger.log(LogStatus.FAIL, "TC-LOMT-338-10_VerifyState_UI_SchoolGLobal");
		}
		school.getHomePage();
		reports.endTest(logger);
		reports.flush();
	}
	
	@Test(priority = 2)
	public void exportSchoolCurriculum() {
		logger = reports.startTest(SchoolConstant.SCHOOL_CURRICULUM_EXPORT_LOMT_614);
		
		logger.log(LogStatus.INFO, "NALS is wired off : De-scoped");
		logger.log(LogStatus.INFO, "TC-LOMT-612_01_Verify_Export_Option_In_Actions_For_CurriculumStandard_SchoolNA");
		logger.log(LogStatus.INFO, "TC-LOMT-612_02_Verify_Export_CurriculumStandard_To_ExcelFile_SchoolNA");
		logger.log(LogStatus.INFO, "TC-LOMT-612_03_Verify_Curriculum Standard_written_To_ExcelSpreadsheet_SchoolNA");
		logger.log(LogStatus.INFO, "TC-LOMT-612_04_Verify_Title_Of_Excel_Spreadsheet_Is_curriculumStandardLabel_SchoolNA");
		logger.log(LogStatus.INFO, "TC-LOMT-612_05_Verify_cell A1_label for the standard_In_ExcelSpreadsheet_SchoolNA");
		logger.log(LogStatus.INFO, "TC-LOMT-612_06_Verify_cellB1_URN_of_Standard_In_ExcelSpreadsheet_SchoolNA");
		logger.log(LogStatus.INFO, "TC-LOMT-612_07_Verify_cell_A2 has column_Heading_Unique ID_In_ExcelSpreadsheet_SchoolNA");
		logger.log(LogStatus.INFO, "TC-LOMT-612_08_Verify_cell_B2 has column_Heading_Grade Low_In_ExcelSpreadsheet_SchoolNA");
		logger.log(LogStatus.INFO, "TC-LOMT-612_09_Verify_cell_C2 has column_Heading_Grade High_In_ExcelSpreadsheet_SchoolNA");
		logger.log(LogStatus.INFO, "TC-LOMT-612_10_Verify_cell_D2 has column_Heading_Grade Title_In_ExcelSpreadsheet_SchoolNA");
		logger.log(LogStatus.INFO, "TC-LOMT-612_11_Verify_cell_E2 has column_Heading_OfficialStandardsCode_In_ExcelSpreadsheet_SchoolNA");
		logger.log(LogStatus.INFO, "TC-LOMT-612_12_Verify_cell_F2 has column_Headings_ForEachLevel_Culminating_LowestLevel_In_ExcelSpreadsheet_SchoolNA");
		logger.log(LogStatus.INFO, "TC-LOMT-612_13_Verify_Last_cell_column_Heading_Tags_In_ExcelSpreadsheet_SchoolNA");
		logger.log(LogStatus.INFO, "TC-LOMT-612_14_Verify_Educational_goal_row3_onwards_In_ExcelSpreadsheet_SchoolNA");
		logger.log(LogStatus.INFO, "TC-LOMT-612_15_Verify_RenderedRowData_In_ExcelSpreadsheet_SchoolNA");
		
		
		//school.searchAndExportIngestedCurriculumData(year, logger, "medium");  // with 3 standard
		school.searchAndExportIngestedCurriculumData(year, logger, "small");  // with 1 standard
		
		reports.endTest(logger);
		reports.flush();
	}
	
	@Test(priority = 3)
	public void reIngestionSchoolCurriculum() {
		logger = reports.startTest(SchoolConstant.CURRICULUM_REINGESTION);
		
		yearReingestion = startYear + (int)Math.round(Math.random() * (endYear - startYear));
		System.out.println("yearReingestion : "+yearReingestion);
		
		school.schoolGlobalBrowsePage();
		school.getLOBAndStructure();
		school.getMetaDataReingestionFields(yearReingestion);
		
		//Update goal framework like name and meta data
		school.reIngestionCurriculumStandard(logger, SchoolConstant.C_USECASE_1); 
		
		//update fields like description, state num, update parent/child and grade description 
		school.getLOBAndStructure();
		school.getMetaDataFields(yearReingestion);
		school.reIngestionCurriculumStandard(logger, SchoolConstant.C_USECASE_2);

		//Add new node
		school.getLOBAndStructure();
		school.getMetaDataFields(yearReingestion);
		school.reIngestionCurriculumStandard(logger, SchoolConstant.C_USECASE_3);
		
		//delete node
		school.getLOBAndStructure();
		school.getMetaDataFields(yearReingestion);
		school.reIngestionCurriculumStandard(logger, SchoolConstant.C_USECASE_4);
		
		//verify re-ingeted data
		school.verifyReingestedDataUI(logger, yearReingestion);
		
		reports.endTest(logger);
		reports.flush();
	}
	
	@Test(priority = 4)
	public void tearDown() {
		school.closeDriverInstance();	
	}
	
}
