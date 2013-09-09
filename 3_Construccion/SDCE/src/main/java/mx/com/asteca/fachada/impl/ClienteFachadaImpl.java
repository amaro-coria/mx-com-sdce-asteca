package mx.com.asteca.fachada.impl;

import java.util.List;

import mx.com.asteca.comun.dto.ClienteDTO;
import mx.com.asteca.comun.dto.TipoClienteDTO;
import mx.com.asteca.fachada.ClienteFachada;
import mx.com.asteca.fachada.FachadaException;
import mx.com.asteca.servicio.BaseService;
import mx.com.asteca.servicio.ClienteServicio;
import mx.com.asteca.servicio.ServicioException;
import mx.com.asteca.servicio.TipoClienteServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClienteFachadaImpl extends BaseFachadaImpl<ClienteDTO, Integer> implements ClienteFachada{

	@Autowired
	private ClienteServicio clienteServicio;
	@Autowired
	private TipoClienteServicio tipoClienteServicio;
	
	@Override
	BaseService getBaseService() {
		return clienteServicio;
	}

	@Override
	public List<TipoClienteDTO> getTiposCliente() throws FachadaException {
		List<TipoClienteDTO> listaTiposClientes = null;
		try {
			listaTiposClientes = tipoClienteServicio.getAll();
		} catch (ServicioException e) {
			throw new FachadaException("Excepcion obteniendo los tipos de clientes", e);
		}
		return listaTiposClientes;
	}

	@Override
	public List<ClienteDTO> getClientesByClave(String clave) throws FachadaException{
		try {
			List<ClienteDTO> listaClientes = clienteServicio.findByClave(clave);
			return listaClientes;
		} catch (ServicioException e) {
			throw new FachadaException(e.getMessage(), e);
		}
	}
	
	@Override
	public List<ClienteDTO> getClientesByClaveAndNombre(String clave, String nombre) throws FachadaException{
		try {
			List<ClienteDTO> listaClientes = clienteServicio.findByClaveAndNombre(clave, nombre);
			return listaClientes;
		} catch (ServicioException e) {
			throw new FachadaException(e.getMessage(), e);
		}
	}

}
