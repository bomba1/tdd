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

import cl.ucn.disc.pdbp.tdd.dao.StorageTest;
import cl.ucn.disc.pdbp.tdd.model.Ficha;
import cl.ucn.disc.pdbp.tdd.model.Persona;
import cl.ucn.disc.pdbp.tdd.model.Sexo;
import cl.ucn.disc.pdbp.tdd.model.Tipo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Pruebas de los contratos
 */
public final class TestContratosImpl {

    /**
     * Variable para imprimir por pantalla u otros motivos
     */
    private static final Logger log = LoggerFactory.getLogger(StorageTest.class);

    /**
     * Metodo que se encarga de probar los contratos implementados
     */
    @Test
    public void probarPrimerosTresContratos() {
        // Base de datos a utilizar en la ram
        String databaseUrl = "jdbc:h2:mem:fivet_db";

        ContratosImpl contratos = new ContratosImpl(databaseUrl);
        {
            // Nueva persona a insertar en tabla Persona
            Persona persona = new Persona("Pablo", "Salas", "194441568", "Calle Falsa 123", 780801, 974994775, "pablo.salas@alumnos.ucn.cl");

            Persona personaAux = contratos.registrarPersona(persona);

            if (personaAux == null) {
                Assertions.fail("No se pudo ingresar la persona porque esta fue nula");
            }

            // Instanciar una ficha para su insercion
            Ficha ficha = new Ficha(123, ZonedDateTime.now(), "Stefy", "Canino", "Pudul toy", Sexo.MACHO, "Blanco", Tipo.INTERNO, persona);
            Ficha ficha2 = new Ficha(1234, ZonedDateTime.now(), "Ricky", "Felino", "Angora", Sexo.MACHO, "Blanco", Tipo.INTERNO, persona);

            Ficha fichaAux = contratos.registrarPaciente(ficha);

            if (fichaAux == null) {
                Assertions.fail("No se pudo ingresar la ficha porque esta es nula");
            }

            Ficha fichaAux2 = contratos.registrarPaciente(ficha2);

            if (fichaAux2 == null) {
                Assertions.fail("No se pudo ingresar la ficha porque fue nula");
            }
        }

        //Probar el contrato de busqueda de fichas
        // Probar con numeros de ficha
        List<Ficha> listaFichas = contratos.buscarFicha("1");
        Assertions.assertEquals("Stefy", listaFichas.get(0).getNombrePaciente());
        Assertions.assertEquals("Felino", listaFichas.get(1).getEspecie());

        //Probar por rut del dueño
        List<Ficha> listaFichas2 = contratos.buscarFicha("19444");
        Assertions.assertEquals("194441568", listaFichas2.get(0).getDuenio().getRut());

        //Probar por nombre del paciente
        List<Ficha> listaFichas3 = contratos.buscarFicha("Ste");
        Assertions.assertEquals("Pudul toy", listaFichas3.get(0).getRaza());

        //Probar por nombre del dueño
        List<Ficha> listaFichas4 = contratos.buscarFicha("Pab");
        Assertions.assertEquals("Pablo Salas", listaFichas4.get(1).getDuenio().getNombreApellido());

        //Revisar la cantidad de fichas que deberia retornar
        Assertions.assertEquals( 2,listaFichas.size());
        Assertions.assertEquals(2,listaFichas2.size());
        Assertions.assertEquals(1, listaFichas3.size());
        Assertions.assertEquals(2, listaFichas4.size());
    }
}
