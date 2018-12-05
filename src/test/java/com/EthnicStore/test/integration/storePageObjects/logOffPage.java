package com.EthnicStore.test.integration.storePageObjects;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import com.EthnicStore.test.integration.utility.Log;

public class logOffPage extends LoadableComponent<logOffPage>{
	@FindBy(xpath = "//div[@class='contentText']")
	WebElement logOffMessageBody;
	
	public logOffPage()
	{
		PageFactory.initElements(Browser.driver(), this);
		
	}
	
	public void verifyLogOffMessage()
	{
		try{
			String message = logOffMessageBody.getText();
			assertTrue(message.contains("You have been logged off your account"));
			Log.info("Log off message verified successfully!");
		}
		catch(Exception e)
		{
			Log.info("Log off message verification is failed.");
		}
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		
	}
	
}
