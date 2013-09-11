package mx.com.asteca.fachada.impl;

import java.util.List;

import mx.com.asteca.comun.dto.NotificacionDTO;
import mx.com.asteca.fachada.FachadaException;
import mx.com.asteca.fachada.NotificacionFachada;
import mx.com.asteca.servicio.BaseService;
import mx.com.asteca.servicio.NotificacionServicio;
import mx.com.asteca.servicio.ServicioException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificacionFachadaImpl extends BaseFachadaImpl<NotificacionDTO, Integer> implements NotificacionFachada{

	@Autowired
	private NotificacionServicio notificacionServicio;
	
	@Override
	BaseService getBaseService() {
		return notificacionServicio;
	}

	@Override
	public List<NotificacionDTO> getNotificacionByEstado(Long estado)
			throws FachadaException {
		try {
			List<NotificacionDTO> listaNotificacion = notificacionServicio.findByEstado(estado);
			return listaNotificacion;
		} catch (ServicioException e) {
			throw new FachadaException(e.getMessage(), e);
		}
	}
	
	@Override
	public List<NotificacionDTO> getNotificacionByTipo(Long tipo)
			throws FachadaException {
		try {
			List<NotificacionDTO> listaNotificacion = notificacionServicio.findByTipo(tipo);
			return listaNotificacion;
		} catch (ServicioException e) {
			throw new FachadaException(e.getMessage(), e);
		}
	}
	
	@Override
	public List<NotificacionDTO> getNotificacionByEstadoAndTipo(Long estado, Long tipo)
			throws FachadaException {
		try {
			List<NotificacionDTO> listaNotificacion = notificacionServicio.findByEstadoAndTipo(estado, tipo);
			return listaNotificacion;
		} catch (ServicioException e) {
			throw new FachadaException(e.getMessage(), e);
		}
	}
	

}
