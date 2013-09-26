/**
 * 
 */
package mx.com.asteca.persistencia.dao;

import java.util.List;

import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.entidades.Administrativos;

/**
 * @author JAMARO
 *
 */
public interface AdministrativoDAO extends BaseDAO<Administrativos, Integer> {

	List<Administrativos> findByNombreAndClave(String nombreCompleto,
			String clave) throws PersistenciaException;

	List<Administrativos> findByNombre(String nombreCompleto)
			throws PersistenciaException;

	List<Administrativos> findByClave(String clave)
			throws PersistenciaException;

}
