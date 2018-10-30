package grupo05.optativa3.com.tarea_05_g05.grupo05.optativa3.com.tarea_05_g05.Pojos;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Usuario implements Serializable {

    private String usuario;
    private String clave;
    private String nombre;
    private String email;
    private String celula;
    private Boolean genero;
    private Date fecha;
    private ArrayList<String> asignaturas;
    private boolean becado;

    public Usuario() {
    }

    public Usuario(String usuario, String clave, String nombre, String email, String celula, Boolean genero, Date fecha, ArrayList<String> asignaturas, boolean becado) {
        this.usuario = usuario;
        this.clave = clave;
        this.nombre = nombre;
        this.email = email;
        this.celula = celula;
        this.genero = genero;
        this.fecha = fecha;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelula() {
        return celula;
    }

    public void setCelula(String celula) {
        this.celula = celula;
    }

    public Boolean getGenero() {
        return genero;
    }

    public void setGenero(Boolean genero) {
        this.genero = genero;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public ArrayList<String> getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(ArrayList<String> asignaturas) {
        this.asignaturas = asignaturas;
    }

    public boolean isBecado() {
        return becado;
    }

    public void setBecado(boolean becado) {
        this.becado = becado;
    }

    @Override
    public String toString(){
        return usuario+";"+clave+";"+nombre+";"+email+";"+genero+";"+fecha.toString()+";"+asignaturas.toString()+";"+becado+"\n" ;
    }
}
