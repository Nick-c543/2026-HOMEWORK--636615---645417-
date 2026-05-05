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

public class ComandoGuarda implements Comando{
	private IO io; 
	
	/** 
	 * Iniziliazza il comando con la Console di gioco
	 * @param io
	 */
	public ComandoGuarda(IO io) {
		this.io = io; 
	}
	
	/**
	 * Mostra la descrizione della stanza corrente, i CFU rimanenti 
	 * e il contenuto della borsa del giocatore
	 * 
	 * @param partita
	 */
	@Override
	public void esegui(Partita partita) {
		io.mostraMessaggio("Sei nella stanza " + partita.getStanzaCorrente());
		
		
		io.mostraMessaggio("Hai ancora a disposizione " + partita.getGiocatore().getCfu() + " Cfu.");
		
		io.mostraMessaggio(partita.getGiocatore().getBorsa().toString());
		
	}
	
	/**
	 * Lascia il parametro vuoto
	 */
	@Override
	public void setParametro(String parametro) {
	}
	
	/**
	 * Restituisce il nome del comando
	 * @return "guarda"
	 */
	@Override
	public String getNome() {return "guarda";}
	
	/**
	 * Restituisce il parametro, in questo caso nullo
	 * @return null
	 */
	@Override
	public String getParametro() {return null;}

}
