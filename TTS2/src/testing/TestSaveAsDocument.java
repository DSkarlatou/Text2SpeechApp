package testing;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Test;

import command.OpenDocument;
import command.SaveAsDocument;
import model.Document;

public class TestSaveAsDocument {
	private ArrayList<String> contentsFromFile = new ArrayList<>();
	private ArrayList<String> customContents = new ArrayList<>();
	private ArrayList<String> contentsAfterBeingSaved = new ArrayList<>();
	private int count = 0;
	@Test //testing docx with none encoding
	public void test1() {
		count++;
		OpenDocument openDocument = new OpenDocument();
		SaveAsDocument saveAsDocument = new SaveAsDocument();
		Document document = new Document();
		String path = ".\\Files For Testing\\testsForSaveDocument\\saveDocxTest.docx";
		String pathToSaveTo = ".\\Files For Testing\\testsForSaveDocument\\saveDocxTestNone"+ Integer.toString(count) + ".docx";
		openDocument.setPathAndDecryption(path, "docx", "none"); //
		openDocument.setDocument(document);
		openDocument.actionPerformed(null); // OPEN
		
		contentsFromFile = openDocument.getDocument().getContents();
		customContents = contentsFromFile;
		customContents.add("\n 2 I am another line added by the test to check if the save command works fine!");
		
		saveAsDocument.setPathAndDecryption(pathToSaveTo, "docx", "none");//save with none encoding(encodes)
		document.setContents(customContents);
		saveAsDocument.setDocument(document);
		saveAsDocument.actionPerformed(null); // SAVE
		
		Document documentAfter = new Document();
		openDocument.setPathAndDecryption(pathToSaveTo, "docx", "none");//open saved file with none encoding(decodes)
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
	
	@Test //testing docx with atbash encoding
	public void test2() {
		count++;
		OpenDocument openDocument = new OpenDocument();
		SaveAsDocument saveAsDocument = new SaveAsDocument();
		Document document = new Document();
		String path = ".\\Files For Testing\\testsForSaveDocument\\saveDocxTest.docx";
		String pathToSaveTo = ".\\Files For Testing\\testsForSaveDocument\\saveDocxTestAtbash"+ Integer.toString(count) + ".docx";
		openDocument.setPathAndDecryption(path, "docx", "none"); //
		openDocument.setDocument(document);
		openDocument.actionPerformed(null); // OPEN
		
		contentsFromFile = openDocument.getDocument().getContents();
		customContents = contentsFromFile;
		customContents.add("Testing docx with atbash encoding");
		saveAsDocument.setPathAndDecryption(pathToSaveTo, "docx", "atbash"); //save with atbash encoding(encodes)
		document.setContents(customContents);
		saveAsDocument.setDocument(document);
		saveAsDocument.actionPerformed(null); // SAVE
		
		Document documentAfter = new Document();
		openDocument.setPathAndDecryption(pathToSaveTo, "docx", "atbash");	//open saved file with atbash encoding(decodes)
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
	
	@Test //testing docx with rot13 encoding
	public void test3() {
		count++;
		OpenDocument openDocument = new OpenDocument();
		SaveAsDocument saveAsDocument = new SaveAsDocument();
		Document document = new Document();
		String path = ".\\Files For Testing\\testsForSaveDocument\\saveDocxTest.docx";
		String pathToSaveTo = ".\\Files For Testing\\testsForSaveDocument\\saveDocxTestRot13"+ Integer.toString(count) + ".docx";
		openDocument.setPathAndDecryption(path, "docx", "none"); //
		openDocument.setDocument(document);
		openDocument.actionPerformed(null); // OPEN
		
		contentsFromFile = openDocument.getDocument().getContents();
		customContents = contentsFromFile;
		customContents.add("Testing rot13 with atbash encoding");
		saveAsDocument.setPathAndDecryption(pathToSaveTo, "docx", "rot13"); //save with rot13 encoding(encodes)
		document.setContents(customContents);
		saveAsDocument.setDocument(document);
		saveAsDocument.actionPerformed(null); // SAVE
		
		Document documentAfter = new Document();
		openDocument.setPathAndDecryption(pathToSaveTo, "docx", "rot13");	//open saved file with rot13 encoding(decodes)
		openDocument.setDocument(documentAfter);
		openDocument.actionPerformed(null);
		contentsAfterBeingSaved = openDocument.getDocument().getContents();

		assertEquals(customContents,contentsAfterBeingSaved);
	}

	@After
	public void post3() {
		contentsFromFile.clear();
		customContents.clear();
		contentsAfterBeingSaved.clear();
	}
	
	@Test //testing xlsx with none encoding
	public void test4() {
		count++;
		OpenDocument openDocument = new OpenDocument();
		SaveAsDocument saveAsDocument = new SaveAsDocument();
		Document document = new Document();
		String path = ".\\Files For Testing\\testsForSaveDocument\\saveXlsxTest.xlsx";
		String pathToSaveTo = ".\\Files For Testing\\testsForSaveDocument\\saveXlsxTestNone"+ Integer.toString(count) + ".xlsx";
		openDocument.setPathAndDecryption(path, "xlsx", "none"); //
		openDocument.setDocument(document);
		openDocument.actionPerformed(null); // OPEN
		
		contentsFromFile = openDocument.getDocument().getContents();
		customContents = contentsFromFile;
		customContents.add("4	someone	else");
		customContents.add("5	someone	else	coming	from	test");
		customContents.add("4	tester	is	testing");
		
		saveAsDocument.setPathAndDecryption(pathToSaveTo, "xlsx", "none");//save with atbash encoding(encodes)
		document.setContents(customContents);
		saveAsDocument.setDocument(document);
		saveAsDocument.actionPerformed(null); // SAVE
		
		Document documentAfter = new Document();
		openDocument.setPathAndDecryption(pathToSaveTo, "xlsx", "none");//open saved file with atbash encoding(decodes)
		openDocument.setDocument(documentAfter);
		openDocument.actionPerformed(null);
		contentsAfterBeingSaved = openDocument.getDocument().getContents();

		assertEquals(customContents,contentsAfterBeingSaved);
	}
	
	@After
	public void post4() {
		contentsFromFile.clear();
		customContents.clear();
		contentsAfterBeingSaved.clear();
	}
	
	@Test //testing xlsx with atbash encoding
	public void test5() {
		count++;
		OpenDocument openDocument = new OpenDocument();
		SaveAsDocument saveAsDocument = new SaveAsDocument();
		Document document = new Document();
		String path = ".\\Files For Testing\\testsForSaveDocument\\saveXlsxTest.xlsx";
		String pathToSaveTo = ".\\Files For Testing\\testsForSaveDocument\\saveXlsxTestAtbash"+ Integer.toString(count) + ".xlsx";
		
		openDocument.setPathAndDecryption(path, "xlsx", "none"); //
		openDocument.setDocument(document);
		openDocument.actionPerformed(null); // OPEN
		
		contentsFromFile = openDocument.getDocument().getContents();
		customContents = contentsFromFile;
		customContents.add("4	someone	else");
		customContents.add("5	someone	else	coming	from	test");
		customContents.add("4	tester	is	testing");
		customContents.add("4	tester	is	testing	rot13	atbash");
		
		saveAsDocument.setPathAndDecryption(pathToSaveTo, "xlsx", "atbash"); //save with atbash encoding(encodes)
		document.setContents(customContents);
		saveAsDocument.setDocument(document);
		saveAsDocument.actionPerformed(null); // SAVE
		
		Document documentAfter = new Document();
		openDocument.setPathAndDecryption(pathToSaveTo, "xlsx", "atbash");	//open saved file with atbash encoding(decodes)
		openDocument.setDocument(documentAfter);
		openDocument.actionPerformed(null);
		contentsAfterBeingSaved = openDocument.getDocument().getContents();

		assertEquals(customContents,contentsAfterBeingSaved);
	}

	@After
	public void post5() {
		contentsFromFile.clear();
		customContents.clear();
		contentsAfterBeingSaved.clear();
	}
	
	@Test //testing xlsx with rot13 encoding
	public void test6() {
		count++;
		OpenDocument openDocument = new OpenDocument();
		SaveAsDocument saveAsDocument = new SaveAsDocument();
		Document document = new Document();
		String path = ".\\Files For Testing\\testsForSaveDocument\\saveXlsxTest.xlsx";
		String pathToSaveTo = ".\\Files For Testing\\testsForSaveDocument\\saveXlsxTestRot13"+ Integer.toString(count) + ".xlsx";
		
		openDocument.setPathAndDecryption(path, "xlsx", "none"); //
		openDocument.setDocument(document);
		openDocument.actionPerformed(null); // OPEN
		
		contentsFromFile = openDocument.getDocument().getContents();
		customContents = contentsFromFile;
		customContents.add("4	someone	else");
		customContents.add("5	someone	else	coming	from	test");
		customContents.add("4	tester	is	testing");
		customContents.add("4	tester	is	testing	rot13	xlsx");
		
		saveAsDocument.setPathAndDecryption(pathToSaveTo, "xlsx", "rot13"); //save with rot13 encoding(encodes)
		document.setContents(customContents);
		saveAsDocument.setDocument(document);
		saveAsDocument.actionPerformed(null); // SAVE
		
		Document documentAfter = new Document();
		openDocument.setPathAndDecryption(pathToSaveTo, "xlsx", "rot13");	//open saved file with rot13 encoding(decodes)
		openDocument.setDocument(documentAfter);
		openDocument.actionPerformed(null);
		contentsAfterBeingSaved = openDocument.getDocument().getContents();

		assertEquals(customContents,contentsAfterBeingSaved);
	}

	@After
	public void post6() {
		contentsFromFile.clear();
		customContents.clear();
		contentsAfterBeingSaved.clear();
	}
	
	
}
