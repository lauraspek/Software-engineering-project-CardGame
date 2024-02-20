package com.sweng.gwt.server;

import com.google.gson.Gson;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import com.google.gson.reflect.TypeToken;
import com.google.gwt.core.client.GWT;
import com.sweng.gwt.shared.Carta;
import com.sweng.gwt.shared.CartaMagic;
import com.sweng.gwt.shared.CartaPokemon;
import com.sweng.gwt.shared.CartaYuGiOh;
import com.sweng.gwt.server.DbCarte;

public class DatabaseInitializer {
	
    private final String jsonMPath = "json/magic_cards.json";
    private final String jsonPPath = "json/pokemon_cards.json";
    private final String jsonYPath = "json/yugioh_cards.json";
    private static final String DB_FILENAME = "dbCarte";

    public void initializeDatabaseIfEmpty() {
        if (databaseIsEmpty()) {
            populateDatabaseFromJson();
        } 
    }

    public static boolean databaseIsEmpty() {
        File dbFile = new File(DB_FILENAME);
        return !dbFile.exists();
    }
    
	
    private void populateDatabaseFromJson() {
  
        try (Reader reader = new FileReader(jsonMPath)) {
            Gson gson = new Gson();
            List<CartaMagic> cardsM = gson.fromJson(reader, new TypeToken<List<CartaMagic>>() {}.getType());
            for (CartaMagic carta : cardsM) {
            	System.out.println("Nome: " + carta.getName() + ", Rarità: " + carta.getRarity());
                DbCarte.salvaCartaMagic(carta);
            }     
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } 
        
        try (Reader reader = new FileReader(jsonPPath)) {
            Gson gson = new Gson();
            List<CartaPokemon> cardsP = gson.fromJson(reader, new TypeToken<List<CartaPokemon>>() {}.getType());
            for (CartaPokemon carta : cardsP) {
            	System.out.println("Nome: " + carta.getName());
                DbCarte.salvaCartaPokemon(carta);
            }     
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } 
        
        try (Reader reader = new FileReader(jsonYPath)) {
            Gson gson = new Gson();
            List<CartaYuGiOh> cardsY = gson.fromJson(reader, new TypeToken<List<CartaYuGiOh>>() {}.getType());
            for (CartaYuGiOh carta : cardsY) {
            	System.out.println("Nome: " + carta.getName());
                DbCarte.salvaCartaYuGiOh(carta);
            }     
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }  
       
        /*
        try (Reader reader2 = new FileReader(jsonPPath)) {
            Gson gson = new Gson();
            List<CartaPokemon> cardsP = gson.fromJson(reader2, new TypeToken<List<CartaPokemon>>() {}.getType());
            for (CartaPokemon carta : cardsP) {
                DbCarte.salvaCarta(carta, "pokemon");
            }  
            reader2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try (Reader reader3 = new FileReader(jsonYPath)) {
            Gson gson = new Gson();
            List<CartaYuGiOh> cardsY = gson.fromJson(reader3, new TypeToken<List<CartaYuGiOh>>() {}.getType());
            for (CartaYuGiOh carta : cardsY) {
                DbCarte.salvaCarta(carta, "yugioh");
            }
            reader3.close();
        } catch (IOException e) {
            e.printStackTrace();
        } 
        */
    }

}

