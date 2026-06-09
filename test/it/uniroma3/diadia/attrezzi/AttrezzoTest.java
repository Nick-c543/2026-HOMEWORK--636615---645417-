package it.uniroma3.diadia.attrezzi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AttrezzoTest {

	@Test
	void testInizializzaAttrezzo() {
		Attrezzo penna = new Attrezzo("penna", 2);
		assertEquals("penna", penna.getNome());
		assertEquals(2, penna.getPeso());
		
	}
	
	@Test
	void testToString() {
		Attrezzo spada = new Attrezzo("spada", 5);
		assertEquals("spada (5kg)", spada.toString());
		
	}

}
