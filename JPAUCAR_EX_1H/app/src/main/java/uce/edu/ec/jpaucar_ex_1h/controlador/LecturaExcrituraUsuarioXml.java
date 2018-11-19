package uce.edu.ec.jpaucar_ex_1h.controlador;


import android.os.Environment;
import android.util.Xml;

import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import uce.edu.ec.jpaucar_ex_1h.modelo.Usuario;

//Librerias para xml SAX,DOM XMLPULLPARSER


public class LecturaExcrituraUsuarioXml {

    private final LecturaEscrituraArchivo manejadorArchivos = new LecturaEscrituraArchivo();
    private final File PATH_ROOT_ARCHIVO = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
    private final File PATH_ARCHIVO = new File(PATH_ROOT_ARCHIVO.getAbsolutePath() + "/OPTATIVA3/usuarios.xml");


    private InputStream is;

    /**
     * escribirUsuarioXml metodo que realizara la escritura en el xml
     *
     * @param usuario usuario que va a ser escrito en el xml
     */
    public void escribirUsuarioXml(Usuario usuario) {

        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        listaUsuarios=lecturaUsuariosXml();//habilitar esta linea si el archivo XML ya esta creada
        listaUsuarios.add(usuario);
        //serailizador para Xml
        XmlSerializer serializer = Xml.newSerializer();
        //en string writer escribire las etiquetas xml
        StringWriter writer = new StringWriter();
        try {
            serializer.setOutput(writer);
            serializer.startDocument("UTF-8", true);
            //etiqueta usuarios<usuarios>
            serializer.startTag("", "usuarios");
            for (Usuario usuario1 : listaUsuarios) {

                //<Usuario>
                serializer.startTag("", "Usuario");

                //<nombreUsuario>
                serializer.startTag("", "nombreUsuario");
                serializer.text(usuario1.getNombreUsuario());
                serializer.endTag("", "nombreUsuario");
                //</nombreUsuario>

                //<contrasena>
                serializer.startTag("", "contrasena");
                serializer.text(usuario1.getContrasena());
                serializer.endTag("", "contrasena");
                //</contrasena>

                //</Usuario>
                serializer.endTag("", "Usuario");

            }


            //</usuarios>
            serializer.endTag("", "usuarios");
            serializer.endDocument();

            String result = writer.toString();
            //System.out.println(result);
            manejadorArchivos.manejadorWriter("usuarios.xml", result);
        } catch (Exception e) {
            throw new RuntimeException(e);

        }


    }

    /**
     * leerUsuarioXml Metodo que leera el archivo xml y cargara los datos en una Lista
     *
     * @see LecturaEscrituraArchivo manejadorArchivos.getInputStream() metodo que obtendra el inputStream del archivo Xml
     */
    public ArrayList<Usuario> lecturaUsuariosXml() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            UsuarioHandler handler = new UsuarioHandler();

            parser.parse(manejadorArchivos.getInputStream("/usuarios.xml"), handler);

            return handler.getListaUsuarios();
        } catch (Exception iop) {
            iop.printStackTrace();
            throw new RuntimeException(iop);
        }
    }

    /**
     * buscarUsuario Metodo que devolvera un usuario dentro de la lista
     *
     * @param usuario1          Busqueda del usuario
     * @param listausuaUsuarios lista en la que se realizara la busqueda
     * @return resultado devolvera el usuario si  es que se encontro
     */
    public Usuario buscarUsuario(String usuario1, ArrayList<Usuario> listausuaUsuarios) {
        Usuario resultado = null;
        for (Usuario est : listausuaUsuarios) {
            System.out.println(est.toString());
            if ((est.getNombreUsuario().equals(usuario1))) {
                resultado = est;
            }
        }
        return resultado;
    }


}