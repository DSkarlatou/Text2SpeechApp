package output;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WriterAtbashDecorator extends WriterDecorator {

	private File file;
	private FileOutputStream fos;
	private DocumentWriter documentWriter;
	private ArrayList<String> contents;

	public WriterAtbashDecorator(DocumentWriter documentWriter) {
		super(documentWriter);
		this.documentWriter = documentWriter;
	}

	//Encoding algorithm for atbash cipher
	public static String encode(String plaintext)
    {
        String ciphertext = "";
        
        for(char c : plaintext.toCharArray())
        {
        	if(Character.isLowerCase(c))
            {
        		ciphertext += (char) ('a' + ('z' - c));
            }
            else if(Character.isUpperCase(c))
            {
            	ciphertext += (char) ('A' + ('Z' - c));
            }
            else
            {
            	ciphertext += c;
            }
        }
        return ciphertext;
    }
	
	
	// here it reads the contents that the user wants to encode,
	//it calls the atbash encoding algorithm above and 
	// returns them encoded
	@Override
	public void write() {
		this.contents = documentWriter.getContents();
		
		ArrayList<String> Newlist= new ArrayList<String>();
		for(String s : contents) {
			Newlist.add(WriterAtbashDecorator.encode(s));
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
		return null;
	}

}
