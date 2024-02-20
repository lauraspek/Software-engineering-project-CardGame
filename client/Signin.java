package com.sweng.gwt.client;
import com.gargoylesoftware.htmlunit.javascript.host.Console;
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
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import java.util.ArrayList;

public class Signin extends Composite {
	private static SigninUiBinder uiBinder = GWT.create(SigninUiBinder.class);
	
	@UiTemplate("Signin.ui.xml")
	interface SigninUiBinder extends UiBinder<Widget, Signin> {

	}

	public Signin() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiHandler("Login")
	void doClickLogin(ClickEvent event) {
        RootPanel.get("container").clear();
        RootPanel.get("container").add(new LoginPage());
    }
	
	
	private DbServiceAsync dbService = GWT.create(DbService.class);
	
	@UiHandler("btnSubmit")
	void doClickSubmit(ClickEvent event) {
	    String email = txtMail.getText();
	    String password = txtPassword.getText();
	    ArrayList<String> userData = new ArrayList<>();
	    userData.add(email);
	    userData.add(password);
	    dbService.registrazioneUtente(userData, new AsyncCallback<String>() {
	        @Override
	        public void onSuccess(String result) {
	        	registrationResult.setText("prova");
	        	registrationResult.setText(result);
	            if (result.equals("Registrazione completata")) {
	            	System.out.println("Registrazione completata");
	            } else {
	            	registrationResult.setText("prova2");
	            	System.out.println("Qulcosa non va");
	            }
	        }

	        @Override
	        public void onFailure(Throwable caught) {
	        	registrationResult.setText("prova3");
	        	
	        }
	    });
	}




	@UiField
	Button Login;
	
	@UiField
    Label registrationResult;

	@UiField
	TextBox txtMail;

	@UiField
	PasswordTextBox txtPassword;

	@UiField
	Button btnSubmit;
}