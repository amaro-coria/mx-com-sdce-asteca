package mx.com.asteca.servicio.assembler;

import org.springframework.stereotype.Component;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.RolesModPermisosSobreDTO;
import mx.com.asteca.persistencia.entidades.Permisos;
import mx.com.asteca.persistencia.entidades.RolesModPermisosSobre;
import mx.com.asteca.persistencia.entidades.RolesModPermisosUsr;

@Component(Constantes.ASSEMBLER_ROLES_MOD_PERMISOS_SOBRE)
public class RolesModPermisosSobreAssembler extends
		Assembler<RolesModPermisosSobreDTO, RolesModPermisosSobre> {

	@Override
	public RolesModPermisosSobreDTO getDTOTransform(
			RolesModPermisosSobre mapping) {
		if(mapping == null) {
			return null;
		}
		RolesModPermisosSobreDTO dto = new RolesModPermisosSobreDTO();
		dto.setIdRolModPermSobre(mapping.getIdRolModPermSobre());
		dto.setIdRolesModPermisosUsr(mapping.getRolesModPermisosUsr().getIdRolModPerUsr());
		dto.setIdPermisos(mapping.getPermisos().getIdPermiso());
		
		return dto;
	}

	@Override
	public RolesModPermisosSobre getMappingTransform(
			RolesModPermisosSobreDTO dto) {
		if(dto == null) {
			return null;
		}
		RolesModPermisosSobre mapping = new RolesModPermisosSobre();
		mapping.setIdRolModPermSobre(dto.getIdRolModPermSobre());
		RolesModPermisosUsr rolesModPermisosUsr = new RolesModPermisosUsr();
		rolesModPermisosUsr.setIdRolModPerUsr(dto.getIdRolesModPermisosUsr());
		mapping.setRolesModPermisosUsr(rolesModPermisosUsr);
		Permisos permiso = new Permisos();
		permiso.setIdPermiso(dto.getIdPermisos());
		mapping.setPermisos(permiso);
		
		return mapping;
	}

}
