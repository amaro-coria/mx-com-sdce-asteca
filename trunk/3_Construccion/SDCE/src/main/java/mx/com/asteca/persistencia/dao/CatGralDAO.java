/**
 * 
 */
package mx.com.asteca.persistencia.dao;

import java.util.List;

import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.entidades.CatGral;

/**
 * @author Jorge Amaro Coria
 * @since 1.0
 * @version 1.0
 *
 */
public interface CatGralDAO extends BaseDAO<CatGral, Integer> {

	/**
	 * Obtiene la lista de registros filtrados por la clave
	 * @param cve
	 * @return
	 * @throws PersistenciaException
	 */
	List<CatGral> findByCve(String cve) throws PersistenciaException;

	/**
	 * Obtiene la lista de registros filtrados por la clave y la descripcion
	 * @param cve
	 * @param dsc
	 * @return
	 * @throws PersistenciaException
	 */
	List<CatGral> findByCveAndDsc(String cve, String dsc)
			throws PersistenciaException;

	/**
	 * Obtiene la lista de registros filtrados por la clave, la descripcion y el estatus
	 * @param cve
	 * @param dsc
	 * @param estatus
	 * @return
	 * @throws PersistenciaException
	 */
	List<CatGral> findByCveDscAndEstatus(String cve, String dsc, String estatus)
			throws PersistenciaException;
	
	/**
	 * Obtiene la lista de registros. Tienen que tener el idTipoEquipo=1
	 * @return
	 * @throws PersistenciaException
	 */
	List<CatGral> findTiposEquipo() throws PersistenciaException;

	/**
	 * Obtiene la lista de registros. Tienen que tener el idTipoEquipo=3
	 * @return
	 * @throws PersistenciaException
	 */
	List<CatGral> findSedes() throws PersistenciaException;

	/**
	 * Obtiene la lista de registros. Tienen que tener el idTipoEquipo=2
	 * @return
	 * @throws PersistenciaException
	 */
	List<CatGral> findAreas() throws PersistenciaException;

	/**
	 * Obtiene la lista de registros. Tienen que tener el idTipoEquipo=4
	 * @return
	 * @throws PersistenciaException
	 */
	List<CatGral> findPaises() throws PersistenciaException;

	/**
	 * Obtiene la lista de registros. Tienen que tener el idTipoEquipo=5
	 * @return
	 * @throws PersistenciaException
	 */
	List<CatGral> findClasifCurso() throws PersistenciaException;

	/**
	 * Obtiene la lista de registros. Tienen que tener el idTipoEquipo=6
	 * @return
	 * @throws PersistenciaException
	 */
	List<CatGral> findMaterias() throws PersistenciaException;

}
