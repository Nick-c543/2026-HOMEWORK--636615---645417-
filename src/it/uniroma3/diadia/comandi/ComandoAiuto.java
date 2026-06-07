package it.uniroma3.diadia.comandi;


import it.uniroma3.diadia.Partita;

/**
 * Comando Aiuto: 
 * Elenca tutti i comandi disponibili
 * 
 * @author Margherita Manzi
 * @see Comando
 * @version base
 */
public class ComandoAiuto extends AbstractComando{
	
	static final private String[] elencoComandi = {"vai", "aiuto", "prendi", "posa", "fine", "guarda"};
	
	/** 
	 * 
	 */
	public ComandoAiuto() {
		super("aiuto");
	}
	
	/**
	 * Mostra l'elenco dei comandi disponibili nella partita
	 * @param partita
	 */
	@Override
	public void esegui(Partita partita) {
		for(String comando: elencoComandi) { 
			this.getIO().mostraMessaggio(comando + " ");
		this.getIO().mostraMessaggio(""); 
		}
	}

}
