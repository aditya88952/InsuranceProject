package com_hackethon_utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelDataFile {
	
	public String path;
	public FileInputStream fis = null;
	public FileOutputStream fileOut = null;
	private XSSFWorkbook workbook = null;
	private XSSFSheet sheet = null;
	private XSSFRow row = null;
	private XSSFCell cell = null;

	public ReadExcelDataFile(String path){
		this.path=path;
		try{
			fis=new FileInputStream(path);
			workbook=new XSSFWorkbook(fis);
			//sheet=workbook.getSheetAt(0);
			//fis.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public String getCellData(String sheetName,int colNum,int rowNum){
		String gg=null;
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rowNum-1);
		if(row==null){
			return "";
		}
		cell = row.getCell(colNum);
			if (cell == null){
				return "";
			}
		/*	switch(cell.getCellType()){
			case STRING:
				gg=cell.getStringCellValue();
				break;
			case NUMERIC:
				gg=String.valueOf(cell.getNumericCellValue());				 
				break;
			}*/
			gg=cell.toString().trim();
		
			return gg;
		}
	

}
