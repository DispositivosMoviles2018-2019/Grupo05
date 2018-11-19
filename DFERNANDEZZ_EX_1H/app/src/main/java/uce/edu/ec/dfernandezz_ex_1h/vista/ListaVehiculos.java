package uce.edu.ec.dfernandezz_ex_1h.vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import uce.edu.ec.dfernandezz_ex_1h.R;
import uce.edu.ec.dfernandezz_ex_1h.controlador.LecturaEscrituraVehiculoXml;

public class ListaVehiculos extends AppCompatActivity {
    private RecyclerView lista_vehiculos_vista;
    private AdapterVehiculos adapterVehiculos;
    private LecturaEscrituraVehiculoXml lecturaEscritura= new LecturaEscrituraVehiculoXml();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_vehiculos);
        lista_vehiculos_vista=(RecyclerView)findViewById(R.id.recycler_view_vehiculos);
        //lista_vehiculos_vista.setHasFixedSize(true);

        System.out.println(lecturaEscritura.lecturaVehiculosXml().toString());
        adapterVehiculos = new AdapterVehiculos(lecturaEscritura.lecturaVehiculosXml(),this);
        lista_vehiculos_vista.setAdapter(adapterVehiculos);
        lista_vehiculos_vista.setLayoutManager(new  LinearLayoutManager(this));

        //lista_vehiculos_vista.setOnClickListener(this);
        System.out.println(lecturaEscritura.lecturaVehiculosXml().toString());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_opciones_toolbar,menu);
        return super.onCreateOptionsMenu(menu);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch(item.getItemId()){
            case R.id.mnu_item_nuevo:
                Toast.makeText(this, "Nuevo Automovil", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(ListaVehiculos.this, RegistroVehiculo.class);
                startActivity(intent);
                return true;
            case R.id.mnu_item_persistir:
                Toast.makeText(this, "Persistir", Toast.LENGTH_LONG).show();
                return  true;
            case R.id.mnu_item_salir:
                finish();
                Toast.makeText(this, "Salir", Toast.LENGTH_LONG).show();
                return  true;
                default:
                    return super.onOptionsItemSelected(item);
        }

    }
}
