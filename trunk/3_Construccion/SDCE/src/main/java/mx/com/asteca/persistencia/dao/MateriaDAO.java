/**
 * 
 */
package mx.com.asteca.persistencia.dao;

import java.util.List;

import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.entidades.Cursos;
import mx.com.asteca.persistencia.entidades.Materias;

/**
 * @author JAMARO
 *
 */
public interface MateriaDAO extends BaseDAO<Materias, Integer> {

	List<Materias> findMateriasPorCurso(Cursos curso)
			throws PersistenciaException;

}
