package it.uniroma3.personaggi;

import it.uniroma3.diadia.attrezzi.*;
import it.uniroma3.diadia.Partita;

public class Mago extends AbstractPersonaggio{
	
	private static final String MESSAGGIO_DONO = 
			"Sei un vero simpaticone, con una mia magica azione, " +
			"troverai un nuovo oggetto per il tuo borsone!"; 
	
	private static final String MESSAGGIO_SCUSE = 
			"Mi spiace, ma non ho più nulla..."; 
	
	private Attrezzo attrezzo; 
	
	public Mago(String nome, String presentazione, Attrezzo attrezzo) {
		super(nome, presentazione); 
		this.attrezzo = attrezzo; 
	}
	
	@Override
	public String agisci(Partita partita) {
		String msg; 
		if (this.attrezzo != null) {
			partita.getStanzaCorrente().addAttrezzo(this.attrezzo); 
			this.attrezzo = null; 
			msg = MESSAGGIO_DONO; 
		}
		else msg = MESSAGGIO_SCUSE;
		
		return msg; 
	}
	
	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		attrezzo.setPeso(attrezzo.getPeso()/2);
		partita.getStanzaCorrente().addAttrezzo(attrezzo); 
		return "Ho fatto una magia: ora il tuo attrezzo è più leggero!"; 
	}
}
