package com.EthnicStore.test.integration.storePageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import com.EthnicStore.test.integration.utility.WaitTool;
public class FbShareTabPage extends LoadableComponent<FbShareTabPage>{
	@FindBy(id = "email")
	WebElement fbUserField;
	
	@FindBy(id = "pass")
	WebElement fbPassField;
	
	@FindBy(id = "u_0_2")
	WebElement logInButton;
	
	
	public FbShareTabPage()
	{
		PageFactory.initElements(Browser.driver(), this);
	}
	
	public void shareOnFB(String user, String pass)
	{
		WaitTool.waitForElementPresent(fbUserField, 15);
		fbUserField.sendKeys(user);
		
		WaitTool.waitForElementPresent(fbPassField, 15);
		fbPassField.sendKeys(pass);
		
		logInButton.click();
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
