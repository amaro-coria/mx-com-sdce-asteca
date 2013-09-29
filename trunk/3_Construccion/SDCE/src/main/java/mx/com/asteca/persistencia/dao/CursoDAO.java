/**
 * 
 */
package mx.com.asteca.persistencia.dao;

import java.util.Date;
import java.util.List;

import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.entidades.Cursos;

public interface CursoDAO extends BaseDAO<Cursos, Integer> {

	public List<Cursos> findByInstructorSedeAndArea(Integer instructor,
			Integer sede, Integer area, Date fechaIni, Date fechaFin) throws PersistenciaException;
}
