package com.sweng.gwt.server;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.Serializer; 
import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import com.sweng.gwt.shared.User;

public class DbUtenti {
private static DB db;
	
	private static DB getDB() {
		DB db = DBMaker.fileDB(new File("dbUser")).make();
		return db;
	}
	
	private static boolean checkMail(String email) {
	    boolean find = false;
	    DB db = getDB();
	    Map<String, User> Users = db.hashMap("UserMap", Serializer.STRING, Serializer.JAVA).createOrOpen();
	    for (Entry<String, User> prov : Users.entrySet()) {
	        if (prov.getValue().getEmail().equalsIgnoreCase(email)) {
	            find = true;
	            break; 
	        }
	    }
	    db.close(); 
	    return find;
	}

	
	public static String registrazioneUtente(ArrayList<String> value) {
	    if (!checkMail(value.get(0))) {
	        DB db = getDB();
	        Map<String, User> Users = db.hashMap("UserMap", Serializer.STRING, Serializer.JAVA).createOrOpen(); 
	        User user = new User(value.get(0), value.get(1));
	        Users.put(user.getEmail(), user);
	        db.commit();
	        db.close();
	        return "Registrazione completata";
	    } else {
	        return "Errore";
	    }
	}
	
	public static String loginUtente(String email, String password) {
        DB db = getDB();
        Map<String, User> Users = db.hashMap("UserMap", Serializer.STRING, Serializer.JAVA).createOrOpen(); 
        if (Users.containsKey(email)) {
            User user = Users.get(email);
            if (user.getPassword().equals(password)) {
                db.close();
                return email;
            } else {
                db.close();
                return "Password errata";
            }
        } else {
            db.close();
            return "Utente non registrato";
        }
    }
	
	public static String[] getAllUserEmails() {
	    DB db = getDB();
	    Map<String, User> Users = db.hashMap("UserMap", Serializer.STRING, Serializer.JAVA).createOrOpen();
	    String[] userEmails = Users.keySet().toArray(new String[0]);
	    db.close();
	    return userEmails;
	}
	
	
}
