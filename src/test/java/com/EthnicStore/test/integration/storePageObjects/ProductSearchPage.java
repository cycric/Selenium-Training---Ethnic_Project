package com.EthnicStore.test.integration.storePageObjects;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import com.EthnicStore.test.integration.utility.Log;
import com.EthnicStore.test.integration.utility.WaitTool;
import com.EthnicStore.test.integration.utility.WebTable;

public class ProductSearchPage extends LoadableComponent<ProductSearchPage> {
	@FindBy(xpath = "//table[@class='productListingData']")
	WebElement searchListTable;

	@FindBy(xpath = "//table[@class='productListingData']/tbody/tr[1]/td[1]")
	WebElement firstProduct;

	@FindBy(xpath = ".//div/div[1]/div[2]/span[2]/strong[3]")
	WebElement countNumber;

	@FindBy(how=How.LINK_TEXT, using="Write a review on this product!")
	WebElement reviewLink;

	@FindBy(xpath = "//td[@class='fieldValue']/textarea")
	WebElement inputTextField;

	@FindBy(xpath = "//td[@class='fieldValue']/input")
	List<WebElement> ratingRadioBtns;

	@FindBy(id = "tdb6")
	WebElement submitReviewButton;

	@FindBy(xpath = "//td[@class='messageStackSuccess']")
	WebElement successMsg;

	WebTable webtable;

	public ProductSearchPage() {
		PageFactory.initElements(Browser.driver(), this);
		webtable = new WebTable(searchListTable);
	}
	
	public int productListSearchCount() {
		try {
			WaitTool.waitForElementPresent(searchListTable, 30);
		} catch (Exception e) {
			System.out.println("WebTable is not found on the page.");
		}
		return webtable.getRowCount();
	}

	public void verifyProductListCount() {
		WaitTool.waitForElementPresent(countNumber, 15);
		int totalRows = webtable.getRowCount();

		String value = countNumber.getText();
		int noOfProducts = Integer.parseInt(value);

		System.out.println("Total no. of rows: " + totalRows);
		System.out.println("Total products in search list:" + noOfProducts);
		assertEquals(totalRows, noOfProducts);
	}

	public void writeProductReview(String reviewCotent, String ratingValue) {
		WaitTool.waitForElementPresent(firstProduct, 15);
		firstProduct.click();
		Log.info("Clicked on 1st product from the search list.");

		WaitTool.waitForElementPresent(reviewLink, 30);
		reviewLink.click();
		Log.info("Clicked on product review link.");

		WaitTool.waitForElementPresent(inputTextField, 15);
		inputTextField.sendKeys(reviewCotent);
		Log.info("Entered review message in text area.");

		for (WebElement selectRating : ratingRadioBtns) {
			if (selectRating.getAttribute("value").equals(ratingValue)) {
				selectRating.click();
				Log.info("Selected review rating radio button.");
				break;
			} 
		}

		WaitTool.waitForElementPresent(submitReviewButton, 15);
		submitReviewButton.click();
		Log.info("Clicked on Submit button to submit entered review.");

		try {
			Log.info("Alert window is opened.");
			Alert reviewAlert = Browser.driver().switchTo().alert();
			String errorMessage = "must have at least 50 char";
			assertTrue(reviewAlert.getText().contains(errorMessage), "Message on alert has been verified!");
			reviewAlert.accept();
		} catch (NoAlertPresentException e) {
			System.out.println("No alert is present. You have entered more than 50 Characters in review successfully!");
			Assert.assertTrue(successMsg.getText().contains("Thank you for your review"),
					"Submit review success message has been verified!");
		}
	}

	@Override
	protected void load(){
	}

	@Override
	protected void isLoaded() throws Error {
	}

}
