package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;


class BorsaTest {
	
	private Borsa borsa; 
	private Attrezzo libro; 
	private Attrezzo cassapanca;
	private Borsa borsa_standard; 
	private Borsa borsaSparsa; 

	@BeforeEach
	void setUp() throws Exception {
		this.borsa_standard = new Borsa();
		this.borsa = new Borsa(11);
		this.libro = new Attrezzo("libro",1); 
		this.cassapanca = new Attrezzo("cassapanca",12); 
		this.borsaSparsa = new Borsa(30); 
		borsaSparsa.addAttrezzo(new Attrezzo("piombo", 10)); 
		borsaSparsa.addAttrezzo(new Attrezzo("ps", 5)); 
		borsaSparsa.addAttrezzo(new Attrezzo("piuma", 1)); 
		borsaSparsa.addAttrezzo(new Attrezzo("libro", 5)); 
		
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
		Attrezzo manubrio = new Attrezzo("manubrio", 10);
		borsa_standard.addAttrezzo(manubrio); 
		assertEquals(borsa_standard.getPesoMax(), borsa_standard.getPeso());
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
	
	@Test
	void testGetContenutoOrdinatoPerPesoOggettiSparsi() {
		List<Attrezzo> ordinata = borsaSparsa.getContenutoOrdinatoPerPeso(); 
		assertEquals("piuma", ordinata.get(0).getNome());
		assertEquals("libro", ordinata.get(1).getNome());
		assertEquals("ps", ordinata.get(2).getNome());
		assertEquals("piombo", ordinata.get(3).getNome());
	}
	
	@Test
	void testGetContenutoOrdinatoPerNomeOggettiSparsi() {
		SortedSet<Attrezzo> ordinato = borsaSparsa.getContenutoOrdinatoPerNome(); 
		Iterator<Attrezzo> it = ordinato.iterator(); 
		assertTrue(it.hasNext()) ;
		assertEquals("libro", it.next().getNome()); 
		assertTrue(it.hasNext()) ;
		assertEquals("piombo", it.next().getNome()); 
		assertTrue(it.hasNext()) ;
		assertEquals("piuma", it.next().getNome()); 
		assertTrue(it.hasNext()) ;
		assertEquals("ps", it.next().getNome()); 
		assertFalse(it.hasNext());
	}
	
	@Test
	void testGetContenutoRaggruppatoPerPesoOggettiSparsi() {
		Map<Integer, Set<Attrezzo>> ordinata = borsaSparsa.getContenutoRaggruppatoPerPeso(); 
		assertTrue(ordinata.containsKey(1)); 
		assertTrue(ordinata.containsKey(5)); 
		assertTrue(ordinata.containsKey(10));
		
		Iterator<Attrezzo> it1 = ordinata.get(1).iterator();
		assertTrue(it1.hasNext()) ;
		assertEquals("piuma", it1.next().getNome());
		assertFalse(it1.hasNext());
		
		Iterator<Attrezzo> it5 = ordinata.get(5).iterator();
		assertTrue(it5.hasNext()) ;
		assertEquals("libro", it5.next().getNome());
		assertTrue(it5.hasNext()) ;
		assertEquals("ps", it5.next().getNome());
		assertFalse(it5.hasNext());
		
		Iterator<Attrezzo> it10 = ordinata.get(10).iterator();
		assertTrue(it10.hasNext()) ;
		assertEquals("piombo", it10.next().getNome());
		assertFalse(it10.hasNext());
		
	}
	
	@Test
	void testGetSortedSetOrdinatoPerPesoAttrezziStessoPeso() {
		borsa.addAttrezzo(new Attrezzo("violino", 3)); 
		borsa.addAttrezzo(new Attrezzo("tromba", 5)); 
		borsa.addAttrezzo(new Attrezzo("clarinetto", 3)); 
		SortedSet<Attrezzo> ordinato = borsa.getSortedSetOrdinatoPerPeso(); 
		
		Iterator<Attrezzo> it = ordinato.iterator(); 
		assertTrue(it.hasNext()); 
		assertEquals("clarinetto", it.next().getNome());
		assertTrue(it.hasNext()); 
		assertEquals("violino", it.next().getNome());
		assertTrue(it.hasNext()); 
		assertEquals("tromba", it.next().getNome());
		assertFalse(it.hasNext()) ;

	}
	
	@Test
	void testGetSortedSetOrdinatoPerPesoOggettiSparsi() {
		SortedSet<Attrezzo> ordinato = borsaSparsa.getSortedSetOrdinatoPerPeso(); 
		
		Iterator<Attrezzo> it = ordinato.iterator(); 
		assertTrue(it.hasNext()); 
		assertEquals("piuma", it.next().getNome());
		assertTrue(it.hasNext()); 
		assertEquals("libro", it.next().getNome());
		assertTrue(it.hasNext()); 
		assertEquals("ps", it.next().getNome());
		assertTrue(it.hasNext()); 
		assertEquals("piombo", it.next().getNome());
		assertFalse(it.hasNext()) ;
		
	}
	
	

}
