package com.sweng.gwt;
import java.util.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import com.sweng.gwt.shared.*;

import com.sweng.gwt.server.DbServiceImpl;

public class test {
	private DbServiceImpl dbService;
	private ScambioCarte scambio;
	
	@Before
    public void setUp() {
        dbService = new DbServiceImpl(); 
        List<Carta> carteMittente = new ArrayList<>();
        List<Carta> carteDestinatario = new ArrayList<>();
        scambio = new ScambioCarte("test1@email.com", "test2@email.com", carteMittente, carteDestinatario);
    }
	
	@Test
    public void testRegistrazioneUtente() {
        ArrayList<String> datiUtente = new ArrayList<>();
        datiUtente.add("test1@email.com");
        datiUtente.add("test1");
        String risultato = dbService.registrazioneUtente(datiUtente);
        assertEquals("Registrazione completata", risultato);
    }

    @Test
    public void testLoginUtente() {
        String email = "test1@email.com";
        String password = "test1";
        String risultato = dbService.loginUtente(email, password);
        assertEquals(email, risultato);
    }
    
    @Test
    public void testGetCarteYuGiOh() {
        List<CartaYuGiOh> carteYuGiOh = dbService.getCarteYuGiOh();
        System.out.println(carteYuGiOh.size());
        assertNotNull(carteYuGiOh);
        assertFalse(carteYuGiOh.isEmpty());
    }
    
    @Test
    public void testAddScambio() {
    	String risultato = dbService.salvaScambio(this.scambio);
    	System.out.println(risultato);
        assertEquals("Successo", risultato);
    }
    
    @Test
    public void testRemoveScambio() {
    	String risultato = dbService.rimuoviScambio(scambio.getChiave());
    	System.out.println(risultato);
        assertEquals("Scambio rimosso", risultato);
    }
    
    @Test
    public void getAllEmail() {
    	String[] risultato = dbService.getAllUserEmails();
    	System.out.println(""+ risultato.length);
    	assertNotNull(risultato);
        assertFalse(risultato.length==0);
    }
	
}
