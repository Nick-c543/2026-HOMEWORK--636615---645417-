package it.uniroma3.diadia.comandi;

/**
 * Rappresenta un comando eseguibile nel gioco
 * 
 * @author Margherita Manzi
 * @version base
 */
import it.uniroma3.diadia.Partita;

public interface Comando {
	
	/**
	 * Esecuzione del comando
	 */
	public void esegui(Partita partita);
	
	/**
	 * Set parametro del comando
	 */
	public void setParametro(String parametro); 

	/**
	 * Restituzione del nome del comando
	 */
	public String getNome();
	
	/**
	 * Restituzione del parametro
	 */
	public String getParametro();
	
}
