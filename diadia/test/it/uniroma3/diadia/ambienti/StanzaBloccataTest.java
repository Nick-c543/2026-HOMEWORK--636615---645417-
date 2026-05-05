package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBloccataTest {
	
	private StanzaBloccata prigione;
	private Stanza corridoio; 
	private Stanza cortile; 

	@BeforeEach
	void setUp() throws Exception {
		prigione = new StanzaBloccata("prigione");
		corridoio = new Stanza("corridoio");
		cortile = new Stanza("cortile");
		prigione.impostaStanzaAdiacente("nord", corridoio);
		prigione.impostaStanzaAdiacente("est", cortile);
	}

	@Test
	void testGetStanzaAdiacenteNonBloccata() {
		assertEquals(prigione.getStanzaAdiacente("est"), cortile);
	}
	
	@Test
	void testGetStanzaAdiacenteInesistente() {
		assertNull(prigione.getStanzaAdiacente("sud"));
	}
	
	@Test
	void testGetStanzaAdiacenteBloccataSenzaAttrezzo() {
		assertEquals(prigione, prigione.getStanzaAdiacente("nord"));
	}
	
	@Test 
	void testGetStanzaAdiacenteBloccataConAttrezzo() {
		prigione.addAttrezzo(new Attrezzo("chiave", 1));
		assertEquals(corridoio, prigione.getStanzaAdiacente("nord"));
	}

}
