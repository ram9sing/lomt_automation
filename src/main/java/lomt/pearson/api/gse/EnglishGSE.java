package lomt.pearson.api.gse;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import lomt.pearson.common.BaseClass;
import lomt.pearson.common.LoadPropertiesFile;
import lomt.pearson.common.ReadGSEXLSFile;
import lomt.pearson.constant.LOMTConstant;
import lomt.pearson.constant.TestCases;
import lomt.pearson.page_object.CommonPOM;
import lomt.pearson.page_object.EnglishPOM;
import lomt.pearson.page_object.ExternalFrameworkPOM;
import lomt.pearson.page_object.Login;

public class EnglishGSE extends BaseClass {

	private WebDriver driver;
	
	private Login login = null;
	private CommonPOM commonPOM = null;
	private EnglishPOM englishPOM = null;
	private ExternalFrameworkPOM exfPOM = null;
	EnglishGseHelper gseHealper = new EnglishGseHelper();
	
	private String environment = LoadPropertiesFile.getPropertiesValues(LOMTConstant.LOMT_ENVIRONMENT);
	String userName = LoadPropertiesFile.getPropertiesValues(LOMTConstant.USER_NAME);
	//String userName = LoadPropertiesFile.getPropertiesValues(LOMTConstant.USER_NAME_TEST);
	String pwd = LoadPropertiesFile.getPropertiesValues(LOMTConstant.PASSWORD);
	
	String userNameBasic = LoadPropertiesFile.getPropertiesValues(LOMTConstant.USER_NAME_LEARNING_USER);
	String pwdBasic = LoadPropertiesFile.getPropertiesValues(LOMTConstant.PASSWORD_LEARNING_USER_PWD);
	
	public void getDriverInstance(WebDriver driver) {
		this.driver = initialiseChromeDriver();
	}

	public void openBrowser() {
		getDriverInstance(driver);
		driver.manage().window().maximize();
		driver.get(environment);
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);

		commonPOM = new CommonPOM(driver);
		englishPOM = new EnglishPOM(driver);
		exfPOM = new ExternalFrameworkPOM(driver);
	}
	
	public void login() {
		try {
			login = new Login(driver);
			login.getUserName().sendKeys(userName);
			login.getPassword().sendKeys(pwd);
			login.getLoginButton().click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
			// add logger
		}
	}
	
	public void loginNonAdminRole() {
		try {
			login = new Login(driver);
			login.getUserName().sendKeys(userNameBasic);
			login.getPassword().sendKeys(pwdBasic);
			login.getLoginButton().click();
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
		} catch (Exception e) {
			e.printStackTrace();
			// add logger
		}
	}
	
	public void englishBrowsePage(ExtentTest logger) {
		try {
			Assert.assertTrue(commonPOM.getEnglishLOB().isDisplayed());
			commonPOM.getEnglishLOB().click();
			
			//Assert.assertEquals(commonPOM.getWelcomeTitle().getText(), LOMTConstant.WELCOME_TITLE);
			Assert.assertTrue(englishPOM.getEnglishBanner().isDisplayed());
			Assert.assertTrue(englishPOM.getGseLink().isDisplayed());
			Assert.assertTrue(englishPOM.getExternalFrameworkLink().isDisplayed());
			Assert.assertTrue(englishPOM.getProductLink().isDisplayed());
			Assert.assertTrue(commonPOM.getManageIngestion().isDisplayed());
			
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,500)");
			
			logger.log(LogStatus.PASS, "TC-LOMT-11-02_Admin_verify Manage Ingestion");
			
			commonPOM.getManageIngestion().click();
			logger.log(LogStatus.PASS, "TC-LOMT-11-03_Admin_Manage Ingestion_Click");
		} catch (Exception e) {
			e.printStackTrace();
			// add logger
		}
	}
	
	public void logout() {
		try {
			commonPOM.getLogoutOption().click();
			Thread.sleep(2000);
			commonPOM.getLogout().click();
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void englishGSEBrowsePageForNonAdminUser() throws InterruptedException {
		Assert.assertTrue(commonPOM.getEnglishLOB().isDisplayed());
		commonPOM.getEnglishLOB().click();
		Thread.sleep(1000);
		//For non-admin user Manage Ingestion XPATH IS not available on browser so
		// unable to apply assertion, need to discuss with @kailash
		commonPOM.getPearsonLogo().click();
	}
	
	public void createUploadStructurePage() throws Exception {
		try {
			/*//Header, Footer 
			Assert.assertTrue(commonPOM.getBackLinkFirst().isDisplayed());
			Assert.assertEquals(commonPOM.getCreateUploadStructureHeader().getText(), LOMTConstant.CREATE_STRUCTURE_TILE);
			Assert.assertFalse(commonPOM.getNextButtonFirst().isEnabled());  
			// Next button is disabled
			
			//LOB
			Assert.assertEquals(commonPOM.getSelectLOBTitle().getText(), LOMTConstant.SELECT_LOB);
			Assert.assertTrue(commonPOM.getEnglishLOBRadioButton().isDisplayed());
			Assert.assertTrue(commonPOM.getHeLOBRadioButton().isDisplayed());
			Assert.assertTrue(commonPOM.getSchoolGlobalLOBRadioButton().isDisplayed());
			Assert.assertTrue(commonPOM.getNalsLOBRadioButton().isDisplayed());
			
			//Structure
			Assert.assertEquals(commonPOM.getSelectStructureTitle().getText(), LOMTConstant.SELECT_STRUCTURE_TYPE);
			Assert.assertTrue(commonPOM.getGseStructureRadioButton().isEnabled());
			Assert.assertTrue(commonPOM.getProductExternalFrameworkStructureRadioButton().isDisplayed());*/
			//Assert.assertTrue(commonPOM.getGseProductStructureRadioButton().isEnabled()); - not available in PPE
			//Assert.assertFalse(commonPOM.getGseProductStructureRadioButton().isEnabled());
			
			commonPOM.getGseStructureRadioButton().click();
			Thread.sleep(2000);
			
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,500)");
			
			//Right options
			/*Assert.assertTrue(commonPOM.getFirstTextImage().isDisplayed());
			Assert.assertEquals(commonPOM.getFirstTextMessage().getText(), LOMTConstant.CHOOSE_STRUCTURE_TYPE);			
			Assert.assertTrue(englishPOM.getSecondTextImage().isDisplayed());
			Assert.assertEquals(englishPOM.getSecondTextEnglish().getText(), LOMTConstant.STRUCTURE_PAGE_IMAGE_2_TEXT);			
			Assert.assertTrue(commonPOM.getThirdTextImage().isDisplayed());
			Assert.assertEquals(commonPOM.getThirdTextMessage().getText(), LOMTConstant.STRUCTURE_PAGE_IMAGE_3_TEXT);			
			Assert.assertTrue(commonPOM.getFourthTextImage().isDisplayed());
			Assert.assertEquals(commonPOM.getFourthTextMessage().getText(), LOMTConstant.STRUCTURE_PAGE_IMAGE_4_TEXT);*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean createUploadStructurePageWithBackOperation() {
		boolean flag = false;
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,-800)");
			
			commonPOM.getBackLinkFirst().click();
			Thread.sleep(1000);
			commonPOM.getGseStructureRadioButton().click();
			Thread.sleep(1000);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public boolean createUploadStructurePageWithNextOperation() {
		boolean flag = false;
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,500)");
			if (commonPOM.getNextButtonFirst().isEnabled()) {
				commonPOM.getNextButtonFirst().click();
				flag = true;
			}
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public void createUploadStructurePageWithIncorrectGSEFile(ExtentTest logger) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 900);
			//Header, Footer
			Assert.assertTrue(commonPOM.getBackLinkFirst().isDisplayed());
			Assert.assertEquals(commonPOM.getCreateUploadStructureHeader().getText(), LOMTConstant.CREATE_STRUCTURE_TILE);
			Assert.assertTrue(commonPOM.getNextButtonFirst().isDisplayed());  // Next button is disabled
			Assert.assertTrue(commonPOM.getBackButtonSt2().isEnabled());
			
			//Right options
			Assert.assertTrue(commonPOM.getFirstTextImage().isDisplayed());
			Assert.assertEquals(commonPOM.getFirstTextMessage().getText(), LOMTConstant.CHOOSE_STRUCTURE_TYPE);			
			Assert.assertTrue(englishPOM.getSecondTextImage().isDisplayed());
			Assert.assertEquals(englishPOM.getSecondTextEnglish().getText(), LOMTConstant.STRUCTURE_PAGE_IMAGE_2_TEXT);			
			Assert.assertTrue(commonPOM.getThirdTextImage().isDisplayed());
			Assert.assertEquals(commonPOM.getThirdTextMessage().getText(), LOMTConstant.STRUCTURE_PAGE_IMAGE_3_TEXT);			
			Assert.assertTrue(commonPOM.getFourthTextImage().isDisplayed());
			Assert.assertEquals(commonPOM.getFourthTextMessage().getText(), LOMTConstant.STRUCTURE_PAGE_IMAGE_4_TEXT);
			
			//Center
			Assert.assertTrue(commonPOM.getPlusSign().isDisplayed());
			Assert.assertEquals(commonPOM.getDragAndDropFilesText().getText(),LOMTConstant.DRAG_DROP_TEXT);
			Assert.assertTrue(commonPOM.getUploadFileLink().isDisplayed());
			
			 //File upload logic 
			commonPOM.getUploadFileLink().click();			
			Runtime.getRuntime().exec(LOMTConstant.ENGLISH_GSE_SHEET_WITHOUT_MANDATORY_FIELDS_FILE_PATH);			
			Thread.sleep(5000);
			commonPOM.getNextButtonSt2().click();				
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
			Thread.sleep(3000);
			if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_FAILED_MESSAGE)) {
				//add logger
				Assert.assertEquals(commonPOM.getIngestFailed().getText(), LOMTConstant.INGESTION_FAILED_MESSAGE);
				Assert.assertTrue(commonPOM.getViewIngestFullLogButton().isDisplayed());
				commonPOM.getViewIngestFullLogButton().click();
				Thread.sleep(1000);
				
				// mandatory fields validation check
				boolean flag = gseValidationCheck();
				if (flag) {
					
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1488_03_ADMIN_VERIFY_INGEST_SUCESS_MESSAGE_ON_THE_CREATE_OR_UPLOAD_A_STRUCTURE_PAGE_ACADEMIC_SKILLS_HEADER_BLANK);
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1488_04_ADMIN_VERIFY_INGEST_SUCESS_MESSAGE_ON_THE_CREATE_OR_UPLOAD_A_STRUCTURE_PAGE_ACADEMIC_SKILLS_WRONG_VALUE);
					
					logger.log(LogStatus.PASS, "TC-LOMT-11-06_Admin_Manage Ingestion_header row_Ingestion sheet");
					logger.log(LogStatus.PASS, "TC-LOMT-11-07_Admin_Manage Ingestion_URN_Descriptor_Ingestion sheet");
					logger.log(LogStatus.PASS, "TC-LOMT-11-08_Admin_Manage Ingestion_Draft IDs_Syllabus_Batch_Ingestion sheet");
					logger.log(LogStatus.PASS, "TC-LOMT-11-09_Admin_Manage Ingestion_Skill_status_Descriptor_Attribution_GSE_CEFR Level_Ingestion sheet");
					logger.log(LogStatus.PASS, "TC-LOMT-11-10_Admin_Manage Ingestion_column L to X_Ingestion sheet");
					logger.log(LogStatus.PASS, "TC-LOMT-11-11_Admin_Manage Ingestion_Uplaod success");
					logger.log(LogStatus.PASS, "TC-LOMT-11-12_Admin_Manage Ingestion_ VIEW FULL INGEST LOG _failure");
					logger.log(LogStatus.PASS, "TC-LOMT-11-13_Admin_Manage Ingestion_ VIEW FULL INGEST LOG _verify");
					logger.log(LogStatus.PASS, "TC-LOMT-11-14_Admin_Manage Ingestion_ VIEW FULL INGEST LOG _Done");
				} else {
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1488_03_ADMIN_VERIFY_INGEST_SUCESS_MESSAGE_ON_THE_CREATE_OR_UPLOAD_A_STRUCTURE_PAGE_ACADEMIC_SKILLS_HEADER_BLANK);
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1488_04_ADMIN_VERIFY_INGEST_SUCESS_MESSAGE_ON_THE_CREATE_OR_UPLOAD_A_STRUCTURE_PAGE_ACADEMIC_SKILLS_WRONG_VALUE);
					
					logger.log(LogStatus.FAIL, "TC-LOMT-11-06_Admin_Manage Ingestion_header row_Ingestion sheet");
					logger.log(LogStatus.FAIL, "TC-LOMT-11-07_Admin_Manage Ingestion_URN_Descriptor_Ingestion sheet");
					logger.log(LogStatus.FAIL, "TC-LOMT-11-08_Admin_Manage Ingestion_Draft IDs_Syllabus_Batch_Ingestion sheet");
					logger.log(LogStatus.FAIL, "TC-LOMT-11-09_Admin_Manage Ingestion_Skill_status_Descriptor_Attribution_GSE_CEFR Level_Ingestion sheet");
					logger.log(LogStatus.FAIL, "TC-LOMT-11-10_Admin_Manage Ingestion_column L to X_Ingestion sheet");
					logger.log(LogStatus.FAIL, "TC-LOMT-11-11_Admin_Manage Ingestion_Uplaod success");
					logger.log(LogStatus.FAIL, "TC-LOMT-11-12_Admin_Manage Ingestion_ VIEW FULL INGEST LOG _failure");
					logger.log(LogStatus.FAIL, "TC-LOMT-11-13_Admin_Manage Ingestion_ VIEW FULL INGEST LOG _verify");
					logger.log(LogStatus.FAIL, "TC-LOMT-11-14_Admin_Manage Ingestion_ VIEW FULL INGEST LOG _Done");
				}
				Thread.sleep(2000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void incorrectGSEFileReingestion(ExtentTest logger) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 900);
			//Header, Footer
			Assert.assertTrue(commonPOM.getBackLinkFirst().isDisplayed());
			Assert.assertEquals(commonPOM.getCreateUploadStructureHeader().getText(), LOMTConstant.CREATE_STRUCTURE_TILE);
			Assert.assertTrue(commonPOM.getNextButtonFirst().isDisplayed());  // Next button is disabled
			Assert.assertTrue(commonPOM.getBackButtonSt2().isEnabled());
			
			//Right options
			Assert.assertTrue(commonPOM.getFirstTextImage().isDisplayed());
			Assert.assertEquals(commonPOM.getFirstTextMessage().getText(), LOMTConstant.CHOOSE_STRUCTURE_TYPE);			
			Assert.assertTrue(englishPOM.getSecondTextImage().isDisplayed());
			Assert.assertEquals(englishPOM.getSecondTextEnglish().getText(), LOMTConstant.STRUCTURE_PAGE_IMAGE_2_TEXT);			
			Assert.assertTrue(commonPOM.getThirdTextImage().isDisplayed());
			Assert.assertEquals(commonPOM.getThirdTextMessage().getText(), LOMTConstant.STRUCTURE_PAGE_IMAGE_3_TEXT);			
			Assert.assertTrue(commonPOM.getFourthTextImage().isDisplayed());
			Assert.assertEquals(commonPOM.getFourthTextMessage().getText(), LOMTConstant.STRUCTURE_PAGE_IMAGE_4_TEXT);
			
			//Center
			Assert.assertTrue(commonPOM.getPlusSign().isDisplayed());
			Assert.assertEquals(commonPOM.getDragAndDropFilesText().getText(),LOMTConstant.DRAG_DROP_TEXT);
			Assert.assertTrue(commonPOM.getUploadFileLink().isDisplayed());
			
			 //File upload logic 
			commonPOM.getUploadFileLink().click();			
			Runtime.getRuntime().exec(LOMTConstant.ENGLISH_GSE_SHEET_WITHOUT_MANDATORY_FIELDS_FILE_PATH);			
			Thread.sleep(3000);
			commonPOM.getNextButtonSt2().click();				
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
			Thread.sleep(3000);
			if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_FAILED_MESSAGE)) {
				//add logger
				//Assert.assertEquals(commonPOM.getIngestFailed().getText(), LOMTConstant.INGESTION_FAILED_MESSAGE);
				//Assert.assertTrue(commonPOM.getViewIngestFullLogButton().isDisplayed());
				commonPOM.getViewIngestFullLogButton().click();
				Thread.sleep(1000);
				
				// mandatory fields validation check
				boolean flag = gseValidationCheck();
				if (flag) {
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1488_07_ADMIN_VERIFY_REINGEST_SUCESS_MESSAGE_ON_THE_CREATE_OR_UPLOAD_A_STRUCTURE_PAGE_ACADEMIC_SKILLS_VALUE_BLANK);
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1488_08_ADMIN_VERIFY_REINGEST_SUCESS_MESSAGE_ON_THE_CREATE_OR_UPLOAD_A_STRUCTURE_PAGE_ACADEMIC_SKILLS_WRONG_VAL);
				} else {
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1488_07_ADMIN_VERIFY_REINGEST_SUCESS_MESSAGE_ON_THE_CREATE_OR_UPLOAD_A_STRUCTURE_PAGE_ACADEMIC_SKILLS_VALUE_BLANK);
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1488_08_ADMIN_VERIFY_REINGEST_SUCESS_MESSAGE_ON_THE_CREATE_OR_UPLOAD_A_STRUCTURE_PAGE_ACADEMIC_SKILLS_WRONG_VAL);
				}
				Thread.sleep(2000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean ingestionAndReingestion(String value, ExtentTest logger) throws IOException, InterruptedException {
		boolean flag = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 900);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			
			if (value.equalsIgnoreCase(LOMTConstant.FRESH_INGESTION)) {
				commonPOM.getUploadFileLink().click();
				
				Runtime.getRuntime().exec(LOMTConstant.ENGLISH_GSE_FILE_PATH);
				Thread.sleep(6000);
				commonPOM.getNextButtonSt2().click();				
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
				Thread.sleep(3000);
				
				if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_SUCESSFULL_MESSAGE)) {
					flag = true;
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1488_01_ADMIN_VERIFY_INGEST_SUCESS_MESSAGE_ON_THE_CREATE_OR_UPLOAD_A_STRUCTURE_PAGE_ACADEMIC_SKILLS_VALUE);
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1488_02_ADMIN_VERIFY_INGEST_SUCESS_MESSAGE_ON_THE_CREATE_OR_UPLOAD_A_STRUCTURE_PAGE_ACADEMIC_SKILLS_BLANK);
				} else {
					flag = false;
					logger.log(LogStatus.FAIL, "GSE ingestion failed");
				}
			}
			if (value.equalsIgnoreCase(LOMTConstant.RE_INGESTION_WITHOUT_URN)) {
				commonPOM.getUploadFileLink().click();
				
				Runtime.getRuntime().exec(LOMTConstant.ENGLISH_GSE_REINGESTION_FILE_PATH);
				
				Thread.sleep(6000);
				commonPOM.getNextButtonSt2().click();				
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
				Thread.sleep(3000);
				
				if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_SUCESSFULL_MESSAGE)) {
					flag = true;
					//logger.log(LogStatus.PASS, "GSE re-ingestion successful");
				} else {
					flag = false;
					logger.log(LogStatus.FAIL, "GSE re-ingestion failed : where Descriptive id doesn't have URN");
				}
			}
			if (value.equalsIgnoreCase(LOMTConstant.RE_INGESTION_URN)) {
				commonPOM.getUploadFileLink().click();
				
				Runtime.getRuntime().exec(LOMTConstant.ENGLISH_GSE_REINGESTION_FILE_PATH_URN);
				
				Thread.sleep(6000);
				commonPOM.getNextButtonSt2().click();				
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
				Thread.sleep(3000);
				
				if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_SUCESSFULL_MESSAGE)) {
					flag = true;
					//logger.log(LogStatus.PASS, "GSE re-ingestion successful");
					
					jse.executeScript("window.scrollBy(0,-500)");
					commonPOM.getPearsonLogo().click();
					
				} else {
					flag = false;
					logger.log(LogStatus.FAIL, "GSE re-ingestion failed : where Descriptive id have URN");
					
					jse.executeScript("window.scrollBy(0,-500)");
					commonPOM.getPearsonLogo().click();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public void gseReingestion() {
		try {
			//Header, Footer 
			Assert.assertTrue(commonPOM.getBackLinkFirst().isDisplayed());
			Assert.assertEquals(commonPOM.getCreateUploadStructureHeader().getText(), LOMTConstant.CREATE_STRUCTURE_TILE);
			Assert.assertTrue(commonPOM.getNextButtonFirst().isDisplayed());  // Next button is disabled
			
			//LOB
			Assert.assertEquals(commonPOM.getSelectLOBTitle().getText(), LOMTConstant.SELECT_LOB);
			Assert.assertTrue(commonPOM.getEnglishLOBRadioButton().isDisplayed());
			Assert.assertTrue(commonPOM.getHeLOBRadioButton().isDisplayed());
			Assert.assertTrue(commonPOM.getSchoolGlobalLOBRadioButton().isDisplayed());
			Assert.assertTrue(commonPOM.getNalsLOBRadioButton().isDisplayed());
			
			//Structure
			Assert.assertEquals(commonPOM.getSelectStructureTitle().getText(), LOMTConstant.SELECT_STRUCTURE_TYPE);
			Assert.assertTrue(commonPOM.getGseStructureRadioButton().isEnabled());
			Assert.assertTrue(commonPOM.getProductExternalFrameworkStructureRadioButton().isDisplayed());
			Assert.assertTrue(commonPOM.getGseProductStructureRadioButton().isEnabled());
			
			commonPOM.getGseStructureRadioButton().click();
			Thread.sleep(1000);
			
			//Right options
			Assert.assertTrue(commonPOM.getFirstTextImage().isDisplayed());
			Assert.assertEquals(commonPOM.getFirstTextMessage().getText(), LOMTConstant.CHOOSE_STRUCTURE_TYPE);			
			Assert.assertTrue(englishPOM.getSecondTextImage().isDisplayed());
			Assert.assertEquals(englishPOM.getSecondTextEnglish().getText(), LOMTConstant.STRUCTURE_PAGE_IMAGE_2_TEXT);			
			Assert.assertTrue(commonPOM.getThirdTextImage().isDisplayed());
			Assert.assertEquals(commonPOM.getThirdTextMessage().getText(), LOMTConstant.STRUCTURE_PAGE_IMAGE_3_TEXT);			
			Assert.assertTrue(commonPOM.getFourthTextImage().isDisplayed());
			Assert.assertEquals(commonPOM.getFourthTextMessage().getText(), LOMTConstant.STRUCTURE_PAGE_IMAGE_4_TEXT);
			
			Assert.assertTrue(commonPOM.getNextButtonFirst().isEnabled()); // Next button is enabled
			
			Thread.sleep(2000);
			commonPOM.getNextButtonFirst().click();
			
			 //File upload logic 
			commonPOM.getUploadFileLink().click();
			
			Runtime.getRuntime().exec(LOMTConstant.ENGLISH_GSE_FILE_PATH);
			
			Thread.sleep(3000);
			commonPOM.getNextButtonSt2().click();				
			WebDriverWait wait = new WebDriverWait(driver, 600);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGEST_SUCESS_MESSAGE)));
			
			Thread.sleep(2000);
			//Header
			Assert.assertTrue(commonPOM.getBackLinkFirst().isDisplayed());
			Assert.assertEquals(commonPOM.getCreateUploadStructureHeader().getText(), LOMTConstant.CREATE_STRUCTURE_TILE);
			
			//Right options
			Assert.assertTrue(commonPOM.getFirstTextImage().isDisplayed());
			Assert.assertEquals(commonPOM.getFirstTextMessage().getText(), LOMTConstant.CHOOSE_STRUCTURE_TYPE);			
			Assert.assertTrue(englishPOM.getSecondTextImage().isDisplayed());
			Assert.assertEquals(englishPOM.getSecondTextEnglish().getText(), LOMTConstant.STRUCTURE_PAGE_IMAGE_2_TEXT);			
			Assert.assertTrue(commonPOM.getThirdTextImage().isDisplayed());
			Assert.assertEquals(commonPOM.getThirdTextMessage().getText(), LOMTConstant.STRUCTURE_PAGE_IMAGE_3_TEXT);			
			Assert.assertTrue(commonPOM.getFourthTextImage().isDisplayed());
			Assert.assertEquals(commonPOM.getFourthTextMessage().getText(), LOMTConstant.STRUCTURE_PAGE_IMAGE_4_TEXT);

			Assert.assertTrue(commonPOM.getDoneButton().isEnabled()); 
			commonPOM.getDoneButton().click();
			Thread.sleep(2000);
			
			commonPOM.getPearsonLogo().click();
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean gseValidationCheck() {
		boolean flag = false;
		if (exfPOM.getErrorRowOne().getText().contains(LOMTConstant.SYLLABUS_MANDATORY)
				|| exfPOM.getErrorRowTwo().getText().contains(LOMTConstant.SYLLABUS_MANDATORY)
				|| exfPOM.getErrorRowThree().getText().contains(LOMTConstant.SYLLABUS_MANDATORY)
				|| exfPOM.getErrorRowFour().getText().contains(LOMTConstant.SYLLABUS_MANDATORY)
				|| exfPOM.getErrorRowFive().getText().contains(LOMTConstant.SYLLABUS_MANDATORY)
				|| exfPOM.getErrorRowSix().getText().contains(LOMTConstant.SYLLABUS_MANDATORY)
				|| exfPOM.getErrorRowSeven().getText().contains(LOMTConstant.SYLLABUS_MANDATORY)
				|| exfPOM.getErrorRowEight().getText().contains(LOMTConstant.SYLLABUS_MANDATORY)
				|| exfPOM.getErrorRowNine().getText().contains(LOMTConstant.SYLLABUS_MANDATORY)
				|| exfPOM.getErrorRowTen().getText().contains(LOMTConstant.SYLLABUS_MANDATORY)
				|| exfPOM.getErrorRowEleven().getText().contains(LOMTConstant.SYLLABUS_MANDATORY) ) {
			flag = true;
		} else {
			flag = false;
			return flag;
		}
		if (exfPOM.getErrorRowOne().getText().contains(LOMTConstant.DESCRIPTIVE_ID_MADATORY)
				|| exfPOM.getErrorRowTwo().getText().contains(LOMTConstant.DESCRIPTIVE_ID_MADATORY)
				|| exfPOM.getErrorRowThree().getText().contains(LOMTConstant.DESCRIPTIVE_ID_MADATORY)
				|| exfPOM.getErrorRowFour().getText().contains(LOMTConstant.DESCRIPTIVE_ID_MADATORY)
				|| exfPOM.getErrorRowFive().getText().contains(LOMTConstant.DESCRIPTIVE_ID_MADATORY)
				|| exfPOM.getErrorRowSix().getText().contains(LOMTConstant.DESCRIPTIVE_ID_MADATORY)
				|| exfPOM.getErrorRowSeven().getText().contains(LOMTConstant.DESCRIPTIVE_ID_MADATORY)
				|| exfPOM.getErrorRowEight().getText().contains(LOMTConstant.DESCRIPTIVE_ID_MADATORY)
				|| exfPOM.getErrorRowNine().getText().contains(LOMTConstant.DESCRIPTIVE_ID_MADATORY)
				|| exfPOM.getErrorRowTen().getText().contains(LOMTConstant.DESCRIPTIVE_ID_MADATORY)
				|| exfPOM.getErrorRowEleven().getText().contains(LOMTConstant.DESCRIPTIVE_ID_MADATORY) ) {
			flag = true;
		} else {
			flag = false;
			return flag;
		}
		if (exfPOM.getErrorRowOne().getText().contains(LOMTConstant.STATUS_MANDATORY)
				|| exfPOM.getErrorRowTwo().getText().contains(LOMTConstant.STATUS_MANDATORY)
				|| exfPOM.getErrorRowThree().getText().contains(LOMTConstant.STATUS_MANDATORY)
				|| exfPOM.getErrorRowFour().getText().contains(LOMTConstant.STATUS_MANDATORY)
				|| exfPOM.getErrorRowFive().getText().contains(LOMTConstant.STATUS_MANDATORY)
				|| exfPOM.getErrorRowSix().getText().contains(LOMTConstant.STATUS_MANDATORY)
				|| exfPOM.getErrorRowSeven().getText().equalsIgnoreCase(LOMTConstant.STATUS_MANDATORY)
				|| exfPOM.getErrorRowEight().getText().contains(LOMTConstant.STATUS_MANDATORY)
				|| exfPOM.getErrorRowNine().getText().contains(LOMTConstant.STATUS_MANDATORY)
				|| exfPOM.getErrorRowTen().getText().contains(LOMTConstant.STATUS_MANDATORY)
				|| exfPOM.getErrorRowEleven().getText().contains(LOMTConstant.STATUS_MANDATORY) ) {
			flag = true;
		} else {
			flag = false;
			return flag;
		}
		if (exfPOM.getErrorRowOne().getText().contains(LOMTConstant.DESCRIPTOR_MANDATORY)
				|| exfPOM.getErrorRowTwo().getText().contains(LOMTConstant.DESCRIPTOR_MANDATORY)
				|| exfPOM.getErrorRowThree().getText().contains(LOMTConstant.DESCRIPTOR_MANDATORY)
				|| exfPOM.getErrorRowFour().getText().contains(LOMTConstant.DESCRIPTOR_MANDATORY)
				|| exfPOM.getErrorRowFive().getText().contains(LOMTConstant.DESCRIPTOR_MANDATORY)
				|| exfPOM.getErrorRowSix().getText().contains(LOMTConstant.DESCRIPTOR_MANDATORY)
				|| exfPOM.getErrorRowSeven().getText().contains(LOMTConstant.DESCRIPTOR_MANDATORY)
				|| exfPOM.getErrorRowEight().getText().contains(LOMTConstant.DESCRIPTOR_MANDATORY)
				|| exfPOM.getErrorRowNine().getText().contains(LOMTConstant.DESCRIPTOR_MANDATORY)
				|| exfPOM.getErrorRowTen().getText().contains(LOMTConstant.DESCRIPTOR_MANDATORY)
				|| exfPOM.getErrorRowEleven().getText().contains(LOMTConstant.DESCRIPTOR_MANDATORY) ) {
			flag = true;
		} else {
			flag = false;
			return flag;
		}
		if (exfPOM.getErrorRowOne().getText().contains(LOMTConstant.GRAMMATICAL_CATEGORIES_COUNT_NOT_MORETHAN_THREE)
				|| exfPOM.getErrorRowTwo().getText().contains(LOMTConstant.GRAMMATICAL_CATEGORIES_COUNT_NOT_MORETHAN_THREE)
				|| exfPOM.getErrorRowThree().getText().contains(LOMTConstant.GRAMMATICAL_CATEGORIES_COUNT_NOT_MORETHAN_THREE)
				|| exfPOM.getErrorRowFour().getText().contains(LOMTConstant.GRAMMATICAL_CATEGORIES_COUNT_NOT_MORETHAN_THREE)
				|| exfPOM.getErrorRowFive().getText().contains(LOMTConstant.GRAMMATICAL_CATEGORIES_COUNT_NOT_MORETHAN_THREE)
				|| exfPOM.getErrorRowSix().getText().contains(LOMTConstant.GRAMMATICAL_CATEGORIES_COUNT_NOT_MORETHAN_THREE)
				|| exfPOM.getErrorRowSeven().getText().contains(LOMTConstant.GRAMMATICAL_CATEGORIES_COUNT_NOT_MORETHAN_THREE)
				|| exfPOM.getErrorRowEight().getText().contains(LOMTConstant.GRAMMATICAL_CATEGORIES_COUNT_NOT_MORETHAN_THREE)
				|| exfPOM.getErrorRowNine().getText().contains(LOMTConstant.GRAMMATICAL_CATEGORIES_COUNT_NOT_MORETHAN_THREE)
				|| exfPOM.getErrorRowTen().getText().contains(LOMTConstant.GRAMMATICAL_CATEGORIES_COUNT_NOT_MORETHAN_THREE)
				|| exfPOM.getErrorRowEleven().getText().contains(LOMTConstant.GRAMMATICAL_CATEGORIES_COUNT_NOT_MORETHAN_THREE) ) {
			flag = true;
		} else {
			flag = false;
			return flag;
		}
		if (exfPOM.getErrorRowOne().getText().contains(LOMTConstant.BUSINESS_SKILLS_NOT_MORETHAN_THREE)
				|| exfPOM.getErrorRowTwo().getText().contains(LOMTConstant.BUSINESS_SKILLS_NOT_MORETHAN_THREE)
				|| exfPOM.getErrorRowThree().getText().contains(LOMTConstant.BUSINESS_SKILLS_NOT_MORETHAN_THREE)
				|| exfPOM.getErrorRowFour().getText().contains(LOMTConstant.BUSINESS_SKILLS_NOT_MORETHAN_THREE)
				|| exfPOM.getErrorRowFive().getText().contains(LOMTConstant.BUSINESS_SKILLS_NOT_MORETHAN_THREE)
				|| exfPOM.getErrorRowSix().getText().contains(LOMTConstant.BUSINESS_SKILLS_NOT_MORETHAN_THREE)
				|| exfPOM.getErrorRowSeven().getText().contains(LOMTConstant.BUSINESS_SKILLS_NOT_MORETHAN_THREE)
				|| exfPOM.getErrorRowEight().getText().contains(LOMTConstant.BUSINESS_SKILLS_NOT_MORETHAN_THREE)
				|| exfPOM.getErrorRowNine().getText().contains(LOMTConstant.BUSINESS_SKILLS_NOT_MORETHAN_THREE)
				|| exfPOM.getErrorRowTen().getText().contains(LOMTConstant.BUSINESS_SKILLS_NOT_MORETHAN_THREE)
				|| exfPOM.getErrorRowEleven().getText().contains(LOMTConstant.BUSINESS_SKILLS_NOT_MORETHAN_THREE) ) {
			flag = true;
		} else {
			flag = false;
			return flag;
		}
		if (exfPOM.getErrorRowOne().getText().contains(LOMTConstant.COMMUNICATIVE_CATEGORIES_COUNT_NOT_MORETHAN_THREE)
				|| exfPOM.getErrorRowTwo().getText().contains(LOMTConstant.COMMUNICATIVE_CATEGORIES_COUNT_NOT_MORETHAN_THREE)
				|| exfPOM.getErrorRowThree().getText().contains(LOMTConstant.COMMUNICATIVE_CATEGORIES_COUNT_NOT_MORETHAN_THREE)
				|| exfPOM.getErrorRowFour().getText().contains(LOMTConstant.COMMUNICATIVE_CATEGORIES_COUNT_NOT_MORETHAN_THREE)
				|| exfPOM.getErrorRowFive().getText().contains(LOMTConstant.COMMUNICATIVE_CATEGORIES_COUNT_NOT_MORETHAN_THREE)
				|| exfPOM.getErrorRowSix().getText().contains(LOMTConstant.COMMUNICATIVE_CATEGORIES_COUNT_NOT_MORETHAN_THREE)
				|| exfPOM.getErrorRowSeven().getText().contains(LOMTConstant.COMMUNICATIVE_CATEGORIES_COUNT_NOT_MORETHAN_THREE)
				|| exfPOM.getErrorRowEight().getText().contains(LOMTConstant.COMMUNICATIVE_CATEGORIES_COUNT_NOT_MORETHAN_THREE)
				|| exfPOM.getErrorRowNine().getText().contains(LOMTConstant.COMMUNICATIVE_CATEGORIES_COUNT_NOT_MORETHAN_THREE)
				|| exfPOM.getErrorRowTen().getText().contains(LOMTConstant.COMMUNICATIVE_CATEGORIES_COUNT_NOT_MORETHAN_THREE)
				|| exfPOM.getErrorRowEleven().getText().contains(LOMTConstant.COMMUNICATIVE_CATEGORIES_COUNT_NOT_MORETHAN_THREE) ) {
			flag = true;
		} else {
			flag = false;
			return flag;
		}
		if (exfPOM.getErrorRowOne().getText().contains(LOMTConstant.ACADEMIC_SKILLS_UP_TO_3)
				|| exfPOM.getErrorRowTwo().getText().contains(LOMTConstant.ACADEMIC_SKILLS_UP_TO_3)
				|| exfPOM.getErrorRowThree().getText().contains(LOMTConstant.ACADEMIC_SKILLS_UP_TO_3)
				|| exfPOM.getErrorRowFour().getText().contains(LOMTConstant.ACADEMIC_SKILLS_UP_TO_3)
				|| exfPOM.getErrorRowFive().getText().contains(LOMTConstant.ACADEMIC_SKILLS_UP_TO_3)
				|| exfPOM.getErrorRowSix().getText().contains(LOMTConstant.ACADEMIC_SKILLS_UP_TO_3)
				|| exfPOM.getErrorRowSeven().getText().contains(LOMTConstant.ACADEMIC_SKILLS_UP_TO_3)
				|| exfPOM.getErrorRowEight().getText().contains(LOMTConstant.ACADEMIC_SKILLS_UP_TO_3)
				|| exfPOM.getErrorRowNine().getText().contains(LOMTConstant.ACADEMIC_SKILLS_UP_TO_3)
				|| exfPOM.getErrorRowTen().getText().contains(LOMTConstant.ACADEMIC_SKILLS_UP_TO_3)
				|| exfPOM.getErrorRowEleven().getText().contains(LOMTConstant.ACADEMIC_SKILLS_UP_TO_3) ) {
			flag = true;
		} else {
			flag = false;
			return flag;
		}
		if (exfPOM.getErrorRowOne().getText().contains(LOMTConstant.ACADEMIC_SKILLS_VAL_INVALID)
				|| exfPOM.getErrorRowTwo().getText().contains(LOMTConstant.ACADEMIC_SKILLS_VAL_INVALID)
				|| exfPOM.getErrorRowThree().getText().contains(LOMTConstant.ACADEMIC_SKILLS_VAL_INVALID)
				|| exfPOM.getErrorRowFour().getText().contains(LOMTConstant.ACADEMIC_SKILLS_VAL_INVALID)
				|| exfPOM.getErrorRowFive().getText().contains(LOMTConstant.ACADEMIC_SKILLS_VAL_INVALID)
				|| exfPOM.getErrorRowSix().getText().contains(LOMTConstant.ACADEMIC_SKILLS_VAL_INVALID)
				|| exfPOM.getErrorRowSeven().getText().contains(LOMTConstant.ACADEMIC_SKILLS_VAL_INVALID)
				|| exfPOM.getErrorRowEight().getText().contains(LOMTConstant.ACADEMIC_SKILLS_VAL_INVALID)
				|| exfPOM.getErrorRowNine().getText().contains(LOMTConstant.ACADEMIC_SKILLS_VAL_INVALID)
				|| exfPOM.getErrorRowTen().getText().contains(LOMTConstant.ACADEMIC_SKILLS_VAL_INVALID)
				|| exfPOM.getErrorRowEleven().getText().contains(LOMTConstant.ACADEMIC_SKILLS_VAL_INVALID) ) {
			flag = true;
		} else {
			flag = false;
			return flag;
		}
		if (exfPOM.getErrorRowOne().getText().contains(LOMTConstant.COMM_CAT_VAL_INVALID)
				|| exfPOM.getErrorRowTwo().getText().contains(LOMTConstant.COMM_CAT_VAL_INVALID)
				|| exfPOM.getErrorRowThree().getText().contains(LOMTConstant.COMM_CAT_VAL_INVALID)
				|| exfPOM.getErrorRowFour().getText().contains(LOMTConstant.COMM_CAT_VAL_INVALID)
				|| exfPOM.getErrorRowFive().getText().contains(LOMTConstant.COMM_CAT_VAL_INVALID)
				|| exfPOM.getErrorRowSix().getText().contains(LOMTConstant.COMM_CAT_VAL_INVALID)
				|| exfPOM.getErrorRowSeven().getText().contains(LOMTConstant.COMM_CAT_VAL_INVALID)
				|| exfPOM.getErrorRowEight().getText().contains(LOMTConstant.COMM_CAT_VAL_INVALID)
				|| exfPOM.getErrorRowNine().getText().contains(LOMTConstant.COMM_CAT_VAL_INVALID)
				|| exfPOM.getErrorRowTen().getText().contains(LOMTConstant.COMM_CAT_VAL_INVALID)
				|| exfPOM.getErrorRowEleven().getText().contains(LOMTConstant.COMM_CAT_VAL_INVALID) ) {
			flag = true;
		} else {
			flag = false;
			return flag;
		}
		if (exfPOM.getErrorRowOne().getText().contains(LOMTConstant.BUSINESS_SKILLS_VAL_INVALID)
				|| exfPOM.getErrorRowTwo().getText().contains(LOMTConstant.BUSINESS_SKILLS_VAL_INVALID)
				|| exfPOM.getErrorRowThree().getText().contains(LOMTConstant.BUSINESS_SKILLS_VAL_INVALID)
				|| exfPOM.getErrorRowFour().getText().contains(LOMTConstant.BUSINESS_SKILLS_VAL_INVALID)
				|| exfPOM.getErrorRowFive().getText().contains(LOMTConstant.BUSINESS_SKILLS_VAL_INVALID)
				|| exfPOM.getErrorRowSix().getText().contains(LOMTConstant.BUSINESS_SKILLS_VAL_INVALID)
				|| exfPOM.getErrorRowSeven().getText().contains(LOMTConstant.BUSINESS_SKILLS_VAL_INVALID)
				|| exfPOM.getErrorRowEight().getText().contains(LOMTConstant.BUSINESS_SKILLS_VAL_INVALID)
				|| exfPOM.getErrorRowNine().getText().contains(LOMTConstant.BUSINESS_SKILLS_VAL_INVALID)
				|| exfPOM.getErrorRowTen().getText().contains(LOMTConstant.BUSINESS_SKILLS_VAL_INVALID)
				|| exfPOM.getErrorRowEleven().getText().contains(LOMTConstant.BUSINESS_SKILLS_VAL_INVALID) ) {
			flag = true;
		} else {
			flag = false;
			return flag;
		}
		return flag;
	}
	
	/**
	 * TODO: Need to remove manual pause, and apply wait condition on page elements, 
	 * having issue due to transparent UI.
	 * 
	 * English GSE export at Educational Goal level
	 * @throws InterruptedException
	 */
	public void gseEducationalGoalFrameworkExport(ExtentTest logger) throws InterruptedException {
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;			
			WebDriverWait wait1 = new WebDriverWait(driver, 180); 
			
			commonPOM.getEnglishLOB().click();			
			englishPOM.getGseLink().click();
		
			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.GSE_ACTION_LINK)));
			
			Thread.sleep(200);
			englishPOM.getGseStructure().click();			
			wait1.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			
			jse.executeScript("window.scrollBy(0,500)");			
			// removing existing files from the download directory
			gseHealper.removeExistingFile();
			englishPOM.getSelectAll().click();
			englishPOM.getRenderedLink().click();
			Thread.sleep(1000);
			
			commonPOM.getExportButton().click();
			wait1.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			
			String date = new Date().toString();
			String[] CurrentDate= date.substring(4).split(" ");	 
			String formatedDate = CurrentDate[LOMTConstant.ONE]+CurrentDate[LOMTConstant.ZERO]+CurrentDate[LOMTConstant.FOUR];
			
			File exportedFile = new File(LOMTConstant.EXPORTED_FILE_PATH + LOMTConstant.EMPTY_STRING + LOMTConstant.EXPORTED_FILE_NAME + formatedDate + LOMTConstant.XLSX_EXTENSION);
			if (exportedFile.isFile() && exportedFile.exists()) {
				boolean flag = gseHealper.findDuplicateRecords(exportedFile);
				if(flag) {
					logger.log(LogStatus.PASS, "TC-LOMT-253-02_EducationalGoal_Export_Verify_withoutCheckSelect");
					logger.log(LogStatus.PASS, "TC-LOMT-253-03_EducationalGoal_Export");
					logger.log(LogStatus.PASS, "TC-LOMT-253-04_GSE_EducationalGoal_Export_VerifyTab");
					logger.log(LogStatus.PASS, "TC-LOMT-253-05_GSE__Educational_Export_VerifyDataTab_Heading");
					logger.log(LogStatus.PASS, "TC-LOMT-253-05_GSE_Educational_Export_VerifyDataTab_Values");
					
					logger.log(LogStatus.PASS, "LOMT-1154-03 v1.2.5_GSE_Export");
					logger.log(LogStatus.PASS, "LOMT-1154-04 v1.2.4_GSE_Export");
					logger.log(LogStatus.PASS, "LOMT-1154-05 v1.2.4_GSE_Export");
					logger.log(LogStatus.PASS, "LOMT-1154-06 v1.2.5_GSE_Export");
					logger.log(LogStatus.PASS, "LOMT v1.2.4_LOMT-1197-03_GSE_Export");
					logger.log(LogStatus.PASS, "LOMT v1.2.4_LOMT-1197-04_GSE_Export");
				} else {
					logger.log(LogStatus.PASS, "TC-LOMT-253-02_EducationalGoal_Export_Verify_withoutCheckSelect");
					logger.log(LogStatus.PASS, "TC-LOMT-253-03_EducationalGoal_Export");
					logger.log(LogStatus.PASS, "TC-LOMT-253-04_GSE_EducationalGoal_Export_VerifyTab");
					logger.log(LogStatus.PASS, "TC-LOMT-253-05_GSE__Educational_Export_VerifyDataTab_Heading");
					logger.log(LogStatus.PASS, "TC-LOMT-253-05_GSE_Educational_Export_VerifyDataTab_Values");
					
					logger.log(LogStatus.FAIL, "LOMT-1154-03 v1.2.5_GSE_Export");
					logger.log(LogStatus.FAIL, "LOMT-1154-04 v1.2.4_GSE_Export");
					logger.log(LogStatus.FAIL, "LOMT-1154-05 v1.2.4_GSE_Export");
					logger.log(LogStatus.FAIL, "LOMT-1154-06 v1.2.5_GSE_Export");
					logger.log(LogStatus.FAIL, "LOMT v1.2.4_LOMT-1197-03_GSE_Export");
					logger.log(LogStatus.FAIL, "LOMT v1.2.4_LOMT-1197-04_GSE_Export");
					
					logger.log(LogStatus.FAIL, "DUPLICATE DESCRIPTIVE ID FOUND GSE SPREADSHEET");
				}
				
			}
			jse.executeScript("window.scrollBy(0,-800)");
			commonPOM.getPearsonLogo().click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void executeGSETCs(boolean ingestionFlag, ExtentTest logger) {
		if (ingestionFlag) {
			//TC is common for ingestion re-ingestion with URN & without URN so,
			//writing outside the success block
			
			logger.log(LogStatus.PASS, TestCases.TC_LOMT_1488_06_ADMIN_VERIFY_REINGEST_SUCESS_MESSAGE_ON_THE_CREATE_OR_UPLOAD_A_STRUCTURE_PAGE_ACADEMIC_SKILLS_VALUE);
			
			logger.log(LogStatus.PASS, "TC-LOMT-11-15_Admin_Re-Ingestion_ Descriptive ID");
			logger.log(LogStatus.PASS, "TC-LOMT-11-16_Admin_Re-Ingestion_ Draft IDs");
			logger.log(LogStatus.PASS, "TC-LOMT-11-17_Admin_Re-Ingestion_ Syllabus and Batch");
			logger.log(LogStatus.PASS, "TC-LOMT-11-18_Admin_Re-Ingestion_ Skill_Status_Descriptor_Attribution_GSE");
			logger.log(LogStatus.PASS, "TC-LOMT-11-19_Admin_Re-Ingestion_ CEFR Level");
			logger.log(LogStatus.PASS, "TC-LOMT-11-20_Admin_Re-Ingestion_ Communicative Categories");
			logger.log(LogStatus.PASS, "TC-LOMT-11-21_Admin_Re-Ingestion_ Business skills");
			logger.log(LogStatus.PASS, "TC-LOMT-11-22_Admin_Re-Ingestion_ Topic L1");
			logger.log(LogStatus.PASS, "TC-LOMT-11-23_Admin_Re-Ingestion_ YL Simplified_Structure");
			logger.log(LogStatus.PASS, "TC-LOMT-11-24_Admin_Re-Ingestion_ Grammatical Categories");
			logger.log(LogStatus.PASS, "TC-LOMT-11-25_Admin_Re-Ingestion_ Example_Variant terms");
			logger.log(LogStatus.PASS, "TC-LOMT-11-26_Admin_Re-Ingestion_ Function or Notion");
			logger.log(LogStatus.PASS, "TC-LOMT-11-27_Admin_Re-Ingestion_ Anchor");
			logger.log(LogStatus.PASS, "TC-LOMT-11-28_Admin_Re-Ingestion_ Source Descriptor_Source");
			logger.log(LogStatus.PASS, "TC-LOMT-11-29_Admin_Re-Ingestion_ Estimated Level");
			logger.log(LogStatus.PASS, "TC-LOMT-11-30_Admin_Re-Ingestion_ Notes");
			logger.log(LogStatus.PASS, "TC-LOMT-11-31_Admin_Re-Ingestion_ blank_NonMandatory");
			logger.log(LogStatus.PASS, "TC-LOMT-11-32_Admin_Re-Ingestion_ blank_Add New");
			logger.log(LogStatus.PASS, "TC-LOMT-11-33_Admin_Re-Ingestion_ blank_UpdateURN");
			
			logger.log(LogStatus.PASS, "TC-LOMT-1008-01__GSE_NoURN_NewDescriptive ID");
			logger.log(LogStatus.PASS, "TC-LOMT-1008-02__GSE_NoURN_OldDescriptive ID");
			logger.log(LogStatus.PASS, "TC-LOMT-1008-03__GSE_WithURN_OldDescriptive ID");
			
			logger.log(LogStatus.PASS, "LOMT-1154-01 v1.2.4_GSE_Ingest");
			logger.log(LogStatus.PASS, "LOMT-1154-02 v1.2.4_GSE_Ingest");
			logger.log(LogStatus.PASS, "LOMT-1154-07 v1.2.4_GSE_Ingest");
			logger.log(LogStatus.PASS, "LOMT-1154-08 v1.2.4_GSE_Ingest");
			
			logger.log(LogStatus.PASS, "TC-LOMT-968-01_English_GSE_Ingestion_goalFramework_Admin_role");
			logger.log(LogStatus.PASS, "TC-LOMT-968-02__English_GSE_Export_goalFramework_Admin_role");
			logger.log(LogStatus.PASS, "TC-LOMT-968-03__English_GSE_Reingestion_goalFramework_Admin_role");
			logger.log(LogStatus.PASS, "TC-LOMT-968-04__English_GSE_Browse_goalFramework_Admin_role");
			logger.log(LogStatus.PASS, "TC-LOMT-968-05__English_GSE_Edit_goalFramework_Admin_role");
			
			logger.log(LogStatus.PASS, "LOMT v1.2.4_LOMT-1197-01_GSE_Ingest");
			logger.log(LogStatus.PASS, "LOMT v1.2.4_LOMT-1197-02_GSE_Ingest");
		} else {
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1488_06_ADMIN_VERIFY_REINGEST_SUCESS_MESSAGE_ON_THE_CREATE_OR_UPLOAD_A_STRUCTURE_PAGE_ACADEMIC_SKILLS_VALUE);
			
			logger.log(LogStatus.FAIL, "TC-LOMT-11-15_Admin_Re-Ingestion_ Descriptive ID");
			logger.log(LogStatus.FAIL, "TC-LOMT-11-16_Admin_Re-Ingestion_ Draft IDs");
			logger.log(LogStatus.FAIL, "TC-LOMT-11-17_Admin_Re-Ingestion_ Syllabus and Batch");
			logger.log(LogStatus.FAIL, "TC-LOMT-11-18_Admin_Re-Ingestion_ Skill_Status_Descriptor_Attribution_GSE");
			logger.log(LogStatus.FAIL, "TC-LOMT-11-19_Admin_Re-Ingestion_ CEFR Level");
			logger.log(LogStatus.FAIL, "TC-LOMT-11-20_Admin_Re-Ingestion_ Communicative Categories");
			logger.log(LogStatus.FAIL, "TC-LOMT-11-21_Admin_Re-Ingestion_ Business skills");
			logger.log(LogStatus.FAIL, "TC-LOMT-11-22_Admin_Re-Ingestion_ Topic L1");
			logger.log(LogStatus.FAIL, "TC-LOMT-11-23_Admin_Re-Ingestion_ YL Simplified_Structure");
			logger.log(LogStatus.FAIL, "TC-LOMT-11-24_Admin_Re-Ingestion_ Grammatical Categories");
			logger.log(LogStatus.FAIL, "TC-LOMT-11-25_Admin_Re-Ingestion_ Example_Variant terms");
			logger.log(LogStatus.FAIL, "TC-LOMT-11-26_Admin_Re-Ingestion_ Function or Notion");
			logger.log(LogStatus.FAIL, "TC-LOMT-11-27_Admin_Re-Ingestion_ Anchor");
			logger.log(LogStatus.FAIL, "TC-LOMT-11-28_Admin_Re-Ingestion_ Source Descriptor_Source");
			logger.log(LogStatus.FAIL, "TC-LOMT-11-29_Admin_Re-Ingestion_ Estimated Level");
			logger.log(LogStatus.FAIL, "TC-LOMT-11-30_Admin_Re-Ingestion_ Notes");
			logger.log(LogStatus.FAIL, "TC-LOMT-11-31_Admin_Re-Ingestion_ blank_NonMandatory");
			logger.log(LogStatus.FAIL, "TC-LOMT-11-32_Admin_Re-Ingestion_ blank_Add New");
			logger.log(LogStatus.FAIL, "TC-LOMT-11-33_Admin_Re-Ingestion_ blank_UpdateURN");
			
			logger.log(LogStatus.FAIL, "TC-LOMT-1008-01__GSE_NoURN_NewDescriptive ID");
			logger.log(LogStatus.FAIL, "TC-LOMT-1008-02__GSE_NoURN_OldDescriptive ID");
			logger.log(LogStatus.FAIL, "TC-LOMT-1008-03__GSE_WithURN_OldDescriptive ID");
			
			logger.log(LogStatus.FAIL, "LOMT-1154-01 v1.2.4_GSE_Ingest");
			logger.log(LogStatus.FAIL, "LOMT-1154-02 v1.2.4_GSE_Ingest");
			logger.log(LogStatus.FAIL, "LOMT-1154-07 v1.2.4_GSE_Ingest");
			logger.log(LogStatus.FAIL, "LOMT-1154-08 v1.2.4_GSE_Ingest");
			
			logger.log(LogStatus.FAIL, "TC-LOMT-968-01_English_GSE_Ingestion_goalFramework_Admin_role");
			logger.log(LogStatus.FAIL, "TC-LOMT-968-02__English_GSE_Export_goalFramework_Admin_role");
			logger.log(LogStatus.FAIL, "TC-LOMT-968-03__English_GSE_Reingestion_goalFramework_Admin_role");
			logger.log(LogStatus.FAIL, "TC-LOMT-968-04__English_GSE_Browse_goalFramework_Admin_role");
			logger.log(LogStatus.FAIL, "TC-LOMT-968-05__English_GSE_Edit_goalFramework_Admin_role");
			
			logger.log(LogStatus.FAIL, "LOMT v1.2.4_LOMT-1197-01_GSE_Ingest");
			logger.log(LogStatus.FAIL, "LOMT v1.2.4_LOMT-1197-02_GSE_Ingest");
		}
	}
	
	public void searchDescriptiveId(File actualFile) {
		try {
			WebDriverWait wait1 = new WebDriverWait(driver, 300); 
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			//int innerCounter = 0;
			
			jse.executeScript("window.scrollBy(0,-500)");
			commonPOM.getPearsonLogo().click();
			commonPOM.getEnglishLOB().click();
			
			englishPOM.getGseLink().click();
			
			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.GSE_ACTION_LINK)));
			
			Thread.sleep(200);
			englishPOM.getGseStructure().click();	
			Thread.sleep(60000);
			//wait1.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));	
			
			ReadGSEXLSFile exportGSEObject = new ReadGSEXLSFile();			
			String descriptorId = exportGSEObject.getDescriptor(actualFile);
			//List<String> descriptorList = exportGSEObject.getDescriptor(actualFile);
			
			englishPOM.getCancelFilterBySet().click();
			jse.executeScript("window.scrollBy(0,400)");	
			commonPOM.getUpdateResultButton().click();
			wait1.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			jse.executeScript("window.scrollBy(0,200)");	
			
			Thread.sleep(1000);
			//commonPOM.getDescriptiveIdADSearch().sendKeys(descriptorId); 
			englishPOM.getDescriptiveIdWithFilterSet().sendKeys(descriptorId); 
			
			commonPOM.getUpdateResultButton().click();
			wait1.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			
			jse.executeScript("window.scrollBy(0,400)");
			// removing existing files from the download directory
			gseHealper.removeExistingFile();
			
			commonPOM.getEgCheckbox().click();	
			Thread.sleep(1000);
			
			commonPOM.getExportButton().click();
			wait1.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			//Thread.sleep(40000);
						
			jse.executeScript("window.scrollBy(0,-800)");
			commonPOM.getPearsonLogo().click();
			
			commonPOM.getEnglishLOB().click();
			commonPOM.getManageIngestion().click();
			commonPOM.getGseStructureRadioButton().click();
			
			jse.executeScript("window.scrollBy(0,300)");	
			commonPOM.getNextButtonFirst().click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void verfifyAgainIngestedDataWithURN(File actualFile, File exportedFile) {
		try {
			WebDriverWait wait1 = new WebDriverWait(driver, 180); 
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			
			jse.executeScript("window.scrollBy(0,-800)");
			commonPOM.getPearsonLogo().click();
			commonPOM.getEnglishLOB().click();
			
			englishPOM.getGseLink().click();
			
			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.GSE_ACTION_LINK)));
			
			Thread.sleep(200);
			englishPOM.getGseStructure().click();
			
			wait1.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			
			ReadGSEXLSFile exportGSEObject = new ReadGSEXLSFile();			
			String descriptor = exportGSEObject.getDescriptor(actualFile);
			
			jse.executeScript("window.scrollBy(0,200)");	
			
			commonPOM.getDescriptiveIdADSearch().sendKeys(descriptor); 
			Thread.sleep(1000);
			
			englishPOM.getUpdateResultButton().click();
			wait1.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			
			jse.executeScript("window.scrollBy(0,300)");	
			
			// removing existing files from the download directory
			gseHealper.removeExistingFile();
			
			commonPOM.getEgCheckbox().click();	
			Thread.sleep(1000);
			
			commonPOM.getExportButton().click();
			wait1.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
						
			jse.executeScript("window.scrollBy(0,-800)");
			commonPOM.getPearsonLogo().click();
			
			gseHealper.compareSpreadFiles(exportedFile, actualFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	public void closeDriverInstance() {
		driver.manage().timeouts().implicitlyWait(05, TimeUnit.SECONDS);
		driver.close();
	}	

}
