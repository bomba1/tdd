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

import cl.ucn.disc.pdbp.tdd.model.Ficha;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.javalin.Javalin;
import io.javalin.apibuilder.ApiBuilder;
import io.javalin.core.util.RouteOverviewPlugin;
import io.javalin.plugin.json.JavalinJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Clase de aplicacion
 */
public class Application {

    /**
     * El logger
     */
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    /**
     * Constructor vacio privado
     */
    private Application () {
        // Nada aqui
    }

    /**
     * El punto de partida del programa
     * @param args de la consola
     */
    public static void main(String[] args) {

        //Los contratos
        Contratos contratos = new ContratosImpl("jdbc:sqlite:fivet.db");

        // Persona <----> Json via libreria Gson
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        JavalinJson.setFromJsonMapper(gson::fromJson);
        JavalinJson.setToJsonMapper(gson::toJson);

        log.debug("Empezando la aplicacion");

        // El servidor web Javalin
        Javalin javalin = Javalin.create(javalinConfig -> {

            // configuracion del logger
            javalinConfig.requestLogger(((ctx, executionTimeMs) -> {
                log.info("Served {} in {} ms.", ctx.fullUrl(),executionTimeMs);
                ctx.header("Server-Timing", "total;dur" + executionTimeMs);
            }));
            javalinConfig.registerPlugin(new RouteOverviewPlugin("/routes"));

        }).routes (() -> {

            // La version
            ApiBuilder.path("v1", () -> {

                // /fichas
                ApiBuilder.path("fichas", () -> {

                    // Obtener -> /fichas
                    ApiBuilder.get(ApiRestEndpoints::obtenerTodasLasFichas);

                    //Obtener ->/fichas/find/{query}
                    ApiBuilder.path("find/:query", () -> {
                        ApiBuilder.get(ApiRestEndpoints::buscarFichas);
                    });
                });
            });

        }).start(7000);

        //Ruta para mostrar el tiempo
        javalin.get("/",ctx -> {
            // retorna el dato
            ctx.result("The Date: " + ZonedDateTime.now());
        });

        //Obtener las fichas
        javalin.get("/v1/fichas/", ctx -> {
            List<Ficha> fichas = contratos.obtenerTodasLasFichas();
            ctx.json(fichas);
        });

        //Obtener las fichas
        javalin.get("/v1/fichas/:query", ctx -> {
            String query = ctx.pathParam("query");
            log.debug("Query: <{}>", query);

            List<Ficha> fichas = contratos.buscarFicha(query);
            ctx.json(fichas);
        });

    }
}
