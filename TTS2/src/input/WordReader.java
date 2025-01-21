package input;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

public class WordReader implements DocumentReader{
	
	private File file;
	private FileInputStream fis;
	private ArrayList<String> contents=  new ArrayList<String>();
		
	// Here is the word reading algorithm
	@Override
	public  List<String> read(){
			
		try {     
	        XWPFDocument document = new XWPFDocument(fis);
	
	        List<XWPFParagraph> paragraphs = document.getParagraphs();
		        
	        for (XWPFParagraph para : paragraphs) {
	        	contents.add(para.getText());
	        }
	        fis.close();
	        document.close();
		}
		catch (FileNotFoundException fe) {
			fe.printStackTrace();
		}
	 	catch (IOException ie) {
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
     

