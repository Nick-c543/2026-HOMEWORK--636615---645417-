package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.*;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;


class ComandoPosaTest {
	
	private ComandoPosa posaNulla; 
	private ComandoPosa posaBandiera; 
	private Partita partita; 
	private Stanza atrio; 
	private Attrezzo bandiera;
	private IO io; 

	@BeforeEach
	void setUp() throws Exception { 
		posaNulla = new ComandoPosa(); 
		posaBandiera = new ComandoPosa(); 
		posaBandiera.setParametro("bandiera");
		
		io = new IOSimulator(new ArrayList<>()); 
		posaNulla.setIO(io); 
		posaBandiera.setIO(io);
		
		partita = new Partita(); 
		atrio = partita.getLabirinto().getEntrata();
		bandiera = new Attrezzo("bandiera", 2);
	}

	
	@Test
	void testEseguiAttrezzoNull() {
		posaNulla.esegui(partita);
		partita.getGiocatore().getBorsa().addAttrezzo(bandiera); 
		assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo("bandiera"));
		assertFalse(atrio.hasAttrezzo("bandiera"));
	}
	
	@Test
	void testEseguiAttrezzoNonInBorsa() {
		posaBandiera.esegui(partita);
		assertFalse(atrio.hasAttrezzo("bandiera"));
	}
	
	@Test
	void testEseguiAttrezzoInBorsa() {
		partita.getGiocatore().getBorsa().addAttrezzo(bandiera); 
		posaBandiera.esegui(partita);
		assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo("bandiera"));
		assertTrue(atrio.hasAttrezzo("bandiera"));
		
	}


}
