package command;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.event.ChangeEvent;

import model.Document;
import view.Test2SpeechEditorView;

/*Converts contents of editor to speech*/

public class DocumentToSpeech implements ActionListener{
	private static Document document = new Document();
	private ReplayManager replayManager;
	private static ArrayList<String> contents = new ArrayList<String>();

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e != null)
		{
			contents.clear();
			contents.addAll(Arrays.asList(Test2SpeechEditorView.getTxtpn().getText().split("\n")));
			document.setContents(contents);
			document.playContents();
			
			DocumentToSpeech d  = new DocumentToSpeech();
			d.setContents(contents);
			replayManager.add(d);
		}
		else
		{
			document.playContents();
			
			DocumentToSpeech d  = new DocumentToSpeech();
			d.setContents(document.getContents());
			replayManager.add(d);
		}
		
		
	}
	
	// setters getters
	public void setContents(ArrayList<String> contents)
	{
		this.contents = contents;
	}
	
	public ArrayList<String> getContents()
	{
		return contents;
	}
	
	public void setReplayManager(ReplayManager replayManager)
	{
		this.replayManager = replayManager;
	}
	
	public ReplayManager getReplayManager()
	{
		return replayManager;
	}
	
	public void setDocument(Document document)
	{
		this.document = document;
	}
	
	public Document getDocument()
	{
		return document;
	}
	
}
