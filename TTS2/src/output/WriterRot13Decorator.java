package output;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WriterRot13Decorator extends WriterDecorator {

	private ArrayList<String> contents;
	private DocumentWriter documentWriter;
	private File file;
	private FileOutputStream fos;
	
	public WriterRot13Decorator(DocumentWriter documentWriter) {
		super(documentWriter);
		this.documentWriter = documentWriter;
	}

	//Encoding algorithm for rot13 cipher
	public static String encode(String plaintext)
	{
		char[] values = plaintext.toCharArray();
		 for (int i = 0; i < values.length; i++) {
			 char letter = values[i];
	            if (letter >= 'a' && letter <= 'z') 
	            {
	                if (letter > 'm') 
	                    letter -= 13;
	                else 
	                    letter += 13;
	            } 
	            else if (letter >= 'A' && letter <= 'Z') 
	            {
	                if (letter > 'M')
	                    letter -= 13;
	                else 
	                    letter += 13;
	            }
	            values[i] = letter;
	        }
	        return new String(values);
	}
	
	
	// here it reads the contents that the user wants to encode,
	//it calls the rot13 encoding algorithm above and 
	// returns them encoded
	@Override
	public void write() {
		this.contents = documentWriter.getContents();
		
		ArrayList<String> Newlist= new ArrayList<String>();
		for(String s : contents) {
			Newlist.add(WriterRot13Decorator.encode(s));
		}
		
		documentWriter.setContents(Newlist);
		documentWriter.write();		
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

	@Override
	public void setContents(ArrayList<String> contents) {
		this.contents = contents;
	}

	@Override
	public ArrayList<String> getContents() {
		// TODO Auto-generated method stub
		return null;
	}
}
