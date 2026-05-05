package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Stanza con specifiche particolari: 
 * se vengono aggiunti degli attrezzi per un numero di volte
 * superiore alla soglia magica, i nuovi attrezzi aggiunti
 * vengono modificati con il nome invertito e il peso raddoppiato
 * 
 * @author Margherita Manzi
 * @see Stanza
 * @see Attrezzo
 * @version base
 */
public class StanzaMagica extends Stanza{

	final static private int SOGLIA_MAGICA_DEFAULT = 3; 
	
	private int contatoreAttrezziPosati; 
	private int sogliaMagica; 
	
	/**
	 * Inizializza la stanza magica con soglia magica di default
	 * @param nome
	 */
	public StanzaMagica(String nome) {
		this(nome, SOGLIA_MAGICA_DEFAULT);
	}
	
	/**
	 * Inizializza la stanza magica con soglia magica scelta dall'utente
	 * @param nome
	 * @param soglia
	 */
	public StanzaMagica(String nome, int soglia) {
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
	 * sua versione standards
	 * 
	 * @param attrezzo
	 * @return true o false se riesce ad aggiungere l'attrezzo o meno
	 */
	@Override
	public boolean addAttrezzo(Attrezzo attrezzo) {
		this.contatoreAttrezziPosati++; 
		if (this.contatoreAttrezziPosati>this.sogliaMagica)
			attrezzo = this.modificaAttrezzo(attrezzo); 
		return super.addAttrezzo(attrezzo);
	}
	
}
