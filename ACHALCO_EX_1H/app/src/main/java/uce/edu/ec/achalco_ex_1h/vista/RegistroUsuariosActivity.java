package uce.edu.ec.achalco_ex_1h.vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import uce.edu.ec.achalco_ex_1h.R;
import uce.edu.ec.achalco_ex_1h.modelo.Usuario;

public class RegistroUsuariosActivity extends AppCompatActivity {

    private ArrayList<Usuario> usuarios;
    private EditText etxUsuario,etxPassword;
    private Button btnGuardar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuarios);
        etxUsuario = (EditText) findViewById(R.id.etxUsuario);
        etxPassword = (EditText) findViewById(R.id.etxPassword);
        btnGuardar = (Button) findViewById(R.id.btnGuardar);

        usuarios = new ArrayList<>();

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registroUsuarios();
                Toast.makeText(RegistroUsuariosActivity.this,"Registro Exitoso",Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void registroUsuarios(){
        Usuario user = new Usuario();
        user.setUsuario(etxUsuario.getText().toString().toLowerCase());
        user.setContraseña(etxPassword.getText().toString().toLowerCase());
        // usuarios.add(user);

        Intent intent = new Intent(RegistroUsuariosActivity.this,LoginActivity.class);
        Bundle bundle = new Bundle();

        bundle.putSerializable("usuario",user);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
