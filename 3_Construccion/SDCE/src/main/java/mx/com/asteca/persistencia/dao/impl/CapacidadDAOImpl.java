/**
 * 
 */
package mx.com.asteca.persistencia.dao.impl;

import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.dao.CapacidadDAO;
import mx.com.asteca.persistencia.entidades.Alumnos;
import mx.com.asteca.persistencia.entidades.Capacidades;
import mx.com.asteca.persistencia.entidades.CapacidadesAlumnos;

import org.springframework.stereotype.Repository;

/**
 * @author Jorge Amaro Coria
 * 
 */
@Repository
public class CapacidadDAOImpl extends BaseDAOImpl<Capacidades, Integer>
		implements CapacidadDAO {

	/* (non-Javadoc)
	 * @see mx.com.asteca.persistencia.dao.CapacidadDAO#saveCapacidadPorAlumno(mx.com.asteca.persistencia.entidades.Capacidades, mx.com.asteca.persistencia.entidades.Alumnos)
	 */
	@Override
	public Integer saveCapacidadPorAlumno(Capacidades capacidad, Alumnos alumno)
			throws PersistenciaException {
		try {
			CapacidadesAlumnos cap = new CapacidadesAlumnos();
			cap.setAlumnos(alumno);
			cap.setCapacidades(capacidad);
			Integer i = (Integer) getSessionFactory().getCurrentSession().save(
					cap);
			return i;
		} catch (Exception ex) {
			throw new PersistenciaException(
					"Error salvando capacidades por alumno:" + ex.getMessage(),
					ex);
		}

	}
}
