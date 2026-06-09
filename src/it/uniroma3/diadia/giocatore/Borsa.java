package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.Configurazione;
import it.uniroma3.diadia.attrezzi.*;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;


/**
 * Classe che gestisce la borsa del giocatore e i suoi atrezzi
 * @author: Margherita Manzi e Niccolo Ilari
 * @see: Giocatore, Attrezzo
 * @versio: base
 */
public class Borsa {
	
	private Map<String, Attrezzo> attrezzi; 
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
		this.attrezzi = new HashMap<String, Attrezzo>(); 
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
		
		this.attrezzi.put(attrezzo.getNome(), attrezzo);
		return this.attrezzi.containsKey(attrezzo.getNome());
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
		a = this.attrezzi.get(nomeAttrezzo); 
		return a;
	}
	
	/**
	 * Restituisce il peso attuale della borsa
	 * @return peso
	 */
	public int getPeso() {
		int peso = 0;
		for (Attrezzo a : this.attrezzi.values())
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
		return this.attrezzi.containsKey(nomeAttrezzo);
	}
		
	/**
	 * Rimuove atrezzo dalla borsa
	 * @param nomeAttrezzo
	 * @return attrezzo rimosso
	 */
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		a = this.attrezzi.remove(nomeAttrezzo);
		return a; 
	}
	
	public List<Attrezzo> getContenutoOrdinatoPerPeso(){
		ArrayList<Attrezzo> ordinati = new ArrayList<>(this.attrezzi.values());
		
		ordinati.sort(new Comparator<Attrezzo>() {
			@Override
			public int compare(Attrezzo a1, Attrezzo a2) {
				int cmpPeso = a1.getPeso() - a2.getPeso(); 
				if (cmpPeso == 0) 
					return a1.getNome().compareTo(a2.getNome());
				return cmpPeso; 
			}
		}); 

		return ordinati; 
	}
	
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
		SortedSet<Attrezzo> ordinati = new TreeSet<>(new Comparator<Attrezzo>() {
			@Override
			public int compare(Attrezzo a1, Attrezzo a2) {
				return a1.getNome().compareTo(a2.getNome()) ;
			}
		});
		ordinati.addAll(this.attrezzi.values()); 
		return ordinati; 
	}
	
	public Map<Integer, Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
		Map<Integer, Set<Attrezzo>> raggruppati = new HashMap<>(); 
		for (Attrezzo a: this.attrezzi.values()) {
			int peso = a.getPeso(); 
			
			//Controllo se ho già aggunto il set relativo a quel peso
			if(!(raggruppati.containsKey(peso)))
				raggruppati.put(peso, new HashSet<>());
			
			raggruppati.get(peso).add(a); 
		}
		return raggruppati; 
	}
	
	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso(){
		SortedSet<Attrezzo> ordinati = new TreeSet<>( new Comparator<Attrezzo>() {
			@Override
			public int compare(Attrezzo a1, Attrezzo a2) {
				int cmpPeso = a1.getPeso() - a2.getPeso();
				if (cmpPeso == 0)
					return a1.getNome().compareTo(a2.getNome()); 
				return cmpPeso; 
			}
		}); 
		ordinati.addAll(this.attrezzi.values()); 
		return ordinati;
	}
	
	
	/**
	 * Descrizione della borsa	
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();

		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+ this.getPeso() + "kg/" + this.getPesoMax() + "kg): ");
			for (Attrezzo a: this.getContenutoOrdinatoPerPeso())
				s.append(a + " "); 
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}

}
