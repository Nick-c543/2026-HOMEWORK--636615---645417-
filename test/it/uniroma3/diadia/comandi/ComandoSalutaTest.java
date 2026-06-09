package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.StanzaNotFoundException;
import it.uniroma3.personaggi.Cane;

class ComandoSalutaTest {

	private ComandoSaluta saluta; 
	private IOSimulator io; 
	
	@BeforeEach
	void setUp() throws Exception {
		io = new IOSimulator(Arrays.asList("saluta"));
		saluta = new ComandoSaluta("saluta");
		saluta.setIO(io); 
	}

	@Test
	void testSalutaMaNonCeNessunPersonaggio() {
		Partita partita = new Partita(); 
		saluta.esegui(partita);
		assertEquals("Non c'è nessun personaggio nella stanza corrente...", 
				io.getMessaggio(0)); 
	}
	
	@Test
	void testSalutaCanePerLaPrimaVolta() throws StanzaNotFoundException {
		Labirinto labirinto = Labirinto.newBuilder()
				.addStanzaIniziale("parco")
				.addStanzaVincente("parco")
				.getLabirinto(); 
		Partita partita = new Partita(labirinto); 
		partita.getStanzaCorrente().setPersonaggio(new Cane("Fido", "Bau!"));
		saluta.esegui(partita);
		assertEquals("Ciao, io sono Fido. Bau!", 
				io.getMessaggio(0)); 
	}
	
	@Test
	void testSalutaCaneGiaSalutato() throws StanzaNotFoundException {
		Labirinto labirinto = Labirinto.newBuilder()
				.addStanzaIniziale("parco")
				.addStanzaVincente("parco")
				.getLabirinto(); 
		Partita partita = new Partita(labirinto); 
		partita.getStanzaCorrente().setPersonaggio(new Cane("Fido", "Bau!"));
		saluta.esegui(partita);
		saluta.esegui(partita);
		assertEquals("Ciao, io sono Fido. Ci siamo già presentati!", 
				io.getMessaggio(1)); 
	}

}
