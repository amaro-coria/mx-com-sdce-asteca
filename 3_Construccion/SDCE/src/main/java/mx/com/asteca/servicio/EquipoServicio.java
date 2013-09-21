/**
 * 
 */
package mx.com.asteca.servicio;

import java.util.List;

import mx.com.asteca.comun.dto.EquipoDTO;
import mx.com.asteca.persistencia.entidades.Equipos;

/**
 * @author Rabelt Ibarra Godinez.
 *
 */
public interface EquipoServicio extends BaseService<EquipoDTO, Integer, Equipos> {

	/**
	 * Obtiene la lista filtrada de equipos
	 * @param cve
	 * @return
	 * @throws ServicioException
	 */
	public List<EquipoDTO> findByCve(String cve) throws ServicioException;

	/**
	 * Obtiene la lista filtrada de equipos
	 * @param cve
	 * @param dsc
	 * @return
	 * @throws ServicioException
	 */
	List<EquipoDTO> findByCveAndDsc(String cve, String dsc)
			throws ServicioException;

	/**
	 * Obtiene la lista filtrada de equipos
	 * @param cve
	 * @param dsc
	 * @param tipo
	 * @return
	 * @throws ServicioException
	 */
	List<EquipoDTO> findByCveDscAndTipo(String cve, String dsc, String tipo)
			throws ServicioException;
}
