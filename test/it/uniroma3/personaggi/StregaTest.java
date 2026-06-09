package it.uniroma3.personaggi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;

class StregaTest {

	private Strega strega; 
	private Partita partita; 
	
	@BeforeEach
	void setUp() throws Exception {
		strega = new Strega("Maria", "Sono la strega più spaventosa!"); 
		partita = new Partita(); 
	}

	@Test
	void testAgisciHaSalutato() {
		assertEquals(partita.getStanzaCorrente().getNome(), "Atrio");
		strega.saluta();
		strega.agisci(partita); 
		assertEquals(partita.getStanzaCorrente().getNome(), "Aula N10"); 
	}
	
	@Test
	void testAgisciNonHaSalutato() {
		assertEquals(partita.getStanzaCorrente().getNome(), "Atrio");
		strega.agisci(partita); 
		assertEquals(partita.getStanzaCorrente().getNome(), "Laboratorio Campus");
	}

}
