/*
 * MIT License
 *
 * Copyright (c) 2020 Diego Urrutia-Astorga <durrutia@ucn.cl>.
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

package cl.ucn.disc.pdbp.tdd.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
     * Metodo para verificar si un campo es nulo
     * @param nombre de la persona
     * @param apellido de la persona
     * @param rutOk de la persona
     */
    public void verificarNulidad(String nombre, String apellido, String rutOk) {
        if (nombre == null || apellido == null || rutOk == null) {
            throw new NullPointerException("Parametro nulo");
        }
    }

    /**
     * Metodo que verifica el tamaño de un nombre
     * @param nombre de la persona
     */
    public void verificarTamanioNombre(String nombre) {
        int cantidadLetras = nombre.length();
        if (cantidadLetras > 1) {
            log.debug("Nombre correcto");
        } else {
            throw new IllegalArgumentException("El nombre tiene menos de 2 letras");
        }
    }

    /**
     * Metodo que verifica el tamaño del apellido
     * @param apellido de la persona
     */
    public void verificarTamanioApellido(String apellido) {
        int cantidadLetras = apellido.length();
        if (cantidadLetras > 2) {
            log.debug("Apellido valido");
        } else {
            throw new IllegalArgumentException("El apellido tiene menos de 3 letras");
        }
    }

    /**
     * Metodo para validar rut
     * @param rut
     * @return
     */
    public static boolean validarRut(String rut) {

        boolean validacion = false;
        if (rut == null){
            return false;
        }
        try {
            rut =  rut.toUpperCase();
            rut = rut.replace(".", "");
            rut = rut.replace("-", "");
            int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));

            char dv = rut.charAt(rut.length() - 1);

            int m = 0, s = 1;
            for (; rutAux != 0; rutAux /= 10) {
                s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
            }
            if (dv == (char) (s != 0 ? s + 47 : 75)) {
                validacion = true;
            }

        } catch (java.lang.NumberFormatException e) {
        } catch (Exception e) {
        }
        return validacion;
    }

    /**
     * Metodo para validar rut con exception
     * @param rut
     */
    public void validarRut2(String rut) {

        boolean validacion = false;

        try {
            rut =  rut.toUpperCase();
            rut = rut.replace(".", "");
            rut = rut.replace("-", "");
            int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));

            char dv = rut.charAt(rut.length() - 1);

            int m = 0, s = 1;
            for (; rutAux != 0; rutAux /= 10) {
                s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
            }
            if (dv == (char) (s != 0 ? s + 47 : 75)) {
                validacion = true;
            }

        } catch (java.lang.NumberFormatException e) {
        } catch (Exception e) {
        }
        if (validacion == false) {
            throw new RuntimeException("Rut no valido");
        } else {
            log.debug("Rut valido");
        }

    }

}
