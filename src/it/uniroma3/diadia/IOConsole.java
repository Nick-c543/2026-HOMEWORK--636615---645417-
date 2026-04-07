package it.uniroma3.diadia;
import java.util.Scanner;

/**
 * Classe che gestisce la console di Input/Output del gioco
 * 
 * @author Margherita Manzi e Niccolò Ilari
 * @versione base
 */

public class IOConsole {
	
	/**
	 * Stampa a a schermo la stringa ricevuta 
	 * @param msg
	 */
	public void mostraMessaggio(String msg) {
		System.out.println(msg);
	}
	
	/**
	 * Legge e memorizza l'input 
	 * @return rigaInput
	 */
	public String leggiRiga() {
		Scanner scannerDiLinee = new Scanner(System.in);
		String riga = scannerDiLinee.nextLine();
		//scannerDiLinee.close();
		return riga;
	}
}
