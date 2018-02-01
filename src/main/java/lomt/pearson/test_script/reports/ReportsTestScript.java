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
	String userName = null;
	Reports report = new Reports();

	@Test(priority = 0)
	public void setup() {
		report.openBrowser();
		report.login();
	}

	// @Test(priority = 1)
	@Ignore
	public void forwardIndirectIntermediaryReport() throws Exception {
		logger = reports.startTest(ReportsConstant.FORWARD_INDIRECT_INTERMEDIARY_REPORT + LOMTConstant.COMMA
				+ LOMTConstant.EMPTY_SPACE + ReportsConstant.LOMT_1758);

		// Admin user
		String reportName = report.createAndDownloadReport(ReportsConstant.FORWARD_INDIRECT_INTERMEDIARY_REPORT,
				ReportsConstant.CS_GOALFRAMEWORK_NAME_PPE, ReportsConstant.INGESTED_INTERMEDIARY_PPE, null, logger);
		if (reportName != null) {
			Map<String, List<String>> forwardIIRepMap = report
					.verifiedForwardIndirectIntermediaryReportsExportedFile(reportName, logger);
			if (!forwardIIRepMap.isEmpty()) {
				report.verifyCurriculumStandardDataUI(forwardIIRepMap, logger);
				report.verifyIntermediaryDataUI(forwardIIRepMap, logger);
				logger.log(LogStatus.PASS,
						"TC-LOMT-1758-01_Admin_User_SchoolGlobal_Reports_&exports_Forward-Indirect_Intermediary_Report");
			}
		} else {
			logger.log(LogStatus.FAIL,
					"TC-LOMT-1758-01_Admin_User_SchoolGlobal_Reports_&exports_Forward-Indirect_Intermediary_Report");
		}

		// Coordinator User
		report.logout();
		report.loginLearningEditor();
		boolean coordinatorReportFlag = report.searchAndExportReport(reportName, userName);
		if (coordinatorReportFlag) {
			logger.log(LogStatus.PASS,
					"TC-LOMT-1758-02_Coordinator_User_SchoolGlobal_Reports_&exports_Forward-Indirect_Intermediary_Report");
		} else {
			logger.log(LogStatus.FAIL,
					"TC-LOMT-1758-02_Coordinator_User_SchoolGlobal_Reports_&exports_Forward-Indirect_Intermediary_Report");
		}

		// SME User
		report.logout();
		report.loginLearingSME();
		boolean smeReportFlag = report.searchAndExportReport(reportName, userName);
		if (smeReportFlag) {
			logger.log(LogStatus.PASS,
					"TC-LOMT-1758-03_SME_User_SchoolGlobal_Reports_&exports_Forward-Indirect_Intermediary_Report");
		} else {
			logger.log(LogStatus.FAIL,
					"TC-LOMT-1758-03_SME_User_SchoolGlobal_Reports_&exports_Forward-Indirect_Intermediary_Report");
		}

		// BasicBrowser User
		report.logout();
		report.loginLearningUser();
		boolean basicReportFlag = report.searchAndExportReport(reportName, userName);
		if (basicReportFlag) {
			logger.log(LogStatus.PASS,
					"TC-LOMT-1758-04_BasicBrowser_User_SchoolGlobal_Reports_&exports_Forward-Indirect_Intermediary_Report");
		} else {
			logger.log(LogStatus.FAIL,
					"TC-LOMT-1758-04_BasicBrowser_User_SchoolGlobal_Reports_&exports_Forward-Indirect_Intermediary_Report");
		}

		reports.endTest(logger);
		reports.flush();
	}

	// @Test(priority = 2)
	@Ignore
	public void productToCIntermediaryReport() throws Exception {
		logger = reports.startTest("Product (ToC) Intermediary Report, LOMT-1762");

		//Admin user
		String reportName = report.createAndDownloadReportSourceProduct(ReportsConstant.PRODUCT_INT_TEXT,ReportsConstant.INGESTED_PRODUCT,
				ReportsConstant.INGESTED_INTERMEDIARY,null,logger);
		if(!reportName.isEmpty()) {
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1762_01_ADMIN_USER_DOWNLOAD_REPORT);
			Map<String, List<String>> productTIRepMap = report.verifyProductToCIntermediaryReport();
			if (!productTIRepMap.isEmpty()) {
				boolean verifyFlag1 = report.verifyProductDataUI(productTIRepMap,logger);
				boolean verifyFlag2 = report.verifyIntermediaryDataUI(productTIRepMap,logger);
				if (verifyFlag1 && verifyFlag2){
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1762_03_ADMIN_USER_DOWNLOAD_VERIFY_REPORT);
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1762_04_BASIC_USER_DOWNLOAD_VERIFY_REPORT);
				}
				else{
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1762_03_ADMIN_USER_DOWNLOAD_VERIFY_REPORT);
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1762_04_BASIC_USER_DOWNLOAD_VERIFY_REPORT);
				}
			}
		} 

		else {
			logger.log(LogStatus.FAIL, "TC_LOMT-1762-01_Admin_User_School_Global_Report_Export_Download"); 
			return;
		}
		// BasicBrowser User
				report.logout();
				report.loginLearningUser();
				boolean basicReportFlag = report.searchAndExportReport(reportName, userName);
				if (basicReportFlag) {
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1762_02_BASIC_USER_DOWNLOAD_REPORT);
				} else {
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1762_02_BASIC_USER_DOWNLOAD_REPORT);
				}

		reports.endTest(logger);
		reports.flush();
	}

	//@Test(priority = 1)
	@Ignore
	public void reverseSharedIntermediaryReport() throws Exception {
		logger = reports.startTest(ReportsConstant.REVERSE_SHARED_INT_TEXT + LOMTConstant.COMMA
				+ LOMTConstant.EMPTY_SPACE + ReportsConstant.LOMT_1839);

		// Admin user
		String reportName = report.createAndDownloadReportSourceProduct(ReportsConstant.REVERSE_SHARED_INT_TEXT,ReportsConstant.INGESTED_PRODUCT,
				ReportsConstant.INGESTED_INTERMEDIARY,ReportsConstant.INGESTED_STANDARD_YEAR,logger);
		if (!reportName.isEmpty()) {
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1839_01_DOWNLOAD_REPORT);
			Map<String, List<String>> productCSRepMap = report.verifyReport(reportName,logger);
			if (!productCSRepMap.isEmpty()) {
				logger.log(LogStatus.PASS, TestCases.TC_LOMT_1839_03_CORRELATION_VERIFY);
				logger.log(LogStatus.PASS, TestCases.TC_LOMT_1839_04_STRENGTH_WEAK_VERIFY);
				logger.log(LogStatus.PASS, TestCases.TC_LOMT_1839_05_STRENGTH_AVERAGE_VERIFY);
				logger.log(LogStatus.PASS, TestCases.TC_LOMT_1839_06_STRENGTH_COMPLETE_VERIFY);
				logger.log(LogStatus.PASS, TestCases.TC_LOMT_1839_07_MET_VERIFY);
				logger.log(LogStatus.PASS, TestCases.TC_LOMT_1839_08_UNMET_VERIFY);
				boolean verifyFlag1 = report.verifyProductDataUI(productCSRepMap, logger);
				boolean verifyFlag2 = report.verifyCurriculumStandardDataUI(productCSRepMap, logger);
				;
				if (verifyFlag1 && verifyFlag2) {
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1839_02_REPORT_VERIFY);
				} else {
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1839_02_REPORT_VERIFY);
				}
			}
		} else {
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1839_01_DOWNLOAD_REPORT);
		}

		report.logout();
		userName = report.loginLearningEditor();
		boolean coordinatorReportFlag = report.searchAndExportReport(reportName,userName);
		if (coordinatorReportFlag) {
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1839_01_DOWNLOAD_REPORT + ": Coordinator User");
		} else {
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1839_01_DOWNLOAD_REPORT + ": Coordinator User");
		}

		// SME User
		report.logout();
		userName = report.loginLearingSME();
		boolean smeReportFlag = report.searchAndExportReport(reportName,userName);
		if (smeReportFlag) {
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1839_01_DOWNLOAD_REPORT + ": SME User");
		} else {
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1839_01_DOWNLOAD_REPORT + ": SME User");
		}

		// BasicBrowser User
		report.logout();
		userName = report.loginLearningUser();
		boolean basicReportFlag = report.searchAndExportReport(reportName,userName);
		if (basicReportFlag) {
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1839_01_DOWNLOAD_REPORT + ": Basic User");
		} else {
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1839_01_DOWNLOAD_REPORT + ": Basic User");
		}

		reports.endTest(logger);
		reports.flush();
	}
	
	//@Test(priority = 1)
	@Ignore
	public void reverseDirectReport() throws Exception {
		logger = reports.startTest(ReportsConstant.REVERSE_DIRECT_TEXT + LOMTConstant.COMMA
				+ LOMTConstant.EMPTY_SPACE + ReportsConstant.LOMT_1761);

		// Admin user
		String reportName = report.createAndDownloadReportSourceProduct(ReportsConstant.REVERSE_DIRECT_TEXT,ReportsConstant.INGESTED_PRODUCT,
				ReportsConstant.INGESTED_INTERMEDIARY,ReportsConstant.INGESTED_STANDARD_YEAR,logger);
		if (!reportName.isEmpty()) {
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1761_01_DOWNLOAD_REPORT_ADMIN);
			Map<String, List<String>> productCSRepMap = report.verifyReport(reportName,logger);
			if (!productCSRepMap.isEmpty()) {
				logger.log(LogStatus.PASS, TestCases.TC_LOMT_1761_05_CORRELATION_VERIFY);
				boolean verifyFlag1 = report.verifyProductDataUI(productCSRepMap, logger);
				boolean verifyFlag2 = report.verifyCurriculumStandardDataUI(productCSRepMap, logger);
				;
				if (verifyFlag1 && verifyFlag2) {
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1761_06_REPORT_VERIFY);
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1761_07_REPORT_VERIFY_BASIC);
				} else {
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1761_06_REPORT_VERIFY);
				}
			}
		} else {
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1761_01_DOWNLOAD_REPORT_ADMIN);
		}

		report.logout();
		userName = report.loginLearningEditor();
		boolean coordinatorReportFlag = report.searchAndExportReport(reportName,userName);
		if (coordinatorReportFlag) {
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1761_03_DOWNLOAD_REPORT_COORDINATOR);
		} else {
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1761_03_DOWNLOAD_REPORT_COORDINATOR);
		}

		// SME User
		report.logout();
		userName = report.loginLearingSME();
		boolean smeReportFlag = report.searchAndExportReport(reportName,userName);
		if (smeReportFlag) {
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1761_02_DOWNLOAD_REPORT_SME);
		} else {
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1761_02_DOWNLOAD_REPORT_SME);
		}

		// Basic User
		report.logout();
		userName = report.loginLearningUser();
		boolean basicReportFlag = report.searchAndExportReport(reportName,userName);
		if (basicReportFlag) {
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1761_04_DOWNLOAD_REPORT_BASIC);
		} else {
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1761_04_DOWNLOAD_REPORT_BASIC);
		}
		
		logger.log(LogStatus.INFO, TestCases.TC_LOMT_1761_08_SCAPI_URL_VERIFY);
		logger.log(LogStatus.INFO, TestCases.TC_LOMT_1761_09_SCAPI_DATA_VERIFY);
		//Login with Admin user again for next report
		report.logout();
		report.login();

		reports.endTest(logger);
		reports.flush();
	}

	@Test(priority = 1)
	public void reverseTocToStandardViaIntermediaryReport() throws Exception {
		logger = reports.startTest(ReportsConstant.REVERSE_TOC_STANDARD_VIA_INT_TEXT + LOMTConstant.COMMA
				+ LOMTConstant.EMPTY_SPACE + ReportsConstant.LOMT_1837);

		// Admin user
		String reportName = report.createAndDownloadReportSourceProduct(ReportsConstant.REVERSE_TOC_STANDARD_VIA_INT_TEXT,ReportsConstant.INGESTED_PRODUCT,
				ReportsConstant.INGESTED_INTERMEDIARY,ReportsConstant.INGESTED_STANDARD_YEAR,logger);
		if (!reportName.isEmpty()) {
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1837_01_DOWNLOAD_REPORT);
			Map<String, List<String>> productCSRepMap = report.verifyReport(reportName,logger);
			if (!productCSRepMap.isEmpty()) {
				logger.log(LogStatus.PASS, TestCases.TC_LOMT_1761_05_CORRELATION_VERIFY);
				boolean verifyFlag1 = report.verifyProductDataUI(productCSRepMap, logger);
				boolean verifyFlag2 = report.verifyCurriculumStandardDataUI(productCSRepMap, logger);
				;
				if (verifyFlag1 && verifyFlag2) {
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1761_06_REPORT_VERIFY);
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1761_07_REPORT_VERIFY_BASIC);
				} else {
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1761_06_REPORT_VERIFY);
				}
			}
		} else {
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1837_01_DOWNLOAD_REPORT);
		}

		report.logout();
		userName = report.loginLearningEditor();
		boolean coordinatorReportFlag = report.searchAndExportReport(reportName,userName);
		if (coordinatorReportFlag) {
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1761_03_DOWNLOAD_REPORT_COORDINATOR);
		} else {
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1761_03_DOWNLOAD_REPORT_COORDINATOR);
		}

		// SME User
		report.logout();
		userName = report.loginLearingSME();
		boolean smeReportFlag = report.searchAndExportReport(reportName,userName);
		if (smeReportFlag) {
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1761_02_DOWNLOAD_REPORT_SME);
		} else {
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1761_02_DOWNLOAD_REPORT_SME);
		}

		// Basic User
		report.logout();
		userName = report.loginLearningUser();
		boolean basicReportFlag = report.searchAndExportReport(reportName,userName);
		if (basicReportFlag) {
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1761_04_DOWNLOAD_REPORT_BASIC);
		} else {
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1761_04_DOWNLOAD_REPORT_BASIC);
		}
		
		logger.log(LogStatus.INFO, TestCases.TC_LOMT_1761_08_SCAPI_URL_VERIFY);
		logger.log(LogStatus.INFO, TestCases.TC_LOMT_1761_09_SCAPI_DATA_VERIFY);
		//Login with Admin user again for next report
		report.logout();
		report.login();

		reports.endTest(logger);
		reports.flush();
	}
	
	@Test(priority = 4)
	public void tearDown() {
		report.closeDriverInstance();
	}

}
