package it.uniroma3.diadia;

/**
 * Gestisce Input/Output tramite due semplici funzioni, 
 * da usare al posto di System.in e System.out
 * 
 * @author Margherita Manzi 
 * @version base
 */
public interface IO {
	/**
	 * Mostra il messaggio di Output (prende il posto di System.out)
	 * @param messaggio da mandare in output
	 */
	public void mostraMessaggio(String messaggio);
	
	/**
	 * Legge una riga in Input (prende il posto di System.in)
	 * @return stringa data in Input
	 */
	public String leggiRiga();
}
