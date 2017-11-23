package com.sbsromero.proyectosadministradoressara.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.NotificationCompat;
import android.util.Base64;

import com.sbsromero.proyectosadministradoressara.R;
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

    //Metodo que permite realizar una insercion de un administrador
    public static void agregarAdmin(Realm realm){
        realm.beginTransaction();
        Admin admin = new Admin("admin",Util.encriptarPassword("123"),"adming@gmail.com");
        realm.copyToRealm(admin);
        realm.commitTransaction();
    }

    //Metodo que verifica si en el sharedpreferences hay un username guardado
    public static String getUsername(SharedPreferences prefs){
        return prefs.getString("username",null);
    }

    //Metodo que verifica si en el sharedpreferences hay una password guardado
    public static String getPassword(SharedPreferences prefs){
        return prefs.getString("password",null);
    }

    //Metodo que cifra una contraseña
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

    //Metodo que descifra una contraseña
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

    //Metodo que verifica si esta conectado a una red
    public static boolean conectadoARed(Context context) {

        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo actNetInfo = connectivityManager.getActiveNetworkInfo();

        return (actNetInfo != null && actNetInfo.isConnected());
    }

    //Metodo que comprueba si hay conexion a internet
    public static Boolean compruebaConexion() {

        try {
            Process p = java.lang.Runtime.getRuntime().exec("ping -c 1 www.google.es");

            int val = p.waitFor();
            boolean reachable = (val == 0);
            return reachable;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    //Metodo que muestra una notificacion cuando no e tiene conexion a internet
    public static void mostrarNotificacion(Context context){
        NotificationCompat.Builder mBuilder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(context.getString(R.string.app_name))
                        .setContentText(context.getString(R.string.msjConexion))
                        .setOngoing(true);

        android.app.NotificationManager notificationManager =
                (android.app.NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(14,mBuilder.build());
    }

    //Metodo que oculta la notificación correspondiente cuando se tiene acceso a internet
    public static void ocultarNotificacion(Context context){
        android.app.NotificationManager notificationManager =
                (android.app.NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(14);

    }
}
