package lomt.pearson.test_script.reports;

import java.util.List;
import java.util.Map;
import org.junit.Ignore;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import lomt.pearson.api.reports.Reports;
import lomt.pearson.constant.LOMTConstant;
import lomt.pearson.constant.ReportsConstant;
import lomt.pearson.constant.TestCases;

public class ReportsTestScript {

	ExtentTest logger;
	ExtentReports reports = new ExtentReports(LOMTConstant.REPORT_FILE_PATH, true); 
	
	Reports report = new Reports();

	@Test(priority = 0)
	public void setup() {
		report.openBrowser();		
		report.login();
	}
	
	@Test(priority = 1)
	public void forwardIndirectIntermediaryReport() throws Exception {
		logger = reports.startTest(ReportsConstant.FORWARD_INDIRECT_INTERMEDIARY_REPORT+LOMTConstant.COMMA+LOMTConstant.EMPTY_SPACE+ReportsConstant.LOMT_1758);
		
		//Admin user		
		String reportName = report.createAndDownloadReport(ReportsConstant.FORWARD_INDIRECT_INTERMEDIARY_REPORT, ReportsConstant.CS_GOALFRAMEWORK_NAME_PPE,
				 ReportsConstant.INGESTED_INTERMEDIARY_PPE, null, logger);
		if(reportName != null) {
			Map<String, List<String>> forwardIIRepMap = report.verifiedForwardIndirectIntermediaryReportsExportedFile(reportName, logger);
			if (!forwardIIRepMap.isEmpty()) {
				report.verifyCurriculumStandardDataUI(forwardIIRepMap, logger);
				report.verifyIntermediaryDataUI(forwardIIRepMap, logger);
				logger.log(LogStatus.PASS, "TC-LOMT-1758-01_Admin_User_SchoolGlobal_Reports_&exports_Forward-Indirect_Intermediary_Report"); 
			}
		} else {
			logger.log(LogStatus.FAIL, "TC-LOMT-1758-01_Admin_User_SchoolGlobal_Reports_&exports_Forward-Indirect_Intermediary_Report"); 
		}
		
		//Coordinator User
		report.logout();
		report.loginLearningEditor();
		boolean coordinatorReportFlag = report.searchAndExportReport(reportName);
		if (coordinatorReportFlag) {
			logger.log(LogStatus.PASS, "TC-LOMT-1758-02_Coordinator_User_SchoolGlobal_Reports_&exports_Forward-Indirect_Intermediary_Report"); 
		} else {
			logger.log(LogStatus.FAIL, "TC-LOMT-1758-02_Coordinator_User_SchoolGlobal_Reports_&exports_Forward-Indirect_Intermediary_Report"); 
		}
		
		//SME User
		report.logout();
		report.loginLearingSME();
		boolean smeReportFlag = report.searchAndExportReport(reportName);
		if (smeReportFlag) {
			logger.log(LogStatus.PASS, "TC-LOMT-1758-03_SME_User_SchoolGlobal_Reports_&exports_Forward-Indirect_Intermediary_Report"); 
		} else {
			logger.log(LogStatus.FAIL, "TC-LOMT-1758-03_SME_User_SchoolGlobal_Reports_&exports_Forward-Indirect_Intermediary_Report"); 
		}
		
		//BasicBrowser User
		report.logout();
		report.loginLearningUser();
		boolean basicReportFlag = report.searchAndExportReport(reportName);
		if (basicReportFlag) {
			logger.log(LogStatus.PASS, "TC-LOMT-1758-04_BasicBrowser_User_SchoolGlobal_Reports_&exports_Forward-Indirect_Intermediary_Report"); 
		} else {
			logger.log(LogStatus.FAIL, "TC-LOMT-1758-04_BasicBrowser_User_SchoolGlobal_Reports_&exports_Forward-Indirect_Intermediary_Report"); 
		}
		
		reports.endTest(logger);
		reports.flush();
	}
	
	//@Test(priority = 2)
	@Ignore
	public void productToCIntermediaryReport() throws Exception {
		logger = reports.startTest("Product (ToC) Intermediary Report, LOMT-1762");
		
		//Admin user
		String reportName = report.createAndDownloadReport1(ReportsConstant.PRODUCT_INT_TEXT);
		if(!reportName.isEmpty()) {
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1762_01_ADMIN_USER_DOWNLOAD_REPORT);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1762_02_BASIC_USER_DOWNLOAD_REPORT);
			Map<String, List<String>> productTIRepMap = report.verifyProductToCIntermediaryReport();
			if (!productTIRepMap.isEmpty()) {
				boolean verifyFlag1 = report.verifyProductDataUI(productTIRepMap,logger);
				boolean verifyFlag2 = report.verifyIntermediaryDataUI(productTIRepMap,logger);
				if (verifyFlag1 && verifyFlag2){
				logger.log(LogStatus.PASS, TestCases.TC_LOMT_1762_03_ADMIN_USER_DOWNLOAD_VERIFY_REPORT);
				}
				else{
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1762_03_ADMIN_USER_DOWNLOAD_VERIFY_REPORT);
				}
			}
		} 
		
		else {
			logger.log(LogStatus.FAIL, "TC_LOMT-1762-01_Admin_User_School_Global_Report_Export_Download"); 
			return;
		}
				
		reports.endTest(logger);
		reports.flush();
	}
	
	@Test(priority = 1)
	public void reverseSharedIntermediaryReport() throws Exception {
		logger = reports.startTest("Reverse Shared Intermediary Report, LOMT-1839");
		
		//Admin user
		String reportName = report.createAndDownloadReport1(ReportsConstant.REVERSE_SHARED_INT_TEXT);
		if(!reportName.isEmpty()) {
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1839_01_DOWNLOAD_REPORT);
			Map<String, List<String>> productCSRepMap = report.verifyReverseSharedIntermediaryReport(logger);
			if (!productCSRepMap.isEmpty()) {
				logger.log(LogStatus.PASS, TestCases.TC_LOMT_1839_03_CORRELATION_VERIFY);
				logger.log(LogStatus.PASS, TestCases.TC_LOMT_1839_04_STRENGTH_WEAK_VERIFY);
				logger.log(LogStatus.PASS, TestCases.TC_LOMT_1839_05_STRENGTH_AVERAGE_VERIFY);
				logger.log(LogStatus.PASS, TestCases.TC_LOMT_1839_06_STRENGTH_COMPLETE_VERIFY);
				logger.log(LogStatus.PASS, TestCases.TC_LOMT_1839_07_MET_VERIFY);
				logger.log(LogStatus.PASS, TestCases.TC_LOMT_1839_08_UNMET_VERIFY);
				boolean verifyFlag1 = report.verifyProductDataUI(productCSRepMap,logger);
				boolean verifyFlag2 = report.verifyCurriculumStandardDataUI(productCSRepMap,logger);;
				if (verifyFlag1 && verifyFlag2){
				logger.log(LogStatus.PASS, TestCases.TC_LOMT_1839_02_REPORT_VERIFY);
				}
				else{
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1839_02_REPORT_VERIFY);
				}
			}
		}
		else{
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1839_01_DOWNLOAD_REPORT);
		}
		
		reports.endTest(logger);
		reports.flush();
	}
	
	@Test(priority = 3)
	public void tearDown() {
		report.closeDriverInstance();
	}

}
