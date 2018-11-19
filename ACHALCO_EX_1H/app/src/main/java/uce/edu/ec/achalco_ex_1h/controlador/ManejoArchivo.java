package uce.edu.ec.achalco_ex_1h.controlador;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import uce.edu.ec.achalco_ex_1h.modelo.Vehiculo;

public class ManejoArchivo implements Serializable {
    private ArrayList<String> listadoDatos = new ArrayList<String>();
    private ArrayList<Vehiculo> listadoBean = new ArrayList<Vehiculo>();
    private Context context;
    private FileReader fr=null;

    public ManejoArchivo() {

    }

    public ArrayList<Vehiculo> getListadoBean() {
        return listadoBean;
    }

    public void setListadoBean(ArrayList<Vehiculo> listadoBean) {
        this.listadoBean = listadoBean;
    }

    public ArrayList<String> getListadoDatos() {
        return listadoDatos;
    }

    public void setListadoDatos(ArrayList<String> listadoDatos) {
        this.listadoDatos = listadoDatos;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void escribirJSON(String nombreArchivo,ArrayList<Vehiculo> vehiculos) {
        Gson gson = new Gson();
        String jsonString;
        Vehiculo vehiculo = new Vehiculo();

        try {

            //File tarjeta = Environment.getExternalStorageDirectory();
            File tarjeta = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File file = new File(tarjeta.getAbsolutePath(), nombreArchivo);
            OutputStreamWriter fout = new OutputStreamWriter(new FileOutputStream(file));

            //fout = new OutputStreamWriter(context.getApplicationContext().openFileOutput(Environment.getExternalStorageDirectory()+"/"+nombre_archivo, Activity.MODE_WORLD_WRITEABLE));
            fout.write("{\"TotalV\":" + vehiculos.size() + ",\n\r");

            for (int i = 0; i < vehiculos.size(); i++) {
                jsonString = "\"Vehiculo " + (i + 1) + "\": ";

                vehiculo = vehiculos.get(i);

                jsonString += gson.toJson(vehiculo);

                Log.e("FILE", jsonString.toString());

                if (i < vehiculos.size() - 1)
                    jsonString += ",\n\r";
                fout.write(jsonString);
                Log.e("JSON: " + (i + 1), jsonString);
            }
            fout.write("}\n\r");
            fout.close();
        } catch (Exception e) {
            Toast mensaje = Toast.makeText(context.getApplicationContext(), "Error grabando archivo " + e.toString(), Toast.LENGTH_LONG);
            mensaje.show();
        }
    }
        public ArrayList<String> leerJSON(Context context,String nombreArchivo){
            this.context = context;  //guardo el contexto de la aplicacion

            Vehiculo vehiculo;

            //verificaArchivo(nombre_archivo);

            try {
                //File tarjeta = Environment.getExternalStorageDirectory();
                File tarjeta = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                fr = new FileReader( new File(tarjeta.getAbsolutePath(), nombreArchivo) );
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error al abrir fichero de memoria interna");
                //Toast mensaje = Toast.makeText(context.getApplicationContext(), "Error al abrir fichero de memoria interna "+e.toString(), Toast.LENGTH_LONG);
                //mensaje.show();
                return null;
            }

            try {
                String tot="", cadena;
                BufferedReader b = new BufferedReader(fr);

                //leemos el contenido del archivo
                while((cadena = b.readLine())!=null) {
                    System.out.println(cadena);
                    tot += cadena;
                    tot += "\n";
                }
                b.close();

                JSONObject reader = new JSONObject(tot);

                int cont = 1;
                double valorVehiculo=0;
                boolean matriculado;
                String data, item;

                int total = (int)reader.getInt("TotalV");
                Toast mensaje = Toast.makeText(context, "Total vehiculos: "+total, Toast.LENGTH_LONG);
                mensaje.show();

                SimpleDateFormat formateador = new SimpleDateFormat("yyyy/MM/dd");

                for (int i=0; i<total; i++) {
                    item = "";
                    JSONObject innerObject = (JSONObject) reader.get("Vehiculo " + cont);
                    vehiculo = new Vehiculo();

                    item = "Vehiculo ";

                    data = (String) innerObject.get("placa");
                    vehiculo.setPlaca(data);

                    data = (String) innerObject.get("marca");
                    vehiculo.setMarca(data);

                    data = (String) innerObject.get("fecFabricacion");
                    Date date = new Date(data);
                    vehiculo.setFecFabricacion(date);

                    valorVehiculo = (double) innerObject.getDouble("costo");
                    vehiculo.setCosto(valorVehiculo);

                    matriculado = (boolean) innerObject.getBoolean("matriculado");
                    vehiculo.setMatriculado(matriculado);

                    data = (String) innerObject.get("color");
                    vehiculo.setColor(data);

                    item += "\n\r" + vehiculo.toString() + "\n\r";

                    listadoDatos.add(item);
                    listadoBean.add(vehiculo);
                    cont ++;
                }
            } catch (Exception e) {
                System.out.println(e.toString());
                Toast mensaje = Toast.makeText(context, "error leyendo json: "+e.toString(), Toast.LENGTH_LONG);
                mensaje.show();
            }

            return listadoDatos;
        }
}
