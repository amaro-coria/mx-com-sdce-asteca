package mx.com.asteca.fachada;

import java.util.List;

import mx.com.asteca.comun.dto.ReporteAulasDTO;

/**
 * @author Rabelt Ibarra Godinez
 * @version 1.0
 * @since 1.0
 *
 */
public interface ReporteAulasFachada extends BaseFachada<ReporteAulasDTO, Integer> {

	/**
	 * Obtien todos los reportes por Aula
	 * @return
	 * @throws FachadaException
	 */
	List<ReporteAulasDTO> getAula() throws FachadaException;

	

}
