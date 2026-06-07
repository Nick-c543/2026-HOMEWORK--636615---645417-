package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import static it.uniroma3.diadia.ambienti.Direzione.*;

class ComandoVaiTest {
	
	private ComandoVai vaiInput; 
	private ComandoVai vaiNord;
	private Partita partita; 
	private Stanza atrio;
	private IO io; 

	@BeforeEach
	void setUp() throws Exception {
		io = new IOConsole();
		vaiInput = new ComandoVai();
		vaiInput.setIO(io);
		vaiNord = new ComandoVai();
		vaiNord.setIO(io);
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
	void testEseguiDirezioneNulla() {
		vaiInput.setParametro(null);
		vaiInput.esegui(partita);
		assertEquals(partita.getStanzaCorrente(), atrio);
		assertEquals(partita.getGiocatore().getCfu(), 20);
	}
	
	@Test
	void testEseguiDirezioneCorretta() {
		vaiNord.esegui(partita);
		assertEquals(partita.getStanzaCorrente(), atrio.getStanzaAdiacente(NORD));
		assertEquals(partita.getGiocatore().getCfu(), 19);
	}

	@Test 
	void testEseguiDirezioneNonValida(){
		vaiNord.esegui(partita);
		vaiNord.esegui(partita); 
		assertEquals(partita.getStanzaCorrente(), atrio.getStanzaAdiacente(NORD));
		assertEquals(partita.getGiocatore().getCfu(), 19);
	}
	

}
