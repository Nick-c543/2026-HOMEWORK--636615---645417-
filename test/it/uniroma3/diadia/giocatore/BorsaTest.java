package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

class BorsaTest {
	
	private Borsa borsa; 
	private Attrezzo libro; 
	private Attrezzo cassapanca;
	private Borsa borsa_standard; 

	@BeforeEach
	void setUp() throws Exception {
		this.borsa_standard = new Borsa();
		this.borsa = new Borsa(11);
		this.libro = new Attrezzo("libro",1); 
		this.cassapanca = new Attrezzo("cassapanca",12); 
		
	}

	@Test
	void testAddAttrezzoNull() {
		assertFalse(borsa.addAttrezzo(null));
	}
	
	@Test
	void testAddAttrezzoConSuccesso() {
		assertTrue(borsa.addAttrezzo(libro));
	}
	
	@Test
	void TestAddAttrezzoPesoMaxRaggiunto() {
		assertFalse(borsa.addAttrezzo(cassapanca)); 
		
	}
	
	@Test
	void testAddAttrezzoNumeroMaXDiAttrezziRaggiunto() {
		for(int i=0; i<11; i++)
			borsa.addAttrezzo(libro); 
		assertFalse(borsa.addAttrezzo(libro)); 
	}
	
	@Test
	void testGetPesoMaxStandard() {
		assertEquals(borsa_standard.getPesoMax(),10);
	}
	
	@Test 
	void testGetPesoMaxNonStandard() {
		assertEquals(borsa.getPesoMax(),11);
	}
	
	@Test 
	void testGetAttrezzoNonPresente() {
		assertNull(borsa.getAttrezzo("libro"));
	}
	
	@Test
	void testGetAttrezzoPresente() {
		borsa.addAttrezzo(libro); 
		assertEquals(borsa.getAttrezzo("libro"),libro); 
		
	}
	
	@Test
	void testGetPesoBorsaVuota() {
		assertEquals(borsa.getPeso(),0);
	}
	
	@Test
	void testGetPesoBorsaConAttrezziNonPiena() {
		borsa.addAttrezzo(libro);
		assertEquals(borsa.getPeso(),1);
	}
	
	@Test
	void testGetPesoBorsaPiena() {
		for(int i=0; i<10; i++)
			borsa_standard.addAttrezzo(libro); 
		assertEquals(borsa_standard.getPeso(),borsa_standard.getPesoMax());
	}
	
	@Test
	void testIsEmptyBorsaVuota() {
		assertTrue(borsa.isEmpty());
	}
	
	@Test
	void testIsEmptyBorsaNonVuota() {
		borsa.addAttrezzo(libro);
		assertFalse(borsa.isEmpty());
	}
	
	@Test
	void testHasAttrezzoNonCeLHa() {
		assertFalse(borsa.hasAttrezzo("libro"));
	}
	
	@Test
	void testHasAttrezzoCeLHa() {
		borsa.addAttrezzo(libro);
		assertTrue(borsa.hasAttrezzo("libro"));
	}
	
	@Test
	void testRemoveAttrezzoCheNonHa() {
		assertNull(borsa.removeAttrezzo("libro"));
	}
	
	@Test
	void testRemoveAttrezzoCheHa() {
		borsa.addAttrezzo(libro);
		assertEquals(borsa.removeAttrezzo("libro"),libro);
		assertTrue(borsa.isEmpty()); 
	}
	
	

}
