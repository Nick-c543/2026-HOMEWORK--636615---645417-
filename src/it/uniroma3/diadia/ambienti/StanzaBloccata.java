package it.uniroma3.diadia.ambienti;

import static it.uniroma3.diadia.ambienti.Direzione.*; 

/**
 * Stanza con delle specifiche particolari:
 * Una direzione è bloccata, a meno che nella stanza non sia presente un particole attrezzo.
 * 
 * @author Margherita Manzi
 * @see Stanza
 * @versione base
 */
public class StanzaBloccata extends Stanza {
	private Direzione direzioneBloccata;
	final static private Direzione DIR_BLOCCATA_DEFAULT = NORD;
	private String attrezzoSblocca; 
	final static private String ATTR_SBLOCCA_DEFAULT = "chiave";
	
	/**
	 * Inizializza la stanza bloccata con direzione bloccata 
	 * e attrezzo sbloccante standard
	 * @param nome
	 */
	public StanzaBloccata(String nome) {
		this(nome, DIR_BLOCCATA_DEFAULT, ATTR_SBLOCCA_DEFAULT);
	}
	
	/**
	 * Inizializza la stanza bloccata con direzione bloccata 
	 * e attrezzo bloccato scelti dall'utente
	 * @param nome
	 * @param direzioneBloccata
	 * @param attrezzoSblocca
	 */
	public StanzaBloccata(String nome, Direzione direzioneBloccata, String attrezzoSblocca) {
		super(nome); 
		this.direzioneBloccata = direzioneBloccata; 
		this.attrezzoSblocca = attrezzoSblocca; 
	}
	
	/**
	 * Se la direzione non è bloccato o è presente l'attrezzo sbloccante si comporta normalmente,
	 * altrimenti blocca l'accesso e costringe a rimanere nella stanza corrente
	 * @return Stanza (adiacente o corrente)
	 */
	@Override
	public Stanza getStanzaAdiacente(Direzione dir) {
		if (dir.equals(this.direzioneBloccata) && !this.hasAttrezzo(this.attrezzoSblocca))
			return this;
		return super.getStanzaAdiacente(dir);
	}
	
	/**
	 * Aggiunge alla descrizione standard informazioni 
	 * sulla direzione bloccata e sull'attrezzo sbloccante
	 */
	@Override
	public String getDescrizione() {
		return "Direzione bloccata: " + this.direzioneBloccata + 
				"\nAttrezzo sbloccante: " + this.attrezzoSblocca +
				"\n" + this.toString();
	}

}
