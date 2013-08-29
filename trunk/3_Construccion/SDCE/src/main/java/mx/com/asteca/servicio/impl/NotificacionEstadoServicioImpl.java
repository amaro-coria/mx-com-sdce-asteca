package mx.com.asteca.servicio.impl;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.NotificacionEstadoDTO;
import mx.com.asteca.persistencia.dao.BaseDAO;
import mx.com.asteca.persistencia.dao.NotificacionEstadoDAO;
import mx.com.asteca.persistencia.entidades.NotificacionesEstados;
import mx.com.asteca.servicio.NotificacionEstadoServicio;
import mx.com.asteca.servicio.assembler.Assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class NotificacionEstadoServicioImpl extends
		BaseServiceImpl<NotificacionEstadoDTO, Integer, NotificacionesEstados> implements
		NotificacionEstadoServicio {

	@Autowired
	private NotificacionEstadoDAO notificacionEstadoDAO;
	@Autowired
	@Qualifier(Constantes.ASSEMBLER_NOTIFICACION_ESTADO)
	private Assembler assemblerNotificacionEstado;

	@Override
	BaseDAO getDAO() {
		return notificacionEstadoDAO;
	}

	@Override
	Assembler getAssembler() {
		return assemblerNotificacionEstado;
	}

}
