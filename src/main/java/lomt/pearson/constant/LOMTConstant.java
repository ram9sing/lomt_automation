package lomt.pearson.constant;

public class LOMTConstant {

	// LOBs constant
	public static String ENGLISH_LOB = "English";
	public static String HE_LOB = "Higher Education";
	public static String NALS_LOB = "North America Learning Services";
	public static String SG_LOB = "School Global";
	public static String SCHOOL = "School";
	public static String CUSTOM = "Custom";
	
	public static String LOMT_ENVIRONMENT = "lomt.environment";
	public static String USER_NAME = "lomt.cms.service.user";
	public static String PASSWORD = "lomt.cms.service.password";
	
	public static String USER_NAME_TEST = "lomt.cms.service.test.user";
	
	public static String USER_NAME_LEARNING_USER = "lomt.cms.service.learning.user";
	public static String PASSWORD_LEARNING_USER_PWD = "lomt.cms.service.learning.user.password";
	
	public static String USER_NAME_LEARNING_SME = "lomt.cms.service.learning.sme";
	public static String PASSWORD_LEARNING_SME_PWD = "lomt.cms.service.learning.sme.password";
	
	public static String USER_NAME_LEARNING_EDITOR = "lomt.cms.service.learning.editor";
	public static String PASSWORD_LEARNING_EDITOR = "lomt.cms.service.learning.editor.password";
	
	public static String ADMIN_USER_PRIVILEGE = "Admin";
	public static String BASIC_BROWSER_USER_PRIVILEGE = "Basic Browser";
	
	public static String CONFIGURATION_FILE_PATH = "src/main/java/lomt/pearson/resources/lomt-config.properties";
	public static String LOG4J_FILE_PATH = "src/main/java/lomt/pearson/resources/log4j.properties";
	public static String REPORT_FILE_PATH = "src/main/java/lomt/pearson/reports/lomt_report.html";
	public static String REPORT_FILE_PATH_COMMON = "src/main/java/lomt/pearson/reports/lomt_common.html";
	public static String REPORT_INTERMEDIARY_FILE_PATH = "src/main/java/lomt/pearson/reports/lomt_intermediary.html";
	public static String REPORT_GSE_FILE_PATH = "src/main/java/lomt/pearson/reports/lomt_gse.html";
	public static String REPORT_PRODUCT_TOC_FILE_PATH = "src/main/java/lomt/pearson/reports/lomt_product_toc.html";
	public static String REPORT_EXF_FILE_PATH = "src/main/java/lomt/pearson/reports/lomt_exf.html";
	public static String REPORT_SCHOOL_FILE_PATH = "src/main/java/lomt/pearson/reports/lomt_school.html";
	public static String REPORT_HE_FILE_PATH = "src/main/java/lomt/pearson/reports/lomt_he.html";
	public static String NONADMIN_REPORT_FILE_PATH = "src/main/java/lomt/pearson/reports/lomt_non_admin.html";

	public static String ENGLISH_GSE_FILE_PATH = "src/main/java/lomt/pearson/fileupload/english/gse/ingestion/GSE_Ingestion_FileUpload.exe";
	public static String ENGLISH_GSE_REINGESTION_FILE_PATH = "src/main/java/lomt/pearson/fileupload/english/gse/reingestion/GSERe-ingestionFileUpload.exe";
	public static String ENGLISH_GSE_REINGESTION_FILE_PATH_URN = "src/main/java/lomt/pearson/fileupload/english/gse/reingestion/GSERe-ingestionFileUpload-URN.exe";
	public static String ENGLISH_GSE_REINGESTION_NO_URN_FILE_PATH = "src/main/java/lomt/pearson/fileupload/english/gse/Gse_Template-Re-ingestion-No-URN_GSEFileUpload.exe";
			
	public static String ENGLISH_GSE_XLS_FILE_PATH = System.getProperty("user.dir")+ "\\src\\main\\java\\lomt\\pearson\\fileupload\\english\\gse\\ingestion\\Gse_Ingestion_Template.xlsx";
	public static String ENGLISH_GSE_REIN_XLS_FILE_PATH = System.getProperty("user.dir")+ "\\src\\main\\java/lomt\\pearson\\fileupload\\english\\gse\\reingestion\\GSERe-ingestion.xlsx";
	public static String ENGLISH_GSE_REIN_XLS_FILE_PATH_URN = System.getProperty("user.dir")+ "\\src\\main\\java\\lomt\\pearson\\fileupload\\english\\gse\\reingestion\\GSERe-ingestion_URN.xlsx";
	
	public static String ENGLISH_GSE_SHEET_WITHOUT_MANDATORY_FIELDS_FILE_PATH = "src/main/java/lomt/pearson/fileupload/english/gse/GSEFileUploadIncorrect.exe";
	public static String ENGLISH_GSE_INCORRECT_FILE_PATH_URN = "src/main/java/lomt/pearson/fileupload/english/gse/GSERe-ingestionFileUpload-URN.exe";
	public static String INVALID_FORMAT_FILE_TO_INGEST = "src/main/java/lomt/pearson/fileupload/english/gse/IngestionWrongFormat_FileUpload.exe";
	public static String ENGLISH_GSE_FILE_PATH_WITHOUT_NON_MANDATORY_FIELDS = "src/main/java/lomt/pearson/fileupload/english/gse/IngestNonMandatory_GSEFileUpload.exe";
	
	public static String INTERMEDIARY_FILE_PATH = "src/main/java/lomt/pearson/fileupload/intermediary/IntermediaryFileUpload.exe";
	public static String INTERMEDIARY_REINGESTION_FILE_PATH = "src/main/java/lomt/pearson/fileupload/intermediary/reingestion/Intermediary_ReingestionFileUpload.exe";
	public static String INTERMEDIARY_INCORRECT_FILE_PATH = "src/main/java/lomt/pearson/fileupload/intermediary/IntermediaryIncorrectFileUpload.exe";
	
	public static String INVALID_FORMAT_FILE_PATH = "src/main/java/lomt/pearson/fileupload/english/product_toc/Ingestion_WrongFormat_FileUpload.exe";
	public static String PRODUCT_TOC_FILE_WITHOUT_MANDATORY_FIELDS_PATH_ENGLISH = "src/main/java/lomt/pearson/fileupload/english/product_toc/TOC_GSE_IngestionFile_Without_MandatoryFields_FileUpload.exe";
	public static String PRODUCT_FILE_PATH_ENGLISH = "src/main/java/lomt/pearson/fileupload/english/product_toc/TOC_GSE_IngestionFile_FileUpload.exe";
	public static String PRODUCT_FILE_NON_MANDATORY_FIELDS_PATH_ENGLISH = "src/main/java/lomt/pearson/fileupload/english/product_toc/TOC_GSE_IngestionFile_Without_Non_MandatoryFields_FileUpload.exe";
	
	public static String PRODUCT_TOC_FILE_WITHOUT_MANDATORY_FIELDS_PATH_HE = "src/main/java/lomt/pearson/fileupload/highereducation/producttoc/TOC_HE_IngestionFile_Without_MandatoryFields_FileUpload.exe";
	public static String PRODUCT_FILE_PATH_HE = "src/main/java/lomt/pearson/fileupload/highereducation/producttoc/TOC_HE_IngestionFileUpload.exe";
	public static String PRODUCT_FILE_NON_MANDATORY_FIELDS_PATH_HE = "src/main/java/lomt/pearson/fileupload/highereducation/producttoc/TOC_HE_IngestionFile_Without_Non_MandatoryFields_FileUpload.exe";
	
	//SCHOOL PRODUCT TOC EXE FILE PATH
	public static String PRODUCT_TOC_FILE_WITHOUT_MANDATORY_FIELDS_PATH_SCHOOL = "src/main/java/lomt/pearson/fileupload/nals_school_global/producttoc/TOC_School_IngestionFile_Without_MandatoryFields_FileUpload.exe";
	public static String PRODUCT_FILE_PATH_SCHOOL = "src/main/java/lomt/pearson/fileupload/nals_school_global/producttoc/TOC_School_IngestionFile_FileUpload.exe";
	public static String PRODUCT_FILE_NON_MANDATORY_FIELDS_PATH_SCHOOL = "src/main/java/lomt/pearson/fileupload/nals_school_global/producttoc/TOC_School_IngestionFile_Without_Non_MandatoryFields_FileUploadexe";
	
	public static String PRODUCT_TOC_FILE_PATH_1 = "src/main/java/lomt/pearson/fileupload/nals_school_global/producttoc/igestion_usecase_1/Product_TOC_Ingestion_NoAlignUsecase-1_FileUpload.exe";
	public static String PRODUCT_TOC_FILE_PATH_2 = "src/main/java/lomt/pearson/fileupload/nals_school_global/producttoc/igestion_usecase_2/Product_TOC_Ingestion_NoAlignUsecase-2_FileUpload.exe";
	public static String PRODUCT_TOC_FILE_PATH_3 = "src/main/java/lomt/pearson/fileupload/nals_school_global/producttoc/igestion_usecase_3/Product_TOC_Ingestion_NoAlignUsecase-3_FileUpload.exe";
	public static String PRODUCT_TOC_FILE_PATH_4 = "src/main/java/lomt/pearson/fileupload/nals_school_global/producttoc/igestion_usecase_4/Product_TOC_Ingestion_NoAlignUsecase-4_FileUpload.exe";
	public static String PRODUCT_TOC_FILE_PATH_5 = "src/main/java/lomt/pearson/fileupload/nals_school_global/producttoc/igestion_usecase_5/School_TOC_Ingestion_Usecase-5_FileUpload.exe";
	
	public static String PRODUCT_TOC_FILE_PATH_7 = "src/main/java/lomt/pearson/fileupload/nals_school_global/producttoc/igestion_usecase_7/Product_TOC_School_AlignmnetCode-7_FileUpload.exe";
	public static String PRODUCT_TOC_FILE_PATH_8 = "src/main/java/lomt/pearson/fileupload/nals_school_global/producttoc/igestion_usecase_8/Product_TOC_School_GoalURN-8_FileUpload.exe";
	public static String PRODUCT_TOC_FILE_PATH_9 = "src/main/java/lomt/pearson/fileupload/nals_school_global/producttoc/igestion_usecase_9/Product_TOC_Ingestion_Seq_Mismatch_FileUpload.exe";
	public static String PRODUCT_TOC_FILE_PATH_11 = "src/main/java/lomt/pearson/fileupload/nals_school_global/producttoc/igestion_usecase_11/Product_TOC_School_WrongAlignCode_FileUpload.exe";
	public static String PRODUCT_TOC_FILE_PATH_12 = "src/main/java/lomt/pearson/fileupload/nals_school_global/producttoc/igestion_usecase_12/Product_TOC_School_WrongGoalURN_FileUpload.exe";
	public static String PRODUCT_TOC_FILE_PATH_13 = "src/main/java/lomt/pearson/fileupload/nals_school_global/producttoc/igestion_usecase_13/Product_TOC_Ingestion_NonMandFields_FileUpload.exe";
	public static String PRODUCT_FILE_PATH_SCHOOL_15 = "src/main/java/lomt/pearson/fileupload/nals_school_global/producttoc/igestion_usecase_15/Product_TOC_Ingestion_WithoutMandFields_FileUpload.exe";
	
	public static String PRODUCT_FILE_PATH_SCHOOL_26 = "src/main/java/lomt/pearson/fileupload/nals_school_global/producttoc/validation_usecase_26/ProductTitleBlank_FileUpload.exe";
	public static String PRODUCT_FILE_PATH_SCHOOL_27 = "src/main/java/lomt/pearson/fileupload/nals_school_global/producttoc/validation_usecase_27/Level_for_HierarchyBlank_FileUpload.exe";
	public static String PRODUCT_FILE_PATH_SCHOOL_28 = "src/main/java/lomt/pearson/fileupload/nals_school_global/producttoc/validation_usecase_28/Level_Title_Blank_FileUpload.exe";
	public static String PRODUCT_FILE_PATH_SCHOOL_29 = "src/main/java/lomt/pearson/fileupload/nals_school_global/producttoc/validation_usecase_29/School_TOC_Ingestion_Validation_Usecase-29_FileUpload.exe";
	
	public static String PRODUCT_FILE_PATH_SCHOOL_30 = "src/main/java/lomt/pearson/fileupload/nals_school_global/producttoc/validation_usecase_30/School_TOC_Ingestion_Validation_Usecase-30_FileUpload.exe";
	public static String PRODUCT_FILE_PATH_SCHOOL_31 = "src/main/java/lomt/pearson/fileupload/nals_school_global/producttoc/validation_usecase_31/School_TOC_Ingestion_Validation_Usecase-31_FileUpload.exe";
	public static String PRODUCT_FILE_PATH_SCHOOL_32 = "src/main/java/lomt/pearson/fileupload/nals_school_global/producttoc/validation_usecase_32/School_TOC_Ingestion_Validation_Usecase-32_FileUpload.exe";
	public static String PRODUCT_FILE_PATH_SCHOOL_33 = "src/main/java/lomt/pearson/fileupload/nals_school_global/producttoc/validation_usecase_33/ingestion_validation_tc_33_FileUpload.exe";
	
	//English
	public static String PRODUCT_FILE_PATH_ENGLISH_1 = "src/main/java/lomt/pearson/fileupload/english/product_toc/igestion_usecase_1/School_TOC_Ingestion_Usecase-1_FileUpload.exe";
	public static String PRODUCT_FILE_PATH_ENGLISH_2 = "src/main/java/lomt/pearson/fileupload/english/product_toc/igestion_usecase_2/School_TOC_Ingestion_Usecase-2_FileUpload.exe";
	public static String PRODUCT_FILE_PATH_ENGLISH_3 = "src/main/java/lomt/pearson/fileupload/english/product_toc/igestion_usecase_3/School_TOC_Ingestion_Usecase-3_FileUpload.exe";
	public static String PRODUCT_FILE_PATH_ENGLISH_4 = "src/main/java/lomt/pearson/fileupload/english/product_toc/igestion_usecase_4/School_TOC_Ingestion_Usecase-4_FileUpload.exe";
	public static String PRODUCT_FILE_PATH_ENGLISH_6 = "src/main/java/lomt/pearson/fileupload/english/product_toc/igestion_usecase_6/English_TOC_Ingestion_Usecase-6_FileUpload.exe";
	public static String PRODUCT_FILE_PATH_ENGLISH_7 = "src/main/java/lomt/pearson/fileupload/english/product_toc/igestion_usecase_7/School_TOC_Ingestion_Usecase-7_FileUpload.exe";
	public static String PRODUCT_FILE_PATH_ENGLISH_8 = "src/main/java/lomt/pearson/fileupload/english/product_toc/igestion_usecase_8/English_TOC_Ingestion_Usecase-8_FileUpload.exe";
	public static String PRODUCT_FILE_PATH_ENGLISH_10 = "src/main/java/lomt/pearson/fileupload/english/product_toc/igestion_usecase_10/English_TOC_Ingestion_Usecase-10_FileUpload.exe";
	public static String PRODUCT_FILE_PATH_ENGLISH_11 = "src/main/java/lomt/pearson/fileupload/english/product_toc/igestion_usecase_11/English_TOC_Ingestion_Usecase-11_FileUpload.exe";
	
	//HE product TOC
	public static String PRODUCT_FILE_PATH_HE_11 = "src/main/java/lomt/pearson/fileupload/highereducation/producttoc/igestion_usecase_11/HE_TOC_Ingestion_Usecase-1_FileUpload.exe";
	public static String PRODUCT_FILE_PATH_HE_12 = "src/main/java/lomt/pearson/fileupload/highereducation/producttoc/igestion_usecase_12/HE_TOC_Ingestion_Usecase-2_FileUpload.exe";
	public static String PRODUCT_FILE_PATH_HE_13 = "src/main/java/lomt/pearson/fileupload/highereducation/producttoc/igestion_usecase_13/HE_TOC_Ingestion_Usecase-3_FileUpload.exe";
	public static String PRODUCT_FILE_PATH_HE_14 = "src/main/java/lomt/pearson/fileupload/highereducation/producttoc/igestion_usecase_14/HE_TOC_Ingestion_Usecase-4_FileUpload.exe";
	public static String PRODUCT_FILE_PATH_HE_16 = "src/main/java/lomt/pearson/fileupload/highereducation/producttoc/igestion_usecase_16/HE_TOC_Ingestion_Usecase-6_FileUpload.exe";
	public static String PRODUCT_FILE_PATH_HE_18 = "src/main/java/lomt/pearson/fileupload/highereducation/producttoc/igestion_usecase_18/HE_TOC_Ingestion_Usecase-8_FileUpload.exe";
	public static String PRODUCT_FILE_PATH_HE_23 = "src/main/java/lomt/pearson/fileupload/highereducation/producttoc/igestion_usecase_23/HE_TOC_Ingestion_Usecase-23_FileUpload.exe";
	
	public static String HE_PRODUCT_TOC_VALIDATION = "src/main/java/lomt/pearson/fileupload/highereducation/producttoc/validation/Validation_Case_FileUpload.exe";
	public static String HE_PRODUCT_TOC_VALIDATION_ALIGN = "src/main/java/lomt/pearson/fileupload/highereducation/producttoc/validation/ProductTocAlignCode-Validation_FileUpload.exe";
	
	//Product toc Re-ingestion
	public static String SCHOOL_PRODUCT_TOC_XLSX_FILE_PATH_UC2 = "src/main/java/lomt/pearson/fileupload/nals_school_global/producttoc/igestion_usecase_2/Product_TOC_Ingestion_NoAlignUsecase-2.xlsx";
	public static String SCHOOL_PRODUCT_TOC_XLSX_FILE_PATH = "src/main/java/lomt/pearson/fileupload/nals_school_global/producttoc/igestion_usecase_7/Product_TOC_School_AlignmnetCode-7.xlsx";
	public static String HE_PRODUCT_TOC_XLSX_FILE_PATH = "src/main/java/lomt/pearson/fileupload/nals_school_global/producttoc/igestion_usecase_7/Product_TOC_School_AlignmnetCode-7.xlsx";
	public static String ENGLISH_PRODUCT_TOC_XLSX_FILE_PATH = "src/main/java/lomt/pearson/fileupload/english/product_toc/igestion_usecase_6/English_TOC_Ingestion_Usecase-6.xlsx";
	public static String TOC_RE_INGESTION_DEST_FILE_PATH = System.getProperty("user.dir")+ "\\src\\main\\java\\lomt\\pearson\\fileupload\\nals_school_global\\producttoc\\re_ingestion\\";
	public static String TOC_RE_INGESTION_TEMPLATE = "ProductTOCReingestion_Template.xlsx";
	
	public static String TOC_SCHOOL_REINGESTION_EXE_FILE_PATH_XLSX = "src/main/java/lomt/pearson/fileupload/nals_school_global/producttoc/re_ingestion/ProductTOCReingestion_Template.xlsx";
	public static String TOC_SCHOOL_REINGESTION_EXE_FILE_PATH = "src/main/java/lomt/pearson/fileupload/nals_school_global/producttoc/re_ingestion/TOC_REINGESTION_FileUpload.exe";
	
	//English Alignment
	public static String EENGLISH_PRODUCT_TOC_XLSX_FILE_PATH = "src/main/java/lomt/pearson/fileupload/english/product_toc/igestion_usecase_6/English_TOC_Ingestion_Usecase-6.xlsx";
	
	public static String HE_RE_PRODUCT_TOC_XLSX_FILE_PATH = "src/main/java/lomt/pearson/fileupload/highereducation/producttoc/alignment_case/ProductTocAlignCode.xlsx";
	
	//External Framework
	public static String EXF_ALL_FIELDS_XLS_PATH = "src/main/java/lomt/pearson/fileupload/externalframework/exf_ingestion_all_fields/ExF_Ingestion_all_fields_FileUpload.exe";
	public static String EXF_MANDATORY_FIELDS_XLS_PATH = "src/main/java/lomt/pearson/fileupload/externalframework/exf_ingestion_mandatory_fields/ExF_Ingestion_mandatory_fields_FileUpload.exe";
	public static String EXF_WRONG_FORMAT_XLS_PATH = "src/main/java/lomt/pearson/fileupload/externalframework/IngestionWrongFormat_FileUpload.exe";
	public static String EXF_WITHOUT_MANDATORY_FIELDS_XLS_PATH = "src/main/java/lomt/pearson/fileupload/externalframework/exf_without_mandatory_fields/ExF_Ingestion_without_mandatory_fields_FileUpload.exe";
	public static String EXF_WRONG_GRADE_VAL_XLS_PATH = "src/main/java/lomt/pearson/fileupload/externalframework/exf_wrong_grade_and_level_value/ExF_Ingestion_wrong_grade_val_FileUpload.exe";
	public static String EXF_WRONG_LEVEL_VAL_XLS_PATH = "src/main/java/lomt/pearson/fileupload/externalframework/exf_wrong_grade_and_level_value/ExF_Ingestion_level_same_row_FileUpload.exe";
	public static String EXF_COMMON_TCS_INGESTION_XLS_PATH = "src/main/java/lomt/pearson/fileupload/externalframework/exf_ingestion_common_tcs/ExF_Ingestion_common_tcs_FileUpload.exe";
	public static String EXF_INGESTION_10TH_LEVEL_XLS_PATH = "src/main/java/lomt/pearson/fileupload/externalframework/exf_ingestion_common_tcs/ExternalFramework_Stds_10Level_FileUpload.exe";
	
	public static String EXF_REINGESTION_NTH_LEVEL_EXE_PATH = "src/main/java/lomt/pearson/fileupload/externalframework/ex_reingestion/ExF_Reingestion_FileUpload.exe";
	public static String EXF_REINGESTION_NTH_LEVEL_XLS_PATH = "src/main/java/lomt/pearson/fileupload/externalframework/ex_reingestion/External_Framework_Template.xlsx";
	
	public static String HE_INGESTION_VALIDATION_MISSED_FILE_PATH = "src/main/java/lomt/pearson/fileupload/highereducation/educationalgoal/HEIngestion_ValidationMissed_FileUpload.exe";
	public static String HE_INGESTION_FILE_PATH = "src/main/java/lomt/pearson/fileupload/highereducation/educationalgoal/HE_SHEET_short_QADATA_Ingestion_FileUpload.exe";
	public static String HE_REINGESTION_FILE_PATH = "src/main/java/lomt/pearson/fileupload/highereducation/educationalgoal/reingestion/HE_EO_Reingestion_FileUpload.exe";
	
	public static String HE_TOC_INGEST_FILE_PATH = "src/main/java/lomt/pearson/fileupload/highereducation/producttoc/he_ingestion/HE_Ingestion_FileUpload.exe";
	public static String HE_TOC_ALIGN_CODE_INGEST_FILE_PATH = "src/main/java/lomt/pearson/fileupload/highereducation/producttoc/alignment_case/TocAlignCode_ingestion_FileUpload.exe";
	
	//Intermediary ingestion file path
	public static String INTERMEDIARY_INGESTION_PRODUCT_TOC_SCHOOL_FILE_PATH = "src/main/java/lomt/pearson/fileupload/nals_school_global/producttoc/igestion_usecase_7/Economics_Intermediary_Template_FU.exe";
	
	public static String CREATE_STRUCTURE_TILE = "Create or upload a structure";				
	public static String SELECT_LOB = "Select line of business:";
	public static String SELECT_STRUCTURE_TYPE = "Select type of structure:";
	public static String CHOOSE_STRUCTURE_TYPE = "Choose the type of structure you want to create or upload";
	public static String STRUCTURE_PAGE_IMAGE_2_TEXT = "Provide metadata for English GSE (Global Scale of English) Standard";
	public static String STRUCTURE_PAGE_IMAGE_2_TEXT_SG = "Provide metadata for School Global Intermediary";
	public static String STRUCTURE_PAGE_IMAGE_2_TEXT_NALS = "Provide metadata for North America Learning Services Intermediary";
	public static String STRUCTURE_PAGE_IMAGE_3_TEXT = "Upload your .xls or .xlsx file";
	public static String UPLOAD_YOUR_FILE = "Upload your file";
	public static String UPLOAD_YOUR_XLS_FILE = "Upload your .xls or .xlsx file";
	public static String STRUCTURE_PAGE_IMAGE_4_TEXT = "Review outcome";
	public static String INGESTION_SUCESSFULL_MESSAGE = "Ingest Successful";
	public static String INGESTION_FAILED_MESSAGE = "Ingest failed";
	public static String VIEW_FULL_INGESTION_LOG = "VIEW FULL INGEST LOG";
	public static String FRESH_INGESTION = "Fresh Ingestinon";
	public static String RE_INGESTION_WITHOUT_URN = "re-ingesion_urn";
	public static String RE_INGESTION_URN = "re-ingesion_no_urn";
	public static String EXPORT_DONE = "Export done";
	public static String FRESH_INGESTION_WITHOUT_NON_MANDATORY_FIELDS = "Ingestion Without Non-mandatory fields";
	public static String EXF_STRUCTURE_SELECTION_MESSAGE_NALS_2 = "Provide metadata for North America Learning Services Curriculum Standard (custom)"; 
	public static String EXF_STRUCTURE_SELECTION_MESSAGE_SG_2 = "Provide metadata for School Global Curriculum Standard (custom)";
	
	public static String WELCOME_TITLE = "Welcome back to LOMT, learn";
	public static String MANAGE_INGESTION_TEMP_MESSAGE = "Temporary link to ingestion screen";
	public static String PROVIDE_METADATA_HE = "Provide metadata for Higher Education Product";
	public static String PROVIDE_METADATA_NALS = "Provide metadata for North America Learning Services Product";
	public static String PROVIDE_METADATA_NALS_EXF = "Provide metadata for North America Learning Services";
	public static String PROVIDE_METADATA_SG = "Provide metadata for School Global Product";
	public static String PROVIDE_METADATA_HE_EO = "Provide metadata for Higher Education Educational Objective";	
	public static String DRAG_DROP_TEXT = "Drag and drop files here";
	public static String PROVIDE_ENGLISH_PRODUCT_METADASTA = "Provide metadata for English Product";
	public static String PROVIDE_ENGLISH_METADASTA = "Provide metadata for English";
	public static String NO_RESULT_FOUND = "No results found";
	public static String META_DATA_HE = "Provide metadata for Higher Education";
	public static String META_DATA_HE_EXF = "Provide metadata for Higher Education External Framework";
	
	//Validation Error Message
	public static String ROW_NO = "Row No";
	public static String TYPE = "Type";
	public static String MESSAGE = "Message";
	public static String ROW_NO_ERROR_DATA = "1";
	public static String TYPE_ERROR = "Error";
	
	public static String DESCRIPTIVE_ID_MADATORY = "Descriptive ID column is mandatory in Row : 2";
	public static String SYLLABUS_MANDATORY = "Syllabus column is mandatory in Row : 2";
	public static String STATUS_MANDATORY = "Status column is mandatory in Row : 2";
	public static String DESCRIPTOR_MANDATORY = "Descriptor column is mandatory in Row : 2";
	public static String COMMUNICATIVE_CATEGORIES_COUNT_NOT_MORETHAN_THREE = "Communicative Categories record set count should not be greater than 3.";
	public static String GRAMMATICAL_CATEGORIES_COUNT_NOT_MORETHAN_THREE = "Grammatical Categories record set count should not be greater than 3.";
	public static String BUSINESS_SKILLS_NOT_MORETHAN_THREE = "Business Skills record set count should not be greater than 3.";
	public static String ACADEMIC_SKILLS_UP_TO_3 = "Academic Skills record set count should not be greater than 3.";
	public static String ACADEMIC_SKILLS_VAL_INVALID = "Academic Skills value is invalid in Row : 3";
	public static String COMM_CAT_VAL_INVALID = "Communicative Categories value is invalid in Row : 3";
	public static String BUSINESS_SKILLS_VAL_INVALID = "Business Skills value is invalid in Row : 3";
	
	public static String EXPORTED_FILE_PATH = System.getProperty("user.dir")+ "\\src\\main\\java\\lomt\\pearson\\downloads\\";
	public static String ACTUAL_FILE_PATH_RE = System.getProperty("user.dir")+ "\\src\\main\\java\\lomt\\pearson\\fileupload\\english\\gse\\reingestion\\";
	public static String ACTUAL_FILE_PATH = System.getProperty("user.dir")+ "\\src\\main\\java\\lomt\\pearson\\fileupload\\english\\gse\\ingestion\\";
	public static String ACTUAL_FILE_PATH_EXF = System.getProperty("user.dir")+ "\\src\\main\\java\\lomt\\pearson\\fileupload\\externalframework\\exf_ingestion_all_fields\\";
	public static String ACTUAL_FILE_PATH_EXF_SCHOOL = System.getProperty("user.dir")+ "\\src\\main\\java\\lomt\\pearson\\fileupload\\externalframework\\exf_ingestion_common_tcs\\";
	public static String EMPTY_STRING = "";
	public static String EXPORTED_FILE_NAME = "Gse_Template_Level1";
	public static String ACTUAL_FILE_NAME = "Gse_Ingestion_Template";
	public static String ACTUAL_FILE_NAME_EXF = "ExF_Ingestion_all_fields";
	public static String ECONOMICS = "Economics";
	public static String ACTUAL_FILE_NAME_EXF_NTH_LEVEL = "ExternalFramework_Stds_10Level";
	public static String EXPORTED_FILE_NAME_EXF = "External_Framework_Template";
	public static String XLSX_EXTENSION = ".xlsx";
	public static String XLS_EXTENSION = ".xls";
	
	public static String PRODUCT_TOC_XLS_FILE_PATH_1 = System.getProperty("user.dir")+ "\\src\\main\\java\\lomt\\pearson\\fileupload\\nals_school_global\\producttoc\\igestion_usecase_1\\Product_TOC_Ingestion_NoAlignUsecase-1.xlsx";
	public static String PRODUCT_TOC_XLS_FILE_PATH_2 = System.getProperty("user.dir")+ "\\src\\main\\java\\lomt\\pearson\\fileupload\\nals_school_global\\producttoc\\igestion_usecase_2\\Product_TOC_Ingestion_NoAlignUsecase-2.xlsx";
	public static String PRODUCT_TOC_XLS_FILE_PATH_3 = System.getProperty("user.dir")+ "\\src\\main\\java\\lomt\\pearson\\fileupload\\nals_school_global\\producttoc\\igestion_usecase_5\\Product_TOC_Ingestion_NoAlignUsecase-5.xlsx";
	public static String PRODUCT_TOC_XLS_FILE_PATH_4 = System.getProperty("user.dir")+ "\\src\\main\\java\\lomt\\pearson\\fileupload\\nals_school_global\\producttoc\\igestion_usecase_7\\Product_TOC_School_AlignmnetCode-7.xlsx";
	public static String PRODUCT_TOC_XLS_FILE_PATH_5 = System.getProperty("user.dir")+ "\\src\\main\\java\\lomt\\pearson\\fileupload\\nals_school_global\\producttoc\\igestion_usecase_13\\Product_TOC_Ingestion_NonMandFields.xlsx";
	
	public static String P_TOC_XLS_PATH_ALIGN_GOAL_URN = System.getProperty("user.dir")+ "\\src\\main\\java\\lomt\\pearson\\fileupload\\nals_school_global\\producttoc\\igestion_usecase_8\\Product_TOC_School_GoalURN-8.xlsx";
	
	public static String _TEMPLATE = "_Template";
	public static String DATE_FORMAT_EXF = "ddMMMyyyy";
	
	public static String INSTRUCTIONS = "instructions";
	public static String DATA = "data";
	// GSE Headers Name
	public static String HEADER_URN = "URN";
	public static String HEADER_DESCRIPTIVE_ID = "Descriptive ID";
	public static String HEADER_DRAFT_IDS = "Draft IDs";
	public static String HEADER_SYLLABUS = "Syllabus";
	public static String HEADER_BATCH = "Batch";
	public static String HEADER_SKILL = "Skill";
	public static String HEADER_STATUS = "Status";
	public static String HEADER_DESCRIPTOR = "Descriptor";
	public static String HEADER_ATTRIBUTION = "Attribution";
	public static String HEADER_GSE = "GSE";
	public static String HEADER_CERF_LEVEL = "CEFR Level";
	public static String HEADER_COMMUNICATIVE_CATEGGORIES = "Communicative Categories";
	public static String HEADER_BUSINESS_SKILLS = "Business Skills";
	public static String HEADER_TOPIC_L1 = "Topic L1";
	public static String HEADER_YL_SIMPLIFIED = "YL Simplified";
	public static String HEADER_STRUCTURE = "Structure";
	public static String HEADER_GRAMMATICAL_CATEGORIES = "Grammatical Categories";
	public static String HEADER_EXAMPLE = "Example";
	public static String HEADER_VARIANT_TERMS = "Variant Terms";
	public static String HEADER_FUNCTION_NOTION = "Function/Notion";
	public static String HEADER_ANCHOR = "Anchor";
	public static String HEADER_SOURCE_DESCRIPTOR = "Source Descriptor";
	public static String HEADER_SOURCE = "Source";
	public static String HEADER_ESTIMATED_LEVEL = "Estimated Level";
	public static String HEADER_NOTES = "Notes";

	public static Integer ZERO = 0;
	public static Integer ONE = 1;
	public static Integer TWO = 2;
	public static Integer THREE = 3;
	public static Integer FOUR = 4;
	public static Integer FIVE = 5;
	public static Integer SIX = 6;
	public static Integer SEVEN = 7;
	public static Integer EIGHT = 8;
	public static Integer NINE = 9;
	public static Integer TEN = 10;
	public static Integer ELEVENTH = 11;
	public static Integer TWELEVE = 12;
	public static Integer THIRTEEN = 13;
	public static Integer FOURTEEN = 14;
	public static Integer FIFTEEN = 15;
	public static Integer SIXTEEN = 16;
	public static Integer SEVENTEEN = 17;
	public static Integer EIGHTEEN = 18;
	public static Integer NINETEEN = 19;
	public static Integer TWENTY = 20;
	public static Integer TWENTY_ONE = 21;
	public static Integer TWENTY_TWO = 22;
	public static Integer TWENTY_THREE = 23;
	public static Integer TWENTY_FOUR = 24;
	public static Integer ONE_THOUSAND_MILI_SEC = 1000;
	public static Integer TWO_THOUSAND_MILI_SEC = 2000;
	public static Integer THREE_THOUSAND_MILI_SEC = 3000;
	public static Integer SIX_HUNDRED = 600;
	public static String COMMA = ",";

	public static String NOT_NULL = "should not be null";
	public static String IS_EMPTY = "is empty";
	public static String GL = "GL";
	public static String PL = "PL";
	public static String YL = "YL";
	public static String GR = "GR";
	public static String VC = "VC";
	public static String NULL = "null";
	
	//TODO - NEED TO REMOVE
	public static String INGEST_SUCESS_MESSAGE = "//div[@class='ingestion']/div[1]/div[1]/div/div/label";
	public static String INGEST_FAILED_MESSAGE = "//div[@class='ingestion']/div/div/div/span/div/label"; 
	public static String INGESTION_STATUS = "//div[contains(@class,'ingestion-status')]/div[1]";
	public static String GSE_ACTION_LINK = "//div[@class='list-row row']/div[2]/div/span";
	public static String BUSINESS_XPATH = "//div[@class='ingestion']/div/div/div/div[1]/span/span";
	public static String LOADER = "//div[@id='lomtAppId']/div/span/div/div[2]";
	
	// Product TOC
	public static String PRODUCT_TOC_INGESTION = "ProductTOC ingestion";
	public static String LOMT_1039 = "LOMT-1039";
	public static String LOMT_1040 = "LOMT-1040";
	public static String LOMT_1041 = "LOMT-1041";
	public static String EMPTY_SPACE = " ";
	public static String TC_COUNT_PRODUCT_TOC = "Total 38 TCs";
	public static String PRODUCT_TOC_SCHOOL_TC_COUNT = "Total 33 TCs";
	public static String ENGLISH_TC_COUNT_PRODUCT_TOC = "Total 27 TCs";
	public static String HE_TC_COUNT_PRODUCT_TOC = "Total 28 TCs";
	public static String TC_COUNT_1040 = "";
	public static String TC_COUNT_LOMT_11 = "33";
	//public static String TC_COUNT = "Total 10 TCs";
	public static String HAS_PASSED = " has Passed";
	public static String LOG_OUT_FEATURES_NOT_AVAILABLE_BELOW_PPE_ENVIRONMENT = "Log out feature is not available in below PPE environment so that its De-scoped";
	public static String LOMT_LOGIN = "LOMTLogin";
	public static String LOMT_LOGIN_IN_PROCESS = "LOMT login in-process";
	public static String SUCESSFULLY_LOGIN_BASIC_BROWSER_LOGIN = "Successfully logged-in with basic browser role in LOMT application";
	public static String ENGLISH_GSE_INGESTION = "English GSE Ingestion";
	
	//Higher Education
	public static String EO_INGESTION = "Educational Objective Ingestion";
	public static String LOMT_457 = "LOMT-457";
	public static String TC_COUNT_HE_457 = "32";
	public static String LOMT_717 = "LOMT-717, 4 TCs";
	public static String LOMT_718 = "LOMT-718, 1 TCs";
	public static String LOMT_656 = "LOMT-656, 5 TCs";
	
	public static String DE_SCOPED = "De-scoped";
	public static String LEARNING_TITLE_NAME = "Learning Experience Name / Title";
	public static String DOMAIN = "Domain";
	public static String STATUS = "Status";
	public static String OBJECT_HIERARCHY_SET = "Objective Hierarchy Set";
	public static String HE_INGESTION_FILE_NAME = "HE Educational Objective Test-";
	public static String INVALID_FORMAT_FILE = "InvalidFormatIngestionFile";
	public static String VALIDATION_MISSED = "ValidationMissed";
	public static String INGEST = "Ingest";
	
	public static String EXF_TEXT = "Provide metadata for English External Framework";
	public static String EXF_DESCRIPTION_METADATA = "ExternalFramework_english";
	
	public static String DESCRIPTION = "Description";
	public static String SUBJECT = "Subject";
	public static String ISSUE_DATE = "Issue Date";
	public static String WAS_REVISION_OF = "Was Revision Of";
	public static String SET = "Set";
	public static String APPLICATION_LEVEL = "Application Level";
	public static String EXF_DESC_NAME = "ExternalFramework_Pearson !@#$%^&*()_Test-+{}|':><?_123Test_Test_Test_Test_Test_Test_Test_Test_Test_Test_Test_Test_Test_Test_Test_Test_Test_Test_Test_Test";
	public static String GSE = "GSE";
	public static String DATE = "2017";
	
	public static String TC_CASE_7_8_9_10_11 = "Mandatory and Non-mandatory, Program, Course are blank and Product Title has value";
	//public static String USE_CASE_2 = "Program, Course are blank and Product Title has value";
	public static String TC_12 = "Program, Course and Product Title has new value";
	public static String TC_13 = "Program has new value, Course has existing value and Product Title has new or old value";
	public static String TC_14 = "Program has existing value, Course has new value and Product Title has new or old value";
	public static String TC_15 = "Program has existing and Course has existing value, Product Title has new or old value";
	public static String TC_16 = "There should not be duplicate Product Title"; // not implement yet, re-ingestion 
	public static String TC_17 = "AlignmentCode column has value";
	public static String TC_18 = "CMT Intermediary Unique ID column has value";
	public static String TC_19 = "Sequence mismatch for Level for Hierarchy";
	public static String TC_20 = "Level for Hierarchy should be expanded for all level";
	public static String TC_21 = "Alignment Code has wrong value";
	public static String TC_22 = "CMT Intermediary Unique ID has wrong value";
	public static String TC_23 = "Ingestion without non-mandatory fields";
	public static String TC_25 = "Ingestion without mandatory fields";
	public static String TC_26 = "Product Title is blank";
	public static String TC_27 = "Level for Hierarchy is blank";
	public static String TC_28 = "Level Title is blank";
	public static String TC_29 = "Content Title is blank";
	public static String TC_30 = "Level for Hierarchy and Content Title are  blank";
	public static String TC_31 = "Level Title and Content Title are  blank";
	public static String TC_32 = "Content Title has correct value while AlignmentCode has wrong value";
	public static String TC_33 = "Level Title has correct value while CMT Intermediary Unique has wrong value";
	public static String TC_18_EO = "Educational goal urn";
	
	
	public static String TC_VALIDATION_CHECK_22_23 = "mandatory fields check";
	
	public static String ROW_NO_14 = "14";
	public static String ROW_NO_20 = "20";  
	public static String ROW_NO_22 = "22";
	public static String LEVEL_FOR_HIERARCHY_ERROR_MESSAGE = "Level for hierarchy is incorrect";
	public static String ALIGNMENT_CODE_WRONG_VAL_ERROR_MESSAGE = "This discipline is not valid : Science for MTH.3503";
	public static String CMT_WRONG_VALUE_ERROR_MESSAGE = "This discipline is not valid : Mathematics for urn:pearson:educationalgoal:0a33cfc7-6356-4b65-8d06-16e5a10e04b4";
	public static String PRODUCT_TITLE_CAN_NOT_BLANK = "Product Title value is blank in Row : 3";
	public static String LEVEL_HIERARCHY_CAN_NOT_BLANK = "Level for hierarchy cannot be blank";
	public static String LEVEL_TITLE_CAN_NOT_BLANK = "Level Tile cannot be blank";
	public static String CONTENT_TITLE_CAN_NOT_BLANK = "Content Title cannot be blank";
	public static String MANGE_INGESTION = "Manage Ingestion";
	
	public static final String SUCCESSFULLY_LOGGED_IN_IN_LOMT_APPLICATION = "Successfully logged-in in LOMT application";
	public static final String LOMT_LOGIN_IS_IN_PROGRESS = "LOMT login is in progress";
	public static final String LOMT_APPLICATION_IS_IN_PROCESS = "LOMT application is in-process";
	public static final String LOMT_APPLICAION = "LOMT Applicaion";
	
	// External Framework
	public static final String EXTERNAL_FRAMEWORK_INGESTION = "External Framework Ingestion";
	public static final String EXTERNAL_FRAMEWORK_RE_INGESTION = "External Framework Reingestion";
	public static final String EXTERNAL_FRAMEWORK_EXPORT = "External Framework Export";
	public static final String FOR_HE_ENGLISH_SCHOOL = " for HE, English and School(NALS & SG)";
	public static final String LOMT_1357 = "LOMT-1357";
	public static final String LOMT_1358 = "LOMT-1358";
	public static final String LOMT_1408 = "LOMT_1408";
	public static final String LOMT_1389 = "LOMT_1389";
	public static final String LOMT_1409 = "LOMT_1409";
	public static final String LOMT_11 = "LOMT-11";
	public static final String LOMT_1008 = "LOMT-1108";
	public static final String LOMT_1448 = "LOMT-1488";
	public static final String LOMT_1408_TC = " TC count is 54";
	public static final String TC_COUNT_ENGLISH_EXF = " TC count is 31";
	public static final String TC_COUNT_HE_EXF = " TC count is 30";
	public static final String TC_COUNT_SCHOOL_EXF = " TC count is 29";
	public static final String TC_COUNT_META_DATA = " TC count is 20";
	public static final String TC_COUNT_EXF_HE = "24";
	
	public static final String DROPDOWN_SIZE_NOT_ZERO = " dropdown size should not be zero";
	public static final String FRAMEWORK_PURPOSE = "Framework Purpose";
	public static final String DEFINED_BY = "Defined By";
	public static final String STATUS_VAL_ENGLISH = "Current-Draft";
	public static final String APPLICATION_LEVEL_ENGLISH = "Adult-General";
	public static final String PURPOSE_ENGLISH = "Educational";
	public static final String DEFINED_BY_ENGLISH = "Lumina Foundation";
	
	public static final String EXF_ALL_FIELDS = "All mandatory and non-mandatory fields";
	public static final String EXF_NON_MANDATORY_FIELDS = "non-mandatory fields";
	public static final String EXF_WRONG_FORMAT_FILE = "Wrong format file";
	public static final String EXF_WITHOUT_MANDATORY_FIELDS = "Without mandatory fields";
	
	//EXF validation error message
	public static final String EXTERNAL_FRAMEWORK_TITLE_ERROR_MESSAGE = "The External Framework label is mandatory at row 1";
	public static final String OFFICIAL_STANDARD_CODE_ERROR_MESSAGE_3 = "Official Standard Code column is mandatory in Row : 3";
	public static final String OFFICIAL_STANDARD_CODE_ERROR_MESSAGE_15 = "Official Standard Code column is mandatory in Row : 15";
	
	public static final String LEVEL_COLUMN_1_ERROR_MESSAGE = "Level column is mandatory in Row : 3";
	public static final String LEVEL_COLUMN_1_ERROR_MESSAGE_5 = "Level column is mandatory in Row : 5";
	public static final String LEVEL_COLUMN_1_ERROR_MESSAGE_15 = "Level column is mandatory in Row : 15";
	
	public static final String GENERIC_ERROR_MESSAGE = "The format of the file selected for upload is incorrect - please contact the support team for details of the correct file format";
	
	public static final String WRONG_GRADE_VALUE = "Wrong grade value";
	public static final String LEVELS_AT_SAME_ROW = "Levels at same row";
	public static final String COMMON_TCS_INGESTION = "Common TCs for Ingestion";
	public static final String NTH_LEVEL = "nth level ingestion";
	public static final String REINGESTION_WRONG_GRADE_SAME_LEVEL = "Re-ingestion : wrong grade and Level at same row";
	
	public static final String MULTIPLE_LEVEL_ROW_3 = "Multiple level text provided at row 3";
	public static final String MULTIPLE_LEVEL_ROW_16 = "Multiple level text provided at row 16";
	public static final String GRADE_LOW_ROW_4 = "Grade Low value is invalid in Row : 4";
	public static final String GRADE_LOW_ROW_5 = "Grade High value is invalid in Row : 5";
	
	public static final String GRADE_LOW_3_ERROR_MESSAGE = "Grade Low value is invalid in Row : 3";
	public static final String GRADE_HIGH_3_ERROR_MESSAGE = "Grade High value is invalid in Row : 3";
	public static final String GRADE_LOW_9_ERROR_MESSAGE = "Grade Low value is invalid in Row : 9";
	public static final String GRADE_HIGH_12_ERROR_MESSAGE = "Grade High value is invalid in Row : 12";
	public static final String GRADE_LOW_HIGH_INVALID = "Grade Low-Grade High value is invalid in Row : 13";
	public static final String LEVEL_ROW_16  = "The level is not correct at row 16";
	public static final String LEVEL_ROW_17  = "The level is not correct at row 17";
	public static final String GRADE_LOW_HIGH_ERROR_MESSAGE = "Grade Low-Grade High value is invalid in Row : 5";
	
	public static final String LEVEL_3_INCORRECT_ERROR_MESSAGE = "The level is not correct at row 3";
	public static final String LEVEL_15_INCORRECT_ERROR_MESSAGE =  "The level is not correct at row 15";
	
	//EXF ingested file name
	public static final String EXF_INGESTION_FILE_NAME_1 = "External Frameworks-Test_Z007";	
	public static final String INGESTION_FILE_10TH_LEVEL = "External_Frameworks_10Level-Z007";
	public static final String INGESTION_NON_MAND = "External Frameworks_non_mandatory_fields_without_metadata-Test-Z007";
	
	public static final String INGESTION_FAILED_10TH_LEVEL = "External Framework ingestion is failing with 10th level values";
	public static final String INGESTION_SUCCESSFUL_NTH_LEVEL = "External Framework ingestion is successful with 10th level values";
	
	public static final String META_DATA = "Metadata for";
	public static final String CUSTOME_EXF_SCHOOL = "Custom (external framework) ingest option for School Global or NALS ";
	
	public static final String ANTHROPOLOGY_SUBJECT_METADATA_HE = "Anthropology";
	public static final String STATUS_METADATA_HE = "Draft";
	public static final String APPLICATION_LEVEL__METADATA_HE = "Course";
	public static final String PURPOSE_METADATA_HE = "Educational";
	public static final String DEFINED_BY_METADATA_HE = "Lumina Foundation";
	public static final String DEFINED_BY_METADATA_School = "Edexcel";
	public static final String SCHOOL_SUBJECT = "Business";
	
	//Product toc 
	public static final String SEQUENCE_MISMATCH_ERROR_MESSAGE = "Level for hierarchy is incorrect";
	public static final String WRONG_ALIGNMENT_CODE_ERROR_MESSAGEA_1 = "This discipline is not valid : Engineering for ELA.LA.82";
	public static final String WRONG_ALIGNMENT_CODE_ERROR_MESSAGEA_2 = "This discipline is not valid : Economics for ELA.LA.123";
	public static final String WRONG_CMT_URN_1 = "This discipline is not valid : Engineering for";
	public static final String WRONG_CMT_URN_2 = "This discipline is not valid : Economics for test";
	public static final String MANDATORY_FIELDS_ERROR_MESSAGE_1 = "Product Title value is blank in Row : 3";
	public static final String MANDATORY_FIELDS_ERROR_MESSAGE_2 = "Level Tile cannot be blank";
	public static final String MANDATORY_FIELDS_ERROR_MESSAGE_3 = "Level for hierarchy cannot be blank";
	public static final String MANDATORY_FIELDS_ERROR_MESSAGE_4 = "Level for hierarchy is incorrect";
	
	public static final String LEVEL_HIERARCHY_ERROR_MESSAGE_1 = "Level for hierarchy cannot be blank";
	public static final String LEVEL_HIERARCHY_ERROR_MESSAGE_2 = "Level for hierarchy is incorrect";
	
	//ExternalFramework Headers
	public static final String UNIQUE_ID = "Unique id";
	public static final String GRADE_LOW = "Grade Low";
	public static final String GRADE_HIGH = "Grade High";
	public static final String GRADE_TITLE = "Grade Title";
	public static final String OFFICAL_STAN_CODE = "Official Standard Code";
	public static final String LEVEL_1 = "Level 1";
	public static final String LEVEL_2 = "Level 2";
	public static final String LEVEL_3 = "Level 3";
	public static final String LEVEL_4 = "Level 4";
	public static final String LEVEL_5 = "Level 5";
	public static final String LEVEL_6 = "Level 6";
	public static final String LEVEL_7 = "Level 7";
	public static final String LEVEL_8 = "Level 8";
	public static final String LEVEL_9 = "Level 9";
	public static final String LEVEL_10 = "Level 10";
	
	public static final String LOWEST_LEVEL = "Lowest level";
	public static final String TAGS = "Tags";
	
	//validation messages for Product TOC
	
	public static final String PRODUCT_TITLE_BLANK = "Product Title value is blank in Row";
	public static final String LEVEL_FOR_HIERARCHY_BLANK = "Level for hierarchy cannot be blank";
	public static final String LEVEL_FOR_HIERARCHY_INCORRECT = "Level for hierarchy is incorrect";
	public static final String LEVEL_TITLE_BLANK = "Level Tile cannot be blank";
	public static final String VALIDATION_TOC = "Validation TOC";
	
	/*public static void main(String[] args) {
		String s1 = "Language functions>Explaining|Language functions>Negotiating, complaining and dealing with problems";
		String s2 = "Language functions>Negotiating, complaining and dealing with problems|Language functions>Explaining";
		
		//System.out.println(s1.replaceAll(" ", "") + " : "+s2.replaceAll(" ", ""));
		
		if (s1.contains(s2)) {
			System.out.println("dfdf");
		}
			
	}*/
}
