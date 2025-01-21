package model;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class TTSFacade{

	private VoiceManager vm;
	private Voice voice;
	private Thread thread;
	private boolean isSpeaking = false;
	
	public TTSFacade()
	{
		System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
	    voice = VoiceManager.getInstance().getVoice("kevin16"); 
	    if (voice != null)
	        voice.allocate();
	    
		voice.setPitch(130); 
		voice.setRate(150);
	}
	
	// transform the given String s to audio
	public void play(String s)
	{
		thread = new Thread() {
			public void run() {
				try {
					if(voice != null)
					{
						isSpeaking = true;  
						voice.speak(s);
						isSpeaking = false; 
						
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};		
		thread.start();
	}
	
	// stops audio
	public void stop()
	{
		voice.getAudioPlayer().cancel();
	}
	
	public void setVolume(float volume)
	{
		voice.setVolume(volume * 0.01f);
	}
	
	public void setPitch(float i)
	{
		voice.setPitch(i);
	}
	
	public void setRate(float i)
	{
		voice.setRate(i);
	}

	public boolean isSpeaking() 
	{
		return isSpeaking;
	}


	public Voice getVoice() {
		return voice;
	}
}
