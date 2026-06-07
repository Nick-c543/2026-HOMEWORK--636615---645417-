package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import static it.uniroma3.diadia.ambienti.Direzione.*;

class StanzaBloccataTest {
	
	private StanzaBloccata prigione;
	private Stanza corridoio; 
	private Stanza cortile; 

	@BeforeEach
	void setUp() throws Exception {
		prigione = new StanzaBloccata("prigione");
		corridoio = new Stanza("corridoio");
		cortile = new Stanza("cortile");
		prigione.impostaStanzaAdiacente(NORD, corridoio);
		prigione.impostaStanzaAdiacente(EST, cortile);
	}

	@Test
	void testGetStanzaAdiacenteNonBloccata() {
		assertEquals(prigione.getStanzaAdiacente(EST), cortile);
	}
	
	@Test
	void testGetStanzaAdiacenteInesistente() {
		assertNull(prigione.getStanzaAdiacente(SUD));
	}
	
	@Test
	void testGetStanzaAdiacenteBloccataSenzaAttrezzo() {
		assertEquals(prigione, prigione.getStanzaAdiacente(NORD));
	}
	
	@Test 
	void testGetStanzaAdiacenteBloccataConAttrezzo() {
		prigione.addAttrezzo(new Attrezzo("chiave", 1));
		assertEquals(corridoio, prigione.getStanzaAdiacente(NORD));
	}

}
