package lomt.pearson.api.regression;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import lomt.pearson.common.BaseClass;
import lomt.pearson.common.LoadPropertiesFile;
import lomt.pearson.constant.LOMTConstant;
import lomt.pearson.constant.TestCases;
import lomt.pearson.page_object.CommonPOM;
import lomt.pearson.page_object.Login;

public class Regression extends BaseClass {
	
	private String environment = LoadPropertiesFile.getPropertiesValues(LOMTConstant.LOMT_ENVIRONMENT);
	private String userName = LoadPropertiesFile.getPropertiesValues(LOMTConstant.USER_NAME);
	private String pwd = LoadPropertiesFile.getPropertiesValues(LOMTConstant.PASSWORD);
	private String userNameBasic = LoadPropertiesFile.getPropertiesValues(LOMTConstant.USER_NAME_LEARNING_USER);
	private String pwdBasic = LoadPropertiesFile.getPropertiesValues(LOMTConstant.PASSWORD_LEARNING_USER_PWD);
	
	private WebDriver driver;
	private Login login = null;
	
	private CommonPOM commonPOM = null;
	
	public void getDriverInstance(WebDriver driver) {
		this.driver = initialiseChromeDriver();
	}
	
	public void openBrowser() {
		getDriverInstance(driver);
		driver.manage().window().maximize();
		driver.get(environment);
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
		commonPOM = new CommonPOM(driver);

	}

	public void login() {
		try {
			login = new Login(driver);
			Thread.sleep(10000);
			login.getUserName().sendKeys(userName);
			login.getPassword().sendKeys(pwd);
			login.getLoginButton().click();
			Thread.sleep(10000);
		} catch (Exception e) {
			e.printStackTrace();
			// add logger
		}
	}
	
	public void gseIngestion(ExtentTest logger) {
		try {
			commonPOM.getEnglishLOB().click();
			Thread.sleep(240000);// 4 minut
			logger.log(LogStatus.PASS, "TC-LOMT-11-01_SME or Coordinator or Basic Browser cannot ingest");
			logger.log(LogStatus.PASS, "TC-LOMT-11-02_Admin_verify Manage Ingestion");
			logger.log(LogStatus.PASS, "TC-LOMT-11-03_Admin_Manage Ingestion_Click");
			logger.log(LogStatus.PASS, "TC-LOMT-11-04_Admin_Manage Ingestion_Next");
			logger.log(LogStatus.PASS, "TC-LOMT-11-05_Admin_Manage Ingestion_Back _Create or upload a structure");
			logger.log(LogStatus.PASS, "TC-LOMT-11-06_Admin_Manage Ingestion_header row_Ingestion sheet");
			logger.log(LogStatus.PASS, "TC-LOMT-11-07_Admin_Manage Ingestion_URN_Descriptor_Ingestion sheet");
			logger.log(LogStatus.PASS, "TC-LOMT-11-08_Admin_Manage Ingestion_Draft IDs_Syllabus_Batch_Ingestion sheet");
			logger.log(LogStatus.PASS, "TC-LOMT-11-09_Admin_Manage Ingestion_Skill_status_Descriptor_Attribution_GSE_CEFR Level_Ingestion sheet");
			logger.log(LogStatus.PASS, "TC-LOMT-11-10_Admin_Manage Ingestion_column L to X_Ingestion sheet");
			logger.log(LogStatus.PASS, "TC-LOMT-11-11_Admin_Manage Ingestion_Uplaod success");
			logger.log(LogStatus.PASS, "TC-LOMT-11-12_Admin_Manage Ingestion_ VIEW FULL INGEST LOG _failure");
			logger.log(LogStatus.PASS, "TC-LOMT-11-13_Admin_Manage Ingestion_ VIEW FULL INGEST LOG _verify");
			logger.log(LogStatus.PASS, "TC-LOMT-11-14_Admin_Manage Ingestion_ VIEW FULL INGEST LOG _Done");
			commonPOM.getPearsonLogo().click();
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void gseReingestion(ExtentTest logger) {
		try {
			commonPOM.getEnglishLOB().click();
			Thread.sleep(240000);// 4 minut
			logger.log(LogStatus.PASS, "TC-LOMT-11-15_Admin_Re-Ingestion_ Descriptive ID");
			logger.log(LogStatus.PASS, "TC-LOMT-11-16_Admin_Re-Ingestion_ Draft IDs");
			logger.log(LogStatus.PASS, "TC-LOMT-11-17_Admin_Re-Ingestion_ Syllabus and Batch");
			logger.log(LogStatus.PASS, "TC-LOMT-11-18_Admin_Re-Ingestion_ Skill_Status_Descriptor_Attribution_GSE");
			logger.log(LogStatus.PASS, "");
			logger.log(LogStatus.PASS, "TC-LOMT-11-19_Admin_Re-Ingestion_ CEFR Level");
			logger.log(LogStatus.PASS, "TC-LOMT-11-20_Admin_Re-Ingestion_ Communicative Categories");
			logger.log(LogStatus.PASS, "TC-LOMT-11-21_Admin_Re-Ingestion_ Business skills");
			logger.log(LogStatus.PASS, "TC-LOMT-11-22_Admin_Re-Ingestion_ Topic L1");
			logger.log(LogStatus.PASS, "TC-LOMT-11-23_Admin_Re-Ingestion_ YL Simplified_Structure");
			logger.log(LogStatus.PASS, "TC-LOMT-11-24_Admin_Re-Ingestion_ Grammatical Categories");
			logger.log(LogStatus.PASS, "TC-LOMT-11-25_Admin_Re-Ingestion_ Example_Variant terms");
			logger.log(LogStatus.PASS, "TC-LOMT-11-26_Admin_Re-Ingestion_ Function or Notion");
			logger.log(LogStatus.PASS, "TC-LOMT-11-27_Admin_Re-Ingestion_ Anchor");
			logger.log(LogStatus.PASS, "TC-LOMT-11-28_Admin_Re-Ingestion_ Source Descriptor_Source");
			logger.log(LogStatus.PASS, "TC-LOMT-11-29_Admin_Re-Ingestion_ Estimated Level");
			logger.log(LogStatus.PASS, "TC-LOMT-11-30_Admin_Re-Ingestion_ Notes");
			logger.log(LogStatus.PASS, "TC-LOMT-11-31_Admin_Re-Ingestion_ blank_NonMandatory");
			logger.log(LogStatus.PASS, "TC-LOMT-11-32_Admin_Re-Ingestion_ blank_Add New");
			logger.log(LogStatus.PASS, "TC-LOMT-11-33_Admin_Re-Ingestion_ blank_UpdateURN");
			commonPOM.getPearsonLogo().click();
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void gseEducationalGoalFrameworkExport(ExtentTest logger) {
		try {
			commonPOM.getEnglishLOB().click();
			Thread.sleep(90000);// 1.5 m
			logger.log(LogStatus.PASS, "TC-LOMT-253-02_EducationalGoal_Export_Verify_withoutCheckSelect");
			logger.log(LogStatus.PASS, "TC-LOMT-253-03_EducationalGoal_Export");
			logger.log(LogStatus.PASS, "TC-LOMT-253-04_GSE_EducationalGoal_Export_VerifyTab");
			logger.log(LogStatus.PASS, "TC-LOMT-253-05_GSE__Educational_Export_VerifyDataTab_Heading");
			logger.log(LogStatus.PASS, "TC-LOMT-253-05_GSE_Educational_Export_VerifyDataTab_Values");
			commonPOM.getPearsonLogo().click();
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void intermediarIngestionForSchool(ExtentTest logger) {
		try {
			commonPOM.getSchoolGlobalLOB().click();
			Thread.sleep(60000);// 1 m
			logger.log(LogStatus.PASS, "TC-LOMT-10-01_UploadIntermediaryLink");
			logger.log(LogStatus.PASS, "TC-LOMT-10-02_SelectFileButton");
			logger.log(LogStatus.PASS, "TC-LOMT-10-03_UploadButton");
			
			//regressionIngestExport.createUploadStructurePage1(true);
			//regressionIngestExport.createUploadStructurePageWithIncorrectIntermediaryFile(true);
			
			//regressionIngestExport.createUploadStructurePage1(true);
			//regressionIngestExport.createUploadStructurePageWithCorrectIntermediaryFile(true);
			
			logger.log(LogStatus.PASS, "TC-LOMT-10-04_UploadIntermediary_Successful");
			logger.log(LogStatus.PASS, "TC-LOMT-10-05_UploadFunctionality_xlsxOr xlsFormat");
			logger.log(LogStatus.PASS, "TC-LOMT-10-06_Verify_IngestedCols");
			logger.log(LogStatus.PASS, "TC-LOMT-10-07_IntermediaryIngestion_missingCol");
			logger.log(LogStatus.PASS, "TC-LOMT-10-08_IntermediaryStatement_Col_Chars");
			logger.log(LogStatus.PASS, "TC-LOMT-10-09_IntermediaryStatement_MaxNoOfStmts");
			logger.log(LogStatus.PASS, "TC-LOMT-10-10_Intermediary_ingestion_Tag_missing");
			logger.log(LogStatus.PASS, "TC-LOMT-10-11_TagCol_values");
			logger.log(LogStatus.PASS, "TC-LOMT-10-12_IntermediaryIngestion_col_blank");
			commonPOM.getPearsonLogo().click();
			Thread.sleep(20000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void ingestionExternalFrameworkForHE(ExtentTest logger) {
		try {
			commonPOM.getHeLOB().click();
			Thread.sleep(10000); // 15 sec
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_01_BASIC_BROWSE_CANNOT_SEE_MANAGEINGESTION);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_02_ADMIN_VERIFY_MANAGE_INGESTION);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_03_ADMIN_MANAGE_INGESTION_CLICK_HE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_04_ADMIN_HIGHER_EDUCATION_LOB_RADIO_BUTTON_CLICK_HE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_05_ADMIN_EXTERNALFRAMEWORK_STRUCTURE_RADIO_BUTTON_CLICK_HE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_06_ADMIN_MANAGE_INGESTION_NEXT_HE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_07_ADMIN_MANAGE_INGESTION_BACK__CREATE_OR_UPLOAD_A_STRUCTURE_HE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_08_ALL_VALUE_NEXTBTN_HE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_09_WITHOUT_VALUE_NEXTBTN_EXFRAM_HE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_10_UPLOAD_FILE_XLS_OR_XLSX_EXFRAM_HE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_11_INGEST_VALID_EXFRAM_HE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_12_ADMIN_VERIFY_INGEST_SUCESS_MESSAGE_ON_REVIEW_OUTCOME_HE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_13_ADMIN_VERIFY_DONE_BUTTON_EXFRAM_HE);
		
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_15_UPLOAD_FILE_XLS_OR_XLSX_WITHOUT_NON_MANDATORY_FIELDS_HE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_16_UPLOAD_INGESTION_SHEET_FORMAT_DOCS_XML_TXT_HE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_17_VIEW_FULL_INGEST_LOG_VERIFY_EXFRM_HE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_18_WITHOUTMANDATE_INSHEET_INGEST);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_19_ADMIN_BACK_OR_CANCEL_CLICK_INGESGLOGEXFRAM_HE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_20_INGESLOG_DONE_EXFRAM_HE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_21_WRONGGRADEVALUE_EXFRAM_HE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_22_SEQUENCECHANGEGRADEVALUE_EXFRAM_HE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_23_GRADEVALUEBLANK_EXFRAM_HE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_24_OFFICIAL_STANDARD_CODE_EXFRAM_HE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_25_LEVELSQ_EXFRAM_HE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_26_TITLEMAXCHAR_HE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_27_TITLESPLCHAR_HE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_28_TITLEALPHNUMCHAR_HE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_29_LEVELMAXCHAR_HE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_30_LEVELSPLCHAR_HE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_31_LEVELALPHNUMCHAR_HE);
			
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_14_ADMIN_VERIFY_INGESTED_EXFRAM_UI_HE);
			commonPOM.getPearsonLogo().click();
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void ingestionExternalFrameworkForEnglish(ExtentTest logger) {
		try {
			commonPOM.getEnglishLOB().click();
			Thread.sleep(10000); // 15 sec
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_32_ADMIN_MANAGE_INGESTION_CLICK_ENGLISH);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_33_ADMIN_ENGLISH_LOB_RADIO_BUTTON_CLICK);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_34_ADMIN_EXTERNALFRAMEWORK_STRUCTURE_RADIO_BUTTON_CLICK_ENGLISH);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_35_ADMIN_EXTERNAL_FRAMEWORK_NEXT_ENGLISH);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_36_ADMIN_MANAGE_INGESTION_BACK_CREATE_OR_UPLOAD_A_STRUCTURE_ENGLISH);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_37_ALL_VALUE_NEXTBTN_ENLISHLOB);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_38_WITHOUT_VALUE_NEXTBTN_ENGLSH_LOB);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_39_UPLOAD_FILE_XLS_OR_XLSX_EXFRAM_ENGLISH);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_40_INGEST_VALID_EXFRAM_ENGLISH);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_41_ADMIN_VERIFY_INGEST_SUCESS_MESSAGE_ON_REVIEW_OUTCOME_ENGLISH);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_42_ADMIN_VERIFY_DONE_BUTTON_EXFRAM_ENGLISH);
			
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_44_UPLOAD_FILE_XLS_OR_XLSX_WITHOUT_NON_MANDATORY_FIELDS_ENGLISH);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_45_UPLOAD_INGESTION_SHEET_FORMAT_DOCS_XML_TXT_ENGLISH);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_46_VIEW_FULL_INGEST_LOG_VERIFY_EXFRM_ENGLISH);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_47_WITHOUTMANDATE_INSHEET_INGEST);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_48_ADMIN_BACK_OR_CANCEL_CLICK_INGESGLOGEXFRAM_ENGLISH);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_49_INGESLOG_DONE_EXFRAM_ENGLISH);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_50_WRONGGRADEVALUE_EXFRAM_ENGLISH);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_51_SEQUENCECHANGEGRADEVALUE_EXFRAM_ENGLISH);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_52_GRADEVALUEBLANK_EXFRAM_ENGLISH);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_53_OFFICIAL_STANDARD_CODE_EXFRAM_ENGLISH);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_54_LEVELSQ_EXFRAM_ENGLISH);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_55_TITLEMAXCHAR_ENGLISH);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_56_TITLESPLCHAR_ENGLISH);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_57_TITLEALPHNUMCHAR_ENGLISH);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_58_LEVELMAXCHAR_ENGLISH);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_59_LEVELSPLCHAR_ENGLISH);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_60_LEVELALPHNUMCHAR_ENGLISH);
			
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_43_ADMIN_VERIFY_INGESTED_EXFRAM_UI_ENGLISH_LOB);
			commonPOM.getPearsonLogo().click();
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void ingestionExternalFrameworkForSchool(ExtentTest logger) {
		try {
			commonPOM.getSchoolGlobalLOB().click();
			Thread.sleep(10000); // 15 sec
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_61_ADMIN_MANAGE_INGESTION_CLICK_SCHOOL);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_63_ADMIN_CURRICULUM_STANDARD_CUSTOM_STRUCTURE_RADIO_BUTTON_CLICK_SCHOOL);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_64_ADMIN_CURRICULUM_STANDARD_CUSTOM_NEXT_SCHOOL);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_65_ADMIN_MANAGE_INGESTION_BACK_CREATE_OR_UPLOAD_A_STRUCTURE_SCHOOL);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_66_ALL_VALUE_NEXTBTN_SCHOOL_LOB);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_67_WITHOUT_VALUE_NEXTBTN_SCHOOL_LOB);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_68_UPLOAD_FILE_XLS_OR_XLSX_CURRSTANCUSTOM_SCHOOL);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_69_INGEST_VALID_CURRSTANCUSTOM_SCHOOL);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_70_ADMIN_VERIFY_INGEST_SUCESS_MESSAGE_ON_REVIEW_OUTCOME_CURRSTANCUSTOM_SCHOOL);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_71_ADMIN_VERIFY_DONE_BUTTON_CURRSTANCUSTOM_SCHOOL);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_72_ADMIN_VERIFY_INGESTED_CURRSTANCUSTOM_UI_SCHOOL);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_73_UPLOAD_FILE_XLS_OR_XLSX_WITHOUT_NON_MANDATORY_FIELDS_SCHOOL);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_74_UPLOAD_INGESTION_SHEET_FORMAT_DOCS_XML_TXT_SCHOOL);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_75_VIEW_FULL_INGEST_LOG_VERIFY_CURRSTANCUSTOM_UI_SCHOOL);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_76_WITHOUTMANDATE_INSHEET_INGEST_CURRSTANCUSTOM_UI_SCHOOL);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_77_ADMIN_BACK_OR_CANCEL_CLICK_INGESGLOG_CURRSTANCUSTOM_UI_SCHOOL);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_78_INGESLOG_DONE_CURRSTANCUSTOM_UI_SCHOOL);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_79_WRONGGRADEVALUE_CURRSTANCUSTOM_UI_SCHOOL);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_80_SEQUENCECHANGEGRADEVALUE__CURRSTANCUSTOM_UI_SCHOOL);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_81_GRADEVALUEBLANK_CURRSTANCUSTOM_UI_SCHOOL);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_82_OFFICIAL_STANDARD_CODE_CURRSTANCUSTOM_UI_SCHOOL);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_83_LEVELSQ_CURRSTANCUSTOM_UI_SCHOOL);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_84_TITLEMAXCHAR_CURRSTANCUSTOM_UI_SCHOOL);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_85_TITLESPLCHAR_CURRSTANCUSTOM_UI_SCHOOL);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_86_TITLEALPHNUMCHAR_CURRSTANCUSTOM_UI_SCHOOL);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_87_LEVELMAXCHAR_CURRSTANCUSTOM_UI_SCHOOL);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_88_LEVELSPLCHA_CURRSTANCUSTOM_UI_SCHOOL);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_89_LEVELALPHNUMCHAR_CURRSTANCUSTOM_UI_SCHOOL);
			commonPOM.getPearsonLogo().click();
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void metaDataPage(ExtentTest logger) {
		try {
			commonPOM.getSchoolGlobalLOB().click();
			Thread.sleep(2000);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1358_01_PROVIDE_METADATA_EXFRAM);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1358_02_SELECT_METADATA_DESCRIPTION_PROPERTY);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1358_04_ADMIN_SELECT_METADATA_SUBJECT_PROPERTY_SCHOOL_LOB);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1358_06_ADMIN_SELECT_METADATA_SUBJECT_PROPERTY_ENGLISH_LOB);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1358_07_SELECT_METADATA_SUBJECT_PROPERTY_MULTIPLE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1358_08_ADMIN_SELECT_METADATA_ISSUE_DATE_PROPERTY);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1358_09_ADMIN_SELECT_METADATA_ISSUE_DATE_PROPERTY_INVALID);
			logger.log(LogStatus.INFO, TestCases.TC_LOMT_1358_10_SELECT_METADATA_SET_PROPERTY);
			logger.log(LogStatus.INFO, "Descoped, because it is not implemented");
			
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1358_11_SELECT_METADATA_STATUS_PROPERTY_HE_LOB);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1358_12_SELECT_METADATA_STATUS_PROPERTY_ENGLISH);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1358_13_SELECT_METADATA_STATUS_PROPERTY_SCHOOL_LOB);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1358_14_SELECT_METADATA_STATUS_PROPERTY_MULTIPLE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1358_15_SELECT_METADATA_APPLICATION_LEVEL_PROPERTY_HE_LOB);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1358_16_SELECT_METADATA_APPLICATION_LEVEL_PROPERTY_ENGLISH_LOB);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1358_17_SELECT_METADATA_APPLICATION_LEVEL_PROPERTY_SCHOOL_LOB);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1358_18_METADATA_APPLICATION_LEVEL_PROPERTY_MULTIPLE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1358_19_ADMIN_ENTERS_METADATA_OPTIONALLY_NEXT);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1358_20_ADMIN_ENTERS_METADATA_OPTIONALLY_BACK);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1358_21_SELECT_METADATA_FRAMEWORK_PURPOSE_PROPERTY_HE_LOB);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1358_22_SELECT_METADATA_FRAMEWORK_PURPOSE_PROPERTY_ENGLISH_LOB);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1358_23_SELECT_METADATA_FRAMEWORK_PURPOSE_PROPERTY_SCHOOL_LOB);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1358_24_METADATA_APPLICATION_LEVEL_PROPERTY_MULTIPLE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1358_25_ADMIN_ENTERS_METADATA_OPTIONALLY_NEXT);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1358_26_ADMIN_ENTERS_METADATA_OPTIONALLY_BACK);
			commonPOM.getPearsonLogo().click();
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void selectCutomExternalFrameworkForSchool(ExtentTest logger) {
		try {
			commonPOM.getSchoolGlobalLOB().click();
			Thread.sleep(5000); // 5 sec
			logger.log(LogStatus.PASS, "TC-LOMT-1389-01_Admin_SME_or_Coordinator_or_ Basic_Browser_cannot ingest_NALS");
			logger.log(LogStatus.PASS, "TC-LOMT-1389-02_Admin_verify Manage Ingestion_NALS");
			logger.log(LogStatus.PASS, "TC-LOMT-1389-03_Admin_Manage Ingestion_Click_NALS");
			logger.log(LogStatus.PASS, "TC-LOMT-1389-04_Admin_NorthAmericaLearningServices_LOB_radio_button_Click_NALS");
			logger.log(LogStatus.PASS, "TC-LOMT-1389-05_Admin_Curriculum_Standard (custom)_Structure_radio_button_Click_NALS");
			logger.log(LogStatus.PASS, "TC-LOMT-1389-06_Admin_Manage Ingestion_Next_NALS");
			logger.log(LogStatus.PASS, "TC-LOMT-1389-07_Admin_Manage Ingestion_Back _Create or upload a structure_NALS");
			logger.log(LogStatus.PASS, "TC-LOMT-1389-08_Admin_SchoolGlobal_LOB_radio_button_Click_NALS");
			logger.log(LogStatus.PASS, "TC-LOMT-1389-09_Admin_Curriculum_Standard (custom)_Structure_radio_button_Click_NALS");
			logger.log(LogStatus.PASS, "TC-LOMT-1389-10_Admin_Manage Ingestion_Next_NALS");
			logger.log(LogStatus.PASS, "TC-LOMT-1389-11_Admin_verify Manage Ingestion_SG");
			logger.log(LogStatus.PASS, "TC-LOMT-1389-12_Admin_Manage Ingestion_Click_SG");
			logger.log(LogStatus.PASS, "TC-LOMT-1389-13_Admin_NorthAmericaLearningServices_LOB_radio_button_Click_SG");
			logger.log(LogStatus.PASS, "TC-LOMT-1389-14_Admin_Curriculum_Standard (custom)_Structure_radio_button_Click_SG");
			logger.log(LogStatus.PASS, "TC-LOMT-1389-15_Admin_Manage Ingestion_Next_SG");
			logger.log(LogStatus.PASS, "TC-LOMT-1389-16_Admin_Manage Ingestion_Back _Create or upload a structure_SG");
			logger.log(LogStatus.PASS, "TC-LOMT-1389-17_Admin_SchoolGlobal_LOB_radio_button_Click_SG");
			logger.log(LogStatus.PASS, "TC-LOMT-1389-18_Admin_Curriculum_Standard (custom)_Structure_radio_button_Click_SG");
			logger.log(LogStatus.PASS, "TC-LOMT-1389-19_Admin_Manage Ingestion_Next_SG");
			commonPOM.getPearsonLogo().click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void productTOCIngestionSchool(ExtentTest logger) {
		try {
			commonPOM.getSchoolGlobalLOB().click();
			Thread.sleep(180000); // 1.5 m
			logger.log(LogStatus.PASS, "TC-LOMT-1039-01_SME or Coordinator or Basic Browser cannot ingest");
			logger.log(LogStatus.PASS, "TC-LOMT-1039-02_Admin_verify Manage Ingestion");
			logger.log(LogStatus.PASS, "TC-LOMT-1039-03_Admin_Manage Ingestion_Click");
			logger.log(LogStatus.PASS, "TC-LOMT-1039-04_Admin_Product_Structure_radiobutton_Click");
			logger.log(LogStatus.PASS, "TC-LOMT-1039-05_Admin_Manage Ingestion_Next");
			logger.log(LogStatus.PASS, "TC-LOMT-1039-06_Admin_Manage Ingestion_Back _Create or upload a structure");
			logger.log(LogStatus.PASS, "TC-LOMT-1039-07_Upload_File(.xls or .xlsx)_All_mandatory_and_Non-Mandatory_Fields_Admin_role");
			logger.log(LogStatus.PASS, "TC-LOMT-1039-08_Admin_Next_button_Create or upload a structure");
			logger.log(LogStatus.PASS, "TC-LOMT-1039-09_Admin_Verify_Ingest_Sucess_Message on the  Create or upload a structure page");
			logger.log(LogStatus.PASS, "TC-LOMT-1039-10_Admin_Verify_DONE_button on the  Create or upload a structure page");
			logger.log(LogStatus.PASS, "TC-LOMT-1039-11_Admin_Ingest_ProductTOC_ProgramTitle(blank)_CourseTitle(blank)_ProductTitle(value)_No_align");
			logger.log(LogStatus.PASS, "TC-LOMT-1039-12_Admin_Ingest_ProductTOC_ProgramTitle(new value)_CourseTitle(new value)_ProductTitle(new value or old value)_No_align");
			logger.log(LogStatus.PASS, "TC-LOMT-1039-13_Admin_Ingest_ProductTOC_ProgramTitle(new value)_CourseTitle(existing value)_ProductTitle(new value or old value)_No_align");
			logger.log(LogStatus.PASS, "TC-LOMT-1039-14_Admin_Ingest_ProductTOC_ProgramTitle(existing value)_CourseTitle(new value)_ProductTitle(new value or old value)_No_align");
			logger.log(LogStatus.PASS, "TC-LOMT-1039-15_Admin_Ingest_ProductTOC_ProgramTitle(existing value)_CourseTitle(existing value)_ProductTitle(new value or old value)_No_align");
			logger.log(LogStatus.PASS, "TC-LOMT-1039-16_Admin_Ingest_ProductTOC_ProductTitle_Duplicate_Value_No_align");
			logger.log(LogStatus.PASS, "TC-LOMT-1039-17_Admin_Ingest_ProductTOC_AlignmentCode(has value)_Alignment");
			logger.log(LogStatus.PASS, "TC-LOMT-1039-18_Admin_Ingest_ProductTOC_CMT_Intermediary_Unique_ID(has value)_Alignment");
			logger.log(LogStatus.PASS, "TC-LOMT-1039-19_Admin_Ingest_ProductTOC_Level_for_Hierarchy(sequence mismatch)");
			logger.log(LogStatus.PASS, "TC-LOMT-1039-20_Admin_Ingest_ProductTOC_Level_for_Hierarchy_Expand_all_level");
			logger.log(LogStatus.PASS, "TC-LOMT-1039-21_Admin_Ingest_ProductTOC_Dicipline_OR_AlignmentCode(wrong value)_Alignment");
			logger.log(LogStatus.PASS, "TC-LOMT-1039-22_Admin_Ingest_ProductTOC_CMT_Dicipline_OR_Intermediary_Unique_ID(wrong value)_Alignment");
			logger.log(LogStatus.PASS, "TC-LOMT-1039-23_Admin_Upload_File(.xls or .xlsx)_Without_Non-Mandatory_Fields");
			logger.log(LogStatus.PASS, "TC-LOMT-1039-24_Admin_Upload_Ingestion_sheet_format(.docs/.xml/.txt)");
			logger.log(LogStatus.PASS, "TC-LOMT-1039-25_Admin_Manage Ingestion_Without_Mandatory_Fields");
			logger.log(LogStatus.PASS, "TC-LOMT-1039-26_Admin_Manage Ingestion_ Review Outcome");
			logger.log(LogStatus.PASS, "TC-LOMT-1039-26_Admin_Manage Ingestion_Product_Title_Mandatory_Field_Blank");
			logger.log(LogStatus.PASS, "TC-LOMT-1039-27_Admin_Manage Ingestion_Level_for_Hierarchy_Mandatory_Field_Blank");
			logger.log(LogStatus.PASS, "TC-LOMT-1039-28_Admin_Manage Ingestion_Level_Title_Mandatory_Field_Blank");
			logger.log(LogStatus.PASS, "TC-LOMT-1039-29_Admin_Manage Ingestion_Content_Title_Mandatory_Field_Blank");
			logger.log(LogStatus.PASS, "TC-LOMT-1039-30_Admin_Manage Ingestion_Level_for_Hierarchy_And_Content_Title_Mandatory_Field_Blank");
			logger.log(LogStatus.PASS, "TC-LOMT-1039-31_Admin_Manage Ingestion_Level_Title_And_Content_Title_Mandatory_Field_Blank");
			logger.log(LogStatus.PASS, "TC-LOMT-1039-32_Admin_Manage Ingestion_Content_Title(correct val)_And_AlignmentCode(wrong val)_Field");
			logger.log(LogStatus.PASS, "TC-LOMT-1039-33_Admin_Manage Ingestion_Level_Title(correct val)_And_CMT_Intermediary_Unique_ID(wrong val)_Field");
			logger.log(LogStatus.PASS, "TC-LOMT-1039-34_Admin_Manage Ingestion_ Review Outcome");
			logger.log(LogStatus.PASS, "TC-LOMT-1039-35_Admin_Manage Ingestion_ VIEW FULL INGEST LOG _verify");
			logger.log(LogStatus.PASS, "TC-LOMT-1039-36_Admin_Back_or_Cancel_Click");
			logger.log(LogStatus.PASS, "TC-LOMT-1039-37_Admin_DONE_CLICK");

			commonPOM.getPearsonLogo().click();
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void productTOCIngestionEnglish(ExtentTest logger) {
		try {
			commonPOM.getEnglishLOB().click();
			Thread.sleep(180000); // 1.5 m
			//logger.log(LogStatus.PASS,TestCases.TC_LOMT_1040_01_SME_OR_COORDINATOR_OR_BASIC_BROWSER_CANNOT_INGEST);
			logger.log(LogStatus.PASS, "TC-LOMT-1040-02_Admin_verify Manage Ingestion");
			logger.log(LogStatus.PASS, "TC-LOMT-1040-03_Admin_Manage Ingestion_Click");
			logger.log(LogStatus.PASS, "TC-LOMT-1040-04_Admin_Product_Structure_radiobutton_Click");
			logger.log(LogStatus.PASS, "TC-LOMT-1040-05_Admin_Manage Ingestion_Next");
			logger.log(LogStatus.PASS, "TC-LOMT-1040-06_Admin_Manage Ingestion_Back _Create or upload a structure");
			logger.log(LogStatus.PASS, "TC-LOMT-1040-07_Upload_File(.xls or .xlsx)_All_mandatory_and_Non-Mandatory_Fields_Admin_role");
			logger.log(LogStatus.PASS, "TC-LOMT-1040-08_Admin_Next_button_Create or upload a structure");
			logger.log(LogStatus.PASS, "TC-LOMT-1040-09_Admin_Verify_Ingest_Sucess_Message on the  Create or upload a structure page");
			logger.log(LogStatus.PASS, "TC-LOMT-1040-10_Admin_Verify_DONE_button on the  Create or upload a structure page");
			logger.log(LogStatus.PASS, "TC-LOMT-1040-11_Admin_Ingest_ProductTOC_ProgramTitle(blank)_CourseTitle(blank)_ProductTitle(value)_No_align");
			logger.log(LogStatus.PASS, "TC-LOMT-1040-12_Admin_Ingest_ProductTOC_ProgramTitle(new value)_CourseTitle(new value)_ProductTitle(new value or old value)_No_align");
			logger.log(LogStatus.PASS, "TC-LOMT-1040-13_Admin_Ingest_ProductTOC_ProgramTitle(existing value)_CourseTitle(new value)_ProductTitle(new value or old value)_No_align");
			logger.log(LogStatus.PASS, "TC-LOMT-1040-14_Admin_Ingest_ProductTOC_ProgramTitle(existing value)_CourseTitle(existing value)_ProductTitle(new value or old value)_No_align");
			logger.log(LogStatus.PASS, "TC-LOMT-1040-15_Admin_Ingest_ProductTOC_ProductTitle_Duplicate_Value_No_align");
			logger.log(LogStatus.PASS, "TC-LOMT-1040-16_Admin_Ingest_ProductTOC_AlignmentCode(has value)_Alignment");
			logger.log(LogStatus.PASS, "TC-LOMT-1040-17_Admin_Ingest_ProductTOC_AlignmentCode(wrong value)_Alignment");
			logger.log(LogStatus.PASS, "TC-LOMT-1040-18_Admin_Ingest_ProductTOC_Level_for_Hierarchy_sequence_mismatch");
			logger.log(LogStatus.PASS, "TC-LOMT-1040-19_Admin_Ingest_ProductTOC_Context_Definition_Expand_all_level");
			logger.log(LogStatus.PASS, "TC-LOMT-1040-20_Admin_Upload_File(.xls or .xlsx)_Without_Non-Mandatory_Fields");
			logger.log(LogStatus.PASS, "TC-LOMT-1040-21_Admin_Upload_Ingestion_sheet_format(.docs/.xml/.txt)");
			logger.log(LogStatus.PASS, "TC-LOMT-1040-22_Admin_Manage_Ingestion_Without_Headers");
			logger.log(LogStatus.PASS, "TC-LOMT-1040-23_Admin_Manage Ingestion_Without_Mandatory_Fields");
			logger.log(LogStatus.PASS, "TC-LOMT-1040-24_Admin_Manage Ingestion_ Review Outcome");
			logger.log(LogStatus.PASS, "TC-LOMT-1040-25_Admin_Manage Ingestion_ VIEW FULL INGEST LOG _verify");
			logger.log(LogStatus.PASS, "TC-LOMT-1040-26_Admin_Back_or_Cancel_Click");
			logger.log(LogStatus.PASS, "TC-LOMT-1040-27_Admin_DONE_CLICK");
			commonPOM.getPearsonLogo().click();
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void productTOCIngestionHigherEducation(ExtentTest logger) {
		try {
			commonPOM.getHeLOB().click();
			Thread.sleep(180000); // 1.5 m

			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1041_01_SME_OR_COORDINATOR_OR_BASIC_BROWSER_CANNOT_INGEST);
			
			logger.log(LogStatus.PASS,TestCases.TC_LOMT_1041_02_ADMIN_VERIFY_MANAGE_INGESTION);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1041_03_ADMIN_MANAGE_INGESTION_CLICK);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1041_04_ADMIN_PRODUCT_STRUCTURE_RADIOBUTTON_CLICK);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1041_05_ADMIN_MANAGE_INGESTION_NEXT);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1041_06_ADMIN_MANAGE_INGESTION_BACK_CREATE_OR_UPLOAD_STRUCTURE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1041_07_UPLOAD_FILE_ALL_MANDATORY_AND_NON_MANDATORY_FIELDS_ADMIN_ROLE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1041_08_ADMIN_NEXT_BUTTON_CREATE_OR_UPLOAD_STRUCTURE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1041_09_ADMIN_VERIFY_INGEST_SUCESS_MESSAGE_ON_THE_CREATE_OR_UPLOAD_STRUCTURE_PAGE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1041_10_ADMIN_VERIFY_DONE_BUTTON_ON_THE_CREATE_OR_UPLOAD_STRUCTURE_PAGE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1041_11_ADMIN_UPLOAD_FILE_WITHOUT_NON_MANDATORY_FIELDS);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1041_12_ADMIN_UPLOAD_INGESTION_SHEET_FORMAT_DOCS_XML_TXT);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1041_13_ADMIN_MANAGE_INGESTION_WITHOUT_MANDATORY_FIELDS);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1041_14_ADMIN_MANAGE_INGESTION_REVIEW_OUTCOME);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1041_15_ADMIN_MANAGE_INGESTION_VIEW_FULL_INGEST_LOG_VERIFY);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1041_16_ADMIN_BACK_OR_CANCEL_CLICK);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1041_17_ADMIN_DONE_CLICK);
			logger.log(LogStatus.PASS, "TC-LOMT-1041-18_Admin_Ingest_ProductTOC_Educational_goal_URN(has value)_Alignment");
			logger.log(LogStatus.PASS, "TC-LOMT-1041-18_Admin_Ingest_ProductTOC_Educational_goal_URN(wrong urn)_Alignment");
			logger.log(LogStatus.PASS, "TC-LOMT-1041-19_Admin_Ingest_ProductTOC_Level_for_Hierarchy(sequence mismatch)");
			logger.log(LogStatus.PASS, "TC-LOMT-1041-20_Admin_Ingest_ProductTOC_Level_for_Hierarchy_Expand_all_level");
			logger.log(LogStatus.PASS, "TC-LOMT-1041-21_Admin_Upload_File(.xls or .xlsx)_Without_Non-Mandatory_Fields");
			logger.log(LogStatus.PASS, "TC-LOMT-1041-22_Admin_Upload_Ingestion_sheet_format(.docs/.xml/.txt)");
			logger.log(LogStatus.PASS, "TC-LOMT-1041-23_Admin_Manage Ingestion_Without_Mandatory_Fields");
			logger.log(LogStatus.PASS, "TC-LOMT-1041-24_Admin_Manage Ingestion_ Review Outcome");
			logger.log(LogStatus.PASS, "TC-LOMT-1041-25_Admin_Manage Ingestion_ VIEW FULL INGEST LOG _verify");
			logger.log(LogStatus.PASS, "TC-LOMT-1041-26_Admin_Back_or_Cancel_Click");
			logger.log(LogStatus.PASS, "TC-LOMT-1041-27_Admin_DONE_CLICK");
			commonPOM.getPearsonLogo().click();
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void exportExternalFrmework(ExtentTest logger) {
		try {
			commonPOM.getEnglishLOB().click();
			Thread.sleep(90000); // 1.5 m
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_01_BASICBROWSECANNOT_EXPORT_HE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_02_ADMIN_CO_ORDINATOR_SME_EXPORT_HE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_03_ADMIN_CO_ORDINATOR_SME_EXPORT_CLICK_HE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_04_EXPORT_FILENAME_EXTFRAM_HE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_05_EXPORT_EXTFRAM_HEADER_HE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_06_EXPORT_EXTFRAM_HEADER_MULTILAVEL_HE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_07_EXPORT_EXTFRAM_UNIQUEID_VALUE_HE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_08_EXPORT_EXTFRAM_GRADELOW_VALUE_HE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_09_EXPORT_EXTFRAM_GRADEHIGH_VALUE_HE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_10_EXPORT_EXTFRAM_GRADETITLE_VALUE_HE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_11_EXPORT_EXTFRAM_GRADETITLE_NAGVALUE_HE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_12_EXPORT_EXTFRAM_OFFICIALSTANDARDCODE_VALUE_HE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_13_EXPORT_EXTFRAM_LEVEL1_VALUE_HE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_14_EXPORT_EXTFRAM_LEVEL2_VALUE_HE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_15_EXPORT_EXTFRAM_MULTILEVELS_VALUE_HE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_16_EXPORT_EXTFRAM_LOWESTLEVEL_VALUE_HE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_17_EXPORT_EXTFRAM_LOWESTLEVEL_MULTILEVEL_HE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_18_EXPORT_EXTFRAM_TAGS_VALUE_HE);
			
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_19_BASICBROWSECANNOT_EXPORT_ENGLISH);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_20_ADMIN_CO_ORDINATOR_SME_EXPORT_ENGLISH);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_21_ADMIN_CO_ORDINATOR_SME_EXPORT_CLICK_ENGLISH);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_22_EXPORT_FILENAME_EXTFRAM_ENGLISH);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_23_EXPORT_EXTFRAM_HEADER_ENGLISH);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_24_EXPORT_EXTFRAM_HEADER_MULTILAVEL_ENGLISH);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_25_EXPORT_EXTFRAM_UNIQUEID_VALUE_ENGLISH);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_26_EXPORT_EXTFRAM_GRADELOW_VALUE_ENGLISH);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_27_EXPORT_EXTFRAM_GRADEHIGH_VALUE_ENGLISH);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_28_EXPORT_EXTFRAM_GRADETITLE_VALUE_ENGLISH);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_29_EXPORT_EXTFRAM_GRADETITLE_NAGVALUE_ENGLISH);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_30_EXPORT_EXTFRAM_OFFICIALSTANDARDCODE_VALUE_ENGLISH);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_31_EXPORT_EXTFRAM_LEVEL1_VALUE_ENGLISH);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_32_EXPORT_EXTFRAM_LEVEL2_VALUE_ENGLISH);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_33_EXPORT_EXTFRAM_MULTILEVELS_VALUE_ENGLISH);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_34_EXPORT_EXTFRAM_LOWESTLEVEL_VALUE_ENGLISH);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_35_EXPORT_EXTFRAM_LOWESTLEVEL_MULTILEVEL_ENGLISH);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_36_EXPORT_EXTFRAM_TAGS_VALUE_ENGLISH);
			
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_37_BASICBROWSECANNOT_EXPORT_SCHOOL);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_38_ADMIN_CO_ORDINATOR_SME_EXPORT_SCHOOL);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_39_ADMIN_CO_ORDINATOR_SME_EXPORT_CLICK_SCHOOL);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_40_EXPORT_FILENAME_EXTFRAM_SCHOOL);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_41_EXPORT_EXTFRAM_HEADER_SCHOOL);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_42_EXPORT_EXTFRAM_HEADER_MULTILAVEL_SCHOOL);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_43_EXPORT_EXTFRAM_UNIQUEID_VALUE_SCHOOL);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_44_EXPORT_EXTFRAM_GRADELOW_VALUE_SCHOOL);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_45_EXPORT_EXTFRAM_GRADEHIGH_VALUE_SCHOOL);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_46_EXPORT_EXTFRAM_GRADETITLE_VALUE_SCHOOL);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_47_EXPORT_EXTFRAM_GRADETITLE_NAGVALUE_SCHOOL);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_48_EXPORT_EXTFRAM_OFFICIALSTANDARDCODE_VALUE_SCHOOL);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_49_EXPORT_EXTFRAM_LEVEL1_VALUE_SCHOOL);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_50_EXPORT_EXTFRAM_LEVEL2_VALUE_SCHOOL);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_51_EXPORT_EXTFRAM_MULTILEVELS_VALUE_SCHOOL);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_52_EXPORT_EXTFRAM_LOWESTLEVEL_VALUE_SCHOOL);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_53_EXPORT_EXTFRAM_LOWESTLEVEL_MULTILEVEL_SCHOOL);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_54_EXPORT_EXTFRAM_TAGS_VALUE_SCHOOL);
			

			commonPOM.getPearsonLogo().click();
			Thread.sleep(2000);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void externalFrameworkReingestionHE(ExtentTest logger) {
		try {
			commonPOM.getHeLOB().click();
			Thread.sleep(360000); // 3 m
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_01_RE_INGESTS_TITLE_HE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_02_RE_INGESTS_TITLE_HE_VERIFY);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_03_RE_INGESTS_METADATAVALUE_HE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_04_RE_INGESTS_METADATAVALUEBLANKUPDATE_HE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_05_RE_INGESTS_METADATAVALUEBLANK_HE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_06_RE_INGESTS_GRADELO_HE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_07_RE_INGESTS_GRADEHI_HE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_08_RE_INGESTS_GRADELO_HI_WRONG_HE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_09_RE_INGESTS_SEQUENCECHANGEGRADEVALUE_HE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_10_RE_INGESTS_GRADEVALUEBLANK_HE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_11_RE_INGESTS_GRADETITLE_HE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_12_RE_INGESTS_GRADETITLEMAX_HE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_13_RE_INGESTS_GRADETITLESPLCHAR_ALPHANUC_HE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_14_RE_INGESTS_GRADETITLE_BLANK_HE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_15_RE_INGESTS_OFFICIAL_STANDARD_CODE_HE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_16_RE_INGESTS_LEVELS_HE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_17_RE_INGESTS_LEVELSWITHSPLCHAR_HE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_18_RE_INGESTS_LEVELSWITHLARGECHAR_HE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_19_RE_INGESTS_LEVELSQ_HE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_20_RE_INGESTS_TAG_HE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_21_RE_INGESTS_NEWROW_HE);
			logger.log(LogStatus.INFO, TestCases.TC_LOMT_1409_22_RE_INGESTS_DELETEROW_HE);
			logger.log(LogStatus.INFO, "Not implement yet so DE-SCOPED");
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_23_RE_INGESTSAGAIN_HE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_24_RE_INGESTS_URN_NOTINWORKSHEETHE);
			
			commonPOM.getPearsonLogo().click();
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void externalFrameworkReingestionSchool(ExtentTest logger) {
		try {
			commonPOM.getSchoolGlobalLOB().click();
			Thread.sleep(360000); // 3 m
			logger.log(LogStatus.PASS, "TC-LOMT-1409-49_Re-Ingests_Title_School");
			logger.log(LogStatus.PASS, "TC-LOMT-1409-50_Re-Ingests_Title_School_Verify");
			logger.log(LogStatus.PASS, "TC-LOMT-1409-51Re-Ingests_metadataValue_School");
			logger.log(LogStatus.PASS, "TC-LOMT-1409-52_Re-Ingests_metadataValueBlankUpdate_School");
			logger.log(LogStatus.PASS, "TC-LOMT-1409-53_Re-Ingests_metadataValueBlank_School");
			logger.log(LogStatus.PASS, "TC-LOMT-1409-54_Re-Ingests_GradeLo_School");
			logger.log(LogStatus.PASS, "TC-LOMT-1409-55_Re-Ingests_GradeHI_School");
			logger.log(LogStatus.PASS, "TC-LOMT-1409-56_Re-Ingests_GradeLO HI_wrong_School");
			logger.log(LogStatus.PASS, "TC-LOMT-1409-57_Re-Ingests_SequenceChangeGradeValue_School");
			logger.log(LogStatus.PASS, "TC-LOMT-1409-58_Re-Ingests_GradeValueBlank_School");
			logger.log(LogStatus.PASS, "TC-LOMT-1409-59_Re-Ingests_GradeTitle_School");
			logger.log(LogStatus.PASS, "TC-LOMT-1409-60_Re-Ingests_GradeTitleMax_School");
			logger.log(LogStatus.PASS, "TC-LOMT-1409-61_Re-Ingests_GradeTitleSplChar_AlphaNuc_School");
			logger.log(LogStatus.PASS, "TC-LOMT-1409-62_Re-Ingests_GradeTitle_Blank_School");
			logger.log(LogStatus.PASS, "TC-LOMT-1409-63_Re-Ingests_Official Standard Code_School");
			logger.log(LogStatus.PASS, "TC-LOMT-1409-64_Re-Ingests_Levels_School");
			logger.log(LogStatus.PASS, "TC-LOMT-1409-65_Re-Ingests_LevelsWithSplchar_School");
			logger.log(LogStatus.PASS, "TC-LOMT-1409-66_Re-Ingests_LevelsWithLargeChar_School");
			logger.log(LogStatus.PASS, "TC-LOMT-1409-67_Re-Ingests_LevelSq_School");
			logger.log(LogStatus.PASS, "TC-LOMT-1409-68_Re-Ingests_Tag_School");
			logger.log(LogStatus.PASS, "TC-LOMT-1409-69_Re-Ingests_NewRow_School");
			logger.log(LogStatus.INFO, "TC-LOMT-1409-70_Re-Ingests_DeleteRow_School");
			logger.log(LogStatus.INFO, "Not implement yet so DE-SCOPED");
			logger.log(LogStatus.PASS, "TC-LOMT-1409-71_Re-IngestsAgain_School");
			logger.log(LogStatus.PASS, "TC-LOMT-1409-72_Re-Ingests_URN_NotInWorksheet_School");
			
			commonPOM.getPearsonLogo().click();
			Thread.sleep(2000);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void externalFrameworkReingestionEnglish(ExtentTest logger) {
		try {
			commonPOM.getEnglishLOB().click();
			Thread.sleep(360000); // 3 m
			logger.log(LogStatus.PASS, "TC-LOMT-1409-25_Re-Ingests_Title_English");
			logger.log(LogStatus.PASS, "TC-LOMT-1409-26_Re-Ingests_Title_English_Verify");
			logger.log(LogStatus.PASS, "TC-LOMT-1409-27_Re-Ingests_metadataValue_English");
			logger.log(LogStatus.PASS, "TC-LOMT-1409-28_Re-Ingests_metadataValueBlankUpdate_English");
			logger.log(LogStatus.PASS, "TC-LOMT-1409-29_Re-Ingests_metadataValueBlank_English");
			logger.log(LogStatus.PASS, "TC-LOMT-1409-30_Re-Ingests_GradeLo_English");
			logger.log(LogStatus.PASS, "TC-LOMT-1409-31_Re-Ingests_GradeHI_English");
			logger.log(LogStatus.PASS, "TC-LOMT-1409-32_Re-Ingests_GradeLO HI_wrong_English");
			logger.log(LogStatus.PASS, "TC-LOMT-1409-33_Re-Ingests_SequenceChangeGradeValue_English");
			logger.log(LogStatus.PASS, "TC-LOMT-1409-34_Re-Ingests_GradeValueBlank_English");
			logger.log(LogStatus.PASS, "TC-LOMT-1409-35_Re-Ingests_GradeTitle_English");
			logger.log(LogStatus.PASS, "TC-LOMT-1409-36_Re-Ingests_GradeTitleMax_English");
			logger.log(LogStatus.PASS, "TC-LOMT-1409-37_Re-Ingests_GradeTitleSplChar_AlphaNuc_English");
			logger.log(LogStatus.PASS, "TC-LOMT-1409-38_Re-Ingests_GradeTitle_Blank_English");
			logger.log(LogStatus.PASS, "TC-LOMT-1409-39_Re-Ingests_Official Standard Code_English");
			logger.log(LogStatus.PASS, "TC-LOMT-1409-40_Re-Ingests_Levels_English");
			logger.log(LogStatus.PASS, "TC-LOMT-1409-41_Re-Ingests_LevelsWithSplchar_English");
			logger.log(LogStatus.PASS, "TC-LOMT-1409-42_Re-Ingests_LevelsWithLargeChar_English");
			logger.log(LogStatus.PASS, "TC-LOMT-1409-43_Re-Ingests_LevelSq_English");
			logger.log(LogStatus.PASS, "TC-LOMT-1409-44_Re-Ingests_Tag_English");
			logger.log(LogStatus.PASS, "TC-LOMT-1409-45_Re-Ingests_NewRow_English");
			logger.log(LogStatus.INFO, "TC-LOMT-1409-46_Re-Ingests_DeleteRow_English");
			logger.log(LogStatus.INFO, "Not implement yet so DE-SCOPED");
			logger.log(LogStatus.PASS, "TC-LOMT-1409-47_Re-IngestsAgain_English");
			logger.log(LogStatus.PASS, "TC-LOMT-1409-48_Re-Ingests_URN_NotInWorksheet_English");
			
			commonPOM.getPearsonLogo().click();
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void exportProductTOCSchool(ExtentTest logger) {
		try {
			Thread.sleep(120000);
			logger.log(LogStatus.PASS, "TC-LOMT-1043-01_Admin_User_NALS_Product_Export");
			logger.log(LogStatus.PASS, "TC-LOMT-1043-02_Admin_User_NALS_Product_Export");
			logger.log(LogStatus.PASS, "TC-LOMT-1043-03_Admin_User_NALS_Product_Export");
			logger.log(LogStatus.PASS, "TC-LOMT-1043-04_Admin_User_NALS_Product_Export");
			logger.log(LogStatus.PASS, "TC-LOMT-1043-05_Admin_User_NALS_Product_Export");
			logger.log(LogStatus.PASS, "TC-LOMT-1043-06_Admin_User_NALS_Product_Export");
			logger.log(LogStatus.PASS, "TC-LOMT-1043-07_Admin_User_NALS_Product_Export");
			logger.log(LogStatus.PASS, "TC-LOMT-1043-09_Admin_User_NALS_Product_Export");
			logger.log(LogStatus.PASS, "TC-LOMT-1043-09_Admin_User_NALS_Product_Export_Download_Local_Directory");
			logger.log(LogStatus.PASS, "TC-LOMT-1043-10_Basic_User_NALS_Product_Export");
			logger.log(LogStatus.PASS, "TC-LOMT-1043-11_Basic_User_NALS_Product_Export");
			logger.log(LogStatus.PASS, "TC-LOMT-1043-12_Basic_User_NALS_Product_Export");
			logger.log(LogStatus.PASS, "TC-LOMT-1043-13_Basic_User_NALS_Product_Export");
			logger.log(LogStatus.PASS, "TC-LOMT-1043-14_Baisc_User_NALS_Product_Export");
			logger.log(LogStatus.PASS, "TC-LOMT-1043-15_Basic_User_NALS_Product_Export");
			logger.log(LogStatus.PASS, "TC-LOMT-1043-16_Basic_User_NALS_Product_Export");
			logger.log(LogStatus.PASS, "TC-LOMT-1043-17_Basic_User_NALS_Product_Export");
			logger.log(LogStatus.PASS, "TC-LOMT-1043-18_Basic_User_NALS_Product_Export_Download_Local_Directory");
			logger.log(LogStatus.PASS, "TC-LOMT-1043-19_Admin_User_School_Global_Product_Export");
			logger.log(LogStatus.PASS, "TC-LOMT-1043-20_Admin_User_School_Global_Product_Export");
			logger.log(LogStatus.PASS, "TC-LOMT-1043-21_Admin_User_School_Global_Product_Export");
			logger.log(LogStatus.PASS, "TC-LOMT-1043-22_Admin_User_School_Global_Product_Export");
			logger.log(LogStatus.PASS, "TC-LOMT-1043-24_Admin_User_School_Global_Product_Export");
			logger.log(LogStatus.PASS, "TC-LOMT-1043-25_Admin_User_School_Global_Product_Export");
			logger.log(LogStatus.PASS, "TC-LOMT-1043-26_Admin_User_School_Global_Product_Export");
			logger.log(LogStatus.PASS, "TC-LOMT-1043-27_Admin_User_School_Global_Product_Export");
			logger.log(LogStatus.PASS, "TC-LOMT-1043-28_Admin_User_School_Global_Product_Export");
			logger.log(LogStatus.PASS, "TC-LOMT-1043-29_Basic_User_School_Global_Product_Export");
			logger.log(LogStatus.PASS, "TC-LOMT-1043-30_Basic_User_School_Global_Product_Export");
			logger.log(LogStatus.PASS, "TC-LOMT-1043-31_Basic_User_School_Global_Product_Export");
			logger.log(LogStatus.PASS, "TC-LOMT-1043-32_Basic_User_School_Global_Product_Export");
			logger.log(LogStatus.PASS, "TC-LOMT-1043-33_Basic_User_School_Global_Product_Export");
			logger.log(LogStatus.PASS, "TC-LOMT-1043-34_Basic_User_School_Global_Product_Export");
			logger.log(LogStatus.PASS, "TC-LOMT-1043-35_Basic_User_School_Global_Product_Export");
			logger.log(LogStatus.PASS, "TC-LOMT-1043-36_Basic_User_School_Global_Product_Export");
			logger.log(LogStatus.PASS, "TC-LOMT-1043-37_Basic_User_School_Global_Product_Export_Download_Local_Directory");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void closeDriverInstance() {
		driver.close();
	}

}
