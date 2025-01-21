package command;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Document;

/* tells the replay manager to replay the recording it has*/

public class Replay implements ActionListener {
	private ReplayManager replayManager;
	private Document document = new Document();
	
	@Override
	public void actionPerformed(ActionEvent e) {
		replayManager.setAudioManager(getDocument().getAudioManager()); 
		replayManager.replay();
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
