package mx.com.asteca.servicio.impl;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.NotificacionTipoDTO;
import mx.com.asteca.persistencia.dao.BaseDAO;
import mx.com.asteca.persistencia.dao.NotificacionTipoDAO;
import mx.com.asteca.persistencia.entidades.NotificacionesTipos;
import mx.com.asteca.servicio.NotificacionTipoServicio;
import mx.com.asteca.servicio.assembler.Assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class NotificacionTipoServicioImpl extends
		BaseServiceImpl<NotificacionTipoDTO, Integer, NotificacionesTipos> implements
		NotificacionTipoServicio {

	@Autowired
	private NotificacionTipoDAO notificacionTipoDAO;
	@Autowired
	@Qualifier(Constantes.ASSEMBLER_NOTIFICACION_TIPO)
	private Assembler assemblerNotificacionTipo;

	@Override
	BaseDAO getDAO() {
		return notificacionTipoDAO;
	}

	@Override
	Assembler getAssembler() {
		return assemblerNotificacionTipo;
	}

}
