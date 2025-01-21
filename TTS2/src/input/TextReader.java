package input;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


public class TextReader implements DocumentReader {
	private File file;
	private FileInputStream fis;
	private ArrayList<String> contents=  new ArrayList<String>();
	
	// Here is  the text reading algorithm. it returns a list with contents
	@Override
	public List<String> read() {
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(fis, StandardCharsets.UTF_8));
            String line;
            while ((line = br.readLine()) != null) {
            	contents.add(line);
            } 
		}
		catch (FileNotFoundException fe) {
    			fe.printStackTrace();
		}
    	catch (IOException ie) {
    	 		ie.printStackTrace();
    	}
		try {
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return contents;
	}

	@Override
	public void setFile(File file) throws FileNotFoundException {
		this.file = file;
		this.fis = new FileInputStream(file.getAbsolutePath());
	}

}
