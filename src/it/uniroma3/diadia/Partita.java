package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.*;

import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza, Labirinto, Giocatore 
 * @version aggiornata
 */

public class Partita {

	
	private Labirinto labirinto; 
	private Stanza stanzaCorrente;
	private boolean finita;
	private Giocatore giocatore; 
	
	/**
	 * Inizializza la partita impostando il labirinto di default,
	 * la stanza corrente e il giocatore. 
	 */
	public Partita(){
		
		this.labirinto = new Labirinto("labirinto-default.txt");
		this.finita = false;
		this.stanzaCorrente=labirinto.getEntrata(); 
		this.giocatore = new Giocatore(); 
		
	}
	
	/**
	 * Inizializza la partita impostando un labirinto passato come parametro
	 * @param labirinto
	 */
	public Partita(Labirinto labirinto) {
		this.labirinto = labirinto; 
		this.finita = false;
		this.stanzaCorrente=labirinto.getEntrata(); 
		this.giocatore = new Giocatore(); 
	}
	
	/**
	 * Inizializza la partita impostando un labirinto in base al 
	 * livello passato come parametro
	 * @param livello
	 */
	public Partita(int livello) {
		String nomeFileLivello = ""; 
		
		switch(livello) {
		case 1: 
			nomeFileLivello = "labirinto1.txt"; 
			break;
		case 2: 
			nomeFileLivello = "labirinto2.txt"; 
			break; 
		case 3: 
			nomeFileLivello = "labirinto3.txt"; 
			break; 
		default: 
			throw new IllegalArgumentException("Il livello selezionato " + 
												livello + " non esiste!"); 
		}
	
		this.labirinto = new Labirinto(nomeFileLivello); 

		this.finita = false;
		this.stanzaCorrente=labirinto.getEntrata(); 
		this.giocatore = new Giocatore(); 
	}

	/**
	 * Imposta la stanza corrente
	 * @param stanzaCorrente
	 */
	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}
	/**
	 * Restituisce la stanza corrente
	 * @return stanzaCorrente
	 */
	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}
	
	/**
	 * Restituisce la stanza vincente
	 * @return uscita
	 */
	public Stanza getStanzaVincente() {
		return labirinto.getUscita();
	}
	
	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return this.getStanzaCorrente()== this.getStanzaVincente();
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || (this.getGiocatore().getCfu() == 0);
	}

	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}
	
	/**
	 * Restituisce il giocatore della partita corrente
	 * @return Giocatore
	 */
	public Giocatore getGiocatore() { return this.giocatore; }
	
	
	
	/**
	 * Restituisce il labirinto della partita corrente
	 * @return Labirinto
	 */
	public Labirinto getLabirinto() { return this.labirinto; } 
	
	/**
	 * Imposta il labirinto della partita a quello passato per parametro
	 * @param labirinto
	 */
	public void setLabirinto(Labirinto labirinto) { this.labirinto = labirinto; }
	
	/**
	 * Ci dice se il giocatore è vivo (se non ha ancora finito i CFU)
	 * @return boolean
	 */
	public boolean giocatoreIsVivo() { return this.getGiocatore().getCfu() != 0;}
	
	
}
