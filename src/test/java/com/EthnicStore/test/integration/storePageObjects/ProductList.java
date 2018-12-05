package com.EthnicStore.test.integration.storePageObjects;

import static org.testng.Assert.assertTrue;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.EthnicStore.test.integration.model.GooglePlusShareWindowPage;
import com.EthnicStore.test.integration.storePageObjects.PinItShareWindowPage;
import com.EthnicStore.test.integration.storePageObjects.Browser;
import com.EthnicStore.test.integration.utility.ConfigProperties;
import com.EthnicStore.test.integration.utility.Log;
import com.EthnicStore.test.integration.utility.WaitTool;

public class ProductList extends LoadableComponent<ProductList>{
	
	@FindBy(xpath = "//*[@id='columnRight']/div[3]/div[2]/a[2]")
	WebElement fbShareButton;
	
	@FindBy(xpath = "//iframe[@title='+Share']")
	WebElement gPlusiframe;
	
	@FindBy(xpath = ".//*[@id='button']/div")
	WebElement gPlusShareBtn;
	
	@FindBy(xpath = "//div[@class='ui-widget-content infoBoxContents']/span")
	WebElement pinItButton;

	PinItShareWindowPage pinItShare;
	GooglePlusShareWindowPage gPlusShare;
	FbShareTabPage fbShare;
	
	private String menuSelection;
	public ProductList(String ProductCategory)
	{
		PageFactory.initElements(Browser.driver(), this);
		this.menuSelection = ProductCategory;
	}
	
	@Override
    public void isLoaded() {
		if (menuSelection != "")
			assertTrue(Browser.driver().getTitle().equals(menuSelection + " ,  " + ConfigProperties.getProperty("HOMEPAGE_TITLE")));
		else
			assertTrue(Browser.driver().getTitle().equals(ConfigProperties.getProperty("HOMEPAGE_TITLE")));
    }

	@Override
	protected void load() {
	}
	
	public ShoppingCartPage clickAddToCartButton(String productID) {
		  
		  WaitTool.waitForElementPresentBy(By.id("tdb4"), 30);
		  WebElement addToCartButton;
		  try {
			  addToCartButton = Browser.driver().findElement(By.xpath("//span[@class='buttonAction']/span/button/span[2]"));
			  addToCartButton.click();
			  Log.info("The product has been added into the cart successfully!");
		  } catch (NoSuchElementException e)
		  {
				  System.out.println("Problem with page loading");				  
		  }
		  ShoppingCartPage option = new ShoppingCartPage();
		  return option;
		 }
	
	public void shareProductOnPinIt(String pinItUserName, String pinItPassWord)
	{
		System.out.println("Current window:... "+Browser.driver().getCurrentUrl());
		String parentWindowID = Browser.driver().getWindowHandle();
		
		WaitTool.waitForElementPresent(pinItButton, 15);
		pinItButton.click();
		Set<String> allWindowIDs = Browser.driver().getWindowHandles();
		Log.info("Clicked on PinIt share button.");
		try{
		for(String currentWindow:allWindowIDs)
		{
			
			if(Browser.driver().switchTo().window(currentWindow).getTitle().contains("Pinterest"))
			{
			Log.info("Switched on PinIt window!");	
			Browser.driver().navigate().refresh();
			
		    pinItShare = new PinItShareWindowPage();
			pinItShare.pinItUserLogin(pinItUserName, pinItPassWord);
			
			Browser.driver().close();
			Browser.driver().switchTo().window(parentWindowID);
			Log.info("Switched to parent window from PinIt child window.");
			break;
			}else
			continue;
		}
		
		}catch(NoSuchFrameException e){
			e.toString();
			System.out.println("Pin It share window is not found!");
		}
	}
	
	public void shareProductOnFb(String fbUser, String fbPass)
	{
		WaitTool.waitForElementPresent(fbShareButton, 15);
		fbShareButton.click();
		String parentWindowID = Browser.driver().getWindowHandle();
		Set<String> allWindowIDs = Browser.driver().getWindowHandles();
		Log.info("Clicked on Facebook share button.");
		try{
		for(String currentWindow:allWindowIDs)
		{
			
			if(Browser.driver().switchTo().window(currentWindow).getTitle().contains("Pinterest"))
			{
			Log.info("Switched on Facebook tab!");	
			Browser.driver().navigate().refresh();
			
			fbShare = new FbShareTabPage();
			fbShare.shareOnFB(fbUser, fbPass);
			
			Browser.driver().close();
			Browser.driver().switchTo().window(parentWindowID);
			Log.info("Switched to parent window from PinIt child window.");
			break;
			}else
			continue;
		}
		
		}catch(NoSuchFrameException e){
			e.toString();
			System.out.println("Pin It share window is not found!");
		}
		
	}

	public void shareProductOnGPlus(String gPlusEmail, String gPlusPassword)
	{
		String parentWindowID = Browser.driver().getWindowHandle();
		Browser.driver().switchTo().frame(gPlusiframe);
		WaitTool.waitForElementPresent(gPlusShareBtn, 15);
		gPlusShareBtn.click();
		Set<String> allWindowIDs = Browser.driver().getWindowHandles();
		
		try{
			for(String currentWindow:allWindowIDs)
			{
				if(Browser.driver().switchTo().window(currentWindow).getTitle().contains("Google+"))
				{
					System.out.println("Switched on Google+ share window.");
					//Browser.driver().navigate().refresh();
				
					gPlusShare = new GooglePlusShareWindowPage();
					gPlusShare.gPlusUserLogin(gPlusEmail, gPlusPassword);
				
					Browser.driver().close();
					Browser.driver().switchTo().window(parentWindowID);
				}
				else 
					continue;
			}
			
			Browser.driver().close();
			Browser.driver().switchTo().defaultContent();
			}
			catch(NoSuchWindowException e){
			  System.out.println("Google+ share window is not found!");
			}
	}
}
