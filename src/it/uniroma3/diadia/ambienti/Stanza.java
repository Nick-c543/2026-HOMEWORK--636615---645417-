package it.uniroma3.diadia.ambienti;

import java.util.*; 
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.personaggi.*;

/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author docente di POO 
 * @see Attrezzo
 * @version aggiornata
*/

public class Stanza {
	
	private String nome;
   	private List<Attrezzo> attrezzi; 
    private Map<Direzione, Stanza> stanzeAdiacenti;
    private AbstractPersonaggio personaggio; 

    
    
    /**
     * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
     * @param nome il nome della stanza
     */
    public Stanza(String nome) {
        this.nome = nome;
        this.stanzeAdiacenti = new HashMap<>(); 
        this.attrezzi = new ArrayList<>();
        this.personaggio = null; 
    }

    /**
     * Imposta una stanza adiacente.
     *
     * @param direzione in cui sara' posta la stanza adiacente.
     * @param stanza adiacente nella direzione indicata dal primo parametro.
     */
    public void impostaStanzaAdiacente(Direzione direzione, Stanza stanza) {
    	this.stanzeAdiacenti.put(direzione, stanza);
    }

    /**
     * Restituisce la stanza adiacente nella direzione specificata
     * @param direzione
     */
	public Stanza getStanzaAdiacente(Direzione direzione) {
		return this.stanzeAdiacenti.get(direzione);
	}

    /**
     * Restituisce la nome della stanza.
     * @return il nome della stanza
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Restituisce la descrizione della stanza.
     * @return la descrizione della stanza
     */
    public String getDescrizione() {
        return this.toString();
    }

    /**
     * Restituisce la collezione di attrezzi presenti nella stanza.
     * @return collezione di attrezzi nella stanza.
     */
    public List<Attrezzo> getAttrezzi() {
        return this.attrezzi;
    }

    /**
     * Aggiunge un attrezzo alla stanza.
     * @param attrezzo l'attrezzo da mettere nella stanza.
     * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
     */
    public boolean addAttrezzo(Attrezzo attrezzo) {
    	return this.attrezzi.add(attrezzo); 
    }

   /**
	* Restituisce una rappresentazione stringa di questa stanza,
	* stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	* @return la rappresentazione stringa
	*/
    public String toString() {
    	StringBuilder risultato = new StringBuilder();
    	risultato.append(this.nome);
    	risultato.append("\nUscite: ");
    	for (Direzione direzione : this.stanzeAdiacenti.keySet())
    		if (direzione!=null)
    			risultato.append(" " + direzione);
    	risultato.append("\nAttrezzi nella stanza: ");
    	for (Attrezzo attrezzo : this.attrezzi) {
    		if(attrezzo!=null)   
    			risultato.append(attrezzo.toString()+" "); 
    	}
    	return risultato.toString();
    }

    /**
	* Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	* @return true se l'attrezzo esiste nella stanza, false altrimenti.
	*/
	public boolean hasAttrezzo(String nomeAttrezzo) {
		boolean trovato;
		trovato = false;
		for (Attrezzo attrezzo : this.attrezzi) {
			if(attrezzo!=null) { 
				if (attrezzo.getNome().equals(nomeAttrezzo))
					trovato = true;
			}
		}
		return trovato;
	}

	/**
     * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
     * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		
		//Iterator<Attrezzo> it = this.attrezzi.iterator(); 
		for (Attrezzo a: this.attrezzi)
		//while(it.hasNext()) {
			//Attrezzo a = it.next(); 
			if (a.getNome().equals(nomeAttrezzo))
					return a; 
		//}
		return null; 
	}
	

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return attrezzo rimosso
	 */
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo cercato=null; 
		
		Iterator<Attrezzo> it = this.attrezzi.iterator(); 
		
		while(it.hasNext()) {
			Attrezzo a = it.next(); 
			if (a.getNome().equals(nomeAttrezzo)) {
				cercato = a; 
				it.remove(); 
				break; 
			}
		}
		return cercato;
	}

	/**
	 * Restituisce le direzioni della stanza per cui sono presenti stanze adiacenti.
	 * @return direzioni
	 */
	public Set<Direzione> getDirezioni() {
		return this.stanzeAdiacenti.keySet(); 
    }
	
	public AbstractPersonaggio getPersonaggio() {
		return this.personaggio;
	}
	
	public void setPersonaggio(AbstractPersonaggio personaggio) {
		this.personaggio = personaggio; 
	}

}