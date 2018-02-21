package lomt.pearson.constant;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SchoolConstant {
	
	public static String SCHOOL_CURRICULUM_FILE_PATH = "src/main/java/lomt/pearson/fileupload/nals_school_global/curriculumstandard/Curriculum_IngestionTemplate_small_FileUpload.exe";
	public static String SCHOOL_CURRICULUM_WRONG_FILE_PATH = "src/main/java/lomt/pearson/fileupload/nals_school_global/curriculumstandard/Curriculum_Wrong_IngestionTemplate_large_FileUpload.exe";
	
	public static String SCHOOL_CURRICULUM_FILE_PATH_1 = "src/main/java/lomt/pearson/fileupload/nals_school_global/curriculumstandard/re-ingestion/Curriculum_ReingestionTemplate_1_FileUpload.exe";
	public static String SCHOOL_CURRICULUM_FILE_PATH_2 = "src/main/java/lomt/pearson/fileupload/nals_school_global/curriculumstandard/re-ingestion/Curriculum_ReingestionTemplate_2_FileUpload.exe";
	public static String SCHOOL_CURRICULUM_FILE_PATH_3 = "src/main/java/lomt/pearson/fileupload/nals_school_global/curriculumstandard/re-ingestion/Curriculum_ReingestionTemplate_3_FileUpload.exe";
	public static String SCHOOL_CURRICULUM_FILE_PATH_4 = "src/main/java/lomt/pearson/fileupload/nals_school_global/curriculumstandard/re-ingestion/Curriculum_ReingestionTemplate_4_FileUpload.exe";
	
	public static String INT_DESTINATION_FILE_PATH = System.getProperty("user.dir")+ "\\src\\main\\java\\lomt\\pearson\\fileupload\\intermediary\\reingestion\\";
	public static String INT_REINGESTION_TEMPLATE = "Intermediary_Reingestion_Template.xlsx";
	
	public static String CURRICULUM_REINGESTION = "School Global Curriculum Standard Re-ingestion, LOMT-947, Total TCs is 10";
	
	public static String DATA_1 = "Key Ideas and Details";
	public static String DATA_2 = "Key Ideas and Details updated-4.";
	public static String DATA_3 = "Integration of Knowledge and Ideas";
	public static String DATA_4 = "Range of Reading and Level of Text Complexity";
	public static String DATA_5 = "Read and comprehend complex literary and informational texts independently and proficiently.";
	//public static String DATA_6 = "Text Types and Purposes<EXTENDEDTOPIC>These broad types of writing include many subgenres.";
	public static String DATA_7 = "Vocabulary Acquisition and Use";
	
	
	public static String SCHOOL_CURRICULUM_INGESTION_LOMT_09 = "School Curriculum Ingestion";
	
	public static String SCHOOL_CURRICULUM_EXPORT_LOMT_612 = "School Curriculum Export, LOMT-612, Total TCs is 30";
	
	public static String CS_SOURCE_URL = "https://www.google.com/";	
	public static String CS_INFO_URL = "https://www.amazon.com/";
	
	public static String CS_SOURCE_URL_REINGESTION = "https://www.snapdeal.com/";	
	public static String CS_INFO_URL_REINGESTION = "https://www.flipkart.com/";
	
	public static String UNITIED_STATES = "United States";
	public static String LOMT_458 = "LOMT-458";
	public static String LOMT_338 = "LOMT-338";
	public static String LOMT_1548 = "LOMT-1548";
	
	public static String CS_METADATA = "Curriculum Standard METADATA";
	public static String URN = "URN";
	public static String TITLE = "TITLE";
	public static String DESCRIPTION = "DESCRIPTION";
	public static String DEFINED_BY = "Defined By";
	public static String SUBJECT = "SUBJECT";
	public static String COUNTRY = "COUNTRY";
	public static String ISSUE_DATE = "ISSUE DATE";
	public static String SETS = "SETS";
	public static String STATUS = "STATUS";
	public static String FRAMEWORK_LEVEL = "FRAMEWORK LEVEL";
	public static String LAST_UPDATED = "LAST UPDATED";
	public static String INGESTION_TYPE = "INGESTION TYPE";
	public static String SOURCE_URL = "SOURCE URL";
	public static String CURRICULUM_INFO_URL = "CURRICULUM INFO URL";
	
	public static List<String> getCurrilumTestData() {
		List<String> list = new LinkedList<String>();
		list.add(DATA_1);
		list.add(DATA_2);
		list.add(DATA_3);
		list.add(DATA_4);
		list.add(DATA_5);
		//list.add(DATA_6);
		list.add(DATA_7);
		
		return list;
	}
	
	//Export Constant
	
	public static String UNIQUE_ID = "Unique id";
	public static String GRADE_LOW = "Grade Low";
	public static String GRADE_HIGH = "Grade High";
	public static String GRADE_TITLE = "Grade Title";
	public static String OFFICIAL_STANDARD_CODE = "Official Standard Code";
	public static String LEVEL_1 = "Level 1";
	public static String LEVEL_2 = "Level 2";
	public static String LEVEL_3 = "Level 3";
	public static String LEVEL_4 = "Level 4";
	public static String LEVEL_5 = "Level 5";
	public static String LEVEL_6 = "Level 6";
	public static String LOWEST_LEVEL = "Lowest Level";
	public static String TAGS = "Tags";
	
	//Grade K-12
	public static String LEVEL1 = "Reading";
	public static String LEVEL2 = "Key Ideas and Details";
	public static String LEVEL3 = "Read closely to determine what the text says explicitly and to make logical inferences from it; cite specific textual evidence when writing or speaking to support conclusions drawn from the text.";
	public static String LEVEL4 = "Key Ideas and Details updated-1.";
	public static String LEVEL5 = "Key Ideas and Details updated-2.";
	public static String LEVEL6 = "Key Ideas and Details updated-3.";
	public static String LOWEST_LEVEL_F = "Key Ideas and Details updated-4.";
	
	//Re-ingestion
	public static String C_USECASE_1 = "update goalframework details";
	public static String C_USECASE_2 = "update fields";
	public static String C_USECASE_3 = "add new node";
	public static String C_USECASE_4 = "delete node";
	
	//updated Grade value
	
	//use case 2
	public static String GRADE_1 = "College- and Career-Readiness Anchor Standards Enhanced to 12";
	public static String GRADE_2 = "New PK to 5 grade created";
	
	public static String DESC_1 = "Reading UPDATED 1.0";
	public static String DESC_2 = "Key Ideas and Details UPDATED 1.0";
	public static String DESC_3 = "Read closely to determine what the text says explicitly and to make logical inferences from it; cite specific textual evidence when writing or speaking to support conclusions drawn from the text UPDATED 1.2.";
	public static String DESC_4 = "Key Ideas and Details UPDATED 1.3.";
	public static String DESC_5 = "Key Ideas and Details UPDATED 1.4.";
	public static String DESC_6 = "Key Ideas and Details UPDATED 1.5.";
	public static String DESC_7 = "Key Ideas and Details UPDATED 1.6.";
	public static String STATE_NUM_DESC = "QS 007";
	
	//Re-ingestion
	public static List<String> getCurrilumDescUpdatedData() {
		List<String> reingestionList1 = new LinkedList<String>();
		reingestionList1.add(DESC_1);
		reingestionList1.add(DESC_2);
		reingestionList1.add(DESC_3);
		reingestionList1.add(DESC_4);
		reingestionList1.add(DESC_5);
		reingestionList1.add(DESC_6);
		reingestionList1.add(DESC_7);
		//reingestionList1.add(STATE_NUM_DESC);
		
		return reingestionList1;
	}
	
	//New node added
	public static String NEW_NODE_DESC_1 = "Parent Topic added";
	public static String NEW_NODE_DESC_2 = "Child Topic-1 added";
	public static String NEW_NODE_DESC_3 = "New Child Topic added";
	public static String NEW_NODE_DESC_4 = "New Topic added";
	
	public static String STATE_NUM = "QS 007";
	
	public static List<String> getCurrilumNewAddedNode() {
		List<String> reingestionList1 = new LinkedList<String>();
		reingestionList1.add(NEW_NODE_DESC_1);
		reingestionList1.add(NEW_NODE_DESC_2);
		reingestionList1.add(NEW_NODE_DESC_3);
		reingestionList1.add(NEW_NODE_DESC_4);
		//reingestionList1.add(STATE_NUM);
		
		return reingestionList1;
	}
	
	public static String P_NODE_DES_1 = "New node added-1.";
	public static String STATE_NUM_1 = "L.1.6.1";
	
	public static String C_NODE_DES_2 = "New node added-2.";
	public static String STATE_NUM_2 = "L.1.6.1.2";
	
	// DELETED NODE
	public static String DELETED_NODE = "Child Topic-2 added";
	
	public static List<String> getIntermediaryDisciplineData() {
		List<String> IntermediaryDisciplineList = new LinkedList<String>();
		IntermediaryDisciplineList.add("Accounting");
		IntermediaryDisciplineList.add("Art and Design");
		IntermediaryDisciplineList.add("Business");
		IntermediaryDisciplineList.add("Business Studies");
		IntermediaryDisciplineList.add("Citizenship");
		IntermediaryDisciplineList.add("Citizenship and Personal, Social and Health Education");
		IntermediaryDisciplineList.add("Classical Studies");
		IntermediaryDisciplineList.add("Design and Technology");
		IntermediaryDisciplineList.add("Economics");
		IntermediaryDisciplineList.add("Engineering");
		IntermediaryDisciplineList.add("Environment and Ecology");
		IntermediaryDisciplineList.add("Foundation");
		IntermediaryDisciplineList.add("Geography");
		IntermediaryDisciplineList.add("Health and Social Care");
		IntermediaryDisciplineList.add("History");
		IntermediaryDisciplineList.add("ICT");
		IntermediaryDisciplineList.add("Information and Communication Technology");
		IntermediaryDisciplineList.add("Language");
		IntermediaryDisciplineList.add("Law");
		IntermediaryDisciplineList.add("Leisure and Tourism");		
		IntermediaryDisciplineList.add("Literacy");
		IntermediaryDisciplineList.add("Manufacturing");
		IntermediaryDisciplineList.add("Mathematics");
		IntermediaryDisciplineList.add("Music");
		IntermediaryDisciplineList.add("Native language");
		IntermediaryDisciplineList.add("Personal Wellbeing");
		IntermediaryDisciplineList.add("Physical Education");
		IntermediaryDisciplineList.add("Psychology");
		IntermediaryDisciplineList.add("Religious Education");
		IntermediaryDisciplineList.add("Science");
		IntermediaryDisciplineList.add("Science and technology");
		IntermediaryDisciplineList.add("Social Science");
		IntermediaryDisciplineList.add("Social studies");
		
		return IntermediaryDisciplineList;
	}
	
	public static Map<String, String> getIntermediaryDisciplineDataInMap() {
		
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("1", "Accounting");
		map.put("2", "Art and Design");	// 600 pixel
		map.put("3", "Business");
		map.put("4", "Business Studies");
		map.put("5", "Citizenship");
		map.put("6", "Citizenship and Personal, Social and Health Education");
		map.put("7", "Classical Studies");
		map.put("8", "Design and Technology");
		
		map.put("9", "Economics"); //1000 pixel
		map.put("10", "Engineering");
		map.put("11", "Environment and Ecology");
		map.put("12", "Foundation");
		map.put("13", "Geography");
		map.put("14", "Health and Social Care");
		map.put("15", "History");
		map.put("16", "ICT");
		
		map.put("17", "Information and Communication Technology");
		map.put("18", "Language");
		map.put("19", "Law");
		map.put("20", "Leisure and Tourism");
		map.put("21", "Literacy");
		map.put("22", "Manufacturing");	//1500 pixel
		map.put("23", "Mathematics");
		map.put("24", "Music");
		
		map.put("25", "Native language");
		map.put("26", "Personal Wellbeing");
		map.put("27", "Physical Education");
		map.put("28", "Psychology");
		map.put("29", "Religious Education");
		map.put("30", "Science");
		map.put("31", "Science and technology");
		map.put("32", "Social Science");
		map.put("33", "Social studies");
		
		return map;
	}
	
	public static Map<String, String> getIntermediaryDisciplineDataXPATH() {
		
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("1", "//div[@class='ingestion']/div/div/div/div[1]/input");
		map.put("2", "//div[@class='ingestion']/div/div/div/div[2]/input");
		map.put("3", "//div[@class='ingestion']/div/div/div/div[3]/input");
		map.put("4", "//div[@class='ingestion']/div/div/div/div[4]/input");
		map.put("5", "//div[@class='ingestion']/div/div/div/div[5]/input");
		map.put("6", "//div[@class='ingestion']/div/div/div/div[6]/input");
		map.put("7", "//div[@class='ingestion']/div/div/div/div[7]/input");
		map.put("8", "//div[@class='ingestion']/div/div/div/div[8]/input");
		
		map.put("9", "//div[@class='ingestion']/div/div/div/div[9]/input");
		map.put("10", "//div[@class='ingestion']/div/div/div/div[10]/input");
		map.put("11", "//div[@class='ingestion']/div/div/div/div[11]/input");
		map.put("12", "//div[@class='ingestion']/div/div/div/div[12]/input");
		map.put("13", "//div[@class='ingestion']/div/div/div/div[13]/input");
		map.put("14", "//div[@class='ingestion']/div/div/div/div[14]/input");
		map.put("15", "//div[@class='ingestion']/div/div/div/div[15]/input");
		map.put("16", "//div[@class='ingestion']/div/div/div/div[16]/input");
		
		map.put("17", "//div[@class='ingestion']/div/div/div/div[17]/input");
		map.put("18", "//div[@class='ingestion']/div/div/div/div[18]/input");
		map.put("19", "//div[@class='ingestion']/div/div/div/div[19]/input");
		map.put("20", "//div[@class='ingestion']/div/div/div/div[20]/input");
		map.put("21", "//div[@class='ingestion']/div/div/div/div[21]/input");
		map.put("22", "//div[@class='ingestion']/div/div/div/div[22]/input");
		map.put("23", "//div[@class='ingestion']/div/div/div/div[23]/input");
		map.put("24", "//div[@class='ingestion']/div/div/div/div[24]/input");
		
		map.put("25", "//div[@class='ingestion']/div/div/div/div[25]/input");
		map.put("26", "//div[@class='ingestion']/div/div/div/div[26]/input");
		map.put("27", "//div[@class='ingestion']/div/div/div/div[27]/input");
		map.put("28", "//div[@class='ingestion']/div/div/div/div[28]/input");
		map.put("29", "//div[@class='ingestion']/div/div/div/div[29]/input");
		map.put("30", "//div[@class='ingestion']/div/div/div/div[30]/input");
		map.put("31", "//div[@class='ingestion']/div/div/div/div[31]/input");
		map.put("32", "//div[@class='ingestion']/div/div/div/div[32]/input");
		map.put("33", "//div[@class='ingestion']/div/div/div/div[33]/input");
		
		return map;
	}
	
	//Intermediary Test data
	
	public static List<String> getIntTestData() {
		List<String> intTestDataList = new LinkedList<String>();
		intTestDataList.add("Cognitive process exercised is analyzing.");
		intTestDataList.add("ELA.CP.2");
		intTestDataList.add("Alphabetize a series of words to the first letter to find them in a dictionary or glossary.");
		intTestDataList.add("ELA.LA.34");
		
		intTestDataList.add("Ask questions with appropriate subject-verb inversion.");
		intTestDataList.add("ELA.LA.58");
		intTestDataList.add("Differentiate among multiple meanings of words using sentence structure.");
		intTestDataList.add("ELA.LA.144");
		
		return intTestDataList;
	}
	
	public static String LOMT_10_TC = "School Global Intermediary ingestion, LOMT-10, Total 12 TCs";
	public static String LOMT_615_TC = "School Global Intermediary Export, LOMT-615, Total 12 TCs";
	
	//export test data
	public static String INT_STATEMENT_HEADING = "Intermediary Statement";
	public static String TAG_HEADING = "Tag (STFk, P, CU)";
	public static String CATEGORY_HEADING = "Category";
	public static String INT_STMT_CODE_HEADING = "Intermediary Statement Code";
	public static String INT_STMT_ID_HEADING= "Intermediary Statement Id";
	public static String INTERMEDIARY = "Intermediary";
	
	//reingestion test data
	
	//NEW ROW ADDED TEST DATA
	public static String INTERMEDIARY_STMT_NEW_1 = "New Intermediary Statement added-1";
	public static String TAG_NEW = "CU";
	public static String CATEGORY_NEW = "";
	public static String IS_CODE_NEW = "";
	
	
	public static String INTERMEDIARY_STMT_NEW_2 = "New Intermediary Statement added-2!@#$%^&*()_+{}|':<>?Test12345 test test test test test test test test test test test test test test test test test test test test test test test test test test test added";
	public static String TAG_NEW_2 = "CU";
	public static String CATEGORY_NEW_2 = "Writing";
	public static String IS_CODE_NEW_2 = "ELA.CP.1.008";
	
	//udapted existing
	public static String INTERMEDIARY_STMT_UPDATE = "Existing Intermediary Statement Updated";
	public static String TAG_UPDATE = "CU";
	public static String CATEGORY_UPDATE = "Writing";
	public static String IS_CODE_UPDATE = "ELA.CP.1.009";
	
	public static List<String> getIntermediaryUpdatedData() {
		List<String> list = new LinkedList<String>();
		list.add(INTERMEDIARY_STMT_UPDATE);
		list.add(INTERMEDIARY_STMT_NEW_1);
		return list;
	}
	
	public static List<String> getIntermediaryDeletedData() {
		List<String> list = new LinkedList<String>();
		list.add(INTERMEDIARY_STMT_NEW_2);
		return list;
	}

}
