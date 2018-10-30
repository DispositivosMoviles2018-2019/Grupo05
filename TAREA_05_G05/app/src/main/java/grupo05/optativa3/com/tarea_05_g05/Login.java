package grupo05.optativa3.com.tarea_05_g05;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import grupo05.optativa3.com.tarea_05_g05.grupo05.optativa3.com.tarea_05_g05.Pojos.Usuario;

public class Login extends AppCompatActivity implements  PopupMenu.OnMenuItemClickListener{
    EditText txt_usuario;
    EditText txt_password;
    Button btn_login;
    Button btn_regitro;
    Button btn_usuarios;
    Usuario u;
    ArrayList<Usuario> listausuaUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txt_usuario = (EditText) findViewById(R.id.txt_usuario_login);
        txt_password = (EditText) findViewById(R.id.txt_password_login);
        btn_login = (Button) findViewById(R.id.btn_logear);
        btn_regitro = (Button) findViewById(R.id.btn_registrar_registro);
        btn_usuarios=(Button) findViewById(R.id.btn_usuarios);

        btn_regitro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(Login.this, Registro.class);

                Bundle bundle= new Bundle();
                bundle.putSerializable("cargarLista",cargarDatos());
                i1.putExtras(bundle);
                startActivity(i1);

    }
});


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarDatos();
                if(buscarUsuario(txt_usuario.getText().toString(),txt_password.getText().toString())!= null){
                    Toast.makeText(Login.this,"USUARIO ENCONTRADO", Toast.LENGTH_SHORT).show();

                    //// CARGAR AL LIST VIEEW TODO LO QUE SE DEBA CARGAR PILAS

                }else{

                    Toast.makeText(Login.this,"USUARIO NO REGISTRADO REGISTRESE ALV!!!", Toast.LENGTH_SHORT).show();

                }

            }
        });

//*********************************************MENU POPUP
        /*
        btn_usuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               PopupMenu popupMenu = new PopupMenu(Login.this,btn_usuarios);
               popupMenu.getMenuInflater().inflate(R.menu.popup, popupMenu.getMenu());

               popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                   @Override
                   public boolean onMenuItemClick(MenuItem item) {
                       Toast.makeText(Login.this,""+item.getTitle(), Toast.LENGTH_SHORT).show();
                       return true;
                   }
               });




            }
        });
*/

    }



    public ArrayList<Usuario>  cargarDatos(){

        try {

            String file = "/OPTATIVA3/"+"estudiantes.txt";
            u= new Usuario();
            FileInputStream archivo=new FileInputStream(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+ file);
            BufferedReader br = new BufferedReader(new InputStreamReader(archivo));
            listausuaUsuarios = new ArrayList<>();
            String linea;
            SimpleDateFormat sdf3 = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);

            while((linea=br.readLine())!= null){




                u.setUsuario(linea.split(";")[0]);
                u.setClave(linea.split(";")[1]);
                u.setNombre(linea.split(";")[2]);
                u.setEmail(linea.split(";")[3]);
                //genero
                u.setGenero(Boolean.parseBoolean(linea.split(";")[4]));
                //fecha
                Date a = sdf3.parse(linea.split(";")[5]);
                u.setFecha(a);


                ArrayList<String> asignaduras = new ArrayList<>();
                asignaduras.add(linea.split(";")[6]);
                u.setAsignaturas(asignaduras);
                u.setBecado(Boolean.parseBoolean(linea.split(";")[7]));

                listausuaUsuarios.add(u);
                System.out.println(listausuaUsuarios.toString());


            }

            br.close();

        } catch (Exception e) {
           // Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
            //Toast.makeText(getApplicationContext(),listausuaUsuarios.size()+"",Toast.LENGTH_LONG).show();

        }
        return listausuaUsuarios;
    }



    private Usuario buscarUsuario(String usuario1, String contrasena1) {
        //System.out.println(usuario1);
        // System.out.println(contrasena1);
        Usuario resultado = null;
        for (Usuario est : listausuaUsuarios) {
             System.out.println(est.toString());
            if ((est.getUsuario().equals(usuario1)) || (est.getClave().equals(contrasena1))) {
                  System.out.println("seeeeeeee");
                resultado = est;
            }
        }
        return resultado;
    }

    public void login(View view){
        Intent i= new Intent(this,Login.class);
    }



    public void  showPopup(View v){
        PopupMenu popup= new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.popup);
        popup.show();

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.uno:
                String alexis="alexis";
                Intent intent = new Intent(this, Menu.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra("info", alexis);
                startActivity(intent);
                Toast.makeText(this, "Usuario 1 ", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.dos:
                String deysi="deysi";
                Intent intent2 = new Intent(this, Menu.class);
                //intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent2.putExtra("info", deysi);
                startActivity(intent2);

                Toast.makeText(this, "Usuario 2", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.tres:
                String jhonathan="jhonathan";
                Intent intent3 = new Intent(this, Menu.class);
                //intent3.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent3.putExtra("info", jhonathan);
                startActivity(intent3);

                Toast.makeText(this, "Usuario 3 ", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return false;

        }
    }



}
