package lomt.pearson.test_script.externalframework;

import lomt.pearson.api.externalframework.ExternalFramework2;
import lomt.pearson.constant.LOMTConstant;
import lomt.pearson.constant.TestCases;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExternalFrameworkTestScript {
	
	private static int year = 0;
	private static int startYear = 2080;
	private static int endYear = 2090;
	private static int reIngestionYear = 21;
	private static int yearNthLevel = 0;
	
	ExtentTest logger;
	ExtentReports reports = new ExtentReports(LOMTConstant.REPORT_EXF_FILE_PATH, true);

	ExternalFramework2 exf = new ExternalFramework2();

	@Test(priority = 0)
	public void setup() {
		exf.openBrowser();
		exf.login();
	}

	@Test(priority = 1)
	public void ingestionExternalFrameworkForHE() {
		logger = reports.startTest(LOMTConstant.HE_LOB+LOMTConstant.EMPTY_SPACE+LOMTConstant.EXTERNAL_FRAMEWORK_INGESTION, 
				LOMTConstant.LOMT_1357+LOMTConstant.COMMA+LOMTConstant.EMPTY_SPACE+LOMTConstant.TC_COUNT_HE_EXF);
		
		exf.heBrowsePage(logger);
		exf.createUploadStructureFirstPageExf(logger);	
		
		exf.backLinKSelection(logger);	
		exf.getLOBAndStructure();
		
		exf.createUploadStructureWithoutMetaDataPageExf();
		logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_09_WITHOUT_VALUE_NEXTBTN_EXFRAM_HE);
		
		exf.commonBackLink();		
		exf.createUploadStructureMetaDataPageExf(logger);
		
		//Ingestion with all the mandatory and non-mandatory fields along-with all meta data
		exf.externalFrameworkIngestion(logger, LOMTConstant.EXF_ALL_FIELDS);
		exf.commonBackLink();
		exf.createUploadStructureWithoutMetaDataPageExf();
		
		exf.externalFrameworkIngestion(logger, LOMTConstant.EXF_NON_MANDATORY_FIELDS);
		exf.commonBackLink();
		exf.createUploadStructureWithoutMetaDataPageExf();
		
		exf.externalFrameworkIngestion(logger, LOMTConstant.EXF_WRONG_FORMAT_FILE);
		exf.commonBackLink();
		exf.createUploadStructureWithoutMetaDataPageExf();
		
		exf.externalFrameworkIngestion(logger, LOMTConstant.EXF_WITHOUT_MANDATORY_FIELDS);
		exf.commonBackLink();
		exf.createUploadStructureWithoutMetaDataPageExf();
		
		exf.externalFrameworkIngestion(logger, LOMTConstant.WRONG_GRADE_VALUE);
		exf.commonBackLink();
		exf.createUploadStructureWithoutMetaDataPageExf();
		
		exf.externalFrameworkIngestion(logger, LOMTConstant.LEVELS_AT_SAME_ROW);
		exf.commonBackLink();
		exf.createUploadStructureWithoutMetaDataPageExf();
		
		// common TCs for ingestion
		exf.externalFrameworkIngestion(logger, LOMTConstant.COMMON_TCS_INGESTION);
		exf.commonBackLink();
		exf.createUploadStructureWithoutMetaDataPageExf();
		
		//Ingestion 10th level
		exf.externalFrameworkIngestion(logger, LOMTConstant.NTH_LEVEL);
		
		// Verified ingested ExF data
		exf.lomtHELOBPage();
		exf.verifyIngestedDataOnResultPage(logger, 0, 0);
		
		reports.endTest(logger);
		reports.flush();
	}
	
	@Test(priority = 2)
	public void ingestionExternalFrameworkForEnglish() {
		logger = reports.startTest(LOMTConstant.ENGLISH_LOB+LOMTConstant.EMPTY_SPACE+LOMTConstant.EXTERNAL_FRAMEWORK_INGESTION, 
				LOMTConstant.LOMT_1357+LOMTConstant.COMMA+LOMTConstant.EMPTY_SPACE+LOMTConstant.TC_COUNT_ENGLISH_EXF);
		
		exf.englishBrowsePage(logger);
		exf.createUploadStructureFirstPageExf(logger);	
		
		exf.backLinKSelection(logger);	
		exf.getLOBAndStructure();
		
		exf.createUploadStructureWithoutMetaDataPageExf();
		logger.log(LogStatus.PASS, TestCases.TC_LOMT_1357_38_WITHOUT_VALUE_NEXTBTN_ENGLSH_LOB);
		
		exf.commonBackLink();		
		exf.createUploadStructureMetaDataPageExf(logger);
		
		//Ingestion with all the mandatory and non-mandatory fields along-with all meta data
		exf.externalFrameworkIngestion(logger, LOMTConstant.EXF_ALL_FIELDS);
		exf.commonBackLink();
		exf.createUploadStructureWithoutMetaDataPageExf();
		
		exf.externalFrameworkIngestion(logger, LOMTConstant.EXF_NON_MANDATORY_FIELDS);
		exf.commonBackLink();
		exf.createUploadStructureWithoutMetaDataPageExf();
		
		exf.externalFrameworkIngestion(logger, LOMTConstant.EXF_WRONG_FORMAT_FILE);
		exf.commonBackLink();
		exf.createUploadStructureWithoutMetaDataPageExf();
		
		exf.externalFrameworkIngestion(logger, LOMTConstant.EXF_WITHOUT_MANDATORY_FIELDS);
		exf.commonBackLink();
		exf.createUploadStructureWithoutMetaDataPageExf();
		
		exf.externalFrameworkIngestion(logger, LOMTConstant.WRONG_GRADE_VALUE);
		exf.commonBackLink();
		exf.createUploadStructureWithoutMetaDataPageExf();
		
		exf.externalFrameworkIngestion(logger, LOMTConstant.LEVELS_AT_SAME_ROW);
		exf.commonBackLink();
		exf.createUploadStructureWithoutMetaDataPageExf();
		
		// common TCs for ingestion
		exf.externalFrameworkIngestion(logger, LOMTConstant.COMMON_TCS_INGESTION);
		exf.commonBackLink();
		exf.createUploadStructureWithoutMetaDataPageExf();
		
		//Ingestion 10th level
		exf.externalFrameworkIngestion(logger, LOMTConstant.NTH_LEVEL);
		
		// Verified ingested ExF data
		exf.lomtEnglishLOBPage();
		exf.verifyIngestedDataOnResultPage(logger, 0, 0);
		
		reports.endTest(logger);
		reports.flush();
	}
	
	@Test(priority = 3)
	public void ingestionExternalFrameworkForSchool() {
		logger = reports.startTest(LOMTConstant.SCHOOL+LOMTConstant.EMPTY_SPACE+LOMTConstant.EXTERNAL_FRAMEWORK_INGESTION, 
				LOMTConstant.LOMT_1357+LOMTConstant.COMMA+LOMTConstant.EMPTY_SPACE+LOMTConstant.TC_COUNT_SCHOOL_EXF);
		
		exf.sgBrowsePage(logger);
		exf.createUploadStructureFirstPageExf(logger);	
		
		exf.backLinKSelection(logger);	
		exf.getLOBAndStructure();
		
		//De-scoped
		logger.log(LogStatus.INFO, TestCases.TC_LOMT_1357_67_WITHOUT_VALUE_NEXTBTN_SCHOOL_LOB);
		
		 year = startYear + (int)Math.round(Math.random() * (endYear - startYear));
		 System.out.println("year : "+year);
		 exf.getMetaDataFields(year);
		 
		exf.externalFrameworkIngestion(logger, LOMTConstant.EXF_ALL_FIELDS);
		exf.commonBackLink();
		
		int yearNonMandatory = year-1;
		exf.getMetaDataFields(yearNonMandatory);
		System.out.println("yearNonMandatory : "+yearNonMandatory);		
		exf.externalFrameworkIngestion(logger, LOMTConstant.EXF_NON_MANDATORY_FIELDS);
		
		exf.commonBackLink();		
		exf.getMetaDataFields(year);		
		exf.externalFrameworkIngestion(logger, LOMTConstant.EXF_WRONG_FORMAT_FILE);
		
		exf.commonBackLink();
		exf.getMetaDataFields(year);		
		exf.externalFrameworkIngestion(logger, LOMTConstant.EXF_WITHOUT_MANDATORY_FIELDS);
		
		exf.commonBackLink();
		exf.getMetaDataFields(year);		
		exf.externalFrameworkIngestion(logger, LOMTConstant.WRONG_GRADE_VALUE);
		
		exf.commonBackLink();
		exf.getMetaDataFields(year);
		exf.externalFrameworkIngestion(logger, LOMTConstant.LEVELS_AT_SAME_ROW);
		
		exf.commonBackLink();
		int yearCommon = year-2;
		exf.getMetaDataFields(yearCommon);		
		exf.externalFrameworkIngestion(logger, LOMTConstant.COMMON_TCS_INGESTION);
		
		exf.commonBackLink();
		yearNthLevel = year+3;
		exf.getMetaDataFields(yearNthLevel);			
		exf.externalFrameworkIngestion(logger, LOMTConstant.NTH_LEVEL);
		
		exf.verifyIngestedDataOnResultPage(logger, year, yearNthLevel);
		
		reports.endTest(logger);
		reports.flush();
	}
	
	@Test(priority = 4)
	public void metaDataExternalFramework() {
		logger = reports.startTest(LOMTConstant.META_DATA+LOMTConstant.EMPTY_SPACE+LOMTConstant.EXTERNAL_FRAMEWORK_INGESTION, 
				LOMTConstant.LOMT_1358+LOMTConstant.COMMA+LOMTConstant.EMPTY_SPACE+" TC count is 26");
		//School
		exf.metaDataExternalFramework(logger);
		
		reports.endTest(logger);
		reports.flush();
	}
	
	@Test(priority = 5)
	public void selectCutomExternalFrameworkForSchool() {
		logger = reports.startTest(LOMTConstant.CUSTOME_EXF_SCHOOL,LOMTConstant.LOMT_1389+LOMTConstant.COMMA+LOMTConstant.EMPTY_SPACE+" TC count is 19");
		
		exf.selectCutomExternalFrameworkForSchool(logger);
		
		reports.endTest(logger);
	}
	
	@Test(priority = 6)
	public void exportExternalFramework() {
		logger = reports.startTest(LOMTConstant.EXTERNAL_FRAMEWORK_EXPORT + LOMTConstant.FOR_HE_ENGLISH_SCHOOL,
				LOMTConstant.LOMT_1408 + LOMTConstant.COMMA + LOMTConstant.EMPTY_SPACE + LOMTConstant.LOMT_1408_TC);
		
		logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_01_BASICBROWSECANNOT_EXPORT_HE);
		exf.exportExternalFramework(LOMTConstant.HE_LOB, logger, 0); 

		//logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_19_BASICBROWSECANNOT_EXPORT_ENGLISH);
		exf.exportExternalFramework(LOMTConstant.ENGLISH_LOB, logger, 0); 

		logger.log(LogStatus.PASS, TestCases.TC_LOMT_1408_37_BASICBROWSECANNOT_EXPORT_SCHOOL);
		exf.exportExternalFramework(LOMTConstant.SCHOOL, logger, yearNthLevel); 

		reports.endTest(logger);
		reports.flush();
	} 
	
	@Test(priority = 7)
	public void externalFrameworkReingestionHE() {
		logger = reports.startTest(LOMTConstant.EXTERNAL_FRAMEWORK_RE_INGESTION+LOMTConstant.EMPTY_SPACE+LOMTConstant.HE_LOB,
				LOMTConstant.LOMT_1409 + LOMTConstant.COMMA + LOMTConstant.EMPTY_SPACE + LOMTConstant.TC_COUNT_EXF_HE);
		
		exf.searchAndExportExFFileReingestion(LOMTConstant.HE_LOB, logger, 0, 0);
				
		reports.endTest(logger);
		reports.flush();
	}
	
	@Test(priority = 8)
	public void externalFrameworkReingestionEnglish() {
		logger = reports.startTest(LOMTConstant.EXTERNAL_FRAMEWORK_RE_INGESTION+LOMTConstant.EMPTY_SPACE+LOMTConstant.ENGLISH_LOB,
				LOMTConstant.LOMT_1409 + LOMTConstant.COMMA + LOMTConstant.EMPTY_SPACE + LOMTConstant.TC_COUNT_EXF_HE);
		
		exf.searchAndExportExFFileReingestion(LOMTConstant.ENGLISH_LOB, logger,0 ,0);
				
		reports.endTest(logger);
		reports.flush();
	}
	
	@Test(priority = 9)
	public void externalFrameworkReingestionSchool() {
		logger = reports.startTest(LOMTConstant.EXTERNAL_FRAMEWORK_RE_INGESTION+LOMTConstant.EMPTY_SPACE+LOMTConstant.SCHOOL,
				LOMTConstant.LOMT_1409 + LOMTConstant.COMMA + LOMTConstant.EMPTY_SPACE + LOMTConstant.TC_COUNT_EXF_HE);
		
		exf.searchAndExportExFFileReingestion(LOMTConstant.SCHOOL, logger, year, reIngestionYear+year);
				
		reports.endTest(logger);
		reports.flush();
	}

	@Test(priority = 10)
	public void tearDown() {
		exf.closeDriverInstance();	
	}

}
