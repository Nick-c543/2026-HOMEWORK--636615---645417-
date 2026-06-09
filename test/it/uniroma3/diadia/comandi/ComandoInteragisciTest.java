package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.personaggi.*;

class ComandoInteragisciTest {
	
	private Cane fido;
	private ComandoInteragisci interagisci; 
	private IO io; 
	private Partita partita;

	@BeforeEach
	void setUp() throws Exception {
		partita = new Partita(); 
		fido = new Cane("Fido", "Sono un cane fedele, ma attento a quando mordo!"); 
		interagisci = new ComandoInteragisci(); 
		io = new IOSimulator(new ArrayList<>());
		interagisci.setIO(io);
	}

	@Test
	void testInteragisciConPersonaggioAssente() {
		assertEquals(partita.getStanzaCorrente().getNome(), "Atrio"); 
		assertEquals(partita.getStanzaCorrente().getAttrezzi().size(), 1);
		assertEquals(partita.getGiocatore().getCfu(), 20);
		interagisci.esegui(partita);
		assertEquals(partita.getStanzaCorrente().getNome(), "Atrio"); 
		assertEquals(partita.getStanzaCorrente().getAttrezzi().size(), 1);
		assertEquals(partita.getGiocatore().getCfu(), 20);
	}
	
	@Test void testInteragiscConUnCane() {
		assertEquals(partita.getGiocatore().getCfu(), 20);
		partita.getStanzaCorrente().setPersonaggio(fido);
		interagisci.esegui(partita); 
		assertEquals(partita.getGiocatore().getCfu(), 19);
	}

}
