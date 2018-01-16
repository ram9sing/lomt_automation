package lomt.pearson.common;

import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import lomt.pearson.constant.HEConstant;
import lomt.pearson.constant.LOMTConstant;
import lomt.pearson.constant.SchoolConstant;
import lomt.pearson.page_object.CommonPOM;
import lomt.pearson.page_object.ExternalFrameworkPOM;
import lomt.pearson.page_object.HEPom;
import lomt.pearson.page_object.IntermediaryPOM;
import lomt.pearson.page_object.Login;
import lomt.pearson.page_object.NALSPom;
import lomt.pearson.page_object.SGPom;
import lomt.pearson.page_object.SchoolPOM;

public class Common extends BaseClass {
	
	private String environment = LoadPropertiesFile.getPropertiesValues(LOMTConstant.LOMT_ENVIRONMENT);
	private String userName = LoadPropertiesFile.getPropertiesValues(LOMTConstant.USER_NAME);
	
	//private String userName = LoadPropertiesFile.getPropertiesValues(LOMTConstant.USER_NAME_TEST);
	
	private String pwd = LoadPropertiesFile.getPropertiesValues(LOMTConstant.PASSWORD);
	
	private WebDriver driver;

	private Login login = null;
	private CommonPOM commonPOM = null;
	private HEPom hePom = null;
	private NALSPom nalsPom  = null;
	private SGPom sgPom = null;
	private SchoolPOM schoolPOM = null;
	private ExternalFrameworkPOM exfPOM = null;
	private IntermediaryPOM intermediaryPOM = null;
	
	public void getDriverInstance(WebDriver driver) {
		this.driver = initialiseChromeDriver();
	}
	
	public void openBrowser() {
		getDriverInstance(driver);
		driver.manage().window().maximize();
		driver.get(environment);
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);

		commonPOM = new CommonPOM(driver);
		hePom = new HEPom(driver);
		nalsPom = new NALSPom(driver);
		sgPom = new SGPom(driver);
		schoolPOM = new SchoolPOM(driver);
		exfPOM = new ExternalFrameworkPOM(driver);
		intermediaryPOM = new IntermediaryPOM(driver);
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
		}
	}
	
	public void englishPreviousAndBackOptionVerification(ExtentTest logger) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 900);
		try {
			commonPOM.getEnglishLOB().click();
			commonPOM.getManageIngestion().click();
			
			//GSE Structure
			commonPOM.getGseStructureRadioButton().click();
			jse.executeScript("window.scrollBy(0,500)");
			commonPOM.getNextButtonFirst().click();
			
			commonPOM.getUploadFileLink().click();			
			Runtime.getRuntime().exec(LOMTConstant.ENGLISH_GSE_SHEET_WITHOUT_MANDATORY_FIELDS_FILE_PATH);			
			Thread.sleep(10000);
			commonPOM.getNextButtonSt2().click();
			jse.executeScript("window.scrollBy(0,-100)");
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
			Thread.sleep(3000);
			if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_FAILED_MESSAGE)) {
				jse.executeScript("window.scrollBy(0,-500)");
				assertTrue(commonPOM.getBackLinkFirst().isEnabled());
				
				jse.executeScript("window.scrollBy(0,500)");
				assertTrue(commonPOM.getPreviousButton().isEnabled());
				commonPOM.getPreviousButton().click();
				
				assertTrue(commonPOM.getXlsFileExtention().getText().contains(".xlsx"));				
				
				logger.log(LogStatus.PASS, "TC_LOMT-1584-01_Admin_User_English_GSE_Ingestion_Select_Previous");
				logger.log(LogStatus.PASS, "TC_LOMT-1584-13_Admin_User_English_GSE_Ingestion_Select_back");
				
				jse.executeScript("window.scrollBy(0,-500)");
				commonPOM.getBackLinkFirst().click();
			} else {
				logger.log(LogStatus.FAIL, "TC_LOMT-1584-01_Admin_User_English_GSE_Ingestion_Select_Previous");
				logger.log(LogStatus.FAIL, "TC_LOMT-1584-13_Admin_User_English_GSE_Ingestion_Select_back");
				
				jse.executeScript("window.scrollBy(0,-500)");
				commonPOM.getBackLinkFirst().click();
				Thread.sleep(1000);
			}
			
			//External Framework Structure
			commonPOM.getSgEXFStructureRadioButton().click();
			jse.executeScript("window.scrollBy(0,400)");
			commonPOM.getNextButtonFirst().click(); 
			
			jse.executeScript("window.scrollBy(0,500)");
			commonPOM.getNextButton().click();
			
			commonPOM.getUploadFileLink().click();
			
			Runtime.getRuntime().exec(LOMTConstant.EXF_WITHOUT_MANDATORY_FIELDS_XLS_PATH);
			
			Thread.sleep(10000);
			commonPOM.getNextButtonSt2().click();
			jse.executeScript("window.scrollBy(0,-100)");
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
			Thread.sleep(3000);
			if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_FAILED_MESSAGE)) {
				jse.executeScript("window.scrollBy(0,-500)");
				assertTrue(commonPOM.getBackLinkFirst().isEnabled());
				
				jse.executeScript("window.scrollBy(0,500)");
				assertTrue(commonPOM.getPreviousButton().isEnabled());
				commonPOM.getPreviousButton().click();
				
				assertTrue(commonPOM.getXlsFileExtention().getText().contains(".xlsx") || commonPOM.getXlsFileExtention().getText().contains(".xls"));				
				
				logger.log(LogStatus.PASS, "TC_LOMT-1584-02_Admin_User_English_External_Framework_Ingestion_Select_Previous");
				logger.log(LogStatus.PASS, "TC_LOMT-1584-14_Admin_User_English_External_Framework_Ingestion_Select_Back ");
				
				jse.executeScript("window.scrollBy(0,-500)");
				commonPOM.getBackLinkFirst().click();
			} else {
				logger.log(LogStatus.FAIL, "TC_LOMT-1584-02_Admin_User_English_External_Framework_Ingestion_Select_Previous");
				logger.log(LogStatus.FAIL, "TC_LOMT-1584-14_Admin_User_English_External_Framework_Ingestion_Select_Back ");
				
				jse.executeScript("window.scrollBy(0,-500)");
				commonPOM.getBackLinkFirst().click();
				Thread.sleep(1000);
			}
			
			//Product Structure
			commonPOM.getGseProductStructureRadioButton().click();
			jse.executeScript("window.scrollBy(0,500)");
			commonPOM.getNextButtonFirst().click();
			
			jse.executeScript("window.scrollBy(0,200)");
			commonPOM.getNextButton().click();
			
			commonPOM.getUploadFileLink().click();
			
			Runtime.getRuntime().exec(LOMTConstant.PRODUCT_FILE_PATH_ENGLISH_11);
			Thread.sleep(10000);
			commonPOM.getNextButtonSt2().click();
			jse.executeScript("window.scrollBy(0,-100)");
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
			Thread.sleep(3000);
			if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_FAILED_MESSAGE)) {
				jse.executeScript("window.scrollBy(0,-500)");
				assertTrue(commonPOM.getBackLinkFirst().isEnabled());
				
				jse.executeScript("window.scrollBy(0,500)");
				assertTrue(commonPOM.getPreviousButton().isEnabled());
				commonPOM.getPreviousButton().click();
				
				assertTrue(commonPOM.getXlsFileExtention().getText().contains(".xlsx") || commonPOM.getXlsFileExtention().getText().contains(".xls"));				
				
				logger.log(LogStatus.PASS, "TC_LOMT-1584-03_Admin_User_English_Product_Ingestion_Select_Previous");
				logger.log(LogStatus.PASS, "TC_LOMT-1584-15_Admin_User_English_Product_Ingestion_Select_Back");
				
				jse.executeScript("window.scrollBy(0,-500)");
				commonPOM.getPearsonLogo().click();
				Thread.sleep(1000);
			} else {
				logger.log(LogStatus.FAIL, "TC_LOMT-1584-03_Admin_User_English_Product_Ingestion_Select_Previous");
				logger.log(LogStatus.FAIL, "TC_LOMT-1584-15_Admin_User_English_Product_Ingestion_Select_Back");
				
				jse.executeScript("window.scrollBy(0,-500)");
				commonPOM.getPearsonLogo().click();
				Thread.sleep(1000);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void hePreviousAndBackOptionVerification(ExtentTest logger) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 900);
		try {
			//External Framework 
			Thread.sleep(2000);
			commonPOM.getHeLOB().click();
			commonPOM.getManageIngestion().click();
			
			commonPOM.getHeLOBRadioButton().click();
			exfPOM.getExternalFrameworkStructureHE().click();
			
			jse.executeScript("window.scrollBy(0,500)");
			commonPOM.getNextButtonFirst().click(); 
			
			jse.executeScript("window.scrollBy(0,500)");
			commonPOM.getNextButton().click();
			
			commonPOM.getUploadFileLink().click();
			
			Runtime.getRuntime().exec(LOMTConstant.EXF_WITHOUT_MANDATORY_FIELDS_XLS_PATH);
			Thread.sleep(5000);
			commonPOM.getNextButtonSt2().click();
			jse.executeScript("window.scrollBy(0,-100)");
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
			Thread.sleep(3000);
			if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_FAILED_MESSAGE)) {
				jse.executeScript("window.scrollBy(0,-500)");
				assertTrue(commonPOM.getBackLinkFirst().isEnabled());
				
				jse.executeScript("window.scrollBy(0,500)");
				assertTrue(commonPOM.getPreviousButton().isEnabled());
				commonPOM.getPreviousButton().click();
				
				assertTrue(commonPOM.getXlsFileExtention().getText().contains(".xlsx") || commonPOM.getXlsFileExtention().getText().contains(".xls"));				
				
				logger.log(LogStatus.PASS, "TC_LOMT-1584-04_Admin_User_Higher_Education_External_Framework_Ingestion_Select_Previous");
				logger.log(LogStatus.PASS, "TC_LOMT-1584-16_Admin_User_Higher_Education_External_Framework_Ingestion_Select_Back");
				
				jse.executeScript("window.scrollBy(0,-500)");
				commonPOM.getBackLinkFirst().click();
			} else {
				logger.log(LogStatus.FAIL, "TC_LOMT-1584-04_Admin_User_Higher_Education_External_Framework_Ingestion_Select_Previous");
				logger.log(LogStatus.FAIL, "TC_LOMT-1584-16_Admin_User_Higher_Education_External_Framework_Ingestion_Select_Back");
				
				jse.executeScript("window.scrollBy(0,-500)");
				commonPOM.getBackLinkFirst().click();
				Thread.sleep(1000);
			}
			
			//Product
			commonPOM.getHeLOBRadioButton().click();
			commonPOM.getProductRadioButton().click();
			
			jse.executeScript("window.scrollBy(0,500)");
			commonPOM.getNextButtonFirst().click();
			
			jse.executeScript("window.scrollBy(0,500)");
			
			commonPOM.getNextButton().click();
			
			commonPOM.getUploadFileLink().click();
			Runtime.getRuntime().exec(LOMTConstant.HE_PRODUCT_TOC_VALIDATION);
			
			Thread.sleep(10000);
			commonPOM.getNextButtonSt2().click();
			jse.executeScript("window.scrollBy(0,-100)");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
			Thread.sleep(3000);
			if (commonPOM.getWaitForIngestionStatusText().getText().contains(LOMTConstant.INGESTION_FAILED_MESSAGE)) {
				jse.executeScript("window.scrollBy(0,-500)");
				assertTrue(commonPOM.getBackLinkFirst().isEnabled());
				
				jse.executeScript("window.scrollBy(0,500)");
				assertTrue(commonPOM.getPreviousButton().isEnabled());
				commonPOM.getPreviousButton().click();
				
				assertTrue(commonPOM.getXlsFileExtention().getText().contains(".xlsx") || commonPOM.getXlsFileExtention().getText().contains(".xls"));				
				
				logger.log(LogStatus.PASS, "TC_LOMT-1584-05_Admin_User_Higher_Education_Product_Ingestion_Select_Previous");
				logger.log(LogStatus.PASS, "TC_LOMT-1584-17_Admin_User_Higher_Education_Product_Ingestion_Select_Back");
				
				jse.executeScript("window.scrollBy(0,-500)");
				commonPOM.getBackLinkFirst().click();
			} else {
				logger.log(LogStatus.FAIL, "TC_LOMT-1584-05_Admin_User_Higher_Education_Product_Ingestion_Select_Previous");
				logger.log(LogStatus.FAIL, "TC_LOMT-1584-17_Admin_User_Higher_Education_Product_Ingestion_Select_Back");
				
				jse.executeScript("window.scrollBy(0,-500)");
				commonPOM.getBackLinkFirst().click();
				Thread.sleep(1000);
			}
			
			//Educational Objective
			commonPOM.getHeLOBRadioButton().click();
			commonPOM.getEducationalObjRadioButton().click();				
			Thread.sleep(1000);
			jse.executeScript("window.scrollBy(0,200)");
			
			commonPOM.getNextButtonFirst().click();
			
			hePom.getLearningTitleInputText().sendKeys("Test EducationalObjective");
			Thread.sleep(1000);
			
			//DOMAIN SELECTION
			hePom.getDomainNameDropDown().click();
			Thread.sleep(4000);
			List<WebElement> domainList = hePom.getDomainList();
			int domainLength = domainList.size();
			if (domainLength > 0) {
				for (int i = 0; i <= domainLength; i++) {
					WebElement element = domainList.get(i);
					// TODO : apply assertion for all the fields
					if (element.getText().equalsIgnoreCase(HEConstant.BUSINESS_STATISTICES)) {
						element.click();
						break;
					}
				}
			} else {
				Assert.assertFalse((domainLength == 0), "HE Domain dropdown size is zero");
			}
			//STATUS SELECTION
			hePom.getStatusDropDown().click();
			Thread.sleep(4000);
			List<WebElement> statusList = hePom.getStatusDropDownList();
			int statusLength = statusList.size();
			if (statusLength > 0) {
				for (int i = 0; i < statusLength; i++) {
					WebElement element = domainList.get(i);
					System.out.println("Status value : " + element.getText());
					if (element.getText().equalsIgnoreCase(HEConstant.DRAFT)) {
						element.click();
						break;
					}
				}
			} else {
				Assert.assertFalse((domainLength == 0), "HE Status dropdown size is zero");
			}
			//Objective Hierarchy Set SELECTION-NON MANDATORY FIELDS
			//TODO
			jse.executeScript("window.scrollBy(0,300)");
			
			hePom.getNextButton().click();
			Thread.sleep(2000);
			
			commonPOM.getUploadFileLink().click();
			
			Runtime.getRuntime().exec(LOMTConstant.HE_INGESTION_VALIDATION_MISSED_FILE_PATH);
			Thread.sleep(10000);
			
			commonPOM.getNextButtonSt2().click();
			
			jse.executeScript("window.scrollBy(0, -300)");
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
			Thread.sleep(2000);
			
			if (commonPOM.getWaitForIngestionStatusText().getText().contains(LOMTConstant.INGESTION_FAILED_MESSAGE)) {
				jse.executeScript("window.scrollBy(0,-500)");
				assertTrue(commonPOM.getBackLinkFirst().isEnabled());
				
				jse.executeScript("window.scrollBy(0,500)");
				assertTrue(commonPOM.getPreviousButton().isEnabled());
				commonPOM.getPreviousButton().click();
				
				assertTrue(commonPOM.getXlsFileExtention().getText().contains(".xlsx") || commonPOM.getXlsFileExtention().getText().contains(".xls"));				
				
				logger.log(LogStatus.PASS, "TC_LOMT-1584-06_Admin_User_Higher_Education_Education_Objective_Ingestion_Select_Previous");
				logger.log(LogStatus.PASS, "TC_LOMT-1584-18_Admin_User_Higher_Education_Education_Objective_Ingestion_Select_Back");
				
				jse.executeScript("window.scrollBy(0,-500)");
				commonPOM.getPearsonLogo().click();
				Thread.sleep(1000);
			} else {
				logger.log(LogStatus.FAIL, "TC_LOMT-1584-06_Admin_User_Higher_Education_Education_Objective_Ingestion_Select_Previous");
				logger.log(LogStatus.FAIL, "TC_LOMT-1584-18_Admin_User_Higher_Education_Education_Objective_Ingestion_Select_Back");
				
				jse.executeScript("window.scrollBy(0,-500)");
				commonPOM.getPearsonLogo().click();
				Thread.sleep(1000);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void schoolGlobalPreviousAndBackOptionVerification(ExtentTest logger) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 300);
		try {
			Thread.sleep(2000);
			commonPOM.getSchoolGlobalLOB().click();
			
			//Curriculum Standard (ab.xml)
			commonPOM.getManageIngestion().click();
			commonPOM.getSchoolGlobalLOBRadioButton().click();
			commonPOM.getCurriculumStandardStructure().click();
			jse.executeScript("window.scrollBy(0,500)");
			
			commonPOM.getNextButtonFirst().click(); 
			
			Thread.sleep(4000);
			//SUBJECT Selection
			schoolPOM.getSubjectDropdown().click();
			Thread.sleep(6000);
			List<WebElement> subjectList = schoolPOM.getSubjectDropdownList();
			int subjectLength = subjectList.size();
			if (subjectLength > 0) {
				for (int i = 0; i <= subjectLength; i++) {
					WebElement element = subjectList.get(i);
					if (element.getText()!= null) {
						element.click();
						break;
					}
				}
			} else {
				Assert.assertFalse((subjectLength == 0), LOMTConstant.SUBJECT+LOMTConstant.DROPDOWN_SIZE_NOT_ZERO);
			}
			
			//Authority Selection, pick authority name, 
			schoolPOM.getAuthorityDropdown().click();
			Thread.sleep(6000);
			List<WebElement> authorityList = schoolPOM.getAuthorityDropdownList();
			int aLength = authorityList.size();
			if (aLength > 0) {
				for (int i = 0; i <= aLength; i++) {
					WebElement element = authorityList.get(i);
					if (element.getText()!= null) {
						element.click();
						break;
					}
				}
			} else {
				Assert.assertFalse((subjectLength == 0), "Authority"+LOMTConstant.DROPDOWN_SIZE_NOT_ZERO);
			}
			
			//Curriculum Selection
			schoolPOM.getCurriculumSetDropdown().click();
			Thread.sleep(6000);
			List<WebElement> curriculumSetList = schoolPOM.getCurriculumSetDropdownList();
			int csLength = curriculumSetList.size();
			if (csLength > 0) {
				for (int i = 0; i <= csLength; i++) {
					WebElement element = curriculumSetList.get(i);
					if (element.getText()!= null) {
						element.click();
						break;
					}
				}
			} else {
				Assert.assertFalse((subjectLength == 0), "Curriculum Set"+LOMTConstant.DROPDOWN_SIZE_NOT_ZERO);
			}
			
			//Adopted Year Selection
			schoolPOM.getAdoptedYear().sendKeys("2017");
			
			jse.executeScript("window.scrollBy(0,500)");
			
			commonPOM.getNextButton().click();
			
			commonPOM.getUploadFileLink().click();
			
			Runtime.getRuntime().exec(SchoolConstant.SCHOOL_CURRICULUM_WRONG_FILE_PATH);
			
			jse.executeScript("window.scrollBy(0, 300)");
			Thread.sleep(4000);		
			commonPOM.getNextButtonSt2().click();
			jse.executeScript("window.scrollBy(0, -400)");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
			Thread.sleep(3000);
			if (commonPOM.getWaitForIngestionStatusText().getText().contains(LOMTConstant.INGESTION_FAILED_MESSAGE)) {
				jse.executeScript("window.scrollBy(0,-500)");
				assertTrue(commonPOM.getBackLinkFirst().isEnabled());
				
				jse.executeScript("window.scrollBy(0,500)");
				assertTrue(commonPOM.getPreviousButton().isEnabled());
				commonPOM.getPreviousButton().click();
				
				assertTrue(commonPOM.getXlsFileExtention().getText().contains(".xml") || commonPOM.getXlsFileExtention().getText().contains(".xml"));				
				
				logger.log(LogStatus.PASS, "TC_LOMT-1584-07_Admin_User_School_Global_Curriculum_Standard(ab.xml)_Ingestion_Select_Previous");
				logger.log(LogStatus.PASS, "TC_LOMT-1584-19_Admin_User_School_Global_Curriculum_Standard(ab.xml)_Ingestion_Select_Back");
				
				jse.executeScript("window.scrollBy(0,-500)");
				commonPOM.getBackLinkFirst().click();
			} else {
				logger.log(LogStatus.FAIL, "TC_LOMT-1584-07_Admin_User_School_Global_Curriculum_Standard(ab.xml)_Ingestion_Select_Previous");
				logger.log(LogStatus.FAIL, "TC_LOMT-1584-19_Admin_User_School_Global_Curriculum_Standard(ab.xml)_Ingestion_Select_Back");
				
				jse.executeScript("window.scrollBy(0,-500)");
				commonPOM.getBackLinkFirst().click();
				Thread.sleep(1000);
			}
			
			//Curriculum Standard (custom)
			commonPOM.getSchoolGlobalLOBRadioButton().click();
			commonPOM.getSgEXFStructureRadioButton().click();
			jse.executeScript("window.scrollBy(0,500)");
			
			commonPOM.getNextButtonFirst().click(); 
			
			jse.executeScript("window.scrollBy(0,500)");
			commonPOM.getNextButton().click();
			
			commonPOM.getUploadFileLink().click();
			
			Runtime.getRuntime().exec(LOMTConstant.EXF_WITHOUT_MANDATORY_FIELDS_XLS_PATH);
			
			Thread.sleep(10000);
			commonPOM.getNextButtonSt2().click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
			Thread.sleep(3000);
			if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_FAILED_MESSAGE)) {
				jse.executeScript("window.scrollBy(0,-500)");
				assertTrue(commonPOM.getBackLinkFirst().isEnabled());
				
				jse.executeScript("window.scrollBy(0,500)");
				assertTrue(commonPOM.getPreviousButton().isEnabled());
				commonPOM.getPreviousButton().click();
				
				assertTrue(commonPOM.getXlsFileExtention().getText().contains(".xlsx") || commonPOM.getXlsFileExtention().getText().contains(".xls"));				
				
				logger.log(LogStatus.PASS, "School_Global_Curriculum_Standard(custom)_Ingestion_Select_Previous");
				logger.log(LogStatus.PASS, "Admin_User_School_Global_Curriculum_Standard(custom)_Ingestion_Select_Back");
				
				jse.executeScript("window.scrollBy(0,-500)");
				commonPOM.getBackLinkFirst().click();
			} else {
				logger.log(LogStatus.FAIL, "School_Global_Curriculum_Standard(custom)_Ingestion_Select_Previous");
				logger.log(LogStatus.FAIL, "Admin_User_School_Global_Curriculum_Standard(custom)_Ingestion_Select_Back");
				
				jse.executeScript("window.scrollBy(0,-500)");
				commonPOM.getBackLinkFirst().click();
				Thread.sleep(1000);
			}
			
			//Product
			commonPOM.getSchoolGlobalLOBRadioButton().click();
			commonPOM.getGseProductStructureRadioButton().click();
			jse.executeScript("window.scrollBy(0,500)");
			Thread.sleep(1000);
			commonPOM.getNextButtonFirst().click();
			
			jse.executeScript("window.scrollBy(0,500)");
			
			commonPOM.getProductTOCMetaDataDesc().sendKeys("Product!@#$%^&*(');_+{}|:'';<>?Tesst123");		
			commonPOM.getNextButton().click();
			
			commonPOM.getUploadFileLink().click();
			
			Runtime.getRuntime().exec(LOMTConstant.PRODUCT_FILE_PATH_SCHOOL_15);
			Thread.sleep(10000);
			commonPOM.getNextButtonSt2().click();
			jse.executeScript("window.scrollBy(0,-100)");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
			Thread.sleep(3000);
			if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_FAILED_MESSAGE)) {
				jse.executeScript("window.scrollBy(0,-500)");
				assertTrue(commonPOM.getBackLinkFirst().isEnabled());
				
				jse.executeScript("window.scrollBy(0,500)");
				assertTrue(commonPOM.getPreviousButton().isEnabled());
				commonPOM.getPreviousButton().click();
				
				assertTrue(commonPOM.getXlsFileExtention().getText().contains(".xlsx") || commonPOM.getXlsFileExtention().getText().contains(".xls"));				
				
				logger.log(LogStatus.PASS, "TC_LOMT-1584-08_Admin_User_School_Global_Product_Ingestion_Select_Previous");
				logger.log(LogStatus.PASS, "TC_LOMT-1584-20_Admin_User_School_Global_Product_Ingestion_Select_Back");
				
				jse.executeScript("window.scrollBy(0,-500)");
				commonPOM.getBackLinkFirst().click();
			} else {
				logger.log(LogStatus.FAIL, "TC_LOMT-1584-08_Admin_User_School_Global_Product_Ingestion_Select_Previous");
				logger.log(LogStatus.FAIL, "TC_LOMT-1584-20_Admin_User_School_Global_Product_Ingestion_Select_Back");
				
				jse.executeScript("window.scrollBy(0,-500)");
				commonPOM.getBackLinkFirst().click();
				Thread.sleep(1000);
			}
			
			//Intermediary
			commonPOM.getSchoolGlobalLOBRadioButton().click();
			commonPOM.getSchoolGlobalLOBRadioButton().click();
			commonPOM.getIntermediaryRadioButton().click();
			
			jse.executeScript("window.scrollBy(0,200)");
			commonPOM.getNextButtonFirst().click();
			Thread.sleep(10000);
			
			intermediaryPOM.getBusinessRadioButton().click();
			jse.executeScript("window.scrollBy(0, 800)");
			commonPOM.getNextButtonSt2().click();
			Thread.sleep(1000);
			commonPOM.getUploadFileLink().click();
			
			//upload incorrect intermediary xls file
			Runtime.getRuntime().exec(LOMTConstant.INTERMEDIARY_INCORRECT_FILE_PATH);
			Thread.sleep(8000);
			commonPOM.getNextButtonSt2().click();
			
			jse.executeScript("window.scrollBy(0, -100)");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
			Thread.sleep(3000);
			
			if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_FAILED_MESSAGE)) {
				jse.executeScript("window.scrollBy(0,-500)");
				assertTrue(commonPOM.getBackLinkFirst().isEnabled());
				
				jse.executeScript("window.scrollBy(0,500)");
				assertTrue(commonPOM.getPreviousButton().isEnabled());
				commonPOM.getPreviousButton().click();
				
				assertTrue(commonPOM.getXlsFileExtention().getText().contains(".xlsx") || commonPOM.getXlsFileExtention().getText().contains(".xls"));				
				
				logger.log(LogStatus.PASS, "TC_LOMT-1584-09_Admin_User_School_Global_Intermediaries_Ingestion_Select_Previous");
				logger.log(LogStatus.PASS, "TC_LOMT-1584-21_Admin_User_School_Global_Intermediaries_Ingestion_Select_Back");
				
				jse.executeScript("window.scrollBy(0,-500)");
				commonPOM.getPearsonLogo().click();
			} else {
				logger.log(LogStatus.FAIL, "TC_LOMT-1584-09_Admin_User_School_Global_Intermediaries_Ingestion_Select_Previous");
				logger.log(LogStatus.FAIL, "TC_LOMT-1584-21_Admin_User_School_Global_Intermediaries_Ingestion_Select_Back");
								
				jse.executeScript("window.scrollBy(0,-500)");
				commonPOM.getPearsonLogo().click();
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void closeDriverInstance() {
		driver.close();
	}

}
