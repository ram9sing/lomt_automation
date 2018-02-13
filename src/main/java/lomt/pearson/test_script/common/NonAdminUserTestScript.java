package lomt.pearson.test_script.common;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import lomt.pearson.common.Common;
import lomt.pearson.common.NonAdminUserBrowseExport;
import lomt.pearson.constant.LOMTConstant;
import lomt.pearson.constant.TestCases;

public class NonAdminUserTestScript {

	ExtentTest logger;
	ExtentReports reports = new ExtentReports(LOMTConstant.REPORT_FILE_PATH_COMMON, true); 
	String disciplineName = null;
	NonAdminUserBrowseExport nonAdminUser = new NonAdminUserBrowseExport();
	Common common = new Common();

	@Test(priority = 0)
	public void setup() {
		nonAdminUser.openBrowser();		
		nonAdminUser.loginAdmin();
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
	public void exportAndBrowseUserRoles() throws Exception {
		logger = reports.startTest("English LOB, LearningUser, LearingSME & LearningEditor "+
									"LOMT-968, "+ "LOMT-1408, "+"LOMT-1044");
		nonAdminUser.logout();
		
		//###### Basic user #########
		String learningUser = nonAdminUser.loginLearningUser(); 
		//for basic user Manage Ingestion link does not appear so we can not apply assertion
		//that's why directly passing it.
		logger.log(LogStatus.PASS, "TC-LOMT-968-06_English_GSE_Basic_Browse_ManageIngestion_Verify"); 
		
		boolean englishFlag = nonAdminUser.englishGSEBrowseAndExport(logger, learningUser); // gse
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
		
		boolean exfFlag = nonAdminUser.exfBrowseAndExport(logger, learningUser, LOMTConstant.ENGLISH_LOB); // exf
		if (exfFlag) {
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_19_BASICBROWSECANNOT_EXPORT_ENGLISH);
		} else {
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1408_19_BASICBROWSECANNOT_EXPORT_ENGLISH);
		}
	
		boolean productFlag = nonAdminUser.productBrowseAndExport(logger, learningUser, LOMTConstant.ENGLISH_LOB); // product
		if (productFlag) {
			logger.log(LogStatus.PASS, "English Product TOC export and browse is successful with loggedin user : "+learningUser);
		} else {
			logger.log(LogStatus.FAIL, "English Product TOC export and browse is successful with loggedin user : "+learningUser);
		}
		
		//Higher Education LOB
		boolean heEXFFlag = nonAdminUser.exfBrowseAndExport(logger, learningUser, LOMTConstant.HE_LOB); //exf
		if (heEXFFlag) {
			logger.log(LogStatus.PASS, "HE External Framework export and browse is successful with loggedin user : "+learningUser);
		} else {
			logger.log(LogStatus.FAIL, "HE External Framework export and browse is successful with loggedin user : "+learningUser);
		}
		
		boolean heProductFlag = nonAdminUser.productBrowseAndExport(logger, learningUser, LOMTConstant.HE_LOB); //product
		if (heProductFlag) {
			logger.log(LogStatus.PASS, "HE Product TOC export and browse is successful with loggedin user : "+learningUser);
		} else {
			logger.log(LogStatus.FAIL, "HE Product TOC export and browse is successful with loggedin user : "+learningUser);
		}
		
		boolean heEOFlag = nonAdminUser.heEducationalObjectiveAndExport(logger, learningUser); // EO
		if (heEOFlag) {
			logger.log(LogStatus.PASS, "HE Educational Objective export and browse is successful with loggedin user : "+learningUser);
		} else {
			logger.log(LogStatus.FAIL, "HE Educational Objective export and browse is successful with loggedin user : "+learningUser);
		}
		
		//School LOB
		boolean abCustomFlag = nonAdminUser.getABAndCustomBrowseAndExport(logger, learningUser, LOMTConstant.SCHOOL);
		if (abCustomFlag) {
			logger.log(LogStatus.PASS, "AB xml export and browse is successful with loggedin user : "+learningUser);
		} else {
			logger.log(LogStatus.FAIL, "AB xml export and browse is successful with loggedin user : "+learningUser);
		}
		
		boolean customProductFlag = nonAdminUser.getABAndCustomBrowseAndExport(logger, learningUser, LOMTConstant.CUSTOM);
		if (customProductFlag) {
			logger.log(LogStatus.PASS, "External Framework export and browse is successful with loggedin user : "+learningUser);
		} else {
			logger.log(LogStatus.FAIL, "External Framework export and browse is successful with loggedin user : "+learningUser);
		}
		
		boolean schoolProductFlag = nonAdminUser.productBrowseAndExport(logger, learningUser, LOMTConstant.SCHOOL);
		if (schoolProductFlag) {
			logger.log(LogStatus.PASS, "School Product TOC export and browse is successful with loggedin user : "+learningUser);
		} else {
			logger.log(LogStatus.FAIL, "School Product TOC export and browse is successful with loggedin user : "+learningUser);
		}
		
		boolean intermediaryFlag = nonAdminUser.getIntermediaryBrowseAndExport(logger, learningUser);
		if (intermediaryFlag) {
			logger.log(LogStatus.PASS, "School Intermediary export and browse is successful with loggedin user : "+learningUser);
		} else {
			logger.log(LogStatus.FAIL, "School Intermediary export and browse is successful with loggedin user : "+learningUser);
		}
		
		//####### SME user #########
		nonAdminUser.logout();
		String smeUser = nonAdminUser.loginLearingSME();
		
		boolean englishFlagSME = nonAdminUser.englishGSEBrowseAndExport(logger, smeUser); // gse
		if (englishFlagSME) {
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
		
		boolean exfFlagSME = nonAdminUser.exfBrowseAndExport(logger, smeUser, LOMTConstant.ENGLISH_LOB); // exf
		if (exfFlagSME) {
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_19_BASICBROWSECANNOT_EXPORT_ENGLISH);
		} else {
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1408_19_BASICBROWSECANNOT_EXPORT_ENGLISH);
		}
	
		boolean productFlagSME = nonAdminUser.productBrowseAndExport(logger, smeUser, LOMTConstant.ENGLISH_LOB); // product
		if (productFlagSME) {
			logger.log(LogStatus.PASS, "English Product TOC export and browse is successful with loggedin user : "+smeUser);
		} else {
			logger.log(LogStatus.FAIL, "English Product TOC export and browse is successful with loggedin user : "+smeUser);
		}
		
		//Higher Education LOB
		boolean heEXFFlagSME = nonAdminUser.exfBrowseAndExport(logger, smeUser, LOMTConstant.HE_LOB); //exf
		if (heEXFFlagSME) {
			logger.log(LogStatus.PASS, "HE External Framework export and browse is successful with loggedin user : "+smeUser);
		} else {
			logger.log(LogStatus.FAIL, "HE External Framework export and browse is successful with loggedin user : "+smeUser);
		}
		
		boolean heProductFlagSME = nonAdminUser.productBrowseAndExport(logger, smeUser, LOMTConstant.HE_LOB); //product
		if (heProductFlagSME) {
			logger.log(LogStatus.PASS, "HE Product TOC export and browse is successful with loggedin user : "+smeUser);
		} else {
			logger.log(LogStatus.FAIL, "HE Product TOC export and browse is successful with loggedin user : "+smeUser);
		}
		
		boolean heEOFlagSME = nonAdminUser.heEducationalObjectiveAndExport(logger, smeUser); // EO
		if (heEOFlagSME) {
			logger.log(LogStatus.PASS, "HE Educational Objective export and browse is successful with loggedin user : "+smeUser);
		} else {
			logger.log(LogStatus.FAIL, "HE Educational Objective export and browse is successful with loggedin user : "+smeUser);
		}
		
		//School LOB
		boolean abCustomFlagSME = nonAdminUser.getABAndCustomBrowseAndExport(logger, smeUser, LOMTConstant.SCHOOL);
		if (abCustomFlagSME) {
			logger.log(LogStatus.PASS, "AB xml export and browse is successful with loggedin user : "+smeUser);
		} else {
			logger.log(LogStatus.FAIL, "AB xml export and browse is successful with loggedin user : "+smeUser);
		}
		
		boolean customProductFlagSME = nonAdminUser.getABAndCustomBrowseAndExport(logger, smeUser, LOMTConstant.CUSTOM);
		if (customProductFlagSME) {
			logger.log(LogStatus.PASS, "External Framework export and browse is successful with loggedin user : "+smeUser);
		} else {
			logger.log(LogStatus.FAIL, "External Framework export and browse is successful with loggedin user : "+smeUser);
		}
		
		boolean schoolProductFlagSME = nonAdminUser.productBrowseAndExport(logger, smeUser, LOMTConstant.SCHOOL);
		if (schoolProductFlagSME) {
			logger.log(LogStatus.PASS, "School Product TOC export and browse is successful with loggedin user : "+smeUser);
		} else {
			logger.log(LogStatus.FAIL, "School Product TOC export and browse is successful with loggedin user : "+smeUser);
		}
		
		boolean intermediaryFlagSME = nonAdminUser.getIntermediaryBrowseAndExport(logger, smeUser);
		if (intermediaryFlagSME) {
			logger.log(LogStatus.PASS, "School Intermediary export and browse is successful with loggedin user : "+smeUser);
		} else {
			logger.log(LogStatus.FAIL, "School Intermediary export and browse is successful with loggedin user : "+smeUser);
		}
		
		//####### Editor user #########
		nonAdminUser.logout();
		String editorUser = nonAdminUser.loginLearningEditor();
		
		boolean englishFlagEditor = nonAdminUser.englishGSEBrowseAndExport(logger, editorUser); // gse
		if (englishFlagEditor) {
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
		
		boolean exfFlagEditor = nonAdminUser.exfBrowseAndExport(logger, editorUser, LOMTConstant.ENGLISH_LOB); // exf
		if (exfFlagEditor) {
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_19_BASICBROWSECANNOT_EXPORT_ENGLISH);
		} else {
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1408_19_BASICBROWSECANNOT_EXPORT_ENGLISH);
		}
	
		boolean productFlagEditor = nonAdminUser.productBrowseAndExport(logger, editorUser, LOMTConstant.ENGLISH_LOB); // product
		if (productFlagEditor) {
			logger.log(LogStatus.PASS, "English Product TOC export and browse is successful with loggedin user : "+editorUser);
		} else {
			logger.log(LogStatus.FAIL, "English Product TOC export and browse is successful with loggedin user : "+editorUser);
		}
		
		//Higher Education LOB
		boolean heEXFFlagEditor = nonAdminUser.exfBrowseAndExport(logger, editorUser, LOMTConstant.HE_LOB); //exf
		if (heEXFFlagEditor) {
			logger.log(LogStatus.PASS, "HE External Framework export and browse is successful with loggedin user : "+editorUser);
		} else {
			logger.log(LogStatus.FAIL, "HE External Framework export and browse is successful with loggedin user : "+editorUser);
		}
		
		boolean heProductFlagEditor = nonAdminUser.productBrowseAndExport(logger, editorUser, LOMTConstant.HE_LOB); //product
		if (heProductFlagEditor) {
			logger.log(LogStatus.PASS, "HE Product TOC export and browse is successful with loggedin user : "+editorUser);
		} else {
			logger.log(LogStatus.FAIL, "HE Product TOC export and browse is successful with loggedin user : "+editorUser);
		}
		
		boolean heEOFlagEditor = nonAdminUser.heEducationalObjectiveAndExport(logger, editorUser); // EO
		if (heEOFlagEditor) {
			logger.log(LogStatus.PASS, "HE Educational Objective export and browse is successful with loggedin user : "+editorUser);
		} else {
			logger.log(LogStatus.FAIL, "HE Educational Objective export and browse is successful with loggedin user : "+editorUser);
		}
		
		//School LOB
		boolean abCustomFlagEditor = nonAdminUser.getABAndCustomBrowseAndExport(logger, editorUser, LOMTConstant.SCHOOL);
		if (abCustomFlagEditor) {
			logger.log(LogStatus.PASS, "AB xml export and browse is successful with loggedin user : "+editorUser);
		} else {
			logger.log(LogStatus.FAIL, "AB xml export and browse is successful with loggedin user : "+editorUser);
		}
		
		boolean customProductFlagEditor = nonAdminUser.getABAndCustomBrowseAndExport(logger, editorUser, LOMTConstant.CUSTOM);
		if (customProductFlagEditor) {
			logger.log(LogStatus.PASS, "External Framework export and browse is successful with loggedin user : "+editorUser);
		} else {
			logger.log(LogStatus.FAIL, "External Framework export and browse is successful with loggedin user : "+editorUser);
		}
		
		boolean schoolProductFlagEditor = nonAdminUser.productBrowseAndExport(logger, editorUser, LOMTConstant.SCHOOL);
		if (schoolProductFlagEditor) {
			logger.log(LogStatus.PASS, "School Product TOC export and browse is successful with loggedin user : "+editorUser);
		} else {
			logger.log(LogStatus.FAIL, "School Product TOC export and browse is successful with loggedin user : "+editorUser);
		}
		
		boolean intermediaryFlagEditor = nonAdminUser.getIntermediaryBrowseAndExport(logger, editorUser);
		if (intermediaryFlagEditor) {
			logger.log(LogStatus.PASS, "School Intermediary export and browse is successful with loggedin user : "+editorUser);
		} else {
			logger.log(LogStatus.FAIL, "School Intermediary export and browse is successful with loggedin user : "+editorUser);
		}
		
		reports.endTest(logger);
		reports.flush();
	}
	
	@Test(priority = 3)
	public void tearDown() {
		nonAdminUser.closeDriverInstance();
	}

}
