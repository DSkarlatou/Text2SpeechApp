package input;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReaderRot13Decorator extends ReaderDecorator{
	
	private File file;
	private FileInputStream fis;
	private DocumentReader documentReader;
	
	public ReaderRot13Decorator(DocumentReader documentReader) {
		super(documentReader);
		this.documentReader = documentReader;
	}

	//Here is the Rot13 decoding algorithm
	public static String decode(String ciphertext) {
		 char[] values = ciphertext.toCharArray();
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

	@Override
	public void setFile(File file) throws FileNotFoundException {
		this.file = file;
		this.fis = new FileInputStream(file.getAbsolutePath());
	}

	//After the above algorithm converts the contents of the file form rot13 to normal 
	//text it returns it back 
	
	@Override
	public List<String> read() {
		List<String> list = documentReader.read();
		
		ArrayList<String> Newlist= new ArrayList<String>();
		for(String s : list) {
			Newlist.add(ReaderRot13Decorator.decode(s));
		}
		
		return Newlist;
	}
	
}
