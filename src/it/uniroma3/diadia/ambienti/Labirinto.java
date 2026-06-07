package it.uniroma3.diadia.ambienti;


//import lombok.Getter;
//import lombok.Builder;
 
import java.util.HashMap;
import java.util.Map;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import static it.uniroma3.diadia.ambienti.Direzione.*; 

/**
 * Questa classe modella il labirinto per la partita
 * @author: Margherita Manzi e Niccolò Ilari
 * @see Stanza
 * @see Attrezzo
 * @versione base 
 */

public class Labirinto {
	
	private final Stanza entrata; 
	private final Stanza uscita; 
	
	public Labirinto() {
		try {
			Labirinto labirinto = new Labirinto.Builder()
					.addStanzaEntrata("Atrio")
					.addStanzaUscita("Biblioteca")
					.addStanza("Aula N11")
					.addStanza("Aula N10")
					.addStanza("Laboratorio Campus")
					.addAdiacenza("Atrio", "Biblioteca", NORD)
					.addAdiacenza("Atrio", "Aula N11", EST)
					.addAdiacenza("Atrio", "Aula N10", SUD)
					.addAdiacenza("Atrio", "Laboratorio Campus", OVEST)
					.addAdiacenza("Aula N11", "Laboratorio Campus", EST)
					.addAdiacenza("Aula N11", "Atrio", OVEST)
					.addAdiacenza("Aula N10", "Atrio", NORD)
					.addAdiacenza("Aula N10", "Aula N11", EST)
					.addAdiacenza("Aula N10", "Laboratorio Campus", OVEST)
					.addAdiacenza("Laboratorio Campus", "Atrio", EST)
					.addAdiacenza("Laboratorio Campus", "Aula N11", OVEST)
					.addAdiacenza("Biblioteca", "Atrio", SUD)
					.addAttrezzo("lanterna", 3, "Aula N10")
					.addAttrezzo("osso", 1, "Atrio")
					.build();
			
			this.entrata = labirinto.getEntrata();
			this.uscita = labirinto.getUscita();
		} catch (StanzaNotFoundException e) {
			System.err.println("Errore di configurazione nel labirinto di default!"); 
			throw new RuntimeException(e); 
		}
				
	}
	
	public Labirinto(String fileName) {
		try {
			CaricatoreLabirinto c = new CaricatoreLabirinto(fileName);c.carica(); 
			this.entrata = c.getStanzaIniziale(); 
			this.uscita = c.getStanzaVincente();
		} catch (FormatoFileNonValidoException e) {
			System.err.println("Errore di formato file!"); 
			throw new RuntimeException(e); 
		}
	 
	}
	
	/**
	 * Inizializza il labirinto facendo uso di init(), definita sotto
	 */
	private Labirinto(Builder builder) {
		this.entrata = builder.entrata; 
		this.uscita = builder.uscita; 
		//init();
	}
	
	/**
	 * Ritorna l'entrata
	 * @return Stanza
	 */
	public Stanza getEntrata() {
		return entrata;
	}
	/**
	 * Ritorna l'uscita
	 * @return Stanza
	 */
	public Stanza getUscita() {
		return uscita;
	}
	
	
	public static class Builder {
		private Stanza entrata; 
		private Stanza uscita; 
		
		private final Map<String, Stanza> stanze; 
		
		public Builder() {
			this.stanze = new HashMap<>(); 
		}
		
		public Builder addStanzaEntrata(String nomeStanza) {
			Stanza stanza = new Stanza(nomeStanza);
			this.entrata = stanza;
			this.stanze.put(nomeStanza, stanza);
			return this; // Permette la concatenazione (fluent interface)
		}
		
		public Builder addStanzaUscita(String nomeStanza) {
			Stanza stanza = new Stanza(nomeStanza);
			this.uscita = stanza;
			this.stanze.put(nomeStanza, stanza);
			return this;
		}
		
		public Builder addStanza(String nomeStanza) {
			if (!this.stanze.containsKey(nomeStanza)) {
				this.stanze.put(nomeStanza, new Stanza(nomeStanza));
			}
			return this;
		}
		
		public Builder addAttrezzo(String nomeAttrezzo, int peso, String nomeStanza) 
													throws StanzaNotFoundException {
			Stanza stanza = this.stanze.get(nomeStanza);
			if (stanza == null)
				throw new StanzaNotFoundException(
						"Impossibile aggiungere l'attrezzo. La stanza " + nomeStanza +
						" non esiste."); 
			
			stanza.addAttrezzo(new Attrezzo(nomeAttrezzo, peso));
			return this;
		}
		
		public Builder addAdiacenza(String stanzaCorrente, String stanzaAdiacente, Direzione direzione) 
		throws StanzaNotFoundException {
			Stanza corrente = this.stanze.get(stanzaCorrente);
			Stanza adiacente = this.stanze.get(stanzaAdiacente);
			
			if (corrente == null)
				throw new StanzaNotFoundException(
						"La stanza " + stanzaCorrente + " non esiste.");
			if (adiacente == null)
				throw new StanzaNotFoundException(
						"La stanza " + stanzaAdiacente + " non esiste.");

			corrente.impostaStanzaAdiacente(direzione, adiacente);
			return this;
		}

		
		public Labirinto build() {
			if (this.entrata == null || this.uscita == null) {
				throw new IllegalStateException("Impossibile creare il labirinto senza un'entrata e un'uscita.");
			}
			return new Labirinto(this);
		}
		
		
		
	}
	
	
}
