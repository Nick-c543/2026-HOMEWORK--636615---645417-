package it.uniroma3.diadia.ambienti;

import java.io.*;
import java.util.*;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.personaggi.*;

/**
 * Legge e costruisce un labirinto a partire da un file di testo o da un reader. 
 * Utilizza Builder per istanziare le stanze, posizionare gli attrezzi, i personaggi 
 * e impostare le adiacenze. I file di testo devono rispettare una sintassi specifica. 
 * 
 * @author Margherita Manzi
 * @version aggiornata
 */
public class CaricatoreLabirinto {
	
	/**
	 * Marker per il file di testo
	 */
	private static final String STANZE_MARKER = "Stanze:";
	private static final String STANZA_INIZIALE_MARKER = "Inizio:";
	private static final String STANZA_VINCENTE_MARKER = "Vincente:";
	private static final String ATTREZZI_MARKER = "Attrezzi:";
	private static final String USCITE_MARKER = "Uscite:";
	private static final String STANZE_BUIE_MARKER = "Stanze buie:"; 
	private static final String STANZE_MAGICHE_MARKER = "Stanze magiche:"; 
	private static final String STANZE_BLOCCATE_MARKER = "Stanze bloccate:"; 
	private static final String PERSONAGGI_MARKER = "Personaggi:"; 

	private LineNumberReader reader;
	private Labirinto.LabirintoBuilder builder;
	private Labirinto labirintoPronto;

	/**
	 * Costruttore del caricatore leggendo un file di testo
	 * @param nomeFile
	 * @throws FileNotFoundException
	 */
	public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException {
		this.builder = Labirinto.newBuilder();
		InputStream is = getClass().getResourceAsStream("/" + nomeFile);
		if (is == null) throw new FileNotFoundException("Impossibile trovare il file: " + nomeFile);
		this.reader = new LineNumberReader(new InputStreamReader(is));
	}
	
	/**
	 * Costruttore del caricatore tramite reader
	 * @param reader
	 */
	public CaricatoreLabirinto(java.io.Reader reader) {
		this.builder = Labirinto.newBuilder(); 
		this.reader = new java.io.LineNumberReader(reader);
	}
	
	/**
	 * Avvia la procedura di parsing del file di testo. 
	 * Legge la sequanza di righe e delega la costruzione al LabirintoBuilder
	 * @throws FormatoFileNonValidoException
	 */
	public void carica() throws FormatoFileNonValidoException {
		try {
			this.leggiECreaStanze();
			this.leggiECreaStanzeBloccate();
			this.leggiECreaStanzeBuie();
			this.leggiECreaStanzeMagiche();
			this.leggiInizialeEvincente();
			this.leggiECollocaAttrezzi();
			this.leggiECollocaPersonaggi();
			this.leggiEImpostaUscite();
			
			this.labirintoPronto = this.builder.getLabirinto();
			
		} catch (StanzaNotFoundException e) {
			throw new FormatoFileNonValidoException("Errore logico nel labirinto: " + e.getMessage());
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}

	private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
		try {
			String riga = this.reader.readLine();
			check(riga != null && riga.startsWith(marker), "era attesa una riga che cominciasse per " + marker);
			return riga.substring(marker.length()).trim();
		} catch (IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}
	}

	private void leggiECreaStanze() throws FormatoFileNonValidoException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MARKER);
		for(String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
			this.builder.addStanza(nomeStanza); 
		}
	}
	
	private void leggiECreaStanzeBloccate() throws FormatoFileNonValidoException  {
		String nomiStanzeBloccate = this.leggiRigaCheCominciaPer(STANZE_BLOCCATE_MARKER);
		for(String bloccata : separaStringheAlleVirgole(nomiStanzeBloccate)) {
			try(Scanner scanner = new Scanner(bloccata)){
				
				check(scanner.hasNext(), msgTerminazionePrecoce("il nome della stanza bloccata")); 
				String nome = scanner.next(); 
				
				check(scanner.hasNext(), msgTerminazionePrecoce("la direzione bloccata")); 
				String direzione = scanner.next();
				Direzione dir = Direzione.valueOf(direzione.toUpperCase()); 
				
				check(scanner.hasNext(), msgTerminazionePrecoce("l'attrezzo sbloccante")); 
				String sbloccante = scanner.next(); 
				
				this.builder.addStanzaBloccata(nome, dir, sbloccante); 
			}
		}
	}
	
	private void leggiECreaStanzeBuie() throws FormatoFileNonValidoException {
		String nomiStanzeBuie = this.leggiRigaCheCominciaPer(STANZE_BUIE_MARKER); 
		for(String buia : separaStringheAlleVirgole(nomiStanzeBuie)) {
			try(Scanner scanner = new Scanner(buia)){
				
				check(scanner.hasNext(), msgTerminazionePrecoce("il nome della stanza buia")); 
				String nome = scanner.next();  
				
				check(scanner.hasNext(), msgTerminazionePrecoce("il nome dell'attrezzo")); 
				String nomeAttrezzo = scanner.next(); 
				
				this.builder.addStanzaBuia(nome, nomeAttrezzo); 
			}
		}
	}
	
	private void leggiECreaStanzeMagiche() throws FormatoFileNonValidoException {
		String nomiStanzeMagiche = this.leggiRigaCheCominciaPer(STANZE_MAGICHE_MARKER); 
		for(String magica : separaStringheAlleVirgole(nomiStanzeMagiche)) {
			try(Scanner scanner = new Scanner(magica)){
				
				check(scanner.hasNext(), msgTerminazionePrecoce("il nome della stanza magica")); 
				String nome = scanner.next(); 
				
				check(scanner.hasNext(), msgTerminazionePrecoce("la soglia magica")); 
				int sogliaMagica = Integer.parseInt(scanner.next()); 
				
				this.builder.addStanzaMagica(nome, sogliaMagica); 
			}
		}
	}

	private List<String> separaStringheAlleVirgole(String string) {
		List<String> result = new LinkedList<>();
		String[] frammenti = string.split(",");
		for (String f : frammenti) {
			if (!f.trim().isEmpty()) {
				result.add(f.trim());
			}
		}
		return result;
	}

	private void leggiInizialeEvincente() throws FormatoFileNonValidoException, StanzaNotFoundException {
		String nomeStanzaIniziale = this.leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER);
		this.builder.addStanzaIniziale(nomeStanzaIniziale); 

		String nomeStanzaVincente = this.leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER);
		this.builder.addStanzaVincente(nomeStanzaVincente); 
	}

	private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException, StanzaNotFoundException {
		String specificheAttrezzi = this.leggiRigaCheCominciaPer(ATTREZZI_MARKER);

		for(String specificaAttrezzo : separaStringheAlleVirgole(specificheAttrezzi)) {
			String nomeAttrezzo = null;
			String pesoAttrezzo = null;
			String nomeStanza = null; 
			
			try (Scanner scannerLinea = new Scanner(specificaAttrezzo)) {
				check(scannerLinea.hasNext(), msgTerminazionePrecoce("il nome di un attrezzo."));
				nomeAttrezzo = scannerLinea.next();
				
				check(scannerLinea.hasNext(), msgTerminazionePrecoce("il peso dell'attrezzo " + nomeAttrezzo + "."));
				pesoAttrezzo = scannerLinea.next();
				
				check(scannerLinea.hasNext(), msgTerminazionePrecoce("il nome della stanza."));
				
				nomeStanza = scannerLinea.nextLine().trim();
			}				
			
			try {
				int peso = Integer.parseInt(pesoAttrezzo);
				this.builder.addAttrezzo(nomeAttrezzo, peso, nomeStanza); 
			} catch (NumberFormatException e) {
				check(false, "Peso attrezzo " + nomeAttrezzo + " non valido");
			}
		}
	}
	
	private void leggiECollocaPersonaggi() throws FormatoFileNonValidoException {
		String specifichePersonaggi = this.leggiRigaCheCominciaPer(PERSONAGGI_MARKER);

		for (String specifica : separaStringheAlleVirgole(specifichePersonaggi)) {
			try (Scanner scanner = new Scanner(specifica)) {
				
				check(scanner.hasNext(), msgTerminazionePrecoce("il tipo di personaggio"));
				String tipo = scanner.next();

				check(scanner.hasNext(), msgTerminazionePrecoce("il nome del personaggio"));
				String nome = scanner.next();

				check(scanner.hasNext(), msgTerminazionePrecoce("la presentazione del personaggio"));
				String presentazione = scanner.next();

				check(scanner.hasNext(), msgTerminazionePrecoce("la stanza in cui collocarlo"));
				String nomeStanza = scanner.next(); 

				AbstractPersonaggio personaggio = null;

				switch (tipo.toLowerCase()) {
					case "cane":
						personaggio = new Cane(nome, presentazione);
						break;
					case "strega":
						personaggio = new Strega(nome, presentazione);
						break;
					case "mago":
						personaggio = new Mago(nome, presentazione, new Attrezzo("bacchetta", 1));
						break;
					default:
						check(false, "Tipo di personaggio sconosciuto: " + tipo);
				}

				this.builder.addPersonaggio(personaggio, nomeStanza);

			} catch (StanzaNotFoundException e) {
				throw new FormatoFileNonValidoException("Errore personaggio: " + e.getMessage());
			}
		}
	}

	private void leggiEImpostaUscite() throws FormatoFileNonValidoException, StanzaNotFoundException {
		String specificheUscite = this.leggiRigaCheCominciaPer(USCITE_MARKER);
		
		for(String specificaUscita : separaStringheAlleVirgole(specificheUscite)) {

			String[] parole = specificaUscita.split("\\s+");
			String stanzaPartenza = "";
			String stanzaDestinazione = "";
			String dirStringa = null;
			
			boolean trovataDirezione = false;
			
			for (String parola : parole) {
				if (!trovataDirezione) {
					if (isDirezione(parola)) {
						dirStringa = parola;
						trovataDirezione = true;
					} else {
						stanzaPartenza += parola + " ";
					}
				} else {
					stanzaDestinazione += parola + " ";
				}
			}
			
			// Puliamo gli spazi in eccesso ai lati
			stanzaPartenza = stanzaPartenza.trim();
			stanzaDestinazione = stanzaDestinazione.trim();
			
			check(dirStringa != null, "Direzione mancante o non valida in: " + specificaUscita);
			
			Direzione dir = Direzione.valueOf(dirStringa.toUpperCase()); 
			this.builder.addAdiacenza(stanzaPartenza, stanzaDestinazione, dir); 
		} 
	}
	
	private boolean isDirezione(String parola) {
		try {
			Direzione.valueOf(parola.toUpperCase());
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}
	
	private String msgTerminazionePrecoce(String msg) {
		return "Terminazione precoce del file prima di leggere " + msg;
	}

	final private void check(boolean condizioneCheDeveEsseraVera, String messaggioErrore) throws FormatoFileNonValidoException {
		if (!condizioneCheDeveEsseraVera)
			throw new FormatoFileNonValidoException("Formato file non valido [" + this.reader.getLineNumber() + "] " + messaggioErrore);		
	}

	/**
	 * Restituisce la stanza iniziale del labirinto
	 * @return stanza iniziale
	 */
	public Stanza getStanzaIniziale() {
		return this.labirintoPronto.getEntrata();
	}

	/**
	 * Restituisce la stanza vincente del labirinto
	 * @return stanza vincente
	 */
	public Stanza getStanzaVincente() {
		return this.labirintoPronto.getUscita(); 
	}

	/**
	* Restituisce una stanza creata dal caricatore dato il suo nome.
	* Utile per i test o per ispezionare il labirinto caricato.
	*/
	public Stanza getStanza(String nomeStanza) {
		return this.builder.getListaStanze().get(nomeStanza);
	}
	
}

