package com.EthnicStore.test.integration.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.EthnicStore.test.integration.storePageObjects.Browser;
import com.EthnicStore.test.integration.utility.Log;
import com.EthnicStore.test.integration.utility.WaitTool;

public class GooglePlusShareWindowPage {
	@FindBy(id="identifierId")
	WebElement emailField;
	
	@FindBy(id = "identifierNext")
	WebElement nextButton;
	
	@FindBy(xpath="//input[@name='password']")
	WebElement passField;
	
	@FindBy(id="passwordNext")
	WebElement signInButton;
	
	public GooglePlusShareWindowPage()
	{
		PageFactory.initElements(Browser.driver(), this);
	}
	
	
	public void gPlusUserLogin(String user, String pass)
	{
		//WaitTool.waitForElementPresentBy(By.xpath(".//*[@id='logo']/div/svg"), 15);
		WaitTool.waitForElementPresent(nextButton, 20);
		
		emailField.sendKeys(user);
		nextButton.click();
		Log.info("G+ username has been entered");
		
		WaitTool.waitForElementPresent(passField, 30);
		passField.sendKeys(pass);
		Log.info("G+ password has been entered");
		
		signInButton.click();
	}
	
}
