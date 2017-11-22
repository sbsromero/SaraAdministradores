package com.sbsromero.proyectosadministradoressara.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.sbsromero.proyectosadministradoressara.R;
import com.sbsromero.proyectosadministradoressara.fragments.ListMonitorFragment;
import com.sbsromero.proyectosadministradoressara.models.Monitor;

import java.util.zip.Inflater;

public class MonitoresActivity extends AppCompatActivity implements ListMonitorFragment.MonitorListener {

    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitores);
        getSupportActionBar().setTitle(R.string.monitores);
        prefs = getSharedPreferences("preferencesLogin", Context.MODE_PRIVATE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_logout:
                cerrarSesion();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void enviarDatosMonitor(int id) {
        Intent intent = new Intent(MonitoresActivity.this,VisualizarMonitorActivity.class);
        intent.putExtra("id",id);
        startActivity(intent);
    }

    //Cierra sesion sin elminiar los shared preferences
    private void cerrarSesion(){
        //prefs.edit().clear().apply();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
