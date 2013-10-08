/**
 * 
 */
package mx.com.asteca.servicio;

import java.util.List;

import mx.com.asteca.comun.dto.MateriaRegistroDTO;
import mx.com.asteca.persistencia.entidades.MateriasRegistros;

/**
 * @author JAMARO
 *
 */
public interface MateriaRegistroServicio extends
		BaseService<MateriaRegistroDTO, Integer, MateriasRegistros> {

	List<MateriaRegistroDTO> findByNombre(String nombre)
			throws ServicioException;

	List<MateriaRegistroDTO> findByIdProgramaEstudio(int idProgramaEstudio) throws ServicioException;
}
