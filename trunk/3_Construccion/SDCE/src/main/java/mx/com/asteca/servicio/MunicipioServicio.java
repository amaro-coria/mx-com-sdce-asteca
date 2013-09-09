/**
 * 
 */
package mx.com.asteca.servicio;

import java.util.List;

import mx.com.asteca.comun.dto.MunicipioDTO;
import mx.com.asteca.persistencia.entidades.Municipios;
import mx.com.asteca.persistencia.entidades.MunicipiosId;

/**
 * @author JAMARO
 *
 */
public interface MunicipioServicio extends
		BaseService<MunicipioDTO, MunicipiosId, Municipios> {

	public List<MunicipioDTO> getFromEstado(int estadoID) throws ServicioException;

	MunicipioDTO getFromMunicipioEdo(int estadoID, int municipioID)
			throws ServicioException;
	
}
