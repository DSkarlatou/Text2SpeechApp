package output;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TextWriter implements DocumentWriter {
	private File file;
	private FileOutputStream fos;
	private ArrayList<String> contents = new ArrayList<String>();

	// Here we save a plain text file	
	@Override
	public void write() {
		FileWriter writer = null;
		try {
			writer = new FileWriter(file.getAbsolutePath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(String i : contents)
		{
			try {
				writer.write(i);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
