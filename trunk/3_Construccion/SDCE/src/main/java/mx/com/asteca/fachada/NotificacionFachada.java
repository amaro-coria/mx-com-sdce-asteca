package mx.com.asteca.fachada;

import java.util.List;

import mx.com.asteca.comun.dto.NotificacionDTO;

public interface NotificacionFachada {
	
	public Integer saveNotificacion(NotificacionDTO notificacion) throws FachadaException;

	public List<NotificacionDTO> getAllNotificacion() throws FachadaException;
}
