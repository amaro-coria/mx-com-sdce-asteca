package mx.com.asteca.fachada.impl;

import java.util.List;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.EstadoDTO;
import mx.com.asteca.comun.dto.MunicipioDTO;
import mx.com.asteca.comun.dto.PaisDTO;
import mx.com.asteca.fachada.FachadaException;
import mx.com.asteca.fachada.MunicipioFachada;
import mx.com.asteca.servicio.BaseService;
import mx.com.asteca.servicio.EstadoServicio;
import mx.com.asteca.servicio.MunicipioServicio;
import mx.com.asteca.servicio.PaisServicio;
import mx.com.asteca.servicio.ServicioException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MunicipioFachadaImpl extends BaseFachadaImpl<MunicipioDTO, Integer> implements
		MunicipioFachada {

	@Autowired
	private MunicipioServicio servicio;
	@Autowired
	private EstadoServicio servicioEstado;
	@Autowired
	private PaisServicio servicioPais;
	
	@Override
	BaseService getBaseService() {
		return servicio;
	}

	@Override
	public List<EstadoDTO> getListaEstados() throws FachadaException {
		List<EstadoDTO> listaEstados;
		try {
			listaEstados = servicioEstado.getAll();
			return listaEstados;
		} catch (ServicioException e) {
			throw new FachadaException(Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO, e);
		}
	}

	@Override
	public List<PaisDTO> getListaPaises() throws FachadaException {
		List<PaisDTO> listaPaises;
		try {
			listaPaises = servicioPais.getAll();
			return listaPaises;
		} catch (ServicioException e) {
			throw new FachadaException(Constantes.ERROR_OBTENIENDO_LISTA_CATALOGO, e);
		}
	}

}
