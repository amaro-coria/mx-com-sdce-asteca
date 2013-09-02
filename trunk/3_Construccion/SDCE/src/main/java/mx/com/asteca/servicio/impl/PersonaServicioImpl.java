package mx.com.asteca.servicio.impl;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.NotificacionDTO;
import mx.com.asteca.persistencia.dao.BaseDAO;
import mx.com.asteca.persistencia.dao.NotificacionDAO;
import mx.com.asteca.persistencia.entidades.Notificaciones;
import mx.com.asteca.servicio.assembler.Assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PersonaServicioImpl extends
		BaseServiceImpl<NotificacionDTO, Integer, Notificaciones> {

	@Autowired
	private NotificacionDAO daoNotificacion;
	@Autowired
	@Qualifier(Constantes.ASSEMBLER_NOTIFICACION)
	private Assembler assembler;
	
	@Override
	BaseDAO getDAO() {
		return daoNotificacion;
	}

	@Override
	Assembler getAssembler() {
		return assembler;
	}

}
