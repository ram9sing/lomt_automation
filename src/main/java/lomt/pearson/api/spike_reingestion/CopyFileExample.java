package lomt.pearson.api.spike_reingestion;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import lomt.pearson.constant.ExternalFrameworkTestData;
import lomt.pearson.constant.LOMTConstant;

public class CopyFileExample {
	
	public static String EXPORTED_FILE_PATH = System.getProperty("user.dir")
			+ "\\src\\main\\java\\lomt\\pearson\\api\\spike_reingestion\\External_Framework_Template22Sep2017.xlsx";
	public static String EXPORTED_FILE_PATH1 = System.getProperty("user.dir")
			+ "\\src\\main\\java\\lomt\\pearson\\api\\spike_reingestion\\NEW.xlsx";

	public static void main(String[] args) throws IOException {
		/*File sourceFile = new File(EXPORTED_FILE_PATH);
		File destinationFile = new File(EXPORTED_FILE_PATH1);
		
		copyFileUsingChannel(sourceFile, destinationFile);*/
		//getFileFromDirectory();
		
		updateExF(new File(EXPORTED_FILE_PATH));
	}
	
	private static void copyFileUsingChannel(File source, File dest) throws IOException {
	    FileChannel sourceChannel = null;
	    FileChannel destChannel = null;
	    try {
	        sourceChannel = new FileInputStream(source).getChannel();
	        destChannel = new FileOutputStream(dest).getChannel();
	        destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
	       }finally{
	           sourceChannel.close();
	           destChannel.close();
	   }
	}
	
	/*private static void getFileFromDirectory() {
		File folder = new File(LOMTConstant.EXPORTED_FILE_PATH);
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
		      if (listOfFiles[i].isFile()) {
		        System.out.println("File " + listOfFiles[i].getName());
		      } 
		    }
	}*/
	
	public static void updateExF(File file) throws IOException {
		OutputStream os = null;
		InputStream is = null;
		XSSFWorkbook ws = null;
		try {
			if (file.isFile()) {
				
			}
			os = new FileOutputStream(file);
			is = new FileInputStream(file);
			ws = new XSSFWorkbook(is);
			XSSFSheet sheet = ws.getSheetAt(0);

			Iterator<Row> rowIteratore = sheet.iterator();
			while (rowIteratore.hasNext()) {
				Row rowExp = rowIteratore.next();
				if (!(String.valueOf(rowExp.getCell(LOMTConstant.ZERO)).contains(LOMTConstant.NULL))) {
					Cell cell = rowExp.getCell(LOMTConstant.ZERO);
					cell.setCellValue(ExternalFrameworkTestData.EXF_GOALFRAMEWORK_TITLE_UPDATED);
					ws.write(os);
					os.close();
					is.close();
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
