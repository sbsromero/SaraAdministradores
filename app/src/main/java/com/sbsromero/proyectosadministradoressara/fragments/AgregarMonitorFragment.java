package com.sbsromero.proyectosadministradoressara.fragments;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
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
public class AgregarMonitorFragment extends Fragment {

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
    public EditText editTextFecha;
    public EditText editTextHora;
    public ImageButton imgBtnFecha;
    public ImageButton imgBtnHora;
    public ImageButton imgButtonFoto;
    public ImageView imageViewFotoPerfil;
    private final int FOTO_CAMARA = 14;
    private String urlFoto;

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

    /**
     * Metodo encargado de recuperar los atributos de la vista y
     * setear los listener al calendario, hora y registrar
     * @param view
     */
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
        editTextFecha = view.findViewById(R.id.editTextFecha);
        editTextHora = view.findViewById(R.id.editTextHora);
        imgBtnFecha = view.findViewById(R.id.imgBtnFecha);
        imgBtnHora = view.findViewById(R.id.imgBtnHora);
        imgButtonFoto = view.findViewById(R.id.imgButtonFoto);

        accionCalendario(view);
        accionHora(view);
        accionFoto(view);

        btnRegistrarMonitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearMonitor(urlFoto,editTextCedula.getText().toString(), editTextNombre.getText().toString(),
                        editTextTelefono.getText().toString(),editTextUsername.getText().toString(),
                        editTextPassword.getText().toString(),
                        spinnerSemestre.getSelectedItem().toString(),spinerLineaAsesoria.getSelectedItem().toString(),
                       editTextFecha.getText().toString(),editTextHora.getText().toString(),editTextLugar.getText().toString());
            }
        });
    }

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

                    ImageView imageViewFotoPerfil = (ImageView) getView().findViewById(R.id.imageViewFotoPerfil);
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

    /**
     * Metood que permite realizar la insercion en la base de datos
     * @param cedula
     * @param nombre
     * @param telefono
     * @param username
     * @param password
     * @param semestre
     * @param lineaAsesoria
     * @param fecha
     * @param hora
     * @param lugar
     */
    public void crearMonitor(String foto, String cedula, String nombre, String telefono, String username, String password,
                             String semestre, String lineaAsesoria, String fecha, String hora, String lugar){

        boolean bandera = validarCampos(cedula,nombre,telefono, username, password, semestre,lineaAsesoria,fecha,hora,lugar);
        if(bandera){
            String dateString = fecha+" "+hora;
            Date date = null;
            password = Util.encriptarPassword(password);
            try {
                date = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(dateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            realm.beginTransaction();
            Monitor monitor = new Monitor(foto,cedula,nombre,telefono,username,password,semestre,lineaAsesoria,date,lugar);
            realm.copyToRealm(monitor);
            realm.commitTransaction();
            getActivity().finish();
        }
    }

    /**
     * Metodo que valida los campos para agregar un nuevo mentor
     * @param cedula
     * @param nombre
     * @param telefono
     * @param username
     * @param password
     * @param semestre
     * @param lineaAsesoria
     * @param fecha
     * @param hora
     * @param lugar
     * @return true si todos los campos son validos, de lo contrario false
     */
    public boolean validarCampos(String cedula, String nombre, String telefono, String username, String password,
                              String semestre, String lineaAsesoria, String fecha,String hora, String lugar){
        boolean bandera = true;
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        if(cedula.equals("")){
            bandera = false;
            builder.setMessage("El campo cedula no puede estar vacio");
            builder.create().show();
        }else if(nombre.equals("")){
            bandera = false;
            builder.setMessage("El campo nombre no puede estar vacio");
            builder.create().show();
        }else if(telefono.equals("")){
            bandera = false;
            builder.setMessage("El campo telefono no puede estar vacio");
            builder.create().show();
        }else if(username.equals("")){
            bandera = false;
            builder.setMessage("El campo username no puede estar vacio");
            builder.create().show();
        }else if(password.equals("")){
            bandera = false;
            builder.setMessage("El campo password no puede estar vacio");
            builder.create().show();
        }else if(semestre.equals("")){
            bandera = false;
            builder.setMessage("El campo semestre no puede estar vacio");
            builder.create().show();
        }else if(lineaAsesoria.equals("Seleccione uno")){
            bandera = false;
            builder.setMessage("Seleccione una linea de asesoria");
            builder.create().show();
        } else if(fecha.equals("")){
            bandera = false;
            builder.setMessage("El campo fecha no puede estar vacio");
            builder.create().show();
        }else if(hora.equals("")){
            bandera = false;
            builder.setMessage("El campo hora no puede estar vacio");
            builder.create().show();
        } else if(lugar.equals("")){
            bandera = false;
            builder.setMessage("El campo lugar no puede estar vacio");
            builder.create().show();
        }
        return bandera;
    }
}
