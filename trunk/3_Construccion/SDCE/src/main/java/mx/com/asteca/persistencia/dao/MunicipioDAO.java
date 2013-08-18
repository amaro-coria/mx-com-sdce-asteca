/**
 * 
 */
package mx.com.asteca.persistencia.dao;

import java.util.List;

import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.entidades.Estados;
import mx.com.asteca.persistencia.entidades.Municipios;
import mx.com.asteca.persistencia.entidades.MunicipiosId;

/**
 * @author JAMARO
 *
 */
public interface MunicipioDAO extends BaseDAO<Municipios, MunicipiosId> {

	public List<Municipios> findByEstado(Estados estado) throws PersistenciaException;
	
}
