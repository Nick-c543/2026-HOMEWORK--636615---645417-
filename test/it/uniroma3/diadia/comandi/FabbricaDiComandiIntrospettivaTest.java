package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import it.uniroma3.diadia.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FabbricaDiComandiIntrospettivaTest {
	private IOSimulator io; 
	private FabbricaDiComandiIntrospettiva fab; 
	@BeforeEach
	void setUp() throws Exception {
		io = new IOSimulator(Arrays.asList());
		fab = new FabbricaDiComandiIntrospettiva(io); 
	}

	@Test
	void testCostruisciComandoIstruzioneNulla() {
		Comando c = fab.costruisciComando(null); 
		assertEquals("fine", c.getNome()); 
	}
	
	@Test
	void testCostruisciComandoNonValido() {
		Comando c = fab.costruisciComando("ciao");
		assertEquals("non valido", c.getNome());
	}
	
	@Test
	void testCostruisciComandoConParametro() {
		Comando c = fab.costruisciComando("prendi osso"); 
		assertEquals("prendi", c.getNome());
		assertEquals("osso", c.getParametro());
	}
	
	@Test
	void testCostruisciComandoSenzaParametro() {
		Comando c = fab.costruisciComando("guarda"); 
		assertEquals("guarda", c.getNome());
		assertNull(c.getParametro());
	}
	
	@Test
	void testCostruisciComandoStringaVuota() {
		// Simula l'utente che preme solo "Invio"
		Comando c = fab.costruisciComando(""); 
		assertEquals("non valido", c.getNome());
	}
	
	@Test
	void testCostruisciComandoSoloSpazi() {
		// Simula l'utente che digita spazi per sbaglio
		Comando c = fab.costruisciComando("   "); 
		assertEquals("non valido", c.getNome());
	}

}
