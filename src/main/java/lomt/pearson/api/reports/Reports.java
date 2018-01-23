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
	// private String userName =
	// LoadPropertiesFile.getPropertiesValues(LOMTConstant.USER_NAME);
	private String userName = LoadPropertiesFile.getPropertiesValues(LOMTConstant.USER_NAME_TEST);
	private String pwd = LoadPropertiesFile.getPropertiesValues(LOMTConstant.PASSWORD);

	private String learningUser = LoadPropertiesFile.getPropertiesValues(LOMTConstant.USER_NAME_LEARNING_USER);
	private String learningUserPwd = LoadPropertiesFile.getPropertiesValues(LOMTConstant.PASSWORD_LEARNING_USER_PWD);

	private String learningSME = LoadPropertiesFile.getPropertiesValues(LOMTConstant.USER_NAME_LEARNING_SME);
	private String learningSMEPwd = LoadPropertiesFile.getPropertiesValues(LOMTConstant.PASSWORD_LEARNING_SME_PWD);

	private String learningEditor = LoadPropertiesFile.getPropertiesValues(LOMTConstant.USER_NAME_LEARNING_EDITOR);
	private String learningEditorPwd = LoadPropertiesFile.getPropertiesValues(LOMTConstant.PASSWORD_LEARNING_EDITOR);
	
	private String reportType = null;

	private WebDriver driver;

	private Login login = null;
	private CommonPOM commonPOM = null;
	private HEPom hePom = null;
	private NALSPom nalsPom = null;
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
				if (!reportsPOM.getCurriculumLoadMoreButton().getAttribute("class")
						.contains("load-more-text disabled")) {
					reportsPOM.getCurriculumLoadMoreButton().click();
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
					jse.executeScript("window.scrollBy(0,1000)");
					if (!reportsPOM.getCurriculumLoadMoreButton().getAttribute("class")
							.contains("load-more-text disabled")) {
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
					WebElement childStructureElement = itr.next();
					String curriculumStd = childStructureElement.getText();
					if (curriculumStd.contains("ABXmlImport")) {
						int i = curriculumStd.indexOf("\n");
						int j = curriculumStd.indexOf("\n", ++i);
						int l = curriculumStd.lastIndexOf("\n");
						String cuStd = curriculumStd.substring(j, l);

						curriculumName = cuStd.substring(0, cuStd.indexOf(" (")).trim();
						break;
					}
				}
			}

			// Search curriculum standard and create Report
			if (curriculumName != null) {
				Thread.sleep(1000);
				jse.executeScript("window.scrollBy(0, -500)");
				schoolPOM.getEnterEnterSearch().sendKeys(curriculumName);

				schoolPOM.getSchoolUpdateResultButton().click();
				// wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
				Thread.sleep(8000);
				jse.executeScript("window.scrollBy(0, 400)");

				Thread.sleep(1000);
				schoolPOM.getAction().click();
				reportsPOM.getSchoolCreateReportLink().click();
				Thread.sleep(10000);
				// wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));

				// Model window is appears, click on next button
				reportsPOM.getSchoolModelWindowNextButton().click();
				Thread.sleep(10000);
				// wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));

				jse.executeScript("window.scrollBy(0, 400)");
				Thread.sleep(1000);
				reportsPOM.getForwardIndirectIntermediaryReport().click();
				Thread.sleep(10000);
				// wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));

				reportsPOM.getFirstIntermediaryPivot().click();
				Thread.sleep(10000);
				// wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));

				reportsPOM.getSchoolModelWindowNextButton().click();
				Thread.sleep(10000);
				// wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));

				reportName = ReportsConstant.FORWARD_INDIRECT_INT_REPORT_FILE_NAME
						+ String.valueOf(1300 + (int) Math.round(Math.random() * (1400 - 1300)));

				reportsPOM.getReportName().clear();
				reportsPOM.getReportName().sendKeys(reportName);

				jse.executeScript("window.scrollBy(0,500)");
				reportsPOM.getRunReport().click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));

				Thread.sleep(60000);
				jse.executeScript("window.scrollBy(0,-300)");
				reportsPOM.getEnterSearchTerm().sendKeys(reportName);
				reportsPOM.getUpdateResult().click();
				// wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
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
					// wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
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
					// create new reports Forward Indirect Intermediary Report
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
			// wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			Thread.sleep(8000);
			if (reportsPOM.getRepotCountText().getText().contains("Showing")) {
				jse.executeScript("window.scrollBy(0,250)");
				reportsPOM.getReportActionLink().click();
				reportsPOM.getReportExportButton().click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));

				// Read the file and verify static text
				// preparing map for curriculum standard
				// preparing map for Intermediary
				// for alignment as well so that data can be compare on UI.
			} else {
				// Create Forward Indirect Intermediary new Report
				createAndDownloadReport();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Map<String, List<String>> verifyProductToCIntermediaryReport() {
		Map<String, List<String>> productTIRepMap = new LinkedHashMap<String, List<String>>();
		InputStream inputStream = null;
		XSSFWorkbook workbook = null;
		XSSFSheet worksheet = null;
		try {
			String fileName = getFileFromDirectory(LOMTConstant.EXPORTED_FILE_PATH);
			File productTIReportExpFile = new File(LOMTConstant.EXPORTED_FILE_PATH + fileName);

			inputStream = new FileInputStream(productTIReportExpFile);
			workbook = new XSSFWorkbook(inputStream);
			worksheet = workbook.getSheetAt(0);
			boolean headerFlag = verifyProductToCIntermediaryReportHeaders(worksheet);
			if (headerFlag) {
				getProductAndIntermediaryDataFromExportedSheet(worksheet, productTIRepMap);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return productTIRepMap;
	}

	public Map<String, List<String>> verifyReport(String reportName, ExtentTest logger) {
		Map<String, List<String>> productCSRepMap = new LinkedHashMap<String, List<String>>();
		InputStream inputStream = null;
		XSSFWorkbook workbook = null;
		XSSFSheet worksheet = null;
		try {
			String fileName = getFileFromDirectory(LOMTConstant.EXPORTED_FILE_PATH);
			File productTIReportExpFile = new File(LOMTConstant.EXPORTED_FILE_PATH + fileName);

			inputStream = new FileInputStream(productTIReportExpFile);
			workbook = new XSSFWorkbook(inputStream);
			worksheet = workbook.getSheetAt(0);
			if(reportName.contains(ReportsConstant.REVERSE_SHARED_INT_TEXT)){
				reportType = ReportsConstant.REVERSE_SHARED_INT_TEXT;
				verifyReverseSharedIntermediaryReportHeaders(worksheet, logger, reportName);
			}else if(reportName.contains(ReportsConstant.REVERSE_DIRECT_TEXT)){
				reportType = ReportsConstant.REVERSE_DIRECT_TEXT;
				verifyReverseDirectReportHeaders(worksheet, logger, reportName);
			}
			
			getProductAndCurriculumDataFromExportedSheet(worksheet, productCSRepMap,logger,reportType);
			// Verify the Correlation, met, unmet and Strength for Report
			if(reportType.equalsIgnoreCase( ReportsConstant.REVERSE_SHARED_INT_TEXT)){
			verifyScoresAndStrength(productCSRepMap, logger);
			}else{
				verifyScoresAndStrengthReverseDirectReport(productCSRepMap, logger);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productCSRepMap;
	}

	public Map<String, List<String>> verifyScoresAndStrength(Map<String, List<String>> productCSRepMap,
			ExtentTest logger) {
		try {
			List<String> correlationList = productCSRepMap.get("Correlation_Product");
			List<String> metList = productCSRepMap.get("Met_Statement");
			List<String> strengthList = productCSRepMap.get("Strength_Product");
			List<String> unmetList = productCSRepMap.get("Unmet_Statement");
			if (correlationList.equals(ReportsConstant.Corr_Product)) {
			} else {
				logger.log(LogStatus.FAIL, "Reverse Shared Intermediary Report: Correlation scores mismatch");
			}
			if (metList.equals(ReportsConstant.met_List)) {
			} else {
				logger.log(LogStatus.FAIL,
						"Reverse Shared Intermediary Report: Met statements are not as expected in the exported report");
			}
			if (strengthList.equals(ReportsConstant.strength_List)) {
			} else {
				logger.log(LogStatus.FAIL,
						"Reverse Shared Intermediary Report: Strength values not as expected in the exported report");
			}
			if (unmetList.equals(ReportsConstant.unmet_List)) {
			} else {
				logger.log(LogStatus.FAIL,
						"Reverse Shared Intermediary Report: Met statements are not as expected in the exported report");
			}
		} catch (Exception e) {
			logger.log(LogStatus.FAIL,
					"Reverse Shared Intermediary Report: Error occured while veryfying Score and statements");
		}
		return productCSRepMap;
	}
	
	public Map<String, List<String>> verifyScoresAndStrengthReverseDirectReport(Map<String, List<String>> productCSRepMap,
			ExtentTest logger) {
		try {
			List<String> correlationList = productCSRepMap.get("Correlation_Product");
			List<String> strengthList = productCSRepMap.get("Strength_Product");
			if (correlationList.equals(ReportsConstant.Corr_Product)) {
			} else {
				logger.log(LogStatus.FAIL, "Reverse Direct Report: Correlation scores mismatch");
			}
			if (strengthList.equals(ReportsConstant.strength_List)) {
			} else {
				logger.log(LogStatus.FAIL,
						"Reverse Direct Report: Strength values not as expected in the exported report");
			}
		} catch (Exception e) {
			logger.log(LogStatus.FAIL,
					"Reverse Direct Report: Error occured while veryfying Score and statements");
		}
		return productCSRepMap;
	}

	public boolean verifyProductToCIntermediaryReportHeaders(XSSFSheet worksheet) {
		boolean flag = false;
		try {
			assertEquals(worksheet.getRow(LOMTConstant.ZERO).getCell(LOMTConstant.ZERO).getStringCellValue(),
					ReportsConstant.TITLE);
			assertNotNull(worksheet.getRow(LOMTConstant.ZERO).getCell(LOMTConstant.ONE).getStringCellValue());

			assertEquals(worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.ZERO).getStringCellValue().trim(),
					ReportsConstant.USER.trim());
			assertTrue(worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.ONE).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.ADMIN_USER_COMMON)
					|| worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.ONE).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.ADMIN_USER_PPE)
					|| worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.ONE).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.LEARNING_USER_PPE)
					|| worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.ONE).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.SME_USER)
					|| worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.ONE).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.EDITOR_USER));

			// assertEquals(worksheet.getRow(LOMTConstant.TWO).getCell(LOMTConstant.ZERO).getStringCellValue().trim(),
			// ReportsConstant.DATE_TIME_GENERATION.trim());
			assertTrue(isValidFormat(
					worksheet.getRow(LOMTConstant.TWO).getCell(LOMTConstant.ONE).getStringCellValue().trim()));

			assertEquals(worksheet.getRow(LOMTConstant.THREE).getCell(LOMTConstant.ZERO).getStringCellValue().trim(),
					ReportsConstant.ALIGNMENTS);
			assertTrue(worksheet.getRow(LOMTConstant.THREE).getCell(LOMTConstant.ONE).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.CENTRAL_PERIPHERAL)
					|| worksheet.getRow(LOMTConstant.THREE).getCell(LOMTConstant.ONE).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.CENTRAL)
					|| worksheet.getRow(LOMTConstant.THREE).getCell(LOMTConstant.ONE).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.PERIPHERAL));

			assertEquals(worksheet.getRow(LOMTConstant.FIVE).getCell(LOMTConstant.ZERO).getStringCellValue().trim(),
					ReportsConstant.CONTENT);
			assertEquals(worksheet.getRow(LOMTConstant.FIVE).getCell(LOMTConstant.SEVEN).getStringCellValue().trim(),
					ReportsConstant.INTERMEDIARY);

			assertEquals(worksheet.getRow(LOMTConstant.SIX).getCell(LOMTConstant.ZERO).getStringCellValue().trim(),
					ReportsConstant.PROGRAM);
			assertEquals(worksheet.getRow(LOMTConstant.SIX).getCell(LOMTConstant.SEVEN).getStringCellValue().trim(),
					ReportsConstant.DISCIPLINE);
			assertNotNull(worksheet.getRow(LOMTConstant.SIX).getCell(LOMTConstant.EIGHT).getStringCellValue());

			assertEquals(worksheet.getRow(LOMTConstant.SEVEN).getCell(LOMTConstant.ZERO).getStringCellValue(),
					ReportsConstant.COURSE);
			assertNotNull(worksheet.getRow(LOMTConstant.SEVEN).getCell(LOMTConstant.ONE).getStringCellValue());

			assertEquals(worksheet.getRow(LOMTConstant.EIGHT).getCell(LOMTConstant.ZERO).getStringCellValue(),
					ReportsConstant.PRODUCT);
			assertNotNull(worksheet.getRow(LOMTConstant.EIGHT).getCell(LOMTConstant.ONE).getStringCellValue());

			assertEquals(worksheet.getRow(LOMTConstant.NINE).getCell(LOMTConstant.ZERO).getStringCellValue(),
					ReportsConstant.GEOGRAPHIC_AREA_OR_COUNTRY);
			// assertNotNull(worksheet.getRow(LOMTConstant.NINE).getCell(LOMTConstant.ONE).getStringCellValue());

			assertEquals(worksheet.getRow(LOMTConstant.TEN).getCell(LOMTConstant.ZERO).getStringCellValue(),
					ReportsConstant.STATE_OR_REGION);
			assertNotNull(worksheet.getRow(LOMTConstant.TEN).getCell(LOMTConstant.ONE).getStringCellValue());

			assertEquals(worksheet.getRow(LOMTConstant.ELEVENTH).getCell(LOMTConstant.ZERO).getStringCellValue(),
					ReportsConstant.START_GRADE);
			assertNotNull(worksheet.getRow(LOMTConstant.ELEVENTH).getCell(LOMTConstant.ONE).getStringCellValue());

			assertEquals(worksheet.getRow(LOMTConstant.TWELEVE).getCell(LOMTConstant.ZERO).getStringCellValue(),
					ReportsConstant.END_GRADE);
			assertNotNull(worksheet.getRow(LOMTConstant.TWELEVE).getCell(LOMTConstant.ONE).getStringCellValue());

			assertEquals(worksheet.getRow(LOMTConstant.THIRTEEN).getCell(LOMTConstant.ZERO).getStringCellValue(),
					ReportsConstant.ISBN10);
			assertNotNull(worksheet.getRow(LOMTConstant.THIRTEEN).getCell(LOMTConstant.ONE).getStringCellValue());

			assertEquals(worksheet.getRow(LOMTConstant.FOURTEEN).getCell(LOMTConstant.ZERO).getStringCellValue(),
					ReportsConstant.ISBN13);
			assertNotNull(worksheet.getRow(LOMTConstant.FOURTEEN).getCell(LOMTConstant.ONE).getStringCellValue());

			assertEquals(worksheet.getRow(LOMTConstant.FIFTEEN).getCell(LOMTConstant.ZERO).getStringCellValue(),
					ReportsConstant.TYPE);
			assertNotNull(worksheet.getRow(LOMTConstant.FIFTEEN).getCell(LOMTConstant.ONE).getStringCellValue());

			assertEquals(worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.ZERO).getStringCellValue(),
					ReportsConstant.URN);
			assertEquals(worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.ONE).getStringCellValue(),
					ReportsConstant.ALFRESCO_OBJECT_ID);
			assertEquals(worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.TWO).getStringCellValue(),
					ReportsConstant.COMPONENT_TOC);
			assertEquals(worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.THREE).getStringCellValue(),
					ReportsConstant.START_PAGE);
			assertEquals(worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.FOUR).getStringCellValue(),
					ReportsConstant.END_PAGE);
			assertEquals(worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.FIVE).getStringCellValue(),
					ReportsConstant.PERIPHERAL_ALIGNMENTS);
			// System.out.println(worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.SIX).getStringCellValue());
			assertTrue(worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.SIX) == null);
			assertEquals(worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.SEVEN).getStringCellValue(),
					ReportsConstant.INTERMEDIARY_URN);
			assertEquals(worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.EIGHT).getStringCellValue(),
					ReportsConstant.SUBJECT);
			assertEquals(worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.NINE).getStringCellValue(),
					ReportsConstant.CODE);
			assertEquals(worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.TEN).getStringCellValue(),
					ReportsConstant.DESCRIPTION);
			assertEquals(worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.ELEVENTH).getStringCellValue(),
					ReportsConstant.SPANISH_DESCRIPTION);
			flag = true;
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
			return flag;
		}
		return flag;
	}

	public void verifyReverseSharedIntermediaryReportHeaders(XSSFSheet worksheet, ExtentTest logger,
			String reportName) {
		try {
			// Title
			if (worksheet.getRow(LOMTConstant.ZERO).getCell(LOMTConstant.ZERO).getStringCellValue().trim()
					.equalsIgnoreCase(ReportsConstant.TITLE)) {
				if (worksheet.getRow(LOMTConstant.ZERO).getCell(LOMTConstant.ONE).getStringCellValue() != null
						&& worksheet.getRow(LOMTConstant.ZERO).getCell(LOMTConstant.ONE).getStringCellValue()
								.equalsIgnoreCase(reportName)) {
				} else {
					logger.log(LogStatus.FAIL, "Title value " + reportName + " does not match in exported file");
				}
			} else {
				logger.log(LogStatus.FAIL, "Header : Title does not match in exported file");
			}

			// User
			if (worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.ZERO).getStringCellValue().trim()
					.equalsIgnoreCase(ReportsConstant.USER.trim())) {
				if (worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.ONE).getStringCellValue()
						.equalsIgnoreCase(ReportsConstant.ADMIN_USER_COMMON)
						|| worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.ONE).getStringCellValue()
								.equalsIgnoreCase(ReportsConstant.ADMIN_USER_PPE)
						|| worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.ONE).getStringCellValue()
								.equalsIgnoreCase(ReportsConstant.LEARNING_USER_PPE)
						|| worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.ONE).getStringCellValue()
								.equalsIgnoreCase(ReportsConstant.SME_USER)
						|| worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.ONE).getStringCellValue()
								.equalsIgnoreCase(ReportsConstant.EDITOR_USER)) {
				} else {
					logger.log(LogStatus.FAIL, "User name does not match in exported file");
				}
			} else {
				logger.log(LogStatus.FAIL, "Header : User does not match in exported file");
			}

			// Date/time of generation
			if (worksheet.getRow(LOMTConstant.TWO).getCell(LOMTConstant.ZERO).getStringCellValue().trim()
					.equalsIgnoreCase(ReportsConstant.DATE_TIME_GENERATION.trim())) {
				if (isValidFormat(
						worksheet.getRow(LOMTConstant.TWO).getCell(LOMTConstant.ONE).getStringCellValue().trim())) {
				} else {
					logger.log(LogStatus.FAIL, "Date/time of generation format does not match in exported file");
				}
			} else {
				logger.log(LogStatus.FAIL, "Header : Date/time of generation does not match in exported file");
			}

			// ALIGNMENTS
			if (worksheet.getRow(LOMTConstant.THREE).getCell(LOMTConstant.ZERO).getStringCellValue().trim()
					.equalsIgnoreCase(ReportsConstant.ALIGNMENTS)) {
				if (worksheet.getRow(LOMTConstant.THREE).getCell(LOMTConstant.ONE).getStringCellValue()
						.contains(ReportsConstant.CENTRAL)
						|| worksheet.getRow(LOMTConstant.THREE).getCell(LOMTConstant.ONE).getStringCellValue()
								.contains(ReportsConstant.PERIPHERAL)
						|| worksheet.getRow(LOMTConstant.THREE).getCell(LOMTConstant.ONE).getStringCellValue()
								.contains(ReportsConstant.CENTRAL_PERIPHERAL)) {
				} else {
					logger.log(LogStatus.FAIL, "Alignments value(Central/Peripheral) does not match in exported file");
				}
			} else {
				logger.log(LogStatus.FAIL, "Header : Alignments does not match in exported file");
			}

			// Content
			if (worksheet.getRow(LOMTConstant.FIVE).getCell(LOMTConstant.ZERO).getStringCellValue().trim()
					.equalsIgnoreCase(ReportsConstant.CONTENT)) {
			} else {
				logger.log(LogStatus.FAIL, "Header : Content does not match in exported file");
			}
			// Common Alignment
			if (worksheet.getRow(LOMTConstant.FIVE).getCell(LOMTConstant.SEVEN).getStringCellValue().trim()
					.equalsIgnoreCase(ReportsConstant.COMMON_ALIGNMENT)) {
			} else {
				logger.log(LogStatus.FAIL, "Header : Common Alignment does not match in exported file");
			}
			// Standard
			if (worksheet.getRow(LOMTConstant.FIVE).getCell(LOMTConstant.TEN).getStringCellValue().trim()
					.equalsIgnoreCase(ReportsConstant.STANDARD)) {
			} else {
				logger.log(LogStatus.FAIL, "Header : Standard does not match in exported file");
			}

			// Program
			if (worksheet.getRow(LOMTConstant.SIX).getCell(LOMTConstant.ZERO).getStringCellValue().trim()
					.equalsIgnoreCase(ReportsConstant.PROGRAM)) {
			} else {
				logger.log(LogStatus.FAIL, "Header : Program does not match in exported file");
			}
			// Discipline
			if (worksheet.getRow(LOMTConstant.SIX).getCell(LOMTConstant.SEVEN).getStringCellValue().trim()
					.equalsIgnoreCase(ReportsConstant.INTERMEDIARY)) {
				if (worksheet.getRow(LOMTConstant.SIX).getCell(LOMTConstant.EIGHT).getStringCellValue().trim()
						.equalsIgnoreCase(ReportsConstant.INGESTED_INTERMEDIARY)) {
				} else {
					logger.log(LogStatus.FAIL, "Intermediary name/value does not match in exported file");
				}
			} else {
				logger.log(LogStatus.FAIL, "Header : Discipline does not match in exported file");
			}

			// Title
			if (worksheet.getRow(LOMTConstant.SIX).getCell(LOMTConstant.TEN).getStringCellValue().trim()
					.equalsIgnoreCase(ReportsConstant.TITLE)) {
				if (worksheet.getRow(LOMTConstant.SIX).getCell(LOMTConstant.ELEVENTH) != null) {
				} else {
					logger.log(LogStatus.FAIL, "Curriculum Standard should not be null in exported file");
				}
			} else {
				logger.log(LogStatus.FAIL, "Header : Standard does not match in exported file");
			}

			// Course
			if (worksheet.getRow(LOMTConstant.SEVEN).getCell(LOMTConstant.ZERO).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.COURSE)) {
				if (worksheet.getRow(LOMTConstant.SEVEN).getCell(LOMTConstant.ONE) != null) {
				} else {
					logger.log(LogStatus.FAIL, "Course is null/empty in exported file");
				}
			} else {
				logger.log(LogStatus.FAIL, "Header : Course does not match in exported file");
			}

			// Country
			if (worksheet.getRow(LOMTConstant.SEVEN).getCell(LOMTConstant.TEN).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.COUNTRY)) {
				if (worksheet.getRow(LOMTConstant.SEVEN).getCell(LOMTConstant.ELEVENTH) != null) {
				} else {
					logger.log(LogStatus.FAIL, "Country is null/empty in exported file");
				}
			} else {
				logger.log(LogStatus.FAIL, "Header : Country does not match in exported file");
			}

			// Product
			if (worksheet.getRow(LOMTConstant.EIGHT).getCell(LOMTConstant.ZERO).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.PRODUCT)) {
				if (worksheet.getRow(LOMTConstant.EIGHT).getCell(LOMTConstant.ONE).getStringCellValue()
						.equalsIgnoreCase(ReportsConstant.INGESTED_PRODUCT)) {
				} else {
					logger.log(LogStatus.FAIL, "Product Name does not match in the exported file");
				}
			} else {
				logger.log(LogStatus.FAIL, "Header : Product does not match in exported file");
			}

			// Grade
			if (worksheet.getRow(LOMTConstant.EIGHT).getCell(LOMTConstant.TEN).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.GRADE)) {
				if (worksheet.getRow(LOMTConstant.EIGHT).getCell(LOMTConstant.ELEVENTH) != null) {
				} else {
					logger.log(LogStatus.FAIL, "Grade is null/empty in exported file");
				}
			} else {
				logger.log(LogStatus.FAIL, "Header : Grade does not match in exported file");
			}
			// Geographic Area or Country
			if (worksheet.getRow(LOMTConstant.NINE).getCell(LOMTConstant.ZERO).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.GEOGRAPHIC_AREA_OR_COUNTRY)) {
				if (worksheet.getRow(LOMTConstant.NINE).getCell(LOMTConstant.ONE) != null) {
				} else {
					logger.log(LogStatus.FAIL, "Geographic Area or Country is null/empty in exported file");
				}
			} else {
				logger.log(LogStatus.FAIL, "Header : Geographic Area or Country does not match in exported file");
			}

			// State Or Region
			if (worksheet.getRow(LOMTConstant.TEN).getCell(LOMTConstant.ZERO).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.STATE_OR_REGION)) {
				if (worksheet.getRow(LOMTConstant.TEN).getCell(LOMTConstant.ONE) != null) {
				} else {
					logger.log(LogStatus.FAIL, "State or Region is null/empty in exported file");
				}
			} else {
				logger.log(LogStatus.FAIL, "Header : State or Region does not match in exported file");
			}

			// Start Grade
			if (worksheet.getRow(LOMTConstant.ELEVENTH).getCell(LOMTConstant.ZERO).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.START_GRADE)) {
				if (worksheet.getRow(LOMTConstant.ELEVENTH).getCell(LOMTConstant.ONE) != null) {
				} else {
					logger.log(LogStatus.FAIL, "Start Grade is null/empty in exported file");
				}
			} else {
				logger.log(LogStatus.FAIL, "Header : Start Grade does not match in exported file");
			}

			// End Grade
			if (worksheet.getRow(LOMTConstant.TWELEVE).getCell(LOMTConstant.ZERO).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.END_GRADE)) {
				if (worksheet.getRow(LOMTConstant.TWELEVE).getCell(LOMTConstant.ONE) != null) {
				} else {
					logger.log(LogStatus.FAIL, "End Grade is null/empty in exported file");
				}
			} else {
				logger.log(LogStatus.FAIL, "Header : End Grade does not match in exported file");
			}

			// ISBN10
			if (worksheet.getRow(LOMTConstant.THIRTEEN).getCell(LOMTConstant.ZERO).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.ISBN10)) {
				if (worksheet.getRow(LOMTConstant.THIRTEEN).getCell(LOMTConstant.ONE) != null) {
				} else {
					logger.log(LogStatus.FAIL, "ISBN10 is null/empty in exported file");
				}
			} else {
				logger.log(LogStatus.FAIL, "Header : ISBN10 does not match in exported file");
			}

			// ISBN13
			if (worksheet.getRow(LOMTConstant.FOURTEEN).getCell(LOMTConstant.ZERO).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.ISBN13)) {
				if (worksheet.getRow(LOMTConstant.FOURTEEN).getCell(LOMTConstant.ONE) != null) {
				} else {
					logger.log(LogStatus.FAIL, "ISBN13 is null/empty in exported file");
				}
			} else {
				logger.log(LogStatus.FAIL, "Header : ISBN13 does not match in exported file");
			}

			// Type
			if (worksheet.getRow(LOMTConstant.FIFTEEN).getCell(LOMTConstant.ZERO).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.TYPE)) {
				if (worksheet.getRow(LOMTConstant.FIFTEEN).getCell(LOMTConstant.ONE) != null) {
				} else {
					logger.log(LogStatus.FAIL, "Type is null/empty in exported file");
				}
			} else {
				logger.log(LogStatus.FAIL, "Header : Type does not match in exported file");
			}

			// COMPONENT REFERENCE
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.ZERO).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.COMPONENT_REFERENCE)) {
			} else {
				logger.log(LogStatus.FAIL, "Header : COMPONENT REFERENCE does not match in exported file");
			}
			// Start Page
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.ONE).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.START_PAGE)) {
			} else {
				logger.log(LogStatus.FAIL, "Header : Start Page does not match in exported file");
			}
			// End Page
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.TWO).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.END_PAGE)) {
			} else {
				logger.log(LogStatus.FAIL, "Header : End Page does not match in exported file");
			}
			// Correlation Score
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.THREE).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.CORRELATION_SCORE)) {
			} else {
				logger.log(LogStatus.FAIL, "Header : Correlation Score does not match in exported file");
			}
			// Strength
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.FOUR).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.STRENGTH)) {
			} else {
				logger.log(LogStatus.FAIL, "Header : Strength does not match in exported file");
			}
			// Peripheral Alignments
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.FIVE).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.PERIPHERAL_ALIGNMENTS)) {
			} else {
				logger.log(LogStatus.FAIL, "Header : Peripheral Alignments does not match in exported file");
			}

			// Unmet Statements
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.SEVEN).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.UNMET_STATEMENTS)) {
			} else {
				logger.log(LogStatus.FAIL, "Header : Unmet Statements does not match in exported file");
			}
			// Met Statements
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.EIGHT).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.MET_STATMENTS)) {
			} else {
				logger.log(LogStatus.FAIL, "Header : MET Statements does not match in exported file");
			}
			// Empty Column Check
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.SIX) == null) {
			} else {
				logger.log(LogStatus.FAIL, "Column G is not blank in exported file");
			}
			// Empty Column Check
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.NINE) == null) {
			} else {
				logger.log(LogStatus.FAIL, "Column J is not blank in exported file");
			}
			// Standard Strands
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.TEN).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.STANDARDS_STRANDS)) {
			} else {
				logger.log(LogStatus.FAIL, "Header : Standard Strands does not match in exported file");
			}
			// Standard Topics
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.ELEVENTH).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.STANDARDS_TOPICS)) {
			} else {
				logger.log(LogStatus.FAIL, "Header : Standard Topics does not match in exported file");
			}
			// Standard Number
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.TWELEVE).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.STANDARDS_NUMBER)) {
			} else {
				logger.log(LogStatus.FAIL, "Header : Standard Number does not match in exported file");
			}
			// ABGUID
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.THIRTEEN).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.AB_GUIDE)) {
			} else {
				logger.log(LogStatus.FAIL, "Header : ABGUID does not match in exported file");
			}
			// Correlation Score
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.FOURTEEN).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.CORRELATION_SCORE)) {
			} else {
				logger.log(LogStatus.FAIL, "Header : Correlation Score for Standard does not match in exported file");
			}
			// Strength
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.FIFTEEN).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.STRENGTH)) {
			} else {
				logger.log(LogStatus.FAIL, "Header : Strength for Standard does not match in exported file");
			}
			// Peripheral Alignments
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.SIXTEEN).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.PERIPHERAL_ALIGNMENTS)) {
			} else {
				logger.log(LogStatus.FAIL, "Header : Peripheral Alignments for Standard does not match in exported file");
			}

		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Unable to verify Headers in exported file");
		}
	}

	public Map<String, List<String>> getProductAndIntermediaryDataFromExportedSheet(XSSFSheet worksheet,
			Map<String, List<String>> productTIRepMap) {
		try {
			int counter = 0;
			List<String> productList = new LinkedList<String>();
			List<String> disciplineList = new LinkedList<String>();

			Iterator<Row> rowIterator = worksheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				if (row.getRowNum() == 6) {
					// adding Product and Intermediary goal framework name for
					// searching purpose on UI
					disciplineList.add(row.getCell(8).getStringCellValue());
				}
				if (row.getRowNum() == 8) {
					productList.add(row.getCell(1).getStringCellValue());
				}

				if (row.getRowNum() > 17 && counter <= 10) {
					if (row.getCell(2) != null && row.getCell(2).getStringCellValue() != null) {
						productList.add(row.getCell(2).getStringCellValue());
					}
					if (row.getCell(11) != null && row.getCell(11).getStringCellValue() != null) {
						disciplineList.add(row.getCell(11).getStringCellValue());
						// function(disciplineList, 11);
					}
					counter++;
				}
			}

			/*
			 * function(list, cellnumber) {
			 * list.add(row.getCell(cellnumber).getStringCellValue()); }
			 */
			// else {
			productTIRepMap.put("Product", productList);
			productTIRepMap.put("Discipline", disciplineList);
			return productTIRepMap;
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productTIRepMap;
	}

	public Map<String, List<String>> getProductAndCurriculumDataFromExportedSheet(XSSFSheet worksheet,
			Map<String, List<String>> productTIRepMap, ExtentTest logger, String reportType) {
		try {
			int counter = 0;
			List<String> productList = new LinkedList<String>();
			List<String> standardList = new LinkedList<String>();
			List<String> correlationList = new LinkedList<String>();
			List<String> metList = new LinkedList<String>();
			List<String> unmetList = new LinkedList<String>();
			List<String> strengthList = new LinkedList<String>();

			Iterator<Row> rowIterator = worksheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				if (row.getRowNum() == 6){
					if(reportType.equalsIgnoreCase(ReportsConstant.REVERSE_SHARED_INT_TEXT)) {
						standardList.add(row.getCell(11).getStringCellValue());
					}else{
						standardList.add(row.getCell(9).getStringCellValue());
					}
					
				}
				// adding Product goal framework name for searching purpose on UI
				if (row.getRowNum() == 8) {
					productList.add(row.getCell(1).getStringCellValue());
				}

				if (row.getRowNum() > 17 && counter <= 10) {
					//Reverse Shared Intermediary List
					if(reportType.equalsIgnoreCase(ReportsConstant.REVERSE_SHARED_INT_TEXT)){
						if (row.getCell(0) != null) {
							productList.add(row.getCell(0).getStringCellValue());
						}
						if (row.getCell(3) != null) {
							correlationList.add(row.getCell(3).getStringCellValue());
						} else {
							correlationList.add("");
						}
						if (row.getCell(4) != null) {
							strengthList.add(row.getCell(4).getStringCellValue());
						} else {
							strengthList.add("");
						}
						if (row.getCell(7) != null) {
							unmetList.add(row.getCell(7).getStringCellValue());
						} else {
							unmetList.add("");
						}
						if (row.getCell(8) != null) {
							metList.add(row.getCell(8).getStringCellValue());
						} else {
							metList.add("");
						}
						if (row.getCell(11) != null) {
							standardList.add(row.getCell(11).getStringCellValue());
						}
					}   //Reverse Direct Report List
						else if(reportType.equalsIgnoreCase(ReportsConstant.REVERSE_DIRECT_TEXT)){
						if (row.getCell(1) != null) {
							productList.add(row.getCell(1).getStringCellValue());
						}
						if (row.getCell(4) != null) {
							correlationList.add(row.getCell(4).getStringCellValue());
						} else {
							correlationList.add("");
						}
						if (row.getCell(5) != null) {
							strengthList.add(row.getCell(5).getStringCellValue());
						} else {
							strengthList.add("");
						}
						if (row.getCell(9) != null) {
							standardList.add(row.getCell(9).getStringCellValue());
						}
					}
					
					
					counter++;
				}
			}
			productTIRepMap.put("Product", productList);
			productTIRepMap.put("CurriculumStandard", standardList);
			productTIRepMap.put("Correlation_Product", correlationList);
			productTIRepMap.put("Met_Statement", metList);
			productTIRepMap.put("Strength_Product", strengthList);
			productTIRepMap.put("Unmet_Statement", unmetList);
			return productTIRepMap;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return productTIRepMap;
	}

	public boolean verifyProductDataUI(Map<String, List<String>> productTIRepMap, ExtentTest logger) {
		boolean flag = false;
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 120);
		List<String> prList = null;
		try {
			jse.executeScript("window.scrollBy(0,-500)");
			commonPOM.getPearsonLogo().click();
			commonPOM.getSchoolGlobalLOB().click();
			hePom.getProductLink().click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			jse.executeScript("window.scrollBy(0,200)");
			prList = productTIRepMap.get("Product");
			if (!prList.isEmpty()) {
				// goalframework
				for (String prTopics : prList) {
					productTocPOM.getEnterSearchTerm().sendKeys(prTopics);
					productTocPOM.getUpdateResultButton().click();
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
					jse.executeScript("window.scrollBy(0,300)");
					if (schoolPOM.getResultFound().getText().contains("Showing")) {
						schoolPOM.getCurriculumGoalFramework().click();
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
						jse.executeScript("window.scrollBy(0,300)");
						break;
					}
				}
				// verifying titles
				for (String prTitle : prList) {
					if (!prTitle.equalsIgnoreCase(ReportsConstant.INGESTED_PRODUCT)) {
						List<WebElement> webElement = schoolPOM.getParentChildList();
						productTocPOM.getInnerEnterSearchTerm().sendKeys(prTitle);
						productTocPOM.getProductInnerUpdateResultButton().click();
						// wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
						Thread.sleep(4000);
						jse.executeScript("window.scrollBy(0,300)");
						if (!webElement.isEmpty()) {
							Iterator<WebElement> itr = webElement.iterator();
							while (itr.hasNext()) {
								WebElement childStructureElement = itr.next();
								String structureName = childStructureElement.getText();
								if (structureName.contains(prTitle)) {
									flag = true;
									continue;
								} else {
									flag = false;
									logger.log(LogStatus.FAIL,
											"Product Data Verification Failed - Not Found Topic : " + structureName);
								}
							}
							productTocPOM.getInnerEnterSearchTerm().clear();
							continue;
						}
					}
				}
			}

		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Product UI Data Verification Failed");
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

	/*
	 * public static void main(String [] args) throws ParseException {
	 * //System.out.println("isValid - dd/MM/yyyy with 25/09/2013 12:13:50 = " +
	 * isValidFormat("DD/MM/YYYY HH:MM:SS", "25/09/2013  12:13:50"));
	 * 
	 * SimpleDateFormat desiredFormat = new SimpleDateFormat(
	 * "dd/MM/yyyy HH:mm:ss"); Date date = desiredFormat.parse(
	 * "25/09/2013  12:13:50");
	 * //System.out.println(desiredFormat.format(date));
	 * System.out.println(isValidFormat("25/09/2013  12:13:50")); }
	 */

	public String createAndDownloadReportSourceProduct(String reportType, String source, String pivot, String target,
			ExtentTest logger) {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		String reportName = null;
		try {
			reportName = createAndDownloadProductReport(reportType, source, pivot, target, wait, jse);
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, reportType + " either Creation or download is failed");
			return reportName;
		}
		return reportName;
	}

	public String createAndDownloadProductReport(String reportType, String source, String pivot, String target,
			WebDriverWait wait, JavascriptExecutor jse) {
		String reportName = null;
		try {
			jse.executeScript("window.scrollBy(0,-1000)");
			// Search the ingested product and create report
			commonPOM.getSchoolGlobalLOB().click();
			hePom.getProductLink().click();
			// Thread.sleep(4000);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			productTocPOM.getEnterSearchTerm().sendKeys(source);
			productTocPOM.getUpdateResultButton().click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));

			jse.executeScript("window.scrollBy(0,400)");

			Thread.sleep(1000);
			schoolPOM.getAction().click();
			reportsPOM.getSchoolCreateReportLink().click();
			Thread.sleep(1000);
			reportsPOM.getSchoolModelWindowNextButton().click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			if (reportType.equalsIgnoreCase(ReportsConstant.PRODUCT_INT_TEXT)) {
				reportsPOM.getProductToCIntermediaryReport().click();
			} else if (reportType.equalsIgnoreCase(ReportsConstant.REVERSE_SHARED_INT_TEXT)) {
				jse.executeScript("window.scrollBy(0,400)");
				reportsPOM.getReverseSharedIntermediaryReport().click();
			} else if (reportType.equalsIgnoreCase(ReportsConstant.REVERSE_DIRECT_TEXT)) {
				jse.executeScript("window.scrollBy(0,400)");
				reportsPOM.getReverseDirectReport().click();
			}
			//Thread.sleep(6000);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			// pivot select and click
			clickIngestedIntermediaryDiscipline(pivot);
			reportsPOM.getSchoolModelWindowNextButton().click();
			// Thread.sleep(2000);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			Thread.sleep(3000);
			if (reportType.equalsIgnoreCase(ReportsConstant.REVERSE_SHARED_INT_TEXT)
					|| reportType.equalsIgnoreCase(ReportsConstant.REVERSE_DIRECT_TEXT)) {
				jse.executeScript("window.scrollBy(0,-250)");
				schoolPOM.getEnterEnterSearch().sendKeys(ReportsConstant.INGESTED_STANDARD_YEAR);
				schoolPOM.getSchoolUpdateResultButton().click();
				//Thread.sleep(2000);
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
				jse.executeScript("window.scrollBy(0,300)");
				reportsPOM.getSelectTarget().click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
				// Thread.sleep(4000);
				reportsPOM.getSchoolModelWindowNextButton().click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			}

			reportName = reportType + String.valueOf(13000 + (int) Math.round(Math.random() * (1400 - 1300)));

			reportsPOM.getReportName().clear();
			reportsPOM.getReportName().sendKeys(reportName);

			jse.executeScript("window.scrollBy(0,500)");
			reportsPOM.getRunReport().click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));

			Thread.sleep(20000);
			jse.executeScript("window.scrollBy(0,-200)");
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

	public void clickIngestedIntermediaryDiscipline(String pivot) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 600);
		try {
			jse.executeScript("window.scrollBy(0,1000)");
			// check that all the intermediaries are displayed
			if (!intermediaryPOM.getLoadMoreButton().getAttribute("class").contains("load-more-text disabled")) {
				intermediaryPOM.getLoadMoreButton().click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
				jse.executeScript("window.scrollBy(0,1800)");
				if (!intermediaryPOM.getLoadMoreButton().getAttribute("class").contains("load-more-text disabled")) {
					intermediaryPOM.getLoadMoreButton().click();
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
					jse.executeScript("window.scrollBy(0,2000)");
					if (!intermediaryPOM.getLoadMoreButton().getAttribute("class")
							.contains("load-more-text disabled")) {
						intermediaryPOM.getLoadMoreButton().click();
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
					} else {
						jse.executeScript("window.scrollBy(0, -4000)");
					}
				} else {
					jse.executeScript("window.scrollBy(0, -2000)");
				}
			} else {
				jse.executeScript("window.scrollBy(0, -1000)");
			}

			List<WebElement> webElement = intermediaryPOM.getIntermediaryGFList();
			if (!webElement.isEmpty()) {
				int counter = 0;
				Iterator<WebElement> itr = webElement.iterator();
				while (itr.hasNext()) {
					WebElement childStructureElement = itr.next();
					String discipline = childStructureElement.getText();
					int i = discipline.indexOf("\n");
					int j = discipline.lastIndexOf("\n");
					counter++;
					if (pivot.equalsIgnoreCase(discipline.substring(i, j).trim())) {
						String xpath = "//div[@id='report-target-container']/div[2]/div/div[1]/div[2]/div[" + counter
								+ "]/div/div[2]/div/span[1]";
						if (counter <= 7) {
							WebElement element = driver.findElement(By.xpath(xpath));
							System.out.println("Selecting Intermediary : " + discipline.substring(i, j).trim());
							element.click();
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
							break;
						}
						if (counter >= 7 && counter <= 14) {
							jse.executeScript("window.scrollBy(0,600)");
							WebElement element = driver.findElement(By.xpath(xpath));
							element.click();
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
							break;
						}
						if (counter >= 14 && counter <= 21) {
							jse.executeScript("window.scrollBy(0,1100)");
							WebElement element = driver.findElement(By.xpath(xpath));
							element.click();
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
							break;
						}
						if (counter >= 21 && counter <= 28) {
							jse.executeScript("window.scrollBy(0,1600)");
							WebElement element = driver.findElement(By.xpath(xpath));
							element.click();
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
							break;
						}
						if (counter >= 28 && counter <= 35) {
							jse.executeScript("window.scrollBy(0,2100)");
							WebElement element = driver.findElement(By.xpath(xpath));
							element.click();
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
							break;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void searchIntermediaryDiscipline(String dis) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 600);
		try {
			intermediaryPOM.getIntermediaryStructure().click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			jse.executeScript("window.scrollBy(0,2000)");

			// expanding all the ingested Intermediary disciplines
			if (!intermediaryPOM.getLoadMoreButton().getAttribute("class").contains("load-more-text disabled")) {
				intermediaryPOM.getLoadMoreButton().click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
				jse.executeScript("window.scrollBy(0,2000)");
				if (!intermediaryPOM.getLoadMoreButton().getAttribute("class").contains("load-more-text disabled")) {
					intermediaryPOM.getLoadMoreButton().click();
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
					jse.executeScript("window.scrollBy(0,2000)");
					if (!intermediaryPOM.getLoadMoreButton().getAttribute("class")
							.contains("load-more-text disabled")) {
						intermediaryPOM.getLoadMoreButton().click();
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
						jse.executeScript("window.scrollBy(0,2000)");
						if (!intermediaryPOM.getLoadMoreButton().getAttribute("class")
								.contains("load-more-text disabled")) {
							intermediaryPOM.getLoadMoreButton().click();
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
							jse.executeScript("window.scrollBy(0, -4000)");
						}
					} else {
						jse.executeScript("window.scrollBy(0, -4000)");
					}
				} else {
					jse.executeScript("window.scrollBy(0, -2000)");
				}
			} else {
				jse.executeScript("window.scrollBy(0, -2000)");
			}

			jse.executeScript("window.scrollBy(0,200)");

			// verifying ingested data
			List<WebElement> webElement = intermediaryPOM.getIntermediaryGFList();
			if (!webElement.isEmpty()) {
				int counter = 0;
				Iterator<WebElement> itr = webElement.iterator();
				while (itr.hasNext()) {
					WebElement childStructureElement = itr.next();
					String discipline = childStructureElement.getText();
					// System.out.println("Intermediary ingested discipline :
					// "+structureName);
					int i = discipline.indexOf("\n");
					int j = discipline.lastIndexOf("\n");
					counter++;
					if (dis.equalsIgnoreCase(discipline.substring(i, j).trim())) {
						String xpath = "//div[@class='list-data-container']/child::div[" + counter
								+ "]/div/div[1]/div/span/span[2]/a";
						if (counter <= 7) {
							WebElement element = driver.findElement(By.xpath(xpath));
							element.click();
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
							break;
						}
						if (counter > 7 && counter <= 14) {
							jse.executeScript("window.scrollBy(0,600)");
							WebElement element = driver.findElement(By.xpath(xpath));
							element.click();
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
							jse.executeScript("window.scrollBy(0,-1000)");
							break;
						}
						if (counter > 14 && counter <= 21) {
							jse.executeScript("window.scrollBy(0,1100)");
							WebElement element = driver.findElement(By.xpath(xpath));
							element.click();
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
							jse.executeScript("window.scrollBy(0,-1500)");
							break;
						}
						if (counter > 21 && counter <= 28) {
							jse.executeScript("window.scrollBy(0,1600)");
							WebElement element = driver.findElement(By.xpath(xpath));
							element.click();
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
							jse.executeScript("window.scrollBy(0,-2000)");
							break;
						}
						if (counter > 28 && counter <= 35) {
							jse.executeScript("window.scrollBy(0,2100)");
							WebElement element = driver.findElement(By.xpath(xpath));
							element.click();
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
							jse.executeScript("window.scrollBy(0,-2100)");
							break;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String createAndDownloadReport(String reportType, String source, String pivot, String target,
			ExtentTest logger) {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		String reportName = null;
		try {
			reportName = createAndDownloadReport(reportType, source, pivot, target, wait, jse);
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Forward Indirect Intermediary Report either Creation or download is failed");
			return reportName;
		}
		return reportName;
	}

	public String createAndDownloadReport(String reportType, String source, String pivot, String target,
			WebDriverWait wait, JavascriptExecutor jse) throws IOException {
		String reportName = null;
		commonPOM.getSchoolGlobalLOB().click();
		schoolPOM.getCurriculumSt().click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));

		schoolPOM.getEnterEnterSearch().sendKeys(source);

		schoolPOM.getSchoolUpdateResultButton().click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
		jse.executeScript("window.scrollBy(0, 300)");

		schoolPOM.getAction().click();
		reportsPOM.getSchoolCreateReportLink().click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));

		// Model window is appears, click on next button
		reportsPOM.getSchoolModelWindowNextButton().click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));

		// Selection of report based on coming Report Type
		if (reportType.equalsIgnoreCase(ReportsConstant.FORWARD_INDIRECT_INTERMEDIARY_REPORT)) {
			jse.executeScript("window.scrollBy(0, 400)");
			reportsPOM.getForwardIndirectIntermediaryReport().click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
		}

		clickIngestedIntermediaryDiscipline(pivot);

		reportsPOM.getSchoolModelWindowNextButton().click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));

		reportName = ReportsConstant.FORWARD_INDIRECT_INT_REPORT_FILE_NAME + "-"
				+ String.valueOf(1300 + (int) Math.round(Math.random() * (1400 - 1300)));

		reportsPOM.getReportName().clear();
		reportsPOM.getReportName().sendKeys(reportName);

		jse.executeScript("window.scrollBy(0,500)");
		reportsPOM.getRunReport().click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));

		jse.executeScript("window.scrollBy(0,-300)");
		reportsPOM.getEnterSearchTerm().sendKeys(reportName);
		reportsPOM.getUpdateResult().click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));

		if (reportsPOM.getRepotCountText().getText().contains("Showing")) {
			removeExistingFile();

			jse.executeScript("window.scrollBy(0,300)");
			reportsPOM.getReportActionLink().click();
			reportsPOM.getReportExportButton().click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			jse.executeScript("window.scrollBy(0,-500)");
			commonPOM.getPearsonLogo().click();
		}

		return reportName;
	}

	public Map<String, List<String>> verifiedForwardIndirectIntermediaryReportsExportedFile(String reportName,
			ExtentTest logger) {
		Map<String, List<String>> forwardIIRepMap = new LinkedHashMap<String, List<String>>();
		InputStream inputStream = null;
		XSSFWorkbook workbook = null;
		XSSFSheet worksheet = null;
		try {
			String fileName = getFileFromDirectory(LOMTConstant.EXPORTED_FILE_PATH);
			File forwardIIReportExpFile = new File(LOMTConstant.EXPORTED_FILE_PATH + fileName);

			inputStream = new FileInputStream(forwardIIReportExpFile);
			workbook = new XSSFWorkbook(inputStream);
			worksheet = workbook.getSheetAt(0);
			verifyForwardIIReportHeaders(worksheet, reportName, logger);
			getCurriculumStandardAndIntermediaryDataFromExportedSheet(worksheet, forwardIIRepMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return forwardIIRepMap;
	}

	public void verifyForwardIIReportHeaders(XSSFSheet worksheet, String reportName, ExtentTest logger) {
		try {
			// Title
			if (worksheet.getRow(LOMTConstant.ZERO).getCell(LOMTConstant.ZERO).getStringCellValue().trim()
					.equalsIgnoreCase(ReportsConstant.TITLE)) {
				if (worksheet.getRow(LOMTConstant.ZERO).getCell(LOMTConstant.ONE).getStringCellValue() != null
						&& worksheet.getRow(LOMTConstant.ZERO).getCell(LOMTConstant.ONE).getStringCellValue()
								.equalsIgnoreCase(reportName)) {
				} else {
					logger.log(LogStatus.FAIL, "Title value(report name) does not match in exported file");
				}
			} else {
				logger.log(LogStatus.FAIL, "Header : Title does not match in exported file");
			}

			// User
			if (worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.ZERO).getStringCellValue().trim()
					.equalsIgnoreCase(ReportsConstant.USER.trim())) {
				if (worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.ONE).getStringCellValue()
						.equalsIgnoreCase(ReportsConstant.ADMIN_USER_COMMON)
						|| worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.ONE).getStringCellValue()
								.equalsIgnoreCase(ReportsConstant.ADMIN_USER_PPE)
						|| worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.ONE).getStringCellValue()
								.equalsIgnoreCase(ReportsConstant.LEARNING_USER_PPE)
						|| worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.ONE).getStringCellValue()
								.equalsIgnoreCase(ReportsConstant.SME_USER)
						|| worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.ONE).getStringCellValue()
								.equalsIgnoreCase(ReportsConstant.EDITOR_USER)) {
				} else {
					logger.log(LogStatus.FAIL, "User name does not match in exported file");
				}
			} else {
				logger.log(LogStatus.FAIL, "Header : User does not match in exported file");
			}

			// Date/time of generation
			if (worksheet.getRow(LOMTConstant.TWO).getCell(LOMTConstant.ZERO).getStringCellValue().trim()
					.equalsIgnoreCase(ReportsConstant.DATE_TIME_GENERATION.trim())) {
				if (isValidFormat(
						worksheet.getRow(LOMTConstant.TWO).getCell(LOMTConstant.ONE).getStringCellValue().trim())) {
				} else {
					logger.log(LogStatus.FAIL, "Date/time of generation format does not match in exported file");
				}
			} else {
				logger.log(LogStatus.FAIL, "Header : Date/time of generation does not match in exported file");
			}

			// Alignments ALIGNMENTS
			if (worksheet.getRow(LOMTConstant.THREE).getCell(LOMTConstant.ZERO).getStringCellValue().trim()
					.equalsIgnoreCase(ReportsConstant.ALIGNMENTS)) {
				if (worksheet.getRow(LOMTConstant.THREE).getCell(LOMTConstant.ONE).getStringCellValue()
						.contains(ReportsConstant.CENTRAL)
						|| worksheet.getRow(LOMTConstant.THREE).getCell(LOMTConstant.ONE).getStringCellValue()
								.contains(ReportsConstant.PERIPHERAL)) {
				} else {
					logger.log(LogStatus.FAIL, "Alignments value(Central/Peripheral) does not match in exported file");
				}
			} else {
				logger.log(LogStatus.FAIL, "Header : Alignments does not match in exported file");
			}

			// Standard
			if (worksheet.getRow(LOMTConstant.FIVE).getCell(LOMTConstant.ZERO).getStringCellValue().trim()
					.equalsIgnoreCase(ReportsConstant.STANDARD)) {
			} else {
				logger.log(LogStatus.FAIL, "Header : Standard does not match in exported file");
			}

			// Title
			if (worksheet.getRow(LOMTConstant.SIX).getCell(LOMTConstant.ZERO).getStringCellValue().trim()
					.equalsIgnoreCase(ReportsConstant.TITLE)) {
				if (worksheet.getRow(LOMTConstant.SIX).getCell(LOMTConstant.ONE).getStringCellValue() != null) {
				} else {
					logger.log(LogStatus.FAIL, "Curriculum Standard should not be null in exported file");
				}
			} else {
				logger.log(LogStatus.FAIL, "Header : Standard does not match in exported file");
			}

			// Country
			if (worksheet.getRow(LOMTConstant.SEVEN).getCell(LOMTConstant.ZERO).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.COUNTRY)) {
				if (worksheet.getRow(LOMTConstant.SEVEN).getCell(LOMTConstant.ONE).getStringCellValue() != null) {
				} else {
					logger.log(LogStatus.FAIL, "Country should not be null in exported file");
				}
			} else {
				logger.log(LogStatus.FAIL, "Header : Country does not match in exported file");
			}

			// Grade
			if (worksheet.getRow(LOMTConstant.EIGHT).getCell(LOMTConstant.ZERO).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.GRADE)) {
				if (worksheet.getRow(LOMTConstant.EIGHT).getCell(LOMTConstant.ONE).getStringCellValue() != null) {
				} else {
					logger.log(LogStatus.FAIL, "Grade should not be null in exported file");
				}
			} else {
				logger.log(LogStatus.FAIL, "Header : Grade does not match in exported file");
			}

			// Intermediary
			if (worksheet.getRow(LOMTConstant.FIVE).getCell(LOMTConstant.EIGHT).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.INTERMEDIARY)) {
			} else {
				logger.log(LogStatus.FAIL, "Header : Intermediary does not match in exported file");
			}

			// Discipline
			if (worksheet.getRow(LOMTConstant.SIX).getCell(LOMTConstant.EIGHT).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.DISCIPLINE)) {
				if (worksheet.getRow(LOMTConstant.SIX).getCell(LOMTConstant.NINE).getStringCellValue() != null) {
				} else {
					logger.log(LogStatus.FAIL, "Discipline should not be null in exported file");
				}
			} else {
				logger.log(LogStatus.FAIL, "Header : Discipline does not match in exported file");
			}

			// Standards' Strands
			if (worksheet.getRow(LOMTConstant.TEN).getCell(LOMTConstant.ZERO).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.STANDARDS_STRANDS)) {
			} else {
				logger.log(LogStatus.FAIL, "Header : Standards' Strands does not match in exported file");
			}

			// Standards' Topics
			if (worksheet.getRow(LOMTConstant.TEN).getCell(LOMTConstant.ONE).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.STANDARDS_TOPICS)) {
			} else {
				logger.log(LogStatus.FAIL, "Header : Standards' Topics does not match in exported file");
			}

			// Standard Number
			if (worksheet.getRow(LOMTConstant.TEN).getCell(LOMTConstant.TWO).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.STANDARDS_NUMBER)) {
			} else {
				logger.log(LogStatus.FAIL, "Header : Standard Number does not match in exported file");
			}

			// Parent Code
			if (worksheet.getRow(LOMTConstant.TEN).getCell(LOMTConstant.THREE).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.PARENT_CODE)) {
			} else {
				logger.log(LogStatus.FAIL, "Header : Parent Code does not match in exported file");
			}

			// Grade
			if (worksheet.getRow(LOMTConstant.TEN).getCell(LOMTConstant.FOUR).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.GRADE)) {
			} else {
				logger.log(LogStatus.FAIL, "Header : Grade does not match in exported file");
			}

			// AB GUID
			if (worksheet.getRow(LOMTConstant.TEN).getCell(LOMTConstant.FIVE).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.AB_GUIDE)) {
			} else {
				logger.log(LogStatus.FAIL, "Header : AB GUID does not match in exported file");
			}

			// System Unique ID
			if (worksheet.getRow(LOMTConstant.TEN).getCell(LOMTConstant.SIX).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.SYSTEM_UNIQUE_ID)) {
			} else {
				logger.log(LogStatus.FAIL, "Header : System Unique ID does not match in exported file");
			}

			// Intermediary URN
			if (worksheet.getRow(LOMTConstant.TEN).getCell(LOMTConstant.EIGHT).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.INTERMEDIARY_URN)) {
			} else {
				logger.log(LogStatus.FAIL, "Header : Intermediary URN does not match in exported file");
			}

			// Subject
			if (worksheet.getRow(LOMTConstant.TEN).getCell(LOMTConstant.NINE).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.SUBJECT)) {
			} else {
				logger.log(LogStatus.FAIL, "Header : Subject does not match in exported file");
			}

			// Code
			if (worksheet.getRow(LOMTConstant.TEN).getCell(LOMTConstant.TEN).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.CODE)) {
			} else {
				logger.log(LogStatus.FAIL, "Header : Code does not match in exported file");
			}

			// Description
			if (worksheet.getRow(LOMTConstant.TEN).getCell(LOMTConstant.ELEVENTH).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.DESCRIPTION)) {
			} else {
				logger.log(LogStatus.FAIL, "Header : Description does not match in exported file");
			}

			// Spanish Description
			if (worksheet.getRow(LOMTConstant.TEN).getCell(LOMTConstant.TWELEVE).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.SPANISH_DESCRIPTION)) {
			} else {
				logger.log(LogStatus.FAIL, "Header : Spanish Description does not match in exported file");
			}
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Unable to verify Headers in exported file");
		}
	}

	public Map<String, List<String>> getCurriculumStandardAndIntermediaryDataFromExportedSheet(XSSFSheet worksheet,
			Map<String, List<String>> forwardIIRepMap) {
		try {
			List<String> curriculumStdList = new LinkedList<String>();
			List<String> disciplineList = new LinkedList<String>();

			Iterator<Row> rowIterator = worksheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				if (row.getRowNum() == 6) {
					// adding Curriculum Standard and Intermediary for UI
					// verification
					curriculumStdList.add(row.getCell(1).getStringCellValue());
					disciplineList.add(row.getCell(9).getStringCellValue());
				}

				if (row.getRowNum() > 10) {
					if (!(row.getCell(1) == null)) {
						curriculumStdList.add(row.getCell(1).getStringCellValue());
					}
					if (!(row.getCell(11) == null)) {
						disciplineList.add(row.getCell(11).getStringCellValue());
					}
				}
			}
			forwardIIRepMap.put("CurriculumStandard", curriculumStdList);
			forwardIIRepMap.put("Discipline", disciplineList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return forwardIIRepMap;
	}

	public boolean verifyCurriculumStandardDataUI(Map<String, List<String>> forwardIIRepMap, ExtentTest logger) {
		boolean flag = false;
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 120);
		List<String> csList = null;
		try {
			jse.executeScript("window.scrollBy(0,-500)");
			commonPOM.getPearsonLogo().click();
			commonPOM.getSchoolGlobalLOB().click();
			schoolPOM.getCurriculumSt().click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));

			csList = forwardIIRepMap.get("CurriculumStandard");

			if (!csList.isEmpty()) {
				// goalframework
				for (String csTopics : csList) {
					schoolPOM.getEnterEnterSearch().sendKeys(csTopics);
					schoolPOM.getSchoolUpdateResultButton().click();
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
					jse.executeScript("window.scrollBy(0,400)");

					if (schoolPOM.getResultFound().getText().contains("Showing")) {
						schoolPOM.getCurriculumGoalFramework().click();
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
						jse.executeScript("window.scrollBy(0,300)");
						break;
					}
				}
				// verifying topics
				for (String csTopics : csList) {
					if (!csTopics.equalsIgnoreCase(ReportsConstant.CS_GOALFRAMEWORK_NAME_PPE)) {
						schoolPOM.getInnerEnterSearch().sendKeys(csTopics);
						schoolPOM.getSchoolInnerUpdateResultButton().click();
						Thread.sleep(4000);
						// wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));

						List<WebElement> webElement = schoolPOM.getParentChildList();
						if (!webElement.isEmpty()) {
							Iterator<WebElement> itr = webElement.iterator();
							while (itr.hasNext()) {
								WebElement childStructureElement = itr.next();
								String structureName = childStructureElement.getText();
								if (structureName.contains(csTopics)) {
									flag = true;
									continue;
								} else {
									flag = false;
									logger.log(LogStatus.FAIL, "Curriculum Standard Data Verification failed,  "
											+ " does not found Topic : " + structureName);
								}
							}
							schoolPOM.getInnerEnterSearch().clear();
						}
					}
				}
			}
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Curriculum Standard Data Verification failed");
		}
		return flag;
	}

	public boolean verifyIntermediaryDataUI(Map<String, List<String>> productTIRepMap, ExtentTest logger) {
		boolean flag = false;
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 120);
		List<String> disList = null;
		try {
			jse.executeScript("window.scrollBy(0,-500)");
			commonPOM.getPearsonLogo().click();
			commonPOM.getSchoolGlobalLOB().click();
			// searchIntermediaryDiscipline();
			disList = productTIRepMap.get("Discipline");

			if (!disList.isEmpty()) {

				for (String dis : disList) {
					searchIntermediaryDiscipline(dis);
					break;
				}

				for (String dis : disList) {
					if (!dis.equalsIgnoreCase(ReportsConstant.INGESTED_INTERMEDIARY_PPE)) {
						List<WebElement> webElement = intermediaryPOM.getIntermediaryGFList();
						intermediaryPOM.getEnterSearchTerm().sendKeys(dis);
						intermediaryPOM.getUpdateResultButton().click();
						// wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
						Thread.sleep(10000);
						jse.executeScript("window.scrollBy(0,300)");
						if (!webElement.isEmpty()) {
							Iterator<WebElement> itr = webElement.iterator();
							while (itr.hasNext()) {
								WebElement childStructureElement = itr.next();
								String structureName = childStructureElement.getText();
								if (structureName.contains(dis)) {
									flag = true;
									continue;
								} else {
									flag = false;
									logger.log(LogStatus.FAIL,
											"Intermediary Educational goals not found in exported sheet : "
													+ structureName);
								}
							}
							intermediaryPOM.getEnterSearchTerm().clear();
							continue;
						} else {
							flag = false;
							logger.log(LogStatus.FAIL, "Ingested Goalframework not found");
						}

					}
				}
			}
			jse.executeScript("window.scrollBy(0,-500)");
			commonPOM.getPearsonLogo().click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	// Basic User
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

	// Co ordinator User
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

	public boolean searchAndExportReport(String reportName, String userName) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 120);
		boolean flag = false;
		try {
			removeExistingFile();
			commonPOM.getSchoolGlobalLOB().click();
			if (userName.equalsIgnoreCase(learningEditor)) {
				reportsPOM.getReportsExportLink().click();
			} else {
				reportsPOM.getReportsExportLinkNonAdmin().click();
			}
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));

			reportsPOM.getEnterSearchTerm().sendKeys(reportName);
			reportsPOM.getUpdateResult().click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			if (reportsPOM.getRepotCountText().getText().contains("Showing")) {
				jse.executeScript("window.scrollBy(0,300)");
				reportsPOM.getReportActionLink().click();
				reportsPOM.getReportExportButton().click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
				flag = true;
			}
		} catch (Exception e) {
			flag = false;
		}
		jse.executeScript("window.scrollBy(0,-500)");
		commonPOM.getPearsonLogo().click();
		return flag;
	}
	
	public void verifyReverseDirectReportHeaders(XSSFSheet worksheet, ExtentTest logger,
			String reportName) {
		try {
			// Title
			if (worksheet.getRow(LOMTConstant.ZERO).getCell(LOMTConstant.ZERO).getStringCellValue().trim()
					.equalsIgnoreCase(ReportsConstant.TITLE)) {
				if (worksheet.getRow(LOMTConstant.ZERO).getCell(LOMTConstant.ONE).getStringCellValue() != null
						&& worksheet.getRow(LOMTConstant.ZERO).getCell(LOMTConstant.ONE).getStringCellValue().trim()
								.equalsIgnoreCase(reportName)) {
				} else {
					logger.log(LogStatus.FAIL, "Title value " + reportName + " does not match in exported file");
				}
			} else {
				logger.log(LogStatus.FAIL, "Header : Title does not match in exported file");
			}

			// User
			if (worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.ZERO).getStringCellValue().trim()
					.equalsIgnoreCase(ReportsConstant.USER.trim())) {
				if (worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.ONE).getStringCellValue()
						.equalsIgnoreCase(ReportsConstant.ADMIN_USER_COMMON)
						|| worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.ONE).getStringCellValue()
								.equalsIgnoreCase(ReportsConstant.ADMIN_USER_PPE)
						|| worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.ONE).getStringCellValue()
								.equalsIgnoreCase(ReportsConstant.LEARNING_USER_PPE)
						|| worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.ONE).getStringCellValue()
								.equalsIgnoreCase(ReportsConstant.SME_USER)
						|| worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.ONE).getStringCellValue()
								.equalsIgnoreCase(ReportsConstant.EDITOR_USER)) {
				} else {
					logger.log(LogStatus.FAIL, "User name does not match in exported file");
				}
			} else {
				logger.log(LogStatus.FAIL, "Header : User does not match in exported file");
			}

			// Date/time of generation
			if (worksheet.getRow(LOMTConstant.TWO).getCell(LOMTConstant.ZERO).getStringCellValue().trim()
					.equalsIgnoreCase(ReportsConstant.DATE_TIME_GENERATION.trim())) {
				if (isValidFormat(
						worksheet.getRow(LOMTConstant.TWO).getCell(LOMTConstant.ONE).getStringCellValue().trim())) {
				} else {
					logger.log(LogStatus.FAIL, "Date/time of generation format does not match in exported file");
				}
			} else {
				logger.log(LogStatus.FAIL, "Header : Date/time of generation does not match in exported file");
			}

			// ALIGNMENTS
			if (worksheet.getRow(LOMTConstant.THREE).getCell(LOMTConstant.ZERO).getStringCellValue().trim()
					.equalsIgnoreCase(ReportsConstant.ALIGNMENTS)) {
				if (worksheet.getRow(LOMTConstant.THREE).getCell(LOMTConstant.ONE).getStringCellValue()
						.contains(ReportsConstant.CENTRAL)
						|| worksheet.getRow(LOMTConstant.THREE).getCell(LOMTConstant.ONE).getStringCellValue()
								.contains(ReportsConstant.PERIPHERAL)
						|| worksheet.getRow(LOMTConstant.THREE).getCell(LOMTConstant.ONE).getStringCellValue()
								.contains(ReportsConstant.CENTRAL_PERIPHERAL)) {
				} else {
					logger.log(LogStatus.FAIL, "Alignments value(Central/Peripheral) does not match in exported file");
				}
			} else {
				logger.log(LogStatus.FAIL, "Header : Alignments does not match in exported file");
			}

			// Content
			if (worksheet.getRow(LOMTConstant.FIVE).getCell(LOMTConstant.ZERO).getStringCellValue().trim()
					.equalsIgnoreCase(ReportsConstant.CONTENT)) {
			} else {
				logger.log(LogStatus.FAIL, "Header : Content does not match in exported file");
			}
			
			// Standard
			if (worksheet.getRow(LOMTConstant.FIVE).getCell(LOMTConstant.EIGHT).getStringCellValue().trim()
					.equalsIgnoreCase(ReportsConstant.STANDARD)) {
			} else {
				logger.log(LogStatus.FAIL, "Header : Standard does not match in exported file");
			}

			// Program
			if (worksheet.getRow(LOMTConstant.SIX).getCell(LOMTConstant.ZERO).getStringCellValue().trim()
					.equalsIgnoreCase(ReportsConstant.PROGRAM)) {
			} else {
				logger.log(LogStatus.FAIL, "Header : Program does not match in exported file");
			}

			// Title
			if (worksheet.getRow(LOMTConstant.SIX).getCell(LOMTConstant.EIGHT).getStringCellValue().trim()
					.equalsIgnoreCase(ReportsConstant.TITLE)) {
				if (worksheet.getRow(LOMTConstant.SIX).getCell(LOMTConstant.NINE).getStringCellValue().trim().
						equalsIgnoreCase(ReportsConstant.CS_GOALFRAMEWORK_NAME_PPE)) {
				} else {
					logger.log(LogStatus.FAIL, "Curriculum Standard Title should be: "
							+ ReportsConstant.CS_GOALFRAMEWORK_NAME_PPE + "but is : "
							+ worksheet.getRow(LOMTConstant.SIX).getCell(LOMTConstant.NINE).getStringCellValue().trim());
				}
			} else {
				logger.log(LogStatus.FAIL, "Header : Title does not match in exported file");
			}

			// Course
			if (worksheet.getRow(LOMTConstant.SEVEN).getCell(LOMTConstant.ZERO).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.COURSE)) {
				if (worksheet.getRow(LOMTConstant.SEVEN).getCell(LOMTConstant.ONE) != null) {
				} else {
					logger.log(LogStatus.FAIL, "Course is null/empty in exported file");
				}
			} else {
				logger.log(LogStatus.FAIL, "Header : Course does not match in exported file");
			}

			// Country
			if (worksheet.getRow(LOMTConstant.SEVEN).getCell(LOMTConstant.EIGHT).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.COUNTRY)) {
				if (worksheet.getRow(LOMTConstant.SEVEN).getCell(LOMTConstant.NINE) != null) {
				} else {
					logger.log(LogStatus.FAIL, "Country is null/empty in exported file");
				}
			} else {
				logger.log(LogStatus.FAIL, "Header : Country does not match in exported file");
			}

			// Product
			if (worksheet.getRow(LOMTConstant.EIGHT).getCell(LOMTConstant.ZERO).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.PRODUCT)) {
				if (worksheet.getRow(LOMTConstant.EIGHT).getCell(LOMTConstant.ONE).getStringCellValue().trim()
						.equalsIgnoreCase(ReportsConstant.INGESTED_PRODUCT)) {
				} else {
					logger.log(LogStatus.FAIL, "Product Name should be: " +ReportsConstant.INGESTED_PRODUCT+
							"but is : " + worksheet.getRow(LOMTConstant.EIGHT).getCell(LOMTConstant.ONE).getStringCellValue().trim());
				}
			} else {
				logger.log(LogStatus.FAIL, "Header : Product does not match in exported file");
			}

			// Grade
			if (worksheet.getRow(LOMTConstant.EIGHT).getCell(LOMTConstant.EIGHT).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.GRADE)) {
				if (worksheet.getRow(LOMTConstant.EIGHT).getCell(LOMTConstant.NINE) != null) {
				} else {
					logger.log(LogStatus.FAIL, "Grade is null/empty in exported file");
				}
			} else {
				logger.log(LogStatus.FAIL, "Header : Grade does not match in exported file");
			}
			// Geographic Area or Country
			if (worksheet.getRow(LOMTConstant.NINE).getCell(LOMTConstant.ZERO).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.GEOGRAPHIC_AREA_OR_COUNTRY)) {
				if (worksheet.getRow(LOMTConstant.NINE).getCell(LOMTConstant.ONE) != null) {
				} else {
					logger.log(LogStatus.FAIL, "Geographic Area or Country is null/empty in exported file");
				}
			} else {
				logger.log(LogStatus.FAIL, "Header : Geographic Area or Country does not match in exported file");
			}

			// State Or Region
			if (worksheet.getRow(LOMTConstant.TEN).getCell(LOMTConstant.ZERO).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.STATE_OR_REGION)) {
				if (worksheet.getRow(LOMTConstant.TEN).getCell(LOMTConstant.ONE) != null) {
				} else {
					logger.log(LogStatus.FAIL, "State or Region is null/empty in exported file");
				}
			} else {
				logger.log(LogStatus.FAIL, "Header : State or Region does not match in exported file");
			}

			// Start Grade
			if (worksheet.getRow(LOMTConstant.ELEVENTH).getCell(LOMTConstant.ZERO).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.START_GRADE)) {
				if (worksheet.getRow(LOMTConstant.ELEVENTH).getCell(LOMTConstant.ONE) != null) {
				} else {
					logger.log(LogStatus.FAIL, "Start Grade is null/empty in exported file");
				}
			} else {
				logger.log(LogStatus.FAIL, "Header : Start Grade does not match in exported file");
			}

			// End Grade
			if (worksheet.getRow(LOMTConstant.TWELEVE).getCell(LOMTConstant.ZERO).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.END_GRADE)) {
				if (worksheet.getRow(LOMTConstant.TWELEVE).getCell(LOMTConstant.ONE) != null) {
				} else {
					logger.log(LogStatus.FAIL, "End Grade is null/empty in exported file");
				}
			} else {
				logger.log(LogStatus.FAIL, "Header : End Grade does not match in exported file");
			}

			// ISBN10
			if (worksheet.getRow(LOMTConstant.THIRTEEN).getCell(LOMTConstant.ZERO).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.ISBN10)) {
				if (worksheet.getRow(LOMTConstant.THIRTEEN).getCell(LOMTConstant.ONE) != null) {
				} else {
					logger.log(LogStatus.FAIL, "ISBN10 is null/empty in exported file");
				}
			} else {
				logger.log(LogStatus.FAIL, "Header : ISBN10 does not match in exported file");
			}

			// ISBN13
			if (worksheet.getRow(LOMTConstant.FOURTEEN).getCell(LOMTConstant.ZERO).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.ISBN13)) {
				if (worksheet.getRow(LOMTConstant.FOURTEEN).getCell(LOMTConstant.ONE) != null) {
				} else {
					logger.log(LogStatus.FAIL, "ISBN13 is null/empty in exported file");
				}
			} else {
				logger.log(LogStatus.FAIL, "Header : ISBN13 does not match in exported file");
			}

			// Type
			if (worksheet.getRow(LOMTConstant.FIFTEEN).getCell(LOMTConstant.ZERO).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.TYPE)) {
				if (worksheet.getRow(LOMTConstant.FIFTEEN).getCell(LOMTConstant.ONE) != null) {
				} else {
					logger.log(LogStatus.FAIL, "Type is null/empty in exported file");
				}
			} else {
				logger.log(LogStatus.FAIL, "Header : Type does not match in exported file");
			}

			// System UUID
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.ZERO).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.SYSTEM_UUID)) {
			} else {
				logger.log(LogStatus.FAIL, "Header : System UUID does not match in exported file");
			}
			// Content Reference
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.ONE).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.CONTENT_REFERENCE)) {
			} else {
				logger.log(LogStatus.FAIL, "Header : Content Reference does not match in exported file");
			}
			// Start Page
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.TWO).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.START_PAGE)) {
			} else {
				logger.log(LogStatus.FAIL, "Header : Start Page does not match in exported file");
			}
			// End Page
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.THREE).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.END_PAGE)) {
			} else {
				logger.log(LogStatus.FAIL, "Header : End Page does not match in exported file");
			}
			// Correlation Score
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.FOUR).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.CORRELATION_SCORE)) {
			} else {
				logger.log(LogStatus.FAIL, "Header : Correlation Score does not match in exported file");
			}
			// Strength
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.FIVE).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.STRENGTH)) {
			} else {
				logger.log(LogStatus.FAIL, "Header : Strength does not match in exported file");
			}
			// Peripheral Alignments
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.SIX).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.PERIPHERAL_ALIGNMENTS)) {
			} else {
				logger.log(LogStatus.FAIL, "Header : Peripheral Alignments does not match in exported file");
			}

			// Empty Column Check
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.SEVEN) == null) {
			} else {
				logger.log(LogStatus.FAIL, "Column H is not blank in exported file");
			}
			
			// Standard Strands
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.EIGHT).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.STANDARDS_STRANDS)) {
			} else {
				logger.log(LogStatus.FAIL, "Header : Standard Strands does not match in exported file");
			}
			// Standard Topics
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.NINE).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.STANDARDS_TOPICS)) {
			} else {
				logger.log(LogStatus.FAIL, "Header : Standard Topics does not match in exported file");
			}
			// Standard Number
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.TEN).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.STANDARDS_NUMBER)) {
			} else {
				logger.log(LogStatus.FAIL, "Header : Standard Number does not match in exported file");
			}
			// ABGUID
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.ELEVENTH).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.AB_GUIDE)) {
			} else {
				logger.log(LogStatus.FAIL, "Header : ABGUID does not match in exported file");
			}
			// Correlation Score
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.TWELEVE).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.CORRELATION_SCORE)) {
			} else {
				logger.log(LogStatus.FAIL, "Header : Correlation Score for Standard does not match in exported file");
			}
			// Strength
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.THIRTEEN).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.STRENGTH)) {
			} else {
				logger.log(LogStatus.FAIL, "Header : Strength for Standard does not match in exported file");
			}
			// Peripheral Alignments
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.FOURTEEN).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.PERIPHERAL_ALIGNMENTS)) {
			} else {
				logger.log(LogStatus.FAIL, "Header : Peripheral Alignments for Standard does not match in exported file");
			}

		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Unable to verify Headers in exported file");
		}
	}

}
