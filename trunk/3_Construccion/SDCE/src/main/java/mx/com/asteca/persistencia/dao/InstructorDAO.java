/**
 * 
 */
package mx.com.asteca.persistencia.dao;

import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.entidades.Instructores;

/**
 * @author JAMARO
 *
 */
public interface InstructorDAO extends BaseDAO<Instructores, Integer> {

	void removeDependencias(Instructores instructores)
			throws PersistenciaException;

}
