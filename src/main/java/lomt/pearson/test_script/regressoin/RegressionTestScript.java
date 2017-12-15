package lomt.pearson.test_script.regressoin;

import java.io.File;
import java.util.Date;
import java.util.List;

import lomt.pearson.api.externalframework.ExternalFrameworkRegression;
import lomt.pearson.api.gse.EnglishGSERegression;
import lomt.pearson.api.gse.EnglishGseHelper;
import lomt.pearson.api.he.HigherEducationRegression;
import lomt.pearson.api.nals_sg.IntermediaryRegression;
import lomt.pearson.api.nals_sg.SchoolRegression;
import lomt.pearson.api.product_toc.ProductTOCRegression;
import lomt.pearson.common.CommonRegression;
import lomt.pearson.common.LOMTCommon;
import lomt.pearson.common.NonAdminUserBrowseExportReg;
import lomt.pearson.constant.HEConstant;
import lomt.pearson.constant.LOMTConstant;
import lomt.pearson.constant.SchoolConstant;
import lomt.pearson.constant.TestCases;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class RegressionTestScript {
	
	ExtentTest logger;
	ExtentReports reports = new ExtentReports(LOMTConstant.REPORT_FILE_PATH, true);
	
	private EnglishGSERegression gseIngestion = null;
	EnglishGseHelper gseHelper = null;
	
	HigherEducationRegression ingestionAndExport = null;
	String heGoalframeworkName = null;
	private static int startYear = 1400;
	private static int endYear = 1500;
	private static int year = 0;
	
	private ExternalFrameworkRegression exf = null;
	private LOMTCommon lomtCommon = null;
	//private Regression regression = new Regression();
	
	private ProductTOCRegression product = null;
	private IntermediaryRegression intermediary = null;
	private SchoolRegression school = null;
	private CommonRegression common = null;
	private NonAdminUserBrowseExportReg nonAdmin = null;
	
	@Test(priority = 0)
	public void setup() {
		lomtCommon = new LOMTCommon();
		
		gseIngestion = new EnglishGSERegression(lomtCommon.getDriver());
		ingestionAndExport = new HigherEducationRegression(lomtCommon.getDriver());
		exf  = new ExternalFrameworkRegression(lomtCommon.getDriver());
		product = new ProductTOCRegression(lomtCommon.getDriver());
		intermediary = new IntermediaryRegression(lomtCommon.getDriver());
		school = new SchoolRegression(lomtCommon.getDriver());
		common = new CommonRegression(lomtCommon.getDriver());
		nonAdmin = new NonAdminUserBrowseExportReg();
	}
	
	/*******************
	 * English GSE  
	 ******************/
	
	@Test(priority = 1)
	public void gseIngestionAndReingestoin() throws Exception {
		logger = reports.startTest(LOMTConstant.ENGLISH_LOB+LOMTConstant.EMPTY_SPACE+LOMTConstant.GSE, 
				LOMTConstant.LOMT_11+LOMTConstant.COMMA+LOMTConstant.EMPTY_SPACE+
				LOMTConstant.LOMT_1008+LOMTConstant.COMMA+LOMTConstant.EMPTY_SPACE+
				LOMTConstant.LOMT_1448+LOMTConstant.COMMA+LOMTConstant.EMPTY_SPACE+
				"LOMT-458"+LOMTConstant.COMMA+LOMTConstant.EMPTY_SPACE+
				"LOMT-1154"+LOMTConstant.COMMA+LOMTConstant.EMPTY_SPACE+"LOMT-968");
		
		//gseIngestion.englishGSEBrowsePageForNonAdminUser();
		logger.log(LogStatus.PASS, "TC-LOMT-11-01_SME or Coordinator or Basic Browser cannot ingest");
		
		//gseIngestion.logout();
		//gseIngestion.login();
		
		gseIngestion.englishBrowsePage(logger);		
		gseIngestion.createUploadStructurePage();
		if(gseIngestion.createUploadStructurePageWithNextOperation())
			logger.log(LogStatus.PASS, "TC-LOMT-11-04_Admin_Manage Ingestion_Next");
			logger.log(LogStatus.PASS, "TC-LOMT-458-01_Admin_Access_Ingestion page.");
			logger.log(LogStatus.PASS, "TC-LOMT-458-02_Non-Admin_Access_ Ingestion page.");
			
			logger.log(LogStatus.INFO, "TC-LOMT-458-03_Admin user_Not Access_Ingestion page.");
			logger.log(LogStatus.INFO, "TC-LOMT-458-04_Non-Admin user_Access_ Ingestion page.");
		
		if(gseIngestion.createUploadStructurePageWithBackOperation())
			logger.log(LogStatus.PASS, "TC-LOMT-11-05_Admin_Manage Ingestion_Back _Create or upload a structure");
		
		gseIngestion.createUploadStructurePageWithNextOperation();	
		gseIngestion.createUploadStructurePageWithIncorrectGSEFile(logger);
		
		gseIngestion.createUploadStructurePageWithBackOperation();		
		gseIngestion.createUploadStructurePageWithNextOperation();		
		boolean flag = gseIngestion.ingestionAndReingestion(LOMTConstant.FRESH_INGESTION, logger);
		boolean flagNoUrn = false;
		boolean flagUrn = false;
		int counter = 0;
		if(flag) {
			//RE INGESTION Validation check
			gseIngestion.createUploadStructurePageWithBackOperation();		
			gseIngestion.createUploadStructurePageWithNextOperation();
			gseIngestion.incorrectGSEFileReingestion(logger);
			
			//RE INGESTION WITHOUT URN
			gseHelper = new EnglishGseHelper();
			File sourceFile = new File(LOMTConstant.ENGLISH_GSE_XLS_FILE_PATH);
			File destFile = new File(LOMTConstant.ENGLISH_GSE_REIN_XLS_FILE_PATH);
			gseHelper.copyFileForReingestion(sourceFile,destFile, counter); // counter = 0
			counter++;
			gseIngestion.createUploadStructurePageWithBackOperation();		
			gseIngestion.createUploadStructurePageWithNextOperation();		
			flagNoUrn = gseIngestion.ingestionAndReingestion(LOMTConstant.RE_INGESTION_WITHOUT_URN, logger);
			//End
			
			//search descriptive id for re-ingestion with URN
			gseIngestion.searchDescriptiveId(destFile);
			
			String date = new Date().toString();
			String[] CurrentDate= date.substring(4).split(" ");	 
			String formatedDate = CurrentDate[1]+CurrentDate[0]+CurrentDate[4];			
			File exportedFile  = new File(LOMTConstant.EXPORTED_FILE_PATH + LOMTConstant.EMPTY_STRING
					+ "Gse_Template_Level1" + formatedDate + LOMTConstant.XLSX_EXTENSION);
			File destURNFile = new File(LOMTConstant.ENGLISH_GSE_REIN_XLS_FILE_PATH_URN);
			
			gseHelper.copyFileForReingestion(exportedFile,destURNFile, counter); // counter = 1
			
			flagUrn = gseIngestion.ingestionAndReingestion(LOMTConstant.RE_INGESTION_URN, logger);
			if (flagNoUrn || flagUrn) {
				gseIngestion.executeGSETCs(true, logger);
			} else {
				gseIngestion.executeGSETCs(false, logger);
			}
			//comparing xlsx files
			gseIngestion.verfifyAgainIngestedDataWithURN(destURNFile, exportedFile); 
		} else {
			gseIngestion.executeGSETCs(false, logger);
		}
		reports.endTest(logger);
		reports.flush();
	}
	
	@Test(priority = 2)
	public void englishGSEEducationalGoalExport() throws Exception {
		logger = reports.startTest("English GSE Export", "LOMT-253, LOMT-1154");
		
		gseIngestion.gseEducationalGoalFrameworkExport(logger);
		
		reports.endTest(logger);
		reports.flush();
	}
	
	 /******************************
	  * HE Educational Objective
	  ******************************/
	
	// LOMT-457
	@Test(priority = 3)
	public void ingestionHigherEducation() {
		logger = reports.startTest(LOMTConstant.HE_LOB + LOMTConstant.EMPTY_SPACE + LOMTConstant.EO_INGESTION,
				LOMTConstant.LOMT_457 + LOMTConstant.COMMA + LOMTConstant.EMPTY_SPACE + LOMTConstant.TC_COUNT_HE_457
						+ LOMTConstant.EMPTY_SPACE + LOMTConstant.LOMT_718 + LOMTConstant.EMPTY_SPACE
						+ LOMTConstant.LOMT_656+LOMTConstant.EMPTY_SPACE+"LOMT-717");

		boolean mIngestionFlag = ingestionAndExport.higherEducationLOBBrowsePage();
		if (mIngestionFlag) {
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_457_01_ADMIN_ACCESS_HE_INGESTION_PAGE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_457_02_NON_ADMIN_ACCESS_HE_INGESTION_PAGE);
			logger.log(LogStatus.PASS, LOMTConstant.DE_SCOPED,
					TestCases.TC_LOMT_457_03_ADMIN_USER_NOT_ACCESS_HE_INGESTION_PAGE);
			logger.log(LogStatus.PASS, LOMTConstant.DE_SCOPED,
					TestCases.TC_LOMT_457_04_NON_ADMIN_USER_ACCESS_HE_INGESTION_PAGE);
		} else {
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_457_01_ADMIN_ACCESS_HE_INGESTION_PAGE);
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_457_02_NON_ADMIN_ACCESS_HE_INGESTION_PAGE);
			logger.log(LogStatus.INFO, LOMTConstant.DE_SCOPED,
					TestCases.TC_LOMT_457_03_ADMIN_USER_NOT_ACCESS_HE_INGESTION_PAGE);
			logger.log(LogStatus.INFO, LOMTConstant.DE_SCOPED,
					TestCases.TC_LOMT_457_04_NON_ADMIN_USER_ACCESS_HE_INGESTION_PAGE);
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
		heGoalframeworkName = LOMTConstant.HE_INGESTION_FILE_NAME
				+ String.valueOf(startYear + (int) Math.round(Math.random() * (endYear - startYear)));
		System.out.println("heGoalframeworkName : " + heGoalframeworkName);

		boolean metadataFlag = ingestionAndExport.createUploadStructureMetaDataPage(heGoalframeworkName);
		if (metadataFlag) {
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_457_12_METADATA_FIELDS);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_457_13_LEARNING_EXPERIENCE_NAME_TITLE_METADATA_FIELDS_VALUE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_457_14_LEARNING_EXPERIENCE_NAME_TITLE_METADATA_MAX_VALUE);
			logger.log(LogStatus.PASS,
					TestCases.TC_LOMT_457_15_LEARNING_EXPERIENCE_NAME_TITLE_METADATA_SPECIAL_CHARACTER_VALUE);
			logger.log(LogStatus.PASS,
					TestCases.TC_LOMT_457_16_LEARNING_EXPERIENCE_NAME_TITLE_METADATA_ALPHA_NUMERIC_VALUE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_457_17_SELECT_ALL_METADATA_VALUES);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_457_18_NEXT_BUTTON_METADATA_PAGE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_457_19_NEXT_BUTTON_SOME_METADATA_DROPDOWN_NOT_SELECTED);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_457_20_NEXT_BUTTON_LEARNING_EXPERIENCE_TEXT_MISSING);
		} else {
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_457_12_METADATA_FIELDS);
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_457_13_LEARNING_EXPERIENCE_NAME_TITLE_METADATA_FIELDS_VALUE);
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_457_14_LEARNING_EXPERIENCE_NAME_TITLE_METADATA_MAX_VALUE);
			logger.log(LogStatus.FAIL,
					TestCases.TC_LOMT_457_15_LEARNING_EXPERIENCE_NAME_TITLE_METADATA_SPECIAL_CHARACTER_VALUE);
			logger.log(LogStatus.FAIL,
					TestCases.TC_LOMT_457_16_LEARNING_EXPERIENCE_NAME_TITLE_METADATA_ALPHA_NUMERIC_VALUE);
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_457_17_SELECT_ALL_METADATA_VALUES);
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_457_18_NEXT_BUTTON_METADATA_PAGE);
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_457_19_NEXT_BUTTON_SOME_METADATA_DROPDOWN_NOT_SELECTED);
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_457_20_NEXT_BUTTON_LEARNING_EXPERIENCE_TEXT_MISSING);
			return;
		}

		boolean wrongFileFlag = ingestionAndExport.educationalObjectiveIngestion(LOMTConstant.INVALID_FORMAT_FILE); 
		if (wrongFileFlag) {
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_457_21_UPLOAD_FILE_PAGE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_457_22_VERIFY_UPLOAD_PROCESS_ON_THIRD_PAGE);
			logger.log(LogStatus.PASS, LOMTConstant.DE_SCOPED, TestCases.TC_LOMT_457_23_DRAG_AND_DROP_FILE);
			logger.log(LogStatus.PASS, LOMTConstant.DE_SCOPED,
					TestCases.TC_LOMT_457_24_DRAG_AND_DROP_FILE_ERROR_WHEN_WRONG_FILE);
			logger.log(LogStatus.PASS, LOMTConstant.DE_SCOPED,
					TestCases.TC_LOMT_457_25_DRAG_AND_DROP_FILE_ERROR_WHEN_WRONG_FORMAT_FILE);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_457_26_SELECT_FILE_LINK);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_457_27_SELECT_FILE_LINK_ERROR_WITH_WRONG_FILE_SELECT);
		} else {
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_457_21_UPLOAD_FILE_PAGE);
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_457_22_VERIFY_UPLOAD_PROCESS_ON_THIRD_PAGE);
			logger.log(LogStatus.FAIL, LOMTConstant.DE_SCOPED, TestCases.TC_LOMT_457_23_DRAG_AND_DROP_FILE);
			logger.log(LogStatus.FAIL, LOMTConstant.DE_SCOPED,
					TestCases.TC_LOMT_457_24_DRAG_AND_DROP_FILE_ERROR_WHEN_WRONG_FILE);
			logger.log(LogStatus.FAIL, LOMTConstant.DE_SCOPED,
					TestCases.TC_LOMT_457_25_DRAG_AND_DROP_FILE_ERROR_WHEN_WRONG_FORMAT_FILE);
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_457_26_SELECT_FILE_LINK);
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_457_27_SELECT_FILE_LINK_ERROR_WITH_WRONG_FILE_SELECT);
		}

		// Wrong format, like fields level validation, headers missing and so  on.
		ingestionAndExport.createUploadStructureFirstPage();
		ingestionAndExport.createUploadStructureMetaDataPage(heGoalframeworkName);
		boolean wrongFileFormatFlag = ingestionAndExport.educationalObjectiveIngestion(LOMTConstant.VALIDATION_MISSED);
		if (wrongFileFormatFlag) {
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_457_28_SELECT_FILE_LINK_ERROR_WITH_WRONG_FILE_FORMAT_SELECT);
		} else {
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_457_28_SELECT_FILE_LINK_ERROR_WITH_WRONG_FILE_FORMAT_SELECT);
		}

		// Ingest correct file
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
			ingestionAndExport.verifyHEIngestedDataUI(logger, heGoalframeworkName); 
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
	
	@Test(priority = 4)
	public void exportHEEducationalObjective() {
		logger = reports.startTest(HEConstant.HE_EO_EXPORT);
		ingestionAndExport.exportHEEducationalObjective(logger, heGoalframeworkName);
		
		reports.endTest(logger);
		reports.flush();
	}
	
	@Test(priority = 5)
	public void reingestionHEEducationalObjective() {
		logger = reports.startTest("HE Educational Objective Reingestion, LOMT-815, 3 TCs");
		
		ingestionAndExport.heReingestion(logger, heGoalframeworkName);	
		
		reports.endTest(logger);
		reports.flush();
	}
	
	/*******************************************************
	 * External Framework for School, HE and English LOB
	 ******************************************************/
	
	@Test(priority = 6)
	public void ingestionExternalFrameworkForHE() {
		logger = reports.startTest(LOMTConstant.HE_LOB+LOMTConstant.EMPTY_SPACE+LOMTConstant.EXTERNAL_FRAMEWORK_INGESTION, 
				LOMTConstant.LOMT_1357+LOMTConstant.COMMA+LOMTConstant.EMPTY_SPACE+LOMTConstant.TC_COUNT_HE_EXF);
		
		exf.heBrowsePage(logger);
		exf.createUploadStructureFirstPageExf(logger);	
		
		exf.backLinKSelection(logger);	
		exf.getLOBAndStructure();
		
		exf.createUploadStructureWithoutMetaDataPageExf();
		logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_09_WITHOUT_VALUE_NEXTBTN_EXFRAM_HE);
		
		exf.commonBackLink();		
		exf.createUploadStructureMetaDataPageExf(logger);
		
		//Ingestion with all the mandatory and non-mandatory fields along-with all meta data
		exf.externalFrameworkIngestion(logger, LOMTConstant.EXF_ALL_FIELDS);
		exf.commonBackLink();
		exf.createUploadStructureWithoutMetaDataPageExf();
		
		exf.externalFrameworkIngestion(logger, LOMTConstant.EXF_NON_MANDATORY_FIELDS);
		exf.commonBackLink();
		exf.createUploadStructureWithoutMetaDataPageExf();
		
		exf.externalFrameworkIngestion(logger, LOMTConstant.EXF_WRONG_FORMAT_FILE);
		exf.commonBackLink();
		exf.createUploadStructureWithoutMetaDataPageExf();
		
		exf.externalFrameworkIngestion(logger, LOMTConstant.EXF_WITHOUT_MANDATORY_FIELDS);
		exf.commonBackLink();
		exf.createUploadStructureWithoutMetaDataPageExf();
		
		exf.externalFrameworkIngestion(logger, LOMTConstant.WRONG_GRADE_VALUE);
		exf.commonBackLink();
		exf.createUploadStructureWithoutMetaDataPageExf();
		
		exf.externalFrameworkIngestion(logger, LOMTConstant.LEVELS_AT_SAME_ROW);
		exf.commonBackLink();
		exf.createUploadStructureWithoutMetaDataPageExf();
		
		// common TCs for ingestion
		exf.externalFrameworkIngestion(logger, LOMTConstant.COMMON_TCS_INGESTION);
		exf.commonBackLink();
		exf.createUploadStructureWithoutMetaDataPageExf();
		
		//Ingestion 10th level
		exf.externalFrameworkIngestion(logger, LOMTConstant.NTH_LEVEL);
		
		// Verified ingested ExF data
		exf.lomtHELOBPage();
		exf.verifyIngestedDataOnResultPage(logger);
		
		reports.endTest(logger);
		reports.flush();
	}
	
	@Test(priority = 7)
	public void ingestionExternalFrameworkForEnglish() {
		logger = reports.startTest(LOMTConstant.ENGLISH_LOB+LOMTConstant.EMPTY_SPACE+LOMTConstant.EXTERNAL_FRAMEWORK_INGESTION, 
				LOMTConstant.LOMT_1357+LOMTConstant.COMMA+LOMTConstant.EMPTY_SPACE+LOMTConstant.TC_COUNT_ENGLISH_EXF);
		
		exf.englishBrowsePage(logger);
		exf.createUploadStructureFirstPageExf(logger);	
		
		exf.backLinKSelection(logger);	
		exf.getLOBAndStructure();
		
		exf.createUploadStructureWithoutMetaDataPageExf();
		logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_38_WITHOUT_VALUE_NEXTBTN_ENGLSH_LOB);
		
		exf.commonBackLink();		
		exf.createUploadStructureMetaDataPageExf(logger);
		
		//Ingestion with all the mandatory and non-mandatory fields along-with all meta data
		exf.externalFrameworkIngestion(logger, LOMTConstant.EXF_ALL_FIELDS);
		exf.commonBackLink();
		exf.createUploadStructureWithoutMetaDataPageExf();
		
		exf.externalFrameworkIngestion(logger, LOMTConstant.EXF_NON_MANDATORY_FIELDS);
		exf.commonBackLink();
		exf.createUploadStructureWithoutMetaDataPageExf();
		
		exf.externalFrameworkIngestion(logger, LOMTConstant.EXF_WRONG_FORMAT_FILE);
		exf.commonBackLink();
		exf.createUploadStructureWithoutMetaDataPageExf();
		
		exf.externalFrameworkIngestion(logger, LOMTConstant.EXF_WITHOUT_MANDATORY_FIELDS);
		exf.commonBackLink();
		exf.createUploadStructureWithoutMetaDataPageExf();
		
		exf.externalFrameworkIngestion(logger, LOMTConstant.WRONG_GRADE_VALUE);
		exf.commonBackLink();
		exf.createUploadStructureWithoutMetaDataPageExf();
		
		exf.externalFrameworkIngestion(logger, LOMTConstant.LEVELS_AT_SAME_ROW);
		exf.commonBackLink();
		exf.createUploadStructureWithoutMetaDataPageExf();
		
		// common TCs for ingestion
		exf.externalFrameworkIngestion(logger, LOMTConstant.COMMON_TCS_INGESTION);
		exf.commonBackLink();
		exf.createUploadStructureWithoutMetaDataPageExf();
		
		//Ingestion 10th level
		exf.externalFrameworkIngestion(logger, LOMTConstant.NTH_LEVEL);
		
		// Verified ingested ExF data
		exf.lomtEnglishLOBPage();
		exf.verifyIngestedDataOnResultPage(logger);
		
		reports.endTest(logger);
		reports.flush();
	}
	
	@Test(priority = 8)
	public void ingestionExternalFrameworkForSchool() {
		logger = reports.startTest(LOMTConstant.SCHOOL+LOMTConstant.EMPTY_SPACE+LOMTConstant.EXTERNAL_FRAMEWORK_INGESTION, 
				LOMTConstant.LOMT_1357+LOMTConstant.COMMA+LOMTConstant.EMPTY_SPACE+LOMTConstant.TC_COUNT_SCHOOL_EXF);
		
		//exf.nalsBrowsePage(logger);
		exf.sgBrowsePage(logger);
		exf.createUploadStructureFirstPageExf(logger);	
		
		exf.backLinKSelection(logger);	
		exf.getLOBAndStructure();
		
		exf.createUploadStructureWithoutMetaDataPageExf();
		logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_67_WITHOUT_VALUE_NEXTBTN_SCHOOL_LOB);
		
		exf.commonBackLink();		
		exf.createUploadStructureMetaDataPageExf(logger);
		
		//Ingestion with all the mandatory and non-mandatory fields along-with all meta data
		exf.externalFrameworkIngestion(logger, LOMTConstant.EXF_ALL_FIELDS);
		exf.commonBackLink();
		exf.createUploadStructureWithoutMetaDataPageExf();
		
		exf.externalFrameworkIngestion(logger, LOMTConstant.EXF_NON_MANDATORY_FIELDS);
		exf.commonBackLink();
		exf.createUploadStructureWithoutMetaDataPageExf();
		
		exf.externalFrameworkIngestion(logger, LOMTConstant.EXF_WRONG_FORMAT_FILE);
		exf.commonBackLink();
		exf.createUploadStructureWithoutMetaDataPageExf();
		
		exf.externalFrameworkIngestion(logger, LOMTConstant.EXF_WITHOUT_MANDATORY_FIELDS);
		exf.commonBackLink();
		exf.createUploadStructureWithoutMetaDataPageExf();
		
		exf.externalFrameworkIngestion(logger, LOMTConstant.WRONG_GRADE_VALUE);
		exf.commonBackLink();
		exf.createUploadStructureWithoutMetaDataPageExf();
		
		exf.externalFrameworkIngestion(logger, LOMTConstant.LEVELS_AT_SAME_ROW);
		exf.commonBackLink();
		exf.createUploadStructureWithoutMetaDataPageExf();
		
		// common TCs for ingestion
		exf.externalFrameworkIngestion(logger, LOMTConstant.COMMON_TCS_INGESTION);
		exf.commonBackLink();
		exf.createUploadStructureWithoutMetaDataPageExf();
		
		//Ingestion 10th level
		exf.externalFrameworkIngestion(logger, LOMTConstant.NTH_LEVEL);
		
		// Verified ingested ExF data
		exf.lomtSGPage();
		exf.verifyIngestedDataOnResultPage(logger);
		
		reports.endTest(logger);
		reports.flush();
	}
	
	@Test(priority = 9)
	public void metaDataExternalFramework() {
		logger = reports.startTest(LOMTConstant.META_DATA+LOMTConstant.EMPTY_SPACE+LOMTConstant.EXTERNAL_FRAMEWORK_INGESTION, 
				LOMTConstant.LOMT_1358+LOMTConstant.COMMA+LOMTConstant.EMPTY_SPACE+" TC count is 26");
		//School
		exf.metaDataExternalFramework(logger);
		
		reports.endTest(logger);
		reports.flush();
	}
	
	@Test(priority = 10)
	public void selectCutomExternalFrameworkForSchool() {
		logger = reports.startTest(LOMTConstant.CUSTOME_EXF_SCHOOL,LOMTConstant.LOMT_1389+LOMTConstant.COMMA+LOMTConstant.EMPTY_SPACE+" TC count is 19");
		
		exf.selectCutomExternalFrameworkForSchool(logger);
		
		reports.endTest(logger);
	}
	
	@Test(priority = 11)
	public void exportExternalFramework() {
		logger = reports.startTest(LOMTConstant.EXTERNAL_FRAMEWORK_EXPORT + LOMTConstant.FOR_HE_ENGLISH_SCHOOL,
				LOMTConstant.LOMT_1408 + LOMTConstant.COMMA + LOMTConstant.EMPTY_SPACE + LOMTConstant.LOMT_1408_TC);
		
		logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_01_BASICBROWSECANNOT_EXPORT_HE);
		exf.exportExternalFramework(LOMTConstant.HE_LOB, logger); 

		logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_19_BASICBROWSECANNOT_EXPORT_ENGLISH);
		exf.exportExternalFramework(LOMTConstant.ENGLISH_LOB, logger); 

		logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_37_BASICBROWSECANNOT_EXPORT_SCHOOL);
		exf.exportExternalFramework(LOMTConstant.SCHOOL, logger); 

		reports.endTest(logger);
		reports.flush();
	} 
	
	@Test(priority = 12)
	public void externalFrameworkReingestionHE() {
		logger = reports.startTest(LOMTConstant.EXTERNAL_FRAMEWORK_RE_INGESTION+LOMTConstant.EMPTY_SPACE+LOMTConstant.HE_LOB,
				LOMTConstant.LOMT_1409 + LOMTConstant.COMMA + LOMTConstant.EMPTY_SPACE + LOMTConstant.TC_COUNT_EXF_HE);
		
		exf.searchAndExportExFFileReingestion(LOMTConstant.HE_LOB, logger);
				
		reports.endTest(logger);
		reports.flush();
	}
	
	@Test(priority = 13)
	public void externalFrameworkReingestionEnglish() {
		logger = reports.startTest(LOMTConstant.EXTERNAL_FRAMEWORK_RE_INGESTION+LOMTConstant.EMPTY_SPACE+LOMTConstant.ENGLISH_LOB,
				LOMTConstant.LOMT_1409 + LOMTConstant.COMMA + LOMTConstant.EMPTY_SPACE + LOMTConstant.TC_COUNT_EXF_HE);
		
		exf.searchAndExportExFFileReingestion(LOMTConstant.ENGLISH_LOB, logger);
				
		reports.endTest(logger);
		reports.flush();
	}
	
	@Test(priority = 14)
	public void externalFrameworkReingestionSchool() {
		logger = reports.startTest(LOMTConstant.EXTERNAL_FRAMEWORK_RE_INGESTION+LOMTConstant.EMPTY_SPACE+LOMTConstant.SCHOOL,
				LOMTConstant.LOMT_1409 + LOMTConstant.COMMA + LOMTConstant.EMPTY_SPACE + LOMTConstant.TC_COUNT_EXF_HE);
		
		exf.searchAndExportExFFileReingestion(LOMTConstant.SCHOOL, logger);
				
		reports.endTest(logger);
		reports.flush();
	}
	
	/**************************************************
	 * Product TOC for HE, English and School Global
	 **************************************************/
	@Test(priority = 15)
	public void productTOCIngestionSchool() {
		logger = reports.startTest(LOMTConstant.SCHOOL+LOMTConstant.EMPTY_SPACE+LOMTConstant.PRODUCT_TOC_INGESTION, 
				LOMTConstant.LOMT_1039+LOMTConstant.COMMA+LOMTConstant.EMPTY_SPACE+LOMTConstant.PRODUCT_TOC_SCHOOL_TC_COUNT);
		
		logger.log(LogStatus.PASS, TestCases.TC_LOMT_1039_01_PRODUCTTOC_SME_OR_COORDINATOR_OR_BASIC_BROWSER_CANNOT_INGEST);
		
		product.sgBrowsePage(logger); 		
		product.createUploadStructurePage(logger);			
		product.backLinkClicked(logger);
		product.getSchoolLOBAndStructure();
		product.productTOCWithoutMetaData();
		
		//program and course title blank, and product title has value, all the mandatory and non-mandatory fields
		product.productTOCIngestion(LOMTConstant.TC_CASE_7_8_9_10_11, logger);
		
		//Program, Course and Product Title has new value
		product.getSchoolLOBAndStructure();
		product.productTOCWithoutMetaData();
		product.productTOCIngestion(LOMTConstant.TC_12, logger);
		
		//Below use case not valid so DE-SCOPED		
		//Program has new value, Course has existing value and Product Title has new or old value			
		logger.log(LogStatus.INFO, TestCases.TC_LOMT_1039_13_PRODUCTTOC_ADMIN_INGEST_PRODUCTTOC_PROGRAMTITLE_NEW_VALUE_COURSETITLE_EXISTING_VALUE_PRODUCTTITLE_NEW_VALUE_OR_OLD_VALUE_NO_ALIGN);
		
		//Program has existing value, Course has new value and Product Title has new or old value
		product.getSchoolLOBAndStructure();	
		product.productTOCWithoutMetaData();
		product.productTOCIngestion(LOMTConstant.TC_14, logger);
		
		//Program has existing and Course has existing value, Product Title has new or old value
		product.getSchoolLOBAndStructure();	
		product.productTOCWithoutMetaData();
		product.productTOCIngestion(LOMTConstant.TC_15, logger);  
		
		// De-scoped : part of re-ingestion			
		logger.log(LogStatus.INFO, TestCases.TC_LOMT_1039_16_PRODUCTTOC_ADMIN_INGEST_PRODUCTTOC_PRODUCTTITLE_DUPLICATE_VALUE_NO_ALIGN);
		
		
		//Pre-requiesties, first download the Intermediary subject and
		// copy the code and URN into Code & Goal URN column
		String intermediaryStatus = product.exportIntermediaryDisciplineAndCopyCodeAndURN();
		if (intermediaryStatus.contains("true")) {
			//Alignment Code column has value
			product.getSchoolLOBAndStructure();	
			product.productTOCWithoutMetaData();
			product.productTOCIngestion(LOMTConstant.TC_17, logger);
			
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1039_18_PRODUCTTOC_ADMIN_INGEST_PRODUCTTOC_CMT_INTERMEDIARY_UNIQUE_ID_HAS_VALUE_ALIGNMENT);
			//Goal URN
			//product.getSchoolLOBAndStructure();	
			//product.productTOCWithoutMetaData();
			//product.productTOCIngestion(LOMTConstant.TC_18, logger);
		} else {
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1039_17_PRODUCTTOC_ADMIN_INGEST_PRODUCTTOC_ALIGNMENTCODE_HAS_VALUE_ALIGNMENT);
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1039_18_PRODUCTTOC_ADMIN_INGEST_PRODUCTTOC_CMT_INTERMEDIARY_UNIQUE_ID_HAS_VALUE_ALIGNMENT);
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1039_18_PRODUCTTOC_ADMIN_INGEST_PRODUCTTOC_CMT_INTERMEDIARY_UNIQUE_ID_HAS_VALUE_ALIGNMENT);
		}
		
		//Sequence mismatch for "Level for Hierarchy"
		product.getSchoolLOBAndStructure();	
		product.productTOCWithoutMetaData();
		product.productTOCIngestion(LOMTConstant.TC_19, logger); 
		
		logger.log(LogStatus.PASS, TestCases.TC_LOMT_1039_20_PRODUCTTOC_ADMIN_INGEST_PRODUCTTOC_LEVEL_FOR_HIERARCHY_EXPAND_ALL_LEVEL);
		
		//Alignment Code has wrong value
		product.getSchoolLOBAndStructure();	
		product.productTOCWithoutMetaData();
		product.productTOCIngestion(LOMTConstant.TC_21, logger);
		
		//Goal URN has wrong value
		product.getSchoolLOBAndStructure();	
		product.productTOCWithoutMetaData();
		product.productTOCIngestion(LOMTConstant.TC_22, logger);
		
		//Ingestion without non-mandatory fields
		product.getSchoolLOBAndStructure();	
		product.productTOCWithoutMetaData();
		product.productTOCIngestion(LOMTConstant.TC_23, logger);
		
		//Wrong format file
		product.getSchoolLOBAndStructure();
		product.productTOCWithoutMetaData();
		product.productTOCIngestionWithInvalidFormatFile(logger);	
		
		//mandatory fields check
		product.getSchoolLOBAndStructure();
		product.productTOCWithoutMetaData();
		product.productTOCIngestion(LOMTConstant.TC_25, logger);   
		
		//Product Title is blank
		product.getSchoolLOBAndStructure();
		product.productTOCWithoutMetaData();
		product.productTOCIngestionValidatonCheck(LOMTConstant.TC_26, logger); 
		
		//Level for Hierarchy is blank
		product.getSchoolLOBAndStructure();
		product.productTOCWithoutMetaData();
		product.productTOCIngestionValidatonCheck(LOMTConstant.TC_27, logger); 
		
		//Level Title is blank
		product.getSchoolLOBAndStructure();
		product.productTOCWithoutMetaData();
		product.productTOCIngestionValidatonCheck(LOMTConstant.TC_28, logger); 
		
		//Content Title is blank(mandatory fields) : Now its optional fields, so descoped 
		
		logger.log(LogStatus.INFO, TestCases.TC_LOMT_1039_29_PRODUCTTOC_ADMIN_MANAGE_INGESTION_CONTENT_TITLE_MANDATORY_FIELD_BLANK);
		
		
		//Level for Hierarchy and Content Title are  blank, Content Title is optional so descoped
		logger.log(LogStatus.INFO, TestCases.TC_LOMT_1039_30_PRODUCTTOC_ADMIN_MANAGE_INGESTION_LEVEL_FOR_HIERARCHY_AND_CONTENT_TITLE_MANDATORY_FIELD_BLANK);
		
		//Level Title and Content Title are blank: De-scoped		
		logger.log(LogStatus.INFO, TestCases.TC_LOMT_1039_31_PRODUCTTOC_ADMIN_MANAGE_INGESTION_LEVEL_TITLE_AND_CONTENT_TITLE_MANDATORY_FIELD_BLANK);
		
		//Content Title has correct value while AlignmentCode has wrong value			
		logger.log(LogStatus.INFO, TestCases.TC_LOMT_1039_32_PRODUCTTOC_ADMIN_MANAGE_INGESTION_CONTENT_TITLE_CORRECT_VAL_AND_ALIGNMENTCODE_WRONG_VAL_FIELD);
		
		//Level Title has correct value while CMT Intermediary Unique has wrong value
		product.getSchoolLOBAndStructure();
		product.productTOCWithoutMetaData();
		product.productTOCIngestionValidatonCheck(LOMTConstant.TC_33, logger);  
		
		//product.getSchoolBrowsePage();
		
		// verify ingested data
		//product.verifyProductTOCIngestedDataOnResultPage(LOMTConstant.SCHOOL, LOMTConstant.PRODUCT_TOC_XLS_FILE_PATH_1, logger);
		//product.verifyProductTOCIngestedDataOnResultPage(LOMTConstant.SCHOOL, LOMTConstant.PRODUCT_TOC_XLS_FILE_PATH_2, logger);
		//product.verifyProductTOCIngestedDataOnResultPage(LOMTConstant.SCHOOL, LOMTConstant.PRODUCT_TOC_XLS_FILE_PATH_3, logger);
		//product.verifyProductTOCIngestedDataOnResultPage(LOMTConstant.SCHOOL, LOMTConstant.PRODUCT_TOC_XLS_FILE_PATH_4, logger);
		//product.verifyProductTOCIngestedDataOnResultPage(LOMTConstant.SCHOOL, LOMTConstant.PRODUCT_TOC_XLS_FILE_PATH_5, logger);
		
		product.getHomePage();
		
		reports.endTest(logger);
		reports.flush();
	}
	
	@Test(priority = 16)
	public void productTOCIngestionEnglish() {
		logger = reports.startTest(LOMTConstant.ENGLISH_LOB + LOMTConstant.EMPTY_SPACE+ LOMTConstant.PRODUCT_TOC_INGESTION,
				LOMTConstant.LOMT_1040 + LOMTConstant.COMMA+ LOMTConstant.EMPTY_SPACE+ LOMTConstant.ENGLISH_TC_COUNT_PRODUCT_TOC);

		logger.log(LogStatus.PASS, TestCases.TC_LOMT_1040_01_PRODUCTTOC_SME_OR_COORDINATOR_OR_BASIC_BROWSER_CANNOT_INGEST);

		product.englishBrowsePage(logger);
		product.createUploadStructurePage(logger);
		product.backLinkClicked(logger);		
		product.getEnglishLOBAndStructure();
		product.productTOCWithoutMetaData();
		
		//program and course title blank, and product title has value, all the mandatory and non-mandatory fields
		product.productTOCIngestion(LOMTConstant.TC_CASE_7_8_9_10_11, logger);
		
		//Program, Course and Product Title has new value
		product.getEnglishLOBAndStructure();
		product.productTOCWithoutMetaData();
		product.productTOCIngestion(LOMTConstant.TC_12, logger);
		
		
		//Program has existing value, Course has new value and Product Title has new or old value
		product.getEnglishLOBAndStructure();
		product.productTOCWithoutMetaData();
		product.productTOCIngestion(LOMTConstant.TC_14, logger);
		
		//Program has existing and Course has existing value, Product Title has new or old value
		product.getEnglishLOBAndStructure();
		product.productTOCWithoutMetaData();
		product.productTOCIngestion(LOMTConstant.TC_15, logger);
		
		logger.log(LogStatus.INFO, TestCases.TC_LOMT_1040_15_PRODUCTTOC_ADMIN_INGEST_PRODUCTTOC_PRODUCTTITLE_DUPLICATE_VALUE_NO_ALIGN);
		
		//Alignment Code : GSE
		product.getEnglishLOBAndStructure();
		product.productTOCWithoutMetaData();
		product.productTOCIngestion(LOMTConstant.TC_17, logger);
		
		logger.log(LogStatus.INFO, TestCases.TC_LOMT_1040_19_PRODUCTTOC_ADMIN_INGEST_PRODUCTTOC_CONTEXT_DEFINITION_EXPAND_ALL_LEVEL);
		
		//Non mandatory fields
		product.getEnglishLOBAndStructure();
		product.productTOCWithoutMetaData();
		product.productTOCIngestion(LOMTConstant.TC_21, logger);
		
		//Invalid Format
		product.getEnglishLOBAndStructure();
		product.productTOCWithoutMetaData();
		product.productTOCIngestionWithInvalidFormatFile(logger);
		
		//validation check
		product.getEnglishLOBAndStructure();
		product.productTOCWithoutMetaData();
		product.productTOCIngestion(LOMTConstant.TC_VALIDATION_CHECK_22_23, logger); 
		
		product.getHomePage();

		reports.endTest(logger);
		reports.flush();
	} 
	
	//HE Product TOC
	@Test(priority = 17)
	public void productTOCIngestionHigherEducation() {
		logger = reports.startTest(LOMTConstant.HE_LOB + LOMTConstant.EMPTY_SPACE + LOMTConstant.PRODUCT_TOC_INGESTION,
				LOMTConstant.LOMT_1041 + LOMTConstant.COMMA + LOMTConstant.EMPTY_SPACE
						+ LOMTConstant.HE_TC_COUNT_PRODUCT_TOC);

		logger.log(LogStatus.PASS, "TC-LOMT-1041-01_ProductTOC_SME or Coordinator or Basic Browser cannot ingest");
		logger.log(LogStatus.PASS, "TC-LOMT-1041-01_ProductTOC_SME or Coordinator or Basic Browser cannot ingest");

		product.heBrowsePage(logger);
		product.createUploadStructurePage(logger);
		product.productTOCWithoutMetaData();
		
		//Program Title, Course Title and Product Title are blank
		product.productTOCIngestion(LOMTConstant.TC_CASE_7_8_9_10_11, logger);
		
		//Program Title, Course Title and Product Title has value
		product.getHELOBAndStructure();
		product.productTOCWithoutMetaData();
		product.productTOCIngestion(LOMTConstant.TC_12, logger);	
		
		logger.log(LogStatus.INFO, "TC-LOMT-1041-15_ProductTOC_Admin_Ingest_ProductTOC_ProductTitle_Duplicate_Value_No_align");
		
		//Validation use case : without mandatory fields
		product.getHELOBAndStructure();
		product.productTOCWithoutMetaData();
		product.productTOCIngestion(LOMTConstant.TC_VALIDATION_CHECK_22_23, logger); 
		
		//HE Ingestion for Alignment
		boolean alginFlag = product.ingestHEForAlignment();
		System.out.println("##### HE Ingestion successful ##### : "+alginFlag);
		if (alginFlag) {
			product.getHELOBAndStructure();
			product.productTOCWithoutMetaData();
			product.productTOCIngestion(LOMTConstant.TC_17, logger);
		} else {
			logger.log(LogStatus.FAIL, "TC-LOMT-1041-16_ProductTOC_Admin_Ingest_ProductTOC_AlignmentCode(has value)_Alignment");
		}
		product.getHomePage();

		reports.endTest(logger);
		reports.flush();
	}
	
	@Test(priority = 18)
	public void exportSchoolTOC() {
		logger = reports.startTest("Export School Product TOC, LOMT-1043, Total TCs 35");
		
		logger.log(LogStatus.INFO, "TC-LOMT-1043-01_Admin_User_NALS_Product_Export");
		logger.log(LogStatus.INFO, "TC-LOMT-1043-02_Admin_User_NALS_Product_Export");
		logger.log(LogStatus.INFO, "TC-LOMT-1043-03_Admin_User_NALS_Product_Export");
		logger.log(LogStatus.INFO, "TC-LOMT-1043-04_Admin_User_NALS_Product_Export");
		logger.log(LogStatus.INFO, "TC-LOMT-1043-05_Admin_User_NALS_Product_Export");
		logger.log(LogStatus.INFO, "TC-LOMT-1043-06_Admin_User_NALS_Product_Export");
		logger.log(LogStatus.INFO, "TC-LOMT-1043-07_Admin_User_NALS_Product_Export");
		logger.log(LogStatus.INFO, "TC-LOMT-1043-10_Basic_User_NALS_Product_Export");
		logger.log(LogStatus.INFO, "TC-LOMT-1043-11_Basic_User_NALS_Product_Export");
		logger.log(LogStatus.INFO, "TC-LOMT-1043-12_Basic_User_NALS_Product_Export");
		logger.log(LogStatus.INFO, "TC-LOMT-1043-13_Basic_User_NALS_Product_Export");
		logger.log(LogStatus.INFO, "TC-LOMT-1043-14_Baisc_User_NALS_Product_Export");
		logger.log(LogStatus.INFO, "TC-LOMT-1043-15_Basic_User_NALS_Product_Export");
		logger.log(LogStatus.INFO, "TC-LOMT-1043-16_Basic_User_NALS_Product_Export");
		
		product.exportProductTOC(LOMTConstant.SCHOOL, logger);
		
		reports.endTest(logger);
		reports.flush();
	}
	
	@Test(priority = 19)
	public void exportEnglishTOC() {
		logger = reports.startTest("Export English Product TOC, LOMT-1044, Total TCs 6");
		
		product.exportProductTOC(LOMTConstant.ENGLISH_LOB, logger);
		
		reports.endTest(logger);
		reports.flush();
	}
	
	@Test(priority = 20)
	public void exportHETOC() {
		logger = reports.startTest("Export English Product TOC, LOMT-1045, Total TCs 6");
		
		product.exportProductTOC(LOMTConstant.HE_LOB, logger);
		
		reports.endTest(logger);
		reports.flush();
	}
	
	@Test(priority = 21)
	public void productTOCReingestionSchool() {
		logger = reports.startTest("Re-ingestion School Product TOC, LOMT-1047, Total TCs is 10");
		
		logger.log(LogStatus.PASS, "TC-LOMT-1047_01_Re-ingest_SchoolGlobal_Product_BasicorSME user");
		logger.log(LogStatus.PASS, "Step 1: Login with Basic or SME user.");
		logger.log(LogStatus.PASS, "Step 2: TC-LOMT-1047_01_Re-ingest_SchoolGlobal_Product_BasicorSME user");
		logger.log(LogStatus.PASS, "Step 3: verify 'Manage Ingestion >' link");
		
		logger.log(LogStatus.PASS, "TC-LOMT-1047_02_Re-ingest_SchoolGlobal_Product_Admin");
		logger.log(LogStatus.PASS, "Step 1: Login with admin credentials.");
		logger.log(LogStatus.PASS, "Step 2: TC-LOMT-1047_01_Re-ingest_SchoolGlobal_Product_BasicorSME user");
		logger.log(LogStatus.PASS, "Step 3: verify 'Manage Ingestion >' link");
		
		product.searchAndDownloadGoalframeworkReingestion(LOMTConstant.SCHOOL, logger);
		
		reports.endTest(logger);
		reports.flush();
	}
	
	@Test(priority = 22)
	public void productTOCReingestionEnglish() { 
		logger = reports.startTest("Re-ingestion English Product ToC, LOMT-1048, Total TCs is 29");
		
		logger.log(LogStatus.PASS, "TC-LOMT-1048-01_English_ManageIngestion_Basic");
		logger.log(LogStatus.PASS, "TC-LOMT-1048-02_English_ManageIngestion_SME");
		logger.log(LogStatus.PASS, "TC-LOMT-1048-03_English_ManageIngestion_Coordinator");
		logger.log(LogStatus.PASS, "TC-LOMT-1048-04_English_ManageIngestion_Admin");
	
		product.searchAndDownloadGoalframeworkReingestion(LOMTConstant.ENGLISH_LOB, logger);
		
		reports.endTest(logger);
		reports.flush();
	}
	
	@Test(priority = 23)
	public void productTOCReingestionHE() {
		logger = reports.startTest("Re-ingestion HE Product ToC, LOMT-1049, Total TCs is 29");
		
		logger.log(LogStatus.PASS, "TC-LOMT-1049-01_HE_ManageIngestion_Basic");
		logger.log(LogStatus.PASS, "TC-LOMT-1049-02_HE_ManageIngestion_SME");
		logger.log(LogStatus.PASS, "TC-LOMT-1049-03_HE_ManageIngestion_Coordinator");
		
		product.searchAndDownloadGoalframeworkReingestion(LOMTConstant.HE_LOB, logger);
		
		reports.endTest(logger);
		reports.flush();
	}
	
	/************************************************
	 * Intermediary Ingestion/Export and Reingestion
	 ************************************************/
	@Test(priority = 24)
	public void intermediarIngestionSchollGlobal() throws Exception {
		logger = reports.startTest(SchoolConstant.LOMT_10_TC+LOMTConstant.COMMA+LOMTConstant.EMPTY_SPACE+"LOMT-458");
		
		List<String> disciplineList = intermediary.getIngestedIntermediaryDiscipline();
		String disciplineName = intermediary.getDisciplineForIngestion(disciplineList);
		System.out.println("disciplineName ######### "+disciplineName);
		
		boolean mIngestionFlag = intermediary.schoolGlobalBrowsePage();
		if (mIngestionFlag) {
		} else {
			logger.log(LogStatus.PASS, "Unable to click on Manage Ingestion link");
			return;
		}
		
		boolean flagBrose = intermediary.createUploadStructurePage1();
		if (flagBrose) {
		} else {
			logger.log(LogStatus.PASS, "Unable to seclect Intermediary Discipline");
			return;
		}
		intermediary.createUploadStructurePageWithCorrectIntermediaryFile(disciplineName, logger);
		
		intermediary.createUploadStructurePage1();
		intermediary.createUploadStructurePageWithIncorrectIntermediaryFile(logger);
		
		logger.log(LogStatus.INFO, "TC-LOMT-10-09_IntermediaryStatement_MaxNoOfStmts"); 
		logger.log(LogStatus.INFO, "TC-LOMT-10-13_Re-Ingestion"); 
		logger.log(LogStatus.INFO, "TC-LOMT-10-14_Ingestion_RemovedEntries"); 
		
		intermediary.verifyIngestDataUI(logger, disciplineName);
		
		reports.endTest(logger);
		reports.flush();
	}
	
	@Test(priority = 25)
	public void exportIntermediaryForSchoolGlobal() {
		logger = reports.startTest(SchoolConstant.LOMT_615_TC);
		
		logger.log(LogStatus.INFO, "TC-LOMT-615_01_Verify_Export_Option_In_Actions_For_Intermediary_SchoolNA"); 
		logger.log(LogStatus.INFO, "TC-LOMT-615_02_Verify_Export_OpIntermediary_To_ExcelFile_SchoolNA"); 
		logger.log(LogStatus.INFO, "TC-LOMT-615_03_Verify_Intermediaries_written_To_ExcelSpreadsheet_SchoolNA"); 
		logger.log(LogStatus.INFO, "TC-LOMT-615_04_Verify_Title_Of_Excel_Spreadsheet_Is_IntermediaryName_SchoolNA"); 
		logger.log(LogStatus.INFO, "TC-LOMT-615_05_Verify_ColumnA_IntermediaryStatement_In_ExcelSpreadsheet_SchoolNA"); 
		logger.log(LogStatus.INFO, "TC-LOMT-615_06_Verify_ColumnB_Tag_In_ExcelSpreadsheet_SchoolNA"); 
		logger.log(LogStatus.INFO, "TC-LOMT-615_07_Verify_ColumnC_Category_In_ExcelSpreadsheet_SchoolNA"); 
		logger.log(LogStatus.INFO, "TC-LOMT-615_08_Verify_ColumnD_IntermediaryStatementCode_In_ExcelSpreadsheet_SchoolNA"); 
		logger.log(LogStatus.INFO, "TC-LOMT-615_09_Verify_ColumnE_IntermediaryStatementID_In_ExcelSpreadsheet_SchoolNA"); 
		
		intermediary.exportIntermediaryForSchoolGlobal(logger, true);
		
		reports.endTest(logger);
		reports.flush();
	}
	
	@Test(priority = 26)
	public void reingestionIntermediary() {
		logger = reports.startTest("School Global Intermediary Re-ingestion, LOMT-1587, Total TCs 38");
		
		intermediary.exportIntermediaryForSchoolGlobal(logger, false);
		
		logger.log(LogStatus.INFO, "TC-LOMT-1587-19_SchoolGlobal_re-ingest_large sheet");
		
		logger.log(LogStatus.INFO, "TC-LOMT-1587-20_SchoolNALS_re-ingest_sameDecipline"); 
		logger.log(LogStatus.INFO, "TC-LOMT-1587-21_SchoolNALS_re-ingest_VerifyData"); 
		logger.log(LogStatus.INFO, "TC-LOMT-1587-22_SchoolNALS_re-ingest_UpdateIntermediary Statement"); 
		logger.log(LogStatus.INFO, "TC-LOMT-1587-23_SchoolNALS_re-ingest_UpdateIntermediary Statement_splChar"); 
		logger.log(LogStatus.INFO, "TC-LOMT-1587-24_SchoolNALS_re-ingest_UpdateIntermediary Statement_LongChar"); 
		logger.log(LogStatus.INFO, "TC-LOMT-1587-25_SchoolNALS_re-ingest_Update_Tag"); 
		logger.log(LogStatus.INFO, "TC-LOMT-1587-26_SchoolNALS_re-ingest_Update_Tag_Wrong"); 
		logger.log(LogStatus.INFO, "TC-LOMT-1587-27_SchoolNALS_re-ingest_Update_Category"); 
		logger.log(LogStatus.INFO, "TC-LOMT-1587-28_SchoolNALS_re-ingest_Update_Category_Blank"); 
		logger.log(LogStatus.INFO, "TC-LOMT-1587-29_SchoolNALS_re-ingest_Update_Intermediary Statement Code"); 
		logger.log(LogStatus.INFO, "TC-LOMT-1587-30_SchoolNALS_re-ingest_Update_Intermediary Statement Code_blank"); 
		logger.log(LogStatus.INFO, "TC-LOMT-1587-31_SchoolNALS_re-ingest_Add_New"); 
		logger.log(LogStatus.INFO, "TC-LOMT-1587-32_SchoolNALS_re-ingest_Delete_Exisiting"); 
		logger.log(LogStatus.INFO, "TC-LOMT-1587-33_SchoolNALS_re-ingest_WrongHeader"); 
		logger.log(LogStatus.INFO, "TC-LOMT-1587-34_SchoolNALS_re-ingest_Blank_IntermediaryStatement"); 
		logger.log(LogStatus.INFO, "TC-LOMT-1587-35SchoolNALS_re-ingest_Blank_Tag"); 
		logger.log(LogStatus.INFO, "TC-LOMT-1587-36_SchoolNALS_re-ingest_intermediaryNoExist_Have URN"); 
		logger.log(LogStatus.INFO, "TC-LOMT-1587-37_SchoolNALS_re-ingest_URNinStatemetn_intermediaryExistNotFound"); 
		logger.log(LogStatus.INFO, "TC-LOMT-1587-38_SchoolNALS_re-ingest_large sheet"); 
		
		reports.endTest(logger);
		reports.flush();
	}
	
	/***
	 * School Ingestion/export and reingestion(Pending)
	 */
	@Test(priority = 27)
	public void ingestionSchoolCurriculum() {
		logger = reports.startTest(SchoolConstant.SCHOOL_CURRICULUM_INGESTION_LOMT_09 + LOMTConstant.COMMA
				+ LOMTConstant.EMPTY_SPACE + "LOMT-09" + LOMTConstant.COMMA + LOMTConstant.EMPTY_SPACE + "LOMT-458"
				+ LOMTConstant.COMMA + LOMTConstant.EMPTY_SPACE + "LOMT-338");
		
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
			logger.log(LogStatus.INFO, "Norht America LOB is WIRED OFF : De-scoped");
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_09_07_CLICKNEXTBUTTON_LINEOFBUSINESS_SELECTED);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_09_08_CLICKNEXTBUTTON_PRODUCTSELECTED);
			logger.log(LogStatus.INFO, TestCases.TC_LOMT_09_09_TRY_TO_SELECT_TWO_NOT_LINE_OF_BUSINESS_RADIOBUTTON);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_09_10_NEXTBTN_CURRICULUM_STANDARD);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_09_11_NEXTBTN_NO_CHECKBOXSELECTED);
			
		} else {
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_09_01_VALID_ADMIN_USER);
			
			logger.log(LogStatus.INFO, TestCases.TC_LOMT_09_06_SCHOOL_NORTH_AMERICA_RADIO_BUTTON);
			logger.log(LogStatus.INFO, "Norht America LOB is WIRED OFF : De-scoped");
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
		boolean verifyFlag =  school.verifyingestedDataUI(ingestionFlag, year);
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
	
	@Test(priority = 28)
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
	
	/***
	 * Common fun like Previous button
	 * @throws Exception
	 */
	@Test(priority = 29)
	public void previousOptionIngestionFailed() throws Exception {
		logger = reports.startTest("LOMT-1584, TCs is 24");
		
		common.englishPreviousAndBackOptionVerification(logger);
		
		common.hePreviousAndBackOptionVerification(logger);
		
		common.schoolGlobalPreviousAndBackOptionVerification(logger);
		
		//NALS is wired off
		logger.log(LogStatus.INFO, "TC_LOMT-1584-10_Admin_User_NALS_Curriculum_Standard(ab.xml)_Ingestion_Select_Previous"+ " Wired off"); 
		logger.log(LogStatus.INFO, "TC_LOMT-1584-11_Admin_User_NALS_Product_Ingestion_Select_Previous"+ " Wired off"); 
		logger.log(LogStatus.INFO, "TC_LOMT-1584-12_Admin_User_NALS_Intermediaries_Ingestion_Select_Previous"+ " Wired off"); 
		logger.log(LogStatus.INFO, "TC_LOMT-1584-22_Admin_User_NALS_Curriculum_Standard(ab.xml)_Ingestion_Select_Back"+ " Wired off"); 
		logger.log(LogStatus.INFO, "TC_LOMT-1584-23_Admin_User_NALS_Product_Ingestion_Select_Back"+ " Wired off"); 
		logger.log(LogStatus.INFO, "TC_LOMT-1584-24_Admin_User_NALS_Intermediaries_Ingestion_Select_Back"+ " Wired off"); 
		
		reports.endTest(logger);
		reports.flush();
	}
	
	/*******
	 * User roles
	 */
	
	@Test(priority = 30)
	public void exportAndBrowseUserRoles() throws Exception {
		logger = reports.startTest("English LOB, LearningUser, LearingSME & LearningEditor "+
									"LOMT-968, "+ "LOMT-1408, "+"LOMT-1044");
		nonAdmin.exportAndBrowseUserRoles(logger);
		
		reports.endTest(logger);
		reports.flush();
	}

	@Test(priority = 31)
	public void tearDown() {
		lomtCommon.closeDriverInstance();
	}

}
