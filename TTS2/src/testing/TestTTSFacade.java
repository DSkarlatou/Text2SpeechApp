package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import model.FakeTTSFacade;
import model.TTSFacade;

public class TestTTSFacade {
	FakeTTSFacade fakeTTSFacade = new FakeTTSFacade();
	TTSFacade ttsFacade = new TTSFacade();
	
	@Test
	public void testSetVolume() {
		fakeTTSFacade.setVolume(100);
		ttsFacade = (TTSFacade) fakeTTSFacade; // cast
		assertEquals((float)ttsFacade.getVoice().getVolume(), fakeTTSFacade.getVolume(), 0);
	}

	@Test
	public void testSetPitch() {
		fakeTTSFacade.setPitch(150);
		ttsFacade = (TTSFacade) fakeTTSFacade; // cast
		assertEquals((float)ttsFacade.getVoice().getPitch(), (float)fakeTTSFacade.getPitch(), 0);
	}

	@Test
	public void testSetRate() {
		fakeTTSFacade.setRate(100); //100 becomes 225
		ttsFacade = (TTSFacade) fakeTTSFacade; // cast
		assertEquals(fakeTTSFacade.getRate(), ttsFacade.getVoice().getRate(), 0);
		fakeTTSFacade.setRate(250); //100 becomes 225
		ttsFacade = (TTSFacade) fakeTTSFacade; // cast
		assertEquals(fakeTTSFacade.getRate(), ttsFacade.getVoice().getRate(), 0);
	}
}
