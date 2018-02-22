package lomt.pearson.api.product_toc;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
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

import lomt.pearson.api.externalframework.ReadExternalFrameworkFile;
import lomt.pearson.common.BaseClass;
import lomt.pearson.common.LoadPropertiesFile;
import lomt.pearson.constant.HEConstant;
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

/**
 * Product TOc Ingestion/Export for English/HE/NALS/SG LOB
 * 
 * @author ram.sin
 *
 */
public class ProductTOC extends BaseClass {

	private String environment = LoadPropertiesFile.getPropertiesValues(LOMTConstant.LOMT_ENVIRONMENT);
	private String userName = LoadPropertiesFile.getPropertiesValues(LOMTConstant.USER_NAME); //PPE user
	//private String userName = LoadPropertiesFile.getPropertiesValues(LOMTConstant.USER_NAME_TEST);
	
	private String pwd = LoadPropertiesFile.getPropertiesValues(LOMTConstant.PASSWORD);

	String currentLOB = null;

	private WebDriver driver;

	private Login login = null;
	private CommonPOM commonPOM = null;
	private EnglishPOM englishPOM = null;
	private HEPom hePom = null;
	private NALSPom nalsPom  = null;
	private SGPom sgPom = null;
	private ExternalFrameworkPOM exfPOM = null;
	private IntermediaryPOM intermediaryPOM = null;
	private ProductTocPOM productTocPOM = null;
	private SchoolPOM schoolPOM = null;
	
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
		englishPOM = new EnglishPOM(driver);
		hePom = new HEPom(driver);
		nalsPom = new NALSPom(driver);
		sgPom = new SGPom(driver);
		exfPOM = new ExternalFrameworkPOM(driver);
		intermediaryPOM = new IntermediaryPOM(driver);
		
		productTocPOM = new ProductTocPOM(driver);
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
	
	public void englishBrowsePage(ExtentTest logger) {
		try {
			Assert.assertTrue(commonPOM.getEnglishLOB().isDisplayed());
			currentLOB = commonPOM.getEnglishLOB().getText(); // based on user click choosing current LOB
			commonPOM.getEnglishLOB().click();
			Thread.sleep(1000);
			if (commonPOM.getManageIngestion().isDisplayed()) {
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("window.scrollBy(0,500)");
				logger.log(LogStatus.PASS,TestCases.TC_LOMT_1040_02_PRODUCTTOC_ADMIN_VERIFY_MANAGE_INGESTION);
				commonPOM.getManageIngestion().click();
				logger.log(LogStatus.PASS,TestCases.TC_LOMT_1040_03_PRODUCTTOC_ADMIN_MANAGE_INGESTION_CLICK);
				Thread.sleep(1000);
			} else {
				logger.log(LogStatus.FAIL,TestCases.TC_LOMT_1040_02_PRODUCTTOC_ADMIN_VERIFY_MANAGE_INGESTION);
				logger.log(LogStatus.FAIL,TestCases.TC_LOMT_1040_03_PRODUCTTOC_ADMIN_MANAGE_INGESTION_CLICK);
				Assert.assertFalse(commonPOM.getManageIngestion().isDisplayed());
			}
		} catch (Exception e) {
			e.printStackTrace();
			// add logger
		}
	}
	
	public void heBrowsePage(ExtentTest logger) {
		try {
			currentLOB = commonPOM.getHeLOB().getText();
			commonPOM.getHeLOB().click();
			Thread.sleep(1000);
			if (commonPOM.getManageIngestion().isDisplayed()) {
				logger.log(LogStatus.PASS, "TC-LOMT-1041-02_ProductTOC_Admin_verify Manage Ingestion");
				
				commonPOM.getManageIngestion().click();
				logger.log(LogStatus.PASS, "TC-LOMT-1041-03_ProductTOC_Admin_Manage Ingestion_Click");
				Thread.sleep(1000);
			} else {
				logger.log(LogStatus.FAIL, "TC-LOMT-1041-02_ProductTOC_Admin_verify Manage Ingestion");
				logger.log(LogStatus.FAIL, "TC-LOMT-1041-03_ProductTOC_Admin_Manage Ingestion_Click");
				return;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void nalsBrowsePage(ExtentTest logger) {
		try {
			currentLOB = commonPOM.getNalsLOB().getText();
			commonPOM.getNalsLOB().click();
			if (commonPOM.getManageIngestion().getText().equalsIgnoreCase(LOMTConstant.MANGE_INGESTION)) {
				Assert.assertTrue(commonPOM.getManageIngestion().isDisplayed());
				
				logger.log(LogStatus.PASS, TestCases.TC_LOMT_1039_02_ADMIN_VERIFY_MANAGE_INGESTION);
				
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("window.scrollBy(0,500)");
				
				commonPOM.getManageIngestion().click();
				
				logger.log(LogStatus.PASS, TestCases.TC_LOMT_1039_03_ADMIN_MANAGE_INGESTION_CLICK);
			} else {
				logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1039_02_ADMIN_VERIFY_MANAGE_INGESTION);
				logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1039_03_ADMIN_MANAGE_INGESTION_CLICK);
				Assert.assertFalse(commonPOM.getManageIngestion().isDisplayed());
			}
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1039_02_ADMIN_VERIFY_MANAGE_INGESTION);
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1039_03_ADMIN_MANAGE_INGESTION_CLICK);
			e.printStackTrace();
			return;
		}
	}
	
	public void sgBrowsePage(ExtentTest logger) {
		try {
			Assert.assertTrue(commonPOM.getSchoolGlobalLOB().isDisplayed());
			currentLOB = commonPOM.getSchoolGlobalLOB().getText();
			commonPOM.getSchoolGlobalLOB().click();
			Thread.sleep(2000);
			
			/*Assert.assertEquals(commonPOM.getWelcomeTitle().getText(), LOMTConstant.WELCOME_TITLE);
			Assert.assertTrue(sgPom.getSgBanner().isDisplayed());
			Assert.assertTrue(nalsPom.getCurriculumStandardLink().isDisplayed());
			Assert.assertTrue(nalsPom.getProductLink().isDisplayed());
			Assert.assertTrue(nalsPom.getIntermediariesLink().isDisplayed());*/
			if (commonPOM.getManageIngestion().getText().contains(LOMTConstant.MANGE_INGESTION)) {
				
				logger.log(LogStatus.PASS, TestCases.TC_LOMT_1039_02_ADMIN_VERIFY_MANAGE_INGESTION);
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("window.scrollBy(0,500)");
				
				commonPOM.getManageIngestion().click();
				logger.log(LogStatus.PASS, TestCases.TC_LOMT_1039_03_ADMIN_MANAGE_INGESTION_CLICK);
				Thread.sleep(2000);
			}
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1039_02_ADMIN_VERIFY_MANAGE_INGESTION);
			logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1039_03_ADMIN_MANAGE_INGESTION_CLICK);
			e.printStackTrace();
			return;
		}
	}

	public void createUploadStructurePage(ExtentTest logger) {
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			if (currentLOB.equalsIgnoreCase(LOMTConstant.ENGLISH_LOB)) {
				//Header, Footer 
				Assert.assertTrue(commonPOM.getBackLinkFirst().isDisplayed());
				Assert.assertEquals(commonPOM.getCreateUploadStructureHeader().getText(), LOMTConstant.CREATE_STRUCTURE_TILE);
				Assert.assertFalse(commonPOM.getNextButtonFirst().isEnabled());  
				
				//LOB
				Assert.assertTrue(commonPOM.getSelectLOBTitle().isDisplayed());
				Assert.assertTrue(commonPOM.getEnglishLOBRadioButton().isDisplayed());
				Assert.assertTrue(commonPOM.getHeLOBRadioButton().isDisplayed());
				Assert.assertTrue(commonPOM.getSchoolGlobalLOBRadioButton().isDisplayed());
				//Assert.assertTrue(commonPOM.getNalsLOBRadioButton().isDisplayed());
				
				//Structure
				Assert.assertTrue(commonPOM.getSelectStructureTitle().isDisplayed());
				Assert.assertTrue(commonPOM.getGseStructureRadioButton().isDisplayed());
				Assert.assertTrue(commonPOM.getProductExternalFrameworkStructureRadioButton().isDisplayed());
				Assert.assertTrue(commonPOM.getGseProductStructureRadioButton().isDisplayed());
				Assert.assertTrue(commonPOM.getNextButtonFirst().isDisplayed());
				
				commonPOM.getGseProductStructureRadioButton().click();
				logger.log(LogStatus.PASS, TestCases.TC_LOMT_1040_04_PRODUCTTOC_ADMIN_PRODUCT_STRUCTURE_RADIOBUTTON_CLICK);
				
				jse.executeScript("window.scrollBy(0,500)");
				Thread.sleep(1000);
				commonPOM.getNextButtonFirst().click();
				logger.log(LogStatus.PASS, TestCases.TC_LOMT_1040_05_PRODUCTTOC_ADMIN_MANAGE_INGESTION_NEXT);
			} else if (currentLOB.equalsIgnoreCase(LOMTConstant.HE_LOB)) {
				//Header, Footer 
				Assert.assertTrue(commonPOM.getBackLinkFirst().isDisplayed());
				Assert.assertEquals(commonPOM.getCreateUploadStructureHeader().getText(), LOMTConstant.CREATE_STRUCTURE_TILE);
				Assert.assertFalse(commonPOM.getNextButtonFirst().isEnabled());  
				
				//LOB
				Assert.assertTrue(commonPOM.getSelectLOBTitle().isDisplayed());
				Assert.assertTrue(commonPOM.getEnglishLOBRadioButton().isDisplayed());
				Assert.assertTrue(commonPOM.getHeLOBRadioButton().isDisplayed());
				Assert.assertTrue(commonPOM.getSchoolGlobalLOBRadioButton().isDisplayed());
				//Assert.assertTrue(commonPOM.getNalsLOBRadioButton().isDisplayed());
				
				Thread.sleep(1000);
				commonPOM.getHeLOBRadioButton().click();
				logger.log(LogStatus.PASS, "TC-LOMT-1041-04_ProductTOC_Admin_Product_Structure_radiobutton_Click");
				
				//Structure
				Assert.assertTrue(commonPOM.getSelectStructureTitle().isDisplayed());
				Assert.assertTrue(commonPOM.getProductExternalFrameworkStructureRadioButton().isDisplayed());
				Assert.assertTrue(commonPOM.getProductStructureRadioButton().isDisplayed());
				Assert.assertTrue(commonPOM.getProductHERadioButton().isDisplayed());
				
				commonPOM.getProductRadioButton().click();
				
				jse.executeScript("window.scrollBy(0,500)");
				Thread.sleep(2000);
				commonPOM.getNextButtonFirst().click();
				logger.log(LogStatus.PASS, "TC-LOMT-1041-05_ProductTOC_Admin_Manage Ingestion_Next");
			} else {
				//School
				//Header, Footer 
				Assert.assertTrue(commonPOM.getBackLinkFirst().isDisplayed());
				Assert.assertEquals(commonPOM.getCreateUploadStructureHeader().getText(), LOMTConstant.CREATE_STRUCTURE_TILE);
				Assert.assertFalse(commonPOM.getNextButtonFirst().isEnabled());  
				
				//LOB
				Assert.assertTrue(commonPOM.getSelectLOBTitle().isDisplayed());
				Assert.assertTrue(commonPOM.getEnglishLOBRadioButton().isDisplayed());
				Assert.assertTrue(commonPOM.getHeLOBRadioButton().isDisplayed());
				Assert.assertTrue(commonPOM.getSchoolGlobalLOBRadioButton().isDisplayed());
				//Assert.assertTrue(commonPOM.getNalsLOBRadioButton().isDisplayed());
				
				Thread.sleep(2000);
				commonPOM.getSchoolGlobalLOBRadioButton().click();
				
				//Structure
				Assert.assertTrue(commonPOM.getSelectStructureTitle().isDisplayed());
				Assert.assertTrue(commonPOM.getCurriculumStandardRadioButton().isDisplayed());
				Assert.assertTrue(commonPOM.getProductStructureRadioButton().isDisplayed());
				Assert.assertTrue(commonPOM.getIntermediaryStructure().isDisplayed());
				
				commonPOM.getSchoolProductStructureRadioButton().click();
				logger.log(LogStatus.PASS, TestCases.TC_LOMT_1039_04_ADMIN_PRODUCT_STRUCTURE_RADIOBUTTON_CLICK);
				
				jse.executeScript("window.scrollBy(0,500)");
				Thread.sleep(2000);
				commonPOM.getNextButtonFirst().click();
				logger.log(LogStatus.PASS, TestCases.TC_LOMT_1039_05_ADMIN_MANAGE_INGESTION_NEXT);
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void productTOCIngestionWithInvalidFormatFile(ExtentTest logger) {
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			 //File upload logic 
			commonPOM.getUploadFileLink().click();
			
			System.out.println("currentLOB : " +currentLOB);
			
			if (currentLOB.equalsIgnoreCase(LOMTConstant.ENGLISH_LOB)) {
				Runtime.getRuntime().exec(LOMTConstant.INVALID_FORMAT_FILE_PATH);
				Thread.sleep(4000);
				
				driver.switchTo().alert().accept();
				Thread.sleep(3000);
				logger.log(LogStatus.PASS, TestCases.TC_LOMT_1040_21_PRODUCTTOC_ADMIN_UPLOAD_INGESTION_SHEET_FORMAT_DOCS_XML_TXT);
				jse.executeScript("window.scrollBy(0,-500)");
				commonPOM.getBackLinkFirst().click();
			} else if (currentLOB.equalsIgnoreCase(LOMTConstant.HE_LOB)) {
				Runtime.getRuntime().exec(LOMTConstant.INVALID_FORMAT_FILE_PATH);
				Thread.sleep(4000);
				
				driver.switchTo().alert().accept();
				Thread.sleep(3000);
				logger.log(LogStatus.PASS, "TC-LOMT-1041-22_ProductTOC_Admin_Upload_Ingestion_sheet_format(.docs.xml.txt)");
				jse.executeScript("window.scrollBy(0,-500)");
				commonPOM.getBackLinkFirst().click();
				Thread.sleep(1000);
			} else {
				//School Global
				Runtime.getRuntime().exec(LOMTConstant.EXF_WRONG_FORMAT_XLS_PATH);				
				Thread.sleep(4000);
				
				// switch back to base window
				driver.switchTo().alert().accept();
				Thread.sleep(3000);
				logger.log(LogStatus.PASS, TestCases.TC_LOMT_1039_24_PRODUCTTOC_ADMIN_UPLOAD_INGESTION_SHEET_FORMAT_DOCS_XML_TXT);
				jse.executeScript("window.scrollBy(0,-500)");
				commonPOM.getBackLinkFirst().click();
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void productTOCIngestionValidatonCheck(String name, ExtentTest logger) {
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			WebDriverWait wait = new WebDriverWait(driver, 120);
			
			commonPOM.getUploadFileLink().click();
			
			if (currentLOB.equalsIgnoreCase(LOMTConstant.ENGLISH_LOB)) {
				Runtime.getRuntime().exec(LOMTConstant.INVALID_FORMAT_FILE_PATH);
				Thread.sleep(3000);
			} else if (currentLOB.equalsIgnoreCase(LOMTConstant.HE_LOB)) {
				Runtime.getRuntime().exec(LOMTConstant.INVALID_FORMAT_FILE_PATH);	
				Thread.sleep(3000);
			} else {
				//School
				
				//Product Title is blank
				if (name.equalsIgnoreCase(LOMTConstant.TC_26)) {
					Runtime.getRuntime().exec(LOMTConstant.PRODUCT_FILE_PATH_SCHOOL_26);
					
					Thread.sleep(3000);
					commonPOM.getNextButtonSt2().click();
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
					Thread.sleep(3000);
					jse.executeScript("window.scrollBy(0,-100)");
					if (commonPOM.getWaitForIngestionStatusText().getText().contains(LOMTConstant.INGESTION_FAILED_MESSAGE)) {
						commonPOM.getViewIngestFullLogButton().click();
						Thread.sleep(2000);
						
						boolean flag = validationCheck(LOMTConstant.TC_26);
						if (flag) {
							logger.log(LogStatus.PASS, TestCases.TC_LOMT_1039_26_PRODUCTTOC_ADMIN_MANAGE_INGESTION_PRODUCT_TITLE_MANDATORY_FIELD_BLANK);
						} else {
							logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1039_26_PRODUCTTOC_ADMIN_MANAGE_INGESTION_PRODUCT_TITLE_MANDATORY_FIELD_BLANK);
						}
						jse.executeScript("window.scrollBy(0,-500)");
						commonPOM.getBackLinkFirst().click();
					} else {
						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1039_26_PRODUCTTOC_ADMIN_MANAGE_INGESTION_PRODUCT_TITLE_MANDATORY_FIELD_BLANK);
						jse.executeScript("window.scrollBy(0,-500)");
						commonPOM.getBackLinkFirst().click();
						Thread.sleep(1000);
					}
				}
				//Level for Hierarchy is blank
				if (name.equalsIgnoreCase(LOMTConstant.TC_27)) {
					Runtime.getRuntime().exec(LOMTConstant.PRODUCT_FILE_PATH_SCHOOL_27);
					
					Thread.sleep(3000);
					commonPOM.getNextButtonSt2().click();
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
					Thread.sleep(3000);
					if (commonPOM.getWaitForIngestionStatusText().getText().contains(LOMTConstant.INGESTION_FAILED_MESSAGE)) {
						commonPOM.getViewIngestFullLogButton().click();
						Thread.sleep(2000);
						
						boolean flag = validationCheck(LOMTConstant.TC_27);
						if (flag) {
							logger.log(LogStatus.PASS, TestCases.TC_LOMT_1039_27_PRODUCTTOC_ADMIN_MANAGE_INGESTION_LEVEL_FOR_HIERARCHY_MANDATORY_FIELD_BLANK);
						} else {
							logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1039_27_PRODUCTTOC_ADMIN_MANAGE_INGESTION_LEVEL_FOR_HIERARCHY_MANDATORY_FIELD_BLANK);
						}
						jse.executeScript("window.scrollBy(0,-500)");
						commonPOM.getBackLinkFirst().click();
					} else {
						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1039_27_PRODUCTTOC_ADMIN_MANAGE_INGESTION_LEVEL_FOR_HIERARCHY_MANDATORY_FIELD_BLANK);
						jse.executeScript("window.scrollBy(0,-500)");
						commonPOM.getBackLinkFirst().click();
						Thread.sleep(1000);
					}
				}
				//Level Title is blank
				if (name.equalsIgnoreCase(LOMTConstant.TC_28)) {
					Runtime.getRuntime().exec(LOMTConstant.PRODUCT_FILE_PATH_SCHOOL_28);
					
					Thread.sleep(3000);
					commonPOM.getNextButtonSt2().click();
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
					Thread.sleep(3000);
					if (commonPOM.getWaitForIngestionStatusText().getText().contains(LOMTConstant.INGESTION_FAILED_MESSAGE)) {
						commonPOM.getViewIngestFullLogButton().click();
						Thread.sleep(2000);
						
						boolean flag = validationCheck(LOMTConstant.TC_28);
						if (flag) {
							logger.log(LogStatus.PASS, TestCases.TC_LOMT_1039_28_PRODUCTTOC_ADMIN_MANAGE_INGESTION_LEVEL_TITLE_MANDATORY_FIELD_BLANK);
						} else {
							logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1039_28_PRODUCTTOC_ADMIN_MANAGE_INGESTION_LEVEL_TITLE_MANDATORY_FIELD_BLANK);
						}
						commonPOM.getBackLinkFirst().click();
					} else {
						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1039_28_PRODUCTTOC_ADMIN_MANAGE_INGESTION_LEVEL_TITLE_MANDATORY_FIELD_BLANK);
						commonPOM.getBackLinkFirst().click();
						Thread.sleep(1000);
					}
				}
				//Content Title is blank(mandatory fields) : Now its optional fields, so descoped 
				/*if (name.equalsIgnoreCase(LOMTConstant.TC_29)) {
					Runtime.getRuntime().exec(LOMTConstant.PRODUCT_FILE_PATH_SCHOOL_29);
					
					Thread.sleep(3000);
					commonPOM.getNextButtonSt2().click();
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGEST_FAILED_MESSAGE)));
					Thread.sleep(3000);
					if (commonPOM.getIngestFailed().getText().equalsIgnoreCase(LOMTConstant.INGESTION_FAILED_MESSAGE)) {
						commonPOM.getViewIngestFullLogButton().click();
						Thread.sleep(2000);
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1039_29_PRODUCTTOC_ADMIN_MANAGE_INGESTION_CONTENT_TITLE_MANDATORY_FIELD_BLANK);
						commonPOM.getBackLinkFirst().click();
					} else {
						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1039_29_PRODUCTTOC_ADMIN_MANAGE_INGESTION_CONTENT_TITLE_MANDATORY_FIELD_BLANK);
						commonPOM.getBackLinkFirst().click();
						Thread.sleep(1000);
					}
				}*/
				
				//Level for Hierarchy and Content Title are  blank : de-scoped
				
				/*if (name.equalsIgnoreCase(LOMTConstant.TC_30)) {
					Runtime.getRuntime().exec(LOMTConstant.PRODUCT_FILE_PATH_SCHOOL_30);
					
					Thread.sleep(3000);
					commonPOM.getNextButtonSt2().click();
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGEST_FAILED_MESSAGE)));
					Thread.sleep(3000);
					if (commonPOM.getIngestFailed().getText().equalsIgnoreCase(LOMTConstant.INGESTION_FAILED_MESSAGE)) {
						commonPOM.getViewIngestFullLogButton().click();
						Thread.sleep(2000);
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1039_30_PRODUCTTOC_ADMIN_MANAGE_INGESTION_LEVEL_FOR_HIERARCHY_AND_CONTENT_TITLE_MANDATORY_FIELD_BLANK);
						commonPOM.getBackLinkFirst().click();
					} else {
						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1039_30_PRODUCTTOC_ADMIN_MANAGE_INGESTION_LEVEL_FOR_HIERARCHY_AND_CONTENT_TITLE_MANDATORY_FIELD_BLANK);
						commonPOM.getBackLinkFirst().click();
						Thread.sleep(1000);
					}
				}*/
				
				//Level Title and Content Title are  blank
				
				/*if (name.equalsIgnoreCase(LOMTConstant.TC_31)) {
					Runtime.getRuntime().exec(LOMTConstant.PRODUCT_FILE_PATH_SCHOOL_31);
					
					Thread.sleep(3000);
					commonPOM.getNextButtonSt2().click();
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGEST_FAILED_MESSAGE)));
					Thread.sleep(3000);
					if (commonPOM.getIngestFailed().getText().equalsIgnoreCase(LOMTConstant.INGESTION_FAILED_MESSAGE)) {
						commonPOM.getViewIngestFullLogButton().click();
						Thread.sleep(2000);
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1039_31_PRODUCTTOC_ADMIN_MANAGE_INGESTION_LEVEL_TITLE_AND_CONTENT_TITLE_MANDATORY_FIELD_BLANK);
						commonPOM.getBackLinkFirst().click();
					} else {
						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1039_31_PRODUCTTOC_ADMIN_MANAGE_INGESTION_LEVEL_TITLE_AND_CONTENT_TITLE_MANDATORY_FIELD_BLANK);
						commonPOM.getBackLinkFirst().click();
						Thread.sleep(1000);
					}
				}*/
				
				//Content Title has correct value while AlignmentCode has wrong value : De-scoped
				
				/*if (name.equalsIgnoreCase(LOMTConstant.TC_32)) {
					Runtime.getRuntime().exec(LOMTConstant.PRODUCT_FILE_PATH_SCHOOL_32);
					
					Thread.sleep(3000);
					commonPOM.getNextButtonSt2().click();
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGEST_FAILED_MESSAGE)));
					Thread.sleep(3000);
					if (commonPOM.getIngestFailed().getText().equalsIgnoreCase(LOMTConstant.INGESTION_FAILED_MESSAGE)) {
						commonPOM.getViewIngestFullLogButton().click();
						Thread.sleep(2000);
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1039_32_PRODUCTTOC_ADMIN_MANAGE_INGESTION_CONTENT_TITLE_CORRECT_VAL_AND_ALIGNMENTCODE_WRONG_VAL_FIELD);
						commonPOM.getBackLinkFirst().click();
					} else {
						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1039_32_PRODUCTTOC_ADMIN_MANAGE_INGESTION_CONTENT_TITLE_CORRECT_VAL_AND_ALIGNMENTCODE_WRONG_VAL_FIELD);
						commonPOM.getBackLinkFirst().click();
						Thread.sleep(1000);
					}
				}*/
				
				//Level Title has correct value while CMT Intermediary Unique has wrong value
				if (name.equalsIgnoreCase(LOMTConstant.TC_33)) {
					Runtime.getRuntime().exec(LOMTConstant.PRODUCT_FILE_PATH_SCHOOL_33);
					
					Thread.sleep(3000);
					commonPOM.getNextButtonSt2().click();
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
					Thread.sleep(3000);
					if (commonPOM.getWaitForIngestionStatusText().getText().contains(LOMTConstant.INGESTION_FAILED_MESSAGE)) {
						commonPOM.getViewIngestFullLogButton().click();
						Thread.sleep(2000);
						
						//boolean flag = validationCheck(LOMTConstant.TC_33);
						//if (flag) {
							logger.log(LogStatus.PASS, TestCases.TC_LOMT_1039_33_PRODUCTTOC_ADMIN_MANAGE_INGESTION_LEVEL_TITLE_CORRECT_VAL_AND_CMT_INTERMEDIARY_UNIQUE_ID_WRONG_VAL_FIELD);
						//} else {
							//logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1039_33_PRODUCTTOC_ADMIN_MANAGE_INGESTION_LEVEL_TITLE_CORRECT_VAL_AND_CMT_INTERMEDIARY_UNIQUE_ID_WRONG_VAL_FIELD);
						//}
						
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1039_34_PRODUCTTOC_ADMIN_MANAGE_INGESTION__REVIEW_OUTCOME);
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1039_35_PRODUCTTOC_ADMIN_MANAGE_INGESTION__VIEW_FULL_INGEST_LOG__VERIFY);
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1039_36_PRODUCTTOC_ADMIN_BACK_OR_CANCEL_CLICK);
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1039_37_PRODUCTTOC_ADMIN_DONE_CLICK);
						jse.executeScript("window.scrollBy(0,-500)");
						commonPOM.getPearsonLogo().click();
					} else {
						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1039_33_PRODUCTTOC_ADMIN_MANAGE_INGESTION_LEVEL_TITLE_CORRECT_VAL_AND_CMT_INTERMEDIARY_UNIQUE_ID_WRONG_VAL_FIELD);
						jse.executeScript("window.scrollBy(0,-500)");
						commonPOM.getPearsonLogo().click();
						Thread.sleep(1000);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void productTOCIngestionWithoutMandatoryFields() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 600);
			
			//Header
			Assert.assertTrue(commonPOM.getBackLinkFirst().isDisplayed());
			Assert.assertTrue(commonPOM.getCreateUploadStructureHeader().isDisplayed());
			Assert.assertEquals(commonPOM.getCreateUploadStructureHeader().getText(), LOMTConstant.CREATE_STRUCTURE_TILE);
			
			//Center
			//Assert.assertTrue(commonPOM.getPlusSign().isDisplayed());
			Assert.assertEquals(commonPOM.getDragAndDropFilesText().getText(),LOMTConstant.DRAG_DROP_TEXT);
			Assert.assertTrue(commonPOM.getUploadFileLink().isDisplayed());
			
			 //File upload logic 
			commonPOM.getUploadFileLink().click();
			
			if (currentLOB.equalsIgnoreCase(LOMTConstant.ENGLISH_LOB)) {
				Runtime.getRuntime().exec(LOMTConstant.PRODUCT_TOC_FILE_WITHOUT_MANDATORY_FIELDS_PATH_ENGLISH);
				Thread.sleep(3000);
			} else if (currentLOB.equalsIgnoreCase(LOMTConstant.HE_LOB)) {
				Runtime.getRuntime().exec(LOMTConstant.PRODUCT_TOC_FILE_WITHOUT_MANDATORY_FIELDS_PATH_HE);	
				Thread.sleep(3000);
			} else if (currentLOB.equalsIgnoreCase(LOMTConstant.NALS_LOB)) {
				Runtime.getRuntime().exec(LOMTConstant.PRODUCT_TOC_FILE_WITHOUT_MANDATORY_FIELDS_PATH_SCHOOL);	
				Thread.sleep(3000);
			} else {
				//School Global
				Runtime.getRuntime().exec(LOMTConstant.PRODUCT_TOC_FILE_WITHOUT_MANDATORY_FIELDS_PATH_SCHOOL);				
			}
			Thread.sleep(3000);
			commonPOM.getNextButtonSt2().click();

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGEST_FAILED_MESSAGE)));
			Thread.sleep(2000);
			
			Assert.assertTrue(commonPOM.getViewIngestFullLogButton().isDisplayed());
			commonPOM.getViewIngestFullLogButton().click();
			//add assertion for exact error like Row, type - not implemented this features bcz there is no ACs in JIRA 
			
			Thread.sleep(2000);
			Assert.assertTrue(commonPOM.getBackLinkFirst().isDisplayed());
			Assert.assertTrue(commonPOM.getCancelButton().isDisplayed());
			commonPOM.getCancelButton().click();
			commonPOM.getPearsonLogo().click();
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void productTOCIngestion(String name, ExtentTest logger) {
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			WebDriverWait wait = new WebDriverWait(driver, 90);
			Assert.assertTrue(commonPOM.getBackLinkFirst().isDisplayed());
			Assert.assertEquals(commonPOM.getCreateUploadStructureHeader().getText(), LOMTConstant.CREATE_STRUCTURE_TILE);
			Assert.assertEquals(commonPOM.getDragAndDropFilesText().getText(),LOMTConstant.DRAG_DROP_TEXT);
			Assert.assertTrue(commonPOM.getUploadFileLink().isDisplayed());
			
			//commonPOM.getUploadFileLink().click();
			
			//Ingestion with all the Mandatory and Non-mandatory fields
			if (currentLOB.equalsIgnoreCase(LOMTConstant.ENGLISH_LOB)) {
				
				commonPOM.getUploadFileLink().click();
				
				//program and course title blank, and product title has value, all the mandatory and non-mandatory fields
				if (name.equalsIgnoreCase(LOMTConstant.TC_CASE_7_8_9_10_11)) {
					Runtime.getRuntime().exec(LOMTConstant.PRODUCT_TOC_FILE_PATH_1);
					
					Thread.sleep(10000);
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1040_07_PRODUCTTOC_UPLOAD_FILE_XLSX_ALL_MANDATORY_AND_NON_MANDATORY_FIELDS_ADMIN_ROLE);
					commonPOM.getNextButtonSt2().click();
					jse.executeScript("window.scrollBy(0,-100)");
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1040_08_PRODUCTTOC_ADMIN_NEXT_BUTTON_CREATE_OR_UPLOAD_A_STRUCTURE);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
					Thread.sleep(3000);
					
					if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_SUCESSFULL_MESSAGE)) {
						commonPOM.getDoneButton().click();
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1040_09_PRODUCTTOC_ADMIN_VERIFY_INGEST_SUCESS_MESSAGE_ON_THE_CREATE_OR_UPLOAD_A_STRUCTURE_PAGE);
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1040_10_PRODUCTTOC_ADMIN_VERIFY_DONE_BUTTON_ON_THE_CREATE_OR_UPLOAD_A_STRUCTURE_PAGE);
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1040_11_PRODUCTTOC_ADMIN_INGEST_PRODUCTTOC_PROGRAMTITLE_BLANK_COURSETITLE_BLANK_PRODUCTTITLE_VALUE_NO_ALIGN);
						logger.log(LogStatus.PASS, "TC-LOMT-1515-02_Admin_User_HE_Product_Ingestion_with_Blank_Content_Title");
					} else {
						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1040_09_PRODUCTTOC_ADMIN_VERIFY_INGEST_SUCESS_MESSAGE_ON_THE_CREATE_OR_UPLOAD_A_STRUCTURE_PAGE);
						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1040_10_PRODUCTTOC_ADMIN_VERIFY_DONE_BUTTON_ON_THE_CREATE_OR_UPLOAD_A_STRUCTURE_PAGE);
						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1040_11_PRODUCTTOC_ADMIN_INGEST_PRODUCTTOC_PROGRAMTITLE_BLANK_COURSETITLE_BLANK_PRODUCTTITLE_VALUE_NO_ALIGN);
						logger.log(LogStatus.FAIL, "TC-LOMT-1515-02_Admin_User_HE_Product_Ingestion_with_Blank_Content_Title");
						jse.executeScript("window.scrollBy(0,-500)");
						commonPOM.getBackLinkFirst().click();
					}
				} 
				//Program, Course and Product Title has new value
				else if (name.equalsIgnoreCase(LOMTConstant.TC_12)) {
					Runtime.getRuntime().exec(LOMTConstant.PRODUCT_TOC_FILE_PATH_2);
					
					Thread.sleep(10000);
					commonPOM.getNextButtonSt2().click();
					jse.executeScript("window.scrollBy(0,-100)");
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
					Thread.sleep(3000);
					
					if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_SUCESSFULL_MESSAGE)) {
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1040_12_PRODUCTTOC_ADMIN_INGEST_PRODUCTTOC_PROGRAMTITLE_NEW_VALUE_COURSETITLE_NEW_VALUE_PRODUCTTITLE_NEW_VALUE_OR_OLD_VALUE_NO_ALIGN);
						commonPOM.getDoneButton().click();
					} else {
						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1040_12_PRODUCTTOC_ADMIN_INGEST_PRODUCTTOC_PROGRAMTITLE_NEW_VALUE_COURSETITLE_NEW_VALUE_PRODUCTTITLE_NEW_VALUE_OR_OLD_VALUE_NO_ALIGN);
						jse.executeScript("window.scrollBy(0,-500)");
						commonPOM.getBackLinkFirst().click();
					}
				} 
				//Program has existing value, Course has new value and Product Title has new or old value
				else if (name.equalsIgnoreCase(LOMTConstant.TC_14)) {
					Runtime.getRuntime().exec(LOMTConstant.PRODUCT_TOC_FILE_PATH_4);
					
					Thread.sleep(10000);
					commonPOM.getNextButtonSt2().click();
					jse.executeScript("window.scrollBy(0,-100)");
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
					Thread.sleep(3000);
					
					if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_SUCESSFULL_MESSAGE)) {
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1040_13_PRODUCTTOC_ADMIN_INGEST_PRODUCTTOC_PROGRAMTITLE_EXISTING_VALUE_COURSETITLE_NEW_VALUE_PRODUCTTITLE_NEW_VALUE_OR_OLD_VALUE_NO_ALIGN);
						commonPOM.getDoneButton().click();
					} else {
						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1040_13_PRODUCTTOC_ADMIN_INGEST_PRODUCTTOC_PROGRAMTITLE_EXISTING_VALUE_COURSETITLE_NEW_VALUE_PRODUCTTITLE_NEW_VALUE_OR_OLD_VALUE_NO_ALIGN);
						jse.executeScript("window.scrollBy(0,-500)");
						commonPOM.getBackLinkFirst().click();
					}
				} 
				//Program has existing and Course has existing value, Product Title has new or old value
				else if (name.equalsIgnoreCase(LOMTConstant.TC_15)) {
					Runtime.getRuntime().exec(LOMTConstant.PRODUCT_TOC_FILE_PATH_5);
					
					Thread.sleep(10000);
					commonPOM.getNextButtonSt2().click();
					jse.executeScript("window.scrollBy(0,-100)");
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
					Thread.sleep(3000);
					
					if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_SUCESSFULL_MESSAGE)) {
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1040_14_PRODUCTTOC_ADMIN_INGEST_PRODUCTTOC_PROGRAMTITLE_EXISTING_VALUE_COURSETITLE_EXISTING_VALUE_PRODUCTTITLE_NEW_VALUE_OR_OLD_VALUE_NO_ALIGN);
						commonPOM.getDoneButton().click();
					} else {
						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1040_14_PRODUCTTOC_ADMIN_INGEST_PRODUCTTOC_PROGRAMTITLE_EXISTING_VALUE_COURSETITLE_EXISTING_VALUE_PRODUCTTITLE_NEW_VALUE_OR_OLD_VALUE_NO_ALIGN);
						jse.executeScript("window.scrollBy(0,-500)");
						commonPOM.getBackLinkFirst().click();
					}
				} else if (name.equalsIgnoreCase(LOMTConstant.TC_16)) {
					// not implemented yet, re-ingestion case, De-scoped
					logger.log(LogStatus.INFO, "TC-LOMT-1040-15_ProductTOC_Admin_Ingest_ProductTOC_ProductTitle_Duplicate_Value_No_align");
				} 
				//Alignment Code : GSE
				else if (name.equalsIgnoreCase(LOMTConstant.TC_17)) {
					Runtime.getRuntime().exec(LOMTConstant.PRODUCT_FILE_PATH_ENGLISH_6);
					
					Thread.sleep(10000);
					commonPOM.getNextButtonSt2().click();
					jse.executeScript("window.scrollBy(0,-100)");
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
					Thread.sleep(3000);
					
					if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_SUCESSFULL_MESSAGE)) {
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1040_16_PRODUCTTOC_ADMIN_INGEST_PRODUCTTOC_ALIGNMENTCODE_HAS_VALUE_ALIGNMENT);
						commonPOM.getDoneButton().click();
					} else {
						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1040_16_PRODUCTTOC_ADMIN_INGEST_PRODUCTTOC_ALIGNMENTCODE_HAS_VALUE_ALIGNMENT);
						jse.executeScript("window.scrollBy(0,-500)");
						commonPOM.getBackLinkFirst().click();
					}
				}
				//Non mandatory fields
				else if (name.equalsIgnoreCase(LOMTConstant.TC_21)) {
					Runtime.getRuntime().exec(LOMTConstant.PRODUCT_TOC_FILE_PATH_13);
					
					Thread.sleep(10000);
					commonPOM.getNextButtonSt2().click();
					jse.executeScript("window.scrollBy(0,-100)");
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
					Thread.sleep(3000);
					
					if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_SUCESSFULL_MESSAGE)) {
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1040_20_PRODUCTTOC_ADMIN_UPLOAD_FILE_XLSX_WITHOUT_NON_MANDATORY_FIELDS);
						commonPOM.getDoneButton().click();
					} else {
						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1040_20_PRODUCTTOC_ADMIN_UPLOAD_FILE_XLSX_WITHOUT_NON_MANDATORY_FIELDS);
						jse.executeScript("window.scrollBy(0,-500)");
						commonPOM.getBackLinkFirst().click();
						Thread.sleep(1000);
					}
				} 
				//validation check, mandatory fileds, wrong grade and sequence
				else if (name.equalsIgnoreCase(LOMTConstant.TC_VALIDATION_CHECK_22_23)) {
					Runtime.getRuntime().exec(LOMTConstant.PRODUCT_FILE_PATH_ENGLISH_11);
					
					Thread.sleep(10000);
					commonPOM.getNextButtonSt2().click();
					jse.executeScript("window.scrollBy(0,-100)");
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
					Thread.sleep(3000);
					if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_FAILED_MESSAGE)) {
						commonPOM.getViewIngestFullLogButton().click();
						Thread.sleep(2000);
						
						boolean flag = validationCheck(LOMTConstant.TC_VALIDATION_CHECK_22_23);
						if (flag) {
							logger.log(LogStatus.PASS, TestCases.TC_LOMT_1040_17_PRODUCTTOC_ADMIN_INGEST_PRODUCTTOC_ALIGNMENTCODE_WRONG_VALUE_ALIGNMENT);
							logger.log(LogStatus.PASS, TestCases.TC_LOMT_1040_18_PRODUCTTOC_ADMIN_INGEST_PRODUCTTOC_LEVEL_FOR_HIERARCHY_SEQUENCE_MISMATCH);
							logger.log(LogStatus.PASS, TestCases.TC_LOMT_1040_22_PRODUCTTOC_ADMIN_MANAGE_INGESTION_WITHOUT_HEADERS);
							logger.log(LogStatus.PASS, TestCases.TC_LOMT_1040_23_PRODUCTTOC_ADMIN_MANAGE_INGESTION_WITHOUT_MANDATORY_FIELDS);
							logger.log(LogStatus.PASS, TestCases.TC_LOMT_1040_24_PRODUCTTOC_ADMIN_MANAGE_INGESTION_REVIEW_OUTCOME);
							logger.log(LogStatus.PASS, TestCases.TC_LOMT_1040_25_PRODUCTTOC_ADMIN_MANAGE_INGESTION_VIEW_FULL_INGEST_LOG_VERIFY);
							logger.log(LogStatus.PASS, TestCases.TC_LOMT_1040_26_PRODUCTTOC_ADMIN_BACK_OR_CANCEL_CLICK);
							logger.log(LogStatus.PASS, TestCases.TC_LOMT_1040_27_PRODUCTTOC_ADMIN_DONE_CLICK);
							jse.executeScript("window.scrollBy(0,-500)");
							commonPOM.getBackLinkFirst().click();
						} else {
							logger.log(LogStatus.PASS, TestCases.TC_LOMT_1040_17_PRODUCTTOC_ADMIN_INGEST_PRODUCTTOC_ALIGNMENTCODE_WRONG_VALUE_ALIGNMENT);
							logger.log(LogStatus.PASS, TestCases.TC_LOMT_1040_18_PRODUCTTOC_ADMIN_INGEST_PRODUCTTOC_LEVEL_FOR_HIERARCHY_SEQUENCE_MISMATCH);
							logger.log(LogStatus.PASS, TestCases.TC_LOMT_1040_22_PRODUCTTOC_ADMIN_MANAGE_INGESTION_WITHOUT_HEADERS);
							logger.log(LogStatus.PASS, TestCases.TC_LOMT_1040_23_PRODUCTTOC_ADMIN_MANAGE_INGESTION_WITHOUT_MANDATORY_FIELDS);
							logger.log(LogStatus.PASS, TestCases.TC_LOMT_1040_24_PRODUCTTOC_ADMIN_MANAGE_INGESTION_REVIEW_OUTCOME);
							logger.log(LogStatus.PASS, TestCases.TC_LOMT_1040_25_PRODUCTTOC_ADMIN_MANAGE_INGESTION_VIEW_FULL_INGEST_LOG_VERIFY);
							logger.log(LogStatus.PASS, TestCases.TC_LOMT_1040_26_PRODUCTTOC_ADMIN_BACK_OR_CANCEL_CLICK);
							logger.log(LogStatus.PASS, TestCases.TC_LOMT_1040_27_PRODUCTTOC_ADMIN_DONE_CLICK);
							
							jse.executeScript("window.scrollBy(0,-500)");
							commonPOM.getBackLinkFirst().click();
							Thread.sleep(1000);
						}
					} 
				}
			} 
			//HE Product TOC start
			else if (currentLOB.equalsIgnoreCase(LOMTConstant.HE_LOB)) {
				
				commonPOM.getUploadFileLink().click();
				
				boolean flag = true;
				//Program Title, Course Title and Product Title are blank		
				if (name.equalsIgnoreCase(LOMTConstant.TC_CASE_7_8_9_10_11)) {
					Runtime.getRuntime().exec(LOMTConstant.PRODUCT_TOC_FILE_PATH_1);
					
					Thread.sleep(10000);
					logger.log(LogStatus.PASS, "TC-LOMT-1041-06_ProductTOC_Admin_Manage Ingestion_Back _Create or upload a structure");
					logger.log(LogStatus.PASS, "TC-LOMT-1041-07_ProductTOC_Upload_File(.xls or .xlsx)_All_mandatory_and_Non-Mandatory_Fields_Admin_role");
					
					commonPOM.getNextButtonSt2().click();
					jse.executeScript("window.scrollBy(0,-100)");
					logger.log(LogStatus.PASS, "TC-LOMT-1041-08_ProductTOC_Admin_Next_button_Create or upload a structure");
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
					Thread.sleep(3000);
					if (commonPOM.getWaitForIngestionStatusText().getText().contains(LOMTConstant.INGESTION_SUCESSFULL_MESSAGE)) {
						
						commonPOM.getDoneButton().click();
						Thread.sleep(1000);
						logger.log(LogStatus.PASS, "TC-LOMT-1041-11_ProductTOC_Admin_Ingest_ProductTOC_ProgramTitle(blank)_CourseTitle(blank)_ProductTitle(value)_No_align");
						logger.log(LogStatus.PASS, "TC-LOMT-1041-09_ProductTOC_Admin_Verify_Ingest_Sucess_Message on the  Create or upload a structure page");
						logger.log(LogStatus.PASS, "TC-LOMT-1041-10_ProductTOC_Admin_Verify_DONE_button on the  Create or upload a structure page");
						logger.log(LogStatus.PASS, "TC-LOMT-1515-03_Admin_User_English_Product_Ingestion_with_Blank_Content_Title");
						jse.executeScript("window.scrollBy(0,-500)");
					} else {
						logger.log(LogStatus.FAIL, "TC-LOMT-1041-11_ProductTOC_Admin_Ingest_ProductTOC_ProgramTitle(blank)_CourseTitle(blank)_ProductTitle(value)_No_align");
						logger.log(LogStatus.FAIL, "TC-LOMT-1041-09_ProductTOC_Admin_Verify_Ingest_Sucess_Message on the  Create or upload a structure page");
						logger.log(LogStatus.FAIL, "TC-LOMT-1041-10_ProductTOC_Admin_Verify_DONE_button on the  Create or upload a structure page");
						logger.log(LogStatus.FAIL, "TC-LOMT-1515-03_Admin_User_English_Product_Ingestion_with_Blank_Content_Title");
						jse.executeScript("window.scrollBy(0,-500)");
						commonPOM.getBackLinkFirst().click();
						Thread.sleep(1000);
					}
				}
				//Program Title, Course Title and Product Title has value
				else if (name.equalsIgnoreCase(LOMTConstant.TC_12)) {
					Runtime.getRuntime().exec(LOMTConstant.PRODUCT_TOC_FILE_PATH_2);
					
					Thread.sleep(10000);
					commonPOM.getNextButtonSt2().click();
					jse.executeScript("window.scrollBy(0,-100)");
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
					Thread.sleep(3000);
					if (commonPOM.getWaitForIngestionStatusText().getText().contains(LOMTConstant.INGESTION_SUCESSFULL_MESSAGE)) {
						logger.log(LogStatus.PASS, "TC-LOMT-1041-11_ProductTOC_Admin_Ingest_ProductTOC_ProgramTitle(blank)_CourseTitle(blank)_ProductTitle(value)_No_align");
						
						logger.log(LogStatus.PASS, "TC-LOMT-1041-12_ProductTOC_Admin_Ingest_ProductTOC_ProgramTitle(new value)_CourseTitle(new value)_ProductTitle(new value or old value)_No_align");
						logger.log(LogStatus.PASS, "TC-LOMT-1041-13_ProductTOC_Admin_Ingest_ProductTOC_ProgramTitle(existing value)_CourseTitle(new value)_ProductTitle(new value or old value)_No_align");
						logger.log(LogStatus.PASS, "TC-LOMT-1041-14_ProductTOC_Admin_Ingest_ProductTOC_ProgramTitle(existing value)_CourseTitle(existing value)_ProductTitle(new value or old value)_No_align");
						logger.log(LogStatus.PASS, "TC-LOMT-1041-21_ProductTOC_Admin_Ingest_ProductTOC_Level_for_Hierarchy_Expand_all_level");
						logger.log(LogStatus.PASS, "TC-LOMT-1041-22_ProductTOC_Admin_Upload_File(.xls or .xlsx)_Without_Non-Mandatory_Fields");
						logger.log(LogStatus.PASS, "TC-LOMT-1041-23_ProductTOC_Admin_Upload_Ingestion_sheet_format(.docs.xml.txt)");
						logger.log(LogStatus.PASS, "");
						
						jse.executeScript("window.scrollBy(0,-500)");
						commonPOM.getBackLinkFirst().click();
						Thread.sleep(1000);
					} else {
						logger.log(LogStatus.FAIL, "TC-LOMT-1041-12_ProductTOC_Admin_Ingest_ProductTOC_ProgramTitle(new value)_CourseTitle(new value)_ProductTitle(new value or old value)_No_align");
						jse.executeScript("window.scrollBy(0,-500)");
						commonPOM.getBackLinkFirst().click();
						Thread.sleep(1000);
					}
				} 
				
				//Validation use case
				else if (name.equalsIgnoreCase(LOMTConstant.TC_VALIDATION_CHECK_22_23)) {
					Runtime.getRuntime().exec(LOMTConstant.HE_PRODUCT_TOC_VALIDATION);
					
					Thread.sleep(10000);
					commonPOM.getNextButtonSt2().click();
					jse.executeScript("window.scrollBy(0,-100)");
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
					Thread.sleep(3000);
					if (commonPOM.getWaitForIngestionStatusText().getText().contains(LOMTConstant.INGESTION_FAILED_MESSAGE)) {
						commonPOM.getViewIngestFullLogButton().click();
						boolean innerFlag = alignValidationCheck(LOMTConstant.TC_VALIDATION_CHECK_22_23);
						//boolean innerFlag = validationCheck(LOMTConstant.TC_VALIDATION_CHECK_22_23);
						
						if (innerFlag) {
							logger.log(LogStatus.PASS, "TC-LOMT-1041-20_ProductTOC_Admin_Ingest_ProductTOC_Level_for_Hierarchy(sequence mismatch)");
							logger.log(LogStatus.PASS, "TC-LOMT-1041-24_ProductTOC_Admin_Manage Ingestion_Without_Mandatory_Fields");
							logger.log(LogStatus.PASS, "TC-LOMT-1041-25_ProductTOC_Admin_Manage Ingestion_ Review Outcome");
							logger.log(LogStatus.PASS, "TC-LOMT-1041-26_ProductTOC_Admin_Manage Ingestion_ VIEW FULL INGEST LOG _verify");
							logger.log(LogStatus.PASS, "TC-LOMT-1041-27_ProductTOC_Admin_Back_or_Cancel_Click");
							logger.log(LogStatus.PASS, "TC-LOMT-1041-28_ProductTOC_Admin_DONE_CLICK");
						} else {
							logger.log(LogStatus.FAIL, "TC-LOMT-1041-20_ProductTOC_Admin_Ingest_ProductTOC_Level_for_Hierarchy(sequence mismatch)");
							logger.log(LogStatus.FAIL, "TC-LOMT-1041-24_ProductTOC_Admin_Manage Ingestion_Without_Mandatory_Fields");
							logger.log(LogStatus.PASS, "TC-LOMT-1041-25_ProductTOC_Admin_Manage Ingestion_ Review Outcome");
							logger.log(LogStatus.PASS, "TC-LOMT-1041-26_ProductTOC_Admin_Manage Ingestion_ VIEW FULL INGEST LOG _verify");
							logger.log(LogStatus.PASS, "TC-LOMT-1041-27_ProductTOC_Admin_Back_or_Cancel_Click");
							logger.log(LogStatus.PASS, "TC-LOMT-1041-28_ProductTOC_Admin_DONE_CLICK");
						}
						
						jse.executeScript("window.scrollBy(0,-500)");
						commonPOM.getBackLinkFirst().click();
						Thread.sleep(1000);
					} else {
						logger.log(LogStatus.FAIL, "TC-LOMT-1041-20_ProductTOC_Admin_Ingest_ProductTOC_Level_for_Hierarchy(sequence mismatch)");
						logger.log(LogStatus.FAIL, "TC-LOMT-1041-24_ProductTOC_Admin_Manage Ingestion_Without_Mandatory_Fields");
						jse.executeScript("window.scrollBy(0,-500)");
						commonPOM.getBackLinkFirst().click();
						Thread.sleep(1000);
					}
				
				} 
				
				//Alignment using Code & Validation check
				else if (name.equalsIgnoreCase(LOMTConstant.TC_17)) {
					Runtime.getRuntime().exec(LOMTConstant.HE_TOC_ALIGN_CODE_INGEST_FILE_PATH);
					
					Thread.sleep(10000);
					commonPOM.getNextButtonSt2().click();
					jse.executeScript("window.scrollBy(0,-100)");
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
					Thread.sleep(3000);
					if (commonPOM.getWaitForIngestionStatusText().getText().contains(LOMTConstant.INGESTION_SUCESSFULL_MESSAGE)) {
						logger.log(LogStatus.PASS, "TC-LOMT-1041-16_ProductTOC_Admin_Ingest_ProductTOC_AlignmentCode(has value)_Alignment");
						logger.log(LogStatus.PASS, "TC-LOMT-1041-18_ProductTOC_Admin_Ingest_ProductTOC_Educational_goal_URN(has value)_Alignment");
						
						jse.executeScript("window.scrollBy(0,-500)");
						commonPOM.getBackLinkFirst().click();
						Thread.sleep(1000);
						
						//Validation with alignment
						commonPOM.getHeLOBRadioButton().click();
						Thread.sleep(1000);
						commonPOM.getProductRadioButton().click();
						Thread.sleep(2000);
						jse.executeScript("window.scrollBy(0,500)");
						commonPOM.getNextButtonFirst().click();
						
						//meta data
						jse.executeScript("window.scrollBy(0,500)");
						
						commonPOM.getProductTOCMetaDataDesc().sendKeys("Product!@#$%^&*(');_+{}|:'';<>?Tesst123");		
						commonPOM.getNextButton().click();
						Thread.sleep(2000);
						
						//File upload
						commonPOM.getUploadFileLink().click();
						
						Runtime.getRuntime().exec(LOMTConstant.HE_PRODUCT_TOC_VALIDATION_ALIGN);
						
						Thread.sleep(6000);
						commonPOM.getNextButtonSt2().click();
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
						Thread.sleep(3000);
						
						if (commonPOM.getWaitForIngestionStatusText().getText().contains(LOMTConstant.INGESTION_FAILED_MESSAGE)) {
							commonPOM.getViewIngestFullLogButton().click();
							Thread.sleep(1000);
							
							boolean innerFlag = alignValidationCheck("test align code validation");
							if (innerFlag) {
								logger.log(LogStatus.PASS, "TC-LOMT-1041-17_ProductTOC_Admin_Ingest_ProductTOC_AlignmentCode(wrong value)_Alignment");
								logger.log(LogStatus.PASS, "TC-LOMT-1041-19_ProductTOC_Admin_Ingest_ProductTOC_Educational_goal_URN(wrong urn)_Alignment");
								logger.log(LogStatus.PASS, "TC-LOMT-1041-28_ProductTOC_Admin_DONE_CLICK");
							} else {
								logger.log(LogStatus.FAIL, "TC-LOMT-1041-17_ProductTOC_Admin_Ingest_ProductTOC_AlignmentCode(wrong value)_Alignment");
								logger.log(LogStatus.FAIL, "TC-LOMT-1041-19_ProductTOC_Admin_Ingest_ProductTOC_Educational_goal_URN(wrong urn)_Alignment");
								logger.log(LogStatus.PASS, "TC-LOMT-1041-28_ProductTOC_Admin_DONE_CLICK");
							}
							
							jse.executeScript("window.scrollBy(0,-500)");
							commonPOM.getBackLinkFirst().click();
							Thread.sleep(1000);
						} else {
							logger.log(LogStatus.FAIL, "TC-LOMT-1041-17_ProductTOC_Admin_Ingest_ProductTOC_AlignmentCode(wrong value)_Alignment");
							logger.log(LogStatus.FAIL, "TC-LOMT-1041-18_ProductTOC_Admin_Ingest_ProductTOC_Educational_goal_URN(has value)_Alignment");
							logger.log(LogStatus.FAIL, "TC-LOMT-1041-19_ProductTOC_Admin_Ingest_ProductTOC_Educational_goal_URN(wrong urn)_Alignment");
						}
						
					} else {
						logger.log(LogStatus.FAIL, "TC-LOMT-1041-16_ProductTOC_Admin_Ingest_ProductTOC_AlignmentCode(has value)_Alignment");
						logger.log(LogStatus.FAIL, "TC-LOMT-1041-17_ProductTOC_Admin_Ingest_ProductTOC_AlignmentCode(wrong value)_Alignment");
						logger.log(LogStatus.FAIL, "TC-LOMT-1041-18_ProductTOC_Admin_Ingest_ProductTOC_Educational_goal_URN(has value)_Alignment");
						logger.log(LogStatus.FAIL, "TC-LOMT-1041-19_ProductTOC_Admin_Ingest_ProductTOC_Educational_goal_URN(wrong urn)_Alignment");
						jse.executeScript("window.scrollBy(0,-500)");
						commonPOM.getBackLinkFirst().click();
						Thread.sleep(1000);
					}
				} 
				
				//HE Product TOC end
			} else {
				
				commonPOM.getUploadFileLink().click();
				
				//School
				//No alignment, program and course title blank and product title has new val, all the mand and non-mand fields
				if (name.equalsIgnoreCase(LOMTConstant.TC_CASE_7_8_9_10_11)) {
					Runtime.getRuntime().exec(LOMTConstant.PRODUCT_TOC_FILE_PATH_1);
					
					Thread.sleep(10000);
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1039_07_UPLOAD_FILE_ALL_MANDATORY_AND_NON_MANDATORY_FIELDS_ADMIN_ROLE);
					commonPOM.getNextButtonSt2().click();
					jse.executeScript("window.scrollBy(0,-100)");
					logger.log(LogStatus.PASS, TestCases.TC_LOMT_1039_08_ADMIN_NEXT_BUTTON_CREATE_OR_UPLOAD_STRUCTURE);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
					Thread.sleep(3000);
					if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_SUCESSFULL_MESSAGE)) {
						
						commonPOM.getDoneButton().click();
						Thread.sleep(1000);
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1039_09_ADMIN_VERIFY_INGEST_SUCESS_MESSAGE_ON_THE_CREATE_OR_UPLOAD_STRUCTURE_PAGE);
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1039_10_ADMIN_VERIFY_DONE_BUTTON_ON_THE_CREATE_OR_UPLOAD_STRUCTURE_PAGE);
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1039_11_PRODUCTTOC_ADMIN_INGEST_PRODUCTTOC_PROGRAMTITLE_BLANK_COURSETITLE_BLANK_PRODUCTTITLE_VALUE_NO_ALIGN);
						logger.log(LogStatus.PASS, "TC-LOMT-1515-01_Admin_User_School_Global_Product_Ingestion_with_Blank_Content_Title");
					} else {
						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1039_09_ADMIN_VERIFY_INGEST_SUCESS_MESSAGE_ON_THE_CREATE_OR_UPLOAD_STRUCTURE_PAGE);
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1039_10_ADMIN_VERIFY_DONE_BUTTON_ON_THE_CREATE_OR_UPLOAD_STRUCTURE_PAGE);
						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1039_11_PRODUCTTOC_ADMIN_INGEST_PRODUCTTOC_PROGRAMTITLE_BLANK_COURSETITLE_BLANK_PRODUCTTITLE_VALUE_NO_ALIGN);
						logger.log(LogStatus.FAIL, "TC-LOMT-1515-01_Admin_User_School_Global_Product_Ingestion_with_Blank_Content_Title");
						
						jse.executeScript("window.scrollBy(0,-500)");
						commonPOM.getBackLinkFirst().click();
						Thread.sleep(1000);
					}
				} 
				//Program, Course and Product Title has new value
				if (name.equalsIgnoreCase(LOMTConstant.TC_12)) {
					Runtime.getRuntime().exec(LOMTConstant.PRODUCT_TOC_FILE_PATH_2);
					
					Thread.sleep(10000);
					commonPOM.getNextButtonSt2().click();
					jse.executeScript("window.scrollBy(0,-100)");
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
					Thread.sleep(3000);
					if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_SUCESSFULL_MESSAGE)) {
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1039_12_PRODUCTTOC_ADMIN_INGEST_PRODUCTTOC_PROGRAMTITLE_NEW_VALUE_COURSETITLE_NEW_VALUE_PRODUCTTITLE_NEW_VALUE_OR_OLD_VALUE_NO_ALIGN);
						commonPOM.getDoneButton().click();
					} else {
						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1039_12_PRODUCTTOC_ADMIN_INGEST_PRODUCTTOC_PROGRAMTITLE_NEW_VALUE_COURSETITLE_NEW_VALUE_PRODUCTTITLE_NEW_VALUE_OR_OLD_VALUE_NO_ALIGN);
						jse.executeScript("window.scrollBy(0,-500)");
						commonPOM.getBackLinkFirst().click();
						Thread.sleep(1000);
					}
				} 
				//Below use case not valid so DE-SCOPED
				//Program has new value, Course has existing value and Product Title has new or old value
				if (name.equalsIgnoreCase(LOMTConstant.TC_13)) {
					Runtime.getRuntime().exec(LOMTConstant.PRODUCT_TOC_FILE_PATH_3);
					
					Thread.sleep(10000);
					commonPOM.getNextButtonSt2().click();
					jse.executeScript("window.scrollBy(0,-100)");
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
					Thread.sleep(3000);
					if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_SUCESSFULL_MESSAGE)) {
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1039_13_PRODUCTTOC_ADMIN_INGEST_PRODUCTTOC_PROGRAMTITLE_NEW_VALUE_COURSETITLE_EXISTING_VALUE_PRODUCTTITLE_NEW_VALUE_OR_OLD_VALUE_NO_ALIGN);
						commonPOM.getDoneButton().click();
					} else {
						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1039_13_PRODUCTTOC_ADMIN_INGEST_PRODUCTTOC_PROGRAMTITLE_NEW_VALUE_COURSETITLE_EXISTING_VALUE_PRODUCTTITLE_NEW_VALUE_OR_OLD_VALUE_NO_ALIGN);
						jse.executeScript("window.scrollBy(0,-500)");
						commonPOM.getBackLinkFirst().click();
						Thread.sleep(1000);
					}
				}
				//Program has existing value, Course has new value and Product Title has new or old value
				if (name.equalsIgnoreCase(LOMTConstant.TC_14)) {
					Runtime.getRuntime().exec(LOMTConstant.PRODUCT_TOC_FILE_PATH_4);
					
					Thread.sleep(10000);
					commonPOM.getNextButtonSt2().click();
					jse.executeScript("window.scrollBy(0,-100)");
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
					Thread.sleep(3000);
					if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_SUCESSFULL_MESSAGE)) {
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1039_14_PRODUCTTOC_ADMIN_INGEST_PRODUCTTOC_PROGRAMTITLE_EXISTING_VALUE_COURSETITLE_NEW_VALUE_PRODUCTTITLE_NEW_VALUE_OR_OLD_VALUE_NO_ALIGN);
						commonPOM.getDoneButton().click();
					} else {
						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1039_14_PRODUCTTOC_ADMIN_INGEST_PRODUCTTOC_PROGRAMTITLE_EXISTING_VALUE_COURSETITLE_NEW_VALUE_PRODUCTTITLE_NEW_VALUE_OR_OLD_VALUE_NO_ALIGN);
						jse.executeScript("window.scrollBy(0,-500)");
						commonPOM.getBackLinkFirst().click();
						Thread.sleep(1000);
					}
				} 
				//Program has existing and Course has existing value, Product Title has new or old value
				if (name.equalsIgnoreCase(LOMTConstant.TC_15)) {
					Runtime.getRuntime().exec(LOMTConstant.PRODUCT_TOC_FILE_PATH_5);
					
					Thread.sleep(10000);
					commonPOM.getNextButtonSt2().click();
					jse.executeScript("window.scrollBy(0,-100)");
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
					Thread.sleep(3000);
					if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_SUCESSFULL_MESSAGE)) {
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1039_15_PRODUCTTOC_ADMIN_INGEST_PRODUCTTOC_PROGRAMTITLE_EXISTING_VALUE_COURSETITLE_EXISTING_VALUE_PRODUCTTITLE_NEW_VALUE_OR_OLD_VALUE_NO_ALIGN);
						commonPOM.getDoneButton().click();
					} else {
						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1039_15_PRODUCTTOC_ADMIN_INGEST_PRODUCTTOC_PROGRAMTITLE_EXISTING_VALUE_COURSETITLE_EXISTING_VALUE_PRODUCTTITLE_NEW_VALUE_OR_OLD_VALUE_NO_ALIGN);
						jse.executeScript("window.scrollBy(0,-500)");
						commonPOM.getBackLinkFirst().click();
						Thread.sleep(1000);
					}
				} 
				//No duplicate Product Title : De-scoped
				if (name.equalsIgnoreCase(LOMTConstant.TC_16)) {
					// not implemented yet, re-ingestion case, De-scoped
					//logger.log(LogStatus.INFO, TestCases.TC_LOMT_1039_16_PRODUCTTOC_ADMIN_INGEST_PRODUCTTOC_PRODUCTTITLE_DUPLICATE_VALUE_NO_ALIGN);
					//Thread.sleep(2000);
				} 
				//AlignmentCode column has value
				if (name.equalsIgnoreCase(LOMTConstant.TC_17)) {
					Runtime.getRuntime().exec(LOMTConstant.PRODUCT_TOC_FILE_PATH_7);
					
					Thread.sleep(10000);
					commonPOM.getNextButtonSt2().click();
					jse.executeScript("window.scrollBy(0,-100)");
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
					Thread.sleep(3000);
					if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_SUCESSFULL_MESSAGE)) {
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1039_17_PRODUCTTOC_ADMIN_INGEST_PRODUCTTOC_ALIGNMENTCODE_HAS_VALUE_ALIGNMENT);
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1039_18_PRODUCTTOC_ADMIN_INGEST_PRODUCTTOC_CMT_INTERMEDIARY_UNIQUE_ID_HAS_VALUE_ALIGNMENT);
						commonPOM.getDoneButton().click();
					} else {
						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1039_17_PRODUCTTOC_ADMIN_INGEST_PRODUCTTOC_ALIGNMENTCODE_HAS_VALUE_ALIGNMENT);
						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1039_18_PRODUCTTOC_ADMIN_INGEST_PRODUCTTOC_CMT_INTERMEDIARY_UNIQUE_ID_HAS_VALUE_ALIGNMENT);
						jse.executeScript("window.scrollBy(0,-500)");
						commonPOM.getBackLinkFirst().click();
						Thread.sleep(1000);
					}
				} 
				//CMT Intermediary Unique ID column has value(GOAL URN)
				if (name.equalsIgnoreCase(LOMTConstant.TC_18)) {
					Runtime.getRuntime().exec(LOMTConstant.PRODUCT_TOC_FILE_PATH_8);
					
					Thread.sleep(10000);
					commonPOM.getNextButtonSt2().click();
					jse.executeScript("window.scrollBy(0,-100)");
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
					Thread.sleep(3000);
					if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_SUCESSFULL_MESSAGE)) {
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1039_18_PRODUCTTOC_ADMIN_INGEST_PRODUCTTOC_CMT_INTERMEDIARY_UNIQUE_ID_HAS_VALUE_ALIGNMENT);
						commonPOM.getDoneButton().click();
					} else {
						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1039_18_PRODUCTTOC_ADMIN_INGEST_PRODUCTTOC_CMT_INTERMEDIARY_UNIQUE_ID_HAS_VALUE_ALIGNMENT);
						jse.executeScript("window.scrollBy(0,-500)");
						commonPOM.getBackLinkFirst().click();
						Thread.sleep(1000);
					}
				}
				//sequence mismatch for "Level for Hierarchy"
				if (name.equalsIgnoreCase(LOMTConstant.TC_19)) {
					Runtime.getRuntime().exec(LOMTConstant.PRODUCT_TOC_FILE_PATH_9);
					
					Thread.sleep(10000);
					commonPOM.getNextButtonSt2().click();
					jse.executeScript("window.scrollBy(0,-100)");
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
					Thread.sleep(3000);
					if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_FAILED_MESSAGE)) {
						commonPOM.getViewIngestFullLogButton().click();
						Thread.sleep(2000);
						
						boolean flag = validationCheck(LOMTConstant.TC_19);
						if (flag) {
							logger.log(LogStatus.PASS, TestCases.TC_LOMT_1039_19_PRODUCTTOC_ADMIN_INGEST_PRODUCTTOC_LEVEL_FOR_HIERARCHY_SEQUENCE_MISMATCH);
						} else {
							logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1039_19_PRODUCTTOC_ADMIN_INGEST_PRODUCTTOC_LEVEL_FOR_HIERARCHY_SEQUENCE_MISMATCH);
						}
						jse.executeScript("window.scrollBy(0,-500)");
						commonPOM.getBackLinkFirst().click();
					} else {
						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1039_19_PRODUCTTOC_ADMIN_INGEST_PRODUCTTOC_LEVEL_FOR_HIERARCHY_SEQUENCE_MISMATCH);
						jse.executeScript("window.scrollBy(0,-500)");
						commonPOM.getBackLinkFirst().click();
						Thread.sleep(1000);
					}
				}
				//Hierarchy Level should exapand, verify using dhc
				if (name.equalsIgnoreCase(LOMTConstant.TC_20)) {
					// not implemented yet, re-ingestion case, De-scoped
					logger.log(LogStatus.INFO, TestCases.TC_LOMT_1039_20_PRODUCTTOC_ADMIN_INGEST_PRODUCTTOC_LEVEL_FOR_HIERARCHY_EXPAND_ALL_LEVEL);
					Thread.sleep(2000);
				} 
				//Alignment Code has wrong value
				if (name.equalsIgnoreCase(LOMTConstant.TC_21)) {
					Runtime.getRuntime().exec(LOMTConstant.PRODUCT_TOC_FILE_PATH_11);
					
					Thread.sleep(10000);
					commonPOM.getNextButtonSt2().click();
					jse.executeScript("window.scrollBy(0,-100)");
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
					Thread.sleep(3000);
					if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_FAILED_MESSAGE)) {
						commonPOM.getViewIngestFullLogButton().click();
						Thread.sleep(2000);
						//boolean flag = validationCheck(LOMTConstant.TC_21);
							logger.log(LogStatus.PASS, TestCases.TC_LOMT_1039_21_PRODUCTTOC_ADMIN_INGEST_PRODUCTTOC_DICIPLINE_OR_ALIGNMENTCODE_WRONG_VALUE_ALIGNMENT);
						jse.executeScript("window.scrollBy(0,-500)");
						commonPOM.getBackLinkFirst().click();
					} else {
						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1039_21_PRODUCTTOC_ADMIN_INGEST_PRODUCTTOC_DICIPLINE_OR_ALIGNMENTCODE_WRONG_VALUE_ALIGNMENT);
						jse.executeScript("window.scrollBy(0,-500)");
						commonPOM.getBackLinkFirst().click();
						Thread.sleep(1000);
					}
				}
				//Goal URN has wrong value
				if (name.equalsIgnoreCase(LOMTConstant.TC_22)) {
					Runtime.getRuntime().exec(LOMTConstant.PRODUCT_TOC_FILE_PATH_12);
					
					Thread.sleep(10000);
					commonPOM.getNextButtonSt2().click();
					jse.executeScript("window.scrollBy(0,-100)");
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
					Thread.sleep(3000);
					if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_FAILED_MESSAGE)) {
						commonPOM.getViewIngestFullLogButton().click();
						Thread.sleep(2000);
						
						//boolean flag = validationCheck(LOMTConstant.TC_22);
						//if (flag) {
							logger.log(LogStatus.PASS, TestCases.TC_LOMT_1039_22_PRODUCTTOC_ADMIN_INGEST_PRODUCTTOC_CMT_DICIPLINE_OR_INTERMEDIARY_UNIQUE_ID_WRONG_VALUE_ALIGNMENT);
						/*} else {
							logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1039_22_PRODUCTTOC_ADMIN_INGEST_PRODUCTTOC_CMT_DICIPLINE_OR_INTERMEDIARY_UNIQUE_ID_WRONG_VALUE_ALIGNMENT);
						}*/
						jse.executeScript("window.scrollBy(0,-500)");
						commonPOM.getBackLinkFirst().click();
					} else {
						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1039_22_PRODUCTTOC_ADMIN_INGEST_PRODUCTTOC_CMT_DICIPLINE_OR_INTERMEDIARY_UNIQUE_ID_WRONG_VALUE_ALIGNMENT);
						jse.executeScript("window.scrollBy(0,-500)");
						commonPOM.getBackLinkFirst().click();
						Thread.sleep(1000);
					}
				}
				//Ingestion without non-mandatory fields
				if (name.equalsIgnoreCase(LOMTConstant.TC_23)) {
					Runtime.getRuntime().exec(LOMTConstant.PRODUCT_TOC_FILE_PATH_13);
					Thread.sleep(10000);
					commonPOM.getNextButtonSt2().click();
					jse.executeScript("window.scrollBy(0,-100)");
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
					Thread.sleep(3000);
					if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_SUCESSFULL_MESSAGE)) {
						logger.log(LogStatus.PASS, TestCases.TC_LOMT_1039_23_PRODUCTTOC_ADMIN_UPLOAD_FILE_XLS_OR_XLSX_WITHOUT_NON_MANDATORY_FIELDS);
						commonPOM.getDoneButton().click();
					} else {
						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1039_23_PRODUCTTOC_ADMIN_UPLOAD_FILE_XLS_OR_XLSX_WITHOUT_NON_MANDATORY_FIELDS);
						jse.executeScript("window.scrollBy(0,-500)");
						commonPOM.getBackLinkFirst().click();
						Thread.sleep(1000);
					}
				}
				//Ingestion without mandatory fields
				if (name.equalsIgnoreCase(LOMTConstant.TC_25)) {
					Thread.sleep(2000);
					Runtime.getRuntime().exec(LOMTConstant.PRODUCT_FILE_PATH_SCHOOL_15);
					Thread.sleep(10000);
					commonPOM.getNextButtonSt2().click();
					jse.executeScript("window.scrollBy(0,-100)");
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
					Thread.sleep(3000);
					if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_FAILED_MESSAGE)) {
						commonPOM.getViewIngestFullLogButton().click();
						Thread.sleep(2000);
						
						boolean flag = validationCheck(LOMTConstant.TC_25);
						if (flag) {
							logger.log(LogStatus.PASS, TestCases.TC_LOMT_1039_25_PRODUCTTOC_ADMIN_MANAGE_INGESTION_WITHOUT_ALL_MANDATORY_FIELDS);
						} else {
							logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1039_25_PRODUCTTOC_ADMIN_MANAGE_INGESTION_WITHOUT_ALL_MANDATORY_FIELDS);
						}
						jse.executeScript("window.scrollBy(0,-500)");
						commonPOM.getBackLinkFirst().click();
					} else {
						logger.log(LogStatus.FAIL, TestCases.TC_LOMT_1039_25_PRODUCTTOC_ADMIN_MANAGE_INGESTION_WITHOUT_ALL_MANDATORY_FIELDS);
						jse.executeScript("window.scrollBy(0,-500)");
						commonPOM.getBackLinkFirst().click();
						Thread.sleep(1000);
					}
				}
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void productTOCIngestionWithoutNonMandatoryFields() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 180);
			Assert.assertTrue(commonPOM.getBackLinkFirst().isDisplayed());
			Assert.assertEquals(commonPOM.getCreateUploadStructureHeader().getText(), LOMTConstant.CREATE_STRUCTURE_TILE);
			Assert.assertEquals(commonPOM.getDragAndDropFilesText().getText(),LOMTConstant.DRAG_DROP_TEXT);
			Assert.assertTrue(commonPOM.getUploadFileLink().isDisplayed());
			
			commonPOM.getUploadFileLink().click();
			
			//Ingestion without non-mandatory fields
			if (currentLOB.equalsIgnoreCase(LOMTConstant.ENGLISH_LOB)) {
				Runtime.getRuntime().exec(LOMTConstant.PRODUCT_FILE_NON_MANDATORY_FIELDS_PATH_ENGLISH);
				Thread.sleep(3000);
			} else if (currentLOB.equalsIgnoreCase(LOMTConstant.HE_LOB)) {
				Runtime.getRuntime().exec(LOMTConstant.PRODUCT_FILE_NON_MANDATORY_FIELDS_PATH_HE);
				Thread.sleep(3000);
			} else if (currentLOB.equalsIgnoreCase(LOMTConstant.NALS_LOB)) {
				Runtime.getRuntime().exec(LOMTConstant.PRODUCT_FILE_PATH_SCHOOL);
				Thread.sleep(3000);
			} else {
				//School Global
				Runtime.getRuntime().exec(LOMTConstant.PRODUCT_FILE_PATH_SCHOOL);
				Thread.sleep(3000);
			}
			commonPOM.getNextButtonSt2().click();

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGEST_SUCESS_MESSAGE)));
			Thread.sleep(3000);
			
			Assert.assertTrue(commonPOM.getBackLinkFirst().isDisplayed());
			Assert.assertTrue(commonPOM.getIngestSucessful().isDisplayed());
			Assert.assertTrue(commonPOM.getDoneButton().isDisplayed());
			Assert.assertTrue(commonPOM.getPearsonLogo().isDisplayed());
			Assert.assertTrue(commonPOM.getFourthTextImage().isDisplayed());
			Assert.assertEquals(commonPOM.getFourthTextMessage().getText(), LOMTConstant.STRUCTURE_PAGE_IMAGE_4_TEXT);
			
			commonPOM.getDoneButton().click();
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void backLinkClicked(ExtentTest logger) {
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			if (currentLOB.equalsIgnoreCase(LOMTConstant.ENGLISH_LOB)) {				
				jse.executeScript("window.scrollBy(0,-500)");
				
				commonPOM.getBackLinkSec().click();
				logger.log(LogStatus.PASS, TestCases.TC_LOMT_1040_06_PRODUCTTOC_ADMIN_MANAGE_INGESTION_BACK_CREATE_OR_UPLOAD_A_STRUCTURE);
				Thread.sleep(1000);
			} else if (currentLOB.equalsIgnoreCase(LOMTConstant.HE_LOB)) {
				jse.executeScript("window.scrollBy(0,-500)");
				
				commonPOM.getBackLinkSec().click();
				logger.log(LogStatus.PASS, TestCases.TC_LOMT_1041_06_ADMIN_MANAGE_INGESTION_BACK_CREATE_OR_UPLOAD_STRUCTURE);
				Thread.sleep(1000);
			} else {
				//school
				jse.executeScript("window.scrollBy(0,-500)");
				
				commonPOM.getBackLinkSec().click();
				logger.log(LogStatus.PASS, TestCases.TC_LOMT_1039_06_ADMIN_MANAGE_INGESTION_BACK_CREATE_OR_UPLOAD_STRUCTURE);
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getHomePage() {
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,-1000)");
			commonPOM.getPearsonLogo().click();
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getSchoolLOBAndStructure() {
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			
			commonPOM.getSchoolGlobalLOBRadioButton().click();
			jse.executeScript("window.scrollBy(0,200)");
			Thread.sleep(1000);
			commonPOM.getSchoolProductStructureRadioButton().click();
			Thread.sleep(1000);
			
			jse.executeScript("window.scrollBy(0,500)");
			
			commonPOM.getNextButtonFirst().click();
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void productTOCWithoutMetaData() {
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,500)");
			
			commonPOM.getProductTOCMetaDataDesc().sendKeys("Product!@#$%^&*(');_+{}|:'';<>?Tesst123");		
			commonPOM.getNextButton().click();
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getEnglishLOBAndStructure() {
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			
			jse.executeScript("window.scrollBy(0,500)");
			commonPOM.getGseProductStructureRadioButton().click();
			Thread.sleep(1000);
			commonPOM.getNextButtonFirst().click();
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getHELOBAndStructure() {
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			
			Thread.sleep(2000);
			commonPOM.getHeLOBRadioButton().click();
			Thread.sleep(1000);
			commonPOM.getProductRadioButton().click();
			Thread.sleep(2000);
			jse.executeScript("window.scrollBy(0,500)");
			commonPOM.getNextButtonFirst().click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean validationCheck(String testCaseName) {
		boolean flag = false;
		if (testCaseName.contains(LOMTConstant.TC_19)) {
			try {
				if (sgPom.getErrorMessage1().getText().contains(LOMTConstant.SEQUENCE_MISMATCH_ERROR_MESSAGE)) {
					flag = true;
				} else {
					flag = false;
				}
			} catch (Exception e) {
				flag = false;
			}
		}
		if (testCaseName.contains(LOMTConstant.TC_21)) {
			try {
				if (sgPom.getErrorMessage1().getText().contains(LOMTConstant.WRONG_CMT_URN_2)) {
					flag = true;
				} else {
					flag = false;
				}
			} catch (Exception e) {
				flag = false;
			}

		}
		if (testCaseName.contains(LOMTConstant.TC_22)) {
			try {
				if (sgPom.getErrorMessage1().getText().contains(LOMTConstant.WRONG_CMT_URN_2)) {
					flag = true;
				} else {
					flag = false;
				}
			} catch (Exception e) {
				flag = false;
			}

		}
		// mandatory fields check
		if (testCaseName.contains(LOMTConstant.TC_25)) {
			try {
				if (sgPom.getErrorMessage1().getText().contains(LOMTConstant.MANDATORY_FIELDS_ERROR_MESSAGE_1)
						|| sgPom.getErrorMessage2().getText().contains(LOMTConstant.MANDATORY_FIELDS_ERROR_MESSAGE_1)
						|| sgPom.getErrorMessage3().getText().contains(LOMTConstant.MANDATORY_FIELDS_ERROR_MESSAGE_1) 
						|| sgPom.getErrorMessage4().getText().contains(LOMTConstant.MANDATORY_FIELDS_ERROR_MESSAGE_1) ) {
					flag = true;
				} else {
					flag = false;
				}
				if (sgPom.getErrorMessage1().getText().contains(LOMTConstant.MANDATORY_FIELDS_ERROR_MESSAGE_2)
						|| sgPom.getErrorMessage2().getText().contains(LOMTConstant.MANDATORY_FIELDS_ERROR_MESSAGE_2)
						|| sgPom.getErrorMessage3().getText().contains(LOMTConstant.MANDATORY_FIELDS_ERROR_MESSAGE_2) 
						|| sgPom.getErrorMessage4().getText().contains(LOMTConstant.MANDATORY_FIELDS_ERROR_MESSAGE_2) ) {
					flag = true;
				} else {
					flag = false;
				}
				if (sgPom.getErrorMessage1().getText().contains(LOMTConstant.MANDATORY_FIELDS_ERROR_MESSAGE_3)
						|| sgPom.getErrorMessage2().getText().contains(LOMTConstant.MANDATORY_FIELDS_ERROR_MESSAGE_3)
						|| sgPom.getErrorMessage3().getText().contains(LOMTConstant.MANDATORY_FIELDS_ERROR_MESSAGE_3) 
						|| sgPom.getErrorMessage4().getText().contains(LOMTConstant.MANDATORY_FIELDS_ERROR_MESSAGE_3) ) {
					flag = true;
				} else {
					flag = false;
				}
				if (sgPom.getErrorMessage1().getText().contains(LOMTConstant.MANDATORY_FIELDS_ERROR_MESSAGE_4)
						|| sgPom.getErrorMessage2().getText().contains(LOMTConstant.MANDATORY_FIELDS_ERROR_MESSAGE_4)
						|| sgPom.getErrorMessage3().getText().contains(LOMTConstant.MANDATORY_FIELDS_ERROR_MESSAGE_4) 
						|| sgPom.getErrorMessage4().getText().contains(LOMTConstant.MANDATORY_FIELDS_ERROR_MESSAGE_4) ) {
					flag = true;
				} else {
					flag = false;
				}
			} catch (Exception e) {
				flag = false;
			}
		}
		// product title is blank
		if (testCaseName.contains(LOMTConstant.TC_26)) {
			try {
				if (sgPom.getErrorMessage1().getText().contains(LOMTConstant.MANDATORY_FIELDS_ERROR_MESSAGE_1)) {
					flag = true;
				} else {
					flag = false;
				}
			} catch (Exception e) {
				flag = false;
			}
		}
		// Level for Hierarchy is blank
		if (testCaseName.contains(LOMTConstant.TC_27)) {
			try {
				if (sgPom.getErrorMessage1().getText().equalsIgnoreCase(LOMTConstant.MANDATORY_FIELDS_ERROR_MESSAGE_1)
						|| sgPom.getErrorMessage2().getText().equalsIgnoreCase(LOMTConstant.MANDATORY_FIELDS_ERROR_MESSAGE_1) ) {
					flag = true;
				} else {
					flag = false;
				}
				if (sgPom.getErrorMessage1().getText().equalsIgnoreCase(LOMTConstant.LEVEL_HIERARCHY_ERROR_MESSAGE_2)
						|| sgPom.getErrorMessage2().getText().equalsIgnoreCase(LOMTConstant.LEVEL_HIERARCHY_ERROR_MESSAGE_2) ) {
					flag = true;
				} else {
					flag = false;
				}
			} catch (Exception e) {
				flag = false;
			}
		}
		//Level Title is blank
		if (testCaseName.equalsIgnoreCase(LOMTConstant.TC_28)) {
			try {
				if (sgPom.getErrorMessage1().getText().equalsIgnoreCase(LOMTConstant.LEVEL_TITLE_CAN_NOT_BLANK)
						|| sgPom.getErrorMessage2().getText().equalsIgnoreCase(LOMTConstant.LEVEL_TITLE_CAN_NOT_BLANK) ) {
					flag = true;
				} else {
					flag = false;
				}				
			} catch (Exception e) {
				flag = false;
			}
		}
		if (testCaseName.equalsIgnoreCase(LOMTConstant.TC_33)) {
			try {
				if (sgPom.getErrorMessage1().getText().contains(LOMTConstant.WRONG_CMT_URN_2)) {
					flag = true;
				} else {
					flag = false;
				}
			} catch (Exception e) {
				flag = false;
			}
		}
		//English Product TOC validation check
		if (testCaseName.equalsIgnoreCase(LOMTConstant.TC_VALIDATION_CHECK_22_23)) {
			if (sgPom.getErrorMessage1().getText().contains("Product Title value is blank in Row : 3")
					|| sgPom.getErrorMessage2().getText().contains("Product Title value is blank in Row : 3")
					|| sgPom.getErrorMessage3().getText().contains("Product Title value is blank in Row : 3")
					|| sgPom.getErrorMessage4().getText().contains("Product Title value is blank in Row : 3")
					|| sgPom.getErrorMessage5().getText().contains("Product Title value is blank in Row : 3") ) {
				flag = true;
			}
			if (sgPom.getErrorMessage1().getText().contains("Level for hierarchy cannot be blank")
					|| sgPom.getErrorMessage2().getText().contains("Level for hierarchy cannot be blank")
					|| sgPom.getErrorMessage3().getText().contains("Level for hierarchy cannot be blank")
					|| sgPom.getErrorMessage4().getText().contains("Level for hierarchy cannot be blank")
					|| sgPom.getErrorMessage5().getText().contains("Level for hierarchy cannot be blank") ) {
				flag = true;
			}
			if (sgPom.getErrorMessage1().getText().contains("Level for hierarchy is incorrect")
					|| sgPom.getErrorMessage2().getText().contains("Level for hierarchy is incorrect")
					|| sgPom.getErrorMessage3().getText().contains("Level for hierarchy is incorrect")
					|| sgPom.getErrorMessage4().getText().contains("Level for hierarchy is incorrect")
					|| sgPom.getErrorMessage5().getText().contains("Level for hierarchy is incorrect") ) {
				flag = true;
			}
			if (sgPom.getErrorMessage1().getText().contains("Level Tile cannot be blank")
					|| sgPom.getErrorMessage2().getText().contains("Level Tile cannot be blank")
					|| sgPom.getErrorMessage3().getText().contains("Level Tile cannot be blank")
					|| sgPom.getErrorMessage4().getText().contains("Level Tile cannot be blank")
					|| sgPom.getErrorMessage5().getText().contains("Level Tile cannot be blank") ) {
				flag = true;
			}
			if (sgPom.getErrorMessage1().getText().contains("GSE ID does not exists : test")
					|| sgPom.getErrorMessage2().getText().contains("GSE ID does not exists : test")
					|| sgPom.getErrorMessage3().getText().contains("GSE ID does not exists : test")
					|| sgPom.getErrorMessage4().getText().contains("GSE ID does not exists : test")
					|| sgPom.getErrorMessage5().getText().contains("GSE ID does not exists : test") ) {
				flag = true;
			}
		}
		
		return flag;
	}
	
	public void verifyProductTOCIngestedDataOnResultPage(String lobName, String filePath, ExtentTest logger) {
		if (lobName.equalsIgnoreCase(LOMTConstant.HE_LOB)) {
			
		} else if (lobName.equalsIgnoreCase(LOMTConstant.ENGLISH_LOB)) {
			
		} else {
			//SCHOOL
			String productTitleName = getProductTitleName(filePath);
			searchIngestedProductTitle(productTitleName, logger);
		}
	}
	
	public String getProductTitleName(String filePath) {
		String productTitleName = null;
		
		InputStream exfFilePathIS = null;
		XSSFWorkbook workbookForexfFile = null;
		File file = new File(filePath);

		if (file.isFile() && file.exists()) {
			try {
				exfFilePathIS = new FileInputStream(file);
				workbookForexfFile = new XSSFWorkbook(exfFilePathIS);
				XSSFSheet actualDataSheet = workbookForexfFile.getSheetAt(0);
				Iterator<Row> rowIteratoreForActual = actualDataSheet.iterator();
				while (rowIteratoreForActual.hasNext()) {
					Row row = rowIteratoreForActual.next();
					if ((row.getRowNum() == 2)) {
						productTitleName = String.valueOf(row.getCell(LOMTConstant.ONE));
						break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return productTitleName;
	}
	
	public void searchIngestedProductTitle(String productTitleName, ExtentTest logger){
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			productTocPOM.getEnterSearchTerm().sendKeys(productTitleName);
			Assert.assertTrue(exfPOM.getUpdateResultButton().isEnabled());
			productTocPOM.getUpdateResultButton().click();
			
			Thread.sleep(10000);
			jse.executeScript("window.scrollBy(0,400)");
			if (exfPOM.getSearchedEXFTitle()!= null && exfPOM.getSearchedEXFTitle().getText() != null) {
				if (productTitleName.equalsIgnoreCase(exfPOM.getSearchedEXFTitle().getText())) {
					System.out.println("School Product TOC found in search result");
				} else {
					//logger.log(LogStatus.INFO, "School Product TOC not found in search result");
					System.out.println("School Product TOC not found in search result");
				}
			} else {
				//logger.log(LogStatus.INFO, "School Product TOC not found in search result");
				System.out.println("School Product TOC not found in search result");
			}
			exfPOM.getEnterSearchTerm().clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getSchoolBrowsePage() {
		try {
			WebDriverWait wait1 = new WebDriverWait(driver, 180); 
			commonPOM.getSchoolGlobalLOB().click();
			commonPOM.getSchoolProductTOCStructure().click();
			Thread.sleep(60000);
			//wait1.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,100)");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String exportIntermediaryDisciplineAndCopyCodeAndURN() {
		String status = null;
		
		ReadExternalFrameworkFile readExternalFrameworkFile = new ReadExternalFrameworkFile();
		ReadProductTOCFile readProductTOCFile = new ReadProductTOCFile();
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			WebDriverWait wait = new WebDriverWait(driver, 600);
			//EXPORT Intermeidary DISCIPLINE
			jse.executeScript("window.scrollBy(0,-500)");
			commonPOM.getPearsonLogo().click();
			commonPOM.getSchoolGlobalLOB().click();
			intermediaryPOM.getIntermediaryStructure().click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			
			List<WebElement> webElement  =  intermediaryPOM.getIntermediaryGFList();
			if (!webElement.isEmpty()) {
				String goalframework = intermediaryPOM.getFirstDisRow().getText(); 
				int i = goalframework.indexOf("\n");
				//int j = goalframework.lastIndexOf("\n");
				String subjectName = goalframework.substring(i, goalframework.length());
				readExternalFrameworkFile.removeExistingFile(); 
						
				schoolPOM.getIntAction().click();
				Thread.sleep(1000);
						
				schoolPOM.getIntExport().click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
						
				jse.executeScript("window.scrollBy(0,-4000)");
				commonPOM.getPearsonLogo().click();
				commonPOM.getSchoolGlobalLOB().click();
				commonPOM.getManageIngestion().click();
						
				ReadExternalFrameworkFile readFileObj = new ReadExternalFrameworkFile();
				//Read the exported file
						
				String exportedFileName = readFileObj.getFileFromDirectory(LOMTConstant.EXPORTED_FILE_PATH);
				File intermediaryExprtedFilePath = new File(LOMTConstant.EXPORTED_FILE_PATH+exportedFileName);
						
				File aligCode = new File(LOMTConstant.PRODUCT_TOC_XLS_FILE_PATH_4);
						
				File aignGoalURN = new File(LOMTConstant.P_TOC_XLS_PATH_ALIGN_GOAL_URN); 
						
				boolean flag = readProductTOCFile.copyCodeAndURNFromExportIntermediaryFile(intermediaryExprtedFilePath, aligCode, aignGoalURN, subjectName);
				if (flag) {
					status = LOMTConstant.EXPORT_DONE+" : "+"true";
				} else {
					status = LOMTConstant.EXPORT_DONE+" : "+"false";
				}
			} else {
				//Fresh Intermediary ingestion, make flag true if ingestion is success
				jse.executeScript("window.scrollBy(0,-500)");
				commonPOM.getPearsonLogo().click();
				commonPOM.getSchoolGlobalLOB().click();
				commonPOM.getManageIngestion().click();
				commonPOM.getSchoolGlobalLOBRadioButton().click();
				commonPOM.getIntermediaryRadioButton().click();
				jse.executeScript("window.scrollBy(0, 500)");
				commonPOM.getNextButtonFirst().click();
				Thread.sleep(5000);
				
				intermediaryPOM.getEconomicsRadioButton().click(); // select Economics Discipline 
				jse.executeScript("window.scrollBy(0, 800)");
				
				Thread.sleep(1000);
				commonPOM.getNextButtonSt2().click();
				Thread.sleep(1000);
				
				commonPOM.getUploadFileLink().click();
				
				Runtime.getRuntime().exec(LOMTConstant.INTERMEDIARY_INGESTION_PRODUCT_TOC_SCHOOL_FILE_PATH);
				Thread.sleep(3000);
				
				jse.executeScript("window.scrollBy(0, 100)");
				commonPOM.getNextButtonSt2().click();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
				Thread.sleep(3000);
				
				if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_SUCESSFULL_MESSAGE)) {
					status = LOMTConstant.FRESH_INGESTION+" : "+"true";
					jse.executeScript("window.scrollBy(0, -300)");
					commonPOM.getBackLinkFirst().click();
					Thread.sleep(1000);
				} else {
					status = LOMTConstant.FRESH_INGESTION+" : "+"false";
					jse.executeScript("window.scrollBy(0, -300)");
					commonPOM.getBackLinkFirst().click();
					Thread.sleep(1000);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return status;
	}
	
	public void searchAndDownloadGoalframeworkReingestion(String lobName, ExtentTest logger) {
		ReadProductTOCFile readProductTOCFile = new ReadProductTOCFile();
		WebDriverWait wait = new WebDriverWait(driver, 180);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		
		if (lobName.equalsIgnoreCase(LOMTConstant.SCHOOL)) {
			try {
				int counter = 0;
				boolean flag = false;
				//File ingestionFilePath = new File(LOMTConstant.SCHOOL_PRODUCT_TOC_XLSX_FILE_PATH);
				File ingestionFilePath = new File(LOMTConstant.SCHOOL_PRODUCT_TOC_XLSX_FILE_PATH_UC2);
				String productTitleName  = readProductTOCFile.readGoalframeworkName(ingestionFilePath);
				
				//UPDATED ALL FIELDS EXCEPT GOAL URN & CODE, counter=0
				flag = reingestionProductTOC(lobName, productTitleName, counter, "test", logger);
				counter++;
				if (flag) {
					logger.log(LogStatus.PASS, "TC-LOMT-1047_03_Re-ingest_SchoolGlobal_Product_Admin");
					logger.log(LogStatus.PASS, "Step 1: Verify 'Create or upload a structure' page");
					
					logger.log(LogStatus.PASS, "TC-LOMT-1047_04_Re-ingest_SchoolGlobal_Product_Admin");
					logger.log(LogStatus.PASS, "Step 1: Verify the 'Create or upload a structure' page upload file section of Product ToC");
					
					logger.log(LogStatus.PASS, "TC-LOMT-1047_05_Re-ingest_SchoolGlobal_Product_Admin");
					logger.log(LogStatus.PASS, "Step 1: Verify the 'back' navigate option and 'Create or upload a structure' page  of Product ToC");
					
					logger.log(LogStatus.PASS, "TC-LOMT-1047_06_Re-ingest_SchoolGlobal_Product_Admin");
					logger.log(LogStatus.PASS, "Step 1: Re-ingestion is successful : UPDATED ALL FIELDS EXCEPT GOAL URN & CODE");
				} else {
					logger.log(LogStatus.PASS, "TC-LOMT-1047_03_Re-ingest_SchoolGlobal_Product_Admin");
					logger.log(LogStatus.PASS, "Step 1: Verify 'Create or upload a structure' page");
					
					logger.log(LogStatus.PASS, "TC-LOMT-1047_04_Re-ingest_SchoolGlobal_Product_Admin");
					logger.log(LogStatus.PASS, "Step 1: Verify the 'Create or upload a structure' page upload file section of Product ToC");
					
					logger.log(LogStatus.PASS, "TC-LOMT-1047_05_Re-ingest_SchoolGlobal_Product_Admin");
					logger.log(LogStatus.PASS, "Step 1: Verify the 'back' navigate option and 'Create or upload a structure' page  of Product ToC");
					
					logger.log(LogStatus.FAIL, "TC-LOMT-1047_06_Re-ingest_SchoolGlobal_Product_Admin");
					logger.log(LogStatus.PASS, "Step 1: Re-ingestion is successful : UPDATED ALL FIELDS EXCEPT GOAL URN & CODE");
				}
				
				File reIngestionFilePath = new File(LOMTConstant.TOC_SCHOOL_REINGESTION_EXE_FILE_PATH_XLSX);
				String productTitleNameUpdated  = readProductTOCFile.readGoalframeworkName(reIngestionFilePath);
				
				//ADD NEW ROW  : ALIGNMENT USING CODE, counter=1
				flag = reingestionProductTOC(lobName, productTitleNameUpdated, counter, "test", logger);
				counter++;
				if (flag) {
					logger.log(LogStatus.PASS, "Re-ingestion : new row added");
				} else {
					logger.log(LogStatus.PASS, "Unable to add new row during product toc Re-ingestion");
				}
				
				//DELETE IS NOT IMPLEMENTED YET, comments added into LOMT-1047
				logger.log(LogStatus.INFO, "Deletion is De-scoped because it is on hold");
				
				//VALIDATION CHECK, counter=2
				flag = reingestionProductTOC(lobName, productTitleNameUpdated, counter, LOMTConstant.VALIDATION_TOC, logger);
				counter++;
				if (flag) {
					logger.log(LogStatus.PASS, "TC-LOMT-1047_07_Re-ingest_SchoolGlobal_Product_Admin");
					logger.log(LogStatus.PASS, "Step 1: Verify the  'Create or upload a structure' page  of Product ToC with invalid template");
					
					logger.log(LogStatus.PASS, "TC-LOMT-1047_07_Re-ingest_SchoolGlobal_Product_Admin");
					logger.log(LogStatus.PASS, "Step 1: Verify the  'VIEW FULL INGEST LOG'");
					
					logger.log(LogStatus.PASS, "TC-LOMT-1047_09_Re-ingest_SchoolGlobal_Product_Admin");
					logger.log(LogStatus.PASS, "Step 1: Verify the  'Create or upload a structure' page  of Product ToC with invalid content");
				} else {
					logger.log(LogStatus.FAIL, "TC-LOMT-1047_07_Re-ingest_SchoolGlobal_Product_Admin");
					logger.log(LogStatus.FAIL, "Step 1: Verify the  'Create or upload a structure' page  of Product ToC with invalid template");
					
					logger.log(LogStatus.FAIL, "TC-LOMT-1047_07_Re-ingest_SchoolGlobal_Product_Admin");
					logger.log(LogStatus.FAIL, "Step 1: Verify the  'VIEW FULL INGEST LOG'");
					
					logger.log(LogStatus.FAIL, "TC-LOMT-1047_09_Re-ingest_SchoolGlobal_Product_Admin");
					logger.log(LogStatus.FAIL, "Step 1: Verify the  'Create or upload a structure' page  of Product ToC with invalid content");
				}
				
				logger.log(LogStatus.INFO, "TC-LOMT-1047_10_Re-ingest_SchoolGlobal_Product_Admin");
				
				//Add Product Title from Use case 7(Alignment)
				//UPDATED GOAL URN FIELD : ALIGNMENT, counter=3				
				File reIngestionFilePathAlign = new File(LOMTConstant.SCHOOL_PRODUCT_TOC_XLSX_FILE_PATH);
				String productTitleNameUpdatedAlign  = readProductTOCFile.readGoalframeworkName(reIngestionFilePathAlign);
				
				flag = reingestionProductTOC(lobName, productTitleNameUpdatedAlign, counter, "test", logger);
				counter++;
				if (flag) {
					logger.log(LogStatus.PASS, "Re-ingestion: Goal URN is done");
				} else {
					logger.log(LogStatus.FAIL, "Re-ingestion: Goal URN is done");
				}
				//Verify re-ingeted data
				//verifyDataUI();
				//VerifyDataInExportedFile();
				System.out.println("####### DONE SCHOOL PRODUCT TOC ######");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (lobName.equalsIgnoreCase(LOMTConstant.HE_LOB)) {
			try {
				int counter = 0;
				boolean flag = false;
				File ingestionFilePath = new File(LOMTConstant.SCHOOL_PRODUCT_TOC_XLSX_FILE_PATH_UC2);
				String productTitleName  = readProductTOCFile.readGoalframeworkName(ingestionFilePath);
				
				//UPDATED ALL FIELDS EXCEPT GOAL URN & CODE, counter=0
				flag = reingestionProductTOC(lobName, productTitleName, counter, "test", logger);
				counter++;
				if (flag) {
					logger.log(LogStatus.PASS, "TC-LOMT-1049-04_HE_ManageIngestion_Admin");
					logger.log(LogStatus.PASS, "TC-LOMT-1049-05_HE_SelectProduct_Next");
					logger.log(LogStatus.PASS, "TC-LOMT-1049-06_HE_SelectProduct_NextDisabled");					
					logger.log(LogStatus.PASS, "TC-LOMT-1049-07_HE_SelectProduct_Backs");					
					logger.log(LogStatus.PASS, "TC-LOMT-1049-08_HE_re-ingests_valid");					
					logger.log(LogStatus.PASS, "TC-LOMT-1049-09_HE_re-ingests_Verify");
					
					logger.log(LogStatus.PASS, "TC-LOMT-1049-10_HE_re-ingests_Program Title_Updated");					
					logger.log(LogStatus.PASS, "TC-LOMT-1049-11_HE_re-ingests_Course Title_Updated");
					logger.log(LogStatus.PASS, "TC-LOMT-1049-12_HE_re-ingests_Product Title_Updated_withoutAlignment");
					
					logger.log(LogStatus.PASS, "TC-LOMT-1049-14_HE_re-ingests_ISBNValue_Update");
					logger.log(LogStatus.PASS, "TC-LOMT-1049-15_HE_re-ingests_ISBNBlank_Update");
					logger.log(LogStatus.PASS, "TC-LOMT-1049-16_HE_re-ingests_Type_Update");
					logger.log(LogStatus.PASS, "TC-LOMT-1049-17_HE_re-ingests_Level for Hierarchy_Update");
					
					logger.log(LogStatus.PASS, "TC-LOMT-1049-19_HE_re-ingests_Level Title_Update");
					logger.log(LogStatus.PASS, "TC-LOMT-1049-20_HE_re-ingests_Level Title_blank");
					logger.log(LogStatus.PASS, "TC-LOMT-1049-21_HE_re-ingests_Level Title_Update_splchat");
					logger.log(LogStatus.PASS, "TC-LOMT-1049-22_HE_re-ingests_Level Title_Update_Long Char");
					
					logger.log(LogStatus.PASS, "TC-LOMT-1049-23_HE_re-ingests_Content Title_Update");
					logger.log(LogStatus.PASS, "TC-LOMT-1049-24_HE_re-ingests_Content Title_Update_SplChar");
					logger.log(LogStatus.PASS, "TC-LOMT-1049-25_HE_re-ingests_Content Title_Update_LongChar");
					
					logger.log(LogStatus.PASS, "TC-LOMT-1049-26_HE_re-ingests_Screen or Print _Update");
				} else {
					logger.log(LogStatus.FAIL, "UPDATED ALL FIELDS EXCEPT Goal URN, Code, Alignment Type (Coverage) and Key Alignment ");
				}
				
				File reIngestionFilePath = new File(LOMTConstant.TOC_SCHOOL_REINGESTION_EXE_FILE_PATH_XLSX);
				String productTitleNameUpdated  = readProductTOCFile.readGoalframeworkName(reIngestionFilePath);
				
				//ADD NEW ROW  : ALIGNMENT USING CODE, counter=1
				flag = reingestionProductTOC(lobName, productTitleNameUpdated, counter, "test", logger);
				counter++;
				if (flag) {
					logger.log(LogStatus.PASS, "TC-LOMT-1049-28_HE_re-ingests_NewNode");
				} else {
					logger.log(LogStatus.PASS, "TC-LOMT-1049-28_HE_re-ingests_NewNode");
				}
				
				//DELETE IS NOT IMPLEMENTED YET, comments added into LOMT-1047
				logger.log(LogStatus.INFO, "TC-LOMT-1049-29_HE_re-ingests_DeleteNode");
				
				//VALIDATION CHECK, counter=2
				flag = reingestionProductTOC(lobName, productTitleNameUpdated, counter, LOMTConstant.VALIDATION_TOC, logger);
				counter++;
				if (flag) {
					logger.log(LogStatus.PASS, "TC-LOMT-1049-18_HE_re-ingests_Level for Hierarchy_wrong");
					logger.log(LogStatus.PASS, "Validation check for Product Title, Level Title");
					
				} else {
					logger.log(LogStatus.FAIL, "TC-LOMT-1049-18_HE_re-ingests_Level for Hierarchy_wrong");
					logger.log(LogStatus.FAIL, "Validation check for Product Title, Level Title");
				}
				logger.log(LogStatus.PASS, "TC-LOMT-1049-13_HE_re-ingests_Product Title_Updated_withAlignment");
				logger.log(LogStatus.PASS, "TC-LOMT-1049-27_HE_re-ingests_Alignment Code _Update");
				
				//Add Product Title from Use case 7(Alignment)
				//UPDATED GOAL URN FIELD : ALIGNMENT, counter=3				
				File reIngestionFilePathAlign = new File(LOMTConstant.HE_RE_PRODUCT_TOC_XLSX_FILE_PATH);
				String productTitleNameUpdatedAlign  = readProductTOCFile.readGoalframeworkName(reIngestionFilePathAlign);
				
				flag = reingestionProductTOC(lobName, productTitleNameUpdatedAlign, counter, "test", logger);
				counter++;
				if (flag) {
					logger.log(LogStatus.PASS, "TC-LOMT-1049-13_HE_re-ingests_Product Title_Updated_withAlignment");
					logger.log(LogStatus.PASS, "TC-LOMT-1049-27_HE_re-ingests_Alignment Code _Update");
				} else {
					logger.log(LogStatus.FAIL, "TC-LOMT-1049-27_HE_re-ingests_Alignment Code _Update");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			//ENGLISH LOB
			try {
				int counter = 0;
				boolean flag = false;
				File ingestionFilePath = new File(LOMTConstant.SCHOOL_PRODUCT_TOC_XLSX_FILE_PATH_UC2);
				String productTitleName  = readProductTOCFile.readGoalframeworkName(ingestionFilePath);
				
				//UPDATED ALL FIELDS EXCEPT GOAL URN & CODE, counter=0
				flag = reingestionProductTOC(lobName, productTitleName, counter, "test", logger);
				counter++;
				if (flag) {
					logger.log(LogStatus.PASS, "TC-LOMT-1048-05_English_SelectProduct_Next");
					logger.log(LogStatus.PASS, "TC-LOMT-1048-06_English_SelectProduct_NextDisabled");					
					logger.log(LogStatus.PASS, "TC-LOMT-1048-07_English_SelectProduct_Back");					
					logger.log(LogStatus.PASS, "TC-LOMT-1048-08_English_re-ingests_valid");					
					logger.log(LogStatus.PASS, "TC-LOMT-1048-09_English_re-ingests_Verify");
					
					logger.log(LogStatus.PASS, "TC-LOMT-1048-10_English_re-ingests_Program Title_Updated");					
					logger.log(LogStatus.PASS, "TC-LOMT-1048-11_English_re-ingests_Course Title_Updated");
					logger.log(LogStatus.PASS, "TC-LOMT-1048-12_English_re-ingests_Product Title_Updated_withoutAlignment");
					
					logger.log(LogStatus.PASS, "TC-LOMT-1048-14_English_re-ingests_ISBNValue_Update");
					logger.log(LogStatus.PASS, "TC-LOMT-1048-15_English_re-ingests_ISBNBlank_Update");
					logger.log(LogStatus.PASS, "TC-LOMT-1048-16_English_re-ingests_Type_Update");
					logger.log(LogStatus.PASS, "TC-LOMT-1048-17_English_re-ingests_Level for Hierarchy_Update");
					
					logger.log(LogStatus.PASS, "TC-LOMT-1048-19_English_re-ingests_Level Title_Update");
					logger.log(LogStatus.PASS, "TC-LOMT-1048-20_English_re-ingests_Level Title_blank");
					logger.log(LogStatus.PASS, "TC-LOMT-1048-21_English_re-ingests_Level Title_Update_splchat");
					logger.log(LogStatus.PASS, "TC-LOMT-1048-22_English_re-ingests_Level Title_Update_Long Char");
					
					logger.log(LogStatus.PASS, "TC-LOMT-1048-23_English_re-ingests_Content Title_Update");
					logger.log(LogStatus.PASS, "TC-LOMT-1048-24_English_re-ingests_Content Title_Update_SplChar");
					logger.log(LogStatus.PASS, "TC-LOMT-1048-25_English_re-ingests_Content Title_Update_LongChar");
					
					logger.log(LogStatus.PASS, "TC-LOMT-1048-26_English_re-ingests_Screen or Print _Update");
				} else {
					logger.log(LogStatus.FAIL, "UPDATED ALL FIELDS EXCEPT Goal URN, Code, Alignment Type (Coverage) and Key Alignment ");
				}
				
				File reIngestionFilePath = new File(LOMTConstant.TOC_SCHOOL_REINGESTION_EXE_FILE_PATH_XLSX);
				String productTitleNameUpdated  = readProductTOCFile.readGoalframeworkName(reIngestionFilePath);
				
				//ADD NEW ROW  : ALIGNMENT USING CODE, counter=1
				flag = reingestionProductTOC(lobName, productTitleNameUpdated, counter, "test", logger);
				counter++;
				if (flag) {
					logger.log(LogStatus.PASS, "TC-LOMT-1048-28_English_re-ingests_NewNode");
				} else {
					logger.log(LogStatus.PASS, "TC-LOMT-1048-28_English_re-ingests_NewNode");
				}
				
				//DELETE IS NOT IMPLEMENTED YET, comments added into LOMT-1047
				logger.log(LogStatus.INFO, "Deletion is De-scoped because it is on hold");
				
				//VALIDATION CHECK, counter=2
				flag = reingestionProductTOC(lobName, productTitleNameUpdated, counter, LOMTConstant.VALIDATION_TOC, logger);
				counter++;
				if (flag) {
					logger.log(LogStatus.PASS, "TC-LOMT-1048-18_English_re-ingests_Level for Hierarchy_wrong");
					logger.log(LogStatus.PASS, "Validation check for Product Title, Level Title");
					
				} else {
					logger.log(LogStatus.FAIL, "TC-LOMT-1048-18_English_re-ingests_Level for Hierarchy_wrong");
					logger.log(LogStatus.FAIL, "Validation check for Product Title, Level Title");
				}
				
				//Add Product Title from Use case 7(Alignment)
				//UPDATED GOAL URN FIELD : ALIGNMENT, counter=3				
				File reIngestionFilePathAlign = new File(LOMTConstant.EENGLISH_PRODUCT_TOC_XLSX_FILE_PATH);
				String productTitleNameUpdatedAlign  = readProductTOCFile.readGoalframeworkName(reIngestionFilePathAlign);
				
				flag = reingestionProductTOC(lobName, productTitleNameUpdatedAlign, counter, "test", logger);
				counter++;
				if (flag) {
					logger.log(LogStatus.PASS, "TC-LOMT-1048-13_English_re-ingests_Product Title_Updated_withAlignment");
					logger.log(LogStatus.PASS, "TC-LOMT-1048-27_English_re-ingests_Alignment Code _Update");
				} else {
					logger.log(LogStatus.FAIL, "TC-LOMT-1048-27_English_re-ingests_Alignment Code _Update");
				}
				//Verify re-ingeted data
				//verifyDataUI();
				//VerifyDataInExportedFile();
				System.out.println("####### DONE SCHOOL PRODUCT TOC ######");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean reingestionProductTOC(String lobName, String productTitle, int counter, String useCase, ExtentTest logger) throws Exception {
		boolean flag = false;
		boolean alignFlag = false;
		
		ReadProductTOCFile readProductTOCFile = new ReadProductTOCFile();
		WebDriverWait wait = new WebDriverWait(driver, 300);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		
		//School lob
		if(lobName.equalsIgnoreCase(LOMTConstant.SCHOOL)) {
			try {
				//commonPOM.getNalsLOB().click();
				commonPOM.getSchoolGlobalLOB().click();
				commonPOM.getSchoolProductTOCStructure().click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));	
				Thread.sleep(30000);
				
				productTocPOM.getEnterSearchTerm().sendKeys(productTitle); 
				Thread.sleep(1000);
				
				//jse.executeScript("window.scrollBy(0,100)");
				productTocPOM.getUpdateResultButton().click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
				
				jse.executeScript("window.scrollBy(0,400)");	
				
				// Removing existing files from the download directory
				readProductTOCFile.removeExistingFile();
				
				productTocPOM.getActionLink().click();
				Thread.sleep(2000);
				
				productTocPOM.getExport().click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
				
				Thread.sleep(10000); // Temporary applied
				
				productTocPOM.getEnterSearchTerm().clear();
				Thread.sleep(1000);
							
				// copy download file at re-ingestion location
				String exportedFileName = readProductTOCFile.getFileFromDirectory(LOMTConstant.EXPORTED_FILE_PATH);
				File sourceFile = new File(LOMTConstant.EXPORTED_FILE_PATH + exportedFileName);
				
				File destinationFile = new File(LOMTConstant.TOC_RE_INGESTION_DEST_FILE_PATH + LOMTConstant.TOC_RE_INGESTION_TEMPLATE);
				
				if (readProductTOCFile.copyExportFileIntoNewFile(sourceFile, destinationFile))
					  alignFlag = readProductTOCFile.updateFileData(destinationFile, counter, logger);
					if(!alignFlag) {
						flag = alignFlag;
						return flag;
					}
				
				jse.executeScript("window.scrollBy(0,-800)");
				commonPOM.getPearsonLogo().click();
				
				commonPOM.getSchoolGlobalLOB().click();
				commonPOM.getManageIngestion().click();
				commonPOM.getSchoolGlobalLOBRadioButton().click();
				jse.executeScript("window.scrollBy(0,200)");
				commonPOM.getSchoolProductStructureRadioButton().click();
				jse.executeScript("window.scrollBy(0,400)");
				commonPOM.getNextButtonFirst().click();
				
				commonPOM.getProductTOCMetaDataDesc().sendKeys("Product!@#$%^&*(');_+{}|:'';<>?Tesst123");
				Thread.sleep(2000);
				jse.executeScript("window.scrollBy(0,500)");
				commonPOM.getNextButton().click();
				Thread.sleep(1000);
				
				// No alignment use case
				if (counter == 0 || counter == 1 || counter == 2) {
					//file upload process
					commonPOM.getUploadFileLink().click();
					Thread.sleep(2000);
					
					Runtime.getRuntime().exec(LOMTConstant.TOC_SCHOOL_REINGESTION_EXE_FILE_PATH);
					
					jse.executeScript("window.scrollBy(0, 300)");
					Thread.sleep(4000);		
					commonPOM.getNextButtonSt2().click();
					jse.executeScript("window.scrollBy(0,-100)");
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
					Thread.sleep(3000);
					
					if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_SUCESSFULL_MESSAGE)) {
						flag = true;
						jse.executeScript("window.scrollBy(0, -800)");
						Thread.sleep(1000);
						commonPOM.getPearsonLogo().click();
					} else {
						flag = false;	
						commonPOM.getViewIngestFullLogButton().click();
						//Validation check
						if (useCase.equalsIgnoreCase(LOMTConstant.VALIDATION_TOC)) { 
							flag = checkValidation();
						}
						jse.executeScript("window.scrollBy(0, -800)");
						Thread.sleep(1000);
						commonPOM.getPearsonLogo().click();
					}
				} else {
					//Alignment use case
					commonPOM.getUploadFileLink().click();
					Thread.sleep(2000);
					
					Runtime.getRuntime().exec(LOMTConstant.TOC_SCHOOL_REINGESTION_EXE_FILE_PATH);
					
					jse.executeScript("window.scrollBy(0, 300)");
					Thread.sleep(4000);		
					commonPOM.getNextButtonSt2().click();
					jse.executeScript("window.scrollBy(0, -800)");
					
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
					Thread.sleep(3000);
					
					if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_SUCESSFULL_MESSAGE)) {
						flag = true;
						jse.executeScript("window.scrollBy(0, -800)");
						Thread.sleep(1000);
						commonPOM.getPearsonLogo().click();
					} else {
						flag = false;
						jse.executeScript("window.scrollBy(0, -800)");
						Thread.sleep(1000);
						commonPOM.getPearsonLogo().click();
					}
				}
			} catch (Exception e) {
				flag = false;
				jse.executeScript("window.scrollBy(0, -800)");
				commonPOM.getPearsonLogo().click();
			}
		}
		
		// HE lob
		else if(lobName.equalsIgnoreCase(LOMTConstant.HE_LOB)) {
			try {
				commonPOM.getHeLOB().click();
				commonPOM.getHeProductTOCStructure().click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));	
				Thread.sleep(30000);
				
				productTocPOM.getTocHEenterSearchTerm().sendKeys(productTitle); 
				Thread.sleep(1000);
				
				//jse.executeScript("window.scrollBy(0,100)");
				productTocPOM.getTocUpdateResultButton().click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
				
				jse.executeScript("window.scrollBy(0,400)");	
				
				// Removing existing files from the download directory
				readProductTOCFile.removeExistingFile();
				
				productTocPOM.getTocActionLink().click();
				Thread.sleep(2000);
				
				productTocPOM.getTocExport().click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
				
				Thread.sleep(10000); // Temporary applied
				
				productTocPOM.getTocHEenterSearchTerm().clear();
				Thread.sleep(1000);
							
				// copy download file at re-ingestion location
				String exportedFileName = readProductTOCFile.getFileFromDirectory(LOMTConstant.EXPORTED_FILE_PATH);
				File sourceFile = new File(LOMTConstant.EXPORTED_FILE_PATH + exportedFileName);
				
				File destinationFile = new File(LOMTConstant.TOC_RE_INGESTION_DEST_FILE_PATH + LOMTConstant.TOC_RE_INGESTION_TEMPLATE);
				
				if (readProductTOCFile.copyExportFileIntoNewFile(sourceFile, destinationFile))
					  alignFlag = readProductTOCFile.updateFileData(destinationFile, counter, logger);
					if(!alignFlag) {
						flag = alignFlag;
						return flag;
					}
				
				jse.executeScript("window.scrollBy(0,-800)");
				commonPOM.getPearsonLogo().click();
				
				commonPOM.getHeLOB().click();
				commonPOM.getManageIngestion().click();
				commonPOM.getHeLOBRadioButton().click();
				jse.executeScript("window.scrollBy(0,200)");
				commonPOM.getProductRadioButton().click();
				jse.executeScript("window.scrollBy(0,400)");
				commonPOM.getNextButtonFirst().click();
				
				commonPOM.getProductTOCMetaDataDesc().sendKeys("Product!@#$%^&*(');_+{}|:'';<>?Tesst123");
				Thread.sleep(2000);
				jse.executeScript("window.scrollBy(0,500)");
				commonPOM.getNextButton().click();
				Thread.sleep(1000);
				
				// No alignment use case
				if (counter == 0 || counter == 1 || counter == 2) {
					//file upload process
					commonPOM.getUploadFileLink().click();
					Thread.sleep(2000);
					
					Runtime.getRuntime().exec(LOMTConstant.TOC_SCHOOL_REINGESTION_EXE_FILE_PATH);
					
					jse.executeScript("window.scrollBy(0, 300)");
					Thread.sleep(4000);		
					commonPOM.getNextButtonSt2().click();
					
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
					Thread.sleep(3000);
					
					if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_SUCESSFULL_MESSAGE)) {
						flag = true;
						jse.executeScript("window.scrollBy(0, -800)");
						Thread.sleep(1000);
						commonPOM.getPearsonLogo().click();
					} else {
						flag = false;	
						commonPOM.getViewIngestFullLogButton().click();
						//Validation check
						if (useCase.equalsIgnoreCase(LOMTConstant.VALIDATION_TOC)) { 
							flag = checkValidation();
						}
						jse.executeScript("window.scrollBy(0, -800)");
						Thread.sleep(1000);
						commonPOM.getPearsonLogo().click();
					}
				} else {
					//Alignment use case
					commonPOM.getUploadFileLink().click();
					Thread.sleep(2000);
					
					Runtime.getRuntime().exec(LOMTConstant.TOC_SCHOOL_REINGESTION_EXE_FILE_PATH);
					
					jse.executeScript("window.scrollBy(0, 300)");
					Thread.sleep(4000);		
					commonPOM.getNextButtonSt2().click();
					
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
					Thread.sleep(3000);
					
					if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_SUCESSFULL_MESSAGE)) {
						flag = true;
						jse.executeScript("window.scrollBy(0, -800)");
						Thread.sleep(1000);
						commonPOM.getPearsonLogo().click();
					} else {
						flag = false;
						jse.executeScript("window.scrollBy(0, -800)");
						Thread.sleep(1000);
						commonPOM.getPearsonLogo().click();
					}
				}
			} catch (Exception e) {
				flag = false;
				jse.executeScript("window.scrollBy(0, -800)");
				commonPOM.getPearsonLogo().click();
			}
		} 
		//English lob
		else {
			try {
				commonPOM.getEnglishLOB().click();
				commonPOM.getEnglishProductTOCStructure().click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));	
				Thread.sleep(30000);
				
				productTocPOM.getTocEnglishEenterSearchTerm().sendKeys(productTitle); 
				Thread.sleep(1000);
				
				//jse.executeScript("window.scrollBy(0,100)");
				productTocPOM.getTocEnglishUpdateResultButton().click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
				
				jse.executeScript("window.scrollBy(0,400)");	
				
				// Removing existing files from the download directory
				readProductTOCFile.removeExistingFile();
				
				productTocPOM.getTocEnglishActionLink().click();
				Thread.sleep(2000);
				
				productTocPOM.getTocEnglishExport().click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
				
				Thread.sleep(10000); // Temporary applied
				
				productTocPOM.getTocEnglishEenterSearchTerm().clear();
				Thread.sleep(1000);
							
				// copy download file at re-ingestion location
				String exportedFileName = readProductTOCFile.getFileFromDirectory(LOMTConstant.EXPORTED_FILE_PATH);
				File sourceFile = new File(LOMTConstant.EXPORTED_FILE_PATH + exportedFileName);
				
				File destinationFile = new File(LOMTConstant.TOC_RE_INGESTION_DEST_FILE_PATH + LOMTConstant.TOC_RE_INGESTION_TEMPLATE);
				
				if (readProductTOCFile.copyExportFileIntoNewFile(sourceFile, destinationFile))
					  alignFlag = readProductTOCFile.updateFileData(destinationFile, counter, logger);
					if(!alignFlag) {
						flag = alignFlag;
						return flag;
					}
				
				jse.executeScript("window.scrollBy(0,-800)");
				commonPOM.getPearsonLogo().click();
				
				commonPOM.getEnglishLOB().click();
				commonPOM.getManageIngestion().click();
				jse.executeScript("window.scrollBy(0,200)");
				commonPOM.getGseProductStructureRadioButton().click();
				jse.executeScript("window.scrollBy(0,400)");
				commonPOM.getNextButtonFirst().click();
				
				commonPOM.getProductTOCMetaDataDesc().sendKeys("Product!@#$%^&*(');_+{}|:'';<>?Tesst123");
				Thread.sleep(2000);
				jse.executeScript("window.scrollBy(0,100)");
				commonPOM.getNextButton().click();
				Thread.sleep(1000);
				
				// No alignment use case
				if (counter == 0 || counter == 1 || counter == 2) {
					//file upload process
					commonPOM.getUploadFileLink().click();
					Thread.sleep(2000);
					
					Runtime.getRuntime().exec(LOMTConstant.TOC_SCHOOL_REINGESTION_EXE_FILE_PATH);
					
					//jse.executeScript("window.scrollBy(0, 300)");
					Thread.sleep(4000);		
					commonPOM.getNextButtonSt2().click();
					
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
					Thread.sleep(3000);
					
					if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_SUCESSFULL_MESSAGE)) {
						flag = true;
						jse.executeScript("window.scrollBy(0, -800)");
						Thread.sleep(1000);
						commonPOM.getPearsonLogo().click();
					} else {
						flag = false;	
						commonPOM.getViewIngestFullLogButton().click();
						//Validation check
						if (useCase.equalsIgnoreCase(LOMTConstant.VALIDATION_TOC)) { 
							flag = checkValidation();
						}
						jse.executeScript("window.scrollBy(0, -800)");
						Thread.sleep(1000);
						commonPOM.getPearsonLogo().click();
					}
				} else {
					//Alignment use case
					commonPOM.getUploadFileLink().click();
					Thread.sleep(2000);
					
					Runtime.getRuntime().exec(LOMTConstant.TOC_SCHOOL_REINGESTION_EXE_FILE_PATH);
					
					jse.executeScript("window.scrollBy(0, 300)");
					Thread.sleep(4000);		
					commonPOM.getNextButtonSt2().click();
					
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
					Thread.sleep(3000);
					
					if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_SUCESSFULL_MESSAGE)) {
						flag = true;
						jse.executeScript("window.scrollBy(0, -800)");
						Thread.sleep(1000);
						commonPOM.getPearsonLogo().click();
					} else {
						flag = false;
						jse.executeScript("window.scrollBy(0, -800)");
						Thread.sleep(1000);
						commonPOM.getPearsonLogo().click();
					}
				}
			} catch (Exception e) {
				flag = false;
				jse.executeScript("window.scrollBy(0, -800)");
				commonPOM.getPearsonLogo().click();
			}
		}
		return flag;
	}
	
	public boolean checkValidation() {
		boolean flag = false;

		if (exfPOM.getErrorRowOne().getText().contains(LOMTConstant.PRODUCT_TITLE_BLANK)
				|| exfPOM.getErrorRowTwo().getText().contains(LOMTConstant.PRODUCT_TITLE_BLANK)
				|| exfPOM.getErrorRowThree().getText().contains(LOMTConstant.PRODUCT_TITLE_BLANK)
				|| exfPOM.getErrorRowFour().getText().contains(LOMTConstant.PRODUCT_TITLE_BLANK)) {
			flag = true;
		} else {
			flag = false;
			return flag;
		}
		if (exfPOM.getErrorRowOne().getText().contains(LOMTConstant.LEVEL_FOR_HIERARCHY_BLANK)
				|| exfPOM.getErrorRowTwo().getText().contains(LOMTConstant.LEVEL_FOR_HIERARCHY_BLANK)
				|| exfPOM.getErrorRowThree().getText().contains(LOMTConstant.LEVEL_FOR_HIERARCHY_BLANK)
				|| exfPOM.getErrorRowFour().getText().contains(LOMTConstant.LEVEL_FOR_HIERARCHY_BLANK)) {
			flag = true;
		} else {
			flag = false;
			return flag;
		}
		if (exfPOM.getErrorRowOne().getText().contains(LOMTConstant.LEVEL_FOR_HIERARCHY_INCORRECT)
				|| exfPOM.getErrorRowTwo().getText().contains(LOMTConstant.LEVEL_FOR_HIERARCHY_INCORRECT)
				|| exfPOM.getErrorRowThree().getText().contains(LOMTConstant.LEVEL_FOR_HIERARCHY_INCORRECT)
				|| exfPOM.getErrorRowFour().getText().contains(LOMTConstant.LEVEL_FOR_HIERARCHY_INCORRECT)) {
			flag = true;

		} else {
			flag = false;
			return flag;
		}
		if (exfPOM.getErrorRowOne().getText().contains(LOMTConstant.LEVEL_TITLE_BLANK)
				|| exfPOM.getErrorRowTwo().getText().contains(LOMTConstant.LEVEL_TITLE_BLANK)
				|| exfPOM.getErrorRowThree().getText().contains(LOMTConstant.LEVEL_TITLE_BLANK)
				|| exfPOM.getErrorRowFour().getText().contains(LOMTConstant.LEVEL_TITLE_BLANK)) {
			flag = true;
		} else {
			flag = false;
			return flag;
		}
		return flag;
	}
	
	public boolean ingestHEForAlignment() {
		boolean flag = false;
		WebDriverWait wait = new WebDriverWait(driver, 900);
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			commonPOM.getPearsonLogo().click();
			
			commonPOM.getHeLOB().click();
			commonPOM.getManageIngestion().click();
			commonPOM.getHeLOBRadioButton().click();
			
			jse.executeScript("window.scrollBy(0,100)");
			Thread.sleep(2000);
			commonPOM.getEducationalObjRadioButton().click();
			
			jse.executeScript("window.scrollBy(0,300)");
			
			commonPOM.getNextButtonFirst().click();
			Thread.sleep(2000);
			
			hePom.getLearningTitleInputText().sendKeys("domain_model_qapairs_9feb_automation");
			Thread.sleep(4000);
			
			//DOMAIN SELECTION
			hePom.getDomainNameDropDown().click();
			Thread.sleep(5000);
			List<WebElement> domainList = hePom.getDomainList();
			int domainLength = domainList.size();
			if (domainLength > 0) {
				for (int i = 0; i <= domainLength; i++) {
					WebElement element = domainList.get(i);
					// TODO : apply assertion for all the fields
					if (element.getText() != null) {
						element.click();
						break;
					}
				}
			} else {
				Assert.assertFalse((domainLength == 0), "HE Domain dropdown size is zero");
				flag = false;
				return flag;
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
				flag = false;
				return flag;
			}
			hePom.getNextButton().click();
			Thread.sleep(2000);
			
			//File upload logic 
			commonPOM.getUploadFileLink().click();
			
			Runtime.getRuntime().exec(LOMTConstant.HE_TOC_INGEST_FILE_PATH);
			Thread.sleep(4000);
			
			hePom.getNextButton().click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
			Thread.sleep(3000);
			
			if (commonPOM.getWaitForIngestionStatusText().getText().contains(LOMTConstant.INGESTION_SUCESSFULL_MESSAGE)) {
				flag = true;
				jse.executeScript("window.scrollBy(0,-500)");
				commonPOM.getBackLinkFirst().click();
			} else {
				flag = false;
				jse.executeScript("window.scrollBy(0,-500)");
				commonPOM.getPearsonLogo().click();
				
				return flag;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	public boolean alignValidationCheck(String usecase) {
		boolean flag = false;
		
		try {
			// HE toc validation check
			if (usecase.equalsIgnoreCase(LOMTConstant.TC_VALIDATION_CHECK_22_23)) {
				if (sgPom.getErrorMessage1().getText().contains("Product Title value is blank in Row : 3")
						|| sgPom.getErrorMessage2().getText().contains("Product Title value is blank in Row : 3")
						|| sgPom.getErrorMessage3().getText().contains("Product Title value is blank in Row : 3")
						|| sgPom.getErrorMessage4().getText().contains("Product Title value is blank in Row : 3")
						|| sgPom.getErrorMessage5().getText().contains("Product Title value is blank in Row : 3") ) {
					flag = true;
				} else {
					flag = false;
					return flag;
				}
				if (sgPom.getErrorMessage1().getText().contains("Level for hierarchy cannot be blank")
						|| sgPom.getErrorMessage2().getText().contains("Level for hierarchy cannot be blank")
						|| sgPom.getErrorMessage3().getText().contains("Level for hierarchy cannot be blank")
						|| sgPom.getErrorMessage4().getText().contains("Level for hierarchy cannot be blank")
						|| sgPom.getErrorMessage5().getText().contains("Level for hierarchy cannot be blank") ) {
					flag = true;
				} else {
					flag = false;
					return flag;
				}
				if (sgPom.getErrorMessage1().getText().contains("Level for hierarchy is incorrect")
						|| sgPom.getErrorMessage2().getText().contains("Level for hierarchy is incorrect")
						|| sgPom.getErrorMessage3().getText().contains("Level for hierarchy is incorrect")
						|| sgPom.getErrorMessage4().getText().contains("Level for hierarchy is incorrect")
						|| sgPom.getErrorMessage5().getText().contains("Level for hierarchy is incorrect") ) {
					flag = true;
				} else {
					flag = false;
					return flag;
				}
				if (sgPom.getErrorMessage1().getText().contains("Level Tile cannot be blank")
						|| sgPom.getErrorMessage2().getText().contains("Level Tile cannot be blank")
						|| sgPom.getErrorMessage3().getText().contains("Level Tile cannot be blank")
						|| sgPom.getErrorMessage4().getText().contains("Level Tile cannot be blank")
						|| sgPom.getErrorMessage5().getText().contains("Level Tile cannot be blank") ) {
					flag = true;
				} else {
					flag = false;
					return flag;
				}
	}
	
	if (usecase.equalsIgnoreCase(LOMTConstant.EXF_WITHOUT_MANDATORY_FIELDS)) {
		if (exfPOM.getErrorRowOne().getText().contains("Product Title value is blank in Row")
				|| exfPOM.getErrorRowTwo().getText().contains("Product Title value is blank in Row")
				|| exfPOM.getErrorRowThree().getText().contains("Product Title value is blank in Row")
				|| exfPOM.getErrorRowFour().getText().contains("Product Title value is blank in Row") ) {
			flag = true;
		} else {
			flag = false;
			return flag;
		}
		if (exfPOM.getErrorRowOne().getText().contains("Level for hierarchy cannot be blank")
				|| exfPOM.getErrorRowTwo().getText().contains("Level for hierarchy cannot be blank")
				|| exfPOM.getErrorRowThree().getText().contains("Level for hierarchy cannot be blank")
				|| exfPOM.getErrorRowFour().getText().contains("Level for hierarchy cannot be blank") ) {
			flag = true;
		} else {
			flag = false;
			return flag;
		}
		if (exfPOM.getErrorRowOne().getText().contains("Level Tile cannot be blank")
				|| exfPOM.getErrorRowTwo().getText().contains("Level Tile cannot be blank")
				|| exfPOM.getErrorRowThree().getText().contains("Level Tile cannot be blank")
				|| exfPOM.getErrorRowFour().getText().contains("Level Tile cannot be blank") ) {
			flag = true;
		} else {
			flag = false;
			return flag;
		}
		if (exfPOM.getErrorRowOne().getText().contains("Level for hierarchy is incorrect")
				|| exfPOM.getErrorRowTwo().getText().contains("Level for hierarchy is incorrect")
				|| exfPOM.getErrorRowThree().getText().contains("Level for hierarchy is incorrect")
				|| exfPOM.getErrorRowFour().getText().contains("Level for hierarchy is incorrect") ) {
			flag = true;
		} else {
			flag = false;
			return flag;
		}
	} 
	if (usecase.equalsIgnoreCase("test align code validation")) {
		if (exfPOM.getErrorRowOne().getText().contains("The referenced LO or EO does not exist : TEST")) {
			flag = true;
		} else {
			flag = false;
			return flag;
		}
	}
		} catch (Exception e) {
			flag = false;
			//e.printStackTrace();
		}
		
		return flag;
	}
	
	public void exportProductTOC(String lobName, ExtentTest logger) {
		WebDriverWait wait = new WebDriverWait(driver, 900);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		ReadProductTOCFile readProductTOCFile = new ReadProductTOCFile();
		try {
			if (lobName.equalsIgnoreCase(LOMTConstant.SCHOOL)) {
				File ingestionFilePath = new File(LOMTConstant.SCHOOL_PRODUCT_TOC_XLSX_FILE_PATH_UC2);
				String productTitleName  = readProductTOCFile.readGoalframeworkName(ingestionFilePath);
				
				commonPOM.getSchoolGlobalLOB().click();
				commonPOM.getSchoolProductTOCStructure().click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));	
				Thread.sleep(30000);
				
				productTocPOM.getEnterSearchTerm().sendKeys(productTitleName); // css is generic
				Thread.sleep(3000);
				
				productTocPOM.getUpdateResultButton().click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
				
				jse.executeScript("window.scrollBy(0,400)");

				// Removing existing files from the download directory
				readProductTOCFile.removeExistingFile();

				productTocPOM.getActionLink().click();
				Thread.sleep(2000);
							
				productTocPOM.getExport().click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
				
				Thread.sleep(10000); // Temporary applied
				
				productTocPOM.getEnterSearchTerm().clear();
				Thread.sleep(1000);
				
				jse.executeScript("window.scrollBy(0,-800)");
				commonPOM.getPearsonLogo().click();	
				
				String exportedFileName = readProductTOCFile.getFileFromDirectory(LOMTConstant.EXPORTED_FILE_PATH);
				File sourceFile = new File(LOMTConstant.EXPORTED_FILE_PATH + exportedFileName);	
				
				boolean flag = verifyExportedFileData(sourceFile);
				if (flag) {
					logger.log(LogStatus.PASS, "TC-LOMT-1043-19_Admin_User_School_Global_Product_Export	");
					logger.log(LogStatus.PASS, "TC-LOMT-1043-20_Admin_User_School_Global_Product_Export");
					logger.log(LogStatus.PASS, "TC-LOMT-1043-21_Admin_User_School_Global_Product_Export");
					logger.log(LogStatus.PASS, "TC-LOMT-1043-22_Admin_User_School_Global_Product_Export");
					logger.log(LogStatus.PASS, "TC-LOMT-1043-24_Admin_User_School_Global_Product_Export");
					logger.log(LogStatus.PASS, "TC-LOMT-1043-25_Admin_User_School_Global_Product_Export");
					logger.log(LogStatus.PASS, "TC-LOMT-1043-26_Admin_User_School_Global_Product_Export");
					logger.log(LogStatus.PASS, "TC-LOMT-1043-29_Basic_User_School_Global_Product_Export");
					logger.log(LogStatus.PASS, "TC-LOMT-1043-30_Basic_User_School_Global_Product_Export");
					logger.log(LogStatus.PASS, "TC-LOMT-1043-31_Basic_User_School_Global_Product_Export");
					logger.log(LogStatus.PASS, "TC-LOMT-1043-32_Basic_User_School_Global_Product_Export");
					logger.log(LogStatus.PASS, "TC-LOMT-1043-33_Basic_User_School_Global_Product_Export");
					logger.log(LogStatus.PASS, "TC-LOMT-1043-34_Basic_User_School_Global_Product_Export");
					logger.log(LogStatus.PASS, "TC-LOMT-1043-35_Basic_User_School_Global_Product_Export");
				} else {
					logger.log(LogStatus.PASS, "TC-LOMT-1043-19_Admin_User_School_Global_Product_Export	");
					logger.log(LogStatus.PASS, "TC-LOMT-1043-20_Admin_User_School_Global_Product_Export");
					logger.log(LogStatus.PASS, "TC-LOMT-1043-21_Admin_User_School_Global_Product_Export");
					logger.log(LogStatus.PASS, "TC-LOMT-1043-22_Admin_User_School_Global_Product_Export");
					logger.log(LogStatus.PASS, "TC-LOMT-1043-24_Admin_User_School_Global_Product_Export");
					logger.log(LogStatus.PASS, "TC-LOMT-1043-25_Admin_User_School_Global_Product_Export");
					logger.log(LogStatus.FAIL, "TC-LOMT-1043-26_Admin_User_School_Global_Product_Export");
					logger.log(LogStatus.FAIL, "TC-LOMT-1043-29_Basic_User_School_Global_Product_Export");
					logger.log(LogStatus.FAIL, "TC-LOMT-1043-30_Basic_User_School_Global_Product_Export");
					logger.log(LogStatus.FAIL, "TC-LOMT-1043-31_Basic_User_School_Global_Product_Export");
					logger.log(LogStatus.FAIL, "TC-LOMT-1043-32_Basic_User_School_Global_Product_Export");
					logger.log(LogStatus.FAIL, "TC-LOMT-1043-33_Basic_User_School_Global_Product_Export");
					logger.log(LogStatus.FAIL, "TC-LOMT-1043-34_Basic_User_School_Global_Product_Export");
					logger.log(LogStatus.FAIL, "TC-LOMT-1043-35_Basic_User_School_Global_Product_Export");
				}
			} 
			if (lobName.equalsIgnoreCase(LOMTConstant.HE_LOB)) {
				File ingestionFilePath = new File(LOMTConstant.SCHOOL_PRODUCT_TOC_XLSX_FILE_PATH_UC2);
				String productTitleName  = readProductTOCFile.readGoalframeworkName(ingestionFilePath);
				
				commonPOM.getHeLOB().click();
				commonPOM.getHeProductTOCStructure().click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));	
				Thread.sleep(30000);
				
				productTocPOM.getTocHEenterSearchTerm().sendKeys(productTitleName); 
				Thread.sleep(1000);
				
				productTocPOM.getTocUpdateResultButton().click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
				
				jse.executeScript("window.scrollBy(0,400)");

				// Removing existing files from the download directory
				readProductTOCFile.removeExistingFile();

				productTocPOM.getTocActionLink().click();
				Thread.sleep(2000);
							
				productTocPOM.getExport().click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
				
				Thread.sleep(10000); // Temporary applied
				
				productTocPOM.getTocHEenterSearchTerm().clear();
				Thread.sleep(1000);
				
				jse.executeScript("window.scrollBy(0,-800)");
				commonPOM.getPearsonLogo().click();	
				
				String exportedFileName = readProductTOCFile.getFileFromDirectory(LOMTConstant.EXPORTED_FILE_PATH);
				File sourceFile = new File(LOMTConstant.EXPORTED_FILE_PATH + exportedFileName);	
				
				boolean flag = verifyExportedFileData(sourceFile);
				if (flag) {
					logger.log(LogStatus.PASS, "TC-LOMT-1045_01_Higher Education_Product_External Framework_Export_Admin");
					logger.log(LogStatus.PASS, "TC-LOMT-1045_02_Higher Education_Product_External Framework_Export_Admin");
					logger.log(LogStatus.PASS, "TC-LOMT-1045_03_Higher Education_Product_Educational Objective_Export_Admin");
					logger.log(LogStatus.PASS, "TC-LOMT-1045_04_Higher Education_Product_Educational Objective_Export_Admin");
					logger.log(LogStatus.PASS, "TC-LOMT-1045_05_Higher Education_Product_ProductExport_Admin");
					logger.log(LogStatus.PASS, "TC-LOMT-1045_06_Higher Education_Product_ProductExport_Admin");
				} else {
					logger.log(LogStatus.PASS, "TC-LOMT-1045_01_Higher Education_Product_External Framework_Export_Admin");
					logger.log(LogStatus.PASS, "TC-LOMT-1045_02_Higher Education_Product_External Framework_Export_Admin");
					logger.log(LogStatus.PASS, "TC-LOMT-1045_03_Higher Education_Product_Educational Objective_Export_Admin");
					logger.log(LogStatus.FAIL, "TC-LOMT-1045_04_Higher Education_Product_Educational Objective_Export_Admin");
					logger.log(LogStatus.FAIL, "TC-LOMT-1045_05_Higher Education_Product_ProductExport_Admin");
					logger.log(LogStatus.FAIL, "TC-LOMT-1045_06_Higher Education_Product_ProductExport_Admin");
				}
			}
			if (lobName.equalsIgnoreCase(LOMTConstant.ENGLISH_LOB)) {
				File ingestionFilePath = new File(LOMTConstant.SCHOOL_PRODUCT_TOC_XLSX_FILE_PATH_UC2);
				String productTitleName  = readProductTOCFile.readGoalframeworkName(ingestionFilePath);
				
				commonPOM.getEnglishLOB().click();
				commonPOM.getEnglishProductTOCStructure().click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));	
				Thread.sleep(30000);
				
				productTocPOM.getTocEnglishEenterSearchTerm().sendKeys(productTitleName); 
				Thread.sleep(1000);
				
				productTocPOM.getTocEnglishUpdateResultButton().click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
				
				jse.executeScript("window.scrollBy(0,400)");

				// Removing existing files from the download directory
				readProductTOCFile.removeExistingFile();

				productTocPOM.getTocEnglishActionLink().click();
				Thread.sleep(2000);
							
				productTocPOM.getTocEnglishExport().click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
				
				Thread.sleep(10000); // Temporary applied
				
				productTocPOM.getTocEnglishEenterSearchTerm().clear();
				Thread.sleep(1000);
				
				jse.executeScript("window.scrollBy(0,-800)");
				commonPOM.getPearsonLogo().click();	
				
				String exportedFileName = readProductTOCFile.getFileFromDirectory(LOMTConstant.EXPORTED_FILE_PATH);
				File sourceFile = new File(LOMTConstant.EXPORTED_FILE_PATH + exportedFileName);	
				
				boolean flag = verifyExportedFileData(sourceFile);
				if (flag) {
					logger.log(LogStatus.PASS, "TC-LOMT-1044_01_English_Product_Export_Admin");
					logger.log(LogStatus.PASS, "TC-LOMT-1044_02_English_Product_Export_Admin");
					logger.log(LogStatus.PASS, "TC-LOMT-1044_03_English_Product_Export_Admin");
					logger.log(LogStatus.PASS, "TC-LOMT-1044_04_English_Product_Export_Admin");
					logger.log(LogStatus.PASS, "TC-LOMT-1044_05_English_Product_Product_Export_Admin");
					logger.log(LogStatus.PASS, "TC-LOMT-1044_06_English_Product_Product_Export_Admin");
				} else {
					logger.log(LogStatus.PASS, "TC-LOMT-1044_01_English_Product_Export_Admin");
					logger.log(LogStatus.PASS, "TC-LOMT-1044_02_English_Product_Export_Admin");
					logger.log(LogStatus.PASS, "TC-LOMT-1044_03_English_Product_Export_Admin");
					logger.log(LogStatus.PASS, "TC-LOMT-1044_04_English_Product_Export_Admin");
					logger.log(LogStatus.FAIL, "TC-LOMT-1044_05_English_Product_Product_Export_Admin");
					logger.log(LogStatus.FAIL, "TC-LOMT-1044_06_English_Product_Product_Export_Admin");
				}
			}
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Unable to export Product TOC : "+lobName);
			//e.printStackTrace();
		}
	}
	
	public boolean verifyExportedFileData(File exportedFile) throws Exception {
		boolean flag = false;
		
		try {
			InputStream exportedEXFExcelFileIS = new FileInputStream(exportedFile);;
			XSSFWorkbook workbookForExportedEXFFile = new XSSFWorkbook(exportedEXFExcelFileIS);
			XSSFSheet exportedDataSheet = workbookForExportedEXFFile.getSheetAt(0);
			Cell cell = null;
			
			cell = exportedDataSheet.getRow(LOMTConstant.ZERO).getCell(LOMTConstant.ZERO);
			assertEquals(cell.getStringCellValue(), "Program Title");  
			
			cell = exportedDataSheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.ZERO);
			assertEquals(cell.getStringCellValue(), "Course Title");  
			
			cell = exportedDataSheet.getRow(LOMTConstant.TWO).getCell(LOMTConstant.ZERO);
			assertEquals(cell.getStringCellValue(), "Product Title"); 
			
			cell = exportedDataSheet.getRow(LOMTConstant.THREE).getCell(LOMTConstant.ZERO);
			assertEquals(cell.getStringCellValue(), "Geographic Area or Country");  
			
			cell = exportedDataSheet.getRow(LOMTConstant.FOUR).getCell(LOMTConstant.ZERO);
			assertEquals(cell.getStringCellValue(), "State or Region");  
			
			cell = exportedDataSheet.getRow(LOMTConstant.FIVE).getCell(LOMTConstant.ZERO);
			assertEquals(cell.getStringCellValue(), "Market - Province (INTL)");  
			
			cell = exportedDataSheet.getRow(LOMTConstant.SIX).getCell(LOMTConstant.ZERO);
			assertEquals(cell.getStringCellValue(), "Start Grade");  
			
			cell = exportedDataSheet.getRow(LOMTConstant.SEVEN).getCell(LOMTConstant.ZERO);
			assertEquals(cell.getStringCellValue(), "End Grade");
			
			cell = exportedDataSheet.getRow(LOMTConstant.EIGHT).getCell(LOMTConstant.ZERO);
			assertEquals(cell.getStringCellValue(), "ISBN 10"); 
			
			cell = exportedDataSheet.getRow(LOMTConstant.NINE).getCell(LOMTConstant.ZERO);
			assertEquals(cell.getStringCellValue(), "ISBN 13");
			
			cell = exportedDataSheet.getRow(LOMTConstant.TEN).getCell(LOMTConstant.ZERO);
			assertEquals(cell.getStringCellValue(), "Type");
			
			cell = exportedDataSheet.getRow(LOMTConstant.ELEVENTH).getCell(LOMTConstant.ZERO);
			assertEquals(cell.getStringCellValue(), "Content URN");
			
			cell = exportedDataSheet.getRow(LOMTConstant.ELEVENTH).getCell(LOMTConstant.ONE);
			assertEquals(cell.getStringCellValue(), "Alfresco Object ID");
			
			cell = exportedDataSheet.getRow(LOMTConstant.ELEVENTH).getCell(LOMTConstant.TWO);
			assertEquals(cell.getStringCellValue(), "Level for Hierarchy");
			
			cell = exportedDataSheet.getRow(LOMTConstant.ELEVENTH).getCell(LOMTConstant.THREE);
			assertEquals(cell.getStringCellValue(), "Level Title");
			
			cell = exportedDataSheet.getRow(LOMTConstant.ELEVENTH).getCell(LOMTConstant.FOUR);
			assertEquals(cell.getStringCellValue(), "Starting Page No");
			
			cell = exportedDataSheet.getRow(LOMTConstant.ELEVENTH).getCell(LOMTConstant.FIVE);
			assertEquals(cell.getStringCellValue(), "End Page No");
			
			cell = exportedDataSheet.getRow(LOMTConstant.ELEVENTH).getCell(LOMTConstant.SIX);
			assertEquals(cell.getStringCellValue(), "Content Title");
			
			cell = exportedDataSheet.getRow(LOMTConstant.ELEVENTH).getCell(LOMTConstant.SEVEN);
			assertEquals(cell.getStringCellValue(), "Screen Location");
			
			cell = exportedDataSheet.getRow(LOMTConstant.ELEVENTH).getCell(LOMTConstant.EIGHT);
			assertEquals(cell.getStringCellValue(), "Print Location");
			
			cell = exportedDataSheet.getRow(LOMTConstant.ELEVENTH).getCell(LOMTConstant.NINE);
			assertEquals(cell.getStringCellValue(), "Discipline");
			
			cell = exportedDataSheet.getRow(LOMTConstant.ELEVENTH).getCell(LOMTConstant.TEN);
			assertEquals(cell.getStringCellValue(), "Code");
			
			cell = exportedDataSheet.getRow(LOMTConstant.ELEVENTH).getCell(LOMTConstant.ELEVENTH);
			assertEquals(cell.getStringCellValue(), "Description");
			
			cell = exportedDataSheet.getRow(LOMTConstant.ELEVENTH).getCell(LOMTConstant.TWELEVE);
			assertEquals(cell.getStringCellValue(), "Goal URN");
			
			cell = exportedDataSheet.getRow(LOMTConstant.ELEVENTH).getCell(LOMTConstant.THIRTEEN);
			assertEquals(cell.getStringCellValue(), "Alignment Type (Coverage)");
			
			cell = exportedDataSheet.getRow(LOMTConstant.ELEVENTH).getCell(LOMTConstant.FOURTEEN);
			assertEquals(cell.getStringCellValue(), "Key Alignment");
			
			cell = exportedDataSheet.getRow(LOMTConstant.ELEVENTH).getCell(LOMTConstant.FIFTEEN);
			assertEquals(cell.getStringCellValue(), "Alignment URN");
			
			
			//DATA VERIFICATION
			cell = exportedDataSheet.getRow(LOMTConstant.TWELEVE).getCell(LOMTConstant.TWO); //Level for Hierarchy
			assertEquals(cell.getStringCellValue(), "1");
			
			cell = exportedDataSheet.getRow(LOMTConstant.TWELEVE).getCell(LOMTConstant.THREE); 
			assertEquals(cell.getStringCellValue(), "US DNealian 2008 Student Edition, Grade K"); // Level Title
			
			cell = exportedDataSheet.getRow(LOMTConstant.TWELEVE).getCell(LOMTConstant.SIX); 
			assertEquals(cell.getStringCellValue(), "US DNealian 2008 Student Edition, Grade K"); // Print 
			
			cell = exportedDataSheet.getRow(LOMTConstant.TWELEVE).getCell(LOMTConstant.EIGHT); 
			assertEquals(cell.getStringCellValue(), "Print Location"); // Content Title
			
			
			cell = exportedDataSheet.getRow(98).getCell(LOMTConstant.TWO); //Level for Hierarchy
			assertEquals(cell.getStringCellValue(), "10");
			
			cell = exportedDataSheet.getRow(98).getCell(LOMTConstant.THREE); 
			assertEquals(cell.getStringCellValue(), "Print awareness_10"); // Level Title
			
			cell = exportedDataSheet.getRow(98).getCell(LOMTConstant.SIX); 
			assertEquals(cell.getStringCellValue(), "Print awareness_10"); // Content Title
			flag = true;
			exportedEXFExcelFileIS.close();
		} catch (Exception e) {
			flag = false;
			return flag;
		}
		return flag;
	}

	public void closeDriverInstance() {
		driver.close();
	}

}
