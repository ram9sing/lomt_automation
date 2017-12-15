package lomt.pearson.constant;

import java.util.LinkedHashMap;
import java.util.Map;

public class HEConstant {
	
	//domain values
	public static String A = "Anatomy & Physiology";
	public static String BUSINESS_STATISTICES = "Business Statistics";
	public static String C = "";
	public static String D = "";
	public static String E = "";
	
	//status values
	public static String APPROVED = "Approved";
	public static String AWAITING_APPROVAL = "Awaiting approval";
	public static String DELETED = "Deleted";
	public static String DRAFT = "Draft";
	public static String PARTIALLY_APPROVED = "Partially approved";
	public static String REJECTED = "Rejected";
	
	//Error Messages
	public static String LANGUAGE_BLANK = "Language value is blank in Row : 6";
	public static String QUESTION_BLANK = "Question should not be blank in row: 7";
	public static String ASSERTION8_VAL = "Assertion8 should not be blank in row: 7";
	public static String ASSERTION1_VAL = "Assertion1 should not be blank in row: 7";
	public static String ASSERTION2_VAL = "Assertion2 should not be blank in row: 7";
	public static String DATE_FORMAT = "Creation Date column should be in proper format in row: 8";
	public static String TOC_NUM_HEADER = "TOC Numbering column is mandatory in Row : 12";
	public static String NEW_EO_AND_NEW_LO_SAME_ROW =  "NEW Enabling Objective #NEW Enabling Objectives Both NEW Learning Objective # and NEW Enabling Objective # cannot have value in the same row. Row # is : 15";
	public static String PROFICIENCY_COL_VAL = "Proficiency Column value is mismatched from the available values in Row : 17";
	public static String DOMAIN_COL_VAL = "Domain Column value is mismatched from the available values in Row : 17";
	public static String BLOOMS_CONG_P_DIMENSIONS_TEXT = "Blooms Cognitive Process Dimensions Column value is mismatched from the available values in Row : 17";
	public static String BLOOMS_KNOWD_DIMENSIONS_TEXT = "Blooms Knowledge Dimensions Column value is mismatched from the available values in Row : 17";
	public static String CONCEPT_SUBJECT_MISCONCEPTION_Y =  "Given \"Concept Subject to Misconception\" is \"Y\", at least One Column value from Misconception Descriptive Statement 1 column to Misconception Assertion(s) 3 should have values in row: 18";
	public static String CONCEPT_SUB_MISCONCEPTION_N = "Given \"Concept Subject to Misconception\" is \"N\", the row values for \"Misconception Descriptive Statement 1\", \"Misconception Feedback 1\", \"Misconception Assertion(s) 1\", \"Misconception Descriptive Statement 2\", \"Misconception Feedback 2\", \"Misconception Assertion(s) 2\", \"Misconception Descriptive Statement 3\", \"Misconception Feedback 3\", and \"Misconception Assertion(s) 3\" should be blane in row 15";
	
	
	//Test data to verify ingested data
	
	public static Map<String, String> getLOAndEOData() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("LO 8.1", "Analyze physical development in middle adulthood.");
		map.put("LO 8.2", "Evaluate the changes in sexuality during middle adulthood.");
		map.put("LO 8.3", "Assess the influences on health in middle adulthood.");
		map.put("LO 8.4", "Evaluate cognitive development in middle adulthood.");
		map.put("LO 8.5", "Evaluate memory development in middle adulthood.");
		
		map.put("EO 8.1.3", "Explain sensory function during middle adulthood.");
		map.put("EO 8.2.3", "Describe the dilemma of hormone replacement therapy for women.");
		map.put("EO 8.3.2", "Explain the impact of stress on health.");
		map.put("EO 8.4.1", "Describe the changing state of intelligence in adulthood.");
		map.put("EO 8.5.3", "Explain the influence of schemas in memory processes.");
		
		return map;
	}
	
	/*public static void main(String [] args) {
		String test = "Items hidden\n"+
					"(EO 8.1.1)Explain physical changes in middle adulthood.";
		String value = "Explain physical changes in middle adulthood.";
		
		int i = test.indexOf(")");
		System.out.println(test.substring(i+1, test.length()));
		
		//System.out.println(test.contains(value));
	}*/
	
	public static String HE_INGESTION_XLS_FILE_PATH = System.getProperty("user.dir")+ "\\src\\main\\java\\lomt\\pearson\\fileupload\\highereducation\\educationalgoal\\LEOH_Template_ForDataVerification.xlsx";
	public static String HE_DESTINATION_FILE_PATH = System.getProperty("user.dir")+ "\\src\\main\\java\\lomt\\pearson\\fileupload\\highereducation\\educationalgoal\\reingestion\\";
	
	public static String HE_REINGESTION_TEMPLATE = "HE_EO_Reingestion.xlsx";
	public static String HE_EO_EXPORT = "HE Educational Objective Export, LOMT-613, 3 TCs";
	
	//Reingestion Test data
	public static String LE_TITLE_RE = "HE_DOMAN_QA_PAIRS_REINGESTION_TEST";// FILTER WITH SPECIAL CHARACTER IS OPEN BUG
	public static String DOMAIN_RE = "Business";
	public static String STATUS_RE = "Approved";
	
	
	// Meta data test values
	
	//use case 1 : LO 8.1
	public static String LO_1_DESC = "Analyze physical development and enhance the performance LO.";
	public static String EO_1_DESC = "Explain physical changes and enhance the performance EO.";
	public static String LO_EO_PREREQUISITES =  "EO 8.1.2";
	public static String IDENTIFIED_AS_A_MOST_DIFFICULT_CONCEPT_Y_N = "Y";
	public static String MISCONCEPTION_DESCRIPTIVE_STATEMENT_1 = "Reaction time peaks in older adulthood, misconception desc statement 1.";
	public static String DOMAIN = "Statistics";
	public static String BLOOMS_COGNITIVE_PROCESS_DIMENSIONS = "Analyze";
	public static String BLOOMS_KNOWLEDGE_DIMENSIONS = "Conceptual";
	public static String WEBBS_DEPTH_OF_KNOWLEDGE_COGNITIVE_COMPLEXITY_DIMENSION = "Extended thinking";
	public static String PROFICIENCY = "Intermediate";
	
	// QA and Pairs Test data
	public static String QUESTION = "What physical benchmarks of change occur in middle adulthood?";
	public static String ANSWER = "The physical benchmarks of change are height, weight, and strength. Most people reach their maximum height in their 20's and remain close to that height until around age 55.";
	public static String ASSERTION1 = "Many of the noticeable changes occur in physical attributes, response time, height, weight, and strength.";
	public static String HINT1 = "What physical changes most are often noticed?";
	
	//Add new LO/EO data
	public static String NEW_LO_NUM = "LO 8.6";
	public static String NEW_LO_NUM_DESC = "New LO added Educational Objective";
	
	public static String NEW_EO_NUM_1 = "EO 8.6.1";
	public static String NEW_EO_NUM_1_DESC = "New EO 8.6.1 added Educational Objective";
	
	public static String NEW_EO_NUM_2 = "EO 8.6.2";
	public static String NEW_EO_NUM_2_DESC = "New EO 8.6.2 added Educational Objective";
	
	public static String NEW_EO_NUM_3 = "EO 8.6.3";	
	public static String NEW_EO_NUM_3_DESC = "New EO 8.6.3 added Educational Objective";
	
	public static String FLAG_Y = "Y";
	public static String FLAG_N = "N";
	
	public static String MISCONCEPTION_DES_STMT_DESC_1 = "newly added misconception stmt 1";
	
	public static String PRE_NEW_LO = "LO 8.5";
	public static String PRE_NEW_EO_1 = "EO 8.6.1";
	public static String PRE_NEW_EO_2 = "EO 8.6.2";
	
	public static String LO_MIS = "LO MISCONCEPTION 1 ADDED";
	
	public static String NEW_QUESTION_ADDED = "New question added test test test test";
	public static String NEW_ANSWER_ADDED = "New answer added test test test test";
	public static String NEW_ASSERTION_ADDED = "New assertion added test test test test";
	public static String NEW_HINT_ADDED = "New hint added test test test test";
	
	
}
