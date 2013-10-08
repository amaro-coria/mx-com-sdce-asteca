/**
 * 
 */
package mx.com.asteca.persistencia.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import mx.com.asteca.comun.dto.AlumnoTempDTO;
import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.entidades.Alumnos;
import mx.com.asteca.persistencia.entidades.Cursos;
import mx.com.asteca.persistencia.entidades.Materias;

/**
 * Interfaz de acceso a datos para Alumnos
 * @author Jorge Amaro Coria
 * @version 1.0
 * @since 1.0
 *
 */
public interface AlumnoDAO extends BaseDAO<Alumnos, Integer> {

	/**
	 * Obtiene la lista de alumnos filtrada por matricula
	 * @param matricula
	 * @return
	 * @throws PersistenciaException
	 */
	List<Alumnos> findByMatricula(String matricula)
			throws PersistenciaException;

	/**
	 * Obtiene la lista de alumnos filtrada por nombreCompleto
	 * @param nombreCompleto
	 * @return
	 * @throws PersistenciaException
	 */
	List<Alumnos> findByNombre(String nombreCompleto)
			throws PersistenciaException;

	/**
	 * Obtiene la lista de alumnos filtrada por estatus
	 * @param estatus
	 * @return
	 * @throws PersistenciaException
	 */
	List<Alumnos> findByEstatus(String estatus) throws PersistenciaException;

	/**
	 * @param matricula
	 * @param nombreCompleto
	 * @return
	 * @throws PersistenciaException
	 */
	List<Alumnos> findByMatriculaAndNombre(String matricula,
			String nombreCompleto) throws PersistenciaException;

	/**
	 * Obtiene la lista de alumnos filtrada por matricula y estatus
	 * @param matricula
	 * @param estatus
	 * @return
	 * @throws PersistenciaException
	 */
	List<Alumnos> findByMatriculaAndStatus(String matricula, String estatus)
			throws PersistenciaException;

	/**
	 * Obtiene la lista de alumnos filtrada por nombreCompleto y estatus
	 * @param nombreCompleto
	 * @param estatus
	 * @return
	 * @throws PersistenciaException
	 */
	List<Alumnos> findByNombreAndStatus(String nombreCompleto, String estatus)
			throws PersistenciaException;

	/**
	 * Obtiene la lista de alumnos filtrada por matricula, nombreCompleto y estatus
	 * @param matricula
	 * @param nombreCompleto
	 * @param estatus
	 * @return
	 * @throws PersistenciaException
	 */
	List<Alumnos> findByMatriculaNombreAndStatus(String matricula,
			String nombreCompleto, String estatus) throws PersistenciaException;
	
	/**
	 * Obtiene la lista de alumnos filtrada por area
	 * @param area
	 * @return
	 * @throws PersistenciaException
	 */
	List<Alumnos> findByArea(Integer area) throws PersistenciaException;

	Alumnos populate(int idAlumno) throws PersistenciaException;

	List<AlumnoTempDTO> getAlumnosDatosBasicos() throws PersistenciaException;

	List<AlumnoTempDTO> getAlumnosPorCurso(Cursos cursos)
			throws PersistenciaException;

	String findAsistencia(Alumnos alumnos, Cursos cursos, Date fecha)
			throws PersistenciaException;

	long registraAsistencia(Alumnos alumnos, Cursos cursos, Date fecha,
			short presente) throws PersistenciaException;

	BigDecimal getCalificacion(Alumnos alumnos, Cursos cursos, Materias materias)
			throws PersistenciaException;

	long saveCalificacion(Alumnos alumnos, Cursos cursos, Materias materias,
			BigDecimal calificacion) throws PersistenciaException;

}
