package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.*;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

class ComandoPrendiTest {
	
	private ComandoPrendi prendiNulla; 
	private ComandoPrendi prendiOggetto;
	private Partita partita; 
	private Stanza atrio;
	private IO io; 


	@BeforeEach
	void setUp() throws Exception {
		io = new IOSimulator(new ArrayList<>());
		prendiNulla = new ComandoPrendi(); 
		prendiOggetto = new ComandoPrendi(); 
		prendiNulla.setIO(io);
		prendiOggetto.setIO(io);
		partita = new Partita();
		atrio = partita.getLabirinto().getEntrata();
	}
	
	@Test
	void testEseguiParametroNull() {
		prendiNulla.esegui(partita);
		assertTrue(atrio.hasAttrezzo("osso"));
		assertNull(partita.getGiocatore().getBorsa().getAttrezzo("osso"));	
	}
	
	@Test 
	void testEseguiAttrezzoNonPresente() {
		prendiOggetto.setParametro("lampada");
		prendiOggetto.esegui(partita);
		assertTrue(atrio.hasAttrezzo("osso"));
		assertNull(partita.getGiocatore().getBorsa().getAttrezzo("lampada"));
	}
	
	@Test 
	void testEseguiAttrezzoPresente() {
		prendiOggetto.setParametro("osso");
		prendiOggetto.esegui(partita); 
		assertFalse(atrio.hasAttrezzo("osso"));
		assertEquals(partita.getGiocatore().getBorsa().getAttrezzo("osso").getNome(), "osso");
		
	}

}
