package testing;

import static org.junit.Assert.*;

import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import org.junit.Test;

import command.ReplayManager;
import command.StopRecord;
import model.Document;
import command.Record;

public class TestStopRecord {

	@Test
	public void testActionPerformed() {
		ReplayManager replayManager = new ReplayManager();
		Record record = new Record();
		StopRecord stopRecord = new StopRecord();
		Document document = new Document();
		boolean start = false, end = false;
		
		try {
			document.open(".\\Files For Testing\\testsForRecord&StopRecord\\testRecordAndStopRecord.docx", "docx", "none");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		record.setReplayManager(replayManager);
		record.setDocument(document);
		stopRecord.setReplayManager(replayManager);
		stopRecord.setDocument(document);
		//start recording
		((ActionListener) record).actionPerformed(null);
		start = replayManager.isActiveRecording();
		//end recording
		((ActionListener) stopRecord).actionPerformed(null);
		end = replayManager.isActiveRecording();

		assertEquals(start, true);   // we have succesfully recorded
		assertEquals(end, false);    // we have succesfully ended the recording
		assertNotEquals(start,end);  // start and end are opposites
	}
}
