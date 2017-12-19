package lomt.pearson.test_script.common;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import lomt.pearson.common.Common;
import lomt.pearson.constant.LOMTConstant;

public class CommonTestScript {

	Common common = new Common();
	
	ExtentTest logger;
	ExtentReports reports = new ExtentReports(LOMTConstant.REPORT_FILE_PATH_COMMON, true); 
	String disciplineName = null;

	@Test(priority = 0)
	public void setup() {
		common.openBrowser();		
		common.login();
	}

	@Test(priority = 1)
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
		//reports.flush();
	}
	
	@Test(priority = 2)
	public void tearDown() {
		common.closeDriverInstance();
	}

}
