package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DiaDiaTest {

	@Test
	void testPartitaConVittoriaImmediata() {
		IOSimulator io = new IOSimulator(new String[] {"vai nord"});
		DiaDia gioco = new DiaDia(io); 
		gioco.gioca(); 
		
		assertEquals(io.getMessaggio(1), "Biblioteca");
		assertEquals(io.getMessaggio(2), "Hai vinto!");
	}
	
	@Test
	void testPartitaConSconfittaFineCfu() {
		IOSimulator io = new IOSimulator(
				new String[]{"vai sud", "vai est", "vai est", "vai est", 
						"vai sud", "vai est", "vai est", "vai est",
						"vai sud", "vai est", "vai est", "vai est",
						"vai sud", "vai est", "vai est", "vai est",
						"vai sud", "vai est", "vai est", "vai est"});
		DiaDia gioco = new DiaDia(io); 
		gioco.gioca();
		
		assertEquals(io.getMessaggio(1), "Aula N10");
		assertEquals(io.getMessaggio(2), "Aula N11");
		assertEquals(io.getMessaggio(3), "Laboratorio Campus");
		
		for (int i = 1; i<4; i++) {
			assertEquals(io.getMessaggio(i*4), "Atrio");
			assertEquals(io.getMessaggio(i*4+1), "Aula N10"); 
			assertEquals(io.getMessaggio(i*4+2), "Aula N11");
			assertEquals(io.getMessaggio(i*4+3), "Laboratorio Campus");
		}
		assertEquals(io.getMessaggio(20), "Atrio");
		assertEquals(io.getMessaggio(21), "Hai esaurito i CFU...");
		
	}
	
	@Test
	void testPartitaComandoNonValido() {
		IOSimulator io = new IOSimulator(new String[]{"vada su", "fine"});
		DiaDia gioco = new DiaDia(io); 
		gioco.gioca();
		assertEquals(io.getMessaggio(1), "Comando inesistente");
		assertEquals(io.getMessaggio(2), "Il comando non è valido... riprova: ");
	}
	
	@Test
	void testPartitaDirezioneNonValida() {
		IOSimulator io = new IOSimulator(new String[] {"vai su", "fine"});
		DiaDia gioco = new DiaDia(io); 
		gioco.gioca();
		assertEquals(io.getMessaggio(1),"Direzione inesistente. Usa nord, est, sud o ovest.");
	}
	

}
