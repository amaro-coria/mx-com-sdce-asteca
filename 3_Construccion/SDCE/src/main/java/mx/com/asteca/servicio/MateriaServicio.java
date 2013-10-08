/**
 * 
 */
package mx.com.asteca.servicio;

import java.util.List;

import mx.com.asteca.comun.dto.MateriaDTO;
import mx.com.asteca.persistencia.entidades.Materias;

/**
 * @author JAMARO
 *
 */
public interface MateriaServicio extends
		BaseService<MateriaDTO, Integer, Materias> {

	List<MateriaDTO> findMateriasPorCurso(int idCurso) throws ServicioException;

}
