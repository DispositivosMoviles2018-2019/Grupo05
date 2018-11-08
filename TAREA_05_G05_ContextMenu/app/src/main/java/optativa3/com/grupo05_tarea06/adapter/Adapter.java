package optativa3.com.grupo05_tarea06.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import optativa3.com.grupo05_tarea06.R;
import optativa3.com.grupo05_tarea06.pojo.Usuario;

public class Adapter extends BaseAdapter {

    private Context context;
    private ArrayList<Usuario> arrayList;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");

    public Adapter(Context context, ArrayList<Usuario> arrayList){
        this.context=context;
        this.arrayList=arrayList;
    }
    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view==null){
            LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view=layoutInflater.inflate(R.layout.item,null);
        }
        TextView usuario=(TextView)view.findViewById(R.id.tv_usuario);
        TextView clave=(TextView)view.findViewById(R.id.tv_clave);
        TextView nombre=(TextView)view.findViewById(R.id.tv_nombre);
        TextView email=(TextView)view.findViewById(R.id.tv_email);
        TextView celular=(TextView)view.findViewById(R.id.tv_ceular);
        TextView genero=(TextView)view.findViewById(R.id.tv_genero);
        TextView fecha=(TextView)view.findViewById(R.id.tv_fecha);
        TextView asignaturas=(TextView)view.findViewById(R.id.tv_asignaturas);
        TextView beca=(TextView)view.findViewById(R.id.tv_beca);


        usuario.setText(arrayList.get(i).getUsuario());
        clave.setText(arrayList.get(i).getClave());
        nombre.setText(arrayList.get(i).getNombre());
        email.setText(arrayList.get(i).getEmail());
        celular.setText(arrayList.get(i).getCelula());
        genero.setText(((arrayList.get(i).getGenero())?"Hombre":"Mujer")); // uso condicional ? : (condicion)?valor1:valor2;
        fecha.setText(sdf.format(arrayList.get(i).getFecha()));  // uso de format para recuperar la fecha en formato dd/MM/aaaa
        switch (arrayList.get(i).getAsignaturas().size()) {
           case 1:
               asignaturas.setText(arrayList.get(i).getAsignaturas().get(0));
               break;
        }
        beca.setText((  arrayList.get(i).isBecado()?"Si":"No" )); //(condicion)?"":"";
        return view;
    }

}
