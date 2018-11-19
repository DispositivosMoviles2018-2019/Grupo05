package uce.edu.ec.dfernandezz_ex_1h.vista;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;




import java.util.ArrayList;

import uce.edu.ec.dfernandezz_ex_1h.controlador.LecturaEscrituraVehiculoXml;
import uce.edu.ec.dfernandezz_ex_1h.modelo.Vehiculo;


public class AdapterVehiculos extends RecyclerView.Adapter<AdapterVehiculos.ViewHolder> {

    private ArrayList<Vehiculo> listaVehiculos;
    private Context myContexto;
    private LecturaEscrituraVehiculoXml busqueda = new LecturaEscrituraVehiculoXml();

    // Obtener referencias de los componentes visuales para cada elemento
    // Es decir, referencias de los EditText, TextViews, Buttons
    //Ademas de esto implemento la clase create context menu para poder definir el listener al card view
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {
        private ItemClickListener itemClickListener;
        // en este ejemplo cada elemento consta solo de un título
        public TextView txt_placa;
        public TextView txt_marca;
        public TextView txt_matriculado;
        public TextView txt_opciones;
        public CardView cardView;

        public ViewHolder(View v)  {
            super(v);
            v.setOnClickListener(ViewHolder.this);
            v.setOnLongClickListener(ViewHolder.this);

            txt_placa = (TextView) v.findViewById(R.id.text_placa);
            txt_marca = (TextView) v.findViewById(R.id.text_marca);
            txt_matriculado = (TextView) v.findViewById(R.id.text_matriculado);
            txt_opciones = (TextView) v.findViewById(R.id.txtOptionDigit);
            cardView = (CardView) v.findViewById(R.id.card_view);


        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
    this.itemClickListener.onClick(view,getAdapterPosition(),false);
        }

        @Override
        public boolean onLongClick(View view) {
            itemClickListener.onClick(view,getAdapterPosition(),true);
            return true;
        }
    }


    // Este es nuestro constructor (puede variar según lo que queremos mostrar)
    public AdapterVehiculos(ArrayList<Vehiculo> myDataSet, Context context) {
        listaVehiculos = myDataSet;
        myContexto = context;
    }

    // El layout manager invoca este método
    // para renderizar cada elemento del RecyclerView
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
        // Creamos una nueva vista
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_vehiculo, parent, false);

        return new ViewHolder(v);
    }

    // Este método reemplaza el contenido de cada view,
    // para cada elemento de la lista (nótese el argumento position)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        // - obtenemos un elemento del dataset según su posición
        // - reemplazamos el contenido de los views según tales datos

        final Vehiculo vehiculo = listaVehiculos.get(position);
        holder.txt_placa.setText(vehiculo.getPlaca());
        holder.txt_marca.setText(vehiculo.getMarca());
        //Debo hacer un IF para el estado de atriculado pilas pocta
        holder.txt_matriculado.setText(vehiculo.getMatriculado().toString());
        holder.txt_opciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //desplegar menu de opciones
                PopupMenu popupMenu = new PopupMenu(myContexto, holder.txt_opciones);
                popupMenu.inflate(R.menu.menu_opciones_list);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.mnu_item_editar:
                                Toast.makeText(myContexto, "Editar Usuario", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(myContexto,EditarVehiculo.class);
                                intent.putExtra("key",listaVehiculos.get(position).getPlaca());
                                myContexto.startActivity(intent);
                                break;
                            case R.id.mnu_item_eliminar:
                                //borrar de la lista
                                listaVehiculos.remove(position);
                                System.out.println(listaVehiculos.toString());
                                busqueda.escribirVeculoXml2(listaVehiculos);
                                 notifyDataSetChanged();
                                   Toast.makeText(myContexto, "Elemento Eliminado", Toast.LENGTH_LONG).show();
                                //persistir Archivo??

                                break;
                            default:
                                break;

                        }

                        return false;
                    }
                });
                popupMenu.show();

            }

        });
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View v, int position, boolean isLongClick) {
                if (isLongClick){

                    Intent intent = new Intent(myContexto,IformacionVehiculo.class);
                    intent.putExtra("key1",listaVehiculos.get(position).getPlaca());
                    myContexto.startActivity(intent);

                    Toast.makeText(myContexto, "Mostrando Mas Informacion...", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    // Método que define la cantidad de elementos del RecyclerView
    // Puede ser más complejo (por ejemplo si implementamos filtros o búsquedas)
    @Override
    public int getItemCount() {
        return listaVehiculos.size();
    }


}




