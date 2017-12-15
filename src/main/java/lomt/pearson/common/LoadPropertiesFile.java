package lomt.pearson.common;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import lomt.pearson.constant.LOMTConstant;

public class LoadPropertiesFile {

	public static String getPropertiesValues(String value) {
		InputStream input = null;
		String propertiesValue = null;
		Properties properties = new Properties();
		try {
			input = new FileInputStream(LOMTConstant.CONFIGURATION_FILE_PATH);
			properties.load(input);
			propertiesValue = properties.getProperty(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return propertiesValue;
	}

}
