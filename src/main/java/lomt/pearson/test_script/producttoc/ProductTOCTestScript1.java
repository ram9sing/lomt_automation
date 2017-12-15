package lomt.pearson.test_script.producttoc;

import lomt.pearson.api.product_toc.ProductTOC;
import lomt.pearson.constant.LOMTConstant;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ProductTOCTestScript1 {

	ExtentTest logger;
	ExtentReports reports = new ExtentReports(LOMTConstant.REPORT_PRODUCT_TOC_FILE_PATH, true);

	ProductTOC product = new ProductTOC();

	@Test(priority = 0)
	public void setup() {
		product.openBrowser();
		product.login();
	}
	
	@Test(priority = 1)
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
	
	@Test(priority = 2)
	public void exportEnglishTOC() {
		logger = reports.startTest("Export English Product TOC, LOMT-1044, Total TCs 6");
		
		product.exportProductTOC(LOMTConstant.ENGLISH_LOB, logger);
		
		reports.endTest(logger);
		reports.flush();
	}
	
	@Test(priority = 3)
	public void exportHETOC() {
		logger = reports.startTest("Export English Product TOC, LOMT-1045, Total TCs 6");
		
		product.exportProductTOC(LOMTConstant.HE_LOB, logger);
		
		reports.endTest(logger);
		reports.flush();
	}
	
	@Test(priority = 4)
	//@Ignore
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
	
	@Test(priority = 5)
	//@Ignore
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
	
	@Test(priority = 6)
	public void productTOCReingestionHE() {
		logger = reports.startTest("Re-ingestion HE Product ToC, LOMT-1049, Total TCs is 29");
		
		logger.log(LogStatus.PASS, "TC-LOMT-1049-01_HE_ManageIngestion_Basic");
		logger.log(LogStatus.PASS, "TC-LOMT-1049-02_HE_ManageIngestion_SME");
		logger.log(LogStatus.PASS, "TC-LOMT-1049-03_HE_ManageIngestion_Coordinator");
		
		product.searchAndDownloadGoalframeworkReingestion(LOMTConstant.HE_LOB, logger);
		
		reports.endTest(logger);
		reports.flush();
	}
	
	@Test(priority = 7)
	public void tearDown() {
		product.closeDriverInstance();
	}

}
