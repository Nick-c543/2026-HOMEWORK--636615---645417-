package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.Configurazione;

/**
 * Questa classe gestisce i cfu del giocatore e crea un collegamento tra Partita e Borsa
 * 
 * @author: Margherita Manzi e Niccolò Ilari
 * @see Borsa , Partita
 * @version base
 */

public class Giocatore {
	
	private int cfu; 
	private Borsa borsa; 
	
	public Giocatore() {
		this.cfu = Configurazione.getCFU();  
		this.borsa = new Borsa();
	}
	
	/**
	 * Restituisce i cfu del giocatore
	 * @return cfu
	 */
	public int getCfu() {
		return this.cfu;
	}
	/**
	 * Imposta i cfu del giocatore
	 * @param cfu
	 */
	public void setCfu(int cfu) {
		this.cfu = cfu;		
	}
	/**
	 * Restituisce la borsa del giocatore
	 * @return Borsa 
	 */
	public Borsa getBorsa() {
		return this.borsa; 
	}
	
}
