package com.sbsromero.proyectosadministradoressara.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sbsromero.proyectosadministradoressara.R;
import com.sbsromero.proyectosadministradoressara.adapters.HomeAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeAdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);

        List<String> options = new ArrayList<>();
        options.add("Monitores");
        options.add("Solicitar cita");

        RecyclerView recyclerViewMonitor = (RecyclerView) findViewById(R.id.recyclerViewHomeAdmin);
        recyclerViewMonitor.setLayoutManager(new GridLayoutManager(this, 2));

        //Creamos nuestro adaptador
        HomeAdapter homeAdapter = new HomeAdapter(options, R.layout.recycler_view_item_home, new HomeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String option, int position) {
                if(position == 0){
                    Intent intent = new Intent(HomeAdminActivity.this,MonitoresActivity.class);
                    startActivity(intent);
                }
   /*             else{
                    Intent intent = new Intent(HomeAdminActivity.this,CancelarCitas.class);
                    startActivity(intent);
                }*/
            }
        });

        recyclerViewMonitor.setHasFixedSize(true);
        //recyclerViewMonitor.setItemAnimator(new DefaultItemAnimator());
        recyclerViewMonitor.setAdapter(homeAdapter);
    }
}
