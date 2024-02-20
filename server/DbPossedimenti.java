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

public class DbPossedimenti{
	
	private static DB db;

	public static DB getDB() {
		DB db = DBMaker.fileDB(new File("dbPossedimenti")).make();
		return db;
	}
	public static String salvaCartaMagic(CartaMagic carta, String email) {
		DB db = getDB();
		 Map<String, CartaMagic> carteMagic = db.hashMap(email+"-Magic", Serializer.STRING, Serializer.JAVA).createOrOpen();
		 carteMagic.put(carta.getName(), carta);
		 db.commit();
	     db.close();
	     return "Added" + email;
	}
	public static String salvaCartaPokemon(CartaPokemon carta, String email) {
		DB db = getDB();
		 Map<String, CartaPokemon> CartePokemon = db.hashMap(email+"-Pokemon", Serializer.STRING, Serializer.JAVA).createOrOpen();
		 CartePokemon.put(carta.getName(), carta);
		 db.commit();
	     db.close();
	     return "Added"+ email;
	}
	public static String salvaCartaYuGiOh(CartaYuGiOh carta, String email) {
		DB db = getDB();
		 Map<String, CartaYuGiOh> carteYuGiOh = db.hashMap(email+"-YuGiOh", Serializer.STRING, Serializer.JAVA).createOrOpen();
		 carteYuGiOh.put(carta.getName(), carta);
		 db.commit();
	     db.close();
	     return "Added"+ email;
	}
	
	//metodi get
	public static List<CartaMagic> getCarteMagicO(String email) {
	    DB db = getDB();
	    Map<String, CartaMagic> carteMagic = db.hashMap(email + "-Magic", Serializer.STRING, Serializer.JAVA).createOrOpen();
	    List<CartaMagic> cartaMagicList = new ArrayList<>(carteMagic.values());
	    db.close();
	    return cartaMagicList;
	}

	public static List<CartaPokemon> getCartePokemonO(String email) {
	    DB db = getDB();
	    Map<String, CartaPokemon> cartePokemon = db.hashMap(email + "-Pokemon", Serializer.STRING, Serializer.JAVA).createOrOpen();
	    List<CartaPokemon> cartaPokemonList = new ArrayList<>(cartePokemon.values());
	    db.close();
	    return cartaPokemonList;
	}

	public static List<CartaYuGiOh> getCarteYuGiOhO(String email) {
	    DB db = getDB();
	    Map<String, CartaYuGiOh> carteYuGiOh = db.hashMap(email + "-YuGiOh", Serializer.STRING, Serializer.JAVA).createOrOpen();
	    List<CartaYuGiOh> cartaYuGiOhList = new ArrayList<>(carteYuGiOh.values());
	    db.close();
	    return cartaYuGiOhList;
	}
	
	//metodi remove
	
	public static String removeYuGiOh(String email, CartaYuGiOh carta) {
	    DB db = getDB();
	    Map<String, CartaYuGiOh> carteYuGiOh = db.hashMap(email + "-YuGiOh", Serializer.STRING, Serializer.JAVA).createOrOpen();
	    carteYuGiOh.remove(carta.getName());
	    db.commit();
	    db.close();
	    return "Removed"+ email;
	}
	
	public static String removeMagic(String email, CartaMagic carta) {
	    DB db = getDB();
	    Map<String, CartaMagic> carteMagic = db.hashMap(email + "-Magic", Serializer.STRING, Serializer.JAVA).createOrOpen();
	    carteMagic.remove(carta.getName());
	    db.commit();
	    db.close();
	    return "Removed"+ email;
	}
	
	public static String removePokemon(String email, CartaPokemon carta) {
	    DB db = getDB();
	    Map<String, CartaPokemon> cartePokemon = db.hashMap(email + "-Pokemon", Serializer.STRING, Serializer.JAVA).createOrOpen();
	    cartePokemon.remove(carta.getName());
	    db.commit();
	    db.close();
	    return "Removed"+ email;
	}
	
	public static String scambio(List<Carta> daMettere, List<Carta> daTogliere, String email){
		DB db = getDB();
		Map<String, CartaYuGiOh> carteYuGiOh = db.hashMap(email + "-YuGiOh", Serializer.STRING, Serializer.JAVA).createOrOpen();
		Map<String, CartaMagic> carteMagic = db.hashMap(email + "-Magic", Serializer.STRING, Serializer.JAVA).createOrOpen();
		Map<String, CartaPokemon> cartePokemon = db.hashMap(email + "-Pokemon", Serializer.STRING, Serializer.JAVA).createOrOpen();
		//eseguo la rimozione
		for(Carta carta: daTogliere) {
			if (carta instanceof CartaMagic) {
		        CartaMagic cartaMagic = (CartaMagic) carta;
		        carteMagic.remove(cartaMagic.getName());
		    } else if (carta instanceof CartaYuGiOh) {
		        CartaYuGiOh cartaYuGiOh = (CartaYuGiOh) carta;
		        carteYuGiOh.remove(cartaYuGiOh.getName());
		    } else if (carta instanceof CartaPokemon) {
		        CartaPokemon cartaPokemon = (CartaPokemon) carta;
		        cartePokemon.remove(cartaPokemon.getName());
		    }
		}
		//eseguo l'aggiunta
		for(Carta carta: daMettere) {
			if (carta instanceof CartaMagic) {
		        CartaMagic cartaMagic = (CartaMagic) carta;
		        carteMagic.put(carta.getName(), cartaMagic);
		    } else if (carta instanceof CartaYuGiOh) {
		        CartaYuGiOh cartaYuGiOh = (CartaYuGiOh) carta;
		        carteYuGiOh.put(cartaYuGiOh.getName(), cartaYuGiOh);
		    } else if (carta instanceof CartaPokemon) {
		        CartaPokemon cartaPokemon = (CartaPokemon) carta;
		        cartePokemon.put(cartaPokemon.getName(), cartaPokemon);
		    }
		}	
		db.commit();
		db.close();
		return "Scambio effettuato";
	}
	
	//carte desiderate
	public static String salvaCartaDesiderata(Carta carta, String email) {
		 DB db = getDB();
		 Map<String, Carta> carteDesiderate = db.hashMap("CarteDesiderate"+email, Serializer.STRING, Serializer.JAVA).createOrOpen();
		 carteDesiderate.put(carta.getName(), carta);
		 db.commit();
	     db.close();
	     return "Carta aggiunta alle desiderate";
	}
	public static String removeCartaDesiderata(Carta carta, String email) {
	    DB db = getDB();
	    Map<String, Carta> carteDesiderate = db.hashMap("CarteDesiderate"+email, Serializer.STRING, Serializer.JAVA).createOrOpen();
	    carteDesiderate.remove(carta.getName());
	    db.commit();
	    db.close();
	    return "Carta rimossa da quelle desiderate";
	}
	public static List<Carta> getCarteDesiderate(String email) {
	    DB db = getDB();
	    Map<String, CartaMagic> carteDesiderate = db.hashMap("CarteDesiderate"+email, Serializer.STRING, Serializer.JAVA).createOrOpen();
	    List<Carta> carte = new ArrayList<>(carteDesiderate.values());
	    db.close();
	    return carte;
	}
	
	//metodo per prendere i propritari di una carta
	public static String[] getEmailsByCardName(String[] emails, String cardName) {
	    List<String> matchingEmails = new ArrayList<>();

	    for (String email : emails) {
	        List<CartaMagic> carteMagic = getCarteMagicO(email);
	        List<CartaPokemon> cartePokemon = getCartePokemonO(email);
	        List<CartaYuGiOh> carteYuGiOh = getCarteYuGiOhO(email);

	        for (CartaMagic cartaMagic : carteMagic) {
	            if (cartaMagic.getName().equals(cardName)) {
	                matchingEmails.add(email);
	                break;
	            }
	        }

	        for (CartaPokemon cartaPokemon : cartePokemon) {
	            if (cartaPokemon.getName().equals(cardName)) {
	                matchingEmails.add(email);
	                break;
	            }
	        }

	        for (CartaYuGiOh cartaYuGiOh : carteYuGiOh) {
	            if (cartaYuGiOh.getName().equals(cardName)) {
	                matchingEmails.add(email);
	                break;
	            }
	        }
	    }

	    return matchingEmails.toArray(new String[0]);
	}

	
}
