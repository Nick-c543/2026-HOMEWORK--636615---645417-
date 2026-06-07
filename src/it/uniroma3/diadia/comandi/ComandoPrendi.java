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

public class ComandoPrendi extends AbstractComando {
	
	/**
	 * Iniziliazza il comando con la Console di gioco e
	 * il nome dell'attrezzo nullo
	 * @param io
	 */
	public ComandoPrendi() {
		super("prendi"); 
	}
	
	/** 
	 * Se presente nella stanza corrente, prende l'attrezzo scelto 
	 * e lo mette nella borsa, altrimenti mostra un messaggio di errore
	 * @param partita
	 */
	@Override
	public void esegui(Partita partita) {
		String nomeAttrezzo = this.getParametro();
		IO io = this.getIO(); 
		if (nomeAttrezzo==null) {
			io.mostraMessaggio("Cosa vuoi prendere?");
			return;
		}
		if (partita.getStanzaCorrente().hasAttrezzo(nomeAttrezzo)) {
			partita.getGiocatore().getBorsa().addAttrezzo(partita.getStanzaCorrente().removeAttrezzo(nomeAttrezzo));
		}
		else
			io.mostraMessaggio("L'attrezzo " + nomeAttrezzo + " non è presente in " + partita.getStanzaCorrente().getNome());
	}
	
	

}
