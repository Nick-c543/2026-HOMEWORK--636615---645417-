package it.uniroma3.diadia;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configurazione {
	
	private static final String NOME_FILE = "diadia.properties"; 
	private static Properties prop = null; 
	
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
	
	public static int getCFU() {
		if(prop == null) carica(); 
		String valore = prop.getProperty("cfu_iniziali", "20"); 
		return Integer.parseInt(valore); 
	}
	
	public static int getPesoMax() {
		if (prop == null) carica();
		String valore = prop.getProperty("peso_max_borsa", "10");
		return Integer.parseInt(valore);
	}
}
