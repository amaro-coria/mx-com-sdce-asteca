package mx.com.asteca.fachada;

import java.util.List;

import mx.com.asteca.comun.dto.AulaDTO;
import mx.com.asteca.comun.dto.CatGralDTO;
import mx.com.asteca.servicio.ServicioException;

/**
 * @author Jorge Amaro Coria
 * @version 1.0
 * @since 1.0
 *
 */
public interface AulaFachada extends BaseFachada<AulaDTO, Integer> {

	/**
	 * Obtiene la lista de sedes
	 * @return
	 * @throws FachadaException
	 */
	List<CatGralDTO> getSedes() throws FachadaException;

	/**
	 * Obtiene la lista de aulas filtradas por sede
	 * @param sede
	 * @return
	 * @throws FachadaException
	 */
	List<AulaDTO> findBySede(String sede) throws FachadaException;

}
