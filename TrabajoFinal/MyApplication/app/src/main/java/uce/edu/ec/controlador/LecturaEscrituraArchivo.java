package uce.edu.ec.controlador;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import uce.edu.ec.modelo.Usuario;
import uce.edu.ec.modelo.Vehiculo;


public class LecturaEscrituraArchivo {

    private final File PATH_ROOT_ARCHIVO= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
    private final File PATH_ARCHIVO = new File(PATH_ROOT_ARCHIVO.getAbsolutePath() + "/OPTATIVA3/");
    private InputStream is;

    /**
     * manejadorWriter metodo de apoyo para escribir el Xml
     * @param fileName nombre del archivo que se escribira ejemplo usuarios.xml
     * @param strXml Parametro que se escribira
     */
    public void manejadorWriter(String fileName,String strXml) {
        if (!PATH_ARCHIVO.exists()) {
            PATH_ARCHIVO.mkdir();
        }
        try {
            File file = new File(PATH_ARCHIVO, fileName);
            FileOutputStream fileOutput = new FileOutputStream(file);
            fileOutput.write(strXml.getBytes(),0,strXml.length());
            fileOutput.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     *
     * @param archivo nombre del archivo que a a ser leido por ejemplo /usuarios.xml
     * @return retorna un imput stream nececsario para la lectura
     */

    public InputStream getInputStream(String archivo) {
        try {
             File ARCHIVO_LECTURA =new File(PATH_ARCHIVO+archivo);
            is = new FileInputStream(ARCHIVO_LECTURA);

            return is;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }





}