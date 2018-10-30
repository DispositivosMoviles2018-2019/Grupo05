package grupo05.optativa3.com.tarea_05_g05;


import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.SQLOutput;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

import grupo05.optativa3.com.tarea_05_g05.grupo05.optativa3.com.tarea_05_g05.ManejoArchivos.LecturaEscritura;
import grupo05.optativa3.com.tarea_05_g05.grupo05.optativa3.com.tarea_05_g05.Pojos.Usuario;

public class Registro extends AppCompatActivity {
    private static final String TAG = "Registro";

    Usuario usu;
    ArrayList<Usuario> listaUsuario;
    ArrayList<String> listaAsignaturas;

    EditText txt_usuario;
    EditText txt_password;
    EditText txt_nombre;
    EditText txt_email;
    EditText txt_cedula;
    TextView displayFecha;

    DatePickerDialog.OnDateSetListener listenerFecha;

    RadioButton rb1, rb2;
    CheckBox cb1, cb2, cb3, cb4, cb5;
    Switch becado;

    Button btn_registrar;
    Button btn_volver;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        txt_usuario = (EditText) findViewById(R.id.txt_usuario_registro);
        txt_password = (EditText) findViewById(R.id.txt_contrasena_registro);
        txt_nombre = (EditText) findViewById(R.id.txt_nombre_registro);
        txt_email = (EditText) findViewById(R.id.txt_email_registro);
        txt_cedula = (EditText) findViewById(R.id.txt_cedula_registro);

        displayFecha = (TextView) findViewById(R.id.txt_fecha);

        rb1 = (RadioButton) findViewById(R.id.rb1);
        rb2 = (RadioButton) findViewById(R.id.rb2);

        cb1 = (CheckBox) findViewById(R.id.cb1);
        cb2 = (CheckBox) findViewById(R.id.cb2);
        cb3 = (CheckBox) findViewById(R.id.cb3);
        cb4 = (CheckBox) findViewById(R.id.cb4);
        cb5 = (CheckBox) findViewById(R.id.cb5);

        becado = (Switch) findViewById(R.id.sw_becado);
        btn_registrar = (Button) findViewById(R.id.btn_registrar_registro);

        listaUsuario = new ArrayList<>();
        listaAsignaturas = new ArrayList<>();
/*
        Bundle objetoRecibido=getIntent().getExtras();

        //if (objetoRecibido!=null){
            ArrayList<Usuario> usuariotemporal;
            usuariotemporal=(ArrayList<Usuario>) objetoRecibido.getSerializable("cargarLista");
            listaUsuario=(ArrayList<Usuario>)usuariotemporal.clone();
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*"+listaUsuario.toString());
       // }

*/
//MANEJO FECHA CON POP UP MENU
        displayFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int anio = cal.get(Calendar.YEAR);
                int mes = cal.get(Calendar.MONTH);
                int dia = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Registro.this,
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
                Log.d(TAG, "onDateSet: date" + month + "/" + day + "/" + year);
                String date = month + "/" + day + "/" + year;
                displayFecha.setText(date);
            }
        };

//manejo de la creacion de usuario boton registrar

        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean validacion = true;

               usu = new Usuario();

               boolean genero = rb1.isChecked();
                usu.setUsuario(txt_usuario.getText().toString());
                usu.setClave(txt_password.getText().toString());
                usu.setNombre(txt_nombre.getText().toString());

                //validacion Email
                if (!validarEmail(txt_email.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Email no válido", Toast.LENGTH_SHORT).show();
                    validacion=false;
                    //miEditText.setError("Email no válido")
                }else{
                    usu.setEmail(txt_email.getText().toString());
                }


                usu.setClave(txt_cedula.getText().toString());

                //seteo del genero
                if (genero == true) {
                    usu.setGenero(genero);
                } else {
                    usu.setGenero(false);
                }

                if(displayFecha.getText().toString()!=null){
                try {
                    String fecha= displayFecha.getText().toString();
                    //Calendar cal = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                   // cal.setTime(sdf.parse(fecha));// all done
                    usu.setFecha(sdf.parse(fecha));
                } catch (Exception e) {
                    e.printStackTrace();
                }}else{
                    Toast.makeText(getApplicationContext(),"No es posible dejar campo fecha vacio",Toast.LENGTH_LONG).show();
                    validacion=false;
                }

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

                //seteo del genero
                if (becado.isChecked() == true) {
                    usu.setBecado(true);
                } else {
                    usu.setBecado(false);
                }


                //listaUsuario.add(usu);
                //System.out.println(listaUsuario.toString());
                // lecturaEscritura.write(usu);

                if (validacion==true){
                write(usu);}else{
                    Toast.makeText(getApplicationContext(),"Existio un Error Usuario no ingresado",Toast.LENGTH_LONG).show();

                }

            }
        });


    }

////-------------------------------------------------------->MANEJO DE ARCHIVOS<--------------------------------------------------------------------------------------
    public void write(Usuario e) {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            File Root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File Dir = new File(Root.getAbsolutePath() + "/OPTATIVA3/");
            if (!Dir.exists()) {
                Dir.mkdir();
            }
            try {
                File file = new File(Dir, "estudiantes.txt");
                FileOutputStream fileOutput = new FileOutputStream(file,true);
               // String listString = String.join(";",e.toString());
                fileOutput.write(e.toString().getBytes());

                fileOutput.close();
                Toast.makeText(getApplicationContext(), "REGISTRADO EXITOSAMENTE", Toast.LENGTH_LONG).show();

            } catch (Exception ex) {
                ex.printStackTrace();
                //Toast.makeText(getApplicationContext(),ex.getMessage()+"",Toast.LENGTH_LONG).show();

            }
        } else {
            Toast.makeText(getApplicationContext(), "Error no sd", Toast.LENGTH_LONG).show();
        }
    }
////////////////////////************ validacion
private boolean validarEmail(String email) {
    Pattern pattern = Patterns.EMAIL_ADDRESS;
    return pattern.matcher(email).matches();
}

}

