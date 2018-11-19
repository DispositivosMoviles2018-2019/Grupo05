package uce.edu.ec.jpaucar_ex_1h.controlador;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

import uce.edu.ec.jpaucar_ex_1h.modelo.Usuario;

public class UsuarioHandler extends DefaultHandler {
    private ArrayList<Usuario> listaUsuarios;
    private Usuario usuarioActual;
    private StringBuilder sbText;
    public Boolean parsingError = false;

    public ArrayList<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }


    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        listaUsuarios = new ArrayList<>();
        sbText = new StringBuilder();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if (localName.equals("Usuario")) {
            usuarioActual = new Usuario();
        }

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        if (this.usuarioActual !=null){
            sbText.append(ch,start,length);
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if (localName.equals("nombreUsuario")){
            usuarioActual.setNombreUsuario(sbText.toString().trim());
            }else if (localName.equals("contrasena")){
            usuarioActual.setContrasena(sbText.toString().trim());
        }else if(localName.equals("Usuario")){
            listaUsuarios.add(usuarioActual);
        }
        sbText.setLength(0);


    }
}
