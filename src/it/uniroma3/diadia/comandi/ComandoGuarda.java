package it.uniroma3.diadia.comandi;

/**
 * Comando Guarda: 
 * Descrizione della stanza corrente e dello stato della partita
 * 
 * @author Margherita Manzi
 * @see Comando
 * @version base
 */
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoGuarda extends AbstractComando{
	
	/** 
	 * Iniziliazza il comando con la Console di gioco
	 * @param io
	 */
	public ComandoGuarda() {
		super("guarda");
	}
	
	/**
	 * Mostra la descrizione della stanza corrente, i CFU rimanenti 
	 * e il contenuto della borsa del giocatore
	 * 
	 * @param partita
	 */
	@Override
	public void esegui(Partita partita) {
		IO io = this.getIO();
		
		io.mostraMessaggio("Sei nella stanza " + partita.getStanzaCorrente());
		
		
		io.mostraMessaggio("Hai ancora a disposizione " + partita.getGiocatore().getCfu() + " Cfu.");
		
		io.mostraMessaggio(partita.getGiocatore().getBorsa().toString());
		
	}
	
}
