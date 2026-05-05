package it.uniroma3.diadia.comandi;

/** 
 * Comando Fine: 
 * Termina la partita.
 * 
 * @author Margherita Manzi
 * @see Comando
 * @version base
 */
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoFine implements Comando{
	private IO io; 
	
	/** 
	 * Iniziliazza il comando con la Console di gioco
	 * @param io
	 */
	public ComandoFine(IO io) {
		this.io = io; 
	}
	
	/**
	 * Mostra un messaggio di fine partita e la imposta a finita
	 * @param partita
	 */
	@Override
	public void esegui(Partita partita) {
		io.mostraMessaggio("Grazie per aver giocato!");
		partita.setFinita();
	}
	
	/**
	 * Lascia il parametro vuoto
	 */
	@Override
	public void setParametro(String parametro) {
	}
	
	/**
	 * Restituisce il nome del comando
	 * @return "fine"
	 */
	@Override
	public String getNome() { return "fine";}
	
	/**
	 * Restituisce il parametro, in questo caso nullo
	 * @return null
	 */
	@Override
	public String getParametro() { return null;}
}
