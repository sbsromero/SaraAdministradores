package com.sbsromero.proyectosadministradoressara.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sbsromero.proyectosadministradoressara.R;
import com.sbsromero.proyectosadministradoressara.models.Admin;
import com.sbsromero.proyectosadministradoressara.models.Monitor;
import com.sbsromero.proyectosadministradoressara.utils.Util;

import io.realm.Realm;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button btnLogin;
    private SharedPreferences prefs;
    private Realm realm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bindUi();
        realm = Realm.getDefaultInstance();
        //Util.agregarAdmin(realm);
/*        realm.beginTransaction();
        realm.deleteAll();
        realm.commitTransaction();*/
        prefs = getSharedPreferences("preferencesLogin", Context.MODE_PRIVATE);
        guardarCredenciales();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();
                login(username,password);
                guardarPreferencias(username,password);
            }
        });
    }

    //Inicializa los elementos de la vista
    private void bindUi(){
        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
    }

    //Si existen datos de credenciales la guardan
    private void guardarCredenciales(){
        String username = Util.getUsername(prefs);
        String password = Util.getPassword(prefs);

        if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)){
            editTextUsername.setText(username);
            editTextPassword.setText(password);
        }
    }

    //Metodo que realiza la redireccion hacia el home del administrador
    //o retorna un mensaje de error
    private void login(String username, String password){
        if(validarDatos(username,password)){
            Intent intent = new Intent(LoginActivity.this, MonitoresActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        else{
            Toast.makeText(this,"Datos incorrectos, por favor verifiquelos",Toast.LENGTH_LONG).show();
        }
    }

    //Metodo que guarda en el sharedPreferences el username y el password
    private void guardarPreferencias(String username,String password){
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("username",username);
        editor.putString("password",password);
        editor.commit();
        editor.apply();
    }

    //Metodo que valida los datos, al momento de iniciar sesion
    private boolean validarDatos(String username, String password){
        Admin admin = realm.where(Admin.class)
                .equalTo("username", username)
                .findFirst();
        if(admin!=null){
            String adminPassword = Util.desencriptarPassword(admin.getPassword());
            return (password.equals(adminPassword)) ? true : false;
        }
        return false;
    }

}
