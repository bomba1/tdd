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
 * Examen de un paciente
 */
public final class Examen {

    /**
     * Id del examen
     */
    private Long idExamen;

    /**
     * Nombre del examen
     */
    private String nombre;

    /**
     * Fecha del examen
     */
    public ZonedDateTime fechaExamen;

    /**
     * Constructor vacio de Examen
     */
    Examen () {

    }
    /**
     * Constructor de la clase Examen
     * @param nombre
     * @param fechaExamen
     */
    public Examen(String nombre, ZonedDateTime fechaExamen) {
        this.nombre = nombre;
        this.fechaExamen = fechaExamen;
    }

    /**
     * @return id examen
     */
    public Long getIdExamen() {
        return idExamen;
    }

    /**
     * @return nombre examen
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return fecha examen
     */
    public ZonedDateTime getFechaExamen() {
        return fechaExamen;
    }
}
