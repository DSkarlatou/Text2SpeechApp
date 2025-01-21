package testing;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import javax.swing.text.BadLocationException;
import org.junit.Test;
import command.DocumentToSpeechHighlighted;
import command.ReplayManager;
import model.Document;
import model.FakeTTSFacade;
import view.Test2SpeechEditorView;

public class TestDocumentToSpeechHighlighted {

	@Test
	public void testActionPerformed() {
		DocumentToSpeechHighlighted documentToSpeechHighlighted = new DocumentToSpeechHighlighted();
		ReplayManager replayManager = new ReplayManager();
		Document document = new Document();
		FakeTTSFacade fakeTTSFacade = new FakeTTSFacade();
		Test2SpeechEditorView text2SpeechEditorView = new Test2SpeechEditorView();
		String playedContents = "";
		String highlightedText = "";
		//text2SpeechEditorView.UIrunner();
		
		try {
			document.open(".\\Files For Testing\\testsForDocumentToSpeech\\testDocumentToSpeech.docx", "docx", "none");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			text2SpeechEditorView.insertInTextPane(document.getContents());
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		
		text2SpeechEditorView.getTxtpn().setSelectionStart(7); //select text from "column" 7
		text2SpeechEditorView.getTxtpn().setSelectionEnd(19);  //to "column" 19
		
		highlightedText = text2SpeechEditorView.getHighlightedText(); // = "I am a test." -> this is part of the opened .docx
		
		document.setAudioManager(fakeTTSFacade);
		documentToSpeechHighlighted.setDocument(document);
		documentToSpeechHighlighted.setReplayManager(replayManager);
		documentToSpeechHighlighted.setHighlight(highlightedText);
		documentToSpeechHighlighted.actionPerformed(null);
		playedContents = fakeTTSFacade.getPlayedContents();
		assertEquals(playedContents, "I am a test.");
	}
}
