package uce.edu.ec.dfernandezz_ex_1h.controlador;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class VehiculoHandler extends DefaultHandler {
    private ArrayList<Vehiculo> listaVehiculos;
    private Vehiculo vehiculoActual;
    private StringBuilder sbText;
    public Boolean parsingError = false;

    public ArrayList<Vehiculo> getListaVehiculos() {
        return listaVehiculos;
    }


    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        listaVehiculos = new ArrayList<Vehiculo>();
        sbText = new StringBuilder();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if (localName.equals("Vehiculo")) {
            vehiculoActual = new Vehiculo();
        }

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        if (this.vehiculoActual != null) {
            sbText.append(ch, start, length);
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        double costo;
        String matriculado;
        if (localName.equals("placa")) {
            vehiculoActual.setPlaca(sbText.toString().trim());
        } else if (localName.equals("marca")) {
            vehiculoActual.setMarca(sbText.toString().trim());
        } else if (localName.equals("fecFabricacion")) {

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = formatter.parse(sbText.toString().trim());
                //formatter.format(date);
                vehiculoActual.setFecFabricacion(date);

            } catch (ParseException e) {
                parsingError = true;
                e.printStackTrace();
            }
        } else if (localName.equals("costo")) {
            costo = Double.parseDouble(sbText.toString().trim());
            vehiculoActual.setCosto(costo);
        } else if (localName.equals("matriculado")) {
            matriculado = sbText.toString().trim();
            if (matriculado.equalsIgnoreCase("SI")) {
                vehiculoActual.setMatriculado(Boolean.TRUE);
            } else if (matriculado.equalsIgnoreCase("NO")) {
                vehiculoActual.setMatriculado(Boolean.FALSE);
            } else {
                parsingError = true;
            }
        } else if (localName.equals("color")) {
            vehiculoActual.setColor(sbText.toString().trim());
        } else if (localName.equals("Vehiculo")) {
            listaVehiculos.add(vehiculoActual);
        }
        sbText.setLength(0);
    }

}



