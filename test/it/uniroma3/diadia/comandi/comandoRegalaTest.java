package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.attrezzi.*;

import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.personaggi.*;
import it.uniroma3.diadia.ambienti.*;

class comandoRegalaTest {
	
	private ComandoRegala regala; 
	private IOSimulator io; 
	private Attrezzo osso; 

	
	@BeforeEach
	void setUp() throws Exception {
		io = new IOSimulator(new ArrayList<>()); 
		osso = new Attrezzo("osso", 2);
		regala = new ComandoRegala("regala"); 
		regala.setIO(io);
		regala.setParametro("osso");
	}

	@Test
	void testEseguiNonCèIlPersonaggio() throws StanzaNotFoundException {
		Labirinto labirinto = Labirinto.newBuilder()
				.addStanzaIniziale("atrio")
				.addStanzaVincente("atrio")
				.getLabirinto(); 
		Partita partita = new Partita(labirinto); 
		regala.esegui(partita);
		assertEquals("Non c'è nessun personaggio nella stanza corrente...", io.getMessaggio(0)); 
	}
	
	@Test
	void testEseguiNonCèLOggettoNellaBorsa() throws StanzaNotFoundException {
		Labirinto labirinto = Labirinto.newBuilder()
				.addStanzaIniziale("atrio")
				.addStanzaVincente("atrio")
				.addPersonaggio(new Cane("Fido", "Bau!"), "atrio")
				.getLabirinto(); 
		Partita partita = new Partita(labirinto); 
		partita.setStanzaCorrente(partita.getStanzaVincente());
		regala.esegui(partita);
		assertEquals("L'attrezzo che vuoi regalare non è presente nella tua borsa.", io.getMessaggio(0)); 
	}
	
	@Test
	void testEseguiCaneEContento() throws StanzaNotFoundException {
		Labirinto labirinto = Labirinto.newBuilder()
				.addStanzaIniziale("atrio")
				.addStanzaVincente("atrio")
				.addPersonaggio(new Cane("Fido", "Bau!"), "atrio")
				.getLabirinto(); 
		Partita partita = new Partita(labirinto); 
		partita.setStanzaCorrente(partita.getStanzaVincente());
		partita.getGiocatore().getBorsa().addAttrezzo(osso); 
		regala.esegui(partita);
		assertEquals("Grazie per avermi dato il mio cibo preferito. In cambio ti farò un regalo.", io.getMessaggio(0));
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("pallina")); 
	}
	
	@Test
	void testEseguiCaneNonContento() throws StanzaNotFoundException {
		Labirinto labirinto = Labirinto.newBuilder()
				.addStanzaIniziale("atrio")
				.addStanzaVincente("atrio")
				.addPersonaggio(new Cane("Fido", "Bau!"), "atrio")
				.getLabirinto(); 
		Partita partita = new Partita(labirinto); 
		partita.setStanzaCorrente(partita.getStanzaVincente());
		partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("penna", 1)); 
		regala.setParametro("penna");
		assertEquals(20, partita.getGiocatore().getCfu());
		regala.esegui(partita);
		assertEquals("Che cosa mi hai dato!? Non mi interessa! Ora ti rimangono solo 19 Cfu...", io.getMessaggio(0));
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("penna")); 
		assertEquals(19, partita.getGiocatore().getCfu());
	}
	
	@Test
	void testEseguiMago() throws StanzaNotFoundException {
		Labirinto labirinto = Labirinto.newBuilder()
				.addStanzaIniziale("atrio")
				.addStanzaVincente("atrio")
				.addPersonaggio(new Mago("Merlino", "Sono un mago molto saggio.", new Attrezzo("pozione", 2)), "atrio")
				.getLabirinto(); 
		Partita partita = new Partita(labirinto); 
		partita.setStanzaCorrente(partita.getStanzaVincente());
		partita.getGiocatore().getBorsa().addAttrezzo(osso); 
		assertEquals(2, osso.getPeso());
		regala.esegui(partita);
		assertEquals("Ho fatto una magia: ora il tuo attrezzo è più leggero!", io.getMessaggio(0));
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("osso")); 
		assertEquals(1, osso.getPeso());
	}
	
	@Test
	void testEseguiStrega() throws StanzaNotFoundException {
		Labirinto labirinto = Labirinto.newBuilder()
				.addStanzaIniziale("atrio")
				.addStanzaVincente("atrio")
				.addPersonaggio(new Strega("Malefica", "Sono una strega molto cattiva"), "atrio")
				.getLabirinto(); 
		Partita partita = new Partita(labirinto); 
		partita.setStanzaCorrente(partita.getStanzaVincente());
		partita.getGiocatore().getBorsa().addAttrezzo(osso); 
		regala.esegui(partita);
		assertEquals("Grazie per il regalo, ora però è mio HIHIHIHI!", io.getMessaggio(0));
		assertFalse(partita.getStanzaCorrente().hasAttrezzo("osso")); 
		assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo("osso")); 
	}

}
