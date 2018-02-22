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
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
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
	private String userName = LoadPropertiesFile.getPropertiesValues(LOMTConstant.USER_NAME);
	//private String userName = LoadPropertiesFile.getPropertiesValues(LOMTConstant.USER_NAME_TEST);
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
			}else if(reportName.contains(ReportsConstant.REVERSE_TOC_STANDARD_VIA_INT_TEXT)){
				reportType = ReportsConstant.REVERSE_TOC_STANDARD_VIA_INT_TEXT;
				verifyReverseTocToStandardViaIntermediaryReportHeaders(worksheet, logger, reportName);
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

	public void verifyReverseTocToStandardViaIntermediaryReportHeaders(XSSFSheet worksheet, ExtentTest logger,
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
			// Standard
			if (worksheet.getRow(LOMTConstant.FIVE).getCell(LOMTConstant.SEVEN).getStringCellValue().trim()
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
			if (worksheet.getRow(LOMTConstant.SIX).getCell(LOMTConstant.SEVEN).getStringCellValue().trim()
					.equalsIgnoreCase(ReportsConstant.TITLE)) {
				if (worksheet.getRow(LOMTConstant.SIX).getCell(LOMTConstant.EIGHT) != null) {
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
			if (worksheet.getRow(LOMTConstant.SEVEN).getCell(LOMTConstant.SEVEN).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.COUNTRY)) {
				if (worksheet.getRow(LOMTConstant.SEVEN).getCell(LOMTConstant.EIGHT) != null) {
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
			if (worksheet.getRow(LOMTConstant.EIGHT).getCell(LOMTConstant.SEVEN).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.GRADE)) {
				if (worksheet.getRow(LOMTConstant.EIGHT).getCell(LOMTConstant.EIGHT) != null) {
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

			// COMPONENT TOC
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.ZERO).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.COMPONENT_TOC)) {
			} else {
				logger.log(LogStatus.FAIL, "Header : COMPONENT TOC does not match in exported file");
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

			// Empty Column Check
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.SIX) == null) {
			} else {
				logger.log(LogStatus.FAIL, "Column G is not blank in exported file");
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
			} else if (reportType.equalsIgnoreCase(ReportsConstant.REVERSE_TOC_STANDARD_VIA_INT_TEXT)) {
				reportsPOM.getReverseToCStandardintermediaryReport().click();
			}
			//Thread.sleep(6000);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			// pivot select and click
			clickIngestedIntermediaryDiscipline(pivot);
			reportsPOM.getSchoolModelWindowNextButton().click();
			// Thread.sleep(2000);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			Thread.sleep(3000);
			if (!reportType.equalsIgnoreCase(ReportsConstant.PRODUCT_INT_TEXT)) {
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
			reportName = createAndDownloadReport(reportType, source, pivot, target, wait, jse, logger);
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Forward Indirect Intermediary Report either Creation or download is failed");
			return reportName;
		}
		return reportName;
	}

	public String createAndDownloadReport(String reportType, String source, String pivot, String target,
			WebDriverWait wait, JavascriptExecutor jse, ExtentTest logger) throws IOException, InterruptedException {
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

		//Selection of report based on coming Report Type
		if (reportType.equalsIgnoreCase(ReportsConstant.FORWARD_INDIRECT_INTERMEDIARY_REPORT)) {
			jse.executeScript("window.scrollBy(0, 400)");
			reportsPOM.getForwardIndirectIntermediaryReport().click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			
			clickIngestedIntermediaryDiscipline(pivot);

			reportsPOM.getSchoolModelWindowNextButton().click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			
			reportName = ReportsConstant.FORWARD_INDIRECT_INT_REPORT_FILE_NAME + "-"
					+ String.valueOf(1300 + (int) Math.round(Math.random() * (1100 - 1200)));
		}
		if (reportType.equalsIgnoreCase(ReportsConstant.FORWARD_DIRECT_REPORT)) {
			reportsPOM.getForwardDirectReport().click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			
			clickIngestedIntermediaryDiscipline(pivot);

			reportsPOM.getSchoolModelWindowNextButton().click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			Thread.sleep(5000);
			//searching Target(Product)
			jse.executeScript("window.scrollBy(0, -300)");
			reportsPOM.getProductReportEnterSearchTerm().sendKeys(target);
			reportsPOM.getProductReportUpdateResultBtn().click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			jse.executeScript("window.scrollBy(0, 300)");
			
			reportsPOM.getProductTargetLink().click();
			reportsPOM.getSchoolModelWindowNextButton().click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			
			reportName = ReportsConstant.FORWARD_DIRECT_REPORT + "-"+ String.valueOf(1300 + (int) Math.round(Math.random() * (1000 - 1100)));
			
		}
		if (reportType.equalsIgnoreCase(ReportsConstant.GAP_ANALYSIS_REPORT)) {
			jse.executeScript("window.scrollBy(0, 200)");
			reportsPOM.getGapAnalysisReport().click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			
			schoolPOM.getEnterEnterSearch().sendKeys(target);

			schoolPOM.getSchoolUpdateResultButton().click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			jse.executeScript("window.scrollBy(0, 300)");
			
			reportsPOM.getCsTargetBtn().click();			
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			
			reportsPOM.getSchoolModelWindowNextButton().click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			
			reportName = ReportsConstant.GAP_ANALYSIS_REPORT + "-"+ String.valueOf(1300 + (int) Math.round(Math.random() * (1000 - 1100)));
		}
		if (reportType.equalsIgnoreCase(ReportsConstant.SUMMARY_REPORT)) {
			jse.executeScript("window.scrollBy(0, 500)");
			reportsPOM.getSummaryReport().click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			
			schoolPOM.getEnterEnterSearch().sendKeys(target);

			schoolPOM.getSchoolUpdateResultButton().click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			jse.executeScript("window.scrollBy(0, 300)");
			
			reportsPOM.getCsTargetBtn().click();			
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			
			reportsPOM.getSchoolModelWindowNextButton().click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			
			reportName = ReportsConstant.SUMMARY_TEXT + "-"+ String.valueOf(1300 + (int) Math.round(Math.random() * (1000 - 1100)));
		}
		if (reportType.equalsIgnoreCase(ReportsConstant.FOWARD_SHARED_INTERMEDIARY_REPORT)) {
			jse.executeScript("window.scrollBy(0, 500)");
			reportsPOM.getForwardSharedIntermediaryReport().click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			
			clickIngestedIntermediaryDiscipline(pivot);
			reportsPOM.getSchoolModelWindowNextButton().click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			Thread.sleep(3000);
			
			productTocPOM.getEnterSearchTerm().sendKeys(target);
			productTocPOM.getUpdateResultButton().click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			
			jse.executeScript("window.scrollBy(0,400)");
			reportsPOM.getSelectTarget().click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			
			reportsPOM.getSchoolModelWindowNextButton().click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			
			reportName = ReportsConstant.FOWARD_SHARED_INTERMEDIARY_REPORT + "-"+ String.valueOf(1300 + (int) Math.round(Math.random() * (1000 - 1100)));
			logger.log(LogStatus.PASS, "TC_LOMT-1838-01_Admin_User_School_Global_Report_Export_Download_Forward_Direct_Report"); 
		}
		
		System.out.println(reportType+ " : "+reportName);

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

	public boolean searchAndExportReport(String reportName, String uName) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 120);
		boolean flag = false;
		try {
			removeExistingFile();
			commonPOM.getSchoolGlobalLOB().click();
			if (uName.equalsIgnoreCase(learningEditor) || uName.equalsIgnoreCase(userName)) {
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
	
	public Map<String, List<String>> verifyExportedFile(String reportType, String reportName, ExtentTest logger, String source, String pivot, String target) {
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
			
			if (reportType.equalsIgnoreCase(ReportsConstant.FORWARD_INDIRECT_INTERMEDIARY_REPORT)) {
				verifyForwardIIReportHeaders(worksheet, reportName, logger);
				getCurriculumStandardAndIntermediaryDataFromExportedSheet(worksheet, forwardIIRepMap);
			} 
			if (reportType.equalsIgnoreCase(ReportsConstant.FORWARD_DIRECT_REPORT)) {
				verifyForwardDirectReportHeaders(worksheet, reportName, logger);
				//preparing CS and TOC list for UI verification
				//Verifying CS and TOC Correlation Score, Strength and Peripheral Alignments data
				getForwardDirectReportData(worksheet, forwardIIRepMap); 
				verifyForwardDirectReportData(worksheet, logger);
			}
			if (reportType.equalsIgnoreCase(ReportsConstant.GAP_ANALYSIS_REPORT)) {
				verifyGapAnalysisReportHeaders(worksheet, reportName, logger, source, target);
				verifyGapAnalysisReportData(worksheet, reportName, logger);
			}
			if (reportType.equalsIgnoreCase(ReportsConstant.SUMMARY_REPORT)) {
				verifySummaryReportHeaders(worksheet, reportName, logger, source, target);
				verifySummaryReportData(worksheet, reportName, logger);
			}
			if (reportType.equalsIgnoreCase(ReportsConstant.FOWARD_SHARED_INTERMEDIARY_REPORT)) {
				verifyForwardSharedIntermediaryReportHeaders(worksheet, reportName, logger, source, pivot, target);
				verifyForwardSharedIntermediaryReportData(worksheet, reportName, logger);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return forwardIIRepMap;
	}
	
	public void verifyForwardDirectReportHeaders(XSSFSheet worksheet, String reportName, ExtentTest logger) {
		try {
			//Title
			if (worksheet.getRow(LOMTConstant.ZERO).getCell(LOMTConstant.ZERO).getStringCellValue().trim().equalsIgnoreCase(ReportsConstant.TITLE)) {
				if(worksheet.getRow(LOMTConstant.ZERO).getCell(LOMTConstant.ONE).getStringCellValue() != null &&
						worksheet.getRow(LOMTConstant.ZERO).getCell(LOMTConstant.ONE).getStringCellValue().equalsIgnoreCase(reportName)) {
				} else {
					logger.log(LogStatus.FAIL, "Title value(report name) does not match in exported file");
				}
			} else {
				logger.log(LogStatus.FAIL, "Header : Title does not match in exported file");
			}
			
			//User
			if (worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.ZERO).getStringCellValue().trim().equalsIgnoreCase(ReportsConstant.USER.trim())) {
				if (worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.ONE).getStringCellValue().equalsIgnoreCase(ReportsConstant.ADMIN_USER_COMMON)
						|| worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.ONE).getStringCellValue().equalsIgnoreCase(ReportsConstant.ADMIN_USER_PPE)
						|| worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.ONE).getStringCellValue().equalsIgnoreCase(ReportsConstant.LEARNING_USER_PPE)
						|| worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.ONE).getStringCellValue().equalsIgnoreCase(ReportsConstant.SME_USER)
						|| worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.ONE).getStringCellValue().equalsIgnoreCase(ReportsConstant.EDITOR_USER) ) {
				} else {
					logger.log(LogStatus.FAIL, "User name does not match in exported file");
				}
			} else {
				logger.log(LogStatus.FAIL, "Header : User does not match in exported file");
			}
			
			//Date/time of generation
			if (worksheet.getRow(LOMTConstant.TWO).getCell(LOMTConstant.ZERO).getStringCellValue().trim().equalsIgnoreCase(ReportsConstant.DATE_TIME_GENERATION.trim())) {
				if (isValidFormat(worksheet.getRow(LOMTConstant.TWO).getCell(LOMTConstant.ONE).getStringCellValue().trim())) {
				} else {
					logger.log(LogStatus.FAIL, "Date/time of generation format does not match in exported file");
				}
			} else {
				logger.log(LogStatus.FAIL, "Header : Date/time of generation does not match in exported file");
			}
			
			//Alignments ALIGNMENTS
			if (worksheet.getRow(LOMTConstant.THREE).getCell(LOMTConstant.ZERO).getStringCellValue().trim().equalsIgnoreCase(ReportsConstant.ALIGNMENTS)) {
				if (worksheet.getRow(LOMTConstant.THREE).getCell(LOMTConstant.ONE).getStringCellValue().contains(ReportsConstant.CENTRAL)
						|| worksheet.getRow(LOMTConstant.THREE).getCell(LOMTConstant.ONE).getStringCellValue().contains(ReportsConstant.PERIPHERAL)) {
				} else {
					logger.log(LogStatus.FAIL, "Alignments value(Central/Peripheral) does not match in exported file");
				}
			} else {
				logger.log(LogStatus.FAIL, "Header : Alignments does not match in exported file");
			}
			
			//Standard
			if (worksheet.getRow(LOMTConstant.FIVE).getCell(LOMTConstant.ZERO).getStringCellValue().trim().equalsIgnoreCase(ReportsConstant.STANDARD)) {
			} else {
				logger.log(LogStatus.FAIL, "Header : Standard does not match in exported file");
			}
			
			//Title
			if (worksheet.getRow(LOMTConstant.SIX).getCell(LOMTConstant.ZERO).getStringCellValue().trim().equalsIgnoreCase(ReportsConstant.TITLE)) {
				if (worksheet.getRow(LOMTConstant.SIX).getCell(LOMTConstant.ONE).getStringCellValue() != null) {
				} else {
					logger.log(LogStatus.FAIL, "Curriculum Standard should not be null in exported file");
				}
			} else {
				logger.log(LogStatus.FAIL, "Header : Standard does not match in exported file");
			}
			
			//Country
			if (worksheet.getRow(LOMTConstant.SEVEN).getCell(LOMTConstant.ZERO).getStringCellValue().equalsIgnoreCase(ReportsConstant.COUNTRY)) {
				if (worksheet.getRow(LOMTConstant.SEVEN).getCell(LOMTConstant.ONE).getStringCellValue() != null) {
				} else {
					logger.log(LogStatus.FAIL, "Country should not be null in exported file");
				}
			} else {
				logger.log(LogStatus.FAIL, "Header : Country does not match in exported file");
			}
			
			//Grade
			if (worksheet.getRow(LOMTConstant.EIGHT).getCell(LOMTConstant.ZERO).getStringCellValue().equalsIgnoreCase(ReportsConstant.GRADE)) {
				if (worksheet.getRow(LOMTConstant.EIGHT).getCell(LOMTConstant.ONE).getStringCellValue() != null) {
				} else {
					logger.log(LogStatus.FAIL, "Grade should not be null in exported file");
				}
			} else {
				logger.log(LogStatus.FAIL, "Header : Grade does not match in exported file");
			}
			
			//Content
			if (worksheet.getRow(LOMTConstant.FIVE).getCell(LOMTConstant.EIGHT).getStringCellValue().equalsIgnoreCase(ReportsConstant.CONTENT)) {
			} else {
				logger.log(LogStatus.FAIL, "Header : Content does not match in exported file");
			}
			
			//Program
			if (worksheet.getRow(LOMTConstant.SIX).getCell(LOMTConstant.EIGHT).getStringCellValue().equalsIgnoreCase(ReportsConstant.PROGRAM)) {
				if (worksheet.getRow(LOMTConstant.SIX).getCell(LOMTConstant.NINE).getStringCellValue() != null) {
				} else {
					logger.log(LogStatus.FAIL, "Program value should not be null in exported file");
				}
			} else {
				logger.log(LogStatus.FAIL, "Header : Program does not match in exported file");
			}
			
			//Course
			if (worksheet.getRow(LOMTConstant.SEVEN).getCell(LOMTConstant.EIGHT).getStringCellValue().equalsIgnoreCase(ReportsConstant.COURSE)) {
				if (worksheet.getRow(LOMTConstant.SEVEN).getCell(LOMTConstant.NINE).getStringCellValue() != null) {
				} else {
					logger.log(LogStatus.FAIL, "Course value should not be null in exported file");
				}
			} else {
				logger.log(LogStatus.FAIL, "Header : Course does not match in exported file");
			}
			
			//Product
			if (worksheet.getRow(LOMTConstant.EIGHT).getCell(LOMTConstant.EIGHT).getStringCellValue().equalsIgnoreCase(ReportsConstant.PRODUCT)) {
				if (worksheet.getRow(LOMTConstant.EIGHT).getCell(LOMTConstant.NINE).getStringCellValue() != null) {
				} else {
					logger.log(LogStatus.FAIL, "Product value should not be null in exported file");
				}
			} else {
				logger.log(LogStatus.FAIL, "Header : Product does not match in exported file");
			}
			
			//Geographic Area or Country
			if (worksheet.getRow(LOMTConstant.NINE).getCell(LOMTConstant.EIGHT).getStringCellValue().equalsIgnoreCase(ReportsConstant.GEOGRAPHIC_AREA_OR__COUNTRY)) {
			} else {
				logger.log(LogStatus.FAIL, "Header : Geographic Area or Country does not match in exported file");
			}
			
			//State or Region
			if (worksheet.getRow(LOMTConstant.TEN).getCell(LOMTConstant.EIGHT).getStringCellValue().equalsIgnoreCase(ReportsConstant.STATE_OR_REGION)) {
				if (worksheet.getRow(LOMTConstant.TEN).getCell(LOMTConstant.NINE).getStringCellValue() != null) {
				} else {
					logger.log(LogStatus.FAIL, "STATE_OR_REGION value should not be null in exported file");
				}
			} else {
				logger.log(LogStatus.FAIL, "Header : STATE_OR_REGION does not match in exported file");
			}

			//Start Grade
			if (worksheet.getRow(LOMTConstant.ELEVENTH).getCell(LOMTConstant.EIGHT).getStringCellValue().equalsIgnoreCase(ReportsConstant.START_GRADE)) {
				if (worksheet.getRow(LOMTConstant.ELEVENTH).getCell(LOMTConstant.NINE).getStringCellValue() != null) {
				} else {
					logger.log(LogStatus.FAIL, "Start Grade value should not be null in exported file");
				}
			} else {
				logger.log(LogStatus.FAIL, "Header : Start Grade does not match in exported file");
			}
			
			//End Grade
			if (worksheet.getRow(LOMTConstant.TWELEVE).getCell(LOMTConstant.EIGHT).getStringCellValue().equalsIgnoreCase(ReportsConstant.END_GRADE)) {
				if (worksheet.getRow(LOMTConstant.TWELEVE).getCell(LOMTConstant.NINE).getStringCellValue() != null) {
				} else {
					logger.log(LogStatus.FAIL, "End Grade value should not be null in exported file");
				}
			} else {
				logger.log(LogStatus.FAIL, "Header : End Grade does not match in exported file");
			}
			
			//ISBN 10
			if (worksheet.getRow(LOMTConstant.THIRTEEN).getCell(LOMTConstant.EIGHT).getStringCellValue().equalsIgnoreCase(ReportsConstant.ISBN10)) {
				if (worksheet.getRow(LOMTConstant.THIRTEEN).getCell(LOMTConstant.NINE).getStringCellValue() != null) {
				} else {
					logger.log(LogStatus.FAIL, "ISBN 10 value should not be null in exported file");
				}
			} else {
				logger.log(LogStatus.FAIL, "Header : ISBN 10 does not match in exported file");
			}
			
			//ISBN 13
			if (worksheet.getRow(LOMTConstant.FOURTEEN).getCell(LOMTConstant.EIGHT).getStringCellValue().equalsIgnoreCase(ReportsConstant.ISBN13)) {
				if (worksheet.getRow(LOMTConstant.FOURTEEN).getCell(LOMTConstant.NINE).getStringCellValue() != null) {
				} else {
					logger.log(LogStatus.FAIL, "ISBN 13 value should not be null in exported file");
				}
			} else {
				logger.log(LogStatus.FAIL, "Header : ISBN 13 does not match in exported file");
			}
			
			//Type
			if (worksheet.getRow(LOMTConstant.FIFTEEN).getCell(LOMTConstant.EIGHT).getStringCellValue().equalsIgnoreCase(ReportsConstant.TYPE)) {
				if (worksheet.getRow(LOMTConstant.FIFTEEN).getCell(LOMTConstant.NINE).getStringCellValue() != null) {
				} else {
					logger.log(LogStatus.FAIL, "TYPE value should not be null in exported file");
				}
			} else {
				logger.log(LogStatus.FAIL, "Header : TYPE does not match in exported file");
			}

			//Standards' Strands
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.ZERO).getStringCellValue().equalsIgnoreCase(ReportsConstant.STANDARDS_STRANDS)) {
			} else {
				logger.log(LogStatus.FAIL, "Header : Standards' Strands does not match in exported file");
			}
			
			//Standards' Topics
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.ONE).getStringCellValue().equalsIgnoreCase(ReportsConstant.STANDARDS_TOPICS)) {
			} else {
				logger.log(LogStatus.FAIL, "Header : Standards' Topics does not match in exported file");
			}
			
			//Standard Number
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.TWO).getStringCellValue().equalsIgnoreCase(ReportsConstant.STANDARDS_NUMBER)) {
			} else {
				logger.log(LogStatus.FAIL, "Header : Standard Number does not match in exported file");
			}
			
			//AB GUID
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.THREE).getStringCellValue().equalsIgnoreCase(ReportsConstant.AB_GUIDE)) {
			} else {
				logger.log(LogStatus.FAIL, "Header : AB GUID does not match in exported file");
			}
			
			//CORRELATION_SCORE
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.FOUR).getStringCellValue().equalsIgnoreCase(ReportsConstant.CORRELATION_SCORE)) {
			} else {
				logger.log(LogStatus.FAIL, "Header : Correlation Score does not match in exported file");
			}
			
			//Strength
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.FIVE).getStringCellValue().equalsIgnoreCase(ReportsConstant.STRENGTH)) {
			} else {
				logger.log(LogStatus.FAIL, "Header : Strength does not match in exported file");
			}
			
			//Peripheral Alignments
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.SIX).getStringCellValue().equalsIgnoreCase(ReportsConstant.PERIPHERAL_ALIGNMENTS)) {
			} else {
				logger.log(LogStatus.FAIL, "Header : Peripheral Alignments does not match in exported file");
			}
			
			//Content Reference
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.EIGHT).getStringCellValue().equalsIgnoreCase(ReportsConstant.CONTENT_REFERENCE)) {
			} else {
				logger.log(LogStatus.FAIL, "Header : Content Reference does not match in exported file");
			}
			
			//Page Start
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.NINE).getStringCellValue().equalsIgnoreCase(ReportsConstant.START_PAGE)) {
			} else {
				logger.log(LogStatus.FAIL, "Header : Page Start does not match in exported file");
			}
			
			//Page End
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.TEN).getStringCellValue().equalsIgnoreCase(ReportsConstant.END_PAGE)) {
			} else {
				logger.log(LogStatus.FAIL, "Header : Page End does not match in exported file");
			}
			
			//Correlation Score
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.ELEVENTH).getStringCellValue().equalsIgnoreCase(ReportsConstant.CORRELATION_SCORE)) {
			} else {
				logger.log(LogStatus.FAIL, "Header : Correlation Score does not match in exported file");
			}
			
			//Strength
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.TWELEVE).getStringCellValue().equalsIgnoreCase(ReportsConstant.STRENGTH)) {
			} else {
				logger.log(LogStatus.FAIL, "Header : Strength does not match in exported file");
			}
			
			//Peripheral Alignments
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.THIRTEEN).getStringCellValue().equalsIgnoreCase(ReportsConstant.PERIPHERAL_ALIGNMENTS)) {
			} else {
				logger.log(LogStatus.FAIL, "Header : Peripheral Alignments does not match in exported file");
			}
			
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Unable to verify Headers in exported file");
		}
	}
	
	public Map<String, List<String>> getForwardDirectReportData(XSSFSheet worksheet, Map<String, List<String>> forwardIIRepMap) {
		try {
			List<String> curriculumStdList = new LinkedList<String>();
			List<String> tocList = new LinkedList<String>();

			Iterator<Row> rowIterator = worksheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				
				//CS goalframework name
				if (row.getRowNum() == 6) {
					//adding Curriculum Standard and Intermediary for UI   
					//verification
					curriculumStdList.add(row.getCell(1).getStringCellValue());
				}
				//TOC goalframework name
				if (row.getRowNum() == 8) {
					tocList.add(row.getCell(9).getStringCellValue());
				}

				if (row.getRowNum() > 17) {  //18 row
					if (!(row.getCell(1) == null)) {
						curriculumStdList.add(row.getCell(1).getStringCellValue());
					}
					if (!(row.getCell(11) == null)) {
						tocList.add(row.getCell(8).getStringCellValue());
					}
				}
			}
			forwardIIRepMap.put("CurriculumStandard", curriculumStdList);
			forwardIIRepMap.put("Discipline", tocList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return forwardIIRepMap;
	}
	
	public void verifyForwardDirectReportData(XSSFSheet worksheet, ExtentTest logger) {
		try {
			int counter = 1;

			Iterator<Row> rowIterator = worksheet.iterator();
			//Verifying CS Correlation Score, Strength and Peripheral Alignments
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				
				if (row.getRowNum() > 18) {
					if (row.getCell(1) != null) {
						Map<String, List<String>> csMap = ReportsConstant.getForwardDirectCSTestData();
						
						List<String> cslist = csMap.get(String.valueOf(counter));
						if (row.getCell(1).getStringCellValue().equalsIgnoreCase(cslist.get(0))) {
						} else {
							logger.log(LogStatus.FAIL, "CS: Standard Topics does not match with test data, Row num : "+row.getRowNum());
						}
						if (row.getCell(4).getStringCellValue().equalsIgnoreCase(cslist.get(1))) {
						} else {
							logger.log(LogStatus.FAIL, "CS: Correlation Score does not match with test data, Row num : "+row.getRowNum());
						}
						if (row.getCell(5).getStringCellValue().equalsIgnoreCase(cslist.get(2))) {
						} else {
							logger.log(LogStatus.FAIL, "CS: Strength does not match with test data, Row num : "+row.getRowNum());
						}
						if (row.getCell(6).getStringCellValue().equalsIgnoreCase(cslist.get(3))) {
						} else {
							logger.log(LogStatus.FAIL, "CS: Peripheral Alignments does not match with test data, Row num : "+row.getRowNum());
						}
						counter++;
						cslist.clear();
					}
				}
			}
			counter = 1;
			Iterator<Row> rowIteratorTOC = worksheet.iterator();
			//Verifying TOC Correlation Score, Strength and Peripheral Alignments
			while (rowIteratorTOC.hasNext()) {
				Row row = rowIteratorTOC.next();
				if (row.getRowNum() > 18) {
					if (row.getCell(8) != null) {
						Map<String, List<String>> csMap = ReportsConstant.getForwardDirectCSTestData();
						List<String> cslist = csMap.get(String.valueOf(counter));
						if (row.getCell(8).getStringCellValue().equalsIgnoreCase(cslist.get(0))) {
						} else {
							logger.log(LogStatus.FAIL, "TOC: Standard Topics does not match with test data, Row num : "+row.getRowNum());
						}
						if (row.getCell(11).getStringCellValue().equalsIgnoreCase(cslist.get(1))) {
						} else {
							logger.log(LogStatus.FAIL, "TOC: Correlation Score does not match with test data, Row num : "+row.getRowNum());
						}
						if (row.getCell(12).getStringCellValue().equalsIgnoreCase(cslist.get(2))) {
						} else {
							logger.log(LogStatus.FAIL, "TOC: Strength does not match with test data, Row num : "+row.getRowNum());
						}
						if (row.getCell(13).getStringCellValue().equalsIgnoreCase(cslist.get(3))) {
						} else {
							logger.log(LogStatus.FAIL, "TOC: Peripheral Alignments does not match with test data, Row num : "+row.getRowNum());
						}
						counter++;
						cslist.clear();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void verifySummaryReportHeaders(XSSFSheet worksheet, String reportName, ExtentTest logger, String source, String target) {
		try {
			int count = 0;
			//Title
			if (worksheet.getRow(LOMTConstant.ZERO).getCell(LOMTConstant.ZERO).getStringCellValue().trim().equalsIgnoreCase(ReportsConstant.TITLE)) {
				if(worksheet.getRow(LOMTConstant.ZERO).getCell(LOMTConstant.ONE).getStringCellValue() != null &&
						worksheet.getRow(LOMTConstant.ZERO).getCell(LOMTConstant.ONE).getStringCellValue().equalsIgnoreCase(reportName)) {
				} else {
					count++;
					logger.log(LogStatus.FAIL, "TC_LOMT-1841-Title value does not match in exported file");
				}
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1841-Title header does not match in exported file");
			}
			
			//User
			if (worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.ZERO).getStringCellValue().trim().equalsIgnoreCase(ReportsConstant.USER.trim())) {
				if (worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.ONE).getStringCellValue().equalsIgnoreCase(ReportsConstant.ADMIN_USER_COMMON)
						|| worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.ONE).getStringCellValue().equalsIgnoreCase(ReportsConstant.ADMIN_USER_PPE)
						|| worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.ONE).getStringCellValue().equalsIgnoreCase(ReportsConstant.LEARNING_USER_PPE)
						|| worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.ONE).getStringCellValue().equalsIgnoreCase(ReportsConstant.SME_USER)
						|| worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.ONE).getStringCellValue().equalsIgnoreCase(ReportsConstant.EDITOR_USER) ) {
				} else {
					count++;
					logger.log(LogStatus.FAIL, "TC_LOMT-1841-User name does not match in exported file");
				}
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1841-Username header does not match in exported file");
			}
			
			//Date/time of generation
			if (worksheet.getRow(LOMTConstant.TWO).getCell(LOMTConstant.ZERO).getStringCellValue().trim().equalsIgnoreCase(ReportsConstant.DATE_TIME_GENERATION.trim())) {
				if (isValidFormat(worksheet.getRow(LOMTConstant.TWO).getCell(LOMTConstant.ONE).getStringCellValue().trim())) {
				} else {
					count++;
					logger.log(LogStatus.FAIL, "TC_LOMT-1841-Date/time of generation format does not match in exported file");
				}
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1841-Date/time of generation header does not match in exported file");
			}
			
			//ALIGNMENTS
			if (worksheet.getRow(LOMTConstant.THREE).getCell(LOMTConstant.ZERO).getStringCellValue().trim().equalsIgnoreCase(ReportsConstant.ALIGNMENTS)) {
				if (worksheet.getRow(LOMTConstant.THREE).getCell(LOMTConstant.ONE).getStringCellValue().contains(ReportsConstant.EXACT)
						&& worksheet.getRow(LOMTConstant.THREE).getCell(LOMTConstant.ONE).getStringCellValue().contains(ReportsConstant.RELATED)
						&& worksheet.getRow(LOMTConstant.THREE).getCell(LOMTConstant.ONE).getStringCellValue().contains(ReportsConstant.BROAD)
						&& worksheet.getRow(LOMTConstant.THREE).getCell(LOMTConstant.ONE).getStringCellValue().contains(ReportsConstant.CLOSE)
						&& worksheet.getRow(LOMTConstant.THREE).getCell(LOMTConstant.ONE).getStringCellValue().contains(ReportsConstant.NARROW)) {
				} else {
					count++;
					logger.log(LogStatus.FAIL, "TC_LOMT-1841-Alignments value does not match in exported file");
				}
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1841-Alignments header does not match in exported file");
			}
			
			//Standard
			if (worksheet.getRow(LOMTConstant.FIVE).getCell(LOMTConstant.ZERO).getStringCellValue().trim().equalsIgnoreCase(ReportsConstant.STANDARD)) {
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1841-Standard header does not match in exported file");
			}
			
			//Title
			if (worksheet.getRow(LOMTConstant.SIX).getCell(LOMTConstant.ZERO).getStringCellValue().trim().equalsIgnoreCase(ReportsConstant.TITLE)) {
				if (worksheet.getRow(LOMTConstant.SIX).getCell(LOMTConstant.ONE) != null) {
				} else {
					count++;
					logger.log(LogStatus.FAIL, "TC_LOMT-1841-Curriculum Standard should not be null in exported file");
				}
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1841-Title header does not match in exported file");
			}
			
			//Country
			if (worksheet.getRow(LOMTConstant.SEVEN).getCell(LOMTConstant.ZERO).getStringCellValue().equalsIgnoreCase(ReportsConstant.COUNTRY)) {
				if (worksheet.getRow(LOMTConstant.SEVEN).getCell(LOMTConstant.ONE) != null) {
				} else {
					count++;
					logger.log(LogStatus.FAIL, "TC_LOMT-1841-Country should not be null in exported file");
				}
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1841-Country header does not match in exported file");
			}
			
			//Grade
			if (worksheet.getRow(LOMTConstant.EIGHT).getCell(LOMTConstant.ZERO).getStringCellValue().equalsIgnoreCase(ReportsConstant.GRADE)) {
				if (worksheet.getRow(LOMTConstant.EIGHT).getCell(LOMTConstant.ONE) != null) {
				} else {
					count++;
					logger.log(LogStatus.FAIL, "TC_LOMT-1841-Grade should not be null in exported file");
				}
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1841-Grade header does not match in exported file");
			}
			
			//Standard
			if (worksheet.getRow(LOMTConstant.FIVE).getCell(LOMTConstant.THIRTEEN).getStringCellValue().equalsIgnoreCase(ReportsConstant.STANDARD)) {
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1841-Standard header does not match in exported file at column N");
			}
			
			//Title
			if (worksheet.getRow(LOMTConstant.SIX).getCell(LOMTConstant.THIRTEEN).getStringCellValue().equalsIgnoreCase(ReportsConstant.TITLE)) {
				if (worksheet.getRow(LOMTConstant.SIX).getCell(LOMTConstant.FOURTEEN) != null) {
				} else {
					count++;
					logger.log(LogStatus.FAIL, "TC_LOMT-1841-Title value should not be null in exported file at column O");
				}
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1841-Title header does not match in exported file at column N");
			}
			
			//Country
			if (worksheet.getRow(LOMTConstant.SEVEN).getCell(LOMTConstant.THIRTEEN).getStringCellValue().equalsIgnoreCase(ReportsConstant.COUNTRY)) {
				if (worksheet.getRow(LOMTConstant.SEVEN).getCell(LOMTConstant.FOURTEEN) != null) {
				} else {
					count++;
					logger.log(LogStatus.FAIL, "TC_LOMT-1841-Country value should not be null in exported file at column O");
				}
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1841-Country header does not match in exported file at column N");
			}
			
			//Grade
			if (worksheet.getRow(LOMTConstant.EIGHT).getCell(LOMTConstant.THIRTEEN).getStringCellValue().equalsIgnoreCase(ReportsConstant.GRADE)) {
				if (worksheet.getRow(LOMTConstant.EIGHT).getCell(LOMTConstant.FOURTEEN) != null) {
				} else {
					count++;
					logger.log(LogStatus.FAIL, "TC_LOMT-1841-Grade value should not be null in exported file at column O");
				}
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1841-Grade header does not match in exported file at column N");
			}
			
			//mSubject
			if (worksheet.getRow(LOMTConstant.TEN).getCell(LOMTConstant.ZERO).getStringCellValue().equalsIgnoreCase(ReportsConstant.MSUBJECT)) {
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1841-mSubject header does not match in exported file");
			}
			
			//cName
			if (worksheet.getRow(LOMTConstant.TEN).getCell(LOMTConstant.ONE).getStringCellValue().equalsIgnoreCase(ReportsConstant.CNAME)) {
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1841-Header : cName does not match in exported file");
			}
			
			//mYear
			if (worksheet.getRow(LOMTConstant.TEN).getCell(LOMTConstant.TWO).getStringCellValue().equalsIgnoreCase(ReportsConstant.MYEAR)) {
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1841-Header : mYear does not match in exported file");
			}
			
			//mCountry
			if (worksheet.getRow(LOMTConstant.TEN).getCell(LOMTConstant.THREE).getStringCellValue().equalsIgnoreCase(ReportsConstant.MCOUNTRY)) {
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1841-Header : mCountry does not match in exported file");
			}
			
			//mCountryCode
			if (worksheet.getRow(LOMTConstant.TEN).getCell(LOMTConstant.FOUR).getStringCellValue().equalsIgnoreCase(ReportsConstant.MCOUNTRYCODE)) {
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1841-Header : mCountryCode does not match in exported file");
			}
			
			//mLocUri
			if (worksheet.getRow(LOMTConstant.TEN).getCell(LOMTConstant.FIVE).getStringCellValue().equalsIgnoreCase(ReportsConstant.MLOCURI)) {
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1841-Header : mLocUri does not match in exported file");
			}
			
			//mStandard
			if (worksheet.getRow(LOMTConstant.TEN).getCell(LOMTConstant.SIX).getStringCellValue().equalsIgnoreCase(ReportsConstant.MSTANDARD)) {
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1841-Header : mStandard does not match in exported file");
			}
			
			//mCode
			if (worksheet.getRow(LOMTConstant.TEN).getCell(LOMTConstant.SEVEN).getStringCellValue().equalsIgnoreCase(ReportsConstant.MCODE)) {
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1841-Header : mCode does not match in exported file");
			}
			
			//mLabel
			if (worksheet.getRow(LOMTConstant.TEN).getCell(LOMTConstant.EIGHT).getStringCellValue().equalsIgnoreCase(ReportsConstant.MLABEL)) {
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1841-Header : mLabel does not match in exported file");
			}
			
			//mMin
			if (worksheet.getRow(LOMTConstant.TEN).getCell(LOMTConstant.NINE).getStringCellValue().equalsIgnoreCase(ReportsConstant.MMIN)) {
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1841-Header : Page End does not match in exported file");
			}
			
			//mMax
			if (worksheet.getRow(LOMTConstant.TEN).getCell(LOMTConstant.TEN).getStringCellValue().equalsIgnoreCase(ReportsConstant.MMAX)) {
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1841-Header : mMax does not match in exported file");
			}
			
			//aType
			if (worksheet.getRow(LOMTConstant.TEN).getCell(LOMTConstant.ELEVENTH).getStringCellValue().equalsIgnoreCase(ReportsConstant.ATYPE)) {
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1841-Header : aType does not match in exported file");
			}
			
			//aSubject
			if (worksheet.getRow(LOMTConstant.TEN).getCell(LOMTConstant.THIRTEEN).getStringCellValue().equalsIgnoreCase(ReportsConstant.ASUBJECT)) {
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1841-Header : aSubject does not match in exported file");
			}
			
			//aName
			if (worksheet.getRow(LOMTConstant.TEN).getCell(LOMTConstant.FOURTEEN).getStringCellValue().equalsIgnoreCase(ReportsConstant.ANAME)) {
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1841-Header : aName does not match in exported file");
			}
			
			//aYear
			if (worksheet.getRow(LOMTConstant.TEN).getCell(LOMTConstant.FIFTEEN).getStringCellValue().equalsIgnoreCase(ReportsConstant.AYEAR)) {
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1841-Header : aYear does not match in exported file");
			}
			
			//aCountry
			if (worksheet.getRow(LOMTConstant.TEN).getCell(LOMTConstant.SIXTEEN).getStringCellValue().equalsIgnoreCase(ReportsConstant.ACOUNTRY)) {
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1841-Header : aCountry does not match in exported file");
			}
			
			//aCountryCode
			if (worksheet.getRow(LOMTConstant.TEN).getCell(LOMTConstant.SEVENTEEN).getStringCellValue().equalsIgnoreCase(ReportsConstant.ACOUNTRYCODE)) {
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1841-Header : aCountryCode does not match in exported file");
			}
			
			//aLocUri
			if (worksheet.getRow(LOMTConstant.TEN).getCell(LOMTConstant.EIGHTEEN).getStringCellValue().equalsIgnoreCase(ReportsConstant.ALOCURI)) {
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1841-Header : aLocUri does not match in exported file");
			}
			
			//aStandard
			if (worksheet.getRow(LOMTConstant.TEN).getCell(LOMTConstant.NINETEEN).getStringCellValue().equalsIgnoreCase(ReportsConstant.ASTANDARD)) {
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1841-Header : aStandard does not match in exported file");
			}
			
			//aStandard
			if (worksheet.getRow(LOMTConstant.TEN).getCell(LOMTConstant.NINETEEN).getStringCellValue().equalsIgnoreCase(ReportsConstant.ASTANDARD)) {
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1841-Header : aStandard does not match in exported file");
			}
			
			//aCode
			if (worksheet.getRow(LOMTConstant.TEN).getCell(LOMTConstant.TWENTY).getStringCellValue().equalsIgnoreCase(ReportsConstant.ACODE)) {
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1841-Header : aCode does not match in exported file");
			}
			
			//aLabel
			if (worksheet.getRow(LOMTConstant.TEN).getCell(LOMTConstant.TWENTY_ONE).getStringCellValue().equalsIgnoreCase(ReportsConstant.ALABEL)) {
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1841-Header : aLabel does not match in exported file");
			}
			
			//aMin
			if (worksheet.getRow(LOMTConstant.TEN).getCell(LOMTConstant.TWENTY_TWO).getStringCellValue().equalsIgnoreCase(ReportsConstant.AMIN)) {
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1841-Header : aMin does not match in exported file");
			}
			
			//aMax
			if (worksheet.getRow(LOMTConstant.TEN).getCell(LOMTConstant.TWENTY_THREE).getStringCellValue().equalsIgnoreCase(ReportsConstant.AMAX)) {
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1841-Header : aMax does not match in exported file");
			}
			
			if (count == 0) {
				logger.log(LogStatus.PASS, "TC_LOMT-1841-02_All_User_Reports&Exports_download_Summary Report_verify_Report");
			} else {
				logger.log(LogStatus.FAIL, "TC_LOMT-1841-02_All_User_Reports&Exports_download_Summary Report_verify_Report");
				System.out.println(count + " Values not matching for" + reportName);
			}
			
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Unable to verify Headers in exported file");
		}
	}
	
	public void verifySummaryReportData(XSSFSheet worksheet,String reportName, ExtentTest logger) {
		try {
			int counter = 1;

			Iterator<Row> rowIterator = worksheet.iterator();
			//Verifying CS Correlation Score, Strength and Peripheral Alignments
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				
				if (row.getRowNum() == 12) {
					System.out.println("At row number : " + row.getRowNum());
						if (row.getCell(0) != null) {
							} else {counter++;
							logger.log(LogStatus.FAIL, "TC_LOMT-1841 mSubject doesn't match at row number : "+row.getRowNum());
						}
						if (row.getCell(1) != null) {
						} else {counter++;
							logger.log(LogStatus.FAIL, "TC_LOMT-1841 cNAme doesn't match at row number : "+row.getRowNum());
							}
						if (row.getCell(LOMTConstant.SIX) != null && row.getCell(LOMTConstant.SIX).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.LEVEL_1)) {
							System.out.println(row.getCell(LOMTConstant.SIX).getStringCellValue());
							}else {counter++;
								logger.log(LogStatus.FAIL, "TC_LOMT-1841 mStandard doesn't match at row number : "+row.getRowNum());
							}
						if (row.getCell(LOMTConstant.ELEVENTH) != null && row.getCell(LOMTConstant.ELEVENTH).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.EXACT)) {
							logger.log(LogStatus.PASS, "TC_LOMT-1841-03_Admin_user_verify_EXACT_Alignmnet_In_Summary_Report");	
							}else {counter++;
								logger.log(LogStatus.FAIL, "TC_LOMT-1841-03_Admin_user_verify_EXACT_Alignmnet_In_Summary_Report");
							}
						if (row.getCell(LOMTConstant.NINETEEN) != null && row.getCell(LOMTConstant.NINETEEN).getStringCellValue()
								.equalsIgnoreCase(ReportsConstant.ST_1)) {
								}else {counter++;
							logger.log(LogStatus.FAIL, "TC_LOMT-1841 aStandard value should be "+ReportsConstant.ST_1 + "at row number : "+row.getRowNum());
								}
					}
				else if (row.getRowNum() == 13) {
					System.out.println("At row number : " + row.getRowNum());
						if (row.getCell(0) != null) {
							} else {counter++;
							logger.log(LogStatus.FAIL, "TC_LOMT-1841 mSubject doesn't match at row number : "+row.getRowNum());
						}
						if (row.getCell(1) != null) {
						} else {counter++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1841 cNAme doesn't match at row number : "+row.getRowNum());
							}
						if (row.getCell(LOMTConstant.SIX) != null && row.getCell(LOMTConstant.SIX).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.LEVEL_2)) {
							System.out.println(row.getCell(LOMTConstant.SIX).getStringCellValue());
							}else {counter++;
								logger.log(LogStatus.FAIL, "TC_LOMT-1841 mStandard doesn't match at row number : "+row.getRowNum());
							}
						if (row.getCell(LOMTConstant.ELEVENTH) != null && row.getCell(LOMTConstant.ELEVENTH).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.RELATED)) {
							logger.log(LogStatus.PASS, "TC_LOMT-1841-04_Admin_user_verify_RELATED_Alignmnet_In_Summary_Report");
							}else {counter++;
								logger.log(LogStatus.FAIL, "TC_LOMT-1841-04_Admin_user_verify_RELATED_Alignmnet_In_Summary_Report");
							}
						if (row.getCell(LOMTConstant.NINETEEN) != null && row.getCell(LOMTConstant.NINETEEN).getStringCellValue()
								.equalsIgnoreCase(ReportsConstant.ST_2)) {
								}else {counter++;
									logger.log(LogStatus.FAIL, "TC_LOMT-1841 aStandard value should be "+ReportsConstant.ST_2 + "at row number : "+row.getRowNum());
								}
					}
				else if (row.getRowNum() == 14) {
					System.out.println("At row number : " + row.getRowNum());
						if (row.getCell(0) != null) {
							} else {counter++;
							logger.log(LogStatus.FAIL, "TC_LOMT-1841 mSubject doesn't match at row number : "+row.getRowNum());
						}
						if (row.getCell(1) != null) {
						} else {counter++;
							logger.log(LogStatus.FAIL, "TC_LOMT-1841 cNAme doesn't match at row number : "+row.getRowNum());
							}
						if (row.getCell(LOMTConstant.SIX) != null && row.getCell(LOMTConstant.SIX).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.LEVEL_3)) {
							System.out.println(row.getCell(LOMTConstant.SIX).getStringCellValue());
							}else {counter++;
								logger.log(LogStatus.FAIL, "TC_LOMT-1841 mStandard doesn't match at row number : "+row.getRowNum());
							}
						if (row.getCell(LOMTConstant.ELEVENTH) != null && row.getCell(LOMTConstant.ELEVENTH).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.BROAD)) {
							logger.log(LogStatus.PASS, "TC_LOMT-1841-05_Admin_user_verify_BROAD_Alignmnet_In_Summary_Report");
							}else {counter++;
								logger.log(LogStatus.FAIL, "TC_LOMT-1841-05_Admin_user_verify_BROAD_Alignmnet_In_Summary_Report");
							}
						if (row.getCell(LOMTConstant.NINETEEN) != null && row.getCell(LOMTConstant.NINETEEN).getStringCellValue()
								.equalsIgnoreCase(ReportsConstant.ST_3)) {
								}else {counter++;
							logger.log(LogStatus.FAIL, "TC_LOMT-1841 aStandard value should be "+ReportsConstant.ST_3 + "at row number : "+row.getRowNum());
								}
					}
				else if (row.getRowNum() == 15) {
					System.out.println("At row number : " + row.getRowNum());
						if (row.getCell(0) != null) {
							} else {counter++;
							logger.log(LogStatus.FAIL, "TC_LOMT-1841 mSubject doesn't match at row number : "+row.getRowNum());
						}
						if (row.getCell(1) != null) {
						} else {counter++;
							logger.log(LogStatus.FAIL, "TC_LOMT-1841 cNAme doesn't match at row number : "+row.getRowNum());
							}
						if (row.getCell(LOMTConstant.SIX) != null && row.getCell(LOMTConstant.SIX).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.LEVEL_4)) {
							System.out.println(row.getCell(LOMTConstant.SIX).getStringCellValue());
							}else {counter++;
								logger.log(LogStatus.FAIL, "TC_LOMT-1841 mStandard doesn't match at row number : "+row.getRowNum());
							}
						if (row.getCell(LOMTConstant.ELEVENTH) != null && row.getCell(LOMTConstant.ELEVENTH).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.CLOSE)) {
							logger.log(LogStatus.PASS, "TC_LOMT-1841-06_Admin_user_verify_CLOSE_Alignmnet_In_Summary_Report");
							}else {counter++;
								logger.log(LogStatus.FAIL, "TC_LOMT-1841-06_Admin_user_verify_CLOSE_Alignmnet_In_Summary_Report");
							}
						if (row.getCell(LOMTConstant.NINETEEN) != null && row.getCell(LOMTConstant.NINETEEN).getStringCellValue()
								.equalsIgnoreCase(ReportsConstant.ST_4)) {
								}else {counter++;
							    logger.log(LogStatus.FAIL, "TC_LOMT-1841 aStandard value should be "+ReportsConstant.ST_4 + "at row number : "+row.getRowNum());
								}
					}
				else if (row.getRowNum() == 17) {
					System.out.println("At row number : " + row.getRowNum());
						if (row.getCell(0) != null) {
							} else {counter++;
								logger.log(LogStatus.FAIL, "TC_LOMT-1841 mSubject doesn't match at row number : "+row.getRowNum());
						}
						if (row.getCell(1) != null) {
						} else {counter++;
								logger.log(LogStatus.FAIL, "TC_LOMT-1841 cNAme doesn't match at row number : "+row.getRowNum());
							}
						if (row.getCell(LOMTConstant.SIX) != null && row.getCell(LOMTConstant.SIX).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.LEVEL_5)) {
							System.out.println(row.getCell(LOMTConstant.SIX).getStringCellValue());
							}else {counter++;
								logger.log(LogStatus.FAIL, "TC_LOMT-1841 mStandard doesn't match at row number : "+row.getRowNum());
							}
						if (row.getCell(LOMTConstant.ELEVENTH) != null && row.getCell(LOMTConstant.ELEVENTH).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.NARROW)) {
								logger.log(LogStatus.PASS, "TC_LOMT-1841-07_Admin_user_verify_NARROW_Alignmnet_In_Summary_Report");
							}else {counter++;
								logger.log(LogStatus.FAIL, "TC_LOMT-1841-07_Admin_user_verify_NARROW_Alignmnet_In_Summary_Report");
							}
						if (row.getCell(LOMTConstant.NINETEEN) != null && row.getCell(LOMTConstant.NINETEEN).getStringCellValue()
								.equalsIgnoreCase(ReportsConstant.ST_5)) {
								}else {counter++;
									logger.log(LogStatus.FAIL, "TC_LOMT-1841 aStandard value should be "+ReportsConstant.ST_5 + "at row number : "+row.getRowNum());
								}
					}
				if (counter != 1){
					logger.log(LogStatus.FAIL, "TC_LOMT-1841-02_All_User_Reports&Exports_download_Summary Report_verify_Report");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void verifyGapAnalysisReportHeaders(XSSFSheet worksheet, String reportName, ExtentTest logger, String source, String target) {
		try {
			int count = 0;
			//Title
			if (worksheet.getRow(LOMTConstant.ZERO).getCell(LOMTConstant.ZERO).getStringCellValue().trim().equalsIgnoreCase(ReportsConstant.TITLE)) {
				XSSFCellStyle style1 = worksheet.getRow(LOMTConstant.ZERO).getCell(LOMTConstant.ZERO).getCellStyle();
				short blackColourCode = style1.getFillForegroundColorColor().getIndexed();
				if (blackColourCode == IndexedColors.BLACK.getIndex()) {
				} else {
					count++;
					logger.log(LogStatus.FAIL, "TC_LOMT-1840-Title Foreground colour does not match in exported file");
				}
				if (worksheet.getRow(LOMTConstant.ZERO).getCell(LOMTConstant.ONE).getStringCellValue() != null
						&& worksheet.getRow(LOMTConstant.ZERO).getCell(LOMTConstant.ONE).getStringCellValue()
								.equalsIgnoreCase(reportName)) {
				} else {
					count++;
					logger.log(LogStatus.FAIL, "TC_LOMT-1840-Title value does not match in exported file");
				}
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1840-Title header does not match in exported file");
			}

			// User
			if (worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.ZERO).getStringCellValue().trim()
					.equalsIgnoreCase(ReportsConstant.USER.trim())) {
				XSSFCellStyle style1 = worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.ZERO).getCellStyle();
				short blackColourCode = style1.getFillForegroundColorColor().getIndexed();
				if (blackColourCode == IndexedColors.BLACK.getIndex()) {
				} else {
					count++;
					logger.log(LogStatus.FAIL, "TC_LOMT-1840-User Foreground colour does not match in exported file");
				}
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
					count++;
					logger.log(LogStatus.FAIL, "TC_LOMT-1840-User name does not match in exported file");
				}
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1840-User headers does not match in exported file");
			}

			//Date/time of generation
			if (worksheet.getRow(LOMTConstant.TWO).getCell(LOMTConstant.ZERO).getStringCellValue().trim().equalsIgnoreCase(ReportsConstant.DATE_TIME_GENERATION.trim())) {
				XSSFCellStyle style1 = worksheet.getRow(LOMTConstant.TWO).getCell(LOMTConstant.ZERO).getCellStyle();
				short blackColourCode = style1.getFillForegroundColorColor().getIndexed();
				if (blackColourCode == IndexedColors.BLACK.getIndex()) {
				} else {
					count++;
					logger.log(LogStatus.FAIL, "TC_LOMT-1840-Date of generation Foreground colour does not match in exported file");
				}
				
				if (isValidFormat(worksheet.getRow(LOMTConstant.TWO).getCell(LOMTConstant.ONE).getStringCellValue().trim())) {
				} else {
					count++;
					logger.log(LogStatus.FAIL, "TC_LOMT-1840-Date of generation format does not match in exported file");
				}
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1840-Date of generation header does not match in exported file");
			}

			//Alignments 
			if (worksheet.getRow(LOMTConstant.THREE).getCell(LOMTConstant.ZERO).getStringCellValue().trim().equalsIgnoreCase(ReportsConstant.ALIGNMENTS)) {
				XSSFCellStyle style1 = worksheet.getRow(LOMTConstant.THREE).getCell(LOMTConstant.ZERO).getCellStyle();
				short blackColourCode = style1.getFillForegroundColorColor().getIndexed();
				if (blackColourCode == IndexedColors.BLACK.getIndex()) {
				} else {
					count++;
					logger.log(LogStatus.FAIL, "TC_LOMT-1840-Alignments Foreground colour does not match in exported file");
				}
				
				if (worksheet.getRow(LOMTConstant.THREE).getCell(LOMTConstant.ONE).getStringCellValue().contains(ReportsConstant.EXACT)
						&& worksheet.getRow(LOMTConstant.THREE).getCell(LOMTConstant.ONE).getStringCellValue().contains(ReportsConstant.RELATED)
						&& worksheet.getRow(LOMTConstant.THREE).getCell(LOMTConstant.ONE).getStringCellValue().contains(ReportsConstant.BROAD)
						&& worksheet.getRow(LOMTConstant.THREE).getCell(LOMTConstant.ONE).getStringCellValue().contains(ReportsConstant.CLOSE)
						&& worksheet.getRow(LOMTConstant.THREE).getCell(LOMTConstant.ONE).getStringCellValue().contains(ReportsConstant.NARROW)) {
				} else {
					count++;
					logger.log(LogStatus.FAIL, "TC_LOMT-1840-Alignments value(Central/Peripheral) does not match in exported file");
				}
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1840-Alignments header does not match in exported file");
			}

			//Standard - Left side
			if (worksheet.getRow(LOMTConstant.FIVE).getCell(LOMTConstant.ZERO).getStringCellValue().trim().equalsIgnoreCase(ReportsConstant.STANDARD)) {
				XSSFCellStyle style1 = worksheet.getRow(LOMTConstant.THREE).getCell(LOMTConstant.ZERO).getCellStyle();
				short blackColourCode = style1.getFillForegroundColorColor().getIndexed();
				if (blackColourCode == IndexedColors.BLACK.getIndex()) {
				} else {
					count++;
					logger.log(LogStatus.FAIL, "TC_LOMT-1840-Standard Foreground colour does not match in exported file");
				}
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1840-Standard header does not match in exported file");
			}
			
			//Standard - Right side
			if (worksheet.getRow(LOMTConstant.FIVE).getCell(LOMTConstant.EIGHT).getStringCellValue().trim().equalsIgnoreCase(ReportsConstant.STANDARD)) {
				XSSFCellStyle style1 = worksheet.getRow(LOMTConstant.FIVE).getCell(LOMTConstant.EIGHT).getCellStyle();
				short blackColourCode = style1.getFillForegroundColorColor().getIndexed();
				if (blackColourCode == IndexedColors.BLACK.getIndex()) {
				} else {
					count++;
					logger.log(LogStatus.FAIL, "TC_LOMT-1840-Standard Foreground colour does not match in exported file");
				}
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1840-Standard header does not match in exported file");
			}

			// Title - Left side
			if (worksheet.getRow(LOMTConstant.SIX).getCell(LOMTConstant.ZERO).getStringCellValue().trim().equalsIgnoreCase(ReportsConstant.TITLE)) {
				XSSFCellStyle style1 = worksheet.getRow(LOMTConstant.SIX).getCell(LOMTConstant.ZERO).getCellStyle();
				short blackColourCode = style1.getFillForegroundColorColor().getIndexed();
				if (blackColourCode == IndexedColors.BLACK.getIndex()) {
				} else {
					count++;
					logger.log(LogStatus.FAIL, "TC_LOMT-1840-Title Foreground colour does not match in exported file");
				}
				
				if (worksheet.getRow(LOMTConstant.SIX).getCell(LOMTConstant.ONE).getStringCellValue() != null &&
						worksheet.getRow(LOMTConstant.SIX).getCell(LOMTConstant.ONE).getStringCellValue().equalsIgnoreCase(source)) {
				} else {
					count++;
					logger.log(LogStatus.FAIL, "TC_LOMT-1840-Title of Curriculum Standard should not be null in exported file");
				}
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1840-Title header does not match in exported file");
			}
			
			//Title - right side
			if (worksheet.getRow(LOMTConstant.SIX).getCell(LOMTConstant.EIGHT).getStringCellValue().trim().equalsIgnoreCase(ReportsConstant.TITLE)) {
				XSSFCellStyle style1 = worksheet.getRow(LOMTConstant.SIX).getCell(LOMTConstant.EIGHT).getCellStyle();
				short blackColourCode = style1.getFillForegroundColorColor().getIndexed();
				if (blackColourCode == IndexedColors.BLACK.getIndex()) {
				} else {
					count++;
					logger.log(LogStatus.FAIL, "TC_LOMT-1840-Title Foreground colour does not match in exported file");
				}
				
				if (worksheet.getRow(LOMTConstant.SIX).getCell(LOMTConstant.ONE).getStringCellValue() != null &&
						worksheet.getRow(LOMTConstant.SIX).getCell(LOMTConstant.ONE).getStringCellValue().equalsIgnoreCase(source)) {
				} else {
					count++;
					logger.log(LogStatus.FAIL, "TC_LOMT-1840-Title of Curriculum Standard should not be null in exported file");
				}
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1840-Title header does not match in exported file");
			}
			

			// Country - Left side
			if (worksheet.getRow(LOMTConstant.SEVEN).getCell(LOMTConstant.ZERO).getStringCellValue().equalsIgnoreCase(ReportsConstant.COUNTRY)) {
				XSSFCellStyle style1 = worksheet.getRow(LOMTConstant.SEVEN).getCell(LOMTConstant.ZERO).getCellStyle();
				short blackColourCode = style1.getFillForegroundColorColor().getIndexed();
				if (blackColourCode == IndexedColors.BLACK.getIndex()) {
				} else {
					count++;
					logger.log(LogStatus.FAIL, "TC_LOMT-1840-Country Foreground colour does not match in exported file");
				}
				
				if (worksheet.getRow(LOMTConstant.SEVEN).getCell(LOMTConstant.ONE).getStringCellValue() != null) {
				} else {
					count++;
					logger.log(LogStatus.FAIL, "TC_LOMT-1840-Country should not be null in exported file");
				}
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1840-Country header does not match in exported file");
			}
			
			//Country - right side
			if (worksheet.getRow(LOMTConstant.SEVEN).getCell(LOMTConstant.EIGHT).getStringCellValue().equalsIgnoreCase(ReportsConstant.COUNTRY)) {
				XSSFCellStyle style1 = worksheet.getRow(LOMTConstant.SEVEN).getCell(LOMTConstant.EIGHT).getCellStyle();
				short blackColourCode = style1.getFillForegroundColorColor().getIndexed();
				if (blackColourCode == IndexedColors.BLACK.getIndex()) {
				} else {
					count++;
					logger.log(LogStatus.FAIL, "TC_LOMT-1840-Country Foreground colour does not match in exported file");
				}
				
				if (worksheet.getRow(LOMTConstant.SEVEN).getCell(LOMTConstant.ONE).getStringCellValue() != null) {
				} else {
					count++;
					logger.log(LogStatus.FAIL, "TC_LOMT-1840-Country should not be null in exported file");
				}
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1840-Country header does not match in exported file");
			}

			//Grade - left side
			if (worksheet.getRow(LOMTConstant.EIGHT).getCell(LOMTConstant.ZERO).getStringCellValue().equalsIgnoreCase(ReportsConstant.GRADE)) {
				XSSFCellStyle style1 = worksheet.getRow(LOMTConstant.EIGHT).getCell(LOMTConstant.ZERO).getCellStyle();
				short blackColourCode = style1.getFillForegroundColorColor().getIndexed();
				if (blackColourCode == IndexedColors.BLACK.getIndex()) {
				} else {
					count++;
					logger.log(LogStatus.FAIL, "TC_LOMT-1840-Grade(Source) Foreground colour does not match in exported file");
				}
				
				if (worksheet.getRow(LOMTConstant.EIGHT).getCell(LOMTConstant.ONE).getStringCellValue() != null) {
				} else {
					count++;
					logger.log(LogStatus.FAIL, "TC_LOMT-1840-Grade(Source) should not be null in exported file");
				}
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1840-Grade header(Source) does not match in exported file");
			}
			
			//Grade - Right side
			if (worksheet.getRow(LOMTConstant.EIGHT).getCell(LOMTConstant.EIGHT).getStringCellValue().equalsIgnoreCase(ReportsConstant.GRADE)) {
				XSSFCellStyle style1 = worksheet.getRow(LOMTConstant.EIGHT).getCell(LOMTConstant.EIGHT).getCellStyle();
				short blackColourCode = style1.getFillForegroundColorColor().getIndexed();
				if (blackColourCode == IndexedColors.BLACK.getIndex()) {
				} else {
					count++;
					logger.log(LogStatus.FAIL, "TC_LOMT-1840-Grade(Target) Foreground colour does not match in exported file");
				}
				
				if (worksheet.getRow(LOMTConstant.EIGHT).getCell(LOMTConstant.ONE).getStringCellValue() != null) {
				} else {
					count++;
					logger.log(LogStatus.FAIL, "TC_LOMT-1840-Grade(Target) should not be null in exported file");
				}
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1840-Grade(Target) header does not match in exported file");
			}
			
			//####### Colour Key ##########			
			if (worksheet.getRow(LOMTConstant.TEN).getCell(LOMTConstant.ZERO).getStringCellValue().equalsIgnoreCase(ReportsConstant.COLOUR_KEY)) {
				XSSFCellStyle style1 = worksheet.getRow(LOMTConstant.TEN).getCell(LOMTConstant.ZERO).getCellStyle();
				short blackColourCode = style1.getFillForegroundColorColor().getIndexed();
				if (blackColourCode == IndexedColors.BLACK.getIndex()) {
				} else {
					count++;
					logger.log(LogStatus.FAIL, "TC_LOMT-1840-Colour Key Foreground colour does not match in exported file");
				}
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1840-Colour Key header does not match in exported file");
			}
			
			//Overview Heading
			if (worksheet.getRow(LOMTConstant.ELEVENTH).getCell(LOMTConstant.ONE).getStringCellValue().equalsIgnoreCase(ReportsConstant.OVERVIEW_HEADING)) {
				XSSFCellStyle style1 = worksheet.getRow(LOMTConstant.ELEVENTH).getCell(LOMTConstant.ZERO).getCellStyle();
				short blackColourCode = style1.getFillForegroundColorColor().getIndexed();
				if (blackColourCode == IndexedColors.RED.getIndex()) {
				} else {
					count++;
					logger.log(LogStatus.FAIL, "TC_LOMT-1840-Overview Heading Foreground colour does not match in exported file");
				}
			} else {
				System.out.println("dfdf");
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1840-Overview Heading header does not match in exported file");
			}
			
			//Level 1 Heading
			if (worksheet.getRow(LOMTConstant.TWELEVE).getCell(LOMTConstant.ONE).getStringCellValue().equalsIgnoreCase(ReportsConstant.LEVEL_1_HEADING)) {
				XSSFCellStyle style1 = worksheet.getRow(LOMTConstant.TWELEVE).getCell(LOMTConstant.ZERO).getCellStyle();
				short blackColourCode = style1.getFillForegroundColorColor().getIndexed();
				if (blackColourCode == IndexedColors.LIGHT_ORANGE.getIndex()) {
				} else {
					count++;
					logger.log(LogStatus.FAIL, "TC_LOMT-1840-Level 1 Heading Foreground colour does not match in exported file");
				}
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1840-Level 1 Heading header does not match in exported file");
			}
			
			//Level 2 Heading
			if (worksheet.getRow(LOMTConstant.THIRTEEN).getCell(LOMTConstant.ONE).getStringCellValue().equalsIgnoreCase(ReportsConstant.LEVEL_2_HEADING)) {
				XSSFCellStyle style1 = worksheet.getRow(LOMTConstant.THIRTEEN).getCell(LOMTConstant.ZERO).getCellStyle();
				short blackColourCode = style1.getFillForegroundColorColor().getIndexed();
				if (blackColourCode == 47) { // TODO: need to get exact color code
				} else {
					count++;
					logger.log(LogStatus.FAIL, "TC_LOMT-1840-Level 2 Heading Foreground colour does not match in exported file");
				}
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1840-Level 2 Heading header does not match in exported file");
			}
			
			//Level 3 Heading
			if (worksheet.getRow(LOMTConstant.FOURTEEN).getCell(LOMTConstant.ONE).getStringCellValue().equalsIgnoreCase(ReportsConstant.LEVEL_3_HEADING)) {
				XSSFCellStyle style1 = worksheet.getRow(LOMTConstant.FOURTEEN).getCell(LOMTConstant.ZERO).getCellStyle();
				short blackColourCode = style1.getFillForegroundColorColor().getIndexed();
				if (blackColourCode == IndexedColors.LIGHT_YELLOW.getIndex()) { // colour code is 43
				} else {
					count++;
					logger.log(LogStatus.FAIL, "TC_LOMT-1840-Level 3 Heading Foreground colour does not match in exported file");
				}
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1840-Level 3 Heading header does not match in exported file");
			}
			
			//No Alignment
			if (worksheet.getRow(LOMTConstant.FIFTEEN).getCell(LOMTConstant.ONE).getStringCellValue().equalsIgnoreCase(ReportsConstant.NO_ALIGNMENT)) {
				XSSFCellStyle style1 = worksheet.getRow(LOMTConstant.FIFTEEN).getCell(LOMTConstant.ZERO).getCellStyle();
				short blackColourCode = style1.getFillForegroundColorColor().getIndexed();
				if (blackColourCode == IndexedColors.LIGHT_GREEN.getIndex()) { // colour code is 42
				} else {
					count++;
					logger.log(LogStatus.FAIL, "TC_LOMT-1840-No Alignment Heading Foreground colour does not match in exported file");
				}
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1840-No Alignment Heading header does not match in exported file");
			}
			
			//Source, Target Educational Goal's headings
			
			//Grade
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.ZERO).getStringCellValue().equalsIgnoreCase(ReportsConstant.GRADE)) {
				XSSFCellStyle style1 = worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.ZERO).getCellStyle();
				short blackColourCode = style1.getFillForegroundColorColor().getIndexed();
				if (blackColourCode == IndexedColors.BLACK.getIndex()) { 
				} else {
					count++;
					logger.log(LogStatus.FAIL, "TC_LOMT-1840-Grade Heading Foreground colour does not match in exported file");
				}
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1840-Grade header does not match in exported file");
			}

			//Standards' Strands			
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.ONE).getStringCellValue().equalsIgnoreCase(ReportsConstant.STANDARDS_STRANDS)) {
				XSSFCellStyle style1 = worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.ONE).getCellStyle();
				short blackColourCode = style1.getFillForegroundColorColor().getIndexed();
				if (blackColourCode == IndexedColors.BLACK.getIndex()) { 
				} else {
					count++;
					logger.log(LogStatus.FAIL, "TC_LOMT-1840-Standards' Strands Heading Foreground colour does not match in exported file");
				}
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1840-Standards' Strands header does not match in exported file");
			}

			//Standards' Topics
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.TWO).getStringCellValue().equalsIgnoreCase(ReportsConstant.STANDARDS_TOPICS)) {
				XSSFCellStyle style1 = worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.TWO).getCellStyle();
				short blackColourCode = style1.getFillForegroundColorColor().getIndexed();
				if (blackColourCode == IndexedColors.BLACK.getIndex()) { 
				} else {
					count++;
					logger.log(LogStatus.FAIL, "TC_LOMT-1840-Standards' Topics Heading Foreground colour does not match in exported file");
				}
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1840-Standards' Topics does not match in exported file");
			}

			//Standard Number
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.THREE).getStringCellValue().equalsIgnoreCase(ReportsConstant.STANDARDS_NUMBER)) {
				XSSFCellStyle style1 = worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.THREE).getCellStyle();
				short blackColourCode = style1.getFillForegroundColorColor().getIndexed();
				if (blackColourCode == IndexedColors.BLACK.getIndex()) { 
				} else {
					count++;
					logger.log(LogStatus.FAIL, "TC_LOMT-1840-Standards' Number Heading Foreground colour does not match in exported file");
				}
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1840-Standard Number does not match in exported file");
			}

			//AB GUID
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.FOUR).getStringCellValue().equalsIgnoreCase(ReportsConstant.AB_GUIDE)) {
				XSSFCellStyle style1 = worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.FOUR).getCellStyle();
				short blackColourCode = style1.getFillForegroundColorColor().getIndexed();
				if (blackColourCode == IndexedColors.BLACK.getIndex()) { 
				} else {
					count++;
					logger.log(LogStatus.FAIL, "TC_LOMT-1840-AB GUID Heading Foreground colour does not match in exported file");
				}
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1840-AB GUID header does not match in exported file");
			}

			// Alignment
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.FIVE).getStringCellValue().equalsIgnoreCase(ReportsConstant.ALIGNMENT)) {
				XSSFCellStyle style1 = worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.FIVE).getCellStyle();
				short blackColourCode = style1.getFillForegroundColorColor().getIndexed();
				if (blackColourCode == IndexedColors.BLACK.getIndex()) { 
				} else {
					count++;
					logger.log(LogStatus.FAIL, "TC_LOMT-1840-AB Alignment(Source) Heading Foreground colour does not match in exported file");
				}
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1840-Alignment header(Source) does not match in exported file");
			}

			//Comment
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.SIX).getStringCellValue().equalsIgnoreCase(ReportsConstant.COMMENT)) {
				XSSFCellStyle style1 = worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.SIX).getCellStyle();
				short blackColourCode = style1.getFillForegroundColorColor().getIndexed();
				if (blackColourCode == IndexedColors.BLACK.getIndex()) { 
				} else {
					count++;
					logger.log(LogStatus.FAIL, "TC_LOMT-1840-Comment Heading Foreground colour does not match in exported file");
				}
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1840-Comment header does not match in exported file");
			}
			
			//Right side
			//Grade
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.EIGHT).getStringCellValue().equalsIgnoreCase(ReportsConstant.GRADE)) {
				XSSFCellStyle style1 = worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.EIGHT).getCellStyle();
				short blackColourCode = style1.getFillForegroundColorColor().getIndexed();
				if (blackColourCode == IndexedColors.BLACK.getIndex()) { 
				} else {
					count++;
					logger.log(LogStatus.FAIL, "TC_LOMT-1840-Grade Heading Foreground colour does not match in exported file");
				}
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1840-Grade header(Target) does not match in exported file");
			}
			
			//Standards' Strands
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.NINE).getStringCellValue().equalsIgnoreCase(ReportsConstant.STANDARDS_STRANDS)) {
				XSSFCellStyle style1 = worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.NINE).getCellStyle();
				short blackColourCode = style1.getFillForegroundColorColor().getIndexed();
				if (blackColourCode == IndexedColors.BLACK.getIndex()) { 
				} else {
					count++;
					logger.log(LogStatus.FAIL, "TC_LOMT-1840-Standards' Strands Heading Foreground colour does not match in exported file");
				}
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1840-Standards' Strands(Target) does not match in exported file");
			}
			
			//Standards' Topics
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.TEN).getStringCellValue().equalsIgnoreCase(ReportsConstant.STANDARDS_TOPICS)) {
				XSSFCellStyle style1 = worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.TEN).getCellStyle();
				short blackColourCode = style1.getFillForegroundColorColor().getIndexed();
				if (blackColourCode == IndexedColors.BLACK.getIndex()) { 
				} else {
					count++;
					logger.log(LogStatus.FAIL, "TC_LOMT-1840-Standards' Topics Heading Foreground colour does not match in exported file");
				}
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1840-Standards' Topics(Target) does not match in exported file");
			}
			
			//Standard Number
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.ELEVENTH).getStringCellValue().equalsIgnoreCase(ReportsConstant.STANDARDS_NUMBER)) {
				XSSFCellStyle style1 = worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.ELEVENTH).getCellStyle();
				short blackColourCode = style1.getFillForegroundColorColor().getIndexed();
				if (blackColourCode == IndexedColors.BLACK.getIndex()) { 
				} else {
					count++;
					logger.log(LogStatus.FAIL, "TC_LOMT-1840-Standards' Number Heading Foreground colour does not match in exported file");
				}
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1840-Standards' Number(Target) does not match in exported file");
			}

			//AB GUID
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.TWELEVE).getStringCellValue().equalsIgnoreCase(ReportsConstant.AB_GUIDE)) {
				XSSFCellStyle style1 = worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.TWELEVE).getCellStyle();
				short blackColourCode = style1.getFillForegroundColorColor().getIndexed();
				if (blackColourCode == IndexedColors.BLACK.getIndex()) { 
				} else {
					count++;
					logger.log(LogStatus.FAIL, "TC_LOMT-1840-AB GUID Heading Foreground colour does not match in exported file");
				}
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1840-AB GUID(Target) does not match in exported file");
			}
			
			if (count == 0) {
				logger.log(LogStatus.PASS, "TC_LOMT-1840-05_For_SchoolGlobal_download_GAP_Analysis_StandardToStandard_Report");
			} else {
				logger.log(LogStatus.FAIL, "TC_LOMT-1840-05_For_SchoolGlobal_download_GAP_Analysis_StandardToStandard_Report");
			}
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Unable to verify Headers in exported file");
		}
	}
	
	public void verifyGapAnalysisReportData(XSSFSheet worksheet, String reportName, ExtentTest logger) {
		try {
			Iterator<Row> rowItr = worksheet.iterator();
			while (rowItr.hasNext()) {
				Row row = rowItr.next();
				if (row.getRowNum() == 18) {
					//Color checking : RED
					XSSFCellStyle style1 = (XSSFCellStyle) row.getCell(LOMTConstant.ZERO).getCellStyle();
					short blackColourCode = style1.getFillForegroundColorColor().getIndexed();
					if (blackColourCode == IndexedColors.RED.getIndex()) {
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Foreground colour does not match in exported file at row : "+row.getRowNum());
					}
					
					if (row.getCell(LOMTConstant.ZERO) != null && row.getCell(LOMTConstant.ZERO).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.GARDE_12)) {
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Gade doesn't match at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.ONE) != null && row.getCell(LOMTConstant.ONE).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.SS_1)) {
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Standards' Strands doesn't match at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.FOUR) != null) {
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 AB GUID doesn't blank : "+row.getRowNum());
					}
				} else if (row.getRowNum() == 19) {
					//Color : LIGHT_ORANGE
					XSSFCellStyle style1 = (XSSFCellStyle) row.getCell(LOMTConstant.ZERO).getCellStyle();
					short blackColourCode = style1.getFillForegroundColorColor().getIndexed();
					if (blackColourCode == IndexedColors.LIGHT_ORANGE.getIndex()) {
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Foreground colour does not match in exported file at row : "+row.getRowNum());
					}
					
					if (row.getCell(LOMTConstant.ZERO) != null && row.getCell(LOMTConstant.ZERO).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.GARDE_12)) {
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Gade value doesn't match at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.TWO) != null && row.getCell(LOMTConstant.TWO).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.LEVEL_1)) { //Standards' Topics
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Standards' Topics doesn't match at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.THREE) != null && row.getCell(LOMTConstant.THREE).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.SN_1)) { //Standards' Number
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Standards' Number doesn't match at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.FOUR) != null) { 
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 AB GUID can not be null at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.FIVE) != null && row.getCell(LOMTConstant.FIVE).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.EXACT)) { 
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Alignment Exact value doesn't match at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.SIX) != null && row.getCell(LOMTConstant.SIX).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.COMMENT_1)) { 
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Comment value doesn't match at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.EIGHT) != null && row.getCell(LOMTConstant.EIGHT).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.GARDE_12)) { 
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Grade value doesn't match at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.TEN) != null && row.getCell(LOMTConstant.TEN).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.ST_1)) { //Target Standards' Topics
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Target Standards' Topics value doesn't match at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.ELEVENTH) != null && row.getCell(LOMTConstant.ELEVENTH).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.SN_1)) { //Target Standards' Number
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Target Standards' Number value doesn't match at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.TWELEVE) != null) { 
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Target AB GUID value doesn't match at row number : "+row.getRowNum());
					}
				} else if (row.getRowNum() == 20) {
					//Color : 47
					XSSFCellStyle style1 = (XSSFCellStyle) row.getCell(LOMTConstant.ZERO).getCellStyle();
					short blackColourCode = style1.getFillForegroundColorColor().getIndexed();
					if (blackColourCode == 47) {
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Foreground colour does not match in exported file at row : "+row.getRowNum());
					}
					
					if (row.getCell(LOMTConstant.ZERO) != null && row.getCell(LOMTConstant.ZERO).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.GARDE_12)) {
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Gade value doesn't match at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.TWO) != null && row.getCell(LOMTConstant.TWO).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.LEVEL_2)) { //Standards' Topics
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Standards' Topics doesn't match at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.FOUR) != null) { 
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 AB GUID can not be null at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.FIVE) != null && row.getCell(LOMTConstant.FIVE).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.RELATED)) { 
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Alignment Exact value doesn't match at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.EIGHT) != null && row.getCell(LOMTConstant.EIGHT).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.GARDE_12)) { 
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Grade value doesn't match at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.TEN) != null && row.getCell(LOMTConstant.TEN).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.ST_2)) { //Target Standards' Topics
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Target Standards' Topics value doesn't match at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.ELEVENTH) != null && row.getCell(LOMTConstant.ELEVENTH).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.SL)) { //Target Standards' Number
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Target Standards' Number value doesn't match at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.TWELEVE) != null) { 
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Target AB GUID value doesn't match at row number : "+row.getRowNum());
					}
				} else if (row.getRowNum() == 21) {
					//Color : LIGHT_YELLOW
					XSSFCellStyle style1 = (XSSFCellStyle) row.getCell(LOMTConstant.ZERO).getCellStyle();
					short blackColourCode = style1.getFillForegroundColorColor().getIndexed();
					if (blackColourCode == IndexedColors.LIGHT_YELLOW.getIndex()) {
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Foreground colour does not match in exported file at row : "+row.getRowNum());
					}
					
					if (row.getCell(LOMTConstant.ZERO) != null && row.getCell(LOMTConstant.ZERO).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.GARDE_12)) {
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Gade value doesn't match at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.TWO) != null && row.getCell(LOMTConstant.TWO).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.LEVEL_3)) { //Standards' Topics
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Standards' Topics doesn't match at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.THREE) != null && row.getCell(LOMTConstant.THREE).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.SN_2)) { //Standards' Number
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Standards' Number doesn't match at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.FOUR) != null) { 
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 AB GUID can not be null at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.FIVE) != null && row.getCell(LOMTConstant.FIVE).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.BROAD)) { 
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Alignment Broad value doesn't match at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.EIGHT) != null && row.getCell(LOMTConstant.EIGHT).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.GARDE_12)) { 
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Grade value doesn't match at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.TEN) != null && row.getCell(LOMTConstant.TEN).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.ST_3)) { //Target Standards' Topics
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Target Standards' Topics value doesn't match at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.ELEVENTH) != null && row.getCell(LOMTConstant.ELEVENTH).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.L)) { //Target Standards' Number
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Target Standards' Number value doesn't match at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.TWELEVE) != null) { 
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Target AB GUID value doesn't match at row number : "+row.getRowNum());
					}
				} else if (row.getRowNum() == 22) {
					//Colour : Light Yellow
					XSSFCellStyle style1 = (XSSFCellStyle) row.getCell(LOMTConstant.ZERO).getCellStyle();
					short blackColourCode = style1.getFillForegroundColorColor().getIndexed();
					if (blackColourCode == IndexedColors.LIGHT_YELLOW.getIndex()) {
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Foreground colour does not match in exported file at row : "+row.getRowNum());
					}
					
					if (row.getCell(LOMTConstant.ZERO) != null && row.getCell(LOMTConstant.ZERO).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.GARDE_12)) {
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Gade value doesn't match at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.TWO) != null && row.getCell(LOMTConstant.TWO).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.LEVEL_4)) { //Standards' Topics
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Standards' Topics doesn't match at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.THREE) != null && row.getCell(LOMTConstant.THREE).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.SN_2)) { //Standards' Number
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Standards' Number doesn't match at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.FOUR) != null) { 
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 AB GUID can not be null at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.FIVE) != null && row.getCell(LOMTConstant.FIVE).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.CLOSE)) { 
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Alignment Exact value doesn't match at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.EIGHT) != null && row.getCell(LOMTConstant.EIGHT).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.GARDE_12)) { 
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Grade value doesn't match at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.TEN) != null && row.getCell(LOMTConstant.TEN).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.ST_4)) { //Target Standards' Topics
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Target Standards' Topics value doesn't match at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.ELEVENTH) != null && row.getCell(LOMTConstant.ELEVENTH).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.L)) { //Target Standards' Number
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Target Standards' Number value doesn't match at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.TWELEVE) != null) { 
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Target AB GUID value doesn't match at row number : "+row.getRowNum());
					}
				} else if (row.getRowNum() == 23) {
					//Color : light yellow
					XSSFCellStyle style1 = (XSSFCellStyle) row.getCell(LOMTConstant.ZERO).getCellStyle();
					short blackColourCode = style1.getFillForegroundColorColor().getIndexed();
					if (blackColourCode == IndexedColors.LIGHT_YELLOW.getIndex()) {
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Foreground colour does not match in exported file at row : "+row.getRowNum());
					}
					
					if (row.getCell(LOMTConstant.ZERO) != null && row.getCell(LOMTConstant.ZERO).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.GARDE_12)) {
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Gade value doesn't match at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.TWO) != null && row.getCell(LOMTConstant.TWO).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.LEVEL_5)) { //Standards' Topics
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Standards' Topics doesn't match at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.THREE) != null && row.getCell(LOMTConstant.THREE).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.SN_2)) { //Standards' Number
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Standards' Number doesn't match at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.FOUR) != null) { 
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 AB GUID can not be null at row number : "+row.getRowNum());
					}
				} else if (row.getRowNum() == 24) {
					//No background color
					XSSFCellStyle style1 = (XSSFCellStyle) row.getCell(LOMTConstant.ZERO).getCellStyle();
					XSSFColor blackColourCode = style1.getFillForegroundColorColor();
					if (blackColourCode == null) {
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Foreground colour does not match in exported file at row : "+row.getRowNum());
					}
					
					if (row.getCell(LOMTConstant.ZERO) != null && row.getCell(LOMTConstant.ZERO).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.GARDE_12)) {
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Gade value doesn't match at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.TWO) != null && row.getCell(LOMTConstant.TWO).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.LEVEL_6)) { //Standards' Topics
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Standards' Topics doesn't match at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.THREE) != null && row.getCell(LOMTConstant.THREE).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.SN_2)) { //Standards' Number
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Standards' Number doesn't match at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.FOUR) != null) { 
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 AB GUID can not be null at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.FIVE) != null && row.getCell(LOMTConstant.FIVE).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.NARROW)) { 
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Alignment Exact value doesn't match at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.SIX) != null && row.getCell(LOMTConstant.SIX).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.COMMENT_2)) { 
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Comment value doesn't match at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.EIGHT) != null && row.getCell(LOMTConstant.EIGHT).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.GARDE_12)) { 
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Grade value doesn't match at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.TEN) != null && row.getCell(LOMTConstant.TEN).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.ST_5)) { //Target Standards' Topics
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Target Standards' Topics value doesn't match at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.TWELEVE) != null) { 
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Target AB GUID value doesn't match at row number : "+row.getRowNum());
					}
				} 
				//########### Target Curriculum Standard ##########
				else if (row.getRowNum() == 25) {
					//Color checking : RED
					XSSFCellStyle style1 = (XSSFCellStyle) row.getCell(LOMTConstant.ZERO).getCellStyle();
					short blackColourCode = style1.getFillForegroundColorColor().getIndexed();
					if (blackColourCode == IndexedColors.RED.getIndex()) {
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Foreground colour does not match in exported file at row : "+row.getRowNum());
					}
					
					if (row.getCell(LOMTConstant.ZERO) != null && row.getCell(LOMTConstant.ZERO).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.GARDE_5)) {
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Gade doesn't match at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.ONE) != null && row.getCell(LOMTConstant.ONE).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.SS_2)) {
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Standards' Strands doesn't match at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.FOUR) != null) {
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 AB GUID doesn't blank : "+row.getRowNum());
					}
				} else if (row.getRowNum() == 26) {
					//Color : LIGHT_ORANGE
					XSSFCellStyle style1 = (XSSFCellStyle) row.getCell(LOMTConstant.ZERO).getCellStyle();
					short blackColourCode = style1.getFillForegroundColorColor().getIndexed();
					if (blackColourCode == IndexedColors.LIGHT_ORANGE.getIndex()) {
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Foreground colour does not match in exported file at row : "+row.getRowNum());
					}
					
					if (row.getCell(LOMTConstant.ZERO) != null && row.getCell(LOMTConstant.ZERO).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.GARDE_5)) {
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Gade value doesn't match at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.TWO) != null && row.getCell(LOMTConstant.TWO).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.LEVEL_1)) { //Standards' Topics
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Standards' Topics doesn't match at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.THREE) != null && row.getCell(LOMTConstant.THREE).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.SN_1)) { //Standards' Number
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Standards' Number doesn't match at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.FOUR) != null) { 
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 AB GUID can not be null at row number : "+row.getRowNum());
					}
				} else if (row.getRowNum() == 27) {
					//Color : LIGHT_ORANGE
					XSSFCellStyle style1 = (XSSFCellStyle) row.getCell(LOMTConstant.ZERO).getCellStyle();
					short blackColourCode = style1.getFillForegroundColorColor().getIndexed();
					if (blackColourCode == 47) {
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Foreground colour does not match in exported file at row : "+row.getRowNum());
					}
					
					if (row.getCell(LOMTConstant.ZERO) != null && row.getCell(LOMTConstant.ZERO).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.GARDE_5)) {
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Gade value doesn't match at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.TWO) != null && row.getCell(LOMTConstant.TWO).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.LEVEL_2)) { //Standards' Topics
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Standards' Topics doesn't match at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.FOUR) != null) { 
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 AB GUID can not be null at row number : "+row.getRowNum());
					}
				} else if (row.getRowNum() == 28) {
					//Color : LIGHT_YELLOW
					XSSFCellStyle style1 = (XSSFCellStyle) row.getCell(LOMTConstant.ZERO).getCellStyle();
					short blackColourCode = style1.getFillForegroundColorColor().getIndexed();
					if (blackColourCode == IndexedColors.LIGHT_YELLOW.getIndex()) {
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Foreground colour does not match in exported file at row : "+row.getRowNum());
					}
					
					if (row.getCell(LOMTConstant.ZERO) != null && row.getCell(LOMTConstant.ZERO).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.GARDE_5)) {
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Gade value doesn't match at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.TWO) != null && row.getCell(LOMTConstant.TWO).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.LEVEL_3)) { //Standards' Topics
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Standards' Topics doesn't match at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.THREE) != null && row.getCell(LOMTConstant.THREE).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.SN_2)) { //Standards' Number
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Standards' Number doesn't match at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.FOUR) != null) { 
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 AB GUID can not be null at row number : "+row.getRowNum());
					}
				} else if (row.getRowNum() == 29) {
					//Color : LIGHT_YELLOW
					XSSFCellStyle style1 = (XSSFCellStyle) row.getCell(LOMTConstant.ZERO).getCellStyle();
					short blackColourCode = style1.getFillForegroundColorColor().getIndexed();
					if (blackColourCode == IndexedColors.LIGHT_YELLOW.getIndex()) {
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Foreground colour does not match in exported file at row : "+row.getRowNum());
					}
					
					if (row.getCell(LOMTConstant.ZERO) != null && row.getCell(LOMTConstant.ZERO).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.GARDE_5)) {
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Gade value doesn't match at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.TWO) != null && row.getCell(LOMTConstant.TWO).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.LEVEL_4)) { //Standards' Topics
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Standards' Topics doesn't match at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.THREE) != null && row.getCell(LOMTConstant.THREE).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.SN_2)) { //Standards' Number
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Standards' Number doesn't match at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.FOUR) != null) { 
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 AB GUID can not be null at row number : "+row.getRowNum());
					}
				} else if (row.getRowNum() == 30) {
					//Color : LIGHT_YELLOW
					XSSFCellStyle style1 = (XSSFCellStyle) row.getCell(LOMTConstant.ZERO).getCellStyle();
					short blackColourCode = style1.getFillForegroundColorColor().getIndexed();
					if (blackColourCode == IndexedColors.LIGHT_YELLOW.getIndex()) {
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Foreground colour does not match in exported file at row : "+row.getRowNum());
					}
					
					if (row.getCell(LOMTConstant.ZERO) != null && row.getCell(LOMTConstant.ZERO).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.GARDE_5)) {
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Gade value doesn't match at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.TWO) != null && row.getCell(LOMTConstant.TWO).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.LEVEL_5)) { //Standards' Topics
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Standards' Topics doesn't match at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.THREE) != null && row.getCell(LOMTConstant.THREE).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.SN_2)) { //Standards' Number
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Standards' Number doesn't match at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.FOUR) != null) { 
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 AB GUID can not be null at row number : "+row.getRowNum());
					}
				}  else if (row.getRowNum() == 31) {
					//Color : LIGHT_GREEN
					XSSFCellStyle style1 = (XSSFCellStyle) row.getCell(LOMTConstant.ZERO).getCellStyle();
					short blackColourCode = style1.getFillForegroundColorColor().getIndexed();
					if (blackColourCode == IndexedColors.LIGHT_GREEN.getIndex()) {
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Foreground colour does not match in exported file at row : "+row.getRowNum());
					}
					
					if (row.getCell(LOMTConstant.ZERO) != null && row.getCell(LOMTConstant.ZERO).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.GARDE_5)) {
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Gade value doesn't match at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.TWO) != null && row.getCell(LOMTConstant.TWO).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.LEVEL_6)) { //Standards' Topics
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Standards' Topics doesn't match at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.THREE) != null && row.getCell(LOMTConstant.THREE).getStringCellValue()
							.equalsIgnoreCase(ReportsConstant.SN_2)) { //Standards' Number
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 Standards' Number doesn't match at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.FOUR) != null) { 
					} else {
						logger.log(LogStatus.FAIL, "TC_LOMT-1840 AB GUID can not be null at row number : "+row.getRowNum());
					}
				}  else if (row.getRowNum() == 32) {
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void verifyForwardSharedIntermediaryReportData(XSSFSheet worksheet, String reportName, ExtentTest logger) {
		try {
			int count = 0;
			Iterator<Row> rowItr = worksheet.iterator();
			while (rowItr.hasNext()) {
				Row row = rowItr.next();
				//Standards' Strands
				if (row.getRowNum() == 18) {
					if (row.getCell(LOMTConstant.ZERO) != null && row.getCell(LOMTConstant.ZERO).getStringCellValue().equalsIgnoreCase(ReportsConstant.STND_STRANDS)) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 Standards' Strands doesn't match at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.THREE) != null) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 AB GUID should not be null at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.FOUR) != null) {
					} else {
						count++; 
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 Correlation Score should not be null at row number : "+row.getRowNum());
					}
				}
				
				else if (row.getRowNum() == 19) {
					if (row.getCell(LOMTConstant.ONE) != null && row.getCell(LOMTConstant.ONE).getStringCellValue().equalsIgnoreCase(ReportsConstant.ST_1)) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 Standards' Topics doesn't match at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.TWO) != null && row.getCell(LOMTConstant.TWO).getStringCellValue().equalsIgnoreCase(ReportsConstant.W)) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 Standards' Number doesn't match at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.THREE) != null) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 AB GUID should not be null at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.FOUR) != null && row.getCell(LOMTConstant.FOUR).getStringCellValue().equalsIgnoreCase(ReportsConstant.TWO_BY_TWO)) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 Correlation Score doesn't match at row number at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.FIVE) != null && row.getCell(LOMTConstant.FIVE).getStringCellValue().equalsIgnoreCase(ReportsConstant.STRENGTH_COMPLETE)) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 Strength doesn't match at row number at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.NINE) != null && 
							row.getCell(LOMTConstant.NINE).getStringCellValue().contains(ReportsConstant.ELA_CP_1)
							&& row.getCell(LOMTConstant.NINE).getStringCellValue().contains(ReportsConstant.ELA_CP_2)) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 Met Statements doesn't match at row number at row number : "+row.getRowNum());
					}
					
					if (row.getCell(LOMTConstant.ELEVENTH) != null && 
							row.getCell(LOMTConstant.ELEVENTH).getStringCellValue().contains(ReportsConstant.TOC_TEST_DATA_1)) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 Component Reference(TOC description) doesn't match at row number at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.TWELEVE) != null && 
							row.getCell(LOMTConstant.TWELEVE).getStringCellValue().contains(ReportsConstant.PAGE_START_4)) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 Page Start doesn't match at row number at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.THIRTEEN) != null && 
							row.getCell(LOMTConstant.THIRTEEN).getStringCellValue().contains(ReportsConstant.PAGE_START_6)) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 Page End doesn't match at row number at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.FOURTEEN) != null && 
							row.getCell(LOMTConstant.FOURTEEN).getStringCellValue().contains(ReportsConstant.TWO_BY_TWO)) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 Correlation Score(TOC) doesn't match at row number at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.FIFTEEN) != null && 
							row.getCell(LOMTConstant.FIFTEEN).getStringCellValue().contains(ReportsConstant.STRENGTH_COMPLETE)) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 Strength(TOC) doesn't match at row number at row number : "+row.getRowNum());
					}
				} 
				
				else if (row.getRowNum() == 20) {
					if (row.getCell(LOMTConstant.ONE) != null && row.getCell(LOMTConstant.ONE).getStringCellValue().contains(ReportsConstant.CS_LEVEL_1)) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 Standards' Topics(CS) doesn't match at row number at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.THREE) != null) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 AB GUID(CS) can't be null at row number at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.FOUR) != null && row.getCell(LOMTConstant.FOUR).getStringCellValue().contains(ReportsConstant.ZERO_BY_ZERO)) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 Correlation Score(CS) doesn't match at row number at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.FIVE) != null && row.getCell(LOMTConstant.FIVE).getStringCellValue().contains(ReportsConstant.NO_CORRELATION)) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 Strength(CS) doesn't match at row number at row number : "+row.getRowNum());
					}
				}
				
				else if (row.getRowNum() == 21) {
					if (row.getCell(LOMTConstant.ONE) != null && row.getCell(LOMTConstant.ONE).getStringCellValue().contains(ReportsConstant.CS_LEVEL_2)) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 Standards' Topics(CS) doesn't match at row number at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.TWO) != null && row.getCell(LOMTConstant.TWO).getStringCellValue().contains(ReportsConstant.W_1)) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 Standard Number(CS) doesn't match at row number at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.THREE) != null) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 AB GUID(CS) can't be null at row number at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.FOUR) != null && row.getCell(LOMTConstant.FOUR).getStringCellValue().contains(ReportsConstant.ZERO_BY_ZERO)) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 Correlation Score(CS) doesn't match at row number at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.FIVE) != null && row.getCell(LOMTConstant.FIVE).getStringCellValue().contains(ReportsConstant.NO_CORRELATION)) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 Strength(CS) doesn't match at row number at row number : "+row.getRowNum());
					}
				}
				
				else if (row.getRowNum() == 22) {
					if (row.getCell(LOMTConstant.ONE) != null && row.getCell(LOMTConstant.ONE).getStringCellValue().contains(ReportsConstant.ST_2)) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 Standards' Topics(CS) doesn't match at row number at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.TWO) != null && row.getCell(LOMTConstant.TWO).getStringCellValue().contains(ReportsConstant.SL)) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 Standard Number(CS) doesn't match at row number at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.THREE) != null) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 AB GUID(CS) can't be null at row number at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.FOUR) != null && row.getCell(LOMTConstant.FOUR).getStringCellValue().contains(ReportsConstant.ZERO_BY_ZERO)) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 Correlation Score(CS) doesn't match at row number at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.FIVE) != null && row.getCell(LOMTConstant.FIVE).getStringCellValue().contains(ReportsConstant.NO_CORRELATION)) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 Strength(CS) doesn't match at row number at row number : "+row.getRowNum());
					}
				}
				
				else if (row.getRowNum() == 23) { //Alignment 
					if (row.getCell(LOMTConstant.ONE) != null && row.getCell(LOMTConstant.ONE).getStringCellValue().contains(ReportsConstant.CS_LEVEL_3)) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 Standards' Topics(CS) doesn't match at row number at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.THREE) != null) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 AB GUID(CS) can't be null at row number at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.FOUR) != null && row.getCell(LOMTConstant.FOUR).getStringCellValue().contains(ReportsConstant.ZERO_BY_ONE)) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 Correlation Score(CS) doesn't match at row number at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.FIVE) != null && row.getCell(LOMTConstant.FIVE).getStringCellValue().contains(ReportsConstant.STRENGTH_WEAK)) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 Strength(CS) doesn't match at row number at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.EIGHT) != null && row.getCell(LOMTConstant.EIGHT).getStringCellValue().contains(ReportsConstant.UNMET_1)) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 Unmet Statements(Discipline) doesn't match at row number at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.ELEVENTH) != null && row.getCell(LOMTConstant.ELEVENTH).getStringCellValue().contains(ReportsConstant.TOC_1)) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 Component Reference(toc) doesn't match at row number at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.TWELEVE) != null && row.getCell(LOMTConstant.TWELEVE).getStringCellValue().contains("7")) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 Page start(toc) doesn't match at row number at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.THIRTEEN) != null && row.getCell(LOMTConstant.THIRTEEN).getStringCellValue().contains("9")) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 Page end(toc) doesn't match at row number at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.FOURTEEN) != null && row.getCell(LOMTConstant.FOURTEEN).getStringCellValue().contains(ReportsConstant.ZERO_BY_ZERO)) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 Correlation Score(toc) doesn't match at row number at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.FIFTEEN) != null && row.getCell(LOMTConstant.FIFTEEN).getStringCellValue().contains(ReportsConstant.NO_CORRELATION)) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 Strength(toc) doesn't match at row number at row number : "+row.getRowNum());
					}
				}
				
				else if (row.getRowNum() == 24) {
					if (row.getCell(LOMTConstant.ONE) != null && row.getCell(LOMTConstant.ONE).getStringCellValue().contains(ReportsConstant.CS_LEVEL_4)) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 Standards' Topics(CS) doesn't match at row number at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.TWO) != null && row.getCell(LOMTConstant.TWO).getStringCellValue().contains(ReportsConstant.SL_1)) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 Standard Number(CS) doesn't match at row number at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.THREE) != null) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 AB GUID(CS) can't be null at row number at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.FOUR) != null && row.getCell(LOMTConstant.FOUR).getStringCellValue().contains(ReportsConstant.ZERO_BY_ZERO)) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 Correlation Score(CS) doesn't match at row number at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.FIVE) != null && row.getCell(LOMTConstant.FIVE).getStringCellValue().contains(ReportsConstant.NO_CORRELATION)) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 Strength(CS) doesn't match at row number at row number : "+row.getRowNum());
					}
				}
				
				else if (row.getRowNum() == 25) { // Alignment 
					if (row.getCell(LOMTConstant.ONE) != null && row.getCell(LOMTConstant.ONE).getStringCellValue().contains(ReportsConstant.ST_3)) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 Standards' Topics(CS) doesn't match at row number at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.TWO) != null && row.getCell(LOMTConstant.TWO).getStringCellValue().contains(ReportsConstant.L)) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 Standard Number(CS) doesn't match at row number at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.THREE) != null) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 AB GUID(CS) can't be null at row number at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.FOUR) != null && row.getCell(LOMTConstant.FOUR).getStringCellValue().contains(ReportsConstant.THREE_BY_FOUR)) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 Correlation Score(CS) doesn't match at row number at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.FIVE) != null && row.getCell(LOMTConstant.FIVE).getStringCellValue().contains(ReportsConstant.STRENGTH_STRONG)) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 Strength(CS) doesn't match at row number at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.EIGHT) != null && row.getCell(LOMTConstant.EIGHT).getStringCellValue().contains(ReportsConstant.UNMET_2)) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 Unmet(Discipline) doesn't match at row number at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.NINE) != null && row.getCell(LOMTConstant.NINE).getStringCellValue().contains(ReportsConstant.MET_1)
							&& row.getCell(LOMTConstant.NINE).getStringCellValue().contains(ReportsConstant.MET_2)
							&& row.getCell(LOMTConstant.NINE).getStringCellValue().contains(ReportsConstant.MET_3)) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 Met(Discipline) doesn't match at row number at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.ELEVENTH) != null && row.getCell(LOMTConstant.ELEVENTH).getStringCellValue().contains(ReportsConstant.TOC_2)) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 COMPONENT REFERENCE(TOC) doesn't match at row number at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.FOURTEEN) != null && row.getCell(LOMTConstant.FOURTEEN).getStringCellValue().contains(ReportsConstant.THREE_BY_THREE)) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 Correlation Score(TOC) doesn't match at row number at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.FIFTEEN) != null && row.getCell(LOMTConstant.FIFTEEN).getStringCellValue().contains(ReportsConstant.STRENGTH_COMPLETE)) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 Correlation Score(TOC) doesn't match at row number at row number : "+row.getRowNum());
					}
					
				}
				
				else if (row.getRowNum() == 26) { 
					if (row.getCell(LOMTConstant.ONE) != null && row.getCell(LOMTConstant.ONE).getStringCellValue().contains(ReportsConstant.ST_5)) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 Standards' Topics(CS) doesn't match at row number at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.THREE) != null) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 AB GUID(CS) can't be null at row number at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.FOUR) != null && row.getCell(LOMTConstant.FOUR).getStringCellValue().contains(ReportsConstant.ZERO_BY_ZERO)) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 Correlation Score(CS) doesn't match at row number at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.FIVE) != null && row.getCell(LOMTConstant.FIVE).getStringCellValue().contains(ReportsConstant.NO_CORRELATION)) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 Strength(CS) doesn't match at row number at row number : "+row.getRowNum());
					}
				}
				
				else if (row.getRowNum() == 27) { 
					if (row.getCell(LOMTConstant.ONE) != null && row.getCell(LOMTConstant.ONE).getStringCellValue().contains(ReportsConstant.CS_LEVEL_5)) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 Standards' Topics(CS) doesn't match at row number at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.TWO) != null && row.getCell(LOMTConstant.TWO).getStringCellValue().contains(ReportsConstant.L_1)) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 Standard Number(CS) doesn't match at row number at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.THREE) != null) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 AB GUID(CS) can't be null at row number at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.FOUR) != null && row.getCell(LOMTConstant.FOUR).getStringCellValue().contains(ReportsConstant.ZERO_BY_ZERO)) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 Correlation Score(CS) doesn't match at row number at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.FIVE) != null && row.getCell(LOMTConstant.FIVE).getStringCellValue().contains(ReportsConstant.NO_CORRELATION)) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 Strength(CS) doesn't match at row number at row number : "+row.getRowNum());
					}
				}
				
				else if (row.getRowNum() == 28) { 
					if (row.getCell(LOMTConstant.ONE) != null && row.getCell(LOMTConstant.ONE).getStringCellValue().contains(ReportsConstant.ST_4)) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 Standards' Topics(CS) doesn't match at row number at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.TWO) != null && row.getCell(LOMTConstant.TWO).getStringCellValue().contains(ReportsConstant.L)) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 Standard Number(CS) doesn't match at row number at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.THREE) != null) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 AB GUID(CS) can't be null at row number at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.FOUR) != null && row.getCell(LOMTConstant.FOUR).getStringCellValue().contains(ReportsConstant.ZERO_BY_ZERO)) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 Correlation Score(CS) doesn't match at row number at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.FIVE) != null && row.getCell(LOMTConstant.FIVE).getStringCellValue().contains(ReportsConstant.NO_CORRELATION)) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 Strength(CS) doesn't match at row number at row number : "+row.getRowNum());
					}
				}
				
				else if (row.getRowNum() == 29) {  //Alignment 
					if (row.getCell(LOMTConstant.ONE) != null && row.getCell(LOMTConstant.ONE).getStringCellValue().contains(ReportsConstant.ST_5)) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 Standards' Topics(CS) doesn't match at row number at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.THREE) != null) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 AB GUID(CS) can't be null at row number at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.FOUR) != null && row.getCell(LOMTConstant.FOUR).getStringCellValue().contains(ReportsConstant.ONE_BY_TWO)) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 Correlation Score(CS) doesn't match at row number at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.FIVE) != null && row.getCell(LOMTConstant.FIVE).getStringCellValue().contains(ReportsConstant.STRENGTH_AVERAGE)) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 Strength(CS) doesn't match at row number at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.EIGHT) != null && row.getCell(LOMTConstant.EIGHT).getStringCellValue().contains(ReportsConstant.UNMET_3)) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 Unmet(Discipline) doesn't match at row number at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.NINE) != null && row.getCell(LOMTConstant.NINE).getStringCellValue().contains(ReportsConstant.MET_4)) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 Met(Discipline) doesn't match at row number at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.ELEVENTH) != null && row.getCell(LOMTConstant.ELEVENTH).getStringCellValue().contains(ReportsConstant.TOC_3)) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 Component Reference(toc) doesn't match at row number at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.FOURTEEN) != null && row.getCell(LOMTConstant.FOURTEEN).getStringCellValue().contains(ReportsConstant.ONE_BY_ONE)) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 Correlation Score(toc) doesn't match at row number at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.FIFTEEN) != null && row.getCell(LOMTConstant.FIFTEEN).getStringCellValue().contains(ReportsConstant.STRENGTH_COMPLETE)) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 Strength(toc) doesn't match at row number at row number : "+row.getRowNum());
					}
				}
				
				else if (row.getRowNum() == 30) {  
					if (row.getCell(LOMTConstant.ONE) != null && row.getCell(LOMTConstant.ONE).getStringCellValue().contains(ReportsConstant.CS_LEVEL_6)) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 Standards' Topics(CS) doesn't match at row number at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.TWO) != null && row.getCell(LOMTConstant.TWO).getStringCellValue().contains(ReportsConstant.L_1)) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 Standard Number(CS) doesn't match at row number at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.THREE) != null) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 AB GUID(CS) can't be null at row number at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.FOUR) != null && row.getCell(LOMTConstant.FOUR).getStringCellValue().contains(ReportsConstant.ZERO_BY_ZERO)) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 Correlation Score(CS) doesn't match at row number at row number : "+row.getRowNum());
					}
					if (row.getCell(LOMTConstant.FIVE) != null && row.getCell(LOMTConstant.FIVE).getStringCellValue().contains(ReportsConstant.NO_CORRELATION)) {
					} else {
						count++;
						logger.log(LogStatus.FAIL, "TC_LOMT-1838 Strength(CS) doesn't match at row number at row number : "+row.getRowNum());
					}
				}
				
				if (count == 0) {
					logger.log(LogStatus.PASS, "TC_LOMT-1838-09_Admin_User_School_Global_Report_Export_Download_Forward_Direct_Report");
					logger.log(LogStatus.PASS, "TC_LOMT-1838-11_Admin_User_School_Global_Report_Export_Download_Forward_Direct_Report");
					logger.log(LogStatus.PASS, "TC_LOMT-1838-17_Admin_User_School_Global_Report_Export_Download_Forward_Direct_Report");
				} else {
					logger.log(LogStatus.FAIL, "TC_LOMT-1838-09_Admin_User_School_Global_Report_Export_Download_Forward_Direct_Report");
					logger.log(LogStatus.FAIL, "TC_LOMT-1838-11_Admin_User_School_Global_Report_Export_Download_Forward_Direct_Report");
					logger.log(LogStatus.FAIL, "TC_LOMT-1838-17_Admin_User_School_Global_Report_Export_Download_Forward_Direct_Report");
				}
			}
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "TC_LOMT-1838 Error occured during data verification : ");
			e.printStackTrace();
		}
	}
	
	public void verifyForwardSharedIntermediaryReportHeaders(XSSFSheet worksheet, String reportName, ExtentTest logger, String source, String pivot, String target) {
		try {
			int count = 0;
			//Title
			if (worksheet.getRow(LOMTConstant.ZERO).getCell(LOMTConstant.ZERO).getStringCellValue().trim().equalsIgnoreCase(ReportsConstant.TITLE)) {
				if (worksheet.getRow(LOMTConstant.ZERO).getCell(LOMTConstant.ONE).getStringCellValue() != null
						&& worksheet.getRow(LOMTConstant.ZERO).getCell(LOMTConstant.ONE).getStringCellValue().equalsIgnoreCase(reportName)) {
				} else {
					count++;
					logger.log(LogStatus.FAIL, "TC_LOMT-1838-Title value does not match in exported file");
				}
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1838-Title header does not match in exported file");
			}
			//User
			if (worksheet.getRow(LOMTConstant.ONE).getCell(LOMTConstant.ZERO).getStringCellValue().trim().equalsIgnoreCase(ReportsConstant.USER.trim())) {
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
					count++;
					logger.log(LogStatus.FAIL, "TC_LOMT-1838-User name does not match in exported file");
				}
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1838-User headers does not match in exported file");
			}

			//Date of generation
			if (worksheet.getRow(LOMTConstant.TWO).getCell(LOMTConstant.ZERO).getStringCellValue().trim().equalsIgnoreCase(ReportsConstant.DATE_TIME_GENERATION.trim())) {
				if (isValidFormat(worksheet.getRow(LOMTConstant.TWO).getCell(LOMTConstant.ONE).getStringCellValue().trim())) {
				} else {
					count++;
					logger.log(LogStatus.FAIL, "TC_LOMT-1838-Date of generation format does not match in exported file");
				}
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1840-1838 of generation header does not match in exported file");
			}

			//Alignments 
			if (worksheet.getRow(LOMTConstant.THREE).getCell(LOMTConstant.ZERO).getStringCellValue().trim().equalsIgnoreCase(ReportsConstant.ALIGNMENTS)) {
				if (worksheet.getRow(LOMTConstant.THREE).getCell(LOMTConstant.ONE).getStringCellValue().contains(ReportsConstant.CENTRAL)
						&& worksheet.getRow(LOMTConstant.THREE).getCell(LOMTConstant.ONE).getStringCellValue().contains(ReportsConstant.PERIPHERAL)) {
				} else {
					count++;
					logger.log(LogStatus.FAIL, "TC_LOMT-1838-Alignments value(Central/Peripheral) does not match in exported file");
				}
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1838-Alignments header does not match in exported file");
			}

			//Standard - Left side
			if (worksheet.getRow(LOMTConstant.FIVE).getCell(LOMTConstant.ZERO).getStringCellValue().trim().equalsIgnoreCase(ReportsConstant.STANDARD)) {
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1838-Standard header does not match in exported file");
			}
			
			//Component Alignment - Right side
			if (worksheet.getRow(LOMTConstant.FIVE).getCell(LOMTConstant.EIGHT).getStringCellValue().trim().equalsIgnoreCase(ReportsConstant.COMPONENT_ALIGNMENT)) {
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1838-Component Alignment header does not match in exported file");
			}
			
			//Content - Right side
			if (worksheet.getRow(LOMTConstant.FIVE).getCell(LOMTConstant.ELEVENTH).getStringCellValue().trim().equalsIgnoreCase(ReportsConstant.CONTENT)) {
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1838-Content header does not match in exported file");
			}

			//Title - Left side
			if (worksheet.getRow(LOMTConstant.SIX).getCell(LOMTConstant.ZERO).getStringCellValue().trim().equalsIgnoreCase(ReportsConstant.TITLE)) {
				if (worksheet.getRow(LOMTConstant.SIX).getCell(LOMTConstant.ONE).getStringCellValue() != null &&
						worksheet.getRow(LOMTConstant.SIX).getCell(LOMTConstant.ONE).getStringCellValue().equalsIgnoreCase(source)) {
				} else {
					count++;
					logger.log(LogStatus.FAIL, "TC_LOMT-1838-Title of Curriculum Standard should not be null in exported file");
				}
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1838-Title header does not match in exported file");
			}
			
			//Intermediary - right side
			if (worksheet.getRow(LOMTConstant.SIX).getCell(LOMTConstant.EIGHT).getStringCellValue().trim().equalsIgnoreCase(ReportsConstant.INTERMEDIARY)) {
				if (worksheet.getRow(LOMTConstant.SIX).getCell(LOMTConstant.NINE).getStringCellValue() != null &&
						worksheet.getRow(LOMTConstant.SIX).getCell(LOMTConstant.NINE).getStringCellValue().equalsIgnoreCase(pivot)) {
				} else {
					count++;
					logger.log(LogStatus.FAIL, "TC_LOMT-1838-Title of Intermediary should not be null in exported file");
				}
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1838-Title header does not match in exported file");
			}
			
			//Program right side
			if (worksheet.getRow(LOMTConstant.SIX).getCell(LOMTConstant.ELEVENTH).getStringCellValue().trim().equalsIgnoreCase(ReportsConstant.PROGRAM)) {
				if (worksheet.getRow(LOMTConstant.SIX).getCell(LOMTConstant.ELEVENTH).getStringCellValue() != null) {
				} else {
					count++;
					logger.log(LogStatus.FAIL, "TC_LOMT-1838-Program(toc) should not be null in exported file");
				}
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1838-Program(toc) header does not match in exported file");
			}
			

			// Country - Left side
			if (worksheet.getRow(LOMTConstant.SEVEN).getCell(LOMTConstant.ZERO).getStringCellValue().equalsIgnoreCase(ReportsConstant.COUNTRY)) {
				if (worksheet.getRow(LOMTConstant.SEVEN).getCell(LOMTConstant.ONE).getStringCellValue() != null) {
				} else {
					count++;
					logger.log(LogStatus.FAIL, "TC_LOMT-1838-Country should not be null in exported file");
				}
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1838-Country header does not match in exported file");
			}
			
			//Course - right side
			if (worksheet.getRow(LOMTConstant.SEVEN).getCell(LOMTConstant.ELEVENTH).getStringCellValue().equalsIgnoreCase(ReportsConstant.COURSE)) {
				if (worksheet.getRow(LOMTConstant.SEVEN).getCell(LOMTConstant.TWELEVE).getStringCellValue() != null) {
				} else {
					count++;
					logger.log(LogStatus.FAIL, "TC_LOMT-1838-Course should not be null in exported file");
				}
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1838-Course header does not match in exported file");
			}

			//Grade - left side
			if (worksheet.getRow(LOMTConstant.EIGHT).getCell(LOMTConstant.ZERO).getStringCellValue().equalsIgnoreCase(ReportsConstant.GRADE)) {
				if (worksheet.getRow(LOMTConstant.EIGHT).getCell(LOMTConstant.ONE).getStringCellValue() != null) {
				} else {
					count++;
					logger.log(LogStatus.FAIL, "TC_LOMT-1838-Grade(Source) should not be null in exported file");
				}
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1838-Grade header(Source) does not match in exported file");
			}
			
			//Product title - Right side
			if (worksheet.getRow(LOMTConstant.EIGHT).getCell(LOMTConstant.ELEVENTH).getStringCellValue().equalsIgnoreCase(ReportsConstant.PRODUCT)) {
				if (worksheet.getRow(LOMTConstant.EIGHT).getCell(LOMTConstant.TWELEVE).getStringCellValue() != null 
				&& worksheet.getRow(LOMTConstant.EIGHT).getCell(LOMTConstant.TWELEVE).getStringCellValue().equalsIgnoreCase(target)) {
				} else {
					count++;
					logger.log(LogStatus.FAIL, "TC_LOMT-1838-Product(toc) should not be null in exported file");
				}
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1838-Product(toc) header does not match in exported file");
			}
			
			//Geographic Area or Country - right side
			if (worksheet.getRow(LOMTConstant.NINE).getCell(LOMTConstant.ELEVENTH).getStringCellValue().equalsIgnoreCase(ReportsConstant.GEOGRAPHIC_AREA_OR__COUNTRY)) {
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1838-Geographic Area or Country(toc) header does not match in exported file");
			}
			
			//STATE_OR_REGION
			if (worksheet.getRow(LOMTConstant.TEN).getCell(LOMTConstant.ELEVENTH).getStringCellValue()
					.equalsIgnoreCase(ReportsConstant.STATE_OR_REGION)) {
				if (worksheet.getRow(LOMTConstant.TEN).getCell(LOMTConstant.TWELEVE).getStringCellValue() != null) {
				} else {
					count++;
					logger.log(LogStatus.FAIL, "TC_LOMT-1838-STATE_OR_REGION(toc) should not be null in exported file");
				}
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1838-STATE_OR_REGION(toc) header does not match in exported file");
			}
			
			//Start Grade, ROW=11, COL=11, VAL ROW NUM=11, COL=12
			if (worksheet.getRow(LOMTConstant.ELEVENTH).getCell(LOMTConstant.ELEVENTH).getStringCellValue().equalsIgnoreCase(ReportsConstant.START_GRADE)) {
				if (worksheet.getRow(LOMTConstant.ELEVENTH).getCell(LOMTConstant.TWELEVE).getStringCellValue() != null) {
				} else {
					count++;
					logger.log(LogStatus.FAIL, "TC_LOMT-1838-Start Grade(toc) should not be null in exported file");
				}
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1838-Start Grade(toc) header does not match in exported file");
			}
			
			//End Grade
			if (worksheet.getRow(LOMTConstant.TWELEVE).getCell(LOMTConstant.ELEVENTH).getStringCellValue().equalsIgnoreCase(ReportsConstant.END_GRADE)) {
				if (worksheet.getRow(LOMTConstant.TWELEVE).getCell(LOMTConstant.TWELEVE).getStringCellValue() != null) {
				} else {
					count++;
					logger.log(LogStatus.FAIL, "TC_LOMT-1838-End Grade(toc) should not be null in exported file");
				}
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1838-End Grade(toc) header does not match in exported file");
			}
			//ISBN 10
			if (worksheet.getRow(LOMTConstant.THIRTEEN).getCell(LOMTConstant.ELEVENTH).getStringCellValue().equalsIgnoreCase(ReportsConstant.ISBN10)) {
				if (worksheet.getRow(LOMTConstant.THIRTEEN).getCell(LOMTConstant.TWELEVE).getStringCellValue() != null) {
				} else {
					count++;
					logger.log(LogStatus.FAIL, "TC_LOMT-1838-ISBN10(toc) should not be null in exported file");
				}
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1838-ISBN10(toc) header does not match in exported file");
			}
			//ISBN13
			if (worksheet.getRow(LOMTConstant.FOURTEEN).getCell(LOMTConstant.ELEVENTH).getStringCellValue().equalsIgnoreCase(ReportsConstant.ISBN13)) {
				if (worksheet.getRow(LOMTConstant.FOURTEEN).getCell(LOMTConstant.TWELEVE).getStringCellValue() != null) {
				} else {
					count++;
					logger.log(LogStatus.FAIL, "TC_LOMT-1838-ISBN13(toc) should not be null in exported file");
				}
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1838-ISBN13(toc) header does not match in exported file");
			}
			//TYPE
			if (worksheet.getRow(LOMTConstant.FIFTEEN).getCell(LOMTConstant.ELEVENTH).getStringCellValue().equalsIgnoreCase(ReportsConstant.TYPE)) {
				if (worksheet.getRow(LOMTConstant.FIFTEEN).getCell(LOMTConstant.TWELEVE).getStringCellValue() != null) {
				} else {
					count++;
					logger.log(LogStatus.FAIL, "TC_LOMT-1838-TYPE(toc) should not be null in exported file");
				}
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1838-TYPE(toc) header does not match in exported file");
			}
			
			//Standards' Strands
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.ZERO).getStringCellValue().equalsIgnoreCase(ReportsConstant.STANDARDS_STRANDS)) {
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1838-Standards' Strands Heading header does not match in exported file");
			}
			//Standards' Topics
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.ONE).getStringCellValue().equalsIgnoreCase(ReportsConstant.STANDARDS_TOPICS)) {
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1838-Standards' Topics Heading header does not match in exported file");
			}
			
			//Standard Number
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.TWO).getStringCellValue().equalsIgnoreCase(ReportsConstant.STANDARDS_NUMBER)) {
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1838-Standards' Number Heading header does not match in exported file");
			}
			//AB GUID
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.THREE).getStringCellValue().equalsIgnoreCase(ReportsConstant.AB_GUIDE)) {
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1838-AB GUID Heading header does not match in exported file");
			}
			//Correlation Score
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.FOUR).getStringCellValue().equalsIgnoreCase(ReportsConstant.CORRELATION_SCORE)) {
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1838-Correlation Score Heading header does not match in exported file");
			}
			//Strength
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.FIVE).getStringCellValue().equalsIgnoreCase(ReportsConstant.STRENGTH)) {
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1838-Strength Heading header does not match in exported file");
			}
			//Peripheral Alignments
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.SIX).getStringCellValue().equalsIgnoreCase(ReportsConstant.PERIPHERAL_ALIGNMENTS)) {
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1838-Peripheral Alignments Heading header does not match in exported file");
			}
			//Unmet Statements
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.EIGHT).getStringCellValue().equalsIgnoreCase(ReportsConstant.UNMET_STATEMENTS)) {
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1838-Unmet Statements Heading header does not match in exported file");
			}
			//Met Statements
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.NINE).getStringCellValue().equalsIgnoreCase(ReportsConstant.MET_STATMENTS)) {
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1838-Met Statements Heading header does not match in exported file");
			}
			//Component Reference
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.ELEVENTH).getStringCellValue().equalsIgnoreCase(ReportsConstant.COMPONENT_REFERENCE)) {
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1838-Component Reference Heading header does not match in exported file");
			}
			//Page Start
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.TWELEVE).getStringCellValue().equalsIgnoreCase(ReportsConstant.START_PAGE)) {
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1838-Page Start Heading header does not match in exported file");
			}
			//Page End
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.THIRTEEN).getStringCellValue().equalsIgnoreCase(ReportsConstant.END_PAGE)) {
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1838-Page End Heading header does not match in exported file");
			}
			//Correlation Score
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.FOURTEEN).getStringCellValue().equalsIgnoreCase(ReportsConstant.CORRELATION_SCORE)) {
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1838-Correlation Score Heading header does not match in exported file");
			}
			//Strength
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.FIFTEEN).getStringCellValue().equalsIgnoreCase(ReportsConstant.STRENGTH)) {
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1838-Strength Heading header does not match in exported file");
			}
			//Peripheral Alignments
			if (worksheet.getRow(LOMTConstant.SEVENTEEN).getCell(LOMTConstant.SIXTEEN).getStringCellValue().equalsIgnoreCase(ReportsConstant.PERIPHERAL_ALIGNMENTS)) {
			} else {
				count++;
				logger.log(LogStatus.FAIL, "TC_LOMT-1838-Peripheral Alignments Heading header does not match in exported file");
			}
			
			if (count == 0) {
				logger.log(LogStatus.PASS, "TC_LOMT-1838-07_Admin_User_School_Global_Report_Export_Download_Forward_Direct_Report");
				logger.log(LogStatus.PASS, "TC_LOMT-1838-13_Admin_User_School_Global_Report_Export_Download_Forward_Direct_Report");
				logger.log(LogStatus.PASS, "TC_LOMT-1838-15_Admin_User_School_Global_Report_Export_Download_Forward_Direct_Report");
			} else {
				logger.log(LogStatus.FAIL, "TC_LOMT-1838-07_Admin_User_School_Global_Report_Export_Download_Forward_Direct_Report");
				logger.log(LogStatus.FAIL, "TC_LOMT-1838-13_Admin_User_School_Global_Report_Export_Download_Forward_Direct_Report");
				logger.log(LogStatus.FAIL, "TC_LOMT-1838-15_Admin_User_School_Global_Report_Export_Download_Forward_Direct_Report");
			}
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "TC_LOMT-1838-07_Admin_User_School_Global_Report_Export_Download_Forward_Direct_Report");
			logger.log(LogStatus.FAIL, "TC_LOMT-1838-13_Admin_User_School_Global_Report_Export_Download_Forward_Direct_Report");
			logger.log(LogStatus.FAIL, "TC_LOMT-1838-15_Admin_User_School_Global_Report_Export_Download_Forward_Direct_Report");
		}
	}
	

}
