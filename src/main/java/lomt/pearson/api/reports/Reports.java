package lomt.pearson.api.reports;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import lomt.pearson.common.BaseClass;
import lomt.pearson.common.LoadPropertiesFile;
import lomt.pearson.constant.LOMTConstant;
import lomt.pearson.constant.ReportsConstant;
import lomt.pearson.constant.SchoolConstant;
import lomt.pearson.page_object.CommonPOM;
import lomt.pearson.page_object.ExternalFrameworkPOM;
import lomt.pearson.page_object.HEPom;
import lomt.pearson.page_object.IntermediaryPOM;
import lomt.pearson.page_object.Login;
import lomt.pearson.page_object.NALSPom;
import lomt.pearson.page_object.ProductTocPOM;
import lomt.pearson.page_object.ReportsPOM;
import lomt.pearson.page_object.SGPom;
import lomt.pearson.page_object.SchoolPOM;

public class Reports extends BaseClass {
	
	private String environment = LoadPropertiesFile.getPropertiesValues(LOMTConstant.LOMT_ENVIRONMENT);
	//private String userName = LoadPropertiesFile.getPropertiesValues(LOMTConstant.USER_NAME);
	private String userName = LoadPropertiesFile.getPropertiesValues(LOMTConstant.USER_NAME_TEST);
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
	private ProductTocPOM productTocPOM = null;
	private ReportsPOM reportsPOM = null;
	
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
		productTocPOM = new ProductTocPOM(driver);
		intermediaryPOM = new IntermediaryPOM(driver);
		
		reportsPOM = new ReportsPOM(driver);
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
	
	public String createAndDownloadReport() {
		String reportName = null;
		String curriculumName = null;
		WebDriverWait wait = new WebDriverWait(driver, 120);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		try {
			jse.executeScript("window.scrollBy(0,-1000)");
			commonPOM.getPearsonLogo().click();
			commonPOM.getSchoolGlobalLOB().click();
			schoolPOM.getCurriculumSt().click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			jse.executeScript("window.scrollBy(0,1000)");
			
			// checking load more is disabled or not, expanding 3 level only
			if (!reportsPOM.getCurriculumLoadMoreButton().getAttribute("class").contains("load-more-text disabled")) {
				reportsPOM.getCurriculumLoadMoreButton().click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
				jse.executeScript("window.scrollBy(0,1000)");
				if (!reportsPOM.getCurriculumLoadMoreButton().getAttribute("class").contains("load-more-text disabled")) {
					reportsPOM.getCurriculumLoadMoreButton().click();
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
					jse.executeScript("window.scrollBy(0,1000)");
					if (!reportsPOM.getCurriculumLoadMoreButton().getAttribute("class").contains("load-more-text disabled")) {
						reportsPOM.getCurriculumLoadMoreButton().click();
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
						jse.executeScript("window.scrollBy(0, -3000)");
					} else {
						jse.executeScript("window.scrollBy(0, -3000)");
					}
				} else {
					jse.executeScript("window.scrollBy(0, -2000)");
				}
				
			} else {
				jse.executeScript("window.scrollBy(0, -1000)");
			}
			
			Thread.sleep(1000);
			jse.executeScript("window.scrollBy(0, 400)");
			List<WebElement> webElement = reportsPOM.getCurriculumStandardList();
			if (!webElement.isEmpty()) {
				Iterator<WebElement> itr = webElement.iterator();
				while (itr.hasNext()) {
					WebElement childStructureElement = 	itr.next();
					String curriculumStd = childStructureElement.getText();
					if (curriculumStd.contains("ABXmlImport")){
						int i = curriculumStd.indexOf("\n");
						int j = curriculumStd.indexOf("\n", ++i);
						int l = curriculumStd.lastIndexOf("\n");
						String cuStd = curriculumStd.substring(j, l);
						
						curriculumName = cuStd.substring(0, cuStd.indexOf(" (")).trim();
						break;
					}
				}
			}
			
			//Search curriculum standard and create Report
			if (curriculumName != null) {
				Thread.sleep(1000);
				jse.executeScript("window.scrollBy(0, -500)");
				schoolPOM.getEnterEnterSearch().sendKeys(curriculumName);
				
				schoolPOM.getSchoolUpdateResultButton().click();	
				//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
				Thread.sleep(8000);
				jse.executeScript("window.scrollBy(0, 400)");
				
				Thread.sleep(1000);
				schoolPOM.getAction().click();
				reportsPOM.getSchoolCreateReportLink().click();
				Thread.sleep(10000);
				//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
				
				//Model window is appears, click on next button
				reportsPOM.getSchoolModelWindowNextButton().click();
				Thread.sleep(10000);
				//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
				
				jse.executeScript("window.scrollBy(0, 400)");
				Thread.sleep(1000);
				reportsPOM.getForwardIndirectIntermediaryReport().click();
				Thread.sleep(10000);
				//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
				
				reportsPOM.getFirstIntermediaryPivot().click();
				Thread.sleep(10000);
				//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
				
				reportsPOM.getSchoolModelWindowNextButton().click();
				Thread.sleep(10000);
				//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
				
				reportName = ReportsConstant.FORWARD_INDIRECT_INT_REPORT_FILE_NAME + String.valueOf(1300 + (int)Math.round(Math.random() * (1400 - 1300)));
				
				reportsPOM.getReportName().clear();
				reportsPOM.getReportName().sendKeys(reportName);
				
				jse.executeScript("window.scrollBy(0,500)");
				reportsPOM.getRunReport().click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
				
				Thread.sleep(60000);
				jse.executeScript("window.scrollBy(0,-300)");
				reportsPOM.getEnterSearchTerm().sendKeys(reportName);
				reportsPOM.getUpdateResult().click();
				//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
				Thread.sleep(10000);
				if (reportsPOM.getRepotCountText().getText().contains("Showing")) {
					removeExistingFile();
					
					jse.executeScript("window.scrollBy(0,300)");
					reportsPOM.getReportActionLink().click();
					reportsPOM.getReportExportButton().click();
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
					jse.executeScript("window.scrollBy(0,-500)");
					commonPOM.getPearsonLogo().click();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reportName;
	}
	
	public boolean forwardIndirectIntermediaryReports() {
		boolean flag = false;
		WebDriverWait wait = new WebDriverWait(driver, 120);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		try {
			commonPOM.getSchoolGlobalLOB().click();
			if (reportsPOM.getReportsExportLink().isDisplayed()) {
				reportsPOM.getReportsExportLink().click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
				if (reportsPOM.getRepotCountText().getText().contains("Showing")) {
					
					reportsPOM.getEnterSearchTerm().sendKeys(ReportsConstant.INDIRECT_TEXT);
					reportsPOM.getUpdateResult().click();
					//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
					Thread.sleep(10000);
					if (reportsPOM.getRepotCountText().getText().contains("Showing")) {
						removeExistingFile();
						
						jse.executeScript("window.scrollBy(0,300)");
						reportsPOM.getReportActionLink().click();
						reportsPOM.getReportExportButton().click();
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
						jse.executeScript("window.scrollBy(0,-500)");
						commonPOM.getPearsonLogo().click();
						flag = true;
					}
				} else {
					//create new reports Forward Indirect Intermediary Report
					createAndDownloadReport();
					flag = true;
				}
				
			} else {
				flag = false;
				return flag;
			}
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
			return flag;
		}
		return flag;
	}
public void searchAndExportForwardIndirectIntermediaryReport() {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 120);
		try {
			reportsPOM.getEnterSearchTerm().sendKeys(ReportsConstant.INDIRECT_TEXT);
			reportsPOM.getUpdateResult().click();
			//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			Thread.sleep(8000);
			if (reportsPOM.getRepotCountText().getText().contains("Showing")) {
				jse.executeScript("window.scrollBy(0,250)");
				reportsPOM.getReportActionLink().click();
				reportsPOM.getReportExportButton().click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
				
				//Read the file and verify static text 
				//preparing map for curriculum standard
				//preparing map for Intermediary
				//for alignment as well so that data can be compare on UI.
			} else {
				//Create Forward Indirect Intermediary new Report
				createAndDownloadReport();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Map<String, List<String>> verifiedForwardIndirectIntermediaryReportsExportedFile() {
		Map<String, List<String>> forwardIIRepMap = new LinkedHashMap<String, List<String>>();
		InputStream inputStream = null;
		XSSFWorkbook workbook = null;
		XSSFSheet worksheet = null;
		try {
			String fileName = getFileFromDirectory(LOMTConstant.EXPORTED_FILE_PATH);
			File forwardIIReportExpFile = new File(LOMTConstant.EXPORTED_FILE_PATH + fileName);
			
			inputStream =  new FileInputStream(forwardIIReportExpFile);
			workbook = new XSSFWorkbook(inputStream);
			worksheet = workbook.getSheetAt(0);
			boolean headerFlag = verifyForwardIIReportHeaders(worksheet);
			if(headerFlag) {
				getCurriculumStandardAndIntermediaryDataFromExportedSheet(worksheet, forwardIIRepMap);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return forwardIIRepMap;
	}
	
	public Map<String, List<String>> verifyProductToCIntermediaryReport() {
		Map<String, List<String>> productTIRepMap = new LinkedHashMap<String, List<String>>();
		InputStream inputStream = null;
		XSSFWorkbook workbook = null;
		XSSFSheet worksheet = null;
		try {
			String fileName = getFileFromDirectory(LOMTConstant.EXPORTED_FILE_PATH);
			File productTIReportExpFile = new File(LOMTConstant.EXPORTED_FILE_PATH + fileName);
			
			inputStream =  new FileInputStream(productTIReportExpFile);
			workbook = new XSSFWorkbook(inputStream);
			worksheet = workbook.getSheetAt(0);
			boolean headerFlag = verifyProductToCIntermediaryReportHeaders(worksheet);
			if(headerFlag) {
				getProductAndIntermediaryDataFromExportedSheet(worksheet,productTIRepMap);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productTIRepMap;
	}
	
	
	public boolean verifyForwardIIReportHeaders(XSSFSheet worksheet) {
		boolean flag = false;
		try {
			assertEquals(worksheet.getRow(LOMTConstant.ZERO).getCell(LOMTConstant.ZERO).getStringCellValue(), ReportsConstant.TITLE);
			assertNotNull(worksheet.getRow(LOMTConstant.ZERO).getCell(LOMTConstant.ONE).getStringCellValue());
			
			assertEquals(worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.ZERO).getStringCellValue().trim(), ReportsConstant.DATE_TIME_GENERATION.trim());
			assertTrue(isValidFormat(worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.ZERO).getStringCellValue().trim()));
				
			assertEquals(worksheet.getRow(LOMTConstant.TWO).getCell(LOMTConstant.ZERO).getStringCellValue().trim(), ReportsConstant.USER.trim());
			assertTrue(worksheet.getRow(LOMTConstant.TWO).getCell(LOMTConstant.ONE).getStringCellValue().equalsIgnoreCase(ReportsConstant.ADMIN_USER_COMMON)
					|| worksheet.getRow(LOMTConstant.TWO).getCell(LOMTConstant.ONE).getStringCellValue().equalsIgnoreCase(ReportsConstant.ADMIN_USER_PPE)
					|| worksheet.getRow(LOMTConstant.TWO).getCell(LOMTConstant.ONE).getStringCellValue().equalsIgnoreCase(ReportsConstant.LEARNING_USER_PPE)
					|| worksheet.getRow(LOMTConstant.TWO).getCell(LOMTConstant.ONE).getStringCellValue().equalsIgnoreCase(ReportsConstant.SME_USER)
					|| worksheet.getRow(LOMTConstant.TWO).getCell(LOMTConstant.ONE).getStringCellValue().equalsIgnoreCase(ReportsConstant.EDITOR_USER) );
			
			assertEquals(worksheet.getRow(LOMTConstant.FOUR).getCell(LOMTConstant.ZERO).getStringCellValue().trim(), ReportsConstant.STANDARD);
			
			assertEquals(worksheet.getRow(LOMTConstant.FOUR).getCell(LOMTConstant.EIGHT).getStringCellValue().trim(), ReportsConstant.INTERMEDIARY);
			
			assertEquals(worksheet.getRow(LOMTConstant.FIVE).getCell(LOMTConstant.ZERO).getStringCellValue().trim(), ReportsConstant.TITLE);
			assertNotNull(worksheet.getRow(LOMTConstant.FIVE).getCell(LOMTConstant.ONE).getStringCellValue());
			
			assertEquals(worksheet.getRow(LOMTConstant.FIVE).getCell(LOMTConstant.EIGHT).getStringCellValue().trim(), ReportsConstant.DISCIPLINE);
			assertNotNull(worksheet.getRow(LOMTConstant.FIVE).getCell(LOMTConstant.NINE).getStringCellValue());
			
			assertEquals(worksheet.getRow(LOMTConstant.SIX).getCell(LOMTConstant.ZERO).getStringCellValue(), ReportsConstant.COUNTRY);
			assertNotNull(worksheet.getRow(LOMTConstant.SIX).getCell(LOMTConstant.ONE).getStringCellValue());
			
			assertEquals(worksheet.getRow(LOMTConstant.SEVEN).getCell(LOMTConstant.ZERO).getStringCellValue(), ReportsConstant.GRADE);
			assertNotNull(worksheet.getRow(LOMTConstant.SEVEN).getCell(LOMTConstant.ONE).getStringCellValue());
			
			assertEquals(worksheet.getRow(LOMTConstant.EIGHT).getCell(LOMTConstant.ZERO).getStringCellValue(), ReportsConstant.DISCIPLINE);
			assertNotNull(worksheet.getRow(LOMTConstant.EIGHT).getCell(LOMTConstant.ONE).getStringCellValue());
			
			assertEquals(worksheet.getRow(LOMTConstant.TEN).getCell(LOMTConstant.ZERO).getStringCellValue(), ReportsConstant.STANDARDS_STRANDS);
			assertEquals(worksheet.getRow(LOMTConstant.TEN).getCell(LOMTConstant.ONE).getStringCellValue(), ReportsConstant.STANDARDS_TOPICS);
			assertEquals(worksheet.getRow(LOMTConstant.TEN).getCell(LOMTConstant.TWO).getStringCellValue(), ReportsConstant.STANDARDS_NUMBER);
			assertEquals(worksheet.getRow(LOMTConstant.TEN).getCell(LOMTConstant.THREE).getStringCellValue(), ReportsConstant.PARENT_CODE);
			assertEquals(worksheet.getRow(LOMTConstant.TEN).getCell(LOMTConstant.FOUR).getStringCellValue(), ReportsConstant.GRADE);
			assertEquals(worksheet.getRow(LOMTConstant.TEN).getCell(LOMTConstant.FIVE).getStringCellValue(), ReportsConstant.AB_GUIDE);
			assertEquals(worksheet.getRow(LOMTConstant.TEN).getCell(LOMTConstant.SIX).getStringCellValue(), ReportsConstant.SYSTEM_UNIQUE_ID);
			assertEquals(worksheet.getRow(LOMTConstant.TEN).getCell(LOMTConstant.EIGHT).getStringCellValue(), ReportsConstant.INTERMEDIARY_URN);
			assertEquals(worksheet.getRow(LOMTConstant.TEN).getCell(LOMTConstant.NINE).getStringCellValue(), ReportsConstant.SUBJECT);
			assertEquals(worksheet.getRow(LOMTConstant.TEN).getCell(LOMTConstant.TEN).getStringCellValue(), ReportsConstant.CODE);
			assertEquals(worksheet.getRow(LOMTConstant.TEN).getCell(LOMTConstant.ELEVENTH).getStringCellValue(), ReportsConstant.DESCRIPTION);
			assertEquals(worksheet.getRow(LOMTConstant.TEN).getCell(LOMTConstant.TWELEVE).getStringCellValue(), ReportsConstant.SPANISH_DESCRIPTION);
			flag = true;
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
			return flag;
		}
		return flag;
	}
	
	public boolean verifyProductToCIntermediaryReportHeaders(XSSFSheet worksheet) {
		boolean flag = false;
		try {
			assertEquals(worksheet.getRow(LOMTConstant.ZERO).getCell(LOMTConstant.ZERO).getStringCellValue(), ReportsConstant.TITLE);
			assertNotNull(worksheet.getRow(LOMTConstant.ZERO).getCell(LOMTConstant.ONE).getStringCellValue());
			
			assertEquals(worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.ZERO).getStringCellValue().trim(), ReportsConstant.USER.trim());
			assertTrue(worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.ONE).getStringCellValue().equalsIgnoreCase(ReportsConstant.ADMIN_USER_COMMON)
					|| worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.ONE).getStringCellValue().equalsIgnoreCase(ReportsConstant.ADMIN_USER_PPE)
					|| worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.ONE).getStringCellValue().equalsIgnoreCase(ReportsConstant.LEARNING_USER_PPE)
					|| worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.ONE).getStringCellValue().equalsIgnoreCase(ReportsConstant.SME_USER)
					|| worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.ONE).getStringCellValue().equalsIgnoreCase(ReportsConstant.EDITOR_USER) );
			
			//assertEquals(worksheet.getRow(LOMTConstant.TWO).getCell(LOMTConstant.ZERO).getStringCellValue().trim(), ReportsConstant.DATE_TIME_GENERATION.trim());
			assertTrue(isValidFormat(worksheet.getRow(LOMTConstant.TWO).getCell(LOMTConstant.ONE).getStringCellValue().trim()));
				
			assertEquals(worksheet.getRow(LOMTConstant.THREE).getCell(LOMTConstant.ZERO).getStringCellValue().trim(), ReportsConstant.ALIGNMENTS);
			assertTrue(worksheet.getRow(LOMTConstant.THREE).getCell(LOMTConstant.ONE).getStringCellValue().equalsIgnoreCase(ReportsConstant.CENTRAL_PERIPHERAL)
					|| worksheet.getRow(LOMTConstant.THREE).getCell(LOMTConstant.ONE).getStringCellValue().equalsIgnoreCase(ReportsConstant.CENTRAL)
					|| worksheet.getRow(LOMTConstant.THREE).getCell(LOMTConstant.ONE).getStringCellValue().equalsIgnoreCase(ReportsConstant.PERIPHERAL)	);
			
			assertEquals(worksheet.getRow(LOMTConstant.FIVE).getCell(LOMTConstant.ZERO).getStringCellValue().trim(), ReportsConstant.CONTENT);
			assertEquals(worksheet.getRow(LOMTConstant.FIVE).getCell(LOMTConstant.SEVEN).getStringCellValue().trim(), ReportsConstant.INTERMEDIARY);
			
			assertEquals(worksheet.getRow(LOMTConstant.SIX).getCell(LOMTConstant.ZERO).getStringCellValue().trim(), ReportsConstant.PROGRAM);
			assertEquals(worksheet.getRow(LOMTConstant.SIX).getCell(LOMTConstant.SEVEN).getStringCellValue().trim(), ReportsConstant.DISCIPLINE);
			assertNotNull(worksheet.getRow(LOMTConstant.SIX).getCell(LOMTConstant.EIGHT).getStringCellValue());
			
			assertEquals(worksheet.getRow(LOMTConstant.SEVEN).getCell(LOMTConstant.ZERO).getStringCellValue(), ReportsConstant.COURSE);
			assertNotNull(worksheet.getRow(LOMTConstant.SEVEN).getCell(LOMTConstant.ONE).getStringCellValue());
			
			assertEquals(worksheet.getRow(LOMTConstant.EIGHT).getCell(LOMTConstant.ZERO).getStringCellValue(), ReportsConstant.PRODUCT);
			assertNotNull(worksheet.getRow(LOMTConstant.EIGHT).getCell(LOMTConstant.ONE).getStringCellValue());
			
			assertEquals(worksheet.getRow(LOMTConstant.NINE).getCell(LOMTConstant.ZERO).getStringCellValue(), ReportsConstant.GEOGRAPHIC_AREA_OR__COUNTRY);
			//assertNotNull(worksheet.getRow(LOMTConstant.NINE).getCell(LOMTConstant.ONE).getStringCellValue());
			
			assertEquals(worksheet.getRow(LOMTConstant.TEN).getCell(LOMTConstant.ZERO).getStringCellValue(), ReportsConstant.STATE_OR_REGION);
			assertNotNull(worksheet.getRow(LOMTConstant.TEN).getCell(LOMTConstant.ONE).getStringCellValue());
			
			assertEquals(worksheet.getRow(LOMTConstant.ELEVENTH).getCell(LOMTConstant.ZERO).getStringCellValue(), ReportsConstant.START_GRADE);
			assertNotNull(worksheet.getRow(LOMTConstant.ELEVENTH).getCell(LOMTConstant.ONE).getStringCellValue());
			
			assertEquals(worksheet.getRow(LOMTConstant.TWELEVE).getCell(LOMTConstant.ZERO).getStringCellValue(), ReportsConstant.END_GRADE);
			assertNotNull(worksheet.getRow(LOMTConstant.TWELEVE).getCell(LOMTConstant.ONE).getStringCellValue());
			
			assertEquals(worksheet.getRow(LOMTConstant.THIRTEEN).getCell(LOMTConstant.ZERO).getStringCellValue(), ReportsConstant.ISBN10);
			assertNotNull(worksheet.getRow(LOMTConstant.THIRTEEN).getCell(LOMTConstant.ONE).getStringCellValue());
			
			assertEquals(worksheet.getRow(LOMTConstant.FOURTEEN).getCell(LOMTConstant.ZERO).getStringCellValue(), ReportsConstant.ISBN13);
			assertNotNull(worksheet.getRow(LOMTConstant.FOURTEEN).getCell(LOMTConstant.ONE).getStringCellValue());
			
			assertEquals(worksheet.getRow(LOMTConstant.FIFTEEN).getCell(LOMTConstant.ZERO).getStringCellValue(), ReportsConstant.TYPE);
			assertNotNull(worksheet.getRow(LOMTConstant.FIFTEEN).getCell(LOMTConstant.ONE).getStringCellValue());			
			
			assertEquals(worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.ZERO).getStringCellValue(), ReportsConstant.URN);
			assertEquals(worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.ONE).getStringCellValue(), ReportsConstant.ALFRESCO_OBJECT_ID);
			assertEquals(worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.TWO).getStringCellValue(), ReportsConstant.COMPONENT_TOC);
			assertEquals(worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.THREE).getStringCellValue(), ReportsConstant.START_PAGE);
			assertEquals(worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.FOUR).getStringCellValue(), ReportsConstant.END_PAGE);
			assertEquals(worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.FIVE).getStringCellValue(), ReportsConstant.PERIPHERAL_ALIGNMENTS);
			//System.out.println(worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.SIX).getStringCellValue());
			assertTrue(worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.SIX)==null);
			assertEquals(worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.SEVEN).getStringCellValue(), ReportsConstant.INTERMEDIARY_URN);
			assertEquals(worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.EIGHT).getStringCellValue(), ReportsConstant.SUBJECT);
			assertEquals(worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.NINE).getStringCellValue(), ReportsConstant.CODE);
			assertEquals(worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.TEN).getStringCellValue(), ReportsConstant.DESCRIPTION);
			assertEquals(worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.ELEVENTH).getStringCellValue(), ReportsConstant.SPANISH_DESCRIPTION);
			flag = true;
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
			return flag;
		}
		return flag;
	}
	
	public Map<String, List<String>> getCurriculumStandardAndIntermediaryDataFromExportedSheet(XSSFSheet worksheet, Map<String, List<String>> forwardIIRepMap) {
		try {
			int counter = 0;
			List<String> curriculumStdList = new LinkedList<String>();
			List<String> disciplineList = new LinkedList<String>();
			
			Iterator<Row> rowIterator = worksheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				if (row.getRowNum() == 5) {
					//adding Curriculum Standard and Intermediary goalframework name for searching purpose on UI
					curriculumStdList.add(row.getCell(1).getStringCellValue());
					disciplineList.add(row.getCell(9).getStringCellValue());
				}
				
				if (row.getRowNum() > 10 && counter<=10) {
					curriculumStdList.add(row.getCell(1).getStringCellValue());
					disciplineList.add(row.getCell(11).getStringCellValue());
					counter++;
				} else {
					forwardIIRepMap.put("CurriculumStandard", curriculumStdList);
					forwardIIRepMap.put("Discipline", disciplineList);
					return forwardIIRepMap;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return forwardIIRepMap;
	}
	
	public Map<String, List<String>> getProductAndIntermediaryDataFromExportedSheet(XSSFSheet worksheet, Map<String, List<String>> productTIRepMap) {
		try {
			int counter = 0;
			List<String> productList = new LinkedList<String>();
			List<String> disciplineList = new LinkedList<String>();
			
			Iterator<Row> rowIterator = worksheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				if (row.getRowNum() == 6) {
					//adding Product and Intermediary goal framework name for searching purpose on UI
					disciplineList.add(row.getCell(8).getStringCellValue());
				}
				if (row.getRowNum() == 8) {
					productList.add(row.getCell(1).getStringCellValue());
				}
				
				if (row.getRowNum() > 17 && counter<=10) {
					if (row.getCell(2) != null && row.getCell(2).getStringCellValue() !=null){
						productList.add(row.getCell(2).getStringCellValue());
					}
					if (row.getCell(11) != null && row.getCell(11).getStringCellValue() !=null){
						disciplineList.add(row.getCell(11).getStringCellValue());
						//function(disciplineList, 11);
					}
					counter++;
				}
			}
			
			/*function(list, cellnumber) {
				list.add(row.getCell(cellnumber).getStringCellValue());
			}*/
			//else {
				productTIRepMap.put("Product", productList);
				productTIRepMap.put("Discipline", disciplineList);
				return productTIRepMap;
			//}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productTIRepMap;
	}
	
	public boolean verifyCurriculumStandardDataUI(Map<String, List<String>> forwardIIRepMap) {
		boolean flag = false;
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 120);
		List<String> csList = null;
		try {
			commonPOM.getSchoolGlobalLOB().click();
			schoolPOM.getCurriculumSt().click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			jse.executeScript("window.scrollBy(0,200)");
			
			for(String csKey : forwardIIRepMap.keySet()) {
				csList = forwardIIRepMap.get(csKey);
				break;
			}
			if (!csList.isEmpty()) {
				//goalframework
				for (String csTopics : csList) {
					schoolPOM.getEnterEnterSearch().sendKeys(csTopics);
					schoolPOM.getSchoolUpdateResultButton().click();
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
					jse.executeScript("window.scrollBy(0,300)");
					if(schoolPOM.getResultFound().getText().contains("Showing")) {
						schoolPOM.getCurriculumGoalFramework().click();
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
						jse.executeScript("window.scrollBy(0,300)");
						break;
					}
				}
				//verifying topics
				for (String csTopics : csList) {
					schoolPOM.getInnerEnterSearch().sendKeys(csTopics);
					schoolPOM.getSchoolInnerUpdateResultButton().click();
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
					
					List<WebElement> webElement  =  schoolPOM.getParentChildList();
					if (!webElement.isEmpty()) {
						Iterator<WebElement> itr = webElement.iterator();
						while (itr.hasNext()) {
							WebElement childStructureElement = 	itr.next();
							String structureName = childStructureElement.getText();
							if (structureName.contains(csTopics)) {
								flag = true;
								continue;
							} else {
								flag = false;
								//logger.log(LogStatus.FAIL, "Curriculum Standard Re-ingestion : Parent/Child description does not match, "+"Level - "+pcCounter);
							}
						}
						schoolPOM.getInnerEnterSearch().clear();
						//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
						continue;
					} else {
						flag = false;
						/*logger.log(LogStatus.FAIL, "Curriculum Standard Re-ingestion : Parent/Child description does not filtered using Enter serach term option"
								+ "so data verification is failed");*/
					}
					
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public boolean verifyProductDataUI(Map<String, List<String>> productTIRepMap) {
		boolean flag = false;
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 120);
		List<String> prList = null;
		try {
			commonPOM.getSchoolGlobalLOB().click();
			hePom.getProductLink().click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			jse.executeScript("window.scrollBy(0,200)");
			
			for(String prKey : productTIRepMap.keySet()) {
				prList = productTIRepMap.get(prKey);
				break;
			}
			if (!prList.isEmpty()) {
				//goalframework
				for (String prTopics : prList) {
					productTocPOM.getEnterSearchTerm().sendKeys(prTopics);
					productTocPOM.getUpdateResultButton().click();
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
					jse.executeScript("window.scrollBy(0,300)");
					if(schoolPOM.getResultFound().getText().contains("Showing")) {
						schoolPOM.getCurriculumGoalFramework().click();
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
						jse.executeScript("window.scrollBy(0,300)");
						break;
					}
				}
				//verifying topics
				for (String prTopics : prList) {
					productTocPOM.getInnerEnterSearchTerm().sendKeys(prTopics);
					productTocPOM.getProductInnerUpdateResultButton().click();
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
					jse.executeScript("window.scrollBy(0,300)");
					List<WebElement> webElement  =  schoolPOM.getParentChildList();
					if (!webElement.isEmpty()) {
						Iterator<WebElement> itr = webElement.iterator();
						while (itr.hasNext()) {
							WebElement childStructureElement = 	itr.next();
							String structureName = childStructureElement.getText();
							if (structureName.contains(prTopics)) {
								flag = true;
								continue;
							} else {
								flag = false;
								//logger.log(LogStatus.FAIL, "Curriculum Standard Re-ingestion : Parent/Child description does not match, "+"Level - "+pcCounter);
							}
						}
						productTocPOM.getInnerEnterSearchTerm().clear();
						//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
						continue;
					} else {
						flag = false;
						/*logger.log(LogStatus.FAIL, "Curriculum Standard Re-ingestion : Parent/Child description does not filtered using Enter serach term option"
								+ "so data verification is failed");*/
					}
					
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	public boolean verifyIntermediaryDataUI(Map<String, List<String>> productTIRepMap) {
		boolean flag = false;
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			WebDriverWait wait = new WebDriverWait(driver, 600);
			List <String> disList = null;
			try {
				jse.executeScript("window.scrollBy(0,-500)");
				commonPOM.getPearsonLogo().click();
				commonPOM.getSchoolGlobalLOB().click();
				for(String disKey : productTIRepMap.keySet()) {
					disList = productTIRepMap.get(disKey);
					break;
				}
				if(!disList.isEmpty()){
					if (!intermediaryPOM.getInnerLoadMoreButton().getAttribute("class").contains("load-more-text disabled")) {
						intermediaryPOM.getInnerLoadMoreButton().click();
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
						jse.executeScript("window.scrollBy(0,1000)");
					}
					for(String dis : disList){
						this.searchIntermediaryDiscipline(dis);
						break;
					}
						
				for (String dis : disList) {
					
					intermediaryPOM.getEnterSearchTerm().sendKeys(dis);
					intermediaryPOM.getUpdateResultButton().click();
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
					if (intermediaryPOM.getSearchResultText().getText().contains("Showing")) {
						jse.executeScript("window.scrollBy(0,200)");
						intermediaryPOM.getEnterSearchTerm().clear();
						flag = true;
						continue;
					} else {
						flag = false;
					}
				}
				
				
				}} catch (Exception e) {
				flag = false;
				e.printStackTrace();
				return flag;
			}
			jse.executeScript("window.scrollBy(0,-500)");
			commonPOM.getPearsonLogo().click();
			return flag;
			
		} catch (Exception e) {
			e.printStackTrace();
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
	
	public static boolean isValidFormat(String value) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            date = sdf.parse(value);
            System.out.println(sdf.format(date));
            if (!value.replace(" ", "").equalsIgnoreCase(sdf.format(date).replace(" ", ""))) {
                date = null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return date != null;
    }
	
	public void closeDriverInstance() {
		driver.close();
	}
	
	/*public static void main(String [] args) throws ParseException {
		//System.out.println("isValid - dd/MM/yyyy with 25/09/2013 12:13:50 = " + isValidFormat("DD/MM/YYYY HH:MM:SS", "25/09/2013  12:13:50"));
		
		SimpleDateFormat desiredFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = desiredFormat.parse("25/09/2013  12:13:50");
		//System.out.println(desiredFormat.format(date));
		System.out.println(isValidFormat("25/09/2013  12:13:50"));
	}*/
	
	public String createAndDownloadReportProductToCInt() {
		String reportName = null;
		WebDriverWait wait = new WebDriverWait(driver, 120);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		try {
			jse.executeScript("window.scrollBy(0,-1000)");			
			// Search the ingested product and create report
			commonPOM.getSchoolGlobalLOB().click();
			hePom.getProductLink().click();
			//Thread.sleep(4000);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			productTocPOM.getEnterSearchTerm().sendKeys(ReportsConstant.INGESTED_PRODUCT);
			productTocPOM.getUpdateResultButton().click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			
			jse.executeScript("window.scrollBy(0,400)");
			
			Thread.sleep(1000);
			schoolPOM.getAction().click();
			reportsPOM.getSchoolCreateReportLink().click();
			Thread.sleep(1000);
			reportsPOM.getSchoolModelWindowNextButton().click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			reportsPOM.getProductToCIntermediaryReport().click();
			Thread.sleep(3000);
			//pivot select and click
			clickIngestedIntermediaryDiscipline();	
			reportsPOM.getSchoolModelWindowNextButton().click();
			Thread.sleep(2000);
			
			reportName = ReportsConstant.PRODUCT_TOC_INT_REPORT_FILE_NAME + String.valueOf(1300 + (int)Math.round(Math.random() * (1400 - 1300)));
			
			reportsPOM.getReportName().clear();
			reportsPOM.getReportName().sendKeys(reportName);
			
			jse.executeScript("window.scrollBy(0,500)");
			reportsPOM.getRunReport().click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			
			Thread.sleep(1000);
			reportsPOM.getUpdateResult().click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			jse.executeScript("window.scrollBy(0,-300)");
			reportsPOM.getEnterSearchTerm().sendKeys(reportName);
			reportsPOM.getUpdateResult().click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			Thread.sleep(2000);
			if (reportsPOM.getRepotCountText().getText().contains("Showing")) {
				removeExistingFile();
				
				jse.executeScript("window.scrollBy(0,300)");
				reportsPOM.getReportActionLink().click();
				reportsPOM.getReportExportButton().click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
				jse.executeScript("window.scrollBy(0,-500)");
				commonPOM.getPearsonLogo().click();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reportName;
	}
	
	public void clickIngestedIntermediaryDiscipline() {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 600);
		try {
				jse.executeScript("window.scrollBy(0,600)");
				//check that all the intermediaries are displayed
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
				int counter = 0;
				Iterator<WebElement> itr = webElement.iterator();
				while (itr.hasNext()){
				WebElement childStructureElement = 	itr.next();
				String discipline = childStructureElement.getText();
				int i = discipline.indexOf("\n");
				int j = discipline.lastIndexOf("\n");
				counter++;
					if(ReportsConstant.INGESTED_INTERMEDIARY.equalsIgnoreCase(discipline.substring(i, j).trim())){
					String xpath = "//div[@id='report-target-container']/div[2]/div/div[1]/div[2]/div["+counter+"]/div/div[2]/div/span[1]";
						if (counter <=7) {
							WebElement element = driver.findElement(By.xpath(xpath));
							System.out.println("Selecting Intermediary : "+discipline.substring(i, j).trim());
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
					}
				}
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
		public void searchIntermediaryDiscipline(String disName) {
			//List<String> intermediaryList = new LinkedList<String>();
			
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			WebDriverWait wait = new WebDriverWait(driver, 600);
			try {
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
							}
						}
					}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
}
