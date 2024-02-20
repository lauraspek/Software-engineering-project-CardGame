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


public class DbScambi{
	
	private static DB db;

	public static DB getDB() {
		DB db = DBMaker.fileDB(new File("dbScambi")).make();
		return db;
	}
	
	public static String salvaScambio(ScambioCarte scambio) {
        DB db = getDB();
        Map<String, ScambioCarte> scambiMap = db.hashMap("Scambi", Serializer.STRING, Serializer.JAVA).createOrOpen();
        scambiMap.put(scambio.getChiave(), scambio);
        //scambiMap.put(generaChiaveScambio(scambio.getMittenteEmail(), scambio.getDestinatarioEmail()), scambio);
        db.commit();
        db.close();
        return "Successo";
    }
	/*
	 private static String generaChiaveScambio(String mittenteEmail, String destinatarioEmail) {
	        return mittenteEmail + "-" + destinatarioEmail;
	    }
	*/
	
	public static String rimuoviScambio(String chiave) {
	    DB db = getDB();
	    Map<String, ScambioCarte> scambi = db.hashMap("Scambi", Serializer.STRING, Serializer.JAVA).createOrOpen();
	    scambi.remove(chiave);
	    db.commit();
        db.close();
        return "Scambio rimosso";
	}
	
	
    public static List<ScambioCarte> getScambiByDestinatarioEmail(String destinatarioEmail) {
        DB db = getDB();
        Map<String, ScambioCarte> scambiMap = db.hashMap("Scambi", Serializer.STRING, Serializer.JAVA).createOrOpen();
        List<ScambioCarte> scambiCorrispondenti = new ArrayList<>();
        for (ScambioCarte scambio : scambiMap.values()) {
            if (scambio.getDestinatarioEmail().equals(destinatarioEmail)) {
                scambiCorrispondenti.add(scambio);
            }
        }
        db.close();
        return scambiCorrispondenti;
    }

	
	
	
}
