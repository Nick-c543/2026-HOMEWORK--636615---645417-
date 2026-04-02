package diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LabirintoTest {
	
	private Labirinto labirinto; 
	private Stanza atrio;
	private Stanza biblioteca; 

	@BeforeEach
	void setUp() throws Exception {
		this.labirinto = new Labirinto();
		this.atrio = labirinto.getEntrata();
		this.biblioteca = atrio.getStanzaAdiacente("nord");
	}
	
	@Test
	void testInitStanzaAtrio() {
		assertEquals(biblioteca.getNome(), "Biblioteca");	
	}
	

	@Test
	void testInitStanzaConAttrezzo() {
		assertTrue(atrio.getStanzaAdiacente("sud").hasAttrezzo("lanterna"));
	}
	
	@Test
	void testInitN11Null() {
		assertEquals(atrio.getStanzaAdiacente("est").getStanzaAdiacente("nord"),null); 
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

