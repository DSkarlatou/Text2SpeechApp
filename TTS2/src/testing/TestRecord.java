package testing;

import static org.junit.Assert.*;

import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import org.junit.Test;

import command.Record;
import command.ReplayManager;
import command.StopRecord;
import model.Document;

public class TestRecord {

	@Test
	public void testActionPerformed() {
		ReplayManager replayManager = new ReplayManager();
		Record record = new Record();
		Document document = new Document();
		boolean start = false;
		
		try {
			document.open(".\\Files For Testing\\testsForRecord&StopRecord\\testRecordAndStopRecord.docx", "docx", "none");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		record.setReplayManager(replayManager);
		record.setDocument(document);

		//start recording
		((ActionListener) record).actionPerformed(null);
		start = replayManager.isActiveRecording(); //has the actionPerformed started recording? if true then yes

		assertEquals(start, true);   // we are succesfully recording
	}

}
