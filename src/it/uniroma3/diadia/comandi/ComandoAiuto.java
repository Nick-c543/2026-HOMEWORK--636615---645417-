package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

/**
 * Comando Aiuto: 
 * Elenca tutti i comandi disponibili
 * 
 * @author Margherita Manzi
 * @see Comando
 * @version base
 */
public class ComandoAiuto implements Comando{
	
	static final private String[] elencoComandi = {"vai", "aiuto", "prendi", "posa", "fine", "guarda"};
	private IO io; 
	
	/** 
	 * Iniziliazza il comando con la Console di gioco
	 * @param io
	 */
	public ComandoAiuto(IO io) {
		this.io = io;
	}
	
	/**
	 * Mostra l'elenco dei comandi disponibili nella partita
	 * @param partita
	 */
	@Override
	public void esegui(Partita partita) {
		for(String comando: elencoComandi) { 
			io.mostraMessaggio(comando + " ");
		io.mostraMessaggio(""); 
		}
	}
	
	/**
	 * Lascia il parametro vuoto
	 */
	@Override
	public void setParametro(String parametro) {
	}
	
	/**
	 * Restituisce il nome del comando
	 * @return "aiuto"
	 */
	@Override
	public String getNome() {return "aiuto";}
	
	/**
	 * Restituisce il parametro, in questo caso nullo
	 * @return null
	 */
	@Override
	public String getParametro() {return null;}

}
