package com.optativa3.deber_04;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

public class Inicio extends AppCompatActivity {

    EditText usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio);
        Bundle bdl = getIntent().getExtras();
        String usuarioL = bdl.get("u1").toString();
        String claveL = bdl.get("u2").toString();
        String nombreL = bdl.get("u3").toString();
        String asignaturasL = bdl.get("u6").toString();
        String fechaL = bdl.get("u7").toString();
        String becaL = bdl.get("u8").toString();


 //       TextView nom= findViewById(R.id.textViewNombre);
  //      nom.append(nombreL);
    }

}
