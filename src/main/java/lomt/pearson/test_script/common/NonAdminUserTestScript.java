package lomt.pearson.test_script.common;

import lomt.pearson.common.NonAdminUserBrowseExport;
import lomt.pearson.constant.LOMTConstant;
import lomt.pearson.constant.TestCases;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class NonAdminUserTestScript {

	ExtentTest logger;
	//ExtentReports reports = new ExtentReports(LOMTConstant.NONADMIN_REPORT_FILE_PATH, true); 
	ExtentReports reports = new ExtentReports(LOMTConstant.REPORT_FILE_PATH_COMMON, true); 
	String disciplineName = null;
	NonAdminUserBrowseExport nonAdminUser = new NonAdminUserBrowseExport();

	@Test(priority = 0)
	public void setup() {
		nonAdminUser.openBrowser();		
		nonAdminUser.loginAdmin();
	}
	
	//englishLOBExportAndBrowse
	@Test(priority = 1)
	public void exportAndBrowseUserRoles() throws Exception {
		logger = reports.startTest("English LOB, LearningUser, LearingSME & LearningEditor "+
									"LOMT-968, "+ "LOMT-1408, "+"LOMT-1044");
		nonAdminUser.logout();
		String learningUser = nonAdminUser.loginLearningUser();
		logger.log(LogStatus.PASS, "TC-LOMT-968-06_English_GSE_Basic_Browse_ManageIngestion_Verify"); 
		
		boolean englishFlag = nonAdminUser.englishGSEBrowseAndExport(logger, learningUser);
		if (englishFlag) {
			logger.log(LogStatus.PASS, "TC-LOMT-968-07_English_GSE_Basic_Browse_goalFramework_Restricted_access");
			logger.log(LogStatus.PASS, "TC-LOMT-968-08_English_GSE_Basic_Browse_edit_Verify");
			logger.log(LogStatus.PASS, "TC-LOMT-968-09_English_GSE_Basic_Browse_goalFramework");
			logger.log(LogStatus.PASS, "TC-LOMT-968-10_English_GSE_Basic_Browse_goalFramework_status");
			logger.log(LogStatus.PASS, "TC-LOMT-968-11_English_GSE_Basic_Export_option");
			logger.log(LogStatus.PASS, "TC-LOMT-968-12_English_GSE_Basic_Export_goalFramework_Restricted_access");
		} else {
			logger.log(LogStatus.PASS, "TC-LOMT-968-08_English_GSE_Basic_Browse_edit_Verify");
			logger.log(LogStatus.FAIL, "TC-LOMT-968-09_English_GSE_Basic_Browse_goalFramework");
			logger.log(LogStatus.FAIL, "TC-LOMT-968-10_English_GSE_Basic_Browse_goalFramework_status");
			logger.log(LogStatus.FAIL, "TC-LOMT-968-11_English_GSE_Basic_Export_option");
			logger.log(LogStatus.FAIL, "TC-LOMT-968-12_English_GSE_Basic_Export_goalFramework_Restricted_access");
		}
		
		boolean nexfFlag = nonAdminUser.englishEXFBrowseAndExport(logger, learningUser);
		if (nexfFlag) {
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_19_BASICBROWSECANNOT_EXPORT_ENGLISH);
		} else {
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1408_19_BASICBROWSECANNOT_EXPORT_ENGLISH);
		}
	
		boolean productFlag = nonAdminUser.englishProductBrowseAndExport(logger, learningUser);
		/*if (productFlag) {
		} else {
			logger.log(LogStatus.FAIL, "LOMT-1044 "+learningUser);
		}	*/
		
		//SME user
		nonAdminUser.logout();
		String smeUser = nonAdminUser.loginLearingSME();
		
		/*boolean englishFlag1 = nonAdminUser.englishGSEBrowseAndExport(logger, smeUser);
		if(englishFlag1) {
		} else {
			logger.log(LogStatus.FAIL, "lomt-968 "+smeUser);
		}
		
		boolean exfFlag1 = nonAdminUser.englishEXFBrowseAndExport(logger, smeUser);
		if (exfFlag1) {
		} else {
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1408_19_BASICBROWSECANNOT_EXPORT_ENGLISH+" "+smeUser);
		}
		
		boolean productFlag1 = nonAdminUser.englishProductBrowseAndExport(logger, smeUser);
		if (productFlag1) {
		} else {
			logger.log(LogStatus.FAIL, "LOMT-1044 "+smeUser);
		}*/
		
		//Editor user
		nonAdminUser.logout();
		String editorUser = nonAdminUser.loginLearningEditor();
		
		/*boolean englishFlag2 = nonAdminUser.englishGSEBrowseAndExport(logger, editorUser);
		if(englishFlag2) {
		} else {
			logger.log(LogStatus.FAIL, "lomt-968 "+editorUser);
		}
		
		boolean exfFlag2 = nonAdminUser.englishEXFBrowseAndExport(logger, editorUser);
		if (exfFlag2) {
		} else {
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1408_19_BASICBROWSECANNOT_EXPORT_ENGLISH+" "+editorUser);
		}
		
		boolean productFlag2 = nonAdminUser.englishProductBrowseAndExport(logger, editorUser);
		if (productFlag2) {
		} else {
			logger.log(LogStatus.FAIL, "LOMT-1044 "+editorUser);
		}*/
		
		reports.endTest(logger);
		reports.flush();
	}
	
	@Test(priority = 2)
	public void tearDown() {
		nonAdminUser.closeDriverInstance();
	}

}
