package it.uniroma3.personaggi;

import java.util.Set;

import java.util.Iterator;

import it.uniroma3.diadia.Partita; 
import it.uniroma3.diadia.ambienti.*;

public class Strega extends AbstractPersonaggio{
	
	public Strega(String nome, String presentazione) {
		super(nome, presentazione); 
	}
	
	@Override
	public String agisci(Partita partita) {
		Stanza qui = partita.getStanzaCorrente(); 
		Set<Direzione> adiacenti = qui.getDirezioni();
		
		if (this.haSalutato()) {
			Iterator<Direzione> it = adiacenti.iterator(); 
			Direzione stanzaMaxAttr = it.next();  
			while(it.hasNext()) {
				Direzione dirCorrente = it.next(); 
				if (qui.getStanzaAdiacente(dirCorrente).getAttrezzi().size() > 
				qui.getStanzaAdiacente(stanzaMaxAttr).getAttrezzi().size())
					stanzaMaxAttr = dirCorrente; 
			}
			partita.setStanzaCorrente(qui.getStanzaAdiacente(stanzaMaxAttr));
			return "Dato che mi hai salutata, ti sposto nella stanza " + stanzaMaxAttr; 
		}
		
		else {
			Iterator<Direzione> it = adiacenti.iterator(); 
			Direzione stanzaMinAttr = it.next();  
			while(it.hasNext()) {
				Direzione dirCorrente = it.next(); 
				if (qui.getStanzaAdiacente(dirCorrente).getAttrezzi().size() < 
				qui.getStanzaAdiacente(stanzaMinAttr).getAttrezzi().size())
					stanzaMinAttr = dirCorrente; 
			}
			partita.setStanzaCorrente(qui.getStanzaAdiacente(stanzaMinAttr));
			return "Dato che non mi hai salutata, ti sposto nella stanza " + stanzaMinAttr;			
			
		}
		
	}

}
