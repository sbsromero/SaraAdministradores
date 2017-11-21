package com.sbsromero.proyectosadministradoressara.fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sbsromero.proyectosadministradoressara.R;
import com.sbsromero.proyectosadministradoressara.activities.EditarMonitorActivity;
import com.sbsromero.proyectosadministradoressara.models.Monitor;
import com.squareup.picasso.Picasso;

import java.io.Serializable;

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
    public ImageView imageViewFotoPerfil;
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
        imageViewFotoPerfil = view.findViewById(R.id.imageViewFotoPerfil);

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
        String urlFoto = monitor.getUrlFoto();
        if(urlFoto!=""){
            Uri foto = Uri.parse(urlFoto);
            Picasso.with(getContext()).load(foto).into(imageViewFotoPerfil);
        }
        textViewNombreMonitor.setText(monitor.getNombre());
        textViewTelefono.setText(monitor.getNumTelefono());
        textViewUsername.setText(monitor.getUsername());
        textViewSemestre.setText(monitor.getSemestre());
        textViewLineaAsesoria.setText(monitor.getLineaMonitoria());
    }

    //Metodo que eliminar un monitor
    public void eliminarMonitor(int id){
        monitor = realm.where(Monitor.class)
                .equalTo("id",id)
                .findFirst();
        realm.beginTransaction();
        monitor.deleteFromRealm();
        realm.commitTransaction();
        getActivity().finish();
    }

    //Metodo que hace el llamado a una nueva actividad para editar un monitor
    public void editarMonitor(int id){
        Intent intent = new Intent(getActivity(), EditarMonitorActivity.class);
        intent.putExtra("id",id);
        startActivity(intent);
    }

    //Metodo que comparte a redes sociales mediante un intent
    public void compartir(int id){
        monitor = realm.where(Monitor.class)
                .equalTo("id",id)
                .findFirst();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, monitor.getNombre()+"\n"+monitor.getUsername()
        +"\n"+monitor.getSemestre()+"\n"+monitor.getLineaMonitoria()+"\n"+monitor.getNumTelefono()
        +"\n"+monitor.getLugar());
        startActivity(Intent.createChooser(intent, "Compartir en"));
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
            case R.id.editarMonitor:
                getActivity().finish();
                editarMonitor(monitorId);
                return true;
            case R.id.compartir:
                compartir(monitorId);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
