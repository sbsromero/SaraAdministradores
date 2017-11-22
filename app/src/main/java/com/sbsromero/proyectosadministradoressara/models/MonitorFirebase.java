package com.sbsromero.proyectosadministradoressara.models;

import java.util.Date;
import java.util.List;

/**
 * Created by USERPC on 21/11/2017.
 */

public class MonitorFirebase {


    private int id;
    private String cedula;
    private String nombre;
    private String urlFoto;
    private String username;
    private String password;
    private String numTelefono;
    private String semestre;
    private String lineaMonitoria;
    private Date fecha;
    private String lugar;
    private List<Cita> citas;

    public MonitorFirebase(String cedula, String nombre, String urlFoto, String username, String password,
                           String numTelefono, String semestre, String lineaMonitoria, Date fecha, String lugar) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.urlFoto = urlFoto;
        this.username = username;
        this.password = password;
        this.numTelefono = numTelefono;
        this.semestre = semestre;
        this.lineaMonitoria = lineaMonitoria;
        this.fecha = fecha;
        this.lugar = lugar;
        //this.citas = citas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }
}
