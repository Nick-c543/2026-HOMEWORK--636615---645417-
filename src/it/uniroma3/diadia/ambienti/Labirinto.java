package it.uniroma3.diadia.ambienti;


import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

//import lombok.Getter;
//import lombok.Builder;
 

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.personaggi.AbstractPersonaggio;

import static it.uniroma3.diadia.ambienti.Direzione.*; 

/**
 * Questa classe modella il labirinto per la partita avvalendosi di un Builder
 * @author: Margherita Manzi e Niccolò Ilari
 * @see Stanza
 * @see Attrezzo
 * @versione aggiornata 
 */

public class Labirinto {
	
	private final Stanza entrata; 
	private final Stanza uscita; 
	
	private Labirinto(Stanza entrata, Stanza uscita) {
		this.entrata = entrata; 
		this.uscita = uscita; 
	}
	
	/**
	 * Costruttore di un labirinto da file di testo
	 * @param fileName
	 */
	public Labirinto(String fileName) {
		try {
			CaricatoreLabirinto c = new CaricatoreLabirinto(fileName);c.carica(); 
			this.entrata = c.getStanzaIniziale(); 
			this.uscita = c.getStanzaVincente();
		} catch (FormatoFileNonValidoException | FileNotFoundException e) {
			System.err.println("Errore di file!"); 
			throw new RuntimeException(e); 
		}
	 
	}
	
	/**
	 * Costruttore del builder
	 * @return LabirintoBuilder
	 */
	public static LabirintoBuilder newBuilder() {
		return new LabirintoBuilder();
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
	
	/**
	 * Classe nidificata Labirinto Builder
	 * 
	 * @author Margherita Manzi
	 * @version aggiornata
	 */
	public static class LabirintoBuilder {
		
		private Stanza entrata;
		private Stanza uscita; 
		private Stanza ultimaAggiunta; 
		private Map<String, Stanza> stanze; 
		
		public LabirintoBuilder() {
			this.stanze = new HashMap<>(); 
		}
		
		public LabirintoBuilder addStanzaIniziale(String nomeStanza) throws StanzaNotFoundException {
			if (nomeStanza == null) throw new IllegalArgumentException("Il nome della stanza non può essere null"); 
			Stanza stanza = this.stanze.get(nomeStanza); 
			if (stanza == null) {
				stanza = new Stanza(nomeStanza); 
				this.stanze.put(nomeStanza, stanza); 
			}
			this.entrata = stanza;
			this.stanze.put(nomeStanza, stanza);
			this.ultimaAggiunta = stanza; 
			return this; // Permette la concatenazione (fluent interface)
		}
		
		public LabirintoBuilder addStanzaVincente(String nomeStanza) throws StanzaNotFoundException {
			if (nomeStanza == null) throw new IllegalArgumentException("Il nome della stanza non può essere null"); 
			Stanza stanza = this.stanze.get(nomeStanza); 
			if (stanza == null) { 
				stanza = new Stanza(nomeStanza); 
				this.stanze.put(nomeStanza, stanza); 
			}
			this.uscita = stanza;
			this.stanze.put(nomeStanza, stanza);
			this.ultimaAggiunta = stanza; 
			return this;
		}
		
		public LabirintoBuilder addStanza(String nomeStanza) {
			if (nomeStanza == null) throw new IllegalArgumentException("Il nome della stanza non può essere null"); 
			if (!this.stanze.containsKey(nomeStanza)) {
				Stanza stanza = new Stanza(nomeStanza); 
				this.stanze.put(nomeStanza, stanza);
				this.ultimaAggiunta = stanza; 
			}

			return this;
		}
		
		public LabirintoBuilder addStanzaBloccata(String nomeStanza) {
			if (nomeStanza == null) throw new IllegalArgumentException("Il nome della stanza bloccata non può essere null"); 
			if (!this.stanze.containsKey(nomeStanza)) {
				Stanza stanza = new StanzaBloccata(nomeStanza); 
				this.stanze.put(nomeStanza, stanza);
				this.ultimaAggiunta = stanza; 
			}

			return this;
		}
		
		public LabirintoBuilder addStanzaBuia(String nomeStanza) {
			if (nomeStanza == null) throw new IllegalArgumentException("Il nome della stanza buia non può essere null"); 
			if (!this.stanze.containsKey(nomeStanza)) {
				Stanza stanza = new StanzaBuia(nomeStanza); 
				this.stanze.put(nomeStanza, stanza);
				this.ultimaAggiunta = stanza; 
			}

			return this;
		}
		
		public LabirintoBuilder addStanzaMagica(String nomeStanza) {
			if (nomeStanza == null) throw new IllegalArgumentException("Il nome della stanza magica non può essere null"); 
			if (!this.stanze.containsKey(nomeStanza)) {
				Stanza stanza = new StanzaMagica(nomeStanza); 
				this.stanze.put(nomeStanza, stanza);
				this.ultimaAggiunta = stanza; 
			}

			return this;
		}
		
		public LabirintoBuilder addStanzaMagica(String nomeStanzaMagica, int sogliaMagica) {
			if (nomeStanzaMagica == null) throw new IllegalArgumentException("Il nome della stanza non può essere null"); 
			if (!this.stanze.containsKey(nomeStanzaMagica)) {
				StanzaMagica magica = new StanzaMagica(nomeStanzaMagica, sogliaMagica); 
				this.stanze.put(nomeStanzaMagica, magica); 
				this.ultimaAggiunta = magica;
			}
			return this; 
		}
		
		public LabirintoBuilder addStanzaBloccata(String nomeStanzaBloccata, Direzione dirBloccata, String nomeAttrezzo) {
			if (nomeStanzaBloccata == null) throw new IllegalArgumentException("Il nome della stanza non può essere null"); 
			if (!this.stanze.containsKey(nomeStanzaBloccata)) {
				StanzaBloccata bloccata = new StanzaBloccata(nomeStanzaBloccata, dirBloccata, nomeAttrezzo); 
				this.stanze.put(nomeStanzaBloccata, bloccata); 
				this.ultimaAggiunta = bloccata;
			}
			return this; 
		}
		
		public LabirintoBuilder addStanzaBuia(String nomeStanzaBuia, String nomeAttrezzo) {
			if (nomeStanzaBuia == null) throw new IllegalArgumentException("Il nome della stanza non può essere null"); 
			if (!this.stanze.containsKey(nomeStanzaBuia)) {
				StanzaBuia buia = new StanzaBuia(nomeStanzaBuia, nomeAttrezzo); 
				this.stanze.put(nomeStanzaBuia, buia); 
				this.ultimaAggiunta = buia;
			}
			return this; 
		}
		
		public LabirintoBuilder addAttrezzo(String nomeAttrezzo, int peso, String nomeStanza) 
				throws StanzaNotFoundException {
			Stanza stanza = this.stanze.get(nomeStanza);
			if (stanza == null)
			throw new StanzaNotFoundException(
			"Impossibile aggiungere l'attrezzo. La stanza " + nomeStanza +
			" non esiste."); 
			
			stanza.addAttrezzo(new Attrezzo(nomeAttrezzo, peso));
			return this;
		}
		
		/**
		 * Aggiungue l'attrezzo all'ultima stanza aggiunta nel labirinto
		 * @param nomeAttrezzo
		 * @param peso
		 * @return
		 */
		public LabirintoBuilder addAttrezzo(String nomeAttrezzo, int peso) {
			if (nomeAttrezzo == null) throw new IllegalArgumentException("Il nome dell'attrezzo non può essere null"); 
			this.ultimaAggiunta.addAttrezzo(new Attrezzo(nomeAttrezzo, peso));
			return this;
		}
		
		public LabirintoBuilder addAdiacenza(String stanzaCorrente, String stanzaAdiacente, Direzione direzione) 
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
		
		public LabirintoBuilder addPersonaggio(AbstractPersonaggio personaggio, String nomeStanza) 
				throws StanzaNotFoundException {
			Stanza daAggiungere = this.stanze.get(nomeStanza); 
			 if(daAggiungere == null)
				 throw new StanzaNotFoundException(
						 "La stanza " + nomeStanza + " non esiste."); 
			 daAggiungere.setPersonaggio(personaggio);
			 return this; 
				 
		}
		
		public Map<String, Stanza> getListaStanze(){ return this.stanze; }
		
		public Labirinto getLabirinto() {
			if (this.entrata == null || this.uscita == null) {
				throw new IllegalStateException("Impossibile creare il labirinto senza un'entrata e un'uscita.");
			}
			return new Labirinto(this.entrata, this.uscita); 
			
		}
		

	}
	
	
}
