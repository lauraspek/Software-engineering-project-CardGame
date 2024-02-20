package com.sweng.gwt.server;

import org.mapdb.Serializer;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import java.util.Map;
import java.util.Map.Entry;

import java.io.File;
import java.util.*;
import java.util.concurrent.ConcurrentMap;
import com.sweng.gwt.shared.*;


public class DbDeck{
	
	private static DB db;

	public static DB getDB() {
		DB db = DBMaker.fileDB(new File("dbDeck")).make();
		return db;
	}
	
	public static String salvaDeck(DeckFormat deck, String email) {
        DB db = getDB();
        Map<String, DeckFormat> dckMap = db.hashMap("Deck"+email, Serializer.STRING, Serializer.JAVA).createOrOpen();
        dckMap.put(deck.getNome(), deck);
        db.commit();
        db.close();
        return "Deck aggiunto";
    }
	
	public static List<DeckFormat> getDeck(String email){
		DB db = getDB();
	    Map<String, DeckFormat> deckMap = db.hashMap("Deck" + email, Serializer.STRING, Serializer.JAVA).createOrOpen();
	    List<DeckFormat> decks = new ArrayList<>(deckMap.values());
	    db.close();
	    return decks;
	}
	
	public static String updateDeck(DeckFormat deck, String email) {
		DB db = getDB();
		Map<String, DeckFormat> deckMap = db.hashMap("Deck" + email, Serializer.STRING, Serializer.JAVA).createOrOpen();
		deckMap.put(deck.getNome(), deck);
		db.commit();
		db.close();
		return "Success updated";
	}
	
	public static String rimuoviDeck(DeckFormat deck, String email) {
		DB db = getDB();
        Map<String, DeckFormat> deckMap = db.hashMap("Deck" + email, Serializer.STRING, Serializer.JAVA).createOrOpen();
        deckMap.remove(deck.getNome());
        db.commit();
        db.close();
		return "Deck deleted";
	}
	   	
}
