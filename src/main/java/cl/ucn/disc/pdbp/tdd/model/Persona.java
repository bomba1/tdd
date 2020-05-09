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
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import utils.Validation;

/**
 * Clase que representa una persona
 */
@DatabaseTable(tableName = "Persona")
public final class Persona {

    /**
     * Id de la persona
     */
    @DatabaseField(generatedId = true)
    private Long id;

    /**
     * Nombre de la persona
     */
    @DatabaseField(canBeNull = false)
    private String nombre;

    /**
     * Apellido de la persona
     */
    @DatabaseField(canBeNull = false)
    private String apellido;

    /**
     * Rut de la persona
     */
    @DatabaseField(canBeNull = false, index = true)
    private String rut;

    /**
     * Direccion de la persona
     */
    @DatabaseField(canBeNull = false)
    private String direccion;

    /**
     * Telefono fijo
     */
    @DatabaseField(canBeNull = false)
    private Integer telefonoFijo;

    /**
     * Telefono Movil
     */
    @DatabaseField(canBeNull = false)
    private Integer telefonoMovil;

    /**
     * Email
     */
    @DatabaseField(canBeNull = false)
    private String email;


    /**
     * Constructor de una persona
     * @param nombre a utilizar
     * @param apellido a utilizar
     * @param rut valido
     */
    public Persona(String nombre, String apellido, String rut, String direccion, Integer telefonoFijo, Integer telefonoMovil, String email) {

        //Verificaciones
        if (nombre == null || apellido == null || rut == null || direccion == null || telefonoFijo == null || telefonoMovil == null || email == null) {
            throw new NullPointerException("Parametro nulo");
        }

        if (nombre.length() < 2) {
            throw new IllegalArgumentException("El nombre tiene menos de 2 letras");
        }
        this.nombre = nombre;

        if (apellido.length() < 3) {
            throw new IllegalArgumentException("El apellido tiene menos de 3 letras");
        }
        this.apellido = apellido;

        Validation.validarRut2(rut);
        this.rut = rut;

        this.direccion = direccion;

        if (telefonoFijo < 100000) {
            throw new RuntimeException("Telefono no valido");
        }
        this.telefonoFijo = telefonoFijo;

        if (telefonoMovil < 10000000) {
            throw new RuntimeException("Movil no valido");
        }
        this.telefonoMovil = telefonoMovil;

        Validation.validarEmail(email);
        this.email = email;
    }

    /**
     * Funcion que retorna la id de la persona
     */
    public Long getId() {
        return id;
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
     * Retorna el nombre y apellido de la persona
     */
    public String getNombreApellido() {
        return this.nombre + " " + this.apellido;
    }

    /**
     * Retorna el rut de la persona
     */
    public String getRut() {
        return rut;
    }

    /**
     * Retorna la direccion de la persona
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Retorna el telefono fijo de la persona
     */
    public Integer getTelefonoFijo() {
        return telefonoFijo;
    }

    /**
     * Retorna el telefono movil de la persona
     */
    public Integer getTelefonoMovil() {
        return telefonoMovil;
    }

    /**
     * Retorna el email de la persona
     */
    public String getEmail() {
        return email;
    }
}
