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
import com.j256.ormlite.stmt.QueryBuilder;

import java.util.List;

public interface Repository <T, K> {

    /**
     * @return una lista de T
     */
    List<T> obtenerLista();

    /**
     * Obtiene una lista de T filtradas por una palabra clave
     * @param llave a buscar
     * @param valor a buscar
     */
    List<T> obtenerLista(String llave, Object valor);

    /**
     *Metodo que retorna una consulta
     */
    QueryBuilder<T, K> getQuery();

    /**
     * @param id por buscar
     * @return Objeto T
     */
    T buscarPorId(K id);

    /**
     * @param t a registrar
     * @return verdadero o falso
     */
    boolean insertar(T t);

    /**
     * @param t a actualizar
     * @return verdadero o falso
     */
    boolean actualizar(T t);

    /**
     * @param id a eliminar
     * @return verdadero o falso
     */
    boolean eliminar(K id);

}
