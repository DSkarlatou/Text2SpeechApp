package output;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class WordWriter implements DocumentWriter{

	private File file;
	private FileOutputStream fos;
	private ArrayList<String> contents;

	public WordWriter()
	{
		
	}
	//Here we properly write the contents of a text as a .docx format
	@Override
	public void write() {
        try (XWPFDocument doc = new XWPFDocument()) {

        	for(String i : contents)
        	{
        		XWPFParagraph p = doc.createParagraph();
                p.setAlignment(ParagraphAlignment.BOTH);
                
                XWPFRun r = p.createRun();
                r.setFontSize(12);
                r.setText(i);
        	}
        	
            doc.write(fos);
            fos.close();
            doc.close();
        }catch (FileNotFoundException fe) 
        {
    		fe.printStackTrace();
    	}
         catch (IOException e1) {
			e1.printStackTrace();
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
