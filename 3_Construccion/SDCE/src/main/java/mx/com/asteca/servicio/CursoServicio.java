package mx.com.asteca.servicio;

import java.util.Date;
import java.util.List;

import mx.com.asteca.comun.dto.CursoDTO;
import mx.com.asteca.persistencia.entidades.Cursos;

public interface CursoServicio extends BaseService<CursoDTO, Integer, Cursos>{

	public List<CursoDTO> findByInstructorSedeAndArea(Integer instructor,
			Integer sede, Integer area, Date fechaIni, Date fachaFin)  throws ServicioException;

	int saveMateriaCurso(int idCurso, int idMateria) throws ServicioException;

	int saveAlumnoCurso(int idAlumno, int idCurso) throws ServicioException;

	void removeAlumnoCurso(int idAlumno, int idCurso) throws ServicioException;

	void removeMateriaCurso(int idMateria, int idCurso)
			throws ServicioException;

	CursoDTO findByReferencia(String referencia) throws ServicioException;

	CursoDTO findByGrupo(int idGrupo) throws ServicioException;

}
