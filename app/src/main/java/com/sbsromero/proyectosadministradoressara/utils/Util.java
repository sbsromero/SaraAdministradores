package com.sbsromero.proyectosadministradoressara.utils;

import android.content.SharedPreferences;
import android.util.Base64;

import com.sbsromero.proyectosadministradoressara.models.Admin;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import io.realm.Realm;

/**
 * Created by USERPC on 19/11/2017.
 */

public class Util {

    public static void agregarAdmin(Realm realm){
        realm.beginTransaction();
        Admin admin = new Admin("admin",Util.encriptarPassword("123"),"adming@gmail.com");
        realm.copyToRealm(admin);
        realm.commitTransaction();
    }

    public static String getUsername(SharedPreferences prefs){
        return prefs.getString("username",null);
    }

    public static String getPassword(SharedPreferences prefs){
        return prefs.getString("password",null);
    }

    public static String encriptarPassword(String password){
        byte[] data;
        String base64 = "";
        try {
            data = password.getBytes("UTF-8");
            base64 = Base64.encodeToString(data, Base64.DEFAULT);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return base64;
    }

    public static String desencriptarPassword(String password){
        byte[] data = Base64.decode(password, Base64.DEFAULT);
        String base64 = "";
        try {
            base64 = new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return base64;
    }
}
