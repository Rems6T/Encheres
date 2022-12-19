package fr.eni.ENI_Encheres.dal;

import java.io.IOException;
import java.util.Properties;

public class Settings {
	private static Properties properties;

	static {
		properties = new Properties();
		try {
			properties.load(Settings.class.getResourceAsStream("settings.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static String getProperties(String key) {
		return properties.getProperty(key);
	}
	
	
}
