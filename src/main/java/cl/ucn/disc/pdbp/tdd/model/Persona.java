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

/**
 * Clase que representa una persona
 */
public final class Persona {

    /**
     * Nombre de la persona
     */
    private String nombre;

    /**
     * Apellido de la persona
     */
    private String apellido;

    /**
     * Rut de la persona
     */
    private String rutOk;




    /**
     * Constructor de una persona
     * @param nombre a utilizar
     * @param apellido a utilizar
     * @param rut valido
     */
    public Persona(String nombre, String apellido, String rut) {
        Validation validar = new Validation ();

        //Verificaciones
        validar.verificarNulidad(nombre,apellido,rut);

        validar.verificarTamanioNombre(nombre);
        this.nombre = nombre;

        validar.verificarTamanioApellido(apellido);
        this.apellido = apellido;

        validar.validarRut2(rutOk);
        this.rutOk = rut;


    }

    /**
     * Funcion que retorna el nombre de la persona
     */
    public String getNombre(){
        return this.nombre;
    }

    /**
     * Funcion que entrega el apellido de la persona
     */
    public String getApellido(){
        return this.apellido;
    }

    /**
     * Retorna el nombre y apellido de la perosna
     */
    public String getNombreApellido() {
        return this.nombre + " " + this.apellido;
    }
}
