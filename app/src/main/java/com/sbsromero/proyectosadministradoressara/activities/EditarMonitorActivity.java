package com.sbsromero.proyectosadministradoressara.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sbsromero.proyectosadministradoressara.R;

public class EditarMonitorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_monitor);
        getSupportActionBar().setTitle("Editar monitor");
    }
}
