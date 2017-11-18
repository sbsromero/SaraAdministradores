package com.sbsromero.proyectosadministradoressara.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

}