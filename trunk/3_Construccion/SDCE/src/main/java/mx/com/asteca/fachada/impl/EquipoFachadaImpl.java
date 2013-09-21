package mx.com.asteca.fachada.impl;

import java.util.List;

import mx.com.asteca.comun.dto.CatGralDTO;
import mx.com.asteca.comun.dto.EquipoDTO;
import mx.com.asteca.fachada.EquipoFachada;
import mx.com.asteca.fachada.FachadaException;
import mx.com.asteca.servicio.BaseService;
import mx.com.asteca.servicio.CatGralServicio;
import mx.com.asteca.servicio.EquipoServicio;
import mx.com.asteca.servicio.ServicioException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EquipoFachadaImpl extends BaseFachadaImpl<EquipoDTO, Integer> implements EquipoFachada{

	@Autowired
	private EquipoServicio equipoServicio;
	@Autowired
	private CatGralServicio catGralServicio;
	
	@Override
	BaseService getBaseService() {
		return equipoServicio;
	}

	/* (non-Javadoc)
	 * @see mx.com.asteca.fachada.EquipoFachada#getListaTipoEquipos()
	 */
	@Override
	public List<CatGralDTO> getListaTipoEquipos() throws FachadaException {
		try {
			List<CatGralDTO> listaCatGral = catGralServicio.findTiposEquipo();
			return listaCatGral;
		} catch (ServicioException e) {
			throw new FachadaException("Error obteniendo tipos de equipo:"+e.getMessage(), e);
		}
	}

	/* (non-Javadoc)
	 * @see mx.com.asteca.fachada.EquipoFachada#findByClave(java.lang.String)
	 */
	@Override
	public List<EquipoDTO> findByClave(String cve) throws FachadaException {
		try {
			List<EquipoDTO> lista = equipoServicio.findByCve(cve);
			return lista;
		} catch (ServicioException e) {
			throw new FachadaException("Error obteniendo equipos:"+e.getMessage(), e);
		}
		
	}

	/* (non-Javadoc)
	 * @see mx.com.asteca.fachada.EquipoFachada#findByClaveAndDsc(java.lang.String, java.lang.String)
	 */
	@Override
	public List<EquipoDTO> findByClaveAndDsc(String cve, String dsc) throws FachadaException {
		try {
			List<EquipoDTO> lista = equipoServicio.findByCveAndDsc(cve, dsc);
			return lista;
		} catch (ServicioException e) {
			throw new FachadaException("Error obteniendo equipos:"+e.getMessage(), e);
		}
		
	}
	
	/* (non-Javadoc)
	 * @see mx.com.asteca.fachada.EquipoFachada#findByClaveDscAndTipo(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<EquipoDTO> findByClaveDscAndTipo(String cve, String dsc, String tipo) throws FachadaException {
		try {
			List<EquipoDTO> lista = equipoServicio.findByCveDscAndTipo(cve, dsc, tipo);
			return lista;
		} catch (ServicioException e) {
			throw new FachadaException("Error obteniendo equipos:"+e.getMessage(), e);
		}
		
	}

}
