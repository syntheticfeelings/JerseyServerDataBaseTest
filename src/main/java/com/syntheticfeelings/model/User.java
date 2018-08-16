package com.syntheticfeelings.model;

import java.util.UUID;

public class User {

    private int id;
    private String email;
    private String password;
    private int telephone;
    private String uuid;

    public User(int id, String email, String password, int telephone, String uuid) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.telephone = telephone;
        this.uuid = uuid;
    }

    public User(String email, String password, int telephone) {
        this.email = email;
        this.password = password;
        this.telephone = telephone;
        this.uuid = UUID.randomUUID().toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
