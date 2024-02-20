package com.sweng.gwt.client;

import java.util.*;

import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.VerticalPanel;



import com.google.gwt.user.client.ui.Label;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sweng.gwt.shared.*;

public class Homepage extends Composite {
	private static HomepageUiBinder uiBinder = GWT.create(HomepageUiBinder.class);
	public List<Carta> cartePossedute = new ArrayList<>();
	public List<Carta> carteDesiderate = new ArrayList<>();
	
	@UiTemplate("Homepage.ui.xml")
	interface HomepageUiBinder extends UiBinder<Widget, Homepage> {

	}

	public Homepage() {
		initWidget(uiBinder.createAndBindUi(this));
		email.setText(ActiveUser.email);
		getCartePossedute();
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
	
	private DbServiceAsync dbService = GWT.create(DbService.class);
	
	@UiHandler("searchButton")
	public void onClick(ClickEvent event) {
	    String cardName = cardNameTextBox.getText();
	    String game = gameListBox.getSelectedValue(); 	    
	    if ("Pokemon".equalsIgnoreCase(game)) {
	        dbService.getCartePokemon(new AsyncCallback<List<CartaPokemon>>() {
	            @Override
	            public void onSuccess(List<CartaPokemon> result) {
	            	List<Carta> cartaList = new ArrayList<>();
	                for (CartaPokemon cartaPokemon : result) {
	                	
	                    Carta carta = (Carta) cartaPokemon; 
	                    if(cardName == "") {
	                    	cartaList.add(carta);
	                    }
	                    else if(carta.getName().equalsIgnoreCase(cardName)) {
	                    	cartaList.add(carta);
	                    }
	                    
	                }
	                displaySearchResults(cartaList, "pokemon");	                
	            }
	            @Override
	            public void onFailure(Throwable caught) {
	                GWT.log("Errore:", caught);
	            }
	        });
	    } else if ("YuGiOh".equalsIgnoreCase(game)) {
	        dbService.getCarteYuGiOh(new AsyncCallback<List<CartaYuGiOh>>() {
	            @Override
	            public void onSuccess(List<CartaYuGiOh> result) {
	            	List<Carta> cartaList = new ArrayList<>();
	                for (Carta cartaYuGiOh : result) {
	                    Carta carta = (Carta) cartaYuGiOh; 
	                    if(cardName == "") {
	                    	cartaList.add(carta);
	                    }
	                    else if(carta.getName().equalsIgnoreCase(cardName)) {
	                    	cartaList.add(carta);
	                    }
	                }
	                displaySearchResults(cartaList, "yugioh");
	            }
	            @Override
	            public void onFailure(Throwable caught) {
	                GWT.log("Errore:", caught);
	            }
	        });
	    } else if ("Magic".equalsIgnoreCase(game)) {
	        dbService.getCarteMagic(new AsyncCallback<List<CartaMagic>>() {
	            @Override
	            public void onSuccess(List<CartaMagic> result) {
	            	List<Carta> cartaList = new ArrayList<>();
	                for (Carta cartaMagic : result) {
	                    Carta carta = (Carta) cartaMagic; 
	                    if(cardName == "") {
	                    	cartaList.add(carta);
	                    }
	                    else if(carta.getName().equalsIgnoreCase(cardName)) {
	                    	cartaList.add(carta);
	                    }
	                }
	                displaySearchResults(cartaList, "magic");
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


	
	private void displaySearchResults(List<Carta> searchResults, String gioco) {
	    flowPanel.clear();
	    VerticalPanel verticalPanel = new VerticalPanel();
	    for (Carta carta : searchResults) {
	    	final Carta selectedCarta = carta;
	        DisclosurePanel disclosurePanel = new DisclosurePanel(carta.getName());
	        disclosurePanel.setAnimationEnabled(true);
	        VerticalPanel contentPanel = new VerticalPanel();
	        contentPanel.add(new Label("Nome: " + carta.getName()));
	        disclosurePanel.setContent(contentPanel);
	        disclosurePanel.addOpenHandler(event -> {
	        	
	        	RootPanel.get("container").clear();
	    		RootPanel.get("container").add(new CartaDetails(selectedCarta, gioco, this.cartePossedute, this.carteDesiderate));
	    		
	    		GWT.log(selectedCarta.getName());
	        });
	        verticalPanel.add(disclosurePanel);
	    }
	    flowPanel.add(verticalPanel);
	}
	
	private void getCartePossedute() {
		String email = ActiveUser.email;
		
		//yugioh
		dbService.getCarteYuGiOhO(email, new AsyncCallback<List<CartaYuGiOh>>() {
            @Override
            public void onSuccess(List<CartaYuGiOh> result) {
            	GWT.log("succsso");
            	List<Carta> cartaList = new ArrayList<>();
                for (CartaYuGiOh cartaYuGiOh : result) {
                    Carta carta = (Carta) cartaYuGiOh; 
                    cartaList.add(carta);
                    Homepage.this.cartePossedute.add(carta);
                }
                if(cartaList.size()==0) {
                	cartaList.add(new Carta("Nessun risultato trovato", ""));
                }
                ActiveUser.carteYPossedute = cartaList;
                displayY(cartaList, "yugioh");
              //magic
        		dbService.getCarteMagicO(email, new AsyncCallback<List<CartaMagic>>() {
                    @Override
                    public void onSuccess(List<CartaMagic> result) {
                    	GWT.log("succsso");
                    	List<Carta> cartaList = new ArrayList<>();
                        for (CartaMagic cartaMagic : result) {
                            Carta carta = (Carta) cartaMagic; 
                            cartaList.add(carta);
                            Homepage.this.cartePossedute.add(carta);
                        }
                        if(cartaList.size()==0) {
                        	cartaList.add(new Carta("Nessun risultato trovato", ""));
                        }
                        ActiveUser.carteMPossedute = cartaList;
                        displayM(cartaList, "magic");
                      //pokemon
                		dbService.getCartePokemonO(email, new AsyncCallback<List<CartaPokemon>>() {
                            @Override
                            public void onSuccess(List<CartaPokemon> result) {
                            	List<Carta> cartaList = new ArrayList<>();
                                for (CartaPokemon cartaPokemon : result) {
                                    Carta carta = (Carta) cartaPokemon; 
                                    cartaList.add(carta);
                                }
                                if(cartaList.size()==0) {
                                	cartaList.add(new Carta("Nessun risultato trovato", ""));
                                }
                                ActiveUser.cartePPossedute = cartaList;
                                displayP(cartaList, "pokemon");
                                getCarteDesiderate();
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
            @Override
            public void onFailure(Throwable caught) {
                GWT.log("Errore:", caught);
            }
        });	
	}
	
	private void displayP(List<Carta> searchResults, String gioco) {
		ppanel.clear();
	    VerticalPanel verticalPanel = new VerticalPanel();
	    for (Carta carta : searchResults) {
	    	final Carta selectedCarta = carta;
	        DisclosurePanel disclosurePanel = new DisclosurePanel(carta.getName());
	        disclosurePanel.setAnimationEnabled(true);
	        VerticalPanel contentPanel = new VerticalPanel();
	        contentPanel.add(new Label("Nome: " + carta.getName()));
	        disclosurePanel.setContent(contentPanel);
	        disclosurePanel.addOpenHandler(event -> {
	        	RootPanel.get("container").clear();
	    		RootPanel.get("container").add(new CartaDetails(selectedCarta, gioco, this.cartePossedute, this.carteDesiderate));
	    		GWT.log(selectedCarta.getName());
	        });
	        verticalPanel.add(disclosurePanel);
	    }
	    ppanel.add(verticalPanel);
	}
	
	private void displayY(List<Carta> searchResults, String gioco) {
		ypanel.clear();
	    VerticalPanel verticalPanel = new VerticalPanel();
	    for (Carta carta : searchResults) {
	    	final Carta selectedCarta = carta;
	        DisclosurePanel disclosurePanel = new DisclosurePanel(carta.getName());
	        disclosurePanel.setAnimationEnabled(true);
	        VerticalPanel contentPanel = new VerticalPanel();
	        contentPanel.add(new Label("Nome: " + carta.getName()));
	        disclosurePanel.setContent(contentPanel);
	        disclosurePanel.addOpenHandler(event -> {
	        	RootPanel.get("container").clear();
	    		RootPanel.get("container").add(new CartaDetails(selectedCarta, gioco, this.cartePossedute, this.carteDesiderate));
	    		GWT.log(selectedCarta.getName());
	        });
	        verticalPanel.add(disclosurePanel);
	    }
	    ypanel.add(verticalPanel);
	}
	
	private void displayM(List<Carta> searchResults, String gioco) {
		mpanel.clear();
	    VerticalPanel verticalPanel = new VerticalPanel();
	    for (Carta carta : searchResults) {
	    	final Carta selectedCarta = carta;
	        DisclosurePanel disclosurePanel = new DisclosurePanel(carta.getName());
	        disclosurePanel.setAnimationEnabled(true);
	        VerticalPanel contentPanel = new VerticalPanel();
	        contentPanel.add(new Label("Nome: " + carta.getName()));
	        disclosurePanel.setContent(contentPanel);
	        disclosurePanel.addOpenHandler(event -> {
	        	RootPanel.get("container").clear();
	    		RootPanel.get("container").add(new CartaDetails(selectedCarta, gioco, this.cartePossedute, this.carteDesiderate));
	    		GWT.log(selectedCarta.getName());
	        });
	        verticalPanel.add(disclosurePanel);
	    }
	    mpanel.add(verticalPanel);
	}

	private void getCarteDesiderate() {
		String email = ActiveUser.email;
		dbService.getCarteDesiderate(email, new AsyncCallback<List<Carta>>() {
            @Override
            public void onSuccess(List<Carta> result) {
            	GWT.log("succsso");
            	Homepage.this.carteDesiderate = result;
                displayD(result);
            }
            @Override
            public void onFailure(Throwable caught) {
                GWT.log("Errore:", caught);
            }
        });	
	}
	
	private void displayD(List<Carta> carteD) {
		dpanel.clear();
	    VerticalPanel verticalPanel = new VerticalPanel();
	    for (Carta carta : carteD) {
	    	final String gioco;
        	if (carta instanceof CartaMagic) {
		        gioco = "magic";
		    } else if (carta instanceof CartaYuGiOh) {
		        gioco = "yugioh";
		    } else if (carta instanceof CartaPokemon) {
		        gioco = "pokemon";
		    }else {
		    	gioco = "";
		    }
	    	final Carta selectedCarta = carta;
	        DisclosurePanel disclosurePanel = new DisclosurePanel(carta.getName());
	        disclosurePanel.setAnimationEnabled(true);
	        VerticalPanel contentPanel = new VerticalPanel();
	        contentPanel.add(new Label("Nome: " + carta.getName()));
	        disclosurePanel.setContent(contentPanel);
	        disclosurePanel.addOpenHandler(event -> {
	        	RootPanel.get("container").clear();
	    		RootPanel.get("container").add(new CartaDetails(selectedCarta, gioco, this.cartePossedute, this.carteDesiderate));
	    		GWT.log(selectedCarta.getName());
	        });
	        verticalPanel.add(disclosurePanel);
	    }
	    dpanel.add(verticalPanel);
	}


	
	
	@UiField
    Label email;
	@UiField
	Button btnHome;
	@UiField
	Button btnScambi;
	@UiField
	Button btnDeck;
	@UiField
	Button btnLogout;
	@UiField
	TextBox cardNameTextBox;
	@UiField
	ListBox gameListBox;
	@UiField
	Button searchButton;
	@UiField
	FlowPanel flowPanel;
	@UiField
	FlowPanel ypanel;
	@UiField
	FlowPanel ppanel;
	@UiField
	FlowPanel mpanel;
	@UiField
	FlowPanel dpanel;
	
}