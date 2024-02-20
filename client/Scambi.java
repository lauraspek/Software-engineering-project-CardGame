package com.sweng.gwt.client;

import java.util.*;


import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.sweng.gwt.shared.*;
import com.google.gwt.event.dom.client.ClickHandler;


public class Scambi extends Composite {
	
	private static ScambiUiBinder uiBinder = GWT.create(ScambiUiBinder.class);
	@UiTemplate("Scambi.ui.xml")
	interface ScambiUiBinder extends UiBinder<Widget, Scambi> {
		
	}

	public Scambi() {
		initWidget(uiBinder.createAndBindUi(this));
		this.loadscambio();
		this.loadRichieste();
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
	
	public void loadscambio() {
		for(Carta carta : ActiveUser.carteCedute) {
			carteCedute.addItem(carta.getName());
		}
		for(Carta carta : ActiveUser.carteCercate) {
			carteVolute.addItem(carta.getName());
		}
	}
	
	private DbServiceAsync dbService = GWT.create(DbService.class);
	
	private void loadRichieste() {
	    String destinatarioEmail = ActiveUser.email;
	    dbService.getScambiByDestinatarioEmail(destinatarioEmail, new AsyncCallback<List<ScambioCarte>>() {
	        @Override
	        public void onSuccess(List<ScambioCarte> result) {
	        	for (ScambioCarte scambio : result) {
                    addScambioRow(scambio);
                }
	        }
	        @Override
	        public void onFailure(Throwable caught) {
	            GWT.log("Errore:", caught);
	        }
	    });
	}
	
	private void addScambioRow(ScambioCarte scambio) {
	    FlexTable table = new FlexTable();
	    int row = 0;
	    table.setText(row, 0, "Mittente: " + scambio.getMittenteEmail());
	    table.setText(row, 1, "Destinatario: " + scambio.getDestinatarioEmail());
	    row++;
	    for (Carta carta : scambio.getCarteMittente()) {
	        table.setText(row, 0, carta.getName());
	        row++;
	    }
	    row = 1;
	    for (Carta carta : scambio.getCarteDestinatario()) {
	        table.setText(row, 1, carta.getName());
	        row++;
	    }
	    Button accettaButton = new Button("Accetta");
        accettaButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                eseguiScambio(scambio);
            }
        });
        
        Button rifiutaButton = new Button("Rifiuta");
        rifiutaButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                rimuoviScambio(scambio);
            }
        });
        table.setWidget(row, 2, accettaButton);
        table.setWidget(row, 3, rifiutaButton);
	    mainPanel.add(table);
	}
	
	private void rimuoviScambio(ScambioCarte scambio) {
		dbService.rimuoviScambio(scambio.getChiave(), new AsyncCallback<String>() {
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
	
	private void eseguiScambio(ScambioCarte scambio) {
		List<Carta> carteMittente = scambio.getCarteMittente();
		List<Carta> carteDestinatario = scambio.getCarteDestinatario();
		String mittente = scambio.getMittenteEmail();
		String destinatario = scambio.getDestinatarioEmail();
		
		dbService.scambio(carteMittente, carteDestinatario, destinatario, new AsyncCallback<String>() {
            @Override
            public void onSuccess(String result) {
            	GWT.log(result);
            	dbService.scambio(carteDestinatario, carteMittente, mittente, new AsyncCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                    	GWT.log(result);
                    	rimuoviScambio(scambio);
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
	
	
	@UiHandler("sendscambio")
	void doClicksendScambio(ClickEvent event) {
		ScambioCarte scambio = new ScambioCarte(ActiveUser.email, dest.getText(), ActiveUser.carteCedute, ActiveUser.carteCercate);
		dbService.salvaScambio(scambio, new AsyncCallback<String>() {
	        @Override
	        public void onSuccess(String result) {
	        	GWT.log(result);
	        	ActiveUser.carteCedute.clear();
	    		ActiveUser.carteCercate.clear();
	        }
	        @Override
	        public void onFailure(Throwable caught) {
	            GWT.log("Errore:", caught);
	        }
	    });
	}
	
	
	@UiField
    FlowPanel mainPanel;
	@UiField
	Button btnHome;
	@UiField
	Button btnScambi;
	@UiField
	Button btnDeck;
	@UiField
	Button btnLogout;
	@UiField
	ListBox carteCedute;
	@UiField
	ListBox carteVolute;
	@UiField
	Button sendscambio;
	@UiField
	TextBox dest;
	@UiField
	FlexTable scambiTable;
	
}