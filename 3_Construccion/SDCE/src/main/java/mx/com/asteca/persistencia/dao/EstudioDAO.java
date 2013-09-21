/**
 * 
 */
package mx.com.asteca.persistencia.dao;

import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.entidades.Alumnos;
import mx.com.asteca.persistencia.entidades.Estudios;

/**
 * @author JAMARO
 *
 */
public interface EstudioDAO extends BaseDAO<Estudios, Integer> {

	Integer saveEstudioPorAlumno(Estudios estudio, Alumnos alumno)
			throws PersistenciaException;

}
