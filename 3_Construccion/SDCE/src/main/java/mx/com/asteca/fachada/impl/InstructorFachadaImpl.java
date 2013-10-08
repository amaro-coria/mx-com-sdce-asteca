/**
 * 
 */
package mx.com.asteca.fachada.impl;

import java.util.List;

import mx.com.asteca.comun.dto.AsentamientoDTO;
import mx.com.asteca.comun.dto.ConfDescargasDTO;
import mx.com.asteca.comun.dto.DocumentoDTO;
import mx.com.asteca.comun.dto.DomicilioDTO;
import mx.com.asteca.comun.dto.EstadoDTO;
import mx.com.asteca.comun.dto.EstatusDTO;
import mx.com.asteca.comun.dto.InstructorDTO;
import mx.com.asteca.comun.dto.InstructorDocumentoDTO;
import mx.com.asteca.comun.dto.InstructorMateriaDTO;
import mx.com.asteca.comun.dto.MateriaRegistroDTO;
import mx.com.asteca.comun.dto.MunicipioDTO;
import mx.com.asteca.comun.dto.PersonaDTO;
import mx.com.asteca.comun.dto.TipoInstructorDTO;
import mx.com.asteca.fachada.FachadaException;
import mx.com.asteca.fachada.InstructorFachada;
import mx.com.asteca.servicio.AsentamientoServicio;
import mx.com.asteca.servicio.BaseService;
import mx.com.asteca.servicio.ConfDescargasServicio;
import mx.com.asteca.servicio.DocsServicio;
import mx.com.asteca.servicio.DomicilioServicio;
import mx.com.asteca.servicio.EstadoServicio;
import mx.com.asteca.servicio.EstatusServicio;
import mx.com.asteca.servicio.InstructorDocumentoServicio;
import mx.com.asteca.servicio.InstructorMateriaServicio;
import mx.com.asteca.servicio.InstructorServicio;
import mx.com.asteca.servicio.MateriaRegistroServicio;
import mx.com.asteca.servicio.MunicipioServicio;
import mx.com.asteca.servicio.PersonaServicio;
import mx.com.asteca.servicio.ServicioException;
import mx.com.asteca.servicio.TipoInstructorServicio;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author JAMARO
 * 
 */
@Component
public class InstructorFachadaImpl extends
		BaseFachadaImpl<InstructorDTO, Integer> implements InstructorFachada {

	@Autowired
	private InstructorServicio servicioInstructor;
	@Autowired
	private InstructorMateriaServicio servicioInstructorMateria;
	@Autowired
	private InstructorDocumentoServicio servicioInstructorDocumento;
	@Autowired
	private TipoInstructorServicio servicioTipoInstructor;
	@Autowired
	private MateriaRegistroServicio servicioMateriaRegistro;
	@Autowired
	private EstatusServicio servicioEstatus;
	@Autowired
	private ConfDescargasServicio servicioDescargas;
	@Autowired
	private AsentamientoServicio servicioAsentamiento;
	@Autowired
	private MunicipioServicio servicioMunicipio;
	@Autowired
	private EstadoServicio servicioEstado;
	@Autowired
	private PersonaServicio servicioPersona;
	@Autowired
	private DomicilioServicio servicioDomicilio;
	@Autowired
	private DocsServicio servicioDoc;

	@Override
	BaseService getBaseService() {
		return servicioInstructor;
	}
	
	@Override
	public int saveDocumento(DocumentoDTO dto) throws FachadaException{
		try{
			int pk = servicioDoc.save(dto);
			return pk;
		} catch (ServicioException e) {
			throw new FachadaException("Error en saveDocumento Fachada : "
					+ e.getMessage(), e);
		}
	}
	
	@Override
	public Integer save(InstructorDTO dto) throws FachadaException {
		try{
			DomicilioDTO dtoDomicilio = dto.getDtoDomicilio();
			PersonaDTO dtoPersona = dto.getDtoPersona();
			int pkDomicilio = servicioDomicilio.save(dtoDomicilio);
			int pkPersona = servicioPersona.save(dtoPersona);
			dtoDomicilio.setIdDomicilio(pkDomicilio);
			dtoPersona.setIdPersona(pkPersona);
			dto.setDtoDomicilio(dtoDomicilio);
			dto.setDtoPersona(dtoPersona);
			int pk = super.save(dto);
			return pk;
		} catch (ServicioException e) {
			throw new FachadaException("Error en save Fachada : "
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
	public List<MateriaRegistroDTO> findMateriasRegistros() throws FachadaException{
		try{
			List<MateriaRegistroDTO> lista = servicioMateriaRegistro.getAll();
			return lista;
		}catch(ServicioException e){
			throw new FachadaException("Exception en findMateriasRegistros:"+e.getMessage(), e);
		}
	}
	
	@Override
	public List<TipoInstructorDTO> findTiposInstructores() throws FachadaException{
		try{
			List<TipoInstructorDTO> lista = servicioTipoInstructor.getAll();
			return lista;
		}catch(ServicioException e){
			throw new FachadaException("Exception en findTiposInstructores:"+e.getMessage(), e);
		}
	}
	
	@Override
	public int saveInstructorMateria(InstructorMateriaDTO dto) throws FachadaException{
		try{
			int pk = servicioInstructorMateria.save(dto);
			return pk;
		}catch(ServicioException e){
			throw new FachadaException("Exception en saveInstructorMateria:"+e.getMessage(), e);
		}
	}
	
	@Override
	public void saveOrUpdateInstructorMateria(InstructorMateriaDTO dto) throws FachadaException{
		try{
			servicioInstructorMateria.saveOrUpdate(dto);
		}catch(ServicioException e){
			throw new FachadaException("Exception en saveOrUpdateInstructorMateria:"+e.getMessage(), e);
		}
	}
	
	@Override
	public void removeInstructorMateria(InstructorMateriaDTO dto) throws FachadaException{
		try{
			servicioInstructorMateria.remove(dto);
		}catch(ServicioException e){
			throw new FachadaException("Exception en removeInstructorMateria:"+e.getMessage(), e);
		}
	}
	
	@Override
	public int saveInstructorDocumento(InstructorDocumentoDTO dto) throws FachadaException{
		try{
			int pk = servicioInstructorDocumento.save(dto);
			return pk;
		}catch(ServicioException e){
			throw new FachadaException("Exception en saveInstructorDocumento:"+e.getMessage(), e);
		}
	}
	
	@Override
	public void saveOrUpdateInstructorDocumento(InstructorDocumentoDTO dto) throws FachadaException{
		try{
			servicioInstructorDocumento.saveOrUpdate(dto);
		}catch(ServicioException e){
			throw new FachadaException("Exception en saveOrUpdateInstructorDocumento:"+e.getMessage(), e);
		}
	}
	
	@Override
	public void removeInstructorDocumento(InstructorDocumentoDTO dto) throws FachadaException{
		try{
			servicioInstructorDocumento.remove(dto);
		}catch(ServicioException e){
			throw new FachadaException("Exception en removeInstructorDocumento:"+e.getMessage(), e);
		}
	}

}
