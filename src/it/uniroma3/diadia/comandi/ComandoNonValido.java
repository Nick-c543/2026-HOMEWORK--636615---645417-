package it.uniroma3.diadia.comandi;

/** 
 * Gestisce il caso in cui in Input viene dato un comando non valido
 * 
 * @author Margherita Manzi
 * @see Comando
 * @version base
 */
import it.uniroma3.diadia.Partita;

public class ComandoNonValido extends AbstractComando{
	
	
	/** 
	 * Iniziliazza il comando con la Console di gioco
	 * @param io
	 */
	public ComandoNonValido() {
		super("non valido"); 
	}
	
	/**
	 * Mostra il messaggio di comando non valido
	 */
	@Override
	public void esegui(Partita partita) {
		this.getIO().mostraMessaggio("Il comando non è valido... riprova: ");
	}
	
}

