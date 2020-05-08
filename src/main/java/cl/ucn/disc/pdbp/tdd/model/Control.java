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
import java.time.ZonedDateTime;
/**
 * Control de un paciente
 *
 */
public final class Control {
    /**
     * Fecha del control
     */
    private final ZonedDateTime fecha;

    /**
     * Fecha proximo control
     */
    private final ZonedDateTime fechaProximoControl;

    /**
     * Temperatura
     * Min 28
     * Max 50   //TODO: verificar la temperatura minima y maxima
     */
    private final double temperatura;

    /**
     * Peso(kg)
     * Min 0
     * Max 200  //TODO: Verificar el valor maximo
     */
    private final double peso;

    /**
     * Altura(cm)
     * Min 1
     * Max 200 //TODO: Verificar la altura maxima
     */
    private final double altura;

    /**
     * Diagnostico
     */
    private final String diagnostico;

    /**
     * El veterinario
     * nota: Enlace muchos a uno con veterinario
     */
    private final Persona veterinario;

    /**
     * Constructor de la clase
     * @param fecha
     * @param fechaProximoControl
     * @param temperatura
     * @param peso
     * @param altura
     * @param diagnostico
     * @param veterinario
     */
    public Control(ZonedDateTime fecha, ZonedDateTime fechaProximoControl, double temperatura, double peso, double altura, String diagnostico, Persona veterinario) {
        // TODO :AGREGAR LAS RESTRICCIONES
        this.fecha = fecha;
        this.fechaProximoControl = fechaProximoControl;
        this.temperatura = temperatura;
        this.peso = peso;
        this.altura = altura;
        this.diagnostico = diagnostico;
        this.veterinario = veterinario;
    }

    /**
     *
     * @return fecha control
     */
    public ZonedDateTime getFecha() {
        return fecha;
    }

    /**
     *
     * @return fecha proximo control
     */
    public ZonedDateTime getFechaProximoControl() {
        return fechaProximoControl;
    }

    /**
     *
     * @return temperatura
     */
    public double getTemperatura() {
        return temperatura;
    }

    /**
     *
     * @return peso
     */
    public double getPeso() {
        return peso;
    }

    /**
     *
     * @return altura
     */
    public double getAltura() {
        return altura;
    }

    /**
     *
     * @return diagnostico
     */
    public String getDiagnostico() {
        return diagnostico;
    }

    /**
     *
     * @return el veterinario(persona)
     */
    public Persona getVeterinario() {
        return veterinario;
    }
}
