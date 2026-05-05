package it.uniroma3.diadia.comandi;

/** 
 * Gestisce il caso in cui in Input viene dato un comando non valido
 * 
 * @author Margherita Manzi
 * @see Comando
 * @version base
 */
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoNonValido implements Comando{
	
	private IO io; 
	
	/** 
	 * Iniziliazza il comando con la Console di gioco
	 * @param io
	 */
	public ComandoNonValido(IO io) {
		this.io = io;
	}
	
	/**
	 * Mostra il messaggio di comando non valido
	 */
	@Override
	public void esegui(Partita partita) {
		io.mostraMessaggio("Il comando non è valido... riprova: ");
	}
	
	/**
	 * Lascia il parametro vuoto
	 */
	@Override
	public void setParametro(String parametro) {
	}
	
	/**
	 * Restituisce il nome del comando
	 * @return "non valido"
	 */
	@Override
	public String getNome() {return "non valido";}
	
	/**
	 * Restituisce il parametro, in questo caso nullo
	 * @return null
	 */
	@Override
	public String getParametro() {return null;}
}

