package input;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader implements DocumentReader {
	
	private File file;
	private FileInputStream fis;
	private ArrayList<String> contents=  new ArrayList<>();
	
	public ExcelReader()
	{
		
	}
	// Here is the excel reading algorithm. It returns the contents of an excel file separated with a \t 
	// for displaying purposes
	@Override
	public List<String> read() {
		 
	    try {
	        XSSFWorkbook book = new XSSFWorkbook(fis);
	        int numberOfSheets = book.getNumberOfSheets();
	        int i = 0;
	        while(i < numberOfSheets) {
	        	XSSFSheet sheet = book.getSheetAt(i);
		        Iterator<Row> itr = sheet.iterator();
		        // Iterating over Excel file in Java
		        while (itr.hasNext()) {
		        	String s = "";
		            Row row = itr.next();
		            
		            Iterator<Cell> cellIterator = row.cellIterator();
		            
		            while (cellIterator.hasNext()) {
		                Cell cell = cellIterator.next();
		                
		                if(s.equals(""))
		                	s = cell.toString();
		                else
		                	s = s + "\t" + cell.toString();
		                
		            }
		            contents.add(s);
		        }
	        	i++;
	        }
	        
	        book.close();
	        fis.close();
	
	    } catch (FileNotFoundException fe) {
	        fe.printStackTrace();
	    } catch (IOException ie) {
	        ie.printStackTrace();
	    }
	    
		return contents;
	}
	
	@Override
	public void setFile(File file) throws FileNotFoundException {
		this.file = file;
		this.fis = new FileInputStream(file.getAbsolutePath());
	}
}
	    
	    
