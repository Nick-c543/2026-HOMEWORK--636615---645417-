package it.uniroma3.diadia.ambienti;

/**
 * Stanza con specifiche particolari: 
 * se non è presente un particolare attrezzo è buia
 * e non possiamo vederne il contenuto.
 * @author Margherita Manzi
 * @see Stanza
 * @version base
 */
public class StanzaBuia extends Stanza {
	private String nomeOggetto; 
	final static private String NOME_OGGETTO_DEFAULT = "lanterna";
	
	/**
	 * Inizializza la stanza buia con attrezzo illuminante standard
	 * @param nome
	 */
	public StanzaBuia(String nome) {
		this(nome, NOME_OGGETTO_DEFAULT);
	}
	
	/**
	 * Inizializza la stanza buia con attrezzo illuminante scelto dall'utente
	 * @param nome
	 * @param nomeOggetto
	 */
	public StanzaBuia(String nome, String nomeOggetto) {
		super(nome);
		this.nomeOggetto = nomeOggetto; 
	}
	
	/**
	 * Se non è presente l'attrezzo illuminante, restituisce la stringa "qui c'è buio pesto", 
	 * altrimenti restituisce la descrizione della stanza
	 * 
	 * @return String di descrizione
	 */
	@Override
	public String getDescrizione() {
		if (this.hasAttrezzo(this.nomeOggetto))
			return this.toString();
		return "qui c'è buio pesto";
    }
}
