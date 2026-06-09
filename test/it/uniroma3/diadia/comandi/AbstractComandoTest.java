package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;

class AbstractComandoTest {
	
	private AbstractComando comandoFake; 
	private IOSimulator io;

	@BeforeEach
	void setUp() throws Exception {
		this.comandoFake = new AbstractComando("comando falso") {
			@Override 
			public void esegui(Partita partita) {}; 
		}; 
		io = new IOSimulator(Arrays.asList("")); 
	}

	@Test
	void testGetNome() {
		assertEquals("comando falso", comandoFake.getNome());
	}
	
	@Test
	void testSetEGetParametro() {
		assertNull(comandoFake.getParametro());
		comandoFake.setParametro("sopra");
		assertEquals("sopra", comandoFake.getParametro());
	}
	
	@Test
	void testSetEGetIO() {
		assertNull(comandoFake.getIO());
		comandoFake.setIO(io);
		assertEquals(io, comandoFake.getIO()); 
	}

}
