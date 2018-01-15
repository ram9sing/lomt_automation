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

	//@Test(priority = 1)
	@Ignore
	public void forwardIndirectIntermediaryReport() throws Exception {
		logger = reports.startTest("Forward Indirect Intermediary Report, LOMT-1758");
		
		//Admin user
		boolean reportFlag = report.forwardIndirectIntermediaryReports();
		if(reportFlag) {
			Map<String, List<String>> forwardIIRepMap = report.verifiedForwardIndirectIntermediaryReportsExportedFile();
			if (!forwardIIRepMap.isEmpty()) {
				report.verifyCurriculumStandardDataUI(forwardIIRepMap);
				report.verifyIntermediaryDataUI(forwardIIRepMap,logger);
			}
		} else {
			logger.log(LogStatus.FAIL, "TC-LOMT-1758-01_Admin_User_SchoolGlobal_Reports_&exports_Forward-Indirect_Intermediary_Report"); 
			return;
		}
		
		
		logger.log(LogStatus.INFO, ""); 
		
		reports.endTest(logger);
		reports.flush();
	}
	
	@Test(priority = 2)
	public void productToCIntermediaryReport() throws Exception {
		logger = reports.startTest("Product (ToC) Intermediary Report, LOMT-1762");
		
		//Admin user
		String reportName = report.createAndDownloadReportProductToCInt();
		if(!reportName.isEmpty()) {
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1762_01_ADMIN_USER_DOWNLOAD_REPORT);
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1762_02_BASIC_USER_DOWNLOAD_REPORT);
			Map<String, List<String>> productTIRepMap = report.verifyProductToCIntermediaryReport();
			if (!productTIRepMap.isEmpty()) {
				boolean verifyFlag =	report.verifyProductDataUI(productTIRepMap,logger);
				verifyFlag = report.verifyIntermediaryDataUI(productTIRepMap,logger);
				if (verifyFlag){
				logger.log(LogStatus.PASS, TestCases.TC_LOMT_1762_03_ADMIN_USER_DOWNLOAD_VERIFY_REPORT);
				}
			}
		} 
		
		else {
			logger.log(LogStatus.FAIL, "TC_LOMT-1762-01_Admin_User_School_Global_Report_Export_Download"); 
			return;
		}
		
		
		logger.log(LogStatus.INFO, ""); 
		
		reports.endTest(logger);
		reports.flush();
	}
	
	@Test(priority = 3)
	public void tearDown() {
		report.closeDriverInstance();
	}

}
