package mx.com.asteca.persistencia.dao.impl;

import java.util.List;

import mx.com.asteca.persistencia.PersistenciaException;
import mx.com.asteca.persistencia.dao.ReporteAulasDAO;
import mx.com.asteca.persistencia.entidades.ReporteAulas;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

/**
 * @author Rabelt Ibarra Godinez
 * @version 1.0
 * @since 1.0
 * 
 */
@Repository
public class ReporteAulasDAOImpl extends BaseDAOImpl<ReporteAulas, Integer>
		implements ReporteAulasDAO {

	@Override
	public List<ReporteAulas> findAulas() throws PersistenciaException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession()
					.createCriteria(ReporteAulas.class);
			@SuppressWarnings("unchecked")
			List<ReporteAulas> listaReporteAulas = criteria.list();
			return listaReporteAulas;
		} catch (Exception ex) {
			throw new PersistenciaException(
					"Error accediendo a la bd para ReporteAulas:sedes :: "
							+ ex.getMessage(), ex);
		}
	}
}
