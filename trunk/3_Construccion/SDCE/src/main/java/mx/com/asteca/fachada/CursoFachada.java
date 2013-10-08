/**
 * 
 */
package mx.com.asteca.fachada;

import java.util.Date;
import java.util.List;

import mx.com.asteca.comun.dto.AlumnoDTO;
import mx.com.asteca.comun.dto.AulaDTO;
import mx.com.asteca.comun.dto.CatGralDTO;
import mx.com.asteca.comun.dto.ClienteDTO;
import mx.com.asteca.comun.dto.CursoDTO;
import mx.com.asteca.comun.dto.InstructorDTO;
import mx.com.asteca.comun.dto.MateriaDTO;
import mx.com.asteca.comun.dto.MateriaRegistroDTO;
import mx.com.asteca.comun.dto.ProgramaEstudiosDTO;

public interface CursoFachada extends BaseFachada<CursoDTO, Integer> {

	/**
	 * Obtiene la lista de CursoDTO filtrada por instrictor sede area y periodo
	 * 
	 * @param instructor
	 * @param sede
	 * @param area
	 * @param fechaIni
	 * @param fechaFin
	 * @return
	 * @throws FachadaException
	 */
	List<CursoDTO> findByInstructorSedeAndArea(Integer instructor,
			Integer sede, Integer area, Date fechaIni, Date fachaFin)
			throws FachadaException;

	List<ClienteDTO> findClientes() throws FachadaException;

	List<CatGralDTO> findAreas() throws FachadaException;

	List<ProgramaEstudiosDTO> findProgramaEstudios() throws FachadaException;

	List<CatGralDTO> findSedes() throws FachadaException;

	List<MateriaRegistroDTO> findMateriasPorProgramaEstudio(
			int idProgramaEstudio) throws FachadaException;

	List<InstructorDTO> findInstructores() throws FachadaException;

	List<MateriaRegistroDTO> findAllMaterias() throws FachadaException;

	List<AulaDTO> getAulasDisponibles(Date timeStampInicial, Date timeStampFinal, int idSede)
			throws FachadaException;

	List<CatGralDTO> findClasifCursos() throws FachadaException;

	void updateMateria(MateriaDTO materiaDTO) throws FachadaException;

	int addMateria(MateriaDTO dto) throws FachadaException;

	int saveMateriaCurso(int idMateriaRegistro, int idCurso) throws FachadaException;

	MateriaDTO getMateria(int materiaId) throws FachadaException;

	List<MateriaDTO> findMateriasPorCurso(int idCurso) throws FachadaException;

	List<AlumnoDTO> findAlumnos() throws FachadaException;

	int saveAlumnoCurso(int idAlumno, int idCurso) throws FachadaException;

	AlumnoDTO findAlumno(int idAlumno) throws FachadaException;

	void removeAlumnoCurso(int idAlumno, int idCurso) throws FachadaException;

	void removeMateriaCurso(int idMateria, int idCurso) throws FachadaException;

	int addMateriaCurso(MateriaDTO materiaDTO, int idCurso)
			throws FachadaException;

	CursoDTO findByReferencia(String referencia) throws FachadaException;

	List<AlumnoDTO> findAlumnosPorCurso(int idCurso) throws FachadaException;

	String findAsistencia(int idAlumno, int idCurso, Date fecha)
			throws FachadaException;

	long registraAsistencia(int idAlumno, int idCurso, Date fecha,
			short presente) throws FachadaException;

	String getCalificacion(int idAlumno, int idCurso, int idMateria)
			throws FachadaException;

	long saveCalificacion(int idAlumno, int idCurso, int idMateria,
			double calificacion) throws FachadaException;

}
