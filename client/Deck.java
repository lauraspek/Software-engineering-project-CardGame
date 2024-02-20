package com.sweng.gwt.client;

import java.util.ArrayList;
import java.util.List;

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
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sweng.gwt.shared.*;


public class Deck extends Composite {
	private static DeckUiBinder uiBinder = GWT.create(DeckUiBinder.class);
	
	@UiTemplate("Deck.ui.xml")
	interface DeckUiBinder extends UiBinder<Widget, Deck> {

	}

	public Deck() {
		initWidget(uiBinder.createAndBindUi(this));
		this.loadDeck();
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
	
	@UiHandler("createDeck")
	public void onClick(ClickEvent event) {
	    String cardName = nameDeck.getText();
	    String game = gameListBox.getSelectedValue(); 	    
	    DeckFormat deck = new DeckFormat(cardName, game);
	    dbService.salvaDeck(deck, ActiveUser.email, new AsyncCallback<String>() {
            @Override
            public void onSuccess(String result) {
            	GWT.log(result);
            }
            @Override
            public void onFailure(Throwable caught) {
                GWT.log("Errore:", caught);
            }
        });
	}
	
	public void loadDeck() {
		dbService.getDeck(ActiveUser.email, new AsyncCallback<List<DeckFormat>>() {
            @Override
            public void onSuccess(List<DeckFormat> result) {
            	displaydeck(result);
            }
            @Override
            public void onFailure(Throwable caught) {
                GWT.log("Errore:", caught);
            }
        });
	}
	
	public void displaydeck(List<DeckFormat> decklist) {
		List <DeckFormat> deckpokemon = new ArrayList<>();
		List <DeckFormat> deckmagic = new ArrayList<>();
		List <DeckFormat> deckyugioh = new ArrayList<>();
		for(DeckFormat deck : decklist) {
			GWT.log(deck.getNome());
			if(deck.getGioco() == "YuGiOh") {
				deckyugioh.add(deck);
			}else if(deck.getGioco() == "Pokemon") {
				deckpokemon.add(deck);
			}else if(deck.getGioco() == "Magic") {
				deckmagic.add(deck);
			}
		}
		this.displayY(deckyugioh);
		this.displayM(deckmagic);
		this.displayP(deckpokemon);
	}
	
	private void displayP(List<DeckFormat> decks) {
		ppanel.clear();
	    VerticalPanel verticalPanel = new VerticalPanel();
	    for (DeckFormat deck : decks) {
	    	final DeckFormat d = deck;
	        DisclosurePanel disclosurePanel = new DisclosurePanel(d.getNome());
	        disclosurePanel.setAnimationEnabled(true);
	        VerticalPanel contentPanel = new VerticalPanel();
	        contentPanel.add(new Label("Nome: " + d.getNome()));
	        disclosurePanel.setContent(contentPanel);
	        disclosurePanel.addOpenHandler(event -> {
	        	RootPanel.get("container").clear();
	    		RootPanel.get("container").add(new DeckDetails(deck));
	        });
	        verticalPanel.add(disclosurePanel);
	    }
	    ppanel.add(verticalPanel);
	}
	
	private void displayY(List<DeckFormat> decks) {
		ypanel.clear();
	    VerticalPanel verticalPanel = new VerticalPanel();
	    for (DeckFormat deck : decks) {
	    	final DeckFormat d = deck;
	        DisclosurePanel disclosurePanel = new DisclosurePanel(d.getNome());
	        disclosurePanel.setAnimationEnabled(true);
	        VerticalPanel contentPanel = new VerticalPanel();
	        contentPanel.add(new Label("Nome: " + d.getNome()));
	        disclosurePanel.setContent(contentPanel);
	        disclosurePanel.addOpenHandler(event -> {
	        	RootPanel.get("container").clear();
	    		RootPanel.get("container").add(new DeckDetails(deck));
	        });
	        verticalPanel.add(disclosurePanel);
	    }
	    ypanel.add(verticalPanel);
	}
	
	private void displayM(List<DeckFormat> decks) {
		mpanel.clear();
	    VerticalPanel verticalPanel = new VerticalPanel();
	    for (DeckFormat deck : decks) {
	    	final DeckFormat d = deck;
	        DisclosurePanel disclosurePanel = new DisclosurePanel(d.getNome());
	        disclosurePanel.setAnimationEnabled(true);
	        VerticalPanel contentPanel = new VerticalPanel();
	        contentPanel.add(new Label("Nome: " + d.getNome()));
	        disclosurePanel.setContent(contentPanel);
	        disclosurePanel.addOpenHandler(event -> {
	        	RootPanel.get("container").clear();
	    		RootPanel.get("container").add(new DeckDetails(deck));
	        });
	        verticalPanel.add(disclosurePanel);
	    }
	    mpanel.add(verticalPanel);
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
	Button createDeck;
	@UiField
	FlowPanel ypanel;
	@UiField
	FlowPanel ppanel;
	@UiField
	FlowPanel mpanel;
	@UiField
	TextBox nameDeck;
	@UiField
	ListBox gameListBox;
	
}