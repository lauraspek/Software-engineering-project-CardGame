package com.sweng.gwt.shared;

import java.io.Serializable;

public class CartaPokemon extends Carta implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public static class Variants implements Serializable{
		private static final long serialVersionUID = 1L;
		
		public Variants() {
	        firstEdition = false;
	        holo = false;
	        normal = false;
	        reverse = false;
	        wPromo = false;
	    }
		
		public boolean firstEdition;
		public boolean holo;
		public boolean normal;
		public boolean reverse;
		public boolean wPromo;
	}
	 
    private String illustrator;
    private String image;
    private Variants variants;
    private String[] types;
    private String description;
    
    public CartaPokemon() {}

    public CartaPokemon(String name, String rarity, String illustrator, String image,
                        Variants variants, String[] types, String description) {
        super(name, rarity);
        this.illustrator = illustrator;
        this.image = image;
        this.variants = variants;
        this.types = types;
        this.description = description;
    }
    
   
    public Variants getVariants() {
        return variants;
    }

    public void setVariants(Variants variants) {
        this.variants = variants;
    }
    
    public String getIllustrator() {
        return illustrator;
    }

    public void setIllustrator(String illustrator) {
        this.illustrator = illustrator;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String[] getTypes() {
        return types;
    }

    public void setTypes(String[] types) {
        this.types = types;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
