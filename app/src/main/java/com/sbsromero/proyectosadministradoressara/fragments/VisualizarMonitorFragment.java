package com.sbsromero.proyectosadministradoressara.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sbsromero.proyectosadministradoressara.R;
import com.sbsromero.proyectosadministradoressara.models.Monitor;

import io.realm.Realm;
import io.realm.RealmResults;

import static android.R.attr.id;

/**
 * A simple {@link Fragment} subclass.
 */
public class VisualizarMonitorFragment extends Fragment {


    private Realm realm;
    public TextView textViewNombreMonitor;
    public TextView textViewTelefono;
    public TextView textViewUsername;
    public TextView textViewSemestre;
    public TextView textViewLineaAsesoria;
    private Monitor monitor;
    private int monitorId;



    public VisualizarMonitorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_visualizar_monitor, container, false);
        realm = Realm.getDefaultInstance();
        textViewNombreMonitor = view.findViewById(R.id.textViewNombreMonitor);
        textViewTelefono = view.findViewById(R.id.textViewTelefono);
        textViewUsername = view.findViewById(R.id.textViewUsername);
        textViewSemestre = view.findViewById(R.id.textViewSemestre);
        textViewLineaAsesoria = view.findViewById(R.id.textViewLineaAsesoria);

        if(getActivity().getIntent().getExtras() != null){
            monitorId = getActivity().getIntent().getExtras().getInt("id");
        }
        renderMonitor(monitorId);
        setHasOptionsMenu(true);

        return view;
    }

    //Metodo que renderiza la informacion de un monitor
    public void renderMonitor(int id){
        monitor = realm.where(Monitor.class)
                .equalTo("id",id)
                .findFirst();
        textViewNombreMonitor.setText(monitor.getNombre());
        textViewTelefono.setText(monitor.getNumTelefono());
        textViewUsername.setText(monitor.getUsername());
        textViewSemestre.setText(monitor.getSemestre());
        textViewLineaAsesoria.setText(monitor.getLineaMonitoria());
    }

    public void eliminarMonitor(int id){
        monitor = realm.where(Monitor.class)
                .equalTo("id",id)
                .findFirst();
        realm.beginTransaction();
        monitor.deleteFromRealm();
        realm.commitTransaction();
        getActivity().finish();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.action_bar_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.deleteMonitor:
                eliminarMonitor(monitorId);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
