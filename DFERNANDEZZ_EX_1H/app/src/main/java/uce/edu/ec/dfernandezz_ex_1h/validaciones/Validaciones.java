package uce.edu.ec.dfernandezz_ex_1h.validaciones;

import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validaciones {

    public static boolean validar(String expReg, String valor) {
        Pattern patron = Pattern.compile(expReg);
        Matcher encajador = patron.matcher(valor);
        return encajador.matches();
    }
    /**
     * valida el nombre y apellido
     * debe de empezar con una letra mayuscula
     * seguido de letras minusculas
     * @param c
     * @return boolean
     */
    public boolean isNombreApell(String c) {
        return validar("^[A-Z]{1,1}[a-z]{2,}", c);
    }
    /**
     * valida el numero de celular
     * debe contener 10 numeros y siempre debe de
     * empezar con 09
     * @param d
     * @return
     */
    public boolean isCelu(String d) {
        return validar("^09\\d{8,8}", d);
    }
    /**
     * validar el codigo
     * solo debe de tener numeros
     * la longitud es de 1 a 6 numeros
     * @param c
     * @return
     */
    public boolean isCodig(String c) {
        return validar("\\d{1,6}", c);
    }
    /**
     *valida el correo electronico
     * empiez con letras minusculas
     * seguido de letras mayusculas, minusculas , numeros seguida de _,-,.una o mas veces
     * debe de haber un arroba
     * seguido dominio de 4 letras hasta 10
     * y terminar en .com,.es
     * @param d
     * @return
     */
    public boolean isCorreo(String d) {
        return validar("^[a-z]+([A-Z0-9a-z])*([\\_|\\-|\\.]+)*([A-Za-z0-9])*@[a-z]{4,10}\\.(com|es)$", d);
    }
    /**
     * valida el telefono
     * debe de empezar con 02,07,04
     * debe d contener 9 numeros
     * @param a
     * @return
     */
    public boolean isTelefono(String a) {
        return validar("^([02]|[04]|[07])[0-9][0-9]{7,7}", a);
    }
    /**
     * valida la fecha año -mes-dia
     * debe ser una fecha comprendida entre 1800- 2099
     * @param a
     * @return
     */
    public boolean isFecha(String a) {
        return validar("^((1[8|9][0-9][0-9])|(20[0-9][0-9]))\\-((0[1-9])|[1-9]|(1[0|1|2]))\\-((0[1-9])|[1-9]|(1[0-9])|(2[0-9]|(30|31)))", a);
    }
    /**
     * validar una ciudad Q,C,G
     * @param a
     * @return
     */
    public boolean isCiudad(String a) {
        return validar("[Q|G|C]", a);
    }
    /**
     * valida yb genero M, F
     * @param d
     * @return
     */
    public boolean isGenero(String d) {
        return validar("[F|M]", d);
    }
    /**valida un usuario que contenga letras numeros y _
     * longitud de la cadena de 4 a 32 caracteres
     */
    public boolean isUsuario(String d) {
        //return validar("^[a-zA-Z]+([a-z0-9A-Z]*[\\_]*[a-zA-Z]*)*{4,32}", d);
        return validar("^[a-zA-Z]+([a-z0-9A-Z]*[a-zA-Z]*)", d);
    }
    /**
     * Metodo booleano que valida una contraseña Politicas : 1. Empezar con una
     * letra 2. Seguido de un numero 3. Seguido almenos dos Caracter especial 4.
     * Al final cualquier caracter 5. la longitud de la cadena debe ser minimo
     * de 4
     */
    public static boolean isContraseña(String d) {

        // return validar("^[a-zA-Z]+[0-9]+[\\W]+[\\D]+[a-zA-Z]*{4,}.*$", d);
        return validar(".{5,}",d );
    }
    /**
     * valida un numero no debe contener caracteres longitud de 1 a 10
     * @param d
     * @return
     */
    public boolean isNumero(String d) {
        return validar("[0-9]{1,10}", d);
    }
    public boolean isCosto(String d) {
        return validar("[1-9]{1,10}", d);
    }
    public boolean isPlaca(String mensaje){
        return validar("^([A-Z]{3}-\\d{3,4})(0|2|4|6|8)$",mensaje);
        //return  validar("^[A-Z]{3}-",mensaje);
    }
    public boolean isColor(String mensaje){
        return validar("^[a-zA-Z]{6,}",mensaje);
    }

}

