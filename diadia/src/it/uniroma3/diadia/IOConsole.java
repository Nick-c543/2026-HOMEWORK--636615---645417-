package it.uniroma3.diadia;
import java.util.Scanner;

/**
 * Classe che gestisce la console di Input/Output del gioco
 * 
 * @author Margherita Manzi e Niccolò Ilari
 * @see IO
 * @version base
 */

public class IOConsole implements IO{
	
	private Scanner scanner; 
	
	/**
	 * Inizializza la Console
	 */
	public IOConsole() {
		this.scanner = new Scanner(System.in);
	}
	
	
	/**
	 * Stampa a a schermo la stringa ricevuta 
	 * @param msg
	 */
	@Override
	public void mostraMessaggio(String msg) {
		System.out.println(msg);
	}
	
	/**
	 * Legge e memorizza l'input da tastiera 
	 * @return rigaInput
	 */
	@Override
	public String leggiRiga() {
		String riga = this.scanner.nextLine();
		//this.scanner.close();
		return riga;
	}
}