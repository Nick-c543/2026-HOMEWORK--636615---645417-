package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;
//import it.uniroma3.diadia.ambienti.*; 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;

class CaricatoreLabirintoTest {
	private CaricatoreLabirinto c; 

	@BeforeEach
	void setUp() throws Exception {
		c = new CaricatoreLabirinto("labirinto1.txt");
	}

	@Test
	void testCaricatoreLabirintoFileNonTrovatoLanciaEccezione() {
		FileNotFoundException e = assertThrows(FileNotFoundException.class ,() -> {
			
			new CaricatoreLabirinto("labirints.txt"); 
			
		}); 

	}
	
	@Test
	void testCaricaLabirinto1VerificaBase() throws FormatoFileNonValidoException {
		c.carica();
		assertEquals("N10", c.getStanzaIniziale().getNome()); 
		assertEquals("N11", c.getStanzaVincente().getNome());

	}
	
	@Test
	void testCaricaLabirinto1VerificaUscite() throws FormatoFileNonValidoException{
		c.carica();
		Stanza entrata = c.getStanzaIniziale(); 
		Stanza uscita = c.getStanzaVincente(); 
		Stanza biblioteca = c.getStanza("biblioteca"); 
		
		assertEquals(entrata, biblioteca.getStanzaAdiacente(Direzione.NORD)); 
		assertEquals(uscita, biblioteca.getStanzaAdiacente(Direzione.SUD)); 
	}
	
	@Test
	void testCaricaLabirinto1VerificaAttrezzi() throws FormatoFileNonValidoException {
		c.carica();
		Stanza entrata = c.getStanzaIniziale(); 
		Stanza biblioteca = c.getStanza("biblioteca"); 
		
		assertTrue(entrata.hasAttrezzo("pinza")); 
		assertTrue(biblioteca.hasAttrezzo("martello"));
		
	}
	
	@Test
	void testCaricaLabirintoVerificaStanzaBloccata() throws FormatoFileNonValidoException {
		String labirintoBloccate = 
				"Stanze: Atrio\n" +
						"Stanze bloccate: Prigione nord chiave\n" +
						"Stanze buie:\n" +
						"Stanze magiche:\n" +
						"Inizio: Prigione\n" +
						"Vincente: Atrio\n" +
						"Attrezzi:\n" +
						"Personaggi:\n" +
						"Uscite:\n";
		CaricatoreLabirinto c = new CaricatoreLabirinto(new java.io.StringReader(labirintoBloccate)); 
		c.carica(); 
		assertEquals(StanzaBloccata.class, c.getStanzaIniziale().getClass()); 
		
	}
	
	@Test
	void testCaricaLabirintoVerificaStanzaBuia() throws FormatoFileNonValidoException {
		String labirintoBloccate = 
				"Stanze: Atrio\n" +
						"Stanze bloccate: Prigione nord chiave\n" +
						"Stanze buie: Cinema torcia\n" +
						"Stanze magiche:\n" +
						"Inizio: Cinema\n" +
						"Vincente: Atrio\n" +
						"Attrezzi:\n" +
						"Personaggi:\n" +
						"Uscite:\n";
		CaricatoreLabirinto c = new CaricatoreLabirinto(new java.io.StringReader(labirintoBloccate)); 
		c.carica(); 
		assertEquals(StanzaBuia.class, c.getStanzaIniziale().getClass()); 
		
	}
	
	@Test
	void testCaricaLabirintoVerificaStanzaMagica() throws FormatoFileNonValidoException {
		String labirintoBloccate = 
				"Stanze: Atrio\n" +
						"Stanze bloccate: Prigione nord chiave\n" +
						"Stanze buie: Cinema torcia\n" +
						"Stanze magiche: magica 2\n" +
						"Inizio: magica\n" +
						"Vincente: Atrio\n" +
						"Attrezzi:\n" +
						"Personaggi:\n" +
						"Uscite:\n";
		CaricatoreLabirinto c = new CaricatoreLabirinto(new java.io.StringReader(labirintoBloccate)); 
		c.carica(); 
		assertEquals(StanzaMagica.class, c.getStanzaIniziale().getClass()); 
		
	}
	
	@Test
	void testCaricaLabirintoVerificaPersonaggio() throws FormatoFileNonValidoException {
		String labirintoBloccate = 
				"Stanze: Atrio\n" +
						"Stanze bloccate: Prigione nord chiave\n" +
						"Stanze buie: Cinema torcia\n" +
						"Stanze magiche: magica 2\n" +
						"Inizio: Atrio\n" +
						"Vincente: Atrio\n" +
						"Attrezzi:\n" +
						"Personaggi: Cane Fido Bau! Atrio\n" +
						"Uscite:\n";
		CaricatoreLabirinto c = new CaricatoreLabirinto(new java.io.StringReader(labirintoBloccate)); 
		c.carica(); 
		assertNotNull(c.getStanzaIniziale().getPersonaggio()); 
		
	}
	
	@Test
	void testCaricaLabirintoStanzaInesistenteInAttrezzi() {
		FormatoFileNonValidoException e = assertThrows(FormatoFileNonValidoException.class, () -> {
			CaricatoreLabirinto cSbagliato = new CaricatoreLabirinto("labirintoSbagliatoStanzaInesistente.txt");
			cSbagliato.carica(); 
			
		});
	}
	
	@Test
	void testCaricaLabirintoFormatoPesoAttrezzoSbagliato() {
		FormatoFileNonValidoException e = assertThrows(FormatoFileNonValidoException.class, () -> {
			CaricatoreLabirinto cSbagliato = new CaricatoreLabirinto("labirintoSbagliatoPesoNonValido.txt");
			cSbagliato.carica(); 
		});
		
	}
	
	@Test
	void testCaricaLabirintoMarkerMancante() {
		System.out.println("Java sta cercando i file qui: " + this.getClass().getResource("/"));
		FormatoFileNonValidoException e = assertThrows(FormatoFileNonValidoException.class, () -> {
			CaricatoreLabirinto caricatore = new CaricatoreLabirinto("labirintoSbagliatoMarkerMancante.txt");
			caricatore.carica(); 
		});
	}
	
	@Test
	void testCaricaLabirintoMonolocale() throws FormatoFileNonValidoException {
		String labirintoMonolocale = 
				"Stanze: Atrio\n" +
				"Stanze bloccate:\n" +
				"Stanze buie:\n" +
				"Stanze magiche:\n" +
				"Inizio: Atrio\n" +
				"Vincente: Atrio\n" +
				"Attrezzi:\n" +
				"Personaggi:\n" +
				"Uscite:\n";
		
		CaricatoreLabirinto cMono = new CaricatoreLabirinto(new java.io.StringReader(labirintoMonolocale));
		cMono.carica();
		
		assertEquals("Atrio", cMono.getStanzaIniziale().getNome());
		assertEquals("Atrio", cMono.getStanzaVincente().getNome());
	}
	
	@Test
	void testCaricaLabirintoBilocale() throws FormatoFileNonValidoException {
		String labirintoBilocale = 
				"Stanze: Ingresso, Salone\n" +
				"Stanze bloccate:\n" +
				"Stanze buie:\n" +
				"Stanze magiche:\n" +
				"Inizio: Ingresso\n" +
				"Vincente: Salone\n" +
				"Attrezzi:\n" +
				"Personaggi:\n" +
				"Uscite: Ingresso nord Salone\n";
		
		CaricatoreLabirinto cBilo = new CaricatoreLabirinto(new java.io.StringReader(labirintoBilocale));
		cBilo.carica();
		
		assertEquals("Ingresso", cBilo.getStanzaIniziale().getNome());
		assertEquals("Salone", cBilo.getStanzaVincente().getNome());
		
		Stanza ingresso = cBilo.getStanzaIniziale();
		assertEquals("Salone", ingresso.getStanzaAdiacente(Direzione.NORD).getNome());
	}
	

}
