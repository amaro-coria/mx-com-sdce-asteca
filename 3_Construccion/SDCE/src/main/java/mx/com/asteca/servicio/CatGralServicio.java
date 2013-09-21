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
	
	/**
	 * Obtiene la lista de tipos de equipo. Los registros tienen que coincidir con tener en idTipoCatGral = 1
	 * @return
	 * @throws ServicioException
	 */
	List<CatGralDTO> findTiposEquipo() throws ServicioException;

	/**
	 * Obtiene la lista de areas. Los registros tienen que coincidir con tener en idTipoCatGral = 2
	 * @return
	 * @throws ServicioException
	 */
	List<CatGralDTO> findTiposArea() throws ServicioException;

	/**
	 * Obtiene la lista de sedes. Los registros tienen que coincidir con tener en idTipoCatGral = 3
	 * @return
	 * @throws ServicioException
	 */
	List<CatGralDTO> findTiposSede() throws ServicioException;

	/**
	 * Obtiene la lista de paises. Los registros tienen que coincidir con tener en idTipoCatGral = 4
	 * @return
	 * @throws ServicioException
	 */
	List<CatGralDTO> findTiposPais() throws ServicioException;

	/**
	 * Obtiene la lista de clasif de cursos. Los registros tienen que coincidir con tener en idTipoCatGral = 5
	 * @return
	 * @throws ServicioException
	 */
	List<CatGralDTO> findTiposClasifCurso() throws ServicioException;

	/**
	 * Obtiene la lista de tipos materias. Los registros tienen que coincidir con tener en idTipoCatGral = 6
	 * @return
	 * @throws ServicioException
	 */
	List<CatGralDTO> findTiposMaterias() throws ServicioException;

}
