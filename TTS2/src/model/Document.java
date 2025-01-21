package model;

import java.util.ArrayList;
import java.util.List;

import input.DocumentReader;
import input.DocumentReaderFactory;
import output.DocumentWriter;
import output.DocumentWriterFactory;
import view.Test2SpeechEditorView;

import java.io.FileNotFoundException;

public class Document{
	private DocumentReader file;
	private static ArrayList<String> contents = new ArrayList<String>();
	private TTSFacade audioManager = new TTSFacade();
	private DocumentReader documentReader;
	private DocumentWriter documentWriter;
	private DocumentReaderFactory docReaderFactory = new DocumentReaderFactory();
	private DocumentWriterFactory docWriterFactory = new DocumentWriterFactory();
	
	public void setAudioManager(TTSFacade audioManager)
	{
		this.audioManager = audioManager;
	}
	
	public TTSFacade getAudioManager()
	{
		return audioManager;
	}
	
	public void SetDocReaderFactory(DocumentReaderFactory docReaderFactory)
	{
		this.docReaderFactory = docReaderFactory;
	}
	
	public void SetDocWriterFactory(DocumentWriterFactory docWriterFactory)
	{
		this.docWriterFactory = docWriterFactory;
	}
	
	// call DocumentReaderFactory to read a file
	public void open(String filepath, String format, String cryption) throws FileNotFoundException {
		
		documentReader = docReaderFactory.createReader(filepath, format, cryption);
		
		if(documentReader == null)
		{
			Test2SpeechEditorView.errorDialog("File not found");
			return;
		}
		
		contents = ((ArrayList<String>) documentReader.read());
	}
	
	// call DocumentReaderFactory to write to a file
	public void save(String filepath, String format, String cryption) throws FileNotFoundException {
		docWriterFactory.setContents(contents);
		documentWriter = docWriterFactory.createWriter(filepath, format, cryption);
		
		if(documentWriter == null) {
			Test2SpeechEditorView.errorDialog("The file you are trying to write to, is already opened in another process.\nPlease close the opened file before attempting to write to it.");
			return;
		}
		
		documentWriter.write();
	}

	public void playContents() {

		audioManager.play(contents.toString());
	}
	
	public void playHighlighted(String s)
	{
		audioManager.play(s);
	}
	
	public void stop()
	{
		audioManager.stop();
	}
	
	//GETTERS & SETTERS
	public ArrayList<String> getContents() {
		return contents;
	}

	public void setContents(ArrayList<String> contents) {
		Document.contents = contents;
	}	
	
}
