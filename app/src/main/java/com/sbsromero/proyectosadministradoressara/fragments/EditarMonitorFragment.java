package com.sbsromero.proyectosadministradoressara.fragments;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.sbsromero.proyectosadministradoressara.R;
import com.sbsromero.proyectosadministradoressara.models.Monitor;
import com.sbsromero.proyectosadministradoressara.utils.Util;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import io.realm.Realm;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditarMonitorFragment extends Fragment {

    private Realm realm;
    private Monitor monitor;
    public Button btnEditarMonitor;
    public EditText editTextCedula;
    public EditText editTextNombre;
    public EditText editTextTelefono;
    public EditText editTextUsername;
    public EditText editTextPassword;
    public Spinner spinnerSemestre;
    public Spinner spinerLineaAsesoria;
    public EditText editTextLugar;
    public EditText editTextFecha;
    public EditText editTextHora;
    public ImageButton imgBtnFecha;
    public ImageButton imgBtnHora;
    public ImageButton imgButtonFoto;
    public ImageView imageViewFotoPerfil;
    private final int FOTO_CAMARA = 14;
    private String urlFoto;
    private int monitorId;

    public EditarMonitorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_editar_monitor, container, false);
        realm = Realm.getDefaultInstance();
        bindUi(view);

        if(getActivity().getIntent().getExtras() != null){
            monitorId = getActivity().getIntent().getExtras().getInt("id");
        }

        renderMonitor(monitorId);
        accionCalendario(view);
        accionHora(view);
        accionFoto(view);
        btnEditarMonitor(monitorId);

        return view;
    }

    /**
     * Metodo que dita un monitor
     * @param id
     * @param urlFoto
     * @param cedula
     * @param nombre
     * @param telefono
     * @param password
     * @param semestre
     * @param lineaAsesoria
     * @param fecha
     * @param hora
     * @param lugar
     */
    public void editarMonitor(int id, String urlFoto, String cedula, String nombre, String telefono
    ,String password, String semestre, String lineaAsesoria, String fecha,
                              String hora, String lugar){
        monitor = realm.where(Monitor.class)
                .equalTo("id",id)
                .findFirst();
        realm.beginTransaction();
        monitor.setUrlFoto(urlFoto);
        monitor.setCedula(cedula);
        monitor.setNombre(nombre);
        monitor.setNumTelefono(telefono);
        monitor.setPassword(Util.encriptarPassword(password));
        monitor.setSemestre(semestre);
        monitor.setLineaMonitoria(lineaAsesoria);

        String dateString = fecha+" "+hora;
        Date date = null;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        monitor.setFecha(date);
        monitor.setLugar(lugar);
        realm.copyToRealmOrUpdate(monitor);
        realm.commitTransaction();
        getActivity().finish();
    }

    /**
     * Metodo que setea el listener al boton de editar y hace un llamado
     * al metodo de actualizar los datos
     * @param id
     */
    public void btnEditarMonitor(final int id){
        btnEditarMonitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editarMonitor(id, urlFoto,editTextCedula.getText().toString(), editTextNombre.getText().toString(),
                        editTextTelefono.getText().toString(),
                        editTextPassword.getText().toString(),
                        spinnerSemestre.getSelectedItem().toString(),spinerLineaAsesoria.getSelectedItem().toString(),
                        editTextFecha.getText().toString(),editTextHora.getText().toString(),editTextLugar.getText().toString());
            }
        });
    }

    //Metodo que setea el listener al boton calendario
    public void accionCalendario(View view){
        imgBtnFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker arg0, int year, int month, int day) {
                        editTextFecha.setText(day+"/"+month+"/"+year);
                    }
                };
                DatePickerDialog dp = new DatePickerDialog(getContext(),myDateListener,year,month,day);
                dp.show();
            }
        });
    }
    //Metodo que setea el listener al boton de hora
    public void accionHora(View vie){
        imgBtnHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR);
                int minute = c.get(Calendar.MINUTE);
                TimePickerDialog.OnTimeSetListener timerListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                        editTextHora.setText(hour+":"+minute);
                    }
                };

                TimePickerDialog tm = new TimePickerDialog(getContext(),timerListener,hour,minute,false);
                tm.show();
            }
        });
    }
    //Metdo que setea el listener al boton de cambiar imagen de perfil
    public void accionFoto(View view){
        ImageButton imgButtonFoto = (ImageButton) view.findViewById(R.id.imgButtonFoto);
        imgButtonFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intenCamara = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Intent intenCamara = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intenCamara, FOTO_CAMARA);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case FOTO_CAMARA:
                if (resultCode == Activity.RESULT_OK) {
                    Uri result = data.getData();

                    imageViewFotoPerfil = getView().findViewById(R.id.imageViewFotoPerfil);
                    Picasso.with(getContext()).load(result).into(imageViewFotoPerfil);
                    urlFoto = result.toString();
                    Toast.makeText(getContext(),urlFoto,Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(), "hubo un error en la captura de imagen", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }
    }

    //Metodo que renderiza la informacion de un monitor
    public void renderMonitor(int id){
        monitor = realm.where(Monitor.class)
                .equalTo("id",id)
                .findFirst();
        urlFoto = monitor.getUrlFoto();
        if(urlFoto!=""){
            Uri foto = Uri.parse(urlFoto);
            Picasso.with(getContext()).load(foto).into(imageViewFotoPerfil);
        }
        editTextCedula.setText(monitor.getCedula());
        editTextNombre.setText(monitor.getNombre());
        editTextTelefono.setText(monitor.getNumTelefono());
        editTextUsername.setText(monitor.getUsername());
        editTextPassword.setText(monitor.getPassword());
        spinnerSemestre.setSelection(getIndex(spinnerSemestre, monitor.getSemestre()));
        spinerLineaAsesoria.setSelection(getIndex(spinerLineaAsesoria,monitor.getLineaMonitoria()));
        Date date = monitor.getFecha();
        SimpleDateFormat simpleDate =  new SimpleDateFormat("dd/MM/yyyy");
        String fecha = simpleDate.format(date).toString();
        editTextFecha.setText(fecha);
        SimpleDateFormat simpleHour = new SimpleDateFormat("hh:mm");
        String hora = simpleHour.format(date).toString();
        editTextHora.setText(hora);
        editTextLugar.setText(monitor.getLugar());
    }

    //Metodo que inicializa los componentes graficos
    public void bindUi(View view){
        btnEditarMonitor = view.findViewById(R.id.btnEditarMonitor);
        editTextCedula = view.findViewById(R.id.editTextCedula);
        editTextNombre = view.findViewById(R.id.editTextNombre);
        editTextTelefono = view.findViewById(R.id.editTextTelefono);
        editTextUsername = view.findViewById(R.id.editTextUsername);
        editTextPassword = view.findViewById(R.id.editTextPassword);
        spinnerSemestre = view.findViewById(R.id.spinnerSemestre);
        spinerLineaAsesoria = view.findViewById(R.id.spinnerLineaMonitoria);
        editTextLugar = view.findViewById(R.id.editTextLugar);
        editTextFecha = view.findViewById(R.id.editTextFecha);
        editTextHora = view.findViewById(R.id.editTextHora);
        imgBtnFecha = view.findViewById(R.id.imgBtnFecha);
        imgBtnHora = view.findViewById(R.id.imgBtnHora);
        imgButtonFoto = view.findViewById(R.id.imgButtonFoto);
        imageViewFotoPerfil = view.findViewById(R.id.imageViewFotoPerfil);

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
    }

    /**
     * Metodo que obtiene el index del spiner de acuerdo a un valor
     * @param spinner
     * @param myString
     * @return
     */
    private int getIndex(Spinner spinner, String myString)
    {
        int index = 0;

        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                index = i;
                break;
            }
        }
        return index;
    }

}
