package mx.com.asteca.servicio.assembler;

import org.springframework.stereotype.Component;

import mx.com.asteca.comun.Constantes;
import mx.com.asteca.comun.dto.RolesModulosDTO;
import mx.com.asteca.persistencia.entidades.Permisos;
import mx.com.asteca.persistencia.entidades.RolesModulos;

@Component(Constantes.ASSEMBLER_ROLES_MODULOS)
public class RolesModulosAssembler extends
		Assembler<RolesModulosDTO, RolesModulos> {

	@Override
	public RolesModulosDTO getDTOTransform(RolesModulos mapping) {
		if(mapping == null) {
			return null;
		}
		RolesModulosDTO dto = new RolesModulosDTO();
		dto.setIdRolMod(mapping.getIdRolMod());
		dto.setIdRol(mapping.getIdRol());
		dto.setIdModulo(mapping.getIdModulo());
		dto.setIdPermisos(mapping.getPermisos().getIdPermiso());
		dto.setActivo(mapping.getActivo());
		
		return dto;
	}

	@Override
	public RolesModulos getMappingTransform(RolesModulosDTO dto) {
		if(dto == null) {
			return null;
		}
		RolesModulos mapping = new RolesModulos();
		mapping.setIdRolMod(dto.getIdRolMod());
		mapping.setIdRol(dto.getIdRol());
		mapping.setIdModulo(dto.getIdModulo());
		Permisos permiso = new Permisos();
		permiso.setIdPermiso(dto.getIdPermisos());
		mapping.setPermisos(permiso);
		mapping.setActivo(dto.getActivo());
		
		return mapping;
	}

}
