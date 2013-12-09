/**
 * 
 */
package mx.com.asteca.servicio.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.AlumnoDTO;
import mx.com.asteca.comun.dto.AlumnoTempDTO;
import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.dao.AlumnoDAO;
import mx.com.asteca.persistencia.dao.BaseDAO;
import mx.com.asteca.persistencia.entidades.Alumnos;
import mx.com.asteca.persistencia.entidades.Cursos;
import mx.com.asteca.persistencia.entidades.Materias;
import mx.com.asteca.servicio.AlumnoServicio;
import mx.com.asteca.servicio.ServicioException;
import mx.com.asteca.servicio.assembler.Assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementacion del servicio para AlumnosDTO
 * 
 * @author Jorge Amaro Coria
 * @since 1.0
 * @version 1.0
 * 
 */
@Service
public class AlumnoServicioImpl extends
		BaseServiceImpl<AlumnoDTO, Integer, Alumnos> implements AlumnoServicio {

	@Autowired
	private AlumnoDAO daoAlumno;

	@Autowired
	@Qualifier(Constantes.ASSEMBLER_ALUMNO)
	private Assembler assemblerAlumno;

	@Override
	BaseDAO getDAO() {
		return daoAlumno;
	}

	@Override
	Assembler getAssembler() {
		return assemblerAlumno;
	}		

	@Override
	@Transactional
	public long saveCalificacion(int idAlumno, int idCurso, int idMateria, double calificacion) throws ServicioException{
		try{
			Alumnos alumnos = new Alumnos();
			Cursos cursos = new Cursos();
			Materias materias = new Materias();
			alumnos.setIdAlumno(idAlumno);
			cursos.setIdCurso(idCurso);
			materias.setIdMateria(idMateria);
			long pk = daoAlumno.saveCalificacion(alumnos, cursos, materias, BigDecimal.valueOf(calificacion));
			return pk;
		} catch (PersistenciaException e) {
			throw new ServicioException("Error en saveCalificacion Servicio : "
					+ e.getMessage(), e);
		}
	}
	
	@Override
	@Transactional(readOnly=true)
	public String getCalificacion(int idAlumno, int idCurso, int idMateria) throws ServicioException{
		try{
			Alumnos alumnos = new Alumnos();
			Cursos cursos = new Cursos();
			Materias materias = new Materias();
			alumnos.setIdAlumno(idAlumno);
			cursos.setIdCurso(idCurso);
			materias.setIdMateria(idMateria);
			BigDecimal result = daoAlumno.getCalificacion(alumnos, cursos, materias);
			return String.valueOf(result.doubleValue());
		} catch (PersistenciaException e) {
			throw new ServicioException("Error en getCalificacion Servicio : "
					+ e.getMessage(), e);
		}
	}
	
	@Override
	@Transactional
	public long registraAsistencia(int idAlumno, int idCurso, Date fecha, short presente) throws ServicioException{
		try{
			Alumnos alumnos = new Alumnos();
			Cursos cursos = new Cursos();
			alumnos.setIdAlumno(idAlumno);
			cursos.setIdCurso(idCurso);
			long pk = daoAlumno.registraAsistencia(alumnos, cursos, fecha, presente);
			return pk;
		} catch (PersistenciaException e) {
			throw new ServicioException("Error en registraAsistencia Servicio : "
					+ e.getMessage(), e);
		}
	}
	
	@Override
	@Transactional(readOnly=true)
	public String findAsistencia(int idAlumno, int idCurso, Date fecha) throws ServicioException{
		try{
			Alumnos alumnos = new Alumnos();
			Cursos cursos = new Cursos();
			alumnos.setIdAlumno(idAlumno);
			cursos.setIdCurso(idCurso);
			String asistencia = daoAlumno.findAsistencia(alumnos, cursos, fecha);
			return asistencia;
		} catch (PersistenciaException e) {
			throw new ServicioException("Error en findAsistencia Servicio : "
					+ e.getMessage(), e);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.com.asteca.servicio.AlumnoServicio#findByMatricula(java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<AlumnoDTO> findByMatricula(String matricula)
			throws ServicioException {
		try {
			List<Alumnos> listMapping = daoAlumno.findByMatricula(matricula);
			List<AlumnoDTO> listDTOs = assemblerAlumno
					.getDTOListTransform(listMapping);
			return listDTOs;
		} catch (PersistenciaException e) {
			throw new ServicioException("Error en findByMatricula Servicio : "
					+ e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.com.asteca.servicio.AlumnoServicio#findByNombre(java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<AlumnoDTO> findByNombre(String nombreCompleto)
			throws ServicioException {
		try {
			List<Alumnos> listMapping = daoAlumno.findByNombre(nombreCompleto);
			List<AlumnoDTO> listDTOs = assemblerAlumno
					.getDTOListTransform(listMapping);
			return listDTOs;
		} catch (PersistenciaException e) {
			throw new ServicioException("Error en findByNombre Servicio : "
					+ e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.com.asteca.servicio.AlumnoServicio#findByEstatus(java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<AlumnoDTO> findByEstatus(String estatus)
			throws ServicioException {
		try {
			List<Alumnos> listMapping = daoAlumno.findByEstatus(estatus);
			List<AlumnoDTO> listDTOs = assemblerAlumno
					.getDTOListTransform(listMapping);
			return listDTOs;
		} catch (PersistenciaException e) {
			throw new ServicioException("Error en findByEstatus Servicio : "
					+ e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.com.asteca.servicio.AlumnoServicio#findByMatriculaAndNombre(java.lang
	 * .String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<AlumnoDTO> findByMatriculaAndNombre(String matricula,
			String nombreCompleto) throws ServicioException {
		try {
			List<Alumnos> listMapping = daoAlumno.findByMatriculaAndNombre(
					matricula, nombreCompleto);
			List<AlumnoDTO> listDTOs = assemblerAlumno
					.getDTOListTransform(listMapping);
			return listDTOs;
		} catch (PersistenciaException e) {
			throw new ServicioException(
					"Error en findByMatriculaAndNombre Servicio : "
							+ e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.com.asteca.servicio.AlumnoServicio#findByMatriculaAndStatus(java.lang
	 * .String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<AlumnoDTO> findByMatriculaAndStatus(String matricula,
			String estatus) throws ServicioException {
		try {
			List<Alumnos> listMapping = daoAlumno.findByMatriculaAndStatus(
					matricula, estatus);
			List<AlumnoDTO> listDTOs = assemblerAlumno
					.getDTOListTransform(listMapping);
			return listDTOs;
		} catch (PersistenciaException e) {
			throw new ServicioException(
					"Error en findByMatriculaAndStatus Servicio : "
							+ e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.com.asteca.servicio.AlumnoServicio#findByNombreAndStatus(java.lang
	 * .String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<AlumnoDTO> findByNombreAndStatus(String nombreCompleto,
			String estatus) throws ServicioException {
		try {
			List<Alumnos> listMapping = daoAlumno.findByNombreAndStatus(
					nombreCompleto, estatus);
			List<AlumnoDTO> listDTOs = assemblerAlumno
					.getDTOListTransform(listMapping);
			return listDTOs;
		} catch (PersistenciaException e) {
			throw new ServicioException(
					"Error en findByNombreAndStatus Servicio : "
							+ e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.com.asteca.servicio.AlumnoServicio#findByMatriculaNombreAndStatus(
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<AlumnoDTO> findByMatriculaNombreAndStatus(String matricula,
			String nombreCompleto, String estatus) throws ServicioException {
		try {
			List<Alumnos> listMapping = daoAlumno
					.findByMatriculaNombreAndStatus(matricula, nombreCompleto,
							estatus);
			List<AlumnoDTO> listDTOs = assemblerAlumno
					.getDTOListTransform(listMapping);
			return listDTOs;
		} catch (PersistenciaException e) {
			throw new ServicioException(
					"Error en findByMatriculaNombreAndStatus Servicio : "
							+ e.getMessage(), e);
		}
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<AlumnoDTO> findByArea(Integer area) throws ServicioException {
		try {
			List<Alumnos> listMapping = daoAlumno.findByArea(area);
			List<AlumnoDTO> listDTOs = assemblerAlumno.getDTOListTransform(listMapping);
			return listDTOs;
		} catch (PersistenciaException e) {
			throw new ServicioException(
					"Error en findByArea Servicio : "
							+ e.getMessage(), e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public AlumnoDTO populate(int alumnoID) throws ServicioException {
		try {
			Alumnos mapping = daoAlumno.populate(alumnoID);
			AlumnoDTO dto = (AlumnoDTO) assemblerAlumno.getDTOTransform(mapping);
			return dto;
		} catch (PersistenciaException e) {
			throw new ServicioException("Error en populate Servicio : "
					+ e.getMessage(), e);
		}
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<AlumnoDTO> getListaDatosBasicos() throws ServicioException{
		try{
			List<AlumnoTempDTO> listaBasica = daoAlumno.getAlumnosDatosBasicos();
			List<AlumnoDTO> listaAlumnos = new ArrayList<AlumnoDTO>();
			for(AlumnoTempDTO temp : listaBasica){
				AlumnoDTO dto = new AlumnoDTO();
					dto.setIdAlumno(temp.getIdAlumno());
					dto.setNombre(temp.getNombre() + " " + temp.getApellidoP() + " " + temp.getApellidoM());
					dto.setEstatus(temp.getDescEstatus());
					dto.setMatricula(temp.getMatricula());
				listaAlumnos.add(dto);
			}
			return listaAlumnos;
		} catch (PersistenciaException e) {
			throw new ServicioException("Error en getListaDatosBasicos Servicio : "
					+ e.getMessage(), e);
		}
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<AlumnoDTO> findAlumnosByCurso(int idCurso)throws ServicioException{
		try{
			Cursos cursos = new Cursos();
			cursos.setIdCurso(idCurso);
			List<AlumnoTempDTO> listaBasica = daoAlumno.getAlumnosPorCurso(cursos);
			List<AlumnoDTO> listaAlumnos = new ArrayList<AlumnoDTO>();
			for(AlumnoTempDTO temp : listaBasica){
				AlumnoDTO dto = new AlumnoDTO();
					dto.setIdAlumno(temp.getIdAlumno());
					dto.setNombre(temp.getNombre() + " " + temp.getApellidoP() + " " + temp.getApellidoM());
					dto.setEstatus(temp.getDescEstatus());
					dto.setMatricula(temp.getMatricula());
				listaAlumnos.add(dto);
			}
			return listaAlumnos;
		} catch (PersistenciaException e) {
			throw new ServicioException("Error en findAlumnosByCurso Servicio : "
					+ e.getMessage(), e);
		}
	}
}