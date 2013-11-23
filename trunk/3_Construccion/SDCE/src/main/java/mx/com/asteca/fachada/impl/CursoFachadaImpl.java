/**
 * 
 */
package mx.com.asteca.fachada.impl;

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
import mx.com.asteca.fachada.CursoFachada;
import mx.com.asteca.fachada.FachadaException;
import mx.com.asteca.servicio.AlumnoServicio;
import mx.com.asteca.servicio.AulaServicio;
import mx.com.asteca.servicio.BaseService;
import mx.com.asteca.servicio.CatGralServicio;
import mx.com.asteca.servicio.ClienteServicio;
import mx.com.asteca.servicio.CursoServicio;
import mx.com.asteca.servicio.InstructorServicio;
import mx.com.asteca.servicio.MateriaRegistroServicio;
import mx.com.asteca.servicio.MateriaServicio;
import mx.com.asteca.servicio.ProgramaEstudiosServicio;
import mx.com.asteca.servicio.ServicioException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CursoFachadaImpl extends BaseFachadaImpl<CursoDTO, Integer>
		implements CursoFachada {

	@Autowired
	private CursoServicio cursoServicio;
	
	@Autowired
	private ClienteServicio servicioClientes;
	
	@Autowired
	private CatGralServicio servicioCatGral;
	
	@Autowired
	private ProgramaEstudiosServicio servicioProgrEstudios;
	
	@Autowired
	private MateriaRegistroServicio servicioMateriasRegistro;
	
	@Autowired
	private InstructorServicio servicioInstructor;
	
	@Autowired
	private AulaServicio servicioAula;
	
	@Autowired
	private MateriaServicio servicioMateria;
	
	@Autowired
	private AlumnoServicio servicioAlumnos;

	@Override
	BaseService getBaseService() {
		return cursoServicio;
	}
	
	@Override
	public CursoDTO findCursoByGrupo(int grupo) throws FachadaException{
		try{
			CursoDTO dto = cursoServicio.findByGrupo(grupo);
			return dto;
		}catch (ServicioException e)  {
			throw new FachadaException(
					"Error en findCursoByGrupo Fachada : "
							+ e.getMessage(), e);
		}
	}
	
	@Override
	public List<InstructorDTO> findInstructorCapacidato(int idMateriaRegistro) throws FachadaException{
		try{
			List<InstructorDTO> lista = servicioInstructor.findInstructoresHabilitadosParaMateria(idMateriaRegistro);
			return lista;
		}catch (ServicioException e)  {
			throw new FachadaException(
					"Error en findInstructorCapacidato Fachada : "
							+ e.getMessage(), e);
		}
	}
	
	@Override
	public long saveCalificacion(int idAlumno, int idCurso, int idMateria, double calificacion) throws FachadaException{
		try{
			long pk = servicioAlumnos.saveCalificacion(idAlumno, idCurso, idMateria, calificacion);
			return pk;
		} catch (ServicioException e)  {
			throw new FachadaException(
					"Error en saveCalificacion Fachada : "
							+ e.getMessage(), e);
		}
	}
	
	@Override
	public String getCalificacion(int idAlumno, int idCurso, int idMateria) throws FachadaException{
		try{
			String result = servicioAlumnos.getCalificacion(idAlumno, idCurso, idMateria);
			return result;
		} catch (ServicioException e)  {
			throw new FachadaException(
					"Error en registraAsistencia Fachada : "
							+ e.getMessage(), e);
		}
	}
	
	@Override
	public long registraAsistencia(int idAlumno, int idCurso, Date fecha, short presente) throws FachadaException{
		try{
			long pk = servicioAlumnos.registraAsistencia(idAlumno, idCurso, fecha, presente);
			return pk;
		} catch (ServicioException e)  {
			throw new FachadaException(
					"Error en registraAsistencia Fachada : "
							+ e.getMessage(), e);
		}
	}
	
	@Override
	public String findAsistencia(int idAlumno, int idCurso, Date fecha) throws FachadaException{
		try{
			String asistencia = servicioAlumnos.findAsistencia(idAlumno, idCurso, fecha);
			return asistencia;
		} catch (ServicioException e)  {
			throw new FachadaException(
					"Error en findAsistencia Fachada : "
							+ e.getMessage(), e);
		}
	}
	
	@Override
	public List<AlumnoDTO> findAlumnosPorCurso(int idCurso) throws FachadaException{
		try{
			List<AlumnoDTO> lista = servicioAlumnos.findAlumnosByCurso(idCurso);
			return lista;
		} catch (ServicioException e)  {
			throw new FachadaException(
					"Error en findAlumnosPorCurso Fachada : "
							+ e.getMessage(), e);
		}
	}
	
	@Override
	public CursoDTO findByReferencia(String referencia) throws FachadaException{
		try{
			CursoDTO dto = cursoServicio.findByReferencia(referencia);
			return dto;
		} catch (ServicioException e)  {
			throw new FachadaException(
					"Error en findByReferencia Fachada : "
							+ e.getMessage(), e);
		}
	}
	
	@Override
	public void removeMateriaCurso(int idMateria, int idCurso) throws FachadaException{
		try{
			cursoServicio.removeMateriaCurso(idMateria, idCurso);
		} catch (ServicioException e)  {
			throw new FachadaException(
					"Error en removeMateriaCurso Fachada : "
							+ e.getMessage(), e);
		}
	}
	
	@Override
	public void removeAlumnoCurso(int idAlumno, int idCurso) throws FachadaException{
		try{
			cursoServicio.removeAlumnoCurso(idAlumno, idCurso);
		} catch (ServicioException e)  {
			throw new FachadaException(
					"Error en removeAlumnoCurso Fachada : "
							+ e.getMessage(), e);
		}
	}
	
	@Override
	public AlumnoDTO findAlumno(int idAlumno) throws FachadaException{
		try{
			AlumnoDTO dto = servicioAlumnos.findByPK(idAlumno);
			return dto;
		} catch (ServicioException e)  {
			throw new FachadaException(
					"Error en findAlumno Fachada : "
							+ e.getMessage(), e);
		}
	}
	
	
	
	@Override
	public int saveAlumnoCurso(int idAlumno, int idCurso) throws FachadaException{
		try{
			int pk = cursoServicio.saveAlumnoCurso(idAlumno, idCurso);
			return pk;
		} catch (ServicioException e)  {
			throw new FachadaException(
					"Error en saveAlumnoCurso Fachada : "
							+ e.getMessage(), e);
		}
	}
	
	@Override
	public List<AlumnoDTO> findAlumnos() throws FachadaException{
		try{
			List<AlumnoDTO> lista = servicioAlumnos.getAll();
			return lista;
		} catch (ServicioException e)  {
			throw new FachadaException(
					"Error en findAlumnos Fachada : "
							+ e.getMessage(), e);
		}
	}
	
	@Override
	public List<MateriaDTO> findMateriasPorCurso(int idCurso) throws FachadaException{
		try{
			List<MateriaDTO> lista = servicioMateria.findMateriasPorCurso(idCurso);
			return lista;
		} catch (ServicioException e)  {
			throw new FachadaException(
					"Error en findMateriasPorCurso Fachada : "
							+ e.getMessage(), e);
		}
	}
	
	@Override
	public MateriaDTO getMateria(int materiaId) throws FachadaException{
		try{
			MateriaDTO dto = servicioMateria.findByPK(materiaId);
			return dto;
		} catch (ServicioException e)  {
			throw new FachadaException(
					"Error en getMateria Fachada : "
							+ e.getMessage(), e);
		}
	}
	
	@Override
	public int addMateriaCurso(MateriaDTO materiaDTO, int idCurso) throws FachadaException{
		try{
			int pk = servicioMateria.save(materiaDTO);
			int pkCruce = cursoServicio.saveMateriaCurso(idCurso, pk);
			return pk;
		} catch (ServicioException e)  {
			throw new FachadaException(
					"Error en saveMateriaCurso Fachada : "
							+ e.getMessage(), e);
		}
	}
	
	@Override
	public int saveMateriaCurso(int idMateriaRegistro, int idCurso) throws FachadaException{
		try{
			MateriaDTO materia = new MateriaDTO();
				materia.setIdMateriaRegistro(idMateriaRegistro);
			int pkMateria = servicioMateria.save(materia);
			int pk = cursoServicio.saveMateriaCurso(idCurso, pkMateria);
			//int pk = cursoServicio.saveMateriaCurso(idMateriaRegistro, idCurso);
			return  pkMateria;
		} catch (ServicioException e)  {
			throw new FachadaException(
					"Error en saveMateriaCurso Fachada : "
							+ e.getMessage(), e);
		}
	}
	
	@Override
	public int addMateria(MateriaDTO dto) throws FachadaException{
		try{
			int pk = servicioMateria.save(dto);
			return pk;
		} catch (ServicioException e)  {
			throw new FachadaException(
					"Error en addMateria Fachada : "
							+ e.getMessage(), e);
		}
	}
	
	@Override
	public void updateMateria(MateriaDTO materiaDTO) throws FachadaException{
		try{
			servicioMateria.update(materiaDTO);
		} catch (ServicioException e)  {
			throw new FachadaException(
					"Error en updateMateria Fachada : "
							+ e.getMessage(), e);
		}
	}
	
	@Override
	public List<CatGralDTO> findClasifCursos() throws FachadaException{
		try{
			List<CatGralDTO> lista = servicioCatGral.findTiposClasifCurso();
			return lista;
		} catch (ServicioException e)  {
			throw new FachadaException(
					"Error en findClasifCursos Fachada : "
							+ e.getMessage(), e);
		}
	}
	
	@Override
	public List<AulaDTO> getAulasDisponibles(Date timeStampInicial, Date timeStampFinal, int idSede) throws FachadaException{
		try{
			List<AulaDTO> lista = servicioAula.findAulasDisponibles(timeStampInicial, timeStampFinal, idSede);
			return lista;
		} catch (ServicioException e)  {
			throw new FachadaException(
					"Error en getAulasDisponibles Fachada : "
							+ e.getMessage(), e);
		}
	}
	
	@Override
	public List<MateriaRegistroDTO> findAllMaterias() throws FachadaException{
		try{
			List<MateriaRegistroDTO> lista = servicioMateriasRegistro.getAll();
			return lista;
		} catch (ServicioException e)  {
			throw new FachadaException(
					"Error en findAllMaterias Fachada : "
							+ e.getMessage(), e);
		}
	}
	
	@Override
	public List<InstructorDTO> findInstructores() throws FachadaException{
		try{
			List<InstructorDTO> lista = servicioInstructor.getAll();
			return lista;
		} catch (ServicioException e)  {
			throw new FachadaException(
					"Error en findInstructores Fachada : "
							+ e.getMessage(), e);
		}
	}
	
	@Override
	public List<MateriaRegistroDTO> findMateriasPorProgramaEstudio(int idProgramaEstudio) throws FachadaException{
		try{
			List<MateriaRegistroDTO> lista = servicioMateriasRegistro.findByIdProgramaEstudio(idProgramaEstudio);
			return lista;
		} catch (ServicioException e)  {
			throw new FachadaException(
					"Error en findMateriasPorProgramaEstudio Fachada : "
							+ e.getMessage(), e);
		}
	}
	
	@Override
	public List<CatGralDTO> findSedes() throws FachadaException{
		try{
			List<CatGralDTO> lista = servicioCatGral.findTiposSede();
			return lista;
		} catch (ServicioException e)  {
			throw new FachadaException(
					"Error en findSedes Fachada : "
							+ e.getMessage(), e);
		}
	}
	
	@Override
	public List<ProgramaEstudiosDTO> findProgramaEstudios() throws FachadaException{
		try{
			List<ProgramaEstudiosDTO> lista = servicioProgrEstudios.getAll();
			return lista;
		} catch (ServicioException e)  {
			throw new FachadaException(
					"Error en findProgramaEstudios Fachada : "
							+ e.getMessage(), e);
		}
	}
	
	@Override
	public List<CatGralDTO> findAreas() throws FachadaException{
		try{
			List<CatGralDTO> lista = servicioCatGral.findTiposArea();
			return lista;
		} catch (ServicioException e) {
			throw new FachadaException(
					"Error en findAreas Fachada : "
							+ e.getMessage(), e);
		}
	}
	
	@Override
	public List<ClienteDTO> findClientes() throws FachadaException{
		try{
			List<ClienteDTO> lista = servicioClientes.getAll();
			return lista;
		} catch (ServicioException e) {
			throw new FachadaException(
					"Error en findClientes Fachada : "
							+ e.getMessage(), e);
		}
	}

	@Override
	public List<CursoDTO> findByInstructorSedeAndArea(Integer instructor,
			Integer sede, Integer area, Date fechaIni, Date fachaFin)
			throws FachadaException {
		try {
			List<CursoDTO> lista = cursoServicio.findByInstructorSedeAndArea(
					instructor, sede, area, fechaIni, fachaFin);
			return lista;
		} catch (ServicioException e) {
			throw new FachadaException(
					"Error en findByInstructorSedeAndArea Fachada : "
							+ e.getMessage(), e);
		}
	}
}
