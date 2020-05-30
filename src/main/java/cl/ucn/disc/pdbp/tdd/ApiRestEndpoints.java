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
import io.javalin.http.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ApiRestEndpoints {

    /**
     *  El logger
     */
    private static final Logger log = LoggerFactory.getLogger(ApiRestEndpoints.class);

    /**
     *  Los contratos(usando SQLite).
     */
    private static final Contratos CONTRATOS = new ContratosImpl("jdbc:sqlite:fivet.db");

    /**
     *  Constructor Privado
     */
    private ApiRestEndpoints () {

    }

    /**
     * Obtener todas las fichas
     * @param ctx
     */
    public static void obtenerTodasLasFichas(Context ctx) {
        log.debug("Obteniendo todas las fichas ...");
        List<Ficha> fichas = CONTRATOS.obtenerTodasLasFichas();
        ctx.json(fichas);
    }

    /**
     * buscar fichas
     */
    public static void buscarFichas(Context ctx) {

        String query = ctx.pathParam("query");
        log.debug("Buscando fichas con query <{}> ...", query);

        List<Ficha> fichas = CONTRATOS.buscarFicha(query);
        ctx.json(fichas);
    }

    /**
     * Obtener todas las personas
     */
    public static void obtenerTodasLasPersonas(Context ctx) {
        log.debug("Obteniendo todas las personas ...");
        List<Persona> personas = CONTRATOS.obtenerTodasLasPersonas();
        ctx.json(personas);
    }

    /**
     * Obtener una lista de controles a partir de un numero de ficha
     */
    public static void obtenerControlesDeFichas(Context ctx) {
        log.debug("Obteniendo controles especificos...");
        String query = ctx.pathParam("numeroFicha");
        log.debug("Buscando controles con numero de ficha <{}> ...", query);
        List<Control> controles = CONTRATOS.obtenerControlesDeFicha(query);
        ctx.json(controles);
    }

    /**
     *Devuelve una persona un numero de ficha
     */
    public static void buscarPersonaPorFicha(Context ctx) {
        log.debug("Buscando persona por numero de ficha...");
        String query = ctx.pathParam("numeroFicha");
        Persona persona = CONTRATOS.buscarPersonaPorFicha(query);
        ctx.json(persona);
    }
}
