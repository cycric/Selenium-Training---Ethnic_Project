package com.EthnicStore.test.integration.storePageObjects;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;

import com.EthnicStore.test.integration.model.Address;
import com.EthnicStore.test.integration.utility.ConfigProperties;
import com.EthnicStore.test.integration.utility.Log;
import com.EthnicStore.test.integration.utility.WaitTool;

public class NormalCheckoutPage extends LoadableComponent<NormalCheckoutPage>{
	@FindBy(id = "tdb5")
	WebElement changeAddrBtn;
	
	@FindBy(how = How.TAG_NAME, using = "textarea")
    WebElement commentBox;
	
	 @FindBy(xpath = ".//*[@id='tdb6']/span[2]")
	 WebElement continueBtn;
	 
	 @FindBy(id = "tdb5")
	 WebElement submitAddrBtn;
	
	 @FindBy(xpath = "//input[@value='m']")
	 WebElement maleRadio;
	
	 @FindBy(xpath = "//input[@value='f']")
	 WebElement femaleRadio;
	
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
	  
	  @FindBy(xpath = "//input[@value='cod']")
	  WebElement codRadio;
	  
	  @FindBy(xpath = "//input[@value='paypal_express']")
	  WebElement paypalRadio;
	  
	  @FindBy(id = "tdb5")
	  WebElement confirmOrderBtn;
	  
	  @FindBy(xpath = ".//*[@id='bodyContent']/h1")
	  WebElement confirmationMsgField;
	  
	  
	  public NormalCheckoutPage()
	  {
		  PageFactory.initElements(Browser.driver(), this);
	  }
	  
	  public void confirmOrderUsingCOD()
	  {
		  WaitTool.waitForElementPresent(continueBtn, 15);
		  continueBtn.click();
		  Log.info("Delivery Information step passed!");
		  
		  WaitTool.waitForElementPresent(codRadio, 15);
		  codRadio.click();
		  Log.info("Cash on delivery option is selected!");
		  
		  continueBtn.click();
		  Log.info("Payment Information step passed!");
		  
		  WaitTool.waitForElementPresent(confirmOrderBtn, 15);
		  confirmOrderBtn.click();
		  Log.info("Order Confirmation step passed!");
		  
		  String message = confirmationMsgField.getText();
		  assertTrue(message.contains("Your Order Has Been Processed"), "Order confirmation message is verified!");
		  
	  }
	  
	  public void changeShippingAddress(Address newAddress)
	  {
		  WaitTool.waitForElementPresent(changeAddrBtn, 15);
		  changeAddrBtn.click();
		  
		  WaitTool.waitForElementPresent(maleRadio, 15);
		  if(newAddress.getGender().equalsIgnoreCase("Male"))
		  {
			  maleRadio.click();
		  }else{
			  femaleRadio.click();
		  }
		  
		  firstName.sendKeys(newAddress.getFirstName());
		  lastName.sendKeys(newAddress.getLastName());
		  addrField.sendKeys(newAddress.getAddLine1());
		  postCodeField.sendKeys(newAddress.getZipcode());
		  cityField.sendKeys(newAddress.getCity());
		  stateField.sendKeys(newAddress.getState());
		  Select country = new Select(countryField);
		  country.selectByVisibleText(newAddress.getCountry());
		  
		  submitAddrBtn.click();
	  }
	  
	  public PaypalCheckoutMethod confirmOrderUsingPaypal()
	  {
		  WaitTool.waitForElementPresent(continueBtn, 15);
		  continueBtn.click();
		  Log.info("Delivery Information step passed!");
		  
		  WaitTool.waitForElementPresent(paypalRadio, 15);
		  paypalRadio.click();
		  Log.info("Paypal option is selected!");
		  
		  continueBtn.click();
		  Log.info("Clicked on continue button and page is redirecting to Paypal website!");
		  
		  PaypalCheckoutMethod paypalMethod = new PaypalCheckoutMethod();
		  return paypalMethod;
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
