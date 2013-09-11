/**
 * 
 */
package mx.com.asteca.servicio;

import java.util.List;

import mx.com.asteca.comun.dto.NotificacionDTO;
import mx.com.asteca.persistencia.entidades.Notificaciones;

/**
 * @author Rabelt Ibarra Godinez.
 *
 */
public interface NotificacionServicio extends BaseService<NotificacionDTO, Integer, Notificaciones> {
	
	public List<NotificacionDTO> findByEstado(Long estado) throws ServicioException;;
	
	public List<NotificacionDTO> findByTipo(Long tipo) throws ServicioException;;
	
	public List<NotificacionDTO> findByEstadoAndTipo(Long estado, Long tipo) throws ServicioException;;
}
