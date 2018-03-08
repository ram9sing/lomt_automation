package lomt.pearson.test_script.nals_sg;

import java.util.List;

import lomt.pearson.api.nals_sg.Intermediary;
import lomt.pearson.constant.LOMTConstant;
import lomt.pearson.constant.SchoolConstant;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class IntermediaryTestScript {

	Intermediary intermediary = new Intermediary();
	
	ExtentTest logger;
	ExtentReports reports = new ExtentReports(LOMTConstant.REPORT_INTERMEDIARY_FILE_PATH, true); 
	String disciplineName = null;

	@Test(priority = 0)
	public void setup() {
		intermediary.openBrowser();		
		intermediary.login();
	}

	@Test(priority = 1)
	public void intermediarIngestionSchollGlobal() throws Exception {
		logger = reports.startTest(SchoolConstant.LOMT_10_TC+LOMTConstant.COMMA+LOMTConstant.EMPTY_SPACE+"LOMT-458");
		List<String> disciplineList = intermediary.getIngestedIntermediaryDiscipline(logger);
		String disciplineName = intermediary.getDisciplineForIngestion(disciplineList);
		System.out.println("disciplineName ######### "+disciplineName);
		
		boolean mIngestionFlag = intermediary.schoolGlobalBrowsePage();
		if (mIngestionFlag) {
		} else {
			logger.log(LogStatus.PASS, "Unable to click on Manage Ingestion link");
			return;
		}
		
		boolean flagBrose = intermediary.createUploadStructurePage1();
		if (flagBrose) {
		} else {
			logger.log(LogStatus.PASS, "Unable to select Intermediary Discipline");
			return;
		}
		intermediary.createUploadStructurePageWithCorrectIntermediaryFile(disciplineName, logger);
		
		intermediary.createUploadStructurePage1();
		intermediary.createUploadStructurePageWithIncorrectIntermediaryFile(logger);
		
		logger.log(LogStatus.INFO, "TC-LOMT-10-09_IntermediaryStatement_MaxNoOfStmts"); 
		logger.log(LogStatus.INFO, "TC-LOMT-10-13_Re-Ingestion"); 
		logger.log(LogStatus.INFO, "TC-LOMT-10-14_Ingestion_RemovedEntries"); 
		
		intermediary.verifyIngestDataUI(logger, disciplineName);
		
		reports.endTest(logger);
		reports.flush();
	}
	
	@Test(priority = 2)
	public void exportIntermediaryForSchoolGlobal() {
		logger = reports.startTest(SchoolConstant.LOMT_615_TC);
		
		logger.log(LogStatus.INFO, "TC-LOMT-615_01_Verify_Export_Option_In_Actions_For_Intermediary_SchoolNA"); 
		logger.log(LogStatus.INFO, "TC-LOMT-615_02_Verify_Export_OpIntermediary_To_ExcelFile_SchoolNA"); 
		logger.log(LogStatus.INFO, "TC-LOMT-615_03_Verify_Intermediaries_written_To_ExcelSpreadsheet_SchoolNA"); 
		logger.log(LogStatus.INFO, "TC-LOMT-615_04_Verify_Title_Of_Excel_Spreadsheet_Is_IntermediaryName_SchoolNA"); 
		logger.log(LogStatus.INFO, "TC-LOMT-615_05_Verify_ColumnA_IntermediaryStatement_In_ExcelSpreadsheet_SchoolNA"); 
		logger.log(LogStatus.INFO, "TC-LOMT-615_06_Verify_ColumnB_Tag_In_ExcelSpreadsheet_SchoolNA"); 
		logger.log(LogStatus.INFO, "TC-LOMT-615_07_Verify_ColumnC_Category_In_ExcelSpreadsheet_SchoolNA"); 
		logger.log(LogStatus.INFO, "TC-LOMT-615_08_Verify_ColumnD_IntermediaryStatementCode_In_ExcelSpreadsheet_SchoolNA"); 
		logger.log(LogStatus.INFO, "TC-LOMT-615_09_Verify_ColumnE_IntermediaryStatementID_In_ExcelSpreadsheet_SchoolNA"); 
		
		intermediary.exportIntermediaryForSchoolGlobal(logger, true);
		
		reports.endTest(logger);
		reports.flush();
	}
	
	@Test(priority = 3)
	public void reingestionIntermediary() {
		logger = reports.startTest("School Global Intermediary Re-ingestion, LOMT-1587, Total TCs 38");
		
		intermediary.exportIntermediaryForSchoolGlobal(logger, false);
		
		logger.log(LogStatus.INFO, "TC-LOMT-1587-19_SchoolGlobal_re-ingest_large sheet");
		
		logger.log(LogStatus.INFO, "TC-LOMT-1587-20_SchoolNALS_re-ingest_sameDecipline"); 
		logger.log(LogStatus.INFO, "TC-LOMT-1587-21_SchoolNALS_re-ingest_VerifyData"); 
		logger.log(LogStatus.INFO, "TC-LOMT-1587-22_SchoolNALS_re-ingest_UpdateIntermediary Statement"); 
		logger.log(LogStatus.INFO, "TC-LOMT-1587-23_SchoolNALS_re-ingest_UpdateIntermediary Statement_splChar"); 
		logger.log(LogStatus.INFO, "TC-LOMT-1587-24_SchoolNALS_re-ingest_UpdateIntermediary Statement_LongChar"); 
		logger.log(LogStatus.INFO, "TC-LOMT-1587-25_SchoolNALS_re-ingest_Update_Tag"); 
		logger.log(LogStatus.INFO, "TC-LOMT-1587-26_SchoolNALS_re-ingest_Update_Tag_Wrong"); 
		logger.log(LogStatus.INFO, "TC-LOMT-1587-27_SchoolNALS_re-ingest_Update_Category"); 
		logger.log(LogStatus.INFO, "TC-LOMT-1587-28_SchoolNALS_re-ingest_Update_Category_Blank"); 
		logger.log(LogStatus.INFO, "TC-LOMT-1587-29_SchoolNALS_re-ingest_Update_Intermediary Statement Code"); 
		logger.log(LogStatus.INFO, "TC-LOMT-1587-30_SchoolNALS_re-ingest_Update_Intermediary Statement Code_blank"); 
		logger.log(LogStatus.INFO, "TC-LOMT-1587-31_SchoolNALS_re-ingest_Add_New"); 
		logger.log(LogStatus.INFO, "TC-LOMT-1587-32_SchoolNALS_re-ingest_Delete_Exisiting"); 
		logger.log(LogStatus.INFO, "TC-LOMT-1587-33_SchoolNALS_re-ingest_WrongHeader"); 
		logger.log(LogStatus.INFO, "TC-LOMT-1587-34_SchoolNALS_re-ingest_Blank_IntermediaryStatement"); 
		logger.log(LogStatus.INFO, "TC-LOMT-1587-35SchoolNALS_re-ingest_Blank_Tag"); 
		logger.log(LogStatus.INFO, "TC-LOMT-1587-36_SchoolNALS_re-ingest_intermediaryNoExist_Have URN"); 
		logger.log(LogStatus.INFO, "TC-LOMT-1587-37_SchoolNALS_re-ingest_URNinStatemetn_intermediaryExistNotFound"); 
		logger.log(LogStatus.INFO, "TC-LOMT-1587-38_SchoolNALS_re-ingest_large sheet"); 
		
		reports.endTest(logger);
		reports.flush();
	}

	@Test(priority = 4)
	public void tearDown() {
		intermediary.closeDriverInstance();
	}

}
