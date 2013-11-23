/**
 * 
 */
package mx.com.asteca.persistencia.dao;

import java.util.List;

import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.entidades.Instructores;
import mx.com.asteca.persistencia.entidades.MateriasRegistros;

/**
 * @author JAMARO
 *
 */
public interface InstructorDAO extends BaseDAO<Instructores, Integer> {

	void removeDependencias(Instructores instructores)
			throws PersistenciaException;

	List<Instructores> findInstructoresHabilitados(int idMateriaRegistro)
			throws PersistenciaException;

}
