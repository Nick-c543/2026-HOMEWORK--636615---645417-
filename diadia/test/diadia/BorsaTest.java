package diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BorsaTest {
	
	private Borsa borsa; 
	private Attrezzo libro; 
	private Attrezzo cassapanca;

	@BeforeEach
	void setUp() throws Exception {
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
		for(int i=0; i<10; i++)
			borsa.addAttrezzo(libro); 
		assertFalse(borsa.addAttrezzo(libro)); 
	}
	
	@Test
	void testGetPesoMaxStandard() {
		Borsa borsa_standard = new Borsa(); 
		assertEquals(borsa_standard.getPesoMax(),10);
	}
	
	@Test 
	void testGetPesoMaxNonStandard() {
		assertEquals(borsa.getPesoMax(),11);
	}

}
