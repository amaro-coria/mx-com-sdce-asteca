package mx.com.asteca.fachada;

import java.util.List;

import mx.com.asteca.comun.dto.CatGralDTO;

/**
 * @author Jorge Amaro Coria
 * @version 1.0
 * @since 1.0
 *
 */
public interface CatGralFachada extends BaseFachada<CatGralDTO, Integer>{

	/**
	 * Obtiene la lista filtrada por clave
	 * @param clave
	 * @return
	 * @throws FachadaException
	 */
	List<CatGralDTO> findByCve(String clave) throws FachadaException;

	/**
	 * Obtiene la lista filtrada por clave y descripcion
	 * @param cve
	 * @param dsc
	 * @return
	 * @throws FachadaException
	 */
	List<CatGralDTO> findByCveAndDsc(String cve, String dsc)
			throws FachadaException;

	/**
	 * Obtiene la lista filtrada por clave, descripcion y estatus
	 * @param cve
	 * @param dsc
	 * @param estatus
	 * @return
	 * @throws FachadaException
	 */
	List<CatGralDTO> findByCveDscAndEstatus(String cve, String dsc,
			String estatus) throws FachadaException;
	
	/**
	 * Obtiene la lista filtrada por clave, descripcion y estatus
	 * @param cve
	 * @param dsc
	 * @param estatus
	 * @return
	 * @throws FachadaException
	 */
	List<CatGralDTO> findTiposArea() throws FachadaException;
	
}
