package it.uniroma3.personaggi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita; 

class CaneTest {
	private Cane cane; 
	private Partita partita; 

	@BeforeEach
	void setUp() throws Exception {
		cane = new Cane("Fido", "Sono un cane fedele, ma attento a quando mordo!"); 
		partita = new Partita(); 
		
	}

	@Test
	void testAgisci() {
		assertEquals(partita.getGiocatore().getCfu(), 20);
		cane.agisci(partita); 
		assertEquals(partita.getGiocatore().getCfu(), 19);
	}

}
