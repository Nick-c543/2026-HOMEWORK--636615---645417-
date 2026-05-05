package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;

class PartitaTest {
	
	private Partita partita;
	private Stanza stanzaProva; 

	@BeforeEach
	void setUp() throws Exception {
		this.partita = new Partita(); 
		this.stanzaProva = new Stanza("stanza prova");
		
	}

	@Test
	void testSetStanzaCorrenteQualunque() {
		partita.setStanzaCorrente(stanzaProva);
		assertEquals(partita.getStanzaCorrente(),stanzaProva);
	}
	
	@Test
	void testSetStanzaCorrenteNull() {
		partita.setStanzaCorrente(null);
		assertNull(partita.getStanzaCorrente());
	}
	
	@Test
	void testSetStanzaCorrenteVincente() {
		partita.setStanzaCorrente(partita.getStanzaVincente());
		assertEquals(partita.getStanzaCorrente(),partita.getStanzaVincente());
	}
	
	@Test
	void testVintaNo() {
		partita.setStanzaCorrente(stanzaProva);
		assertFalse(partita.vinta()); 
	}
	
	@Test 
	void testVintaSi() {
		partita.setStanzaCorrente(partita.getStanzaVincente());
		assertTrue(partita.vinta());
		
	}
	
	@Test 
	void testIsFinitaNo() {
		assertFalse(partita.isFinita());
	}
	
	@Test 
	void testIsFinitaSiVintaNoFinitiCfu() {
		partita.setStanzaCorrente(partita.getStanzaVincente());
		assertTrue(partita.isFinita());
	}
	
	@Test 
	void testIsFinitaNoVintaSiFinitiCfu() {
		partita.getGiocatore().setCfu(0); 
		assertTrue(partita.isFinita());
	}
	
	@Test 
	void testSetFinita() {
		partita.setFinita(); 
		assertTrue(partita.isFinita()); 
	}
	
	@Test
	void testGetCfuNonInizializzati() {
		assertEquals(partita.getGiocatore().getCfu(), 20); 
	}
	
	@Test
	void testSetCfu() {
		partita.getGiocatore().setCfu(40); 
		assertEquals(partita.getGiocatore().getCfu(), 40); 
	}
	
	@Test
	void testGiocatoreIsVivoVivo() {
		assertTrue(partita.giocatoreIsVivo());
	}
	
	@Test
	void testGiocatoreIsVivoMorto() {
		partita.getGiocatore().setCfu(0);
		assertFalse(partita.giocatoreIsVivo());
	}
}

