package it.uniroma3.diadia.comandi;


import java.net.URL;

import java.io.File; 

import it.uniroma3.diadia.Partita;

/**
 * Comando Aiuto: 
 * Elenca tutti i comandi disponibili
 * 
 * @author Margherita Manzi
 * @see Comando
 * @version base
 */
public class ComandoAiuto extends AbstractComando{
	
	//static final private String[] elencoComandi = {"vai", "aiuto", "prendi", "posa", "fine", "guarda"};
	
	/** 
	 * 
	 */
	public ComandoAiuto() {
		super("aiuto");
	}
	
	/**
	 * Mostra l'elenco dei comandi disponibili nella partita
	 * @param partita
	 */
	@Override
	public void esegui(Partita partita) {
		try {
			String packageName = "it/uniroma3/diadia/comandi"; 
			URL cartellaUrl = this.getClass().getClassLoader().getResource(packageName); 
			
			if (cartellaUrl == null) {
				this.getIO().mostraMessaggio("Errore: cartella comandi non trovata."); 
				return; 
			}
			
			File directory = new File(cartellaUrl.getFile());
			File[] files = directory.listFiles();
			
			if (files != null) {
				StringBuilder elenco = new StringBuilder();
				
				for (File file : files) {
					String nomeFile = file.getName();
					
					// 3. Verifichiamo le due assunzioni della slide: 
					// Inizia con "Comando" e finisce con ".class" (ignoro file estranei)
					if (nomeFile.startsWith("Comando") && 
						    nomeFile.endsWith(".class") && 
						    !nomeFile.endsWith("Test.class") && // Esclude ComandoVaiTest.class ecc.
						    !nomeFile.equals("ComandoNonValido.class")) { // Esclude il comando di errore
						    
						    // 4. Estraggo il nome del comando. 
						    String nomeComando = nomeFile.substring(7, nomeFile.length() - 6).toLowerCase();
						    
						    elenco.append(nomeComando).append(" ");
						}
				}
				
				// Stampo tutti i comandi trovati in un colpo solo
				this.getIO().mostraMessaggio(elenco.toString());
				this.getIO().mostraMessaggio(""); 
			}
			
		} catch (Exception e) {
			this.getIO().mostraMessaggio("Si è verificato un errore durante la ricerca dei comandi.");
		}
	}
}
