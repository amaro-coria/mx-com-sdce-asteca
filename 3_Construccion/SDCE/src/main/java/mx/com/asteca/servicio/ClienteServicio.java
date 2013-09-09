package mx.com.asteca.servicio;

import java.util.List;

import mx.com.asteca.comun.dto.ClienteDTO;
import mx.com.asteca.persistencia.entidades.Clientes;

public interface ClienteServicio extends
		BaseService<ClienteDTO, Integer, Clientes> {

	List<ClienteDTO> findByClave(String clave)throws ServicioException;

	List<ClienteDTO> findByClaveAndNombre(String clave, String nombre)
			throws ServicioException;

}
