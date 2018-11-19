package uce.edu.ec.achalco_ex_1h.vista;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import uce.edu.ec.achalco_ex_1h.R;
import uce.edu.ec.achalco_ex_1h.modelo.Usuario;

public class LoginActivity extends AppCompatActivity {

    private Button btnIngresar;
    private EditText txtUsuario;
    private EditText txtPassword;
    private Button btnCrearCuenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(ContextCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(LoginActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
        }
        btnIngresar = (Button) findViewById(R.id.btnIngresar);
        txtUsuario = (EditText) findViewById(R.id.txtUsuario);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        btnCrearCuenta = (Button) findViewById(R.id.btnCrearCuenta);

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle objetoUsuario = getIntent().getExtras();
                Usuario user = null;
                if(objetoUsuario!=null){
                    user = (Usuario) objetoUsuario.getSerializable("usuario");

                    if((txtUsuario.getText().toString().toLowerCase().equals(user.getUsuario()))&&
                            (txtPassword.getText().toString().toLowerCase().equals(user.getContraseña()))){
                        Intent intent = new Intent(LoginActivity.this,ListaAutosActivity.class);
                        startActivity(intent);
                        Toast.makeText(LoginActivity.this, "Bienvenido usuario " + txtUsuario.getText(), Toast.LENGTH_SHORT).show();
                        txtUsuario.setText("");
                        txtPassword.setText("");
                    }else{
                        Toast.makeText(LoginActivity.this,"Usuario o Contraseña Incorrecta...",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this,"Registro de usuarios..",Toast.LENGTH_SHORT).show();
                Intent btnCrearCuenta = new Intent(LoginActivity.this,RegistroUsuariosActivity.class);
                startActivity(btnCrearCuenta);
            }
        });
    }
}
