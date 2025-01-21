package command;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;

import model.Document;
import view.Test2SpeechEditorView;

/* tells the replay manager to start recording */

public class Record implements ActionListener{
	private ImageIcon rec = new ImageIcon(getClass().getClassLoader().getResource("record.gif"));
	private ReplayManager replayManager;
	private Document document;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		replayManager.setAudioManager(getDocument().getAudioManager()); 
		replayManager.startRecording();
		
		if(e!=null)
		{
			((AbstractButton) e.getSource()).setIcon(rec);
		}
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