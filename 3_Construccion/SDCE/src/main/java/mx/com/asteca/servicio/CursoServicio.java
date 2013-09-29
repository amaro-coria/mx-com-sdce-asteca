package mx.com.asteca.servicio;

import java.util.Date;
import java.util.List;

import mx.com.asteca.comun.dto.CursoDTO;
import mx.com.asteca.persistencia.entidades.Cursos;

public interface CursoServicio extends BaseService<CursoDTO, Integer, Cursos>{

	public List<CursoDTO> findByInstructorSedeAndArea(Integer instructor,
			Integer sede, Integer area, Date fechaIni, Date fachaFin)  throws ServicioException;

}
