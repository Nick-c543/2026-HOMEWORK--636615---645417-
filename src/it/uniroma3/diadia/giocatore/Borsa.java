package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.Configurazione;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import java.util.List; 
import java.util.ArrayList;
import java.util.Iterator; 

/**
 * Classe che gestisce la borsa del giocatore e i suoi atrezzi
 * @author: Margherita Manzi e Niccolo Ilari
 * @see: Giocatore, Attrezzo
 * @versio: base
 */
public class Borsa {
	
	private List<Attrezzo> attrezzi; 
	private int pesoMax;
	
	/**
	 * Inizializza la borsa con peso massimo di default
	 */
	public Borsa() {
		this(Configurazione.getPesoMax());
	}
	
	/**
	 * Inizializza la borsa con peso massimo scelto dall'utente
	 * @param pesoMax
	 */
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new ArrayList<Attrezzo>(); 
		//this.attrezzi = new Attrezzo[pesoMax];
		//this.numeroAttrezzi = 0;
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
		
		if (this.getPeso()==this.getPesoMax()) 
				return false;
		return this.attrezzi.add(attrezzo);
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
		for (Attrezzo attr: this.attrezzi) {
			if(attr.getNome().equals(nomeAttrezzo))
				a = attr; 
		}
		return a;
	}
	
	/**
	 * Restituisce il peso attuale della borsa
	 * @return peso
	 */
	public int getPeso() {
		int peso = 0;
		//Iterator<Attrezzo> iteratore = this.attrezzi.iterator(); 
		for (Attrezzo a : this.attrezzi)
			peso += a.getPeso(); 
		return peso;
	}
	
	/**
	 * Verifica se la borsa sia vuota o no
	 * @return boolean
	 */
	public boolean isEmpty() {
		return this.attrezzi.isEmpty();
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
		Iterator<Attrezzo> it = this.attrezzi.iterator(); 
		while(it.hasNext()) {
			a = it.next(); 
			if (a.getNome().equals(nomeAttrezzo)) {
				it.remove(); 
				return a;
			}
		}
		return null;
	}
	
	
	/**
	 * Descrizione della borsa	
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();

		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			for (Attrezzo a: this.attrezzi)
				s.append(a.toString()+" ");
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}

}
