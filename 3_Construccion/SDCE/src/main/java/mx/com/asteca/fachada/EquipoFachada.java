package mx.com.asteca.fachada;

import java.util.List;

import mx.com.asteca.comun.dto.CatGralDTO;
import mx.com.asteca.comun.dto.EquipoDTO;

public interface EquipoFachada extends BaseFachada<EquipoDTO, Integer>{

	/**
	 * Obtiene la lista de tipos de equipo
	 * @return
	 * @throws FachadaException
	 */
	public List<CatGralDTO> getListaTipoEquipos() throws FachadaException;
	
	/**
	 * Obtiene la lista filtrada de equipos
	 * @param cve
	 * @return
	 * @throws FachadaException
	 */
	public List<EquipoDTO> findByClave(String cve) throws FachadaException;

	/**
	 * Obtiene la lista filtrada de equipos
	 * @param cve
	 * @param dsc
	 * @return
	 * @throws FachadaException
	 */
	List<EquipoDTO> findByClaveAndDsc(String cve, String dsc)
			throws FachadaException;

	/**
	 * Obtiene la lista filtrada de equipos
	 * @param cve
	 * @param dsc
	 * @param tipo
	 * @return
	 * @throws FachadaException
	 */
	List<EquipoDTO> findByClaveDscAndTipo(String cve, String dsc, String tipo)
			throws FachadaException;
}
