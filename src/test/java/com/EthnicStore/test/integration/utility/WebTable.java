package com.EthnicStore.test.integration.utility;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

import java.util.List;

public class WebTable {
	
	private WebElement _webTable;

	public WebTable(WebElement webTable) {
		set_webTable(webTable);
	}

	public WebElement get_webTable() {
		return _webTable;
	}

	public void set_webTable(WebElement _webTable) {
		this._webTable = _webTable;
	}

	public int getRowCount() {
	    List<WebElement> tableRows = _webTable.findElements(By.tagName("tr"));
	    return tableRows.size();
	}
	
	public int getColumnCountFromHeader() {
		List<WebElement> tableRows = _webTable.findElements(By.tagName("tr"));
		WebElement headerRow = tableRows.get(0);
		List<WebElement> tableCols = headerRow.findElements(By.tagName("th"));
		return tableCols.size();
	}
	
	public String getCellData(int rowIdx, int colIdx) {
		List<WebElement> tableRows = _webTable.findElements(By.tagName("tr"));
		WebElement currentRow = tableRows.get(rowIdx-1);
		List<WebElement> tableCols = currentRow.findElements(By.tagName("td"));
		WebElement cell = tableCols.get(colIdx-1);
		return cell.getText();		
	}
	
	public WebElement getCellEditor(int rowIdx, int colIdx, int editorIdx) throws NoSuchElementException {
		try {
			
			List<WebElement> tableRows = _webTable.findElements(By.tagName("tr"));
			WebElement currentRow = tableRows.get(rowIdx-1);
			List<WebElement> tableCols = currentRow.findElements(By.tagName("td"));
			WebElement cell = tableCols.get(colIdx-1);
			WebElement cellEditor = cell.findElements(By.tagName("input")).get(editorIdx);
			return cellEditor;
			
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException("Failed to get cell editor");
		}
	}
	public WebElement getElement(int rowIdx, int colIdx) throws NoSuchElementException {
		try {
			
			List<WebElement> tableRows = _webTable.findElements(By.tagName("tr"));
			WebElement currentRow = tableRows.get(rowIdx);
			List<WebElement> tableCols = currentRow.findElements(By.tagName("td"));
			WebElement cell = tableCols.get(colIdx);
			return cell;
			
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException("Failed to get cell editor");
		}
	}
	
	public WebElement searchElementInARowBasedOnColumnValue(int searchIncolIdx, String columnValue, int returnFromColIdx) throws NoSuchElementException {
		try {
			boolean valuePresentInTableColumn = false;
			List<WebElement> tableRows = _webTable.findElements(By.tagName("tr"));
			for (WebElement tableRow:tableRows)
			{
				List<WebElement> tableCols = tableRow.findElements(By.tagName("td"));
				if (tableCols.get(searchIncolIdx-1).getText().equals(columnValue))
				{
					valuePresentInTableColumn = true;
					return tableCols.get(returnFromColIdx);
					
				}
				
			}
			return null;
			
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException("The search value is not present in the table");
		}
	}

}