package com.sweng.gwt.shared;

import java.io.Serializable;

public class User implements Serializable {
    private String email;
    private String password;

    public User() {
        // per la serializzazione
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
