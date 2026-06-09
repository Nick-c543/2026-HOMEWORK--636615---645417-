package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import org.junit.jupiter.api.Test;

class ComandoAiutoTest {


	@Test
	void testEseguiAiuto() {
		
		ComandoAiuto aiuto = new ComandoAiuto(); 
		IOSimulator io = new IOSimulator(Arrays.asList("aiuto")); 
		Partita partita = new Partita(); 
		
		aiuto.setIO(io);
		aiuto.esegui(partita);
		assertEquals("prendi regala saluta fine  posa guarda interagisci vai aiuto ", io.getMessaggio(0));
	}

}
