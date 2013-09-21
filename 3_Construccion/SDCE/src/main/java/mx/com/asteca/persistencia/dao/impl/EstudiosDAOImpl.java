/**
 * 
 */
package mx.com.asteca.persistencia.dao.impl;

import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.dao.EstudioDAO;
import mx.com.asteca.persistencia.entidades.Alumnos;
import mx.com.asteca.persistencia.entidades.Estudios;
import mx.com.asteca.persistencia.entidades.EstudiosAlumno;

import org.springframework.stereotype.Repository;

/**
 * @author JAMARO
 *
 */
@Repository
public class EstudiosDAOImpl extends BaseDAOImpl<Estudios, Integer> implements EstudioDAO {

	@Override
	public Integer saveEstudioPorAlumno(Estudios estudio, Alumnos alumno) throws PersistenciaException{
		try{
			EstudiosAlumno estudiosAlumno = new EstudiosAlumno();
				estudiosAlumno.setEstudios(estudio);
				estudiosAlumno.setAlumnos(alumno);
				Integer i = (Integer) getSessionFactory().getCurrentSession().save(estudiosAlumno);
				return i;
		} catch (Exception ex) {
			throw new PersistenciaException(
					"Error salvando saveEstudioPorAlumno por alumno:" + ex.getMessage(),
					ex);
		}
	}
	
}
