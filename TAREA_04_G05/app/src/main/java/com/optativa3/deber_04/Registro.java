package com.optativa3.deber_04;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.EmptyStackException;
import java.util.TreeMap;

public class Registro extends AppCompatActivity{

    Button registrar;
    EditText usuario, clave,nombre,apellido,email,celular;
    RadioGroup genero;
    RadioButton hombre,mujer;

    String fecha;
    Spinner diaS;
    Spinner mesS;
    Spinner anioS;
    CheckBox op1,op2,op3,prog1,prog2;
    Switch beca;
    Estudiante nuevo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);
        registrar = (Button) findViewById(R.id.btnregistrar);
        usuario = (EditText) findViewById(R.id.txtRusuario);
        clave = (EditText) findViewById(R.id.txtRclave);
        nombre= (EditText) findViewById(R.id.txtRnombre);
        apellido = (EditText) findViewById(R.id.txtRapellido);
        email= (EditText) findViewById(R.id.txtRemail);
        celular = (EditText) findViewById(R.id.txtRcelular);
        genero= (RadioGroup) findViewById(R.id.radioGenero);
        hombre= (RadioButton) findViewById(R.id.radioHombre);
        mujer= (RadioButton) findViewById(R.id.radioMujer);
        diaS =(Spinner) findViewById(R.id.spinnerD);
        mesS =(Spinner) findViewById(R.id.spinnerM);
        anioS =(Spinner) findViewById(R.id.spinnerA);
        op1=(CheckBox)findViewById(R.id.checkBoxop1);
        op2=(CheckBox)findViewById(R.id.checkBoxop2);
        op3=(CheckBox)findViewById(R.id.checkBoxop3);
        prog1=(CheckBox)findViewById(R.id.checkBoxprogra1);
        prog2=(CheckBox)findViewById(R.id.checkBoxprogra2);
        beca=(Switch)findViewById(R.id.switch1);

        String[] dia={"1"};
        String[] mes={"1","2","3","4","5","6","7","8","9","10","11","12"};
        String[] anio={"1992","1993","1994","1995","1996","1997","1998","1999","2000"};


        ArrayAdapter<String> adaptadorD = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,dia);
        ArrayAdapter<String> adaptadorM = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,mes);
        ArrayAdapter<String> adaptadorA = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,anio);
        diaS.setAdapter(adaptadorD);
        mesS.setAdapter(adaptadorM);
        anioS.setAdapter(adaptadorA);
        fecha= diaS.getSelectedItem().toString()+"-"+mesS.getSelectedItem().toString()+"-"+anioS.getSelectedItem().toString();

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadioButton radioButton = (RadioButton) findViewById(R.id.radioHombre);
                boolean estado = radioButton.isChecked();
                RadioButton radioButton2 = (RadioButton) findViewById(R.id.radioMujer);
                boolean estado2 = radioButton2.isChecked();
                boolean genero=false;

                if (estado==true){
                    genero=true;
                }else if (estado2==true){
                    genero=false;
                }
                String materias= op1.isChecked()+";"+ op2.isChecked()+";"+op3.isChecked()+";"+prog1.isChecked()+";"+prog2.isChecked();
                Toast.makeText(getApplicationContext(),fecha,Toast.LENGTH_LONG).show();

                nuevo = new Estudiante();
                nuevo.setUsuario(usuario.getText().toString());
                nuevo.setClave(clave.getText().toString());
                nuevo.setNombre(nombre.getText().toString());
                nuevo.setApellido(apellido.getText().toString());
                nuevo.setEmail(email.getText().toString());
                nuevo.setCelular(celular.getText().toString());
                nuevo.setGenero(genero);
                nuevo.setFecha_nacimiento(fecha);

                /*SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                try {
                    nuevo.setFecha_nacimiento(formatter.parse(fecha));
                } catch (ParseException e) {
                    e.printStackTrace();
                }*/
                nuevo.setAsignaturas(materias);
                nuevo.setBecado(beca.isChecked());
                write(nuevo);
                Intent registro = new Intent(Registro.this, LoginActivity.class);
                startActivity(registro);
            }
        });
    }


    //public void write(View view){
    public void write(Estudiante e){
        String state= Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state)){
            File Root =Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File Dir = new File(Root.getAbsolutePath()+"/OPTATIVA3/");
            if (!Dir.exists()) {
                Dir.mkdir();
            }
            try {
                File file = new File(Dir,"estudiantes.txt");
                FileOutputStream fileOutput = new FileOutputStream(file);
                fileOutput.write(e.toString().getBytes());
                fileOutput.close();
                Toast.makeText(getApplicationContext(),"REGISTRADO EXITOSAMENTE",Toast.LENGTH_LONG).show();

            }catch (Exception ex){
                Toast.makeText(getApplicationContext(),ex.getMessage()+"",Toast.LENGTH_LONG).show();

            }
        }else {
            Toast.makeText(getApplicationContext(),"Error no sd",Toast.LENGTH_LONG).show();
        }
    }


}