package uce.edu.ec.jpaucar_ex_1h.vista;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import uce.edu.ec.jpaucar_ex_1h.controlador.LecturaEscrituraVehiculoXml;
import uce.edu.ec.jpaucar_ex_1h.modelo.Vehiculo;
import uce.edu.ec.myapplication.R;

public class IformacionVehiculo extends AppCompatActivity {
    LecturaEscrituraVehiculoXml lecturaEscrituraVehiculoXml = new LecturaEscrituraVehiculoXml();
    Vehiculo buscar = new Vehiculo();
    TextView txt_placa;
    TextView txt_marca;
    TextView txt_fecha;
    TextView txt_costo;
    TextView txt_matriculado;
    TextView txt_color;

    Button btn_regresar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iformacion_vehiculo);
        init();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String placa_Auto = extras.getString("key1");
            buscar = lecturaEscrituraVehiculoXml.buscarVehiculo(placa_Auto, lecturaEscrituraVehiculoXml.lecturaVehiculosXml());
            txt_placa.setText(buscar.getPlaca());
            txt_marca.setText(buscar.getMarca());
            txt_color.setText(buscar.getColor());
            txt_costo.setText(buscar.getCosto().toString());
            if (buscar.getMatriculado()== Boolean.TRUE){
                txt_matriculado.setText("SI");
            }else{
                txt_matriculado.setText("NO");
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            txt_fecha.setText(sdf.format(buscar.getFecFabricacion()));
        }

        btn_regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IformacionVehiculo.this,ListaVehiculos.class);
                startActivity(intent);
            }
        });
    }


    public void init() {
        txt_placa = (TextView) findViewById(R.id.txt_info_placa);
        txt_marca = (TextView)findViewById(R.id.txt_info_marca);
        txt_fecha = (TextView)findViewById(R.id.txt_info_fecha);
        txt_costo = (TextView)findViewById(R.id.txt_info_costo);
        txt_matriculado = (TextView)findViewById(R.id.txt_info_matriculado);
        txt_color = (TextView)findViewById(R.id.txt_info_color);
        btn_regresar=(Button)findViewById(R.id.btn_info_regresar);

    }
}
