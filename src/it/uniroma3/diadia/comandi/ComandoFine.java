package it.uniroma3.diadia.comandi;

/** 
 * Comando Fine: 
 * Termina la partita.
 * 
 * @author Margherita Manzi
 * @see Comando
 * @version base
 */

import it.uniroma3.diadia.Partita;

public class ComandoFine extends AbstractComando{
	
	/** 
	 */
	public ComandoFine() {
		super("fine");  
	}
	
	/**
	 * Mostra un messaggio di fine partita e la imposta a finita
	 * @param partita
	 */
	@Override
	public void esegui(Partita partita) {
		this.getIO().mostraMessaggio("Grazie per aver giocato!");
		partita.setFinita();
	}
	
	
}
