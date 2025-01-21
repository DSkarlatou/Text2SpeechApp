package testing;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestDocumentToSpeech.class, TestDocumentToSpeechHighlighted.class, TestOpenDocument.class,
		TestRecord.class, TestReplay.class, TestSaveAsDocument.class, TestSaveDocument.class, TestStopRecord.class,
		TestTTSFacade.class })
public class AllTests {

}