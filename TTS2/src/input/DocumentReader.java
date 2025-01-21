package input;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public interface DocumentReader {
	
	List<String> read();
	
	void setFile(File file) throws FileNotFoundException;
	
}
