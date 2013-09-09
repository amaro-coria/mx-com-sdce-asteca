package mx.com.asteca.fachada.impl;

import java.util.List;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.EstadoDTO;
import mx.com.asteca.comun.dto.PaisDTO;
import mx.com.asteca.fachada.EstadoFachada;
import mx.com.asteca.fachada.FachadaException;
import mx.com.asteca.servicio.BaseService;
import mx.com.asteca.servicio.EstadoServicio;
import mx.com.asteca.servicio.PaisServicio;
import mx.com.asteca.servicio.ServicioException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EstadoFachadaImpl extends BaseFachadaImpl<EstadoDTO, Integer>
		implements EstadoFachada {

	@Autowired
	private EstadoServicio servicio;
	@Autowired
	private PaisServicio servicioPais;
	@Override
	BaseService getBaseService() {
		return servicio;
	}
	@Override
	public List<PaisDTO> getPaises() throws FachadaException {
		List<PaisDTO> listaPaises;
		try {
			listaPaises = servicioPais.getAll();
			return listaPaises;
		} catch (ServicioException e) {
			throw new FachadaException(Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO, e);
		}
		
	}
	@Override
	public List<EstadoDTO> getFromPais(int idPais) throws FachadaException {
		List<EstadoDTO> listaEdos;
		try {
			listaEdos = servicio.getFromPais(idPais);
			return listaEdos;
		} catch (ServicioException e) {
			throw new FachadaException(e.getMessage(), e);
		}
		
	}



}
