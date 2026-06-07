package it.uniroma3.personaggi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AbstractPersonaggioTest {
	
	private AbstractPersonaggio fake; 

	@BeforeEach
	void setUp() throws Exception {
		fake = new FakePersonaggio("fake", "Sono un personaggio fake!");
	}

	@Test
	void testGetNome() {
		assertEquals("fake", fake.getNome());
	}
	
	@Test
	void testHaSalutatoNo() {
		assertFalse(fake.haSalutato());
	}
	
	@Test
	void testHaSalutatoSi() {
		fake.saluta(); 
		assertTrue(fake.haSalutato());
	}
	
	@Test
	void testSalutaNonHaSalutato() {
		assertEquals(fake.saluta(), 
				"Ciao, io sono fake. Sono un personaggio fake!");
	}
	
	@Test
	void testSalutaHaGiaSalutato() {
		fake.saluta();
		assertEquals(fake.saluta(),"Ciao, io sono fake. Ci siamo già presentati!");
	}

}
