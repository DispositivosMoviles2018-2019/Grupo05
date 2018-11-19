package uce.edu.ec.achalco_ex_1h.vista;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import uce.edu.ec.achalco_ex_1h.R;
import uce.edu.ec.achalco_ex_1h.controlador.ManejoArchivo;
import uce.edu.ec.achalco_ex_1h.modelo.Vehiculo;

public class RegistroVehiculosActivity extends AppCompatActivity {

    private EditText etxPlaca,etxMarca,etxCosto,etxColor;
    private Button btnGuardarVehiculo;
    private TextView txtDisplay;
    private RadioButton rbSi,rbNo;
    private ArrayList<Vehiculo> listaVehiculos;
    private DatePickerDialog.OnDateSetListener listenerFecha;
    private static final String TAG = "Registro";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_vehiculos);

        etxPlaca = (EditText) findViewById(R.id.etxPlaca);
        etxMarca = (EditText) findViewById(R.id.etxMarca);
        etxCosto = (EditText) findViewById(R.id.etxCosto);
        etxColor = (EditText) findViewById(R.id.etxColor);
        txtDisplay = (TextView) findViewById(R.id.txtDisplay);
        rbNo = (RadioButton) findViewById(R.id.rbNo);
        rbSi = (RadioButton) findViewById(R.id.rbSi);
        listaVehiculos = new ArrayList<>();
        btnGuardarVehiculo = (Button) findViewById(R.id.btnGuardarVehiculo);
        final String nombre = "vehiculos.json";
        final ManejoArchivo manejoArchivo = new ManejoArchivo();
        btnGuardarVehiculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                manejoArchivo.escribirJSON(nombre,registroVehiculo());
                Toast.makeText(RegistroVehiculosActivity.this,"AUTO INGRESADO SATISFACTORIAMENTE",Toast.LENGTH_SHORT).show();
            }
        });

        txtDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        RegistroVehiculosActivity.this,android.R.style.Theme_Holo_Dialog_MinWidth,listenerFecha,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        listenerFecha = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month +1;
                Log.d(TAG,"onDateSet: date" +month+"/"+day+"/"+year);
                String date = month+"/"+day+"/"+year;
                txtDisplay.setText(date);
            }
        };

    }

    public ArrayList<Vehiculo> registroVehiculo(){
        Vehiculo vehiculo = new Vehiculo();
        Gson gson = new Gson();
        String file = "vehiculos.json";
        boolean matricula = rbSi.isChecked();


        double precio = Double.valueOf(etxCosto.getText().toString());
        if(precio>15.000&&precio<35.000){
            vehiculo.setPlaca(etxPlaca.getText().toString());

        }else{
            Toast.makeText(RegistroVehiculosActivity.this,"El precio mayor 15.000 y menor que 35.000",Toast.LENGTH_LONG).show();
        }

        vehiculo.setMarca(etxMarca.getText().toString());
        vehiculo.setColor(etxColor.getText().toString());
        vehiculo.setCosto(precio);

        if(matricula== true){
            vehiculo.setMatriculado(matricula);
        }else{
            vehiculo.setMatriculado(false);
        }

        try{
            String fecha = txtDisplay.getText().toString();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            vehiculo.setFecFabricacion(sdf.parse(fecha));
        }catch (Exception e){
            e.printStackTrace();
        }

        listaVehiculos.add(vehiculo);

        return listaVehiculos;
    }
}
