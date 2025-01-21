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
/* handles the save as operation, pops up a filechooser and calls the appropriate methods to write to a file*/

public class SaveAsDocument implements ActionListener{
	private String path;
	private String format;
	private String encryption;
	private Document document = new Document();
	private static ArrayList<String> contents = new ArrayList<>();
	private ReplayManager replayManager;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		StackTraceElement ste = Thread.currentThread().getStackTrace()[2];
        String callerClass = ste.getClassName();
		if(e != null || callerClass.equals("view.Test2SpeechEditorView"))
		{
			this.path = Test2SpeechEditorView.fileChooser("save");
			if(this.path == null) // this is in case the user doesn't want to choose a file after pressing SaveAs
				return;			  // and clicks X
			this.encryption = Test2SpeechEditorView.cryptionPopUp("encryption");
			this.format = FileNameUtils.getExtension(this.path);

			String s = Test2SpeechEditorView.getTxtpn().getText();							//gets text from textpane

			contents = new ArrayList<>(Arrays.asList(s.split("\n")));	//turn string to arraylist, split it at \n
			document.setContents(contents);
			
			try {
				document.save(this.path, this.format, this.encryption);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Test2SpeechEditorView.setSavedStatus(true);
		}
		
		else
		{
			document.setContents(document.getContents());
			try {
				document.save(this.path, this.format, this.encryption);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
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
	public ReplayManager getReplayManager()
	{
		return replayManager;
	}

	
	
	public Document getDocument()
	{
		return document;
	}

	public void setPathAndDecryption(String path, String format, String encryption)
	{
		this.path = path;	  
		this.format = format;
		this.encryption = encryption;
	}

}
