package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe che gestisce la borsa del giocatore e i suoi atrezzi
 * @author: Margherita Manzi e Niccolo Ilari
 * @see: Giocatore, Attrezzo
 * @versio: base
 */
public class Borsa {
	
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private Attrezzo[] attrezzi;
	private int numeroAttrezzi;
	private int pesoMax;
	
	/**
	 * Inizializza la bora con peso massimo di default
	 */
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}
	
	/**
	 * Inizializza la borsa con peso massimo scelto dall'utente
	 * @param pesoMax
	 */
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new Attrezzo[pesoMax];
		this.numeroAttrezzi = 0;
	}
	
	/**
	 * Aggiunge un attrezzo alla borsa e restituisce true se l'operazione è andata a buon fine
	 * @param attrezzo
	 * @return boolean
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (attrezzo==null) 
			return false; 
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
				return false;
		
		if (this.numeroAttrezzi==this.getPesoMax()) //mpdifica qui
				return false;
		this.attrezzi[this.numeroAttrezzi] = attrezzo;
		this.numeroAttrezzi++;
		return true;
	}
	
	/**
	 * Restituisce il peso massimo della borsa
	 * @return int
	 */
	public int getPesoMax() {
		return pesoMax;
	}
	
	/**
	 * Restituisce l'attrezzo cercato, se presente, altrimenti restituisce null
	 * @param nomeAttrezzo
	 * @return attrezzo cercato
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		for (int i= 0; i<this.numeroAttrezzi; i++)
			if (this.attrezzi[i].getNome().equals(nomeAttrezzo))
				a = attrezzi[i];

		return a;
	}
	
	/**
	 * Restituisce il peso attuale della borsa
	 * @return peso
	 */
	public int getPeso() {
		int peso = 0;
		for (int i= 0; i<this.numeroAttrezzi; i++)
		peso += this.attrezzi[i].getPeso();

		return peso;
	}
	
	/**
	 * Verifica se la borsa sia vuota o no
	 * @return boolean
	 */
	public boolean isEmpty() {
		return this.numeroAttrezzi == 0;
	}
	
	/**
	 * Verfica se un attrezzo sia presente o meno nella borsa
	 * @param nomeAttrezzo
	 * @return boolean
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}
		
	/**
	 * Rimuove atrezzo dalla borsa
	 * @param nomeAttrezzo
	 * @return attrezzo rimosso
	 */
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		for (int i=0; i<this.numeroAttrezzi; i++) {
			if(this.attrezzi[i].getNome().equals(nomeAttrezzo)) {
				a=this.attrezzi[i];
				this.attrezzi[i]=null;
				this.numeroAttrezzi--;
				for(int j=i+1; j<this.numeroAttrezzi; j++) {
					this.attrezzi[j-1]=this.attrezzi[j]; 
				}
				break;
			}
		}
		return a;
	}
	
	
	/**
	 * Descrizione della borsa	
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();

		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			for (int i= 0; i<this.numeroAttrezzi; i++)
				s.append(attrezzi[i].toString()+" ");
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}

}
