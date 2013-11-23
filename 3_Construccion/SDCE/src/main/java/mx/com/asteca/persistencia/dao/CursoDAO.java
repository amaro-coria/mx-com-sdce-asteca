/**
 * 
 */
package mx.com.asteca.persistencia.dao;

import java.util.Date;
import java.util.List;

import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.entidades.Alumnos;
import mx.com.asteca.persistencia.entidades.Cursos;
import mx.com.asteca.persistencia.entidades.Materias;

public interface CursoDAO extends BaseDAO<Cursos, Integer> {

	public List<Cursos> findByInstructorSedeAndArea(Integer instructor,
			Integer sede, Integer area, Date fechaIni, Date fechaFin) throws PersistenciaException;

	int saveMateriaCurso(Materias materia, Cursos curso)
			throws PersistenciaException;

	int saveAlumnoCurso(Alumnos alumno, Cursos curso)
			throws PersistenciaException;

	void deleteAlumnoCurso(Alumnos alumno, Cursos curso)
			throws PersistenciaException;

	void deleteMateriaCurso(Materias materias, Cursos cursos)
			throws PersistenciaException;

	Cursos findByReferencia(String referencia) throws PersistenciaException;

	Cursos findByGrupo(int grupo) throws PersistenciaException;
}
