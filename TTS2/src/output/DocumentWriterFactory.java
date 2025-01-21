package output;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import view.Test2SpeechEditorView;

public class DocumentWriterFactory {
	private DocumentWriter documentWriter;

	private String[] acceptableFormats = {"docx","xlsx","txt"};
	private String[] acceptableEncryptions = {"rot13","atbash","none"};

	private ArrayList<String> contents;
	//Checks the existance of the path
	public DocumentWriter createWriter(String filepath, String format, String cryption) {
		int setFileValue = 0;
		File file = new File(filepath);
		if(!Arrays.asList(acceptableFormats).contains(format))
			return null;

		if(!Arrays.asList(acceptableEncryptions).contains(cryption)) 
		{
			Test2SpeechEditorView.errorDialog("Encryption not supported");
			return null;
		}
		
		
		// chooses in which format to save it
		switch(format) {
		  case "docx":
			  documentWriter = new WordWriter();
			  break;
		  case "xlsx":
			  documentWriter = new ExcelWriter();
			  break;
		  case "txt":
			  documentWriter = new TextWriter();
			  break;
		  default:
			  throw new IllegalArgumentException();
		}
		documentWriter.setContents(contents);
		
		
		try {
			setFileValue = documentWriter.setFile(file);
			if(setFileValue == -1) //could not open file!
				return null;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		documentWriter.setContents(contents);
		
		//chooses how to encode them
		switch(cryption) {
		  case "atbash":
			  documentWriter = new WriterAtbashDecorator(documentWriter);
		    break;
		  case "rot13":
			  documentWriter = new WriterRot13Decorator(documentWriter);
		    break;
		  case "none":
			  break;
		  default:
			  throw new IllegalArgumentException();
		}
		return documentWriter;
	}

	public void setContents(ArrayList<String> contents) {
		this.contents= contents;
	}
}
