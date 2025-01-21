package command;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.event.ChangeEvent;
import javax.swing.text.BadLocationException;

import org.apache.commons.compress.utils.FileNameUtils;

import model.Document;
import model.TTSFacade;
import view.Test2SpeechEditorView;

/* calls appropriate methods to open and display a file */

public class OpenDocument  implements ActionListener{
	private static String path;
	private Document document = new Document();
	private ReplayManager replayManager;
	private static String decryption;
	private static String format;
	private static ArrayList<String> contents = new ArrayList<>();
	
	@Override
	public void actionPerformed(ActionEvent e) {

		
		if(e != null)
		{
			this.path = Test2SpeechEditorView.fileChooser("open");
			if(this.path == null) 
				return;			  
			this.format = FileNameUtils.getExtension(this.path);
			this.decryption = Test2SpeechEditorView.cryptionPopUp("decryption");
			try {
				document.open(this.path, this.format, this.decryption);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			
			contents = document.getContents();
			
			try {
				Test2SpeechEditorView.insertInTextPane(contents);
			} catch (BadLocationException e1) {
				e1.printStackTrace();
			}	
		}
		
		else
		{
			Test2SpeechEditorView test2SpeechEditorView = new Test2SpeechEditorView();
			try {
				document.open(this.path, this.format, this.decryption);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			
			contents = document.getContents();
			
			try {
				test2SpeechEditorView.insertInTextPane(contents);
			} catch (BadLocationException e1) {
				e1.printStackTrace();
			}	
		}
		
		
	}
	
	public void setPathAndDecryption(String path, String format,String decryption)
	{
		this.path = path;	  
		this.format = format;
		this.decryption = decryption;
	}
	
	public Document getDocument() {
		return document;
	}

	public ArrayList<String> getContents()
	{
		return contents;
	}

	public void setReplayManager(ReplayManager replayManager)
	{
		this.replayManager = replayManager;
	}
	public void setDocument(Document document)
	{
		this.document = document;
	}

	
}
