package com.optativa3.deber_04;

import java.util.Arrays;
import java.util.Date;

public class Estudiante {

    String usuario;
    String clave;
    String nombre;
    String apellido;
    String email;
    String celular;
    String idfoto;
    boolean genero;                 //radiobutton
    String fecha_nacimiento;          // spiner-dd/mm/aaaa
    String asignaturas;            //checkbox entre 5 min1-max5
    boolean becado;                 //switch

    public Estudiante() {
    }

    public Estudiante(String usuario, String clave, String nombre, String apellido,
                      String email, String celular, String idfoto, boolean genero,
                      String fecha_nacimiento, String asignaturas, boolean becado) {
        this.usuario = usuario;
        this.clave = clave;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.celular = celular;
        this.idfoto = idfoto;
        this.genero = genero;
        this.fecha_nacimiento = fecha_nacimiento;
        this.asignaturas = asignaturas;
        this.becado = becado;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getIdfoto() {
        return idfoto;
    }

    public void setIdfoto(String idfoto) {
        this.idfoto = idfoto;
    }

    public boolean isGenero() {
        return genero;
    }

    public void setGenero(boolean genero) {
        this.genero = genero;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(String asignaturas) {
        asignaturas = asignaturas;
    }

    public boolean isBecado() {
        return becado;
    }

    public void setBecado(boolean becado) {
        this.becado = becado;
    }

    @Override
    public String toString() {
        return  usuario + ';' +
                clave + ';' +
                nombre + ';' +
                apellido + ';' +
                email + ';' +
                celular + ';' +
                idfoto + ';' +
                genero +';'+
                fecha_nacimiento +';'+
                asignaturas +';'+
                becado;
    }
}
