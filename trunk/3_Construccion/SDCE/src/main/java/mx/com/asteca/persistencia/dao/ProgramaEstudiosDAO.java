/**
 * 
 */
package mx.com.asteca.persistencia.dao;

import java.util.List;

import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.entidades.ProgramaEstudios;

/**
 * @author JAMARO
 *
 */
public interface ProgramaEstudiosDAO extends
		BaseDAO<ProgramaEstudios, Integer> {

	List<ProgramaEstudios> findByClave(String cve) throws PersistenciaException;

	List<ProgramaEstudios> findByDsc(String dsc) throws PersistenciaException;

	List<ProgramaEstudios> findByTipo(int idTipo) throws PersistenciaException;

	List<ProgramaEstudios> findByClaveDscAndTipo(String cve, String dsc,
			int idTipo) throws PersistenciaException;

	List<ProgramaEstudios> findByCveAndDsc(String cve, String dsc)
			throws PersistenciaException;

	List<ProgramaEstudios> findByCveAndTipo(String cve, int idTipo)
			throws PersistenciaException;

	List<ProgramaEstudios> findByDscAndTipo(String dsc, int idTipo)
			throws PersistenciaException;

}
