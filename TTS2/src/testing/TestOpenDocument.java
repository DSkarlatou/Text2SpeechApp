package testing;

import static org.junit.Assert.*;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.*;

import input.*;
import view.Test2SpeechEditorView;

import java.io.FileNotFoundException;


import org.junit.Test;

import model.Document;
import command.CommandsFactory;
import command.OpenDocument;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

public class TestOpenDocument {
	private String path;
	private Document document = new Document();
	private String format;
	private String decryption;
	private ArrayList<String> contentsFromDoc = new ArrayList<>();
	private ArrayList<String> customContents = new ArrayList<>();
	JTextPane txtpn = new JTextPane();
	//Hello world.This is a text in Atbash encoding.	for atbash
	//Hello world.This is a text in rot13 encoding.Enjoy! for rot13

	//Hint: Use test.docx and none decoding
	@Test
	public void test1() {
		OpenDocument openDocument = new OpenDocument();
		openDocument.setPathAndDecryption(".\\Files For Testing\\testsForOpenDocument\\test.docx", "docx", "none"); //
		openDocument.actionPerformed(null);
		customContents.add("This is a text.");
		contentsFromDoc = openDocument.getContents();	
		assertEquals(contentsFromDoc,customContents);
	}
	
	@After
	public void post() {
		customContents.clear();
	}
	
	//Hint: Use AtDoc.docx and Atbash decoding.
	@Test
	public void test2() {
		OpenDocument openDocument = new OpenDocument();
		openDocument.setPathAndDecryption(".\\Files For Testing\\testsForOpenDocument\\AtDoc.docx", "docx", "atbash"); //
		openDocument.actionPerformed(null);
		customContents.add("Hello world.This is a text in Atbash encoding.");
		contentsFromDoc = openDocument.getContents();	
		assertEquals(contentsFromDoc,customContents);
	}
	
	@After
	public void postb() {
		customContents.clear();
	}
	
	//Hint:Use RotDoc.docx and Rot13 decoding.
	@Test
	public void test3() {
		OpenDocument openDocument = new OpenDocument();
		openDocument.setPathAndDecryption(".\\Files For Testing\\testsForOpenDocument\\RotDoc.docx", "docx", "rot13"); //
		openDocument.actionPerformed(null);
		customContents.add("Hello world.This is a text in rot13 encoding.Enjoy!");
		contentsFromDoc = openDocument.getContents();	
		assertEquals(contentsFromDoc,customContents);
	}
	
	@After
	public void postc() {
		customContents.clear();
	}
	//Hint: Use Workbook.xlsx and none decoding.
	@Test
	public void test4() {
		OpenDocument openDocument = new OpenDocument();
		openDocument.setPathAndDecryption(".\\Files For Testing\\testsForOpenDocument\\Workbook.xlsx", "xlsx", "none"); //
		openDocument.actionPerformed(null);
		customContents.add("Name	Grades	ID	Country"); 
		customContents.add("John	7.0	1000.0	U.S.A."); 
		customContents.add("Kostas	2.0	1001.0	Greece");
		customContents.add("Yuri	4.0	1002.0	Russia"); 
		customContents.add("Selim	2.0	1003.0	Uzbekistan");
		contentsFromDoc = openDocument.getContents();	
		assertEquals(contentsFromDoc,customContents);
	}
	
	@After
	public void postd() {
		customContents.clear();
	}
	//Hint:Use book1.xlsx and Atbash decoding.
	@Test
	public void test5() {
		OpenDocument openDocument = new OpenDocument();
		openDocument.setPathAndDecryption(".\\Files For Testing\\testsForOpenDocument\\book1.xlsx", "xlsx", "atbash"); //
		openDocument.actionPerformed(null);
		customContents.add("id	tel	country\r"); 
		customContents.add("01	3021	Yugoslavia\r"); 
		customContents.add("02	4203	Czechia");
		contentsFromDoc = openDocument.getContents();	
		assertEquals(contentsFromDoc,customContents);

	}
	
	@After
	public void poste() {
		customContents.clear();
	}
	//Hint: Use book2.xlsx and Atbash decoding.
	@Test
	public void test6() {
		OpenDocument openDocument = new OpenDocument();
		openDocument.setPathAndDecryption(".\\Files For Testing\\testsForOpenDocument\\book2.xlsx", "xlsx", "rot13"); //
		openDocument.actionPerformed(null);
		customContents.add("city	country\r"); 
		customContents.add("London	England\r"); 
		customContents.add("Paris	France\r");
		customContents.add("Madrid	Spain");
		contentsFromDoc = openDocument.getContents();	
		assertEquals(contentsFromDoc,customContents);
	}
	
}
