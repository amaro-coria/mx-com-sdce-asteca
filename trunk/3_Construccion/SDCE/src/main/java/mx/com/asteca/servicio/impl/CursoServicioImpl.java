package mx.com.asteca.servicio.impl;

import java.util.Date;
import java.util.List;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.CursoDTO;
import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.dao.BaseDAO;
import mx.com.asteca.persistencia.dao.CursoDAO;
import mx.com.asteca.persistencia.entidades.Alumnos;
import mx.com.asteca.persistencia.entidades.Cursos;
import mx.com.asteca.persistencia.entidades.Materias;
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
	@Transactional(readOnly=true)
	public CursoDTO findByReferencia(String referencia) throws ServicioException{
		try{
			Cursos mapping = cursoDAO.findByReferencia(referencia);
			CursoDTO dto = (CursoDTO) assemblerCurso.getDTOTransform(mapping);
			return dto;
		} catch (PersistenciaException e) {
			throw new ServicioException(e.getMessage(), e);
		}
	}
	
	@Override
	@Transactional
	public void removeMateriaCurso(int idMateria, int idCurso)
			throws ServicioException {
		try{
			Materias materias = new Materias();
			Cursos cursos = new Cursos();
			materias.setIdMateria(idMateria);
			cursos.setIdCurso(idCurso);
			cursoDAO.deleteMateriaCurso(materias, cursos);
		} catch (PersistenciaException e) {
			throw new ServicioException(e.getMessage(), e);
		}

	}

	@Override
	@Transactional
	public void removeAlumnoCurso(int idAlumno, int idCurso)
			throws ServicioException {
		try {
			Alumnos alumnos = new Alumnos();
			Cursos cursos = new Cursos();
			alumnos.setIdAlumno(idAlumno);
			cursos.setIdCurso(idCurso);
			cursoDAO.deleteAlumnoCurso(alumnos, cursos);
		} catch (PersistenciaException e) {
			throw new ServicioException(e.getMessage(), e);
		}
	}

	@Override
	@Transactional
	public int saveAlumnoCurso(int idAlumno, int idCurso)
			throws ServicioException {
		try {
			Alumnos alumnos = new Alumnos();
			alumnos.setIdAlumno(idAlumno);
			Cursos cursos = new Cursos();
			cursos.setIdCurso(idCurso);
			int pk = cursoDAO.saveAlumnoCurso(alumnos, cursos);
			return pk;
		} catch (PersistenciaException e) {
			throw new ServicioException(e.getMessage(), e);
		}
	}

	@Override
	@Transactional
	public int saveMateriaCurso(int idCurso, int idMateria)
			throws ServicioException {
		try {
			Cursos curso = new Cursos();
			Materias materias = new Materias();
			curso.setIdCurso(idCurso);
			materias.setIdMateria(idMateria);
			int pk = cursoDAO.saveMateriaCurso(materias, curso);
			return pk;
		} catch (PersistenciaException e) {
			throw new ServicioException(e.getMessage(), e);
		}
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
