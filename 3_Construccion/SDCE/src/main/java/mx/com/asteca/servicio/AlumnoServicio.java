/**
 * 
 */
package mx.com.asteca.servicio;

import java.util.Date;
import java.util.List;

import mx.com.asteca.comun.dto.AlumnoDTO;
import mx.com.asteca.persistencia.entidades.Alumnos;

/**
 * Interfaz de servicio para AlumnosDTO
 * 
 * @author Jorge Amaro Coria
 * @version 1.0
 * @since 1.0
 * 
 */
public interface AlumnoServicio extends
		BaseService<AlumnoDTO, Integer, Alumnos> {

	/**
	 * Obtiene la lista de AlumnosDTO filtrada por matricula
	 * 
	 * @param matricula
	 * @return
	 * @throws ServicioException
	 */
	List<AlumnoDTO> findByMatricula(String matricula) throws ServicioException;

	/**
	 * Obtiene la lista de AlumnosDTO filtrada por nombreCompleto
	 * 
	 * @param nombreCompleto
	 * @return
	 * @throws ServicioException
	 */
	List<AlumnoDTO> findByNombre(String nombreCompleto)
			throws ServicioException;

	/**
	 * Obtiene la lista de AlumnosDTO filtrada por estatus
	 * 
	 * @param estatus
	 * @return
	 * @throws ServicioException
	 */
	List<AlumnoDTO> findByEstatus(String estatus) throws ServicioException;

	/**
	 * Obtiene la lista de AlumnosDTO filtrada por matricula y nombreCompleto
	 * 
	 * @param matricula
	 * @param nombreCompleto
	 * @return
	 * @throws ServicioException
	 */
	List<AlumnoDTO> findByMatriculaAndNombre(String matricula,
			String nombreCompleto) throws ServicioException;

	/**
	 * Obtiene la lista de AlumnosDTO filtrada por matricula y estatus
	 * 
	 * @param matricula
	 * @param estatus
	 * @return
	 * @throws ServicioException
	 */
	List<AlumnoDTO> findByMatriculaAndStatus(String matricula, String estatus)
			throws ServicioException;

	/**
	 * Obtiene la lista de AlumnosDTO filtrada por nombreCompleto y estatus
	 * 
	 * @param nombreCompleto
	 * @param estatus
	 * @return
	 * @throws ServicioException
	 */
	List<AlumnoDTO> findByNombreAndStatus(String nombreCompleto, String estatus)
			throws ServicioException;
	
	/**
	 * Obtiene la lista de AlumnosDTO filtrada por area
	 * 
	 * @param area
	 * @param estatus
	 * @return
	 * @throws ServicioException
	 */
	List<AlumnoDTO> findByArea(Integer area) throws ServicioException;

	/**
	 * Obtiene la lista de AlumnosDTO filtrada por matricula, nombreCompleto y
	 * estatus
	 * 
	 * @param matricula
	 * @param nombreCompleto
	 * @param estatus
	 * @return
	 * @throws ServicioException
	 */
	List<AlumnoDTO> findByMatriculaNombreAndStatus(String matricula,
			String nombreCompleto, String estatus) throws ServicioException;
	
	AlumnoDTO populate(int alumnoID) throws ServicioException;

	List<AlumnoDTO> getListaDatosBasicos() throws ServicioException;

	List<AlumnoDTO> findAlumnosByCurso(int idCurso) throws ServicioException;

	String findAsistencia(int idAlumno, int idCurso, Date fecha)
			throws ServicioException;

	long registraAsistencia(int idAlumno, int idCurso, Date fecha,
			short presente) throws ServicioException;

	String getCalificacion(int idAlumno, int idCurso, int idMateria)
			throws ServicioException;

	long saveCalificacion(int idAlumno, int idCurso, int idMateria,
			double calificacion) throws ServicioException;

}
