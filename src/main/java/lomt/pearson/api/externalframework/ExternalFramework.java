package lomt.pearson.api.externalframework;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import lomt.pearson.constant.LOMTConstant;
import lomt.pearson.constant.TestCases;
import lomt.pearson.page_object.CommonPOM;
import lomt.pearson.page_object.EnglishPOM;
import lomt.pearson.page_object.ExternalFrameworkPOM;
import lomt.pearson.page_object.HEPom;
import lomt.pearson.page_object.Login;
import lomt.pearson.page_object.NALSPom;
import lomt.pearson.page_object.SGPom;

/**
 * TODO : add logger	
 * @author ram.sin
 *
 */
public class ExternalFramework /*extends BaseClass*/ {
	
	/*private String environment = LoadPropertiesFile.getPropertiesValues(LOMTConstant.LOMT_ENVIRONMENT);
	private String userName = LoadPropertiesFile.getPropertiesValues(LOMTConstant.USER_NAME);
	private String pwd = LoadPropertiesFile.getPropertiesValues(LOMTConstant.PASSWORD);
	private String userNameBasic = LoadPropertiesFile.getPropertiesValues(LOMTConstant.USER_NAME_BASIC);
	private String pwdBasic = LoadPropertiesFile.getPropertiesValues(LOMTConstant.PASSWORD_BASIC);*/
	
	
	String currentLOB = null;

	private WebDriver driver;

	private CommonPOM commonPOM = null;
	private HEPom hePom = null;
	private NALSPom nalsPom  = null;
	private SGPom sgPom = null;
	private EnglishPOM englishPOM = null;
	
	private ExternalFrameworkPOM externalFrameworkPOM = null;
	
	private ExternalFrameworkPOM exfPOM = null;
	
	public ExternalFramework(WebDriver driver) {
		this.driver = driver;
		
		commonPOM = new CommonPOM(driver);
		hePom = new HEPom(driver);
		nalsPom = new NALSPom(driver);
		sgPom = new SGPom(driver);
		englishPOM = new EnglishPOM(driver);
		exfPOM = new ExternalFrameworkPOM(driver);
		externalFrameworkPOM = new ExternalFrameworkPOM(driver);
	}
	
	/*public void getDriverInstance(WebDriver driver) {
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
		englishPOM = new EnglishPOM(driver);
		exfPOM = new ExternalFrameworkPOM(driver);
	}*/

	/*public void login() {
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
	}*/
	
	// Logout feature is not available below PPE environment
	/*public void loginNonAdminRole() {
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
	}*/
	
	
	public ExternalFramework() {
		// no argument constructor
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
			Assert.assertTrue(commonPOM.getHeLOB().isDisplayed());
			currentLOB = commonPOM.getHeLOB().getText();
			commonPOM.getHeLOB().click();
			if (commonPOM.getManageIngestion().getText().equalsIgnoreCase(LOMTConstant.MANGE_INGESTION)) {
				Assert.assertTrue(commonPOM.getManageIngestion().isDisplayed());

				logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_02_ADMIN_VERIFY_MANAGE_INGESTION);

				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("window.scrollBy(0,500)");
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
				Assert.assertTrue(commonPOM.getNalsLOBRadioButton().isDisplayed());
				
				logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_33_ADMIN_ENGLISH_LOB_RADIO_BUTTON_CLICK);
				Thread.sleep(2000);
				// Right side message validation before Structure select
				Assert.assertTrue(commonPOM.getFirstTextImage().isDisplayed());
				Assert.assertEquals(commonPOM.getFirstTextMessage().getText(), LOMTConstant.CHOOSE_STRUCTURE_TYPE);
				Assert.assertTrue(englishPOM.getSecondTextImage().isDisplayed());
				Assert.assertEquals(englishPOM.getSecondTextEnglish().getText(),LOMTConstant.PROVIDE_ENGLISH_METADASTA);
				Assert.assertTrue(commonPOM.getThirdTextImage().isDisplayed());
				Assert.assertEquals(commonPOM.getThirdTextMessage().getText(), LOMTConstant.UPLOAD_YOUR_FILE);
				Assert.assertTrue(commonPOM.getFourthTextImage().isDisplayed());
				Assert.assertEquals(commonPOM.getFourthTextMessage().getText(),LOMTConstant.STRUCTURE_PAGE_IMAGE_4_TEXT);
				
				// Structure radio button selection
				Assert.assertEquals(commonPOM.getSelectStructureTitle().getText(), LOMTConstant.SELECT_STRUCTURE_TYPE);

				Assert.assertTrue(commonPOM.getGseStructureRadioButton().isEnabled());
				Assert.assertTrue(commonPOM.getProductExternalFrameworkStructureRadioButton().isEnabled());
				Assert.assertTrue(commonPOM.getGseProductStructureRadioButton().isEnabled());
				
				commonPOM.getEnglishExternalFrameworkStructureRadioButton().click();
				logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_34_ADMIN_EXTERNALFRAMEWORK_STRUCTURE_RADIO_BUTTON_CLICK_ENGLISH);
				Thread.sleep(1000);
				// right side message validation after Structure selected
				Assert.assertTrue(englishPOM.getSecondTextImage().isDisplayed());
				Assert.assertEquals(englishPOM.getSecondTextEnglish().getText(), LOMTConstant.EXF_TEXT);
				Assert.assertTrue(commonPOM.getNextButtonFirst().isEnabled());

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
				Assert.assertTrue(commonPOM.getNalsLOBRadioButton().isDisplayed());
				
				commonPOM.getHeLOBRadioButton().click();
				Thread.sleep(2000);
				logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_04_ADMIN_HIGHER_EDUCATION_LOB_RADIO_BUTTON_CLICK_HE);
				
				// Right side message validation before Structure select
				Assert.assertTrue(commonPOM.getFirstTextImage().isDisplayed());
				Assert.assertEquals(commonPOM.getFirstTextMessage().getText(), LOMTConstant.CHOOSE_STRUCTURE_TYPE);
				Assert.assertTrue(englishPOM.getSecondTextImage().isDisplayed());
				Assert.assertEquals(englishPOM.getSecondTextEnglish().getText(),LOMTConstant.META_DATA_HE);
				Assert.assertTrue(commonPOM.getThirdTextImage().isDisplayed());
				Assert.assertEquals(commonPOM.getThirdTextMessage().getText(), LOMTConstant.UPLOAD_YOUR_FILE);
				Assert.assertTrue(commonPOM.getFourthTextImage().isDisplayed());
				Assert.assertEquals(commonPOM.getFourthTextMessage().getText(),LOMTConstant.STRUCTURE_PAGE_IMAGE_4_TEXT);
				
				// Structure radio button selection
				Assert.assertEquals(commonPOM.getSelectStructureTitle().getText(), LOMTConstant.SELECT_STRUCTURE_TYPE);

				Assert.assertTrue(commonPOM.getGseStructureRadioButton().isEnabled());
				Assert.assertTrue(commonPOM.getProductExternalFrameworkStructureRadioButton().isEnabled());
				Assert.assertTrue(commonPOM.getGseProductStructureRadioButton().isEnabled());
				
				exfPOM.getExternalFrameworkStructureHE().click();
				logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_05_ADMIN_EXTERNALFRAMEWORK_STRUCTURE_RADIO_BUTTON_CLICK_HE);
				
				Thread.sleep(2000);
				// right side message validation after Structure selected
				Assert.assertTrue(englishPOM.getSecondTextImage().isDisplayed());
				Assert.assertEquals(englishPOM.getSecondTextEnglish().getText(), LOMTConstant.META_DATA_HE_EXF);
				Assert.assertTrue(commonPOM.getNextButtonFirst().isEnabled());

				commonPOM.getNextButtonFirst().click(); 
				logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_06_ADMIN_MANAGE_INGESTION_NEXT_HE);
				Thread.sleep(2000);
			} else {
				// School 
				// Header, Footer
				Assert.assertTrue(commonPOM.getBackLinkFirst().isDisplayed());
				Assert.assertEquals(commonPOM.getCreateUploadStructureHeader().getText(),LOMTConstant.CREATE_STRUCTURE_TILE);
				Assert.assertFalse(commonPOM.getNextButtonFirst().isEnabled());

				// LOB radio button selection, by default English lob is selected
				Assert.assertEquals(commonPOM.getSelectLOBTitle().getText(), LOMTConstant.SELECT_LOB);
				
				Assert.assertTrue(commonPOM.getEnglishLOBRadioButton().isDisplayed());
				Assert.assertTrue(commonPOM.getHeLOBRadioButton().isDisplayed());
				Assert.assertTrue(commonPOM.getSchoolGlobalLOBRadioButton().isDisplayed());
				Assert.assertTrue(commonPOM.getNalsLOBRadioButton().isDisplayed());
				
				commonPOM.getNalsLOBRadioButton().click();
				Thread.sleep(2000);
				
				// Right side message validation before Structure select
				Assert.assertTrue(commonPOM.getFirstTextImage().isDisplayed());
				Assert.assertEquals(commonPOM.getFirstTextMessage().getText(), LOMTConstant.CHOOSE_STRUCTURE_TYPE);
				Assert.assertTrue(englishPOM.getSecondTextImage().isDisplayed());
				Assert.assertEquals(englishPOM.getSecondTextEnglish().getText(),LOMTConstant.PROVIDE_METADATA_NALS_EXF);
				Assert.assertTrue(commonPOM.getThirdTextImage().isDisplayed());
				Assert.assertEquals(commonPOM.getThirdTextMessage().getText(), LOMTConstant.UPLOAD_YOUR_FILE);
				Assert.assertTrue(commonPOM.getFourthTextImage().isDisplayed());
				Assert.assertEquals(commonPOM.getFourthTextMessage().getText(),LOMTConstant.STRUCTURE_PAGE_IMAGE_4_TEXT);
				
				// Structure radio button selection
				Assert.assertEquals(commonPOM.getSelectStructureTitle().getText(), LOMTConstant.SELECT_STRUCTURE_TYPE);

				Assert.assertTrue(commonPOM.getCurriculumStandardRadioButton().isEnabled());
				Assert.assertTrue(commonPOM.getNalsEXFStructureRadioButton().isEnabled());
				Assert.assertTrue(commonPOM.getSchoolProductStructureRadioButton().isEnabled());
				Assert.assertTrue(commonPOM.getSchoolIntermeidaryStructureRadioButton().isEnabled());
				
				commonPOM.getNalsEXFStructureRadioButton().click();
				logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_63_ADMIN_CURRICULUM_STANDARD_CUSTOM_STRUCTURE_RADIO_BUTTON_CLICK_SCHOOL);
				
				Thread.sleep(2000);
				// right side message validation after Structure selected
				Assert.assertTrue(englishPOM.getSecondTextImage().isDisplayed());
				Assert.assertEquals(englishPOM.getSecondTextEnglish().getText(), LOMTConstant.EXF_STRUCTURE_SELECTION_MESSAGE_NALS_2);
				Assert.assertTrue(commonPOM.getNextButtonFirst().isEnabled());

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
			if (currentLOB.equalsIgnoreCase(LOMTConstant.HE_LOB)) {
				if (commonPOM.getBackLinkSec().isDisplayed()) {
					commonPOM.getBackLinkSec().click();
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_07_ADMIN_MANAGE_INGESTION_BACK__CREATE_OR_UPLOAD_A_STRUCTURE_HE);
				} else {
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1357_07_ADMIN_MANAGE_INGESTION_BACK__CREATE_OR_UPLOAD_A_STRUCTURE_HE);
					commonPOM.getPearsonLogo().click();
					Thread.sleep(1000);
					commonPOM.getHeLOB().click();
					currentLOB = commonPOM.getHeLOB().getText();
				}
			} else if (currentLOB.equalsIgnoreCase(LOMTConstant.ENGLISH_LOB)) {
				if (commonPOM.getBackLinkSec().isDisplayed()) {
					commonPOM.getBackLinkSec().click();
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_36_ADMIN_MANAGE_INGESTION_BACK_CREATE_OR_UPLOAD_A_STRUCTURE_ENGLISH);
				} else {
					logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1357_36_ADMIN_MANAGE_INGESTION_BACK_CREATE_OR_UPLOAD_A_STRUCTURE_ENGLISH);
					commonPOM.getPearsonLogo().click();
					Thread.sleep(1000);
					commonPOM.getEnglishLOB().click();
					currentLOB = commonPOM.getEnglishLOB().getText();
				}
			} else {
				//school
				if (commonPOM.getBackLinkSec().isDisplayed()) {
					commonPOM.getBackLinkSec().click();
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_65_ADMIN_MANAGE_INGESTION_BACK_CREATE_OR_UPLOAD_A_STRUCTURE_SCHOOL);
				} else {
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
			if (currentLOB.equalsIgnoreCase(LOMTConstant.HE_LOB)) {
				commonPOM.getBackLinkFirst().click();
				Thread.sleep(1000);
				commonPOM.getHeLOBRadioButton().click();
				exfPOM.getExternalFrameworkStructureHE().click();
				commonPOM.getNextButtonFirst().click();
				Thread.sleep(1000);
			} else if (currentLOB.equalsIgnoreCase(LOMTConstant.ENGLISH_LOB)) {
				Thread.sleep(1000);
				commonPOM.getBackLinkFirst().click();
				commonPOM.getEnglishExternalFrameworkStructureRadioButton().click();
				commonPOM.getNextButtonFirst().click();
				Thread.sleep(1000);
			} else {
				commonPOM.getBackLinkFirst().click();
				Thread.sleep(1000);
				commonPOM.getSchoolGlobalLOBRadioButton().click();
				commonPOM.getSgEXFStructureRadioButton().click();
				commonPOM.getNextButtonFirst().click();
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getLOBAndStructure() {
		try {
			if (currentLOB.equalsIgnoreCase(LOMTConstant.HE_LOB)) {
				Thread.sleep(1000);
				commonPOM.getHeLOBRadioButton().click();
				exfPOM.getExternalFrameworkStructureHE().click();
				commonPOM.getNextButtonFirst().click();
				Thread.sleep(1000);
			} else if (currentLOB.equalsIgnoreCase(LOMTConstant.ENGLISH_LOB)) {
				Thread.sleep(1000);
				commonPOM.getEnglishExternalFrameworkStructureRadioButton().click();
				commonPOM.getNextButtonFirst().click();
				Thread.sleep(1000);
			} else {
				//School
				Thread.sleep(1000);
				commonPOM.getSchoolGlobalLOBRadioButton().click();
				Thread.sleep(1000);
				commonPOM.getSgEXFStructureRadioButton().click();
				commonPOM.getNextButtonFirst().click();
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createUploadStructureMetaDataPageExf(ExtentTest logger) {
		try {
			if (currentLOB.equalsIgnoreCase(LOMTConstant.ENGLISH_LOB)) {
				//Header and Footer
				Assert.assertTrue(commonPOM.getBackLinkSec().isDisplayed());
				Assert.assertEquals(commonPOM.getCreateUploadStructureHeader2().getText(), LOMTConstant.CREATE_STRUCTURE_TILE);
				Assert.assertTrue(commonPOM.getNextButton().isEnabled());  
				Assert.assertTrue(commonPOM.getBackButton().isEnabled()); 
				
				// Right side message validation before Structure select
				Assert.assertTrue(commonPOM.getFirstTextImage().isDisplayed());
				Assert.assertEquals(commonPOM.getFirstTextMessage().getText(), LOMTConstant.CHOOSE_STRUCTURE_TYPE);
				Assert.assertTrue(englishPOM.getSecondTextImage().isDisplayed());
				Assert.assertEquals(englishPOM.getSecondTextEnglish().getText(), LOMTConstant.EXF_TEXT);
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
						if (element.getText().equalsIgnoreCase(LOMTConstant.GSE)) {
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
				
				//Application Level drop-down selection
				exfPOM.getApplicationLevelDropdown().click();
				Thread.sleep(1000);
				
				List<WebElement> applicationLevelList = exfPOM.getApplicationLevelList();
				int applicationLevelLength = applicationLevelList.size();
				if (applicationLevelLength > 0) {
					for (int i = 0; i <= applicationLevelLength; i++) {
						WebElement element = applicationLevelList.get(i);
						if (element.getText().equalsIgnoreCase(LOMTConstant.APPLICATION_LEVEL_ENGLISH)) {
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
						if (element.getText().equalsIgnoreCase(LOMTConstant.PURPOSE_ENGLISH)) {
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
						if (element.getText().equalsIgnoreCase(LOMTConstant.DEFINED_BY_ENGLISH)) {
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
						if (element.getText().equalsIgnoreCase(LOMTConstant.ANTHROPOLOGY_SUBJECT_METADATA_HE)) {
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
				
				//Application Level drop-down selection
				exfPOM.getApplicationLevelDropdown().click();
				Thread.sleep(1000);
				
				List<WebElement> applicationLevelList = exfPOM.getApplicationLevelList();
				int applicationLevelLength = applicationLevelList.size();
				if (applicationLevelLength > 0) {
					for (int i = 0; i <= applicationLevelLength; i++) {
						WebElement element = applicationLevelList.get(i);
						if (element.getText().equalsIgnoreCase(LOMTConstant.APPLICATION_LEVEL__METADATA_HE)) {
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
						if (element.getText().equalsIgnoreCase(LOMTConstant.PURPOSE_METADATA_HE)) {
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
						if (element.getText().equalsIgnoreCase(LOMTConstant.DEFINED_BY_METADATA_HE)) {
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
				Thread.sleep(1000);
				
				//SUBJECT Selection
				exfPOM.getSubjectDropdown().click();
				Thread.sleep(2000);
				List<WebElement> subjectList = exfPOM.getSubjectList();
				int subjectLength = subjectList.size();
				if (subjectLength > 0) {
					for (int i = 0; i <= subjectLength; i++) {
						WebElement element = subjectList.get(i);
						if (element.getText().equalsIgnoreCase(LOMTConstant.SCHOOL_SUBJECT)) {
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
						if (element.getText().equalsIgnoreCase(LOMTConstant.PURPOSE_METADATA_HE)) {
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
						if (element.getText().equalsIgnoreCase(LOMTConstant.DEFINED_BY_METADATA_School)) {
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
			//logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_38_WITHOUT_VALUE_NEXTBTN_ENGLSH_LOB);
			commonPOM.getNextButton().click();
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void externalFrameworkIngestion(ExtentTest logger, String rules) {
		try {
			if (currentLOB.equalsIgnoreCase(LOMTConstant.HE_LOB)) {
				WebDriverWait wait = new WebDriverWait(driver, 60);
				//Header and Footer
				Assert.assertTrue(commonPOM.getBackLinkFirst().isDisplayed());
				Assert.assertEquals(commonPOM.getCreateUploadStructureHeader().getText(), LOMTConstant.CREATE_STRUCTURE_TILE);
				// Center
				//Assert.assertTrue(commonPOM.getPlusSign().isDisplayed());
				Assert.assertEquals(commonPOM.getDragAndDropFilesText().getText(),LOMTConstant.DRAG_DROP_TEXT);
				Assert.assertTrue(commonPOM.getUploadFileLink().isDisplayed());
				
				// Right side message validation before Structure select
				Assert.assertTrue(commonPOM.getFirstTextImage().isDisplayed());
				Assert.assertEquals(commonPOM.getFirstTextMessage().getText(), LOMTConstant.CHOOSE_STRUCTURE_TYPE);
				Assert.assertTrue(englishPOM.getSecondTextImage().isDisplayed());
				Assert.assertEquals(englishPOM.getSecondTextEnglish().getText(), LOMTConstant.META_DATA_HE_EXF);
				Assert.assertTrue(commonPOM.getThirdTextImage().isDisplayed());
				Assert.assertEquals(commonPOM.getThirdTextMessage().getText(), LOMTConstant.UPLOAD_YOUR_XLS_FILE);
				Assert.assertTrue(commonPOM.getFourthTextImage().isDisplayed());
				Assert.assertEquals(commonPOM.getFourthTextMessage().getText(),LOMTConstant.STRUCTURE_PAGE_IMAGE_4_TEXT);
				
				commonPOM.getUploadFileLink().click();
				
				// TC-37 & TC-39, all the mandatory and non-mandatory along-with meta data
				if (rules.equalsIgnoreCase(LOMTConstant.EXF_ALL_FIELDS)) {
					Runtime.getRuntime().exec(LOMTConstant.EXF_ALL_FIELDS_XLS_PATH);
					
					Thread.sleep(3000);
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_10_UPLOAD_FILE_XLS_OR_XLSX_EXFRAM_HE);
					commonPOM.getNextButtonSt2().click();
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_11_INGEST_VALID_EXFRAM_HE);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
					Thread.sleep(3000);
					if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_SUCESSFULL_MESSAGE)) {
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_12_ADMIN_VERIFY_INGEST_SUCESS_MESSAGE_ON_REVIEW_OUTCOME_HE);
						
						// Right side message validation before Structure select
						Assert.assertTrue(commonPOM.getFirstTextImage().isDisplayed());
						Assert.assertEquals(commonPOM.getFirstTextMessage().getText(), LOMTConstant.CHOOSE_STRUCTURE_TYPE);
						Assert.assertTrue(englishPOM.getSecondTextImage().isDisplayed());
						Assert.assertEquals(englishPOM.getSecondTextEnglish().getText(), LOMTConstant.META_DATA_HE_EXF);
						Assert.assertTrue(commonPOM.getThirdTextImage().isDisplayed());
						Assert.assertEquals(commonPOM.getThirdTextMessage().getText(), LOMTConstant.UPLOAD_YOUR_XLS_FILE);
						Assert.assertTrue(commonPOM.getFourthTextImage().isDisplayed());
						Assert.assertEquals(commonPOM.getFourthTextMessage().getText(),LOMTConstant.STRUCTURE_PAGE_IMAGE_4_TEXT);
						
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
					
					Thread.sleep(3000);
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
					Thread.sleep(3000);
					// switch back to base window
					driver.switchTo().alert().accept();
					Thread.sleep(2000);
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_16_UPLOAD_INGESTION_SHEET_FORMAT_DOCS_XML_TXT_HE);
				}
				//TC-46, TC-47, TC-48, TC-49
				if (rules.equalsIgnoreCase(LOMTConstant.EXF_WITHOUT_MANDATORY_FIELDS)) {
					Runtime.getRuntime().exec(LOMTConstant.EXF_WITHOUT_MANDATORY_FIELDS_XLS_PATH);
					
					Thread.sleep(3000);
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
					
					Thread.sleep(3000);
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
					
					Thread.sleep(3000);
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
					
					Thread.sleep(3000);
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
					
					Thread.sleep(3000);
					commonPOM.getNextButtonSt2().click();
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
					Thread.sleep(3000);
					if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_SUCESSFULL_MESSAGE)) {
						System.out.println("Ingestion 10th level successful");
						commonPOM.getDoneButton().click();
						commonPOM.getBackLinkFirst().click();
						Thread.sleep(1000);
						 logger.log(LogStatus.PASS, LOMTConstant.INGESTION_SUCCESSFUL_NTH_LEVEL);
				  } else {
						logger.log(LogStatus.FAIL, LOMTConstant.INGESTION_SUCCESSFUL_NTH_LEVEL);
					}
				} 
			} else if (currentLOB.equalsIgnoreCase(LOMTConstant.ENGLISH_LOB)) {
				WebDriverWait wait = new WebDriverWait(driver, 60);
				//Header and Footer
				Assert.assertTrue(commonPOM.getBackLinkFirst().isDisplayed());
				Assert.assertEquals(commonPOM.getCreateUploadStructureHeader().getText(), LOMTConstant.CREATE_STRUCTURE_TILE);
				// Center
				//Assert.assertTrue(commonPOM.getPlusSign().isDisplayed());
				Assert.assertEquals(commonPOM.getDragAndDropFilesText().getText(),LOMTConstant.DRAG_DROP_TEXT);
				Assert.assertTrue(commonPOM.getUploadFileLink().isDisplayed());
				
				// Right side message validation before Structure select
				Assert.assertTrue(commonPOM.getFirstTextImage().isDisplayed());
				Assert.assertEquals(commonPOM.getFirstTextMessage().getText(), LOMTConstant.CHOOSE_STRUCTURE_TYPE);
				Assert.assertTrue(englishPOM.getSecondTextImage().isDisplayed());
				Assert.assertEquals(englishPOM.getSecondTextEnglish().getText(), LOMTConstant.EXF_TEXT);
				Assert.assertTrue(commonPOM.getThirdTextImage().isDisplayed());
				Assert.assertEquals(commonPOM.getThirdTextMessage().getText(), LOMTConstant.UPLOAD_YOUR_XLS_FILE);
				Assert.assertTrue(commonPOM.getFourthTextImage().isDisplayed());
				Assert.assertEquals(commonPOM.getFourthTextMessage().getText(),LOMTConstant.STRUCTURE_PAGE_IMAGE_4_TEXT);
				
				commonPOM.getUploadFileLink().click();
				
				// TC-37 & TC-39, all the mandatory and non-mandatory along-with meta data
				if (rules.equalsIgnoreCase(LOMTConstant.EXF_ALL_FIELDS)) {
					Runtime.getRuntime().exec(LOMTConstant.EXF_ALL_FIELDS_XLS_PATH);
					
					Thread.sleep(3000);
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_39_UPLOAD_FILE_XLS_OR_XLSX_EXFRAM_ENGLISH);
					commonPOM.getNextButtonSt2().click();
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_40_INGEST_VALID_EXFRAM_ENGLISH);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
					Thread.sleep(3000);
					if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_SUCESSFULL_MESSAGE)) {
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_41_ADMIN_VERIFY_INGEST_SUCESS_MESSAGE_ON_REVIEW_OUTCOME_ENGLISH);
						
						// Right side message validation before Structure select
						Assert.assertTrue(commonPOM.getFirstTextImage().isDisplayed());
						Assert.assertEquals(commonPOM.getFirstTextMessage().getText(), LOMTConstant.CHOOSE_STRUCTURE_TYPE);
						Assert.assertTrue(englishPOM.getSecondTextImage().isDisplayed());
						Assert.assertEquals(englishPOM.getSecondTextEnglish().getText(), LOMTConstant.EXF_TEXT);
						Assert.assertTrue(commonPOM.getThirdTextImage().isDisplayed());
						Assert.assertEquals(commonPOM.getThirdTextMessage().getText(), LOMTConstant.UPLOAD_YOUR_XLS_FILE);
						Assert.assertTrue(commonPOM.getFourthTextImage().isDisplayed());
						Assert.assertEquals(commonPOM.getFourthTextMessage().getText(),LOMTConstant.STRUCTURE_PAGE_IMAGE_4_TEXT);
						
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
					
					Thread.sleep(3000);
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
					Thread.sleep(3000);
					// switch back to base window
					driver.switchTo().alert().accept();
					Thread.sleep(2000);
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_45_UPLOAD_INGESTION_SHEET_FORMAT_DOCS_XML_TXT_ENGLISH);
				}
				//TC-46, TC-47, TC-48, TC-49
				if (rules.equalsIgnoreCase(LOMTConstant.EXF_WITHOUT_MANDATORY_FIELDS)) {
					Runtime.getRuntime().exec(LOMTConstant.EXF_WITHOUT_MANDATORY_FIELDS_XLS_PATH);
					
					Thread.sleep(3000);
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
					
					Thread.sleep(3000);
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
					
					Thread.sleep(3000);
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
					
					Thread.sleep(3000);
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
					
					Thread.sleep(3000);
					commonPOM.getNextButtonSt2().click();
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
					Thread.sleep(3000);
					if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_SUCESSFULL_MESSAGE)) {
						System.out.println("Ingestion 10th level successful");
						commonPOM.getDoneButton().click();
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
				//Header and Footer
				Assert.assertTrue(commonPOM.getBackLinkFirst().isDisplayed());
				Assert.assertEquals(commonPOM.getCreateUploadStructureHeader().getText(), LOMTConstant.CREATE_STRUCTURE_TILE);
				// Center
				//Assert.assertTrue(commonPOM.getPlusSign().isDisplayed());
				Assert.assertEquals(commonPOM.getDragAndDropFilesText().getText(),LOMTConstant.DRAG_DROP_TEXT);
				Assert.assertTrue(commonPOM.getUploadFileLink().isDisplayed());
				
				//Right side message validation before Structure select
				Assert.assertTrue(commonPOM.getFirstTextImage().isDisplayed());
				Assert.assertEquals(commonPOM.getFirstTextMessage().getText(), LOMTConstant.CHOOSE_STRUCTURE_TYPE);
				Assert.assertTrue(englishPOM.getSecondTextImage().isDisplayed());
				Assert.assertEquals(englishPOM.getSecondTextEnglish().getText(), LOMTConstant.EXF_STRUCTURE_SELECTION_MESSAGE_SG_2);
				Assert.assertTrue(commonPOM.getThirdTextImage().isDisplayed());
				Assert.assertEquals(commonPOM.getThirdTextMessage().getText(), LOMTConstant.UPLOAD_YOUR_XLS_FILE);
				Assert.assertTrue(commonPOM.getFourthTextImage().isDisplayed());
				Assert.assertEquals(commonPOM.getFourthTextMessage().getText(),LOMTConstant.STRUCTURE_PAGE_IMAGE_4_TEXT);
				
				commonPOM.getUploadFileLink().click();
				
				// TC-37 & TC-39, all the mandatory and non-mandatory along-with meta data
				if (rules.equalsIgnoreCase(LOMTConstant.EXF_ALL_FIELDS)) {
					Runtime.getRuntime().exec(LOMTConstant.EXF_ALL_FIELDS_XLS_PATH);
					
					Thread.sleep(3000);
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_68_UPLOAD_FILE_XLS_OR_XLSX_CURRSTANCUSTOM_SCHOOL);
					commonPOM.getNextButtonSt2().click();
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_69_INGEST_VALID_CURRSTANCUSTOM_SCHOOL);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
					Thread.sleep(3000);
					if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_SUCESSFULL_MESSAGE)) {
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_70_ADMIN_VERIFY_INGEST_SUCESS_MESSAGE_ON_REVIEW_OUTCOME_CURRSTANCUSTOM_SCHOOL);
						
						// Right side message validation before Structure select
						Assert.assertTrue(commonPOM.getFirstTextImage().isDisplayed());
						Assert.assertEquals(commonPOM.getFirstTextMessage().getText(), LOMTConstant.CHOOSE_STRUCTURE_TYPE);
						Assert.assertTrue(englishPOM.getSecondTextImage().isDisplayed());
						Assert.assertEquals(englishPOM.getSecondTextEnglish().getText(), LOMTConstant.EXF_STRUCTURE_SELECTION_MESSAGE_SG_2);
						Assert.assertTrue(commonPOM.getThirdTextImage().isDisplayed());
						Assert.assertEquals(commonPOM.getThirdTextMessage().getText(), LOMTConstant.UPLOAD_YOUR_XLS_FILE);
						Assert.assertTrue(commonPOM.getFourthTextImage().isDisplayed());
						Assert.assertEquals(commonPOM.getFourthTextMessage().getText(),LOMTConstant.STRUCTURE_PAGE_IMAGE_4_TEXT);
						
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
					
					Thread.sleep(3000);
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
					Thread.sleep(3000);
					// switch back to base window
					driver.switchTo().alert().accept();
					Thread.sleep(2000);
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_74_UPLOAD_INGESTION_SHEET_FORMAT_DOCS_XML_TXT_SCHOOL);
				}
				//TC-46, TC-47, TC-48, TC-49
				if (rules.equalsIgnoreCase(LOMTConstant.EXF_WITHOUT_MANDATORY_FIELDS)) {
					Runtime.getRuntime().exec(LOMTConstant.EXF_WITHOUT_MANDATORY_FIELDS_XLS_PATH);
					
					Thread.sleep(3000);
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
					
					Thread.sleep(3000);
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
					
					Thread.sleep(3000);
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
					
					Thread.sleep(3000);
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
					Thread.sleep(3000);
					if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_SUCESSFULL_MESSAGE)) {
						System.out.println("Ingestion 10th level successful");
						commonPOM.getDoneButton().click();
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
			try {
				System.out.println("currentLOB : "+currentLOB);
				exfPOM.getExternalFrameworkStructureBrowseHE().click();
				Thread.sleep(20000);

				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("window.scrollBy(0,500)");
				
				//1st External Framework Title Search
				exfPOM.getEnterSearchTerm().sendKeys(LOMTConstant.EXF_INGESTION_FILE_NAME_1);
				Thread.sleep(1000);

				Assert.assertTrue(exfPOM.getUpdateResultButton().isEnabled());
				exfPOM.getUpdateResultButton().click();
				
				Thread.sleep(10000);
				Assert.assertEquals(LOMTConstant.EXF_INGESTION_FILE_NAME_1, exfPOM.getSearchedEXFTitle().getText());
				
				//2nd External Framework Title Search
				exfPOM.getEnterSearchTerm().clear();
				Thread.sleep(1000);
				exfPOM.getEnterSearchTerm().sendKeys(LOMTConstant.INGESTION_FILE_10TH_LEVEL);
				Thread.sleep(1000);

				Assert.assertTrue(exfPOM.getUpdateResultButton().isEnabled());
				exfPOM.getUpdateResultButton().click();
				
				Thread.sleep(10000);
				Assert.assertEquals(LOMTConstant.INGESTION_FILE_10TH_LEVEL, exfPOM.getSearchedEXFTitle().getText());
				
				logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_14_ADMIN_VERIFY_INGESTED_EXFRAM_UI_HE);
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
				exfPOM.getEnterSearchTerm().sendKeys(LOMTConstant.EXF_INGESTION_FILE_NAME_1);
				Thread.sleep(1000);

				Assert.assertTrue(exfPOM.getUpdateResultButton().isEnabled());
				exfPOM.getUpdateResultButton().click();
				
				Thread.sleep(10000);
				Assert.assertEquals(LOMTConstant.EXF_INGESTION_FILE_NAME_1, exfPOM.getSearchedEXFTitle().getText());
				
				//2nd External Framework Title Search
				exfPOM.getEnterSearchTerm().clear();
				Thread.sleep(1000);
				exfPOM.getEnterSearchTerm().sendKeys(LOMTConstant.INGESTION_FILE_10TH_LEVEL);
				Thread.sleep(1000);

				Assert.assertTrue(exfPOM.getUpdateResultButton().isEnabled());
				exfPOM.getUpdateResultButton().click();
				
				Thread.sleep(10000);
				Assert.assertEquals(LOMTConstant.INGESTION_FILE_10TH_LEVEL, exfPOM.getSearchedEXFTitle().getText());
				
				logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_43_ADMIN_VERIFY_INGESTED_EXFRAM_UI_ENGLISH_LOB);
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
				exfPOM.getEnterSearchTerm().sendKeys(LOMTConstant.EXF_INGESTION_FILE_NAME_1);
				Thread.sleep(1000);

				Assert.assertTrue(exfPOM.getUpdateResultButton().isEnabled());
				exfPOM.getUpdateResultButton().click();
				
				Thread.sleep(10000);
				Assert.assertEquals(LOMTConstant.EXF_INGESTION_FILE_NAME_1, exfPOM.getSearchedEXFTitle().getText());
				
				//2nd External Framework Title Search
				exfPOM.getEnterSearchTerm().clear();
				Thread.sleep(1000);
				exfPOM.getEnterSearchTerm().sendKeys(LOMTConstant.INGESTION_FILE_10TH_LEVEL);
				Thread.sleep(1000);

				Assert.assertTrue(exfPOM.getUpdateResultButton().isEnabled());
				exfPOM.getUpdateResultButton().click();
				
				Thread.sleep(10000);
				Assert.assertEquals(LOMTConstant.INGESTION_FILE_10TH_LEVEL, exfPOM.getSearchedEXFTitle().getText());
				
				//3RD External Framework Title Search
				exfPOM.getEnterSearchTerm().clear();
				Thread.sleep(1000);
				exfPOM.getEnterSearchTerm().sendKeys(LOMTConstant.INGESTION_NON_MAND);
				Thread.sleep(1000);

				Assert.assertTrue(exfPOM.getUpdateResultButton().isEnabled());
				exfPOM.getUpdateResultButton().click();
				
				Thread.sleep(10000);
				Assert.assertEquals(LOMTConstant.INGESTION_NON_MAND, exfPOM.getSearchedEXFTitle().getText());
				
				logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_72_ADMIN_VERIFY_INGESTED_CURRSTANCUSTOM_UI_SCHOOL);
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
						|| exfPOM.getErrorRowThree().getText().equalsIgnoreCase(LOMTConstant.EXTERNAL_FRAMEWORK_TITLE_ERROR_MESSAGE)) {
					flag = true;
					System.out.println("####### 1 ");
				} else {
					return flag;
				}
				if (exfPOM.getErrorRowOne().getText().equalsIgnoreCase(LOMTConstant.OFFICIAL_STANDARD_CODE_ERROR_MESSAGE_3)
						|| exfPOM.getErrorRowTwo().getText().equalsIgnoreCase(LOMTConstant.OFFICIAL_STANDARD_CODE_ERROR_MESSAGE_3)
						|| exfPOM.getErrorRowThree().getText().equalsIgnoreCase(LOMTConstant.OFFICIAL_STANDARD_CODE_ERROR_MESSAGE_3)) {
					flag = true;
				} else {
					return flag;
				}
				if (exfPOM.getErrorRowOne().getText().equalsIgnoreCase(LOMTConstant.LEVEL_COLUMN_1_ERROR_MESSAGE)
						|| exfPOM.getErrorRowTwo().getText().equalsIgnoreCase(LOMTConstant.LEVEL_COLUMN_1_ERROR_MESSAGE)
						|| exfPOM.getErrorRowThree().getText().equalsIgnoreCase(LOMTConstant.LEVEL_COLUMN_1_ERROR_MESSAGE)) {
					flag = true;
				} else {
					return flag;
				}
			}
		} catch (Exception e) {
			flag = false;
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
		logger.log(LogStatus.INFO, TestCases.TC_LOMT_1358_10_SELECT_METADATA_SET_PROPERTY);
		logger.log(LogStatus.INFO, "Set metadata is failing only for English, because it is not implemented");

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
			logger.log(LogStatus.PASS, "TC-LOMT-1389-01_Admin_SME_or_Coordinator_or_ Basic_Browser_cannot ingest_NALS");
			logger.log(LogStatus.PASS, "TC-LOMT-1389-02_Admin_verify Manage Ingestion_NALS");
			logger.log(LogStatus.PASS, "TC-LOMT-1389-03_Admin_Manage Ingestion_Click_NALS");
			logger.log(LogStatus.PASS, "TC-LOMT-1389-04_Admin_NorthAmericaLearningServices_LOB_radio_button_Click_NALS");
			logger.log(LogStatus.PASS, "TC-LOMT-1389-05_Admin_Curriculum_Standard (custom)_Structure_radio_button_Click_NALS");
			logger.log(LogStatus.PASS, "TC-LOMT-1389-06_Admin_Manage Ingestion_Next_NALS");
			logger.log(LogStatus.PASS, "TC-LOMT-1389-07_Admin_Manage Ingestion_Back _Create or upload a structure_NALS");
			logger.log(LogStatus.PASS, "TC-LOMT-1389-08_Admin_SchoolGlobal_LOB_radio_button_Click_NALS");
			logger.log(LogStatus.PASS, "TC-LOMT-1389-09_Admin_Curriculum_Standard (custom)_Structure_radio_button_Click_NALS");
			logger.log(LogStatus.PASS, "TC-LOMT-1389-10_Admin_Manage Ingestion_Next_NALS");
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
		ReadExternalFrameworkFile readExternalFrameworkFile = null;
		
		if (lobName.equalsIgnoreCase(LOMTConstant.HE_LOB)) {
			try {
				commonPOM.getHeLOB().click();
				exfPOM.getExternalFrameworkStructureBrowseHE().click();
				Thread.sleep(20000);
				
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("window.scrollBy(0,500)");
				
				readExternalFrameworkFile = new ReadExternalFrameworkFile();
				
				String goalframeworkName = readExternalFrameworkFile.getEXFGoalFrameworkName(lobName);
				
				if (goalframeworkName != null && !goalframeworkName.isEmpty()) {
					System.out.println("Goalframework name : " + goalframeworkName);

					exfPOM.getEnterSearchTerm().sendKeys(goalframeworkName);
					Thread.sleep(2000);

					Assert.assertTrue(exfPOM.getUpdateResultButton().isEnabled());
					exfPOM.getUpdateResultButton().click();
					Thread.sleep(15000);
					
					Assert.assertTrue(exfPOM.getActionLink().isDisplayed());
					exfPOM.getActionLink().click();
					Thread.sleep(2000);
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_02_ADMIN_CO_ORDINATOR_SME_EXPORT_HE);
					
					// removing existing files from the download directory
					readExternalFrameworkFile.removeExistingFile(); 
					
					Assert.assertTrue(exfPOM.getExportButton().isDisplayed());
					exfPOM.getExportButton().click();
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_03_ADMIN_CO_ORDINATOR_SME_EXPORT_CLICK_HE);

					Thread.sleep(30000);
					exfPOM.getEnterSearchTerm().clear();
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
				
				String goalframeworkName = readExternalFrameworkFile.getEXFGoalFrameworkName(lobName);
				
				if (goalframeworkName != null && !goalframeworkName.isEmpty()) {
					System.out.println("Goalframework name : " + goalframeworkName);

					exfPOM.getEnterSearchTerm().sendKeys(goalframeworkName);
					Thread.sleep(2000);

					Assert.assertTrue(exfPOM.getUpdateResultButton().isEnabled());
					exfPOM.getUpdateResultButton().click();
					Thread.sleep(10000);
					
					Assert.assertTrue(exfPOM.getActionLink().isDisplayed());
					exfPOM.getActionLink().click();
					Thread.sleep(2000);
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_20_ADMIN_CO_ORDINATOR_SME_EXPORT_ENGLISH);
					
					// removing existing files from the download directory
					readExternalFrameworkFile.removeExistingFile(); 
					
					Assert.assertTrue(exfPOM.getExportButton().isDisplayed());
					exfPOM.getExportButton().click();
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_21_ADMIN_CO_ORDINATOR_SME_EXPORT_CLICK_ENGLISH);

					Thread.sleep(30000);
					exfPOM.getEnterSearchTerm().clear();
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
				commonPOM.getNalsLOB().click();
				exfPOM.getCurriculumStandardStructureBrowseSchool().click();
				Thread.sleep(20000);
				
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("window.scrollBy(0,500)");
				
				readExternalFrameworkFile = new ReadExternalFrameworkFile();
				
				String goalframeworkName = readExternalFrameworkFile.getEXFGoalFrameworkName(lobName);
				
				if (goalframeworkName != null && !goalframeworkName.isEmpty()) {
					System.out.println("Goalframework name : " + goalframeworkName);

					exfPOM.getEnterSearchTerm().sendKeys(goalframeworkName);
					Thread.sleep(2000);

					Assert.assertTrue(exfPOM.getUpdateResultButton().isEnabled());
					exfPOM.getUpdateResultButton().click();
					Thread.sleep(10000);
					
					Assert.assertTrue(exfPOM.getActionLink().isDisplayed());
					exfPOM.getActionLink().click();
					Thread.sleep(2000);
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_38_ADMIN_CO_ORDINATOR_SME_EXPORT_SCHOOL);
					
					// removing existing files from the download directory
					readExternalFrameworkFile.removeExistingFile(); 
					
					Assert.assertTrue(exfPOM.getExportButton().isDisplayed());
					exfPOM.getExportButton().click();
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_39_ADMIN_CO_ORDINATOR_SME_EXPORT_CLICK_SCHOOL);

					Thread.sleep(30000);
					exfPOM.getEnterSearchTerm().clear();
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
					commonPOM.getPearsonLogo().click();
				} else {
					// add logger
					System.out.println("HE External Framework doesn't exist");
				}
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
		}
	}

	/*public void closeDriverInstance() {
		driver.close();
	}*/

}
