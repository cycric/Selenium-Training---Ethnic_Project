package com.EthnicStore.test.integration.storePageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;

import com.EthnicStore.test.integration.model.NewRegistrationDetails;
import com.EthnicStore.test.integration.utility.WaitTool;

public class RegistrationFormPage extends LoadableComponent<RegistrationFormPage>{
  @FindBy(xpath="//input[@name='gender']")
  List<WebElement> genderRadio;

  @FindBy(xpath="//input[@name='firstname']")
  WebElement firstName;
  
  @FindBy(xpath="//input[@name='lastname']")
  WebElement lastName;
  
  @FindBy(id="dob")
  WebElement dobField;
  
  @FindBy(xpath="//input[@name='email_address']")
  WebElement emailField;
  
  @FindBy(xpath="//input[@name='street_address']")
  WebElement addrField;
  
  @FindBy(xpath="//input[@name='postcode']")
  WebElement postCodeField;
  
  @FindBy(xpath = "//input[@name='city']")
  WebElement cityField;
  
  @FindBy(xpath="//input[@name='state']")
  WebElement stateField;
  
  @FindBy(xpath = "//select[@name='country']")
  WebElement countryField;
  
  @FindBy(xpath = "//input[@name='telephone']")
  WebElement telePhoneField;

  @FindBy(xpath = "//input[@name='password']")
  WebElement passField;
  
  @FindBy(xpath="//input[@name='confirmation']")
  WebElement confirmPassField;
 
  @FindBy(id="tdb4")
  WebElement continueButton;
  

public RegistrationFormPage()
{
	PageFactory.initElements(Browser.driver(), this);
	get();
	
}

@SuppressWarnings("static-access")
public void registerUser(NewRegistrationDetails registerUser)
{
	WaitTool.waitForListElementsPresent(Browser.driver(), By.xpath("//input[@name='gender']"), 30);
	
		if(registerUser.getGender().equalsIgnoreCase("Male"))
		{
			genderRadio.get(0).click();
		}
		else{
			genderRadio.get(1).click();
		}
	
	
	firstName.sendKeys(registerUser.getFirstName());
	lastName.sendKeys(registerUser.getLastName());
	dobField.sendKeys(registerUser.getDOB());
	emailField.sendKeys(registerUser.getEmail());
	addrField.sendKeys(registerUser.getStreetLine1());
	postCodeField.sendKeys(registerUser.getZipcode());
	cityField.sendKeys(registerUser.getCity());
	stateField.sendKeys(registerUser.getState());
	Select country = new Select(countryField);
	country.selectByVisibleText(registerUser.getCountry());
	telePhoneField.sendKeys(registerUser.getPhone());
	passField.sendKeys(registerUser.getPassword());
	confirmPassField.sendKeys(registerUser.getRePassword());
	continueButton.click();
}

@Override
protected void isLoaded() throws Error {
	// TODO Auto-generated method stub
	
}

@Override
protected void load() {
	// TODO Auto-generated method stub
	
}
  
}
