/**
 * 
 */
package mx.com.asteca.fachada;

import java.util.List;

import mx.com.asteca.comun.dto.CatGralDTO;
import mx.com.asteca.comun.dto.MateriaRegistroDTO;
import mx.com.asteca.comun.dto.ProgramaEstudiosAutorizacionDTO;
import mx.com.asteca.comun.dto.ProgramaEstudiosDTO;
import mx.com.asteca.comun.dto.ProgramaEstudiosMateriasDTO;

/**
 * @author JAMARO
 *
 */
public interface ProgramaEstudiosFachada extends
		BaseFachada<ProgramaEstudiosDTO, Integer> {

	List<CatGralDTO> findTiposClasifCursos() throws FachadaException;

	List<MateriaRegistroDTO> findMaterias() throws FachadaException;

	MateriaRegistroDTO findMateriaById(int idMateria) throws FachadaException;

	String getRuta() throws FachadaException;

	int saveAutorizacion(ProgramaEstudiosAutorizacionDTO dto)
			throws FachadaException;

	int saveEstudioMateria(ProgramaEstudiosMateriasDTO dto)
			throws FachadaException;

	void saveOrUpdateAutorizacion(ProgramaEstudiosAutorizacionDTO dto)
			throws FachadaException;

	void saveOrUpdateEstudioMateria(ProgramaEstudiosMateriasDTO dto)
			throws FachadaException;

	void removeEstudioMateria(ProgramaEstudiosMateriasDTO dto)
			throws FachadaException;

	void removeAutorizacion(ProgramaEstudiosAutorizacionDTO dto)
			throws FachadaException;

	List<ProgramaEstudiosDTO> findByDsc(String dsc) throws FachadaException;

	List<ProgramaEstudiosDTO> findByClave(String cve) throws FachadaException;

	List<ProgramaEstudiosDTO> findByTipo(int idTipo) throws FachadaException;

	List<ProgramaEstudiosDTO> findByClaveDscAndTipo(String cve, String dsc,
			int idTipo) throws FachadaException;

	List<ProgramaEstudiosDTO> findByClaveAndDsc(String cve, String dsc)
			throws FachadaException;

	List<ProgramaEstudiosDTO> findByClaveAndTipo(String cve, int idTipo)
			throws FachadaException;

	List<ProgramaEstudiosDTO> findByDscAndTipo(String dsc, int idTipo)
			throws FachadaException;

}
