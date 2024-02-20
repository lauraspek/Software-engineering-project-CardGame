package com.sweng.gwt.shared;

import java.io.Serializable;

public class CartaYuGiOh extends Carta implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    private String type;
    private String desc;
    private String race;
    private String image_url;
    private String small_image_url;
    
    public CartaYuGiOh() {}

    public CartaYuGiOh(String name, String rarity, String type, String desc,
                       String race, String image_url, String small_image_url) {
        super(name, rarity);
        this.type = type;
        this.desc = desc;
        this.race = race;
        this.image_url = image_url;
        this.small_image_url = small_image_url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getSmall_image_url() {
        return small_image_url;
    }

    public void setSmall_image_url(String small_image_url) {
        this.small_image_url = small_image_url;
    }
}

