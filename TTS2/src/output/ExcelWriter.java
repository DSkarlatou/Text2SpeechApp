package output;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import model.Document;

public class ExcelWriter implements DocumentWriter{

	private File file;
	private FileOutputStream fos;
	private ArrayList<String> contents;
	
	//here we save text into an excel file
	//It's very important to add \t in order to be saved properly
	@Override
	public void write() {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Sheet1");
		
		int rowNum = 0;
		for(String i : contents)
		{
			String s[] = i.split("\t");
			Row row = sheet.createRow(rowNum++);
			
			int cellNum = 0;
			for(String j : s)
			{	
				Cell cell = row.createCell(cellNum++);
				cell.setCellValue(j);
			}
		}
		try
		{
			fos = new FileOutputStream(file);
			workbook.write(fos);
			fos.close();
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				workbook.close();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void setContents(ArrayList<String> contents)
	{
		this.contents = contents;
	}
	
	public ArrayList<String> getContents()
	{
		return this.contents;
	}
	
	@Override
	public int setFile(File file){
		this.file = file;
		try {
			this.fos = new FileOutputStream(file.getAbsolutePath());
			return 1;
		} catch (FileNotFoundException e) {
			return -1;
			
		}
	}
}
