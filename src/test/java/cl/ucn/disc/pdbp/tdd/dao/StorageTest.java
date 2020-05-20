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
package cl.ucn.disc.pdbp.tdd.dao;

import cl.ucn.disc.pdbp.tdd.model.*;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.Entity;

import java.io.IOException;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * Prueba de almacenamiento
 *
 * @author Pablo Salas
 */
public final class StorageTest {

    /**
     * Variable para imprimir por pantalla u otros motivos
     */
    private static final Logger log = LoggerFactory.getLogger(StorageTest.class);


    /**
     * Metodo para probar el almacenamiento
     */
    @Test
    public void probarBaseDeDatos() throws SQLException {

        // Base de datos a utilizar en la ram
        String databaseUrl = "jdbc:h2:mem:fivet_db";

        // Fuente de conexion: se cerrara solo con el trycatch
        try (ConnectionSource conectionSource = new JdbcConnectionSource(databaseUrl)) {

            // Crear la tabla persona
            TableUtils.createTableIfNotExists(conectionSource, Persona.class);

            // Dao de persona
            Dao<Persona, Long> daoPersona = DaoManager.createDao(conectionSource, Persona.class);

            // Nueva Persona
            Persona persona = new Persona( "Pablo", "Salas",  "194441568",  "Calle Falsa 123", 780801, 974994775, "pablo.salas@alumnos.ucn.cl");

            // Insertar la persona en la base de datos
            int tupla = daoPersona.create(persona);
            log.debug("Id: {}", persona.getId());

            Assertions.assertEquals(1,tupla,"Guarde tuplas distintas de 1");

            //Probar el obtener desde la base de datos, si esque este es igual a la persona creada
            Persona personaDb = daoPersona.queryForId(persona.getId());

            Assertions.assertEquals(persona.getNombre(), personaDb.getNombre(), "El nombre no es igual");
            Assertions.assertEquals(persona.getApellido(), personaDb.getApellido(), "El apellido no es igual");
            Assertions.assertEquals(persona.getRut(),personaDb.getRut(),"el rut no es igual");
            Assertions.assertEquals(persona.getDireccion(),personaDb.getDireccion(),"La direccion no es igual");
            Assertions.assertEquals(persona.getTelefonoFijo(),personaDb.getTelefonoFijo(),"El telefono fijo no es igual");
            Assertions.assertEquals(persona.getTelefonoMovil(),personaDb.getTelefonoMovil(),"El celular no es igual");
            Assertions.assertEquals(persona.getEmail(),personaDb.getEmail(),"El email no es igual");

            //Buscar por rut: SELECT * FROM Persona WHERE rut =194441568
            List<Persona> listaPersona = daoPersona.queryForEq("rut", "194441568");
            Assertions.assertEquals(1,listaPersona.size(), "Mas de una persona?!");

            //No se encontro el rut
            Assertions.assertEquals(0,daoPersona.queryForEq("rut", "1").size(),"Nada");

        } catch (IOException e) {
            log.error("Error",e);
        }
    }

    /**
     * Probando el repositorio de una persona
     */
    @Test
    public void testRepositoryPersona() {

        //La base de datos a utilizar(en la memoria RAM)
        String databaseUrl = "jdbc:h2:mem:fivet_db";

        //Fuente de conexion
        try (ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl)) {

            // Crear la tabla persona
            TableUtils.createTableIfNotExists(connectionSource, Persona.class);

            //El repositorio
            Repository<Persona, Long> elRepo = new RepositoryOrmLite<>(connectionSource, Persona.class);

            //Obtener lista de personas(0)
            {
                List<Persona> personas = elRepo.obtenerLista();

                //El tamaño debe ser 0
                Assertions.assertEquals(0, personas.size(), "size != 0!!");
            }


            //Probar la insercion de persona 1
            {
                Persona persona = new Persona( "Pablo", "Salas",  "194441568",  "Calle Falsa 123", 780801, 974994775, "pablo.salas@alumnos.ucn.cl");
                if (!elRepo.insertar(persona)) {
                    Assertions.fail("No se inserto la persona");
                }
            }

            //Probar la insercion de persona repetida(error)
            {
                Persona persona = new Persona( "Pablo", "Salas",  "194441568",  "Calle Falsa 123", 780801, 974994775, "pablo.salas@alumnos.ucn.cl");
                Assertions.assertThrows(RuntimeException.class, ()-> elRepo.insertar(persona));

            }


            //Obtener lista de personas(1)
            {
                List<Persona> personas = elRepo.obtenerLista();

                //El tamaño debe ser 1
                Assertions.assertEquals(1, personas.size(), "size != 1!!");
            }



        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Probar el repositorio de una ficha
     */
    @Test
    public void testRepositoryFicha() {
        //La base de datos a utilizar(en la memoria RAM)
        String databaseUrl = "jdbc:h2:mem:fivet_db";

        //Fuente de conexion
        try (ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl)) {

            // Crear las tablas a probar
            TableUtils.createTableIfNotExists(connectionSource, Persona.class);
            TableUtils.createTableIfNotExists(connectionSource, Ficha.class);
            TableUtils.createTableIfNotExists(connectionSource, Control.class);

            //Repositorio de ficha
            RepositoryOrmLite<Ficha, Long> repositoryFicha = new RepositoryOrmLite<Ficha, Long>(connectionSource, Ficha.class);
            RepositoryOrmLite<Control, Long> repositoryControl = new RepositoryOrmLite<Control, Long>(connectionSource, Control.class);
            RepositoryOrmLite<Persona, Long> repositoryPersona = new RepositoryOrmLite<Persona, Long>(connectionSource, Persona.class);
            {
                // Nueva persona a insertar en tabla Persona
                Persona persona = new Persona("Pablo", "Salas", "194441568", "Calle Falsa 123", 780801, 974994775, "pablo.salas@alumnos.ucn.cl");
                if (!new RepositoryOrmLite<Persona, Long>(connectionSource, Persona.class).insertar(persona)) {
                    Assertions.fail("No se puede insertar a la persona");
                }

                // Instanciar una ficha
                Ficha ficha = new Ficha(123, ZonedDateTime.now(), "Stefy", "Canino", "Pudul toy", Sexo.MACHO, "Blanco", Tipo.INTERNO, persona);

                // Insertar una ficha en su tabla
                if (!repositoryFicha.insertar(ficha)) {
                    Assertions.fail("No se pudo crear la ficha");
                }
            }

            {
                //Obtener una ficha y ver si sus atributos son distintos de null
                Ficha ficha = repositoryFicha.buscarPorId(1L);
                //La ficha no puede ser nula
                Assertions.assertNotNull(ficha,"Ficha fue nula");
                Assertions.assertNotNull(ficha.getDuenio(),"La ficha no tiene dueño");
                Assertions.assertNotNull(ficha.getDuenio().getRut(),"El rut del duenio fue nulo");
                Assertions.assertNotNull(ficha.getFechaNacimiento(),"La mascota no tiene fecha de nacimiento");

                Persona veterinario = new Persona("Ryu", "Gon", "84324302", "Calle Falsa 123", 780801, 974994775, "ryu.gon@gmail.com");
                if (!repositoryPersona.insertar(veterinario)) {
                    Assertions.fail("No se puede insertar a la persona");
                }

                Control control = new Control(ZonedDateTime.now(),ZonedDateTime.now(),36,50,10,"Esta bien",veterinario, ficha);
                Control control2 = new Control(ZonedDateTime.now(),ZonedDateTime.now(),37,55,15,"Esta mal",veterinario, ficha);

                if (!repositoryControl.insertar(control)) {
                    Assertions.fail("No se pudo crear el control");
                }



                if (!repositoryControl.insertar(control2)) {
                    Assertions.fail("No se pudo crear el control");
                }


                //Imprimir los atributos de la ficha
                log.debug("Ficha {}.", Entity.toString(ficha));
            }
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
