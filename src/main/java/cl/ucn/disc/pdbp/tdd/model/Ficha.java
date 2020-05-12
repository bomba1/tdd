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
import java.time.ZonedDateTime;
/**
 *Ficha Veterinaria
 */
public final class Ficha {

    /**
     * Numero de ficha
     */
    private Long numero;

    /**
     * Fecha de nacimiento
     */
    private ZonedDateTime fechaNacimiento;

    /**
     * Nombre del paciente
     */
    private String nombrePaciente;

    /**
     * Especie del paciente
     */
    private String especie;

    /**
     * Raza del paciente
     */
    private String raza;

    /**
     * Sexo del paciente
     */
    private Sexo sexo;

    /**
     * Color del paciente
     */
    private String color;

    /**
     * Tipo del paciente
     */
    private Tipo tipo;

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
    public Ficha(Long numero, ZonedDateTime fechaNacimiento, String nombrePaciente, String especie, String raza, Sexo sexo, String color, Tipo tipo) {
        this.numero = numero;
        this.fechaNacimiento = fechaNacimiento;
        this.nombrePaciente = nombrePaciente;
        this.especie = especie;
        this.raza = raza;
        this.sexo = sexo;
        this.color = color;
        this.tipo = tipo;
    }

    /**
     *
     * @return the numero
     */
    public Long getNumero() {
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
}
