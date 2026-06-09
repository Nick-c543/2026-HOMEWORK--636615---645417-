package it.uniroma3.diadia;

import java.util.*; 

/**
 * Classe che gestice Input/Output in modo automatico
 * 
 * @author Margherita Manzi
 * @see IO
 * @version aggiornata
 */
public class IOSimulator implements IO {
	
	List<String> output; 
	Map<String, List<String>> messaggi; 
	private List<String> righeInput; 
	private int indexInput;
	private String comandoCorrente;
	
	/**
	 * Inizializza il Simulator con le righe da mandare in Input 
	 * @param righeInput
	 */
	public IOSimulator(List<String> righeInput) {
		this.messaggi = new HashMap<>(); 
		this.output = new ArrayList<>(); 
		this.righeInput = righeInput;
		this.indexInput = 0; 
		this.comandoCorrente = "Inizio partita";
		this.messaggi.put(comandoCorrente, new ArrayList<>()); 
		}
	
	/**
	 * Memorizza il messaggio di Ouput in un array
	 * @param msg
	 */
	public void mostraMessaggio(String msg) {
		this.output.add(msg); 	
		this.messaggi.get(this.comandoCorrente).add(msg); 
	}
	
	/**
	 * Restituisce le righe di Input
	 * @return stringa data in Input di indice specifico
	 */
	public String leggiRiga() {
		if (this.indexInput >= this.righeInput.size())
			return null; 
		this.comandoCorrente = this.righeInput.get(indexInput++);
		this.messaggi.put(comandoCorrente, new ArrayList<>()); 
		return this.comandoCorrente;
	}
	/**
	 * Restituisce il messaggio in Output di indice i
	 * @param i indice del messaggio 
	 * @return messaggio in Output
	 */
	public String getMessaggio(int i) {
		return this.output.get(i); 
	}
	
	/**
	 * Restituisce i messaggi prodotti dal comando passato come parametro
	 * @param comando
	 * @return messaggio
	 */
	public List<String> getMessaggiProdottiDalComando(String comando){
		return this.messaggi.get(comando); 
	}
}
