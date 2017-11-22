package com.sbsromero.proyectosadministradoressara.utils;



import android.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sbsromero.proyectosadministradoressara.models.Monitor;

/**
 * Created by USERPC on 21/11/2017.
 */

public class ManagerFirebase {

    private FirebaseDatabase database;
    private DatabaseReference databaseRef;
    private static ManagerFirebase instancia;
    private Fragment fragment;

    private ManagerFirebase(Fragment fragment){
        database = FirebaseDatabase.getInstance();
        databaseRef = database.getReference();
        this.fragment = fragment;
    }

    public static ManagerFirebase instanciar(Fragment fragment){
        if(instancia == null){
            instancia = new ManagerFirebase(fragment);
        }
        return instancia;
    }

    public void agregarMonitor(Monitor monitor){
        databaseRef.push().setValue(monitor);
    }
}
