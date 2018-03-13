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

	@Test(enabled=false)//priority = 1)
	public void forwardIndirectIntermediaryReport() throws Exception {
		logger = reports.startTest(ReportsConstant.FORWARD_INDIRECT_INTERMEDIARY_REPORT + LOMTConstant.COMMA
				+ LOMTConstant.EMPTY_SPACE + ReportsConstant.LOMT_1758);

		// Admin user
		String reportName = report.createAndDownloadReport(ReportsConstant.FORWARD_INDIRECT_INTERMEDIARY_REPORT,
				ReportsConstant.INGESTED_STANDARD_YEAR, ReportsConstant.INGESTED_INTERMEDIARY, null, logger);
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
		userName = report.loginLearningEditor();
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
		userName = report.loginLearingSME();
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
		userName = report.loginLearningUser();
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

	@Test(enabled=false)//priority = 1)
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
			userName = 	report.loginLearningUser();
			boolean basicReportFlag = report.searchAndExportReport(reportName, userName);
			if (basicReportFlag) {
				logger.log(LogStatus.PASS, TestCases.TC_LOMT_1762_02_BASIC_USER_DOWNLOAD_REPORT);
			} else {
				logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1762_02_BASIC_USER_DOWNLOAD_REPORT);
			}

		reports.endTest(logger);
		reports.flush();
	}

	@Test(enabled=false)//priority = 1)
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
	
	@Test(enabled=false)//priority = 1)
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
		}
		else{
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1837_01_DOWNLOAD_REPORT);
		}
		
		//Coordinator User
		report.logout();
		userName = report.loginLearningEditor();
		boolean coordinatorReportFlag = report.searchAndExportReport(reportName,userName);
		if (coordinatorReportFlag) {
			logger.log(LogStatus.PASS, "TC_LOMT-1837-01_Coordinator_User_Reports&Exports_download_Reverse ToC to Standard via Intermediary Report");
		} else {
			logger.log(LogStatus.FAIL,"TC_LOMT-1837-01_Coordinator_User_Reports&Exports_download_Reverse ToC to Standard via Intermediary Report");
		}

		// SME User
		report.logout();
		userName = report.loginLearingSME();
		boolean smeReportFlag = report.searchAndExportReport(reportName,userName);
		if (smeReportFlag) {
			logger.log(LogStatus.PASS, "TC_LOMT-1837-01_SME_User_Reports&Exports_download_Reverse ToC to Standard via Intermediary Report");
		} else {
			logger.log(LogStatus.FAIL, "TC_LOMT-1837-01_SME_User_Reports&Exports_download_Reverse ToC to Standard via Intermediary Report");
		}

		// Basic User
		report.logout();
		userName = report.loginLearningUser();
		boolean basicReportFlag = report.searchAndExportReport(reportName,userName);
		if (basicReportFlag) {
			logger.log(LogStatus.PASS, "TC_LOMT-1837-01_Basic_User_Reports&Exports_download_Reverse ToC to Standard via Intermediary Report");
		} else {
			logger.log(LogStatus.FAIL, "TC_LOMT-1837-01_Basic_User_Reports&Exports_download_Reverse ToC to Standard via Intermediary Report");
		}
		//Login with Admin user again for next report
		report.logout();
		report.login();

		reports.endTest(logger);
		reports.flush();
	}
	
	//@Test(priority = 1)
	@Ignore
	public void forwardDirectReport() {
		logger = reports.startTest(ReportsConstant.FORWARD_DIRECT_REPORT+LOMTConstant.COMMA+LOMTConstant.EMPTY_SPACE+ReportsConstant.LOMT_1760);
		
		//Admin user		
		String reportName = report.createAndDownloadReport(ReportsConstant.FORWARD_DIRECT_REPORT, ReportsConstant.INGESTED_STANDARD_YEAR,
						 ReportsConstant.INGESTED_INTERMEDIARY, ReportsConstant.INGESTED_PRODUCT, logger);
		
		//String reportName = "Forward (Indirect) Intermediary Report-1267";
		if(reportName != null) {
			logger.log(LogStatus.PASS, "TC_LOMT-1760-01_Admin_User_School_Global_Report_Export_Download_Forward_Direct_Report"); 
			logger.log(LogStatus.PASS, "TC_LOMT-1760-03_Admin_User_School_Global_Report_Export_Download_Forward_Direct_Report"); 
			logger.log(LogStatus.PASS, "TC_LOMT-1760-05_Admin_User_School_Global_Report_Export_Download_Forward_Direct_Report"); 
			
			Map<String, List<String>> forwardIIRepMap = report.verifyExportedFile(ReportsConstant.FORWARD_DIRECT_REPORT, reportName, logger, null, null, null);
			if (!forwardIIRepMap.isEmpty()) {
				report.verifyCurriculumStandardDataUI(forwardIIRepMap, logger);
				report.verifyIntermediaryDataUI(forwardIIRepMap, logger);
			}
		} else {
			logger.log(LogStatus.FAIL, "TC_LOMT-1760-01_Admin_User_School_Global_Report_Export_Download_Forward_Direct_Report"); 
		}
		
		reports.endTest(logger);
		reports.flush();
	}
	
	//@Test(priority = 1)
	@Ignore
	public void gapAnalysisReport() {
		logger = reports.startTest(ReportsConstant.GAP_ANALYSIS_REPORT+LOMTConstant.COMMA+LOMTConstant.EMPTY_SPACE+ReportsConstant.LOMT_1840);
		
		String reportName = report.createAndDownloadReport(ReportsConstant.GAP_ANALYSIS_REPORT, ReportsConstant.CS_SOURCE_YEAR_UAT,
				 null, ReportsConstant.CS_TARGET_YEAR_UAT, logger); 
		//String reportName =  "Gap Analysis Report: R J"; // UAT report
		if (reportName != null) {
			logger.log(LogStatus.PASS, "TC_LOMT-1840-01_For_SchoolGlobal_download_GAP_Analysis_StandardToStandard_Report_for_Admin"); 
			report.verifyExportedFile(ReportsConstant.GAP_ANALYSIS_REPORT, reportName, logger, ReportsConstant.CS_SOURCE_YEAR_UAT, null,
					ReportsConstant.CS_TARGET_YEAR_UAT);
		} else {
			logger.log(LogStatus.FAIL, "TC_LOMT-1840-01_For_SchoolGlobal_download_GAP_Analysis_StandardToStandard_Report_for_Admin"); 
		}
		
		//Coordinator User
		report.logout();
		String userNameCoordinator = report.loginLearningEditor();
		boolean coordinatorReportFlag = report.searchAndExportReport(reportName, userNameCoordinator);
		if (coordinatorReportFlag) {
			logger.log(LogStatus.PASS,"TC_LOMT-1840-04_For_SchoolGlobal_download_GAP_Analysis_StandardToStandard_Report_For_Coordinator");
		} else {
			logger.log(LogStatus.FAIL,"TC_LOMT-1840-04_For_SchoolGlobal_download_GAP_Analysis_StandardToStandard_Report_For_Coordinator");
		}

		// SME User
		report.logout();
		
		String userNameSME = report.loginLearingSME();
		boolean smeReportFlag = report.searchAndExportReport(reportName, userNameSME);
		if (smeReportFlag) {
			logger.log(LogStatus.PASS,"TC_LOMT-1840-03_For_SchoolGlobal_download_GAP_Analysis_StandardToStandard_Report_for_SME");
		} else {
			logger.log(LogStatus.FAIL,"TC_LOMT-1840-03_For_SchoolGlobal_download_GAP_Analysis_StandardToStandard_Report_for_SME");
		}

		//BasicBrowser User
		report.logout();
		String userNameBasic = report.loginLearningUser();
		boolean basicReportFlag = report.searchAndExportReport(reportName, userNameBasic);
		if (basicReportFlag) {
			logger.log(LogStatus.PASS,"TC_LOMT-1840-02_For_SchoolGlobal_download_GAP_Analysis_StandardToStandard_Report_for_basicUser");
		} else {
			logger.log(LogStatus.FAIL,"TC_LOMT-1840-02_For_SchoolGlobal_download_GAP_Analysis_StandardToStandard_Report_for_basicUser");
		}
		reports.endTest(logger);
		reports.flush();
	}
	
	//@Test(priority = 1)
	@Ignore
	public void summmaryReport() throws Exception {
		logger = reports.startTest(ReportsConstant.SUMMARY_TEXT + LOMTConstant.COMMA
				+ LOMTConstant.EMPTY_SPACE + ReportsConstant.LOMT_1841);
		// Admin user
		String reportName = report.createAndDownloadReport(ReportsConstant.SUMMARY_REPORT, ReportsConstant.CS_SOURCE_YEAR_UAT,
				 null, ReportsConstant.CS_TARGET_YEAR_UAT, logger);
		if (!reportName.isEmpty()) {
			logger.log(LogStatus.PASS, "TC_LOMT-1841-01_All_User_Reports&Exports_download_Summary Report");
			report.verifyExportedFile(ReportsConstant.SUMMARY_REPORT, reportName, logger, ReportsConstant.CS_SOURCE_YEAR_UAT,
					null,ReportsConstant.CS_TARGET_YEAR_UAT);
			
			//Coordinator User
			report.logout();
			userName = report.loginLearningEditor();
			boolean coordinatorReportFlag = report.searchAndExportReport(reportName,userName);
			if (!coordinatorReportFlag) {
				logger.log(LogStatus.FAIL, "TC_LOMT-1841-01_All_User_Reports&Exports_download_Summary Report");
			}

			// SME User
			report.logout();
			userName = report.loginLearingSME();
			boolean smeReportFlag = report.searchAndExportReport(reportName,userName);
			if (!smeReportFlag) {
				logger.log(LogStatus.FAIL, "TC_LOMT-1841-01_All_User_Reports&Exports_download_Summary Report");
			}

			// Basic User
			report.logout();
			userName = report.loginLearningUser();
			boolean basicReportFlag = report.searchAndExportReport(reportName,userName);
			if (!basicReportFlag) {
				logger.log(LogStatus.FAIL, "TC_LOMT-1841-01_All_User_Reports&Exports_download_Summary Report");
			}
			
			//Login with Admin user again for next report
			report.logout();
			report.login();

			reports.endTest(logger);
			reports.flush();}
		}
	
	//@Test(priority = 1)
	@Ignore
	public void forwardSharedIntermediaryReport() {
		logger = reports.startTest(ReportsConstant.FOWARD_SHARED_INTERMEDIARY_REPORT+LOMTConstant.COMMA+LOMTConstant.EMPTY_SPACE+ReportsConstant.LOMT_1838);
		
		// Admin user 
		String reportName = report.createAndDownloadReport(ReportsConstant.FOWARD_SHARED_INTERMEDIARY_REPORT,
				ReportsConstant.CS_TARGET_YEAR_UAT, ReportsConstant.DISCIPLINE_NAME, ReportsConstant.TOC_NAME, logger);
		if (!reportName.isEmpty()) {
			logger.log(LogStatus.PASS, "TC_LOMT-1838-03_Admin_User_School_Global_Report_Export_Download_Forward_Direct_Report"); 
			logger.log(LogStatus.PASS, "TC_LOMT-1838-05_Admin_User_School_Global_Report_Export_Download_Forward_Direct_Report"); 
			
			report.verifyExportedFile(ReportsConstant.FOWARD_SHARED_INTERMEDIARY_REPORT, reportName, logger,
					ReportsConstant.CS_TARGET_YEAR_UAT, ReportsConstant.DISCIPLINE_NAME, ReportsConstant.TOC_NAME);
		} else {
			logger.log(LogStatus.FAIL, "TC_LOMT-1838-03_Admin_User_School_Global_Report_Export_Download_Forward_Direct_Report");
			logger.log(LogStatus.FAIL, "TC_LOMT-1838-05_Admin_User_School_Global_Report_Export_Download_Forward_Direct_Report");
			logger.log(LogStatus.FAIL, "TC_LOMT-1838-07_Admin_User_School_Global_Report_Export_Download_Forward_Direct_Report");
			logger.log(LogStatus.FAIL, "TC_LOMT-1838-09_Admin_User_School_Global_Report_Export_Download_Forward_Direct_Report");
			logger.log(LogStatus.FAIL, "TC_LOMT-1838-11_Admin_User_School_Global_Report_Export_Download_Forward_Direct_Report");
			logger.log(LogStatus.FAIL, "TC_LOMT-1838-13_Admin_User_School_Global_Report_Export_Download_Forward_Direct_Report");
			logger.log(LogStatus.FAIL, "TC_LOMT-1838-17_Admin_User_School_Global_Report_Export_Download_Forward_Direct_Report");
		}
		
		//Coordinator User
		report.logout();
		String userNameCoordinator = report.loginLearningEditor();
		boolean coordinatorReportFlag = report.searchAndExportReport(reportName, userNameCoordinator);
		
		// SME User
		report.logout();		
		String userNameSME = report.loginLearingSME();
		boolean smeReportFlag = report.searchAndExportReport(reportName, userNameSME);
		
		//BasicBrowser User
		report.logout();
		String userNameBasic = report.loginLearningUser();
		boolean basicReportFlag = report.searchAndExportReport(reportName, userNameBasic);
		
		if (coordinatorReportFlag && smeReportFlag && basicReportFlag) {
			logger.log(LogStatus.PASS,"TC_LOMT-1838-02_Basic_SME_Coordinator_User_School_Global_Report_Export_Download_Forward_Direct_Report");
			logger.log(LogStatus.PASS,"TC_LOMT-1838-04_Basic_SME_Coordinator_User_School_Global_Report_Export_Download_Forward_Direct_Report");
			logger.log(LogStatus.PASS,"TC_LOMT-1838-06_Basic_SME_Coordinator_User_School_Global_Report_Export_Download_Forward_Direct_Report");
			logger.log(LogStatus.PASS,"TC_LOMT-1838-08_Basic_SME_Coordinator_User_School_Global_Report_Export_Download_Forward_Direct_Report");
			logger.log(LogStatus.PASS,"TC_LOMT-1838-10_Basic_SME_Coordinator_User_School_Global_Report_Export_Download_Forward_Direct_Report");
			logger.log(LogStatus.PASS,"TC_LOMT-1838-12_Basic_SME_Coordinator_User_School_Global_Report_Export_Download_Forward_Direct_Report");
			logger.log(LogStatus.PASS,"TC_LOMT-1838-14_Basic_SME_Cooedinator_User_School_Global_Report_Export_Download_Forward_Direct_Report");
			logger.log(LogStatus.PASS,"TC_LOMT-1838-16_Basic_SME_Coordinator_User_School_Global_Report_Export_Download_Forward_Direct_Report");
			logger.log(LogStatus.PASS,"TC_LOMT-1838-18_Basic_SME_User_School_Global_Report_Export_Download_Forward_Direct_Report");
		} else {
			logger.log(LogStatus.FAIL,"TC_LOMT-1838-02_Basic_SME_Coordinator_User_School_Global_Report_Export_Download_Forward_Direct_Report");
			logger.log(LogStatus.FAIL,"TC_LOMT-1838-04_Basic_SME_Coordinator_User_School_Global_Report_Export_Download_Forward_Direct_Report");
			logger.log(LogStatus.FAIL,"TC_LOMT-1838-06_Basic_SME_Coordinator_User_School_Global_Report_Export_Download_Forward_Direct_Report");
			logger.log(LogStatus.FAIL,"TC_LOMT-1838-08_Basic_SME_Coordinator_User_School_Global_Report_Export_Download_Forward_Direct_Report");
			logger.log(LogStatus.FAIL,"TC_LOMT-1838-10_Basic_SME_Coordinator_User_School_Global_Report_Export_Download_Forward_Direct_Report");
			logger.log(LogStatus.FAIL,"TC_LOMT-1838-12_Basic_SME_Coordinator_User_School_Global_Report_Export_Download_Forward_Direct_Report");
			logger.log(LogStatus.FAIL,"TC_LOMT-1838-14_Basic_SME_Cooedinator_User_School_Global_Report_Export_Download_Forward_Direct_Report");
			logger.log(LogStatus.FAIL,"TC_LOMT-1838-16_Basic_SME_Coordinator_User_School_Global_Report_Export_Download_Forward_Direct_Report");
			logger.log(LogStatus.FAIL,"TC_LOMT-1838-18_Basic_SME_User_School_Global_Report_Export_Download_Forward_Direct_Report");
		}
		
		reports.endTest(logger);
		reports.flush();
	}
	
	//@Test(priority = 1)
	@Ignore
	public void standardTocIntermediaryReport() {
		logger = reports.startTest(ReportsConstant.STANDARD_TOC_INTERMEDIARY_REPORT+LOMTConstant.COMMA+LOMTConstant.EMPTY_SPACE+ReportsConstant.LOMT_1836);
		
		// Admin user 
		String reportName = report.createAndDownloadReport(ReportsConstant.STANDARD_TOC_INTERMEDIARY_REPORT,
				ReportsConstant.CS_SOURCE_UAT, ReportsConstant.INTERMEDIARY_NAME, ReportsConstant.FIRST_TOC_NAME, ReportsConstant.SECOND_TOC_NAME, logger);
		if (!reportName.isEmpty()) {
			logger.log(LogStatus.PASS, "TC-LOMT-1836-01_ADMIN_User_SchoolGlobal_ReportsAndExports_StandardToToC ViaIntermediaryReport"); 
			logger.log(LogStatus.PASS, "TC-LOMT-1836-07_Admin_User_SchoolGlobal_ReportsAndExports_StandardToToC ViaIntermediaryReport"); 
			
			report.verifyExportedFile(ReportsConstant.STANDARD_TOC_INTERMEDIARY_REPORT, reportName, logger,
					ReportsConstant.CS_SOURCE_UAT, ReportsConstant.INTERMEDIARY_NAME, ReportsConstant.FIRST_TOC_NAME, ReportsConstant.SECOND_TOC_NAME);
					
			
		} else {
			logger.log(LogStatus.FAIL, "TC-LOMT-1836-01_ADMIN_User_SchoolGlobal_ReportsAndExports_StandardToToC ViaIntermediaryReport");
			logger.log(LogStatus.FAIL, "TC-LOMT-1836-07_Admin_User_SchoolGlobal_ReportsAndExports_StandardToToC ViaIntermediaryReport");
			
		}
		
		//Coordinator User
		report.logout();
		String userNameCoordinator = report.loginLearningEditor();
		boolean coordinatorReportFlag = report.searchAndExportReport(reportName, userNameCoordinator);
				
		// SME User
		report.logout();		
		String userNameSME = report.loginLearingSME();
		boolean smeReportFlag = report.searchAndExportReport(reportName, userNameSME);
				
		//BasicBrowser User
		report.logout();
		String userNameBasic = report.loginLearningUser();
		boolean basicReportFlag = report.searchAndExportReport(reportName, userNameBasic);
		
		if (coordinatorReportFlag && smeReportFlag && basicReportFlag) {
			logger.log(LogStatus.PASS,"TC-LOMT-1836-02_SME_User_SchoolGlobal_ReportsAndExports_StandardToToC ViaIntermediaryReport");
			logger.log(LogStatus.PASS,"TC-LOMT-1836-03_Coordinator_User_SchoolGlobal_ReportsAndExports_StandardToToC ViaIntermediaryReport");
			logger.log(LogStatus.PASS,"TC-LOMT-1836-04_BasicBrowser_User_SchoolGlobal_ReportsAndExports_StandardToToC ViaIntermediaryReport");
			logger.log(LogStatus.PASS,"TC-LOMT-1836-08_SME_User_SchoolGlobal_ReportsAndExports_StandardToToC ViaIntermediaryReport");
			logger.log(LogStatus.PASS,"TC-LOMT-1836-09_Coordinator_User_SchoolGlobal_ReportsAndExports_StandardToToC ViaIntermediaryReport");
			logger.log(LogStatus.PASS,"TC-LOMT-1836-10_BasicBrowser_User_SchoolGlobal_ReportsAndExports_StandardToToC ViaIntermediaryReport");
		} else {
			logger.log(LogStatus.FAIL,"TC-LOMT-1836-02_SME_User_SchoolGlobal_ReportsAndExports_StandardToToC ViaIntermediaryReport");
			logger.log(LogStatus.FAIL,"TC-LOMT-1836-03_Coordinator_User_SchoolGlobal_ReportsAndExports_StandardToToC ViaIntermediaryReport");
			logger.log(LogStatus.FAIL,"TC-LOMT-1836-04_BasicBrowser_User_SchoolGlobal_ReportsAndExports_StandardToToC ViaIntermediaryReport");
			logger.log(LogStatus.FAIL,"TC-LOMT-1836-08_SME_User_SchoolGlobal_ReportsAndExports_StandardToToC ViaIntermediaryReport");
			logger.log(LogStatus.FAIL,"TC-LOMT-1836-09_Coordinator_User_SchoolGlobal_ReportsAndExports_StandardToToC ViaIntermediaryReport");
			logger.log(LogStatus.FAIL,"TC-LOMT-1836-10_BasicBrowser_User_SchoolGlobal_ReportsAndExports_StandardToToC ViaIntermediaryReport");
		}
		
		reports.endTest(logger);
		reports.flush();
	}
	
	@Test(priority = 2)
	public void tearDown() {
		report.closeDriverInstance();
	}

}
