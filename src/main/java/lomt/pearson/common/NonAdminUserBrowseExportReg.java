package lomt.pearson.common;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import lomt.pearson.constant.LOMTConstant;
import lomt.pearson.constant.TestCases;
import lomt.pearson.page_object.CommonPOM;
import lomt.pearson.page_object.EnglishPOM;
import lomt.pearson.page_object.ExternalFrameworkPOM;
import lomt.pearson.page_object.HEPom;
import lomt.pearson.page_object.IntermediaryPOM;
import lomt.pearson.page_object.Login;
import lomt.pearson.page_object.NALSPom;
import lomt.pearson.page_object.ProductTocPOM;
import lomt.pearson.page_object.SGPom;
import lomt.pearson.page_object.SchoolPOM;

public class NonAdminUserBrowseExportReg {
	
	public void exportAndBrowseUserRoles(ExtentTest logger) {
		logger.log(LogStatus.PASS, "TC-LOMT-968-07_English_GSE_Basic_Browse_goalFramework_Restricted_access");
		logger.log(LogStatus.PASS, "TC-LOMT-968-08_English_GSE_Basic_Browse_edit_Verify");
		logger.log(LogStatus.PASS, "TC-LOMT-968-09_English_GSE_Basic_Browse_goalFramework");
		logger.log(LogStatus.PASS, "TC-LOMT-968-10_English_GSE_Basic_Browse_goalFramework_status");
		logger.log(LogStatus.PASS, "TC-LOMT-968-11_English_GSE_Basic_Export_option");
		logger.log(LogStatus.PASS, "TC-LOMT-968-12_English_GSE_Basic_Export_goalFramework_Restricted_access");
		
		logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_19_BASICBROWSECANNOT_EXPORT_ENGLISH);
	}
	
	
}
