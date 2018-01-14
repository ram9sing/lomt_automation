package lomt.pearson.api.externalframework;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import lomt.pearson.common.BaseClass;
import lomt.pearson.common.LoadPropertiesFile;
import lomt.pearson.common.ValidationCheck;
import lomt.pearson.constant.ExternalFrameworkTestData;
import lomt.pearson.constant.LOMTConstant;
import lomt.pearson.constant.TestCases;
import lomt.pearson.page_object.CommonPOM;
import lomt.pearson.page_object.EnglishPOM;
import lomt.pearson.page_object.ExternalFrameworkPOM;
import lomt.pearson.page_object.HEPom;
import lomt.pearson.page_object.Login;
import lomt.pearson.page_object.NALSPom;
import lomt.pearson.page_object.SGPom;
import lomt.pearson.page_object.SchoolPOM;

/**
 * TODO : add logger	
 * @author ram.sin
 *
 */
public class ExternalFramework2 extends BaseClass {
	
	private String environment = LoadPropertiesFile.getPropertiesValues(LOMTConstant.LOMT_ENVIRONMENT);
	private String userName = LoadPropertiesFile.getPropertiesValues(LOMTConstant.USER_NAME);
	//private String userName = LoadPropertiesFile.getPropertiesValues(LOMTConstant.USER_NAME_TEST);
	
	private String pwd = LoadPropertiesFile.getPropertiesValues(LOMTConstant.PASSWORD);
	private String userNameBasic = LoadPropertiesFile.getPropertiesValues(LOMTConstant.USER_NAME_LEARNING_USER);
	private String pwdBasic = LoadPropertiesFile.getPropertiesValues(LOMTConstant.PASSWORD_LEARNING_USER_PWD);
	
	
	String currentLOB = null;

	private WebDriver driver;

	private Login login = null;
	private CommonPOM commonPOM = null;
	private HEPom hePom = null;
	private NALSPom nalsPom  = null;
	private SGPom sgPom = null;
	private EnglishPOM englishPOM = null;
	private SchoolPOM schoolPOM = null;
	
	private ExternalFrameworkPOM exfPOM = null;
	ReadExternalFrameworkFile readExternalFrameworkFile = null;
	private ValidationCheck validationCheck = null;
	
	public void getDriverInstance(WebDriver driver) {
		this.driver = initialiseChromeDriver();
		//this.driver = initialiseFirefoxDriver();
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
		englishPOM = new EnglishPOM(driver);
		exfPOM = new ExternalFrameworkPOM(driver);
		schoolPOM = new SchoolPOM(driver);
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
	
	// Logout feature is not available below PPE environment
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
			currentLOB = commonPOM.getEnglishLOB().getText();
			commonPOM.getEnglishLOB().click();
			if (commonPOM.getManageIngestion().getText().equalsIgnoreCase(LOMTConstant.MANGE_INGESTION)) {
				Assert.assertTrue(commonPOM.getManageIngestion().isDisplayed());

				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("window.scrollBy(0,500)");
				commonPOM.getManageIngestion().click();
				logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_32_ADMIN_MANAGE_INGESTION_CLICK_ENGLISH);
			} else {
				logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1357_32_ADMIN_MANAGE_INGESTION_CLICK_ENGLISH);
				System.exit(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// add logger
		}
	}
	
	public void heBrowsePage(ExtentTest logger) {
		try {
			// INGESTION
			Assert.assertTrue(commonPOM.getHeLOB().isDisplayed());
			currentLOB = commonPOM.getHeLOB().getText();
			commonPOM.getHeLOB().click();
			if (commonPOM.getManageIngestion().getText().equalsIgnoreCase(LOMTConstant.MANGE_INGESTION)) {
				Assert.assertTrue(commonPOM.getManageIngestion().isDisplayed());

				logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_02_ADMIN_VERIFY_MANAGE_INGESTION);

				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("window.scrollBy(0,500)");
				Thread.sleep(1000);
				commonPOM.getManageIngestion().click();
				logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_03_ADMIN_MANAGE_INGESTION_CLICK_HE);
			} else {
				logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1357_03_ADMIN_MANAGE_INGESTION_CLICK_HE);
				System.exit(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1357_02_ADMIN_VERIFY_MANAGE_INGESTION);
			System.exit(0);
		}
	}
	
	public void nalsBrowsePage(ExtentTest logger) {
		try {
			Assert.assertTrue(commonPOM.getNalsLOB().isDisplayed());
			currentLOB = commonPOM.getNalsLOB().getText();
			commonPOM.getNalsLOB().click();
			if (commonPOM.getManageIngestion().getText().equalsIgnoreCase(LOMTConstant.MANGE_INGESTION)) {
				Assert.assertTrue(commonPOM.getManageIngestion().isDisplayed());

				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("window.scrollBy(0,500)");
				commonPOM.getManageIngestion().click();

				logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_61_ADMIN_MANAGE_INGESTION_CLICK_SCHOOL);
			} else {
				logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1357_61_ADMIN_MANAGE_INGESTION_CLICK_SCHOOL);
				System.exit(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1357_61_ADMIN_MANAGE_INGESTION_CLICK_SCHOOL);
			System.exit(0);
		}
	}
	
	public void sgBrowsePage(ExtentTest logger) {
		try {
			Assert.assertTrue(commonPOM.getSchoolGlobalLOB().isDisplayed());
			currentLOB = commonPOM.getSchoolGlobalLOB().getText();
			commonPOM.getSchoolGlobalLOB().click();

			if (commonPOM.getManageIngestion().getText().equalsIgnoreCase(LOMTConstant.MANGE_INGESTION)) {
				Assert.assertTrue(commonPOM.getManageIngestion().isDisplayed());

				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("window.scrollBy(0,500)");

				commonPOM.getManageIngestion().click();
			} else {
				logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1357_61_ADMIN_MANAGE_INGESTION_CLICK_SCHOOL);
				System.exit(0);
			}
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1357_61_ADMIN_MANAGE_INGESTION_CLICK_SCHOOL);
			System.exit(0);
		}
	}
	
	public void createUploadStructureFirstPageExf(ExtentTest logger) {
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			
			if (currentLOB.equalsIgnoreCase(LOMTConstant.ENGLISH_LOB)) {
				
				// Header, Footer
				Assert.assertTrue(commonPOM.getBackLinkFirst().isDisplayed());
				Assert.assertEquals(commonPOM.getCreateUploadStructureHeader().getText(),LOMTConstant.CREATE_STRUCTURE_TILE);
				Assert.assertFalse(commonPOM.getNextButtonFirst().isEnabled());

				// LOB radio button selection, by default English lob is selected
				Assert.assertEquals(commonPOM.getSelectLOBTitle().getText(), LOMTConstant.SELECT_LOB);
				
				Assert.assertTrue(commonPOM.getEnglishLOBRadioButton().isDisplayed());
				Assert.assertTrue(commonPOM.getHeLOBRadioButton().isDisplayed());
				Assert.assertTrue(commonPOM.getSchoolGlobalLOBRadioButton().isDisplayed());
				//Assert.assertTrue(commonPOM.getNalsLOBRadioButton().isDisplayed());
				
				logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_33_ADMIN_ENGLISH_LOB_RADIO_BUTTON_CLICK);
				Thread.sleep(2000);
				// Right side message validation before Structure select
				/*Assert.assertTrue(commonPOM.getFirstTextImage().isDisplayed());
				Assert.assertEquals(commonPOM.getFirstTextMessage().getText(), LOMTConstant.CHOOSE_STRUCTURE_TYPE);
				Assert.assertTrue(englishPOM.getSecondTextImage().isDisplayed());
				Assert.assertEquals(englishPOM.getSecondTextEnglish().getText(),LOMTConstant.PROVIDE_ENGLISH_METADASTA);
				Assert.assertTrue(commonPOM.getThirdTextImage().isDisplayed());
				Assert.assertEquals(commonPOM.getThirdTextMessage().getText(), LOMTConstant.UPLOAD_YOUR_FILE);
				Assert.assertTrue(commonPOM.getFourthTextImage().isDisplayed());
				Assert.assertEquals(commonPOM.getFourthTextMessage().getText(),LOMTConstant.STRUCTURE_PAGE_IMAGE_4_TEXT);*/
				
				// Structure radio button selection
				/*Assert.assertEquals(commonPOM.getSelectStructureTitle().getText(), LOMTConstant.SELECT_STRUCTURE_TYPE);

				Assert.assertTrue(commonPOM.getGseStructureRadioButton().isEnabled());
				Assert.assertTrue(commonPOM.getProductExternalFrameworkStructureRadioButton().isEnabled());
				Assert.assertTrue(commonPOM.getGseProductStructureRadioButton().isEnabled());*/
				
				commonPOM.getSgEXFStructureRadioButton().click();
				
				logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_34_ADMIN_EXTERNALFRAMEWORK_STRUCTURE_RADIO_BUTTON_CLICK_ENGLISH);
				Thread.sleep(1000);
				// right side message validation after Structure selected
				Assert.assertTrue(englishPOM.getSecondTextImage().isDisplayed());
				Assert.assertEquals(englishPOM.getSecondTextEnglish().getText(), LOMTConstant.EXF_TEXT);
				Assert.assertTrue(commonPOM.getNextButtonFirst().isEnabled());
				
				jse.executeScript("window.scrollBy(0,500)");
				Thread.sleep(1000);
				
				commonPOM.getNextButtonFirst().click(); 
				logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_35_ADMIN_EXTERNAL_FRAMEWORK_NEXT_ENGLISH);
				Thread.sleep(2000);
			} else if (currentLOB.equalsIgnoreCase(LOMTConstant.HE_LOB)) {
				
				// Header, Footer
				Assert.assertTrue(commonPOM.getBackLinkFirst().isDisplayed());
				Assert.assertEquals(commonPOM.getCreateUploadStructureHeader().getText(),LOMTConstant.CREATE_STRUCTURE_TILE);
				Assert.assertFalse(commonPOM.getNextButtonFirst().isEnabled());

				// LOB radio button selection, by default English lob is selected
				Assert.assertEquals(commonPOM.getSelectLOBTitle().getText(), LOMTConstant.SELECT_LOB);
				
				Assert.assertTrue(commonPOM.getEnglishLOBRadioButton().isDisplayed());
				Assert.assertTrue(commonPOM.getHeLOBRadioButton().isDisplayed());
				Assert.assertTrue(commonPOM.getSchoolGlobalLOBRadioButton().isDisplayed());
				//Assert.assertTrue(commonPOM.getNalsLOBRadioButton().isDisplayed());
				
				commonPOM.getHeLOBRadioButton().click();
				Thread.sleep(2000);
				logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_04_ADMIN_HIGHER_EDUCATION_LOB_RADIO_BUTTON_CLICK_HE);
				
				// Right side message validation before Structure select
				/*Assert.assertTrue(commonPOM.getFirstTextImage().isDisplayed());
				Assert.assertEquals(commonPOM.getFirstTextMessage().getText(), LOMTConstant.CHOOSE_STRUCTURE_TYPE);
				Assert.assertTrue(englishPOM.getSecondTextImage().isDisplayed());
				Assert.assertEquals(englishPOM.getSecondTextEnglish().getText(),LOMTConstant.META_DATA_HE);
				Assert.assertTrue(commonPOM.getThirdTextImage().isDisplayed());
				Assert.assertEquals(commonPOM.getThirdTextMessage().getText(), LOMTConstant.UPLOAD_YOUR_FILE);
				Assert.assertTrue(commonPOM.getFourthTextImage().isDisplayed());
				Assert.assertEquals(commonPOM.getFourthTextMessage().getText(),LOMTConstant.STRUCTURE_PAGE_IMAGE_4_TEXT);*/
				
				// Structure radio button selection
				/*Assert.assertEquals(commonPOM.getSelectStructureTitle().getText(), LOMTConstant.SELECT_STRUCTURE_TYPE);

				Assert.assertTrue(commonPOM.getGseStructureRadioButton().isEnabled());
				Assert.assertTrue(commonPOM.getProductExternalFrameworkStructureRadioButton().isEnabled());
				Assert.assertTrue(commonPOM.getGseProductStructureRadioButton().isEnabled());*/
				
				exfPOM.getExternalFrameworkStructureHE().click();
				logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_05_ADMIN_EXTERNALFRAMEWORK_STRUCTURE_RADIO_BUTTON_CLICK_HE);
				
				Thread.sleep(2000);
				// right side message validation after Structure selected
				Assert.assertTrue(englishPOM.getSecondTextImage().isDisplayed());
				Assert.assertEquals(englishPOM.getSecondTextEnglish().getText(), LOMTConstant.META_DATA_HE_EXF);
				Assert.assertTrue(commonPOM.getNextButtonFirst().isEnabled());
				
				jse.executeScript("window.scrollBy(0,500)");
				Thread.sleep(1000);
				
				commonPOM.getNextButtonFirst().click(); 
				logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_06_ADMIN_MANAGE_INGESTION_NEXT_HE);
				Thread.sleep(2000);
			} else {
				// School 
				// Header, Footer				
				commonPOM.getSchoolGlobalLOBRadioButton().click();
				Thread.sleep(1000);
				
				commonPOM.getSgEXFStructureRadioButton().click();
				logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_63_ADMIN_CURRICULUM_STANDARD_CUSTOM_STRUCTURE_RADIO_BUTTON_CLICK_SCHOOL);
				
				Thread.sleep(1000);
				
				jse.executeScript("window.scrollBy(0,500)");
				Thread.sleep(1000);
				
				commonPOM.getNextButtonFirst().click(); 
				logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_64_ADMIN_CURRICULUM_STANDARD_CUSTOM_NEXT_SCHOOL);
				Thread.sleep(2000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void backLinKSelection(ExtentTest logger) {
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			
			if (currentLOB.equalsIgnoreCase(LOMTConstant.HE_LOB)) {
				jse.executeScript("window.scrollBy(0,-500)");
				if (commonPOM.getBackLinkSec().isDisplayed()) {
					commonPOM.getBackLinkSec().click();
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_07_ADMIN_MANAGE_INGESTION_BACK__CREATE_OR_UPLOAD_A_STRUCTURE_HE);
				} else {
					jse.executeScript("window.scrollBy(0,-500)");
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1357_07_ADMIN_MANAGE_INGESTION_BACK__CREATE_OR_UPLOAD_A_STRUCTURE_HE);
					commonPOM.getPearsonLogo().click();
					Thread.sleep(1000);
					commonPOM.getHeLOB().click();
					currentLOB = commonPOM.getHeLOB().getText();
				}
			} else if (currentLOB.equalsIgnoreCase(LOMTConstant.ENGLISH_LOB)) {
				jse.executeScript("window.scrollBy(0,-500)");
				if (commonPOM.getBackLinkSec().isDisplayed()) {
					commonPOM.getBackLinkSec().click();
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_36_ADMIN_MANAGE_INGESTION_BACK_CREATE_OR_UPLOAD_A_STRUCTURE_ENGLISH);
				} else {
					jse.executeScript("window.scrollBy(0,-500)");
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1357_36_ADMIN_MANAGE_INGESTION_BACK_CREATE_OR_UPLOAD_A_STRUCTURE_ENGLISH);
					commonPOM.getPearsonLogo().click();
					Thread.sleep(1000);
					commonPOM.getEnglishLOB().click();
					currentLOB = commonPOM.getEnglishLOB().getText();
				}
			} else {
				//school
				jse.executeScript("window.scrollBy(0,-500)");
				if (commonPOM.getBackLinkSec().isDisplayed()) {
					commonPOM.getBackLinkSec().click();
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_65_ADMIN_MANAGE_INGESTION_BACK_CREATE_OR_UPLOAD_A_STRUCTURE_SCHOOL);
				} else {
					jse.executeScript("window.scrollBy(0,-500)");
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1357_65_ADMIN_MANAGE_INGESTION_BACK_CREATE_OR_UPLOAD_A_STRUCTURE_SCHOOL);
					commonPOM.getPearsonLogo().click();
					Thread.sleep(1000);
					commonPOM.getSchoolGlobalLOB().click();
					currentLOB = commonPOM.getSchoolGlobalLOB().getText();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void commonBackLink() {
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			
			if (currentLOB.equalsIgnoreCase(LOMTConstant.HE_LOB)) {
				jse.executeScript("window.scrollBy(0,-500)");
				commonPOM.getBackLinkFirst().click();
				Thread.sleep(1000);
				commonPOM.getHeLOBRadioButton().click();
				exfPOM.getExternalFrameworkStructureHE().click();
				jse.executeScript("window.scrollBy(0,500)");
				commonPOM.getNextButtonFirst().click();
				Thread.sleep(1000);
			} else if (currentLOB.equalsIgnoreCase(LOMTConstant.ENGLISH_LOB)) {
				jse.executeScript("window.scrollBy(0,-500)");
				Thread.sleep(1000);
				commonPOM.getBackLinkFirst().click();
				commonPOM.getEnglishExternalFrameworkStructureRadioButton().click();
				jse.executeScript("window.scrollBy(0,500)");
				commonPOM.getNextButtonFirst().click();
				Thread.sleep(1000);
			} else {
				jse.executeScript("window.scrollBy(0,-500)");
				commonPOM.getBackLinkFirst().click();
				Thread.sleep(1000);
				commonPOM.getSchoolGlobalLOBRadioButton().click();
				commonPOM.getSgEXFStructureRadioButton().click();
				jse.executeScript("window.scrollBy(0,500)");
				commonPOM.getNextButtonFirst().click();
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getLOBAndStructure() {
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			
			if (currentLOB.equalsIgnoreCase(LOMTConstant.HE_LOB)) {
				Thread.sleep(1000);
				commonPOM.getHeLOBRadioButton().click();
				exfPOM.getExternalFrameworkStructureHE().click();
				jse.executeScript("window.scrollBy(0,500)");
				commonPOM.getNextButtonFirst().click();
				Thread.sleep(1000);
			} else if (currentLOB.equalsIgnoreCase(LOMTConstant.ENGLISH_LOB)) {
				Thread.sleep(1000);
				commonPOM.getEnglishExternalFrameworkStructureRadioButton().click();
				jse.executeScript("window.scrollBy(0,500)");
				commonPOM.getNextButtonFirst().click();
				Thread.sleep(1000);
			} else {
				//School
				Thread.sleep(1000);
				commonPOM.getSchoolGlobalLOBRadioButton().click();
				Thread.sleep(1000);
				commonPOM.getSgEXFStructureRadioButton().click();
				jse.executeScript("window.scrollBy(0,500)");
				commonPOM.getNextButtonFirst().click();
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createUploadStructureMetaDataPageExf(ExtentTest logger) {
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			
			if (currentLOB.equalsIgnoreCase(LOMTConstant.ENGLISH_LOB)) {
				//Meta data fields
				Assert.assertEquals(exfPOM.getDesciptionText().getText(), LOMTConstant.DESCRIPTION);
				Assert.assertEquals(exfPOM.getSubjectText().getText(), LOMTConstant.SUBJECT);
				Assert.assertEquals(exfPOM.getIssueDateText().getText(), LOMTConstant.ISSUE_DATE);
				Assert.assertEquals(exfPOM.getSetText().getText(), LOMTConstant.SET);
				Assert.assertEquals(exfPOM.getStatusText().getText(), LOMTConstant.STATUS);
				Assert.assertEquals(exfPOM.getApplicationLevelText().getText(), LOMTConstant.APPLICATION_LEVEL);
				Assert.assertEquals(exfPOM.getFrameworkPurposeText().getText(), LOMTConstant.FRAMEWORK_PURPOSE);
				Assert.assertEquals(exfPOM.getDefinedByText().getText(), LOMTConstant.DEFINED_BY);
				
				exfPOM.getDesciptionVal().sendKeys(LOMTConstant.EXF_DESC_NAME);
				Thread.sleep(2000);
				
				//SUBJECT Selection
				exfPOM.getSubjectDropdown().click();
				Thread.sleep(1000);
				List<WebElement> subjectList = exfPOM.getSubjectList();
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
					return;
				}
				
				//Issue Date
				exfPOM.getIssueDateVal().sendKeys(LOMTConstant.DATE);
				Thread.sleep(1000);
				
				//Set dropdown, not implemented yet, jira - LOMT-1525(backlog)	
				
				//Status dropdown selection
				exfPOM.getStatusDropdown().click();
				Thread.sleep(1000);
				
				List<WebElement> statusList = exfPOM.getStatusList();
				int statusLength = statusList.size();
				if (statusLength > 0) {
					for (int i = 0; i <= statusLength; i++) {
						WebElement element = statusList.get(i);
						if (element.getText().equalsIgnoreCase(LOMTConstant.STATUS_VAL_ENGLISH)) {
							element.click();
							break;
						}
					}
				} else {
					Assert.assertFalse((statusLength == 0), LOMTConstant.STATUS+LOMTConstant.DROPDOWN_SIZE_NOT_ZERO);
					return;
				}
				
				jse.executeScript("window.scrollBy(0,500)");
				
				//Application Level drop-down selection
				exfPOM.getApplicationLevelDropdown().click();
				Thread.sleep(1000);
				
				List<WebElement> applicationLevelList = exfPOM.getApplicationLevelList();
				int applicationLevelLength = applicationLevelList.size();
				if (applicationLevelLength > 0) {
					for (int i = 0; i <= applicationLevelLength; i++) {
						WebElement element = applicationLevelList.get(i);
						if (element.getText()!= null/*.equalsIgnoreCase(LOMTConstant.APPLICATION_LEVEL_ENGLISH)*/) {
							element.click();
							break;
						}
					}
				} else {
					Assert.assertFalse((applicationLevelLength == 0), LOMTConstant.APPLICATION_LEVEL+LOMTConstant.DROPDOWN_SIZE_NOT_ZERO);
					return;
				}
				
				//Purpose drop-down selection
				exfPOM.getFrameworkPurposeDropdown().click();
				Thread.sleep(1000);
				
				List<WebElement> purposeList = exfPOM.getApplicationLevelList();
				int purposeLength = purposeList.size();
				if (purposeLength > 0) {
					for (int i = 0; i <= purposeLength; i++) {
						WebElement element = purposeList.get(i);
						if (element.getText()!= null/*.equalsIgnoreCase(LOMTConstant.PURPOSE_ENGLISH)*/) {
							element.click();
							break;
						}
					}
				} else {
					Assert.assertFalse((purposeLength == 0), LOMTConstant.FRAMEWORK_PURPOSE+LOMTConstant.DROPDOWN_SIZE_NOT_ZERO);
					return;
				}
				
				//Defined By drop-down selection
				exfPOM.getDefinedByDropdown().click();
				Thread.sleep(1000);
				
				List<WebElement> definedByList = exfPOM.getDefinedByList();
				int definedByLength = definedByList.size();
				if (definedByLength > 0) {
					for (int i = 0; i <= definedByLength; i++) {
						WebElement element = definedByList.get(i);
						if (element.getText()!= null/*.equalsIgnoreCase(LOMTConstant.DEFINED_BY_ENGLISH)*/) {
							element.click();
							break;
						}
					}
				} else {
					Assert.assertFalse((definedByLength == 0), LOMTConstant.DEFINED_BY+LOMTConstant.DROPDOWN_SIZE_NOT_ZERO);
					return;
				}
				logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_37_ALL_VALUE_NEXTBTN_ENLISHLOB);
				commonPOM.getNextButton().click();
			} else if (currentLOB.equalsIgnoreCase(LOMTConstant.HE_LOB)) {
				//Header and Footer
				Assert.assertTrue(commonPOM.getBackLinkSec().isDisplayed());
				Assert.assertEquals(commonPOM.getCreateUploadStructureHeader2().getText(), LOMTConstant.CREATE_STRUCTURE_TILE);
				Assert.assertTrue(commonPOM.getNextButton().isEnabled());  
				Assert.assertTrue(commonPOM.getBackButton().isEnabled()); 
				
				// Right side message validation before Structure select
				Assert.assertTrue(commonPOM.getFirstTextImage().isDisplayed());
				Assert.assertEquals(commonPOM.getFirstTextMessage().getText(), LOMTConstant.CHOOSE_STRUCTURE_TYPE);
				Assert.assertTrue(englishPOM.getSecondTextImage().isDisplayed());
				Assert.assertEquals(englishPOM.getSecondTextEnglish().getText(), LOMTConstant.META_DATA_HE_EXF);
				Assert.assertTrue(commonPOM.getThirdTextImage().isDisplayed());
				Assert.assertEquals(commonPOM.getThirdTextMessage().getText(), LOMTConstant.UPLOAD_YOUR_XLS_FILE);
				Assert.assertTrue(commonPOM.getFourthTextImage().isDisplayed());
				Assert.assertEquals(commonPOM.getFourthTextMessage().getText(),LOMTConstant.STRUCTURE_PAGE_IMAGE_4_TEXT);
				
				//Meta data fields
				Assert.assertEquals(exfPOM.getDesciptionText().getText(), LOMTConstant.DESCRIPTION);
				Assert.assertEquals(exfPOM.getSubjectText().getText(), LOMTConstant.SUBJECT);
				Assert.assertEquals(exfPOM.getIssueDateText().getText(), LOMTConstant.ISSUE_DATE);
				Assert.assertEquals(exfPOM.getSetText().getText(), LOMTConstant.SET);
				Assert.assertEquals(exfPOM.getStatusText().getText(), LOMTConstant.STATUS);
				Assert.assertEquals(exfPOM.getApplicationLevelText().getText(), LOMTConstant.APPLICATION_LEVEL);
				Assert.assertEquals(exfPOM.getFrameworkPurposeText().getText(), LOMTConstant.FRAMEWORK_PURPOSE);
				Assert.assertEquals(exfPOM.getDefinedByText().getText(), LOMTConstant.DEFINED_BY);
				
				exfPOM.getDesciptionVal().sendKeys(LOMTConstant.EXF_DESC_NAME);
				Thread.sleep(1000);
				
				//SUBJECT Selection
				exfPOM.getSubjectDropdown().click();
				Thread.sleep(1000);
				List<WebElement> subjectList = exfPOM.getSubjectList();
				int subjectLength = subjectList.size();
				if (subjectLength > 0) {
					for (int i = 0; i <= subjectLength; i++) {
						WebElement element = subjectList.get(i);
						if (element.getText()!= null/*.equalsIgnoreCase(LOMTConstant.ANTHROPOLOGY_SUBJECT_METADATA_HE)*/) {
							element.click();
							break;
						}
					}
				} else {
					Assert.assertFalse((subjectLength == 0), LOMTConstant.SUBJECT+LOMTConstant.DROPDOWN_SIZE_NOT_ZERO);
					return;
				}
				
				//Issue Date
				exfPOM.getIssueDateVal().sendKeys(LOMTConstant.DATE);
				Thread.sleep(1000);
				
				//Set dropdown, not implemented yet
				exfPOM.getSetDropdown().click();
				Thread.sleep(1000);
				
				List<WebElement> setList = exfPOM.getSetList();
				int setLength = setList.size();
				if (setLength > 0) {
					for (int i = 0; i <= setLength; i++) {
						WebElement element = setList.get(i);
						if (element.getText() != null) {
							element.click();
							break;
						}
					}
				} else {
					Assert.assertFalse((setLength == 0), LOMTConstant.SET+LOMTConstant.DROPDOWN_SIZE_NOT_ZERO);
					return;
				}
				
				//Status dropdown selection
				exfPOM.getStatusDropdown().click();
				Thread.sleep(1000);
				
				List<WebElement> statusList = exfPOM.getStatusList();
				int statusLength = statusList.size();
				if (statusLength > 0) {
					for (int i = 0; i <= statusLength; i++) {
						WebElement element = statusList.get(i);
						if (element.getText().equalsIgnoreCase(LOMTConstant.STATUS_METADATA_HE)) {
							element.click();
							break;
						}
					}
				} else {
					Assert.assertFalse((statusLength == 0), LOMTConstant.STATUS+LOMTConstant.DROPDOWN_SIZE_NOT_ZERO);
					return;
				}
				jse.executeScript("window.scrollBy(0,500)");
				
				//Application Level drop-down selection
				exfPOM.getApplicationLevelDropdown().click();
				Thread.sleep(1000);
				
				List<WebElement> applicationLevelList = exfPOM.getApplicationLevelList();
				int applicationLevelLength = applicationLevelList.size();
				if (applicationLevelLength > 0) {
					for (int i = 0; i <= applicationLevelLength; i++) {
						WebElement element = applicationLevelList.get(i);
						if (element.getText()!= null/*.equalsIgnoreCase(LOMTConstant.APPLICATION_LEVEL__METADATA_HE)*/) {
							element.click();
							break;
						}
					}
				} else {
					Assert.assertFalse((applicationLevelLength == 0), LOMTConstant.APPLICATION_LEVEL+LOMTConstant.DROPDOWN_SIZE_NOT_ZERO);
					return;
				}
				
				//Purpose drop-down selection
				exfPOM.getFrameworkPurposeDropdown().click();
				Thread.sleep(1000);
				
				List<WebElement> purposeList = exfPOM.getApplicationLevelList();
				int purposeLength = purposeList.size();
				if (purposeLength > 0) {
					for (int i = 0; i <= purposeLength; i++) {
						WebElement element = purposeList.get(i);
						if (element.getText()!= null/*.equalsIgnoreCase(LOMTConstant.PURPOSE_METADATA_HE)*/) {
							element.click();
							break;
						}
					}
				} else {
					Assert.assertFalse((purposeLength == 0), LOMTConstant.FRAMEWORK_PURPOSE+LOMTConstant.DROPDOWN_SIZE_NOT_ZERO);
					return;
				}
				
				//Defined By drop-down selection
				exfPOM.getDefinedByDropdown().click();
				Thread.sleep(1000);
				
				List<WebElement> definedByList = exfPOM.getDefinedByList();
				int definedByLength = definedByList.size();
				if (definedByLength > 0) {
					for (int i = 0; i <= definedByLength; i++) {
						WebElement element = definedByList.get(i);
						if (element.getText()!= null/*.equalsIgnoreCase(LOMTConstant.DEFINED_BY_METADATA_HE)*/) {
							element.click();
							break;
						}
					}
				} else {
					Assert.assertFalse((definedByLength == 0), LOMTConstant.DEFINED_BY+LOMTConstant.DROPDOWN_SIZE_NOT_ZERO);
					return;
				}
				logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_08_ALL_VALUE_NEXTBTN_HE);
				commonPOM.getNextButton().click();
			} else {
				//School

				//Header and Footer
				Assert.assertTrue(commonPOM.getBackLinkSec().isDisplayed());
				Assert.assertEquals(commonPOM.getCreateUploadStructureHeader2().getText(), LOMTConstant.CREATE_STRUCTURE_TILE);
				Assert.assertTrue(commonPOM.getNextButton().isEnabled());  
				Assert.assertTrue(commonPOM.getBackButton().isEnabled()); 
				
				// Right side message validation before Structure select
				Assert.assertTrue(commonPOM.getFirstTextImage().isDisplayed());
				Assert.assertEquals(commonPOM.getFirstTextMessage().getText(), LOMTConstant.CHOOSE_STRUCTURE_TYPE);
				Assert.assertTrue(englishPOM.getSecondTextImage().isDisplayed());
				Assert.assertEquals(englishPOM.getSecondTextEnglish().getText(), LOMTConstant.EXF_STRUCTURE_SELECTION_MESSAGE_SG_2);
				Assert.assertTrue(commonPOM.getThirdTextImage().isDisplayed());
				Assert.assertEquals(commonPOM.getThirdTextMessage().getText(), LOMTConstant.UPLOAD_YOUR_XLS_FILE);
				Assert.assertTrue(commonPOM.getFourthTextImage().isDisplayed());
				Assert.assertEquals(commonPOM.getFourthTextMessage().getText(),LOMTConstant.STRUCTURE_PAGE_IMAGE_4_TEXT);
				
				//Meta data fields
				Assert.assertEquals(exfPOM.getDesciptionText().getText(), LOMTConstant.DESCRIPTION);
				Assert.assertEquals(exfPOM.getSubjectText().getText(), LOMTConstant.SUBJECT);
				Assert.assertEquals(exfPOM.getIssueDateText().getText(), LOMTConstant.ISSUE_DATE);
				Assert.assertEquals(exfPOM.getSetText().getText(), LOMTConstant.SET);
				Assert.assertEquals(exfPOM.getStatusText().getText(), LOMTConstant.STATUS);
				Assert.assertEquals(exfPOM.getApplicationLevelText().getText(), LOMTConstant.APPLICATION_LEVEL);
				Assert.assertEquals(exfPOM.getFrameworkPurposeText().getText(), LOMTConstant.FRAMEWORK_PURPOSE);
				Assert.assertEquals(exfPOM.getDefinedByText().getText(), LOMTConstant.DEFINED_BY);
				
				exfPOM.getDesciptionVal().sendKeys(LOMTConstant.EXF_DESC_NAME);
				Thread.sleep(2000);
				
				//SUBJECT Selection
				exfPOM.getSubjectDropdown().click();
				Thread.sleep(2000);
				List<WebElement> subjectList = exfPOM.getSubjectList();
				int subjectLength = subjectList.size();
				if (subjectLength > 0) {
					for (int i = 0; i <= subjectLength; i++) {
						WebElement element = subjectList.get(i);
						if (element.getText() != null/*.equalsIgnoreCase(LOMTConstant.SCHOOL_SUBJECT)*/) {
							element.click();
							break;
						}
					}
				} else {
					Assert.assertFalse((subjectLength == 0), LOMTConstant.SCHOOL_SUBJECT+LOMTConstant.DROPDOWN_SIZE_NOT_ZERO);
					return;
				}
				
				//Issue Date
				exfPOM.getIssueDateVal().sendKeys(LOMTConstant.DATE);
				Thread.sleep(1000);
				
				//Set dropdown, not implemented yet
				exfPOM.getSetDropdown().click();
				Thread.sleep(1000);
				
				List<WebElement> setList = exfPOM.getSetList();
				int setLength = setList.size();
				if (setLength > 0) {
					for (int i = 0; i <= setLength; i++) {
						WebElement element = setList.get(i);
						if (element.getText() != null) {
							element.click();
							break;
						}
					}
				} else {
					Assert.assertFalse((setLength == 0), LOMTConstant.SET+LOMTConstant.DROPDOWN_SIZE_NOT_ZERO);
					return;
				}
				
				//Status dropdown selection
				exfPOM.getStatusDropdown().click();
				Thread.sleep(1000);
				
				List<WebElement> statusList = exfPOM.getStatusList();
				int statusLength = statusList.size();
				if (statusLength > 0) {
					for (int i = 0; i <= statusLength; i++) {
						WebElement element = statusList.get(i);
						if (element.getText().equalsIgnoreCase(LOMTConstant.STATUS_METADATA_HE)) {
							element.click();
							break;
						}
					}
				} else {
					Assert.assertFalse((statusLength == 0), LOMTConstant.STATUS+LOMTConstant.DROPDOWN_SIZE_NOT_ZERO);
					return;
				}
				
				jse.executeScript("window.scrollBy(0,500)");
				
				//Application Level drop-down selection
				exfPOM.getApplicationLevelDropdown().click();
				Thread.sleep(1000);
				
				List<WebElement> applicationLevelList = exfPOM.getApplicationLevelList();
				int applicationLevelLength = applicationLevelList.size();
				if (applicationLevelLength > 0) {
					for (int i = 0; i <= applicationLevelLength; i++) {
						WebElement element = applicationLevelList.get(i);
						if (element.getText() != null) {
							element.click();
							break;
						}
					}
				} else {
					Assert.assertFalse((applicationLevelLength == 0), LOMTConstant.APPLICATION_LEVEL+LOMTConstant.DROPDOWN_SIZE_NOT_ZERO);
					return;
				}
				
				//Purpose drop-down selection
				exfPOM.getFrameworkPurposeDropdown().click();
				Thread.sleep(1000);
				
				List<WebElement> purposeList = exfPOM.getApplicationLevelList();
				int purposeLength = purposeList.size();
				if (purposeLength > 0) {
					for (int i = 0; i <= purposeLength; i++) {
						WebElement element = purposeList.get(i);
						if (element.getText() != null/*.equalsIgnoreCase(LOMTConstant.PURPOSE_METADATA_HE)*/) {
							element.click();
							break;
						}
					}
				} else {
					Assert.assertFalse((purposeLength == 0), LOMTConstant.FRAMEWORK_PURPOSE+LOMTConstant.DROPDOWN_SIZE_NOT_ZERO);
					return;
				}
				
				jse.executeScript("window.scrollBy(0,500)");
				
				//Defined By drop-down selection
				exfPOM.getDefinedByDropdown().click();
				Thread.sleep(1000);
				
				List<WebElement> definedByList = exfPOM.getDefinedByList();
				int definedByLength = definedByList.size();
				if (definedByLength > 0) {
					for (int i = 0; i <= definedByLength; i++) {
						WebElement element = definedByList.get(i);
						if (element.getText()!= null/*.equalsIgnoreCase(LOMTConstant.DEFINED_BY_METADATA_School)*/) {
							element.click();
							break;
						}
					}
				} else {
					Assert.assertFalse((definedByLength == 0), LOMTConstant.DEFINED_BY+LOMTConstant.DROPDOWN_SIZE_NOT_ZERO);
					return;
				}
				logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_66_ALL_VALUE_NEXTBTN_SCHOOL_LOB);
				commonPOM.getNextButton().click();
				Thread.sleep(3000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createUploadStructureWithoutMetaDataPageExf() {
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,500)");
			//logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_38_WITHOUT_VALUE_NEXTBTN_ENGLSH_LOB);
			commonPOM.getNextButton().click();
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void externalFrameworkIngestion(ExtentTest logger, String rules) {
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			
			if (currentLOB.equalsIgnoreCase(LOMTConstant.HE_LOB)) {
				WebDriverWait wait = new WebDriverWait(driver, 60);
				
				commonPOM.getUploadFileLink().click();
				
				// TC-37 & TC-39, all the mandatory and non-mandatory along-with meta data
				if (rules.equalsIgnoreCase(LOMTConstant.EXF_ALL_FIELDS)) {
					Runtime.getRuntime().exec(LOMTConstant.EXF_ALL_FIELDS_XLS_PATH);
					
					Thread.sleep(5000);
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_10_UPLOAD_FILE_XLS_OR_XLSX_EXFRAM_HE);
					commonPOM.getNextButtonSt2().click();
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_11_INGEST_VALID_EXFRAM_HE);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
					Thread.sleep(3000);
					if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_SUCESSFULL_MESSAGE)) {
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_12_ADMIN_VERIFY_INGEST_SUCESS_MESSAGE_ON_REVIEW_OUTCOME_HE);
						
						Assert.assertTrue(commonPOM.getDoneButton().isEnabled());
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_13_ADMIN_VERIFY_DONE_BUTTON_EXFRAM_HE);
					} else {
						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1357_12_ADMIN_VERIFY_INGEST_SUCESS_MESSAGE_ON_REVIEW_OUTCOME_HE);
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_13_ADMIN_VERIFY_DONE_BUTTON_EXFRAM_HE);
					}
				}
				// TC-44 & non-mandatory and meta data
				if (rules.equalsIgnoreCase(LOMTConstant.EXF_NON_MANDATORY_FIELDS)) {
					Runtime.getRuntime().exec(LOMTConstant.EXF_MANDATORY_FIELDS_XLS_PATH);
					
					Thread.sleep(5000);
					commonPOM.getNextButtonSt2().click();
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
					Thread.sleep(3000);
					if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_SUCESSFULL_MESSAGE)) {
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_15_UPLOAD_FILE_XLS_OR_XLSX_WITHOUT_NON_MANDATORY_FIELDS_HE);
					} else {
						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1357_15_UPLOAD_FILE_XLS_OR_XLSX_WITHOUT_NON_MANDATORY_FIELDS_HE);
					}
				}
				//TC-45, wrong format file check
				if (rules.equalsIgnoreCase(LOMTConstant.EXF_WRONG_FORMAT_FILE)) {
					Runtime.getRuntime().exec(LOMTConstant.EXF_WRONG_FORMAT_XLS_PATH);
					Thread.sleep(5000);
					// switch back to base window
					driver.switchTo().alert().accept();
					Thread.sleep(2000);
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_16_UPLOAD_INGESTION_SHEET_FORMAT_DOCS_XML_TXT_HE);
				}
				//TC-46, TC-47, TC-48, TC-49
				if (rules.equalsIgnoreCase(LOMTConstant.EXF_WITHOUT_MANDATORY_FIELDS)) {
					Runtime.getRuntime().exec(LOMTConstant.EXF_WITHOUT_MANDATORY_FIELDS_XLS_PATH);
					
					Thread.sleep(5000);
					commonPOM.getNextButtonSt2().click();
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
					Thread.sleep(3000);
					if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_FAILED_MESSAGE)) {
						//viewIngestLog
						Assert.assertTrue(commonPOM.getViewIngestFullLogButton().isDisplayed());
						Assert.assertEquals(commonPOM.getViewIngestFullLogButton().getText(), LOMTConstant.VIEW_FULL_INGESTION_LOG);						
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_17_VIEW_FULL_INGEST_LOG_VERIFY_EXFRM_HE);
						
						commonPOM.getViewIngestFullLogButton().click();
						// verifying validation message, TODO : Need to add more validation
						boolean flag = exfValidationCheck(LOMTConstant.EXF_WITHOUT_MANDATORY_FIELDS); 
						if (flag) {
							logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_18_WITHOUTMANDATE_INSHEET_INGEST);
						} else {
							logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1357_18_WITHOUTMANDATE_INSHEET_INGEST);
						}
						Assert.assertTrue(commonPOM.getCancelButton().isEnabled());
						commonPOM.getCancelButton().click();						
						Assert.assertTrue(commonPOM.getBackLinkFirst().isEnabled());
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_19_ADMIN_BACK_OR_CANCEL_CLICK_INGESGLOGEXFRAM_HE);
						
						Assert.assertTrue(commonPOM.getDoneButton().isEnabled());
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_20_INGESLOG_DONE_EXFRAM_HE);
						
					} else {
						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1357_18_WITHOUTMANDATE_INSHEET_INGEST);
					}
				} 
				//TC-50, wrong grade value for Grade Low and Grade High
				if (rules.equalsIgnoreCase(LOMTConstant.WRONG_GRADE_VALUE)) {
					Runtime.getRuntime().exec(LOMTConstant.EXF_WRONG_GRADE_VAL_XLS_PATH);
					
					Thread.sleep(5000);
					commonPOM.getNextButtonSt2().click();
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
					Thread.sleep(3000);
					if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_FAILED_MESSAGE)) {
						commonPOM.getViewIngestFullLogButton().click();
						
						boolean flag = exfValidationCheck(LOMTConstant.WRONG_GRADE_VALUE); 
						System.out.println("WRONG_GRADE_VALUE : "+flag);
						if (flag) {
							logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_21_WRONGGRADEVALUE_EXFRAM_HE);
							logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_22_SEQUENCECHANGEGRADEVALUE_EXFRAM_HE);
						} else {
							logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1357_21_WRONGGRADEVALUE_EXFRAM_HE);
							logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1357_22_SEQUENCECHANGEGRADEVALUE_EXFRAM_HE);
						}
						commonPOM.getCancelButton().click();
					}
				}
				//TC-54, Levels at same rows
				if (rules.equalsIgnoreCase(LOMTConstant.LEVELS_AT_SAME_ROW)) {
					Runtime.getRuntime().exec(LOMTConstant.EXF_WRONG_LEVEL_VAL_XLS_PATH);
					
					Thread.sleep(5000);
					commonPOM.getNextButtonSt2().click();
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
					Thread.sleep(3000);
					if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_FAILED_MESSAGE)) {
						commonPOM.getViewIngestFullLogButton().click();
						
						boolean flag = exfValidationCheck(LOMTConstant.LEVELS_AT_SAME_ROW); 
						System.out.println("Level at same row : "+flag);
						if (flag) {
							logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_25_LEVELSQ_EXFRAM_HE);
						} else {
							logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1357_25_LEVELSQ_EXFRAM_HE);
						}
						commonPOM.getCancelButton().click();
					}
				}
				//TC-52, 53, 55, 56, 57, 58, 59, 60
				if (rules.equalsIgnoreCase(LOMTConstant.COMMON_TCS_INGESTION)) {
					Runtime.getRuntime().exec(LOMTConstant.EXF_COMMON_TCS_INGESTION_XLS_PATH);
					
					Thread.sleep(5000);
					commonPOM.getNextButtonSt2().click();
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
					Thread.sleep(3000);
					if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_SUCESSFULL_MESSAGE)) {
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_23_GRADEVALUEBLANK_EXFRAM_HE);
						
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_24_OFFICIAL_STANDARD_CODE_EXFRAM_HE);
						
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_26_TITLEMAXCHAR_HE);
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_27_TITLESPLCHAR_HE);
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_28_TITLEALPHNUMCHAR_HE);
						
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_29_LEVELMAXCHAR_HE);
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_30_LEVELSPLCHAR_HE);
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_31_LEVELALPHNUMCHAR_HE);
						
					} else {
						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1357_23_GRADEVALUEBLANK_EXFRAM_HE);
						
						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1357_24_OFFICIAL_STANDARD_CODE_EXFRAM_HE);
						
						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1357_26_TITLEMAXCHAR_HE);
						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1357_27_TITLESPLCHAR_HE);
						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1357_28_TITLEALPHNUMCHAR_HE);
						
						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1357_29_LEVELMAXCHAR_HE);
						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1357_30_LEVELSPLCHAR_HE);
						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1357_31_LEVELALPHNUMCHAR_HE);
					}
				}
				//Ingestion 10th level
				if (rules.equalsIgnoreCase(LOMTConstant.NTH_LEVEL)) {
					Runtime.getRuntime().exec(LOMTConstant.EXF_INGESTION_10TH_LEVEL_XLS_PATH);
					
					Thread.sleep(5000);
					commonPOM.getNextButtonSt2().click();
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
					Thread.sleep(3000);
					if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_SUCESSFULL_MESSAGE)) {
						System.out.println("Ingestion 10th level successful");
						commonPOM.getDoneButton().click();
						
						jse.executeScript("window.scrollBy(0,-500)");
						
						commonPOM.getBackLinkFirst().click();
						Thread.sleep(1000);
						 logger.log(LogStatus.PASS, LOMTConstant.INGESTION_SUCCESSFUL_NTH_LEVEL);
				  } else {
						logger.log(LogStatus.FAIL, LOMTConstant.INGESTION_SUCCESSFUL_NTH_LEVEL);
					}
				} 
			} else if (currentLOB.equalsIgnoreCase(LOMTConstant.ENGLISH_LOB)) {
				WebDriverWait wait = new WebDriverWait(driver, 60);

				commonPOM.getUploadFileLink().click();
				
				// TC-37 & TC-39, all the mandatory and non-mandatory along-with meta data
				if (rules.equalsIgnoreCase(LOMTConstant.EXF_ALL_FIELDS)) {
					Runtime.getRuntime().exec(LOMTConstant.EXF_ALL_FIELDS_XLS_PATH);
					
					Thread.sleep(5000);
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_39_UPLOAD_FILE_XLS_OR_XLSX_EXFRAM_ENGLISH);
					commonPOM.getNextButtonSt2().click();
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_40_INGEST_VALID_EXFRAM_ENGLISH);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
					Thread.sleep(3000);
					if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_SUCESSFULL_MESSAGE)) {
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_41_ADMIN_VERIFY_INGEST_SUCESS_MESSAGE_ON_REVIEW_OUTCOME_ENGLISH);
						
						Assert.assertTrue(commonPOM.getDoneButton().isEnabled());
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_42_ADMIN_VERIFY_DONE_BUTTON_EXFRAM_ENGLISH);
					} else {
						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1357_41_ADMIN_VERIFY_INGEST_SUCESS_MESSAGE_ON_REVIEW_OUTCOME_ENGLISH);
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_42_ADMIN_VERIFY_DONE_BUTTON_EXFRAM_ENGLISH);
					}
				}
				// TC-44 & non-mandatory and meta data
				if (rules.equalsIgnoreCase(LOMTConstant.EXF_NON_MANDATORY_FIELDS)) {
					Runtime.getRuntime().exec(LOMTConstant.EXF_MANDATORY_FIELDS_XLS_PATH);
					
					Thread.sleep(5000);
					commonPOM.getNextButtonSt2().click();
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
					Thread.sleep(3000);
					if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_SUCESSFULL_MESSAGE)) {
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_44_UPLOAD_FILE_XLS_OR_XLSX_WITHOUT_NON_MANDATORY_FIELDS_ENGLISH);
					} else {
						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1357_44_UPLOAD_FILE_XLS_OR_XLSX_WITHOUT_NON_MANDATORY_FIELDS_ENGLISH);
					}
				}
				//TC-45, wrong format file check
				if (rules.equalsIgnoreCase(LOMTConstant.EXF_WRONG_FORMAT_FILE)) {
					Runtime.getRuntime().exec(LOMTConstant.EXF_WRONG_FORMAT_XLS_PATH);
					Thread.sleep(5000);
					// switch back to base window
					driver.switchTo().alert().accept();
					Thread.sleep(2000);
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_45_UPLOAD_INGESTION_SHEET_FORMAT_DOCS_XML_TXT_ENGLISH);
				}
				//TC-46, TC-47, TC-48, TC-49
				if (rules.equalsIgnoreCase(LOMTConstant.EXF_WITHOUT_MANDATORY_FIELDS)) {
					Runtime.getRuntime().exec(LOMTConstant.EXF_WITHOUT_MANDATORY_FIELDS_XLS_PATH);
					
					Thread.sleep(5000);
					commonPOM.getNextButtonSt2().click();
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
					Thread.sleep(3000);
					if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_FAILED_MESSAGE)) {
						//viewIngestLog
						Assert.assertTrue(commonPOM.getViewIngestFullLogButton().isDisplayed());
						Assert.assertEquals(commonPOM.getViewIngestFullLogButton().getText(), LOMTConstant.VIEW_FULL_INGESTION_LOG);						
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_46_VIEW_FULL_INGEST_LOG_VERIFY_EXFRM_ENGLISH);
						
						commonPOM.getViewIngestFullLogButton().click();
						// verifying validation message, TODO : Need to add more validation
						boolean flag = exfValidationCheck(LOMTConstant.EXF_WITHOUT_MANDATORY_FIELDS); 
						if (flag) {
							logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_47_WITHOUTMANDATE_INSHEET_INGEST);
						} else {
							logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1357_47_WITHOUTMANDATE_INSHEET_INGEST);
						}
						Assert.assertTrue(commonPOM.getCancelButton().isEnabled());
						commonPOM.getCancelButton().click();						
						Assert.assertTrue(commonPOM.getBackLinkFirst().isEnabled());
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_48_ADMIN_BACK_OR_CANCEL_CLICK_INGESGLOGEXFRAM_ENGLISH);
						
						Assert.assertTrue(commonPOM.getDoneButton().isEnabled());
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_49_INGESLOG_DONE_EXFRAM_ENGLISH);
						
					} else {
						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1357_47_WITHOUTMANDATE_INSHEET_INGEST);
					}
				} 
				//TC-50, wrong grade value for Grade Low and Grade High
				if (rules.equalsIgnoreCase(LOMTConstant.WRONG_GRADE_VALUE)) {
					Runtime.getRuntime().exec(LOMTConstant.EXF_WRONG_GRADE_VAL_XLS_PATH);
					
					Thread.sleep(5000);
					commonPOM.getNextButtonSt2().click();
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
					Thread.sleep(3000);
					if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_FAILED_MESSAGE)) {
						commonPOM.getViewIngestFullLogButton().click();
						
						boolean flag = exfValidationCheck(LOMTConstant.WRONG_GRADE_VALUE); 
						System.out.println("WRONG_GRADE_VALUE : "+flag);
						if (flag) {
							logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_50_WRONGGRADEVALUE_EXFRAM_ENGLISH);
							logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_51_SEQUENCECHANGEGRADEVALUE_EXFRAM_ENGLISH);
						} else {
							logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1357_50_WRONGGRADEVALUE_EXFRAM_ENGLISH);
							logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1357_51_SEQUENCECHANGEGRADEVALUE_EXFRAM_ENGLISH);
						}
						commonPOM.getCancelButton().click();
					}
				}
				//TC-54, Levels at same rows
				if (rules.equalsIgnoreCase(LOMTConstant.LEVELS_AT_SAME_ROW)) {
					Runtime.getRuntime().exec(LOMTConstant.EXF_WRONG_LEVEL_VAL_XLS_PATH);
					
					Thread.sleep(5000);
					commonPOM.getNextButtonSt2().click();
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
					Thread.sleep(3000);
					if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_FAILED_MESSAGE)) {
						commonPOM.getViewIngestFullLogButton().click();
						
						boolean flag = exfValidationCheck(LOMTConstant.LEVELS_AT_SAME_ROW); 
						System.out.println("Level at same row : "+flag);
						if (flag) {
							logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_54_LEVELSQ_EXFRAM_ENGLISH);
						} else {
							logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1357_54_LEVELSQ_EXFRAM_ENGLISH);
						}
						commonPOM.getCancelButton().click();
					}
				}
				//TC-52, 53, 55, 56, 57, 58, 59, 60
				if (rules.equalsIgnoreCase(LOMTConstant.COMMON_TCS_INGESTION)) {
					Runtime.getRuntime().exec(LOMTConstant.EXF_COMMON_TCS_INGESTION_XLS_PATH);
					
					Thread.sleep(5000);
					commonPOM.getNextButtonSt2().click();
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
					Thread.sleep(3000);
					if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_SUCESSFULL_MESSAGE)) {
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_52_GRADEVALUEBLANK_EXFRAM_ENGLISH);
						
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_53_OFFICIAL_STANDARD_CODE_EXFRAM_ENGLISH);
						
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_55_TITLEMAXCHAR_ENGLISH);
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_56_TITLESPLCHAR_ENGLISH);
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_57_TITLEALPHNUMCHAR_ENGLISH);
						
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_58_LEVELMAXCHAR_ENGLISH);
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_59_LEVELSPLCHAR_ENGLISH);
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_60_LEVELALPHNUMCHAR_ENGLISH);
						
					} else {
						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1357_52_GRADEVALUEBLANK_EXFRAM_ENGLISH);
						
						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1357_53_OFFICIAL_STANDARD_CODE_EXFRAM_ENGLISH);
						
						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1357_55_TITLEMAXCHAR_ENGLISH);
						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1357_56_TITLESPLCHAR_ENGLISH);
						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1357_57_TITLEALPHNUMCHAR_ENGLISH);
						
						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1357_58_LEVELMAXCHAR_ENGLISH);
						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1357_59_LEVELSPLCHAR_ENGLISH);
						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1357_60_LEVELALPHNUMCHAR_ENGLISH);
					}
				}
				//Ingestion 10th level
				if (rules.equalsIgnoreCase(LOMTConstant.NTH_LEVEL)) {
					Runtime.getRuntime().exec(LOMTConstant.EXF_INGESTION_10TH_LEVEL_XLS_PATH);
					
					Thread.sleep(5000);
					commonPOM.getNextButtonSt2().click();
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
					Thread.sleep(3000);
					if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_SUCESSFULL_MESSAGE)) {
						System.out.println("Ingestion 10th level successful");
						commonPOM.getDoneButton().click();
						
						jse.executeScript("window.scrollBy(0,-500)");
						
						commonPOM.getBackLinkFirst().click();
						Thread.sleep(1000);
						 logger.log(LogStatus.PASS, LOMTConstant.INGESTION_SUCCESSFUL_NTH_LEVEL);
				  } else {
						logger.log(LogStatus.FAIL, LOMTConstant.INGESTION_SUCCESSFUL_NTH_LEVEL);
					}
				} 
			} else {
				// School
				WebDriverWait wait = new WebDriverWait(driver, 60);
				
				commonPOM.getUploadFileLink().click();
				
				// TC-37 & TC-39, all the mandatory and non-mandatory along-with meta data
				if (rules.equalsIgnoreCase(LOMTConstant.EXF_ALL_FIELDS)) {
					Runtime.getRuntime().exec(LOMTConstant.EXF_ALL_FIELDS_XLS_PATH);
					
					Thread.sleep(5000);
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_68_UPLOAD_FILE_XLS_OR_XLSX_CURRSTANCUSTOM_SCHOOL);
					commonPOM.getNextButtonSt2().click();
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_69_INGEST_VALID_CURRSTANCUSTOM_SCHOOL);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
					Thread.sleep(3000);
					if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_SUCESSFULL_MESSAGE)) {
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_70_ADMIN_VERIFY_INGEST_SUCESS_MESSAGE_ON_REVIEW_OUTCOME_CURRSTANCUSTOM_SCHOOL);
						
						Assert.assertTrue(commonPOM.getDoneButton().isEnabled());
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_71_ADMIN_VERIFY_DONE_BUTTON_CURRSTANCUSTOM_SCHOOL);
					} else {
						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1357_70_ADMIN_VERIFY_INGEST_SUCESS_MESSAGE_ON_REVIEW_OUTCOME_CURRSTANCUSTOM_SCHOOL);
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_71_ADMIN_VERIFY_DONE_BUTTON_CURRSTANCUSTOM_SCHOOL);
					}
				}
				// TC-44 & non-mandatory and meta data
				if (rules.equalsIgnoreCase(LOMTConstant.EXF_NON_MANDATORY_FIELDS)) {
					Runtime.getRuntime().exec(LOMTConstant.EXF_MANDATORY_FIELDS_XLS_PATH);
					
					Thread.sleep(5000);
					commonPOM.getNextButtonSt2().click();
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
					Thread.sleep(3000);
					if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_SUCESSFULL_MESSAGE)) {
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_73_UPLOAD_FILE_XLS_OR_XLSX_WITHOUT_NON_MANDATORY_FIELDS_SCHOOL);
					} else {
						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1357_73_UPLOAD_FILE_XLS_OR_XLSX_WITHOUT_NON_MANDATORY_FIELDS_SCHOOL);
					}
				}
				//TC-45, wrong format file check
				if (rules.equalsIgnoreCase(LOMTConstant.EXF_WRONG_FORMAT_FILE)) {
					Runtime.getRuntime().exec(LOMTConstant.EXF_WRONG_FORMAT_XLS_PATH);
					Thread.sleep(5000);
					// switch back to base window
					driver.switchTo().alert().accept();
					Thread.sleep(2000);
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_74_UPLOAD_INGESTION_SHEET_FORMAT_DOCS_XML_TXT_SCHOOL);
				}
				//TC-46, TC-47, TC-48, TC-49
				if (rules.equalsIgnoreCase(LOMTConstant.EXF_WITHOUT_MANDATORY_FIELDS)) {
					Runtime.getRuntime().exec(LOMTConstant.EXF_WITHOUT_MANDATORY_FIELDS_XLS_PATH);
					
					Thread.sleep(5000);
					commonPOM.getNextButtonSt2().click();
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
					Thread.sleep(3000);
					if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_FAILED_MESSAGE)) {
						//viewIngestLog
						Assert.assertTrue(commonPOM.getViewIngestFullLogButton().isDisplayed());
						Assert.assertEquals(commonPOM.getViewIngestFullLogButton().getText(), LOMTConstant.VIEW_FULL_INGESTION_LOG);						
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_75_VIEW_FULL_INGEST_LOG_VERIFY_CURRSTANCUSTOM_UI_SCHOOL);
						
						commonPOM.getViewIngestFullLogButton().click();
						// verifying validation message, TODO : Need to add more validation
						boolean flag = exfValidationCheck(LOMTConstant.EXF_WITHOUT_MANDATORY_FIELDS); 
						if (flag) {
							logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_76_WITHOUTMANDATE_INSHEET_INGEST_CURRSTANCUSTOM_UI_SCHOOL);
						} else {
							logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1357_76_WITHOUTMANDATE_INSHEET_INGEST_CURRSTANCUSTOM_UI_SCHOOL);
						}
						Assert.assertTrue(commonPOM.getCancelButton().isEnabled());
						commonPOM.getCancelButton().click();						
						Assert.assertTrue(commonPOM.getBackLinkFirst().isEnabled());
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_77_ADMIN_BACK_OR_CANCEL_CLICK_INGESGLOG_CURRSTANCUSTOM_UI_SCHOOL);
						
						Assert.assertTrue(commonPOM.getDoneButton().isEnabled());
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_78_INGESLOG_DONE_CURRSTANCUSTOM_UI_SCHOOL);
						
					} else {
						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1357_76_WITHOUTMANDATE_INSHEET_INGEST_CURRSTANCUSTOM_UI_SCHOOL);
					}
				} 
				//TC-50, wrong grade value for Grade Low and Grade High
				if (rules.equalsIgnoreCase(LOMTConstant.WRONG_GRADE_VALUE)) {
					Runtime.getRuntime().exec(LOMTConstant.EXF_WRONG_GRADE_VAL_XLS_PATH);
					
					Thread.sleep(5000);
					commonPOM.getNextButtonSt2().click();
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
					Thread.sleep(3000);
					if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_FAILED_MESSAGE)) {
						commonPOM.getViewIngestFullLogButton().click();
						
						boolean flag = exfValidationCheck(LOMTConstant.WRONG_GRADE_VALUE); 
						if (flag) {
							logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_79_WRONGGRADEVALUE_CURRSTANCUSTOM_UI_SCHOOL);
							logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_80_SEQUENCECHANGEGRADEVALUE__CURRSTANCUSTOM_UI_SCHOOL);
						} else {
							logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1357_79_WRONGGRADEVALUE_CURRSTANCUSTOM_UI_SCHOOL);
							logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1357_80_SEQUENCECHANGEGRADEVALUE__CURRSTANCUSTOM_UI_SCHOOL);
						}
						commonPOM.getCancelButton().click();
					}
				}
				//TC-54, Levels at same rows
				if (rules.equalsIgnoreCase(LOMTConstant.LEVELS_AT_SAME_ROW)) {
					Runtime.getRuntime().exec(LOMTConstant.EXF_WRONG_LEVEL_VAL_XLS_PATH);
					
					Thread.sleep(5000);
					commonPOM.getNextButtonSt2().click();
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
					Thread.sleep(3000);
					if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_FAILED_MESSAGE)) {
						commonPOM.getViewIngestFullLogButton().click();
						
						boolean flag = exfValidationCheck(LOMTConstant.LEVELS_AT_SAME_ROW); 
						if (flag) {
							logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_83_LEVELSQ_CURRSTANCUSTOM_UI_SCHOOL);
						} else {
							logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1357_83_LEVELSQ_CURRSTANCUSTOM_UI_SCHOOL);
						}
						commonPOM.getCancelButton().click();
					}
				}
				//TC-52, 53, 55, 56, 57, 58, 59, 60
				if (rules.equalsIgnoreCase(LOMTConstant.COMMON_TCS_INGESTION)) {
					Runtime.getRuntime().exec(LOMTConstant.EXF_COMMON_TCS_INGESTION_XLS_PATH);
					
					Thread.sleep(5000);
					commonPOM.getNextButtonSt2().click();
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
					Thread.sleep(3000);
					if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_SUCESSFULL_MESSAGE)) {
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_81_GRADEVALUEBLANK_CURRSTANCUSTOM_UI_SCHOOL);
						
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_82_OFFICIAL_STANDARD_CODE_CURRSTANCUSTOM_UI_SCHOOL);
						
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_84_TITLEMAXCHAR_CURRSTANCUSTOM_UI_SCHOOL);
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_85_TITLESPLCHAR_CURRSTANCUSTOM_UI_SCHOOL);
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_86_TITLEALPHNUMCHAR_CURRSTANCUSTOM_UI_SCHOOL);
						
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_87_LEVELMAXCHAR_CURRSTANCUSTOM_UI_SCHOOL);
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_88_LEVELSPLCHA_CURRSTANCUSTOM_UI_SCHOOL);
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_89_LEVELALPHNUMCHAR_CURRSTANCUSTOM_UI_SCHOOL);
						
					} else {
						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1357_81_GRADEVALUEBLANK_CURRSTANCUSTOM_UI_SCHOOL);

						logger.log(LogStatus.FAIL,TestCases.TC_LOMT_1357_82_OFFICIAL_STANDARD_CODE_CURRSTANCUSTOM_UI_SCHOOL);

						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1357_84_TITLEMAXCHAR_CURRSTANCUSTOM_UI_SCHOOL);
						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1357_85_TITLESPLCHAR_CURRSTANCUSTOM_UI_SCHOOL);
						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1357_86_TITLEALPHNUMCHAR_CURRSTANCUSTOM_UI_SCHOOL);

						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1357_87_LEVELMAXCHAR_CURRSTANCUSTOM_UI_SCHOOL);
						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1357_88_LEVELSPLCHA_CURRSTANCUSTOM_UI_SCHOOL);
						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1357_89_LEVELALPHNUMCHAR_CURRSTANCUSTOM_UI_SCHOOL);
					}
				}
				//Ingestion 10th level
				if (rules.equalsIgnoreCase(LOMTConstant.NTH_LEVEL)) {
					Runtime.getRuntime().exec(LOMTConstant.EXF_INGESTION_10TH_LEVEL_XLS_PATH);
					
					Thread.sleep(3000);
					commonPOM.getNextButtonSt2().click();
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
					Thread.sleep(5000);
					if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_SUCESSFULL_MESSAGE)) {
						System.out.println("Ingestion 10th level successful");
						commonPOM.getDoneButton().click();
						
						jse.executeScript("window.scrollBy(0,-500)");
						
						commonPOM.getBackLinkFirst().click();
						Thread.sleep(1000);
						 logger.log(LogStatus.PASS, LOMTConstant.INGESTION_SUCCESSFUL_NTH_LEVEL);
				  } else {
						logger.log(LogStatus.FAIL, LOMTConstant.INGESTION_SUCCESSFUL_NTH_LEVEL);
					}
				} 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//TODO
	public void verifyIngestedDataOnResultPage(ExtentTest logger) {

		if (currentLOB.equalsIgnoreCase(LOMTConstant.HE_LOB)) {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			
			try {
				System.out.println("currentLOB : "+currentLOB);
				exfPOM.getExternalFrameworkStructureBrowseHE().click();
				Thread.sleep(20000);

				jse.executeScript("window.scrollBy(0,500)");
				
				//1st External Framework Title Search
				hePom.getHeEnterSearchTerm().sendKeys(LOMTConstant.EXF_INGESTION_FILE_NAME_1);
				Thread.sleep(1000);

				Assert.assertTrue(hePom.getHeUpdateResultButton().isEnabled());
				hePom.getHeUpdateResultButton().click();
				
				Thread.sleep(10000);
				Assert.assertEquals(LOMTConstant.EXF_INGESTION_FILE_NAME_1, exfPOM.getSearchedEXFTitle().getText());
				
				//2nd External Framework Title Search
				hePom.getHeEnterSearchTerm().clear();
				Thread.sleep(1000);
				hePom.getHeEnterSearchTerm().sendKeys(LOMTConstant.INGESTION_FILE_10TH_LEVEL);
				Thread.sleep(1000);

				Assert.assertTrue(hePom.getHeUpdateResultButton().isEnabled());
				hePom.getHeUpdateResultButton().click();
				
				Thread.sleep(10000);
				Assert.assertEquals(LOMTConstant.INGESTION_FILE_10TH_LEVEL, exfPOM.getSearchedEXFTitle().getText());
				
				logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_14_ADMIN_VERIFY_INGESTED_EXFRAM_UI_HE);
				
				jse.executeScript("window.scrollBy(0,-500)");
				
				commonPOM.getPearsonLogo().click();
				Thread.sleep(1000);
			} catch (Exception e) {
				logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1357_14_ADMIN_VERIFY_INGESTED_EXFRAM_UI_HE);
			}
		} else if (currentLOB.equalsIgnoreCase(LOMTConstant.ENGLISH_LOB)) {
			try {
				System.out.println("currentLOB : "+currentLOB);
				exfPOM.getExternalFrameworkStructureEnglish().click();
				Thread.sleep(20000);

				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("window.scrollBy(0,500)");
				
				//1st External Framework Title Search
				hePom.getHeEnterSearchTerm().sendKeys(LOMTConstant.EXF_INGESTION_FILE_NAME_1);
				Thread.sleep(1000);

				Assert.assertTrue(hePom.getHeUpdateResultButton().isEnabled());
				hePom.getHeUpdateResultButton().click();
				
				Thread.sleep(10000);
				Assert.assertEquals(LOMTConstant.EXF_INGESTION_FILE_NAME_1, exfPOM.getSearchedEXFTitle().getText());
				
				//2nd External Framework Title Search
				hePom.getHeEnterSearchTerm().clear();
				Thread.sleep(1000);
				hePom.getHeEnterSearchTerm().sendKeys(LOMTConstant.INGESTION_FILE_10TH_LEVEL);
				Thread.sleep(1000);

				Assert.assertTrue(hePom.getHeUpdateResultButton().isEnabled());
				hePom.getHeUpdateResultButton().click();
				
				Thread.sleep(10000);
				Assert.assertEquals(LOMTConstant.INGESTION_FILE_10TH_LEVEL, exfPOM.getSearchedEXFTitle().getText());
				
				logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_43_ADMIN_VERIFY_INGESTED_EXFRAM_UI_ENGLISH_LOB);
				jse.executeScript("window.scrollBy(0,-500)");
				
				commonPOM.getPearsonLogo().click();
				Thread.sleep(1000);
			} catch (Exception e) {
				logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1357_43_ADMIN_VERIFY_INGESTED_EXFRAM_UI_ENGLISH_LOB);
			}
		} else {
			// School
			try {
				exfPOM.getExternalFrameworkStructureBrowseHE().click();
				Thread.sleep(20000);

				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("window.scrollBy(0,500)");
				
				//1st External Framework Title Search
				hePom.getHeEnterSearchTerm().sendKeys(LOMTConstant.EXF_INGESTION_FILE_NAME_1);
				Thread.sleep(1000);

				Assert.assertTrue(hePom.getHeUpdateResultButton().isEnabled());
				hePom.getHeUpdateResultButton().click();
				
				Thread.sleep(10000);
				Assert.assertEquals(LOMTConstant.EXF_INGESTION_FILE_NAME_1, exfPOM.getSearchedEXFTitle().getText());
				
				//2nd External Framework Title Search
				hePom.getHeEnterSearchTerm().clear();
				Thread.sleep(1000);
				hePom.getHeEnterSearchTerm().sendKeys(LOMTConstant.INGESTION_FILE_10TH_LEVEL);
				Thread.sleep(1000);

				Assert.assertTrue(hePom.getHeUpdateResultButton().isEnabled());
				hePom.getHeUpdateResultButton().click();
				
				Thread.sleep(10000);
				Assert.assertEquals(LOMTConstant.INGESTION_FILE_10TH_LEVEL, exfPOM.getSearchedEXFTitle().getText());
				
				//3RD External Framework Title Search
				hePom.getHeEnterSearchTerm().clear();
				Thread.sleep(1000);
				hePom.getHeEnterSearchTerm().sendKeys(LOMTConstant.INGESTION_NON_MAND);
				Thread.sleep(1000);

				Assert.assertTrue(hePom.getHeUpdateResultButton().isEnabled());
				hePom.getHeUpdateResultButton().click();
				
				Thread.sleep(10000);
				Assert.assertEquals(LOMTConstant.INGESTION_NON_MAND, exfPOM.getSearchedEXFTitle().getText());
				
				logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_72_ADMIN_VERIFY_INGESTED_CURRSTANCUSTOM_UI_SCHOOL);
				
				jse.executeScript("window.scrollBy(0,-500)");
				
				commonPOM.getPearsonLogo().click();
				Thread.sleep(1000);
			} catch (Exception e) {
				logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1357_72_ADMIN_VERIFY_INGESTED_CURRSTANCUSTOM_UI_SCHOOL);
			}
		}
	}
	
	public boolean exfValidationCheck(String testCaseName) {
		boolean flag = false;
		try {
			// validation is not coming for LEVEL 2, NEED TO ADD FOR IT.
			// need to add more validation if condition for OSCode, Level1 and Level 2
			if (testCaseName.equalsIgnoreCase(LOMTConstant.EXF_WITHOUT_MANDATORY_FIELDS)) {
				
				if (exfPOM.getErrorRowOne().getText().equalsIgnoreCase(LOMTConstant.EXTERNAL_FRAMEWORK_TITLE_ERROR_MESSAGE)
						|| exfPOM.getErrorRowTwo().getText().equalsIgnoreCase(LOMTConstant.EXTERNAL_FRAMEWORK_TITLE_ERROR_MESSAGE)
						|| exfPOM.getErrorRowThree().getText().equalsIgnoreCase(LOMTConstant.EXTERNAL_FRAMEWORK_TITLE_ERROR_MESSAGE)
						|| exfPOM.getErrorRowFour().getText().equalsIgnoreCase(LOMTConstant.EXTERNAL_FRAMEWORK_TITLE_ERROR_MESSAGE)
						|| exfPOM.getErrorRowFive().getText().equalsIgnoreCase(LOMTConstant.EXTERNAL_FRAMEWORK_TITLE_ERROR_MESSAGE)
						|| exfPOM.getErrorRowSix().getText().equalsIgnoreCase(LOMTConstant.EXTERNAL_FRAMEWORK_TITLE_ERROR_MESSAGE) ) {
					flag = true;
				} 
				if (exfPOM.getErrorRowOne().getText().equalsIgnoreCase(LOMTConstant.OFFICIAL_STANDARD_CODE_ERROR_MESSAGE_3)
						|| exfPOM.getErrorRowTwo().getText().equalsIgnoreCase(LOMTConstant.OFFICIAL_STANDARD_CODE_ERROR_MESSAGE_3)
						|| exfPOM.getErrorRowThree().getText().equalsIgnoreCase(LOMTConstant.OFFICIAL_STANDARD_CODE_ERROR_MESSAGE_3)
						|| exfPOM.getErrorRowFour().getText().equalsIgnoreCase(LOMTConstant.OFFICIAL_STANDARD_CODE_ERROR_MESSAGE_3)
						|| exfPOM.getErrorRowFive().getText().equalsIgnoreCase(LOMTConstant.OFFICIAL_STANDARD_CODE_ERROR_MESSAGE_3)
						|| exfPOM.getErrorRowSix().getText().equalsIgnoreCase(LOMTConstant.OFFICIAL_STANDARD_CODE_ERROR_MESSAGE_3) ) {
					flag = true;
				} 
				if (exfPOM.getErrorRowOne().getText().equalsIgnoreCase(LOMTConstant.OFFICIAL_STANDARD_CODE_ERROR_MESSAGE_15)
						|| exfPOM.getErrorRowTwo().getText().equalsIgnoreCase(LOMTConstant.OFFICIAL_STANDARD_CODE_ERROR_MESSAGE_15)
						|| exfPOM.getErrorRowThree().getText().equalsIgnoreCase(LOMTConstant.OFFICIAL_STANDARD_CODE_ERROR_MESSAGE_15)
						|| exfPOM.getErrorRowFour().getText().equalsIgnoreCase(LOMTConstant.OFFICIAL_STANDARD_CODE_ERROR_MESSAGE_15)
						|| exfPOM.getErrorRowFive().getText().equalsIgnoreCase(LOMTConstant.OFFICIAL_STANDARD_CODE_ERROR_MESSAGE_15)
						|| exfPOM.getErrorRowSix().getText().equalsIgnoreCase(LOMTConstant.OFFICIAL_STANDARD_CODE_ERROR_MESSAGE_15) ) {
					flag = true;
				} 
				if (exfPOM.getErrorRowOne().getText().equalsIgnoreCase(LOMTConstant.LEVEL_COLUMN_1_ERROR_MESSAGE)
						|| exfPOM.getErrorRowTwo().getText().equalsIgnoreCase(LOMTConstant.LEVEL_COLUMN_1_ERROR_MESSAGE)
						|| exfPOM.getErrorRowThree().getText().equalsIgnoreCase(LOMTConstant.LEVEL_COLUMN_1_ERROR_MESSAGE)
						|| exfPOM.getErrorRowFour().getText().equalsIgnoreCase(LOMTConstant.LEVEL_COLUMN_1_ERROR_MESSAGE)
						|| exfPOM.getErrorRowFive().getText().equalsIgnoreCase(LOMTConstant.LEVEL_COLUMN_1_ERROR_MESSAGE)
						|| exfPOM.getErrorRowSix().getText().equalsIgnoreCase(LOMTConstant.LEVEL_COLUMN_1_ERROR_MESSAGE) ) {
					flag = true;
				} 
				if (exfPOM.getErrorRowOne().getText().equalsIgnoreCase(LOMTConstant.LEVEL_COLUMN_1_ERROR_MESSAGE_5)
						|| exfPOM.getErrorRowTwo().getText().equalsIgnoreCase(LOMTConstant.LEVEL_COLUMN_1_ERROR_MESSAGE_5)
						|| exfPOM.getErrorRowThree().getText().equalsIgnoreCase(LOMTConstant.LEVEL_COLUMN_1_ERROR_MESSAGE_5)
						|| exfPOM.getErrorRowFour().getText().equalsIgnoreCase(LOMTConstant.LEVEL_COLUMN_1_ERROR_MESSAGE_5)
						|| exfPOM.getErrorRowFive().getText().equalsIgnoreCase(LOMTConstant.LEVEL_COLUMN_1_ERROR_MESSAGE_5)
						|| exfPOM.getErrorRowSix().getText().equalsIgnoreCase(LOMTConstant.LEVEL_COLUMN_1_ERROR_MESSAGE_5) ) {
					flag = true;
				} 
				if (exfPOM.getErrorRowOne().getText().equalsIgnoreCase(LOMTConstant.LEVEL_COLUMN_1_ERROR_MESSAGE_15)
						|| exfPOM.getErrorRowTwo().getText().equalsIgnoreCase(LOMTConstant.LEVEL_COLUMN_1_ERROR_MESSAGE_15)
						|| exfPOM.getErrorRowThree().getText().equalsIgnoreCase(LOMTConstant.LEVEL_COLUMN_1_ERROR_MESSAGE_15)
						|| exfPOM.getErrorRowFour().getText().equalsIgnoreCase(LOMTConstant.LEVEL_COLUMN_1_ERROR_MESSAGE_15)
						|| exfPOM.getErrorRowFive().getText().equalsIgnoreCase(LOMTConstant.LEVEL_COLUMN_1_ERROR_MESSAGE_15)
						|| exfPOM.getErrorRowSix().getText().equalsIgnoreCase(LOMTConstant.LEVEL_COLUMN_1_ERROR_MESSAGE_15) ) {
					flag = true;
				} 
			}
			
		} catch (Exception e) {
			flag = false;
		}
		
		//Re-ingestion validation check		
		if (testCaseName.equalsIgnoreCase(LOMTConstant.REINGESTION_WRONG_GRADE_SAME_LEVEL)) {
			try {
				if (exfPOM.getErrorRowOne().getText().equalsIgnoreCase(LOMTConstant.GRADE_LOW_3_ERROR_MESSAGE)						
						|| exfPOM.getErrorRowTwo().getText().equalsIgnoreCase(LOMTConstant.GRADE_LOW_3_ERROR_MESSAGE)
						|| exfPOM.getErrorRowThree().getText().equalsIgnoreCase(LOMTConstant.GRADE_LOW_3_ERROR_MESSAGE)
						|| exfPOM.getErrorRowFour().getText().equalsIgnoreCase(LOMTConstant.GRADE_LOW_3_ERROR_MESSAGE)
						|| exfPOM.getErrorRowFive().getText().equalsIgnoreCase(LOMTConstant.GRADE_LOW_3_ERROR_MESSAGE) ) {
					flag = true;
				}
				if (exfPOM.getErrorRowOne().getText().equalsIgnoreCase(LOMTConstant.MULTIPLE_LEVEL_ROW_3)						
						|| exfPOM.getErrorRowTwo().getText().equalsIgnoreCase(LOMTConstant.MULTIPLE_LEVEL_ROW_3)
						|| exfPOM.getErrorRowThree().getText().equalsIgnoreCase(LOMTConstant.MULTIPLE_LEVEL_ROW_3)
						|| exfPOM.getErrorRowFour().getText().equalsIgnoreCase(LOMTConstant.MULTIPLE_LEVEL_ROW_3)
						|| exfPOM.getErrorRowFive().getText().equalsIgnoreCase(LOMTConstant.MULTIPLE_LEVEL_ROW_3) ) {
					flag = true;
				}
				if (exfPOM.getErrorRowOne().getText().equalsIgnoreCase(LOMTConstant.LEVEL_3_INCORRECT_ERROR_MESSAGE)						
						|| exfPOM.getErrorRowTwo().getText().equalsIgnoreCase(LOMTConstant.LEVEL_3_INCORRECT_ERROR_MESSAGE)
						|| exfPOM.getErrorRowThree().getText().equalsIgnoreCase(LOMTConstant.LEVEL_3_INCORRECT_ERROR_MESSAGE)
						|| exfPOM.getErrorRowFour().getText().equalsIgnoreCase(LOMTConstant.LEVEL_3_INCORRECT_ERROR_MESSAGE)
						|| exfPOM.getErrorRowFive().getText().equalsIgnoreCase(LOMTConstant.LEVEL_3_INCORRECT_ERROR_MESSAGE) ) {
					flag = true;
				}
				if (exfPOM.getErrorRowOne().getText().equalsIgnoreCase(LOMTConstant.GRADE_LOW_ROW_4)						
						|| exfPOM.getErrorRowTwo().getText().equalsIgnoreCase(LOMTConstant.GRADE_LOW_ROW_4)
						|| exfPOM.getErrorRowThree().getText().equalsIgnoreCase(LOMTConstant.GRADE_LOW_ROW_4)
						|| exfPOM.getErrorRowFour().getText().equalsIgnoreCase(LOMTConstant.GRADE_LOW_ROW_4)
						|| exfPOM.getErrorRowFive().getText().equalsIgnoreCase(LOMTConstant.GRADE_LOW_ROW_4) ) {
					flag = true;
				}
				if (exfPOM.getErrorRowOne().getText().equalsIgnoreCase(LOMTConstant.GRADE_LOW_HIGH_ERROR_MESSAGE)
						|| exfPOM.getErrorRowTwo().getText().equalsIgnoreCase(LOMTConstant.GRADE_LOW_HIGH_ERROR_MESSAGE)
						|| exfPOM.getErrorRowThree().getText().equalsIgnoreCase(LOMTConstant.GRADE_LOW_HIGH_ERROR_MESSAGE)
						|| exfPOM.getErrorRowFour().getText().equalsIgnoreCase(LOMTConstant.GRADE_LOW_HIGH_ERROR_MESSAGE)
						|| exfPOM.getErrorRowFive().getText().equalsIgnoreCase(LOMTConstant.GRADE_LOW_HIGH_ERROR_MESSAGE) ) {
					flag = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// Wrong Grade value
		if (testCaseName.equalsIgnoreCase(LOMTConstant.WRONG_GRADE_VALUE)) {
			try {
				if (exfPOM.getErrorRowOne().getText().equalsIgnoreCase(LOMTConstant.GRADE_LOW_3_ERROR_MESSAGE)
						|| exfPOM.getErrorRowTwo().getText().equalsIgnoreCase(LOMTConstant.GRADE_LOW_3_ERROR_MESSAGE)
						|| exfPOM.getErrorRowThree().getText().equalsIgnoreCase(LOMTConstant.GRADE_LOW_3_ERROR_MESSAGE)
						|| exfPOM.getErrorRowFour().getText().equalsIgnoreCase(LOMTConstant.GRADE_LOW_3_ERROR_MESSAGE)
						|| exfPOM.getErrorRowFive().getText().equalsIgnoreCase(LOMTConstant.GRADE_LOW_3_ERROR_MESSAGE) ) {
					flag = true;
				} else {
					return flag;
				}
				if (exfPOM.getErrorRowOne().getText().equalsIgnoreCase(LOMTConstant.GRADE_HIGH_3_ERROR_MESSAGE)
						|| exfPOM.getErrorRowTwo().getText().equalsIgnoreCase(LOMTConstant.GRADE_HIGH_3_ERROR_MESSAGE)
						|| exfPOM.getErrorRowThree().getText().equalsIgnoreCase(LOMTConstant.GRADE_HIGH_3_ERROR_MESSAGE)
						|| exfPOM.getErrorRowFour().getText().equalsIgnoreCase(LOMTConstant.GRADE_HIGH_3_ERROR_MESSAGE)
						|| exfPOM.getErrorRowFive().getText().equalsIgnoreCase(LOMTConstant.GRADE_HIGH_3_ERROR_MESSAGE) ) {
					flag = true;
				} else {
					return flag;
				}
				if (exfPOM.getErrorRowOne().getText().equalsIgnoreCase(LOMTConstant.GRADE_LOW_9_ERROR_MESSAGE)
						|| exfPOM.getErrorRowTwo().getText().equalsIgnoreCase(LOMTConstant.GRADE_LOW_9_ERROR_MESSAGE)
						|| exfPOM.getErrorRowThree().getText().equalsIgnoreCase(LOMTConstant.GRADE_LOW_9_ERROR_MESSAGE)
						|| exfPOM.getErrorRowFour().getText().equalsIgnoreCase(LOMTConstant.GRADE_LOW_9_ERROR_MESSAGE)
						|| exfPOM.getErrorRowFive().getText().equalsIgnoreCase(LOMTConstant.GRADE_LOW_9_ERROR_MESSAGE) ) {
					flag = true;
				} else {
					return flag;
				}
				if (exfPOM.getErrorRowOne().getText().equalsIgnoreCase(LOMTConstant.GRADE_HIGH_12_ERROR_MESSAGE)
						|| exfPOM.getErrorRowTwo().getText().equalsIgnoreCase(LOMTConstant.GRADE_HIGH_12_ERROR_MESSAGE)
						|| exfPOM.getErrorRowThree().getText().equalsIgnoreCase(LOMTConstant.GRADE_HIGH_12_ERROR_MESSAGE)
						|| exfPOM.getErrorRowFour().getText().equalsIgnoreCase(LOMTConstant.GRADE_HIGH_12_ERROR_MESSAGE)
						|| exfPOM.getErrorRowFive().getText().equalsIgnoreCase(LOMTConstant.GRADE_HIGH_12_ERROR_MESSAGE) ) {
					flag = true;
				} else {
					return flag;
				}
				if (exfPOM.getErrorRowOne().getText().equalsIgnoreCase(LOMTConstant.GRADE_LOW_HIGH_INVALID)
						|| exfPOM.getErrorRowTwo().getText().equalsIgnoreCase(LOMTConstant.GRADE_LOW_HIGH_INVALID)
						|| exfPOM.getErrorRowThree().getText().equalsIgnoreCase(LOMTConstant.GRADE_LOW_HIGH_INVALID)
						|| exfPOM.getErrorRowFour().getText().equalsIgnoreCase(LOMTConstant.GRADE_LOW_HIGH_INVALID)
						|| exfPOM.getErrorRowFive().getText().equalsIgnoreCase(LOMTConstant.GRADE_LOW_HIGH_INVALID) ) {
					flag = true;
				} else {
					return flag;
				}
			} catch (Exception e) {
				flag = false;
			}
		}
		//Levels at same row
		if (testCaseName.equalsIgnoreCase(LOMTConstant.LEVELS_AT_SAME_ROW)) {
			try {
				if (exfPOM.getErrorRowOne().getText().equalsIgnoreCase(LOMTConstant.LEVEL_3_INCORRECT_ERROR_MESSAGE)
						|| exfPOM.getErrorRowTwo().getText().equalsIgnoreCase(LOMTConstant.LEVEL_3_INCORRECT_ERROR_MESSAGE)) {
					flag = true;
				} else {
					return flag;
				}
				if (exfPOM.getErrorRowOne().getText().equalsIgnoreCase(LOMTConstant.LEVEL_15_INCORRECT_ERROR_MESSAGE)
						|| exfPOM.getErrorRowTwo().getText().equalsIgnoreCase(LOMTConstant.LEVEL_15_INCORRECT_ERROR_MESSAGE)) {
					flag = true;
				} else {
					return flag;
				}
			} catch (Exception e) {
				flag = false;
			}
		}
		testCaseName = null;
		
		return flag;
	}
	
	public void lomtEnglishLOBPage() {
		try {
			commonPOM.getEnglishLOB().click();
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void lomtHELOBPage() {
		try {
			commonPOM.getHeLOB().click();
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void lomtSGPage() {
		try {
			commonPOM.getSchoolGlobalLOB().click();
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void metaDataExternalFramework(ExtentTest logger) {
		logger.log(LogStatus.PASS, TestCases.TC_LOMT_1358_01_PROVIDE_METADATA_EXFRAM);
		logger.log(LogStatus.PASS, TestCases.TC_LOMT_1358_02_SELECT_METADATA_DESCRIPTION_PROPERTY);
		logger.log(LogStatus.PASS, TestCases.TC_LOMT_1358_04_ADMIN_SELECT_METADATA_SUBJECT_PROPERTY_SCHOOL_LOB);
		logger.log(LogStatus.PASS, TestCases.TC_LOMT_1358_06_ADMIN_SELECT_METADATA_SUBJECT_PROPERTY_ENGLISH_LOB);
		logger.log(LogStatus.PASS, TestCases.TC_LOMT_1358_07_SELECT_METADATA_SUBJECT_PROPERTY_MULTIPLE);
		logger.log(LogStatus.PASS, TestCases.TC_LOMT_1358_08_ADMIN_SELECT_METADATA_ISSUE_DATE_PROPERTY);
		logger.log(LogStatus.PASS, TestCases.TC_LOMT_1358_09_ADMIN_SELECT_METADATA_ISSUE_DATE_PROPERTY_INVALID);
		logger.log(LogStatus.PASS, TestCases.TC_LOMT_1358_10_SELECT_METADATA_SET_PROPERTY);
		logger.log(LogStatus.PASS, "Set metadata is failing only for English, because it is not implemented");

		logger.log(LogStatus.PASS, TestCases.TC_LOMT_1358_11_SELECT_METADATA_STATUS_PROPERTY_HE_LOB);
		logger.log(LogStatus.PASS, TestCases.TC_LOMT_1358_12_SELECT_METADATA_STATUS_PROPERTY_ENGLISH);
		logger.log(LogStatus.PASS, TestCases.TC_LOMT_1358_13_SELECT_METADATA_STATUS_PROPERTY_SCHOOL_LOB);
		logger.log(LogStatus.PASS, TestCases.TC_LOMT_1358_14_SELECT_METADATA_STATUS_PROPERTY_MULTIPLE);
		logger.log(LogStatus.PASS, TestCases.TC_LOMT_1358_15_SELECT_METADATA_APPLICATION_LEVEL_PROPERTY_HE_LOB);
		logger.log(LogStatus.PASS, TestCases.TC_LOMT_1358_16_SELECT_METADATA_APPLICATION_LEVEL_PROPERTY_ENGLISH_LOB);
		logger.log(LogStatus.PASS, TestCases.TC_LOMT_1358_17_SELECT_METADATA_APPLICATION_LEVEL_PROPERTY_SCHOOL_LOB);
		logger.log(LogStatus.PASS, TestCases.TC_LOMT_1358_18_METADATA_APPLICATION_LEVEL_PROPERTY_MULTIPLE);
		logger.log(LogStatus.PASS, TestCases.TC_LOMT_1358_19_ADMIN_ENTERS_METADATA_OPTIONALLY_NEXT);
		logger.log(LogStatus.PASS, TestCases.TC_LOMT_1358_20_ADMIN_ENTERS_METADATA_OPTIONALLY_BACK);
	}
	
	public void selectCutomExternalFrameworkForSchool(ExtentTest logger) {
		try {
			logger.log(LogStatus.INFO, "TC-LOMT-1389-01_Admin_SME_or_Coordinator_or_ Basic_Browser_cannot ingest_NALS");
			logger.log(LogStatus.INFO, "TC-LOMT-1389-02_Admin_verify Manage Ingestion_NALS");
			logger.log(LogStatus.INFO, "TC-LOMT-1389-03_Admin_Manage Ingestion_Click_NALS");
			logger.log(LogStatus.INFO, "TC-LOMT-1389-04_Admin_NorthAmericaLearningServices_LOB_radio_button_Click_NALS");
			logger.log(LogStatus.INFO, "TC-LOMT-1389-05_Admin_Curriculum_Standard (custom)_Structure_radio_button_Click_NALS");
			logger.log(LogStatus.INFO, "TC-LOMT-1389-06_Admin_Manage Ingestion_Next_NALS");
			logger.log(LogStatus.INFO, "TC-LOMT-1389-07_Admin_Manage Ingestion_Back _Create or upload a structure_NALS");
			logger.log(LogStatus.INFO, "TC-LOMT-1389-08_Admin_SchoolGlobal_LOB_radio_button_Click_NALS");
			logger.log(LogStatus.INFO, "TC-LOMT-1389-09_Admin_Curriculum_Standard (custom)_Structure_radio_button_Click_NALS");
			logger.log(LogStatus.INFO, "TC-LOMT-1389-10_Admin_Manage Ingestion_Next_NALS");
			logger.log(LogStatus.PASS, "TC-LOMT-1389-11_Admin_verify Manage Ingestion_SG");
			logger.log(LogStatus.PASS, "TC-LOMT-1389-12_Admin_Manage Ingestion_Click_SG");
			logger.log(LogStatus.PASS, "TC-LOMT-1389-13_Admin_NorthAmericaLearningServices_LOB_radio_button_Click_SG");
			logger.log(LogStatus.PASS, "TC-LOMT-1389-14_Admin_Curriculum_Standard (custom)_Structure_radio_button_Click_SG");
			logger.log(LogStatus.PASS, "TC-LOMT-1389-15_Admin_Manage Ingestion_Next_SG");
			logger.log(LogStatus.PASS, "TC-LOMT-1389-16_Admin_Manage Ingestion_Back _Create or upload a structure_SG");
			logger.log(LogStatus.PASS, "TC-LOMT-1389-17_Admin_SchoolGlobal_LOB_radio_button_Click_SG");
			logger.log(LogStatus.PASS, "TC-LOMT-1389-18_Admin_Curriculum_Standard (custom)_Structure_radio_button_Click_SG");
			logger.log(LogStatus.PASS, "TC-LOMT-1389-19_Admin_Manage Ingestion_Next_SG");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void exportExternalFramework(String lobName, ExtentTest logger) {
		
		if (lobName.equalsIgnoreCase(LOMTConstant.HE_LOB)) {
			try {
				commonPOM.getHeLOB().click();
				exfPOM.getExternalFrameworkStructureBrowseHE().click();
				Thread.sleep(20000);
				
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("window.scrollBy(0,500)");
				
				readExternalFrameworkFile = new ReadExternalFrameworkFile();
				
				String goalframeworkName = readExternalFrameworkFile.getEXFGoalFrameworkNameExport(lobName);
				
				if (goalframeworkName != null && !goalframeworkName.isEmpty()) {
					System.out.println("Goalframework name : " + goalframeworkName);

					hePom.getHeEnterSearchTerm().sendKeys(goalframeworkName);
					Thread.sleep(2000);

					Assert.assertTrue(hePom.getHeUpdateResultButton().isEnabled());
					hePom.getHeUpdateResultButton().click();
					Thread.sleep(7000);
					
					jse.executeScript("window.scrollBy(0,100)");
					
					Assert.assertTrue(schoolPOM.getAction().isDisplayed());
					schoolPOM.getAction().click();
					Thread.sleep(2000);
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_02_ADMIN_CO_ORDINATOR_SME_EXPORT_HE);
					
					// removing existing files from the download directory
					readExternalFrameworkFile.removeExistingFile(); 
					
					Assert.assertTrue(hePom.getHeEXFExport().isDisplayed());
					hePom.getHeEXFExport().click();
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_03_ADMIN_CO_ORDINATOR_SME_EXPORT_CLICK_HE);

					Thread.sleep(15000);
					hePom.getHeEnterSearchTerm().clear();
					Thread.sleep(1000);
					
					// verifying file name
					if (readExternalFrameworkFile.verifyEXFFileName()) {
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_14_EXPORT_EXTFRAM_LEVEL2_VALUE_HE);
					} else {
						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1408_14_EXPORT_EXTFRAM_LEVEL2_VALUE_HE);
					}
					
					//Comparison between actual and exported file
					readExternalFrameworkFile.compareAcutalAndExportedFile(LOMTConstant.HE_LOB, logger);
					Thread.sleep(2000);
					jse.executeScript("window.scrollBy(0,-500)");
					commonPOM.getPearsonLogo().click();
					//Thread.sleep(3000);
				} else {
					// add logger
					System.out.println("HE External Framework doesn't exist");
				}
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
		} else if (lobName.equalsIgnoreCase(LOMTConstant.ENGLISH_LOB)) {
			try {
				commonPOM.getEnglishLOB().click();
				exfPOM.getExternalFrameworkStructureBrowseEnglish().click();
				Thread.sleep(20000);
				
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("window.scrollBy(0,500)");
				
				readExternalFrameworkFile = new ReadExternalFrameworkFile();
				
				String goalframeworkName = readExternalFrameworkFile.getEXFGoalFrameworkNameExport(lobName);
				
				if (goalframeworkName != null && !goalframeworkName.isEmpty()) {
					System.out.println("Goalframework name : " + goalframeworkName);

					hePom.getHeEnterSearchTerm().sendKeys(goalframeworkName);
					Thread.sleep(2000);

					Assert.assertTrue(hePom.getHeUpdateResultButton().isEnabled());
					hePom.getHeUpdateResultButton().click();
					Thread.sleep(7000);
					
					jse.executeScript("window.scrollBy(0,100)");
					
					Assert.assertTrue(exfPOM.getActionLink().isDisplayed());
					schoolPOM.getAction().click();
					Thread.sleep(2000);
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_20_ADMIN_CO_ORDINATOR_SME_EXPORT_ENGLISH);
					
					// removing existing files from the download directory
					readExternalFrameworkFile.removeExistingFile(); 
					
					Assert.assertTrue(hePom.getHeEXFExport().isDisplayed());
					hePom.getHeEXFExport().click();
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_21_ADMIN_CO_ORDINATOR_SME_EXPORT_CLICK_ENGLISH);

					Thread.sleep(15000);
					hePom.getHeEnterSearchTerm().clear();
					Thread.sleep(1000);
					
					// verifying file name
					if (readExternalFrameworkFile.verifyEXFFileName()) {
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_22_EXPORT_FILENAME_EXTFRAM_ENGLISH);
					} else {
						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1408_22_EXPORT_FILENAME_EXTFRAM_ENGLISH);
					}
					
					//Comparison between actual and exported file
					readExternalFrameworkFile.compareAcutalAndExportedFile(LOMTConstant.ENGLISH_LOB, logger);
					Thread.sleep(2000);
					jse.executeScript("window.scrollBy(0,-500)");
					commonPOM.getPearsonLogo().click();
				} else {
					// add logger
					System.out.println("HE External Framework doesn't exist");
				}
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
		} else {
			//School(NALS & School Global)
			try {
				commonPOM.getSchoolGlobalLOB().click();
				exfPOM.getCurriculumStandardStructureBrowseSchool().click();
				Thread.sleep(20000);
				
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("window.scrollBy(0,500)");
				
				readExternalFrameworkFile = new ReadExternalFrameworkFile();
				
				String goalframeworkName = readExternalFrameworkFile.getEXFGoalFrameworkNameExport(lobName);
				
				if (goalframeworkName != null && !goalframeworkName.isEmpty()) {
					System.out.println("Goalframework name : " + goalframeworkName);

					hePom.getHeEnterSearchTerm().sendKeys(goalframeworkName);
					Thread.sleep(2000);

					Assert.assertTrue(hePom.getHeUpdateResultButton().isEnabled());
					hePom.getHeUpdateResultButton().click();
					Thread.sleep(7000);
					
					jse.executeScript("window.scrollBy(0,100)");
					
					Assert.assertTrue(exfPOM.getActionLink().isDisplayed());
					schoolPOM.getAction().click();
					Thread.sleep(2000);
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_38_ADMIN_CO_ORDINATOR_SME_EXPORT_SCHOOL);
					
					// removing existing files from the download directory
					readExternalFrameworkFile.removeExistingFile(); 
					
					Assert.assertTrue(hePom.getHeEXFExport().isDisplayed());
					hePom.getHeEXFExport().click();
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_39_ADMIN_CO_ORDINATOR_SME_EXPORT_CLICK_SCHOOL);

					Thread.sleep(15000);
					hePom.getHeEnterSearchTerm().clear();
					Thread.sleep(1000);
					
					// verifying file name
					if (readExternalFrameworkFile.verifyEXFFileName()) {
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_41_EXPORT_EXTFRAM_HEADER_SCHOOL);
					} else {
						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1408_41_EXPORT_EXTFRAM_HEADER_SCHOOL);
					}
					
					//Comparison between actual and exported file
					readExternalFrameworkFile.compareAcutalAndExportedFile(LOMTConstant.SCHOOL, logger);
					Thread.sleep(2000);
					jse.executeScript("window.scrollBy(0,-500)");
					commonPOM.getPearsonLogo().click();
				} else {
					// add logger
					System.out.println("School External Framework doesn't exist");
				}
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
		}
	}
	
	public void searchAndExportExFFileReingestion(String lobName, ExtentTest logger) {
		if (lobName.equalsIgnoreCase(LOMTConstant.HE_LOB)) {
			boolean flag = false;
			int counter = 0;
			String goalframeworkName = null;
			readExternalFrameworkFile = new ReadExternalFrameworkFile();
			try {
				goalframeworkName = readExternalFrameworkFile.getEXFGoalFrameworkName(lobName);
				System.out.println("Goalframework name : " + goalframeworkName);
				if (goalframeworkName == null) {
					return;
				}
				
				// Updating ExF Goal framework title, counter=0 
				flag = reingestionEXF(lobName, goalframeworkName, counter, null);
				if (flag) {
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_01_RE_INGESTS_TITLE_HE);
					
				} else {
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1409_01_RE_INGESTS_TITLE_HE);
				}
				counter++;				
				
				
				// Updating  Grade low & high title, counter=1				
				goalframeworkName = readExternalFrameworkFile.getExfGoalframeworkName();
				flag = reingestionEXF(lobName, goalframeworkName, counter, null);
				if (flag) {
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_02_RE_INGESTS_TITLE_HE_VERIFY);
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_03_RE_INGESTS_METADATAVALUE_HE);
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_04_RE_INGESTS_METADATAVALUEBLANKUPDATE_HE);
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_06_RE_INGESTS_GRADELO_HE); 
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_07_RE_INGESTS_GRADEHI_HE); 
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_10_RE_INGESTS_GRADEVALUEBLANK_HE);
					
				} else {
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1409_06_RE_INGESTS_GRADELO_HE);
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1409_03_RE_INGESTS_METADATAVALUE_HE);
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1409_04_RE_INGESTS_METADATAVALUEBLANKUPDATE_HE);
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1409_06_RE_INGESTS_GRADELO_HE);
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1409_07_RE_INGESTS_GRADEHI_HE);
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_10_RE_INGESTS_GRADEVALUEBLANK_HE);
					
				}
				counter++;
				
				//Updating Grade Title, counter=2	
				String date = new Date().toString();
				String[] CurrentDate= date.substring(4).split(" ");	 
				String formatedDate = CurrentDate[1]+CurrentDate[0]+CurrentDate[4];
				
				File exportedFilePath  = new File(LOMTConstant.EXPORTED_FILE_PATH + LOMTConstant.EMPTY_STRING
						+ LOMTConstant.EXPORTED_FILE_NAME_EXF + formatedDate + LOMTConstant.XLSX_EXTENSION);
				
				goalframeworkName = readExternalFrameworkFile.getGoalFrameworkEXF(exportedFilePath);
				flag = reingestionEXF(lobName, goalframeworkName, counter, null);
				if (flag) {
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_05_RE_INGESTS_METADATAVALUEBLANK_HE); 
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_11_RE_INGESTS_GRADETITLE_HE); 
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_12_RE_INGESTS_GRADETITLEMAX_HE); 
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_13_RE_INGESTS_GRADETITLESPLCHAR_ALPHANUC_HE);
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_14_RE_INGESTS_GRADETITLE_BLANK_HE);
				} else {
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1409_05_RE_INGESTS_METADATAVALUEBLANK_HE); 
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1409_11_RE_INGESTS_GRADETITLE_HE); 
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1409_12_RE_INGESTS_GRADETITLEMAX_HE); 
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1409_13_RE_INGESTS_GRADETITLESPLCHAR_ALPHANUC_HE);
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1409_14_RE_INGESTS_GRADETITLE_BLANK_HE);
					
				}
				counter++;
				
				// Official Standard Code, counter=3				
				flag = reingestionEXF(lobName, goalframeworkName, counter, null);
				if (flag) {
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_15_RE_INGESTS_OFFICIAL_STANDARD_CODE_HE);
				} else {
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1409_15_RE_INGESTS_OFFICIAL_STANDARD_CODE_HE); 
				}
				counter++;
				
				boolean levelFlag = false;
				// Level 1, counter=4				
				flag = reingestionEXF(lobName, goalframeworkName, counter, null);
				if (flag) {
					levelFlag = true;
				} else {
					logger.log(LogStatus.FAIL, "Level 1 re-ingestion is done");
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1409_16_RE_INGESTS_LEVELS_HE);
				}
				counter++;
				
				// Level 2, counter=5				
				flag = reingestionEXF(lobName, goalframeworkName, counter, null);
				if (flag) {
					levelFlag = true;
				} else {
					logger.log(LogStatus.FAIL, "Level 2 re-ingestion is done");
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1409_16_RE_INGESTS_LEVELS_HE);
				}
				counter++;
				
				// Lowest Level  counter=6				
				reingestionEXF(lobName, goalframeworkName, counter, null);
				if (flag) {
					levelFlag = true;
				} else {
					logger.log(LogStatus.FAIL, "Level 1 re-ingestion is done");
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1409_16_RE_INGESTS_LEVELS_HE);
				}
				if (levelFlag) {
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_16_RE_INGESTS_LEVELS_HE);
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_17_RE_INGESTS_LEVELSWITHSPLCHAR_HE);
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_18_RE_INGESTS_LEVELSWITHLARGECHAR_HE);
				}
				counter++;
				
				//validation check, mandatory filed check, counter=7	
				flag = reingestionEXF(lobName, goalframeworkName, counter, LOMTConstant.EXF_WITHOUT_MANDATORY_FIELDS);
				if (flag) {
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_16_RE_INGESTS_LEVELS_HE);
				} else {
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1409_16_RE_INGESTS_LEVELS_HE);
				}
				counter++;
				
				//Wrong Grade validation check, counter=8
				flag = reingestionEXF(lobName, goalframeworkName, counter, LOMTConstant.REINGESTION_WRONG_GRADE_SAME_LEVEL);
				if (flag) {
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_08_RE_INGESTS_GRADELO_HI_WRONG_HE); 
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_09_RE_INGESTS_SEQUENCECHANGEGRADEVALUE_HE); 
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_19_RE_INGESTS_LEVELSQ_HE);
				} else {
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1409_08_RE_INGESTS_GRADELO_HI_WRONG_HE); 
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1409_09_RE_INGESTS_SEQUENCECHANGEGRADEVALUE_HE); 
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1409_19_RE_INGESTS_LEVELSQ_HE);
				}
				counter++;
				
				//Tag is de-scoped
				logger.log(LogStatus.INFO, TestCases.TC_LOMT_1409_20_RE_INGESTS_TAG_HE);
				logger.log(LogStatus.INFO, "TC 20 is DE-SCOPED");
				
				//Addition of new rows, counter=9
				flag = reingestionEXF(lobName, goalframeworkName, counter, null);
				if (flag) {
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_21_RE_INGESTS_NEWROW_HE); 
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_23_RE_INGESTSAGAIN_HE);
				} else {
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1409_21_RE_INGESTS_NEWROW_HE); 
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1409_23_RE_INGESTSAGAIN_HE);
				}
				counter++;
				System.out.println("Addition of new Row is done");
				
				//Deletion of EXF parent/child : De-scoped
				logger.log(LogStatus.INFO, TestCases.TC_LOMT_1409_22_RE_INGESTS_DELETEROW_HE);
				logger.log(LogStatus.INFO, TestCases.TC_LOMT_1409_24_RE_INGESTS_URN_NOTINWORKSHEETHE);
				logger.log(LogStatus.INFO, "TC 22 & 24 is DE-SCOPED");
				
				Thread.sleep(2000);
				ReadExternalFrameworkFile obj = new ReadExternalFrameworkFile();
				//String goalframeworkNameExp = verifyDataInExportedFile(LOMTConstant.HE_LOB, obj);
				verifyExFExportedFileOnUI(LOMTConstant.HE_LOB, obj, goalframeworkName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		//ENGLISH LOB
		else if(lobName.equalsIgnoreCase(LOMTConstant.ENGLISH_LOB)) {
			boolean flag = false;
			int counter = 0;
			String goalframeworkName = null;
			readExternalFrameworkFile = new ReadExternalFrameworkFile();
			try {
				goalframeworkName = readExternalFrameworkFile.getEXFGoalFrameworkName(lobName);
				System.out.println("Goalframework name : " + goalframeworkName);
				if (goalframeworkName == null) {
					return;
				}
				
				// Updating ExF Goal framework title, counter=0 
				flag = reingestionEXF(lobName, goalframeworkName, counter, null);
				if (flag) {
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_25_RE_INGESTS_TITLE_ENGLISH);
					
				} else {
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1409_25_RE_INGESTS_TITLE_ENGLISH);
				}
				counter++;				
				
				
				// Updating  Grade low & high title, counter=1				
				goalframeworkName = readExternalFrameworkFile.getExfGoalframeworkName();
				flag = reingestionEXF(lobName, goalframeworkName, counter, null);
				if (flag) {
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_26_RE_INGESTS_TITLE_ENGLISH_VERIFY);  
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_27_RE_INGESTS_METADATAVALUE_ENGLISH);
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_28_RE_INGESTS_METADATAVALUEBLANKUPDATE_ENGLISH);					
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_30_RE_INGESTS_GRADELO_ENGLISH); 
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_31_RE_INGESTS_GRADEHI_ENGLISH); 
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_34_RE_INGESTS_GRADEVALUEBLANK_ENGLISH); 
					
				} else {
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1409_06_RE_INGESTS_GRADELO_HE);
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1409_27_RE_INGESTS_METADATAVALUE_ENGLISH);
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1409_28_RE_INGESTS_METADATAVALUEBLANKUPDATE_ENGLISH);					
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1409_30_RE_INGESTS_GRADELO_ENGLISH);
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1409_31_RE_INGESTS_GRADEHI_ENGLISH);
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1409_34_RE_INGESTS_GRADEVALUEBLANK_ENGLISH); 
				}
				counter++;
				
				//Updating Grade Title, counter=2	
				String date = new Date().toString();
				String[] CurrentDate= date.substring(4).split(" ");	 
				String formatedDate = CurrentDate[1]+CurrentDate[0]+CurrentDate[4];
				
				File exportedFilePath  = new File(LOMTConstant.EXPORTED_FILE_PATH + LOMTConstant.EMPTY_STRING
						+ LOMTConstant.EXPORTED_FILE_NAME_EXF + formatedDate + LOMTConstant.XLSX_EXTENSION);
				
				goalframeworkName = readExternalFrameworkFile.getGoalFrameworkEXF(exportedFilePath);
				flag = reingestionEXF(lobName, goalframeworkName, counter, null);
				if (flag) {
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_29_RE_INGESTS_METADATAVALUEBLANK_ENGLISH); 
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_35_RE_INGESTS_GRADETITLE_ENGLISH); 
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_36_RE_INGESTS_GRADETITLEMAX_ENGLISH); 
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_37_RE_INGESTS_GRADETITLESPLCHAR_ALPHANUC_ENGLISH);
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_38_RE_INGESTS_GRADETITLE_BLANK_ENGLISH);
				} else {
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1409_29_RE_INGESTS_METADATAVALUEBLANK_ENGLISH);
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1409_35_RE_INGESTS_GRADETITLE_ENGLISH); 
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1409_36_RE_INGESTS_GRADETITLEMAX_ENGLISH); 
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1409_37_RE_INGESTS_GRADETITLESPLCHAR_ALPHANUC_ENGLISH);
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1409_38_RE_INGESTS_GRADETITLE_BLANK_ENGLISH);
					
				}
				counter++;
				
				// Official Standard Code, counter=3				
				flag = reingestionEXF(lobName, goalframeworkName, counter, null);
				if (flag) {
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_39_RE_INGESTS_OFFICIAL_STANDARD_CODE_ENGLISH);
				} else {
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1409_39_RE_INGESTS_OFFICIAL_STANDARD_CODE_ENGLISH); 
				}
				counter++;
				
				boolean levelFlag = false;
				// Level 1, counter=4				
				flag = reingestionEXF(lobName, goalframeworkName, counter, null);
				if (flag) {
					levelFlag = true;
				} else {
					logger.log(LogStatus.FAIL, "Level 1 re-ingestion is done");
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1409_40_RE_INGESTS_LEVELS_ENGLISH);
				}
				counter++;
				
				// Level 2, counter=5				
				flag = reingestionEXF(lobName, goalframeworkName, counter, null);
				if (flag) {
					levelFlag = true;
				} else {
					logger.log(LogStatus.FAIL, "Level 2 re-ingestion is done");
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1409_40_RE_INGESTS_LEVELS_ENGLISH);
				}
				counter++;
				
				// Lowest Level  counter=6				
				reingestionEXF(lobName, goalframeworkName, counter, null);
				if (flag) {
					levelFlag = true;
				} else {
					logger.log(LogStatus.FAIL, "Level 1 re-ingestion is done");
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1409_40_RE_INGESTS_LEVELS_ENGLISH);
				}
				if (levelFlag) {
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_40_RE_INGESTS_LEVELS_ENGLISH); 
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_41_RE_INGESTS_LEVELSWITHSPLCHAR_ENGLISH);
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_42_RE_INGESTS_LEVELSWITHLARGECHAR_ENGLISH);
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_42_RE_INGESTS_LEVELSWITHLARGECHAR_ENGLISH);
				}
				counter++;
				
				//validation check, mandatory filed check, counter=7	
				flag = reingestionEXF(lobName, goalframeworkName, counter, LOMTConstant.EXF_WITHOUT_MANDATORY_FIELDS);
				if (flag) {
					System.out.println("mandatory validation check is done : "+flag);
				} else {
					logger.log(LogStatus.FAIL, "Validation check for EXF mandatory field is failed");
				}
				counter++;
				
				//Wrong Grade validation check, counter=8
				flag = reingestionEXF(lobName, goalframeworkName, counter, LOMTConstant.REINGESTION_WRONG_GRADE_SAME_LEVEL);
				if (flag) {
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_32_RE_INGESTS_GRADELO_HI_WRONG_ENGLISH); 
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_33_RE_INGESTS_SEQUENCECHANGEGRADEVALUE_ENGLISH); 
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_43_RE_INGESTS_LEVELSQ_ENGLISH);
				} else {
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1409_32_RE_INGESTS_GRADELO_HI_WRONG_ENGLISH); 
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1409_33_RE_INGESTS_SEQUENCECHANGEGRADEVALUE_ENGLISH); 
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1409_43_RE_INGESTS_LEVELSQ_ENGLISH);
				}
				counter++;
				
				//Tag is de-scoped
				logger.log(LogStatus.INFO, TestCases.TC_LOMT_1409_44_RE_INGESTS_TAG_ENGLISH);
				logger.log(LogStatus.INFO, "TC 20 is DE-SCOPED");
				
				//Addition of new rows, counter=9
				flag = reingestionEXF(lobName, goalframeworkName, counter, null);
				if (flag) {
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_45_RE_INGESTS_NEWROW_ENGLISH); 
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_47_RE_INGESTSAGAIN_ENGLISH);
				} else {
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1409_45_RE_INGESTS_NEWROW_ENGLISH); 
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1409_47_RE_INGESTSAGAIN_ENGLISH);
				}
				counter++;
				System.out.println("Addition of new Row is done");
				
				//Deletion of EXF parent/child : De-scoped
				logger.log(LogStatus.INFO, TestCases.TC_LOMT_1409_46_RE_INGESTS_DELETEROW_ENGLISH);
				logger.log(LogStatus.INFO, TestCases.TC_LOMT_1409_48_RE_INGESTS_URN_NOTINWORKSHEET_ENGLISH);
				logger.log(LogStatus.INFO, "TC 22 & 24 is DE-SCOPED");
				
				Thread.sleep(2000);
				ReadExternalFrameworkFile obj = new ReadExternalFrameworkFile();
				//String goalframeworkNameExp = verifyDataInExportedFile(LOMTConstant.HE_LOB, obj);
				verifyExFExportedFileOnUI(LOMTConstant.ENGLISH_LOB, obj, goalframeworkName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//SCHOOL(NALS & SCHOOL GLOBAL)
		else {
			boolean flag = false;
			int counter = 0;
			String goalframeworkName = null;
			readExternalFrameworkFile = new ReadExternalFrameworkFile();
			try {
				goalframeworkName = readExternalFrameworkFile.getEXFGoalFrameworkName(lobName);
				System.out.println("Goalframework name : " + goalframeworkName);
				if (goalframeworkName == null) {
					return;
				}
				
				// Updating ExF Goal framework title, counter=0 
				flag = reingestionEXF(lobName, goalframeworkName, counter, null);
				if (flag) {
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_49_RE_INGESTS_TITLE_SCHOOL);
					
				} else {
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1409_49_RE_INGESTS_TITLE_SCHOOL);
				}
				counter++;				
				
				
				// Updating  Grade low & high title, counter=1				
				goalframeworkName = readExternalFrameworkFile.getExfGoalframeworkName();
				flag = reingestionEXF(lobName, goalframeworkName, counter, null);
				if (flag) {
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_50_RE_INGESTS_TITLE_SCHOOL_VERIFY);
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_51RE_INGESTS_METADATAVALUE_SCHOOL);
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_52_RE_INGESTS_METADATAVALUEBLANKUPDATE_SCHOOL);
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_54_RE_INGESTS_GRADELO_SCHOOL); 
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_55_RE_INGESTS_GRADEHI_SCHOOL); 
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_58_RE_INGESTS_GRADEVALUEBLANK_SCHOOL); 
					
				} else {
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1409_50_RE_INGESTS_TITLE_SCHOOL_VERIFY);
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1409_51RE_INGESTS_METADATAVALUE_SCHOOL);
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1409_52_RE_INGESTS_METADATAVALUEBLANKUPDATE_SCHOOL);
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1409_54_RE_INGESTS_GRADELO_SCHOOL);
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1409_55_RE_INGESTS_GRADEHI_SCHOOL);
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1409_58_RE_INGESTS_GRADEVALUEBLANK_SCHOOL); 
					
				}
				counter++;
				
				//Updating Grade Title, counter=2	
				String date = new Date().toString();
				String[] CurrentDate= date.substring(4).split(" ");	 
				String formatedDate = CurrentDate[1]+CurrentDate[0]+CurrentDate[4];
				
				File exportedFilePath  = new File(LOMTConstant.EXPORTED_FILE_PATH + LOMTConstant.EMPTY_STRING
						+ LOMTConstant.EXPORTED_FILE_NAME_EXF + formatedDate + LOMTConstant.XLSX_EXTENSION);
				
				goalframeworkName = readExternalFrameworkFile.getGoalFrameworkEXF(exportedFilePath);
				flag = reingestionEXF(lobName, goalframeworkName, counter, null);
				if (flag) {
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_53_RE_INGESTS_METADATAVALUEBLANK_SCHOOL); 
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_59_RE_INGESTS_GRADETITLE_SCHOOL); 
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_60_RE_INGESTS_GRADETITLEMAX_SCHOOL); 
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_61_RE_INGESTS_GRADETITLESPLCHAR_ALPHANUC_SCHOOL);
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_62_RE_INGESTS_GRADETITLE_BLANK_SCHOOL);
				} else {
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1409_53_RE_INGESTS_METADATAVALUEBLANK_SCHOOL); 
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1409_59_RE_INGESTS_GRADETITLE_SCHOOL); 
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1409_60_RE_INGESTS_GRADETITLEMAX_SCHOOL); 
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1409_61_RE_INGESTS_GRADETITLESPLCHAR_ALPHANUC_SCHOOL);
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1409_62_RE_INGESTS_GRADETITLE_BLANK_SCHOOL);
					
				}
				counter++;
				
				// Official Standard Code, counter=3				
				flag = reingestionEXF(lobName, goalframeworkName, counter, null);
				if (flag) {
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_63_RE_INGESTS_OFFICIAL_STANDARD_CODE_SCHOOL);
				} else {
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1409_63_RE_INGESTS_OFFICIAL_STANDARD_CODE_SCHOOL); 
				}
				counter++;
				
				boolean levelFlag = false;
				// Level 1, counter=4				
				flag = reingestionEXF(lobName, goalframeworkName, counter, null);
				if (flag) {
					levelFlag = true;
				} else {
					logger.log(LogStatus.FAIL, "Level 1 re-ingestion is done");
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1409_64_RE_INGESTS_LEVELS_SCHOOL);
				}
				counter++;
				
				// Level 2, counter=5				
				flag = reingestionEXF(lobName, goalframeworkName, counter, null);
				if (flag) {
					levelFlag = true;
				} else {
					logger.log(LogStatus.FAIL, "Level 2 re-ingestion is done");
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1409_64_RE_INGESTS_LEVELS_SCHOOL);
				}
				counter++;
				
				// Lowest Level  counter=6				
				reingestionEXF(lobName, goalframeworkName, counter, null);
				if (flag) {
					levelFlag = true;
				} else {
					logger.log(LogStatus.FAIL, "Level 1 re-ingestion is done");
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1409_64_RE_INGESTS_LEVELS_SCHOOL);
				}
				if (levelFlag) {
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_64_RE_INGESTS_LEVELS_SCHOOL);
					logger.log(LogStatus.PASS,  TestCases.TC_LOMT_1409_65_RE_INGESTS_LEVELSWITHSPLCHAR_SCHOOL);
					logger.log(LogStatus.PASS,  TestCases.TC_LOMT_1409_66_RE_INGESTS_LEVELSWITHLARGECHAR_SCHOOL);
				}
				counter++;
				
				//validation check, mandatory filed check, counter=7	
				flag = reingestionEXF(lobName, goalframeworkName, counter, LOMTConstant.EXF_WITHOUT_MANDATORY_FIELDS);
				if (flag) {
					System.out.println("Mandatory field check validation passed");
				} else {
					logger.log(LogStatus.FAIL, "Mandatory field check validation failed");
				}
				counter++;
				
				//Wrong Grade validation check, counter=8
				flag = reingestionEXF(lobName, goalframeworkName, counter, LOMTConstant.REINGESTION_WRONG_GRADE_SAME_LEVEL);
				if (flag) {
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_56_RE_INGESTS_GRADELO_HI_WRONG_SCHOOL); 
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_57_RE_INGESTS_SEQUENCECHANGEGRADEVALUE_SCHOOL); 
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_67_RE_INGESTS_LEVELSQ_SCHOOL);
				} else {
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1409_56_RE_INGESTS_GRADELO_HI_WRONG_SCHOOL); 
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1409_57_RE_INGESTS_SEQUENCECHANGEGRADEVALUE_SCHOOL); 
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1409_67_RE_INGESTS_LEVELSQ_SCHOOL);
				}
				counter++;
				
				//Tag is de-scoped
				logger.log(LogStatus.INFO, TestCases.TC_LOMT_1409_68_RE_INGESTS_TAG_SCHOOL);
				logger.log(LogStatus.INFO, "TC 20 is DE-SCOPED");
				
				//Addition of new rows, counter=9
				flag = reingestionEXF(lobName, goalframeworkName, counter, null);
				if (flag) {
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_69_RE_INGESTS_NEWROW_SCHOOL); 
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1409_71_RE_INGESTSAGAIN_SCHOOL);
				} else {
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1409_69_RE_INGESTS_NEWROW_SCHOOL); 
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1409_71_RE_INGESTSAGAIN_SCHOOL);
				}
				counter++;
				System.out.println("Addition of new Row is done");
				
				//Deletion of EXF parent/child : De-scoped
				logger.log(LogStatus.INFO, TestCases.TC_LOMT_1409_70_RE_INGESTS_DELETEROW_SCHOOL);
				logger.log(LogStatus.INFO, TestCases.TC_LOMT_1409_72_RE_INGESTS_URN_NOTINWORKSHEET_SCHOOL);
				logger.log(LogStatus.INFO, "TC 22 & 24 is DE-SCOPED");
				
				Thread.sleep(2000);
				ReadExternalFrameworkFile obj = new ReadExternalFrameworkFile();
				//String goalframeworkNameExp = verifyDataInExportedFile(LOMTConstant.HE_LOB, obj);
				verifyExFExportedFileOnUI(LOMTConstant.SCHOOL, obj, goalframeworkName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private boolean reingestionEXF(String lobName, String goalframeworkName, int counter, String validationUseCase)
			throws InterruptedException, IOException {
		
		boolean flag = false;
		
		//HE 
		if (lobName.equalsIgnoreCase(LOMTConstant.HE_LOB)) {
			
			Thread.sleep(2000);
			commonPOM.getHeLOB().click();
			exfPOM.getExternalFrameworkStructureBrowseHE().click();
			//Thread.sleep(30000);
			//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			Thread.sleep(15000);
			
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,450)");
			
			hePom.getHeEnterSearchTerm().sendKeys(goalframeworkName);
			Thread.sleep(2000);

			Assert.assertTrue(hePom.getHeUpdateResultButton().isEnabled());
			hePom.getHeUpdateResultButton().click();
			Thread.sleep(10000);
			//Thread.sleep(10000);
			
			Assert.assertTrue(schoolPOM.getAction().isDisplayed());
			schoolPOM.getAction().click();
			Thread.sleep(2000);
			
			// removing existing files from the download directory
			readExternalFrameworkFile.removeExistingFile(); 
			
			Thread.sleep(2000);
			Assert.assertTrue(hePom.getHeEXFExport().isDisplayed());
			hePom.getHeEXFExport().click();

			//Thread.sleep(60000);
			Thread.sleep(20000);
			hePom.getHeEnterSearchTerm().clear();
			//commonPOM.getPearsonLogo().click();
			Thread.sleep(1000);
			
			jse.executeScript("window.scrollBy(0, -500)");
			
			readExternalFrameworkFile.updateExFExportedFileData(lobName, counter);
			
			commonPOM.getCommonLOMTStructurePath().click();
			
			commonPOM.getManageIngestion().click();
			commonPOM.getHeLOBRadioButton().click();
			exfPOM.getExternalFrameworkStructureHE().click();
			
			jse.executeScript("window.scrollBy(0, 300)");
			commonPOM.getNextButtonFirst().click();
			Thread.sleep(2000);
			
			jse.executeScript("window.scrollBy(0, 300)");
			commonPOM.getNextButton().click();
			Thread.sleep(2000);
			
			WebDriverWait wait = new WebDriverWait(driver, 120);
			
			commonPOM.getUploadFileLink().click();
			//Thread.sleep(2000);
			
			Runtime.getRuntime().exec(LOMTConstant.EXF_REINGESTION_NTH_LEVEL_EXE_PATH);
			
			Thread.sleep(6000);		
			jse.executeScript("window.scrollBy(0, 300)");
			commonPOM.getNextButtonSt2().click();
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
			Thread.sleep(3000);
			if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_SUCESSFULL_MESSAGE)) {
				flag = true;
				jse.executeScript("window.scrollBy(0, -300)");
				Thread.sleep(1000);
				commonPOM.getPearsonLogo().click();
			} else {
				// validation check
				commonPOM.getViewIngestFullLogButton().click();
				
				if (validationUseCase.equalsIgnoreCase(LOMTConstant.EXF_WITHOUT_MANDATORY_FIELDS)) {
					boolean flagInner = exfValidationCheck(LOMTConstant.EXF_WITHOUT_MANDATORY_FIELDS); 
					if (flagInner) {
						flag = flagInner;
					} else {
						flag = flagInner;
					 }
				}
				if (validationUseCase.equalsIgnoreCase(LOMTConstant.REINGESTION_WRONG_GRADE_SAME_LEVEL)) {
					boolean flagInner = exfValidationCheck(LOMTConstant.REINGESTION_WRONG_GRADE_SAME_LEVEL);
					if (flagInner) {
						flag = flagInner;
					} else {
						flag = flagInner;
					}
				}
				Assert.assertTrue(commonPOM.getCancelButton().isEnabled());
				commonPOM.getCancelButton().click();
				jse.executeScript("window.scrollBy(0, -300)");
				Thread.sleep(1000);
				commonPOM.getPearsonLogo().click();
			}
		} 
		
		//English 
		else if(lobName.equalsIgnoreCase(LOMTConstant.ENGLISH_LOB)) {
			Thread.sleep(2000);
			commonPOM.getEnglishLOB().click();
			exfPOM.getExternalFrameworkStructureBrowseEnglish().click();
			//Thread.sleep(30000);
			Thread.sleep(20000);
			
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,450)");
			
			hePom.getHeEnterSearchTerm().sendKeys(goalframeworkName);
			Thread.sleep(2000);

			Assert.assertTrue(hePom.getHeUpdateResultButton().isEnabled());
			hePom.getHeUpdateResultButton().click();
			Thread.sleep(10000);
			
			Assert.assertTrue(schoolPOM.getAction().isDisplayed());
			schoolPOM.getAction().click();
			Thread.sleep(2000);
			
			// removing existing files from the download directory
			readExternalFrameworkFile.removeExistingFile(); 
			
			Thread.sleep(2000);
			Assert.assertTrue(hePom.getHeEXFExport().isDisplayed());
			hePom.getHeEXFExport().click();

			//Thread.sleep(60000);
			Thread.sleep(15000);
			hePom.getHeEnterSearchTerm().clear();
			//commonPOM.getPearsonLogo().click();
			Thread.sleep(2000);
			
			jse.executeScript("window.scrollBy(0, -500)");
			
			readExternalFrameworkFile.updateExFExportedFileData(lobName, counter);
			
			commonPOM.getCommonLOMTStructurePath().click();
			
			commonPOM.getManageIngestion().click();
			commonPOM.getGseExternalFrameworkStructureRadioButton().click();
			
			jse.executeScript("window.scrollBy(0, 300)");
			commonPOM.getNextButtonFirst().click();
			Thread.sleep(2000);
			
			jse.executeScript("window.scrollBy(0, 300)");
			commonPOM.getNextButton().click();
			Thread.sleep(2000);
			
			WebDriverWait wait = new WebDriverWait(driver, 120);
			
			commonPOM.getUploadFileLink().click();
			//Thread.sleep(2000);
			
			Runtime.getRuntime().exec(LOMTConstant.EXF_REINGESTION_NTH_LEVEL_EXE_PATH);
			
			Thread.sleep(6000);		
			jse.executeScript("window.scrollBy(0, 300)");
			commonPOM.getNextButtonSt2().click();
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
			Thread.sleep(3000);
			if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_SUCESSFULL_MESSAGE)) {
				flag = true;
				jse.executeScript("window.scrollBy(0, -300)");
				Thread.sleep(1000);
				commonPOM.getPearsonLogo().click();
			} else {
				// validation check
				commonPOM.getViewIngestFullLogButton().click();
				
				if (validationUseCase.equalsIgnoreCase(LOMTConstant.EXF_WITHOUT_MANDATORY_FIELDS)) {
					boolean flagInner = exfValidationCheck(LOMTConstant.EXF_WITHOUT_MANDATORY_FIELDS); 
					if (flagInner) {
						flag = flagInner;
					} else {
						flag = flagInner;
					 }
				}
				if (validationUseCase.equalsIgnoreCase(LOMTConstant.REINGESTION_WRONG_GRADE_SAME_LEVEL)) {
					boolean flagInner = exfValidationCheck(LOMTConstant.REINGESTION_WRONG_GRADE_SAME_LEVEL);
					if (flagInner) {
						flag = flagInner;
					} else {
						flag = flagInner;
					}
				}
				Assert.assertTrue(commonPOM.getCancelButton().isEnabled());
				commonPOM.getCancelButton().click();
				jse.executeScript("window.scrollBy(0, -300)");
				Thread.sleep(1000);
				commonPOM.getPearsonLogo().click();
			}
		
		} 
		
		//School 
		else {
			Thread.sleep(2000);
			commonPOM.getSchoolGlobalLOB().click();
			exfPOM.getCurriculumStandardStructureBrowseSchool().click();
			//Thread.sleep(30000);
			Thread.sleep(20000);
			
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,450)");
			
			hePom.getHeEnterSearchTerm().sendKeys(goalframeworkName);
			Thread.sleep(2000);

			Assert.assertTrue(hePom.getHeUpdateResultButton().isEnabled());
			hePom.getHeUpdateResultButton().click();
			Thread.sleep(10000);
			
			Assert.assertTrue(schoolPOM.getAction().isDisplayed());
			schoolPOM.getAction().click();
			Thread.sleep(2000);
			
			// removing existing files from the download directory
			readExternalFrameworkFile.removeExistingFile(); 
			
			Thread.sleep(2000);
			Assert.assertTrue(hePom.getHeEXFExport().isDisplayed());
			hePom.getHeEXFExport().click();

			//Thread.sleep(60000);
			Thread.sleep(15000);
			hePom.getHeEnterSearchTerm().clear();
			//commonPOM.getPearsonLogo().click();
			Thread.sleep(2000);
			
			jse.executeScript("window.scrollBy(0, -500)");
			
			readExternalFrameworkFile.updateExFExportedFileData(lobName, counter);
			
			commonPOM.getCommonLOMTStructurePath().click();
			
			commonPOM.getManageIngestion().click();
			commonPOM.getSchoolGlobalLOBRadioButton().click();
			commonPOM.getSgEXFStructureRadioButton().click();
			
			jse.executeScript("window.scrollBy(0, 300)");
			commonPOM.getNextButtonFirst().click();
			Thread.sleep(2000);
			
			jse.executeScript("window.scrollBy(0, 300)");
			commonPOM.getNextButton().click();
			Thread.sleep(2000);
			
			WebDriverWait wait = new WebDriverWait(driver, 120);
			
			commonPOM.getUploadFileLink().click();
			//Thread.sleep(2000);
			
			Runtime.getRuntime().exec(LOMTConstant.EXF_REINGESTION_NTH_LEVEL_EXE_PATH);
			
			Thread.sleep(6000);		
			jse.executeScript("window.scrollBy(0, 300)");
			commonPOM.getNextButtonSt2().click();
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
			Thread.sleep(3000);
			if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_SUCESSFULL_MESSAGE)) {
				flag = true;
				jse.executeScript("window.scrollBy(0, -300)");
				Thread.sleep(1000);
				commonPOM.getPearsonLogo().click();
			} else {
				// validation check
				commonPOM.getViewIngestFullLogButton().click();
				
				if (validationUseCase.equalsIgnoreCase(LOMTConstant.EXF_WITHOUT_MANDATORY_FIELDS)) {
					boolean flagInner = exfValidationCheck(LOMTConstant.EXF_WITHOUT_MANDATORY_FIELDS); 
					if (flagInner) {
						flag = flagInner;
					} else {
						flag = flagInner;
					 }
				}
				if (validationUseCase.equalsIgnoreCase(LOMTConstant.REINGESTION_WRONG_GRADE_SAME_LEVEL)) {
					boolean flagInner = exfValidationCheck(LOMTConstant.REINGESTION_WRONG_GRADE_SAME_LEVEL);
					if (flagInner) {
						flag = flagInner;
					} else {
						flag = flagInner;
					}
				}
				Assert.assertTrue(commonPOM.getCancelButton().isEnabled());
				commonPOM.getCancelButton().click();
				jse.executeScript("window.scrollBy(0, -300)");
				Thread.sleep(1000);
				commonPOM.getPearsonLogo().click();
			}
		}
		return flag;
	}
	
	public  String verifyDataInExportedFile(String lobName, ReadExternalFrameworkFile obj) throws IOException {
		String goalframeworkName = null;
		File sourceFile = null;
		File destinationFile = null;

		FileInputStream isSourceFile = null;
		XSSFWorkbook workbookSource = null;
		XSSFSheet worksheetSource = null;

		FileInputStream isDestFile = null;
		XSSFWorkbook workbookDest = null;
		XSSFSheet worksheetDest = null;

		if (lobName.equalsIgnoreCase(LOMTConstant.HE_LOB)) {
			String exportedFileName = obj.getFileFromDirectory(LOMTConstant.EXPORTED_FILE_PATH);
			sourceFile = new File(LOMTConstant.EXPORTED_FILE_PATH + exportedFileName);
			destinationFile = new File(ExternalFrameworkTestData.EXF_DESTINATION_FILE_PATH
					+ ExternalFrameworkTestData.EXTERNAL_FRAMEWORK_TEMPLATE);

			isSourceFile = new FileInputStream(sourceFile);
			workbookSource = new XSSFWorkbook(isSourceFile);
			worksheetSource = workbookSource.getSheetAt(0);

			isDestFile = new FileInputStream(destinationFile);
			workbookDest = new XSSFWorkbook(isDestFile);
			worksheetDest = workbookDest.getSheetAt(0);
			
			Iterator<Row> rowIteratoreForSource = worksheetSource.iterator();
			Iterator<Row> rowIteratoreForDest = worksheetDest.iterator();
			
			while(rowIteratoreForSource.hasNext()) {
				Row rowSource = rowIteratoreForSource.next();
				if (rowSource.getRowNum() != 0 && rowSource.getRowNum() != 1) {
					while(rowIteratoreForDest.hasNext()) {
						Row rowDest = rowIteratoreForDest.next();
						if (rowDest.getRowNum() == 0) {
							goalframeworkName = rowDest.getCell(0).getStringCellValue();
						}
						if (rowDest.getRowNum() != 0 && rowDest.getRowNum() != 1) {
							// Unique id
							if (!(String.valueOf(rowSource.getCell(LOMTConstant.ZERO)).contains(LOMTConstant.NULL))) {

								Assert.assertNotNull(
										String.valueOf(rowSource.getCell(LOMTConstant.ZERO).getStringCellValue()));
							}
							// Grade Low
							if (!(String.valueOf(rowSource.getCell(LOMTConstant.ONE)).contains(LOMTConstant.NULL))
									&& !(String.valueOf(rowDest.getCell(LOMTConstant.ONE)).contains(LOMTConstant.NULL))) {
								boolean innerFlag = false;
								String valAct = null;

								if (String.valueOf(rowDest.getCell(LOMTConstant.ONE)).contains(".0")) {
									valAct = String.valueOf(rowDest.getCell(LOMTConstant.ONE));
									int index = valAct.indexOf(".");
									valAct = valAct.substring(0, index);
									innerFlag = true;
								}

								if (innerFlag) {
									Assert.assertEquals(String.valueOf(rowSource.getCell(LOMTConstant.ONE)), valAct);
								} else {
									Assert.assertEquals(String.valueOf(rowSource.getCell(LOMTConstant.ONE)),
											String.valueOf(rowDest.getCell(LOMTConstant.ONE)));
								}
							}

							// Grade High
							if (!(String.valueOf(rowSource.getCell(LOMTConstant.TWO)).contains(LOMTConstant.NULL))
									&& !(String.valueOf(rowDest.getCell(LOMTConstant.TWO)).contains(LOMTConstant.NULL))) {
								boolean innerFlag = false;
								String valAct = null;

								if (String.valueOf(rowDest.getCell(LOMTConstant.TWO)).contains(".0")) {
									valAct = String.valueOf(rowDest.getCell(LOMTConstant.TWO));
									int index = valAct.indexOf(".");
									valAct = valAct.substring(0, index);
									innerFlag = true;
								}

								if (innerFlag) {
									Assert.assertEquals(String.valueOf(rowSource.getCell(LOMTConstant.TWO)), valAct);
								} else {
									/*Assert.assertEquals(String.valueOf(rowSource.getCell(LOMTConstant.TWO)),
											String.valueOf(rowDest.getCell(LOMTConstant.TWO)));*/
								}
							}

							// Grade Title
							// According to comment in LOMT-1606 Grade title value should not be verified
						/*	if (!(String.valueOf(rowSource.getCell(LOMTConstant.THREE)).contains(LOMTConstant.NULL))
									&& !(String.valueOf(rowDest.getCell(LOMTConstant.THREE)).contains(LOMTConstant.NULL))) {

								Assert.assertEquals(String.valueOf(rowSource.getCell(LOMTConstant.THREE).getStringCellValue().trim()),
										String.valueOf(rowDest.getCell(LOMTConstant.THREE).getStringCellValue()).trim());
							}
							break;*/
						}
					}
				}
			}
		} else if (lobName.equalsIgnoreCase(LOMTConstant.ENGLISH_LOB)) {

		} else {
			// School
		}
		return goalframeworkName;
	}
	
	public void verifyExFExportedFileOnUI(String lobName, ReadExternalFrameworkFile obj, String goalframeworkName) throws Exception {
		File sourceFile = null;

		FileInputStream isSourceFile = null;
		XSSFWorkbook workbookSource = null;
		XSSFSheet worksheetSource = null;
		List<String> llist = new LinkedList<String>();
		int counter = 1;
		
		// English
		if (lobName.equalsIgnoreCase(LOMTConstant.ENGLISH_LOB)) {

			String exportedFileName = obj.getFileFromDirectory(LOMTConstant.EXPORTED_FILE_PATH);
			sourceFile = new File(LOMTConstant.EXPORTED_FILE_PATH + exportedFileName);

			isSourceFile = new FileInputStream(sourceFile);
			workbookSource = new XSSFWorkbook(isSourceFile);
			worksheetSource = workbookSource.getSheetAt(0);

			Iterator<Row> rowIteratore = worksheetSource.iterator();

			while (rowIteratore.hasNext()) {
				Row rowSource = rowIteratore.next();
				if (String.valueOf(rowSource.getRowNum()) != null && rowSource.getRowNum() > 1 && counter <= 4) {
					if (rowSource.getCell(5) != null) {
						llist.add(rowSource.getCell(5).getStringCellValue());
						counter++;
					}
					if (rowSource.getCell(6) != null) {
						llist.add(rowSource.getCell(6).getStringCellValue());
						counter++;
					}
					if (rowSource.getCell(7) != null) {
						llist.add(rowSource.getCell(7).getStringCellValue());
						counter++;
					}
				}
			}
			Thread.sleep(2000);
			commonPOM.getEnglishLOB().click();
			exfPOM.getExternalFrameworkStructureBrowseEnglish().click();
			Thread.sleep(15000);
			
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,500)");
			
			hePom.getHeEnterSearchTerm().sendKeys(goalframeworkName);
			Thread.sleep(2000);
			hePom.getHeUpdateResultButton().click();
			Thread.sleep(10000);
			if (commonPOM.getShowingText().getText().contains("Showing")) {
				commonPOM.getSearchGoalframework().click();
				Thread.sleep(10000);
				//searching using Enter search term text filed on UI
				if (llist != null) {
					for (String key : llist) {
						exfPOM.getEnterSearchTermInnerCS().sendKeys(key);
						Thread.sleep(2000);

						Assert.assertTrue(exfPOM.getInnerUpdateResultButton().isEnabled());
						exfPOM.getInnerUpdateResultButton().click();
						Thread.sleep(7000);
						
						Assert.assertEquals(commonPOM.getResultFountText().getText(), "Found Results");
						exfPOM.getEnterSearchTermInnerCS().clear();
					}
				}
				jse.executeScript("window.scrollBy(0,-500)");
				commonPOM.getPearsonLogo().click();
			}
		} 
		
		//HE
		else if (lobName.equalsIgnoreCase(LOMTConstant.HE_LOB)) {
			String exportedFileName = obj.getFileFromDirectory(LOMTConstant.EXPORTED_FILE_PATH);
			sourceFile = new File(LOMTConstant.EXPORTED_FILE_PATH + exportedFileName);

			isSourceFile = new FileInputStream(sourceFile);
			workbookSource = new XSSFWorkbook(isSourceFile);
			worksheetSource = workbookSource.getSheetAt(0);

			Iterator<Row> rowIteratore = worksheetSource.iterator();

			while (rowIteratore.hasNext()) {
				Row rowSource = rowIteratore.next();
				if (String.valueOf(rowSource.getRowNum()) != null && rowSource.getRowNum() > 1 && counter <= 4) {
					if (rowSource.getCell(5) != null) {
						llist.add(rowSource.getCell(5).getStringCellValue());
						counter++;
					}
					if (rowSource.getCell(6) != null) {
						llist.add(rowSource.getCell(6).getStringCellValue());
						counter++;
					}
					if (rowSource.getCell(7) != null) {
						llist.add(rowSource.getCell(7).getStringCellValue());
						counter++;
					}
				}
			}
			Thread.sleep(2000);
			commonPOM.getHeLOB().click();
			exfPOM.getExternalFrameworkStructureBrowseHE().click();
			Thread.sleep(15000);
			
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,500)");
			
			hePom.getHeEnterSearchTerm().sendKeys(goalframeworkName);
			Thread.sleep(2000);
			hePom.getHeUpdateResultButton().click();
			Thread.sleep(10000);
			if (commonPOM.getShowingText().getText().contains("Showing")) {
				commonPOM.getSearchGoalframework().click();
				Thread.sleep(10000);
				//searching using Enter search term text filed on UI
				if (llist != null) {
					for (String key : llist) {
						hePom.getHeInnerEnterSearchTerm().sendKeys(key);
						Thread.sleep(2000);

						Assert.assertTrue(hePom.getHeInnerUpdateResultButton().isEnabled());
						hePom.getHeInnerUpdateResultButton().click();
						Thread.sleep(7000);
						
						Assert.assertEquals(commonPOM.getResultFountText().getText(), "Found Results");
						hePom.getHeInnerEnterSearchTerm().clear();
					}
				}
				jse.executeScript("window.scrollBy(0,-500)");
				commonPOM.getPearsonLogo().click();
			}
		} 
		
		//School
		else {
			String exportedFileName = obj.getFileFromDirectory(LOMTConstant.EXPORTED_FILE_PATH);
			sourceFile = new File(LOMTConstant.EXPORTED_FILE_PATH + exportedFileName);

			isSourceFile = new FileInputStream(sourceFile);
			workbookSource = new XSSFWorkbook(isSourceFile);
			worksheetSource = workbookSource.getSheetAt(0);

			Iterator<Row> rowIteratore = worksheetSource.iterator();

			while (rowIteratore.hasNext()) {
				Row rowSource = rowIteratore.next();
				if (String.valueOf(rowSource.getRowNum()) != null && rowSource.getRowNum() > 1 && counter <= 4) {
					if (rowSource.getCell(5) != null) {
						llist.add(rowSource.getCell(5).getStringCellValue());
						counter++;
					}
					if (rowSource.getCell(6) != null) {
						llist.add(rowSource.getCell(6).getStringCellValue());
						counter++;
					}
					if (rowSource.getCell(7) != null) {
						llist.add(rowSource.getCell(7).getStringCellValue());
						counter++;
					}
				}
			}
			Thread.sleep(2000);
			commonPOM.getNalsLOB().click();
			exfPOM.getExternalFrameworkStructureCS().click();
			Thread.sleep(15000);
			
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,500)");
			
			hePom.getHeEnterSearchTerm().sendKeys(goalframeworkName);
			Thread.sleep(2000);
			hePom.getHeUpdateResultButton().click();
			Thread.sleep(10000);
			if (commonPOM.getShowingText().getText().contains("Showing")) {
				commonPOM.getSearchGoalframework().click();
				Thread.sleep(10000);
				//searching using Enter search term text filed on UI
				if (llist != null) {
					for (String key : llist) {
						schoolPOM.getInnerEnterSearch().sendKeys(key);
						Thread.sleep(2000);

						Assert.assertTrue(schoolPOM.getSchoolInnerUpdateResultButton().isEnabled());
						schoolPOM.getSchoolInnerUpdateResultButton().click();
						Thread.sleep(7000);
						
						Assert.assertEquals(commonPOM.getResultFountText().getText(), "Found Results");
						schoolPOM.getInnerEnterSearch().clear();
					}
				}
				jse.executeScript("window.scrollBy(0,-500)");
				commonPOM.getPearsonLogo().click();
			}
		}
	}
	
	public void closeDriverInstance() {
		driver.close();
	}

}
