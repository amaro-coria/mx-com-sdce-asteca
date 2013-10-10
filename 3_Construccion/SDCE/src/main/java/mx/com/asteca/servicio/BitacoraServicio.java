/**
 * 
 */
package mx.com.asteca.servicio;

import java.util.List;

import mx.com.asteca.comun.dto.BitacoraDTO;
import mx.com.asteca.persistencia.entidades.Bitacora;

/**
 * @author JAMARO
 *
 */
public interface BitacoraServicio extends
		BaseService<BitacoraDTO, Long, Bitacora> {

	List<BitacoraDTO> findByAccion(String accion) throws ServicioException;

	List<BitacoraDTO> findByIdUsuario(int idUsuario) throws ServicioException;

	List<BitacoraDTO> findByIdUsuarioAndAccion(int idUsuario, String accion)
			throws ServicioException;

}
