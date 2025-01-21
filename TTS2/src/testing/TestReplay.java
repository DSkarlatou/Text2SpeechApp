package testing;

import static org.junit.Assert.*;

import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import command.DocumentToSpeech;
import command.DocumentToSpeechHighlighted;
import command.Record;
import command.Replay;
import command.ReplayManager;
import command.StopRecord;
import model.Document;
import model.FakeTTSFacade;
import model.TTSFacade;
import view.Test2SpeechEditorView;

public class TestReplay {

	@Test
	public void testActionPerformed() {
		boolean start = false, end = false;
		ReplayManager replayManager = new ReplayManager();
		Replay replay = new Replay();
		Record record = new Record();
		StopRecord stopRecord = new StopRecord();
		Document document = new Document();
		FakeTTSFacade fakeTTSFacade = new FakeTTSFacade();
		DocumentToSpeech documentToSpeech = new DocumentToSpeech();
		DocumentToSpeechHighlighted documentToSpeechHighlighted = new DocumentToSpeechHighlighted();
		String expectedOut = "";
		String replayedString;
		
		try {
			document.open(".\\Files For Testing\\testsForRecord&StopRecord\\testRecordAndStopRecord.docx", "docx", "none");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		document.setAudioManager(fakeTTSFacade);
		documentToSpeech.setDocument(document);
		documentToSpeech.setReplayManager(replayManager);
		documentToSpeechHighlighted.setDocument(document);
		documentToSpeechHighlighted.setReplayManager(replayManager);
		
		replay.setDocument(document);
		replay.setReplayManager(replayManager);
		
		record.setReplayManager(replayManager);
		record.setDocument(document);
		
		stopRecord.setReplayManager(replayManager);
		stopRecord.setDocument(document);
		
		record.actionPerformed(null);
		start = replayManager.isActiveRecording();
		
		// lets create some commands for replay!
		documentToSpeech.actionPerformed(null);
		expectedOut = expectedOut + Arrays.toString(documentToSpeech.getContents().toArray()).replace("[", "").replace("]", "");
		
		documentToSpeechHighlighted.setHighlight("I,");
		expectedOut = expectedOut + " " + documentToSpeechHighlighted.getHighlight() + " ";
		documentToSpeechHighlighted.actionPerformed(null);
		
		documentToSpeechHighlighted.setHighlight("am,");
		expectedOut = expectedOut + documentToSpeechHighlighted.getHighlight()+ " ";
		documentToSpeechHighlighted.actionPerformed(null);
		
		documentToSpeechHighlighted.setHighlight("also,");
		expectedOut = expectedOut + documentToSpeechHighlighted.getHighlight()+ " ";
		documentToSpeechHighlighted.actionPerformed(null);
		
		documentToSpeechHighlighted.setHighlight("testing,");
		expectedOut = expectedOut + documentToSpeechHighlighted.getHighlight()+ " ";
		documentToSpeechHighlighted.actionPerformed(null);
		
		documentToSpeechHighlighted.setHighlight("the replay command now!");
		expectedOut = expectedOut + documentToSpeechHighlighted.getHighlight()+ " ";
		documentToSpeechHighlighted.actionPerformed(null);
		
		documentToSpeech.actionPerformed(null);
		expectedOut = expectedOut + Arrays.toString(documentToSpeech.getContents().toArray()).replace("[", "").replace("]", "");
		
		stopRecord.actionPerformed(null);
		end = replayManager.isActiveRecording();

		assertEquals(start, true);   // we have succesfully recorded
		assertEquals(end, false);    // we have succesfully ended the recording
		assertNotEquals(start,end);  // start and end are opposites
		
		// and now we  are going to test the replay command (we can only replay if the recording has stopped!)
		replay.actionPerformed(null);
		replayedString = replay.getReplayManager().getReplayString();
		replayedString = replayedString.stripLeading();

		assertEquals(expectedOut,replayedString);
	}
}
