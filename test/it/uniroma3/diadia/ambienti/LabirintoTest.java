package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;
import static it.uniroma3.diadia.ambienti.Direzione.*; 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class LabirintoTest {
	
	private Labirinto labirinto; 
	private Stanza atrio;
	private Stanza biblioteca; 

	@BeforeEach
	void setUp() throws Exception {
		this.labirinto = new Labirinto("Labirinto-default.txt");
		this.atrio = labirinto.getEntrata();
		this.biblioteca = atrio.getStanzaAdiacente(NORD);
	}
	
	@Test
	void testInitStanzaAtrio() {
		assertEquals(biblioteca.getNome(), "Biblioteca");	
	}
	

	@Test
	void testInitStanzaConAttrezzo() {
		assertTrue(atrio.getStanzaAdiacente(SUD).hasAttrezzo("lanterna"));
	}
	
	@Test
	void testInitN11Null() {
		assertNull(atrio.getStanzaAdiacente(EST).getStanzaAdiacente(NORD)); 
	}
	
	@Test
	void testGetEntrata() {
		assertEquals(labirinto.getEntrata(),atrio); 
	}
	
	@Test
	void testGetUscita() {
		assertEquals(labirinto.getUscita(),biblioteca);
	}

}
