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

import cl.ucn.disc.pdbp.tdd.dao.ZonedDateTimeType;
import cl.ucn.disc.pdbp.tdd.model.*;
import io.javalin.http.Context;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZonedDateTime;
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

    /**
     * Inserta una persona en la base de datos
     * @param ctx
     */
    public static void insertarPersona( Context ctx) {

        //Obtener los datos del json proveniente
        String nombre = ctx.queryParam("nombre");
        String apellido = ctx.queryParam("apellido");
        String rut = ctx.queryParam("rut");
        String direccion = ctx.queryParam("direccion");
        Integer telefonoFijo = Integer.parseInt(ctx.queryParam("telefonoFijo"));
        Integer telefonoMovil = Integer.parseInt(ctx.queryParam("telefonoMovil"));
        String email = ctx.queryParam("email");

        Persona persona = new Persona(nombre,apellido,rut,direccion,telefonoFijo,telefonoMovil,email);

        CONTRATOS.registrarPersona(persona);
        ctx.json(persona);
    }

    /**
     * Insertar una ficha en la base de datos
     * @param ctx
     */
    public static void insertarFicha(Context ctx) {

        // Obtener todos los datos
        Integer numero = Integer.parseInt(ctx.queryParam("numero"));
        String nombrePaciente = ctx.queryParam("nombrePaciente");
        String especie = ctx.queryParam("especie");
        ZonedDateTime fechaNacimiento = ZonedDateTime.parse(ctx.queryParam("fechaNacimiento"));
        String raza = ctx.queryParam("raza");

        // Vemos si el sexo es macho o hembra
        Sexo sexo;
        if(ctx.queryParam("sexo").equalsIgnoreCase("macho")) {
            sexo = Sexo.MACHO;
        } else{
            sexo = Sexo.HEMBRA;
        }
        String color = ctx.queryParam("color");

        //Vemos si el tipo es interno o externo
        Tipo tipo = null;
        if(ctx.queryParam("tipo").equals("interno")) {
            tipo = Tipo.INTERNO;
        } else {
            tipo = Tipo.EXTERNO;
        }

        // Buscamos al dueño de la mascota
        Long idDuenio = Long.parseLong(ctx.queryParam("duenio"));
        Persona duenio = CONTRATOS.buscarPersona(idDuenio);

        // Se crea la ficha
        Ficha ficha = new Ficha(numero,fechaNacimiento,especie,nombrePaciente,raza,sexo,color,tipo,duenio);
        CONTRATOS.registrarPaciente(ficha);
        ctx.json(ficha);
    }


    public static void insertarControl(Context ctx) {
        ZonedDateTime fecha = ZonedDateTime.parse(ctx.queryParam("fecha"));
        ZonedDateTime fechaProximoControl = ZonedDateTime.parse(ctx.queryParam("fechaProximoControl"));
        double temperatura = Double.parseDouble(ctx.queryParam("temperatura"));
        double peso = Double.parseDouble(ctx.queryParam("peso"));
        double altura = Double.parseDouble(ctx.queryParam("altura"));
        String diagnostico = ctx.queryParam("diagnostico");

        // Buscamos al dueño de la mascota
        Long idVeterinario = Long.parseLong(ctx.queryParam("veterinario"));
        Persona veterinario = CONTRATOS.buscarPersona(idVeterinario);

        // Buscamos la ficha del control
        Long idFicha = Long.parseLong(ctx.queryParam("ficha"));
        Ficha ficha = CONTRATOS.buscarUnaFicha(idFicha);

        Control control = new Control(fecha,fechaProximoControl,temperatura,peso,altura,diagnostico,veterinario,ficha);
        CONTRATOS.registrarControl(control);
        ctx.json(control);
    }
}
