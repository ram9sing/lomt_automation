package lomt.pearson.constant;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ReportsConstant {
	
	public static String INDIRECT_TEXT = "Indirect";
	public static String FORWARD_INDIRECT_INT_REPORT_FILE_NAME = "Forward (Indirect) Intermediary Report";
	public static String PRODUCT_TOC_INT_REPORT_FILE_NAME = "Automation Report";
	public static String PRODUCT_INT_TEXT = "Product (ToC) Intermediary Report";
	public static String REVERSE_SHARED_INT_TEXT = "Reverse Shared Intermediary Report";
	public static String REVERSE_DIRECT_TEXT = "Reverse Direct Report";
	public static String REVERSE_TOC_STANDARD_VIA_INT_TEXT = "Reverse ToC to Standard via intermediary report";
	public static String SUMMARY_TEXT = "Summary Report";
	public static String INGESTED_PRODUCT = "Automation_Report_Verification_Product";
	public static String INGESTED_INTERMEDIARY = "Psychology";
	public static String INGESTED_STANDARD_YEAR = "2564";
	
	//Forward Indirect Intermediary Report
	public static String TITLE = "Title";
	public static String DATE_TIME_GENERATION = "Date of generation";
	public static String USER = "User";
	public static String STANDARD = "Standard";
	public static String INTERMEDIARY = "Intermediary";
	public static String COUNTRY = "Country";
	public static String GRADE = "Grade";
	public static String DISCIPLINE = "Discipline";
	public static String STANDARDS_STRANDS = "Standards' Strands"; 
	public static String STANDARDS_TOPICS = "Standards' Topics";
	public static String STANDARDS_NUMBER = "Standard Number";
	public static String PARENT_CODE = "Parent Code";
	public static String AB_GUIDE = "AB GUID";
	public static String SYSTEM_UNIQUE_ID = "System Unique ID";
	public static String INTERMEDIARY_URN = "Intermediary URN";
	public static String SUBJECT = "Subject";
	public static String CODE = "Code";
	public static String DESCRIPTION = "Description";
	public static String SPANISH_DESCRIPTION = "Spanish Description";
	
	//Product ToC Intermediary Report
	public static String ALIGNMENTS = "Alignments";
	public static String CENTRAL = "Central";
	public static String PERIPHERAL = "Peripheral";
	public static String CENTRAL_PERIPHERAL = "Central,Peripheral";
	public static String CONTENT = "Content";
	public static String PROGRAM = "Program";
	public static String COURSE = "Course";
	public static String PRODUCT = "Product";
	public static String GEOGRAPHIC_AREA_OR__COUNTRY = "Geographic Area or Country";
	public static String GEOGRAPHIC_AREA_OR_COUNTRY = "Geographic Area or Country";
	public static String STATE_OR_REGION = "State or Region";
	public static String START_GRADE = "Start Grade";
	public static String END_GRADE = "End Grade";
	public static String ISBN10 = "ISBN 10";
	public static String ISBN13 = "ISBN 13";
	public static String TYPE = "Type";
	public static String URN = "URN";
	public static String ALFRESCO_OBJECT_ID = "Alfresco Object ID";
	public static String COMPONENT_TOC = "Component ToC";
	public static String START_PAGE = "Page Start";
	public static String END_PAGE = "Page End";
	public static String PERIPHERAL_ALIGNMENTS = "Peripheral Alignments";	
	
	//Reverse Shared Intermediary
	public static String COMMON_ALIGNMENT = "Common Alignment";
	public static String COMPONENT_REFERENCE = "Component Reference";
	public static String CORRELATION_SCORE = "Correlation Score";
	public static String STRENGTH = "Strength";
	public static String UNMET_STATEMENTS = "Unmet Statements";
	public static String MET_STATMENTS = "Met Statements";
	
	//Expected Correlation Scores for Product
	public static List <String> Corr_Product = Arrays.asList("1/1","3/4","","","2/4","","0/1","0/0","","0/0");
	public static List <String> met_List = Arrays.asList("ELA.CP.1 -Intermediary 1", "ELA.CP.2 - Intermediary 2\nELA.CP.4 - Intermediary 4\nELA.CP.3 - Intermediary 3","","",
			"ELA.CP.6 - Intermediary 6\nELA.CP.7 - Intermediary 7","","","","","");
	public static List <String> strength_List = Arrays.asList("COMPLETE", "STRONG","","","AVERAGE","","WEAK", "NO CORRELATION","","NO CORRELATION");
	public static List <String> unmet_List = Arrays.asList("","ELA.CP.5 - Intermediary 5","","","ELA.CP.8 - Intermediary 8\n ELA.CP.12 - Intermediary 12","","ELA.CP.9 -Intermediary 9","","","");
	
	public static String DATA_FORMAT = "dd/MM/yyyy HH:mm:ss";
	
	public static String ADMIN_USER_COMMON = "learn usr1";
	public static String ADMIN_USER_PPE = "LOMT ADMIN";
	public static String LEARNING_USER_PPE = "LOMT USER";
	public static String SME_USER = "LOMT SME"; 
	public static String EDITOR_USER = "LOMT EDITOR";
	
	//Forward Indirect Intermediary Report
	public static String LOMT_1758 = "LOMT-1758";
	public static String FORWARD_INDIRECT_INTERMEDIARY_REPORT = "Forward Indirect Intermediary Report";
	
	//Reports Test data - prerequisites : PPE
	public static String CS_GOALFRAMEWORK_NAME_PPE = "Australian Curriculum, Assessment and Reporting Authority Art and Design K-12 2564";
	public static String INGESTED_INTERMEDIARY_PPE = "Information and Communication Technology";
	
	//Reverse Direct Report
	public static String LOMT_1761 = "LOMT-1761";
	public static String SYSTEM_UUID = "System UUID";
	public static String CONTENT_REFERENCE = "Content Reference";
	
	//Reverse Shared Intermediary Report
	public static String LOMT_1839 = "LOMT-1839";
	
	//Summary Report
	public static String LOMT_1841 = "LOMT-1841";
	public static String SUMMARY_REPORT = "Summary Report";
	
	//Reverse ToC to Standard via intermediary report
	public static String LOMT_1837 = "LOMT-1837";
	
	
	//Forward Direct Report
	public static String LOMT_1760 = "LOMT-1760";
	public static String FORWARD_DIRECT_REPORT = "Forward Direct Report";
		
	//Gap Analysis Report
	public static String LOMT_1840 = "LOMT-1840";
	public static String GAP_ANALYSIS_REPORT = "Gap Analysis Standard to Standard Report";
	public static String CS_YEAR_PPE = "1669";	
	public static String CS_SOURCE_YEAR_UAT = "United States Business K-12 2015";
	public static String CS_TARGET_YEAR_UAT = "United States Business Studies K-12 2014";
	public static String COLOUR_KEY = "Colour Key";
	public static String OVERVIEW_HEADING = "= Overview Heading"; 
	public static String LEVEL_1_HEADING = "= Level 1 Heading";
	public static String LEVEL_2_HEADING = "= Level 2 Heading";
	public static String LEVEL_3_HEADING = "= Level 3 Heading";
	public static String NO_ALIGNMENT = "= No Alignment";
	public static String ALIGNMENT = "Alignment";
	public static String COMMENT = "Comment";
	
	//Test data 
	
	//Levels, Standards' Topics
	public static String LEVEL_1 = "Reading";
	public static String LEVEL_2 = "Text Types and Purposes - LEVEL 2.";
	public static String LEVEL_3 = "Write arguments to support claims in an analysis of substantive topics or texts, using valid reasoning and relevant and sufficient evidence - LEVEL 3.";
	public static String LEVEL_4 = "Reading LEVEL 4.";
	public static String LEVEL_5 = "Reading LEVEL 5.";
	public static String LEVEL_6 = "Reading LOWEST LEVEL";
	
	//Comments
	public static String COMMENT_1 = "Gap Analysis";
	public static String COMMENT_2 = "Lowest node alignment";
	
	//Standard Number
	public static String SN_1 = "W";
	public static String SN_2 = "W.1";
	
	//Standards' Strands
	public static String SS_1 = "Automation Report Test-2";
	public static String SS_2 = "Automation Report Test-3";
	
	//Gade
	public static String GARDE_12 = "K-12";
	public static String GARDE_5 = "K-5";
	
	//Alignment Types
	public static String EXACT = "Exact";
	public static String RELATED = "Related";
	public static String BROAD = "Broad";
	public static String CLOSE = "Close";
	public static String NARROW = "Narrow";
	
	//Target Standards' Topics
	public static String ST_1 = "TEST 1";
	public static String ST_2 = "TEST 2";
	public static String ST_3 = "TEST 3";
	public static String ST_4 = "TEST 4";
	public static String ST_5 = "Conventions of Standard English";
	
	//Target Standards' Number
	public static String W = "W";
	public static String SL = "SL";
	public static String L = "L";
	
	//End Test data
	
	//This methods returns Standards' Topics, CS Correlation Score, Strength and Peripheral Alignments
	public static Map<String, List<String>> getForwardDirectCSTestData() {
			Map<String, List<String>> map = new LinkedHashMap<String, List<String>>();
			
			List<String> list = new LinkedList<String>();
			list.add("TEST 1");
			list.add("3/3");
			list.add("COMPLETE");		
			list.add("1");
			map.put("1", list);
			
			List<String> list1 = new LinkedList<String>();
			list1.add("Text Types and Purposes");
			list1.add("NO CORRELATION");
			list1.add("WEAK");
			list1.add("0");
			map.put("2", list1);
			
			List<String> list2 = new LinkedList<String>();
			list2.add("Write arguments to support claims in an analysis of substantive topics or texts, using valid reasoning and relevant and sufficient evidence.");
			list2.add("NO CORRELATION");
			list2.add("WEAK");
			list2.add("0");
			map.put("3", list2);
			
			List<String> list3 = new LinkedList<String>();
			list3.add("TEST 2");
			list3.add("2/2");
			list3.add("COMPLETE");
			list3.add("1");
			map.put("4", list3);
			
			List<String> list4 = new LinkedList<String>();
			list4.add("Comprehension and Collaboration");
			list4.add("NO CORRELATION");
			list4.add("WEAK");
			list4.add("0");
			map.put("5", list4);
			
			List<String> list5 = new LinkedList<String>();
			list5.add("Prepare for and participate effectively in a range of conversations and collaborations with diverse partners, building on others’ ideas and expressing their own clearly and persuasively.");
			list5.add("NO CORRELATION");
			list5.add("WEAK");
			list5.add("0");
			map.put("6", list5);
			
			List<String> list6 = new LinkedList<String>();
			list6.add("TEST 3");
			list6.add("1/1");
			list6.add("COMPLETE");
			list6.add("0");
			map.put("7", list6);
			
			List<String> list7 = new LinkedList<String>();
			list7.add("Conventions of Standard English");
			list7.add("NO CORRELATION");
			list7.add("WEAK");
			list7.add("0");
			map.put("8", list7);
			
			List<String> list8 = new LinkedList<String>();
			list8.add("Demonstrate command of the conventions of standard English grammar and usage when writing or speaking.");
			list8.add("NO CORRELATION");
			list8.add("WEAK");
			list8.add("0");
			map.put("9", list8);
			
			List<String> list9 = new LinkedList<String>();
			list9.add("TEST 4");
			list9.add("NO CORRELATION");
			list9.add("WEAK");
			list9.add("0");
			map.put("10", list9);
			
			List<String> list10 = new LinkedList<String>();
			list10.add("Conventions of Standard English");
			list10.add("NO CORRELATION");
			list10.add("WEAK");
			list10.add("0");
			map.put("11", list10);
			
			List<String> list11 = new LinkedList<String>();
			list11.add("Abc");
			list11.add("NO CORRELATION");
			list11.add("WEAK");
			list11.add("0");
			map.put("12", list11);
			
			return map;
		}

}
