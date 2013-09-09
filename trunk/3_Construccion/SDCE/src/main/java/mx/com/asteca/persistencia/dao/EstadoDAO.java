/**
 * 
 */
package mx.com.asteca.persistencia.dao;

import java.util.List;

import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.entidades.Estados;

/**
 * @author JAMARO
 *
 */
public interface EstadoDAO extends BaseDAO<Estados, Integer> {

	public List<Estados> getFromPais(int idPais) throws PersistenciaException;
	
}
