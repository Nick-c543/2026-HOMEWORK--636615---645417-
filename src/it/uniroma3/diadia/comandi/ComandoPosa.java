package it.uniroma3.diadia.comandi;

/**
 * Comando Posa: 
 * Prende un oggetto dalla borsa e lo posa nella stanza corrente
 * 
 * @author Margherita Manzi
 * @see Comando
 * @version base
 */

import it.uniroma3.diadia.Partita;

public class ComandoPosa extends AbstractComando {
	
	/** 
	 */
	public ComandoPosa() {
		super("posa");
	}
	
	/**
	 * Se presente nella borsa, posa l'attrezzo  parametro nella stanza, 
	 * altrimenti mostra un messaggio di errore
	 * 
	 * @param partita
	 */
	@Override
	public void esegui(Partita partita) {
		String parametro = this.getParametro();
		if (parametro==null) {
			this.getIO().mostraMessaggio("Cosa vuoi posare?");
			return;
		}
		if (partita.getGiocatore().getBorsa().hasAttrezzo(parametro)) {
			partita.getStanzaCorrente().addAttrezzo(partita.getGiocatore().getBorsa().removeAttrezzo(parametro));
		}
		else
			this.getIO().mostraMessaggio("L'attrezzo " + parametro + " non è presente nella borsa del giocatore");
	}
	
}
