package diadia;



/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza, Labirinto , Partita
 * @version aggiornata
 */

public class Partita {

	
	private Labirinto labirinto; 
	private Stanza stanzaCorrente;
	private boolean finita;
	//private int cfu;
	private Giocatore giocatore; 
	
	public Partita(){
		
		this.labirinto = new Labirinto();
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
	public Giocatore getGiocatore() {
		return this.giocatore; 
	}
	
	
}
