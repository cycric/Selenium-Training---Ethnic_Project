package com.EthnicStore.test.integration.storePageObjects;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;

import com.EthnicStore.test.integration.utility.ConfigProperties;
import com.EthnicStore.test.integration.utility.WaitTool;
import com.EthnicStore.test.integration.storePageObjects.Browser;
import com.EthnicStore.test.integration.storePageObjects.ProductList;


public class HomePage extends LoadableComponent<HomePage>{
	String url;
	String title = ConfigProperties.getProperty("HOMEPAGE_TITLE");
	
	@FindBy(id = "tdb1")
	WebElement cartContentBtn;
	
	@FindBy(id = "tdb2")
	WebElement checkoutBtn;
	
	@FindBy(id = "tdb3")
	WebElement myAccountBtn;
	
	@FindBy(id = "tdb4")
	WebElement logOutBtn;
	
	@FindBy(how=How.NAME, using= "keywords")
	WebElement quickFind;
	
	@FindBy(xpath = "//select[@name='manufacturers_id']")
	WebElement manufactDropdown;
	
	@FindBy(xpath = "//form/a")
	WebElement advaceSearchLink;
	
	
	public HomePage()
	{
		url = ConfigProperties.getProperty("STORE_URL");
		System.out.println("URL is " + url);
		PageFactory.initElements(Browser.driver(), this);
	}
	
	@Override
    public void load() {
        Browser.open(url);
	}
	
	@Override
    public void isLoaded() {
        WaitTool.waitForTitle(title);
		assertEquals(title, Browser.driver().getTitle());
		//assertTrue(Browser.driver().getTitle().equals(title));
    }	
	
	public void close() {
        Browser.close();
    }
	
	public EthnicStoreSignIn goToSignInPage()
	{
		WaitTool.waitForElementPresent(myAccountBtn, 30);
		myAccountBtn.click();
		EthnicStoreSignIn signIn = new EthnicStoreSignIn();
		return signIn;
	}
	
	public ShoppingCartPage goToCartPage()
	{
		WaitTool.waitForElementPresent(cartContentBtn, 30);
		cartContentBtn.click();
		
		ShoppingCartPage cart = new ShoppingCartPage();
		return cart;
	}
	
	public boolean isUserLoggedIn()
	{			
	 	if ( WaitTool.waitForElementPresent(logOutBtn, 5))
	 	{		 	
	 		return true;
	 	}
	 	else
	 	{		 		
	 		return false;
	 	}
	}
	
	public logOffPage doSignOut()
	 {
		 if (isUserLoggedIn())
		 {
			logOutBtn.click();
			logOffPage logOff = new logOffPage();
			return logOff;
		 }
		 else
		 {
			 System.out.println("Nobody is singed in so cannot signOut");
			 return null;
		 }
	 }
	
	public ProductList goToProductPageUsingUrl(String ProdCategory, String categID, String productID) {
		//String currentUrl;
		//currentUrl = Browser.driver().getCurrentUrl();
		String constructedUrl = url + "product_info.php?cPath=" + categID + "&products_id=" + productID;
		System.out.println(constructedUrl);
		Browser.driver().get(constructedUrl); 		
		ProductList productList = new ProductList(ProdCategory);
		return productList;
	 }
	
	
	public ProductSearchPage searchUsingQuickFind(String prodcutName)
	{
		WaitTool.waitForElementPresent(quickFind, 30);
		quickFind.sendKeys(prodcutName + Keys.ENTER);
		ProductSearchPage searchList = new ProductSearchPage();
		return searchList;
	}
	
	public ProductSearchPage searchUsingManufacturer(String manufacturerName)
	{
		WaitTool.waitForElementPresent(manufactDropdown, 15);
		Select selectOption = new Select(manufactDropdown);
		selectOption.selectByVisibleText(manufacturerName);
		
		ProductSearchPage searchList = new ProductSearchPage();
		return searchList;
	}
	
	public AdvanceSearchPage advanceSearchOption()
	{
		WaitTool.waitForElementPresent(advaceSearchLink, 15);
		advaceSearchLink.click();
		
		AdvanceSearchPage search = new AdvanceSearchPage();
		return search;
	}
	
	
}
