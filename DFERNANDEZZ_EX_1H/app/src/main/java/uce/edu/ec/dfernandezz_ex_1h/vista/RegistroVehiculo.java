package uce.edu.ec.dfernandezz_ex_1h.vista;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

import uce.edu.ec.dfernandezz_ex_1h.R;
import uce.edu.ec.dfernandezz_ex_1h.controlador.LecturaEscrituraVehiculoXml;


public class RegistroVehiculo extends AppCompatActivity {
    Vehiculo vehiculo;
    LecturaEscrituraVehiculoXml lecturaEscrituraVehiculoXml;
    ArrayList<Vehiculo> listaVehiculos = new ArrayList<>();
    private static final String TAG = "RegistroVehiculo";

    EditText txt_placa;
    EditText txt_marca;
    EditText txt_otroColor;
    TextView txt_fecha;
    EditText txt_costo;

    DatePickerDialog.OnDateSetListener listenerFecha;

    RadioButton rb1, rb2;
    CheckBox cb1, cb2, cb3, cb4;
    Switch matriculado2;


    Button btn_registrar;
    Button btn_volver;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_vehiculo);
        initComponents();
        lecturaEscrituraVehiculoXml =new LecturaEscrituraVehiculoXml();

        final boolean matriculado = rb1.isChecked();

//MANEJO FECHA CON POP UP MENU
        txt_fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int anio = cal.get(Calendar.YEAR);
                int mes = cal.get(Calendar.MONTH);
                int dia = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(
                        RegistroVehiculo.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        listenerFecha,
                        anio, mes, dia);
                //realizar el backgroud trasparente
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }

        });

        //Inicializacion del objeto listener de la fecha aqui el setter de la fecha para el txt
        listenerFecha = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: date" + year + "-" + month + "-" + day);
                String date = year + "-" + month + "-" + day;
                txt_fecha.setText(date);
            }
        };
        //**************************************MANEJO DEL registro de Vehiculos*********************************************
        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean validacion = true;
                listaVehiculos = lecturaEscrituraVehiculoXml.lecturaVehiculosXml();
                vehiculo = new Vehiculo();


                //PLACA
                if (!validarPlaca(txt_placa.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Email no válido", Toast.LENGTH_SHORT).show();
                    validacion = false;

                } else {
                    vehiculo.setPlaca(txt_placa.getText().toString());
                }

                //MARCA
                vehiculo.setMarca(txt_marca.getText().toString());
                //FECHA
                if (txt_fecha.getText().toString() != null) {
                    try {

                        String fecha = txt_fecha.getText().toString();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = sdf.parse(fecha);
                        vehiculo.setFecFabricacion(date);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "No es posible dejar campo fecha vacio", Toast.LENGTH_LONG).show();
                    validacion = false;
                }


                //Matriculado
                if (matriculado == true) {
                    vehiculo.setMatriculado(true);
                } else {
                    vehiculo.setMatriculado(true);
                }

                //Costo
                double costo = Double.parseDouble(txt_costo.getText().toString());
                vehiculo.setCosto(costo);

                //color
                if (cb1.isChecked() == true) {
                    vehiculo.setColor(cb1.getText().toString());
                }if (cb2.isChecked() == true) {
                    vehiculo.setColor(cb2.getText().toString());
                }
                if (cb3.isChecked() == true) {
                    vehiculo.setColor(cb3.getText().toString());
                }
                if (cb4.isChecked() == true) {
                    vehiculo.setColor(cb4.getText().toString());
                }

                if (validacion==true){
                    listaVehiculos.add(vehiculo);
                    System.out.println(listaVehiculos.toString());
                    lecturaEscrituraVehiculoXml.escribirVehiculoXml(vehiculo);
                    Toast.makeText(RegistroVehiculo.this, "AUTO INGRESADO CORRECTAMENTE !!!"  , Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(getApplicationContext(), "Existio un Error Usuario no ingresado", Toast.LENGTH_LONG).show();

                }
            }
        });
        btn_volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(RegistroVehiculo.this,ListaVehiculos.class);
                startActivity(intent2);
            }
        });


    }


    public void initComponents() {
        txt_placa = (EditText) findViewById(R.id.txt_placa_registro);
        txt_marca = (EditText) findViewById(R.id.txt_marca_registro);

        txt_costo=(EditText)findViewById(R.id.txt_costo_registro);


        txt_fecha = (TextView) findViewById(R.id.txt_fecha_registro);

        rb1 = (RadioButton) findViewById(R.id.rb1);
        rb2 = (RadioButton) findViewById(R.id.rb2);

        cb1 = (CheckBox) findViewById(R.id.cb1);
        cb2 = (CheckBox) findViewById(R.id.cb2);
        cb3 = (CheckBox) findViewById(R.id.cb3);
        cb4 = (CheckBox) findViewById(R.id.cb4);


        matriculado2 = (Switch) findViewById(R.id.sw_matriculado2_registro);
        btn_registrar = (Button) findViewById(R.id.btn_registrar_edicion);
        btn_volver = (Button) findViewById(R.id.btn_volver_registro);


    }

    //Validacion Numero De Placa
    private boolean validarPlaca(String placa) {
        Pattern pattern = Pattern.compile("^([A-Z]{3}-\\d{3,4})(0|2|4|6|8)$");
        return pattern.matcher(placa).matches();
    }


}
