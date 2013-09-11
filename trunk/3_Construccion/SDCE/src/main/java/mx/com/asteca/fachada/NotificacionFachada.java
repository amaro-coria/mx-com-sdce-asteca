package mx.com.asteca.fachada;

import java.util.List;

import mx.com.asteca.comun.dto.NotificacionDTO;


public interface NotificacionFachada  extends BaseFachada<NotificacionDTO, Integer>{

	List<NotificacionDTO> getNotificacionByEstado(Long estado) throws FachadaException;
	
	List<NotificacionDTO> getNotificacionByTipo(Long tipo) throws FachadaException;
	
	List<NotificacionDTO> getNotificacionByEstadoAndTipo(Long estado, Long tipo) throws FachadaException;
	
}
