package com.sweng.gwt.shared;

import java.io.Serializable;

public class CartaMagic extends Carta implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    private String artists;
    private String text;
    private String types;
    private boolean hasFoil;
    private boolean isAlternative;
    private boolean isFullArt;
    private boolean isPromo;
    private boolean isReprint;
    
    public CartaMagic() {}
    
    public CartaMagic(String name, String rarity, String artists, String text, String types,
                      boolean hasFoil, boolean isAlternative, boolean isFullArt,
                      boolean isPromo, boolean isReprint) {
        super(name, rarity);
        this.artists = artists;
        this.text = text;
        this.types = types;
        this.hasFoil = hasFoil;
        this.isAlternative = isAlternative;
        this.isFullArt = isFullArt;
        this.isPromo = isPromo;
        this.isReprint = isReprint;
    }

    public String getArtists() {
        return artists;
    }

    public void setArtists(String artists) {
        this.artists = artists;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public boolean isHasFoil() {
        return hasFoil;
    }

    public void setHasFoil(boolean hasFoil) {
        this.hasFoil = hasFoil;
    }

    public boolean isAlternative() {
        return isAlternative;
    }

    public void setAlternative(boolean alternative) {
        isAlternative = alternative;
    }

    public boolean isFullArt() {
        return isFullArt;
    }

    public void setFullArt(boolean fullArt) {
        isFullArt = fullArt;
    }

    public boolean isPromo() {
        return isPromo;
    }

    public void setPromo(boolean promo) {
        isPromo = promo;
    }

    public boolean isReprint() {
        return isReprint;
    }

    public void setReprint(boolean reprint) {
        isReprint = reprint;
    }
}
