/**
 * 
 */
package mx.com.asteca.fachada.impl;

import java.util.List;

import mx.com.asteca.comun.dto.AdministrativoDTO;
import mx.com.asteca.comun.dto.AsentamientoDTO;
import mx.com.asteca.comun.dto.EstadoDTO;
import mx.com.asteca.comun.dto.MunicipioDTO;
import mx.com.asteca.fachada.AdministrativoFachada;
import mx.com.asteca.fachada.FachadaException;
import mx.com.asteca.servicio.AdministrativoServicio;
import mx.com.asteca.servicio.AsentamientoServicio;
import mx.com.asteca.servicio.BaseService;
import mx.com.asteca.servicio.EstadoServicio;
import mx.com.asteca.servicio.MunicipioServicio;
import mx.com.asteca.servicio.ServicioException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author JAMARO
 * 
 */
@Component
public class AdministrativoFachadaImpl extends
		BaseFachadaImpl<AdministrativoDTO, Integer> implements
		AdministrativoFachada {

	@Autowired
	private AdministrativoServicio servicioAdmin;

	@Autowired
	private AsentamientoServicio servicioAsentamiento;
	
	@Autowired
	private MunicipioServicio servicioMunicipio;
	
	@Autowired
	private EstadoServicio servicioEstado;
	
	@Override
	BaseService getBaseService() {
		return servicioAdmin;
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
	public List<AdministrativoDTO> findByNombre(String nombreCompleto) throws FachadaException{
		try{
			List<AdministrativoDTO> list = servicioAdmin.findByNombre(nombreCompleto);
			return list;
		}catch(ServicioException e){
			throw new FachadaException("Error en findByNombre:"+e.getMessage(), e);
		}
	}
	
	@Override
	public List<AdministrativoDTO> findByNombreClave(String nombreCompleto, String clave) throws FachadaException{
		try{
			List<AdministrativoDTO> list = servicioAdmin.findByNombreClave(nombreCompleto, clave);
			return list;
		}catch(ServicioException e){
			throw new FachadaException("Error en findByNombreClave:"+e.getMessage(), e);
		}
	}
	
	@Override
	public List<AdministrativoDTO> findByClave(String clave) throws FachadaException{
		try{
			List<AdministrativoDTO> list = servicioAdmin.findByClave(clave);
			return list;
		}catch(ServicioException e){
			throw new FachadaException("Error en findByClave:"+e.getMessage(), e);
		}
	}
	
}
