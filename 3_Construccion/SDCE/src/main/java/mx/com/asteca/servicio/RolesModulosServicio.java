package mx.com.asteca.servicio;

import java.util.List;

import mx.com.asteca.comun.dto.RolesModulosDTO;
import mx.com.asteca.persistencia.entidades.RolesModulos;

public interface RolesModulosServicio extends
		BaseService<RolesModulosDTO, Integer, RolesModulos> {

	List<RolesModulosDTO> buscarPorRol(int idRol);
}
