package optativa3.com.grupo05_tarea06;

import android.app.DatePickerDialog;
import android.content.Intent;
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
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import optativa3.com.grupo05_tarea06.administrar.AdministrarArchivos;
import optativa3.com.grupo05_tarea06.pojo.Usuario;

public class Registro extends AppCompatActivity {

    AdministrarArchivos ad = new AdministrarArchivos();
    ArrayList<Usuario> listaUsuario=new ArrayList<>();
    ArrayList<String> listaAsignaturas= new ArrayList<>();
    EditText txt_usuario;
    EditText txt_password;
    EditText txt_nombre;
    EditText txt_email;
    EditText txt_cedula;
    TextView displayFecha;
    DatePickerDialog.OnDateSetListener listenerFecha;
    RadioButton rb1, rb2;
    RadioGroup rbG;
    CheckBox cb1, cb2, cb3, cb4, cb5;
    Switch becado;
    Button btn_registrar,btn_volver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);

        btn_registrar=(Button) findViewById(R.id.btn_registrar_registro);
        btn_volver=(Button) findViewById(R.id.btn_volver);

        displayFecha = (TextView) findViewById(R.id.txt_fecha);
        txt_usuario = (EditText) findViewById(R.id.txt_usuario_registro);
        txt_password = (EditText) findViewById(R.id.txt_contrasena_registro);
        txt_nombre = (EditText) findViewById(R.id.txt_nombre_registro);
        txt_email = (EditText) findViewById(R.id.txt_email_registro);
        txt_cedula = (EditText) findViewById(R.id.txt_cedula_registro);
        rbG = (RadioGroup) findViewById(R.id.radioGroup);
        rb1 = (RadioButton) findViewById(R.id.rb1);
        rb2 = (RadioButton) findViewById(R.id.rb2);
        cb1 = (CheckBox) findViewById(R.id.cb1);
        cb2 = (CheckBox) findViewById(R.id.cb2);
        cb3 = (CheckBox) findViewById(R.id.cb3);
        cb4 = (CheckBox) findViewById(R.id.cb4);
        cb5 = (CheckBox) findViewById(R.id.cb5);
        becado = (Switch) findViewById(R.id.sw_becado);


        //MANEJO FECHA CON POP UP MENU
        displayFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                DatePickerDialog dialog = new DatePickerDialog(Registro.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        listenerFecha,cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
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
                String date = day + "/" + month + "/" + year;
                displayFecha.setText(date);
            }
        };


        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Usuario usu = new Usuario();

                    usu.setUsuario(txt_usuario.getText().toString());
                    usu.setClave(txt_password.getText().toString());
                    usu.setNombre(txt_nombre.getText().toString());
                    usu.setEmail(txt_email.getText().toString());
                    usu.setCelula(txt_cedula.getText().toString());
                    usu.setGenero(rb1.isChecked());
                    boolean genero=true;
                    if (rbG.getCheckedRadioButtonId()!=rb1.getId()){
                        genero=false;
                    }
                    usu.setGenero(genero);
                    String fecha = displayFecha.getText().toString();
                    //Calendar cal = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    // cal.setTime(sdf.parse(fecha));// all done
                    usu.setFecha(sdf.parse(fecha));
                    //Manejo de los checkbox
                    //Seteo de las asignaturas
                    if (cb1.isChecked() == true) {
                        listaAsignaturas.add(cb1.getText().toString());
                    }
                    if (cb2.isChecked() == true) {
                        listaAsignaturas.add(cb2.getText().toString());
                    }
                    if (cb3.isChecked() == true) {
                        listaAsignaturas.add(cb3.getText().toString());
                    }
                    if (cb4.isChecked() == true) {
                        listaAsignaturas.add(cb4.getText().toString());
                    }
                    if (cb5.isChecked() == true) {
                        listaAsignaturas.add(cb5.getText().toString());
                    }
                    usu.setAsignaturas(listaAsignaturas);
                        usu.setBecado(becado.isChecked());
                    ad.escribirArchivo(usu);
                    Intent registro = new Intent(Registro.this, Login.class);
                    startActivity(registro);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btn_volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registro = new Intent(Registro.this, Login.class);
                startActivity(registro);
            }
        });


    }

}
