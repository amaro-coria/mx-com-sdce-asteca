/**
 * 
 */
package mx.com.asteca.persistencia.dao;

import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.entidades.Personas;

/**
 * @author JAMARO
 *
 */
public interface PersonaDAO extends BaseDAO<Personas, Integer> {
	Personas findByUserName(String userName) throws PersistenciaException;
}
