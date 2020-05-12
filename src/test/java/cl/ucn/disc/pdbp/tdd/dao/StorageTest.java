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

import cl.ucn.disc.pdbp.tdd.model.Persona;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;
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
}
