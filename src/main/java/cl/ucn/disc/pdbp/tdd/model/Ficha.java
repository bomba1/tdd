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

package cl.ucn.disc.pdbp.tdd.model;
import cl.ucn.disc.pdbp.tdd.dao.ZonedDateTimeType;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *Ficha Veterinaria
 */
public final class Ficha {
    /**
     * Id de la ficha
     */
    @DatabaseField(generatedId = true)
    private Long id;
    /**
     * Numero de ficha
     */
    @DatabaseField(unique = true)
    private Integer numero;

    /**
     * Fecha de nacimiento
     */
    @DatabaseField(persisterClass = ZonedDateTimeType.class)
    private ZonedDateTime fechaNacimiento;

    /**
     * Nombre del paciente
     */
    @DatabaseField
    private String nombrePaciente;

    /**
     * Especie del paciente
     */
    @DatabaseField
    private String especie;

    /**
     * Raza del paciente
     */
    @DatabaseField
    private String raza;

    /**
     * Sexo del paciente
     */
    @DatabaseField
    private Sexo sexo;

    /**
     * Color del paciente
     */
    @DatabaseField
    private String color;

    /**
     * Tipo del paciente
     */
    @DatabaseField
    private Tipo tipo;

    /**
     * El dueio de la mascota
     */
    @DatabaseField(foreign = true, canBeNull = true, foreignAutoRefresh = true)
    private Persona duenio;

    /**
     * Lista de controles
     */
    //@DatabaseField(foreign = true, canBeNull = false, foreignAutoRefresh = true)
    @ForeignCollectionField()
    private ForeignCollection<Control> controles;

    /**
     * Constructor vacio de Ficha
     */
    Ficha () {

    }
    /**
     * Constructor de Ficha
     * @param numero           de la mascota
     * @param fechaNacimiento  de la mascota
     * @param nombrePaciente   de la mascota
     * @param especie          del paciente
     * @param raza             de la mascota
     * @param sexo             de la mascota
     * @param color            de la mascota
     * @param tipo             de la mascota
     */
    public Ficha(Integer numero, ZonedDateTime fechaNacimiento, String nombrePaciente, String especie, String raza, Sexo sexo, String color, Tipo tipo, Persona duenio) {
        this.numero = numero;

        if (fechaNacimiento.isAfter(ZonedDateTime.now())) {
            throw new IllegalArgumentException("La fecha entregada es despues del dia actual...");
        }
        this.fechaNacimiento = fechaNacimiento;

        if (nombrePaciente.length() <= 2) {
            throw new IllegalArgumentException("El nombre tiene menos de 3 letras");
        }

        if (nombrePaciente.equals("")) {
            throw new IllegalArgumentException("El nombre es vacio");
        }
        this.nombrePaciente = nombrePaciente;

        this.especie = especie;

        if (raza.length() <= 3) {
            throw new IllegalArgumentException("La raza tiene muy pocas letras");
        }

        if (raza.equalsIgnoreCase("")) {
            throw new IllegalArgumentException("La raza es vacio");
        }
        this.raza = raza;
        this.sexo = sexo;

        if (color.equalsIgnoreCase("")) {
            throw new IllegalArgumentException("El color es vacio");
        }
        this.color = color;
        this.tipo = tipo;
        this.duenio = duenio;
    }

    /**
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @return the numero
     */
    public Integer getNumero() {
        return numero;
    }

    /**
     *
     * @return the fecha Nacimiento
     */
    public ZonedDateTime getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     *
     * @return the nombrePaciente
     */
    public String getNombrePaciente() {
        return nombrePaciente;
    }

    /**
     *
     * @return the Especie
     */
    public String getEspecie() {
        return especie;
    }

    /**
     *
     * @return the Raza
     */
    public String getRaza() {
        return raza;
    }

    /**
     *
     * @return the Sexo
     */
    public Sexo getSexo() {
        return sexo;
    }

    /**
     *
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     *
     * @return the tipo
     */
    public Tipo getTipo() {
        return tipo;
    }

    /**
     *
     * @return the duenio
     */
    public Persona getDuenio() {
        return duenio;
    }

    public List<Control> getControles() {
        return Collections.unmodifiableList(new ArrayList<>(this.controles));
    }

    /**
     * Cambiar una lista de controles
     * @param controles
     */
    public void setControles(ForeignCollection<Control> controles) {
        this.controles = controles;
    }
}
