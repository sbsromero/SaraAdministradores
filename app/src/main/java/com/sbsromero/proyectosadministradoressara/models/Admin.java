package com.sbsromero.proyectosadministradoressara.models;

import com.sbsromero.proyectosadministradoressara.app.MyApplication;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by USERPC on 19/11/2017.
 */

public class Admin extends RealmObject {

    @PrimaryKey
    private int id;

    private String username;
    private String password;
    private String email;

    public Admin() {
    }

    public Admin(String username, String password, String email) {
        this.id = MyApplication.AdminId.incrementAndGet();
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
