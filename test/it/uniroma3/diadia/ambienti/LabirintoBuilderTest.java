package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.personaggi.Cane;

class LabirintoBuilderTest {
	
	private Labirinto.LabirintoBuilder builder; 
	private String nomeStanzaIniziale = "Atrio";
	private String nomeStanzaVincente = "Uscita";
	
	@BeforeEach
	void setUp() throws Exception {
		builder = Labirinto.newBuilder(); 
	}

	@Test
	void testAddStanzaInizialeEVincente() throws StanzaNotFoundException {
		Labirinto lab = builder.addStanzaIniziale("ingresso")
				.addStanzaVincente("uscita")
				.getLabirinto(); 
		assertEquals("ingresso", lab.getEntrata().getNome());
		assertEquals("uscita", lab.getUscita().getNome()); 
	}
	
	@Test 
	void testAddStanzaInizialeEVincenteNullEccezione() {
		assertThrows(IllegalArgumentException.class, () -> {
			builder.addStanzaIniziale(null);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			builder.addStanzaVincente(null);
		});
	}
	
	@Test
	void testAddStanzaEAddAdiacenza() throws StanzaNotFoundException {
		Labirinto lab = builder.addStanzaIniziale("ingresso")
				.addStanzaVincente("uscita")
				.addStanza("cucina")
				.addAdiacenza("ingresso", "cucina", Direzione.EST)
				.getLabirinto(); 
		Stanza ingresso = lab.getEntrata();
		assertEquals("cucina", ingresso.getStanzaAdiacente(Direzione.EST).getNome()); 
	}
	
	@Test
	void testAddStanzaNullEccezione() {
		assertThrows(IllegalArgumentException.class, () -> {
			builder.addStanza(null);
		});
	}
	
	@Test
	void testAddAttrezzoTreParametri() throws StanzaNotFoundException {
		Labirinto lab = builder.addStanzaIniziale("ingresso")
				.addStanzaVincente("uscita")
				.addAttrezzo("appendiabiti", 5, "ingresso")
				.getLabirinto(); 
		Stanza ingresso = lab.getEntrata();
		assertTrue(ingresso.hasAttrezzo("appendiabiti")); 
	}
	
	@Test
	void testAddAttrezzoTreParametriEccezioneStanzaNonTrovata() {
		assertThrows(StanzaNotFoundException.class, () -> {
			builder.addAttrezzo("appendiabiti", 5, "ingresso");
		});
	}
	
	@Test 
	void testAddAttrezzoDueParametri() throws StanzaNotFoundException {
		Labirinto lab = builder.addStanzaIniziale("ingresso")
				.addStanzaVincente("uscita")
				.addAttrezzo("chiavi", 1)
				.getLabirinto(); 
		assertTrue(lab.getUscita().hasAttrezzo("chiavi"));
	}
	
	@Test
	void testAddAttrezzoDueParametriEccezioneNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			builder.addAttrezzo(null, 0); 
		}); 
	}
	
	@Test 
	void testAddAdiacenzaEccezioneStanzaNotFound() {
		assertThrows(StanzaNotFoundException.class, () -> {
			builder.addAdiacenza("cucina", "soggiorno", Direzione.SUD); 
		});
	}
	
	@Test
	void testLabirintoMonolocale() throws StanzaNotFoundException {
		Labirinto monolocale = builder
				.addStanzaIniziale("salotto") 
				.addStanzaVincente("salotto") 
				.getLabirinto(); 
		assertEquals("salotto", monolocale.getEntrata().getNome());
		assertEquals("salotto", monolocale.getUscita().getNome()); 
	}
	
	@Test
	void testLabirintoBilocale() throws StanzaNotFoundException {
		Labirinto bilocale = builder 
				.addStanzaIniziale("salotto")
				.addStanzaVincente("camera")
				.addAttrezzo("letto",10) 
				.addAdiacenza("salotto", "camera", Direzione.NORD) 
				.getLabirinto(); 
		
		assertEquals("salotto", bilocale.getEntrata().getNome());
		assertEquals("camera", bilocale.getUscita().getNome());
		assertTrue(bilocale.getUscita().hasAttrezzo("letto")); 
		assertEquals("camera", bilocale.getEntrata().getStanzaAdiacente(Direzione.NORD).getNome()); 
	}
	
	@Test
	void testLabirintoTrilocale() throws StanzaNotFoundException {
		Labirinto trilocale = builder
				.addStanzaIniziale("salotto")
				.addStanza("cucina")
				.addAttrezzo("pentola",1) 
				.addStanzaVincente("camera")
				.addAdiacenza("salotto", "cucina", Direzione.NORD)
				.addAdiacenza("cucina", "camera", Direzione.EST)
				.getLabirinto(); 
		
		assertEquals("salotto", trilocale.getEntrata().getNome());
		assertEquals("camera", trilocale.getUscita().getNome());
		assertEquals("cucina", trilocale.getEntrata().getStanzaAdiacente(Direzione.NORD).getNome()); 
		Stanza cucina = trilocale.getEntrata().getStanzaAdiacente(Direzione.NORD); 
		assertTrue(cucina.hasAttrezzo("pentola")); 
		assertEquals("camera", cucina.getStanzaAdiacente(Direzione.EST).getNome()); 
		
		
	}
	
	@Test
	public void testImpostaStanzaInizialeCambiandola() throws StanzaNotFoundException {
		Labirinto maze = builder
				.addStanzaIniziale(this.nomeStanzaIniziale)
				.addStanza("nuova iniziale")
				.addStanzaIniziale("nuova iniziale")
				.addStanzaVincente(nomeStanzaVincente)
				.getLabirinto();
		assertEquals("nuova iniziale",maze.getEntrata().getNome());
	}
	
	@Test
	public void testAggiuntaAttrezzoAStanze_AppenaAggiunteMultiple() throws StanzaNotFoundException {
		String nomeAttrezzo = "attrezzo";
		int peso = 1;
		String nomeStanza = "stanza 1";
		this.builder
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanza(nomeStanza)
				.addAttrezzo(nomeAttrezzo, peso)
				.addStanzaVincente(nomeStanzaVincente)
				.getLabirinto();
		Attrezzo attrezzo = new Attrezzo(nomeAttrezzo,peso);
		Map<String, Attrezzo> attrezzi = builder.getListaStanze().get(nomeStanza).getAttrezzi();
		assertEquals(attrezzo,attrezzi.get(attrezzo.getNome()));
	}
	
	@Test
	public void testAggiuntaAttrezzoAStanze_MoltepliciAttrezziStessaStanza() {
		String nomeAttrezzo1 = "attrezzo 1";
		String nomeAttrezzo2 = "attrezzo 2";
		int peso1 = 1;
		int peso2 = 2;
		String nomeStanza1 = "Stanza 1";
		this.builder
		.addStanza(nomeStanza1)
		.addAttrezzo(nomeAttrezzo1, peso1)
		.addAttrezzo(nomeAttrezzo2, peso2);
		Map<String, Stanza> listaStanze = builder.getListaStanze();
		assertEquals(new Attrezzo(nomeAttrezzo2,peso2),listaStanze.get(nomeStanza1).getAttrezzo(nomeAttrezzo2));
		assertEquals(new Attrezzo(nomeAttrezzo1,peso1),listaStanze.get(nomeStanza1).getAttrezzo(nomeAttrezzo1));
	}
	
	@Test
	public void testAddPersonaggio() throws StanzaNotFoundException {
		Labirinto lab = this.builder
		.addStanzaIniziale("atrio")
		.addStanzaVincente("atrio")
		.addPersonaggio(new Cane("Fido", "Bau!"), "atrio")
		.getLabirinto(); 
		assertNotNull(lab.getUscita().getPersonaggio()); 
	}
	
	@Test
	public void testAddPersonaggioEccezioneStanzaNonTrovata(){
		assertThrows(StanzaNotFoundException.class, () -> {
			builder.addPersonaggio(new Cane("Fido", "Bau!"), "atrio");
		}); 
	}
	
	@Test
	public void testLabirintoConStanzaMagica() {
		int sogliaMagica = 1;
		String nomeStanzaMagica = "Stanza Magica";
		this.builder
		.addStanzaMagica(nomeStanzaMagica, sogliaMagica);
		StanzaMagica stanzaMagica = (StanzaMagica)builder.getListaStanze().get(nomeStanzaMagica);
		assertTrue(stanzaMagica.isMagica());
	}
	
	@Test
	public void testLabirintoConStanzaMagica_AggiuntaElementoOltreSogliaMagica() {
		String nomeAttrezzo1 = "attrezzo 1";
		String nomeAttrezzo2 = "attrezzo 2";
		String nomeAttrezzo2Inv = "2 ozzertta";
		int sogliaMagica = 1;
		int peso1 = 1;
		int peso2 = 2;
		int peso2_x2 = peso2*2;
		String nomeStanzaMagica = "Stanza Magica";
		this.builder
		.addStanzaMagica(nomeStanzaMagica, sogliaMagica)
		.addAttrezzo(nomeAttrezzo1, peso1)
		.addAttrezzo(nomeAttrezzo2, peso2);
		Map<String, Stanza> listaStanze = builder.getListaStanze();
		assertEquals(new Attrezzo(nomeAttrezzo2Inv,peso2_x2), listaStanze.get(nomeStanzaMagica).getAttrezzo(nomeAttrezzo2Inv));
		assertEquals(new Attrezzo(nomeAttrezzo1,peso1), listaStanze.get(nomeStanzaMagica).getAttrezzo(nomeAttrezzo1));
	}
	
	@Test
	public void testLabirintoConStanzaBloccata_ConPassepartout() throws StanzaNotFoundException {
		this.builder
		.addStanzaIniziale(nomeStanzaIniziale)
		.addStanzaBloccata("stanza bloccata", Direzione.NORD, "chiave").addAttrezzo("chiave", 1)
		.addAdiacenza(nomeStanzaIniziale, "stanza bloccata", Direzione.NORD)
		.addAdiacenza("stanza bloccata", nomeStanzaIniziale, Direzione.SUD)
		.addStanzaVincente(nomeStanzaVincente)
		.addAdiacenza("stanza bloccata", nomeStanzaVincente, Direzione.NORD)
		.addAdiacenza(nomeStanzaVincente, "stanza bloccata", Direzione.SUD);
		//Asserisce che in presenza di passepartout, invocato il metodo getStanzaAdiacente(), la stanza bloccata ritorna la corretta adiacenza
		assertEquals(nomeStanzaVincente,builder.getListaStanze().get("stanza bloccata").getStanzaAdiacente(Direzione.NORD).getNome());	
	}
	
	@Test
	public void testLabirintoConStanzaBloccata_SenzaPassepartout() throws StanzaNotFoundException {
		this.builder
		.addStanzaIniziale(nomeStanzaIniziale)
		.addStanzaBloccata("stanza bloccata", Direzione.NORD, "chiave")
		.addAdiacenza(nomeStanzaIniziale, "stanza bloccata", Direzione.NORD)
		.addAdiacenza("stanza bloccata", nomeStanzaIniziale, Direzione.SUD)
		.addStanzaVincente(nomeStanzaVincente)
		.addAdiacenza("stanza bloccata", nomeStanzaVincente, Direzione.NORD)
		.addAdiacenza(nomeStanzaVincente, "stanza bloccata", Direzione.SUD);
		//Asserisce che in caso di mancanza di passepartout, invocato il metodo getStanzaAdiacente(), la stanza bloccata ritorna se stessa
		assertEquals("stanza bloccata",builder.getListaStanze().get("stanza bloccata").getStanzaAdiacente(Direzione.NORD).getNome());
	}
	
	@Test
	public void testLabirintoCompletoConTutteLeStanze() throws StanzaNotFoundException {
		
		Labirinto labirintoCompleto = this.builder
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanzaVincente(nomeStanzaVincente)
				.addStanza("corridoio")
				.addAttrezzo("chiave", 1)
				.addAttrezzo("lanterna", 1)
				.addStanzaBloccata("corridoio bloccato", Direzione.NORD,"chiave")
				.addStanzaMagica("stanza magica", 1)
				.addStanzaBuia("stanza buia","lanterna")
				.addStanza("Aula 1")
				.addAdiacenza(nomeStanzaIniziale, "corridoio", Direzione.NORD)
				.addAdiacenza("corridoio", nomeStanzaIniziale, Direzione.SUD)
				.addAdiacenza("corridoio", "corridoio bloccato", Direzione.NORD)
				.addAdiacenza("corridoio bloccato", "corridoio", Direzione.SUD)
				.addAdiacenza("corridoio bloccato", "Aula 1", Direzione.NORD)
				.addAdiacenza("Aula 1", "corridoio bloccato", Direzione.SUD)
				.addAdiacenza("Aula 1", nomeStanzaVincente,Direzione.NORD)
				.addAdiacenza(nomeStanzaVincente, "Aula 1", Direzione.SUD)
				.addAdiacenza("corridoio", "stanza magica", Direzione.EST)
				.addAdiacenza("stanza magica", "corridoio", Direzione.OVEST)
				.addAdiacenza("corridoio", "stanza buia", Direzione.OVEST)
				.addAdiacenza("stanza buia", "corridoio", Direzione.EST)
				.getLabirinto();
		assertEquals(nomeStanzaIniziale, labirintoCompleto.getEntrata().getNome());
		assertEquals(nomeStanzaVincente, labirintoCompleto.getUscita().getNome());
		Stanza corridoio = labirintoCompleto.getEntrata().getStanzaAdiacente(Direzione.NORD);
		assertEquals("corridoio",corridoio.getNome());
		//assertTrue(corridoio.getDirezioni().containsAll(Arrays.asList("ovest","est","nord","sud")));
		Map<Direzione,Stanza> mapAdiacenti = new HashMap<>();
		mapAdiacenti.put(Direzione.NORD,new Stanza("corridoio bloccato"));
		mapAdiacenti.put(Direzione.SUD,new Stanza(nomeStanzaIniziale));
		mapAdiacenti.put(Direzione.EST,new Stanza("stanza magica"));
		mapAdiacenti.put(Direzione.OVEST,new Stanza("stanza buia"));
		//assertEquals(mapAdiacenti,corridoio.getMapStanzeAdiacenti());
		Attrezzo a1 = new Attrezzo("chiave",1);
		Attrezzo a2 = new Attrezzo("lanterna",1);
		Map<String, Attrezzo> mappaAttrezzi = new HashMap<>(); 
		mappaAttrezzi.put("chiave", a1); 
		mappaAttrezzi.put("lanterna", a2); 
		assertEquals(mappaAttrezzi,corridoio.getAttrezzi());
	}
	
}
