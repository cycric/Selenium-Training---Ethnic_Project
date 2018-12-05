package com.EthnicStore.test.integration.storePageObjects;

import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.annotations.Test;

import com.EthnicStore.test.integration.utility.Log;
import com.EthnicStore.test.integration.utility.WaitTool;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.thoughtworks.selenium.Wait;

public class CheckoutOptionPage extends LoadableComponent<CheckoutOptionPage>{
	
	@FindBy(id="tdb6")
	WebElement checkoutBtn;
	
	@FindBy(xpath=".//*[@id='bodyContent']/form/div/p[2]/a/img")
	WebElement payPalBtn;
	
	public CheckoutOptionPage()
	{
		PageFactory.initElements(Browser.driver(), this);
	}
	
	public NormalCheckoutPage clickOnCheckoutButton()
	{
		try{
		WaitTool.waitForElementPresent(checkoutBtn, 15);
		checkoutBtn.click();
		Log.info("Clicked on normal checkout button.");
		}
		catch(ElementNotFoundException e)
		{
			System.out.println("Checkout button is not present on the page");
		}
		
		NormalCheckoutPage checkout = new NormalCheckoutPage();
		return checkout;
	}
	
	public PaypalCheckoutMethod clickOnPaypalButton()
	{
		try{
			WaitTool.waitForElementPresent(payPalBtn, 15);
			payPalBtn.click();
			Log.info("Clicked on Paypal Checkout button.");
		}catch(NoSuchElementException e)
		{
			System.out.println("The paypal checkout button is not present on the page.");
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
