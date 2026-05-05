package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.attrezzi.Attrezzo; 

class StanzaBuiaTest {
	private StanzaBuia caverna; 

	@BeforeEach
	void setUp() throws Exception {
		caverna = new StanzaBuia("caverna");
	}

	@Test
	void testGetDescrizioneStanzaConOggettoLuminoso() {
		caverna.addAttrezzo(new Attrezzo("lanterna", 2));
		assertNotEquals(caverna.getDescrizione(), "qui c'è buio pesto");		
	}
	
	@Test 
	void testGetDescrizioneStanzaBuia() {
		assertEquals(caverna.getDescrizione(), "qui c'è buio pesto");
	}

}
