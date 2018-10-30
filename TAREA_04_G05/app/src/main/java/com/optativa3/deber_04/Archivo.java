package com.optativa3.deber_04;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Archivo extends AppCompatActivity{
    ListView lista;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista);
        lista = (ListView) findViewById(R.id.listView);
    }
       public void cargarDatos(View view) throws IOException {
            List<String> listar = new ArrayList<String>();
            String lineas;
            InputStream in= this.getResources().openRawResource(R.raw.datos);
            //leer desde un flujo de datos que se llama in
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            if (in!=null){
                while ((lineas=reader.readLine())!=null){
                    listar.add(lineas.split(";")[0]);
                }

            }
            in.close();
           Toast.makeText(this,"Carga de datos"+listar.size(),Toast.LENGTH_LONG);
           //pasar de un areglo ArrayLista a un areglo de string
           String datos[]= listar.toArray(new String [listar.size()]);
           //cargar el listado
           //contexto, de donde sacmos el layout
           ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datos);
           //
           lista.setAdapter(adapter);
        }



    }



