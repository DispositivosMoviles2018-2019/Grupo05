package com.optativa3.deber_04;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.StaticLayout;
import android.view.Menu;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LoginActivity extends AppCompatActivity  implements AdapterView.OnItemClickListener {

    Button entrar,registrar;
    EditText usrA, pwdA;
    ListView lvEstudiantes;
    String usr, pwd;
    public ArrayList<Estudiante> lista = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        cargarDatos();

        /*ArrayAdapter adapter = new EstudiantesAdapter(this, lista);
        lvEstudiantes=(ListView) findViewById(R.id.lvItems);
        lvEstudiantes.setAdapter(adapter);
        lvEstudiantes.setOnItemClickListener(this);
        */


        entrar = (Button) findViewById(R.id.btnlogin);
        registrar = (Button) findViewById(R.id.btnRegistrar);
        usrA = (EditText) findViewById(R.id.txtusuario);
        pwdA = (EditText) findViewById(R.id.txtclave);



        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registro = new Intent(LoginActivity.this, Registro.class);
                startActivity(registro);
            }
        });

        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usr=usrA.getText().toString();
                pwd=pwdA.getText().toString();

                if(usr.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Ingrese el usuario",Toast.LENGTH_LONG).show();
                }else if(pwd.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Ingrese el password",Toast.LENGTH_LONG).show();
                }
                else
                {
                    for(Estudiante e: lista){
                        if((usr.equals(e.usuario) && pwd.equals(e.clave)) )
                        {
                            Toast.makeText(getApplicationContext(),"Correcto......",Toast.LENGTH_LONG).show();
                            Intent ingresar = new Intent(LoginActivity.this, Inicio.class);
                            //ingresar.putExtra("usuarioLogeado",lista);
                            ingresar.putExtra("u1",e.getUsuario());
                            ingresar.putExtra("u2",e.getClave() );
                            ingresar.putExtra("u3",e.getNombre());
                            ingresar.putExtra("u4",e.getNombre());
                            ingresar.putExtra("u5",e.getApellido());
                            ingresar.putExtra("u6",e.getAsignaturas());
                            ingresar.putExtra("u7",e.getFecha_nacimiento());
                            ingresar.putExtra("u8",e.isBecado());

                            startActivity(ingresar);
                        }
                    }
                    //usrA.setText("");
                    //pwdA.setText("");
                    //Toast.makeText(getApplicationContext(),"Usuario no registrado",Toast.LENGTH_LONG).show();
                }
            }

        });
    }

    public void cargarDatos(){

        try {

            String file = "/OPTATIVA3/"+"estudiantes.txt";

            FileInputStream archivo=new FileInputStream(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+ file);
            BufferedReader br = new BufferedReader(new InputStreamReader(archivo));
            List temp = new ArrayList();
            String linea;
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

            while((linea=br.readLine())!= null){
                temp.add(linea.split(";")[0]);
                temp.add(linea.split(";")[1]);
                temp.add(linea.split(";")[2]);
                temp.add(linea.split(";")[3]);
                temp.add(linea.split(";")[4]);
                temp.add(linea.split(";")[5]);
                temp.add(linea.split(";")[6]);
                temp.add(linea.split(";")[7]);
                temp.add(linea.split(";")[8]);
                temp.add(linea.split(";")[9]);
                temp.add(linea.split(";")[10]);
                Date a = formatter.parse(temp.get(8).toString());
                Toast.makeText(getApplicationContext(),a+" ",Toast.LENGTH_LONG).show();


                lista.add(new Estudiante(
                        temp.get(0).toString(),
                        temp.get(1).toString(),
                        temp.get(2).toString(),
                        temp.get(3).toString(),
                        temp.get(4).toString(),
                        temp.get(5).toString(),
                        temp.get(6).toString(),
                        Boolean.parseBoolean(temp.get(7).toString()),
                        //formatter.parse(temp.get(8).toString()),
                        temp.get(8).toString(),
                        temp.get(9).toString(),
                        Boolean.parseBoolean(temp.get(10).toString())
                ));
                temp.clear();

                //String usuario,
                //String clave,
                //String nombre,
                // String apellido,
                // String email,
                // String celular,
                // String idfoto,
                // boolean genero,
                // Date fecha_nacimiento,
                // String[] asignaturas,
                // boolean becado)

            }

            br.close();

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(),lista.size()+"",Toast.LENGTH_LONG).show();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        usrA.setText(lista.get(i).getNombre());
    }
}
