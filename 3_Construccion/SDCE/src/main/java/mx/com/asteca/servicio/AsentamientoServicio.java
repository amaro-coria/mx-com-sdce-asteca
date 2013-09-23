/**
 * 
 */
package mx.com.asteca.servicio;

import java.util.List;

import mx.com.asteca.comun.dto.AsentamientoDTO;
import mx.com.asteca.persistencia.entidades.Asentamientos;
import mx.com.asteca.persistencia.entidades.AsentamientosId;

/**
 * @author JAMARO
 *
 */
public interface AsentamientoServicio extends
		BaseService<AsentamientoDTO, AsentamientosId, Asentamientos> {

	List<Integer> getDistinctCPs() throws ServicioException;

	List<AsentamientoDTO> findAsentamientosByCp(int cp)
			throws ServicioException;

	AsentamientoDTO findAsentamiento(int idAsentamiento, int idMunicipio,
			int idEstado) throws ServicioException;

}
