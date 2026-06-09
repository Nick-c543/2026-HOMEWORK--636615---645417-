package it.uniroma3.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo; 

public class Cane extends AbstractPersonaggio{
	
	private String ciboPreferito; 
	private Attrezzo attrezzoPremio; 
	
	
	public Cane(String nome, String presentazione) {
		super(nome, presentazione); 
		this.ciboPreferito = "osso"; 
		this.attrezzoPremio = new Attrezzo("pallina", 2); 
	}
	
	@Override
	public String agisci(Partita partita) {
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu() - 1);
		String msg = "Ti ho morso! Ora ti rimangono solo " + 
				partita.getGiocatore().getCfu() + " Cfu..."; 
		return msg; 
	}
	
	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		String risposta = ""; 
		if (attrezzo.getNome().equals("osso")) {
			risposta = "Grazie per avermi dato il mio cibo preferito. In cambio ti farò un regalo."; 
			partita.getStanzaCorrente().addAttrezzo(this.attrezzoPremio); 
			this.attrezzoPremio = null; 
		}
		else {
			partita.getGiocatore().setCfu(partita.getGiocatore().getCfu() - 1);
			risposta = "Che cosa mi hai dato!? Non mi interessa! Ora ti rimangono solo " 
			+ partita.getGiocatore().getCfu() + " Cfu...";
			partita.getStanzaCorrente().addAttrezzo(attrezzo); 
			
		}
		return risposta; 
	}
	
	

}
