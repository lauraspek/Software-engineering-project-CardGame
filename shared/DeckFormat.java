package com.sweng.gwt.shared;
import java.io.Serializable;
import java.util.*;

public class DeckFormat implements Serializable {
	private static final long serialVersionUID = 1L;
	private List <CartaYuGiOh> carteyugioh;
	private List <CartaPokemon> cartepokemon;
	private List <CartaMagic> cartemagic;
	private String nomedeck;
	private String gioco;
	
	
	public DeckFormat() {}
	
	public DeckFormat(String nome, String gioco) {
        this.nomedeck = nome;
        this.gioco = gioco;
        this.carteyugioh = new ArrayList<>();
        this.cartepokemon = new ArrayList<>();
        this.cartemagic = new ArrayList<>();
	}
	
	public String getNome() {
		return nomedeck;
	}
	public String getGioco() {
		return gioco;
	}
	
	public void addCard(Carta carta) {
		if (carta instanceof CartaMagic) {
			CartaMagic cartaM = (CartaMagic) carta;
			cartemagic.add(cartaM);
		} else if (carta instanceof CartaYuGiOh) {
			CartaYuGiOh cartaY = (CartaYuGiOh) carta;
			carteyugioh.add(cartaY);
		} else if (carta instanceof CartaPokemon) {
			CartaPokemon cartaP = (CartaPokemon) carta;
			cartepokemon.add(cartaP);
		} 
	}
	
	public List<CartaYuGiOh> getCarteYugioh(){
		return carteyugioh;
	}
	public List<CartaPokemon> getCartePokemon(){
		return cartepokemon;
	}
	public List<CartaMagic> getCarteMagic(){
		return cartemagic;
	}
	
}
