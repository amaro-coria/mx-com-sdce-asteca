/**
 * 
 */
package mx.com.asteca.persistencia.dao;

import java.util.List;

import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.entidades.Bitacora;

/**
 * @author JAMARO
 *
 */
public interface BitacoraDAO extends BaseDAO<Bitacora, Long> {

	List<Bitacora> findByAccion(String accion) throws PersistenciaException;

	List<Bitacora> findByIdUsuario(int idUsuario) throws PersistenciaException;

	List<Bitacora> findByUsuarioAndAccion(int idUsuario, String accion)
			throws PersistenciaException;

}
