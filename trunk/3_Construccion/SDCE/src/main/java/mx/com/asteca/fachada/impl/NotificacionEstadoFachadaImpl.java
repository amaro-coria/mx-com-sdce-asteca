package mx.com.asteca.fachada.impl;

import mx.com.asteca.comun.dto.NotificacionEstadoDTO;
import mx.com.asteca.fachada.NotificacionEstadoFachada;
import mx.com.asteca.servicio.BaseService;
import mx.com.asteca.servicio.NotificacionEstadoServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificacionEstadoFachadaImpl extends BaseFachadaImpl<NotificacionEstadoDTO, Integer> implements NotificacionEstadoFachada{

	@Autowired
	private NotificacionEstadoServicio notificacionEstadoServicio;
	
	@Override
	BaseService getBaseService() {
		return notificacionEstadoServicio;
	}

}
