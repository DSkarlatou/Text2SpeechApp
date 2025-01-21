package command;

/* factory pattern for the commands, creates commands and returns them, also sets some properties for them*/

import java.awt.event.ActionListener;

import model.Document;
import model.TTSFacade;

public class CommandsFactory{
	private TTSFacade audioManager = new TTSFacade();
	private  ReplayManager replayManager = new ReplayManager();
	private Document document = new Document();
	public ActionListener createCommand(String stringCommand)
	{
		document.setAudioManager(audioManager);
		switch(stringCommand) {
			case "open":
				OpenDocument open = new OpenDocument();
				open.setDocument(document);
				open.setReplayManager(replayManager);
				return open;			
			case "saveAs":
				SaveAsDocument saveAs =  new SaveAsDocument();
				saveAs.setDocument(document);
				saveAs.setReplayManager(replayManager);
				return saveAs;
			case "save":
				SaveDocument save =  new SaveDocument();
				save.setDocument(document);
				save.setReplayManager(replayManager);
				return save;
			case "play":
				DocumentToSpeech play =  new DocumentToSpeech();
				play.setDocument(document);
				play.setReplayManager(replayManager);
				return play;
			case "playHighlighted":
				DocumentToSpeechHighlighted playHighlighted =  new DocumentToSpeechHighlighted();
				playHighlighted.setDocument(document);
				playHighlighted.setReplayManager(replayManager);
				return playHighlighted;
			case "stopAudio":
				StopDocumentToSpeech stopAudio =  new StopDocumentToSpeech();
				stopAudio.setDocument(document);
				stopAudio.setReplayManager(replayManager);
				return stopAudio;
			case "record":
				Record record =  new Record();
				record.setDocument(document);
				record.setReplayManager(replayManager);
				return record;
			case "stopRecording":
				StopRecord stopRecord =  new StopRecord();
				stopRecord.setDocument(document);
				stopRecord.setReplayManager(replayManager);
				return stopRecord;
			case "replay":
				Replay replay =  new Replay();
				replay.setDocument(document);
				replay.setReplayManager(replayManager);
				return replay;
			case "help":
				Help help =  new Help();
				return help;
			default:
				throw new IllegalArgumentException("Command Not Found");
		}
	}
	public void setAudioManager(TTSFacade audioManager) {
		this.audioManager = audioManager;
	}
}