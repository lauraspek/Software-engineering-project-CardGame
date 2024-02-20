package com.sweng.gwt.client;

import java.util.*;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.sweng.gwt.shared.*;

@RemoteServiceRelativePath("dbservice")
public interface DbService extends RemoteService {
    String registrazioneUtente(ArrayList<String> value);
    String loginUtente(String email, String password);
    
    List<CartaMagic> getCarteMagic();
    List<CartaPokemon> getCartePokemon();
    List<CartaYuGiOh> getCarteYuGiOh();
    
    
    String salvaCartaMagic(CartaMagic carta, String email);
    String salvaCartaYuGiOh(CartaYuGiOh carta, String email);
    String salvaCartaPokemon(CartaPokemon carta, String email);
    
    List<CartaMagic> getCarteMagicO(String email);
    List<CartaPokemon> getCartePokemonO(String email);
    List<CartaYuGiOh> getCarteYuGiOhO(String email);
    
    String removeYuGiOh(String email, CartaYuGiOh carta);
    String removeMagic(String email, CartaMagic carta);
    String removePokemon(String email, CartaPokemon carta);
    
    String salvaScambio(ScambioCarte scambio);
    List<ScambioCarte> getScambiByDestinatarioEmail(String destinatarioEmail);
    String scambio(List<Carta> daMettere, List<Carta> daTogliere, String email);
    String rimuoviScambio(String chiave);
    String salvaDeck(DeckFormat deck, String email);
    List<DeckFormat> getDeck(String email);
    String updateDeck(DeckFormat deck, String email);
    String rimuoviDeck(DeckFormat deck, String email);
    String salvaCartaDesiderata(Carta carta, String email);
    String removeCartaDesiderata(Carta carta, String email);
    List<Carta> getCarteDesiderate(String email);
    
    String[] getAllUserEmails();
    String[] getEmailsByCardName(String[] emails, String cardName);
}
