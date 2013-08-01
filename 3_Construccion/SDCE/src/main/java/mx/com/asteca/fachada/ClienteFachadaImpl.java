package mx.com.asteca.fachada;

import java.util.List;

import mx.com.asteca.comun.dto.ClienteDTO;
import mx.com.asteca.servicio.ClienteServicio;
import mx.com.asteca.servicio.ServicioException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClienteFachadaImpl implements ClienteFachada{

	@Autowired
	private ClienteServicio clienteServicio;
	
	public Integer saveCliente(ClienteDTO cliente) throws FachadaException {
		//TODO validar reglas extras de negocio
		try {
			Integer pk = clienteServicio.save(cliente);
			return pk;
		} catch (ServicioException e) {
			throw new FachadaException("::Propagando exception de servicio::", e);
		}
	}

	public List<ClienteDTO> getAllClientes() throws FachadaException {
		try {
			List<ClienteDTO> listaClientes = clienteServicio.getAll();
			return listaClientes;
		} catch (ServicioException e) {
			throw new FachadaException("::Propagando exception de servicio::", e);
		}
	}

}
