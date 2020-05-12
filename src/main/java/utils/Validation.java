/*
 * MIT License
 *
 * Copyright (c) 2020 Pablo Salas Olivares <pablo.salas@ucn.cl>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.regex.Pattern;

public class Validation {

    /**
     * Log para enviar mensajes
     */
    private static final Logger log = LoggerFactory.getLogger(Validation.class);

    /**
     * Constructor de validation
     */
    public Validation() {

    }

    /**
     * Metodo para validar rut, sacado de: https://www.qualityinfosolutions.com/validador-de-rut-chileno-en-java/
     * @param rut de la persona
     */
    public static boolean validarRut(String rut) {

        boolean validacion = false;
        if (rut == null){
            return false;
        }
        try {
            //Se quitan los puntos y guiones si esque viene con ellos el String
            rut =  rut.toUpperCase();
            rut = rut.replace(".", "");
            rut = rut.replace("-", "");
            int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));

            char dv = rut.charAt(rut.length() - 1);

            //Aqui se verifica el algoritmo, multiplicando sus partes y aplicando modulo 11(como el algoritmo para verificar digito final)
            int m = 0, s = 1;
            for (; rutAux != 0; rutAux /= 10) {
                s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
            }

            //Si el valor entregado en s es igual al esperado por el algoritmo, la validacion sera verdadera y el rut valido
            if (dv == (char) (s != 0 ? s + 47 : 75)) {
                validacion = true;
            }

        } catch (java.lang.NumberFormatException e) {
        } catch (Exception e) {
        }
        return validacion;
    }

    /**
     * Metodo para validar rut con exception, proveniente de : https://www.qualityinfosolutions.com/validador-de-rut-chileno-en-java/
     * @param rut
     */
    public static void validarRut2(String rut) {

        boolean validacion = false;

        try {
            //Se quitan los puntos y guiones del Sring
            rut =  rut.toUpperCase();
            rut = rut.replace(".", "");
            rut = rut.replace("-", "");
            int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));

            char dv = rut.charAt(rut.length() - 1);

            //Aqui se realiza el algoritmo para saber que numero es el verificador
            int m = 0, s = 1;
            for (; rutAux != 0; rutAux /= 10) {
                s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
            }

            //Si el numero es el esperado, pasa la prueba
            if (dv == (char) (s != 0 ? s + 47 : 75)) {
                validacion = true;
            }

        } catch (java.lang.NumberFormatException e) {
        } catch (Exception e) {
        }
        if (validacion) {
            throw new RuntimeException("Rut no valido");
        } else {
            log.debug("Rut valido");
        }

    }

    /**
     * Metodo para validar el email de una persona
     * @param email de la persona
     */
    public static boolean validarEmail(String email) {
        //Expresion regular sacada desde https://howtodoinjava.com/regex/java-regex-validate-email-address/
        String REGEX = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

        //Ejecuta la expresion regular
        Pattern PATTERN = Pattern.compile(REGEX);

        return PATTERN.matcher(email).find();

    }

    /**
     * @param rut a validar.
     * @return true si el rut es valido
     */
    public static boolean esValido(String rut) {

        // No nulo
        if (rut == null) {
            return false;
        }

        // Si el tamaño menor a 2
        if (rut.length() < 2) {
            return false;
        }

        // Ultimo caracter
        char dv = rut.charAt(rut.length() - 1);

        // Se ve si son solo numeros
        String numbers = rut.substring(0, rut.length() - 1);
        if (!Pattern.matches("[0-9]+", numbers)) {
            return false;
        }

        // Validacion
        return validarRut(Integer.parseInt(numbers), dv);

    }

    /**
     * Validador de rut de la fuente : http://www.creations.cl/2009/01/generador-de-rut-y-validador/
     *
     * @return true si el rut es valido
     */
    private static boolean validarRut(int rut, char dv) {
        int m = 0, s = 1;
        for (; rut != 0; rut /= 10) {
            s = (s + rut % 10 * (9 - m++ % 6)) % 11;
        }
        return dv == (char) (s != 0 ? s + 47 : 75);
    }

}
