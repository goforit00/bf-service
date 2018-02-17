package com.goforit.firstapple.common.integration.model;


import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * Created by goforit on 17/4/13.
 */
public class MailAuth extends Authenticator {

    private String username;
    private String password;

    public MailAuth() {

    }

    public MailAuth(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
