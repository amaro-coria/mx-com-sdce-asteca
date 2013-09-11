package mx.com.asteca.servicio.impl;

import java.util.List;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.NotificacionDTO;
import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.dao.BaseDAO;
import mx.com.asteca.persistencia.dao.NotificacionDAO;
import mx.com.asteca.persistencia.entidades.Notificaciones;
import mx.com.asteca.servicio.NotificacionServicio;
import mx.com.asteca.servicio.ServicioException;
import mx.com.asteca.servicio.assembler.Assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Override
	@Transactional(readOnly=true)
	public List<NotificacionDTO> findByEstado(Long estado)
			throws ServicioException {
		try {
			List<Notificaciones> listNotificacion = notificacionDAO.findByEstado(estado);
			List<NotificacionDTO> listaDTOs = assemblerNotificacion.getDTOListTransform(listNotificacion);
			
			return listaDTOs;
		} catch (PersistenciaException e) {
			throw new ServicioException(e.getMessage(), e);
		}
		
	}

	@Override
	@Transactional(readOnly=true)
	public List<NotificacionDTO> findByTipo(Long tipo)
			throws ServicioException {
		try {
			List<Notificaciones> listNotificacion = notificacionDAO.findByTipo(tipo);
			List<NotificacionDTO> listaDTOs = assemblerNotificacion.getDTOListTransform(listNotificacion);
			
			return listaDTOs;
		} catch (PersistenciaException e) {
			throw new ServicioException(e.getMessage(), e);
		}
	}

	@Override
	@Transactional(readOnly=true)
	public List<NotificacionDTO> findByEstadoAndTipo(Long estado,
			Long tipo) throws ServicioException {

		try {
			List<Notificaciones> listNotificacion = notificacionDAO.findByEstadoAndTipo(estado, tipo);
			List<NotificacionDTO> listaDTOs = assemblerNotificacion.getDTOListTransform(listNotificacion);
			
			return listaDTOs;
		} catch (PersistenciaException e) {
			throw new ServicioException(e.getMessage(), e);
		}
	}

}
