package it.uniroma3.diadia.attrezzi; 
import it.uniroma3.diadia.ambienti.Stanza;

/**
 * Una semplice classe che modella un attrezzo.
 * Gli attrezzi possono trovarsi all'interno delle stanze
 * del labirinto.
 * Ogni attrezzo ha un nome ed un peso.
 *
 * @author  docente di POO
 * @see Stanza
 * @version aggiornata
 */
public class Attrezzo {

	private String nome;
	private int peso;

	/**
	 * Crea un attrezzo
	 * @param nome il nome che identifica l'attrezzo
	 * @param peso il peso dell'attrezzo
	 */
	public Attrezzo(String nome, int peso) {
		this.peso = peso;
		this.nome = nome;
	}

	/**
	 * Restituisce il nome identificatore dell'attrezzo
	 * @return il nome identificatore dell'attrezzo
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Restituisce il peso dell'attrezzo
	 * @return il peso dell'attrezzo
	 */
	public int getPeso() {
		return this.peso;
	}
	
	public void setPeso(int peso) { this.peso = peso; }

	/**
	 * Restituisce una rappresentazione stringa di questo attrezzo
	 * @return la rappresentazione stringa
	 */
	public String toString() {
		return this.getNome()+" ("+this.getPeso()+"kg)";
	}
	
	/**
	 * Due Attrezzi sono uguali se lo sono i loro pesi e nomi
	 */
	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		
		if (o == null || this.getClass() != o.getClass()) 
	        return false; 
		
		Attrezzo that = (Attrezzo)o; 
		
		return this.getPeso() == that.getPeso() && this.getNome().equals(that.getNome()) ;
	}
	
	@Override
	public int hashCode() {
		return this.getNome().hashCode() + this.getPeso(); 
	}
	
	public int comparePeso(Attrezzo a1, Attrezzo a2) {
		return a1.getPeso() - a2.getPeso(); 
	}
	
	public int compareNome(Attrezzo a1, Attrezzo a2) {
		return a1.getNome().compareTo(a2.getNome());
	}

}