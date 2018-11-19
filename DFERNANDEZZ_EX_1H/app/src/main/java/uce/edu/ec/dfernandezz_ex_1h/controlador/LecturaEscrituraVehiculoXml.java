package uce.edu.ec.dfernandezz_ex_1h.controlador;

import android.util.Xml;

import org.xmlpull.v1.XmlSerializer;

import java.io.InputStream;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


public class LecturaEscrituraVehiculoXml {
    private final LecturaEscrituraArchivo manejadorArchivos = new LecturaEscrituraArchivo();





    private InputStream is;

    /**
     * escribirUsuarioXml metodo que realizara la escritura en el xml
     *
     * @param vehiculo vehiculo que va a ser escrito en el xml
     */
    public void escribirVehiculoXml(Vehiculo vehiculo) {

        ArrayList<Vehiculo> listaVehiculos = new ArrayList<>();
        listaVehiculos=lecturaVehiculosXml(); // habilitar esta linea con el xml creado
        listaVehiculos.add(vehiculo);
        //serailizador para Xml
        XmlSerializer serializer = Xml.newSerializer();
        //en string writer escribire las etiquetas xml
        StringWriter writer = new StringWriter();
        try {
            serializer.setOutput(writer);
            serializer.startDocument("UTF-8", true);
            //etiqueta usuarios<usuarios>
            serializer.startTag("", "Vehiculos");
            for (Vehiculo vehiculo1 : listaVehiculos) {

                //<Vehiculo>
                serializer.startTag("", "Vehiculo");

                //<placa>
                serializer.startTag("", "placa");
                serializer.text(vehiculo1.getPlaca());
                serializer.endTag("", "placa");
                //</placa>

                //<marca>
                serializer.startTag("", "marca");
                serializer.text(vehiculo1.getMarca());
                serializer.endTag("", "marca");
                //</marca>

                //<fecFabricacion>
                serializer.startTag("", "fecFabricacion");
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                serializer.text(formatter.format(vehiculo1.getFecFabricacion()));
                serializer.endTag("", "fecFabricacion");
                //</fecFabricacion>

                //<costo>
                serializer.startTag("", "costo");
                serializer.text(vehiculo1.getCosto().toString());
                serializer.endTag("", "costo");
                //</costo>

                //<matriculado>
                serializer.startTag("", "matriculado");
                if (vehiculo1.getMatriculado()==Boolean.TRUE){
                    serializer.text("SI");
                }else{
                    serializer.text("NO");
                }
                serializer.endTag("", "matriculado");
                //</matriculado>

                //<color>
                serializer.startTag("", "color");
                serializer.text(vehiculo1.getColor());
                serializer.endTag("", "color");
                //</color>


                //</Usuario>
                serializer.endTag("", "Vehiculo");

            }


            //</usuarios>
            serializer.endTag("", "Vehiculos");
            serializer.endDocument();

            String result = writer.toString();
            //System.out.println(result);
            manejadorArchivos.manejadorWriter("vehiculos.xml", result);
        } catch (Exception e) {
            throw new RuntimeException(e);

        }


    }


    /**
     * leerUsuarioXml Metodo que leera el archivo xml y cargara los datos en una Lista
     *
     * @see LecturaEscrituraArchivo manejadorArchivos.getInputStream() metodo que obtendra el inputStream del archivo Xml
     */
    public ArrayList<Vehiculo> lecturaVehiculosXml() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            VehiculoHandler handler = new VehiculoHandler();

            parser.parse(manejadorArchivos.getInputStream("/vehiculos.xml"), handler);

            return handler.getListaVehiculos();
        } catch (Exception iop) {
            iop.printStackTrace();
            throw new RuntimeException(iop);
        }
    }



    public void escribirVeculoXml2(ArrayList<Vehiculo> escibirVehiculos){

        ArrayList<Vehiculo> listaVehiculos = new ArrayList<>();
        listaVehiculos=escibirVehiculos; // habilitar esta linea con el xml creado
        // serailizador para Xml
        XmlSerializer serializer = Xml.newSerializer();
        //en string writer escribire las etiquetas xml
        StringWriter writer = new StringWriter();
        try {
            serializer.setOutput(writer);
            serializer.startDocument("UTF-8", true);
            //etiqueta usuarios<usuarios>
            serializer.startTag("", "Vehiculos");
            for (Vehiculo vehiculo1 : listaVehiculos) {

                //<Vehiculo>
                serializer.startTag("", "Vehiculo");

                //<placa>
                serializer.startTag("", "placa");
                serializer.text(vehiculo1.getPlaca());
                serializer.endTag("", "placa");
                //</placa>

                //<marca>
                serializer.startTag("", "marca");
                serializer.text(vehiculo1.getMarca());
                serializer.endTag("", "marca");
                //</marca>

                //<fecFabricacion>
                serializer.startTag("", "fecFabricacion");
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                serializer.text(formatter.format(vehiculo1.getFecFabricacion()));
                serializer.endTag("", "fecFabricacion");
                //</fecFabricacion>

                //<costo>
                serializer.startTag("", "costo");
                serializer.text(vehiculo1.getCosto().toString());
                serializer.endTag("", "costo");
                //</costo>

                //<matriculado>
                serializer.startTag("", "matriculado");
                if (vehiculo1.getMatriculado()==Boolean.TRUE){
                    serializer.text("SI");
                }else{
                    serializer.text("NO");
                }
                serializer.endTag("", "matriculado");
                //</matriculado>

                //<color>
                serializer.startTag("", "color");
                serializer.text(vehiculo1.getColor());
                serializer.endTag("", "color");
                //</color>


                //</Usuario>
                serializer.endTag("", "Vehiculo");

            }


            //</usuarios>
            serializer.endTag("", "Vehiculos");
            serializer.endDocument();

            String result = writer.toString();
            //System.out.println(result);
            manejadorArchivos.manejadorWriter("vehiculos.xml", result);
        } catch (Exception e) {
            throw new RuntimeException(e);

        }



    }













    /**
     * buscarVehiculo Metodo que devolvera un usuario dentro de la lista
     *
     * @param placa placa que va a ser buscada
     * @param listaVehiculos lista en la que se realizara la busqueda
     * @return resultado devolvera el vehiculo si  es que se encontro
     */
    public Vehiculo buscarVehiculo(String placa, ArrayList<Vehiculo> listaVehiculos) {
        Vehiculo resultado = null;
        for (Vehiculo est : listaVehiculos) {
            System.out.println(est.toString());
            if ((est.getPlaca().equals(placa))) {
                resultado = est;
            }
        }
        return resultado;
    }


}
