package mx.com.asteca.persistencia.dao;

import java.util.List;

import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.entidades.Aulas;

/**
 * @author Jorge Amaro Coria
 * @version 1.0
 * @since 1.0
 *
 */
public interface AulaDAO extends BaseDAO<Aulas, Integer> {

	/**
	 * Obtiene la lista de aulas que coincidan con la descripcion de la sede
	 * @param sede
	 * @return
	 * @throws PersistenciaException
	 */
	List<Aulas> findBySede(String sede) throws PersistenciaException;

}
