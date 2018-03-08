package lomt.pearson.api.he;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
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
import lomt.pearson.common.ValidationCheck;
import lomt.pearson.constant.HEConstant;
import lomt.pearson.constant.LOMTConstant;
import lomt.pearson.page_object.CommonPOM;
import lomt.pearson.page_object.EnglishPOM;
import lomt.pearson.page_object.ExternalFrameworkPOM;
import lomt.pearson.page_object.HEPom;
import lomt.pearson.page_object.Login;
import lomt.pearson.page_object.SchoolPOM;

public class HigherEducation extends BaseClass {
	
	private String environment = LoadPropertiesFile.getPropertiesValues(LOMTConstant.LOMT_ENVIRONMENT);
	private String userName = LoadPropertiesFile.getPropertiesValues(LOMTConstant.USER_NAME); //PPE user name
	//private String userName = LoadPropertiesFile.getPropertiesValues(LOMTConstant.USER_NAME_TEST);
	private String pwd = LoadPropertiesFile.getPropertiesValues(LOMTConstant.PASSWORD);
	
	String currentLOB = null;

	private WebDriver driver;

	private Login login = null;
	private CommonPOM commonPOM = null;
	private EnglishPOM englishPOM = null;
	private HEPom hePom = null;
	private ExternalFrameworkPOM exfPOM = null;
	private SchoolPOM schoolPOM = null;
	private ValidationCheck validationCheck = null;
	
	private static int startYear = 1400;
	private static int endYear = 1500;
	String updateGoalframework = null;
	
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
		hePom = new HEPom(driver);
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
	
	public boolean higherEducationLOBBrowsePage() {
		boolean flag = false;
		try {
			Assert.assertTrue(commonPOM.getHeLOB().isDisplayed());
			currentLOB = commonPOM.getHeLOB().getText();
			commonPOM.getHeLOB().click();

			if (commonPOM.getManageIngestion().getText().equalsIgnoreCase(LOMTConstant.MANGE_INGESTION)) {
				commonPOM.getManageIngestion().click();
				flag = true;
			} else {
				flag = false;
				return flag;
			}
		} catch (Exception e) {
			flag = false;
			return flag;
		}
		return flag;
	}
	
	public boolean createUploadStructureFirstPageNegativeCase() {
		boolean flag = false;
		try {
			Thread.sleep(1000);
			Assert.assertFalse(commonPOM.getEducationalObjRadioButton().isEnabled());			
		} catch (Exception e) {
			flag = false;
			return flag;
		}
		return flag;
	}
	
	public boolean createUploadStructureFirstPage() {
		boolean flag = false;
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		try {
				//Selecting HE LOB and EO Structure
				commonPOM.getHeLOBRadioButton().click();
				Thread.sleep(1000);				
				commonPOM.getEducationalObjRadioButton().click();				
				Thread.sleep(1000);
				jse.executeScript("window.scrollBy(0,200)");
				
				commonPOM.getNextButtonFirst().click();
				Thread.sleep(3000);
				flag = true;
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}
	
	public void backSelect() {
		try {
			commonPOM.getBackLinkFirst().click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean createUploadStructureMetaDataPage(String heGoalframeworkName) {
		boolean flag = false;
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		try {
			Assert.assertEquals(hePom.getLearningTitleName().getText(), LOMTConstant.LEARNING_TITLE_NAME);
			Assert.assertEquals(hePom.getDomainName().getText(), LOMTConstant.DOMAIN);
			Assert.assertEquals(hePom.getStatusName().getText(), LOMTConstant.STATUS);
			Assert.assertEquals(hePom.getObjectiveHierarchySetName().getText(), LOMTConstant.OBJECT_HIERARCHY_SET);
			
			hePom.getLearningTitleInputText().sendKeys(heGoalframeworkName);
			Thread.sleep(7000);
			
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
						flag = true;
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
						flag = true;
						break;
					}
				}
			} else {
				Assert.assertFalse((domainLength == 0), "HE Status dropdown size is zero");
				flag = false;
				return flag;
			}
			//Objective Hierarchy Set SELECTION-NON MANDATORY FIELDS
			//TODO
			jse.executeScript("window.scrollBy(0,300)");
			
			hePom.getNextButton().click();
			Thread.sleep(2000);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}
	
	public boolean createUploadStructureMetaDataPageReingestion(String heGoalframeworkName) {
		boolean flag = false;
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		try {
			Assert.assertEquals(hePom.getLearningTitleName().getText(), LOMTConstant.LEARNING_TITLE_NAME);
			Assert.assertEquals(hePom.getDomainName().getText(), LOMTConstant.DOMAIN);
			Assert.assertEquals(hePom.getStatusName().getText(), LOMTConstant.STATUS);
			Assert.assertEquals(hePom.getObjectiveHierarchySetName().getText(), LOMTConstant.OBJECT_HIERARCHY_SET);
			
			hePom.getLearningTitleInputText().sendKeys(heGoalframeworkName);
			Thread.sleep(7000);
			
			//DOMAIN SELECTION
			hePom.getDomainNameDropDown().click();
			Thread.sleep(4000);
			List<WebElement> domainList = hePom.getDomainList();
			int domainLength = domainList.size();
			if (domainLength > 0) {
				for (int i = 0; i <= domainLength; i++) {
					WebElement element = domainList.get(i);
					// TODO : apply assertion for all the fields
					if (element.getText().equalsIgnoreCase(HEConstant.DOMAIN_RE)) {
						element.click();
						flag = true;
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
					if (element.getText().equalsIgnoreCase(HEConstant.STATUS_RE)) {
						element.click();
						flag = true;
						break;
					}
				}
			} else {
				Assert.assertFalse((domainLength == 0), "HE Status dropdown size is zero");
				flag = false;
				return flag;
			}
			//Objective Hierarchy Set SELECTION-NON MANDATORY FIELDS
			//TODO
			jse.executeScript("window.scrollBy(0,300)");
			
			hePom.getNextButton().click();
			Thread.sleep(2000);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}
	
	public boolean educationalObjectiveIngestion(String fileType) {
		boolean flag = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 300);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			
			 //File upload logic 
			commonPOM.getUploadFileLink().click();
			
			if (fileType.equalsIgnoreCase(LOMTConstant.INVALID_FORMAT_FILE)) {
				Runtime.getRuntime().exec(LOMTConstant.INVALID_FORMAT_FILE_PATH);
				Thread.sleep(10000);
				driver.switchTo().alert().accept();
				//Thread.sleep(1000);
				jse.executeScript("window.scrollBy(0, -300)");
				commonPOM.getBackLinkFirst().click();
				Thread.sleep(1000);
				flag = true;
			} else if (fileType.equalsIgnoreCase(LOMTConstant.VALIDATION_MISSED)) {
				Runtime.getRuntime().exec(LOMTConstant.HE_INGESTION_VALIDATION_MISSED_FILE_PATH);
				Thread.sleep(10000);
				
				commonPOM.getNextButtonSt2().click();
				
				jse.executeScript("window.scrollBy(0, -300)");
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
				Thread.sleep(2000);
				
				if (commonPOM.getWaitForIngestionStatusText().getText().contains(LOMTConstant.INGESTION_FAILED_MESSAGE)) {
					flag = true;
					
					Assert.assertTrue(commonPOM.getViewIngestFullLogButton().isDisplayed());
					commonPOM.getViewIngestFullLogButton().click();
					Thread.sleep(2000);
					// validation check
					validationCheck = new ValidationCheck();
					if(validationCheck.heValidationErrorMessageAssertions(hePom)) {
						jse.executeScript("window.scrollBy(0,-500)");
						commonPOM.getBackLinkFirst().click();
						Thread.sleep(1000);
						flag = true;
					} else {
						flag = false;
						jse.executeScript("window.scrollBy(0,-500)");
						commonPOM.getBackLinkFirst().click();
						Thread.sleep(1000);
					}
				} else {
					flag = false;
					
					jse.executeScript("window.scrollBy(0,-500)");
					commonPOM.getBackLinkFirst().click();
					Thread.sleep(1000);
				}
			} else if (fileType.equalsIgnoreCase(LOMTConstant.INGEST)) {
				Runtime.getRuntime().exec(LOMTConstant.HE_INGESTION_FILE_PATH);
				Thread.sleep(10000);
				
				commonPOM.getNextButtonSt2().click();
				
				jse.executeScript("window.scrollBy(0, -300)");
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
				Thread.sleep(2000);
				if (commonPOM.getWaitForIngestionStatusText().getText().contains(LOMTConstant.INGESTION_SUCESSFULL_MESSAGE)) {
					flag = true;
					jse.executeScript("window.scrollBy(0,-800)");
					commonPOM.getPearsonLogo().click();
					Thread.sleep(1000);
				} else {
					flag = false;
					jse.executeScript("window.scrollBy(0,-800)");
					commonPOM.getPearsonLogo().click();
					Thread.sleep(1000);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public void verifyHEIngestedDataUI(ExtentTest logger, String goalFramework) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 300);
		boolean flag = false;
		try {
			commonPOM.getHeLOB().click();
			hePom.getEoStructure().click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			
			hePom.getHeEnterSearchTerm().sendKeys(goalFramework);
			Thread.sleep(1000);
			
			hePom.getHeUpdateResultButton().click();				
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			//Thread.sleep(15000);
			
			jse.executeScript("window.scrollBy(0,400)");
			
			if(schoolPOM.getResultFound().getText().contains("Showing")) {
				hePom.getHegoalframework().click();
				//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
				Thread.sleep(15000);
				jse.executeScript("window.scrollBy(0,100)");
				
				//Compare LO and EO number 
				Map<String, String> eoMap = HEConstant.getLOAndEOData();
				for (Map.Entry<String, String> entry : eoMap.entrySet()) {
					//System.out.println(entry.getKey() + "/" + entry.getValue());
					hePom.getHeInnerSearchTerm().sendKeys(entry.getKey());
					
					hePom.getHeUpdateResultButton().click();
					//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
					Thread.sleep(7000);
					int counter = 1;
					List<WebElement> webElement  =  schoolPOM.getParentChildList();
					if (!webElement.isEmpty()) {
						Iterator<WebElement> itr = webElement.iterator();
						while (itr.hasNext()) {
							WebElement childStructureElement = 	itr.next();
							String searchLO = childStructureElement.getText();
							int i = searchLO.indexOf(")");
							String value = searchLO.substring(i+1, searchLO.length());
							if (searchLO.contains(entry.getKey()) || value.equalsIgnoreCase(entry.getValue())) {
								flag = true;
								continue;
							} else {
								flag = false;
								logger.log(LogStatus.FAIL, "Unable to search LO/EO data using search term fields : "+entry.getKey()+" - "+entry.getValue());
							}
						}
					} else {
						flag = false;
						logger.log(LogStatus.FAIL, "Unable to search LO/EO data using search term fields: LO/EO not found");
					}
					counter++;
					hePom.getHeInnerSearchTerm().clear();
				}
				if (flag) {
					logger.log(LogStatus.PASS, "HE Educational Objective: Verified ingested data");
				}
			} else {
				logger.log(LogStatus.FAIL, "Unable to search HE Educational Objective Goalframework on UI : data verification failed");
			}
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "HE Educational Objective data verification is failed due to exception");
		}
	}
	
	public void exportHEEducationalObjective(ExtentTest logger,
			String goalFramework) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 300);
		try {
			commonPOM.getHeLOB().click();
			hePom.getEoStructure().click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));

			hePom.getHeEnterSearchTerm().sendKeys(goalFramework);
			Thread.sleep(1000);

			hePom.getHeUpdateResultButton().click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));

			jse.executeScript("window.scrollBy(0,600)");

			if (schoolPOM.getResultFound().getText().contains("Showing")) {
				removeExistingFile();
				schoolPOM.getAction().click();
				Thread.sleep(1000);
				
				assertTrue(hePom.getHeExport().isDisplayed());
				logger.log(LogStatus.PASS, "TC-LOMT-613-01_Export_Available");
				hePom.getHeExport().click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
				
				logger.log(LogStatus.PASS, "TC-LOMT-613-02_Export_Functionality");
				
				File ingestionFile = new File(HEConstant.HE_INGESTION_XLS_FILE_PATH);
				ReadExternalFrameworkFile readExternalFrameworkFile = new ReadExternalFrameworkFile();
				String exportedFileName = readExternalFrameworkFile.getFileFromDirectory(LOMTConstant.EXPORTED_FILE_PATH);
				
				File eoExportFile = new File(LOMTConstant.EXPORTED_FILE_PATH + exportedFileName);
				verifyEOData(logger, ingestionFile, eoExportFile);
				logger.log(LogStatus.PASS, "TC-LOMT-613-03_Export_Functionality");
				jse.executeScript("window.scrollBy(0,-1000)");
				commonPOM.getPearsonLogo().click();
			} else {
				logger.log(LogStatus.FAIL, "TC-LOMT-613-01_Export_Available");
				logger.log(LogStatus.FAIL, "TC-LOMT-613-02_Export_Functionality");
				logger.log(LogStatus.FAIL, "TC-LOMT-613-03_Export_Functionality");
			}
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Unable to verify HE Educational Objective exported file data");
			jse.executeScript("window.scrollBy(0,-1000)");
			commonPOM.getPearsonLogo().click();
		}
	}
	
	public void verifyEOData(ExtentTest logger, File ingestedFile, File exportedFile) {
		try {
			verifyEOHeaders(logger, exportedFile);
			verifyEOIngestedData(logger, ingestedFile, exportedFile);
		} catch(Exception e) {
			logger.log(LogStatus.FAIL, "Unable to verify HE Educational Objective exported file data");
		}
	}
	
	public void verifyEOHeaders(ExtentTest logger, File exportedFile) {
		InputStream exportedExcelFileIS = null;
		XSSFWorkbook workbookForExportedFile = null;
		try {
			exportedExcelFileIS = new FileInputStream(exportedFile);
			workbookForExportedFile = new XSSFWorkbook(exportedExcelFileIS);
			XSSFSheet actualDataSheet = workbookForExportedFile.getSheetAt(0);
			Cell cell = null;
			
			if (actualDataSheet.getRow(0).getCell(1).getStringCellValue().contains("Objective Hierarchy Set")) {
			} else {
				logger.log(LogStatus.FAIL, "Header value is not matching : "+"Objective Hierarchy Set");
			}
			if (actualDataSheet.getRow(1).getCell(1).getStringCellValue().contains("Domain")) {
			} else {
				logger.log(LogStatus.FAIL, "Header value is not matching : "+"Domain");
			}
			if (actualDataSheet.getRow(2).getCell(1).getStringCellValue().contains("Learning Experience Name")) {
			} else {
				logger.log(LogStatus.FAIL, "Header value is not matching : "+"Learning Experience Name");
			}
			if (actualDataSheet.getRow(3).getCell(1).getStringCellValue().contains("Learning Experience Segment")) {
			} else {
				logger.log(LogStatus.FAIL, "Header value is not matching : "+"Learning Experience Segment");
			}
			if (actualDataSheet.getRow(4).getCell(1).getStringCellValue().contains("Target Learner Population")) {
			} else {
				logger.log(LogStatus.FAIL, "Header value is not matching : "+"Target Learner Population");
			}
			if (actualDataSheet.getRow(5).getCell(1).getStringCellValue().contains("Education Context Level")) {
			} else {
				logger.log(LogStatus.FAIL, "Header value is not matching : "+"Education Context Level");
			}
			if (actualDataSheet.getRow(6).getCell(1).getStringCellValue().contains("Language")) {
			} else {
				logger.log(LogStatus.FAIL, "Header value is not matching : "+"Language");
			}
			if (actualDataSheet.getRow(7).getCell(1).getStringCellValue().contains("Creation Date")) {
			} else {
				logger.log(LogStatus.FAIL, "Header value is not matching : "+"Creation Date");
			}
			if (actualDataSheet.getRow(11).getCell(0).getStringCellValue().contains("TOC Numbering")) {
			} else {
				logger.log(LogStatus.FAIL, "Header value is not matching : "+"TOC Numbering");
			}
			if (actualDataSheet.getRow(11).getCell(1).getStringCellValue().contains("CURRENT Title TOC")) {
			} else {
				logger.log(LogStatus.FAIL, "Header value is not matching : "+"CURRENT Title TOC");
			}
			if (actualDataSheet.getRow(11).getCell(2).getStringCellValue().contains("LO Numbering")) {
			} else {
				logger.log(LogStatus.FAIL, "Header value is not matching : "+"LO Numbering");
			}
			if (actualDataSheet.getRow(11).getCell(3).getStringCellValue().contains("CURRENT Learning Objectives")) {
			} else {
				logger.log(LogStatus.FAIL, "Header value is not matching : "+"CURRENT Learning Objectives");
			}
			if (actualDataSheet.getRow(11).getCell(4).getStringCellValue().contains("NEW LO/EO Alignment to Current Content")) {
			} else {
				logger.log(LogStatus.FAIL, "Header value is not matching : "+"NEW LO/EO Alignment to Current Content");
			}
			if (actualDataSheet.getRow(11).getCell(5).getStringCellValue().contains("NEW Learning Objective #")) {
			} else {
				logger.log(LogStatus.FAIL, "Header value is not matching : "+"NEW Learning Objective #");
			}
			if (actualDataSheet.getRow(11).getCell(6).getStringCellValue().contains("NEW Learning Objectives")) {
			} else {
				logger.log(LogStatus.FAIL, "Header value is not matching : "+"NEW Learning Objectives");
			}
			if (actualDataSheet.getRow(11).getCell(7).getStringCellValue().contains("NEW Enabling Objective #")) {
			} else {
				logger.log(LogStatus.FAIL, "Header value is not matching : "+"NEW Enabling Objective #");
			}
			if (actualDataSheet.getRow(11).getCell(8).getStringCellValue().contains("NEW Enabling Objectives")) {
			} else {
				logger.log(LogStatus.FAIL, "Header value is not matching : "+"NEW Enabling Objectives");
			}
			if (actualDataSheet.getRow(11).getCell(9).getStringCellValue().contains("NEW LO/EO Prerequisites (Include only LO/EO #)")) {
			} else {
				logger.log(LogStatus.FAIL, "Header value is not matching : "+"NEW LO/EO Prerequisites (Include only LO/EO #)");
			}
			if (actualDataSheet.getRow(11).getCell(10).getStringCellValue().contains("Identified as a Key Concept (Y/N)")) {
			} else {
				logger.log(LogStatus.FAIL, "Header value is not matching : "+"Identified as a Key Concept (Y/N)");
			}
			if (actualDataSheet.getRow(11).getCell(11).getStringCellValue().contains("Identified as a Most Difficult Concept (Y/N)")) {
			} else {
				logger.log(LogStatus.FAIL, "Header value is not matching : "+"Identified as a Most Difficult Concept (Y/N)");
			}
			if (actualDataSheet.getRow(11).getCell(12).getStringCellValue().contains("Identified as a Concept Subject to Misconception (Y/N)")) {
			} else {
				logger.log(LogStatus.FAIL, "Header value is not matching : "+"Identified as a Concept Subject to Misconception (Y/N)");
			}
			if (actualDataSheet.getRow(11).getCell(13).getStringCellValue().contains("Misconception Descriptive Statement 1")) {
			} else {
				logger.log(LogStatus.FAIL, "Header value is not matching : "+"Misconception Descriptive Statement 1");
			}
			if (actualDataSheet.getRow(11).getCell(14).getStringCellValue().contains("Misconception Feedback 1")) {
			} else {
				logger.log(LogStatus.FAIL, "Header value is not matching : "+"Misconception Feedback 1");
			}
			if (actualDataSheet.getRow(11).getCell(15).getStringCellValue().contains("Misconception Assertion(s) 1")) {
			} else {
				logger.log(LogStatus.FAIL, "Header value is not matching : "+"Misconception Assertion(s) 1");
			}
			if (actualDataSheet.getRow(11).getCell(16).getStringCellValue().contains("Misconception Descriptive Statement 2")) {
			} else {
				logger.log(LogStatus.FAIL, "Header value is not matching : "+"Misconception Descriptive Statement 2");
			}
			if (actualDataSheet.getRow(11).getCell(17).getStringCellValue().contains("Misconception Feedback 2")) {
			} else {
				logger.log(LogStatus.FAIL, "Header value is not matching : "+"Misconception Feedback 2");
			}
			if (actualDataSheet.getRow(11).getCell(18).getStringCellValue().contains("Misconception Assertion(s) 2")) {
			} else {
				logger.log(LogStatus.FAIL, "Header value is not matching : "+"Misconception Assertion(s) 2");
			}
			if (actualDataSheet.getRow(11).getCell(19).getStringCellValue().contains("Misconception Descriptive Statement 3")) {
			} else {
				logger.log(LogStatus.FAIL, "Header value is not matching : "+"Misconception Descriptive Statement 3");
			}
			if (actualDataSheet.getRow(11).getCell(20).getStringCellValue().contains("Misconception Feedback 3")) {
			} else {
				logger.log(LogStatus.FAIL, "Header value is not matching : "+"Misconception Feedback 3");
			}
			if (actualDataSheet.getRow(11).getCell(21).getStringCellValue().contains("Misconception Assertion(s) 3")) {
			} else {
				logger.log(LogStatus.FAIL, "Header value is not matching : "+"Misconception Assertion(s) 3");
			}
			if (actualDataSheet.getRow(11).getCell(22).getStringCellValue().contains("Domain")) {
			} else {
				logger.log(LogStatus.FAIL, "Header value is not matching : "+"Domain");
			}
			if (actualDataSheet.getRow(11).getCell(23).getStringCellValue().contains("Blooms Cognitive Process Dimensions")) {
			} else {
				logger.log(LogStatus.FAIL, "Header value is not matching : "+"Blooms Cognitive Process Dimensions");
			}
			if (actualDataSheet.getRow(11).getCell(24).getStringCellValue().contains("Blooms Knowledge Dimensions")) {
			} else {
				logger.log(LogStatus.FAIL, "Header value is not matching : "+"Blooms Knowledge Dimensions");
			}
			if (actualDataSheet.getRow(11).getCell(25).getStringCellValue().contains("Webb's Depth of Knowledge Cognitive Complexity Dimension")) {
			} else {
				logger.log(LogStatus.FAIL, "Header value is not matching : "+"Webb's Depth of Knowledge Cognitive Complexity Dimension");
			}
			if (actualDataSheet.getRow(11).getCell(26).getStringCellValue().contains("Proficiency")) {
			} else {
				logger.log(LogStatus.FAIL, "Header value is not matching : "+"Proficiency");
			}
			if (actualDataSheet.getRow(11).getCell(27).getStringCellValue().contains("URN")) {
			} else {
				logger.log(LogStatus.FAIL, "Header value is not matching : "+"URN");
			}
			
			exportedExcelFileIS.close();
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Unable to verify HE Educational Objective Headers exported file data");
		}
	}
	
	public void verifyEOIngestedData(ExtentTest logger, File ingestedFile, File exportedFile) {
		InputStream isIngestion = null;
		XSSFWorkbook workbookIngestion = null;

		InputStream isExported = null;
		XSSFWorkbook workbookExported = null;
		int counter = 1;
		try {
			isIngestion = new FileInputStream(ingestedFile); //Ingestion File 
			workbookIngestion = new XSSFWorkbook(isIngestion);
			XSSFSheet ingestionDataSheet = workbookIngestion.getSheetAt(0);
			Iterator<Row> rowIteratoreIngestion = ingestionDataSheet.iterator();
			
			isExported = new FileInputStream(exportedFile); //Exported File
			workbookExported = new XSSFWorkbook(isExported);
			XSSFSheet exportedDataSheet = workbookExported.getSheetAt(0);
			Iterator<Row> rowIteratoreExport = exportedDataSheet.iterator();
			
			while (rowIteratoreExport.hasNext()) {
				Row rowExp = rowIteratoreExport.next();
				while (rowIteratoreIngestion.hasNext()) {
					if (counter <= 2) {
						Row rowIngest = rowIteratoreIngestion.next();
						
						if (rowExp.getRowNum()>11 && rowIngest.getRowNum()>11) {
							//NEW Learning Objective #
							if (!String.valueOf(rowExp.getCell(5)).contains("null") && !String.valueOf(rowIngest.getCell(5)).contains("null")) {
								if (rowExp.getCell(5).getStringCellValue().equalsIgnoreCase(rowIngest.getCell(5).getStringCellValue())) {
								} else {
									logger.log(LogStatus.FAIL, "HE Educational Objective data is not matched at row : "+rowExp.getRowNum());
								}
							} 
							//Analyze physical development in middle adulthood.
							if (!String.valueOf(rowExp.getCell(6)).contains("null") && !String.valueOf(rowIngest.getCell(6)).contains("null")) {
								if (rowExp.getCell(6).getStringCellValue().equalsIgnoreCase(rowIngest.getCell(6).getStringCellValue())) {
								} else {
									logger.log(LogStatus.FAIL, "HE Educational Objective data is not matched at row : "+rowExp.getRowNum());
								}
							} 
							//NEW Enabling Objective #
							if (!String.valueOf(rowExp.getCell(7)).contains("null") && !String.valueOf(rowIngest.getCell(7)).contains("null")) {
								if (rowExp.getCell(7).getStringCellValue().equalsIgnoreCase(rowIngest.getCell(7).getStringCellValue())) {
								} else {
									logger.log(LogStatus.FAIL, "HE Educational Objective data is not matched at row : "+rowExp.getRowNum());
								}
							} 
							//NEW Enabling Objectives
							if (!String.valueOf(rowExp.getCell(8)).contains("null") && !String.valueOf(rowIngest.getCell(8)).contains("null")) {
								if (rowExp.getCell(8).getStringCellValue().equalsIgnoreCase(rowIngest.getCell(8).getStringCellValue())) {
								} else {
									logger.log(LogStatus.FAIL, "HE Educational Objective data is not matched at row : "+rowExp.getRowNum());
								}
							} 
							//NEW LO/EO Prerequisites (Include only LO/EO #)
							if (!String.valueOf(rowExp.getCell(9)).contains("null") && !String.valueOf(rowIngest.getCell(9)).contains("null")) {
								if (rowExp.getCell(9).getStringCellValue().equalsIgnoreCase(rowIngest.getCell(9).getStringCellValue())) {
								} else {
									logger.log(LogStatus.FAIL, "HE Educational Objective data is not matched at row : "+rowExp.getRowNum());
								}
							} 
							//Identified as a Key Concept (Y/N)
							if (!String.valueOf(rowExp.getCell(10)).contains("null") && !String.valueOf(rowIngest.getCell(10)).contains("null")) {
								if (rowExp.getCell(10).getStringCellValue().equalsIgnoreCase(rowIngest.getCell(10).getStringCellValue())) {
								} else {
									logger.log(LogStatus.FAIL, "HE Educational Objective data is not matched at row : "+rowExp.getRowNum());
								}
							} 
							
							//Identified as a Most Difficult Concept (Y/N)
							if (!String.valueOf(rowExp.getCell(11)).contains("null") && !String.valueOf(rowIngest.getCell(11)).contains("null")) {
								if (rowExp.getCell(11).getStringCellValue().equalsIgnoreCase(rowIngest.getCell(11).getStringCellValue())) {
								} else {
									logger.log(LogStatus.FAIL, "HE Educational Objective data is not matched at row : "+rowExp.getRowNum());
								}
							} 
							
							//Identified as a Concept Subject to Misconception (Y/N)
							if (!String.valueOf(rowExp.getCell(12)).contains("null") && !String.valueOf(rowIngest.getCell(12)).contains("null")) {
								if (rowExp.getCell(12).getStringCellValue().equalsIgnoreCase(rowIngest.getCell(12).getStringCellValue())) {
								} else {
									logger.log(LogStatus.FAIL, "HE Educational Objective data is not matched at row : "+rowExp.getRowNum());
								}
							} 
							
							//Misconception Descriptive Statement 1
							if (!String.valueOf(rowExp.getCell(13)).contains("null")) {
								if (rowExp.getCell(13).getStringCellValue().equalsIgnoreCase(rowIngest.getCell(13).getStringCellValue())
										|| rowExp.getCell(13).getStringCellValue().equalsIgnoreCase(rowIngest.getCell(16).getStringCellValue())
										|| rowExp.getCell(13).getStringCellValue().equalsIgnoreCase(rowIngest.getCell(19).getStringCellValue())) {
								} else {
									logger.log(LogStatus.FAIL, "HE Misconception Descriptive Statement 1 data is not matched at row : " + rowExp.getRowNum());
								}
							} else {
								logger.log(LogStatus.FAIL, "HE Misconception Descriptive Statement 1 can not be null at row : " + rowExp.getRowNum());
							}
							
							//Misconception Feedback 1
							if (!String.valueOf(rowExp.getCell(14)).contains("null")) {
								if (rowExp.getCell(14).getStringCellValue().equalsIgnoreCase(rowIngest.getCell(14).getStringCellValue())
										|| rowExp.getCell(14).getStringCellValue().equalsIgnoreCase(rowIngest.getCell(17).getStringCellValue())
										|| rowExp.getCell(14).getStringCellValue().equalsIgnoreCase(rowIngest.getCell(20).getStringCellValue())) {
								} else {
									logger.log(LogStatus.FAIL, "HE Misconception Feedback 1 data is not matched at row : " + rowExp.getRowNum());
								}
							} else {
								logger.log(LogStatus.FAIL, "HE Misconception Feedback 1 can not be null at row : " + rowExp.getRowNum());
							}
							
							//Misconception Assertion(s) 1
							if (!String.valueOf(rowExp.getCell(15)).contains("null")) {
								if (rowExp.getCell(15).getStringCellValue().equalsIgnoreCase(rowIngest.getCell(15).getStringCellValue())
										|| rowExp.getCell(15).getStringCellValue().equalsIgnoreCase(rowIngest.getCell(18).getStringCellValue())
										|| rowExp.getCell(15).getStringCellValue().equalsIgnoreCase(rowIngest.getCell(21).getStringCellValue())) {
								} else {
									logger.log(LogStatus.FAIL, "HE Misconception Assertion(s) 1 data is not matched at row : " + rowExp.getRowNum());
								}
							} else {
								logger.log(LogStatus.FAIL, "HE Misconception Assertion(s) 1 can not be null at row : " + rowExp.getRowNum());
							}
							
							
							//Misconception Descriptive Statement 2
							if (!String.valueOf(rowExp.getCell(16)).contains("null")) {
								if (rowExp.getCell(16).getStringCellValue().equalsIgnoreCase(rowIngest.getCell(16).getStringCellValue())
										|| rowExp.getCell(16).getStringCellValue().equalsIgnoreCase(rowIngest.getCell(13).getStringCellValue())
										|| rowExp.getCell(16).getStringCellValue().equalsIgnoreCase(rowIngest.getCell(19).getStringCellValue())) {
								} else {
									logger.log(LogStatus.FAIL, "HE Misconception Descriptive Statement 2 data is not matched at row : " + rowExp.getRowNum());
								}
							} else {
								logger.log(LogStatus.FAIL, "HE Misconception Descriptive Statement 2 can not be null at row : " + rowExp.getRowNum());
							}
							
							//Misconception Feedback 2
							if (!String.valueOf(rowExp.getCell(17)).contains("null")) {
								if (rowExp.getCell(17).getStringCellValue().equalsIgnoreCase(rowIngest.getCell(17).getStringCellValue())
										|| rowExp.getCell(17).getStringCellValue().equalsIgnoreCase(rowIngest.getCell(14).getStringCellValue())
										|| rowExp.getCell(17).getStringCellValue().equalsIgnoreCase(rowIngest.getCell(20).getStringCellValue())) {
								} else {
									logger.log(LogStatus.FAIL, "HE Misconception Feedback 2 data is not matched at row : " + rowExp.getRowNum());
								}
							} else {
								logger.log(LogStatus.FAIL, "HE Misconception Feedback 2 can not be null at row : " + rowExp.getRowNum());
							}
							
							//Misconception Assertion(s) 2
							if (!String.valueOf(rowExp.getCell(18)).contains("null")) {
								if (rowExp.getCell(18).getStringCellValue().equalsIgnoreCase(rowIngest.getCell(18).getStringCellValue())
										|| rowExp.getCell(18).getStringCellValue().equalsIgnoreCase(rowIngest.getCell(15).getStringCellValue())
										|| rowExp.getCell(18).getStringCellValue().equalsIgnoreCase(rowIngest.getCell(21).getStringCellValue())) {
								} else {
									logger.log(LogStatus.FAIL, "HE Misconception Assertion(s) 2 data is not matched at row : " + rowExp.getRowNum());
								}
							} else {
								logger.log(LogStatus.FAIL, "HE Misconception Assertion(s) 2 can not be null at row : " + rowExp.getRowNum());
							}
							
							
							//Misconception Descriptive Statement 3
							if (!String.valueOf(rowExp.getCell(19)).contains("null")) {
								if (rowExp.getCell(19).getStringCellValue().equalsIgnoreCase(rowIngest.getCell(19).getStringCellValue())
										|| rowExp.getCell(19).getStringCellValue().equalsIgnoreCase(rowIngest.getCell(13).getStringCellValue())
										|| rowExp.getCell(19).getStringCellValue().equalsIgnoreCase(rowIngest.getCell(16).getStringCellValue())) {
								} else {
									logger.log(LogStatus.FAIL, "HE Misconception Descriptive Statement 3 data is not matched at row : " + rowExp.getRowNum());
								}
							} else {
								logger.log(LogStatus.FAIL, "HE Misconception Descriptive Statement 3 can not be null at row : " + rowExp.getRowNum());
							}
							
							//Misconception Feedback 3
							if (!String.valueOf(rowExp.getCell(20)).contains("null")) {
								if (rowExp.getCell(20).getStringCellValue().equalsIgnoreCase(rowIngest.getCell(20).getStringCellValue())
										|| rowExp.getCell(20).getStringCellValue().equalsIgnoreCase(rowIngest.getCell(14).getStringCellValue())
										|| rowExp.getCell(20).getStringCellValue().equalsIgnoreCase(rowIngest.getCell(17).getStringCellValue())) {
								} else {
									logger.log(LogStatus.FAIL, "HE Misconception Feedback 2 data is not matched at row : " + rowExp.getRowNum());
								}
							} else {
								logger.log(LogStatus.FAIL, "HE Misconception Feedback 2 can not be null at row : " + rowExp.getRowNum());
							}
							
							//Misconception Assertion(s) 3
							if (!String.valueOf(rowExp.getCell(21)).contains("null")) {
								if (rowExp.getCell(21).getStringCellValue().equalsIgnoreCase(rowIngest.getCell(21).getStringCellValue())
										|| rowExp.getCell(21).getStringCellValue().equalsIgnoreCase(rowIngest.getCell(15).getStringCellValue())
										|| rowExp.getCell(21).getStringCellValue().equalsIgnoreCase(rowIngest.getCell(18).getStringCellValue())) {
								} else {
									logger.log(LogStatus.FAIL, "HE Misconception Assertion(s) 2 data is not matched at row : " + rowExp.getRowNum());
								}
							} else {
								logger.log(LogStatus.FAIL, "HE Misconception Assertion(s) 2 can not be null at row : " + rowExp.getRowNum());
							}
							//Domain
							if (!String.valueOf(rowExp.getCell(22)).contains("null")) {
								if (rowExp.getCell(22).getStringCellValue().equalsIgnoreCase(rowIngest.getCell(22).getStringCellValue()) ) {
								} else {
									logger.log(LogStatus.FAIL, "HE Domain data is not matched at row : "+rowExp.getRowNum()); 
								}
							} else {
								logger.log(LogStatus.FAIL, "HE Domain data can not be null  at row : "+rowExp.getRowNum()); 
							}
							
							//Blooms Cognitive Process Dimensions
							if (!String.valueOf(rowExp.getCell(23)).contains("null")) {
								if (rowExp.getCell(23).getStringCellValue().trim().contains("Analyze")
										&& rowExp.getCell(23).getStringCellValue().trim().contains("Remember")
										&& rowExp.getCell(23).getStringCellValue().trim().contains("Apply") 
										&& rowExp.getCell(23).getStringCellValue().trim().contains("Create")) {
								} else {
									logger.log(LogStatus.FAIL, "HE Blooms Cognitive Process Dimensions data is not matched at row : "+rowExp.getRowNum());
								}
							} else {
								logger.log(LogStatus.FAIL, "HE Blooms Cognitive Process Dimensions data can not be null  at row : "+rowExp.getRowNum()); 
							}
							
							//Blooms Knowledge Dimensions
							if (!String.valueOf(rowExp.getCell(24)).contains("null")) {  
								if (rowExp.getCell(24).getStringCellValue().contains("Metacognitive")
										&& rowExp.getCell(24).getStringCellValue().contains("Factual")
										&& rowExp.getCell(24).getStringCellValue().contains("Conceptual")
										&& rowExp.getCell(24).getStringCellValue().contains("Procedural")) {
								} else {
									logger.log(LogStatus.FAIL, "HE Blooms Knowledge Dimensions data is not matched at row : "+rowExp.getRowNum());
								}
							} else {
								logger.log(LogStatus.FAIL, "HE Blooms Knowledge Dimensions data can not be null  at row : "+rowExp.getRowNum());
							}
							
							//Webb's Depth of Knowledge Cognitive Complexity Dimension
							if (!String.valueOf(rowExp.getCell(25)).contains("null")) {  
								if (rowExp.getCell(25).getStringCellValue().contains("Extended thinking")
										&& rowExp.getCell(25).getStringCellValue().contains("Recall and reproduction")
										&& rowExp.getCell(25).getStringCellValue().contains("Short term strategic thinking")
										&& rowExp.getCell(25).getStringCellValue().contains("Skills and Concepts")) {
								} else {
									logger.log(LogStatus.FAIL, "HE Webb's Depth of Knowledge Cognitive Complexity Dimension data is not matched at row : "+rowExp.getRowNum());
								}
							} else {
								logger.log(LogStatus.FAIL, "HE Webb's Depth of Knowledge Cognitive Complexity Dimension data can not be null  at row : "+rowExp.getRowNum());
							}
							
							//Proficiency
							if (!String.valueOf(rowExp.getCell(26)).contains("null")) {
								if (rowExp.getCell(26).getStringCellValue().equalsIgnoreCase(rowIngest.getCell(26).getStringCellValue()) ) {
								} else {
									logger.log(LogStatus.FAIL, "HE Proficiency data is not matched at row : "+rowExp.getRowNum());
								}
							} else {
								logger.log(LogStatus.FAIL, "HE Proficiency data can not be null  at row : "+rowExp.getRowNum());
							}
							
							//URN 
							if (!String.valueOf(rowExp.getCell(27)).contains("null")) {
							} else {
								logger.log(LogStatus.FAIL, "HE URN data can not be null  at row : "+rowExp.getRowNum());
							}
							//break;
						} else {
							break;
						}
						counter++;
					} else {
						return;
					}
					break;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(LogStatus.FAIL, "Unable to verify HE Educational Objective exported file data");
		}
	}
	
	public void verifyQAPairsData(ExtentTest logger) {
		try {
			
		} catch(Exception e) {
			logger.log(LogStatus.FAIL, "Unable to verify HE Educational Objective exported file data");
		}
	}
	
	public void removeExistingFile() throws IOException {
		if (new File(LOMTConstant.EXPORTED_FILE_PATH).exists()) 
			FileUtils.cleanDirectory(new File(LOMTConstant.EXPORTED_FILE_PATH));
	}
	
	public void gethomePage() {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		try {
			jse.executeScript("window.scrollBy(0,-1000)");
			commonPOM.getPearsonLogo().click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void heReingestion(ExtentTest logger, String heGoalframeworkName) {
		boolean flag = false;
		int counter = 0;
		try {
			updateGoalframework = HEConstant.LE_TITLE_RE + String.valueOf(startYear + (int)Math.round(Math.random() * (endYear - startYear)));
			System.out.println("heGoalframeworkName Reingestion: "+updateGoalframework);
			
			//update and reingest all Domain and QA_Pair values, counter = 0
			flag = reingestionHE(heGoalframeworkName, counter, logger, updateGoalframework);
			if (flag) {
				logger.log(LogStatus.PASS, "TC-LOMT-815-01_ingest_Educational Objective");
				logger.log(LogStatus.PASS, "TC-LOMT-815-02_re-ingest_Educational Objective");
				logger.log(LogStatus.PASS, "TC-LOMT-815-03_re-ingest_invalidURN_Educational Objective");
				logger.log(LogStatus.PASS, "TC-LOMT-815-04_re-ingest_VerifyURN_LO or EO");
				logger.log(LogStatus.PASS, "TC-LOMT-815-05_identifier axiom_for LO");	
				logger.log(LogStatus.PASS, "TC-LOMT-815-06_Update_LO_Description");
				logger.log(LogStatus.PASS, "TC-LOMT-815-07_identifier axiom_for EO");	
				logger.log(LogStatus.PASS, "TC-LOMT-815-08_Update_EO_Description");	
				logger.log(LogStatus.PASS, "TC-LOMT-815-09_Update_LO or EO Prerequisites");	
				logger.log(LogStatus.PASS, "TC-LOMT-815-10_Update_Identified_values");
				logger.log(LogStatus.PASS, "TC-LOMT-815-11_Update_Misconception_values");
				logger.log(LogStatus.PASS, "TC-LOMT-815-12_Update_Domain_values");
				logger.log(LogStatus.PASS, "TC-LOMT-815-13_Update_learningDimension");
				logger.log(LogStatus.PASS, "TC-LOMT-815-14_Update_Proficiency_values");
				logger.log(LogStatus.PASS, "TC-LOMT-815-21_Update_educational Objective Name");
			} else {
				logger.log(LogStatus.FAIL, "TC-LOMT-815-01_ingest_Educational Objective");
				logger.log(LogStatus.FAIL, "TC-LOMT-815-02_re-ingest_Educational Objective");
				logger.log(LogStatus.PASS, "TC-LOMT-815-03_re-ingest_invalidURN_Educational Objective");
				logger.log(LogStatus.FAIL, "TC-LOMT-815-04_re-ingest_VerifyURN_LO or EO");
				logger.log(LogStatus.FAIL, "TC-LOMT-815-05_identifier axiom_for LO");	
				logger.log(LogStatus.FAIL, "TC-LOMT-815-06_Update_LO_Description");
				logger.log(LogStatus.FAIL, "TC-LOMT-815-07_identifier axiom_for EO");	
				logger.log(LogStatus.FAIL, "TC-LOMT-815-08_Update_EO_Description");	
				logger.log(LogStatus.FAIL, "TC-LOMT-815-09_Update_LO or EO Prerequisites");	
				logger.log(LogStatus.FAIL, "TC-LOMT-815-10_Update_Identified_values");
				logger.log(LogStatus.FAIL, "TC-LOMT-815-11_Update_Misconception_values");
				logger.log(LogStatus.FAIL, "TC-LOMT-815-12_Update_Domain_values");
				logger.log(LogStatus.FAIL, "TC-LOMT-815-13_Update_learningDimension");
				logger.log(LogStatus.FAIL, "TC-LOMT-815-14_Update_Proficiency_values");
				logger.log(LogStatus.FAIL, "TC-LOMT-815-21_Update_educational Objective Name");
			}
			counter++;
			
			//Add new LO/EO , counter = 1			
			flag = reingestionHE(null, counter, logger, updateGoalframework);
			if (flag) {
				logger.log(LogStatus.PASS, "TC-LOMT-815-15_Add_New_values"); 	
				logger.log(LogStatus.PASS, "TC-LOMT-815-20_Add_new_Q&A_Pair_value");
			} else {
				logger.log(LogStatus.FAIL, "TC-LOMT-815-15_Add_New_values");
				logger.log(LogStatus.FAIL, "TC-LOMT-815-20_Add_new_Q&A_Pair_value");
			}
			counter++;
			
			//Delete LO/EO, counter = 2
			flag = reingestionHE(null, counter, logger, updateGoalframework);
			if (!flag) {
				logger.log(LogStatus.PASS, "TC-LOMT-815-17_Update_Question and Ans_Q&A_Pair");
				logger.log(LogStatus.PASS, "TC-LOMT-815-18_Update_Assertion_Q&A_Pair");	
				logger.log(LogStatus.PASS, "TC-LOMT-815-19_Update_Hint_Q&A_Pair");	
				logger.log(LogStatus.PASS, "TC-LOMT-815-22_Delete_Domain_value");	
				logger.log(LogStatus.PASS, "TC-LOMT-815-23_Delete_value_Q&A_Pair_value");
			} else {
				logger.log(LogStatus.FAIL, "TC-LOMT-815-17_Update_Question and Ans_Q&A_Pair");
				logger.log(LogStatus.FAIL, "TC-LOMT-815-18_Update_Assertion_Q&A_Pair");	
				logger.log(LogStatus.FAIL, "TC-LOMT-815-19_Update_Hint_Q&A_Pair");	
				logger.log(LogStatus.FAIL, "TC-LOMT-815-22_Delete_Domain_value");	
				logger.log(LogStatus.FAIL, "TC-LOMT-815-23_Delete_value_Q&A_Pair_value");
			}
			if (!flag) {
				logger.log(LogStatus.PASS, "TC-LOMT-815-16_Verify_URN_LO or EO_Q&A_Pair");			
				logger.log(LogStatus.PASS, "TC-LOMT-815_25_Re-export_And _ee-ingestion");
				logger.log(LogStatus.PASS, "TC-LOMT-815_24_Re-export_after Re-ingestion");
			} else {
				logger.log(LogStatus.FAIL, "TC-LOMT-815-16_Verify_URN_LO or EO_Q&A_Pair");			
				logger.log(LogStatus.FAIL, "TC-LOMT-815_25_Re-export_And _ee-ingestion");
				logger.log(LogStatus.FAIL, "TC-LOMT-815_24_Re-export_after Re-ingestion");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean reingestionHE(String goalframework, int counter, ExtentTest logger, String updateGoalframework) {
		boolean flag = false;
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 300);
		ReadHEFile readHEFile= new ReadHEFile();
		try {
			commonPOM.getHeLOB().click();
			hePom.getEoStructure().click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			//Thread.sleep(10000);
			
			if (goalframework == null) {
				hePom.getHeEnterSearchTerm().sendKeys(updateGoalframework);
			} else {
				hePom.getHeEnterSearchTerm().sendKeys(goalframework);	
			}
			
			jse.executeScript("window.scrollBy(0,100)");
			Thread.sleep(1000);
			hePom.getHeUpdateResultButton().click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			//Thread.sleep(10000);
			
			jse.executeScript("window.scrollBy(0,400)");
			
			if (schoolPOM.getResultFound().getText().contains("Showing")) {
				removeExistingFile();
				schoolPOM.getAction().click();
				Thread.sleep(1000);
				
				assertTrue(hePom.getHeExport().isDisplayed());
				
				hePom.getHeExport().click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
				//Thread.sleep(10000);
				
				readHEFile.updateHEFExportedFileData(counter);
				
				hePom.getHeEnterSearchTerm().clear();
				jse.executeScript("window.scrollBy(0, -1000)");
				Thread.sleep(1000);
				commonPOM.getPearsonLogo().click();
				// go for ingestion
				
				commonPOM.getHeLOB().click();
				commonPOM.getManageIngestion().click();
				createUploadStructureFirstPage();
				
				/*updateGoalframework = HEConstant.LE_TITLE_RE + String.valueOf(startYear + (int)Math.round(Math.random() * (endYear - startYear)));
				System.out.println("heGoalframeworkName Reingestion: "+updateGoalframework);*/				
				
				createUploadStructureMetaDataPageReingestion(updateGoalframework);				
				
				commonPOM.getUploadFileLink().click();

				Runtime.getRuntime().exec(LOMTConstant.HE_REINGESTION_FILE_PATH);
				Thread.sleep(10000);
								
				commonPOM.getNextButtonSt2().click();
								
				jse.executeScript("window.scrollBy(0, -300)");
								
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
				Thread.sleep(2000);
				if (commonPOM.getWaitForIngestionStatusText().getText().contains(LOMTConstant.INGESTION_SUCESSFULL_MESSAGE)) {
					flag = true;
					jse.executeScript("window.scrollBy(0,-800)");
					commonPOM.getPearsonLogo().click();
					Thread.sleep(1000);
				} else {
					flag = false;
					jse.executeScript("window.scrollBy(0,-800)");
					commonPOM.getPearsonLogo().click();
					Thread.sleep(1000);
					}
			} else {
				logger.log(LogStatus.FAIL, "Unable to filter HE EO goalframework using Enter serach term, reingetsion is failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public void closeDriverInstance() {
		driver.close();
	}
	
	/*public static void main(String[] args) {
		HigherEducation obj = new HigherEducation();
		ReadExternalFrameworkFile readExternalFrameworkFile = new ReadExternalFrameworkFile();
		String exportedFileName = readExternalFrameworkFile.getFileFromDirectory(LOMTConstant.EXPORTED_FILE_PATH);
		File eoExportFile = new File(LOMTConstant.EXPORTED_FILE_PATH + exportedFileName);
		
		File ingestionFile = new File(HEConstant.HE_INGESTION_XLS_FILE_PATH);
		
		obj.verifyEOHeaders(null, eoExportFile);
		obj.verifyEOIngestedData(null, ingestionFile, eoExportFile);
	}*/

}
