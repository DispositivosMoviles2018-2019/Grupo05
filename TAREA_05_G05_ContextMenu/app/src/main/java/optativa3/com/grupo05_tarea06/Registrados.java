package optativa3.com.grupo05_tarea06;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import optativa3.com.grupo05_tarea06.adapter.Adapter;
import optativa3.com.grupo05_tarea06.administrar.AdministrarArchivos;
import optativa3.com.grupo05_tarea06.pojo.Usuario;

public class Registrados extends AppCompatActivity {
    private ListView lista;
    private Adapter adapter;
    ArrayList<Usuario> listaUsuarios=new ArrayList<>();
    AdministrarArchivos ad=new AdministrarArchivos();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrados);
        try {
            listaUsuarios=ad.cargarDatos();

            adapter=new Adapter(this,listaUsuarios);
            lista=(ListView)findViewById(R.id.lv_lista);
            lista.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
