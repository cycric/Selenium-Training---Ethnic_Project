package com.EthnicStore.test.integration.storePageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.annotations.Test;

import com.EthnicStore.test.integration.storePageObjects.Browser;
import com.EthnicStore.test.integration.utility.Log;
import com.EthnicStore.test.integration.utility.WaitTool;

public class PinItShareWindowPage {
	@FindBy(id="userEmail")
	WebElement pinItEmailField;
	
	@FindBy(id="userPassword")
	WebElement pinItPassField;
	

	public PinItShareWindowPage()
	{
		PageFactory.initElements(Browser.driver(), this);
	}
	
	public void pinItUserLogin(String user, String pass) 
	{
		Browser.driver().manage().window().maximize();
		WaitTool.waitForElementPresent(pinItEmailField, 60);
		pinItEmailField.sendKeys(user);
		Log.info("Entered PinIt username!");
		
		WaitTool.waitForElementPresent(pinItPassField, 60);
		pinItPassField.sendKeys(pass);
		Log.info("Entered PinIt password!");
	}
}
