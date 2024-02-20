package com.sweng.gwt.shared;

import java.io.Serializable;

public class Carta implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    private String name;
    private String rarity;
    private int state;
    
    public Carta() {}

    public Carta(String name, String rarity) {
        this.name = name;
        this.rarity = rarity;
        this.state = 5;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}

