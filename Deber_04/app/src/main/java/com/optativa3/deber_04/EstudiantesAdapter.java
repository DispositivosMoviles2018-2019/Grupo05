package com.optativa3.deber_04;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class EstudiantesAdapter extends ArrayAdapter{

    private Context context;
    private ArrayList<Estudiante> datos;

    public EstudiantesAdapter(Context context, ArrayList<Estudiante> datos) {

        super(context, R.layout.inicio, datos);
        // Guardamos los parámetros en variables de clase.
        this.context = context;
        this.datos = datos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // En primer lugar "inflamos" una nueva vista, que será la que se
        // mostrará en la celda del ListView.
        View item = LayoutInflater.from(context).inflate(R.layout.inicio, null);
        // A partir de la vista, recogeremos los controles que contiene para
        // poder manipularlos.
        // Recogemos el ImageView y le asignamos una foto.
        //ImageView imagen = (ImageView) item.findViewById(R.id.imgAnimal);
        //imagen.setImageResource(datos.get(position).getDrawableImageID());
        //TextView nombre = (TextView) item.findViewById(R.id.txtIusuario);
        //nombre.setText(datos.get(position).getUsuario());
        //TextView clave = (TextView) item.findViewById(R.id.txtIusuario);
        //clave.setText(datos.get(position).getUsuario());
        // Devolvemos la vista para que se muestre en el ListView.
        return item;
    }


}
