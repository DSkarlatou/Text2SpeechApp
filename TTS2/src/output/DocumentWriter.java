package output;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public interface DocumentWriter {
	
	void write();
	
	int setFile(File file) throws FileNotFoundException;
	
	void setContents(ArrayList<String> contents);

	ArrayList<String> getContents();	
}
