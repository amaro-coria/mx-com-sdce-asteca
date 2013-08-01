package mx.com.asteca.persistencia.dao;

import java.io.Serializable;
import java.util.List;

import mx.com.asteca.persistencia.PersistenciaException;

/**
 * Interfaz que define de manera genérica las operaciones basicas de
 * persistencia.
 *
 * @author aperez
 * @param <T> clase de la entidad a persistir
 * @param <PK> clase de la llave primaria de la entidad
 */
public interface BaseDAO<T, PK extends Serializable> {

    /**
     * Método génerico utilizado para obtener todos los objetos de
     * un tipo particular.
     * 
     * @return Listado de objetos
     */
    List<T> getAll() throws PersistenciaException;

    /**
     * Método genérico para obtener un objeto basandose en su clase
     * e identificador.
     * 
     * @param id Identificador (clave primaria) del objeto a obtener
     * @return Objeto cargado
     */
    T findByPK(PK id) throws PersistenciaException;

    /**
     * Método genérico para salvar un objeto - maneja 
     * actualización e inserción.
     * 
     * @param object Objeto a guardar
     */
    void update(T object) throws PersistenciaException;

    /**
     * Método genérico para borrar un objeto basado en su clase e
     * identificador
     * 
     * @param id el identificador (llave primaria) del objeto a eliminar
     */
    void remove(T object) throws PersistenciaException;

    /**
     * Método genérico para insertar un objeto.
     */
    PK save(T object) throws PersistenciaException;

    /**
     * Guarda o actualiza un registro
     * 
     * @param object
     */
    void saveOrUpdate(T object) throws PersistenciaException;
    
    /**
     * Verifica la existencia de un objeto por clave primaria
     * 
     * @param paramPK Clave primaria
     * @return Verdadero si existe un objeto relacionado con la llave primaria 
     */
    boolean exists(PK paramPK) throws PersistenciaException;
    
}
