package command;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;

import model.Document;
import view.Test2SpeechEditorView;

public class StopRecord implements ActionListener{
	private Image rec = new ImageIcon(this.getClass().getResource("/record.png")).getImage();
	private ReplayManager replayManager;
	private Document document;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		replayManager.setAudioManager(getDocument().getAudioManager()); 
		replayManager.endRecording();
		if(e!=null)
		{
			Test2SpeechEditorView.getRecordButton().setIcon(new ImageIcon(rec));;
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
