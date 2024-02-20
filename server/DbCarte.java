package com.sweng.gwt.server;

import org.mapdb.Serializer;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import java.util.Map;
import java.util.Map.Entry;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import com.sweng.gwt.shared.Carta;
import com.sweng.gwt.shared.CartaMagic;
import com.sweng.gwt.shared.CartaPokemon;
import com.sweng.gwt.shared.CartaYuGiOh;
import com.sweng.gwt.shared.User;

public class DbCarte{
	
	private static DB db;

	public static DB getDB() {
		DB db = DBMaker.fileDB(new File("dbCarte")).make();
		return db;
	}
	public static void salvaCartaMagic(CartaMagic carta) {
		DB db = getDB();
		 Map<String, CartaMagic> carteMagic = db.hashMap("CarteMagic", Serializer.STRING, Serializer.JAVA).createOrOpen();
		 carteMagic.put(carta.getName(), carta);
		 db.commit();
	     db.close();
	}
	public static void salvaCartaPokemon(CartaPokemon carta) {
		DB db = getDB();
		 Map<String, CartaPokemon> CartePokemon = db.hashMap("CartePokemon", Serializer.STRING, Serializer.JAVA).createOrOpen();
		 CartePokemon.put(carta.getName(), carta);
		 db.commit();
	     db.close();
	}
	public static void salvaCartaYuGiOh(CartaYuGiOh carta) {
		DB db = getDB();
		 Map<String, CartaYuGiOh> carteYuGiOh = db.hashMap("CarteYuGiOh", Serializer.STRING, Serializer.JAVA).createOrOpen();
		 carteYuGiOh.put(carta.getName(), carta);
		 db.commit();
	     db.close();
	}
	public static List<CartaMagic> getCarteMagic() {
		DB db = getDB();
        List<CartaMagic> allCarte = new ArrayList<>(); 
        ConcurrentMap<String, CartaMagic> carteMagicMap = db.hashMap("CarteMagic", Serializer.STRING, Serializer.JAVA).createOrOpen();
        allCarte.addAll(carteMagicMap.values());
        db.close();
        return allCarte;
    }
	public static List<CartaPokemon> getCartePokemon() {
		DB db = getDB();
        List<CartaPokemon> allCarte = new ArrayList<>(); 
        ConcurrentMap<String, CartaPokemon> cartePokemonMap = db.hashMap("CartePokemon", Serializer.STRING, Serializer.JAVA).createOrOpen();
        allCarte.addAll(cartePokemonMap.values());
        db.close();
        return allCarte;
    }
	public static List<CartaYuGiOh> getCarteYuGiOh() {
		DB db = getDB();
        List<CartaYuGiOh> allCarte = new ArrayList<>(); 
        ConcurrentMap<String, CartaYuGiOh> carteYuGiOhMap = db.hashMap("CarteYuGiOh", Serializer.STRING, Serializer.JAVA).createOrOpen();
        allCarte.addAll(carteYuGiOhMap.values());
        db.close();
        return allCarte;
    }
	
    
}
