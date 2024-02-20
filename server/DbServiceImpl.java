package com.sweng.gwt.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.sweng.gwt.shared.*;
import com.sweng.gwt.client.DbService;
import java.util.*;

public class DbServiceImpl extends RemoteServiceServlet implements DbService {

    @Override
    public String registrazioneUtente(ArrayList<String> value) {
        return DbUtenti.registrazioneUtente(value);
    }
    
    @Override
    public String loginUtente(String email, String password) {
        return DbUtenti.loginUtente(email, password);
    }
    
    @Override
    public List<CartaMagic> getCarteMagic() {
        return DbCarte.getCarteMagic();
    }
    @Override
    public List<CartaPokemon> getCartePokemon() {
        return DbCarte.getCartePokemon();
    }
    @Override
    public List<CartaYuGiOh> getCarteYuGiOh() {
        return DbCarte.getCarteYuGiOh();
    }
    
    @Override
    public String salvaCartaMagic(CartaMagic carta, String email) {
        return DbPossedimenti.salvaCartaMagic(carta, email);
    }
    @Override
    public String salvaCartaYuGiOh(CartaYuGiOh carta, String email) {
        return DbPossedimenti.salvaCartaYuGiOh(carta, email);
    }
    
    public String salvaCartaPokemon(CartaPokemon carta, String email) {
        return DbPossedimenti.salvaCartaPokemon(carta, email);
    }
    
    
    @Override
    public List<CartaMagic> getCarteMagicO(String email) {
        return DbPossedimenti.getCarteMagicO(email);
    }
    @Override
    public List<CartaPokemon> getCartePokemonO(String email) {
        return DbPossedimenti.getCartePokemonO(email);
    }
    @Override
    public List<CartaYuGiOh> getCarteYuGiOhO(String email) {
        return DbPossedimenti.getCarteYuGiOhO(email);
    }
    
    @Override
    public String removeYuGiOh(String email, CartaYuGiOh carta) {
    	return DbPossedimenti.removeYuGiOh(email, carta);
    }
    
    @Override
    public String removeMagic(String email, CartaMagic carta) {
    	return DbPossedimenti.removeMagic(email, carta);
    }
    
    
    @Override
    public String removePokemon(String email, CartaPokemon carta) {
    	return DbPossedimenti.removePokemon(email, carta);
    }
    
    
    @Override
    public String salvaScambio(ScambioCarte scambio) {
    	return DbScambi.salvaScambio(scambio);
    }
    @Override
    public List<ScambioCarte> getScambiByDestinatarioEmail(String destinatarioEmail){
    	return DbScambi.getScambiByDestinatarioEmail(destinatarioEmail);
    }
    @Override
    public String scambio(List<Carta> daMettere, List<Carta> daTogliere, String email){
    	return DbPossedimenti.scambio(daMettere, daTogliere, email);
    }
    
    @Override
    public String rimuoviScambio(String chiave) {
    	return DbScambi.rimuoviScambio(chiave);
    }
    @Override
    public String salvaDeck(DeckFormat deck, String email) {
    	return DbDeck.salvaDeck(deck, email);
    }
    @Override
    public List<DeckFormat> getDeck(String email){
    	return DbDeck.getDeck(email);
    }
    @Override
    public String updateDeck(DeckFormat deck, String email) {
    	return DbDeck.updateDeck(deck, email);
    }
    @Override
    public String rimuoviDeck(DeckFormat deck, String email) {
    	return DbDeck.rimuoviDeck(deck, email);
    }
    @Override
    public String salvaCartaDesiderata(Carta carta, String email) {
    	return DbPossedimenti.salvaCartaDesiderata(carta, email);
    }
    @Override
    public String removeCartaDesiderata(Carta carta, String email) {
    	return DbPossedimenti.removeCartaDesiderata(carta, email);
    }
    @Override
    public List<Carta> getCarteDesiderate(String email){
    	return DbPossedimenti.getCarteDesiderate(email);
    }
    @Override
    public String[] getAllUserEmails() {
    	return DbUtenti.getAllUserEmails();
    }
    @Override
    public String[] getEmailsByCardName(String[] emails, String cardName) {
    	return DbPossedimenti.getEmailsByCardName(emails, cardName);
    }
}
