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

import cl.ucn.disc.pdbp.tdd.dao.Repository;
import cl.ucn.disc.pdbp.tdd.dao.RepositoryOrmLite;
import cl.ucn.disc.pdbp.tdd.model.Control;
import cl.ucn.disc.pdbp.tdd.model.Ficha;
import cl.ucn.disc.pdbp.tdd.model.Persona;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Implementacion de contratos
 *
 * @author Pablo Salas Olivares
 */
public class ContratosImpl implements Contratos {

    /**
     * El logger
     */
    private static final Logger log = LoggerFactory.getLogger(ContratosImpl.class);

    /**
     * La fuente de coneccion
     */
    private ConnectionSource connectionSource;

    /**
     * Repositorio de ficha
     */
    private Repository<Ficha, Long> repoFicha;

    /**
     * Repositorio de persona
     */
    private Repository<Persona, Long> repoPersona;

    /**
     * Repositorio de control
     */
    private Repository<Control, Long> repoControl;

    /**
     * Constructor de la clase
     * @param databaseUrl jdbc String para conectarse a la base de datos
     */
    public ContratosImpl(String databaseUrl) {

        if (databaseUrl == null) {
            throw new IllegalArgumentException("No se pueden crear contratos si la conexion es nula");
        }

        try {
            //Conexion a la base de datos
            this.connectionSource = new JdbcConnectionSource(databaseUrl);

            TableUtils.createTableIfNotExists(connectionSource, Ficha.class);
            TableUtils.createTableIfNotExists(connectionSource, Persona.class);
            TableUtils.createTableIfNotExists(connectionSource, Control.class);

            this.repoFicha = new RepositoryOrmLite<>(this.connectionSource, Ficha.class);
            this.repoPersona = new RepositoryOrmLite<>(this.connectionSource, Persona.class);
            this.repoControl = new RepositoryOrmLite<>(this.connectionSource, Control.class);


        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    /**
     * Contrato C01-Registrar los datos de un paciente
     * <p>
     * Guarda una ficha en el back end y retorna esa ficha.
     *
     * @param ficha a guardar
     */
    @Override
    public Ficha registrarPaciente(Ficha ficha) {

        if (ficha == null) {
            log.debug("La ficha no puede ser ingresada porque es nula");
            return null;
        }

        this.repoFicha.insertar(ficha);

        Ficha fichaAux = this.repoFicha.buscarPorId(ficha.getId());

        return fichaAux;
    }

    /**
     * Contrato C02-Registrar los datos de una Persona
     * <p>
     * Guardar una persona en el back end y retornar esta misma
     *
     * @param persona a guardar
     */
    @Override
    public Persona registrarPersona(Persona persona) {
        if (persona == null) {
            log.debug("La persona no puede ser ingresada porque es nula");
            return null;
        }

        this.repoPersona.insertar(persona);

        Persona personaAux = this.repoPersona.buscarPorId(persona.getId());

        return personaAux;
    }

    /**
     * Contrato C03-Buscar Ficha
     * <p>
     * Devuelve todas las fichas a traves de una palabra clave
     *
     * @param palabraClave para buscar
     */
    @Override
    public List<Ficha> buscarFicha(String palabraClave) {

        //Verificar nulidad
        if (palabraClave == null) {
            throw new IllegalArgumentException("La palabraClave es nula");
        }

        //Variable a retornar: un listado de fichas
        List<Ficha> fichas = new ArrayList<>();

        try {
            // 1.- Buscar por numero
            if (StringUtils.isNumeric(palabraClave)) {

                //Se añaden todas las fichas con numero
                log.debug("Buscando fichas por numero");
                fichas.addAll(this.repoFicha.obtenerLista("numero", palabraClave));

                // 2.- Buscar por rut
                log.debug("Buscando fichas por rut del dueño");
                QueryBuilder<Persona, Long> queryPersona = this.repoPersona.getQuery();
                queryPersona.where().like("rut", "%" + palabraClave + "%");

                //Hacer join entre ficha y persona
                fichas.addAll(this.repoFicha.
                        getQuery().
                        join(queryPersona).
                        query());
            }

            //3.- Buscar por nombre del paciente
            fichas.addAll(this.repoFicha
                    .getQuery()
                    .where().like("nombrePaciente", "%" + palabraClave + "%").query());

            // 4.- Buscar por el nombre del dueño
            log.debug("Buscando fichas por el nombre del dueño");
            QueryBuilder<Persona, Long> queryPersona = this.repoPersona.getQuery();
            queryPersona.where().like("nombre", "%" + palabraClave + "%");

            //Hacer join entre ficha y persona
            fichas.addAll(this.repoFicha.
                    getQuery().
                    join(queryPersona).
                    query());


        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        //Remueve los duplicados
        return new ArrayList<>(
                fichas.stream().collect(
                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Ficha::getId)))
                )
        );
    }

    /**
     * Retorna una lista con todas las fichas
     */
    @Override
    public List<Ficha> obtenerTodasLasFichas() {
        List<Ficha> fichas = this.repoFicha.obtenerLista();

        return fichas;
    }

    /**
     * Retorna una lista con todas las personas
     */
    @Override
    public List<Persona> obtenerTodasLasPersonas() {
        List<Persona> personas = this.repoPersona.obtenerLista();

        return personas;
    }

    /**
     * Retorna los controles de una ficha
     */
    public List<Control> obtenerControlesDeFicha(String numeroFicha) {

        //Verificar nulidad
        if (numeroFicha == null) {
            throw new IllegalArgumentException("El numero de ficha es nulo");
        }

        //Variable a retornar, un listado de controles a partir de un numero de ficha
        List<Control> controles = new ArrayList<>();

        try {

            if (StringUtils.isNumeric(numeroFicha)) {

                // Buscar por numero de ficha
                log.debug("Buscando controles por numero de ficha");
                QueryBuilder<Ficha, Long> queryFicha = this.repoFicha.getQuery();
                queryFicha.where().eq("numero", numeroFicha);

                //Agregar los controles filtrados por numero de ficha
                controles.addAll(this.repoControl.getQuery().join(queryFicha).query());

                return controles;

            } else {
                throw new IllegalArgumentException("Ingrese un numeros, no letras");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    /**
     * Retorna la persona por ficha
     */
    public Persona buscarPersonaPorFicha(String numeroFicha) {

        //Verificar nulidad
        if (numeroFicha == null) {
            throw new IllegalArgumentException("El numero de ficha es nulo");
        }
        Persona persona = null;

        try {

            if (StringUtils.isNumeric(numeroFicha)) {
                //Buscar la ficha
                QueryBuilder<Ficha, Long> queryFicha = this.repoFicha.getQuery();
                queryFicha.where().eq("numero", numeroFicha);

                QueryBuilder<Persona, Long> queryPersona = this.repoPersona.getQuery();
                persona = queryPersona.join(queryFicha).queryForFirst();
                if (persona == null) {
                    throw new RuntimeException("No hay persona con tal ficha");
                }
                return persona;
            } else {
                throw new IllegalArgumentException("Ingrese un numeros, no letras");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Retorna una persona por su id
     */
    public Persona buscarPersona(Long id) {
        Persona persona = this.repoPersona.buscarPorId(id);
        return persona;
    }

    /**
     * Retorna una ficha por su id
     */
    public Ficha buscarUnaFicha(Long id) {
        Ficha ficha = this.repoFicha.buscarPorId(id);
        return ficha;
    }

    /**
     * Inserta un contrato en la base de datos
     */
    public Control registrarControl(Control control) {
        if (control == null) {
            log.debug("El contrato no puede ser ingresado porque es nulo");
            return null;
        }

        this.repoControl.insertar(control);

        Control controlAux = this.repoControl.buscarPorId(control.getIdControl());

        return controlAux;
    }
}
