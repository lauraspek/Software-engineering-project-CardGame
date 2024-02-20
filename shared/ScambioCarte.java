package com.sweng.gwt.shared;
import java.io.Serializable;
import java.util.*;

public class ScambioCarte implements Serializable {
	private String mittenteEmail;
    private String destinatarioEmail;
    private List<Carta> carteMittente;
    private List<Carta> carteDestinatario;
    private String chiaveScambio;
	
	private static final long serialVersionUID = 1L;
	
	public ScambioCarte() {}
	
	 public ScambioCarte(String mittenteEmail, String destinatarioEmail, List<Carta> carteMittente, List<Carta> carteDestinatario) {
	        this.mittenteEmail = mittenteEmail;
	        this.destinatarioEmail = destinatarioEmail;
	        this.carteMittente = carteMittente;
	        this.carteDestinatario = carteDestinatario;
	        Random random = new Random();
	        this.chiaveScambio = mittenteEmail + "-" + destinatarioEmail + "-" + random.nextInt(1000000);
	 }
	 
	 public String getMittenteEmail() {
	        return mittenteEmail;
	 }

	 public String getDestinatarioEmail() {
	        return destinatarioEmail;
	 }
	 
	 public String getChiave() {
	        return chiaveScambio;
	 }
	 
	 public List<Carta> getCarteMittente() {
	     	return carteMittente;
	 }

	 public List<Carta> getCarteDestinatario() {
	        return carteDestinatario;
	 }
	 
	
}
