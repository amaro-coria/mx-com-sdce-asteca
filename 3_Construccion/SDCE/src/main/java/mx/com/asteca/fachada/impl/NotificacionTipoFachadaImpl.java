package mx.com.asteca.fachada.impl;

import mx.com.asteca.comun.dto.NotificacionTipoDTO;
import mx.com.asteca.fachada.NotificacionTipoFachada;
import mx.com.asteca.servicio.BaseService;
import mx.com.asteca.servicio.NotificacionTipoServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificacionTipoFachadaImpl extends BaseFachadaImpl<NotificacionTipoDTO, Integer> implements NotificacionTipoFachada{

	@Autowired
	private NotificacionTipoServicio notificacionTipoServicio;
	
	@Override
	BaseService getBaseService() {
		return notificacionTipoServicio;
	}

}
