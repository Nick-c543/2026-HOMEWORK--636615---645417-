package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoRegala extends AbstractComando{

	public ComandoRegala(String nome) {
		super(nome);
	}

	@Override
	public void esegui(Partita partita) {
		Stanza stanzaCorrente = partita.getStanzaCorrente(); 
		if (stanzaCorrente.getPersonaggio() != null) {
			String nomeAttrezzo = this.getParametro(); 
			if (partita.getGiocatore().getBorsa().hasAttrezzo(nomeAttrezzo)) {
				Attrezzo attrezzo = partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo); 
				String risposta = stanzaCorrente.getPersonaggio().riceviRegalo(attrezzo, partita);
				partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo); 
				this.getIO().mostraMessaggio(risposta); 
			}
			else {
				this.getIO().mostraMessaggio("L'attrezzo che vuoi regalare non è presente nella tua borsa.");  
			}
		}
		else {
			this.getIO().mostraMessaggio("Non c'è nessun personaggio nella stanza corrente..."); 
		}
		
	}

}
