package it.uniroma3.personaggi;

import it.uniroma3.diadia.Partita; 

public class Cane extends AbstractPersonaggio{
	
	
	public Cane(String nome, String presentazione) {
		super(nome, presentazione); 
	}
	
	@Override
	public String agisci(Partita partita) {
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu() - 1);
		String msg = "Ti ho morso! Ora ti rimangono solo " + 
				partita.getGiocatore().getCfu() + " Cfu..."; 
		return msg; 
	}
	
	

}
