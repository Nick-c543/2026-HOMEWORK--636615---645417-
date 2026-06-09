package it.uniroma3.diadia.ambienti;


import it.uniroma3.diadia.attrezzi.Attrezzo;
/**
 * Stanza magica che utilizza campi protected di StanzaProtected
 * 
 * @author Margherita Manzi
 * @see StanzaMagica
 * @see StanzaProtected
 * @see Attrezzo
 * @version protected
 * 
 */
public class StanzaMagicaProtected extends StanzaProtected{
	
	final static private int SOGLIA_MAGICA_DEFAULT = 3; 
	
	private int contatoreAttrezziPosati; 
	private int sogliaMagica; 
	
	/**
	 * Inizializza la stanza magica con soglia magica di default
	 * @param nome
	 */
	public StanzaMagicaProtected(String nome) {
		this(nome, SOGLIA_MAGICA_DEFAULT);
	}
	
	/**
	 * Inizializza la stanza magica con soglia magica scelta dall'utente
	 * @param nome
	 * @param soglia
	 */
	public StanzaMagicaProtected(String nome, int soglia) {
		super(nome);
		this.contatoreAttrezziPosati = 0;
		this.sogliaMagica = soglia; 
	}
	
	/**
	 * Modifica l'attrezzo invertendo il nome e raddoppiando il peso
	 * 
	 * @param attrezzo
	 * @return attrezzo modificato
	 */
	private Attrezzo modificaAttrezzo(Attrezzo attrezzo) {
		StringBuilder nomeInvertito; 
		int pesoX2 = attrezzo.getPeso() * 2; 
		nomeInvertito = new StringBuilder(attrezzo.getNome()).reverse();
		attrezzo = new Attrezzo(nomeInvertito.toString(), pesoX2);
		return attrezzo; 
	}
	
	/** 
	 * Se è stata superata la soglia magica, l'attrezzo aggiunto viene modificato
	 * secondo la funzione definita sopra. Altrimenti, viene aggiunto nella 
	 * sua versione standard
	 * 
	 * @param attrezzo
	 * @return true o false se riesce ad aggiungere l'attrezzo o meno
	 */
	@Override
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (attrezzo == null) return false; 
		this.contatoreAttrezziPosati++; 
		if (this.contatoreAttrezziPosati>this.sogliaMagica)
			attrezzo = this.modificaAttrezzo(attrezzo); 
		return super.addAttrezzo(attrezzo);
	}
	
	
}
