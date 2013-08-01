package mx.com.asteca.fachada;

import java.util.List;

import mx.com.asteca.comun.dto.ClienteDTO;

public interface ClienteFachada {
	
	public Integer saveCliente(ClienteDTO cliente) throws FachadaException;

	public List<ClienteDTO> getAllClientes() throws FachadaException;
}
