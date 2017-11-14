package com.sbsromero.proyectosadministradoressara.models;

import com.sbsromero.proyectosadministradoressara.app.MyApplication;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by USERPC on 13/11/2017.
 */

public class Monitor extends RealmObject {

    @PrimaryKey
    private int id;
    @Required
    private String cedula;
    @Required
    private String nombre;
    private String urlFoto;
    @Required
    private String username;
    @Required
    private String password;
    private String numTelefono;
    @Required
    private String semestre;
    @Required
    private String lineaMonitoria;
    @Required
    private Date fecha;
    @Required
    private String lugar;

    private RealmList<Cita> citas;

    public Monitor() {
    }

    public Monitor(String cedula, String nombre,String telefono, String username, String password, String semestre, String lineaMonitoria, String lugar) {
        this.id = MyApplication.MonitorId.incrementAndGet();
        this.cedula = cedula;
        this.nombre = nombre;
        this.numTelefono = telefono;
        this.username = username;
        this.password = password;
        this.semestre = semestre;
        this.lineaMonitoria = lineaMonitoria;
        //this.fecha = fecha;
        this.lugar = lugar;
        this.citas = new RealmList<Cita>();
    }

    public int getId() {
        return id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNumTelefono() {
        return numTelefono;
    }

    public void setNumTelefono(String numTelefono) {
        this.numTelefono = numTelefono;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getLineaMonitoria() {
        return lineaMonitoria;
    }

    public void setLineaMonitoria(String lineaMonitoria) {
        this.lineaMonitoria = lineaMonitoria;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public RealmList<Cita> getCitas() {
        return citas;
    }
}
