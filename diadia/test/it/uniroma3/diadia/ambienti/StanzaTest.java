package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaTest {
	
	static final private int NUMERO_MASSIMO_ATTREZZI = 10;
	private Stanza stanza1;
	private Stanza adiacente; 
	private Stanza adiacente2;
	private Attrezzo padella; 
	private Attrezzo computer; 

	@BeforeEach
	void setUp() throws Exception {
		this.stanza1 = new Stanza("stanza 1");
		this.adiacente = new Stanza("adiacente");
		this.adiacente2 = new Stanza("adiacente2");
		this.padella = new Attrezzo("padella",2);
		this.computer = new Attrezzo("computer",3);

	}
	

	@Test
	void testImpostaStanzaAdiacenteImpostaNull() {
		stanza1.impostaStanzaAdiacente("nord", null);
		assertNull(stanza1.getStanzaAdiacente("nord"));	
		}
	
	@Test
	void testImpostaStanzaAdiacenteUnaStanzaAdiacente() {
		stanza1.impostaStanzaAdiacente("nord", adiacente);
		stanza1.impostaStanzaAdiacente("sud", adiacente2);
		assertEquals(stanza1.getStanzaAdiacente("sud"),adiacente2);	
		
	}
	
	@Test
	void testImpostaStanzaAdiacenteStessaStanzaAdiacente() {
		stanza1.impostaStanzaAdiacente("nord", adiacente);
		stanza1.impostaStanzaAdiacente("sud", adiacente);
		assertEquals(stanza1.getStanzaAdiacente("sud"),adiacente);
		assertEquals(stanza1.getStanzaAdiacente("nord"),adiacente);
	}
	
	@Test 
	void testGetStanzaAdiacenteNessunaStanzaAdiacente() {
		assertNull(stanza1.getStanzaAdiacente("nord"));
	}
	
	@Test 
	void testGetStanzaAdiacenteUnaStanzaAdiacente() {
		stanza1.impostaStanzaAdiacente("nord", adiacente);
		assertEquals(stanza1.getStanzaAdiacente("nord"),adiacente);
	}
	
	@Test
	void testGetStanzaAdiacenteSovrascriviStanza() {
		stanza1.impostaStanzaAdiacente("nord", adiacente);
		stanza1.impostaStanzaAdiacente("nord", adiacente2);
		assertEquals(stanza1.getStanzaAdiacente("nord"),adiacente2);
	}
	
	@Test 
	void testGetNome() {
		assertEquals(stanza1.getNome(),"stanza 1");
	}
	
	@Test
	void testGetAttrezziNessunAttrezzo() {
		for (int i=0; i<NUMERO_MASSIMO_ATTREZZI; i++) {
			assertNull(stanza1.getAttrezzi()[i]);
		}
	}
	
	@Test
	void testGetAttrezziUnAttrezzo() {
		stanza1.addAttrezzo(padella);
			assertEquals(stanza1.getAttrezzi()[0],padella);
		for (int i=1; i<NUMERO_MASSIMO_ATTREZZI; i++) {
			assertNull(stanza1.getAttrezzi()[i]);
		}
	}
	
	@Test
	void testGetAttrezziDueAttrezzi() {
		stanza1.addAttrezzo(padella);
		stanza1.addAttrezzo(computer);
			assertEquals(stanza1.getAttrezzi()[0],padella);
			assertEquals(stanza1.getAttrezzi()[1],computer);
		for (int i=2; i<NUMERO_MASSIMO_ATTREZZI; i++) {
			assertNull(stanza1.getAttrezzi()[i]);
		}
	}
	
	@Test
	void testHasAttrezzoNoAttrezzi() {
		assertFalse(stanza1.hasAttrezzo("padella"));
	}
	
	@Test
	void testHasAttrezzoNonHaQuello() {
		stanza1.addAttrezzo(computer);
		assertFalse(stanza1.hasAttrezzo("padella"));
	}
	
	@Test
	void testHasAttrezzoSi() {
		stanza1.addAttrezzo(computer);
		assertTrue(stanza1.hasAttrezzo("computer"));
	}
	
	@Test 
	void testGetAttrezzoNull() {
		assertNull(stanza1.getAttrezzo("computer"));
	}

	@Test 
	void testGetAttrezzoCeLHa() {
		stanza1.addAttrezzo(computer);
		assertEquals(stanza1.getAttrezzo("computer"),computer);
	}
	
	@Test
	void testGetAttrezzoCeLHaDueVolte() {
		stanza1.addAttrezzo(computer);
		stanza1.addAttrezzo(computer);
		assertEquals(stanza1.getAttrezzo("computer"),computer);
	}
	

	@Test
	void testRemoveAttrezzoNonCeLHa() {
		assertNull(stanza1.removeAttrezzo("computer")); 
	}
	
	@Test
	void testRemoveAttrezzoCeLHa() {
		stanza1.addAttrezzo(computer);
		assertEquals(stanza1.removeAttrezzo("computer"),computer); 
		assertFalse(stanza1.hasAttrezzo("computer")); 
	}
	
	@Test
	void testGetDirezioniNessunaDirezione() {
		assertEquals(stanza1.getDirezioni().length,0); 
		}
	

	@Test
	void testGetDirezioniUnaDirezione() {
		stanza1.impostaStanzaAdiacente("nord", adiacente);
		assertEquals(stanza1.getDirezioni()[0],"nord");
	}
	
	@Test
	void testGetDirezioniPiuDirezioni() {
		stanza1.impostaStanzaAdiacente("nord", adiacente);
		stanza1.impostaStanzaAdiacente("sud", adiacente);
		stanza1.impostaStanzaAdiacente("est", adiacente);
		stanza1.impostaStanzaAdiacente("ovest", adiacente);
		assertEquals(stanza1.getDirezioni()[0],"nord");
		assertEquals(stanza1.getDirezioni()[1],"sud");
		assertEquals(stanza1.getDirezioni()[2],"est");
		assertEquals(stanza1.getDirezioni()[3],"ovest");
	}
	
	
	
}
