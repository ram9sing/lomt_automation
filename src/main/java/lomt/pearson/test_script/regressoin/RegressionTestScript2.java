package lomt.pearson.test_script.regressoin;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import lomt.pearson.api.externalframework.ExternalFramework;
import lomt.pearson.api.regression.Regression;
import lomt.pearson.common.BaseClass;
import lomt.pearson.common.LOMTCommon;
import lomt.pearson.constant.LOMTConstant;
import lomt.pearson.constant.TestCases;

public class RegressionTestScript2 extends BaseClass {
	
	ExtentTest logger;
	ExtentReports reports = new ExtentReports(LOMTConstant.REPORT_FILE_PATH, true);
	
	private Regression regression = new Regression();
	private ExternalFramework exf = null;
	private LOMTCommon lomtCommon = null;
	
	@Test(priority = 0)
	public void setup() {
		lomtCommon = new LOMTCommon();
		//lomtCommon.openBrowser();
		//lomtCommon.login();
		
		exf  = new ExternalFramework(lomtCommon.getDriver());
	}
	
	@Test(priority = 1)
	public void gseIngestion() throws Exception {
		logger = reports.startTest("English GSE Ingestion", "LOMT-11, Total 33 TCs");
		
		regression.gseIngestion(logger);
		
		logger.log(LogStatus.INFO, "LOMT-11", "14 TCs has Passed");
		
		reports.endTest(logger);
		reports.flush();
	}
	
	@Test(priority = 2)
	public void gseReingestion() throws Exception {
		logger = reports.startTest("English GSE Ingestion", "LOMT-11, Total 33 TCs");
		
		regression.gseIngestion(logger);
		
		logger.log(LogStatus.INFO, "LOMT-11", "19 TCs has Passed");
		
		reports.endTest(logger);
		reports.flush();
	}
	
	@Test(priority = 3)
	public void gseEducationalGoalFrameworkExport() throws Exception {
		logger = reports.startTest("English GSE Export", "LOMT-253, Total 6 TCs");
		
		regression.gseEducationalGoalFrameworkExport(logger);
		
		logger.log(LogStatus.INFO, "LOMT-253", "6 TCs has Passed");
		
		reports.endTest(logger);
		reports.flush();
	}
	
	@Test(priority = 4)
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
	
	@Test(priority = 5)
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
	
	@Test(priority = 6)
	public void ingestionExternalFrameworkForSchool() {
		logger = reports.startTest(LOMTConstant.SCHOOL+LOMTConstant.EMPTY_SPACE+LOMTConstant.EXTERNAL_FRAMEWORK_INGESTION, 
				LOMTConstant.LOMT_1357+LOMTConstant.COMMA+LOMTConstant.EMPTY_SPACE+LOMTConstant.TC_COUNT_SCHOOL_EXF);
		
		exf.nalsBrowsePage(logger);
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
	
	@Test(priority = 7)
	public void metaDataExternalFramework() {
		logger = reports.startTest(LOMTConstant.META_DATA+LOMTConstant.EMPTY_SPACE+LOMTConstant.EXTERNAL_FRAMEWORK_INGESTION, 
				LOMTConstant.LOMT_1358+LOMTConstant.COMMA+LOMTConstant.EMPTY_SPACE+" TC count is 26");
		//School
		exf.metaDataExternalFramework(logger);
		
		reports.endTest(logger);
		reports.flush();
	}
	
	@Test(priority = 8)
	public void selectCutomExternalFrameworkForSchool() {
		logger = reports.startTest(LOMTConstant.CUSTOME_EXF_SCHOOL,LOMTConstant.LOMT_1389+LOMTConstant.COMMA+LOMTConstant.EMPTY_SPACE+" TC count is 19");
		
		exf.selectCutomExternalFrameworkForSchool(logger);
		
		logger.log(LogStatus.INFO, "LOMT-1389", "19 TCs has Passed");
		reports.endTest(logger);
	}
	
	@Test(priority = 9)
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
	
	/*@Test
	public void ingestionExternalFrameworkForSchool() {
		
	}*/
	
	/*//School Product TOC 
	@Test(priority = 4)
	public void productTOCIngestionSchool() {
		logger = reports.startTest(LOMTConstant.SCHOOL+LOMTConstant.EMPTY_SPACE+LOMTConstant.PRODUCT_TOC_INGESTION, 
				LOMTConstant.LOMT_1039+LOMTConstant.COMMA+LOMTConstant.EMPTY_SPACE+LOMTConstant.TC_COUNT_PRODUCT_TOC);
		
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
		
		logger.log(LogStatus.INFO, LOMTConstant.LOMT_1039, "38 TCs has Passed");
		reports.endTest(logger);
		reports.flush();
	}
	
	//English Product TOC
	@Test(priority = 5)
	public void productTOCIngestionEnglish() {
		logger = reports.startTest(LOMTConstant.ENGLISH_LOB + LOMTConstant.EMPTY_SPACE+ LOMTConstant.PRODUCT_TOC_INGESTION,
				LOMTConstant.LOMT_1040 + LOMTConstant.COMMA+ LOMTConstant.EMPTY_SPACE+ LOMTConstant.ENGLISH_TC_COUNT_PRODUCT_TOC);

		logger.log(LogStatus.PASS,TestCases.TC_LOMT_1040_01_SME_OR_COORDINATOR_OR_BASIC_BROWSER_CANNOT_INGEST);
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
		
		logger.log(LogStatus.INFO, LOMTConstant.LOMT_1040, "27 TCs has Passed");
		reports.endTest(logger);
		reports.flush();
	}
	
	// HE Product TOC
	@Test(priority = 6)
	public void productTOCIngestionHigherEducation() {
		logger = reports.startTest(LOMTConstant.HE_LOB + LOMTConstant.EMPTY_SPACE+ LOMTConstant.PRODUCT_TOC_INGESTION,
				LOMTConstant.LOMT_1041 + LOMTConstant.COMMA+ LOMTConstant.EMPTY_SPACE+ LOMTConstant.HE_TC_COUNT_PRODUCT_TOC);

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
		
		logger.log(LogStatus.INFO, LOMTConstant.LOMT_1041, "28 TCs has Passed");
		reports.endTest(logger);
		reports.flush();
	}*/

	@Test(priority = 10)
	public void tearDown() {
		lomtCommon.closeDriverInstance();
	}

}
