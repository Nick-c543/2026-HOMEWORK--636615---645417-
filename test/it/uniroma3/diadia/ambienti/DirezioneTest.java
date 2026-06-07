package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import static it.uniroma3.diadia.ambienti.Direzione.*;  

import org.junit.jupiter.api.Test;

class DirezioneTest {

	@Test
	void testOrdinal() {
		assertEquals(0, NORD.ordinal()); 
		assertEquals(1, EST.ordinal()); 
		assertEquals(2, SUD.ordinal());
		assertEquals(3, OVEST.ordinal());
	}
	
	@Test
	void testGetDeclaringClass() {
		assertSame(Direzione.class, NORD.getDeclaringClass());
		assertNotSame(Direzione.class, NORD.getClass());
		assertNotSame(EST.getClass(), NORD.getClass());
	}
	
	@Test
	void testValueOf() {
		assertSame(NORD, Direzione.valueOf("NORD"));
		assertSame(EST, Direzione.valueOf("EST")); 
		assertSame(SUD, Direzione.valueOf("SUD"));
		assertSame(OVEST, Direzione.valueOf("OVEST")); 
	}
	
	@Test
	void testCompareTo() {
		assertTrue(NORD.compareTo(EST)<0);
		assertTrue(EST.compareTo(SUD)<0);
		assertTrue(SUD.compareTo(OVEST)<0);
		assertTrue(OVEST.compareTo(NORD)>0);
	}
	
	@Test
	void testValues() {
		final Direzione[] expected = {NORD, EST, SUD, OVEST}; 
		assertArrayEquals(expected, Direzione.values()) ;
		
	}

}
