package mx.com.asteca.fachada;

import java.util.List;

import mx.com.asteca.comun.dto.RolesModulosDTO;

public interface RolesModulosFachada extends
		BaseFachada<RolesModulosDTO, Integer> {

	List<RolesModulosDTO> buscarPorRol(int idRol);
}
