package optativa3.com.grupo05_tarea06.administrar;

import android.os.Environment;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import optativa3.com.grupo05_tarea06.pojo.Usuario;

public class AdministrarArchivos {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");    // Date --> String  .formatter()
                                                                                // String -->Date  .parse()
    public ArrayList<Usuario> cargarDatos(){
        ArrayList<Usuario> listausuaUsuarios= new ArrayList<>();
        ArrayList<String> asignaduras = new ArrayList<>();
        Usuario u=null;
        try {
            String file = "/OPTATIVA3/"+"estudiantes.txt";
            u= new Usuario();
            FileInputStream archivo=new FileInputStream(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+ file);
            BufferedReader br = new BufferedReader(new InputStreamReader(archivo));
            listausuaUsuarios = new ArrayList<>();
            String linea;
            while((linea=br.readLine())!= null){
                u.setUsuario(linea.split(";")[0]);
                u.setClave(linea.split(";")[1]);
                u.setNombre(linea.split(";")[2]);
                //fecha
                Date a = sdf.parse(linea.split(";")[3]);
                u.setFecha(a);
                //genero   true--> casi oso; false-->Hombre
                u.setGenero(Boolean.parseBoolean(linea.split(";")[4]));
                u.setEmail(linea.split(";")[5]);
                u.setCelula(linea.split(";")[6]);
                u.setBecado(Boolean.parseBoolean(linea.split(";")[7]));
                asignaduras.add(linea.split(";")[8]);
                u.setAsignaturas(asignaduras);
                listausuaUsuarios.add(u);
                u=null;
                asignaduras.clear();
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listausuaUsuarios;
    }
 //usuario;clave;nombre;fecha;genero;email;celula;Becado;[matematicas,admins,asd]

    public void escribirArchivo(Usuario e) {
        String state = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(state)) {
            File Root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File Dir = new File(Root.getAbsolutePath() + "/OPTATIVA3/");
            if (!Dir.exists()) {
                Dir.mkdir();
            }
            try {
                File file = new File(Dir, "estudiantes.txt");
                FileOutputStream fileOutput = new FileOutputStream(file, true);
                fileOutput.write(e.toString().getBytes());
                fileOutput.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }


    public Usuario buscarUsuario(String usuario1, String contrasena1,ArrayList<Usuario> listausuaUsuarios) {
        Usuario resultado = null;
        for (Usuario est : listausuaUsuarios) {
            System.out.println(est.toString());
            if ((est.getUsuario().equals(usuario1)) || (est.getClave().equals(contrasena1))) {
                resultado = est;
            }
        }
        return resultado;
    }

}
