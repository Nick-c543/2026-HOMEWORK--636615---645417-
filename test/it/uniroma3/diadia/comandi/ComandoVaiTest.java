package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;


class ComandoVaiTest {
	
	private ComandoVai vaiInput; 
	private ComandoVai vaiNord;
	private Partita partita; 
	private Stanza atrio;
	private IO io; 

	@BeforeEach
	void setUp() throws Exception {
		io = new IOConsole();
		vaiInput = new ComandoVai(io);
		vaiNord = new ComandoVai(io);
		vaiNord.setParametro("nord");
		partita = new Partita();
		atrio = partita.getLabirinto().getEntrata();
		
	}

	@Test
	void testSetParametroStringaNulla() {
		vaiInput.setParametro(null);
		assertNull(vaiInput.getParametro());
	}
	
	@Test 
	void testSetParametroDirezioneValide() {
		vaiInput.setParametro("nord");
		assertEquals("nord", vaiInput.getParametro());
	}
	
	@Test
	void testSetParametroDirezioneNonValida() {
		vaiInput.setParametro("sudest");
		assertNull(vaiInput.getParametro());
	}
	
	@Test
	void testEseguiDirezioneNulla() {
		vaiInput.setParametro(null);
		vaiInput.esegui(partita);
		assertEquals(partita.getStanzaCorrente(), atrio);
		assertEquals(partita.getGiocatore().getCfu(), 20);
	}
	
	@Test
	void testEseguiDirezioneCorretta() {
		vaiNord.esegui(partita);
		assertEquals(partita.getStanzaCorrente(), atrio.getStanzaAdiacente("nord"));
		assertEquals(partita.getGiocatore().getCfu(), 19);
	}

	@Test 
	void testEseguiDirezioneNonValida(){
		vaiNord.esegui(partita);
		vaiNord.esegui(partita); 
		assertEquals(partita.getStanzaCorrente(), atrio.getStanzaAdiacente("nord"));
		assertEquals(partita.getGiocatore().getCfu(), 19);
	}
}
