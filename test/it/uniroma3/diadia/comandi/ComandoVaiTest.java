package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//import it.uniroma3.diadia.comandi.*;
import it.uniroma3.diadia.*;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaNotFoundException;

import static it.uniroma3.diadia.ambienti.Direzione.*;

class ComandoVaiTest {
	
	private ComandoVai vaiInput; 
	private ComandoVai vaiNord;
	private Partita partita; 
	private Stanza atrio;
	private IO io; 
	private Labirinto.LabirintoBuilder builder; 

	@BeforeEach
	void setUp() throws Exception {
		io = new IOSimulator(new ArrayList<>());
		vaiInput = new ComandoVai();
		vaiInput.setIO(io);
		vaiNord = new ComandoVai();
		vaiNord.setIO(io);
		vaiNord.setParametro("nord");
		partita = new Partita();
		atrio = partita.getLabirinto().getEntrata();
		builder = Labirinto.newBuilder(); 
		
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
	
	@Test
	void testEseguiSuBilocale() throws StanzaNotFoundException {
		Labirinto bilocale = builder 
				.addStanzaIniziale("salotto")
				.addStanzaVincente("camera")
				.addAttrezzo("letto",10) 
				.addAdiacenza("salotto", "camera", Direzione.NORD) 
				.getLabirinto(); 
		Partita nuova = new Partita(bilocale); 
		
		assertEquals(bilocale.getEntrata(), nuova.getStanzaCorrente()); 
		vaiNord.esegui(nuova);
		assertEquals(bilocale.getEntrata().getStanzaAdiacente(NORD), nuova.getStanzaCorrente()); 
	}
	
	@Test 
	void testEseguiSuTrilocale() throws StanzaNotFoundException {
		Labirinto trilocale = builder
				.addStanzaIniziale("salotto")
				.addStanza("cucina")
				.addAttrezzo("pentola",1) 
				.addStanzaVincente("camera")
				.addAdiacenza("salotto", "cucina", Direzione.NORD)
				.addAdiacenza("cucina", "camera", Direzione.EST)
				.getLabirinto(); 
		Partita nuova = new Partita(trilocale); 
		
		assertEquals(trilocale.getEntrata(), nuova.getStanzaCorrente()); 
		vaiNord.esegui(nuova);
		assertEquals(trilocale.getEntrata().getStanzaAdiacente(NORD), nuova.getStanzaCorrente()); 
		ComandoVai vaiEst = new ComandoVai();
		vaiEst.setIO(io);
		vaiEst.setParametro("est");
		vaiEst.esegui(nuova); 
		assertEquals(trilocale.getEntrata().getStanzaAdiacente(NORD).getStanzaAdiacente(EST), 
				nuova.getStanzaCorrente()); 		
	}
	

}
