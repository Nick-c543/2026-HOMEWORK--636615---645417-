package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IOSimulatorTest {
	
	private List<String> righeInput;
	private IOSimulator io; 

	@BeforeEach
	void setUp() throws Exception {
		righeInput = Arrays.asList("aiuto", "vai sud", "fine");
		io = new IOSimulator(righeInput); 
	}

	@Test
	void testMostraMessaggio() {
		io.mostraMessaggio("Ciao a tutti!");
		assertEquals(io.getMessaggio(0), "Ciao a tutti!"); 
	}
	
	
	@Test
	void testLeggiRiga() {
		assertEquals(io.leggiRiga(), "aiuto"); 
		assertEquals(io.leggiRiga(), "vai sud"); 
		assertEquals(io.leggiRiga(), "fine"); 
		assertNull(io.leggiRiga());
	}
	
	@Test
	void testGetMessaggiProdottiDalComando() {
		assertEquals("aiuto", io.leggiRiga()); 
		io.mostraMessaggio("Messaggio 1");
		io.mostraMessaggio("Messaggio 2");
		
		assertEquals(io.leggiRiga(), "vai sud"); 
		io.mostraMessaggio("Messaggio 3");
		
		assertEquals(io.leggiRiga(), "fine"); 
		io.mostraMessaggio("Messaggio 4");
		io.mostraMessaggio("Messaggio 5");
		io.mostraMessaggio("Messaggio 6");
		
		List<String> attesiAiuto = Arrays.asList("Messaggio 1", "Messaggio 2");
		assertEquals(attesiAiuto, io.getMessaggiProdottiDalComando("aiuto")); 
		
		List<String> attesiVaiSud = Arrays.asList("Messaggio 3"); 
		assertEquals(attesiVaiSud, io.getMessaggiProdottiDalComando("vai sud"));
		
		List<String> attesiFine = Arrays.asList("Messaggio 4", "Messaggio 5", "Messaggio 6");
		assertEquals(attesiFine, io.getMessaggiProdottiDalComando("fine")); 
		
		
		
	}
}
