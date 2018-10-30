package grupo05.optativa3.com.tarea03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class IniciarSesion extends AppCompatActivity {
    Button btnenviar;
    EditText nombre1;
    EditText password1;

    private static final String NOMBRE_USER_ADMIN = "admin";
    private static final String PASSWORD_USER_ADMIN= "admin";

    private static final String NOMBRE_USER_ALEXI = "alexis";
    private static final String PASSWORD_USER_ALEXI = "chalco";

    private static final String NOMBRE_USER_DF = "deysi";
    private static final String PASSWORD_USER_DF = "fernandez";

    private static final String NOMBRE_USER_JP = "jhonathan";
    private static final String PASSWORD_USER_JP = "paucar";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);
        nombre1 = (EditText) findViewById(R.id.txt_nombre1);
        password1 = (EditText) findViewById(R.id.txt_contraseña);
        btnenviar = (Button) findViewById(R.id.btn_enviar);

        btnenviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((nombre1.getText().toString().equals(NOMBRE_USER_ADMIN) && password1.getText().toString().equals(PASSWORD_USER_ADMIN)) ||
                        ((nombre1.getText().toString().equals(NOMBRE_USER_ALEXI) && password1.getText().toString().equals(PASSWORD_USER_ALEXI))) ||
                        ((nombre1.getText().toString().equals(NOMBRE_USER_DF) && password1.getText().toString().equals(PASSWORD_USER_DF))) ||
                        ((nombre1.getText().toString().equals(NOMBRE_USER_JP) && password1.getText().toString().equals(PASSWORD_USER_JP)))){

                    Intent i = new Intent(IniciarSesion.this, Datos.class);
                    i.putExtra("info", nombre1.getText().toString());
                    startActivity(i);

                } else{
                    Toast.makeText(getApplicationContext(), "USUARIO O CONTRASEÑA INCORRECTOS", Toast.LENGTH_SHORT).show();


                }


            }
        });
    }
}
