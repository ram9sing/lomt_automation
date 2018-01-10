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
				report.verifyIntermediaryDataUI(forwardIIRepMap);
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
		report.createAndDownloadReport1();
		boolean reportFlag = report.productToCIntermediaryReport();
		if(reportFlag) {
			Map<String, List<String>> productTIRepMap = report.verifyProductToCIntermediaryReport();
			if (!productTIRepMap.isEmpty()) {
				report.verifyCurriculumStandardDataUI(productTIRepMap);
				report.verifyIntermediaryDataUI(productTIRepMap);
			}
		} else {
			logger.log(LogStatus.FAIL, "TC-LOMT-1758-01_Admin_User_SchoolGlobal_Reports_&exports_Forward-Indirect_Intermediary_Report"); 
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
