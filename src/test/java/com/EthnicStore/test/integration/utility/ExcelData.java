package com.EthnicStore.test.integration.utility;

import java.io.File;
import jxl.*;


public class ExcelData {
	
	 public static String[][] getTableArray(String xlFilePath, String sheetName, String tableName){
	        String[][] tabArray=null;
	        try{
	            Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));
	            Sheet sheet = workbook.getSheet(sheetName);
	            Cell tableStart=sheet.findCell(tableName);
	            
	            int startRow,startCol, endRow, endCol,ci,cj;
	            
	            startRow=tableStart.getRow();
	            startCol=tableStart.getColumn();

	            Cell tableEnd= sheet.findCell(tableName, startCol+1,startRow+1, 100, 64000,  false);                

	            endRow=tableEnd.getRow();
	            endCol=tableEnd.getColumn();
	            
	            System.out.println("startRow="+startRow+", endRow="+endRow+", " +
	                    "startCol="+startCol+", endCol="+endCol);
	            tabArray=new String[endRow-startRow-1][endCol-startCol-1];
	            ci=0;

	            for (int i=startRow+1;i<endRow;i++,ci++){
	                cj=0;
	                for (int j=startCol+1;j<endCol;j++,cj++){
	                    tabArray[ci][cj]=sheet.getCell(j,i).getContents();
	                    System.out.println("Content is: "+sheet.getCell(j,i).getContents());
	                    System.out.print(tabArray[ci][cj] + " ");
	                }
	                System.out.println();
	            }
	        }
	        catch (Exception e)    {
	        	System.out.println(e.getMessage());
	        	System.out.println("error in getTableArray()");
	            
	        }

	        return(tabArray);
	    }

}
