/**
 * 
 */
package mx.com.asteca.servicio;

import java.util.List;

import mx.com.asteca.comun.dto.InstructorDTO;
import mx.com.asteca.persistencia.entidades.Instructores;

/**
 * @author JAMARO
 *
 */
public interface InstructorServicio extends
		BaseService<InstructorDTO, Integer, Instructores> {

	void removeDependencias(InstructorDTO dto) throws ServicioException;

	List<InstructorDTO> findInstructoresHabilitadosParaMateria(
			int idMateriaRegistro) throws ServicioException;

}
