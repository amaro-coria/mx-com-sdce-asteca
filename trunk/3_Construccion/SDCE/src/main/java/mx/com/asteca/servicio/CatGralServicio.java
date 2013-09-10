/**
 * 
 */
package mx.com.asteca.servicio;

import java.util.List;

import mx.com.asteca.comun.dto.CatGralDTO;
import mx.com.asteca.persistencia.entidades.CatGral;


/**
 * @author Jorge Amaro Coria
 * @version 1.0
 * @since 1.0
 *
 */
public interface CatGralServicio extends BaseService<CatGralDTO, Integer, CatGral> {

	/**
	 * Obtiene la lista de registros filtrados por clave
	 * @param cve
	 * @return
	 * @throws ServicioException
	 */
	List<CatGralDTO> findByCve(String cve) throws ServicioException;

	/**
	 * Obtiene la lista de registros filtrados por clave y descripcion
	 * @param cve
	 * @param dsc
	 * @return
	 * @throws ServicioException
	 */
	List<CatGralDTO> findByCveAndDsc(String cve, String dsc)
			throws ServicioException;

	/**
	 * Obtiene la lista de registros filtrados por clave, descripcion y estatus
	 * @param cve
	 * @param dsc
	 * @param estatus
	 * @return
	 * @throws ServicioException
	 */
	List<CatGralDTO> findByCveDscAndEstatus(String cve, String dsc,
			String estatus) throws ServicioException;

}
