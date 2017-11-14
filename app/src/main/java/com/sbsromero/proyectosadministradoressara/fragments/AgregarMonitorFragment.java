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
import android.widget.TextView;
import android.widget.Toast;

import com.sbsromero.proyectosadministradoressara.R;
import com.sbsromero.proyectosadministradoressara.models.Monitor;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class AgregarMonitorFragment extends Fragment{

    private Realm realm;
    public Button btnRegistrarMonitor;
    public EditText editTextCedula;
    public EditText editTextNombre;
    public EditText editTextTelefono;
    public EditText editTextUsername;
    public EditText editTextPassword;
    public Spinner spinnerSemestre;
    public Spinner spinerLineaAsesoria;
    public EditText editTextLugar;

    public AgregarMonitorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_agregar_monitor, container, false);
        realm = Realm.getDefaultInstance();

        Spinner spinner = (Spinner) view.findViewById(R.id.spinnerSemestre);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.semestres_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        Spinner spinnerLineaMonitoria = (Spinner) view.findViewById(R.id.spinnerLineaMonitoria);
        ArrayAdapter<CharSequence> adapterLineaMonitoria = ArrayAdapter.createFromResource(getContext(),
                R.array.lineasAsesoria_array, android.R.layout.simple_spinner_item);
        adapterLineaMonitoria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLineaMonitoria.setAdapter(adapterLineaMonitoria);

        agregarMonitor(view);

        return view;
    }

    public void agregarMonitor(View view)
    {
        btnRegistrarMonitor = view.findViewById(R.id.btnRegistrarMonitor);
        editTextCedula = view.findViewById(R.id.editTextCedula);
        editTextNombre = view.findViewById(R.id.editTextNombre);
        editTextTelefono = view.findViewById(R.id.editTextTelefono);
        editTextUsername = view.findViewById(R.id.editTextUsername);
        editTextPassword = view.findViewById(R.id.editTextPassword);
        spinnerSemestre = view.findViewById(R.id.spinnerSemestre);
        spinerLineaAsesoria = view.findViewById(R.id.spinnerLineaMonitoria);
        editTextLugar = view.findViewById(R.id.editTextLugar);

        btnRegistrarMonitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearMonitor(editTextCedula.getText().toString(), editTextNombre.getText().toString(),
                        editTextTelefono.getText().toString(),editTextUsername.getText().toString(),
                        editTextPassword.getText().toString(),
                        spinnerSemestre.getSelectedItem().toString(),spinerLineaAsesoria.getSelectedItem().toString(),
                        editTextLugar.getText().toString());
                getActivity().finish();
            }
        });

    }

    public void crearMonitor(String cedula, String nombre, String telefono, String username, String password,
                             String semestre, String lineaAsesoria, String lugar){
        realm.beginTransaction();
        Monitor monitor = new Monitor(cedula,nombre,telefono,username,password,semestre,lineaAsesoria,lugar);
        realm.copyToRealm(monitor);
        realm.commitTransaction();
    }

}
