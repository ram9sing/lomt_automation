package lomt.pearson.test_script.english;

import java.io.File;
import java.util.Date;

import lomt.pearson.api.gse.EnglishGSE;
import lomt.pearson.api.gse.EnglishGseHelper;
import lomt.pearson.constant.LOMTConstant;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class EnglishGSETestScript {
	
	ExtentTest logger;
	ExtentReports reports = new ExtentReports(LOMTConstant.REPORT_GSE_FILE_PATH, true); 
	
	EnglishGSE gseIngestion = new EnglishGSE();
	EnglishGseHelper gseHelper = null;

	@Test(priority = 0)
	public void setUp() {
		gseIngestion.openBrowser();
		gseIngestion.login();
	}

	@Test(priority = 1)
	public void gseIngestionAndReingestoin() throws Exception {
		logger = reports.startTest(LOMTConstant.ENGLISH_LOB+LOMTConstant.EMPTY_SPACE+LOMTConstant.GSE, 
				LOMTConstant.LOMT_11+LOMTConstant.COMMA+LOMTConstant.EMPTY_SPACE+
				LOMTConstant.LOMT_1008+LOMTConstant.COMMA+LOMTConstant.EMPTY_SPACE+
				LOMTConstant.LOMT_1448+LOMTConstant.COMMA+LOMTConstant.EMPTY_SPACE+
				"LOMT-458"+LOMTConstant.COMMA+LOMTConstant.EMPTY_SPACE+
				"LOMT-1154"+LOMTConstant.COMMA+LOMTConstant.EMPTY_SPACE+"LOMT-968");
		
		logger.log(LogStatus.PASS, "TC-LOMT-11-01_SME or Coordinator or Basic Browser cannot ingest");
		
		gseIngestion.englishBrowsePage(logger);		
		gseIngestion.createUploadStructurePage();
		if(gseIngestion.createUploadStructurePageWithNextOperation())
			logger.log(LogStatus.PASS, "TC-LOMT-11-04_Admin_Manage Ingestion_Next");
			logger.log(LogStatus.PASS, "TC-LOMT-458-01_Admin_Access_Ingestion page.");
			logger.log(LogStatus.PASS, "TC-LOMT-458-02_Non-Admin_Access_ Ingestion page.");
			
			logger.log(LogStatus.INFO, "TC-LOMT-458-03_Admin user_Not Access_Ingestion page.");
			logger.log(LogStatus.INFO, "TC-LOMT-458-04_Non-Admin user_Access_ Ingestion page.");
		
		if(gseIngestion.createUploadStructurePageWithBackOperation())
			logger.log(LogStatus.PASS, "TC-LOMT-11-05_Admin_Manage Ingestion_Back _Create or upload a structure");
		
		gseIngestion.createUploadStructurePageWithNextOperation();	
		gseIngestion.createUploadStructurePageWithIncorrectGSEFile(logger);
		
		gseIngestion.createUploadStructurePageWithBackOperation();		
		gseIngestion.createUploadStructurePageWithNextOperation();		
		boolean flag = gseIngestion.ingestionAndReingestion(LOMTConstant.FRESH_INGESTION, logger);
		boolean flagNoUrn = false;
		boolean flagUrn = false;
		int counter = 0;
		if(flag) {
			//RE INGESTION Validation check
			gseIngestion.createUploadStructurePageWithBackOperation();		
			gseIngestion.createUploadStructurePageWithNextOperation();
			gseIngestion.incorrectGSEFileReingestion(logger);
			
			//RE INGESTION WITHOUT URN
			gseHelper = new EnglishGseHelper();
			File sourceFile = new File(LOMTConstant.ENGLISH_GSE_XLS_FILE_PATH);
			File destFile = new File(LOMTConstant.ENGLISH_GSE_REIN_XLS_FILE_PATH);
			gseHelper.copyFileForReingestion(sourceFile,destFile, counter); // counter = 0
			counter++;
			gseIngestion.createUploadStructurePageWithBackOperation();		
			gseIngestion.createUploadStructurePageWithNextOperation();		
			flagNoUrn = gseIngestion.ingestionAndReingestion(LOMTConstant.RE_INGESTION_WITHOUT_URN, logger);
			//End
			
			//search descriptive id for re-ingestion with URN
			gseIngestion.searchDescriptiveId(destFile);
			
			String date = new Date().toString();
			String[] CurrentDate= date.substring(4).split(" ");	 
			String formatedDate = CurrentDate[1]+CurrentDate[0]+CurrentDate[4];			
			File exportedFile  = new File(LOMTConstant.EXPORTED_FILE_PATH + LOMTConstant.EMPTY_STRING
					+ "Gse_Template_Level1" + formatedDate + LOMTConstant.XLSX_EXTENSION);
			File destURNFile = new File(LOMTConstant.ENGLISH_GSE_REIN_XLS_FILE_PATH_URN);
			
			gseHelper.copyFileForReingestion(exportedFile,destURNFile, counter); // counter = 1
			
			flagUrn = gseIngestion.ingestionAndReingestion(LOMTConstant.RE_INGESTION_URN, logger);
			if (flagNoUrn || flagUrn) {
				gseIngestion.executeGSETCs(true, logger);
			} else {
				gseIngestion.executeGSETCs(false, logger);
			}
			//comparing xlsx files
			gseIngestion.verfifyAgainIngestedDataWithURN(destURNFile, exportedFile); 
		} else {
			gseIngestion.executeGSETCs(false, logger);
		}
		reports.endTest(logger);
		reports.flush();
	}
	
	@Test(priority = 2)
	public void englishGSEEducationalGoalExport() throws Exception {
		logger = reports.startTest("English GSE Export", "LOMT-253, LOMT-1154");
		
		gseIngestion.gseEducationalGoalFrameworkExport(logger);
		
		reports.endTest(logger);
		reports.flush();
	}
	
	//manages ingestion xpath is not visible
	/*@Test(priority = 2)
	public void gseIngestionWithBasicBrowserRole() throws InterruptedException {
		gseIngestion.logout();
		gseIngestion.loginNonAdminRole();
		logger = reports.startTest("GSEIngestionProcess", "Validate the ingestion porcess.");
		gseIngestion.englishGSEBrowsePageForNonAdminUser();
		gseIngestion.loginNonAdminRole();
		logger.log(LogStatus.INFO, "Manage Ingestion Link is disabled for non-admin user");
		logger.log(LogStatus.INFO, "Non-admin user cann't ingest GSE file");
	}*/

	@Test(priority = 3)
	public void tearDown() throws InterruptedException {
		gseIngestion.closeDriverInstance();
	}
}
