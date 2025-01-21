package command;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.compress.utils.FileNameUtils;

import model.Document;
import model.TTSFacade;
import view.Test2SpeechEditorView;

/* handles the simple  save operation, calls the appropriate methods to write to a file*/

public class SaveDocument implements ActionListener{
	
	private static String path;
	private static String format;
	private static Document document = new Document();
	private static ArrayList<String> contents = new ArrayList<>();
	private ReplayManager replayManager;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		StackTraceElement ste = Thread.currentThread().getStackTrace()[2];
        String callerClass = ste.getClassName();
		if(e != null || callerClass.equals("view.Test2SpeechEditorView"))
		{
			this.path = Test2SpeechEditorView.getPath();
			if(this.path == null) 	// this is in case the user doesn't want to choose a file after pressing Open
			{
				Test2SpeechEditorView.errorDialog("There is no path to save to, please use the menu item <save as> in this situation");
				return;			  			// and clicks X
			}
			this.format = FileNameUtils.getExtension(path);
					
			String s = Test2SpeechEditorView.getTxtpn().getText();							//gets text from textpane
			contents = new ArrayList<>(Arrays.asList(s.split("\n")));	//turn string to arraylist, split it at \n 
			document.setContents(contents);
			try {
				document.save(path, format, "none");
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			
			Test2SpeechEditorView.setSavedStatus(true);
		}
		else
		{
			document.setContents(document.getContents());
			try {
				document.save(path, format, "none");
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	// setters getters
	public void setReplayManager(ReplayManager replayManager)
	{
		this.replayManager = replayManager;
	}
	public void setDocument(Document document)
	{
		this.document = document;
	}
	
	public void setPathAndDecryption(String path, String format, String encryption)
	{
		this.path = path;	  
		this.format = format;
	}
	
	public Document getDocument()
	{
		return document;
	}
	

}
