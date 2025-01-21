package command;

import java.util.ArrayList;

import model.TTSFacade;

/* handles the recording, stop recording and replay operations. Holds a a record list which holds the string to be replayed */

public class ReplayManager{
	private  boolean recordingStatus = false;
	private static  ArrayList<Object> record = new ArrayList<>();
	private  String replay = "";
	private  int replayTimes = 0;
	private  TTSFacade audioManager = new TTSFacade();
	
	public  void replay()
	{
		replayTimes++;
		if(replayTimes == 1)
		{
			if(!isActiveRecording() && !record.isEmpty()) //you should only replay if the recording has stopped
			{
				for(Object e : record)
				{
					if(e.getClass().getCanonicalName().equals("command.DocumentToSpeech"))
					{
						DocumentToSpeech d = (DocumentToSpeech) e;
						for(String s : d.getContents())
						{
							replay = " " + replay + " " + s;
						}
					}
					else if(e.getClass().getCanonicalName().equals("command.DocumentToSpeechHighlighted"))
					{
						DocumentToSpeechHighlighted d = (DocumentToSpeechHighlighted) e;
						replay = " " + replay + " " + d.getHighlight();
					}
				}
			}
		}
		
		audioManager.play(replay);
	}
	
	public  void startRecording()
	{
		if(!record.isEmpty())
		{
			replayTimes = 0;
			record.clear();
			replay = "";
		}
		recordingStatus = true;
	}
	
	public  void endRecording()
	{
		recordingStatus = false;
	}
	
	public  void add(Object object)
	{
		if(isActiveRecording())
		{
			record.add(object);
		}
	}
	
	public String getReplayString()
	{
		return replay;
	}
	
	public ArrayList<Object> getRecord()
	{
		return record;
	}
	
	public void setAudioManager(TTSFacade audioManager)
	{
		this.audioManager = audioManager;
	}
	
	public  boolean isActiveRecording()
	{
		return recordingStatus;
	}
}
