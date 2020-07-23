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

package cl.ucn.disc.pdbp.tdd;

import cl.ucn.disc.pdbp.tdd.model.Control;
import cl.ucn.disc.pdbp.tdd.model.Ficha;
import cl.ucn.disc.pdbp.tdd.model.Persona;

import java.util.List;

/**
 * Contratos del sistema
 * @author Pablo Salas Olivares
 */
public interface Contratos {

    /**
     * Contrato C01-Registrar los datos de un paciente
     *
     * Guarda una ficha en el back end y retorna esa ficha.
     * @param ficha a guardar
     */
    Ficha registrarPaciente(Ficha ficha);

    /**
     * Contrato C02-Registrar los datos de una Persona
     *
     * Guardar una persona en el back end y retornar esta misma
     * @param persona a guardar
     */
    Persona registrarPersona(Persona persona);

    /**
     * Contrato C03-Buscar Ficha
     *
     * Devuelve todas las fichas a traves de una palabra clave
     * @param palabraClave para buscar
     */
    List<Ficha> buscarFicha(String palabraClave);

    /**
     * Retorna una lista con todas las fichas
     */
    List<Ficha> obtenerTodasLasFichas();

    /**
     * Retorna una lista con todas las fichas
     */
    List<Persona> obtenerTodasLasPersonas();

    /**
     * Retorna una lista de controles a partir de un numero de ficha
     * @return
     */
    List<Control> obtenerControlesDeFicha(String numeroFicha);

    /**
     * Retorna una persona a partir de un numero de ficha
     * @return
     */
    Persona buscarPersonaPorFicha(String numeroFicha);

    /**
     * Retorna una persona por su id
     */
    Persona buscarPersona(Long id);

    /**
     * Retorna una ficha por su id
     */
    Ficha buscarUnaFicha(Long id);

    /**
     * Inserta un contrato en la base de datos
     */
    Control registrarControl(Control control);
}
