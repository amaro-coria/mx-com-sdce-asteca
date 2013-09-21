/**
 * 
 */
package mx.com.asteca.fachada;

import java.util.List;

import mx.com.asteca.comun.dto.AlumnoDTO;
import mx.com.asteca.comun.dto.AsentamientoDTO;
import mx.com.asteca.comun.dto.EstadoDTO;
import mx.com.asteca.comun.dto.EstatusDTO;
import mx.com.asteca.comun.dto.MunicipioDTO;
import mx.com.asteca.comun.dto.TipoEstudioDTO;
import mx.com.asteca.comun.dto.TipoLicenciaDTO;

/**
 * Interfaz de acceso a la fachada para AlumnoDTO
 * 
 * @author Jorge Amaro Coria
 * @version 1.0
 * @since 1.0
 * 
 */
public interface AlumnoFachada extends BaseFachada<AlumnoDTO, Integer> {

	/**
	 * Obtiene la lista de AlumnoDTO filtrada por matricula
	 * 
	 * @param matricula
	 * @return
	 * @throws FachadaException
	 */
	List<AlumnoDTO> findByMatricula(String matricula) throws FachadaException;

	/**
	 * Obtiene la lista de AlumnoDTO filtrada por nombreCompleto
	 * 
	 * @param nombreCompleto
	 * @return
	 * @throws FachadaException
	 */
	List<AlumnoDTO> findByNombre(String nombreCompleto) throws FachadaException;

	/**
	 * Obtiene la lista de AlumnoDTO filtrada por estatus
	 * 
	 * @param estatus
	 * @return
	 * @throws FachadaException
	 */
	List<AlumnoDTO> findByEstatus(String estatus) throws FachadaException;

	/**
	 * Obtiene la lista de AlumnoDTO filtrada por matricula y nombreCompleto
	 * 
	 * @param matricula
	 * @param nombreCompleto
	 * @return
	 * @throws FachadaException
	 */
	List<AlumnoDTO> findByMatriculaAndNombre(String matricula,
			String nombreCompleto) throws FachadaException;

	/**
	 * Obtiene la lista de AlumnoDTO filtrada por matricula y estatus
	 * 
	 * @param matricula
	 * @param estatus
	 * @return
	 * @throws FachadaException
	 */
	List<AlumnoDTO> findByMatriculaAndStatus(String matricula, String estatus)
			throws FachadaException;

	/**
	 * Obtiene la lista de AlumnoDTO filtrada por nombreCompleto y estatus
	 * 
	 * @param nombreCompleto
	 * @param estatus
	 * @return
	 * @throws FachadaException
	 */
	List<AlumnoDTO> findByNombreAndStatus(String nombreCompleto, String estatus)
			throws FachadaException;

	/**
	 * Obtiene la lista de AlumnoDTO filtrada por matricula, nombreCompleto y
	 * estatus
	 * 
	 * @param matricula
	 * @param nombreCompleto
	 * @param estatus
	 * @return
	 * @throws FachadaException
	 */
	List<AlumnoDTO> findByMatriculaNombreAndStatus(String matricula,
			String nombreCompleto, String estatus) throws FachadaException;
	
	AlumnoDTO populate(int alumnoID) throws FachadaException;

	List<Short> getDistincCPs() throws FachadaException;

	List<AsentamientoDTO> findAsentamientosByCp(short cp)
			throws FachadaException;

	MunicipioDTO findMunicipio(int idMunicipio, int idEstado)
			throws FachadaException;

	EstadoDTO findEstado(int idEstado) throws FachadaException;

	List<TipoLicenciaDTO> getTiposLicencia() throws FachadaException;

	TipoLicenciaDTO getLicencia(int idTipoLicencia) throws FachadaException;

	List<TipoEstudioDTO> getTiposEstudios() throws FachadaException;

	AsentamientoDTO findAsentamiento(int idAsentamiento, int idMunicipio,
			int idEstado) throws FachadaException;

	List<EstatusDTO> getEstatus() throws FachadaException;

	List<AlumnoDTO> getAlumnosDatosBasicos() throws FachadaException;

}
