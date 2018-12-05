package com.EthnicStore.test.integration.storePageObjects;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.EthnicStore.test.integration.utility.Log;
import com.EthnicStore.test.integration.utility.WaitTool;

public class ShoppingCartPage extends LoadableComponent<ShoppingCartPage>{
	@FindBy(xpath = "//table[@cellspacing='2']/tbody/tr/td[2]/input[1]")
	WebElement qtyField;
 
	@FindBy(xpath = "//table[@cellspacing='2']/tbody/tr/td[2]/span/button")
	WebElement updateBtn;
	
	@FindBy(xpath=".//*[@id='bodyContent']/form/div/div[1]/table/tbody/tr")
	List<WebElement> productNo;
	
	@FindBy(xpath = "//td[@valign='top']/a[2]")
	WebElement removeLink;
	
	@FindBy(xpath = ".//*[@id='tdb2']/span[2]")
	WebElement checkOutBtn;
	
	@FindBy(xpath = "//p[@align='right']/a/img")
	WebElement payPalBtn;
	
	@FindBy(xpath = ".//*[@id='columnRight']/div[1]/table/tbody/tr[1]/td[1]")
	WebElement qtyString;
	
	@FindBy(xpath = "//select[@name='currency']")
	WebElement currencyDropdown;
	
	@FindBy(xpath = "//td[@align='right']/strong")
	WebElement priceString;
	
	@FindBy(xpath = "//div[@class='contentText']")
	WebElement emptyCartMsg;
	
	@FindBy(id = "tdb5")
	WebElement continueBtn;
	
	public ShoppingCartPage()
	{
		PageFactory.initElements(Browser.driver(), this);
	}
	
	public void updateProductQty(String qty)
	{
		WaitTool.waitForElementPresent(qtyField, 15);
		qtyField.clear();
		qtyField.sendKeys(qty);
		
		updateBtn.click();
		Log.info("Product quantinty is updated!");
	}
	
	public int productNoInCart()
	{
		
		return productNo.size();
	}
	
	public boolean isCartEmpty()
	{
		if(emptyCartMsg.getText().contains("Cart is empty"))
		System.out.println("Cart is Empty! Please add product to checkoout");
		//continueBtn.click();
		Log.info("Procceeding to homepage to add products.");
		return true;
	}
	
	public void verifyProductQty()
	{
		System.out.println("Quantity value present in QTY filed: " +qtyField.getAttribute("value"));
		System.out.println("Quantity value mentioned on the cart page:"+qtyString.getText());
		assertTrue(qtyString.getText().contains(qtyField.getAttribute("value")));
		
		Log.info("Quantity of the product is verified!");
	}
	
	public void changeCurrency()
	{
		Select currencyOption = new Select(currencyDropdown);
		if(currencyOption.getFirstSelectedOption().getAttribute("value").equals("USD"))
		{
			currencyOption.selectByIndex(1);
		}
		else{
			currencyOption.selectByValue("USD");
		}
	}
	
	public void verifyCurrency()
	{
		Select currencyOption = new Select(currencyDropdown);
		if(currencyOption.getFirstSelectedOption().getText().equals("U.S. Dollar"))
		{
			Log.info("Currency: U. S. Dollar");
			assertTrue(priceString.getText().contains("$"), "Currency Verified!");
		}
		else
		{
			Log.info("Currency: Euro");
			assertTrue(priceString.getText().contains("€"), "Currency Verified!");
		}
	}
	
	public NormalCheckoutPage usingNormalCheckoutMethod()
	{
		try{
			WaitTool.waitForElementPresent(checkOutBtn, 30);
			checkOutBtn.click();
			Log.info("Procceeding for normal checkout.");
			
		}catch(Exception e)
		{
			System.out.println("Unable to procced for normal checkout process");
		}
		
		NormalCheckoutPage checkout = new NormalCheckoutPage();
		return checkout;
	}
	
	public PaypalCheckoutMethod usingPaypalCheckoutMethod()
	{
		try{
			payPalBtn.click();
			Log.info("Procceeding for Paypal Checkout.");
		}catch(Exception e)
		{
			System.out.println("Unable to proceed for Paypal checkout process");
		}
		PaypalCheckoutMethod paypal = new PaypalCheckoutMethod();
		return paypal;
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
