package lomt.pearson.common;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
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
import lomt.pearson.page_object.EnglishPOM;
import lomt.pearson.page_object.ExternalFrameworkPOM;
import lomt.pearson.page_object.HEPom;
import lomt.pearson.page_object.IntermediaryPOM;
import lomt.pearson.page_object.Login;
import lomt.pearson.page_object.NALSPom;
import lomt.pearson.page_object.ProductTocPOM;
import lomt.pearson.page_object.SGPom;
import lomt.pearson.page_object.SchoolPOM;

public class NonAdminUserBrowseExport extends BaseClass {
	
	private String environment = LoadPropertiesFile.getPropertiesValues(LOMTConstant.LOMT_ENVIRONMENT);
	
	private String userName = LoadPropertiesFile.getPropertiesValues(LOMTConstant.USER_NAME);
	private String pwd = LoadPropertiesFile.getPropertiesValues(LOMTConstant.PASSWORD);
	
	private String learningUser = LoadPropertiesFile.getPropertiesValues(LOMTConstant.USER_NAME_LEARNING_USER);
	private String learningUserPwd = LoadPropertiesFile.getPropertiesValues(LOMTConstant.PASSWORD_LEARNING_USER_PWD);
	
	private String learningSME = LoadPropertiesFile.getPropertiesValues(LOMTConstant.USER_NAME_LEARNING_SME);
	private String learningSMEPwd = LoadPropertiesFile.getPropertiesValues(LOMTConstant.PASSWORD_LEARNING_SME_PWD);
	
	private String learningEditor = LoadPropertiesFile.getPropertiesValues(LOMTConstant.USER_NAME_LEARNING_EDITOR);
	private String learningEditorPwd = LoadPropertiesFile.getPropertiesValues(LOMTConstant.PASSWORD_LEARNING_EDITOR);
	
	private WebDriver driver;

	private Login login = null;
	private CommonPOM commonPOM = null;
	private HEPom hePom = null;
	private NALSPom nalsPom  = null;
	private SGPom sgPom = null;
	private SchoolPOM schoolPOM = null;
	private ExternalFrameworkPOM exfPOM = null;
	private IntermediaryPOM intermediaryPOM = null;
	private EnglishPOM englishPOM = null;
	private ProductTocPOM productTocPOM = null;
	
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
		englishPOM = new EnglishPOM(driver);
		productTocPOM = new ProductTocPOM(driver);
	}
	
	public void loginAdmin() {
		try {
			login = new Login(driver);
			login.getUserName().sendKeys(userName);
			login.getPassword().sendKeys(pwd);
			login.getLoginButton().click();
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String loginLearningUser() {
		String userName = null;
		try {
			userName = learningUser;
			login = new Login(driver);
			login.getUserName().sendKeys(userName);
			login.getPassword().sendKeys(learningUserPwd);
			login.getLoginButton().click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userName;
	}
	
	public String loginLearingSME() {
		String userName = null;
		try {
			userName = learningSME;
			login = new Login(driver);
			login.getUserName().sendKeys(userName);
			login.getPassword().sendKeys(learningSMEPwd);
			login.getLoginButton().click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userName;
	}
	
	public String loginLearningEditor() {
		try {
			userName = learningEditor;
			login = new Login(driver);
			login.getUserName().sendKeys(learningEditor);
			login.getPassword().sendKeys(learningEditorPwd);
			login.getLoginButton().click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userName;
	}
	
	public void logout() {
		try {
			commonPOM.getLogoutOption().click();
			Thread.sleep(2000);
			commonPOM.getLogout().click();
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean englishGSEBrowseAndExport(ExtentTest logger, String userName) {
		boolean flag = false;

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 180);
		try {
			commonPOM.getEnglishLOB().click();
			englishPOM.getGseLink().click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.GSE_ACTION_LINK)));

			englishPOM.getGseStructure().click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			Thread.sleep(10000);
			
			jse.executeScript("window.scrollBy(0,400)");

			if (commonPOM.getShowingGFText().getText().contains("Showing")) {
				removeExistingFile();
				englishPOM.getSelectAll().click();
				englishPOM.getRenderedLink().click();

				commonPOM.getExportButton().click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));				
				Thread.sleep(35000);
				englishPOM.getFirstGSENode().click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
				Thread.sleep(20000);
				flag = true;
				
				jse.executeScript("window.scrollBy(0,-1000)");
				commonPOM.getPearsonLogo().click();
			} else {
				logger.log(LogStatus.FAIL, "No results found on English GSE browse page, logged user : " + userName);
				jse.executeScript("window.scrollBy(0,-800)");
				commonPOM.getPearsonLogo().click();
				flag = false;
				return flag;
			}
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Exception occured on the English GSE browse and export, logged user : " + userName);
			jse.executeScript("window.scrollBy(0,-800)");
			commonPOM.getPearsonLogo().click();
			flag = false;
			return flag;
		}
		return flag;
	}
	
	public boolean exfBrowseAndExport(ExtentTest logger, String userName, String lob) {
		boolean flag = false;
		
		JavascriptExecutor jse = (JavascriptExecutor) driver;			
		WebDriverWait wait = new WebDriverWait(driver, 300); 
		
		if (lob.equalsIgnoreCase(LOMTConstant.ENGLISH_LOB)) {
			try {
				commonPOM.getEnglishLOB().click();
				exfPOM.getExternalFrameworkStructureBrowseEnglish().click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
				jse.executeScript("window.scrollBy(0,300)");
				
				if (commonPOM.getShowingGFText().getText().contains("Showing")) {
					removeExistingFile();
					exfPOM.getActionLink().click();
					Thread.sleep(2000);
					commonPOM.getExfExport().click();
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
					/*String exportedFileName = getFileFromDirectory(LOMTConstant.EXPORTED_FILE_PATH);
					if (exportedFileName.contains("External_Framework_Template")) {
						flag = true;
					}*/
					commonPOM.getExfFirtBrowsedGF().click();
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
					jse.executeScript("window.scrollBy(0,-800)");
					flag = true;
					
					commonPOM.getPearsonLogo().click();
				} else {
					logger.log(LogStatus.FAIL, "No results found on English External Framework browse page, logged user : " + userName);
					jse.executeScript("window.scrollBy(0,-800)");
					commonPOM.getPearsonLogo().click();
					flag = false;
					return flag;
				
				}
			} catch (Exception e) {
				logger.log(LogStatus.FAIL, "Exception occured on the English External Framework during browse and export, logged user : " + userName);
				jse.executeScript("window.scrollBy(0,-800)");
				commonPOM.getPearsonLogo().click();
				flag = false;
				return flag;
			}
		} else if (lob.equalsIgnoreCase(LOMTConstant.HE_LOB)) {
			try {
				commonPOM.getHeLOB().click();
				exfPOM.getExternalFrameworkStructureBrowseHE().click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
				jse.executeScript("window.scrollBy(0,300)");
				
				if (commonPOM.getShowingGFText().getText().contains("Showing")) {
					removeExistingFile();
					exfPOM.getActionLink().click();
					Thread.sleep(2000);
					commonPOM.getCommonExportButton().click();
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
					/*String exportedFileName = getFileFromDirectory(LOMTConstant.EXPORTED_FILE_PATH);
					if (exportedFileName.contains("External_Framework_Template")) {
						flag = true;
					}*/
					commonPOM.getExfFirtBrowsedGF().click();
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
					jse.executeScript("window.scrollBy(0,-1000)");
					flag = true;
					
					commonPOM.getPearsonLogo().click();
				} else {
					logger.log(LogStatus.FAIL, "No results found on HE External Framework browse page, logged user : " + userName);
					jse.executeScript("window.scrollBy(0,-1000)");
					commonPOM.getPearsonLogo().click();
					flag = false;
					return flag;
				}
			} catch (Exception e) {
				logger.log(LogStatus.FAIL, "Exception occured on the HE External Framework during browse and export, logged user : " + userName);
				jse.executeScript("window.scrollBy(0,-1000)");
				commonPOM.getPearsonLogo().click();
				flag = false;
				return flag;
			}
		} 
		return flag;
	}
	
	public boolean productBrowseAndExport(ExtentTest logger, String userName, String lob) {
		boolean flag = false;
		
		JavascriptExecutor jse = (JavascriptExecutor) driver;			
		WebDriverWait wait = new WebDriverWait(driver, 300); 
		
		if (lob.equalsIgnoreCase(LOMTConstant.ENGLISH_LOB)) {
			try {
				commonPOM.getEnglishLOB().click();
				commonPOM.getEnglishProductTOCStructure().click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
				Thread.sleep(30000);
				jse.executeScript("window.scrollBy(0,300)");
				
				if (commonPOM.getShowingGFText().getText().contains("Showing")) {
					removeExistingFile();
					exfPOM.getActionLink().click();
					Thread.sleep(2000);
					commonPOM.getExfExport().click();
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
					
					/*String exportedFileName = getFileFromDirectory(LOMTConstant.EXPORTED_FILE_PATH);
					if (exportedFileName.contains(".xlsx") || exportedFileName.contains(".xlsx") ) {
						flag = true;
					}*/
					commonPOM.getExfFirtBrowsedGF().click();
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
					flag = true;
					
					jse.executeScript("window.scrollBy(0,-1000)");
					commonPOM.getPearsonLogo().click();
				} else {
					logger.log(LogStatus.FAIL, "No results found on English Product browse page, logged user : " + userName);
					jse.executeScript("window.scrollBy(0,-1000)");
					commonPOM.getPearsonLogo().click();
					flag = false;
					return flag;
				}
			} catch (Exception e) {
				logger.log(LogStatus.FAIL, "Exception occured on the English Product browse page, logged user : " + userName);
				jse.executeScript("window.scrollBy(0,-1000)");
				commonPOM.getPearsonLogo().click();
				flag = false;
				return flag;
			}
		} else if(lob.equalsIgnoreCase(LOMTConstant.HE_LOB)) {
			try {
				commonPOM.getHeLOB().click();
				commonPOM.getHeProductTOCStructure().click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));	
				Thread.sleep(40000);
				jse.executeScript("window.scrollBy(0,300)");
				
				if (commonPOM.getShowingGFText().getText().contains("Showing")) {
					removeExistingFile();
					productTocPOM.getTocActionLink().click();
					Thread.sleep(2000);
					commonPOM.getCommonExportButton().click();
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
					
					/*String exportedFileName = getFileFromDirectory(LOMTConstant.EXPORTED_FILE_PATH);
					if (exportedFileName.contains(".xlsx") || exportedFileName.contains(".xlsx") ) {
						flag = true;
					}*/
					commonPOM.getTocFirstGF().click();
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
					flag = true;
					
					jse.executeScript("window.scrollBy(0,-1000)");
					commonPOM.getPearsonLogo().click();
				} else {
					logger.log(LogStatus.FAIL, "No results found on HE Product browse page, logged user : " + userName);
					jse.executeScript("window.scrollBy(0,-1000)");
					commonPOM.getPearsonLogo().click();
					flag = false;
					return flag;
				}
			} catch (Exception e) {
				logger.log(LogStatus.FAIL, "Exception occured on the HE Product browse page, logged user : " + userName);
				jse.executeScript("window.scrollBy(0,-1000)");
				commonPOM.getPearsonLogo().click();
				flag = false;
				return flag;
			}
		} else {
			//School
			try {
				commonPOM.getSchoolGlobalLOB().click();
				commonPOM.getSchoolProductTOCStructure().click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));	
				Thread.sleep(20000);
				
				jse.executeScript("window.scrollBy(0,300)");
				
				if (commonPOM.getShowingGFText().getText().contains("Showing")) {
					removeExistingFile();

					productTocPOM.getActionLink().click();
					Thread.sleep(2000);
								
					commonPOM.getCommonExportButton().click();
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
					
					commonPOM.getExfFirtBrowsedGF().click();
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
					flag = true;
					
					jse.executeScript("window.scrollBy(0,-1000)");
					commonPOM.getPearsonLogo().click();
				} else {
					logger.log(LogStatus.FAIL, "No results found on School Product browse page, logged user : " + userName);
					jse.executeScript("window.scrollBy(0,-1000)");
					commonPOM.getPearsonLogo().click();
					flag = false;
					return flag;
				}
			} catch (Exception e) {
				logger.log(LogStatus.FAIL, "Exception occured on the School Product browse page, logged user : " + userName);
				jse.executeScript("window.scrollBy(0,-800)");
				commonPOM.getPearsonLogo().click();
				flag = false;
				return flag;
			}
		}
		return flag;
	}
	
	public boolean heEducationalObjectiveAndExport(ExtentTest logger, String userName) {
		boolean flag = false;
		JavascriptExecutor jse = (JavascriptExecutor) driver;			
		WebDriverWait wait = new WebDriverWait(driver, 300); 
		try {
			commonPOM.getHeLOB().click();
			hePom.getEoStructure().click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			Thread.sleep(3000);
			jse.executeScript("window.scrollBy(0,300)");
			
			if (commonPOM.getShowingGFText().getText().contains("Showing")) {
				removeExistingFile();
				schoolPOM.getAction().click();
				commonPOM.getCommonExportButton().click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
				Thread.sleep(15000);
				
				hePom.getHegoalframework().click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
				flag = true;
				
				jse.executeScript("window.scrollBy(0,-1500)");
				commonPOM.getPearsonLogo().click();
			} else {
				logger.log(LogStatus.FAIL, "No results found on HE Educational Objetive browse page, logged user : " + userName);
				jse.executeScript("window.scrollBy(0,-1500)");
				commonPOM.getPearsonLogo().click();
				flag = false;
				return flag;
			}
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Exception occured on the HE Educational Objetive browse page, logged user : " + userName);
			jse.executeScript("window.scrollBy(0,-1000)");
			commonPOM.getPearsonLogo().click();
			flag = false;
			return flag;
		}
		return flag;
	}
	
	public boolean getABAndCustomBrowseAndExport(ExtentTest logger, String userName, String lob) {
		boolean flag = false;
		JavascriptExecutor jse = (JavascriptExecutor) driver;			
		WebDriverWait wait = new WebDriverWait(driver, 300); 
		if (lob.equalsIgnoreCase(LOMTConstant.SCHOOL) || lob.equalsIgnoreCase(LOMTConstant.CUSTOM)) {
			try {
				commonPOM.getSchoolGlobalLOB().click();
				schoolPOM.getCurriculumSt().click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));				
				jse.executeScript("window.scrollBy(0,250)");
				if(schoolPOM.getResultFound().getText().contains("Showing")) {
					removeExistingFile();
					//export
					schoolPOM.getAction().click();
					commonPOM.getCommonExportButton().click();
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
					
					jse.executeScript("window.scrollBy(0,100)");
					
					int counter = 0;
					List<WebElement> webElement  =  intermediaryPOM.getIntermediaryGFList(); // common for school CS and Intermediary
					if (!webElement.isEmpty()) {
						Iterator<WebElement> itr = webElement.iterator();
						while (itr.hasNext()) {
							WebElement childStructureElement = 	itr.next();
							String discipline = childStructureElement.getText();
							if (discipline.contains("ABXmlImport") || discipline.contains("ExternalFrameworkSpreadsheetImport")) {
								counter++;
								String xpath = "//div[@class='list-data-container']/child::div["+counter+"]/div/div[1]/div/span/span[2]/a";
								WebElement element = driver.findElement(By.xpath(xpath));
								element.click();
								wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
								break;
							}
						}
						flag = true;
						jse.executeScript("window.scrollBy(0,-1000)");
						commonPOM.getPearsonLogo().click();
					}
				} else {
					logger.log(LogStatus.FAIL, "No results found on School Global Curriculum Standard/Custom browse page, logged user : " + userName);
					jse.executeScript("window.scrollBy(0,-1000)");
					commonPOM.getPearsonLogo().click();
					flag = false;
					return flag;
				}
			} catch (Exception e) {
				logger.log(LogStatus.FAIL, "Exception occured on the School Global Curriculum Standard/Custom browse page, logged user : " + userName);
				jse.executeScript("window.scrollBy(0,-1000)");
				commonPOM.getPearsonLogo().click();
				flag = false;
				return flag;
			}
		} 
		return flag;
	}
	
	public boolean getIntermediaryBrowseAndExport(ExtentTest logger, String userName) {
		boolean flag = false;
		JavascriptExecutor jse = (JavascriptExecutor) driver;			
		WebDriverWait wait = new WebDriverWait(driver, 300); 
		try {
			commonPOM.getSchoolGlobalLOB().click();
			intermediaryPOM.getIntermediaryStructure().click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			jse.executeScript("window.scrollBy(0,300)");
			
			if (schoolPOM.getResultFound().getText().contains("Showing")) {
				removeExistingFile();
				schoolPOM.getIntAction().click();
				
				commonPOM.getCommonExportButton().click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
				
				commonPOM.getExfFirtBrowsedGF().click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
				flag = true;
				
				jse.executeScript("window.scrollBy(0,-1000)");
				commonPOM.getPearsonLogo().click();
			} else {
				logger.log(LogStatus.FAIL, "No results found on School Global Intermediary browse page, logged user : " + userName);
				jse.executeScript("window.scrollBy(0,-1000)");
				commonPOM.getPearsonLogo().click();
				flag = false;
				return flag;
			}
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Exception occured on the School Global Intermediary browse page, logged user : " + userName);
			jse.executeScript("window.scrollBy(0,-1000)");
			commonPOM.getPearsonLogo().click();
			flag = false;
			return flag;
		} 
		return flag;
	}
	
	public void removeExistingFile() throws IOException {
		if (new File(LOMTConstant.EXPORTED_FILE_PATH).exists()) 
			FileUtils.cleanDirectory(new File(LOMTConstant.EXPORTED_FILE_PATH));
	}
	
	public String getFileFromDirectory(String filePath) {
		String exportedFileName = null;
		File folder = new File(filePath);
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
		      if (listOfFiles[i].isFile()) {
		    	  exportedFileName = listOfFiles[i].getName();
		        System.out.println("File " + listOfFiles[i].getName());
		      } 
		    }
		return exportedFileName;
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
			Thread.sleep(15000);
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
			jse.executeScript("window.scrollBy(0,-500)");
			Thread.sleep(15000);
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
			
			//Country selection, as per JIRA: LOMT-1779
			schoolPOM.getCountryDropdown().click();
			Thread.sleep(6000);
			List<WebElement> countryList = schoolPOM.getCountryDropdownList();
			int countryLength = countryList.size();
			if (countryLength > 0) {
				for (int i = 0; i <= countryLength; i++) {
					WebElement element = countryList.get(i);
					if (element.getText().equalsIgnoreCase(SchoolConstant.UNITIED_STATES)) {
						element.click();
						break;
					}
				}
			}else {
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
			jse.executeScript("window.scrollBy(0,-500)");
			Thread.sleep(15000);
			//SUBJECT Selection
			schoolPOM.getSubjectDropdown().click();
			Thread.sleep(6000);
			subjectList = schoolPOM.getSubjectDropdownList();
			subjectLength = subjectList.size();
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
			
			//Country selection, as per JIRA: LOMT-1779
			schoolPOM.getCountryDropdown().click();
			Thread.sleep(6000);
			countryList = schoolPOM.getCountryDropdownList();
			countryLength = countryList.size();
			if (countryLength > 0) {
				for (int i = 0; i <= countryLength; i++) {
					WebElement element = countryList.get(i);
					if (element.getText().equalsIgnoreCase(SchoolConstant.UNITIED_STATES)) {
						element.click();
						break;
					}
				}
			}else {
				Assert.assertFalse((subjectLength == 0), LOMTConstant.SUBJECT+LOMTConstant.DROPDOWN_SIZE_NOT_ZERO);
			}
			//Authority Selection, pick authority name, 
			schoolPOM.getAuthorityDropdown().click();
			Thread.sleep(6000);
			authorityList = schoolPOM.getAuthorityDropdownList();
			aLength = authorityList.size();
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
			curriculumSetList = schoolPOM.getCurriculumSetDropdownList();
			csLength = curriculumSetList.size();
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
		driver.manage().timeouts().implicitlyWait(05, TimeUnit.SECONDS);
		driver.close();
	}

}
