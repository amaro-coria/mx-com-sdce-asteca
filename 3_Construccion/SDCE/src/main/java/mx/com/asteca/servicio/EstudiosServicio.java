/**
 * 
 */
package mx.com.asteca.servicio;

import mx.com.asteca.comun.dto.EstudioDTO;
import mx.com.asteca.persistencia.entidades.Estudios;

/**
 * @author JAMARO
 *
 */
public interface EstudiosServicio extends BaseService<EstudioDTO, Integer, Estudios>{

	int saveEstudioPorAlumno(EstudioDTO estudio, int alumnoID)
			throws ServicioException;

}
