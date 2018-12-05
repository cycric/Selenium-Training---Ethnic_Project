package com.EthnicStore.test.integration.tests;

import org.testng.annotations.Test;

import com.EthnicStore.test.integration.model.NewRegistrationDetails;
import com.EthnicStore.test.integration.storePageObjects.Browser;
import com.EthnicStore.test.integration.storePageObjects.CheckoutOptionPage;
import com.EthnicStore.test.integration.storePageObjects.EthnicStoreSignIn;
import com.EthnicStore.test.integration.storePageObjects.HomePage;
import com.EthnicStore.test.integration.storePageObjects.NormalCheckoutPage;
import com.EthnicStore.test.integration.storePageObjects.PaypalCheckoutMethod;
import com.EthnicStore.test.integration.storePageObjects.RegistrationFormPage;
import com.EthnicStore.test.integration.storePageObjects.ShoppingCartPage;
import com.EthnicStore.test.integration.storePageObjects.logOffPage;
import com.EthnicStore.test.integration.utility.ConfigProperties;
import com.EthnicStore.test.integration.utility.ExcelData;
import com.EthnicStore.test.integration.utility.Log;
import com.EthnicStore.test.integration.utility.SpreadsheetData;

import com.EthnicStore.test.integration.storePageObjects.ProductList;
import com.EthnicStore.test.integration.storePageObjects.ProductSearchPage;

import org.testng.annotations.Parameters;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

public class TestUsingSingleProduct2 {
	HomePage homepage;
	EthnicStoreSignIn signIn;
	SpreadsheetData sheetData;
	CheckoutOptionPage checkoutOpt;
	NormalCheckoutPage normalCheckOut;
	
	Logger Test = Logger.getLogger(TestUsingSingleProduct2.class.getName());

	@Test(groups = "Register and login")
	public void login() {
		if (!homepage.isUserLoggedIn()) 
		{
			signIn = homepage.goToSignInPage();
			Test.info("Loading Sign In Page");
			signIn.userSignIn(ConfigProperties.getProperty("LOGIN_USER_NAME"), ConfigProperties.getProperty("LOGIN_PASSWORD"));
			Test.info("Signed In successfully!");	
		}else {
			Test.debug("User already sign-in so no attempt was made to resign-in");
		}
	}
	
	
	@Test(dependsOnMethods = "shareProductOptionsTest")
	public void logOffTest()
	{
		logOffPage logOff = homepage.doSignOut();
		logOff.verifyLogOffMessage();
	}
	
	
	//@Test(groups = "Register and login", priority=1)
	public void register() {
		homepage.goToSignInPage();
		Test.info("Loading Sign In Page");
		RegistrationFormPage register = signIn.doRegistration();
		Test.info("Filling registration form for creating new user account!");
		NewRegistrationDetails newRegistrationInfo = new NewRegistrationDetails();
		newRegistrationInfo.setFirstName(ConfigProperties.getProperty("REG_FIRST_NAME"));
		newRegistrationInfo.setLastName(ConfigProperties.getProperty("REG_LAST_NAME"));
		newRegistrationInfo.setEmail(ConfigProperties.getProperty("REG_EMAIL_ID"));
		newRegistrationInfo.setGender(ConfigProperties.getProperty("REG_GENDER"));
		newRegistrationInfo.setDOB(ConfigProperties.getProperty("REG_DOB"));
		newRegistrationInfo.setCity(ConfigProperties.getProperty("REG_CITY"));
		newRegistrationInfo.setState(ConfigProperties.getProperty("REG_STATE"));
		newRegistrationInfo.setCountry(ConfigProperties.getProperty("REG_COUNTRY"));
		newRegistrationInfo.setStreetLine1(ConfigProperties.getProperty("REG_STREET_LINE1"));
		newRegistrationInfo.setZipcode(ConfigProperties.getProperty("REG_ZIP"));
		newRegistrationInfo.setPhone(ConfigProperties.getProperty("REG_PHONE"));
		newRegistrationInfo.setPassword(ConfigProperties.getProperty("REG_PASSWORD"));
		newRegistrationInfo.setRePassword(ConfigProperties.getProperty("REG_REPASSWORD"));
		register.registerUser(newRegistrationInfo);

		if (Browser.driver().getCurrentUrl().contains("account_success")) {
			System.out.println("Account has been created successfully!");
		} else {
			//fail("Account is not created. Check your provided details!");
		}

	}

	
	@Parameters("browser")
	@BeforeClass(alwaysRun = true)
	public void setUp(String browser) throws InvalidFormatException, IOException {
		System.out.println("The Test will run on browser -" + browser);
		DOMConfigurator.configure("log4j.xml");
		Browser.setBrowser(browser);
		Test.info("Browser value set");
		ConfigProperties.loadProperties();
		homepage = new HomePage();
		homepage.load();
		Test.info("Homepage is loaded!");
		homepage.isLoaded();
		Test.info("Test into HomePage");

		signIn = new EthnicStoreSignIn();
	}

	@DataProvider(name = "DP1")
    public Object[][] createData1() {
       String file = ConfigProperties.getProperty("DATA_PATH")+ConfigProperties.getProperty("DATA_FILE");
		System.out.println(file);
       Object[][] retObjArr=ExcelData.getTableArray(file,
        		ConfigProperties.getProperty("DATA_SHEET"), ConfigProperties.getProperty("DATA_START_N_END_POINT"));	
        
        return(retObjArr);
    }
	
	
	@Test(dataProvider = "DP1", groups = "ShareProduct", dependsOnMethods="login")
	public void shareProductOptionsTest(String categoryID, String ProductCategory, String subCategoryID,
				String subCategoryName, String productID, String paymentMethod, String shipmentMethod, String moreFlag)
		{
			homepage.get();
			ProductList productList = homepage.goToProductPageUsingUrl(ProductCategory, categoryID, productID);
			if(productList != null){
				//productList.shareProductOnGPlus(ConfigProperties.getProperty("GP_USER"), ConfigProperties.getProperty("GP_PASS"));
				productList.shareProductOnGPlus(ConfigProperties.getProperty("GP_USER"), ConfigProperties.getProperty("GP_PASS"));
			}
		}
	
	@Test(dataProvider = "DP1", groups = "ShareProduct", dependsOnMethods="login")
	public void shareProductOptionTest(String categoryID, String ProductCategory, String subCategoryID,
				String subCategoryName, String productID, String paymentMethod, String shipmentMethod, String moreFlag)
		{
			homepage.get();
			ProductList productList = homepage.goToProductPageUsingUrl(ProductCategory, categoryID, productID);
			if(productList != null){
				//productList.shareProductOnGPlus(ConfigProperties.getProperty("GP_USER"), ConfigProperties.getProperty("GP_PASS"));
				productList.shareProductOnPinIt(ConfigProperties.getProperty("GP_USER"), ConfigProperties.getProperty("GP_PASS"));
			}
		}
	
	@Test(dataProvider = "DP1", groups = "ShareProduct", dependsOnMethods="login")
	public void shareOnFbTest(String categoryID, String ProductCategory, String subCategoryID,
				String subCategoryName, String productID, String paymentMethod, String shipmentMethod, String moreFlag)
		{
			homepage.get();
			ProductList productList = homepage.goToProductPageUsingUrl(ProductCategory, categoryID, productID);
			if(productList != null){
				//productList.shareProductOnGPlus(ConfigProperties.getProperty("GP_USER"), ConfigProperties.getProperty("GP_PASS"));
				productList.shareProductOnFb(ConfigProperties.getProperty("GP_USER"), ConfigProperties.getProperty("GP_PASS"));
			}
		}

	
	@Test(dataProvider = "DP1", groups = "PlaceOrder", dependsOnGroups = "Register and login")
	public void checkOutUsingUrls(String categoryID, String ProductCategory, String subCategoryID,String subCategoryName, 
								  String productID, String paymentMethod, String shipmentMethod, String moreFlag) throws IOException, InterruptedException 
	{	
		//homepage.get();
		ProductList productList = homepage.goToProductPageUsingUrl(ProductCategory, categoryID, productID);	
		if (productList != null) {
			placeOrder(productList, productID, paymentMethod, moreFlag);
		} else {
			fail("Could not go to required product List. Menu or submenu not available or not visible");
		}
	}
	
	

	public void placeOrder(ProductList productList, String productID, String paymnetMethod, String moreFlag) 
	{
				Log.info("PlaceOrder() is called"); 
				ShoppingCartPage myCart;
				if((myCart= productList.clickAddToCartButton(productID)) != null )
				{
					System.out.println("Add to cart is successfull");
				}
				else 
		        {
		        	System.out.println("Add To Cart Is Not Successful");
		        	assertTrue(false,"Add To Cart Failed");        	
		        }
				if(!moreFlag.contains("Y"))
				{
				Log.info("Cart page is opened!");	
				myCart.updateProductQty("1");
			
				System.out.println("Total number of products in the cart: "+myCart.productNoInCart());
				
				if(paymnetMethod.contains("Normal"))
				{	
					System.out.println("Payment method is:"+paymnetMethod);
					normalCheckOut = myCart.usingNormalCheckoutMethod();
					normalCheckOut.confirmOrderUsingCOD();
				}
				else{
					PaypalCheckoutMethod paypal = myCart.usingPaypalCheckoutMethod();
				}
				}
		}

	
	@Test(groups = "productTest", dependsOnGroups = "Register and login")
	public void quickSearchMethodTest() {
		ProductSearchPage searchList = homepage.searchUsingQuickFind(ConfigProperties.getProperty("QUICK_SEARCH_KEYWORD"));
		searchList.productListSearchCount();
		searchList.verifyProductListCount();
	}

	
	@Test(groups = "productTest", dependsOnGroups = "Register and login")
	public void selectManufaturerMethod() {
		ProductSearchPage searchList = homepage.searchUsingManufacturer(ConfigProperties.getProperty("MANUFACTURER_NAME"));
		searchList.productListSearchCount();
		searchList.verifyProductListCount();
	}

	
	@Test(groups = "productTest", dependsOnGroups="Register and login")
	public void productReviewTest()
	{
		ProductSearchPage searchList = homepage.searchUsingManufacturer(ConfigProperties.getProperty("MANUFACTURER_NAME"));
		searchList.writeProductReview(ConfigProperties.getProperty("PR_LESS_THAN_50_CHAR_MSG"), ConfigProperties.getProperty("PR_RATING_VALUE"));	
		
		homepage.searchUsingManufacturer(ConfigProperties.getProperty("MANUFACTURER_NAME"));
		searchList.writeProductReview(ConfigProperties.getProperty("PR_MORE_THAN_50_CHAR_MSG"), ConfigProperties.getProperty("PR_RATING_VALUE"));
	}


	@AfterTest
	public void afterTest() {
		
	}
	

}
