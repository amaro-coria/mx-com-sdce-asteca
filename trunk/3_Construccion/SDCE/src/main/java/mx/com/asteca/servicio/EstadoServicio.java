/**
 * 
 */
package mx.com.asteca.servicio;

import java.util.List;

import mx.com.asteca.comun.dto.EstadoDTO;
import mx.com.asteca.persistencia.entidades.Estados;

/**
 * @author JAMARO
 * 
 */
public interface EstadoServicio extends
		BaseService<EstadoDTO, Integer, Estados> {

	public List<EstadoDTO> getFromPais(int idPais) throws ServicioException;
	
}
