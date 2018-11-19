package uce.edu.ec.jpaucar_ex_1h.modelo;

import java.io.Serializable;
import java.util.Date;

public class Vehiculo implements Serializable {
    private String placa;
    private String marca;
    private Date fecFabricacion;
    private Boolean matriculado;
    private Double costo;
    private String color;

    //blanco negro, otro

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

    public Boolean getMatriculado() {
        return matriculado;
    }

    public void setMatriculado(Boolean matriculado) {
        this.matriculado = matriculado;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Vehiculo{" +
                "placa='" + placa + '\'' +
                ", marca='" + marca + '\'' +
                ", fecFabricacion=" + fecFabricacion +
                ", matriculado=" + matriculado +
                ", costo=" + costo +
                ", color='" + color + '\'' +
                '}';
    }
}
