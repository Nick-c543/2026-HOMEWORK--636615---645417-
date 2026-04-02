package diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
		assertEquals(partita.getStanzaCorrente(),null);
	}
	
	@Test
	void testSetStanzaCorrenteVincente() {
		partita.setStanzaCorrente(partita.getStanzaVincente());
		assertEquals(partita.getStanzaCorrente(),partita.getStanzaVincente());
	}
	
	@Test
	void testVintaNo() {
		partita.setStanzaCorrente(stanzaProva);
		assertEquals(partita.vinta(),false); 
	}
	
	@Test 
	void testVintaSi() {
		partita.setStanzaCorrente(partita.getStanzaVincente());
		assertEquals(partita.vinta(),true);
		
	}
	
	@Test 
	void testIsFinitaNo() {
		assertEquals(partita.isFinita(),false);
	}
	
	@Test 
	void testIsFinitaSiVintaNoFinitiCfu() {
		partita.setStanzaCorrente(partita.getStanzaVincente());
		assertEquals(partita.isFinita(),true);
	}
	
	@Test 
	void testIsFinitaNoVintaSiFinitiCfu() {
		partita.getGiocatore().setCfu(0); 
		assertEquals(partita.isFinita(),true);
	}
	
	@Test 
	void testSetFinita() {
		partita.setFinita(); 
		assertEquals(partita.isFinita(),true); 
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
}

