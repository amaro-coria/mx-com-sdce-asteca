/**
 * 
 */
package mx.com.asteca.servicio;

import java.util.List;

import mx.com.asteca.comun.dto.AdministrativoDTO;
import mx.com.asteca.persistencia.entidades.Administrativos;

/**
 * @author JAMARO
 *
 */
public interface AdministrativoServicio extends
		BaseService<AdministrativoDTO, Integer, Administrativos> {

	List<AdministrativoDTO> findByNombre(String nombreCompleto)
			throws ServicioException;

	List<AdministrativoDTO> findByNombreClave(String nombreCompleto,
			String clave) throws ServicioException;

	List<AdministrativoDTO> findByClave(String clave) throws ServicioException;

}
