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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.Validation;

/**
 * Model test.
 */
public final class ModelTest {

    /**
     * The Logger (console)
     */
    private static final Logger log = LoggerFactory.getLogger(ModelTest.class);

    /**
     * Test the Persona.
     * - El nombre no puede ser null.
     * - El nombre debe tener al menos 2 letras.
     * - El apellido no puede ser null.
     * - El apellido debe tener al menos 3 letras.
     * - El rut debe ser valido.
     */
    @Test //Hara que este metodo se ejecute a traves de las pruebas y que cuando termine no lance exception
    public void testPersona() {

        log.debug("Testing Persona ..");

        // The data!
        log.debug(".. valid ..");
        String nombre = "Andrea";
        String apellido = "Contreras";
        String nombreApellido = nombre + " " + apellido;
        String rutOk = "152532873";
        String rutError = "15253287K";

        String nombreError = "o";
        String apellidoError = "da";

        // Test constructor and getters
        Persona persona = new Persona(nombre, apellido, rutOk);
        Assertions.assertEquals(persona.getNombre(), nombre);
        Assertions.assertEquals(persona.getApellido(), apellido);
        Assertions.assertEquals(persona.getNombreApellido(), nombreApellido);

        // Testing nullity
        log.debug(".. nullity ..");
        Assertions.assertThrows(NullPointerException.class, () -> new Persona(null, null, null));
        Assertions.assertThrows(NullPointerException.class, () -> new Persona(null, null, rutOk));
        Assertions.assertThrows(NullPointerException.class, () -> new Persona(null, apellido, null));
        Assertions.assertThrows(NullPointerException.class, () -> new Persona(null, apellido, rutOk));
        Assertions.assertThrows(NullPointerException.class, () -> new Persona(nombre, null, null));
        Assertions.assertThrows(NullPointerException.class, () -> new Persona(nombre, null, rutOk));
        Assertions.assertThrows(NullPointerException.class, () -> new Persona(nombre, apellido, null));

        // Testing invalid rut
        log.debug(".. rut ..");
        Assertions.assertThrows(RuntimeException.class, () -> new Persona(nombre, apellido, rutError));

        // Testing name and lastname length
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Persona(nombreError,apellido,rutOk));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Persona(nombre,apellidoError,rutOk));
        log.debug("Done.");

    }

    /**
     * Test the digito verificador.
     */
    @Test
    public void testDigitoVerificador() {

        Assertions.assertFalse(Validation.validarRut(null));

        Assertions.assertTrue(Validation.validarRut("152532873"));
        Assertions.assertTrue(Validation.validarRut("21195194K"));
        Assertions.assertTrue(Validation.validarRut("121244071"));
        Assertions.assertTrue(Validation.validarRut("198127949"));
        Assertions.assertTrue(Validation.validarRut("202294316"));

        Assertions.assertFalse(Validation.validarRut("1525A2873"));
        Assertions.assertFalse(Validation.validarRut("15253287K"));
        Assertions.assertFalse(Validation.validarRut("15253287-"));

    }

}
