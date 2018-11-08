package optativa3.com.grupo05_tarea06;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import optativa3.com.grupo05_tarea06.administrar.AdministrarArchivos;
import optativa3.com.grupo05_tarea06.pojo.Usuario;

public class Login extends AppCompatActivity {

    Button btn_login,btn_registrar;
    EditText txt_usuario;
    EditText txt_password;
    AdministrarArchivos ad=new AdministrarArchivos();
    ArrayList<Usuario> listaUsuarios=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        btn_login=(Button) findViewById(R.id.btn_login);
        btn_registrar=(Button) findViewById(R.id.btn_registrar);
        txt_usuario = (EditText) findViewById(R.id.txt_usuario);
        txt_password = (EditText) findViewById(R.id.txt_password);
        listaUsuarios=ad.cargarDatos();
        Toast.makeText(Login.this,"Se cargaron"+listaUsuarios.size()+" usuarios", Toast.LENGTH_SHORT).show();
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Usuario a=ad.buscarUsuario(txt_usuario.getText().toString()
                        ,txt_password.getText().toString()
                        ,listaUsuarios);
                if (a!=null) {
                    Intent login = new Intent(Login.this, Registrados.class);
                    startActivity(login);
                }else{
                    Toast.makeText(Login.this,"Credenciales erroneas", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registro = new Intent(Login.this, Registro.class);
                startActivity(registro);
            }
        });
    }
}
