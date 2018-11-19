package uce.edu.ec.achalco_ex_1h.vista;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import java.util.ArrayList;

import uce.edu.ec.achalco_ex_1h.R;
import uce.edu.ec.achalco_ex_1h.controlador.ManejoArchivo;
import uce.edu.ec.achalco_ex_1h.modelo.Vehiculo;

public class EdicionActivity extends AppCompatActivity {

    private ArrayList<Vehiculo> vehiculo = null;

    private static final String TAG = EdicionActivity.class.getSimpleName();

    private Context contexto;
    private Vehiculo auto = new Vehiculo();
    private int tipo;
    private int resultado;
    private ArrayAdapter<String> adaptador;
    private ArrayList<String> lista;
    private ArrayList<Vehiculo> listadoBean;
    private int seleccionadoListaDatos;
    private int seleccionadoListaBean;
    private boolean error=false;
    private ManejoArchivo manejoArchivo;

    public Context getContexto() {
        return contexto;
    }

    public void setContexto(Context contexto) {
        this.contexto = contexto;
    }

    public Vehiculo getAuto() {
        return auto;
    }

    public void setAuto(Vehiculo auto) {
        this.auto = auto;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getResultado() {
        return resultado;
    }

    public void setResultado(int resultado) {
        this.resultado = resultado;
    }

    public ArrayAdapter<String> getAdaptador() {
        return adaptador;
    }

    public void setAdaptador(ArrayAdapter<String> adaptador) {
        this.adaptador = adaptador;
    }

    public ArrayList<String> getLista() {
        return lista;
    }

    public void setLista(ArrayList<String> lista) {
        this.lista = lista;
    }

    public ArrayList<Vehiculo> getListadoBean() {
        return listadoBean;
    }

    public void setListadoBean(ArrayList<Vehiculo> listadoBean) {
        this.listadoBean = listadoBean;
    }

    public int getSeleccionadoListaDatos() {
        return seleccionadoListaDatos;
    }

    public void setSeleccionadoListaDatos(int seleccionadoListaDatos) {
        this.seleccionadoListaDatos = seleccionadoListaDatos;
    }

    public int getSeleccionadoListaBean() {
        return seleccionadoListaBean;
    }

    public void setSeleccionadoListaBean(int seleccionadoListaBean) {
        this.seleccionadoListaBean = seleccionadoListaBean;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public ManejoArchivo getManejoArchivo() {
        return manejoArchivo;
    }

    public void setManejoArchivo(ManejoArchivo manejoArchivo) {
        this.manejoArchivo = manejoArchivo;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edicion);

        String plc =(String) getIntent().getExtras().get("plc");
        System.out.println("ñññññ"+plc);
        Vehiculo v = new Vehiculo();
        vehiculo = (ArrayList<Vehiculo>) getIntent().getExtras().get("datos");
        System.out.println("aqui"+vehiculo);
        for (int i = 0;i<vehiculo.size();i++){
            //System.out.println("ingreso"+vehiculo.get(i).getPlaca());

            if((vehiculo.get(i).getPlaca()).equals(plc)){
                System.out.println("sssss"+vehiculo.get(i).getPlaca());
                v = vehiculo.get(i);
                // System.out.println("holaaaa"+v.getPlaca());

                EditText placa = (EditText) findViewById(R.id.etxPlaca);
                placa.setText(v.getPlaca());
                placa.setFocusable(false);
                break;
            }
        }
    }
}
