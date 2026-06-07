package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;

public abstract class AbstractComando implements Comando{
	
	private String nome; 
	private String parametro;
	private IO io; 
	
	public AbstractComando(String nome) {
		this.nome = nome; 
	}
	
	abstract public void esegui(Partita partita); 
	
	public void setParametro(String parametro) { this.parametro = parametro; }
	
	public String getParametro() { return this.parametro; }
	
	public void setIO(IO io) { this.io = io; }
	
	public IO getIO() { return this.io; }
	
	
	public String getNome() { return this.nome; }
	

}


