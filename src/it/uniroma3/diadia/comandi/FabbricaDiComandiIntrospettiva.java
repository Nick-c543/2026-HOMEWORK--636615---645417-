package it.uniroma3.diadia.comandi;

import java.util.Scanner;

import it.uniroma3.diadia.IO;

public class FabbricaDiComandiIntrospettiva implements FabbricaDiComandi{
	
	private IO io; 
	
	public FabbricaDiComandiIntrospettiva(IO io) {
		this.io = io;
	}
	
	@Override
	public Comando costruisciComando(String istruzione){
		
		if(istruzione == null) {
			istruzione = "fine";
		}
		
		Scanner scannerDiParole = new Scanner(istruzione); 
		String nomeComando = null; 
		String parametro = null; 
		AbstractComando comando = null; 
		
		if (scannerDiParole.hasNext())
			nomeComando = scannerDiParole.next(); 
		
		if (nomeComando == null) {
			comando = new ComandoNonValido(); 
			comando.setIO(io);
			return comando;
		}
		
		if (scannerDiParole.hasNext())
			parametro = scannerDiParole.next(); 
		
		try {
		
			StringBuilder nomeClasse = 
					new StringBuilder("it.uniroma3.diadia.comandi.Comando"); 
			nomeClasse.append(Character.toUpperCase(nomeComando.charAt(0)));
			nomeClasse.append(nomeComando.substring(1));
			comando = (AbstractComando)Class.forName(nomeClasse.toString()).newInstance();
		
			//alternativa possibile: 
			//comando = ((Class<Comando>)Class.forName(nomeClasse.toString())).newInstance();
			comando.setParametro(parametro);
			comando.setIO(this.io) ;
			
		} catch (Exception e) {
			comando = new ComandoNonValido(); 
			comando.setIO(io);
			this.io.mostraMessaggio("Comando inesistente"); 
		}
		
		return comando; 
	}

}
