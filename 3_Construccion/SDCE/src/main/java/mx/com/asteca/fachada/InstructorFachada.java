/**
 * 
 */
package mx.com.asteca.fachada;

import java.util.List;

import mx.com.asteca.comun.dto.AsentamientoDTO;
import mx.com.asteca.comun.dto.DocumentoDTO;
import mx.com.asteca.comun.dto.EstadoDTO;
import mx.com.asteca.comun.dto.EstatusDTO;
import mx.com.asteca.comun.dto.InstructorDTO;
import mx.com.asteca.comun.dto.InstructorDocumentoDTO;
import mx.com.asteca.comun.dto.InstructorMateriaDTO;
import mx.com.asteca.comun.dto.MateriaRegistroDTO;
import mx.com.asteca.comun.dto.MunicipioDTO;
import mx.com.asteca.comun.dto.TipoInstructorDTO;

/**
 * @author JAMARO
 *
 */
public interface InstructorFachada extends BaseFachada<InstructorDTO, Integer> {

	int saveInstructorMateria(InstructorMateriaDTO dto) throws FachadaException;

	void saveOrUpdateInstructorMateria(InstructorMateriaDTO dto)
			throws FachadaException;

	void removeInstructorMateria(InstructorMateriaDTO dto)
			throws FachadaException;

	int saveInstructorDocumento(InstructorDocumentoDTO dto)
			throws FachadaException;

	void saveOrUpdateInstructorDocumento(InstructorDocumentoDTO dto)
			throws FachadaException;

	void removeInstructorDocumento(InstructorDocumentoDTO dto)
			throws FachadaException;

	List<TipoInstructorDTO> findTiposInstructores() throws FachadaException;

	List<MateriaRegistroDTO> findMateriasRegistros() throws FachadaException;

	List<EstatusDTO> getEstatus() throws FachadaException;

	String getRuta() throws FachadaException;

	AsentamientoDTO findAsentamiento(int idAsentamiento, int idMunicipio,
			int idEstado) throws FachadaException;

	MunicipioDTO findMunicipio(int idMunicipio, int idEstado)
			throws FachadaException;

	EstadoDTO findEstado(int idEstado) throws FachadaException;

	List<AsentamientoDTO> findAsentamientosByCp(int cp) throws FachadaException;

	List<Integer> getDistincCPs() throws FachadaException;

	int saveDocumento(DocumentoDTO dto) throws FachadaException;

	List<InstructorDTO> findInstructoresCapacitados(int idMateriaRegistro)
			throws FachadaException;

	TipoInstructorDTO findTipoInstructorById(short id) throws FachadaException;

	EstatusDTO findEstatusById(short id) throws FachadaException;

}
