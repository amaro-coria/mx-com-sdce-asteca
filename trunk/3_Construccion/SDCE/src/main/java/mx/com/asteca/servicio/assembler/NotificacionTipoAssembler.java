package mx.com.asteca.servicio.assembler;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.NotificacionTipoDTO;
import mx.com.asteca.persistencia.entidades.NotificacionesTipos;

import org.springframework.stereotype.Component;

@Component(Constantes.ASSEMBLER_NOTIFICACION_TIPO)
public class NotificacionTipoAssembler extends Assembler<NotificacionTipoDTO, NotificacionesTipos>{

	@Override
	public NotificacionTipoDTO getDTOTransform(NotificacionesTipos mapping) {
		if(mapping == null || mapping.getIdNotifTipo() == 0){
			return null;
		}
		
		NotificacionTipoDTO dto = new NotificacionTipoDTO();
		dto.setIdNotifTipo(mapping.getIdNotifTipo());
		dto.setNombre(mapping.getNombre());

		return dto;
	}

	@Override
	public NotificacionesTipos getMappingTransform(NotificacionTipoDTO dto) {
		if(dto == null){
			return null;
		}
		NotificacionesTipos mapping = new NotificacionesTipos();
		mapping.setIdNotifTipo(dto.getIdNotifTipo());
		mapping.setNombre(dto.getNombre());

		return mapping;
	}

}
