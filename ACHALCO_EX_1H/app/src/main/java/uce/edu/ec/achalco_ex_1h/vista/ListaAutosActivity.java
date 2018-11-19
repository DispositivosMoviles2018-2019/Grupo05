package uce.edu.ec.achalco_ex_1h.vista;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import uce.edu.ec.achalco_ex_1h.R;
import uce.edu.ec.achalco_ex_1h.controlador.ManejoArchivo;
import uce.edu.ec.achalco_ex_1h.modelo.Vehiculo;

public class ListaAutosActivity extends AppCompatActivity {

    private ListView listaVehiculos;
    private static RegistroVehiculosActivity rva = new RegistroVehiculosActivity();
    private ArrayAdapter<String> adapter;
    int itemSelected = AdapterView.INVALID_POSITION;
    private ManejoArchivo manejoArchivo = new ManejoArchivo();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_autos);

        manejoArchivo.setContext(getApplicationContext());
        manejoArchivo.leerJSON(getApplicationContext(),"vehiculos.json");
        listaVehiculos = (ListView) findViewById(R.id.listaVehiculos);

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,manejoArchivo.getListadoDatos());
        listaVehiculos.setAdapter(adapter);

        registerForContextMenu(listaVehiculos);

        listaVehiculos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> adapterView, View view, int i, long l) {
                final int posicion=i;

                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ListaAutosActivity.this);
                dialogo1.setTitle("ATENCION");
                dialogo1.setMessage("¿Eliminar este vehiculo?");
                dialogo1.setCancelable(false);
                dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        ArrayList<Vehiculo> listaB = manejoArchivo.getListadoBean();

                        String  dat = listaVehiculos.getItemAtPosition(posicion).toString();

                        int ind = dat.indexOf("Placa");
                        String placa = dat.substring(ind+7, ind+7+8);

                        for (int i=0; i<listaB.size(); i++) {
                            if(listaB.get(i).getPlaca().equals(placa)) {
                                manejoArchivo.getListadoBean().remove(i);
                                break;
                            }
                        }

                        manejoArchivo.getListadoDatos().remove(posicion);
                        adapter.notifyDataSetChanged();
                    }
                });
                dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                    }
                });
                dialogo1.show();

                return false;
            }
        });

        listaVehiculos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;

                itemSelected = itemPosition;

                //ListAdapter la = (ListAdapter) parent.getAdapter();

                ArrayList<View> touchables = parent.getTouchables();

                for (View v : touchables){
                    if (v instanceof TextView){
                        v.setBackgroundColor(0xffffffff);
                    }
                }
                view.setBackgroundColor(0xff999999);
            }

        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_options,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id==R.id.opInsertar){
            Intent intent = new Intent(ListaAutosActivity.this,RegistroVehiculosActivity.class);
            startActivity(intent);
        }

        if(id==R.id.opEditar){
            Intent intent = new Intent(ListaAutosActivity.this,EdicionActivity.class);
            if(itemSelected == AdapterView.INVALID_POSITION) {
                Toast.makeText(getApplicationContext(), "Primero debe seleccionar un item de la lista!" , Toast.LENGTH_LONG).show();
                return true;
            }
            ArrayList<Vehiculo> listaB = manejoArchivo.getListadoBean();
            System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            String  dat = listaVehiculos.getItemAtPosition(itemSelected).toString();
            //System.out.println(dat);

            // ArrayList<String> list = new ArrayList<>();
            // list.add(dat);
            int ind = dat.indexOf("Placa");
            System.out.println(ind);
            String placa = dat.substring(ind+7, ind+7+8);

            intent.putExtra("plc",placa);
            intent.putExtra("datos",listaB);
            intent.putExtra("item",dat);

            Vehiculo auto=null;
            EdicionActivity edicionActivity = new EdicionActivity();
            for (int i=0; i<listaB.size(); i++) {
                if(listaB.get(i).getPlaca().equals(placa)) {
                    auto = listaB.get(i);
                    edicionActivity.setSeleccionadoListaBean(i);
                    break;
                }
            }

            edicionActivity.setTipo(2);
            edicionActivity.setContexto(getApplicationContext());
            edicionActivity.setLista(manejoArchivo.getListadoDatos());
            edicionActivity.setSeleccionadoListaDatos(itemSelected);
            edicionActivity.setAdaptador(adapter);
            edicionActivity.setListadoBean(manejoArchivo.getListadoBean());
            edicionActivity.setAuto(auto);
            edicionActivity.setManejoArchivo(manejoArchivo);

            startActivity(intent);
            return true;
        }

        if(id==R.id.opSalir){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        int id = v.getId();
        MenuInflater inflater = getMenuInflater();

        switch (id){
            case R.id.listaVehiculos:
                inflater.inflate(R.menu.menu_context,menu);
                break;
        }
    }

    @Override
    public boolean onContextItemSelected(final MenuItem item) {
        AdapterView.AdapterContextMenuInfo informacion = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()){
            case R.id.ctxMostrar:

                Toast.makeText(this,"Hola",Toast.LENGTH_SHORT).show();
                return true;

            case R.id.ctxActualizar:
                return true;

            case R.id.ctxEliminar:
                return true;
            default:return super.onContextItemSelected(item);
        }

    }
}
