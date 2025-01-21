package command;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import model.Document;
import view.Test2SpeechEditorView;

/* converts highlighted text to speech*/

public class DocumentToSpeechHighlighted implements ActionListener{
	private Document document = new Document();
	private String highlightedText = "";
	private ReplayManager replayManager;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e != null)
		{
			highlightedText = Test2SpeechEditorView.getHighlightedText();
		}
		
		document.playHighlighted(highlightedText);
		
		DocumentToSpeechHighlighted d = new DocumentToSpeechHighlighted();
		d.setHighlight(highlightedText);
		replayManager.add(d);
	}

	public void setHighlight(String highlightedText)
	{
		this.highlightedText = highlightedText;
	}
	
	public String getHighlight()
	{
		return highlightedText;
	}
	
	// setters getters
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
