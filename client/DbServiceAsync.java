package com.sweng.gwt.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.*;
import com.sweng.gwt.shared.*;

public interface DbServiceAsync {
    void registrazioneUtente(ArrayList<String> value, AsyncCallback<String> callback);
    void loginUtente(String email, String password, AsyncCallback<String> callback);
    void getCarteMagic(AsyncCallback<List<CartaMagic>> callback);
    void getCartePokemon(AsyncCallback<List<CartaPokemon>> callback);
    void getCarteYuGiOh(AsyncCallback<List<CartaYuGiOh>> callback);
    
    
    void salvaCartaMagic(CartaMagic carta, String email, AsyncCallback<String> callback);
    void salvaCartaYuGiOh(CartaYuGiOh carta, String email, AsyncCallback<String> callback);
    void salvaCartaPokemon(CartaPokemon carta, String email, AsyncCallback<String> callback);
    
    void getCarteMagicO(String email, AsyncCallback<List<CartaMagic>> callback);
    void getCartePokemonO(String email, AsyncCallback<List<CartaPokemon>> callback);
    void getCarteYuGiOhO(String email, AsyncCallback<List<CartaYuGiOh>> callback);
    
    void removeYuGiOh(String email,CartaYuGiOh carta, AsyncCallback<String> callback);
    void removeMagic(String email,CartaMagic carta, AsyncCallback<String> callback);
    void removePokemon(String email,CartaPokemon carta, AsyncCallback<String> callback);
    
    void salvaScambio(ScambioCarte scambio, AsyncCallback<String> callback);
    void getScambiByDestinatarioEmail(String destinatarioEmail, AsyncCallback<List<ScambioCarte>> callback);
    void scambio(List<Carta> daMettere, List<Carta> daTogliere, String email, AsyncCallback<String> callback);
    void rimuoviScambio(String chiave, AsyncCallback<String> callback);
    void salvaDeck(DeckFormat deck, String email, AsyncCallback<String> callback);
    void getDeck(String email, AsyncCallback<List<DeckFormat>> callback);
    void updateDeck(DeckFormat deck, String email, AsyncCallback<String> callback);
    void rimuoviDeck(DeckFormat deck, String email, AsyncCallback<String> callback);
    void salvaCartaDesiderata(Carta carta, String email,AsyncCallback<String> callback);
    void removeCartaDesiderata(Carta carta, String email,AsyncCallback<String> callback);
    void getCarteDesiderate(String email, AsyncCallback<List<Carta>> callback);
    
    void getAllUserEmails(AsyncCallback<String[]> callback);
    void getEmailsByCardName(String[] emails, String cardName, AsyncCallback<String[]> callback);
}
