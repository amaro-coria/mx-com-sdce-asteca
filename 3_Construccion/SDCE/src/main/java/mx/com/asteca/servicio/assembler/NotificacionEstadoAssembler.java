package mx.com.asteca.servicio.assembler;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.NotificacionEstadoDTO;
import mx.com.asteca.persistencia.entidades.NotificacionesEstados;

import org.springframework.stereotype.Component;

@Component(Constantes.ASSEMBLER_NOTIFICACION_ESTADO)
public class NotificacionEstadoAssembler extends Assembler<NotificacionEstadoDTO, NotificacionesEstados>{

	@Override
	public NotificacionEstadoDTO getDTOTransform(NotificacionesEstados mapping) {
		if(mapping == null || mapping.getIdNotifEdo() == 0){
			return null;
		}
		
		NotificacionEstadoDTO dto = new NotificacionEstadoDTO();
		dto.setIdNotifEdo(mapping.getIdNotifEdo());
		dto.setNombre(mapping.getNombre());

		return dto;
	}

	@Override
	public NotificacionesEstados getMappingTransform(NotificacionEstadoDTO dto) {
		if(dto == null){
			return null;
		}
		NotificacionesEstados mapping = new NotificacionesEstados();
		mapping.setIdNotifEdo(dto.getIdNotifEdo());
		mapping.setNombre(dto.getNombre());

		return mapping;
	}

}
