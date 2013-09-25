package mx.com.asteca.servicio.assembler;


import org.springframework.stereotype.Component;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.RolesModUsuariosDTO;
import mx.com.asteca.persistencia.entidades.Personas;
import mx.com.asteca.persistencia.entidades.Roles;
import mx.com.asteca.persistencia.entidades.RolesModUsuarios;

@Component(Constantes.ASSEMBLER_ROLES_MOD_USUARIOS)
public class RolesModUsuariosAssembler extends
		Assembler<RolesModUsuariosDTO, RolesModUsuarios> {

	@Override
	public RolesModUsuariosDTO getDTOTransform(RolesModUsuarios mapping) {
		if(mapping == null) {
			return null;
		}
		RolesModUsuariosDTO dto = new RolesModUsuariosDTO();
		dto.setIdRolModUsr(mapping.getIdRolModUsr());
		dto.setIdRol(mapping.getRoles().getIdRol());
		dto.setIdPersona(mapping.getPersonas().getIdPersona());
		dto.setPeriodoFin(mapping.getPeriodoFin());
		dto.setPeriodoInicio(mapping.getPeriodoInicio());
		dto.setActivo(mapping.getActivo());
		
		return dto;
	}

	@Override
	public RolesModUsuarios getMappingTransform(RolesModUsuariosDTO dto) {
		if(dto == null) {
			return null;
		}
		RolesModUsuarios mapping = new RolesModUsuarios();
		mapping.setIdRolModUsr(dto.getIdRolModUsr());
		mapping.setPeriodoFin(dto.getPeriodoFin());
		mapping.setPeriodoInicio(dto.getPeriodoInicio());
		Roles roles = new Roles();
		roles.setIdRol(dto.getIdRol());
		mapping.setRoles(roles);
		Personas persona = new Personas();
		persona.setIdPersona(dto.getIdPersona());
		mapping.setPersonas(persona);
		mapping.setActivo(dto.getActivo());
		
		return mapping;
	}

}
