package it.uniroma3.diadia;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Classe per la gestione delle configurazioni iniziali del gioco DiaDia. 
 * Legge i parametri da un file properties esterno. 
 * Se il file non viene trovato o risulta illegibile, la classe fornisce
 * i valori di de default. 
 * @author Margherita Manzi e Niccolo Ilari
 * @versione base
 */
public class Configurazione {
	
	private static final String NOME_FILE = "diadia.properties"; 
	private static Properties prop = null; 
	
	/**
	 * Tenta di aprire e leggere il file specificato.
	 * Cattura eventuali eccezioni di I/O, avvisando
	 * l'utente tramite standard error. 
	 */
	private static void carica() {
		prop = new Properties(); 
		try {
			FileInputStream input = new FileInputStream(NOME_FILE); 
			prop.load(input);
			input.close(); 
		} catch (IOException e) {
			System.err.println("File " + NOME_FILE + " non trovato, uso i valori di default."); 
		}
	}
	
	/**
	 * Restituisce i CFU iniziali del giocatore cercandoli
	 * nel file properties. Se non li trova li imposta a 20.
	 * @return numero CFU iniziali
	 */
	public static int getCFU() {
		if(prop == null) carica(); 
		String valore = prop.getProperty("cfu_iniziali", "20"); 
		return Integer.parseInt(valore); 
	}
	
	/**
	 * Restituisce i il peso massimo iniziale della borsa cercandolo
	 * nel file properties. Se non lo trova lo imposta a 10.
	 * @return numero CFU iniziali
	 */
	public static int getPesoMax() {
		if (prop == null) carica();
		String valore = prop.getProperty("peso_max_borsa", "10");
		return Integer.parseInt(valore);
	}
}
