package mx.com.asteca.servicio;

import java.util.List;

import mx.com.asteca.comun.dto.ReporteAulasDTO;
import mx.com.asteca.fachada.FachadaException;
import mx.com.asteca.persistencia.entidades.ReporteAulas;

/**
 * @author Rabelt Ibarra Godinez
 * @version 1.0
 * @since 1.0
 *
 */
public interface ReporteAulaServicio extends BaseService<ReporteAulasDTO, Integer, ReporteAulas> {
	/**
	 * Obtien todos los reportes por Aula
	 * @return
	 * @throws FachadaException
	 * @throws ServicioException 
	 */
	List<ReporteAulasDTO> getAula() throws ServicioException;
	
}
