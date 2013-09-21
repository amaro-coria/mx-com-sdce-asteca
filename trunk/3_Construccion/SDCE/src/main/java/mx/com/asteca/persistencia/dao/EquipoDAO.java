package mx.com.asteca.persistencia.dao;

import java.util.List;

import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.entidades.Equipos;
/**
 * 
 * @author ribarra
 *
 */
public interface EquipoDAO extends BaseDAO<Equipos, Integer>{

	/**
	 * Obtiene los equipos filtrados por clave
	 * @param cve
	 * @return
	 * @throws PersistenciaException
	 */
	public List<Equipos> findByClave(String cve) throws PersistenciaException;

	/**
	 * Obtiene los equipos filtrados por clave y descripcion
	 * @param cve
	 * @param dsc
	 * @return
	 * @throws PersistenciaException
	 */
	List<Equipos> findByClaveAndDsc(String cve, String dsc)
			throws PersistenciaException;

	/**
	 * Obtiene los equipos filtrados por clave, descripcion y tipo
	 * @param cve
	 * @param dsc
	 * @param tipo
	 * @return
	 * @throws PersistenciaException
	 */
	List<Equipos> findByClaveDscAndTipo(String cve, String dsc, String tipo)
			throws PersistenciaException;
}
