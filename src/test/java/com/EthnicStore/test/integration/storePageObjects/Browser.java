package com.EthnicStore.test.integration.storePageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

//import com.opera.core.systems.OperaDriver;

public class Browser {
	/* Future Enhancements
	 * 1. Add a static int field and set the timeout as per browser, as per 
	 * current information Chrome is the fastest needing the lowest timeout
	 * Then firefox and IE is the slowest
	 * Currently how to get driver executable for Safari is not known and the same
	 * needs to be added
	 * Even Opera needs to be added
	 */
	private static WebDriver driver;
	public static void setBrowser(WebDriver remoteWebDriver) {
		driver = remoteWebDriver;
	}
	public static void setBrowser(String browser) {
		System.out.println("Inside setBrowser value = " + browser);
		if (browser.equals("CH")) {
			System.out.println("Test will run on Chrome Browser");
			System.setProperty("webdriver.chrome.driver", "src\\test\\java\\resources\\chromedriver.exe");
	    	driver = new ChromeDriver();			
		}
		else if (browser.equals("FF")) {
			System.out.println("Test will run on Firefox Browser");
		//	FirefoxProfile profile = new FirefoxProfile();
		//	profile.setEnableNativeEvents(false);
			driver = new FirefoxDriver();
			
		} else if (browser.equals("SF")) {
			DesiredCapabilities capabilities = DesiredCapabilities.safari();
		//	capabilities.setJavascriptEnabled(true);
			capabilities.setCapability("nativeEvents", false);
		// capability.setBrowserName("firefox");
	       // capability.setPlatform(Platform.WIN8_1);
			driver = new SafariDriver(capabilities);
			
			
		}
		else if (browser.equals("IE")) {
			System.out.println("Test will run on Internet Explorer Browser");
			System.setProperty("webdriver.ie.driver", "src\\test\\java\\resources\\IEDriverServer.exe");
	    	driver = new InternetExplorerDriver();
	    	
		}
		else if (browser.equals("OP")) {
			System.out.println("Test will run on Opera Browser");
			driver = new OperaDriver();
		//	 DesiredCapabilities capabilities = DesiredCapabilities.opera();  
		//	 capabilities.setCapability("opera.binary", "C:\\Program Files (x86)\\Opera\\27.0.1689.76\\opera.exe");  
//			 driver = new OperaDriver(capabilities);   
		}
		else 
		{
			System.out.println("Test will run on Firefox Browser as Default Browser");
			driver = new FirefoxDriver();
		}
    	
	}
  
    public static WebDriver driver() {
    	return driver;
    }  
        
    public static void open(String url) {
    	driver.manage().window().maximize();
    	driver.get(url); 
       
     }

    public static void close() {
       driver.quit();
    }

	public static void setRemoteBrowser(String browser) {
		// TODO Auto-generated method stub
		
	}

	public static void setRemotePlatform(String platform) {
		// TODO Auto-generated method stub
		
	}
}
