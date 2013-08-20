package mx.com.asteca.fachada;

import java.util.List;

import mx.com.asteca.comun.dto.NotificacionDTO;
import mx.com.asteca.servicio.NotificacionServicio;
import mx.com.asteca.servicio.ServicioException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificacionFachadaImpl implements NotificacionFachada{

	@Autowired
	private NotificacionServicio notificacionServicio;
	
	public Integer saveNotificacion(NotificacionDTO notificacion) throws FachadaException {
		//TODO validar reglas extras de negocio
		try {
			Integer pk = notificacionServicio.save(notificacion);
			return pk;
		} catch (ServicioException e) {
			throw new FachadaException("::Propagando exception de servicio::", e);
		}
	}

	public List<NotificacionDTO> getAllNotificacion() throws FachadaException {
		try {
			List<NotificacionDTO> listaNotificacion = notificacionServicio.getAll();
			return listaNotificacion;
		} catch (ServicioException e) {
			throw new FachadaException("::Propagando exception de servicio::", e);
		}
	}

}
