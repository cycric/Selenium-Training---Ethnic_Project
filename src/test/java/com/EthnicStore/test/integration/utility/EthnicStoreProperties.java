package com.EthnicStore.test.integration.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class EthnicStoreProperties {
	private static Properties prop;
	
	public final static String BASEURL = "BASEURL";

	public final static String PROPERTY_FILENAME = "config/ethnicStore.properties";

	private Properties avactis_properties = new Properties();

	public final static String XLS_DATA = "XLS_DATA";

	public final static String BROWSER = "BROWSER";

	/**
	 * Loads the properties file
	 * @return 
	 */
	public EthnicStoreProperties() 
{
		prop = new Properties();
}		
		public static void loadProperties()
		{
			try
			{
				prop.load(new FileInputStream("config/ethnicStore.properties"));
				
			}catch (FileNotFoundException e)
			{
				System.out.println("Config.properties file not found");
			} catch (IOException e)
			{
				System.out.println("IO exception while accessing confif.properties file");
			}
		}
		
		public static String getProperty(String keyValue)
		{
			return prop.getProperty(keyValue);
		}
}
	
