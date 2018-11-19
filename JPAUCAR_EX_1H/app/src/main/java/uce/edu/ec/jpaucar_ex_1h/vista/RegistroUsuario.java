package uce.edu.ec.jpaucar_ex_1h.vista;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import uce.edu.ec.jpaucar_ex_1h.controlador.LecturaExcrituraUsuarioXml;
import uce.edu.ec.jpaucar_ex_1h.modelo.Usuario;
import uce.edu.ec.myapplication.R;

public class RegistroUsuario extends AppCompatActivity {

    LecturaExcrituraUsuarioXml escritura = new LecturaExcrituraUsuarioXml();


    EditText txt_usuario;
    EditText getTxt_contrasena;
    Button btn_registrar_usuario;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_usuario);
        initComponents();
        //final LecturaEscrituraArchivo escritura = new LecturaEscrituraArchivo();

        btn_registrar_usuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Usuario u = new Usuario();
                u.setNombreUsuario(txt_usuario.getText().toString());
                u.setContrasena(txt_usuario.getText().toString());
                escritura.escribirUsuarioXml(u);
                //escritura.escribirArchivo(u);
                Toast.makeText(RegistroUsuario.this, "USUARIO: " + txt_usuario.getText().toString() + " Registrado", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(RegistroUsuario.this, Login.class);
                startActivity(intent);

            }
        });

    }

    protected void initComponents() {
        txt_usuario = (EditText) findViewById(R.id.txt_placa_registro);
        getTxt_contrasena = (EditText) findViewById(R.id.txt_marca_registro);
        btn_registrar_usuario = (Button) findViewById(R.id.btn_registrar_usuario);


    }


}
