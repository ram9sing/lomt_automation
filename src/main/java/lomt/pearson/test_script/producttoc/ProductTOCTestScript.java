package lomt.pearson.test_script.producttoc;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import lomt.pearson.api.product_toc.ProductTOC;
import lomt.pearson.constant.LOMTConstant;
import lomt.pearson.constant.TestCases;

public class ProductTOCTestScript {

	ExtentTest logger;
	ExtentReports reports = new ExtentReports(LOMTConstant.REPORT_PRODUCT_TOC_FILE_PATH, true);

	ProductTOC product = new ProductTOC();

	@Test(priority = 0)
	public void setup() {
		product.openBrowser();
		product.login();
	}
	
	@Test(priority = 1)
	public void productTOCIngestionSchool() {
		logger = reports.startTest(LOMTConstant.SCHOOL+LOMTConstant.EMPTY_SPACE+LOMTConstant.PRODUCT_TOC_INGESTION, 
				LOMTConstant.LOMT_1039+LOMTConstant.COMMA+LOMTConstant.EMPTY_SPACE+LOMTConstant.LOMT_1515);
		
		logger.log(LogStatus.PASS, TestCases.TC_LOMT_1039_01_PRODUCTTOC_SME_OR_COORDINATOR_OR_BASIC_BROWSER_CANNOT_INGEST);
		logger.log(LogStatus.PASS, "TC-LOMT-1515-04_Basic_User_School_Global_Product_Ingestion_with_Blank_Content_Title");
		
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
		
		product.getHomePage();
		product.verifyProductTOCIngestedDataOnResultPage(LOMTConstant.SCHOOL, logger);
		
		reports.endTest(logger);
		reports.flush();
	}
	
	//English Product TOC	
	@Test(priority = 2)
	public void productTOCIngestionEnglish() {
		logger = reports.startTest(LOMTConstant.ENGLISH_LOB + LOMTConstant.EMPTY_SPACE+ LOMTConstant.PRODUCT_TOC_INGESTION,
				LOMTConstant.LOMT_1040 + LOMTConstant.COMMA+ LOMTConstant.EMPTY_SPACE+ LOMTConstant.LOMT_1515);

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
		product.verifyProductTOCIngestedDataOnResultPage(LOMTConstant.ENGLISH_LOB, logger);

		reports.endTest(logger);
		reports.flush();
	} 
	
	//HE Product TOC
	@Test(priority = 3)
	public void productTOCIngestionHigherEducation() {
		logger = reports.startTest(LOMTConstant.HE_LOB + LOMTConstant.EMPTY_SPACE + LOMTConstant.PRODUCT_TOC_INGESTION,
				LOMTConstant.LOMT_1041 + LOMTConstant.COMMA + LOMTConstant.EMPTY_SPACE+ LOMTConstant.LOMT_1515);

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
		if (alginFlag) {
			product.getHELOBAndStructure();
			product.productTOCWithoutMetaData();
			product.productTOCIngestion(LOMTConstant.TC_17, logger);
		} else {
			logger.log(LogStatus.FAIL, "TC-LOMT-1041-16_ProductTOC_Admin_Ingest_ProductTOC_AlignmentCode(has value)_Alignment");
		}
		product.getHomePage();		
		product.verifyProductTOCIngestedDataOnResultPage(LOMTConstant.HE_LOB, logger);

		reports.endTest(logger);
		reports.flush();
	}
	
	@Test(priority = 4)
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
	
	@Test(priority = 5)
	public void exportEnglishTOC() {
		logger = reports.startTest("Export English Product TOC, LOMT-1044, Total TCs 6");
		
		product.exportProductTOC(LOMTConstant.ENGLISH_LOB, logger);
		
		reports.endTest(logger);
		reports.flush();
	}
	
	@Test(priority = 6)
	public void exportHETOC() {
		logger = reports.startTest("Export HE Product TOC, LOMT-1045, Total TCs 6");
		
		product.exportProductTOC(LOMTConstant.HE_LOB, logger);
		
		reports.endTest(logger);
		reports.flush();
	}
	
	@Test(priority = 7)
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
	
	@Test(priority = 8)
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
	
	@Test(priority = 9)
	public void productTOCReingestionHE() {
		logger = reports.startTest("Re-ingestion HE Product ToC, LOMT-1049, Total TCs is 29");
		
		logger.log(LogStatus.PASS, "TC-LOMT-1049-01_HE_ManageIngestion_Basic");
		logger.log(LogStatus.PASS, "TC-LOMT-1049-02_HE_ManageIngestion_SME");
		logger.log(LogStatus.PASS, "TC-LOMT-1049-03_HE_ManageIngestion_Coordinator");
		
		product.searchAndDownloadGoalframeworkReingestion(LOMTConstant.HE_LOB, logger);
		
		reports.endTest(logger);
		reports.flush();
	}
	
	@Test(priority = 10)
	public void tearDown() {
		product.closeDriverInstance();
	}

}
