package testing;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.Test;

import command.DocumentToSpeech;
import command.ReplayManager;
import model.Document;
import model.FakeTTSFacade;

public class TestDocumentToSpeech {

	@Test
	public void testActionPerformed() {
		FakeTTSFacade fakeTTSFacade = new FakeTTSFacade();
		Document document = new Document();
		DocumentToSpeech documentToSpeech = new DocumentToSpeech();
		ReplayManager replayManager = new ReplayManager();
		String playedContents;
		String documentContents;
		ArrayList<String> contents = new ArrayList<String>();
		
		try {
			document.open(".\\Files For Testing\\testsForDocumentToSpeech\\testDocumentToSpeech.docx", "docx", "none");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		contents = document.getContents(); 

		document.setAudioManager(fakeTTSFacade);
		documentToSpeech.setReplayManager(replayManager);
		documentToSpeech.setDocument(document);
		documentToSpeech.setContents(contents);
		documentToSpeech.actionPerformed(null);
		
		playedContents = fakeTTSFacade.getPlayedContents();
		documentContents = contents.toString();

		assertEquals(playedContents, documentContents);
	}
}
