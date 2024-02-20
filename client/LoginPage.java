package com.sweng.gwt.client;


import java.util.ArrayList;

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
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;


public class LoginPage extends Composite {
	private static LoginUiBinder uiBinder = GWT.create(LoginUiBinder.class);
	
	@UiTemplate("LoginPage.ui.xml")
	interface LoginUiBinder extends UiBinder<Widget, LoginPage> {

	}

	public LoginPage() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiHandler("Signin")
	void doClickSubmit(ClickEvent event) {
		RootPanel.get("container").clear();
		RootPanel.get("container").add(new Homepage());
	}
	
	private DbServiceAsync dbService = GWT.create(DbService.class);
	
	  @UiHandler("btnSubmit")
	    void doClickLogin(ClickEvent event) {
	        String email = txtMail.getText();
	        String password = txtPassword.getText();
	        
	        dbService.loginUtente(email, password, new AsyncCallback<String>() {
	            @Override
	            public void onSuccess(String result) {
	                if (result.contains("@")) {
	                    ActiveUser.email = result;
	                    RootPanel.get("container").clear();
	            		RootPanel.get("container").add(new Homepage());
	                    
	                } else {
	                	loginResult.setText(result);
	                }
	            }
	            @Override
	            public void onFailure(Throwable caught) {
	                loginResult.setText("Errore durante il login");
	            }
	        });
	    }
	    
	    

	@UiField
	Button Signin;
	
	@UiField
    Label loginResult;

	@UiField
	TextBox txtMail;

	@UiField
	PasswordTextBox txtPassword;

	@UiField
	Button btnSubmit;
}