package uce.edu.ec.jpaucar_ex_1h.vista;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
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

import uce.edu.ec.jpaucar_ex_1h.controlador.LecturaEscrituraVehiculoXml;
import uce.edu.ec.jpaucar_ex_1h.modelo.Vehiculo;
import uce.edu.ec.myapplication.R;

public class EditarVehiculo extends AppCompatActivity {
    Vehiculo vehiculobuscar;
    LecturaEscrituraVehiculoXml lecturaEscrituraVehiculoXml;
    ArrayList<Vehiculo> listaVehiculos = new ArrayList<>();
    private static final String TAG = "RegistroVehiculo";

    TextView txt_placa;
    EditText txt_marca;
    EditText txt_otroColor;
    TextView txt_fecha;
    EditText txt_costo;

    DatePickerDialog.OnDateSetListener listenerFecha;

    RadioButton rb1, rb2;
    CheckBox cb1, cb2, cb3;
    Switch matriculado2;


    Button btn_registrar;
    Button btn_volver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_vehiculo);
        initComponents();
        lecturaEscrituraVehiculoXml = new LecturaEscrituraVehiculoXml();

        //****************************************************************************************Busqueda y seteo de usuarios.
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String placa_Auto = extras.getString("key");
            vehiculobuscar = lecturaEscrituraVehiculoXml.buscarVehiculo(placa_Auto, lecturaEscrituraVehiculoXml.lecturaVehiculosXml());
            txt_placa.setText(vehiculobuscar.getPlaca());

        }


//MANEJO FECHA CON POP UP MENU
        txt_fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int anio = cal.get(Calendar.YEAR);
                int mes = cal.get(Calendar.MONTH);
                int dia = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(
                        EditarVehiculo.this,
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
                lecturaEscrituraVehiculoXml = new LecturaEscrituraVehiculoXml();
                final boolean matriculado = rb1.isChecked();
                Vehiculo vehiculo = new Vehiculo();

                boolean validacion = true;
                listaVehiculos = lecturaEscrituraVehiculoXml.lecturaVehiculosXml();

//VehiculoPlaca
                vehiculo.setPlaca(vehiculobuscar.getPlaca());

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
                }
                if (cb2.isChecked() == true) {
                    vehiculo.setColor(cb2.getText().toString());
                }
                if (cb3.isChecked() == true) {
                    vehiculo.setColor(txt_otroColor.getText().toString());
                }

                if (validacion == true) {


                    for (int i = 0; i < listaVehiculos.size(); i++) {
                        if (listaVehiculos.get(i).getPlaca().equals(vehiculobuscar.getPlaca())) {

                            listaVehiculos.get(i).setCosto(vehiculo.getCosto());
                            listaVehiculos.get(i).setColor(vehiculo.getColor());
                            listaVehiculos.get(i).setMatriculado(vehiculo.getMatriculado());
                            listaVehiculos.get(i).setPlaca(vehiculobuscar.getPlaca());
                            listaVehiculos.get(i).setMarca(vehiculo.getMarca());
                            listaVehiculos.get(i).setFecFabricacion(vehiculo.getFecFabricacion());

                        }

                    }
                    System.out.println(listaVehiculos.toString());

                    //lecturaEscrituraVehiculoXml.escribirVehiculoXml(vehiculo);
                    lecturaEscrituraVehiculoXml.escribirVeculoXml2(listaVehiculos);
                    Toast.makeText(EditarVehiculo.this, "AUTO EDITADO CORRECTAMENTE !!!", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(getApplicationContext(), "Existio un Error Usuario no ingresado", Toast.LENGTH_LONG).show();

                }
            }
        });





    }

    public void initComponents() {
        txt_placa = (TextView) findViewById(R.id.txt_placa_registro);
        txt_marca = (EditText) findViewById(R.id.txt_marca_registro);
        txt_otroColor = (EditText) findViewById(R.id.txt_color_otro_registro);
        txt_costo = (EditText) findViewById(R.id.txt_costo_registro);


        txt_fecha = (TextView) findViewById(R.id.txt_fecha_registro);

        rb1 = (RadioButton) findViewById(R.id.rb1e);
        rb2 = (RadioButton) findViewById(R.id.rb2e);

        cb1 = (CheckBox) findViewById(R.id.cb1e);
        cb2 = (CheckBox) findViewById(R.id.cb2e);
        cb3 = (CheckBox) findViewById(R.id.cb3e);


        matriculado2 = (Switch) findViewById(R.id.sw_matriculado2_registro);
        btn_registrar = (Button) findViewById(R.id.btn_registrar_edicion);
        btn_volver = (Button) findViewById(R.id.btn_volver_registro);


    }


}
