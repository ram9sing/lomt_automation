package lomt.pearson.constant;

import java.util.Random;

public class ExternalFrameworkTestData {
	
	//Constant
	public static String RE_INGESTION = "Ren-ingestion of ";
	public static String HE =  "HE";
	public static String ENGLISH =  "English";
	public static String SCHOOL =  "School";
	public static String EXTERNAL_FRAMEWORK_TEMPLATE = "External_Framework_Template.xlsx";
	public static String EXF_FILE_PATH_VALIDATION = "ExF_ReingestionValidationCheck.xlsx";
	public static String EXF_DESTINATION_FILE_PATH = System.getProperty("user.dir")+ "\\src\\main\\java\\lomt\\pearson\\fileupload\\externalframework\\ex_reingestion\\";
	
	//HE Test data
	public static String EXF_GOALFRAMEWORK_TITLE_UPDATED = "External Frameworks_all_fields_with_metadata_A007_Updated-"+new Random().nextInt(500);
	public static String EXF_GRADE_TITLE = "Grade Title !@$%^&*()_+{}|:';<>?Test123456Updated-"+new Random().nextInt(60);
	public static String EXF_LEVEL_ONE = "Ability to apply the process of science ExternalFramework!@$%^&*()_+{}|:';<>?Test123456Updated-"+new Random().nextInt(70);
	public static String EXF_LEVEL_TWO = "Instantiation of Ability in Disciplinary Practice ExternalFramework!@$%^&*()_+{}|:';<>?Test123456Updated-"+new Random().nextInt(80);
	public static String EXF_LEVEL_LOWEST = "Biology is an evidence-based discipline ExternalFramework!@$%^&*()_+{}|:';<>?Test123456Updated-"+new Random().nextInt(90);
	public static String EXF_OFF_STD_CODE = "CCE A !@$%^&*()_+{}|:';<>?Test123456Updated-"+new Random().nextInt(95);
	public static String EXF_GRADE_LOW_K = "K";
	public static String EXF_GRADE_LOW_PK = "PK";
	public static String EXF_GRADE_LOW_1 = "1";
	
	public static String EXF_GRADE_HIGH_K_11 = "11";	
	public static String EXF_GRADE_HIGH_PK_10 = "10";
	public static String EXF_GRADE_HIGH_1_12 = "12";
	
	/*public static void main(String [] args) {
		//System.out.println(new Random().nextInt(50)+1);
		System.out.println(ExternalFrameworkTestData.EXF_GOALFRAMEWORK_TITLE_UPDATED);
	}*/
}
