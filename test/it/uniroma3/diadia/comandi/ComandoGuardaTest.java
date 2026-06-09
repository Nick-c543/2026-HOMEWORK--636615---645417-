package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import it.uniroma3.diadia.*;
import org.junit.jupiter.api.Test;

class ComandoGuardaTest {


	@Test
	void test() {
		ComandoGuarda guarda = new ComandoGuarda(); 
		Partita partita = new Partita(); 
		IOSimulator io = new IOSimulator(Arrays.asList("guarda")); 
		guarda.setIO(io);
		
		String expected = "Sei nella stanza Atrio\nUscite: EST NORD OVEST SUD\nAttrezzi nella stanza: osso (1kg) ";
		guarda.esegui(partita);
		assertEquals(expected.trim(), io.getMessaggio(0).trim());
		expected = "Hai ancora a disposizione 20 Cfu."; 
		assertEquals(expected.trim(), io.getMessaggio(1).trim());
		expected = "Borsa vuota";
		assertEquals(expected.trim(), io.getMessaggio(2).trim());
	}

}
