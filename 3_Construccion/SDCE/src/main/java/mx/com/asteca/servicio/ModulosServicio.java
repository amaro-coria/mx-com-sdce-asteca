package mx.com.asteca.servicio;

import mx.com.asteca.comun.dto.ModulosDTO;
import mx.com.asteca.persistencia.entidades.Modulos;

public interface ModulosServicio extends
		BaseService<ModulosDTO, Integer, Modulos> {

	ModulosDTO buscarPorNombre(String nombre) throws ServicioException;
}
