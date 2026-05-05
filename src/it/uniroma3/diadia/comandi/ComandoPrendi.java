package it.uniroma3.diadia.comandi;

/** 
 * Comando Prendi: 
 * Prende un attrezzo dalla stanza corrente e lo mette nella borsa del giocatore
 * 
 * @author Margherita Manzi
 * @see Comando
 * @version base
 */
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoPrendi implements Comando {
	private String nomeAttrezzo; 
	private IO io; 
	
	/**
	 * Iniziliazza il comando con la Console di gioco e
	 * il nome dell'attrezzo nullo
	 * @param io
	 */
	public ComandoPrendi(IO io) {
		this.nomeAttrezzo = null; 
		this.io = io;
	}
	
	/** 
	 * Se presente nella stanza corrente, prende l'attrezzo scelto 
	 * e lo mette nella borsa, altrimenti mostra un messaggio di errore
	 * @param partita
	 */
	@Override
	public void esegui(Partita partita) {
		if (this.nomeAttrezzo==null) {
			io.mostraMessaggio("Cosa vuoi prendere?");
			return;
		}
		if (partita.getStanzaCorrente().hasAttrezzo(nomeAttrezzo)) {
			partita.getGiocatore().getBorsa().addAttrezzo(partita.getStanzaCorrente().removeAttrezzo(nomeAttrezzo));
		}
		else
			io.mostraMessaggio("L'attrezzo " + nomeAttrezzo + " non è presente in " + partita.getStanzaCorrente().getNome());
	}
	
	/**
	 * Imposta il nome dell'attrezzo da prendere dal parametro del comando
	 * @param parametro
	 */
	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro; 
	}
	
	/**
	 * Restituisce il nome del comando
	 * @return "prendi"
	 */
	@Override
	public String getNome() {return "prendi";}
	
	/**
	 * Restituisce il parametro 
	 * @return nomeAttrezzo
	 */
	@Override
	public String getParametro() {return this.nomeAttrezzo;}
	
	

}
