/**
 * 
 */
package mx.com.asteca.servicio;

import mx.com.asteca.comun.dto.CapacidadDTO;
import mx.com.asteca.persistencia.entidades.Capacidades;

/**
 * @author JAMARO
 *
 */
public interface CapacidadServicio extends BaseService<CapacidadDTO, Integer, Capacidades> {

	/**
	 * Guarda una capacidad por alumno
	 * @param capacidad
	 * @param alumnoID
	 * @return
	 * @throws ServicioException
	 */
	int saveCapacidadPorAlumno(CapacidadDTO capacidad, int alumnoID)
			throws ServicioException;

}
