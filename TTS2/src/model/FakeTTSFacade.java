package model;

public class FakeTTSFacade extends TTSFacade {
	private String playedContents = "";
	private float volume;
	private float pitch;
	private float rate;
	
	
	@Override
	public void play(String s)
	{
		playedContents = playedContents + s;
		super.play(playedContents);
	}
	
	@Override
	public void setVolume(float i)
	{
		volume = i;
		super.setVolume(volume);
	}
	
	@Override
	public void setPitch(float i)
	{
		pitch = i;
		super.setPitch(pitch);
	}
	
	@Override
	public void setRate(float i)
	{
		rate = i;
		super.setRate(rate);
	}
	
	public String getPlayedContents()
	{
		return playedContents;
	}
	
	public float getVolume() {
		return super.getVoice().getVolume();
	}
	
	public float getPitch() {
		return super.getVoice().getPitch();
	}
	
	public float getRate() {
		return super.getVoice().getRate();
	}
	
}