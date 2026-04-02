package diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LabirintoTest {
	
	private Labirinto labirinto; 
	private Stanza atrio;

	@BeforeEach
	void setUp() throws Exception {
		this.labirinto = new Labirinto();
		this.atrio = labirinto.getEntrata();
	}
	
	@Test
	void testInitStanzaAtrio() {
		assertEquals(atrio.getStanzaAdiacente("nord").getNome(), "Biblioteca");	
	}
	
	

	@Test
	void testInitStanzaConAttrezzo() {
		assertTrue(atrio.getStanzaAdiacente("sud").hasAttrezzo("lanterna"));
	}

}
