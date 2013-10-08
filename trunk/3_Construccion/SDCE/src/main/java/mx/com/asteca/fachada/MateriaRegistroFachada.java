/**
 * 
 */
package mx.com.asteca.fachada;

import java.util.List;

import mx.com.asteca.comun.dto.MateriaRegistroDTO;

/**
 * @author JAMARO
 *
 */
public interface MateriaRegistroFachada extends
		BaseFachada<MateriaRegistroDTO, Integer> {

	List<MateriaRegistroDTO> findByNombre(String nombre)
			throws FachadaException;


}
