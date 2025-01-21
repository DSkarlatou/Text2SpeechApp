package command;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Document;
import view.Test2SpeechEditorView;

/* tells the replay manager to stop recording*/

public class StopDocumentToSpeech implements ActionListener{
	private Document document = new Document();
	private ReplayManager replayManager;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		document.stop();
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
