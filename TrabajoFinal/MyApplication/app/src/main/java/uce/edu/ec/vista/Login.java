package uce.edu.ec.vista;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.util.ArrayList;


import uce.edu.ec.controlador.LecturaExcrituraUsuarioXml;
import uce.edu.ec.modelo.Usuario;
import uce.edu.ec.myapplication.R;

public class Login extends AppCompatActivity {
    EditText txt_usuario;
    EditText txt_contrasena;

    Button btn_logear;
    Button btn_registrar;

    LecturaExcrituraUsuarioXml lectura = new LecturaExcrituraUsuarioXml();


    ArrayList<Usuario> usuarios = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initComponentes();

        btn_logear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                try {
                    /*
                    System.out.println("***********************Handler*****************");
                    System.out.println(lectura.lecturaUsuariosXml().toString());
                    usuarios = lectura.lecturaUsuariosXml();
                    System.out.println("***********************Lista*****************");
                    System.out.println(usuarios.toString());
                    */
                    if (lectura.buscarUsuario((txt_usuario.getText().toString()), usuarios) != null) {
                        int intentos=3;
                        //while (){}
                         // if() usuarios
                        Toast.makeText(Login.this, "Bievenido Usuario:  " + txt_usuario.getText().toString(), Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Login.this, ListaVehiculos.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(Login.this, "USUARIO NO REGISTRADO REGISTRESE PORVAVOR!!!", Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {
                    Toast.makeText(Login.this, "NO EXISTE NINGUN USUARIO EN EL SISTEMA REGISTRE UNO !!", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }


            }
        });

        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, RegistroUsuario.class);
                startActivity(intent);
            }
        });

    }


    public void initComponentes() {

        txt_usuario = (EditText) findViewById(R.id.txt_usuario_login);
        txt_contrasena = (EditText) findViewById(R.id.txt_contrasena_login);
        btn_logear = (Button) findViewById(R.id.btn_logear);
        btn_registrar = (Button) findViewById(R.id.btn_registrarse_login);


    }


}
