/**
 * 
 */
package mx.com.asteca.fachada;

import java.util.Date;
import java.util.List;

import mx.com.asteca.comun.dto.CursoDTO;

public interface CursoFachada extends BaseFachada<CursoDTO, Integer> {

	/**
	 * Obtiene la lista de CursoDTO filtrada por instrictor sede area y periodo
	 * 
	 * @param instructor
	 * @param sede
	 * @param area
	 * @param fechaIni
	 * @param fechaFin
	 * @return
	 * @throws FachadaException
	 */
	List<CursoDTO> findByInstructorSedeAndArea(Integer instructor,
			Integer sede, Integer area, Date fechaIni, Date fachaFin)
			throws FachadaException;

}
