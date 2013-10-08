/**
 * 
 */
package mx.com.asteca.persistencia.dao;

import java.util.List;

import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.entidades.MateriasRegistros;

/**
 * @author JAMARO
 *
 */
public interface MateriaRegistroDAO extends BaseDAO<MateriasRegistros, Integer> {

	List<MateriasRegistros> findByNombre(String nombre)
			throws PersistenciaException;

	List<MateriasRegistros> findByProgramaEstudios(int idProgramaEstudios)
			throws PersistenciaException;

}
