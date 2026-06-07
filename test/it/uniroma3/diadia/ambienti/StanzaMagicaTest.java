package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaMagicaTest {
	
	private StanzaMagica cucina; 
	private StanzaMagica soggiorno; 
	private Attrezzo pentola; 

	@BeforeEach
	void setUp() throws Exception {
		cucina = new StanzaMagica("cucina");
		soggiorno = new StanzaMagica("soggiorno", 0);
		
		pentola = new Attrezzo("pentola", 4); 
	}

	@Test
	void testAddAttrezzoNull() {
		cucina.addAttrezzo(null);
		assertEquals(cucina.getAttrezzi().size(), 1);
	}
	
	@Test
	void testAddAttrezzoPrimaDellaSogliaMagica() {
		cucina.addAttrezzo(pentola);
		assertEquals(cucina.getAttrezzo("pentola"), pentola);
	}
	
	@Test
	void testAddAttrezzoSogliaMagicaRaggiunta() {
		soggiorno.addAttrezzo(pentola);
		assertTrue(soggiorno.hasAttrezzo("alotnep"));
		assertEquals(soggiorno.getAttrezzo("alotnep").getPeso(), 8);
	}

}
