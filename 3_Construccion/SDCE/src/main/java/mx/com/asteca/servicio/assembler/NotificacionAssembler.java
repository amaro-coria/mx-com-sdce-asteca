package mx.com.asteca.servicio.assembler;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.NotificacionDTO;
import mx.com.asteca.persistencia.entidades.Notificaciones;
import mx.com.asteca.persistencia.entidades.NotificacionesEstados;
import mx.com.asteca.persistencia.entidades.NotificacionesTipos;

import org.springframework.stereotype.Component;

@Component(Constantes.ASSEMBLER_NOTIFICACION)
public class NotificacionAssembler extends Assembler<NotificacionDTO, Notificaciones>{

	@Override
	public NotificacionDTO getDTOTransform(Notificaciones mapping) {
		if(mapping == null || mapping.getIdNotificacion() == 0){
			return null;
		}
		NotificacionDTO dto = new NotificacionDTO();
		dto.setFechaAlta(mapping.getFechaAlta());
		dto.setFechaLeido(mapping.getFechaLeido());
		dto.setIdNotificacion(mapping.getIdNotificacion());
		dto.setMensaje(mapping.getMensaje());
		
		NotificacionesEstados notificacionesEstados = mapping.getNotificacionesEstados();
		dto.setIdNotificacionesEstados(notificacionesEstados == null 
				? null : notificacionesEstados.getIdNotifEdo());
		
		NotificacionesTipos notificacionesTipos = mapping.getNotificacionesTipos();
		dto.setIdNotificacionesTipos(notificacionesTipos == null 
				? null : notificacionesTipos.getIdNotifTipo());
		
		return dto;
	}

	@Override
	public Notificaciones getMappingTransform(NotificacionDTO dto) {
		if(dto == null){
			return null;
		}
		Notificaciones mapping = new Notificaciones();
			mapping.setFechaAlta(dto.getFechaAlta());
			mapping.setFechaLeido(dto.getFechaLeido());
			mapping.setIdNotificacion(dto.getIdNotificacion());
			mapping.setMensaje(dto.getMensaje());

			if(dto.getIdNotificacionesEstados() != 0){
				NotificacionesEstados notificacionesEstados = new NotificacionesEstados((short) dto.getIdNotificacionesEstados());
				mapping.setNotificacionesEstados(notificacionesEstados);
			}
			if(dto.getIdNotificacionesTipos() != 0){
				NotificacionesTipos notificacionesTipos = new NotificacionesTipos((short) dto.getIdNotificacionesTipos());
				mapping.setNotificacionesTipos(notificacionesTipos);
			}
		return mapping;
	}

}
