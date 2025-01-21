package input;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReaderAtbashDecorator extends ReaderDecorator {

	private File file;
	private FileInputStream fis;
	private DocumentReader documentReader;
	
	public ReaderAtbashDecorator(DocumentReader documentReader) {
		super(documentReader);
		this.documentReader = documentReader;
	}
	
	// Atbash decoding algorithm
	public static String decode(String ciphertext)
    {
        String plaintext = "";
        for(char c : ciphertext.toCharArray())
        {
            if(Character.isLowerCase(c))
            {
                plaintext += (char) ('z' + ('a' - c));
            }
            else if(Character.isUpperCase(c))
            {
            	plaintext += (char) ('Z' + ('A' - c));
            }
            else
            {
                plaintext += c;
            }
        }
        return plaintext;
    }
	
   // Returns the decoded contents of a file that is about to be opened 
	@Override
	public  List<String> read() {
		List<String> list = documentReader.read();
		//for
		ArrayList<String> Newlist= new ArrayList<String>();
		for(String s : list) {
			Newlist.add(ReaderAtbashDecorator.decode(s));
		}
		
		return Newlist;
	}
	
	@Override
	public void setFile(File file) throws FileNotFoundException {
		this.file = file;
		this.fis = new FileInputStream(file.getAbsolutePath());
	}
}