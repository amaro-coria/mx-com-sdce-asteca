/**
 * 
 */
package mx.com.asteca.persistencia.dao;

import java.util.List;

import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.entidades.Asentamientos;
import mx.com.asteca.persistencia.entidades.AsentamientosId;
import mx.com.asteca.persistencia.entidades.Municipios;

/**
 * @author JAMARO
 *
 */
public interface AsentamientoDAO extends
		BaseDAO<Asentamientos, AsentamientosId> {

	public List<Asentamientos> findByMunicipio(Municipios municipio) throws PersistenciaException;
}
