package com.sbsromero.proyectosadministradoressara.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.sbsromero.proyectosadministradoressara.R;
import com.sbsromero.proyectosadministradoressara.models.Monitor;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class SolicitarCitaFragment extends Fragment {

    public EditText cedulaSolicitante;
    public EditText nombreSolicitante;
    public Spinner spinnerSemestre;
    public Spinner spinnerLineaMonitoria;
    public Spinner spinnerMonitores;
    public Button botonBuscar;
    private RealmResults<Monitor> monitores;
    private Realm realm;

    public SolicitarCitaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_solicitar_cita, container, false);
        bindUi(view);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.semestres_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerSemestre.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapterLineaMonitoria = ArrayAdapter.createFromResource(getContext(),
                R.array.lineasAsesoria_array, android.R.layout.simple_spinner_item);
        adapterLineaMonitoria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLineaMonitoria.setAdapter(adapterLineaMonitoria);

        realm = Realm.getDefaultInstance();
        botonBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dni = cedulaSolicitante.getText().toString();
                String name = nombreSolicitante.getText().toString();
                String semestre = spinnerSemestre.getSelectedItem().toString();
                String lineaMonitoria = spinnerLineaMonitoria.getSelectedItem().toString();
                buscarMonitor(dni,name,semestre,lineaMonitoria);
                if(monitores.size()!=0){

                    List<String> res = spinnerRes(monitores);
                    spinnerMonitores.setVisibility(View.VISIBLE);

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                            getContext(), android.R.layout.simple_spinner_item, res);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerMonitores.setAdapter(adapter);

                }
                else{
                    Toast.makeText(view.getContext(),"No se encontraron monitores",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    public void bindUi(View view){
        cedulaSolicitante = view.findViewById(R.id.cedulaSolicitante);
        nombreSolicitante = view.findViewById(R.id.nombreSolicitante);
        spinnerSemestre = view.findViewById(R.id.spinnerSemestre);
        spinnerLineaMonitoria = view.findViewById(R.id.spinnerLineaMonitoria);
        spinnerMonitores = view.findViewById(R.id.spinnerMonitores);
        botonBuscar = view.findViewById(R.id.botonBuscar);
    }

    public List<String> spinnerRes(RealmResults<Monitor> monitores){
        List<String> res = new ArrayList<String>();
        for (int i = 0; i < monitores.size(); i++) {
            res.add(monitores.get(i).getNombre());
        }
        return res;
    }

    public void buscarMonitor(String dni, String name, String semestre, String lineaMonitoria){
        monitores =  realm.where(Monitor.class)
                .equalTo("activo",true)
                .equalTo("lineaMonitoria",lineaMonitoria)
                .findAll();
    }

}
