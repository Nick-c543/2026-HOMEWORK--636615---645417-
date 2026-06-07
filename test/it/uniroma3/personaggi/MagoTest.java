package it.uniroma3.personaggi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class MagoTest {
	
	private Mago mago; 
	private Partita partita; 
	private Attrezzo pozione; 

	@BeforeEach
	void setUp() throws Exception {
		pozione = new Attrezzo("pozione", 2); 
		mago = new Mago("Merlino", "Sono un mago molto saggio.", pozione);
		partita = new Partita(); 
	}

	@Test
	void testAgisciAttrezzoValido() {
		assertFalse(partita.getStanzaCorrente().hasAttrezzo("pozione")); 
		mago.agisci(partita); 
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("pozione"));
	}
	
	@Test
	void testAgisciAttrezzoNonValido() {
		Mago stregone = new Mago("Arturo", "Non ho nulla da darti", null); 
		assertEquals(partita.getStanzaCorrente().getAttrezzi().size(), 1); 
		stregone.agisci(partita); 
		assertEquals(partita.getStanzaCorrente().getAttrezzi().size(), 1);
	}

}
