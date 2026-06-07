package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.IOConsole;


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
		
		io = new IOConsole(); 
		posaNulla.setIO(io); 
		posaBandiera.setIO(io);
		
		partita = new Partita(); 
		atrio = partita.getLabirinto().getEntrata();
		bandiera = new Attrezzo("bandiera", 2);
	}

	@Test
	void testSetParametroNullo() {
		posaNulla.setParametro(null);
		assertNull(posaNulla.getParametro());
	}
	
	@Test
	void testSetParametroValido() {
		posaNulla.setParametro("bandiera");
		assertEquals(posaNulla.getParametro(), "bandiera");
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
