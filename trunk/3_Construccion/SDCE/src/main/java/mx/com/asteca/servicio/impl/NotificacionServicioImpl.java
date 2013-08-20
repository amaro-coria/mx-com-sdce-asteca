package mx.com.asteca.servicio.impl;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.NotificacionDTO;
import mx.com.asteca.persistencia.dao.BaseDAO;
import mx.com.asteca.persistencia.dao.NotificacionDAO;
import mx.com.asteca.persistencia.entidades.Notificaciones;
import mx.com.asteca.servicio.NotificacionServicio;
import mx.com.asteca.servicio.assembler.Assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class NotificacionServicioImpl extends
		BaseServiceImpl<NotificacionDTO, Integer, Notificaciones> implements
		NotificacionServicio {

	@Autowired
	private NotificacionDAO notificacionDAO;
	@Autowired
	@Qualifier(Constantes.ASSEMBLER_NOTIFICACION)
	private Assembler assemblerNotificacion;

	@Override
	BaseDAO getDAO() {
		return notificacionDAO;
	}

	@Override
	Assembler getAssembler() {
		return assemblerNotificacion;
	}

}
