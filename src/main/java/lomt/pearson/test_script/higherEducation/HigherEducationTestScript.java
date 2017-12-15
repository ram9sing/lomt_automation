package lomt.pearson.test_script.higherEducation;

import lomt.pearson.api.he.HigherEducation;
import lomt.pearson.constant.HEConstant;
import lomt.pearson.constant.LOMTConstant;
import lomt.pearson.constant.TestCases;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class HigherEducationTestScript {
	
	ExtentTest logger;
	ExtentReports reports = new ExtentReports(LOMTConstant.REPORT_HE_FILE_PATH, true);
	
	HigherEducation ingestionAndExport = new HigherEducation();
	String heGoalframeworkName = null;
	private static int startYear = 1300;
	private static int endYear = 1400;
	
	@Test(priority = 0)
	public void setup() {
		ingestionAndExport.openBrowser();
		ingestionAndExport.login();
	}
	
	//LOMT-457
	@Test(priority = 1)
	public void ingestionHigherEducation() {
		logger = reports.startTest(LOMTConstant.HE_LOB + LOMTConstant.EMPTY_SPACE + LOMTConstant.EO_INGESTION,
				LOMTConstant.LOMT_457 + LOMTConstant.COMMA + LOMTConstant.EMPTY_SPACE + LOMTConstant.TC_COUNT_HE_457
				 + LOMTConstant.EMPTY_SPACE + LOMTConstant.LOMT_718+ LOMTConstant.EMPTY_SPACE + LOMTConstant.LOMT_656+LOMTConstant.EMPTY_SPACE+"LOMT-717");
		
		boolean mIngestionFlag = ingestionAndExport.higherEducationLOBBrowsePage();
		if (mIngestionFlag) {
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_457_01_ADMIN_ACCESS_HE_INGESTION_PAGE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_457_02_NON_ADMIN_ACCESS_HE_INGESTION_PAGE);
			logger.log(LogStatus.PASS, LOMTConstant.DE_SCOPED, TestCases.TC_LOMT_457_03_ADMIN_USER_NOT_ACCESS_HE_INGESTION_PAGE);
			logger.log(LogStatus.PASS, LOMTConstant.DE_SCOPED, TestCases.TC_LOMT_457_04_NON_ADMIN_USER_ACCESS_HE_INGESTION_PAGE);
		} else {
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_457_01_ADMIN_ACCESS_HE_INGESTION_PAGE);
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_457_02_NON_ADMIN_ACCESS_HE_INGESTION_PAGE);
			logger.log(LogStatus.SKIP, LOMTConstant.DE_SCOPED, TestCases.TC_LOMT_457_03_ADMIN_USER_NOT_ACCESS_HE_INGESTION_PAGE);
			logger.log(LogStatus.SKIP, LOMTConstant.DE_SCOPED, TestCases.TC_LOMT_457_04_NON_ADMIN_USER_ACCESS_HE_INGESTION_PAGE);
			return;
		}
		
		
		boolean flag = ingestionAndExport.createUploadStructureFirstPage();
		if (flag) {
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_457_05_LOB_HIGHER_EDUCATION_RADIO_BUTTON);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_457_06_LOB_EDUCATIONAL_OBJECTIVE_RADIO_BUTTON);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_457_07_LOB_EDUCATIONAL_OBJECTIVE_RADIO_BUTTON_NEGATIVE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_457_08_LOB_EDUCATIONAL_OBJECTIVE_FIRST_PAGE_UPLOAD_PROCESS);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_457_09_NEXT_BUTTON_LOB_HIGHER_EDUCATION);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_457_09_NEXT_BUTTON_LOB_HIGHER_EDUCATION);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_457_11_LOB_EDUCATIONAL_OBJECTIVE_SECOND_PAGE_UPLOAD_PROCESS);
		} else {
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_457_05_LOB_HIGHER_EDUCATION_RADIO_BUTTON);
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_457_06_LOB_EDUCATIONAL_OBJECTIVE_RADIO_BUTTON);
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_457_07_LOB_EDUCATIONAL_OBJECTIVE_RADIO_BUTTON_NEGATIVE);
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_457_08_LOB_EDUCATIONAL_OBJECTIVE_FIRST_PAGE_UPLOAD_PROCESS);
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_457_09_NEXT_BUTTON_LOB_HIGHER_EDUCATION);
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_457_09_NEXT_BUTTON_LOB_HIGHER_EDUCATION);
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_457_11_LOB_EDUCATIONAL_OBJECTIVE_SECOND_PAGE_UPLOAD_PROCESS);
		}
		heGoalframeworkName = LOMTConstant.HE_INGESTION_FILE_NAME + String.valueOf(startYear + (int)Math.round(Math.random() * (endYear - startYear)));
		System.out.println("heGoalframeworkName : "+heGoalframeworkName);
		
		boolean metadataFlag = ingestionAndExport.createUploadStructureMetaDataPage(heGoalframeworkName);
		if (metadataFlag) {
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_457_12_METADATA_FIELDS);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_457_13_LEARNING_EXPERIENCE_NAME_TITLE_METADATA_FIELDS_VALUE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_457_14_LEARNING_EXPERIENCE_NAME_TITLE_METADATA_MAX_VALUE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_457_15_LEARNING_EXPERIENCE_NAME_TITLE_METADATA_SPECIAL_CHARACTER_VALUE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_457_16_LEARNING_EXPERIENCE_NAME_TITLE_METADATA_ALPHA_NUMERIC_VALUE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_457_17_SELECT_ALL_METADATA_VALUES);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_457_18_NEXT_BUTTON_METADATA_PAGE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_457_19_NEXT_BUTTON_SOME_METADATA_DROPDOWN_NOT_SELECTED); 
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_457_20_NEXT_BUTTON_LEARNING_EXPERIENCE_TEXT_MISSING);
		} else {
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_457_12_METADATA_FIELDS);
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_457_13_LEARNING_EXPERIENCE_NAME_TITLE_METADATA_FIELDS_VALUE);
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_457_14_LEARNING_EXPERIENCE_NAME_TITLE_METADATA_MAX_VALUE);
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_457_15_LEARNING_EXPERIENCE_NAME_TITLE_METADATA_SPECIAL_CHARACTER_VALUE);
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_457_16_LEARNING_EXPERIENCE_NAME_TITLE_METADATA_ALPHA_NUMERIC_VALUE);
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_457_17_SELECT_ALL_METADATA_VALUES);
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_457_18_NEXT_BUTTON_METADATA_PAGE);
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_457_19_NEXT_BUTTON_SOME_METADATA_DROPDOWN_NOT_SELECTED); 
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_457_20_NEXT_BUTTON_LEARNING_EXPERIENCE_TEXT_MISSING);
			return;
		}
		
		boolean wrongFileFlag = ingestionAndExport.educationalObjectiveIngestion(LOMTConstant.INVALID_FORMAT_FILE); //other than excel file
		if (wrongFileFlag) {
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_457_21_UPLOAD_FILE_PAGE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_457_22_VERIFY_UPLOAD_PROCESS_ON_THIRD_PAGE);
			logger.log(LogStatus.PASS, LOMTConstant.DE_SCOPED, TestCases.TC_LOMT_457_23_DRAG_AND_DROP_FILE);
			logger.log(LogStatus.PASS, LOMTConstant.DE_SCOPED, TestCases.TC_LOMT_457_24_DRAG_AND_DROP_FILE_ERROR_WHEN_WRONG_FILE);
			logger.log(LogStatus.PASS, LOMTConstant.DE_SCOPED, TestCases.TC_LOMT_457_25_DRAG_AND_DROP_FILE_ERROR_WHEN_WRONG_FORMAT_FILE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_457_26_SELECT_FILE_LINK);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_457_27_SELECT_FILE_LINK_ERROR_WITH_WRONG_FILE_SELECT);
		} else {
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_457_21_UPLOAD_FILE_PAGE);
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_457_22_VERIFY_UPLOAD_PROCESS_ON_THIRD_PAGE);
			logger.log(LogStatus.FAIL, LOMTConstant.DE_SCOPED, TestCases.TC_LOMT_457_23_DRAG_AND_DROP_FILE);
			logger.log(LogStatus.FAIL, LOMTConstant.DE_SCOPED, TestCases.TC_LOMT_457_24_DRAG_AND_DROP_FILE_ERROR_WHEN_WRONG_FILE);
			logger.log(LogStatus.FAIL, LOMTConstant.DE_SCOPED, TestCases.TC_LOMT_457_25_DRAG_AND_DROP_FILE_ERROR_WHEN_WRONG_FORMAT_FILE);
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_457_26_SELECT_FILE_LINK);
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_457_27_SELECT_FILE_LINK_ERROR_WITH_WRONG_FILE_SELECT);
		}
		
		//Wrong format, like fields level validation, headers missing and so on.
		ingestionAndExport.createUploadStructureFirstPage();
		ingestionAndExport.createUploadStructureMetaDataPage(heGoalframeworkName);
		boolean wrongFileFormatFlag = ingestionAndExport.educationalObjectiveIngestion(LOMTConstant.VALIDATION_MISSED); 
		if (wrongFileFormatFlag) {
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_457_28_SELECT_FILE_LINK_ERROR_WITH_WRONG_FILE_FORMAT_SELECT);
		} else {
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_457_28_SELECT_FILE_LINK_ERROR_WITH_WRONG_FILE_FORMAT_SELECT);
		}
		
		//Ingest correct file
		ingestionAndExport.createUploadStructureFirstPage();
		ingestionAndExport.createUploadStructureMetaDataPage(heGoalframeworkName);
		boolean ingestSuccessFlag = ingestionAndExport.educationalObjectiveIngestion(LOMTConstant.INGEST); 
		if (ingestSuccessFlag) {
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_457_29_NEXT_BUTTON_UPLAOD_FILE_LINK);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_457_29_NEXT_BUTTON_DRAG_AND_DROP_FILE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_457_30_UPLOAD_PROCESS_CHECK_AFTER_INGESTION);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_457_31_DONE_BUTTON_ON_REVIEW_OUTCOME);
			
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_718_01_INGESTIONSUCCESSFUL_LOOREO_BLANK);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_656_01_VALID_MISCONCEPTION_INGEST);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_656_02_BLANKMISCONCEPTION_INGEST_N);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_656_03_BLANKMISCONCEPTION_INGEST_Y);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_656_04_SPECIALCHARS_MISCONCEPTION_INGEST);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_656_05_LONGCHARS_MISCONCEPTION_INGEST);
		} else {
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_457_29_NEXT_BUTTON_UPLAOD_FILE_LINK);
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_457_29_NEXT_BUTTON_DRAG_AND_DROP_FILE);
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_457_30_UPLOAD_PROCESS_CHECK_AFTER_INGESTION);
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_457_31_DONE_BUTTON_ON_REVIEW_OUTCOME);
			
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_718_01_INGESTIONSUCCESSFUL_LOOREO_BLANK);
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_656_01_VALID_MISCONCEPTION_INGEST);
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_656_02_BLANKMISCONCEPTION_INGEST_N);
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_656_03_BLANKMISCONCEPTION_INGEST_Y);
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_656_04_SPECIALCHARS_MISCONCEPTION_INGEST);
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_656_05_LONGCHARS_MISCONCEPTION_INGEST);
		}
		
		if (ingestSuccessFlag) {
			ingestionAndExport.verifyHEIngestedDataUI(logger, heGoalframeworkName); //ExtentTest logger
		} else {
			logger.log(LogStatus.FAIL, "HE EO data verification on UI is failed");
		}
		
		//Deffered TCs
		logger.log(LogStatus.INFO, "TC-LOMT-717-01_IngestionSuccessful_PreRequisite "+ "Deferred");
		logger.log(LogStatus.INFO, "TC-LOMT-717-02_IngestionSuccessful_PreRequisite_AboveOrBelow "+ "Deferred");
		logger.log(LogStatus.INFO, "TC-LOMT-717-03_IngestionSuccessful_PreRequisite_InterchangeReference "+ "Deferred");
		logger.log(LogStatus.INFO, "TC-LOMT-717-04_IngestionSuccessful_PreRequisite_InterchangeReferenceVerify_UI "+ "Deferred");
		
		ingestionAndExport.gethomePage();
		reports.endTest(logger);
		reports.flush();
	}
	
	@Test(priority = 2)
	public void exportHEEducationalObjective() {
		logger = reports.startTest(HEConstant.HE_EO_EXPORT);
		ingestionAndExport.exportHEEducationalObjective(logger, heGoalframeworkName);
		
		reports.endTest(logger);
		reports.flush();
	}
	
	@Test(priority = 3)
	public void reingestionHEEducationalObjective() {
		logger = reports.startTest("HE Educational Objective Reingestion, LOMT-815, 3 TCs");
		
		ingestionAndExport.heReingestion(logger, heGoalframeworkName);	
		
		reports.endTest(logger);
		reports.flush();
	}
	
	@Test(priority = 4)
	public void tearDown() {
		ingestionAndExport.closeDriverInstance();
	}
	
}
