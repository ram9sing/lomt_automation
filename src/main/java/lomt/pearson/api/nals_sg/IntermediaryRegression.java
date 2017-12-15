package lomt.pearson.api.nals_sg;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
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
import org.testng.log4testng.Logger;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import lomt.pearson.api.externalframework.ReadExternalFrameworkFile;
import lomt.pearson.common.BaseClass;
import lomt.pearson.common.LoadPropertiesFile;
import lomt.pearson.constant.LOMTConstant;
import lomt.pearson.constant.SchoolConstant;
import lomt.pearson.page_object.CommonPOM;
import lomt.pearson.page_object.ExternalFrameworkPOM;
import lomt.pearson.page_object.IntermediaryPOM;
import lomt.pearson.page_object.Login;
import lomt.pearson.page_object.SchoolPOM;

/**
 * Ingestion and Export
 * TODO : logger
 * @author ram.sin
 *
 */
public class IntermediaryRegression  {
	
	final static Logger LOGGER = Logger.getLogger(Intermediary.class);
	
	private JavascriptExecutor jse;
	private WebDriverWait wait;
	WebDriver driver;
	
	private CommonPOM commonPOM = null;
	private SchoolPOM schoolPOM = null;
	private IntermediaryPOM intermediaryPOM = null;
	private ExternalFrameworkPOM exfPOM = null;
	
	public IntermediaryRegression(WebDriver driver) {
		commonPOM = new CommonPOM(driver);
		intermediaryPOM = new IntermediaryPOM(driver);
		schoolPOM = new SchoolPOM(driver);
		exfPOM = new ExternalFrameworkPOM(driver);
		
		jse = (JavascriptExecutor) driver;
		wait = new WebDriverWait(driver, 900);
		this.driver = driver;
	}
	
	public boolean schoolGlobalBrowsePage() {
		boolean flag = false;
		try {
			Assert.assertTrue(commonPOM.getSchoolGlobalLOB().isDisplayed());
			commonPOM.getSchoolGlobalLOB().click();
			commonPOM.getManageIngestion().click();
			flag = true;
		} catch (Exception e) {
			flag = false;
			return flag;
		}
		return flag;
	}
	
	public boolean createUploadStructurePage1() {
		boolean flag = false;
		try {
			commonPOM.getSchoolGlobalLOBRadioButton().click();
			commonPOM.getIntermediaryRadioButton().click();
			
			jse.executeScript("window.scrollBy(0,200)");
			commonPOM.getNextButtonFirst().click();
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.BUSINESS_XPATH)));
			Thread.sleep(10000);
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
	
	public void createUploadStructurePageWithCorrectIntermediaryFile(String disciplineName, ExtentTest logger) throws InterruptedException, IOException {
		//int counter = 0;
		String disCounter = null;
		try {
			if (disciplineName != null) {

				Map<String, String> map = SchoolConstant.getIntermediaryDisciplineDataInMap();
				for (Map.Entry<String, String> entry : map.entrySet()) {
					if (entry.getValue().equalsIgnoreCase(disciplineName)) {
						disCounter = entry.getKey();
						break;
					}
				}

				if (Integer.valueOf(disCounter) < 8) {
					jse.executeScript("window.scrollBy(0,100)");
					Thread.sleep(1000);
					Map<String, String> innerMap = SchoolConstant.getIntermediaryDisciplineDataXPATH();
					for (Map.Entry<String, String> entry : innerMap.entrySet()) {
						if (disCounter.equalsIgnoreCase(entry.getKey())) {
							String xpath = entry.getValue();
							WebElement element = driver.findElement(By.xpath(xpath));
							element.click();
							break;
						}
					}
				}
				if (Integer.valueOf(disCounter) >= 8 && Integer.valueOf(disCounter) < 16) {
					jse.executeScript("window.scrollBy(0,250)");
					Thread.sleep(1000);
					Map<String, String> innerMap = SchoolConstant.getIntermediaryDisciplineDataXPATH();
					for (Map.Entry<String, String> entry : innerMap.entrySet()) {
						if (disCounter.equalsIgnoreCase(entry.getKey())) {
							String xpath = entry.getValue();
							WebElement element = driver.findElement(By.xpath(xpath));
							element.click();
							break;
						}
					}
				}
				if (Integer.valueOf(disCounter) >= 16 && Integer.valueOf(disCounter) < 24) {
					jse.executeScript("window.scrollBy(0,400)");
					Thread.sleep(1000);
					Map<String, String> innerMap = SchoolConstant.getIntermediaryDisciplineDataXPATH();
					for (Map.Entry<String, String> entry : innerMap.entrySet()) {
						if (disCounter.equalsIgnoreCase(entry.getKey())) {
							String xpath = entry.getValue();
							WebElement element = driver.findElement(By.xpath(xpath));
							element.click();
							break;
						}
					}
				}
				if (Integer.valueOf(disCounter) >= 24 && Integer.valueOf(disCounter) < 32) {
					jse.executeScript("window.scrollBy(0,700)");
					Thread.sleep(1000);
					Map<String, String> innerMap = SchoolConstant.getIntermediaryDisciplineDataXPATH();
					for (Map.Entry<String, String> entry : innerMap.entrySet()) {
						if (disCounter.equalsIgnoreCase(entry.getKey())) {
							String xpath = entry.getValue();
							WebElement element = driver.findElement(By.xpath(xpath));
							element.click();
							break;
						}
					}
				}
				if (Integer.valueOf(disCounter) >= 32 && Integer.valueOf(disCounter) < 40) {
					jse.executeScript("window.scrollBy(0,900)");
					Thread.sleep(1000);
					Map<String, String> innerMap = SchoolConstant.getIntermediaryDisciplineDataXPATH();
					for (Map.Entry<String, String> entry : innerMap.entrySet()) {
						if (disCounter.equalsIgnoreCase(entry.getKey())) {
							String xpath = entry.getValue();
							WebElement element = driver.findElement(By.xpath(xpath));
							element.click();
							break;
						}
					}
				}
				jse.executeScript("window.scrollBy(0,500)");
				commonPOM.getNextButtonSt2().click();
				Thread.sleep(1000);
				commonPOM.getUploadFileLink().click();

				// upload incorrect intermediary xls file
				Runtime.getRuntime().exec(LOMTConstant.INTERMEDIARY_FILE_PATH);
				Thread.sleep(8000);
				commonPOM.getNextButtonSt2().click();
				
				logger.log(LogStatus.PASS, "TC-LOMT-10-01_UploadIntermediaryLink");
				logger.log(LogStatus.PASS, "TC-LOMT-10-02_SelectFileButton");
				logger.log(LogStatus.PASS, "TC-LOMT-10-03_UploadButton");
				
				jse.executeScript("window.scrollBy(0, -100)");
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
				Thread.sleep(3000);
				
				if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_SUCESSFULL_MESSAGE)) {
					logger.log(LogStatus.PASS, "TC-LOMT-458-07_Ingest Intermediary sheet");
					
					logger.log(LogStatus.PASS, "TC-LOMT-10-04_UploadIntermediary_Successful");
					logger.log(LogStatus.PASS, "TC-LOMT-10-05_UploadFunctionality_xlsxOr xlsFormat");
					logger.log(LogStatus.PASS, "TC-LOMT-10-11_TagCol_values");
					
					jse.executeScript("window.scrollBy(0, -400)");
					commonPOM.getBackLinkFirst().click();
				} else {
					logger.log(LogStatus.FAIL, "TC-LOMT-458-07_Ingest Intermediary sheet");
					
					logger.log(LogStatus.FAIL, "TC-LOMT-10-04_UploadIntermediary_Successful");
					logger.log(LogStatus.PASS, "TC-LOMT-10-05_UploadFunctionality_xlsxOr xlsFormat");
					logger.log(LogStatus.FAIL, "TC-LOMT-10-11_TagCol_values");
					
					jse.executeScript("window.scrollBy(0, -400)");
					commonPOM.getBackLinkFirst().click();
				}
			} else {
				//Fresh ingestion
				intermediaryPOM.getBusinessRadioButton().click();
				jse.executeScript("window.scrollBy(0, 800)");
				commonPOM.getNextButtonSt2().click();
				Thread.sleep(1000);
				commonPOM.getUploadFileLink().click();
				
				// upload incorrect intermediary xls file
				Runtime.getRuntime().exec(LOMTConstant.INTERMEDIARY_FILE_PATH);
				Thread.sleep(8000);
				commonPOM.getNextButtonSt2().click();
				
				logger.log(LogStatus.PASS, "TC-LOMT-10-01_UploadIntermediaryLink");
				logger.log(LogStatus.PASS, "TC-LOMT-10-02_SelectFileButton");
				logger.log(LogStatus.PASS, "TC-LOMT-10-03_UploadButton");
				
				jse.executeScript("window.scrollBy(0, -100)");
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
				Thread.sleep(3000);
				
				if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_SUCESSFULL_MESSAGE)) {
					logger.log(LogStatus.PASS, "TC-LOMT-458-07_Ingest Intermediary sheet");
					
					logger.log(LogStatus.PASS, "TC-LOMT-10-04_UploadIntermediary_Successful");
					logger.log(LogStatus.PASS, "TC-LOMT-10-05_UploadFunctionality_xlsxOr xlsFormat");
					logger.log(LogStatus.PASS, "TC-LOMT-10-11_TagCol_values");
					
					jse.executeScript("window.scrollBy(0, -400)");
					commonPOM.getBackLinkFirst().click();
				} else {
					logger.log(LogStatus.FAIL, "TC-LOMT-458-07_Ingest Intermediary sheet");
					
					logger.log(LogStatus.FAIL, "TC-LOMT-10-04_UploadIntermediary_Successful");
					logger.log(LogStatus.PASS, "TC-LOMT-10-05_UploadFunctionality_xlsxOr xlsFormat");
					logger.log(LogStatus.FAIL, "TC-LOMT-10-11_TagCol_values");
					
					jse.executeScript("window.scrollBy(0, -400)");
					commonPOM.getBackLinkFirst().click();
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createUploadStructurePageWithIncorrectIntermediaryFile(ExtentTest logger) {
		try {
			jse.executeScript("window.scrollBy(0, 500)");
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
				commonPOM.getViewIngestFullLogButton().click();
				boolean validationFlag = validationCheck();
				if (validationFlag) {
					logger.log(LogStatus.PASS, "TC-LOMT-10-07_IntermediaryIngestion_missingCol");				
					logger.log(LogStatus.PASS, "TC-LOMT-10-10_Intermediary_ingestion_Tag_missing");
					logger.log(LogStatus.PASS, "TC-LOMT-10-12_IntermediaryIngestion_col_blank");
				} else {
					logger.log(LogStatus.FAIL, "TC-LOMT-10-07_IntermediaryIngestion_missingCol");				
					logger.log(LogStatus.FAIL, "TC-LOMT-10-10_Intermediary_ingestion_Tag_missing");
					logger.log(LogStatus.FAIL, "TC-LOMT-10-12_IntermediaryIngestion_col_blank");
				}
				jse.executeScript("window.scrollBy(0, -800)");
				commonPOM.getPearsonLogo().click();
				Thread.sleep(1000);
			} else {
				logger.log(LogStatus.FAIL, "TC-LOMT-10-07_IntermediaryIngestion_missingCol");
				logger.log(LogStatus.FAIL, "TC-LOMT-10-10_Intermediary_ingestion_Tag_missing");				
				logger.log(LogStatus.FAIL, "TC-LOMT-10-12_IntermediaryIngestion_col_blank");
				
				jse.executeScript("window.scrollBy(0, -800)");
				commonPOM.getPearsonLogo().click();
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			
		}
	}
	
	public void exportIntermediaryForSchoolGlobal(ExtentTest logger, boolean flag) {
		int reCounter = 0;
		try {
			if (flag) {
				commonPOM.getSchoolGlobalLOB().click();
				intermediaryPOM.getIntermediaryStructure().click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
				
				removeExistingFile();
				schoolPOM.getIntAction().click();
				logger.log(LogStatus.PASS, "TC-LOMT-615_10_Verify_Export_Option_In_Actions_For_Intermediary_SchoolGlobal");
				
				schoolPOM.getIntExport().click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
				logger.log(LogStatus.PASS, "TC-LOMT-615_11_Verify_Export_OpIntermediary_To_ExcelFile_SchoolGlobal");
				
				verifyIntExportedFile(logger);
				jse.executeScript("window.scrollBy(0, -500)");
				commonPOM.getPearsonLogo().click();
			} else {
				
				//Udapting existing values and adding new rows
				boolean flag1 = reingestionIntermediary(reCounter, /*jse, wait,*/ null);
				if (flag1) {
					logger.log(LogStatus.PASS, "TC-LOMT-1587-01_SchoolGlobal_re-ingest_sameDecipline");
					logger.log(LogStatus.PASS, "TC-LOMT-1587-03_SchoolGlobal_re-ingest_UpdateIntermediary Statement");
					logger.log(LogStatus.PASS, "TC-LOMT-1587-04_SchoolGlobal_re-ingest_UpdateIntermediary Statement_splChar");
					logger.log(LogStatus.PASS, "TC-LOMT-1587-05_SchoolGlobal_re-ingest_UpdateIntermediary Statement_LongChar");
					logger.log(LogStatus.PASS, "TC-LOMT-1587-06_SchoolGlobal_re-ingest_Update_Tag");
					logger.log(LogStatus.PASS, "TC-LOMT-1587-08_SchoolGlobal_re-ingest_Update_Category");
					logger.log(LogStatus.PASS, "TC-LOMT-1587-09_SchoolGlobal_re-ingest_Update_Category_Blank");
					logger.log(LogStatus.PASS, "TC-LOMT-1587-10_SchoolGlobal_re-ingest_Update_Intermediary Statement Code");
					logger.log(LogStatus.PASS, "TC-LOMT-1587-11_SchoolGlobal_re-ingest_Update_Intermediary Statement Code_blank");
					logger.log(LogStatus.PASS, "TC-LOMT-1587-12_SchoolGlobal_re-ingest_Add_New");
				} else {
					logger.log(LogStatus.FAIL, "TC-LOMT-1587-01_SchoolGlobal_re-ingest_sameDecipline");
					logger.log(LogStatus.FAIL, "TC-LOMT-1587-03_SchoolGlobal_re-ingest_UpdateIntermediary Statement");
					logger.log(LogStatus.FAIL, "TC-LOMT-1587-04_SchoolGlobal_re-ingest_UpdateIntermediary Statement_splChar");
					logger.log(LogStatus.FAIL, "TC-LOMT-1587-05_SchoolGlobal_re-ingest_UpdateIntermediary Statement_LongChar");
					logger.log(LogStatus.FAIL, "TC-LOMT-1587-06_SchoolGlobal_re-ingest_Update_Tag");
					logger.log(LogStatus.FAIL, "TC-LOMT-1587-08_SchoolGlobal_re-ingest_Update_Category");
					logger.log(LogStatus.FAIL, "TC-LOMT-1587-09_SchoolGlobal_re-ingest_Update_Category_Blank");
					logger.log(LogStatus.FAIL, "TC-LOMT-1587-10_SchoolGlobal_re-ingest_Update_Intermediary Statement Code");
					logger.log(LogStatus.FAIL, "TC-LOMT-1587-11_SchoolGlobal_re-ingest_Update_Intermediary Statement Code_blank");
					logger.log(LogStatus.PASS, "TC-LOMT-1587-12_SchoolGlobal_re-ingest_Add_New");
				}
				reCounter++;
				
				//deletion of rows
				boolean flag2 = reingestionIntermediary(reCounter, /*jse, wait,*/ null);
				if (flag2) {
					logger.log(LogStatus.PASS, "TC-LOMT-1587-13_SchoolGlobal_re-ingest_Delete_Exisiting");
				} else {
					logger.log(LogStatus.FAIL, "TC-LOMT-1587-13_SchoolGlobal_re-ingest_Delete_Exisiting");
				}
				reCounter++;
				
				//validations
				boolean flag3 = reingestionIntermediary(reCounter, /*jse, wait,*/ "ValidationCheck");
				if (flag3) {
					logger.log(LogStatus.PASS, "TC-LOMT-1587-07_SchoolGlobal_re-ingest_Update_Tag_Wrong ");
					logger.log(LogStatus.PASS, "TC-LOMT-1587-14_SchoolGlobal_re-ingest_WrongHeader");
					logger.log(LogStatus.PASS, "TC-LOMT-1587-15_SchoolGlobal_re-ingest_Blank_IntermediaryStatement");
					logger.log(LogStatus.PASS, "TC-LOMT-1587-15SchoolGlobal_re-ingest_Blank_Tag");
					logger.log(LogStatus.PASS, "TC-LOMT-1587-17_SchoolGlobal_re-ingest_intermediaryNoExist_Have URN");
					logger.log(LogStatus.PASS, "TC-LOMT-1587-18_SchoolGlobal_re-ingest_URNinStatemetn_intermediaryExistNotFound");
				} else {
					logger.log(LogStatus.FAIL, "TC-LOMT-1587-07_SchoolGlobal_re-ingest_Update_Tag_Wrong ");
					logger.log(LogStatus.FAIL, "TC-LOMT-1587-14_SchoolGlobal_re-ingest_WrongHeader");
					logger.log(LogStatus.FAIL, "TC-LOMT-1587-15_SchoolGlobal_re-ingest_Blank_IntermediaryStatement");
					logger.log(LogStatus.FAIL, "TC-LOMT-1587-15SchoolGlobal_re-ingest_Blank_Tag");
					logger.log(LogStatus.FAIL, "TC-LOMT-1587-17_SchoolGlobal_re-ingest_intermediaryNoExist_Have URN");
					logger.log(LogStatus.FAIL, "TC-LOMT-1587-18_SchoolGlobal_re-ingest_URNinStatemetn_intermediaryExistNotFound");
				}
				
				//reingested data verification
				boolean flag4 = verificationReingestionDataUI();
				if (flag4) {
					logger.log(LogStatus.PASS, "TC-LOMT-1587-21_SchoolNALS_re-ingest_VerifyData");
				} else {
					logger.log(LogStatus.FAIL, "TC-LOMT-1587-21_SchoolNALS_re-ingest_VerifyData");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<String> getIngestedIntermediaryDiscipline() {
		List<String> intermediaryList = new LinkedList<String>();
		
		try {
			commonPOM.getSchoolGlobalLOB().click();
			intermediaryPOM.getIntermediaryStructure().click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			jse.executeScript("window.scrollBy(0,1000)");
			
			//expanding all the ingested Intermediary disciplines
			if (!intermediaryPOM.getLoadMoreButton().getAttribute("class").contains("load-more-text disabled")) {
				intermediaryPOM.getLoadMoreButton().click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
				jse.executeScript("window.scrollBy(0,1000)");
				if (!intermediaryPOM.getLoadMoreButton().getAttribute("class").contains("load-more-text disabled")) {
					intermediaryPOM.getLoadMoreButton().click();
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
					jse.executeScript("window.scrollBy(0,1000)");
					if (!intermediaryPOM.getLoadMoreButton().getAttribute("class").contains("load-more-text disabled")) {
						intermediaryPOM.getLoadMoreButton().click();
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
					} else {
						jse.executeScript("window.scrollBy(0, -2000)");
					}
				} else {
					jse.executeScript("window.scrollBy(0, -2000)");
				}
			} else {
				jse.executeScript("window.scrollBy(0, -1000)");
			}
			
			List<WebElement> webElement  =  intermediaryPOM.getIntermediaryGFList();
			if (!webElement.isEmpty()) {
				Iterator<WebElement> itr = webElement.iterator();
				while (itr.hasNext()) {
					WebElement childStructureElement = 	itr.next();
					String discipline = childStructureElement.getText();
					//System.out.println("Intermediary ingested discipline : "+structureName);
					int i = discipline.indexOf("\n");
					int j = discipline.lastIndexOf("\n");
					intermediaryList.add(discipline.substring(i, j).trim());
				}
			} else {
				//do simple ingestion
				return intermediaryList;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		jse.executeScript("window.scrollBy(0, -2000)");
		commonPOM.getPearsonLogo().click();
		
		return intermediaryList;
	}
	
	public void searchIntermediaryDiscipline(String disName, ExtentTest logger) {
		//List<String> intermediaryList = new LinkedList<String>();
		
		try {
			commonPOM.getSchoolGlobalLOB().click();
			intermediaryPOM.getIntermediaryStructure().click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			jse.executeScript("window.scrollBy(0,1000)");
			
			//expanding all the ingested Intermediary disciplines
			if (!intermediaryPOM.getLoadMoreButton().getAttribute("class").contains("load-more-text disabled")) {
				intermediaryPOM.getLoadMoreButton().click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
				jse.executeScript("window.scrollBy(0,1000)");
				if (!intermediaryPOM.getLoadMoreButton().getAttribute("class").contains("load-more-text disabled")) {
					intermediaryPOM.getLoadMoreButton().click();
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
					jse.executeScript("window.scrollBy(0,1000)");
					if (!intermediaryPOM.getLoadMoreButton().getAttribute("class").contains("load-more-text disabled")) {
						intermediaryPOM.getLoadMoreButton().click();
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
						jse.executeScript("window.scrollBy(0, -2000)");
					} else {
						jse.executeScript("window.scrollBy(0, -2000)");
					}
				} else {
					jse.executeScript("window.scrollBy(0, -2000)");
				}
			} else {
				jse.executeScript("window.scrollBy(0, -1000)");
			}
			
			jse.executeScript("window.scrollBy(0,200)");
			
			//verifying ingested data
			List<WebElement> webElement  =  intermediaryPOM.getIntermediaryGFList();
				if (!webElement.isEmpty()) {
					int counter = 0;
					Iterator<WebElement> itr = webElement.iterator();
					while (itr.hasNext()) {
						WebElement childStructureElement = 	itr.next();
						String discipline = childStructureElement.getText();
						//System.out.println("Intermediary ingested discipline : "+structureName);
						int i = discipline.indexOf("\n");
						int j = discipline.lastIndexOf("\n");
						counter++;
						if (disName.equalsIgnoreCase(discipline.substring(i, j).trim())) {
							//childStructureElement.click();
							//String xpath = "//div[@class='list-data-container']/child::div["+counter+"]/div/div[1]/div/div/a";
							String xpath = "//div[@class='list-data-container']/child::div["+counter+"]/div/div[1]/div/span/span[2]/a";
							if (counter <=7) {
								WebElement element = driver.findElement(By.xpath(xpath));
								element.click();
								wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
							}
							if (counter >=7 && counter <=14) {
								jse.executeScript("window.scrollBy(0,600)");
								WebElement element = driver.findElement(By.xpath(xpath));
								element.click();
								wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
								jse.executeScript("window.scrollBy(0,-1000)");
							}
							if (counter >=14 && counter <=21) {
								jse.executeScript("window.scrollBy(0,1100)");
								WebElement element = driver.findElement(By.xpath(xpath));
								element.click();
								wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
								jse.executeScript("window.scrollBy(0,-1500)");
							}
							if (counter >=21 && counter <=28) {
								jse.executeScript("window.scrollBy(0,1600)");
								WebElement element = driver.findElement(By.xpath(xpath));
								element.click();
								wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
								jse.executeScript("window.scrollBy(0,-2000)");
							}
							if (counter >=28 && counter <=35) {
								jse.executeScript("window.scrollBy(0,2100)");
								WebElement element = driver.findElement(By.xpath(xpath));
								element.click();
								wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
								jse.executeScript("window.scrollBy(0,-2100)");
							}
							// verifying data
							boolean flag = false;
							jse.executeScript("window.scrollBy(0,200)");
							List<String> testDataList = SchoolConstant.getIntTestData();
							for (String discip : testDataList) {
								intermediaryPOM.getEnterSearchTerm().sendKeys(discip);
								intermediaryPOM.getUpdateResultButton().click();	
								//Thread.sleep(10000);
								wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
								if(intermediaryPOM.getSearchResultText().getText().contains("Showing")) {
									flag = true;
									intermediaryPOM.getEnterSearchTerm().clear();
									continue;
								} else {
									flag = false;
									intermediaryPOM.getEnterSearchTerm().clear();
									//logger.log(LogStatus.FAIL, "Data not found on UI using Enter search term fileds : "+discip);
								}
							}
							if (flag) {
								logger.log(LogStatus.PASS, "TC-LOMT-10-06_Verify_IngestedCols");
							} else {
								logger.log(LogStatus.FAIL, "TC-LOMT-10-06_Verify_IngestedCols");
							}
							break;
						}
					}
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getDisciplineForIngestion(List<String> disciplineList) {
		String disciplineName = null;
		boolean flag = false;
		try {
			List<String> availableDisList = SchoolConstant.getIntermediaryDisciplineData();
			for (String discipline : availableDisList) {
				if (!disciplineList.contains(discipline.trim())) {
					disciplineName = discipline;
				}
				/*for (String ingestedDis : disciplineList) {
					if (ingestedDis.trim().equalsIgnoreCase(discipline.trim())) {
						flag = true;
					}
				}
				if (!flag) {
					disciplineName = discipline;
					break;
				}*/
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return disciplineName;
	}
	
	public boolean validationCheck() {
		boolean flag = false;
		try {
			if (exfPOM.getErrorRowOne().getText().contains("Tag (STFk, P, CU) value cannot be blank in Row : 3") 
					|| exfPOM.getErrorRowTwo().getText().contains("Tag (STFk, P, CU) value cannot be blank in Row : 3")) {
				flag = true;
			} else {
				flag = false;
				return flag;
			}
			if (exfPOM.getErrorRowOne().getText().contains("Intermediary Statement value cannot be blank in Row : 3") 
					|| exfPOM.getErrorRowTwo().getText().contains("Intermediary Statement value cannot be blank in Row : 3")) {
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
	
	public boolean reingestionValidationCheck() {
		boolean flag = false;
		try {
			if (exfPOM.getErrorRowOne().getText().contains("Tag (STFk, P, CU) value cannot be blank in Row : 3") 
					|| exfPOM.getErrorRowTwo().getText().contains("Tag (STFk, P, CU) value cannot be blank in Row : 3")
					|| exfPOM.getErrorRowThree().getText().contains("Tag (STFk, P, CU) value cannot be blank in Row : 3") ) {
				flag = true;
			} else {
				flag = false;
				return flag;
			}
			if (exfPOM.getErrorRowOne().getText().contains("Intermediary Statement value cannot be blank in Row : 2") 
					|| exfPOM.getErrorRowTwo().getText().contains("Intermediary Statement value cannot be blank in Row : 2")
					|| exfPOM.getErrorRowThree().getText().contains("Intermediary Statement value cannot be blank in Row : 2") ) {
				flag = true;
			} else {
				flag = false;
				return flag;
			}
			if (exfPOM.getErrorRowOne().getText().contains("Tag (STFk, P, CU) value cannot be blank in Row : 2") 
					|| exfPOM.getErrorRowTwo().getText().contains("Tag (STFk, P, CU) value cannot be blank in Row : 2")
					|| exfPOM.getErrorRowThree().getText().contains("Tag (STFk, P, CU) value cannot be blank in Row : 2") ) {
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
	
	public void verifyIngestDataUI(ExtentTest logger, String disName) {
		boolean flag = false;
		try {
			searchIntermediaryDiscipline(disName, logger);
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "TC-LOMT-10-06_Verify_IngestedCols");
		}
		jse.executeScript("window.scrollBy(0,-2000)");
		commonPOM.getPearsonLogo().click();
	}
	
	public void removeExistingFile() throws IOException {
		if (new File(LOMTConstant.EXPORTED_FILE_PATH).exists()) 
			FileUtils.cleanDirectory(new File(LOMTConstant.EXPORTED_FILE_PATH));
	}
	
	public void verifyIntExportedFile(ExtentTest logger) {
		ReadExternalFrameworkFile readExternalFrameworkFile = null;
		
		InputStream inputStream = null;
		XSSFWorkbook workbook = null;
		XSSFSheet worksheet = null;
		try {
			readExternalFrameworkFile = new ReadExternalFrameworkFile();
			String exportedFileName = readExternalFrameworkFile.getFileFromDirectory(LOMTConstant.EXPORTED_FILE_PATH);
			
			File intExpFile = new File(LOMTConstant.EXPORTED_FILE_PATH + exportedFileName);
			
			inputStream =  new FileInputStream(intExpFile);
			workbook = new XSSFWorkbook(inputStream);
			worksheet = workbook.getSheetAt(0);
			logger.log(LogStatus.PASS, "TC-LOMT-615_12_Verify_Intermediaries_written_To_ExcelSpreadsheet_SchoolGlobal");
			logger.log(LogStatus.PASS, "TC-LOMT-615_13_Verify_Title_Of_Excel_Spreadsheet_Is_IntermediaryName_SchoolGlobal");
			
			if (SchoolConstant.INT_STATEMENT_HEADING.equalsIgnoreCase(worksheet.getRow(LOMTConstant.ZERO).getCell(LOMTConstant.ZERO).getStringCellValue())) {
				logger.log(LogStatus.PASS, "TC-LOMT-615_14_Verify_ColumnA_IntermediaryStatement_In_ExcelSpreadsheet_SchoolGlobal");
			} else {
				logger.log(LogStatus.FAIL, "TC-LOMT-615_14_Verify_ColumnA_IntermediaryStatement_In_ExcelSpreadsheet_SchoolGlobal");
			}
			if (SchoolConstant.TAG_HEADING.equalsIgnoreCase(worksheet.getRow(LOMTConstant.ZERO).getCell(LOMTConstant.ONE).getStringCellValue())) {
				logger.log(LogStatus.PASS, "TC-LOMT-615_15_Verify_ColumnB_Tag_In_ExcelSpreadsheet_SchoolGlobal");
			} else {
				logger.log(LogStatus.FAIL, "TC-LOMT-615_15_Verify_ColumnB_Tag_In_ExcelSpreadsheet_SchoolGlobal");
			}
			if (SchoolConstant.CATEGORY_HEADING.equalsIgnoreCase(worksheet.getRow(LOMTConstant.ZERO).getCell(LOMTConstant.TWO).getStringCellValue())) {
				logger.log(LogStatus.PASS, "TC-LOMT-615_16_Verify_ColumnC_Category_In_ExcelSpreadsheet_SchoolGlobal");
			} else {
				logger.log(LogStatus.FAIL, "TC-LOMT-615_16_Verify_ColumnC_Category_In_ExcelSpreadsheet_SchoolGlobal");
			}
			if (SchoolConstant.INT_STMT_CODE_HEADING.equalsIgnoreCase(worksheet.getRow(LOMTConstant.ZERO).getCell(LOMTConstant.THREE).getStringCellValue())) {
				logger.log(LogStatus.PASS, "TC-LOMT-615_17_Verify_ColumnD_IntermediaryStatementCode_In_ExcelSpreadsheet_SchoolGlobal");
			} else {
				logger.log(LogStatus.FAIL, "TC-LOMT-615_17_Verify_ColumnD_IntermediaryStatementCode_In_ExcelSpreadsheet_SchoolGlobal");
			}
			if (SchoolConstant.INT_STMT_ID_HEADING.equalsIgnoreCase(worksheet.getRow(LOMTConstant.ZERO).getCell(LOMTConstant.FOUR).getStringCellValue())) {
				logger.log(LogStatus.PASS, "TC-LOMT-615_18_Verify_ColumnE_IntermediaryStatementID_In_ExcelSpreadsheet_SchoolGlobal");
			} else {
				logger.log(LogStatus.FAIL, "TC-LOMT-615_18_Verify_ColumnE_IntermediaryStatementID_In_ExcelSpreadsheet_SchoolGlobal");
			}
			int counter = 0;
			Iterator<Row> rowIteratoreForExport = worksheet.iterator();
			while (rowIteratoreForExport.hasNext()) {
				Row rowExp = rowIteratoreForExport.next();
				if (rowExp.getRowNum() != 0) {
					counter++;
				}
			}
			logger.log(LogStatus.PASS, "Total row count is : "+counter);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean reingestionIntermediary(int counter, /*JavascriptExecutor jse, WebDriverWait wait,*/ String useCase) {
		boolean flag = false;
		IntermediaryHelper iHelper = null;
		int disciplineNum = 0;
		try {
			//Export steps
			jse.executeScript("window.scrollBy(0,-500)");
			commonPOM.getPearsonLogo().click();	
			commonPOM.getSchoolGlobalLOB().click();
			intermediaryPOM.getIntermediaryStructure().click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			
			String intGoalframework = intermediaryPOM.getFirstDisRow().getText(); 
			int i = intGoalframework.indexOf("\n");
			//int j = goalframework.lastIndexOf("\n");
			String finalGF = intGoalframework.substring(i, intGoalframework.length());
			
			removeExistingFile();
			schoolPOM.getIntAction().click();
			Thread.sleep(1000);
			
			schoolPOM.getIntExport().click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			
			
			//Ingestion steps
			iHelper = new IntermediaryHelper();
			jse.executeScript("window.scrollBy(0,-500)");
			commonPOM.getPearsonLogo().click();			
			iHelper.updateIntermediaryExportedFileData(counter);
			
			commonPOM.getSchoolGlobalLOB().click();
			commonPOM.getManageIngestion().click();
			commonPOM.getSchoolGlobalLOBRadioButton().click();
			commonPOM.getIntermediaryRadioButton().click();
			
			jse.executeScript("window.scrollBy(0,200)");
			commonPOM.getNextButtonFirst().click();
			Thread.sleep(10000);
			
			for (Map.Entry<String, String> entry : SchoolConstant.getIntermediaryDisciplineDataInMap().entrySet()) {
				if (entry.getValue().equalsIgnoreCase(finalGF.trim())) {
					disciplineNum = Integer.valueOf(entry.getKey());
					break;
				}
			}
			if (disciplineNum <=13) {
				Map<String, String> innerMap = SchoolConstant.getIntermediaryDisciplineDataXPATH();
				for (Map.Entry<String, String> entry : innerMap.entrySet()) {
					if (String.valueOf(disciplineNum).equalsIgnoreCase(entry.getKey())) {
						String xpath = entry.getValue();
						WebElement element = driver.findElement(By.xpath(xpath));
						element.click();
						break;
					}
				}
			}
			
			else if (disciplineNum >13 && disciplineNum <=25) {
				jse.executeScript("window.scrollBy(0,500)");
				Thread.sleep(1000);
				Map<String, String> innerMap = SchoolConstant.getIntermediaryDisciplineDataXPATH();
				for (Map.Entry<String, String> entry : innerMap.entrySet()) {
					if (String.valueOf(disciplineNum).equalsIgnoreCase(entry.getKey())) {
						String xpath = entry.getValue();
						WebElement element = driver.findElement(By.xpath(xpath));
						element.click();
						break;
					}
				}
			} 
			else if(disciplineNum > 25) {
				jse.executeScript("window.scrollBy(0,1000)");
				Thread.sleep(1000);
				Map<String, String> innerMap = SchoolConstant.getIntermediaryDisciplineDataXPATH();
				for (Map.Entry<String, String> entry : innerMap.entrySet()) {
					if (String.valueOf(disciplineNum).equalsIgnoreCase(entry.getKey())) {
						String xpath = entry.getValue();
						WebElement element = driver.findElement(By.xpath(xpath));
						element.click();
						break;
					}
				}
			}
			commonPOM.getNextButtonSt2().click();
			Thread.sleep(1000);
			commonPOM.getUploadFileLink().click();
			
			Runtime.getRuntime().exec(LOMTConstant.INTERMEDIARY_REINGESTION_FILE_PATH);
			Thread.sleep(8000);
			commonPOM.getNextButtonSt2().click();
			jse.executeScript("window.scrollBy(0,-200)");
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.INGESTION_STATUS)));
			Thread.sleep(3000);
			
			if (commonPOM.getWaitForIngestionStatusText().getText().equalsIgnoreCase(LOMTConstant.INGESTION_SUCESSFULL_MESSAGE)) {
				flag = true;
			} else {
				if (useCase != null) {
					assertTrue(commonPOM.getViewIngestFullLogButton().isDisplayed());
					commonPOM.getViewIngestFullLogButton().click();
					if (reingestionValidationCheck()) {
						flag = true;
					} else {
						flag = false;	
					}
				} else {
					flag = false;	
				}
			}
		} catch (Exception e) {
			flag = false;
			return flag;
		}
		return flag;
	}
	
	public boolean verificationReingestionDataUI() {
		boolean flag = false;
		try {
			jse.executeScript("window.scrollBy(0,-500)");
			commonPOM.getPearsonLogo().click();
			commonPOM.getSchoolGlobalLOB().click();
			intermediaryPOM.getIntermediaryStructure().click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			
			intermediaryPOM.getFirstDisGoalframework().click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			
			for (String dis : SchoolConstant.getIntermediaryUpdatedData()) {
				intermediaryPOM.getEnterSearchTerm().sendKeys(dis);
				intermediaryPOM.getUpdateResultButton().click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
				if (intermediaryPOM.getSearchResultText().getText().contains("Showing")) {
					jse.executeScript("window.scrollBy(0,150)");
					intermediaryPOM.getEnterSearchTerm().clear();
					flag = true;
					continue;
				} else {
					flag = false;
				}
			}
			
			for (String dis : SchoolConstant.getIntermediaryDeletedData()) {
				intermediaryPOM.getEnterSearchTerm().sendKeys(dis);
				intermediaryPOM.getUpdateResultButton().click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
				if (intermediaryPOM.getSearchResultText().getText().contains("No results found")) {
					intermediaryPOM.getEnterSearchTerm().clear();
					flag = true;
					continue;
				} else {
					flag = false;
				}
			}
			
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
			return flag;
		}
		jse.executeScript("window.scrollBy(0,-500)");
		commonPOM.getPearsonLogo().click();
		return flag;
	}
	
}
