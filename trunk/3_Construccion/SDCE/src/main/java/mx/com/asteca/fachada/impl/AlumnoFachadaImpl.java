/**
 * 
 */
package mx.com.asteca.fachada.impl;

import java.util.List;

import mx.com.asteca.comun.dto.AlumnoDTO;
import mx.com.asteca.comun.dto.AsentamientoDTO;
import mx.com.asteca.comun.dto.CapacidadDTO;
import mx.com.asteca.comun.dto.ConfDescargasDTO;
import mx.com.asteca.comun.dto.DocumentoDTO;
import mx.com.asteca.comun.dto.DomicilioDTO;
import mx.com.asteca.comun.dto.EstadoDTO;
import mx.com.asteca.comun.dto.EstatusDTO;
import mx.com.asteca.comun.dto.EstudioDTO;
import mx.com.asteca.comun.dto.FamiliaDTO;
import mx.com.asteca.comun.dto.MunicipioDTO;
import mx.com.asteca.comun.dto.PersonaDTO;
import mx.com.asteca.comun.dto.ReferenciaDTO;
import mx.com.asteca.comun.dto.TipoEstudioDTO;
import mx.com.asteca.comun.dto.TipoLicenciaDTO;
import mx.com.asteca.fachada.AlumnoFachada;
import mx.com.asteca.fachada.FachadaException;
import mx.com.asteca.servicio.AlumnoServicio;
import mx.com.asteca.servicio.AsentamientoServicio;
import mx.com.asteca.servicio.BaseService;
import mx.com.asteca.servicio.CapacidadServicio;
import mx.com.asteca.servicio.ConfDescargasServicio;
import mx.com.asteca.servicio.DocsServicio;
import mx.com.asteca.servicio.DomicilioServicio;
import mx.com.asteca.servicio.EstadoServicio;
import mx.com.asteca.servicio.EstatusServicio;
import mx.com.asteca.servicio.EstudiosServicio;
import mx.com.asteca.servicio.FamiliaServicio;
import mx.com.asteca.servicio.MunicipioServicio;
import mx.com.asteca.servicio.PersonaServicio;
import mx.com.asteca.servicio.ReferenciaServicio;
import mx.com.asteca.servicio.ServicioException;
import mx.com.asteca.servicio.TipoEstudioServicio;
import mx.com.asteca.servicio.TipoLicenciaServicio;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Implementacion de la fachada para AlumnoDTO
 * 
 * @author Jorge Amaro Coria
 * @version 1.0
 * @since 1.0
 * 
 */
@Component
public class AlumnoFachadaImpl extends BaseFachadaImpl<AlumnoDTO, Integer>
		implements AlumnoFachada {

	@Autowired
	private AlumnoServicio servicioAlumno;

	@Autowired
	private AsentamientoServicio servicioAsentamiento;

	@Autowired
	private MunicipioServicio servicioMunicipio;

	@Autowired
	private EstadoServicio servicioEstado;

	@Autowired
	private TipoLicenciaServicio servicioTipoLicencia;

	@Autowired
	private TipoEstudioServicio servicioTipoEstudio;
	
	@Autowired
	private EstatusServicio servicioEstatus;
	
	@Autowired
	private PersonaServicio servicioPersona;
	
	@Autowired
	private DomicilioServicio servicioDomicilio;
	
	@Autowired
	private FamiliaServicio servicioFamilia;
	
	@Autowired
	private  CapacidadServicio servicioCapacidad;
	
	@Autowired
	private EstudiosServicio servicioEstudio;
	
	@Autowired
	private ReferenciaServicio servicioReferencia;
	
	@Autowired
	private DocsServicio servicioDocs;
	
	@Autowired
	private ConfDescargasServicio servicioDescargas;
	
	
	@Override
	BaseService getBaseService() {
		return servicioAlumno;
	}
	
	@Override
	public String getRuta() throws FachadaException{
		try {
			List<ConfDescargasDTO> lista = servicioDescargas.getAll();
			if(!CollectionUtils.isEmpty(lista)){
				ConfDescargasDTO dto = lista.get(0);
				return dto.getRuta();
			}else{
				return "./";
			}
		} catch (ServicioException e) {
			throw new FachadaException("Error en getAlumnosDatosBasicos Fachada : "
					+ e.getMessage(), e);
		}
		
	}
	
	@Override
	public List<AlumnoDTO> getAlumnosDatosBasicos() throws FachadaException{
		try{
			List<AlumnoDTO> lista = servicioAlumno.getListaDatosBasicos();
			return lista;
		} catch (ServicioException e) {
			throw new FachadaException("Error en getAlumnosDatosBasicos Fachada : "
					+ e.getMessage(), e);
		}
	}
	
	@Override
	public Integer save(AlumnoDTO dto) throws FachadaException {
		try{
			//Inicio salvado de datos personales
			PersonaDTO personaDTO = dto.getDtoPersona();
			int idPersona = servicioPersona.save(personaDTO);
			personaDTO.setIdPersona(idPersona);
			dto.setDtoPersona(personaDTO);
			//Fin de salvado datos personales
			//Inicio salvado de domicilio
			DomicilioDTO domicilioDTO = dto.getDtoDomicilio();
			int idDomicilio = servicioDomicilio.save(domicilioDTO);
			domicilioDTO.setIdDomicilio(idDomicilio);
			dto.setDtoDomicilio(domicilioDTO);
			//Fin de salvado domicilio
			//Inicio de salvado familia
			FamiliaDTO familiaDTO = dto.getDtoFamilia();
			int familiaID = servicioFamilia.save(familiaDTO);
			familiaDTO.setIdFam(familiaID);
			//Fin de salvado de familia
			int idAlumno = servicioAlumno.save(dto);
			dto.setIdAlumno(idAlumno);
			//Inicio salvado de capacidades
			if(!CollectionUtils.isEmpty(dto.getListaCapacidades())){
				List<CapacidadDTO> listaCapacidadesDTO = dto.getListaCapacidades();
				for(CapacidadDTO capDTO : listaCapacidadesDTO){
					servicioCapacidad.saveCapacidadPorAlumno(capDTO, idAlumno);
				}
			}
			//Fin salvado de capacidades
			//Inicio de salvado de estudios
			if(!CollectionUtils.isEmpty(dto.getListaEstudios())){
				List<EstudioDTO> listEstudiosDTO = dto.getListaEstudios();
				for(EstudioDTO estDTO : listEstudiosDTO){
					servicioEstudio.saveEstudioPorAlumno(estDTO, idAlumno);
				}
			}
			//Fin de salvado de estudios
			//Inicio de salvado de referencias
			if(!CollectionUtils.isEmpty(dto.getListaReferencias())){
				List<ReferenciaDTO> listaReferenciaDTO = dto.getListaReferencias();
				for(ReferenciaDTO ref : listaReferenciaDTO){
					PersonaDTO refPerTemp = ref.getDtoPersona();
					DomicilioDTO refDomTemp = ref.getDtoDomicilio();
					int pkPersona = servicioPersona.save(refPerTemp);
					int pkDomicilio = servicioDomicilio.save(refDomTemp);
					refPerTemp.setIdPersona(pkPersona);
					refDomTemp.setIdDomicilio(pkDomicilio);
					ref.setDtoDomicilio(refDomTemp);
					ref.setDtoPersona(refPerTemp);
					ref.setIdAlumno(idAlumno);
					servicioReferencia.save(ref);
				}
			}
			//Fin de salvado de referencias
			//Inicio de salvado de documentos
			if(!CollectionUtils.isEmpty(dto.getListaDocumentos())){
				List<DocumentoDTO> listaDocs = dto.getListaDocumentos();
				for(DocumentoDTO doc : listaDocs){
					doc.setIdAlumno(idAlumno);
					int pkDoc = servicioDocs.save(doc);
					doc.setIdDoc(pkDoc);
					servicioDocs.saveDocPorAlumno(doc, idAlumno);
				}
			}
			//Fin de salvado de documentos
			return idAlumno;
		} catch (ServicioException e) {
			throw new FachadaException("Error en save Fachada : "
					+ e.getMessage(), e);
		}
		
	}
	
	@Override
	public List<EstatusDTO> getEstatus() throws FachadaException{
		try {
			List<EstatusDTO> list = servicioEstatus.getAll();
			return list;
		} catch (ServicioException e) {
			throw new FachadaException("Error en getEstatus Fachada : "
					+ e.getMessage(), e);
		}
	}
	
	@Override
	public AsentamientoDTO findAsentamiento(int idAsentamiento, int idMunicipio, int idEstado) throws FachadaException{
		try{
			AsentamientoDTO dto = servicioAsentamiento.findAsentamiento(idAsentamiento, idMunicipio, idEstado);
			return dto;
		} catch (ServicioException e) {
			throw new FachadaException("Error en findAsentamiento Fachada : "
					+ e.getMessage(), e);
		}
	}
	
	@Override
	public List<TipoEstudioDTO> getTiposEstudios() throws FachadaException{
		try {
			List<TipoEstudioDTO> list = servicioTipoEstudio.getAll();
			return list;
		} catch (ServicioException e) {
			throw new FachadaException("Error en getTiposEstudios Fachada : "
					+ e.getMessage(), e);
		}
	}

	@Override
	public List<TipoLicenciaDTO> getTiposLicencia() throws FachadaException {
		try {
			List<TipoLicenciaDTO> list = servicioTipoLicencia.getAll();
			return list;
		} catch (ServicioException e) {
			throw new FachadaException("Error en getTiposLicencia Fachada : "
					+ e.getMessage(), e);
		}
	}
	
	@Override
	public TipoLicenciaDTO getLicencia(int idTipoLicencia) throws FachadaException{
		try{
			TipoLicenciaDTO licencia = servicioTipoLicencia.findByPK((short) idTipoLicencia);
			return licencia;
		} catch (ServicioException e) {
			throw new FachadaException("Error en getLicencia Fachada : "
					+ e.getMessage(), e);
		}
	}

	@Override
	public MunicipioDTO findMunicipio(int idMunicipio, int idEstado)
			throws FachadaException {
		try {
			MunicipioDTO dto = servicioMunicipio.getFromMunicipioEdo(idEstado,
					idMunicipio);
			return dto;
		} catch (ServicioException e) {
			throw new FachadaException("Error en findMunicipio Fachada : "
					+ e.getMessage(), e);
		}
	}

	@Override
	public EstadoDTO findEstado(int idEstado) throws FachadaException {
		try {
			EstadoDTO dto = servicioEstado.findByPK(idEstado);
			return dto;
		} catch (ServicioException e) {
			throw new FachadaException("Error en findEstado Fachada : "
					+ e.getMessage(), e);
		}
	}

	@Override
	public List<AsentamientoDTO> findAsentamientosByCp(int cp)
			throws FachadaException {
		try {
			List<AsentamientoDTO> listaAsentamientos = servicioAsentamiento
					.findAsentamientosByCp(cp);
			return listaAsentamientos;
		} catch (ServicioException e) {
			throw new FachadaException(
					"Error en findAsentamientosByCp Fachada : "
							+ e.getMessage(), e);
		}
	}

	@Override
	public List<Integer> getDistincCPs() throws FachadaException {
		try {
			List<Integer> listaAsentamientos = servicioAsentamiento
					.getDistinctCPs();
			return listaAsentamientos;
		} catch (ServicioException e) {
			throw new FachadaException("Error en getAsentamientos Fachada : "
					+ e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.com.asteca.fachada.AlumnoFachada#findByMatricula(java.lang.String)
	 */
	@Override
	public List<AlumnoDTO> findByMatricula(String matricula)
			throws FachadaException {
		try {
			List<AlumnoDTO> lista = servicioAlumno.findByMatricula(matricula);
			return lista;
		} catch (ServicioException e) {
			throw new FachadaException("Error en findByMatricula Fachada : "
					+ e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.com.asteca.fachada.AlumnoFachada#findByNombre(java.lang.String)
	 */
	@Override
	public List<AlumnoDTO> findByNombre(String nombreCompleto)
			throws FachadaException {
		try {
			List<AlumnoDTO> lista = servicioAlumno.findByNombre(nombreCompleto);
			return lista;
		} catch (ServicioException e) {
			throw new FachadaException("Error en findByNombre Fachada : "
					+ e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.com.asteca.fachada.AlumnoFachada#findByEstatus(java.lang.String)
	 */
	@Override
	public List<AlumnoDTO> findByEstatus(String estatus)
			throws FachadaException {
		try {
			List<AlumnoDTO> lista = servicioAlumno.findByEstatus(estatus);
			return lista;
		} catch (ServicioException e) {
			throw new FachadaException("Error en findByEstatus Fachada : "
					+ e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.com.asteca.fachada.AlumnoFachada#findByMatriculaAndNombre(java.lang
	 * .String, java.lang.String)
	 */
	@Override
	public List<AlumnoDTO> findByMatriculaAndNombre(String matricula,
			String nombreCompleto) throws FachadaException {
		try {
			List<AlumnoDTO> lista = servicioAlumno.findByMatriculaAndNombre(
					matricula, nombreCompleto);
			return lista;
		} catch (ServicioException e) {
			throw new FachadaException(
					"Error en findByMatriculaAndNombre Fachada : "
							+ e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.com.asteca.fachada.AlumnoFachada#findByMatriculaAndStatus(java.lang
	 * .String, java.lang.String)
	 */
	@Override
	public List<AlumnoDTO> findByMatriculaAndStatus(String matricula,
			String estatus) throws FachadaException {
		try {
			List<AlumnoDTO> lista = servicioAlumno.findByMatriculaAndStatus(
					matricula, estatus);
			return lista;
		} catch (ServicioException e) {
			throw new FachadaException(
					"Error en findByMatriculaAndStatus Fachada : "
							+ e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.com.asteca.fachada.AlumnoFachada#findByNombreAndStatus(java.lang.String
	 * , java.lang.String)
	 */
	@Override
	public List<AlumnoDTO> findByNombreAndStatus(String nombreCompleto,
			String estatus) throws FachadaException {
		try {
			List<AlumnoDTO> lista = servicioAlumno.findByNombreAndStatus(
					nombreCompleto, estatus);
			return lista;
		} catch (ServicioException e) {
			throw new FachadaException(
					"Error en findByNombreAndStatus Fachada : "
							+ e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.com.asteca.fachada.AlumnoFachada#findByMatriculaNombreAndStatus(java
	 * .lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<AlumnoDTO> findByMatriculaNombreAndStatus(String matricula,
			String nombreCompleto, String estatus) throws FachadaException {
		try {
			List<AlumnoDTO> lista = servicioAlumno
					.findByMatriculaNombreAndStatus(matricula, nombreCompleto,
							estatus);
			return lista;
		} catch (ServicioException e) {
			throw new FachadaException(
					"Error en findByMatriculaNombreAndStatus Fachada : "
							+ e.getMessage(), e);
		}
	}

	@Override
	public AlumnoDTO populate(int alumnoID) throws FachadaException {
		try {
			AlumnoDTO dto = servicioAlumno.populate(alumnoID);
			return dto;
		} catch (ServicioException e) {
			throw new FachadaException("Error en populate Fachada : "
					+ e.getMessage(), e);
		}
	}

}
