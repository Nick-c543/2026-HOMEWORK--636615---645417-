package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import it.uniroma3.diadia.*; 
import org.junit.jupiter.api.Test;

class ComandoNonValidoTest {


	@Test
	void test() {
		ComandoNonValido nonValido = new ComandoNonValido(); 
		Partita partita = new Partita();
		IOSimulator io = new IOSimulator(Arrays.asList("AH")); 
		nonValido.setIO(io);
		
		nonValido.esegui(partita);
		assertEquals("Il comando non è valido... riprova: ", io.getMessaggio(0));
		
	}

}
