/**
 * 
 */
package mx.com.asteca.servicio;

import java.util.List;

import mx.com.asteca.comun.dto.ProgramaEstudiosDTO;
import mx.com.asteca.persistencia.entidades.ProgramaEstudios;

/**
 * @author JAMARO
 *
 */
public interface ProgramaEstudiosServicio extends
		BaseService<ProgramaEstudiosDTO, Integer, ProgramaEstudios> {

	List<ProgramaEstudiosDTO> findByDsc(String dsc) throws ServicioException;

	List<ProgramaEstudiosDTO> findByClave(String cve) throws ServicioException;

	List<ProgramaEstudiosDTO> findByTipo(int idTipo) throws ServicioException;

	List<ProgramaEstudiosDTO> findByClaveDscAndTipo(String cve, String dsc,
			int idTipo) throws ServicioException;

	List<ProgramaEstudiosDTO> findByClaveAndDsc(String cve, String dsc)
			throws ServicioException;

	List<ProgramaEstudiosDTO> findByClaveAndTipo(String cve, int idTipo)
			throws ServicioException;

	List<ProgramaEstudiosDTO> findByDscAndTipo(String dsc, int idTipo)
			throws ServicioException;

}
