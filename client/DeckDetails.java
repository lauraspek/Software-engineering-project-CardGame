package com.sweng.gwt.client;

import java.util.*;
import com.sweng.gwt.shared.*;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;


public class DeckDetails extends Composite {
	private static DeckDetailsUiBinder uiBinder = GWT.create(DeckDetailsUiBinder.class);
	private String gioco;
	private DeckFormat deck;
	
	@UiTemplate("DeckDetails.ui.xml")
	interface DeckDetailsUiBinder extends UiBinder<Widget, DeckDetails> {

	}

	public DeckDetails(DeckFormat deck) {
		initWidget(uiBinder.createAndBindUi(this));
		this.deck = deck;
		this.gioco = deck.getGioco();
		this.displayDeckCard(deck);
	}
	
	private void displayDeckCard(DeckFormat deck) {
		if(deck.getGioco() == "YuGiOh") {
			List<Carta> carte = new ArrayList<>(deck.getCarteYugioh());
			GWT.log(""+deck.getCarteYugioh().size());
			displayMyCards(ActiveUser.carteYPossedute);
			displaydeckcards(carte);
		}else if(deck.getGioco() == "Pokemon") {
			List<Carta> carte = new ArrayList<>(deck.getCartePokemon());
			displayMyCards(ActiveUser.cartePPossedute);
			displaydeckcards(carte);
		}else if(deck.getGioco() == "Magic") {
			List<Carta> carte = new ArrayList<>(deck.getCarteMagic());
			displayMyCards(ActiveUser.carteMPossedute);
			displaydeckcards(carte);
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
	
	private DbServiceAsync dbService = GWT.create(DbService.class);
	
	public void displaydeckcards(List<Carta> carte) {
		deckcards.clear();
	    VerticalPanel verticalPanel = new VerticalPanel();
	    for (Carta carta : carte) {
	    	final Carta c = carta;
	        DisclosurePanel disclosurePanel = new DisclosurePanel(c.getName());
	        disclosurePanel.setAnimationEnabled(true);
	        VerticalPanel contentPanel = new VerticalPanel();
	        contentPanel.add(new Label("Nome: " + c.getName()));
	        disclosurePanel.setContent(contentPanel);
	        disclosurePanel.addOpenHandler(event -> {
	        	//azione al click
	        });
	        verticalPanel.add(disclosurePanel);
	    }
	    deckcards.add(verticalPanel);
	}
	
	private void displayMyCards(List<Carta> cartepossedute) {
		allcards.clear();
	    VerticalPanel verticalPanel = new VerticalPanel();
	    for (Carta carta : cartepossedute) {
	    	final Carta selectedCarta = carta;
	        DisclosurePanel disclosurePanel = new DisclosurePanel(carta.getName());
	        disclosurePanel.setAnimationEnabled(true);
	        VerticalPanel contentPanel = new VerticalPanel();
	        contentPanel.add(new Label("Nome: " + carta.getName()));
	        disclosurePanel.setContent(contentPanel);
	        disclosurePanel.addOpenHandler(event -> {
	        	addCard(selectedCarta);
	        });
	        verticalPanel.add(disclosurePanel);
	    }
	    allcards.add(verticalPanel);
	}
	
	private void addCard(Carta carta) {
		this.deck.addCard(carta);
		dbService.updateDeck(this.deck, ActiveUser.email, new AsyncCallback<String>() {
            @Override
            public void onSuccess(String result) {
            	GWT.log(result);
            }
            @Override
            public void onFailure(Throwable caught) {
                GWT.log("Errore:", caught);
            }
        });
		this.displayDeckCard(this.deck);
	}
	
	@UiHandler("deleteDeck")
	public void onClick(ClickEvent event) {
	    dbService.rimuoviDeck(deck, ActiveUser.email, new AsyncCallback<String>() {
            @Override
            public void onSuccess(String result) {
            	GWT.log(result);
            	RootPanel.get("container").clear();
        		RootPanel.get("container").add(new Deck());
            }
            @Override
            public void onFailure(Throwable caught) {
                GWT.log("Errore:", caught);
            }
        });
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
	Button deleteDeck;
	@UiField
	FlowPanel deckcards;
	@UiField
	FlowPanel allcards;
	
}