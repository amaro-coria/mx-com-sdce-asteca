/**
 * 
 */
package mx.com.asteca.persistencia.dao;

import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.entidades.Alumnos;
import mx.com.asteca.persistencia.entidades.Capacidades;

/**
 * @author Jorge Amaro Coria
 *
 */
public interface CapacidadDAO extends BaseDAO<Capacidades, Integer> {

	/**
	 * Guarda un registro de capacidad por alumno
	 * @param capacidad
	 * @param alumno
	 * @return
	 * @throws PersistenciaException
	 */
	Integer saveCapacidadPorAlumno(Capacidades capacidad, Alumnos alumno) throws PersistenciaException;

}
