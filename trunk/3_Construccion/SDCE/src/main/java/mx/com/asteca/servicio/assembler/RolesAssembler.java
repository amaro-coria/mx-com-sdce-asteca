package mx.com.asteca.servicio.assembler;

import org.springframework.stereotype.Component;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.RolesDTO;
import mx.com.asteca.persistencia.entidades.Roles;

@Component(Constantes.ASSEMBLER_ROLES)
public class RolesAssembler extends Assembler<RolesDTO, Roles> {

	@Override
	public RolesDTO getDTOTransform(Roles mapping) {
		if(mapping == null) {
			return null;
		}
		RolesDTO dto = new RolesDTO();
		dto.setIdRol(mapping.getIdRol());
		dto.setNombre(mapping.getNombre());
		dto.setClave(mapping.getClave());
		dto.setActivo(mapping.getActivo());
		
		return dto;
	}

	@Override
	public Roles getMappingTransform(RolesDTO dto) {
		if(dto == null) {
			return null;
		}
		Roles mapping = new Roles();
		mapping.setIdRol(dto.getIdRol());
		mapping.setNombre(dto.getNombre());
		mapping.setClave(dto.getClave());
		mapping.setActivo(dto.getActivo());
		
		return mapping;
	}

}
