package mx.com.asteca.fachada;

import java.util.List;

import mx.com.asteca.comun.dto.ClienteDTO;
import mx.com.asteca.comun.dto.TipoClienteDTO;

public interface ClienteFachada extends BaseFachada<ClienteDTO, Integer>{
	
	public List<TipoClienteDTO> getTiposCliente() throws FachadaException;
	
}
