/**/
package com.EthnicStore.test.integration.storePageObjects;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.EthnicStore.test.integration.utility.Log;
import com.EthnicStore.test.integration.utility.WaitTool;

public class AdvanceSearchPage {
	@FindBy(xpath = "//div[@class='contentText']/div/span/a")
	WebElement searchHelpLink;
	
	@FindBy(id= "ui-id-1")
	WebElement helpHeader;
	
	String helpTitle = "Search Help";	
	public AdvanceSearchPage()
	{
		PageFactory.initElements(Browser.driver(), this);
	}
	
	public void verifyHelpTitle()
	{
		searchHelpLink.click();
		WaitTool.waitForElementPresent(helpHeader, 15);
		assertEquals(helpTitle, helpHeader.getText());
		Log.info("The title of Search Help window is verified!");
	}
		
}
