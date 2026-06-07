package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.IO;


class fabbricaDiComandiFisarmonicaTest {
	
	private IO io;
	private FabbricaDiComandiFisarmonica comandi; 

	@BeforeEach
	void setUp() throws Exception {
		io = new IOConsole();
		comandi = new FabbricaDiComandiFisarmonica(io);
	}
	
	@Test
	void testCostruisciComandoVaiNullo() {
		Comando comando = comandi.costruisciComando("vai");
		assertEquals(comando.getNome(), "vai");
		assertNull(comando.getParametro());
	}

	@Test
	void testCostruisciComandoVaiNord() {
		Comando comando = comandi.costruisciComando("vai nord");
		assertEquals(comando.getNome(), "vai");
		assertEquals(comando.getParametro(), "nord");
	}
	
	@Test
	void testCostruisciComandoPrendiNullo() {
		Comando comando = comandi.costruisciComando("prendi");
		assertEquals(comando.getNome(), "prendi");
		assertNull(comando.getParametro());
	}

	@Test
	void testCostruisciComandoPrendiLibro() {
		Comando comando = comandi.costruisciComando("prendi libro");
		assertEquals(comando.getNome(), "prendi");
		assertEquals(comando.getParametro(), "libro");
	}
	
	@Test
	void testCostruisciComandoPosaNullo() {
		Comando comando = comandi.costruisciComando("posa");
		assertEquals(comando.getNome(), "posa");
		assertNull(comando.getParametro());
	}

	@Test
	void testCostruisciComandoPosaLibro() {
		Comando comando = comandi.costruisciComando("posa libro");
		assertEquals(comando.getNome(), "posa");
		assertEquals(comando.getParametro(), "libro");
	}
	
	@Test
	void testCostruisciComandoAiuto() {
		Comando comando = comandi.costruisciComando("aiuto");
		assertEquals(comando.getNome(), "aiuto");
		assertNull(comando.getParametro());
	}

	@Test
	void testCostruisciComandoFine() {
		Comando comando = comandi.costruisciComando("fine");
		assertEquals(comando.getNome(), "fine");
		assertNull(comando.getParametro());
	}
	
	@Test
	void testCostruisciComandoGuarda() {
		Comando comando = comandi.costruisciComando("guarda");
		assertEquals(comando.getNome(), "guarda");
		assertNull(comando.getParametro());
	}
	
	@Test
	void testCostruisciComandoNonValidoNullo() {
		Comando comando = comandi.costruisciComando("");
		assertEquals(comando.getNome(), "non valido");
		assertNull(comando.getParametro());
	}
	
	@Test
	void testCostruisciComandoNonValidoDiverso() {
		Comando comando = comandi.costruisciComando("salta");
		assertEquals(comando.getNome(), "non valido");
		assertNull(comando.getParametro());
	}

	
	
	

}
