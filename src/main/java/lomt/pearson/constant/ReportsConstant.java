package lomt.pearson.constant;

import java.util.Arrays;
import java.util.List;

public class ReportsConstant {
	
	public static String INDIRECT_TEXT = "Indirect";
	public static String FORWARD_INDIRECT_INT_REPORT_FILE_NAME = "Forward (Indirect) Intermediary Report";
	public static String PRODUCT_TOC_INT_REPORT_FILE_NAME = "Automation Report";
	public static String PRODUCT_INT_TEXT = "Product (ToC) Intermediary Report";
	public static String REVERSE_SHARED_INT_TEXT = "Reverse Shared Intermediary Report";
	public static String REVERSE_DIRECT_TEXT = "Reverse Direct Report";
	public static String REVERSE_TOC_STANDARD_VIA_INT_TEXT = "Reverse ToC to Standard via intermediary report";
	public static String INGESTED_PRODUCT = "Automation_Report_Verification_Product";
	public static String INGESTED_INTERMEDIARY = "Psychology";
	public static String INGESTED_STANDARD_YEAR = "1352";
	
	//Forward Indirect Intermediary Report
	public static String TITLE = "Title";
	public static String DATE_TIME_GENERATION = "Date / time of generation";
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
	public static String CS_GOALFRAMEWORK_NAME_PPE = "Department for Education Accounting K-12 1352";
	public static String INGESTED_INTERMEDIARY_PPE = "Information and Communication Technology";
	
	//Reverse Direct Report
	public static String LOMT_1761 = "LOMT-1761";
	public static String SYSTEM_UUID = "System UUID";
	public static String CONTENT_REFERENCE = "Content Reference";
	
	//Reverse Shared Intermediary Report
	public static String LOMT_1839 = "LOMT-1839";
	
	//Reverse ToC to Standard via intermediary report
	public static String LOMT_1837 = "LOMT-1837";
	

}
