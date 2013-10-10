/**
 * 
 */
package mx.com.asteca.fachada;

import java.util.List;

import mx.com.asteca.comun.dto.BitacoraDTO;

/**
 * @author JAMARO
 *
 */
public interface BitacoraFachada extends BaseFachada<BitacoraDTO, Long> {

	List<BitacoraDTO> findByAccion(String accion) throws FachadaException;

	List<BitacoraDTO> findByIdUsuario(int idUsuario) throws FachadaException;

	List<BitacoraDTO> findByIdUsuarioAndAccion(int idUsuario, String accion)
			throws FachadaException;

}
