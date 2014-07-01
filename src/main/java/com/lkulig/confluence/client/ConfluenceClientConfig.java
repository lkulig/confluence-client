package com.lkulig.confluence.client;

public class ConfluenceClientConfig {

    private String username;
    private String password;

    public ConfluenceClientConfig(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
