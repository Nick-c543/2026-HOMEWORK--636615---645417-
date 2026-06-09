package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoSaluta extends AbstractComando{

	public ComandoSaluta(String nome) {
		super(nome);
	}

	@Override
	public void esegui(Partita partita) {
		Stanza stanzaCorrente = partita.getStanzaCorrente(); 
		if (stanzaCorrente.getPersonaggio() != null) {
			String risposta = stanzaCorrente.getPersonaggio().saluta(); 
			this.getIO().mostraMessaggio(risposta);
		}
		else {
			this.getIO().mostraMessaggio("Non c'è nessun personaggio nella stanza corrente...");
		}
	}

}
