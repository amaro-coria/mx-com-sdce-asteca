package mx.com.asteca.servicio.impl;

import java.util.Date;
import java.util.List;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.CursoDTO;
import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.dao.BaseDAO;
import mx.com.asteca.persistencia.dao.CursoDAO;
import mx.com.asteca.persistencia.entidades.Cursos;
import mx.com.asteca.servicio.CursoServicio;
import mx.com.asteca.servicio.ServicioException;
import mx.com.asteca.servicio.assembler.Assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CursoServicioImpl extends
		BaseServiceImpl<CursoDTO, Integer, Cursos> implements CursoServicio {

	@Autowired
	private CursoDAO cursoDAO;

	@Autowired
	@Qualifier(Constantes.ASSEMBLER_CURSO)
	private Assembler assemblerCurso;

	@Override
	BaseDAO getDAO() {
		return cursoDAO;
	}

	@Override
	Assembler getAssembler() {
		return assemblerCurso;
	}

	@Override
	@Transactional(readOnly = true)
	public List<CursoDTO> findByInstructorSedeAndArea(Integer instructor,
			Integer sede, Integer area, Date fechaIni, Date fachaFin)
			throws ServicioException {

		try {
			List<Cursos> listNotificacion = cursoDAO
					.findByInstructorSedeAndArea(instructor, sede, area,
							fechaIni, fachaFin);
			List<CursoDTO> listaDTOs = assemblerCurso
					.getDTOListTransform(listNotificacion);

			return listaDTOs;
		} catch (PersistenciaException e) {
			throw new ServicioException(e.getMessage(), e);
		}
	}

}
