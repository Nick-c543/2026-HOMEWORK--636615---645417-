package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

/**
 * Comando Vai:
 * Sposta il giocatore nella stanza adiacente alla corrente
 * nella direzione specificata
 * 
 * @author Margherita Manzi
 * @see Comando
 * @version base
 */
public class ComandoVai implements Comando {
	private String direzione; 
	private IO io;
	
	/** 
	 * Iniziliazza il comando con la Console di gioco e la direzione nulla
	 * @param io
	 */
	public ComandoVai(IO io) {
		this.direzione = null; 
		this.io = io; 
	}
	
	
	/**
	 * Sposta il giocatore nella direzione specificata, altrimenti
	 * mostra un messaggio d'errore
	 * @param partita
	 */
	@Override
	public void esegui(Partita partita) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Stanza prossimaStanza = null; 
		
		if(this.direzione==null) {
			io.mostraMessaggio("Dove vuoi andare ? Devi specificare una direzione");
			return;
		}
		
		prossimaStanza = stanzaCorrente.getStanzaAdiacente(this.direzione);
		if (prossimaStanza == null) {
			io.mostraMessaggio("Direzione inesistente");
			return;
		}
		
		partita.setStanzaCorrente(prossimaStanza);
		io.mostraMessaggio(partita.getStanzaCorrente().getNome());
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
		
	}
	
	/**
	 * Imposta il parametro nella direzione desiderata o null, 
	 * se la direzione specificata non è valida
	 */
	@Override
	public void setParametro(String parametro) {
		this.direzione = parametro; 
		if (parametro != null) {
			if (!parametro.equals("nord") && !parametro.equals("est") && !parametro.equals("sud") && !parametro.equals("ovest"))
				this.direzione = null;
		}
	}
	
	/**
	 * Restituisce il nome del comando
	 * @return "vai"
	 */
	@Override
	public String getNome() {return "vai";}
	
	/**
	 * Restituisce il parametro
	 * @return direzione
	 */
	@Override
	public String getParametro() {return this.direzione;}
		

}
