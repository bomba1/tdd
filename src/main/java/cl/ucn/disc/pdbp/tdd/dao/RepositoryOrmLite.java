package cl.ucn.disc.pdbp.tdd.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

/**
 * Implementacion generica de Repository
 * @param <T> Tipo de clase
 * @param <K> Tipo de id
 */
public final class RepositoryOrmLite<T, K> implements Repository<T, K> {

    /**
     * The Dao
     * @return
     */
    private final Dao<T, K> elDao;

    /**
     * Constructor de la clase
     * @param connectionSource para conectar a orm
     * @param theClazz
     */
    public RepositoryOrmLite(ConnectionSource connectionSource, Class<T> theClazz) {
        try {
            elDao = DaoManager.createDao(connectionSource, theClazz);
        } catch ( SQLException throwables) {
            throw new RuntimeException(throwables);
        }

    }

    /**
     * Retorna una lista de objetos
     */
    @Override
    public List<T> obtenerLista() {
        try {
            return this.elDao.queryForAll();
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    /**
     * Obtiene una lista de T filtradas por una palabra clave
     * @param llave a buscar
     * @param valor a buscar
     */
    public List<T> obtenerLista(String llave, Object valor) {
        try {
            return elDao.queryForEq(llave,valor);
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    /**
     *Metodo que retorna una consulta
     */
    @Override
    public QueryBuilder<T, K> getQuery() {
        return elDao.queryBuilder();
    }


    /**
     * Retorna un objeto buscandolo por su id
     * @param id por buscar
     */
    @Override
    public T buscarPorId(K id) {
        if (id == null) {
            throw new IllegalArgumentException("Atributo nulo");
        }
        try {
            return this.elDao.queryForId(id);
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    /**
     * Metodo que inserta un objeto y retorna true si se logro.
     * @param t a registrar
     */
    @Override
    public boolean insertar(T t) {
        if (t == null) {
            throw new IllegalArgumentException();
        }
        try {
            return elDao.create(t) == 1;
        } catch (SQLException throwables){
            throw new RuntimeException(throwables);
        }
    }

    /**
     * Funcion que actualiza un objeto en la base de datos y retorna true si fue asi
     * @param t a actualizar
     */
    @Override
    public boolean actualizar(T t) {
        if (t == null) {
            throw new IllegalArgumentException();
        }
        try {
            return elDao.update(t) == 1;
        } catch (SQLException throwables){
            throw new RuntimeException(throwables);
        }
    }

    /**
     * Elimina un objeto y retorna falso
     * @param id para eliminar un objeto
     */
    @Override
    public boolean eliminar(K id) {
        if (id == null) {
            throw new IllegalArgumentException();
        }
        try {
            return elDao.deleteById(id) == 1;
        } catch (SQLException throwables){
            throw new RuntimeException(throwables);
        }
    }


}
