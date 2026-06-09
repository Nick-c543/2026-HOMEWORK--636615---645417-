package it.uniroma3.diadia;

import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.StanzaNotFoundException;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiIntrospettiva;


/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il metodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *         
 * @see IOConsole
 * @see Partita
 *          
 * @version aggiornata
 */

public class DiaDia {
	
	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	

	private Partita partita;
	private IO io; 
	/**
	 * Costruttore per inizializzare una nuova partita standard 
	 * 
	 * @param io
	 */
	public DiaDia(IO io) {
		this.partita = new Partita();
		this.io = io;
	}
	
	/**
	 * Costruttore per iniziare una nuova partita con un labirinto specifico
	 * @param labirinto
	 * @param io
	 */
	public DiaDia(Labirinto labirinto, IO io) {
		this.partita = new Partita(labirinto); 
		this.io = io;
	}

	/**
	 * Avvia il gioco
	 */
	public void gioca() {
		io.mostraMessaggio(MESSAGGIO_BENVENUTO);
		String istruzione = ""; 
		while(!this.partita.isFinita()) {
			istruzione = io.leggiRiga(); 
			if (istruzione == null) {
				break; 
			}
			processaIstruzione(istruzione); 
		}
	}
	
	public Partita getPartita() { return this.partita; }
  


	/**
	 * Processa un'istruzione 
	 *
	 * @return true se la partita è finita, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		
		Comando comandoDaEseguire;
		FabbricaDiComandi factory = new FabbricaDiComandiIntrospettiva(io);
		
		comandoDaEseguire = factory.costruisciComando(istruzione); 
		
		comandoDaEseguire.esegui(this.partita);
		
		if (this.partita.vinta()) 
			io.mostraMessaggio("Hai vinto!");
		if(!this.partita.giocatoreIsVivo())
			io.mostraMessaggio("Hai esaurito i CFU...");
		return this.partita.isFinita();

	}   

	/**
	 * Metodo main
	 * @param argc
	 * @throws StanzaNotFoundException
	 */
	public static void main(String[] argc) throws StanzaNotFoundException {
		try(Scanner scanner = new Scanner(System.in)){
			IO io = new IOConsole(scanner);
			
			DiaDia gioco = new DiaDia(io);
			gioco.gioca();
		}
	}

	
	
	
}