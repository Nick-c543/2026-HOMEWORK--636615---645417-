package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;
import static it.uniroma3.diadia.ambienti.Direzione.*; 

/**
 * Comando Vai:
 * Sposta il giocatore nella stanza adiacente alla corrente
 * nella direzione specificata
 * 
 * @author Margherita Manzi
 * @see Comando
 * @version base
 */
public class ComandoVai extends AbstractComando {
	
	/** 
	 * Iniziliazza il comando con la Console di gioco e la direzione nulla
	 * @param io
	 */
	public ComandoVai() {
		super("vai");
	}
	
	
	/**
	 * Sposta il giocatore nella direzione specificata, altrimenti
	 * mostra un messaggio d'errore
	 * @param partita
	 */
	@Override
	public void esegui(Partita partita) {
		
		IO io = this.getIO();
		String dir = this.getParametro();
		Direzione direzione = null;
		
		if(dir==null) {
			io.mostraMessaggio("Dove vuoi andare ? Devi specificare una direzione");
			return;
		}
		
		try {
			direzione = Direzione.valueOf(dir.toUpperCase()); 
		} catch (IllegalArgumentException e) {
			io.mostraMessaggio("Direzione inesistente. Usa nord, est, sud o ovest.");
			return; 
		}
			
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Stanza prossimaStanza = null; 
	
		
		prossimaStanza = stanzaCorrente.getStanzaAdiacente(direzione);
		if (prossimaStanza == null) {
			io.mostraMessaggio("In quella direzione non c'è nessuna stanza adiacente!");
			return;
		}
		
		partita.setStanzaCorrente(prossimaStanza);
		io.mostraMessaggio(partita.getStanzaCorrente().getNome());
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
		
	}
	

}
