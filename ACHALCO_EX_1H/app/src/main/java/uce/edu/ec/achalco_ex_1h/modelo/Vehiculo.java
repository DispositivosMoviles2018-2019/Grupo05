package uce.edu.ec.achalco_ex_1h.modelo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Vehiculo implements Serializable{
    private String placa;
    private String marca;
    private Date fecFabricacion;
    private Double costo;
    private Boolean matriculado;
    private String color;

    public Vehiculo(){
    }

    public Vehiculo(String placa, String marca, Date fecFabricacion, Double costo, Boolean matriculado, String color) {
        this.placa = placa;
        this.marca = marca;
        this.fecFabricacion = fecFabricacion;
        this.costo = costo;
        this.matriculado = matriculado;
        this.color = color;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Date getFecFabricacion() {
        return fecFabricacion;
    }

    public void setFecFabricacion(Date fecFabricacion) {
        this.fecFabricacion = fecFabricacion;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public Boolean getMatriculado() {
        return matriculado;
    }

    public void setMatriculado(Boolean matriculado) {
        this.matriculado = matriculado;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vehiculo vehiculo = (Vehiculo) o;

        return placa != null ? placa.equals(vehiculo.placa) : vehiculo.placa == null;
    }

    @Override
    public int hashCode() {
        return placa != null ? placa.hashCode() : 0;
    }

    @Override
    public String toString() {
        SimpleDateFormat formateador = new SimpleDateFormat("yyyy/MM/dd");
        String str;

        str =  " Placa: " + placa + "\n\r"+
                " Marca: " + marca + "\n\r"+
                " Fecha de Fabricación: " + formateador.format(fecFabricacion) + "\n\r"+
                " Costo: " + costo + "\n\r";
        if(matriculado)
            str += " Matriculado: si\n\r";
        else
            str += " Matriculado: no\n\r";
        str += " Color: " + color + "\n\r";
        return str;
    }
}
