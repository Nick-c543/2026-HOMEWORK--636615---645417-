package diadia;

/**
 * Questa classe gestisce i cfu del giocatore e crea un collegamento tra Partita e Borsa
 * 
 * @author: Margherita Manzi e Niccolò Ilari
 * @see Borsa , Partita
 * @versione base
 */

public class Giocatore {
	
	static final private int CFU_INIZIALI = 20;
	
	private int cfu; 
	
	public Giocatore() {
		this.cfu=CFU_INIZIALI; 
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
	
	//memorizzare gli attrezz in...
	
}
