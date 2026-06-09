package it.uniroma3.diadia.ambienti;

import static it.uniroma3.diadia.ambienti.Direzione.EST;
import static it.uniroma3.diadia.ambienti.Direzione.NORD;
import static it.uniroma3.diadia.ambienti.Direzione.OVEST;
import static it.uniroma3.diadia.ambienti.Direzione.SUD;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaProtectedTest {

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
			stanza1.impostaStanzaAdiacente(NORD, null);
			assertNull(stanza1.getStanzaAdiacente(NORD));	
			}
		
		@Test
		void testImpostaStanzaAdiacenteUnaStanzaAdiacente() {
			stanza1.impostaStanzaAdiacente(NORD, adiacente);
			stanza1.impostaStanzaAdiacente(NORD, adiacente2);
			assertEquals(stanza1.getStanzaAdiacente(NORD),adiacente2);	
			
		}
		
		@Test
		void testImpostaStanzaAdiacenteStessaStanzaAdiacente() {
			stanza1.impostaStanzaAdiacente(NORD, adiacente);
			stanza1.impostaStanzaAdiacente(SUD, adiacente);
			assertEquals(stanza1.getStanzaAdiacente(SUD),adiacente);
			assertEquals(stanza1.getStanzaAdiacente(SUD),adiacente);
		}
		
		@Test 
		void testGetStanzaAdiacenteNessunaStanzaAdiacente() {
			assertNull(stanza1.getStanzaAdiacente(NORD));
		}
		
		@Test 
		void testGetStanzaAdiacenteUnaStanzaAdiacente() {
			stanza1.impostaStanzaAdiacente(NORD, adiacente);
			assertEquals(stanza1.getStanzaAdiacente(NORD),adiacente);
		}
		
		@Test
		void testGetStanzaAdiacenteSovrascriviStanza() {
			stanza1.impostaStanzaAdiacente(NORD, adiacente);
			stanza1.impostaStanzaAdiacente(NORD, adiacente2);
			assertEquals(stanza1.getStanzaAdiacente(NORD),adiacente2);
		}
		
		@Test 
		void testGetNome() {
			assertEquals(stanza1.getNome(),"stanza 1");
		}
		
		@Test
		void testGetAttrezziNessunAttrezzo() {
			assertEquals(0, stanza1.getAttrezzi().size()) ;
		}
		
		@Test
		void testGetAttrezziUnAttrezzo() {
			stanza1.addAttrezzo(padella);
			assertEquals(stanza1.getAttrezzo("padella"),padella);
			assertEquals(stanza1.getAttrezzi().size(), 1);
		}
		
		@Test
		void testGetAttrezziDueAttrezzi() {
			stanza1.addAttrezzo(padella);
			stanza1.addAttrezzo(computer);
			assertEquals(stanza1.getAttrezzo("padella"),padella);
			assertEquals(stanza1.getAttrezzi().get("computer"),computer);
			assertEquals(2, stanza1.getAttrezzi().size()) ;

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
			assertEquals(stanza1.getDirezioni().size(),0); 
			}
		

		@Test
		void testGetDirezioniUnaDirezione() {
			stanza1.impostaStanzaAdiacente(NORD, adiacente);
			assertTrue(stanza1.getDirezioni().contains(NORD));
		}
		
		@Test
		void testGetDirezioniPiuDirezioni() {
			stanza1.impostaStanzaAdiacente(NORD, adiacente);
			stanza1.impostaStanzaAdiacente(SUD, adiacente);
			stanza1.impostaStanzaAdiacente(EST, adiacente);
			stanza1.impostaStanzaAdiacente(OVEST, adiacente);
			assertTrue(stanza1.getDirezioni().contains(NORD));
			assertTrue(stanza1.getDirezioni().contains(SUD));
			assertTrue(stanza1.getDirezioni().contains(EST));
			assertTrue(stanza1.getDirezioni().contains(OVEST));
		}

}
