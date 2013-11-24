package mx.com.asteca.persistencia.dao;

import java.util.List;

import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.entidades.ReporteAulas;

/**
 * @author Rabelt Ibarra Godinez
 * @version 1.0
 * @since 1.0
 *
 */
public interface ReporteAulasDAO extends BaseDAO<ReporteAulas, Integer> {

	

	List<ReporteAulas> findAulas()
			throws PersistenciaException;

}
