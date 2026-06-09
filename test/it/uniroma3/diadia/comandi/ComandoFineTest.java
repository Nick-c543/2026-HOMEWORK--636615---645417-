package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import it.uniroma3.diadia.*;
import org.junit.jupiter.api.Test;

class ComandoFineTest {

	@Test
	void testComandoFineEsegui() {
		ComandoFine fine = new ComandoFine();
		Partita partita = new Partita(); 
		IOSimulator io = new IOSimulator(Arrays.asList("fine")); 
		fine.setIO(io);
		
		fine.esegui(partita);
		assertEquals("Grazie per aver giocato!", io.getMessaggio(0));
		assertTrue(partita.isFinita());
		
	}

}
