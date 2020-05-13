package cl.ucn.disc.pdbp.tdd.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
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

    @Override
    public List<T> obtenerLista() {
        try {
            return this.elDao.queryForAll();
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    @Override
    public T buscarPorId(K id) {
        return null;
    }

    @Override
    public boolean insertar(T t) {
        try {
            return elDao.create(t) == 1;
        } catch (SQLException throwables){
            throw new RuntimeException(throwables);
        }
    }

    @Override
    public boolean actualizar(T t) {
        return false;
    }

    @Override
    public boolean eliminar(K id) {
        return false;
    }
}
