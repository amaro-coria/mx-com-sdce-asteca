/**
 * 
 */
package mx.com.asteca.fachada;

import java.util.List;

import mx.com.asteca.comun.dto.AdministrativoDTO;
import mx.com.asteca.comun.dto.AsentamientoDTO;
import mx.com.asteca.comun.dto.EstadoDTO;
import mx.com.asteca.comun.dto.MunicipioDTO;

/**
 * @author JAMARO
 *
 */
public interface AdministrativoFachada extends
		BaseFachada<AdministrativoDTO, Integer> {

	List<AdministrativoDTO> findByNombre(String nombreCompleto)
			throws FachadaException;

	List<AdministrativoDTO> findByNombreClave(String nombreCompleto,
			String clave) throws FachadaException;

	List<AdministrativoDTO> findByClave(String clave) throws FachadaException;

	List<Integer> getDistincCPs() throws FachadaException;

	MunicipioDTO findMunicipio(int idMunicipio, int idEstado)
			throws FachadaException;

	EstadoDTO findEstado(int idEstado) throws FachadaException;

	List<AsentamientoDTO> findAsentamientosByCp(int cp) throws FachadaException;

	void delete(AdministrativoDTO dtoAdmin) throws FachadaException;

}
