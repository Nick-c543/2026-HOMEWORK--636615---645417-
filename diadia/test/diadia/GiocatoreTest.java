package diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GiocatoreTest {
	
	private Giocatore giocatore; 

	@BeforeEach
	void setUp() throws Exception {
		this.giocatore = new Giocatore(); 
	}

	@Test
	void testGetCfu() {
		assertEquals(giocatore.getCfu(),20); 
	}
	
	@Test
	void testSetCfu() {
		giocatore.setCfu(50);
		assertEquals(giocatore.getCfu(),50); 
	}

}
