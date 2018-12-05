package com.EthnicStore.test.integration.storePageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import com.EthnicStore.test.integration.storePageObjects.Browser;
import com.EthnicStore.test.integration.utility.WaitTool;
import com.EthnicStore.test.integration.storePageObjects.MyAccount;

public class EthnicStoreSignIn extends LoadableComponent<EthnicStoreSignIn>{
  @FindBy(how=How.NAME, using = "email_address")
  WebElement userNameField;
  
  @FindBy(how=How.NAME, using = "password")
  WebElement passwordField;
  
  @FindBy(how=How.ID, using = "tdb1")
  WebElement signInButton;
  
  @FindBy(how=How.ID, using = "tdb2")
  WebElement registerButton;
  
  public EthnicStoreSignIn()
  {
	  PageFactory.initElements(Browser.driver(), this);
		get();
  }

  public RegistrationFormPage doRegistration()
	{
		registerButton.click();
		RegistrationFormPage registrationFormPage = new RegistrationFormPage();
		return registrationFormPage;
	}
  
  public MyAccount userSignIn(String userName, String passWord)
  {
	  userNameField.sendKeys(userName);
	  passwordField.sendKeys(passWord);
	  
	  signInButton.click();
	  
	  MyAccount myAccount = new MyAccount();
	  return myAccount;
  }
  
@Override
protected void isLoaded() throws Error {
	// TODO Auto-generated method stub
	WaitTool.waitForElementPresent(registerButton, 20);
}

@Override
protected void load() {
	// TODO Auto-generated method stub
	
}
}
