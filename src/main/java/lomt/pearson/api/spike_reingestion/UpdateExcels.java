package lomt.pearson.api.spike_reingestion;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import lomt.pearson.constant.ExternalFrameworkTestData;
public class UpdateExcels {  
	
	public static String EXPORTED_FILE_PATH = System.getProperty("user.dir")
			+ "\\src\\main\\java\\lomt\\pearson\\api\\spike_reingestion\\External_Framework_Template22Sep2018-1.xlsx";
 
        public static void main(String[] args) throws Exception{
                 
                FileInputStream fsIP= new FileInputStream(new File(EXPORTED_FILE_PATH)); //Read the spreadsheet that needs to be updated
                  
                XSSFWorkbook wb = new XSSFWorkbook(fsIP); //Access the workbook
                  
                XSSFSheet worksheet = wb.getSheetAt(0); //Access the worksheet, so that we can update / modify it.
                  
                Cell cell = null; // declare a Cell object
                
                cell = worksheet.getRow(0).getCell(0);   // Access the second cell in second row to update the value
                  
                cell.setCellValue(ExternalFrameworkTestData.EXF_GOALFRAMEWORK_TITLE_UPDATED);  // Get current cell value value and overwrite the value
                  
                fsIP.close(); //Close the InputStream
                 
                FileOutputStream output_file =new FileOutputStream(new File(EXPORTED_FILE_PATH));  //Open FileOutputStream to write updates
                  
                wb.write(output_file); //write changes
                  
                output_file.close();  //close the stream    
        }
}
