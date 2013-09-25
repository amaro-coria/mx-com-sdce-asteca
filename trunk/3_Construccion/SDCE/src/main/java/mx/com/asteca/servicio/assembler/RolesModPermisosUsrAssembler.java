package mx.com.asteca.servicio.assembler;

import org.springframework.stereotype.Component;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.RolesModPermisosUsrDTO;
import mx.com.asteca.persistencia.entidades.RolesModPermisosUsr;
import mx.com.asteca.persistencia.entidades.RolesModUsuarios;
import mx.com.asteca.persistencia.entidades.RolesModulos;

@Component(Constantes.ASSEMBLER_ROLES_MOD_PERMISOS_USR)
public class RolesModPermisosUsrAssembler extends
		Assembler<RolesModPermisosUsrDTO, RolesModPermisosUsr> {

	@Override
	public RolesModPermisosUsrDTO getDTOTransform(RolesModPermisosUsr mapping) {
		if(mapping == null) {
			return null;
		}
		RolesModPermisosUsrDTO dto = new RolesModPermisosUsrDTO();
		dto.setIdRolModPerUsr(mapping.getIdRolModPerUsr());
		dto.setIdRolesModulos(mapping.getRolesModulos().getIdRolMod());
		dto.setIdRolesModUsuarios(mapping.getRolesModUsuarios().getIdRolModUsr());
		
		return dto;
	}

	@Override
	public RolesModPermisosUsr getMappingTransform(RolesModPermisosUsrDTO dto) {
		if(dto == null) {
			return null;
		}
		RolesModPermisosUsr mapping = new RolesModPermisosUsr();
		mapping.setIdRolModPerUsr(dto.getIdRolModPerUsr());
		RolesModulos rolesModulos = new RolesModulos();
		rolesModulos.setIdRolMod(dto.getIdRolesModulos());
		mapping.setRolesModulos(rolesModulos);
		RolesModUsuarios rolesModUsuarios = new RolesModUsuarios();
		rolesModUsuarios.setIdRolModUsr(dto.getIdRolesModUsuarios());
		mapping.setRolesModUsuarios(rolesModUsuarios);
		
		return mapping;
	}

}
