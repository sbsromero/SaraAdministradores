package com.sbsromero.proyectosadministradoressara.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.sbsromero.proyectosadministradoressara.R;
import com.sbsromero.proyectosadministradoressara.fragments.ListMonitorFragment;
import com.sbsromero.proyectosadministradoressara.models.Monitor;

public class MonitoresActivity extends AppCompatActivity implements ListMonitorFragment.MonitorListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitores);
        getSupportActionBar().setTitle("Monitores");
    }

    @Override
    public void enviarDatosMonitor(int id) {
        Intent intent = new Intent(MonitoresActivity.this,VisualizarMonitorActivity.class);
        intent.putExtra("id",id);
        startActivity(intent);
    }
}
