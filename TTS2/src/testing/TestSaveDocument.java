package testing;

import static org.junit.Assert.*;

import java.util.ArrayList;

import javax.swing.JTextPane;

import org.junit.After;
import org.junit.Test;

import command.OpenDocument;
import command.SaveDocument;
import model.Document;

public class TestSaveDocument {

	private ArrayList<String> contentsFromFile = new ArrayList<>();
	private ArrayList<String> customContents = new ArrayList<>();
	private ArrayList<String> contentsAfterBeingSaved = new ArrayList<>();
	@Test
	public void test1() {
		OpenDocument openDocument = new OpenDocument();
		SaveDocument saveDocument = new SaveDocument();
		Document document = new Document();
		String path = ".\\Files For Testing\\testsForSaveDocument\\saveDocxTest.docx";
		
		openDocument.setPathAndDecryption(path, "docx", "none"); //
		openDocument.setDocument(document);
		openDocument.actionPerformed(null); // OPEN
		
		contentsFromFile = openDocument.getDocument().getContents();
		customContents = contentsFromFile;
		customContents.add("\n 2 I am another line added by the test to check if the save command works fine!");
		
		saveDocument.setPathAndDecryption(path, "docx", "none");
		document.setContents(customContents);
		saveDocument.setDocument(document);
		saveDocument.actionPerformed(null); // SAVE
		
		Document documentAfter = new Document();
		openDocument.setPathAndDecryption(path, "docx", "none");
		openDocument.setDocument(documentAfter);
		openDocument.actionPerformed(null);
		contentsAfterBeingSaved = openDocument.getDocument().getContents();

		assertEquals(customContents,contentsAfterBeingSaved);
	}
	
	@After
	public void post1() {
		contentsFromFile.clear();
		customContents.clear();
		contentsAfterBeingSaved.clear();
	}
	
	@Test
	public void test2() {
		OpenDocument openDocument = new OpenDocument();
		SaveDocument saveDocument = new SaveDocument();
		Document document = new Document();
		String path = ".\\Files For Testing\\testsForSaveDocument\\saveXlsxTest.xlsx";
		
		openDocument.setPathAndDecryption(path, "xlsx", "none"); //
		openDocument.setDocument(document);
		openDocument.actionPerformed(null); // OPEN
		
		contentsFromFile = openDocument.getDocument().getContents();
		customContents = contentsFromFile;
		customContents.add("4	someone	else");
		customContents.add("5	someone	else	coming	from	test");
		customContents.add("4	tester	is	testing");
		
		saveDocument.setPathAndDecryption(path, "xlsx", "none");
		document.setContents(customContents);
		saveDocument.setDocument(document);
		saveDocument.actionPerformed(null); // SAVE
		
		Document documentAfter = new Document();
		openDocument.setPathAndDecryption(path, "xlsx", "none");
		openDocument.setDocument(documentAfter);
		openDocument.actionPerformed(null);
		contentsAfterBeingSaved = openDocument.getDocument().getContents();

		assertEquals(customContents,contentsAfterBeingSaved);
	}
	
	@After
	public void post2() {
		contentsFromFile.clear();
		customContents.clear();
		contentsAfterBeingSaved.clear();
	}
}
