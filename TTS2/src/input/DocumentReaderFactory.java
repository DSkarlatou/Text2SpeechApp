package input;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import view.Test2SpeechEditorView;

public class DocumentReaderFactory {
	private DocumentReader documentReader;

	private String[] acceptableFormats = {"docx","xlsx","txt"};
	private String[] acceptableEncryptions = {"rot13","atbash","none"};
	
	public DocumentReader createReader(String filepath, String format, String cryption) {
		//CHECK EXISTANCE OF FILEPATH and if FORMAT IS ACCEPTABLE
		File file = new File(filepath);

		if(!Arrays.asList(acceptableFormats).contains(format))
			return null;
		
		if(!Arrays.asList(acceptableEncryptions).contains(cryption)) 
		{
			Test2SpeechEditorView.errorDialog("Decryption not supported");
			return null;
		}
		// Chooses betwenn different formats. 
		//Very useful if you need to add more formats
		switch(format) {
		  case "docx":
			  documentReader = new WordReader();
		    break;
		  case "xlsx":
			  documentReader = new ExcelReader();
		    break;
		  case "txt":
			  documentReader = new TextReader();
			  break;
		  default:
			  throw new IllegalArgumentException();
		}
		
		try {
			documentReader.setFile(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		//Same thing with decoding algorithms
		switch(cryption) {
		  case "atbash":
			  documentReader = new ReaderAtbashDecorator(documentReader);
		    break;
		  case "rot13":
			  documentReader = new ReaderRot13Decorator(documentReader);
		    break;
		  case "none":
			  break;
		  default:
			  throw new IllegalArgumentException();
		}
		
		return documentReader;
	}
}
