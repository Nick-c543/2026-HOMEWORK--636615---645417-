package it.uniroma3.diadia;

/**
 * Classe che gestice Input/Output in modo automatico
 * 
 * @author Margherita Manzi
 * @see IO
 * @version base
 */
public class IOSimulator implements IO {
	
	static final private int DIM_MAX = 50;
	String[] messaggi; 
	private int numeroMessaggi;
	
	private String[] righeInput; 
	private int indexInput;
	
	/**
	 * Inizializza il Simulator con le righe da mandare in Input 
	 * e un numero massimo di messaggi da memorizzare 
	 * @param righeInput
	 */
	public IOSimulator(String[] righeInput) {
		this.messaggi = new String[DIM_MAX];
		this.numeroMessaggi = 0; 
		this.righeInput = righeInput;
		this.indexInput = 0; 
	}
	
	/**
	 * Memorizza il messaggio di Ouput in un array
	 * @param msg
	 */
	public void mostraMessaggio(String msg) {
		if (this.numeroMessaggi < DIM_MAX) {
			this.messaggi[this.numeroMessaggi] = msg; 
			this.numeroMessaggi++;
		}
		else {
			messaggi[0] = msg; 
			this.numeroMessaggi = 1; 
		}		
	}
	
	/**
	 * Restituisce le righe di Input
	 * @return stringa data in Input di indice specifico
	 */
	public String leggiRiga() {
		return this.righeInput[this.indexInput++];
	}
	/**
	 * Restituisce il messaggio in Output di indice i
	 * @param i indice del messaggio 
	 * @return messaggio in Output
	 */
	public String getMessaggio(int i) {
		return this.messaggi[i]; 
	}
}
