package com.sweng.gwt.client;

import java.util.*;
import com.sweng.gwt.shared.*;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;


public class CartaDetails extends Composite {
	private Carta selectedCarta;
	private String gioco;
	private static CartaDetailsUiBinder uiBinder = GWT.create(CartaDetailsUiBinder.class);
	
	@UiTemplate("CartaDetails.ui.xml")
	interface CartaDetailsUiBinder extends UiBinder<Widget, CartaDetails> {

	}

	public CartaDetails(Carta carta, String gioco, List<Carta> cartePossedute, List<Carta> carteDesiderate) {
		initWidget(uiBinder.createAndBindUi(this));
		this.selectedCarta = carta;
		this.gioco = gioco;
		if(containsCarta(cartePossedute, selectedCarta.getName())) {
			btnAdd.setText("Rimuovi dalle carte possedute");
		}
		if(containsCarta(carteDesiderate, selectedCarta.getName())) {
			btnPrefe.setText("Rimuovi dalle carte desiderate");
		}
		setValue();
		getOwners();
	}
	
	public boolean containsCarta(List<Carta> carte, String targetName) {
	    for (Carta carta : carte) {
	        if (carta.getName().equals(targetName)) {
	            return true;
	        }
	    }
	    return false;
	}
	
	public void setValue() {
		String imageUrl;
		if ("Pokemon".equalsIgnoreCase(this.gioco)) {
	    	CartaPokemon carta = (CartaPokemon) this.selectedCarta;
	    	imageUrl = carta.getImage();
	    	String nome = carta.getName();
	    	String types = "";
	    	try {
		    	for(String type: carta.getTypes()) {
		    		types = types + ", " + type;
		    	}
	    	}catch(Exception e) {};
	    	String rarity = carta.getRarity();
	    	String desc = carta.getDescription();
	    	String v = "";
	    	try {
		    	v = "First edition:" + carta.getVariants().firstEdition + ". Holo:" + carta.getVariants().holo + ". Normal:" + carta.getVariants().normal + ". Reverse:" + carta.getVariants().reverse + ". wPromo:" + carta.getVariants().wPromo;
	    	}catch(Exception e) {};
	    	HTMLPanel panel = new HTMLPanel(""); 
	    	panel.add(new HTML("<h3>" + nome + "</h3><br>" + types + "<br>" + rarity + "<br>" + v + "<br>" + "Description:" + desc));
	    	testoCarta.clear(); 
	    	testoCarta.add(panel);
	    	
	    } else if ("YuGiOh".equalsIgnoreCase(this.gioco)) {
	    	CartaYuGiOh carta = (CartaYuGiOh) this.selectedCarta;
	    	imageUrl = carta.getImage_url();
	    	String nome = carta.getName();
	    	String type = carta.getType();
	    	String rarity = carta.getRarity();
	    	String race = carta.getRace();
	    	String desc = carta.getDesc();
	 
	    	HTMLPanel panel = new HTMLPanel(""); 
	    	panel.add(new HTML("<h3>" + nome + "</h3><br>" + type + "<br>" + rarity + "<br>" + race + "<br>" + desc));
	    	testoCarta.clear(); 
	    	testoCarta.add(panel);
	    } else if ("Magic".equalsIgnoreCase(this.gioco)) {
	    	CartaMagic carta = (CartaMagic) this.selectedCarta;
	    	imageUrl="";
	    	String nome = carta.getName();
	    	String text = carta.getText();
	    	String types = carta.getTypes();
	    	String hasFoil = "Has Foil:" + Boolean.toString(carta.isHasFoil());
	    	String isAlternative = "Is Alternative:" + Boolean.toString(carta.isAlternative());
	    	String isFullArt = "Is Full Art:" + Boolean.toString(carta.isFullArt());
	    	String isPromo = "Is Promo:" + Boolean.toString(carta.isPromo());
	    	String isReprint = "Is Reprint:" + Boolean.toString(carta.isReprint());
	    	
	    	HTMLPanel panel = new HTMLPanel("");
	    	panel.add(new HTML("<h3>" + nome + "</h3><br>" + text + "<br>" + types + "<br>" + hasFoil + "<br>" + isAlternative + "<br>" + isFullArt + "<br>" + isPromo + "<br>" + isReprint));
	    	testoCarta.clear(); 
	    	testoCarta.add(panel);
	    }else {
	    	imageUrl="";
	    } 
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Image image = new Image(imageUrl);
            image.setWidth("300px");
            image.setHeight("420px");
            imagePanel.add(image);
        }
	}
	
	@UiHandler("btnHome")
	void doClickHome(ClickEvent event) {
		RootPanel.get("container").clear();
		RootPanel.get("container").add(new Homepage());
	}
	@UiHandler("btnScambi")
	void doClickScambi(ClickEvent event) {
		RootPanel.get("container").clear();
		RootPanel.get("container").add(new Scambi());
	}
	@UiHandler("btnDeck")
	void doClickDeck(ClickEvent event) {
		RootPanel.get("container").clear();
		RootPanel.get("container").add(new Deck());
	}
	@UiHandler("btnLogout")
	void doClickLogout(ClickEvent event) {
		RootPanel.get("container").clear();
		RootPanel.get("container").add(new LoginPage());
	}
	
	@UiHandler("btnCedo")
	public void onClickCedo(ClickEvent event) {
		if(btnCedo.getText() == "Cedo questa carta") {
			ActiveUser.carteCedute.add(this.selectedCarta);
			btnCedo.setText("Non voglio più cedere questa carta");
		}else {
			ActiveUser.carteCedute.remove(this.selectedCarta);
			btnCedo.setText("Cedo questa carta");
		}  
	}
	
	@UiHandler("btnCerco")
	public void onClickCerco(ClickEvent event) {
		if(btnCerco.getText() == "Voglio questa carta") {
			ActiveUser.carteCercate.add(this.selectedCarta);
			btnCerco.setText("Non voglio più avere questa carta");
		}else {
			ActiveUser.carteCercate.remove(this.selectedCarta);
			btnCerco.setText("Voglio questa carta");
		}  
	}
	
	
	
private DbServiceAsync dbService = GWT.create(DbService.class);
	
	@UiHandler("btnAdd")
	public void onClickAdd(ClickEvent event) {
		if(btnAdd.getText() == "Rimuovi dalle carte possedute") {
			this.removeCarta();
		}else {
			this.addCarta();
		}  
	}

	private void addCarta() {
		if ("Pokemon".equalsIgnoreCase(this.gioco)) {
	    	CartaPokemon carta = (CartaPokemon) this.selectedCarta;
	    	String email = ActiveUser.email;
	    	
	        dbService.salvaCartaPokemon(carta, email, new AsyncCallback<String>() {
	            @Override
	            public void onSuccess(String result) {
	            	GWT.log(result);
	            	btnAdd.setText("Rimuovi dalle carte possedute");
	            }
	            @Override
	            public void onFailure(Throwable caught) {
	                GWT.log("Errore:", caught);
	            }
	        });
	        
	    } else if ("YuGiOh".equalsIgnoreCase(this.gioco)) {
	    	CartaYuGiOh carta = (CartaYuGiOh) this.selectedCarta;
	    	String email = ActiveUser.email;
	        dbService.salvaCartaYuGiOh(carta, email, new AsyncCallback<String>() {
	            @Override
	            public void onSuccess(String result) {
	            	GWT.log(result);
	            	btnAdd.setText("Rimuovi dalle carte possedute");
	            }
	            @Override
	            public void onFailure(Throwable caught) {
	                GWT.log("Errore:", caught);
	            }
	        });
	    } else if ("Magic".equalsIgnoreCase(this.gioco)) {
	    	CartaMagic carta = (CartaMagic) this.selectedCarta;
	    	String email = ActiveUser.email;
	        dbService.salvaCartaMagic(carta, email, new AsyncCallback<String>() {
	            @Override
	            public void onSuccess(String result) {
	            	GWT.log(result);
	            	btnAdd.setText("Rimuovi dalle carte possedute");
	            }
	            @Override
	            public void onFailure(Throwable caught) {
	                GWT.log("Errore:", caught);
	            }
	        });
	    } else {
	        // Nessuna corrispondenza trovata
	    }
	}
	
	private void removeCarta() {
		if ("Pokemon".equalsIgnoreCase(this.gioco)) {
	    	CartaPokemon carta = (CartaPokemon) this.selectedCarta;
	    	String email = ActiveUser.email;
	    	
	        dbService.removePokemon(email, carta, new AsyncCallback<String>() {
	            @Override
	            public void onSuccess(String result) {
	            	GWT.log(result);
	            	btnAdd.setText("Aggiungi alle carte possedute");
	            }
	            @Override
	            public void onFailure(Throwable caught) {
	                GWT.log("Errore:", caught);
	            }
	        });
	        
	    } else if ("YuGiOh".equalsIgnoreCase(this.gioco)) {
	    	CartaYuGiOh carta = (CartaYuGiOh) this.selectedCarta;
	    	String email = ActiveUser.email;
	        dbService.removeYuGiOh(email, carta, new AsyncCallback<String>() {
	            @Override
	            public void onSuccess(String result) {
	            	GWT.log(result);
	            	btnAdd.setText("Aggiungi alle carte possedute");
	            }
	            @Override
	            public void onFailure(Throwable caught) {
	                GWT.log("Errore:", caught);
	            }
	        });
	    } else if ("Magic".equalsIgnoreCase(this.gioco)) {
	    	CartaMagic carta = (CartaMagic) this.selectedCarta;
	    	String email = ActiveUser.email;
	        dbService.removeMagic(email, carta, new AsyncCallback<String>() {
	            @Override
	            public void onSuccess(String result) {
	            	GWT.log(result);
	            	btnAdd.setText("Aggiungi alle carte possedute");
	            }
	            @Override
	            public void onFailure(Throwable caught) {
	                GWT.log("Errore:", caught);
	            }
	        });
	    } else {
	        // Nessuna corrispondenza trovata
	    }
		
	}
	
	@UiHandler("btnPrefe")
	public void onClickAddDes(ClickEvent event) {
		if(btnPrefe.getText() == "Rimuovi dalle carte desiderate") {
			this.removeCarta();
		}else {
			this.addCartaDes();
		}  
	}

	private void addCartaDes() {
		dbService.salvaCartaDesiderata(this.selectedCarta, ActiveUser.email, new AsyncCallback<String>() {
            @Override
            public void onSuccess(String result) {
            	GWT.log(result);
            	btnPrefe.setText("Rimuovi dalle carte desiderate");
            }
            @Override
            public void onFailure(Throwable caught) {
                GWT.log("Errore:", caught);
            }
        });
	}
	
	private void removeCartaDes() {
		dbService.removeCartaDesiderata(this.selectedCarta, ActiveUser.email, new AsyncCallback<String>() {
            @Override
            public void onSuccess(String result) {
            	GWT.log(result);
            	btnPrefe.setText("Aggiungi alle carte desiderate");
            }
            @Override
            public void onFailure(Throwable caught) {
                GWT.log("Errore:", caught);
            }
        });
	}
	
	private void getOwners() {
		dbService.getAllUserEmails(new AsyncCallback<String[]>() {
            @Override
            public void onSuccess(String[] result) {
            	dbService.getEmailsByCardName(result, selectedCarta.getName(), new AsyncCallback<String[]>() {
                    @Override
                    public void onSuccess(String[] result) {
                    	showOwners(result);
                    }
                    @Override
                    public void onFailure(Throwable caught) {
                        GWT.log("Errore:", caught);
                    }
                });
            }
            @Override
            public void onFailure(Throwable caught) {
                GWT.log("Errore:", caught);
            }
        });
	}
	
	public void showOwners(String[] emails) {
	    FlowPanel ownersPanel = new FlowPanel();
	    if(emails.length==0) {
	    	 Label emailLabel = new Label("Nessun utente possiede questa carta");
	    	 ownersPanel.add(emailLabel);
	    }
	    for (String email : emails) {
	        Label emailLabel = new Label(email);
	        ownersPanel.add(emailLabel);
	    }
	    owners.clear(); 
	    owners.add(ownersPanel); 
	}
	
	
	@UiField
	Button btnHome;
	@UiField
	Button btnScambi;
	@UiField
	Button btnDeck;
	@UiField
	Button btnLogout;
	@UiField
	Button btnAdd;
	@UiField
	Button btnCedo;
	@UiField
	Button btnCerco;
	@UiField
	Button btnPrefe;
	@UiField
	FlowPanel imagePanel;
	@UiField
	FlowPanel testoCarta;
	@UiField
	FlowPanel owners;
	
	
}