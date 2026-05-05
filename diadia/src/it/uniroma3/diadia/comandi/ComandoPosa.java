package it.uniroma3.diadia.comandi;

/**
 * Comando Posa: 
 * Prende un oggetto dalla borsa e lo posa nella stanza corrente
 * 
 * @author Margherita Manzi
 * @see Comando
 * @version base
 */
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoPosa implements Comando {
	private String nomeAttrezzo;
	private IO io; 
	
	/** 
	 * Iniziliazza il comando con la Console di gioco
	 * e il nome dell'attrezzo nullo
	 * @param io
	 */
	public ComandoPosa(IO io) {
		this.nomeAttrezzo = null;
		this.io = io; 
	}
	
	/**
	 * Se presente nella borsa, posa l'attrezzo  parametro nella stanza, 
	 * altrimenti mostra un messaggio di errore
	 * 
	 * @param partita
	 */
	@Override
	public void esegui(Partita partita) {
		if (this.nomeAttrezzo==null) {
			io.mostraMessaggio("Cosa vuoi posare?");
			return;
		}
		if (partita.getGiocatore().getBorsa().hasAttrezzo(nomeAttrezzo)) {
			partita.getStanzaCorrente().addAttrezzo(partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo));
		}
		else
			io.mostraMessaggio("L'attrezzo " + nomeAttrezzo + " non è presente nella borsa del giocatore");
	}
	
	/**
	 * Imposta il nome dell'attrezzo da posare dal parametro del comando
	 * @param parametro
	 */
	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro; 
	}
	
	/**
	 * Restituisce il nome del comando
	 * @return "posa"
	 */
	@Override
	public String getNome() {return "posa";}
	
	/**
	 * Restituisce il parametro 
	 * @return nomeAttrezzo
	 */
	@Override
	public String getParametro() {return this.nomeAttrezzo;}

}
