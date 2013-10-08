package mx.com.asteca.servicio;

import java.util.Date;
import java.util.List;

import mx.com.asteca.comun.dto.AulaDTO;
import mx.com.asteca.persistencia.entidades.Aulas;

/**
 * @author Jorge Amaro Coria
 * @version 1.0
 * @since 1.0
 *
 */
public interface AulaServicio extends BaseService<AulaDTO, Integer, Aulas> {

	/**
	 * Obtiene la lista de Aulas filtradas por Sede
	 * @param sede
	 * @return
	 * @throws ServicioException
	 */
	List<AulaDTO> findBySede(String sede) throws ServicioException;

	List<AulaDTO> findAulasDisponibles(Date timeStampInicial,
			Date timeStampFinal, int idSede) throws ServicioException;

}
