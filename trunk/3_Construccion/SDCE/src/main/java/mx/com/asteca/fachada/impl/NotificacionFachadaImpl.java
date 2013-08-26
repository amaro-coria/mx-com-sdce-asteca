package mx.com.asteca.fachada.impl;

import mx.com.asteca.comun.dto.NotificacionDTO;
import mx.com.asteca.fachada.NotificacionFachada;
import mx.com.asteca.servicio.BaseService;
import mx.com.asteca.servicio.NotificacionServicio;

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

}
